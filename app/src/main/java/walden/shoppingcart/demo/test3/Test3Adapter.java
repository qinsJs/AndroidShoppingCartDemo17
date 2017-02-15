package walden.shoppingcart.demo.test3;

import android.content.Context;
import android.graphics.Color;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.TextView;

import walden.lib.cart.adapter.CartExAdapter;
import walden.lib.cart.model.MerchantsBean;
import walden.lib.cart.model.ShopBean;
import walden.lib.cart.usb.ICart;
import walden.shoppingcart.demo.R;

/**
 * Created by walden on 2017/2/15.
 */

public class Test3Adapter extends CartExAdapter {
    private Context mContext;

    public Test3Adapter(Context context, ICart cart) {
        super(cart);
        mContext = context;
    }

    @Override
    public View getGroupView_(int groupPosition, boolean isExpanded, View convertView, ViewGroup parent, MerchantsBean b) {
        GroupHolder holder;
        if (convertView == null) {
            convertView = LayoutInflater.from(mContext).inflate(R.layout.item_ui_3_parent, parent, false);
            holder = new GroupHolder(convertView);
            convertView.setTag(holder);
        } else {
            holder = (GroupHolder) convertView.getTag();
        }
        holder.item_shopping_car_check_box_parent.setChecked(b.isSelected());

        holder.item_shopping_car_check_box_parent.setOnClickListener(new Click(null, b.getMerchantsId()));
        holder.tv_item_shopping_car_parent_name.setText(b.getMerchantsname());

        if (b.getMerchantsname().equals("失效商品")) {
            holder.item_shopping_car_check_box_parent.setVisibility(View.INVISIBLE);
        } else {
            holder.item_shopping_car_check_box_parent.setVisibility(View.VISIBLE);

        }

        return convertView;
    }

    @Override
    public View getChildView_(int groupPosition, int childPosition, boolean isLastChild, View convertView, ViewGroup parent, ShopBean b) {
        Childholder holder;

        if (convertView == null) {
            convertView = LayoutInflater.from(mContext).inflate(R.layout.item_ui_3_child, parent, false);
            holder = new Childholder(convertView);
            convertView.setTag(holder);
        } else {
            holder = (Childholder) convertView.getTag();
        }

        Click c = new Click(b.getId(), b.getMerchantsId());

        holder.shop_cart_item_cb.setChecked(b.isJoin());

        holder.shop_cart_item_cb.setOnClickListener(c);

        // TODO: 2017/2/15  图片加载
//        holder.shop_cart_item_iv.
        holder.shop_cart_item_name_tv.setText(b.getName());
        holder.shop_cart_item_price_tv.setText(Test3Activity.RMB + " " + b.getPrice());

        holder.shop_cart_item_number_tv.setText(b.getCount() + "");


        holder.shop_cart_item_reduce_tv.setOnClickListener(c);
        holder.shop_cart_item_add_tv.setOnClickListener(c);


        ParsingServiceShopCart.DataBean.CommunityCartListBean.CartItemsBean cib = (ParsingServiceShopCart.DataBean.CommunityCartListBean.CartItemsBean) b.getArg1();


        holder.shop_cart_item_name_tv.setTextColor(0xff000000);
        holder.shop_cart_item_price_tv.setTextColor(Color.RED);

        if (b.isFailure()) {
            //失效
            holder.shop_cart_item_control_ll.setVisibility(View.GONE);
            holder.shop_cart_item_cb.setVisibility(View.GONE);

            holder.shop_cart_item_sold_out_icon.setVisibility(View.VISIBLE);

            holder.shop_cart_item_name_tv.setTextColor(0xff999999);
            holder.shop_cart_item_price_tv.setTextColor(0xff999999);
        } else if (cib.getStockNum() < cib.getAlarmNum()) {
            //库存小于预警数量
            holder.shop_cart_item_control_ll.setVisibility(View.GONE);
            holder.shop_cart_item_cb.setVisibility(View.GONE);
            holder.shop_cart_item_sold_out_icon.setVisibility(View.INVISIBLE);
        } else {
            holder.shop_cart_item_cb.setVisibility(View.VISIBLE);
            holder.shop_cart_item_control_ll.setVisibility(View.VISIBLE);
            holder.shop_cart_item_sold_out_icon.setVisibility(View.GONE);
        }


        return convertView;
    }

    public void CheckAll(boolean b) {

        for (MerchantsBean mb : mCart.seeCart()) {
            mb.setSelected(b);
            mb.syncChild();
        }
        mCart.cashier();
        notifyDataSetChanged();
    }

    private class Click implements View.OnClickListener {

        String shopId;
        String merchentid;

        public Click(String shopId, String merchentid) {
            this.shopId = shopId;
            this.merchentid = merchentid;
        }

        @Override
        public void onClick(View v) {

            switch (v.getId()) {
                //商户cb
                case R.id.item_shopping_car_check_box_parent:
                    if (v instanceof CheckBox) {
                        CheckBox c = (CheckBox) v;
                        MerchantsBean mb = mCart.getMerchantsBeanById(merchentid);
                        mb.setSelected(c.isChecked());
                        mb.syncChild();
                    }
                    break;
                //s商品cb
                case R.id.shop_cart_item_cb:
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
                //-----减
                case R.id.shop_cart_item_reduce_tv:
                    mCart.shopMinus(shopId);
                    break;
                //++++ 加
                case R.id.shop_cart_item_add_tv:
                    mCart.shopAdd(shopId);
                    break;
            }
            mCart.cashier();
            notifyDataSetChanged();
        }
    }

    @Override
    public void onCombinedChange(double combined, double fee) {
        double p = combined + fee;
        Log.i("murphy", "合计价格:" + p);
        ((Test3Activity) mContext).heji(p + "");
    }


    private class Childholder {
        CheckBox shop_cart_item_cb;
        ImageView shop_cart_item_iv;
        TextView shop_cart_item_name_tv;
        TextView shop_cart_item_price_tv;
        TextView shop_cart_item_reduce_tv;
        TextView shop_cart_item_number_tv;
        TextView shop_cart_item_add_tv;
        //- 1 +
        View shop_cart_item_control_ll;
        //下架logo
        View shop_cart_item_sold_out_icon;

        public Childholder(View v) {
            shop_cart_item_cb = (CheckBox) v.findViewById(R.id.shop_cart_item_cb);
            shop_cart_item_iv = (ImageView) v.findViewById(R.id.shop_cart_item_iv);
            shop_cart_item_name_tv = (TextView) v.findViewById(R.id.shop_cart_item_name_tv);
            shop_cart_item_price_tv = (TextView) v.findViewById(R.id.shop_cart_item_price_tv);
            shop_cart_item_reduce_tv = (TextView) v.findViewById(R.id.shop_cart_item_reduce_tv);
            shop_cart_item_number_tv = (TextView) v.findViewById(R.id.shop_cart_item_number_tv);
            shop_cart_item_add_tv = (TextView) v.findViewById(R.id.shop_cart_item_add_tv);
            shop_cart_item_control_ll = v.findViewById(R.id.shop_cart_item_control_ll);
            shop_cart_item_sold_out_icon = v.findViewById(R.id.shop_cart_item_sold_out_icon);
        }
    }

    private class GroupHolder {

        CheckBox item_shopping_car_check_box_parent;
        TextView tv_item_shopping_car_parent_name;

        public GroupHolder(View v) {
            item_shopping_car_check_box_parent = (CheckBox) v.findViewById(R.id.item_shopping_car_check_box_parent);
            tv_item_shopping_car_parent_name = (TextView) v.findViewById(R.id.tv_item_shopping_car_parent_name);
        }
    }
}
