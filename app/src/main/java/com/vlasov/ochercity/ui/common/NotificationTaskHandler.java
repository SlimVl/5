package com.vlasov.ochercity.ui.common;

import android.content.Context;

import com.vlasov.ochercity.repository.aboutus.AboutUsRepository;
import com.vlasov.ochercity.utils.Utils;

public class NotificationTaskHandler extends BackgroundTaskHandler {

    private final AboutUsRepository repository;

    public NotificationTaskHandler(AboutUsRepository repository) {
        super();

        this.repository = repository;
    }

    public void registerNotification(Context context, String platform, String token) {

        if(platform == null) return;

        if(platform.equals("")) return;

        Utils.psLog("Register Notification : Notification Handler");
        holdLiveData = repository.registerNotification(context, platform, token);
        loadingState.setValue(new LoadingState(true, null));

        //noinspection ConstantConditions
        holdLiveData.observeForever(this);

    }

    public void unregisterNotification(Context context, String platform, String token) {

        if(platform == null) return;

        if(platform.equals("")) return;

        Utils.psLog("Unregister Notification : Notification Handler");
        holdLiveData = repository.unregisterNotification(context, platform, token);
        loadingState.setValue(new LoadingState(true, null));

        //noinspection ConstantConditions
        holdLiveData.observeForever(this);

    }

}