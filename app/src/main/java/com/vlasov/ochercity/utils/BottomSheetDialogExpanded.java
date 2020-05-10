package com.vlasov.ochercity.utils;

import android.content.Context;
import android.view.View;
import android.widget.FrameLayout;

import androidx.annotation.NonNull;

import com.google.android.material.bottomsheet.BottomSheetBehavior;
import com.google.android.material.bottomsheet.BottomSheetDialog;

public class BottomSheetDialogExpanded extends BottomSheetDialog {

    private BottomSheetBehavior mBehavior;

    public BottomSheetDialogExpanded(@NonNull Context context) {
        super(context);
    }

    @Override
    public void setContentView(View view) {
        super.setContentView(view);

        if(getWindow() != null) {
            FrameLayout frameLayout = getWindow().getDecorView().findViewById(com.google.android.material.R.id.design_bottom_sheet);
            mBehavior = BottomSheetBehavior.from(frameLayout);
            mBehavior.setState(BottomSheetBehavior.STATE_EXPANDED);
        }

    }

    @Override
    protected void onStart() {
        super.onStart();
        if(mBehavior != null) {
            mBehavior.setState(BottomSheetBehavior.STATE_EXPANDED);
        }
    }

    public void setState(int behavior) {
        if(mBehavior != null) {
            mBehavior.setState(behavior);
        }
    }
}
