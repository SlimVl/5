package com.vlasov.ochercity.ui.blog.detail;

import android.os.Bundle;

import androidx.databinding.DataBindingUtil;

import com.vlasov.ochercity.R;
import com.vlasov.ochercity.databinding.ActivityBlogDetailBinding;
import com.vlasov.ochercity.ui.common.PSAppCompactActivity;

public class BlogDetailActivity extends PSAppCompactActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        ActivityBlogDetailBinding binding = DataBindingUtil.setContentView(this, R.layout.activity_blog_detail);

        initUI(binding);

    }

    private void initUI(ActivityBlogDetailBinding binding) {

        // Toolbar
        initToolbar(binding.toolbar, getResources().getString(R.string.blog_detail__title));

        // setup Fragment
        setupFragment(new BlogDetailFragment());

    }
}
