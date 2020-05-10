package com.vlasov.ochercity.ui.category;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;

import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;

import com.vlasov.ochercity.Config;
import com.vlasov.ochercity.R;
import com.vlasov.ochercity.databinding.ActivityCategoryListBinding;
import com.vlasov.ochercity.ui.common.PSAppCompactActivity;
import com.vlasov.ochercity.utils.Constants;
import com.vlasov.ochercity.utils.MyContextWrapper;

public class CategoryListActivity extends PSAppCompactActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        ActivityCategoryListBinding activityFilteringBinding = DataBindingUtil.setContentView(this, R.layout.activity_category_list);

        initUI(activityFilteringBinding);

    }

    @Override
    protected void attachBaseContext(Context newBase) {

        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(newBase);
        String CURRENT_LANG_CODE = preferences.getString(Constants.LANGUAGE_CODE, Config.DEFAULT_LANGUAGE);
        String CURRENT_LANG_COUNTRY_CODE = preferences.getString(Constants.LANGUAGE_COUNTRY_CODE, Config.DEFAULT_LANGUAGE_COUNTRY_CODE);

        super.attachBaseContext(MyContextWrapper.wrap(newBase, CURRENT_LANG_CODE, CURRENT_LANG_COUNTRY_CODE, true));
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        Fragment fragment = getSupportFragmentManager().findFragmentById(R.id.content_frame);
        if (fragment != null) {
            fragment.onActivityResult(requestCode, resultCode, data);
        }
    }

    private void initUI(ActivityCategoryListBinding binding) {

        initToolbar(binding.toolbar, getString(R.string.category__list_title));

        switch (getIntent().getStringExtra(Constants.CATEGORY_TYPE)) {
            case Constants.CATEGORY:
                setupFragment(new CategoryListFragment());
                break;
            case Constants.CATEGORY_TRENDING:
                setupFragment(new TrendingCategoryFragment());
                break;
            default:
                break;
        }

    }

}
