package com.pinakbanik.leadingapps;


import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

public class Alarm extends BroadcastReceiver {

	private static final String TAG = "User";

	@Override
	public void onReceive(Context context, Intent intent) {
		Log.i(TAG, "BroadcastReceiver has received alarm intent.");
		Intent service1 = new Intent(context, AlarmService.class);
		context.startService(service1);
	}




}
