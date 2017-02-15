package walden.lib.cart.model;

import walden.lib.cart.usb.IShopModel;

/**
 * Created by next on 17-1-18.
 */

public class ShopBean {
    /**
     * Y
     * 自己使用的 id,自己生成
     */
    private String id;

    /**
     * Y
     * 商品名称
     */
    private String name;
    /**
     * Y
     * 商品价格
     */
    private double price;
    /**
     * Y
     * 商品数量
     */
    private int count;

    /**
     * 是否参与 合计 计算 , true 表示 参加 合计 计算
     */
    private boolean isJoin;
//-----
    /**
     * 配送费用
     */
    private double fee;

    /**
     * 是否失效, true 失效 不参加运算
     */
    private boolean isFailure;

    /**
     * 商品数量最小限制
     */
    private int min_count = 1;

    /**
     * 商品数量最大限制
     */
    private int max_count = Integer.MAX_VALUE;

    /**
     * 商家的名字,母亲的脐带
     */
    private String merchantsId;

    private Object arg1;

    public ShopBean(IShopModel rules) {
        id = rules.giftId();
        name = rules.giftName();
        price = rules.giftPrice();
        count = rules.giftCount();
        fee = rules.giftFee();
        isJoin = rules.isJoin();
        isFailure = rules.isFailure();
        min_count = rules.minCount();
        max_count = rules.maxCount();
        arg1 = rules.arg1();
    }

    public synchronized void setCount(int count) {
        this.count = count;
    }


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public int getCount() {
        return count;
    }

    public boolean isJoin() {
        return isJoin;
    }

    public void setJoin(boolean join) {
        if (isFailure)
            isJoin = false;
        else
            isJoin = join;
    }

    public boolean isFailure() {
        return isFailure;
    }

    public void setFailure(boolean failure) {
        isFailure = failure;
    }

    public int getMin_count() {
        return min_count;
    }

    public void setMin_count(int min_count) {
        this.min_count = min_count;
    }

    public int getMax_count() {
        return max_count;
    }

    public void setMax_count(int max_count) {
        this.max_count = max_count;
    }

    public double getFee() {
        return fee;
    }

    public void setFee(double fee) {
        this.fee = fee;
    }


    public String getMerchantsId() {
        return merchantsId;
    }

    public void setMerchantsId(String merchantsId) {
        this.merchantsId = merchantsId;
    }

    public Object getArg1() {
        return arg1;
    }

    public void setArg1(Object arg1) {
        this.arg1 = arg1;
    }
}
