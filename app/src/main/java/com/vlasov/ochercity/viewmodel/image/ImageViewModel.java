package com.vlasov.ochercity.viewmodel.image;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Transformations;

import com.vlasov.ochercity.repository.image.ImageRepository;
import com.vlasov.ochercity.utils.AbsentLiveData;
import com.vlasov.ochercity.viewmodel.common.PSViewModel;
import com.vlasov.ochercity.viewobject.Image;
import com.vlasov.ochercity.viewobject.common.Resource;

import java.util.List;

import javax.inject.Inject;

/**
 * Created by Panacea-Soft on 12/8/17.
 * Contact Email : teamps.is.cool@gmail.com
 */


public class ImageViewModel extends PSViewModel {


    //region Variables

    // Get Image Video List
    private final LiveData<Resource<List<Image>>> imageListLiveData;
    private MutableLiveData<TmpDataHolder> imageParentObj = new MutableLiveData<>();

    public String id;
    public List<Image> newsImageList;
    public String imgId;
    public String imgType;

    //endregion


    //region Constructors

    @Inject
    ImageViewModel(ImageRepository repository) {

        imageListLiveData = Transformations.switchMap(imageParentObj, obj -> {
            if (obj == null) {
                return AbsentLiveData.create();
            }
            return repository.getImageList(obj.imgType, obj.imageParentId);
        });

    }

    //endregion


    //region Methods

    public void setImageParentId(String imageType, String imageParentId) {
        TmpDataHolder tmpDataHolder = new TmpDataHolder(imageType, imageParentId);
        this.imageParentObj.setValue(tmpDataHolder);
    }

    public LiveData<Resource<List<Image>>> getImageListLiveData() {
        return imageListLiveData;
    }


    class TmpDataHolder {
        String imgType ;
        String imageParentId ;

        public TmpDataHolder(String imgType, String imageParentId) {
            this.imgType = imgType;
            this.imageParentId = imageParentId;
        }
    }

    //endregion

}
