package com.rn_bridge_satismeter;


import android.app.Activity;

import com.facebook.react.bridge.Callback;
import com.facebook.react.bridge.ReactApplicationContext;
import com.facebook.react.bridge.ReactContextBaseJavaModule;
import com.facebook.react.bridge.ReactMethod;
import com.facebook.react.bridge.Callback;
import com.facebook.react.bridge.ReadableMap;
import com.facebook.react.bridge.ReadableMapKeySetIterator;
import com.facebook.react.bridge.ReadableType;
import com.satismeter.SatisMeter;

import java.util.HashMap;

public class SatisMeterModule extends ReactContextBaseJavaModule {

    private final ReactApplicationContext reactContext;

    public SatisMeterModule(ReactApplicationContext reactContext) {
        super(reactContext);
        this.reactContext = reactContext;
    }

    @Override
    public String getName() {
        return "SatisMeter";
    }

    @ReactMethod
    public void hook(String userId, String writeKey, ReadableMap traits, Callback callback) {
        final Activity activity = getCurrentActivity();
        callback.invoke("SatisMeter installation successful... userId: " + userId + " writeKey: " + writeKey);

        HashMap<String, Object> traitsMap = new HashMap();
        if (traits != null) {
            ReadableMapKeySetIterator iterator = traits.keySetIterator();
            while (iterator.hasNextKey()) {
                String key = iterator.nextKey();
                ReadableType type = traits.getType(key);
                if (type == ReadableType.String) {
                    traitsMap.put((String) key, (String) traits.getString(key));
                }
            }
        }

        SatisMeter.identify(activity, writeKey, userId, traitsMap);
    }
}
