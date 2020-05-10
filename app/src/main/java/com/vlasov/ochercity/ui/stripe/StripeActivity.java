package com.vlasov.ochercity.ui.stripe;

import android.os.Bundle;

import androidx.databinding.DataBindingUtil;

import com.vlasov.ochercity.R;
import com.vlasov.ochercity.databinding.ActivityStripeBinding;
import com.vlasov.ochercity.ui.common.PSAppCompactActivity;

public class StripeActivity extends PSAppCompactActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        ActivityStripeBinding binding = DataBindingUtil.setContentView(this, R.layout.activity_stripe);

        initUI(binding);

    }

    private void initUI(ActivityStripeBinding binding) {

        // Toolbar
        initToolbar(binding.toolbar, getResources().getString(R.string.checkout__stripe));

        // setup Fragment
        setupFragment(new StripeFragment());

    }
}

