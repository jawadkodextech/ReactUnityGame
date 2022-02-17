package com.unitygamecheck;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class DummyAct extends AppCompatActivity {
    Boolean isCameFromBack = false;
    public static String gem = "";

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Intent intent = new Intent(this, MainUnityActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        intent.putExtra("PARAM", getIntent().getExtras().getString("PARAM"));
        intent.addFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT | Intent.FLAG_ACTIVITY_NO_ANIMATION);
//        Toast.makeText(
//                getApplicationContext(),
//                "Open act",
//                Toast.LENGTH_SHORT
//        ).show();
        startActivity(intent);
        new Handler(Looper.myLooper()).postDelayed(() -> isCameFromBack = true, 2000);
    }

    @Override
    protected void onResume() {
        super.onResume();
        if (isCameFromBack) {
//            Toast.makeText(
//                    getApplicationContext(),
//                    "Open act Dummy",
//                    Toast.LENGTH_SHORT
//            ).show();
            SharedPreferences sharedpreferences = getSharedPreferences("UNITY_GAME", Context.MODE_PRIVATE);
//            DummyAct.gem = gam;
//            gemM = gam;
//            SharedPreferences.Editor editor = sharedpreferences.edit();
//            editor.putString("gemM", gam);
//            editor.apply();
            Intent sendBack = new Intent();
            String ite = sharedpreferences.getString("gemM","abc");//MainUnityActivity.gemM;//getIntent().getExtras().getString("CameBack", "");
            sendBack.putExtra("CameBack", ite);
            VoiceChangingModule.returnResponse.invoke(ite);
            setResult(Activity.RESULT_OK, sendBack);
            finish();
        }
    }
}
