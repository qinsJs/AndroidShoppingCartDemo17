package walden.lib.cart;

import java.util.ArrayList;
import java.util.List;

import walden.lib.cart.model.DefaultIMerchantsModel;
import walden.lib.cart.model.MerchantsBean;
import walden.lib.cart.model.ShopBean;
import walden.lib.cart.usb.IMerchantsModel;
import walden.lib.cart.usb.IShopModel;

/**
 * <p>
 * Created by next on 17-1-19.
 */

public class ShopCartTools {


    /**
     * 协议对象转换为对象
     * <p>
     * 转换方法
     *
     * @return
     */
    public static List<ShopBean> transition(List<IShopModel> sd) {

        List<ShopBean> result = new ArrayList<>();

        if (sd == null || sd.size() == 0) return result;

        for (IShopModel s : sd) {
            ShopBean bean = new ShopBean(s);
            if (bean == null) continue;
            result.add(bean);
        }
        return result;
    }


    public static <T extends IMerchantsModel> List<MerchantsBean> transitionMerchants(List<T> imm) {

        List<MerchantsBean> result = new ArrayList<>();

        if (imm == null || imm.size() == 0) return result;

        for (IMerchantsModel i : imm) {
            MerchantsBean bean = new MerchantsBean(i);
            if (bean == null) continue;
            result.add(bean);
        }
        return result;
    }


    public static <T extends IShopModel> List<IMerchantsModel> dontMerchants(List<T> ismS) {
        IMerchantsModel model = new DefaultIMerchantsModel((List<IShopModel>) ismS);
        List<IMerchantsModel> result = new ArrayList<>();
        result.add(model);
        return result;
    }


}
