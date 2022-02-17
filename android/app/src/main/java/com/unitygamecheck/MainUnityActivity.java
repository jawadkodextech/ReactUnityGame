package com.unitygamecheck;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;

import com.alefeducation.abjadiyat.OverrideUnityActivity;
import com.unity3d.player.UnityPlayer;

public class MainUnityActivity extends OverrideUnityActivity {
    String param = "0";
    public static String gemM = "abc";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        param = getIntent().getExtras().getString("PARAM");
    }

    @Override
    protected void OnUnityLoaded() {
        UnityPlayer.UnitySendMessage(
                "Scripts",
                "LaunchGame",
                "{\"gameId\":" + param + ", \"gameAssetBundleURL\": \"https://www.abjadiayt.com/game1\", \"gameCurrentLevel\":5, \"gameTotalScores\":100, \"allowedScreenTime\":30 }"
        );
//        Toast.makeText(
//                getApplicationContext(),
//                "{\"gameId\":0, \"gameAssetBundleURL\": \"https://www.abjadiayt.com/game1\", \"gameCurrentLevel\":5, \"gameTotalScores\":100, \"allowedScreenTime\":30 }",
//                Toast.LENGTH_SHORT
//        ).show();
    }

    @Override
    protected void OnGameplayProgressReceived(String gameId, String isGameCompleted, String scores, String gameDuration) {
        String gam = "gameId:" + gameId + " isGameCompleted:" + isGameCompleted + " scores:" + scores + " gameDuration:" + gameDuration;
//        VoiceChangingModule.returnResponse.invoke(gam);
        SharedPreferences sharedpreferences = getSharedPreferences("UNITY_GAME", Context.MODE_PRIVATE);
        DummyAct.gem = gam;
        gemM = gam;
        SharedPreferences.Editor editor = sharedpreferences.edit();
        editor.putString("gemM", gam);
        editor.apply();
        Intent intent = new Intent(this, DummyAct.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT | Intent.FLAG_ACTIVITY_SINGLE_TOP);
        intent.putExtra("CameBack", gam);
        startActivity(intent);

    }
}
//
//
//package com.unitygamecheck;
//
//        import android.content.Intent;
//        import android.os.Bundle;
//        import android.widget.Toast;
//
//        import com.alefeducation.abjadiyat.OverrideUnityActivity;
//        import com.unity3d.player.UnityPlayer;
//
//public class MainUnityActivity extends OverrideUnityActivity {
//    String param = "0";
//
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        param = getIntent().getExtras().getString("PARAM");
//    }
//
//    @Override
//    public void onBackPressed() {
//        super.onBackPressed();
//
//    }
//
//    @Override
//    protected void OnUnityLoaded() {
//        String game = "{\"gameId\":" + param + ", \"gameAssetBundleURL\": \"https://www.abjadiayt.com/game1\", \"gameCurrentLevel\":5, \"gameTotalScores\":100, \"allowedScreenTime\":30 }";
//        UnityPlayer.UnitySendMessage(
//                "OnSceneLoaded",
//                "LaunchGame",
//                game
//        );
//        Toast.makeText(
//                getApplicationContext(),
//                game,
//                Toast.LENGTH_SHORT
//        ).show();
//    }
//
//    @Override
//    protected void OnGameplayProgressReceived(String gameId, String isGameCompleted, String scores, String gameDuration) {
//
//        if (gameId.equals("1122")) {
////            Intent sendBack = new Intent();
////            sendBack.putExtra("CameBack","TRUE");
////            setResult(Activity.RESULT_OK,sendBack);
////            finish();
//            Intent intent = new Intent(this, DummyAct.class);
//            intent.setFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT | Intent.FLAG_ACTIVITY_SINGLE_TOP);
//            intent.putExtra("CameBack", "TRUE");
//            startActivity(intent);
//        } else {
//            Toast.makeText(
//                    getApplicationContext(),
//                    "gameId:" + gameId + "  isGameCompleted:" + isGameCompleted + "  score:" + scores + "  gameDuration:" + gameDuration + "",
//                    Toast.LENGTH_SHORT
//            ).show();
//        }
//    }
//}
