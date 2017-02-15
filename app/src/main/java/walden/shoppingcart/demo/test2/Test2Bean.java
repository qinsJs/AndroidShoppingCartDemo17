package walden.shoppingcart.demo.test2;

import java.util.List;

import walden.lib.cart.model.ShopBean;
import walden.lib.cart.usb.IMerchantsModel;
import walden.lib.cart.usb.IShopModel;

/**
 * Created by next on 17-1-20.
 */

public class Test2Bean {
    private List<ContentBean> content;

    public List<ContentBean> getContent() {
        return content;
    }

    public void setContent(List<ContentBean> content) {
        this.content = content;
    }

    public static class ContentBean implements IMerchantsModel {
        /**
         * id : 0
         * adress : 广州市天河城
         * isselected : false
         * gooddetail : [{"id":"00","pic":"11","count":"1","limitcount":"10","name":"BB霜","price":"99","isedit":"false","isselected":"false"},{"id":"01","pic":"11","count":"1","limitcount":"5","name":"男士洁面乳","price":"66","isedit":"false","isselected":"false"},{"id":"02","pic":"11","count":"1","limitcount":"2","name":"眼线笔","price":"16","isedit":"false","isselected":"false"}]
         */

        private String id;
        private String adress;
        private String isselected;
        private List<GooddetailBean> gooddetail;

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getAdress() {
            return adress;
        }

        public void setAdress(String adress) {
            this.adress = adress;
        }

        public String getIsselected() {
            return isselected;
        }

        public void setIsselected(String isselected) {
            this.isselected = isselected;
        }

        public List<GooddetailBean> getGooddetail() {
            return gooddetail;
        }

        public void setGooddetail(List<GooddetailBean> gooddetail) {
            this.gooddetail = gooddetail;
        }

        @Override
        public String gifMerchantsId() {
            return id;
        }

        @Override
        public String gifMerchantsName() {
            return adress;
        }

        @Override
        public boolean selected() {
            return Boolean.valueOf(isselected);
        }

        @Override
        public boolean exit() {
            return false;
        }

        @Override
        public List<GooddetailBean> shops() {
            return gooddetail;
        }

        public static class GooddetailBean implements IShopModel {
            /**
             * id : 00
             * pic : 11
             * count : 1
             * limitcount : 10
             * name : BB霜
             * price : 99
             * isedit : false
             * isselected : false
             */

            private String id;
            private String pic;
            private String count;
            private String limitcount;
            private String name;
            private String price;
            private String isedit;
            private String isselected;
            private String merchants;

            public String getMerchants() {
                return merchants;
            }

            public void setMerchants(String merchants) {
                this.merchants = merchants;
            }

            public String getId() {
                return id;
            }

            public void setId(String id) {
                this.id = id;
            }

            public String getPic() {
                return pic;
            }

            public void setPic(String pic) {
                this.pic = pic;
            }

            public String getCount() {
                return count;
            }

            public void setCount(String count) {
                this.count = count;
            }

            public String getLimitcount() {
                return limitcount;
            }

            public void setLimitcount(String limitcount) {
                this.limitcount = limitcount;
            }

            public String getName() {
                return name;
            }

            public void setName(String name) {
                this.name = name;
            }

            public String getPrice() {
                return price;
            }

            public void setPrice(String price) {
                this.price = price;
            }

            public String getIsedit() {
                return isedit;
            }

            public void setIsedit(String isedit) {
                this.isedit = isedit;
            }

            public String getIsselected() {
                return isselected;
            }

            public void setIsselected(String isselected) {
                this.isselected = isselected;
            }

            @Override
            public String giftId() {
                return id;
            }

            @Override
            public String giftName() {
                return name;
            }

            @Override
            public double giftPrice() {
                return Double.valueOf(price);
            }

            @Override
            public int giftCount() {
                return Integer.valueOf(count);
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
                return false;
            }

            @Override
            public int minCount() {
                return 1;
            }

            @Override
            public int maxCount() {
                return Integer.valueOf(limitcount);
            }

            public void syncData(ShopBean b) {

            }
        }
    }
}
