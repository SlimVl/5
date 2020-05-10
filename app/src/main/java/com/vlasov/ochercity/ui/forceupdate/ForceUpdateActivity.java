package com.vlasov.ochercity.ui.forceupdate;

import android.os.Bundle;

import androidx.databinding.DataBindingUtil;

import com.vlasov.ochercity.R;
import com.vlasov.ochercity.databinding.ActivityForceUpdateBinding;
import com.vlasov.ochercity.ui.common.PSAppCompactActivity;

public class ForceUpdateActivity extends PSAppCompactActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        ActivityForceUpdateBinding binding = DataBindingUtil.setContentView(this, R.layout.activity_force_update);

        // Init all UI
        initUI(binding);

    }

    private void initUI(ActivityForceUpdateBinding binding) {

        // Toolbar
//        initToolbar(binding.toolbar, getResources().getString(R.string.comment__title));

        setupFragment(new ForceUpdateFragment());
        // Or you can call like this
        //setupFragment(new NewsListFragment(), R.id.content_frame);

    }

    @Override
    public void onBackPressed() {

    }
}
