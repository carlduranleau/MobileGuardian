package zl.android.application.mobileguard;

import java.util.Timer;
import java.util.TimerTask;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;

public class AlarmService extends Service {
	private final int UPDATE_INTERVAL = 1000;
	private Timer timer = new Timer();
	
	@Override
	public IBinder onBind(Intent intent) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int onStartCommand(Intent intent, int flags, int startId) {
		
		timer.scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {
        		if (!ScreenReceiver.alarmServiceRunning) {
        			
        		}
            }
        }, 0, UPDATE_INTERVAL);
		
		return super.onStartCommand(intent, flags, startId);
	}

	
	
	
}
