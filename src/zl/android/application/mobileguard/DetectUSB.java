package zl.android.application.mobileguard;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.widget.Toast;

public class DetectUSB extends BroadcastReceiver {

	@Override
	public void onReceive(Context context, Intent intent) {
		if(intent.getAction().equalsIgnoreCase("android.intent.action.ACTION_POWER_CONNECTED")) {
			Toast.makeText(context, "USB connected……….", 200).show();
		}
	
		if(intent.getAction().equalsIgnoreCase("android.intent.action.ACTION_POWER_DISCONNECTED")) {
			Toast.makeText(context, "USB Disconnected……….", 200).show();
		}

	}

}

