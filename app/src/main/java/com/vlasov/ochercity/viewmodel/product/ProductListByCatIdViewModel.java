package com.vlasov.ochercity.viewmodel.product;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Transformations;

import com.vlasov.ochercity.Config;
import com.vlasov.ochercity.repository.product.ProductRepository;
import com.vlasov.ochercity.utils.AbsentLiveData;
import com.vlasov.ochercity.utils.Utils;
import com.vlasov.ochercity.viewmodel.common.PSViewModel;
import com.vlasov.ochercity.viewobject.Product;
import com.vlasov.ochercity.viewobject.common.Resource;

import java.util.List;

import javax.inject.Inject;

public class ProductListByCatIdViewModel extends PSViewModel {

    // region variables

    private final LiveData<Resource<List<Product>>> productList;
    private MutableLiveData<TmpDataHolder> productListObj = new MutableLiveData<>();

    private final LiveData<Resource<Boolean>> nextPageLoadingStateData;
    private MutableLiveData<TmpDataHolder> nextPageLoadingStateObj = new MutableLiveData<>();

    //region Constructor
    @Inject
    ProductListByCatIdViewModel(ProductRepository repository) {
        Utils.psLog("Inside ProductListByCatIdViewModel");

        // Latest Product List
        productList = Transformations.switchMap(productListObj, obj -> {
            if (obj == null) {
                return AbsentLiveData.create();
            }
            Utils.psLog("productList");
            return repository.getProductListByCatId(Config.API_KEY, obj.loginUserId, obj.offset, obj.categoryId);
        });

        nextPageLoadingStateData = Transformations.switchMap(nextPageLoadingStateObj, obj -> {
            if (obj == null) {
                return AbsentLiveData.create();
            }
            Utils.psLog("nextPageLoadingStateData");
            return repository.getNextPageProductListByCatId(obj.loginUserId, obj.offset, obj.categoryId);
        });

    }

    //endregion

    //region setProductListObj
    public void setProductListObj(String loginUserId, String offset, String catId) {
        if (!isLoading) {
            TmpDataHolder tmpDataHolder = new TmpDataHolder();
            tmpDataHolder.loginUserId = loginUserId;
            tmpDataHolder.offset = offset;
            tmpDataHolder.categoryId = catId;
            productListObj.setValue(tmpDataHolder);

            // start loading
            setLoadingState(true);
        }
    }

    public LiveData<Resource<List<Product>>> getproductList() {
        return productList;
    }

    //Get Latest Product Next Page
    public void setNextPageLoadingStateObj(String loginUserId, String offset, String catId) {

        if (!isLoading) {
            TmpDataHolder tmpDataHolder = new TmpDataHolder();
            tmpDataHolder.loginUserId = loginUserId;
            tmpDataHolder.offset = offset;
            tmpDataHolder.categoryId = catId;
            nextPageLoadingStateObj.setValue(tmpDataHolder);

            // start loading
            setLoadingState(true);
        }
    }

    public LiveData<Resource<Boolean>> getNextPageLoadingStateData() {
        return nextPageLoadingStateData;
    }

    //endregion

    //region Holder

    class TmpDataHolder {
        public String productId = "";
        public String loginUserId = "";
        public String offset = "";
        String categoryId = "";
        public Boolean isConnected = false;

    }

}
