
package hry.coin.tv;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import hry.core.util.log.LogFactory;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.List;

public class TvClientFactory {
    private static volatile TvClientFactory btsClientFactory;

    public static TvClientFactory getSingleton() {
        if (btsClientFactory == null) {
            synchronized (TvClientFactory.class) {
                if (btsClientFactory == null) {
                    btsClientFactory = new TvClientFactory();
                }
            }
        }
        return btsClientFactory;
    }

    public JSONObject headInfo(String method) {
        JSONObject sendObject = new JSONObject();
        sendObject.put("jsonrpc", "2.0");
        sendObject.put("id", "1");
        sendObject.put("method", method);
        return sendObject;
    }

    public boolean login(PrintWriter os, BufferedReader is, String username, String password) throws IOException {
        boolean flg = false;
        JSONObject sendObject = headInfo("login");
        JSONArray paramsObject = new JSONArray();
        paramsObject.add(username);
        paramsObject.add(password);
        sendObject.put("params", paramsObject);
        String sendMessage = sendObject.toJSONString();
        os.println(sendMessage);
        os.flush();
        String returnMessage = is.readLine();
        JSONObject returnObject = JSONObject.parseObject(returnMessage);
        String result = returnObject.getString("result");
        if ("true".equals(result)) {
            flg = true;
        } else {
            System.out.println("链接钱包失败");
        }
        return flg;
    }

    public String send(String method, List<Object> params, String addr, int port, String username, String password) {
        JSONObject sendObject = headInfo(method);
        JSONArray paramsObject = new JSONArray();
        paramsObject.addAll(params);
        sendObject.put("params", paramsObject);
        String sendMessage = sendObject.toJSONString();
        PrintWriter os = null;
        BufferedReader is = null;
        Socket socket = null;
        try {
            socket = new Socket(addr, port);
            os = new PrintWriter(socket.getOutputStream());
            is = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            JSONObject returnObject = null;
            if (login(os, is, username, password)) {
                os.println(sendMessage);
                os.flush();
                String returnMessage = is.readLine();
                returnObject = JSONObject.parseObject(returnMessage);
            }
            return returnObject.toJSONString();
        } catch (IOException e) {
            LogFactory.info("TV钱包接口连接失败");
        } finally {
            try {
                is.close();
            } catch (Throwable e) {
                LogFactory.info("TV钱包接口连接失败");
            }
            try {
                os.close();
            } catch (Throwable e) {
                LogFactory.info("TV钱包接口连接失败");
            }
            try {
                socket.close();
            } catch (Throwable e) {
                LogFactory.info("TV钱包接口连接失败");
            }
        }
        return null;
    }

}
