package walden.lib.cart.model;

/**
 * Created by next on 17-1-18.
 */

public class ShopBean
{
	/**
	 * Y
	 * 自己使用的 id,自己生成
	 */
	public int id;
	/**
	 * N
	 * 这个是给服务器预留的Id位置
	 */
	public String shopId;
	/**
	 * Y
	 * 商品名称
	 */
	public String name;
	/**
	 * Y
	 * 商品价格
	 */
	public double price;
	/**
	 * Y
	 * 商品数量
	 */
	public int count;

	//-----

	/**
	 * N
	 * 商品简介
	 */
	public String introduction;
	/**
	 * N
	 * 商品状态,下架等
	 */
	public ShopStatus status;
	/**
	 * N
	 * 商品供应商信息
	 */
	public ShopMerchant merchant;
	/**
	 * N
	 * 配送费用
	 */
	public double distribution;
}
