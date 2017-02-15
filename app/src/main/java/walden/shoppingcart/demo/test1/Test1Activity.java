package walden.shoppingcart.demo.test1;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.CheckBox;
import android.widget.ListView;
import android.widget.TextView;

import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.List;

import walden.lib.cart.ShopCart;
import walden.lib.cart.ShopCartTools;
import walden.lib.cart.model.ShopBean;
import walden.lib.cart.usb.ICart;
import walden.lib.cart.usb.IMerchantsModel;
import walden.lib.cart.usb.IShopModel;
import walden.shoppingcart.demo.A;
import walden.shoppingcart.demo.R;

/**
 * 借鉴: http://www.jianshu.com/p/52100f9a61e7
 */
public class Test1Activity extends AppCompatActivity implements View.OnClickListener {
    private ListView shop_list;
    private TextView prices;
    private CheckBox all_checks;
    private TextView settlement;
    private TextView delete_text;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.a_ui_1);
        initview();
        initData();
    }

    private void initview() {
        shop_list = (ListView) findViewById(R.id.shop_list);
        //shop_list.setAdapter(shopAdapter);
        all_checks = (CheckBox) findViewById(R.id.all_checks);//全选
        prices = (TextView) findViewById(R.id.prices);//价格
        settlement = (TextView) findViewById(R.id.settlement);//结算
        delete_text = (TextView) findViewById(R.id.delete_text);//删除
        findViewById(R.id.linearLayout).setOnClickListener(this);
        settlement.setOnClickListener(this);
        delete_text.setOnClickListener(this);
        all_checks.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.settlement: //结算
                break;
            case R.id.delete_text: //删除
                List<String> del = new ArrayList<>();
                for (ShopBean bean : mCart.seeJoinShop()) {
                    del.add(bean.getId());
                }
                mCart.deleteShop(del);
                break;
            case R.id.all_checks:
                mAdapter.selectAll(all_checks.isChecked());
                break;
            case R.id.linearLayout:
                try {
                    String json = A.getJson(this, "test1.json");
                    Test1Bean test1Bean = new Gson().fromJson(json, Test1Bean.class);
                    mCart.addShop(null, test1Bean.getData().get(0));
                } catch (Exception e) {
                    Snackbar.make(settlement, " " + e.toString(), Snackbar.LENGTH_LONG).show();
                }
                break;
        }
    }

    ///------------------------- 自己的代码

    private ICart mCart;
    private Test1Adapter mAdapter;


    private void initData() {
        String json = A.getJson(this, "test1.json");

        mCart = new ShopCart();
        mAdapter = new Test1Adapter(this, mCart);
        try {
            Test1Bean test1Bean = new Gson().fromJson(json, Test1Bean.class);
            List<IMerchantsModel> deta = ShopCartTools.dontMerchants(test1Bean.getData());
            mCart.loadCart(deta);
        } catch (Exception e) {
        }

        shop_list.setAdapter(mAdapter);
    }

    public void heji(String price) {
        prices.setText(price);
    }

    public static void toActivity(Context c) {
        c.startActivity(new Intent(c, Test1Activity.class));
    }

}
