
package trade.hry.trade.websoketContext.model;


import java.math.BigDecimal;
import java.util.List;
import java.util.Map;


public class MarketDepths {
    public Map<String, List<BigDecimal[]>> depths;

    public Map<String, List<BigDecimal[]>> getDepths() {
        return this.depths;
    }

    public void setDepths(Map<String, List<BigDecimal[]>> depths) {
        this.depths = depths;
    }
}