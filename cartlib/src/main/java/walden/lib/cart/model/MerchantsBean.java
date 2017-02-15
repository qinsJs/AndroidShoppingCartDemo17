package walden.lib.cart.model;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import walden.lib.cart.ShopCartTools;
import walden.lib.cart.usb.IMerchantsModel;

/**
 * 商家数据,更改以前单一 商品的使用方式,该改为商家附商品的数据模型
 * <p>
 * Created by walden on 2017/2/15.
 */
public class MerchantsBean {

    /**
     * 商家id
     */
    private String merchantsId;

    /**
     * 商家名称
     */
    private String merchantsname;

    /**
     * 是否选中
     */
    private boolean selected;

    /**
     * 是否退场 , 即不参加运算
     */
    private boolean exit;


    /**
     * 正真关系的商品在这里
     */
    private List<ShopBean> shopList;

    // TODO: 2017/2/15 需不需要保存原始数据 ?

    public MerchantsBean(IMerchantsModel model) {
        merchantsId = model.gifMerchantsId();
        merchantsname = model.gifMerchantsName();
        selected = model.selected();
        exit = model.exit();

        //数据转换
        shopList = ShopCartTools.transition(model.shops());

        for (ShopBean b : shopList) {
            b.setMerchantsId(merchantsId);
        }
    }

    private MerchantsBean() {
    }

    public static MerchantsBean createDefault() {
        MerchantsBean result = new MerchantsBean();
        result.merchantsId = UUID.randomUUID().toString();
        result.merchantsname = "";
        result.shopList = new ArrayList<>();
        return result;
    }

    public void syncChild() {
        for (ShopBean b : shopList) {
            b.setJoin(selected);
        }
    }

    public String getMerchantsId() {
        return merchantsId;
    }

    public void setMerchantsId(String merchantsId) {
        this.merchantsId = merchantsId;
    }

    public String getMerchantsname() {
        return merchantsname;
    }

    public void setMerchantsname(String merchantsname) {
        this.merchantsname = merchantsname;
    }

    public boolean isSelected() {
        return selected;
    }

    public void setSelected(boolean selected) {
        this.selected = selected;
    }

    public boolean isExit() {
        return exit;
    }

    public void setExit(boolean exit) {
        this.exit = exit;
    }

    public List<ShopBean> getShopList() {
        return shopList;
    }

    public void setShopList(List<ShopBean> shopList) {
        this.shopList = shopList;
    }
}
