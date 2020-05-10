package com.vlasov.ochercity.viewmodel.homelist;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Transformations;

import com.vlasov.ochercity.repository.category.CategoryRepository;
import com.vlasov.ochercity.utils.AbsentLiveData;
import com.vlasov.ochercity.utils.Utils;
import com.vlasov.ochercity.viewmodel.common.PSViewModel;
import com.vlasov.ochercity.viewobject.Category;
import com.vlasov.ochercity.viewobject.common.Resource;
import com.vlasov.ochercity.viewobject.holder.CategoryParameterHolder;
import com.vlasov.ochercity.viewobject.holder.ProductParameterHolder;

import java.util.List;

import javax.inject.Inject;

public class HomeTrendingCategoryListViewModel extends PSViewModel {

    private final LiveData<Resource<List<Category>>> homeTrendingCategoryListData;
    private MutableLiveData<TmpDataHolder> homeTrendingCategoryListobj = new MutableLiveData<TmpDataHolder>();

    private final LiveData<Resource<Boolean>> homeTrendingCategoryLoadNetworkData;
    private final MutableLiveData<TmpDataHolder> homeTrendingCategoryLoadNetworkListObj = new MutableLiveData<TmpDataHolder>();

    public ProductParameterHolder productParameterHolder = new ProductParameterHolder();

    public CategoryParameterHolder categoryParameterHolder = new CategoryParameterHolder().getTrendingCategories();

    @Inject
    HomeTrendingCategoryListViewModel(CategoryRepository repository) {
        Utils.psLog("Inside ProductViewModel");

        homeTrendingCategoryListData = Transformations.switchMap(homeTrendingCategoryListobj, obj -> {
            if (obj == null) {
                return AbsentLiveData.create();
            }

            return repository.getAllSearchCategory(obj.categoryParameterHolder, obj.limit, obj.offset);
        });

        homeTrendingCategoryLoadNetworkData = Transformations.switchMap(homeTrendingCategoryLoadNetworkListObj, obj -> {
            if (obj == null) {
                return AbsentLiveData.create();
            }

            return repository.getNextSearchCategory(obj.limit, obj.offset, obj.categoryParameterHolder);
        });
    }

    public void setHomeTrendingCatrgoryListDataObj(CategoryParameterHolder categoryParameterHolder, String limit, String offset) {

        HomeTrendingCategoryListViewModel.TmpDataHolder tmpDataHolder = new HomeTrendingCategoryListViewModel.TmpDataHolder();
        tmpDataHolder.limit = limit;
        tmpDataHolder.offset = offset;
        tmpDataHolder.categoryParameterHolder = categoryParameterHolder;
        homeTrendingCategoryListobj.setValue(tmpDataHolder);

    }

    public LiveData<Resource<List<Category>>> getHomeTrendingCategoryListData() {
        return homeTrendingCategoryListData;
    }


    public void setHomeTrendingCategoryLoadNetworkObj(String loginUserId, String limit, String offset, CategoryParameterHolder categoryParameterHolder) {
        if (!isLoading) {
            HomeTrendingCategoryListViewModel.TmpDataHolder tmpDataHolder = new HomeTrendingCategoryListViewModel.TmpDataHolder();
            tmpDataHolder.loginUserId = loginUserId;
            tmpDataHolder.limit = limit;
            tmpDataHolder.offset = offset;
            tmpDataHolder.categoryParameterHolder = categoryParameterHolder;
            homeTrendingCategoryLoadNetworkListObj.setValue(tmpDataHolder);

            // start loading
            setLoadingState(true);
        }
    }

    public LiveData<Resource<Boolean>> getHomeTrendingCategoryLoadNetworkData() {
        return homeTrendingCategoryLoadNetworkData;
    }

    class TmpDataHolder {
        public String loginUserId = "";
        public String limit = "";
        public String offset = "";
        public String shopId = "";
        public String orderBy = "";
        public CategoryParameterHolder categoryParameterHolder;

    }
}
