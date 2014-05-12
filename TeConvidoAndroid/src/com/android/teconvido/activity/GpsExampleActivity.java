package com.android.teconvido.activity;

import com.android.teconvido.R;

import com.android.utilities.gps.ServiceGps;

import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.Toast;
import android.app.Activity;


public class GpsExampleActivity extends Activity implements OnClickListener {
    
	private ServiceGps gps;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);

            setContentView(R.layout.activity_gps);
            
            Button bt_gps = (Button) findViewById(R.id.bt_gps);
            bt_gps.setOnClickListener((android.view.View.OnClickListener) this);

			gps = new ServiceGps(this);
			gps.start();		
	}

	@Override
	public void onClick(View view) {
		if (view.getId()==findViewById(R.id.bt_gps).getId()){	
			Toast.makeText(this, "Longitud: " + gps.getLongitude() + 
					"\nLatitud: " + gps.getLatitude(), Toast.LENGTH_SHORT).show();
		}
	}
}
