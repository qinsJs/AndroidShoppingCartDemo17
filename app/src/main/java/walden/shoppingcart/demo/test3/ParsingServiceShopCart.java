package walden.shoppingcart.demo.test3;

import java.util.List;

import walden.lib.cart.usb.IMerchantsModel;
import walden.lib.cart.usb.IShopModel;

/**
 * Created by walden on 2017/2/15.
 */

public class ParsingServiceShopCart {


    /**
     * msg : 操作成功
     * code : null
     * data : {"totalNum":0,"totalPrice":{"amount":null,"cent":0,"currency":"CNY"},"userId":"3b6723b9a58c4e6e9c64c69388e03546","communityCartList":[{"mPrice":{"amount":null,"cent":0,"currency":"CNY"},"shopCartId":"f9f92ff5-d3b5-11e6-99f8-000c2913e3a3","communityName":"欣欣想小区1","mNum":0,"communityId":"342f9ceb-ecfe-11e5-b2bd-0050562cb46c","cartItems":[{"merchantId":"749d383c-ecb8-11e5-b2bd-0050562cb46c","price":{"amount":0.01,"cent":1,"currency":"CNY"},"alarmNum":10,"num":5,"stockNum":29,"commodityId":"135afe00-bc56-11e6-99f8-000c2913e3a3","hImg":"2017-02-08/864e51ea-98ca-4cf5-a58d-47b2ffbc9271.jpg","activityLabel":null,"commodityDesc":"  ","cartItemId":"a7de1401-f1c1-11e6-9146-000c2913e3a3","commodityName":"神经病","status":0},{"merchantId":"749d383c-ecb8-11e5-b2bd-0050562cb46c","price":{"amount":0.01,"cent":1,"currency":"CNY"},"alarmNum":10,"num":1,"stockNum":23,"commodityId":"1129822c-bc56-11e6-99f8-000c2913e3a3","hImg":"2016-12-07/b16fb111-6b09-4514-ab04-c056ab16850e.jpg","activityLabel":null,"commodityDesc":"safsafasfasfsaf ","cartItemId":"713fc335-f1cc-11e6-9146-000c2913e3a3","commodityName":"商品测试1","status":0},{"merchantId":"f957ac19-a70d-11e6-99f8-000c2913e3a3","price":{"amount":0.11,"cent":11,"currency":"CNY"},"alarmNum":0,"num":1,"stockNum":182,"commodityId":"7db67f6d-a716-11e6-99f8-000c2913e3a3","hImg":"2016-11-10/b57f9e1f-0844-47d7-9219-51a4c06ca322.png","activityLabel":null,"commodityDesc":"公告","cartItemId":"ca813f77-d64c-11e6-99f8-000c2913e3a3","commodityName":"上衣","status":0},{"merchantId":"f957ac19-a70d-11e6-99f8-000c2913e3a3","price":{"amount":20,"cent":2000,"currency":"CNY"},"alarmNum":0,"num":1,"stockNum":19,"commodityId":"7a3a4cec-a716-11e6-99f8-000c2913e3a3","hImg":"2016-11-10/693ffc07-1881-4356-bddb-2d4f04c96f05.png","activityLabel":null,"commodityDesc":null,"cartItemId":"d0c21a2a-d3b9-11e6-99f8-000c2913e3a3","commodityName":"连衣裙","status":0},{"merchantId":"749d383c-ecb8-11e5-b2bd-0050562cb46c","price":{"amount":0.01,"cent":1,"currency":"CNY"},"alarmNum":10,"num":1,"stockNum":0,"commodityId":"76ce3d12-bc5a-11e6-99f8-000c2913e3a3","hImg":"2016-12-07/66987cef-6747-4fe4-916a-d18e52b65ba1.gif","activityLabel":null,"commodityDesc":"sfasfasf ","cartItemId":"f9f96882-d3b5-11e6-99f8-000c2913e3a3","commodityName":"新鲜的海鲜水果","status":0}]}]}
     * success : true
     */

    private String msg;
    private Object code;
    private DataBean data;
    private boolean success;

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public Object getCode() {
        return code;
    }

    public void setCode(Object code) {
        this.code = code;
    }

    public DataBean getData() {
        return data;
    }

    public void setData(DataBean data) {
        this.data = data;
    }

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public static class DataBean {
        /**
         * totalNum : 0
         * totalPrice : {"amount":null,"cent":0,"currency":"CNY"}
         * userId : 3b6723b9a58c4e6e9c64c69388e03546
         * communityCartList : [{"mPrice":{"amount":null,"cent":0,"currency":"CNY"},"shopCartId":"f9f92ff5-d3b5-11e6-99f8-000c2913e3a3","communityName":"欣欣想小区1","mNum":0,"communityId":"342f9ceb-ecfe-11e5-b2bd-0050562cb46c","cartItems":[{"merchantId":"749d383c-ecb8-11e5-b2bd-0050562cb46c","price":{"amount":0.01,"cent":1,"currency":"CNY"},"alarmNum":10,"num":5,"stockNum":29,"commodityId":"135afe00-bc56-11e6-99f8-000c2913e3a3","hImg":"2017-02-08/864e51ea-98ca-4cf5-a58d-47b2ffbc9271.jpg","activityLabel":null,"commodityDesc":"  ","cartItemId":"a7de1401-f1c1-11e6-9146-000c2913e3a3","commodityName":"神经病","status":0},{"merchantId":"749d383c-ecb8-11e5-b2bd-0050562cb46c","price":{"amount":0.01,"cent":1,"currency":"CNY"},"alarmNum":10,"num":1,"stockNum":23,"commodityId":"1129822c-bc56-11e6-99f8-000c2913e3a3","hImg":"2016-12-07/b16fb111-6b09-4514-ab04-c056ab16850e.jpg","activityLabel":null,"commodityDesc":"safsafasfasfsaf ","cartItemId":"713fc335-f1cc-11e6-9146-000c2913e3a3","commodityName":"商品测试1","status":0},{"merchantId":"f957ac19-a70d-11e6-99f8-000c2913e3a3","price":{"amount":0.11,"cent":11,"currency":"CNY"},"alarmNum":0,"num":1,"stockNum":182,"commodityId":"7db67f6d-a716-11e6-99f8-000c2913e3a3","hImg":"2016-11-10/b57f9e1f-0844-47d7-9219-51a4c06ca322.png","activityLabel":null,"commodityDesc":"公告","cartItemId":"ca813f77-d64c-11e6-99f8-000c2913e3a3","commodityName":"上衣","status":0},{"merchantId":"f957ac19-a70d-11e6-99f8-000c2913e3a3","price":{"amount":20,"cent":2000,"currency":"CNY"},"alarmNum":0,"num":1,"stockNum":19,"commodityId":"7a3a4cec-a716-11e6-99f8-000c2913e3a3","hImg":"2016-11-10/693ffc07-1881-4356-bddb-2d4f04c96f05.png","activityLabel":null,"commodityDesc":null,"cartItemId":"d0c21a2a-d3b9-11e6-99f8-000c2913e3a3","commodityName":"连衣裙","status":0},{"merchantId":"749d383c-ecb8-11e5-b2bd-0050562cb46c","price":{"amount":0.01,"cent":1,"currency":"CNY"},"alarmNum":10,"num":1,"stockNum":0,"commodityId":"76ce3d12-bc5a-11e6-99f8-000c2913e3a3","hImg":"2016-12-07/66987cef-6747-4fe4-916a-d18e52b65ba1.gif","activityLabel":null,"commodityDesc":"sfasfasf ","cartItemId":"f9f96882-d3b5-11e6-99f8-000c2913e3a3","commodityName":"新鲜的海鲜水果","status":0}]}]
         */

        private int totalNum;
        private TotalPriceBean totalPrice;
        private String userId;
        private List<CommunityCartListBean> communityCartList;

        public int getTotalNum() {
            return totalNum;
        }

        public void setTotalNum(int totalNum) {
            this.totalNum = totalNum;
        }

        public TotalPriceBean getTotalPrice() {
            return totalPrice;
        }

        public void setTotalPrice(TotalPriceBean totalPrice) {
            this.totalPrice = totalPrice;
        }

        public String getUserId() {
            return userId;
        }

        public void setUserId(String userId) {
            this.userId = userId;
        }

        public List<CommunityCartListBean> getCommunityCartList() {
            return communityCartList;
        }

        public void setCommunityCartList(List<CommunityCartListBean> communityCartList) {
            this.communityCartList = communityCartList;
        }

        public static class TotalPriceBean {
            /**
             * amount : null
             * cent : 0
             * currency : CNY
             */

            private Object amount;
            private int cent;
            private String currency;

            public Object getAmount() {
                return amount;
            }

            public void setAmount(Object amount) {
                this.amount = amount;
            }

            public int getCent() {
                return cent;
            }

            public void setCent(int cent) {
                this.cent = cent;
            }

            public String getCurrency() {
                return currency;
            }

            public void setCurrency(String currency) {
                this.currency = currency;
            }
        }

        public static class CommunityCartListBean implements IMerchantsModel {
            /**
             * mPrice : {"amount":null,"cent":0,"currency":"CNY"}
             * shopCartId : f9f92ff5-d3b5-11e6-99f8-000c2913e3a3
             * communityName : 欣欣想小区1
             * mNum : 0
             * communityId : 342f9ceb-ecfe-11e5-b2bd-0050562cb46c
             * cartItems : [{"merchantId":"749d383c-ecb8-11e5-b2bd-0050562cb46c","price":{"amount":0.01,"cent":1,"currency":"CNY"},"alarmNum":10,"num":5,"stockNum":29,"commodityId":"135afe00-bc56-11e6-99f8-000c2913e3a3","hImg":"2017-02-08/864e51ea-98ca-4cf5-a58d-47b2ffbc9271.jpg","activityLabel":null,"commodityDesc":"  ","cartItemId":"a7de1401-f1c1-11e6-9146-000c2913e3a3","commodityName":"神经病","status":0},{"merchantId":"749d383c-ecb8-11e5-b2bd-0050562cb46c","price":{"amount":0.01,"cent":1,"currency":"CNY"},"alarmNum":10,"num":1,"stockNum":23,"commodityId":"1129822c-bc56-11e6-99f8-000c2913e3a3","hImg":"2016-12-07/b16fb111-6b09-4514-ab04-c056ab16850e.jpg","activityLabel":null,"commodityDesc":"safsafasfasfsaf ","cartItemId":"713fc335-f1cc-11e6-9146-000c2913e3a3","commodityName":"商品测试1","status":0},{"merchantId":"f957ac19-a70d-11e6-99f8-000c2913e3a3","price":{"amount":0.11,"cent":11,"currency":"CNY"},"alarmNum":0,"num":1,"stockNum":182,"commodityId":"7db67f6d-a716-11e6-99f8-000c2913e3a3","hImg":"2016-11-10/b57f9e1f-0844-47d7-9219-51a4c06ca322.png","activityLabel":null,"commodityDesc":"公告","cartItemId":"ca813f77-d64c-11e6-99f8-000c2913e3a3","commodityName":"上衣","status":0},{"merchantId":"f957ac19-a70d-11e6-99f8-000c2913e3a3","price":{"amount":20,"cent":2000,"currency":"CNY"},"alarmNum":0,"num":1,"stockNum":19,"commodityId":"7a3a4cec-a716-11e6-99f8-000c2913e3a3","hImg":"2016-11-10/693ffc07-1881-4356-bddb-2d4f04c96f05.png","activityLabel":null,"commodityDesc":null,"cartItemId":"d0c21a2a-d3b9-11e6-99f8-000c2913e3a3","commodityName":"连衣裙","status":0},{"merchantId":"749d383c-ecb8-11e5-b2bd-0050562cb46c","price":{"amount":0.01,"cent":1,"currency":"CNY"},"alarmNum":10,"num":1,"stockNum":0,"commodityId":"76ce3d12-bc5a-11e6-99f8-000c2913e3a3","hImg":"2016-12-07/66987cef-6747-4fe4-916a-d18e52b65ba1.gif","activityLabel":null,"commodityDesc":"sfasfasf ","cartItemId":"f9f96882-d3b5-11e6-99f8-000c2913e3a3","commodityName":"新鲜的海鲜水果","status":0}]
             */

            private MPriceBean mPrice;
            private String shopCartId;
            private String communityName;
            private int mNum;
            private String communityId;
            private List<CartItemsBean> cartItems;

            public MPriceBean getMPrice() {
                return mPrice;
            }

            public void setMPrice(MPriceBean mPrice) {
                this.mPrice = mPrice;
            }

            public String getShopCartId() {
                return shopCartId;
            }

            public void setShopCartId(String shopCartId) {
                this.shopCartId = shopCartId;
            }

            public String getCommunityName() {
                return communityName;
            }

            public void setCommunityName(String communityName) {
                this.communityName = communityName;
            }

            public int getMNum() {
                return mNum;
            }

            public void setMNum(int mNum) {
                this.mNum = mNum;
            }

            public String getCommunityId() {
                return communityId;
            }

            public void setCommunityId(String communityId) {
                this.communityId = communityId;
            }

            public List<CartItemsBean> getCartItems() {
                return cartItems;
            }

            public void setCartItems(List<CartItemsBean> cartItems) {
                this.cartItems = cartItems;
            }

            @Override
            public String gifMerchantsId() {
                //shopCartId 购物车id
                //communityId 商家id
                return shopCartId;
            }

            @Override
            public String gifMerchantsName() {
                return communityName;
            }

            @Override
            public boolean selected() {
                return false;
            }

            @Override
            public boolean exit() {
                return false;
            }

            @Override
            public List<CartItemsBean> shops() {
                return cartItems;
            }

            public static class MPriceBean {
                /**
                 * amount : null
                 * cent : 0
                 * currency : CNY
                 */

                private Object amount;
                private int cent;
                private String currency;

                public Object getAmount() {
                    return amount;
                }

                public void setAmount(Object amount) {
                    this.amount = amount;
                }

                public int getCent() {
                    return cent;
                }

                public void setCent(int cent) {
                    this.cent = cent;
                }

                public String getCurrency() {
                    return currency;
                }

                public void setCurrency(String currency) {
                    this.currency = currency;
                }
            }

            public static class CartItemsBean implements IShopModel {
                /**
                 * merchantId : 749d383c-ecb8-11e5-b2bd-0050562cb46c
                 * price : {"amount":0.01,"cent":1,"currency":"CNY"}
                 * alarmNum : 10
                 * num : 5
                 * stockNum : 29
                 * commodityId : 135afe00-bc56-11e6-99f8-000c2913e3a3
                 * hImg : 2017-02-08/864e51ea-98ca-4cf5-a58d-47b2ffbc9271.jpg
                 * activityLabel : null
                 * commodityDesc :
                 * cartItemId : a7de1401-f1c1-11e6-9146-000c2913e3a3
                 * commodityName : 神经病
                 * status : 0
                 */

                private String merchantId;
                private PriceBean price;
                private int alarmNum;
                private int num;
                private int stockNum;
                private String commodityId;
                private String hImg;
                private Object activityLabel;
                private String commodityDesc;
                private String cartItemId;
                private String commodityName;
                private int status;

                public String getMerchantId() {
                    return merchantId;
                }

                public void setMerchantId(String merchantId) {
                    this.merchantId = merchantId;
                }

                public PriceBean getPrice() {
                    return price;
                }

                public void setPrice(PriceBean price) {
                    this.price = price;
                }

                public int getAlarmNum() {
                    return alarmNum;
                }

                public void setAlarmNum(int alarmNum) {
                    this.alarmNum = alarmNum;
                }

                public int getNum() {
                    return num;
                }

                public void setNum(int num) {
                    this.num = num;
                }

                public int getStockNum() {
                    return stockNum;
                }

                public void setStockNum(int stockNum) {
                    this.stockNum = stockNum;
                }

                public String getCommodityId() {
                    return commodityId;
                }

                public void setCommodityId(String commodityId) {
                    this.commodityId = commodityId;
                }

                public String getHImg() {
                    return hImg;
                }

                public void setHImg(String hImg) {
                    this.hImg = hImg;
                }

                public Object getActivityLabel() {
                    return activityLabel;
                }

                public void setActivityLabel(Object activityLabel) {
                    this.activityLabel = activityLabel;
                }

                public String getCommodityDesc() {
                    return commodityDesc;
                }

                public void setCommodityDesc(String commodityDesc) {
                    this.commodityDesc = commodityDesc;
                }

                public String getCartItemId() {
                    return cartItemId;
                }

                public void setCartItemId(String cartItemId) {
                    this.cartItemId = cartItemId;
                }

                public String getCommodityName() {
                    return commodityName;
                }

                public void setCommodityName(String commodityName) {
                    this.commodityName = commodityName;
                }

                public int getStatus() {
                    return status;
                }

                public void setStatus(int status) {
                    this.status = status;
                }

                @Override
                public String giftId() {
                    //cartItemId    购物车id
                    //commodityId   商品id
                    return cartItemId;
                }

                @Override
                public String giftName() {
                    return commodityName;
                }

                @Override
                public double giftPrice() {
                    return price.getAmount();
                }

                @Override
                public int giftCount() {
                    return num;
                }

                @Override
                public double giftFee() {
                    return 0;
                }

                @Override
                public boolean isJoin() {
                    return false;
                }

                @Override
                public boolean isFailure() {
                    //                    商品状态: 0 、上架 1、下架
                    return status == 1;
                }

                @Override
                public int minCount() {

                    return 0;
                }

                @Override
                public int maxCount() {
                    return Integer.MAX_VALUE;
                }

                @Override
                public Object arg1() {
                    return this;
                }

                public static class PriceBean {
                    /**
                     * amount : 0.01
                     * cent : 1
                     * currency : CNY
                     */

                    private double amount;
                    private int cent;
                    private String currency;

                    public double getAmount() {
                        return amount;
                    }

                    public void setAmount(double amount) {
                        this.amount = amount;
                    }

                    public int getCent() {
                        return cent;
                    }

                    public void setCent(int cent) {
                        this.cent = cent;
                    }

                    public String getCurrency() {
                        return currency;
                    }

                    public void setCurrency(String currency) {
                        this.currency = currency;
                    }
                }
            }
        }
    }
}
