package walden.lib.cart.usb;

import java.util.List;

import walden.lib.cart.model.ShopBean;

/**
 * Created by next on 17-1-19.
 */

public interface IShopCartAction
{

	void onCartErr(CartErrCode code);

	enum CartErrCode
	{
		COUNT_LESS_THAN_1("商品数量小于1"),
		OUT_OF_MIN_COUNT("商品数量小于下限"),
		OUT_OF_MAX_COUNT("商品数量小于上限");


		CartErrCode(String s)
		{
			introduce = s;
		}

		public String introduce;
	}


	void onGoodsChange(List<ShopBean> shopList);

	void onCombinedChange(double combined, double fee);

}
