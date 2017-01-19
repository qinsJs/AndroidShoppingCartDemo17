package walden.lib.cart.model;

/**
 * Created by next on 17-1-18.
 */

public enum ShopStatus
{
	NORMAL("正常"),//正常
	SHELVES("下架"),//下架
	STOCKOUT("缺货");//缺货

	ShopStatus(String s)
	{
		introduce = s;
	}

	public String introduce;

}
