package walden.shoppingcart.demo.test3;

import java.util.List;

interface v {

        /**
         * 完成状态
         */
        void completeState();

        /**
         * 编辑状态
         */
        void editorState();

        /**
         * 删除
         */
        void del();

        /**
         * 计算
         */
        void settlement();

        /**
         * 选中
         *
         * @param select true 全选 ,false 全不选
         */
        void selectAll(boolean select);

        /**
         * 取名字的方式简单点
         * <p>
         * 设置数据
         */
        void setData(List<ParsingServiceShopCart.DataBean.CommunityCartListBean> b);

    }