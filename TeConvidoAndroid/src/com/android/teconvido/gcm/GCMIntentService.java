package com.android.teconvido.gcm;

import com.android.teconvido.activity.AbstractTopMenuActivity;
import com.google.android.gms.gcm.GoogleCloudMessaging;

import android.app.IntentService;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;

public class GCMIntentService extends IntentService 
{
	//private static final int NOTIF_ALERTA_ID = 1;

	public GCMIntentService() {
        super("GCMIntentService");
    }
	
	@Override
    protected void onHandleIntent(Intent intent) 
	{
        GoogleCloudMessaging gcm = GoogleCloudMessaging.getInstance(this);
        
        String messageType = gcm.getMessageType(intent);
        Bundle extras = intent.getExtras();

        if (!extras.isEmpty()) 
        {  
            if (GoogleCloudMessaging.MESSAGE_TYPE_MESSAGE.equals(messageType)) 
            {
        		AlertTask alertTask = new AlertTask();
        		alertTask.execute();
        		//mostrarNotification(extras.getString("type"),extras.getString("msg"));
            }
        }
        
        GCMBroadcastReceiver.completeWakefulIntent(intent);
    }
	
	/*private void mostrarNotification(String type, String msg) 
	{
		NotificationManager mNotificationManager =    
				(NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE); 
		
		NotificationCompat.Builder mBuilder = 
			new NotificationCompat.Builder(this)  
				.setSmallIcon(R.drawable.ic_launcher) 
				.setContentTitle(type)  
				.setContentText(msg);
		
		Intent intent =  new Intent(this, AlertActivity.class);    
		PendingIntent contIntent = PendingIntent.getActivity(     
				this, 0, intent, 0);   
		
		mBuilder.setContentIntent(contIntent);
		
		mNotificationManager.notify(NOTIF_ALERTA_ID, mBuilder.build());
    }*/
	
	private class AlertTask extends AsyncTask<Void, Void, Void> {
	    
		@Override
	    protected Void doInBackground(Void... params) {
			return null;
		}

	    @Override
	    protected void onPostExecute(Void result) {
	    	if(AbstractTopMenuActivity.getInstance() != null){
	    		AbstractTopMenuActivity.getInstance().setAlert(true);
	    		AbstractTopMenuActivity.getInstance().setMessages(true);
	        }
	        this.cancel(true);
	    }
	}
}
