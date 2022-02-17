package com.unitygamecheck;

import android.app.Activity;
import android.content.Intent;
import android.media.MediaPlayer;
import android.media.PlaybackParams;
import android.os.Build;

import androidx.annotation.RequiresApi;

import com.facebook.react.bridge.ActivityEventListener;
import com.facebook.react.bridge.BaseActivityEventListener;
import com.facebook.react.bridge.Callback;
import com.facebook.react.bridge.ReactApplicationContext;
import com.facebook.react.bridge.ReactContextBaseJavaModule;
import com.facebook.react.bridge.ReactMethod;

public class VoiceChangingModule extends ReactContextBaseJavaModule {
    ReactApplicationContext mContext;
    public static Callback returnResponse;
    private final ActivityEventListener mActivityEventListener = new BaseActivityEventListener() {
        @Override
        public void onActivityResult(Activity activity, int requestCode, int resultCode, Intent data) {
            super.onActivityResult(activity, requestCode, resultCode, data);
            if (resultCode == Activity.RESULT_OK && requestCode == 121) {
                returnResponse.invoke(data.getStringExtra("CameBack"));
            }
//            Toast.makeText(
//                    mContext.getCurrentActivity().getApplicationContext(),
//                    "Call Back",
//                    Toast.LENGTH_SHORT
//            ).show();
        }

        @Override
        public void onActivityResult(int requestCode, int resultCode, Intent data) {
            super.onActivityResult(requestCode, resultCode, data);
            if (resultCode == Activity.RESULT_OK && requestCode == 121) {
                returnResponse.invoke(data.getStringExtra("CameBack"));
            }
//            Toast.makeText(
//                    mContext.getCurrentActivity().getApplicationContext(),
//                    "Call Back",
//                    Toast.LENGTH_SHORT
//            ).show();
        }
    };

    VoiceChangingModule(ReactApplicationContext context) {
        super(context);
        mContext = context;

    }


    @Override
    public String getName() {
        return "VoiceChangingModule";
    }

    @RequiresApi(api = Build.VERSION_CODES.M)
    @ReactMethod
    public void changeVoiceToAlien(String game, Callback callback) {
        mContext.addActivityEventListener(mActivityEventListener);
        VoiceChangingModule.returnResponse = callback;
        Activity currentActivity = getCurrentActivity();
        if (currentActivity == null) {
//            promise.reject(E_ACTIVITY_DOES_NOT_EXIST, "Activity doesn't exist");
            return;
        }
        Intent intent = new Intent(currentActivity, DummyAct.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        intent.putExtra("PARAM",game);
        intent.addFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT | Intent.FLAG_ACTIVITY_NO_ANIMATION);
//        Toast.makeText(
//                currentActivity.getApplicationContext(),
//                "Open act",
//                Toast.LENGTH_SHORT
//        ).show();
        currentActivity.startActivityForResult(intent, 121);

        // MediaPlayer mp = new MediaPlayer();
        // PlaybackParams playbackParams = new PlaybackParams();
        // playbackParams.setPitch(Float.parseFloat(String.valueOf(0.6f)));
        // try {
        //     mp.setDataSource(file);
        //     mp.prepare();
        //     mp.setPlaybackParams(playbackParams);
        //     mp.start();
        // } catch (Exception e) {
        //     e.printStackTrace();
        // }
    }

    @RequiresApi(api = Build.VERSION_CODES.M)
    @ReactMethod
    public void changeVoiceToChild(String file) {
        MediaPlayer mp = new MediaPlayer();
        PlaybackParams playbackParams = new PlaybackParams();
        playbackParams.setPitch(Float.parseFloat(String.valueOf(1.8f)));
        try {
            mp.setDataSource(file);
            mp.prepare();
            mp.setPlaybackParams(playbackParams);
            mp.start();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @RequiresApi(api = Build.VERSION_CODES.M)
    @ReactMethod
    public void speedUpVoice(String file) {
        MediaPlayer mp = new MediaPlayer();
        PlaybackParams playbackParams = new PlaybackParams();
        playbackParams.setSpeed(Float.parseFloat(String.valueOf(2.5)));
        try {
            mp.setDataSource(file);
            mp.prepare();
            mp.setPlaybackParams(playbackParams);
            mp.start();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @RequiresApi(api = Build.VERSION_CODES.M)
    @ReactMethod
    public void slowDownVoice(String file) {
        MediaPlayer mp = new MediaPlayer();
        PlaybackParams playbackParams = new PlaybackParams();
        playbackParams.setSpeed(Float.parseFloat(String.valueOf(0.4)));
        try {
            mp.setDataSource(file);
            mp.prepare();
            mp.setPlaybackParams(playbackParams);
            mp.start();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}