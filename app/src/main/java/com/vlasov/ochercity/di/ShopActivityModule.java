package com.vlasov.ochercity.di;


import com.vlasov.ochercity.MainActivity;
import com.vlasov.ochercity.ShopActivity;
import com.vlasov.ochercity.SinglePlaceActivity;
import com.vlasov.ochercity.SplashScreenActivity;
import com.vlasov.ochercity.ui.apploading.AppLoadingActivity;
import com.vlasov.ochercity.ui.apploading.AppLoadingFragment;
import com.vlasov.ochercity.ui.basket.BasketListActivity;
import com.vlasov.ochercity.ui.basket.BasketListFragment;
import com.vlasov.ochercity.ui.blog.detail.BlogDetailActivity;
import com.vlasov.ochercity.ui.blog.detail.BlogDetailFragment;
import com.vlasov.ochercity.ui.blog.list.BlogListActivity;
import com.vlasov.ochercity.ui.blog.list.BlogListFragment;
import com.vlasov.ochercity.ui.category.CategoryListActivity;
import com.vlasov.ochercity.ui.category.CategoryListFragment;
import com.vlasov.ochercity.ui.category.TrendingCategoryFragment;
import com.vlasov.ochercity.ui.checkout.CheckoutActivity;
import com.vlasov.ochercity.ui.checkout.CheckoutFragment1;
import com.vlasov.ochercity.ui.checkout.CheckoutFragment2;
import com.vlasov.ochercity.ui.checkout.CheckoutFragment3;
import com.vlasov.ochercity.ui.checkout.CheckoutStatusFragment;
import com.vlasov.ochercity.ui.collection.CollectionActivity;
import com.vlasov.ochercity.ui.collection.CollectionFragment;
import com.vlasov.ochercity.ui.collection.productCollectionHeader.ProductCollectionHeaderListFragment;
import com.vlasov.ochercity.ui.comment.detail.CommentDetailActivity;
import com.vlasov.ochercity.ui.comment.detail.CommentDetailFragment;
import com.vlasov.ochercity.ui.comment.list.CommentListActivity;
import com.vlasov.ochercity.ui.comment.list.CommentListFragment;
import com.vlasov.ochercity.ui.contactus.ContactUsFragment;
import com.vlasov.ochercity.ui.forceupdate.ForceUpdateActivity;
import com.vlasov.ochercity.ui.forceupdate.ForceUpdateFragment;
import com.vlasov.ochercity.ui.gallery.GalleryActivity;
import com.vlasov.ochercity.ui.gallery.GalleryFragment;
import com.vlasov.ochercity.ui.gallery.detail.GalleryDetailActivity;
import com.vlasov.ochercity.ui.gallery.detail.GalleryDetailFragment;
import com.vlasov.ochercity.ui.language.LanguageFragment;
import com.vlasov.ochercity.ui.notification.detail.NotificationActivity;
import com.vlasov.ochercity.ui.notification.detail.NotificationFragment;
import com.vlasov.ochercity.ui.notification.list.NotificationListActivity;
import com.vlasov.ochercity.ui.notification.list.NotificationListFragment;
import com.vlasov.ochercity.ui.notification.setting.NotificationSettingFragment;
import com.vlasov.ochercity.ui.privacyandpolicy.PrivacyPolicyActivity;
import com.vlasov.ochercity.ui.privacyandpolicy.PrivacyPolicyFragment;
import com.vlasov.ochercity.ui.product.ShopFragment;
import com.vlasov.ochercity.ui.product.detail.ProductActivity;
import com.vlasov.ochercity.ui.product.detail.ProductDetailFragment;
import com.vlasov.ochercity.ui.product.favourite.FavouriteListActivity;
import com.vlasov.ochercity.ui.product.favourite.FavouriteListFragment;
import com.vlasov.ochercity.ui.product.filtering.CategoryFilterFragment;
import com.vlasov.ochercity.ui.product.filtering.FilterFragment;
import com.vlasov.ochercity.ui.product.filtering.FilteringActivity;
import com.vlasov.ochercity.ui.product.history.HistoryFragment;
import com.vlasov.ochercity.ui.product.history.UserHistoryListActivity;
import com.vlasov.ochercity.ui.product.list.ProductListActivity;
import com.vlasov.ochercity.ui.product.list.ProductListFragment;
import com.vlasov.ochercity.ui.product.productbycatId.ProductListByCatIdActivity;
import com.vlasov.ochercity.ui.product.productbycatId.ProductListByCatIdFragment;
import com.vlasov.ochercity.ui.product.search.SearchByCategoryActivity;
import com.vlasov.ochercity.ui.product.search.SearchCategoryFragment;
import com.vlasov.ochercity.ui.product.search.SearchCityListFragment;
import com.vlasov.ochercity.ui.product.search.SearchCountryListFragment;
import com.vlasov.ochercity.ui.product.search.SearchShopFragment;
import com.vlasov.ochercity.ui.product.search.SearchSubCategoryFragment;
import com.vlasov.ochercity.ui.rating.RatingListActivity;
import com.vlasov.ochercity.ui.rating.RatingListFragment;
import com.vlasov.ochercity.ui.setting.AppInfoActivity;
import com.vlasov.ochercity.ui.setting.AppInfoFragment;
import com.vlasov.ochercity.ui.setting.NotificationSettingActivity;
import com.vlasov.ochercity.ui.setting.SettingActivity;
import com.vlasov.ochercity.ui.setting.SettingFragment;
import com.vlasov.ochercity.ui.shop.ShopProfileFragment;
import com.vlasov.ochercity.ui.stripe.StripeActivity;
import com.vlasov.ochercity.ui.stripe.StripeFragment;
import com.vlasov.ochercity.ui.terms.TermsAndConditionsActivity;
import com.vlasov.ochercity.ui.terms.TermsAndConditionsFragment;
import com.vlasov.ochercity.ui.transaction.detail.TransactionActivity;
import com.vlasov.ochercity.ui.transaction.detail.TransactionFragment;
import com.vlasov.ochercity.ui.transaction.list.TransactionListActivity;
import com.vlasov.ochercity.ui.transaction.list.TransactionListFragment;
import com.vlasov.ochercity.ui.user.PasswordChangeActivity;
import com.vlasov.ochercity.ui.user.PasswordChangeFragment;
import com.vlasov.ochercity.ui.user.ProfileEditActivity;
import com.vlasov.ochercity.ui.user.ProfileEditFragment;
import com.vlasov.ochercity.ui.user.ProfileFragment;
import com.vlasov.ochercity.ui.user.UserForgotPasswordActivity;
import com.vlasov.ochercity.ui.user.UserForgotPasswordFragment;
import com.vlasov.ochercity.ui.user.UserLoginActivity;
import com.vlasov.ochercity.ui.user.UserLoginFragment;
import com.vlasov.ochercity.ui.user.UserRegisterActivity;
import com.vlasov.ochercity.ui.user.UserRegisterFragment;
import com.vlasov.ochercity.ui.user.verifyemail.VerifyEmailActivity;
import com.vlasov.ochercity.ui.user.verifyemail.VerifyEmailFragment;

import dagger.Module;
import dagger.android.ContributesAndroidInjector;

@Module
abstract class ShopActivityModule {

    @ContributesAndroidInjector(modules = MainModule.class)
    abstract ShopActivity contributeShopActivity();

    @ContributesAndroidInjector(modules = MainModule.class)
    abstract MainActivity contributeMainActivity();

    @ContributesAndroidInjector(modules = MainModule.class)
    abstract SinglePlaceActivity contributeSinglePlaceActivity();

    @ContributesAndroidInjector(modules = TransactionModule.class)
    abstract TransactionListActivity contributeTransactionActivity();

    @ContributesAndroidInjector(modules = FavouriteListModule.class)
    abstract FavouriteListActivity contributeFavouriteListActivity();

    @ContributesAndroidInjector(modules = UserHistoryModule.class)
    abstract UserHistoryListActivity contributeUserHistoryListActivity();

    @ContributesAndroidInjector(modules = UserRegisterModule.class)
    abstract UserRegisterActivity contributeUserRegisterActivity();

    @ContributesAndroidInjector(modules = UserForgotPasswordModule.class)
    abstract UserForgotPasswordActivity contributeUserForgotPasswordActivity();

    @ContributesAndroidInjector(modules = UserLoginModule.class)
    abstract UserLoginActivity contributeUserLoginActivity();

    @ContributesAndroidInjector(modules = PasswordChangeModule.class)
    abstract PasswordChangeActivity contributePasswordChangeActivity();

    @ContributesAndroidInjector(modules = ProductListByCatIdModule.class)
    abstract ProductListByCatIdActivity productListByCatIdActivity();

    @ContributesAndroidInjector(modules = FilteringModule.class)
    abstract FilteringActivity filteringActivity();

    @ContributesAndroidInjector(modules = CommentDetailModule.class)
    abstract CommentDetailActivity commentDetailActivity();

    @ContributesAndroidInjector(modules = DiscountDetailModule.class)
    abstract ProductActivity discountDetailActivity();

    @ContributesAndroidInjector(modules = NotificationModule.class)
    abstract NotificationListActivity notificationActivity();

    @ContributesAndroidInjector(modules = HomeFilteringActivityModule.class)
    abstract ProductListActivity contributehomeFilteringActivity();

    @ContributesAndroidInjector(modules = NotificationDetailModule.class)
    abstract NotificationActivity notificationDetailActivity();

    @ContributesAndroidInjector(modules = TransactionDetailModule.class)
    abstract TransactionActivity transactionDetailActivity();

    @ContributesAndroidInjector(modules = CheckoutActivityModule.class)
    abstract CheckoutActivity checkoutActivity();

    @ContributesAndroidInjector(modules = CommentListActivityModule.class)
    abstract CommentListActivity commentListActivity();

    @ContributesAndroidInjector(modules = BasketlistActivityModule.class)
    abstract BasketListActivity basketListActivity();

    @ContributesAndroidInjector(modules = GalleryDetailActivityModule.class)
    abstract GalleryDetailActivity galleryDetailActivity();

    @ContributesAndroidInjector(modules = GalleryActivityModule.class)
    abstract GalleryActivity galleryActivity();

    @ContributesAndroidInjector(modules = SearchByCategoryActivityModule.class)
    abstract SearchByCategoryActivity searchByCategoryActivity();

    @ContributesAndroidInjector(modules = TermsAndConditionsModule.class)
    abstract TermsAndConditionsActivity termsAndConditionsActivity();

    @ContributesAndroidInjector(modules = EditSettingModule.class)
    abstract SettingActivity editSettingActivity();

    @ContributesAndroidInjector(modules = LanguageChangeModule.class)
    abstract NotificationSettingActivity languageChangeActivity();

    @ContributesAndroidInjector(modules = ProfileEditModule.class)
    abstract ProfileEditActivity contributeProfileEditActivity();

    @ContributesAndroidInjector(modules = AppInfoModule.class)
    abstract AppInfoActivity AppInfoActivity();

    @ContributesAndroidInjector(modules = CategoryListActivityAppInfoModule.class)
    abstract CategoryListActivity categoryListActivity();

    @ContributesAndroidInjector(modules = RatingListActivityModule.class)
    abstract RatingListActivity ratingListActivity();

    @ContributesAndroidInjector(modules = CollectionModule.class)
    abstract CollectionActivity collectionActivity();

    @ContributesAndroidInjector(modules = StripeModule.class)
    abstract StripeActivity stripeActivity();

    @ContributesAndroidInjector(modules = BlogListActivityModule.class)
    abstract BlogListActivity BlogListActivity();

    @ContributesAndroidInjector(modules = BlogDetailModule.class)
    abstract BlogDetailActivity blogDetailActivity();

    @ContributesAndroidInjector(modules = forceUpdateModule.class)
    abstract ForceUpdateActivity forceUpdateActivity();

    @ContributesAndroidInjector(modules = appLoadingModule.class)
    abstract AppLoadingActivity appLoadingActivity();

    @ContributesAndroidInjector(modules = appLoadingModule.class)
    abstract SplashScreenActivity splashScreenActivity();

    @ContributesAndroidInjector(modules = VerifyEmailModule.class)
    abstract VerifyEmailActivity contributeVerifyEmailActivity();

    @ContributesAndroidInjector(modules = PrivacyPolicyActivityModule.class)
    abstract PrivacyPolicyActivity contributePrivacyPolicyActivity();
}

@Module
abstract class CheckoutActivityModule {

    @ContributesAndroidInjector
    abstract CheckoutFragment1 checkoutFragment1();

    @ContributesAndroidInjector
    abstract LanguageFragment languageFragment();

    @ContributesAndroidInjector
    abstract CheckoutFragment2 checkoutFragment2();

    @ContributesAndroidInjector
    abstract CheckoutFragment3 checkoutFragment3();

    @ContributesAndroidInjector
    abstract CheckoutStatusFragment checkoutStatusFragment();
}

@Module
abstract class MainModule {

    @ContributesAndroidInjector
    abstract ProductListFragment contributefeaturedProductFragment();

    @ContributesAndroidInjector
    abstract ShopFragment contributeSelectedShopFragment();

    @ContributesAndroidInjector
    abstract CategoryListFragment contributeCategoryFragment();

    @ContributesAndroidInjector
    abstract CategoryFilterFragment contributeTypeFilterFragment();

    @ContributesAndroidInjector
    abstract ProductCollectionHeaderListFragment contributeProductCollectionHeaderListFragment();

    @ContributesAndroidInjector
    abstract ContactUsFragment contributeContactUsFragment();

    @ContributesAndroidInjector
    abstract UserLoginFragment contributeUserLoginFragment();

    @ContributesAndroidInjector
    abstract UserForgotPasswordFragment contributeUserForgotPasswordFragment();

    @ContributesAndroidInjector
    abstract UserRegisterFragment contributeUserRegisterFragment();

    @ContributesAndroidInjector
    abstract NotificationSettingFragment contributeNotificationSettingFragment();

    @ContributesAndroidInjector
    abstract ProfileFragment contributeProfileFragment();

    @ContributesAndroidInjector
    abstract LanguageFragment contributeLanguageFragment();

    @ContributesAndroidInjector
    abstract FavouriteListFragment contributeFavouriteListFragment();

    @ContributesAndroidInjector
    abstract TransactionListFragment contributTransactionListFragment();

    @ContributesAndroidInjector
    abstract SettingFragment contributEditSettingFragment();

    @ContributesAndroidInjector
    abstract HistoryFragment historyFragment();

    @ContributesAndroidInjector
    abstract ShopProfileFragment contributeAboutUsFragmentFragment();

    @ContributesAndroidInjector
    abstract BasketListFragment basketFragment();

    @ContributesAndroidInjector
    abstract SearchShopFragment contributeSearchFragment();

    @ContributesAndroidInjector
    abstract NotificationListFragment contributeNotificationFragment();

    @ContributesAndroidInjector
    abstract AppInfoFragment contributeAppInfoFragment();

    @ContributesAndroidInjector
    abstract VerifyEmailFragment contributeVerifyEmailFragment();

    @ContributesAndroidInjector
    abstract PrivacyPolicyFragment contributePrivacyPolicyFragment();
}


@Module
abstract class ProfileEditModule {
    @ContributesAndroidInjector
    abstract ProfileEditFragment contributeProfileEditFragment();
}

@Module
abstract class TransactionModule {
    @ContributesAndroidInjector
    abstract TransactionListFragment contributeTransactionListFragment();
}


@Module
abstract class FavouriteListModule {
    @ContributesAndroidInjector
    abstract FavouriteListFragment contributeFavouriteFragment();
}

@Module
abstract class UserRegisterModule {
    @ContributesAndroidInjector
    abstract UserRegisterFragment contributeUserRegisterFragment();
}

@Module
abstract class UserForgotPasswordModule {
    @ContributesAndroidInjector
    abstract UserForgotPasswordFragment contributeUserForgotPasswordFragment();
}

@Module
abstract class UserLoginModule {
    @ContributesAndroidInjector
    abstract UserLoginFragment contributeUserLoginFragment();
}

@Module
abstract class PasswordChangeModule {
    @ContributesAndroidInjector
    abstract PasswordChangeFragment contributePasswordChangeFragment();
}

@Module
abstract class CommentDetailModule {
    @ContributesAndroidInjector
    abstract CommentDetailFragment commentDetailFragment();
}

@Module
abstract class DiscountDetailModule {
    @ContributesAndroidInjector
    abstract ProductDetailFragment discountDetailFragment();
}

@Module
abstract class NotificationModule {
    @ContributesAndroidInjector
    abstract NotificationListFragment notificationFragment();


}


@Module
abstract class NotificationDetailModule {
    @ContributesAndroidInjector
    abstract NotificationFragment notificationDetailFragment();
}

@Module
abstract class TransactionDetailModule {
    @ContributesAndroidInjector
    abstract TransactionFragment transactionDetailFragment();
}

@Module
abstract class UserHistoryModule {
    @ContributesAndroidInjector
    abstract HistoryFragment contributeHistoryFragment();
}

@Module
abstract class AppInfoModule {
    @ContributesAndroidInjector
    abstract AppInfoFragment contributeAppInfoFragment();
}

@Module
abstract class CategoryListActivityAppInfoModule {
    @ContributesAndroidInjector
    abstract CategoryListFragment contributeCategoryFragment();

    @ContributesAndroidInjector
    abstract TrendingCategoryFragment contributeTrendingCategoryFragment();
}

@Module
abstract class RatingListActivityModule {
    @ContributesAndroidInjector
    abstract RatingListFragment contributeRatingListFragment();
}

@Module
abstract class TermsAndConditionsModule {
    @ContributesAndroidInjector
    abstract TermsAndConditionsFragment TermsAndConditionsFragment();
}

@Module
abstract class EditSettingModule {
    @ContributesAndroidInjector
    abstract SettingFragment EditSettingFragment();
}

@Module
abstract class LanguageChangeModule {
    @ContributesAndroidInjector
    abstract NotificationSettingFragment notificationSettingFragment();
}

@Module
abstract class EditProfileModule {
    @ContributesAndroidInjector
    abstract ProfileFragment ProfileFragment();
}

@Module
abstract class ProductListByCatIdModule {
    @ContributesAndroidInjector
    abstract ProductListByCatIdFragment contributeProductListByCatIdFragment();

}

@Module
abstract class FilteringModule {

    @ContributesAndroidInjector
    abstract CategoryFilterFragment contributeTypeFilterFragment();

    @ContributesAndroidInjector
    abstract FilterFragment contributeSpecialFilteringFragment();
}

@Module
abstract class HomeFilteringActivityModule {
    @ContributesAndroidInjector
    abstract ProductListFragment contributefeaturedProductFragment();

    @ContributesAndroidInjector
    abstract CategoryListFragment contributeCategoryFragment();

    @ContributesAndroidInjector
    abstract CategoryFilterFragment contributeTypeFilterFragment();

    @ContributesAndroidInjector
    abstract CollectionFragment contributeCollectionFragment();
}

@Module
abstract class CommentListActivityModule {
    @ContributesAndroidInjector
    abstract CommentListFragment contributeCommentListFragment();
}

@Module
abstract class BasketlistActivityModule {
    @ContributesAndroidInjector
    abstract BasketListFragment contributeBasketListFragment();
}

@Module
abstract class GalleryDetailActivityModule {
    @ContributesAndroidInjector
    abstract GalleryDetailFragment contributeGalleryDetailFragment();
}

@Module
abstract class GalleryActivityModule {
    @ContributesAndroidInjector
    abstract GalleryFragment contributeGalleryFragment();
}

@Module
abstract class SearchByCategoryActivityModule {

    @ContributesAndroidInjector
    abstract SearchCategoryFragment contributeSearchCategoryFragment();

    @ContributesAndroidInjector
    abstract SearchSubCategoryFragment contributeSearchSubCategoryFragment();

    @ContributesAndroidInjector
    abstract SearchCityListFragment contributeSearchCityListFragment();

    @ContributesAndroidInjector
    abstract SearchCountryListFragment contributeSearchCountryListFragment();

}

@Module
abstract class CollectionModule {

    @ContributesAndroidInjector
    abstract CollectionFragment contributeCollectionFragment();

}

@Module
abstract class StripeModule {

    @ContributesAndroidInjector
    abstract StripeFragment contributeStripeFragment();

}

@Module
abstract class BlogListActivityModule {

    @ContributesAndroidInjector
    abstract BlogListFragment contributeBlogListFragment();

}

@Module
abstract class BlogDetailModule {

    @ContributesAndroidInjector
    abstract BlogDetailFragment contributeBlogDetailFragment();
}

@Module
abstract class forceUpdateModule {

    @ContributesAndroidInjector
    abstract ForceUpdateFragment contributeForceUpdateFragment();
}

@Module
abstract class appLoadingModule {

    @ContributesAndroidInjector
    abstract AppLoadingFragment contributeAppLoadingFragment();
}

@Module
abstract class VerifyEmailModule {
    @ContributesAndroidInjector
    abstract VerifyEmailFragment contributeVerifyEmailFragment();
}

@Module
abstract class PrivacyPolicyActivityModule {
    @ContributesAndroidInjector
    abstract PrivacyPolicyFragment contributePrivacyPolicyFragment();
}