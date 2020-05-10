package com.vlasov.ochercity.di;

import android.app.Application;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

import androidx.room.Room;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.vlasov.ochercity.Config;
import com.vlasov.ochercity.api.PSApiService;
import com.vlasov.ochercity.db.AboutUsDao;
import com.vlasov.ochercity.db.BasketDao;
import com.vlasov.ochercity.db.BlogDao;
import com.vlasov.ochercity.db.CategoryDao;
import com.vlasov.ochercity.db.CategoryMapDao;
import com.vlasov.ochercity.db.CityDao;
import com.vlasov.ochercity.db.CommentDao;
import com.vlasov.ochercity.db.CommentDetailDao;
import com.vlasov.ochercity.db.CountryDao;
import com.vlasov.ochercity.db.DeletedObjectDao;
import com.vlasov.ochercity.db.HistoryDao;
import com.vlasov.ochercity.db.ImageDao;
import com.vlasov.ochercity.db.NotificationDao;
import com.vlasov.ochercity.db.PSAppInfoDao;
import com.vlasov.ochercity.db.PSAppVersionDao;
import com.vlasov.ochercity.db.PSCoreDb;
import com.vlasov.ochercity.db.ProductAttributeDetailDao;
import com.vlasov.ochercity.db.ProductAttributeHeaderDao;
import com.vlasov.ochercity.db.ProductCollectionDao;
import com.vlasov.ochercity.db.ProductColorDao;
import com.vlasov.ochercity.db.ProductDao;
import com.vlasov.ochercity.db.ProductMapDao;
import com.vlasov.ochercity.db.ProductSpecsDao;
import com.vlasov.ochercity.db.RatingDao;
import com.vlasov.ochercity.db.ShippingMethodDao;
import com.vlasov.ochercity.db.ShopDao;
import com.vlasov.ochercity.db.SubCategoryDao;
import com.vlasov.ochercity.db.TransactionDao;
import com.vlasov.ochercity.db.TransactionOrderDao;
import com.vlasov.ochercity.db.UserDao;
import com.vlasov.ochercity.utils.AppLanguage;
import com.vlasov.ochercity.utils.Connectivity;
import com.vlasov.ochercity.utils.LiveDataCallAdapterFactory;

import java.util.concurrent.TimeUnit;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

@Module(includes = ViewModelModule.class)
class AppModule {

    @Singleton
    @Provides
    PSApiService providePSApiService() {

        Gson gson = new GsonBuilder()
                .setDateFormat("yyyy-MM-dd HH:mm:ss")
                .create();

        OkHttpClient okHttpClient = new OkHttpClient.Builder()
                .writeTimeout(1, TimeUnit.MINUTES)
                .readTimeout(1, TimeUnit.MINUTES)
                .build();

        return new Retrofit.Builder()
                .baseUrl(Config.APP_API_URL)
                .client(okHttpClient)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .addCallAdapterFactory(new LiveDataCallAdapterFactory())
                .build()
                .create(PSApiService.class);

    }

    @Singleton
    @Provides
    PSCoreDb provideDb(Application app) {
        return Room.databaseBuilder(app, PSCoreDb.class, "PSApp.db")
                //.addMigrations(MIGRATION_1_2)
                .fallbackToDestructiveMigration()
                .build();
    }

    @Singleton
    @Provides
    Connectivity provideConnectivity(Application app) {
        return new Connectivity(app);
    }

    @Singleton
    @Provides
    SharedPreferences provideSharedPreferences(Application app) {
        return PreferenceManager.getDefaultSharedPreferences(app.getApplicationContext());
    }

    @Singleton
    @Provides
    UserDao provideUserDao(PSCoreDb db) {
        return db.userDao();
    }

    @Singleton
    @Provides
    AppLanguage provideCurrentLanguage(SharedPreferences sharedPreferences) {
        return new AppLanguage(sharedPreferences);
    }

    @Singleton
    @Provides
    AboutUsDao provideAboutUsDao(PSCoreDb db) {
        return db.aboutUsDao();
    }

    @Singleton
    @Provides
    ImageDao provideImageDao(PSCoreDb db) {
        return db.imageDao();
    }

    @Singleton
    @Provides
    ProductDao provideProductDao(PSCoreDb db) {
        return db.productDao();
    }

    @Singleton
    @Provides
    CountryDao provideCountryDao(PSCoreDb db) {
        return db.countryDao();
    }

    @Singleton
    @Provides
    CityDao provideCityDao(PSCoreDb db) {
        return db.cityDao();
    }

    @Singleton
    @Provides
    ProductColorDao provideProductColorDao(PSCoreDb db) {
        return db.productColorDao();
    }

    @Singleton
    @Provides
    ProductSpecsDao provideProductSpecsDao(PSCoreDb db) {
        return db.productSpecsDao();
    }

    @Singleton
    @Provides
    ProductAttributeHeaderDao provideProductAttributeHeaderDao(PSCoreDb db) {
        return db.productAttributeHeaderDao();
    }

    @Singleton
    @Provides
    ProductAttributeDetailDao provideProductAttributeDetailDao(PSCoreDb db) {
        return db.productAttributeDetailDao();
    }

    @Singleton
    @Provides
    BasketDao provideBasketDao(PSCoreDb db) {
        return db.basketDao();
    }

    @Singleton
    @Provides
    HistoryDao provideHistoryDao(PSCoreDb db) {
        return db.historyDao();
    }

    @Singleton
    @Provides
    CategoryDao provideCategoryDao(PSCoreDb db) {
        return db.categoryDao();
    }

    @Singleton
    @Provides
    RatingDao provideRatingDao(PSCoreDb db) {
        return db.ratingDao();
    }

    @Singleton
    @Provides
    SubCategoryDao provideSubCategoryDao(PSCoreDb db) {
        return db.subCategoryDao();
    }

    @Singleton
    @Provides
    CommentDao provideCommentDao(PSCoreDb db) {
        return db.commentDao();
    }

    @Singleton
    @Provides
    CommentDetailDao provideCommentDetailDao(PSCoreDb db) {
        return db.commentDetailDao();
    }

    @Singleton
    @Provides
    NotificationDao provideNotificationDao(PSCoreDb db){return db.notificationDao();}

    @Singleton
    @Provides
    ProductCollectionDao provideProductCollectionDao(PSCoreDb db){return db.productCollectionDao();}

    @Singleton
    @Provides
    TransactionDao provideTransactionDao(PSCoreDb db){return db.transactionDao();}

    @Singleton
    @Provides
    TransactionOrderDao provideTransactionOrderDao(PSCoreDb db){return db.transactionOrderDao();}

//    @Singleton
//    @Provides
//    TrendingCategoryDao provideTrendingCategoryDao(PSCoreDb db){return db.trendingCategoryDao();}

    @Singleton
    @Provides
    ShopDao provideShopDao(PSCoreDb db){return db.shopDao();}

    @Singleton
    @Provides
    BlogDao provideNewsFeedDao(PSCoreDb db){return db.blogDao();}

    @Singleton
    @Provides
    ShippingMethodDao provideShippingMethodDao(PSCoreDb db){return db.shippingMethodDao();}

    @Singleton
    @Provides
    ProductMapDao provideProductMapDao(PSCoreDb db){return db.productMapDao();}

    @Singleton
    @Provides
    CategoryMapDao provideCategoryMapDao(PSCoreDb db){return db.categoryMapDao();}

    @Singleton
    @Provides
    PSAppInfoDao providePSAppInfoDao(PSCoreDb db){return db.psAppInfoDao();}

    @Singleton
    @Provides
    PSAppVersionDao providePSAppVersionDao(PSCoreDb db){return db.psAppVersionDao();}

    @Singleton
    @Provides
    DeletedObjectDao provideDeletedObjectDao(PSCoreDb db){return db.deletedObjectDao();}
}
