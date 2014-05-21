package com.android.teconvido.activity;

import com.android.teconvido.R;

import android.os.Bundle;
import android.app.NotificationManager;
import android.content.Context;


public class AlertActivity extends AbstractTopMenuActivity{
    

	@Override
	protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            
            NotificationManager mNotificationManager =    
    				(NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE); 
            mNotificationManager.cancelAll();
            
            setContentView(R.layout.activity_test);
	}
}
