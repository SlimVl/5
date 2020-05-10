package com.vlasov.ochercity.viewmodel.product;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Transformations;

import com.vlasov.ochercity.repository.product.ProductRepository;
import com.vlasov.ochercity.utils.AbsentLiveData;
import com.vlasov.ochercity.utils.Utils;
import com.vlasov.ochercity.viewmodel.common.PSViewModel;
import com.vlasov.ochercity.viewobject.ProductColor;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

public class ProductColorViewModel extends PSViewModel {
    //for product color list

    public boolean isColorData = false;
    private final LiveData<List<ProductColor>> productColorListData;
    private MutableLiveData<TmpDataHolder> productColorObj = new MutableLiveData<>();

    public List<ProductColor> proceededColorListData = new ArrayList<>();
    public String colorSelectId = "";
    public String colorSelectValue = "";

    //region Constructor

    @Inject
    ProductColorViewModel(ProductRepository productRepository) {
        //  product color List
        productColorListData = Transformations.switchMap(productColorObj, (TmpDataHolder obj) -> {
            if (obj == null) {
                return AbsentLiveData.create();
            }
            Utils.psLog("product color List.");
            return productRepository.getProductColor(obj.productId);
        });

    }

    //endregion
    //region Getter And Setter for product color List

    public void setProductColorListObj(String productId) {
        ProductColorViewModel.TmpDataHolder tmpDataHolder = new ProductColorViewModel.TmpDataHolder();
        tmpDataHolder.productId = productId;
        tmpDataHolder.colorSelectedId = colorSelectId;
        tmpDataHolder.colorSelectedValue = colorSelectValue;

        productColorObj.setValue(tmpDataHolder);

        // start loading
        setLoadingState(true);

    }

    public LiveData<List<ProductColor>> getProductColorData() {
        return productColorListData;
    }

    //endregion

    //region Holder
    class TmpDataHolder {
        public String offset = "";
        public String productId = "";
        public String colorSelectedId = "";
        public String colorSelectedValue = "";
        public Boolean isConnected = false;
    }
    //endregion
}
