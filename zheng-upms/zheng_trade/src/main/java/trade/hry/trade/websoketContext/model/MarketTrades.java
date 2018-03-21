
package trade.hry.trade.websoketContext.model;


import java.util.List;


public class MarketTrades {
    public List<MarketTradesSub> trades;

    public List<MarketTradesSub> getTrades() {
        return this.trades;
    }

    public void setTrades(List<MarketTradesSub> trades) {
        this.trades = trades;
    }
}