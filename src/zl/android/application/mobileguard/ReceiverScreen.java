package zl.android.application.mobileguard;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

public class ReceiverScreen extends BroadcastReceiver {

	@Override
	public void onReceive(Context context, Intent intent) {
		Log.w("myApp", "onReceive");
		if (intent.getAction().equals(Intent.ACTION_SCREEN_ON)) {
			Log.w("myApp", "ACTION_SCREEN_ON");
			try {

			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		if (intent.getAction().equals(Intent.ACTION_SCREEN_OFF)) {
			Log.w("myApp", "ACTION_SCREEN_OFF");
			try {

			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		if (intent.getAction().equals(Intent.ACTION_USER_PRESENT)) {
			Log.w("myApp", "ACTION_USER_PRESENT");
			try {

			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
}