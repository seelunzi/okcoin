
package trade.hry.trade.listener;

import hry.core.quartz.QuartzJob;
import hry.core.quartz.QuartzManager;
import hry.core.quartz.ScheduleJob;
import hry.core.util.StringUtil;
import hry.core.util.log.LogFactory;
import hry.core.util.properties.PropertiesUtils;
import hry.core.util.sys.AppUtils;
import hry.core.util.sys.ContextUtil;
import org.springframework.web.context.ContextLoaderListener;
import trade.hry.trade.entrust.service.ExEntrustService;

import javax.servlet.ServletContextEvent;


public class StartupListener
        extends ContextLoaderListener {

    @Override
    public void contextInitialized(ServletContextEvent event) {
        super.contextInitialized(event);
        AppUtils.init(event.getServletContext());
        LogFactory.info("--------------------------------------------------------------------------------");
        LogFactory.info("---------------------------------加载应用app----------------------------------------");
        LogFactory.info("------------------" + PropertiesUtils.APP.getProperty("app.loadApp") + "-----------------");
        String isStartRobot = PropertiesUtils.APP.getProperty("app.isStartRobot");
        if ((!StringUtil.isNull(isStartRobot)) && (isStartRobot.equals("true"))) {
            ScheduleJob autoAddExEntrust = new ScheduleJob();
            autoAddExEntrust.setSpringId("exEntrustService");
            autoAddExEntrust.setMethodName("autoAddExEntrust");
            QuartzManager.addJob("autoAddExEntrust", autoAddExEntrust, QuartzJob.class, "0/4 * * * * ?");
            ScheduleJob cancelAutoAddExEntrust = new ScheduleJob();
            cancelAutoAddExEntrust.setSpringId("exEntrustService");
            cancelAutoAddExEntrust.setMethodName("cancelAutoAddExEntrust");
            QuartzManager.addJob("cancelAutoAddExEntrust", cancelAutoAddExEntrust, QuartzJob.class, "0/30 * * * * ?");
        }
        ScheduleJob reidsToMysql = new ScheduleJob();
        reidsToMysql.setSpringId("exOrderInfoService");
        reidsToMysql.setMethodName("reidsToMysqlmq");
        QuartzManager.addJob("reidsToMysql", reidsToMysql, QuartzJob.class, "0/2 * * * * ?");
        try {
            Thread.sleep(300L);
        } catch (InterruptedException e) {
            e.printStackTrace();

        }
        ScheduleJob reidsToredisLog = new ScheduleJob();
        reidsToredisLog.setSpringId("exOrderInfoService");
        reidsToredisLog.setMethodName("reidsToredisLogmq");
        QuartzManager.addJob("reidsToredisLog", reidsToredisLog, QuartzJob.class, "0/2 * * * * ?");
        ScheduleJob jobRunTimepushMarket = new ScheduleJob();
        jobRunTimepushMarket.setSpringId("webSocketScheduleService");
        jobRunTimepushMarket.setMethodName("pushMarket");
        ExEntrustService exEntrustService = (ExEntrustService) ContextUtil.getBean("exEntrustService");
        long start1 = System.currentTimeMillis();
        exEntrustService.tradeInit();
        long end = System.currentTimeMillis();
        System.out.println("初始化交易数据：");
        System.out.println(end - start1);
    }
}
