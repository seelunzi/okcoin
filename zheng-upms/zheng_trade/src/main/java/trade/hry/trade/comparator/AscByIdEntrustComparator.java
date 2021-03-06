
package trade.hry.trade.comparator;


import hry.trade.redis.model.EntrustTrade;

import java.util.Comparator;


public class AscByIdEntrustComparator
        implements Comparator<EntrustTrade> {

    public int compare(EntrustTrade o1, EntrustTrade o2) {
        /* 12 */
        if (o1.getId().compareTo(o2.getId()) == -1)
            /* 13 */ return 1;
        /* 14 */
        if (o1.getId().compareTo(o2.getId()) == 1) {
            /* 15 */
            return -1;

        }
        /* 17 */
        return 0;

    }

}


/* Location:              E:\trade.war!\WEB-INF\classes\hry\trade\comparator\AscByIdEntrustComparator.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       0.7.1
 */