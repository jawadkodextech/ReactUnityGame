package com.company.product;

import android.os.Bundle;
import android.widget.FrameLayout;

import com.unity3d.player.UnityPlayerActivity;

public abstract class OverrideUnityActivity extends UnityPlayerActivity {
    public static OverrideUnityActivity instance = null;

    abstract protected void OnUnityLoaded();

    abstract protected void OnGameplayProgressReceived(String gameId, String isGameCompleted, String scores, String gameDuration);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        instance = this;
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        instance = null;
    }
}
