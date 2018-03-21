
package hry.coin.znhy.util;


import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.math.BigInteger;
import java.nio.charset.Charset;
import java.util.Properties;


public class Consts {
    private static Properties p = new Properties();

    static {
        InputStream in = Consts.class.getResourceAsStream("/config.properties");
        InputStreamReader r = new InputStreamReader(in, Charset.forName("UTF-8"));
        try {
            p.load(r);
            in.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static BigInteger GAS_PRICE = BigInteger.valueOf(20000000000L);
    public static BigInteger GAS_LIMIT = BigInteger.valueOf(4300000L);
    public static BigInteger ETHER = new BigInteger("1000000000000000000");
    public static String PASSWORD = p.getProperty("password");
    public static String PATH = p.getProperty("path");
    public static String DIRECTORY = p.getProperty("directory");
    public static String HELLOWORLD_ADDR = p.getProperty("helloworldAddr");
}

