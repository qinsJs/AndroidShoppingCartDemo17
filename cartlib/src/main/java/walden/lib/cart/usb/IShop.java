package walden.lib.cart.usb;

import java.util.Collection;

import walden.lib.cart.model.MerchantsBean;
import walden.lib.cart.model.ShopBean;

/**
 * 商品操作
 * <p>
 * 感觉不妙，眉间微绕
 * 子或将别，心里闹闹
 * 谋事于人，成事看天
 * 子夏若弦，或夏非语
 * <p>
 * Created by next on 17-1-18.
 */

public interface IShop {
    /**
     * 添加一个商品
     *
     * @param b
     */
    void addShop(String merchantsId, IShopModel b);


    /**
     * 砍手,添加分多分多分多商品进入购物车
     *
     * @param shops
     */
    void cutHand(String merchantsId, Collection<IShopModel> shops);

    /**
     * 删除商品
     *
     * @param id
     */
    void deleteShop(String id);


    /**
     * 删除商品
     *
     * @param ids
     */
    void deleteShop(Collection<String> ids);


    /**
     * 商品数量+1
     *
     * @param id
     */
    void shopAdd(String id);

    /**
     * 商品数量-1
     *
     * @param id
     */
    void shopMinus(String id);


    /**
     * 改变商品数量
     *
     * @param count
     * @param id
     */
    void changeShopCount(int count, String id);

    ShopBean getShopBeanById(String id);


    MerchantsBean getMerchantsBeanById(String merchantId);
}
