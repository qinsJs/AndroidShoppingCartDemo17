package walden.shoppingcart.demo.test2;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.TextView;

import walden.lib.cart.adapter.CartExAdapter;
import walden.lib.cart.model.MerchantsBean;
import walden.lib.cart.model.ShopBean;
import walden.lib.cart.usb.ICart;
import walden.shoppingcart.demo.R;

/**
 * Created by walden on 2017/2/15.
 */

public class Test2AdapterV2 extends CartExAdapter {
    Context mContext;

    boolean isEdit;

    public Test2AdapterV2(Context context, ICart cart) {
        super(cart);
        mContext = context;
    }

    public void change() {
        isEdit = !isEdit;
        notifyDataSetChanged();
    }

    @Override
    public View getGroupView_(int groupPosition, boolean isExpanded, View convertView, ViewGroup parent, MerchantsBean b) {
//        item_ui_2_merchants
        GroupHolder holder;
        if (convertView == null) {
            convertView = LayoutInflater.from(mContext).inflate(R.layout.item_ui_2_merchants, parent, false);
            holder = new GroupHolder(convertView);
            convertView.setTag(holder);
        } else {
            holder = (GroupHolder) convertView.getTag();
        }

        holder.cb_GroupItem.setChecked(b.isSelected());
        holder.tv_Position.setText(b.getMerchantsname());
        holder.cb_GroupItem.setOnClickListener(new click(null, b.getMerchantsId()));
        return convertView;
    }

    @Override
    public View getChildView_(int groupPosition, int childPosition, boolean isLastChild, View convertView, ViewGroup parent, ShopBean b) {
        Childholder holder;

        if (convertView == null) {
            convertView = LayoutInflater.from(mContext).inflate(R.layout.item_ui_2, parent, false);
            holder = new Childholder(convertView);
            convertView.setTag(holder);
        } else {
            holder = (Childholder) convertView.getTag();
        }

        click c = new click(b.getId(), getGroup(groupPosition).getMerchantsId());

        holder.cb_Item.setChecked(b.isJoin());
        holder.cb_Item.setOnClickListener(c);

        holder.tv_Price.setText("￥" + b.getPrice());
        holder.tv_Count.setText("x" + b.getCount());

        if (isEdit) {
            holder.ll_Nomal.setVisibility(View.GONE);
            holder.ll_Edit.setVisibility(View.VISIBLE);
            holder.iv_Delete.setVisibility(View.VISIBLE);
            holder.iv_Delete.setOnClickListener(c);

            holder.tv_Reduce.setOnClickListener(c);
            holder.tv_Add.setOnClickListener(c);

            holder.et_Count.setText(b.getCount() + "");
        } else {
            holder.ll_Nomal.setVisibility(View.VISIBLE);
            holder.ll_Edit.setVisibility(View.GONE);
            holder.iv_Delete.setVisibility(View.GONE);
            holder.iv_Delete.setOnClickListener(null);

            holder.tv_Reduce.setOnClickListener(null);
            holder.tv_Add.setOnClickListener(null);

            holder.tv_GoodName.setText(b.getName());
            holder.tv_LimitCount.setText(String.format("限购%d件", b.getMax_count()));
        }
        return convertView;
    }

    @Override
    public void onCombinedChange(double combined, double fee) {
        ((Test2Activity) mContext).heji(combined + "", "运费:" + fee);
    }

    public void selectAll(boolean checked) {
        for (MerchantsBean mb : mCart.seeCart()) {
            mb.setSelected(checked);
            mb.syncChild();
        }
        mCart.cashier();
        notifyDataSetChanged();
    }


    private class GroupHolder {
        public View item_merchants_ll;
        public CheckBox cb_GroupItem;
        //		地址
        public TextView tv_Position;

        public GroupHolder(View q) {
            this.item_merchants_ll = q.findViewById(R.id.item_merchants_ll);
            this.cb_GroupItem = (CheckBox) q.findViewById(R.id.cb_GroupItem);
            this.tv_Position = (TextView) q.findViewById(R.id.tv_Position);
        }
    }

    private class Childholder {
        public CheckBox cb_Item;

        //状态1
        public View ll_Nomal;
        public TextView tv_GoodName;
        public TextView tv_LimitCount;

        //		状态2 修改
        public View ll_Edit;
        //      减
        public View tv_Reduce;
        public TextView et_Count;
        //加
        public View tv_Add;

        public TextView tv_Price;
        public TextView tv_Count;
        public View iv_Delete;

        public Childholder(View q) {
            this.cb_Item = (CheckBox) q.findViewById(R.id.cb_Item);
            this.ll_Nomal = q.findViewById(R.id.ll_Nomal);
            this.tv_GoodName = (TextView) q.findViewById(R.id.tv_GoodName);
            this.tv_LimitCount = (TextView) q.findViewById(R.id.tv_LimitCount);
            this.ll_Edit = q.findViewById(R.id.ll_Edit);
            this.tv_Reduce = q.findViewById(R.id.tv_Reduce);
            this.et_Count = (TextView) q.findViewById(R.id.et_Count);
            this.tv_Add = q.findViewById(R.id.tv_Add);
            this.tv_Price = (TextView) q.findViewById(R.id.tv_Price);
            this.tv_Count = (TextView) q.findViewById(R.id.tv_Count);
            this.iv_Delete = q.findViewById(R.id.iv_Delete);
        }
    }


    private class click implements View.OnClickListener {
        //商品id
        private String shopId;

        //商家id
        private String merchantsId;

        public click(String shopId, String merchantsId) {
            this.shopId = shopId;
            this.merchantsId = merchantsId;
        }

        @Override
        public void onClick(View v) {
            switch (v.getId()) {
                //商家选择
                case R.id.cb_GroupItem:
                    if (v instanceof CheckBox) {
                        CheckBox c = (CheckBox) v;
                        MerchantsBean mb = mCart.getMerchantsBeanById(merchantsId);
                        mb.setSelected(c.isChecked());
                        mb.syncChild();
                    }
                    break;
                //商品选择
                case R.id.cb_Item:
                    if (v instanceof CheckBox) {
                        CheckBox c = (CheckBox) v;
                        ShopBean sb = mCart.getShopBeanById(shopId);
                        sb.setJoin(c.isChecked());

                        //自动全选的 判断

                        boolean defaultB = sb.isJoin();

                        MerchantsBean mb = mCart.getMerchantsBeanById(sb.getMerchantsId());


                        boolean caseOver = false;

                        for (ShopBean b : mb.getShopList()) {
                            if (b.isJoin() != defaultB) {
                                //有一个不相同,就说明没有全选 ,或者需要设置为全不选
                                mb.setSelected(false);
                                caseOver = true;
                                break;//结束循环
                            }
                        }

                        if (caseOver) break; //结束 case

                        //循环完了还没有出现break 所有子类的状态和 defaultB 是一致的,那么父类也要如此
                        mb.setSelected(defaultB);
                    }
                    break;
                //单个商品删除
                case R.id.iv_Delete:
                    mCart.deleteShop(shopId);
                    break;
                //加
                case R.id.tv_Add:
                    mCart.shopAdd(shopId);
                    break;
                //减
                case R.id.tv_Reduce:
                    mCart.shopMinus(shopId);
                    break;
            }

            mCart.cashier();
            notifyDataSetChanged();
        }
    }

}
