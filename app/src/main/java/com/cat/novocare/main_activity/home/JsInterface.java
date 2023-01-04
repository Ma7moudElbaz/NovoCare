package com.cat.novocare.main_activity.home;

import android.app.Activity;
import android.content.Context;
import android.content.ContextWrapper;
import android.content.Intent;
import android.util.Log;
import android.webkit.JavascriptInterface;
import android.widget.Toast;

import com.cat.novocare.video_call.activities.VideoActivity;

import org.json.JSONException;
import org.json.JSONObject;

public class JsInterface {
    Context mContext;

    JsInterface(Context mContext) {
        this.mContext = mContext;
    }

    @JavascriptInterface
    public void goToVideoCall(String accessToken, String roomName) {
        Intent i = new Intent(mContext, VideoActivity.class);
        i.putExtra("twilio_access_token", accessToken);
        i.putExtra("room_name", roomName);
        mContext.startActivity(i);
        scanForActivity(mContext).finish();
    }

    private static Activity scanForActivity(Context cont) {
        if (cont == null)
            return null;
        else if (cont instanceof Activity)
            return (Activity) cont;
        else if (cont instanceof ContextWrapper)
            return scanForActivity(((ContextWrapper) cont).getBaseContext());

        return null;
    }
}
