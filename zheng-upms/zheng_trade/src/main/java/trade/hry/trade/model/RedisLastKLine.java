
package trade.hry.trade.model;


import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import hry.core.util.date.DateUtil;
import hry.core.util.sys.ContextUtil;
import hry.exchange.kline2.model.LastKLinePayload;
import hry.redis.common.utils.RedisService;
import hry.trade.entrust.model.ExOrderInfo;
import org.springframework.util.StringUtils;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;


public class RedisLastKLine {

    public static void push24hours(List<ExOrderInfo> exOrderInfo) {

        long splitTime = DateUtil.addDaysToDate(new Date(), -1).getTime();
        JedisPool jedisPool = (JedisPool) ContextUtil.getBean("jedisPool");
        Jedis jedis = jedisPool.getResource();
        ExOrderInfo order = (ExOrderInfo) exOrderInfo.get(exOrderInfo.size() - 1);
        String rediskey = order.getCoinCode() + "_" + order.getFixPriceCoinCode() + ":new24hours";
        String volkey = order.getCoinCode() + "_" + order.getFixPriceCoinCode() + ":new24hoursvol";
        String volstr = jedis.get(volkey);
        BigDecimal vol = new BigDecimal(0);
        if (!StringUtils.isEmpty(volstr)) {
            vol = new BigDecimal(volstr);
        }

        BigDecimal out = new BigDecimal(0);
        Long llen = jedis.llen(rediskey);
        long pageSize = 50L;
        if (llen.longValue() <= pageSize) {
            List<String> list = jedis.lrange(rediskey, 0L, -1L);
            if ((list != null) && (list.size() > 0)) {
                int length = list.size() - 1;
                for (int i = length; i >= 0; i--) {
                    String str = (String) list.get(i);
                    JSONObject obj = JSON.parseObject(str);
                    long longValue = obj.getLongValue("transactionTime");
                    if (longValue >= splitTime) {
                        break;
                    }
                    jedis.rpop(rediskey);
                    out = out.add(new BigDecimal(obj.getLongValue("transactionSum")));
                }
            }

        } else {
            long page = 0L;
            if (llen.longValue() % pageSize == 0L) {
                page = llen.longValue() / pageSize;
            } else {
                page = llen.longValue() / pageSize + 1L;
            }
            for (int x = 1; x <= page; x++) {
                long start = x * pageSize * -1L;
                long end = -1L - (x - 1) * pageSize;
                List<String> list = jedis.lrange(rediskey, start, end);
                int length = list.size() - 1;
                for (int i = length; i >= 0; i--) {
                    String str = (String) list.get(i);
                    JSONObject obj = JSON.parseObject(str);
                    long longValue = obj.getLongValue("transactionTime");
                    if (longValue >= splitTime) {
                        break;
                    } else {
                        BigDecimal in = new BigDecimal(0);
                        for (ExOrderInfo info : exOrderInfo) {
                            JSONObject jsonObject = new JSONObject();
                            jsonObject.put("transactionPrice", info.getTransactionPrice());
                            jsonObject.put("transactionCount", info.getTransactionCount());
                            jsonObject.put("transactionSum", info.getTransactionSum());
                            jsonObject.put("transactionTime", Long.valueOf(info.getTransactionTime().getTime()));
                            jsonObject.put("transactionTimeStr", DateUtil.dateToString(info.getTransactionTime()));
                            jedis.lpush(rediskey, new String[]{jsonObject.toJSONString()});
                            in = in.add(info.getTransactionSum());
                            jedis.rpop(rediskey);
                            out = out.add(new BigDecimal(obj.getLongValue("transactionSum")));
                        }
                        vol = vol.add(in).subtract(out);
                        jedis.set(volkey, vol.toString());
                        jedis.close();
                    }
                }
            }

        }
    }

    public static void savePeriodLastKLineList(List<ExOrderInfo> exOrderInfo) {

        if ((exOrderInfo == null) || (exOrderInfo.size() == 0)) {
            /* 141 */
            return;

        }

        /* 144 */
        push24hours(exOrderInfo);


        /* 147 */
        ExOrderInfo order = (ExOrderInfo) exOrderInfo.get(exOrderInfo.size() - 1);

        /* 149 */
        BigDecimal amout = new BigDecimal(0);

        /* 151 */
        BigDecimal totalMoney = new BigDecimal(0);
        /* 152 */
        if (exOrderInfo.size() > 1) {
            /* 153 */
            for (ExOrderInfo o : exOrderInfo) {
                /* 154 */
                amout = amout.add(o.getTransactionCount());
                /* 155 */
                totalMoney = totalMoney.add(o.getTransactionSum());

            }

        } else {
            /* 158 */
            amout = order.getTransactionCount();
            /* 159 */
            totalMoney = order.getTransactionSum();

        }


        /* 163 */
        RedisService redisService = (RedisService) ContextUtil.getBean("redisService");

        /* 165 */
        String[] periods = {"1min", "5min", "15min", "30min", "60min", "1day", "1week", "1mon"};


        /* 168 */
        Map<String, Date> periodDate = DateUtil.getPeriodDate2(order.getTransactionTime());


        /* 171 */
        String redisList = redisService.get(order.getCoinCode() + "_" + order.getFixPriceCoinCode() + ":PeriodLastKLineList");

        /* 173 */
        List<LastKLinePayload> periodList = JSON.parseArray(redisList, LastKLinePayload.class);


        /* 176 */
        if (periodList == null) {
            /* 177 */
            periodList = new ArrayList();
            /* 178 */
            for (String p : periods) {
                /* 179 */
                LastKLinePayload lastKLinePayload = new LastKLinePayload();
                /* 180 */
                lastKLinePayload.setPeriod(p);

                /* 182 */
                lastKLinePayload.setPriceOpen(order.getTransactionPrice());
                /* 183 */
                lastKLinePayload.setPriceHigh(order.getTransactionPrice());
                /* 184 */
                lastKLinePayload.setPriceLow(order.getTransactionPrice());
                /* 185 */
                lastKLinePayload.setPriceLast(order.getTransactionPrice());
                /* 186 */
                lastKLinePayload.setAmount(amout);
                /* 187 */
                if ("1min".equals(p)) {
                    /* 188 */
                    lastKLinePayload.set_id(Long.valueOf(((Date) periodDate.get("1min")).getTime() / 1000L));

                    /* 190 */
                    lastKLinePayload.setTime(Long.valueOf(((Date) periodDate.get("1min")).getTime() / 1000L));

                    /* 192 */
                    lastKLinePayload.setStartTime(DateUtil.dateToString((Date) periodDate.get("1min"), "yyyy-MM-dd HH:mm"));

                    /* 194 */
                    lastKLinePayload.setEndTime(DateUtil.dateToString(DateUtil.addMinToDate((Date) periodDate.get("1min"), 1), "yyyy-MM-dd HH:mm"));

                }

                if ("5min".equals(p)) {
                    lastKLinePayload.set_id(Long.valueOf(((Date) periodDate.get("5min")).getTime() / 1000L));
                    lastKLinePayload.setTime(Long.valueOf(((Date) periodDate.get("5min")).getTime() / 1000L));
                    lastKLinePayload.setStartTime(DateUtil.dateToString((Date) periodDate.get("5min"), "yyyy-MM-dd HH:mm"));
                    lastKLinePayload.setEndTime(DateUtil.dateToString(DateUtil.addMinToDate((Date) periodDate.get("5min"), 5), "yyyy-MM-dd HH:mm"));

                }
                if ("15min".equals(p)) {
                    lastKLinePayload.set_id(Long.valueOf(((Date) periodDate.get("15min")).getTime() / 1000L));
                    lastKLinePayload.setTime(Long.valueOf(((Date) periodDate.get("15min")).getTime() / 1000L));
                    lastKLinePayload.setStartTime(DateUtil.dateToString((Date) periodDate.get("15min"), "yyyy-MM-dd HH:mm"));
                    lastKLinePayload.setEndTime(DateUtil.dateToString(DateUtil.addMinToDate((Date) periodDate.get("15min"), 15), "yyyy-MM-dd HH:mm"));

                }
                if ("30min".equals(p)) {
                    lastKLinePayload.set_id(Long.valueOf(((Date) periodDate.get("30min")).getTime() / 1000L));
                    lastKLinePayload.setTime(Long.valueOf(((Date) periodDate.get("30min")).getTime() / 1000L));
                    lastKLinePayload.setStartTime(DateUtil.dateToString((Date) periodDate.get("30min"), "yyyy-MM-dd HH:mm"));
                    lastKLinePayload.setEndTime(DateUtil.dateToString(DateUtil.addMinToDate((Date) periodDate.get("30min"), 30), "yyyy-MM-dd HH:mm"));

                }
                if ("60min".equals(p)) {
                    lastKLinePayload.set_id(Long.valueOf(((Date) periodDate.get("60min")).getTime() / 1000L));
                    lastKLinePayload.setTime(Long.valueOf(((Date) periodDate.get("60min")).getTime() / 1000L));
                    lastKLinePayload.setStartTime(DateUtil.dateToString((Date) periodDate.get("60min"), "yyyy-MM-dd HH:mm"));
                    lastKLinePayload.setEndTime(DateUtil.dateToString(DateUtil.addMinToDate((Date) periodDate.get("60min"), 60), "yyyy-MM-dd HH:mm"));
                }
                if ("1day".equals(p)) {
                    lastKLinePayload.set_id(Long.valueOf(((Date) periodDate.get("1day")).getTime() / 1000L));
                    lastKLinePayload.setTime(Long.valueOf(((Date) periodDate.get("1day")).getTime() / 1000L));
                    lastKLinePayload.setStartTime(DateUtil.dateToString((Date) periodDate.get("1day"), "yyyy-MM-dd HH:mm"));
                    lastKLinePayload.setEndTime(DateUtil.dateToString(DateUtil.addDaysToDate((Date) periodDate.get("1day"), 1), "yyyy-MM-dd HH:mm"));
                    lastKLinePayload.setDayTotalDealAmount(new BigDecimal(0).add(order.getTransactionCount().multiply(order.getTransactionPrice())));
                }
                /* 227 */
                if ("1week".equals(p)) {
                    /* 228 */
                    lastKLinePayload.set_id(Long.valueOf(((Date) periodDate.get("1week")).getTime() / 1000L));
                    /* 229 */
                    lastKLinePayload.setTime(Long.valueOf(((Date) periodDate.get("1week")).getTime() / 1000L));
                    /* 230 */
                    lastKLinePayload.setStartTime(DateUtil.dateToString((Date) periodDate.get("1week"), "yyyy-MM-dd HH:mm"));
                    /* 231 */
                    lastKLinePayload.setEndTime(DateUtil.dateToString(DateUtil.addDaysToDate((Date) periodDate.get("1week"), 7), "yyyy-MM-dd HH:mm"));

                }
                if ("1mon".equals(p)) {
                    lastKLinePayload.set_id(Long.valueOf(((Date) periodDate.get("1mon")).getTime() / 1000L));
                    lastKLinePayload.setTime(Long.valueOf(((Date) periodDate.get("1mon")).getTime() / 1000L));
                    lastKLinePayload.setStartTime(DateUtil.dateToString((Date) periodDate.get("1mon"), "yyyy-MM-dd HH:mm"));
                    lastKLinePayload.setEndTime(DateUtil.dateToString(DateUtil.addMonth((Date) periodDate.get("1mon"), 1), "yyyy-MM-dd HH:mm"));
                }
                periodList.add(lastKLinePayload);
            }
            redisService.save(order.getCoinCode() + "_" + order.getFixPriceCoinCode() + ":PeriodLastKLineList", JSON.toJSONString(periodList));
        } else {
            for (LastKLinePayload lastKLinePayload : periodList) {
                String period = lastKLinePayload.getPeriod();
                LastKLinePayload lkp = new LastKLinePayload();
                /* 249 */
                lkp.setPriceOpen(lastKLinePayload.getPriceOpen());
                /* 250 */
                lkp.setPriceHigh(lastKLinePayload.getPriceHigh());
                /* 251 */
                lkp.setPriceLow(lastKLinePayload.getPriceLow());
                /* 252 */
                lkp.setPriceLast(lastKLinePayload.getPriceLast());
                /* 253 */
                lkp.setAmount(lastKLinePayload.getAmount());
                /* 254 */
                lkp.setStartTime(lastKLinePayload.getStartTime());
                /* 255 */
                lkp.setEndTime(lastKLinePayload.getEndTime());
                /* 256 */
                lkp.setPeriod(period);


                /* 259 */
                if ("1min".equals(period)) {
                    /* 260 */
                    boolean type = flushLastKlinePayLoad(periodDate, lastKLinePayload, order, amout, totalMoney, period);
                    /* 261 */
                    if (type) {
                        /* 262 */
                        backups(order, lkp, period);

                    }

                }
                /* 265 */
                if ("5min".equals(period)) {
                    /* 266 */
                    boolean type = flushLastKlinePayLoad(periodDate, lastKLinePayload, order, amout, totalMoney, period);
                    /* 267 */
                    if (type) {
                        /* 268 */
                        backups(order, lkp, period);

                    }

                }
                /* 271 */
                if ("15min".equals(period)) {
                    /* 272 */
                    boolean type = flushLastKlinePayLoad(periodDate, lastKLinePayload, order, amout, totalMoney, period);
                    /* 273 */
                    if (type) {
                        /* 274 */
                        backups(order, lkp, period);

                    }

                }
                /* 277 */
                if ("30min".equals(period)) {
                    /* 278 */
                    boolean type = flushLastKlinePayLoad(periodDate, lastKLinePayload, order, amout, totalMoney, period);
                    /* 279 */
                    if (type) {
                        /* 280 */
                        backups(order, lkp, period);

                    }

                }
                /* 283 */
                if ("60min".equals(period)) {
                    /* 284 */
                    boolean type = flushLastKlinePayLoad(periodDate, lastKLinePayload, order, amout, totalMoney, period);
                    /* 285 */
                    if (type) {
                        /* 286 */
                        backups(order, lkp, period);

                    }

                }
                /* 289 */
                if ("1day".equals(period)) {
                    /* 290 */
                    boolean type = flushLastKlinePayLoad(periodDate, lastKLinePayload, order, amout, totalMoney, period);
                    /* 291 */
                    if (type) {
                        /* 292 */
                        backups(order, lkp, period);

                    }

                }
                /* 295 */
                if ("1week".equals(period)) {
                    /* 296 */
                    boolean type = flushLastKlinePayLoad(periodDate, lastKLinePayload, order, amout, totalMoney, period);
                    /* 297 */
                    if (type) {
                        /* 298 */
                        backups(order, lkp, period);

                    }

                }
                /* 301 */
                if ("1mon".equals(period)) {
                    /* 302 */
                    boolean type = flushLastKlinePayLoad(periodDate, lastKLinePayload, order, amout, totalMoney, period);
                    /* 303 */
                    if (type) {
                        /* 304 */
                        backups(order, lkp, period);

                    }

                }

            }


            /* 310 */
            redisService.save(order.getCoinCode() + "_" + order.getFixPriceCoinCode() + ":PeriodLastKLineList", JSON.toJSONString(periodList));

        }

    }


    public static void backups(ExOrderInfo order, LastKLinePayload lkp, String period) {
        /* 316 */
        RedisService redisService = (RedisService) ContextUtil.getBean("redisService");
        /* 317 */
        String backup = redisService.get(order.getCoinCode() + "_" + order.getFixPriceCoinCode() + ":PeriodLastKLineList_backups");
        /* 318 */
        if (!StringUtils.isEmpty(backup)) {
            /* 319 */
            JSONObject obj = JSON.parseObject(backup);
            /* 320 */
            obj.put(period, lkp);
            /* 321 */
            redisService.save(order.getCoinCode() + "_" + order.getFixPriceCoinCode() + ":PeriodLastKLineList_backups", JSON.toJSONString(obj));

        } else {
            /* 323 */
            JSONObject obj = new JSONObject();
            /* 324 */
            obj.put(period, lkp);
            /* 325 */
            redisService.save(order.getCoinCode() + "_" + order.getFixPriceCoinCode() + ":PeriodLastKLineList_backups", JSON.toJSONString(obj));
        }

    }


    public static boolean flushLastKlinePayLoad(Map<String, Date> periodDate, LastKLinePayload lastKLinePayload, ExOrderInfo exOrderInfo, BigDecimal amout, BigDecimal totalMoney, String period) {
        /* 342 */
        Date date = (Date) periodDate.get(period);




        /* 347 */
        if ("1day".equals(period)) {
            /* 348 */
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            /* 349 */
            String d1 = sdf.format(new Date(lastKLinePayload.getTime().longValue() * 1000L));
            /* 350 */
            String d2 = sdf.format(exOrderInfo.getTransactionTime());
            /* 351 */
            if (!d1.equals(d2)) {
                /* 352 */
                lastKLinePayload.setDayTotalDealAmount(totalMoney);

            } else {
                /* 355 */
                lastKLinePayload.setDayTotalDealAmount(lastKLinePayload.getDayTotalDealAmount().add(totalMoney));

            }

        }



        /* 361 */
        if (lastKLinePayload.getTime().compareTo(Long.valueOf(date.getTime() / 1000L)) == 0) {
            /* 362 */
            if (exOrderInfo.getTransactionPrice().compareTo(lastKLinePayload.getPriceHigh()) > 0) {
                /* 363 */
                lastKLinePayload.setPriceHigh(exOrderInfo.getTransactionPrice());

            }
            /* 365 */
            if (exOrderInfo.getTransactionPrice().compareTo(lastKLinePayload.getPriceLow()) < 0) {
                /* 366 */
                lastKLinePayload.setPriceLow(exOrderInfo.getTransactionPrice());

            }
            /* 368 */
            lastKLinePayload.setPriceLast(exOrderInfo.getTransactionPrice());
            /* 369 */
            lastKLinePayload.setAmount(lastKLinePayload.getAmount().add(amout));


            /* 372 */
            return false;

        }
        lastKLinePayload.setPriceOpen(exOrderInfo.getTransactionPrice());
        lastKLinePayload.setPriceHigh(exOrderInfo.getTransactionPrice());
        lastKLinePayload.setPriceLow(exOrderInfo.getTransactionPrice());
        lastKLinePayload.setPriceLast(exOrderInfo.getTransactionPrice());
        lastKLinePayload.setAmount(amout);
        lastKLinePayload.set_id(Long.valueOf(date.getTime() / 1000L));
        lastKLinePayload.setTime(Long.valueOf(date.getTime() / 1000L));
        if ("1min".equals(period)) {
            lastKLinePayload.setStartTime(DateUtil.dateToString((Date) periodDate.get("1min"), "yyyy-MM-dd HH:mm"));
            lastKLinePayload.setEndTime(DateUtil.dateToString(DateUtil.addMinToDate((Date) periodDate.get("1min"), 1), "yyyy-MM-dd HH:mm"));

        }
        /* 386 */
        if ("5min".equals(period)) {
            lastKLinePayload.setStartTime(DateUtil.dateToString((Date) periodDate.get("5min"), "yyyy-MM-dd HH:mm"));
            lastKLinePayload.setEndTime(DateUtil.dateToString(DateUtil.addMinToDate((Date) periodDate.get("5min"), 5), "yyyy-MM-dd HH:mm"));

        }
        if ("15min".equals(period)) {
            lastKLinePayload.setStartTime(DateUtil.dateToString((Date) periodDate.get("15min"), "yyyy-MM-dd HH:mm"));
            lastKLinePayload.setEndTime(DateUtil.dateToString(DateUtil.addMinToDate((Date) periodDate.get("15min"), 15), "yyyy-MM-dd HH:mm"));

        }
        /* 394 */
        if ("30min".equals(period)) {
            /* 395 */
            lastKLinePayload.setStartTime(DateUtil.dateToString((Date) periodDate.get("30min"), "yyyy-MM-dd HH:mm"));
            /* 396 */
            lastKLinePayload.setEndTime(DateUtil.dateToString(DateUtil.addMinToDate((Date) periodDate.get("30min"), 30), "yyyy-MM-dd HH:mm"));

        }
        if ("60min".equals(period)) {
            lastKLinePayload.setStartTime(DateUtil.dateToString((Date) periodDate.get("60min"), "yyyy-MM-dd HH:mm"));
            lastKLinePayload.setEndTime(DateUtil.dateToString(DateUtil.addMinToDate((Date) periodDate.get("60min"), 60), "yyyy-MM-dd HH:mm"));
        }
        if ("1day".equals(period)) {
            lastKLinePayload.setStartTime(DateUtil.dateToString((Date) periodDate.get("1day"), "yyyy-MM-dd HH:mm"));
            lastKLinePayload.setEndTime(DateUtil.dateToString(DateUtil.addDaysToDate((Date) periodDate.get("1day"), 1), "yyyy-MM-dd HH:mm"));
        }
        if ("1week".equals(period)) {
            lastKLinePayload.setStartTime(DateUtil.dateToString((Date) periodDate.get("1week"), "yyyy-MM-dd HH:mm"));
            lastKLinePayload.setEndTime(DateUtil.dateToString(DateUtil.addDaysToDate((Date) periodDate.get("1week"), 7), "yyyy-MM-dd HH:mm"));
        }
        if ("1mon".equals(period)) {
            lastKLinePayload.setStartTime(DateUtil.dateToString((Date) periodDate.get("1mon"), "yyyy-MM-dd HH:mm"));
            lastKLinePayload.setEndTime(DateUtil.dateToString(DateUtil.addMonth((Date) periodDate.get("1mon"), 1), "yyyy-MM-dd HH:mm"));
        }
        return true;
    }
}
