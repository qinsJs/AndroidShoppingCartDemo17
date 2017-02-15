package walden.lib.cart.model;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import walden.lib.cart.usb.IMerchantsModel;
import walden.lib.cart.usb.IShopModel;

/**
 * Created by walden on 2017/2/15.
 */

public class DefaultIMerchantsModel implements IMerchantsModel {
    List<IShopModel> mShopModels;

    public DefaultIMerchantsModel(List<IShopModel> shopModels) {
        if (shopModels != null)
            mShopModels = shopModels;
        else
            mShopModels = new ArrayList<>();
    }

    @Override
    public String gifMerchantsId() {
        return UUID.randomUUID().toString();
    }

    @Override
    public String gifMerchantsName() {
        return "";
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
    public List<IShopModel> shops() {
        return mShopModels;
    }
}
