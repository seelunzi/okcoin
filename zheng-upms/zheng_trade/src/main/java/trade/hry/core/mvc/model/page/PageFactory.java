
package trade.hry.core.mvc.model.page;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;

import java.io.Serializable;
import java.util.Map;


public class PageFactory<T extends Serializable> {
    public static Page getPage(Map<String, String> map) {
        Page page = null;
        Integer offset = Integer.valueOf((String) map.get("offset"));
        Integer limit = Integer.valueOf((String) map.get("limit"));
        if (limit.intValue() == -1) {
            page = PageHelper.startPage(offset.intValue() / limit.intValue() + 1, 0);
        } else {
            page = PageHelper.startPage(offset.intValue() / limit.intValue() + 1, limit.intValue());
        }
        return page;
    }

}
