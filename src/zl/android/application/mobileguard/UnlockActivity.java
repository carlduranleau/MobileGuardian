package zl.android.application.mobileguard;

import android.app.Activity;
import android.app.Instrumentation;
import android.app.admin.DevicePolicyManager;
import android.content.ComponentName;
import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class UnlockActivity extends Activity implements OnClickListener {
	private DevicePolicyManager mDevicePolicyManager;
	private ComponentName mComponentName;
	
	private boolean m_bDoInject = false, m_bRunning = true;
	
	// To keep track of activity's foreground/background status
	boolean isPaused;
	
	Instrumentation m_Instrumentation = new Instrumentation();
	
	Activity me;
	
	TextView message;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.unlockscreen);

		me = this;
		
		mDevicePolicyManager = (DevicePolicyManager) getSystemService(Context.DEVICE_POLICY_SERVICE);
		mComponentName = new ComponentName(this, AdminReceiver.class);
		
		message = (TextView) findViewById(R.id.textView1);
		Button btnClose = (Button) findViewById(R.id.button1);
		btnClose.setOnClickListener(this);
		Button btnLock = (Button) findViewById(R.id.button2);
		btnLock.setOnClickListener(this);		
		getWindow().getDecorView().setSystemUiVisibility(
                View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                | View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                | View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
                | View.SYSTEM_UI_FLAG_FULLSCREEN
                | View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY);

		getWindow().getDecorView().requestFocus();
		
		// start
	    final Thread t = new Thread() {
	        public void run() {
	        	while (m_bRunning) {
	        		// wait 1sec
	        		try { sleep(100); } catch (InterruptedException e) { e.printStackTrace(); }
	        		if (!m_bDoInject) continue;
	        		me.getWindow().getDecorView().requestFocus();
	        		m_bDoInject = false;
	        		me.startActivity(me.getIntent());
	        		Log.i("test", "Request Focusing!");
	        		//m_Instrumentation.sendKeyDownUpSync(KeyEvent.KEYCODE_BACK);
	        		//m_Instrumentation.sendKeySync(new KeyEvent(KeyEvent.ACTION_DOWN, KeyEvent.KEYCODE_BACK));
	            	//m_Instrumentation.sendPointerSync(MotionEvent.obtain(SystemClock.uptimeMillis(), SystemClock.uptimeMillis(),MotionEvent.ACTION_UP,0, 0, 0));
	       		}
	        }
	    };
	    t.start();
	}

	@Override
	public void onBackPressed() {
		lockDevice();
	}

	@Override
	public void onWindowFocusChanged(boolean hasFocus) {
		message.setText(Boolean.toString(hasFocus));
		if (m_bRunning) {
			m_bDoInject = !hasFocus;
		}
	}
	
	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.button2:
			lockDevice();
			break;
		case R.id.button1:
			m_bRunning = false;
			m_bDoInject = false;
			finish();
		}
	}
	
	@Override
	protected void onPause() {
	    super.onPause();

	    // Activity's been paused      
	    isPaused = true;
	}

	@Override
	protected void onResume() {
	    super.onResume();

	    // Activity's been resumed
	    isPaused = false;
	}
	private void lockDevice() {
		boolean isAdmin = mDevicePolicyManager
				.isAdminActive(mComponentName);
		if (isAdmin) {
			mDevicePolicyManager.lockNow();
		} else {
			Toast.makeText(getApplicationContext(),
					"Not Registered as admin", Toast.LENGTH_SHORT).show();
		}
	}
}
