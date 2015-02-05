package zl.android.application.mobileguard;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.View;

public class ScreenReceiver extends BroadcastReceiver {

	public static boolean wasScreenOn = true;
	public static boolean alarmServiceRunning = false;

	@Override
	public void onReceive(final Context context, final Intent intent) {
		Log.e("test", "onReceive");
		if (intent.getAction().equals(Intent.ACTION_SCREEN_OFF)) {
			// do whatever you need to do here
			wasScreenOn = false;
			Log.e("test", "wasScreenOn" + wasScreenOn);
		} else if (intent.getAction().equals(Intent.ACTION_SCREEN_ON)) {
			// and do whatever you need to do here
			wasScreenOn = true;
			Log.e("test", "wasScreenOn" + wasScreenOn);
		} else if (intent.getAction().equals(Intent.ACTION_USER_PRESENT)) {
			Log.e("test", "userpresent");

			Intent i = new Intent();
		    i.setClassName("zl.android.application.mobileguard", "zl.android.application.mobileguard.UnlockActivity");
		    i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
		    context.startActivity(i);

		    //Start alarm service that will be stopped by the UnlockActivity.
		    alarmServiceRunning = true;
		    
		}
	}
}
