package com.vlasov.ochercity.viewmodel.coupondiscount;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Transformations;

import com.vlasov.ochercity.repository.coupondiscount.CouponDiscountRepository;
import com.vlasov.ochercity.utils.AbsentLiveData;
import com.vlasov.ochercity.viewmodel.common.PSViewModel;
import com.vlasov.ochercity.viewobject.CouponDiscount;
import com.vlasov.ochercity.viewobject.common.Resource;

import javax.inject.Inject;

public class CouponDiscountViewModel extends PSViewModel {

    private final LiveData<Resource<CouponDiscount>> couponDiscountData;
    private final MutableLiveData<TmpDataHolder> couponDiscountObj = new MutableLiveData<>();

    @Inject
    CouponDiscountViewModel(CouponDiscountRepository repository) {

        couponDiscountData = Transformations.switchMap(couponDiscountObj, obj -> {

            if (obj == null) {
                return AbsentLiveData.create();
            }

            return repository.getCouponDiscount(obj.code);
        });
    }

    public void setCouponDiscountObj(String code) {
        TmpDataHolder tmpDataHolder = new TmpDataHolder();
        tmpDataHolder.code = code;

        couponDiscountObj.setValue(tmpDataHolder);
    }

    public LiveData<Resource<CouponDiscount>> getCouponDiscountData() {
        return couponDiscountData;
    }


    class TmpDataHolder {

        public String code = "";
        public String shopId = "";
    }

}
