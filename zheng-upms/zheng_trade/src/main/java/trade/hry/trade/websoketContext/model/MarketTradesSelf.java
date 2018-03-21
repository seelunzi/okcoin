
package trade.hry.trade.websoketContext.model;


import hry.trade.entrust.model.ExOrder;

import java.util.List;


public class MarketTradesSelf {
    public List<ExOrder> trades;

    public List<ExOrder> getTrades() {
        return this.trades;
    }

    public void setTrades(List<ExOrder> trades) {
        this.trades = trades;
    }
}
