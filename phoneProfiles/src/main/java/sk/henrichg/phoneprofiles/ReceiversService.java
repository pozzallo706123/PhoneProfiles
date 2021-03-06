package sk.henrichg.phoneprofiles;

import android.app.Service;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.IBinder;


public class ReceiversService extends Service {

	private final ScreenOnOffBroadcastReceiver screenOnOffReceiver = new ScreenOnOffBroadcastReceiver();
	
	@Override
    public void onCreate()
	{
		// start service for first start
		Intent eventsServiceIntent = new Intent(getApplicationContext(), FirstStartService.class);
		getApplicationContext().startService(eventsServiceIntent);
        
		IntentFilter intentFilter5 = new IntentFilter();
		intentFilter5.addAction(Intent.ACTION_SCREEN_ON);
		intentFilter5.addAction(Intent.ACTION_SCREEN_OFF);
		intentFilter5.addAction(Intent.ACTION_USER_PRESENT);
        getApplicationContext().registerReceiver(screenOnOffReceiver, intentFilter5);
	}
	 
	@Override
    public void onDestroy()
	{
        getApplicationContext().unregisterReceiver(screenOnOffReceiver);
    }
	 
	@Override
    public int onStartCommand(Intent intent, int flags, int startId)
	{
        // We want this service to continue running until it is explicitly
        // stopped, so return sticky.
        return START_STICKY;
    }
	
	@Override
	public void onTaskRemoved(Intent rootIntent)
	{
        //GlobalData.setApplicationStarted(getApplicationContext(), false);
        super.onTaskRemoved(rootIntent);
	}
	
	@Override
	public IBinder onBind(Intent intent)
	{
		return null;
	}

}
