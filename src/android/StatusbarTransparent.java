package net.ekuwang.cordova.plugin.statusbar;

import org.apache.cordova.CordovaPlugin;
import org.apache.cordova.CallbackContext;
import org.json.JSONArray;
import org.json.JSONException;

import android.os.Build.VERSION;
import android.os.Build.VERSION_CODES;

import android.graphics.Color;

import android.view.Window;
import android.view.WindowManager;
import android.view.WindowManager.LayoutParams;

public class StatusbarTransparent extends CordovaPlugin {

	@Override
	public boolean execute(String action, JSONArray args, CallbackContext callback) throws JSONException {
		// grab the correct methods
		if (action.equalsIgnoreCase("enable")) {
			if (VERSION.SDK_INT >= VERSION_CODES.KITKAT) {
				cordova.getActivity().runOnUiThread(new Runnable() {
					public void run() {
						Window window = cordova.getActivity().getWindow();
						window.addFlags(LayoutParams.FLAG_TRANSLUCENT_STATUS);
						window.addFlags(LayoutParams.FLAG_TRANSLUCENT_NAVIGATION);
						window.addFlags(LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
						window.setStatusBarColor(Color.TRANSPARENT);
						window.setNavigationBarColor(Color.TRANSPARENT);
						window.clearFlags(WindowManager.LayoutParams.FLAG_FORCE_NOT_FULLSCREEN);
					}
				});
				callback.success();
			} else {
				callback.error("not supported");
			}
			return true;
		} else if (action.equalsIgnoreCase("disable")) {
			if (VERSION.SDK_INT >= VERSION_CODES.KITKAT) {
				cordova.getActivity().runOnUiThread(new Runnable() {
					public void run() {
						cordova.getActivity().getWindow().clearFlags(LayoutParams.FLAG_TRANSLUCENT_STATUS);
					}
				});
				callback.success();
			} else {
				callback.error("not supported");
			}
			return true;
		} else {
			callback.error("Unknown Action: " + action);
			return false;
		}
	}
}