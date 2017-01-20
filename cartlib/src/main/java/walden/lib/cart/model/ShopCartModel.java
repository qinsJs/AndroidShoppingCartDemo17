package walden.lib.cart.model;

import walden.lib.cart.usb.IServiceBean;

/**
 * Created by next on 17-1-20.
 */

public class ShopCartModel
{
	private ShopBean shop;
	private IServiceBean source;

	public ShopCartModel(ShopBean shop, IServiceBean source)
	{
		this.shop = shop;
		this.source = source;
	}

	public ShopBean getShop()
	{
		return shop;
	}

	public IServiceBean getSource()
	{
		return source;
	}
}
