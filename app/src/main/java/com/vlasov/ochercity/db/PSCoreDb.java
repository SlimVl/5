package com.vlasov.ochercity.db;

import androidx.room.Database;
import androidx.room.RoomDatabase;
import androidx.room.TypeConverters;

import com.vlasov.ochercity.db.common.Converters;
import com.vlasov.ochercity.viewobject.AboutUs;
import com.vlasov.ochercity.viewobject.Basket;
import com.vlasov.ochercity.viewobject.Blog;
import com.vlasov.ochercity.viewobject.Category;
import com.vlasov.ochercity.viewobject.CategoryMap;
import com.vlasov.ochercity.viewobject.City;
import com.vlasov.ochercity.viewobject.Comment;
import com.vlasov.ochercity.viewobject.CommentDetail;
import com.vlasov.ochercity.viewobject.Country;
import com.vlasov.ochercity.viewobject.DeletedObject;
import com.vlasov.ochercity.viewobject.DiscountProduct;
import com.vlasov.ochercity.viewobject.FavouriteProduct;
import com.vlasov.ochercity.viewobject.FeaturedProduct;
import com.vlasov.ochercity.viewobject.HistoryProduct;
import com.vlasov.ochercity.viewobject.Image;
import com.vlasov.ochercity.viewobject.LatestProduct;
import com.vlasov.ochercity.viewobject.LikedProduct;
import com.vlasov.ochercity.viewobject.Noti;
import com.vlasov.ochercity.viewobject.PSAppInfo;
import com.vlasov.ochercity.viewobject.PSAppVersion;
import com.vlasov.ochercity.viewobject.Product;
import com.vlasov.ochercity.viewobject.ProductAttributeDetail;
import com.vlasov.ochercity.viewobject.ProductAttributeHeader;
import com.vlasov.ochercity.viewobject.ProductCollection;
import com.vlasov.ochercity.viewobject.ProductCollectionHeader;
import com.vlasov.ochercity.viewobject.ProductColor;
import com.vlasov.ochercity.viewobject.ProductListByCatId;
import com.vlasov.ochercity.viewobject.ProductMap;
import com.vlasov.ochercity.viewobject.ProductSpecs;
import com.vlasov.ochercity.viewobject.Rating;
import com.vlasov.ochercity.viewobject.RelatedProduct;
import com.vlasov.ochercity.viewobject.ShippingMethod;
import com.vlasov.ochercity.viewobject.Shop;
import com.vlasov.ochercity.viewobject.ShopByTagId;
import com.vlasov.ochercity.viewobject.ShopMap;
import com.vlasov.ochercity.viewobject.ShopTag;
import com.vlasov.ochercity.viewobject.SubCategory;
import com.vlasov.ochercity.viewobject.TransactionDetail;
import com.vlasov.ochercity.viewobject.TransactionObject;
import com.vlasov.ochercity.viewobject.User;
import com.vlasov.ochercity.viewobject.UserLogin;


/**
 * Created by Panacea-Soft on 11/20/17.
 * Contact Email : teamps.is.cool@gmail.com
 */

@Database(entities = {
        Image.class,
        Category.class,
        User.class,
        UserLogin.class,
        AboutUs.class,
        Product.class,
        LatestProduct.class,
        DiscountProduct.class,
        FeaturedProduct.class,
        SubCategory.class,
        ProductListByCatId.class,
        Comment.class,
        CommentDetail.class,
        ProductColor.class,
        ProductSpecs.class,
        RelatedProduct.class,
        FavouriteProduct.class,
        LikedProduct.class,
        ProductAttributeHeader.class,
        ProductAttributeDetail.class,
        Noti.class,
        TransactionObject.class,
        ProductCollectionHeader.class,
        ProductCollection.class,
        TransactionDetail.class,
        Basket.class,
        HistoryProduct.class,
        Shop.class,
        ShopTag.class,
        Blog.class,
        Rating.class,
        ShippingMethod.class,
        ShopByTagId.class,
        ProductMap.class,
        ShopMap.class,
        CategoryMap.class,
        PSAppInfo.class,
        PSAppVersion.class,
        DeletedObject.class,
        Country.class,
        City.class

}, version = 7, exportSchema = false)
//V2.3 = DBV 7
//V2.2 = DBV 7
//V2.1 = DBV 7
//V2.0 = DBV 7
//V1.9 = DBV 7
//V1.8 = DBV 7
//V1.7 = DBV 6
//V1.6 = DBV 5
//V1.5 = DBV 4
//V1.4 = DBV 3
//V1.3 = DBV 2


@TypeConverters({Converters.class})

public abstract class PSCoreDb extends RoomDatabase {

    abstract public UserDao userDao();

    abstract public ProductColorDao productColorDao();

    abstract public ProductSpecsDao productSpecsDao();

    abstract public ProductAttributeHeaderDao productAttributeHeaderDao();

    abstract public ProductAttributeDetailDao productAttributeDetailDao();

    abstract public BasketDao basketDao();

    abstract public HistoryDao historyDao();

    abstract public AboutUsDao aboutUsDao();

    abstract public ImageDao imageDao();

    abstract public CountryDao countryDao();

    abstract public CityDao cityDao();

    abstract public RatingDao ratingDao();

    abstract public CommentDao commentDao();

    abstract public CommentDetailDao commentDetailDao();

    abstract public ProductDao productDao();

    abstract public CategoryDao categoryDao();

    abstract public SubCategoryDao subCategoryDao();

    abstract public NotificationDao notificationDao();

    abstract public ProductCollectionDao productCollectionDao();

    abstract public TransactionDao transactionDao();

    abstract public TransactionOrderDao transactionOrderDao();

    abstract public ShopDao shopDao();

    abstract public BlogDao blogDao();

    abstract public ShippingMethodDao shippingMethodDao();

    abstract public ProductMapDao productMapDao();

    abstract public CategoryMapDao categoryMapDao();

    abstract public PSAppInfoDao psAppInfoDao();

    abstract public PSAppVersionDao psAppVersionDao();

    abstract public DeletedObjectDao deletedObjectDao();


//    /**
//     * Migrate from:
//     * version 1 - using Room
//     * to
//     * version 2 - using Room where the {@link } has an extra field: addedDateStr
//     */
//    public static final Migration MIGRATION_1_2 = new Migration(1, 2) {
//        @Override
//        public void migrate(@NonNull SupportSQLiteDatabase database) {
//            database.execSQL("ALTER TABLE news "
//                    + " ADD COLUMN addedDateStr INTEGER NOT NULL DEFAULT 0");
//        }
//    };

    /* More migration write here */
}