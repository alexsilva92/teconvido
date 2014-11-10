package com.android.teconvido.activity;

import java.util.ArrayList;
import java.util.List;

import com.android.teconvido.R;
import com.teconvido.bd.modelo.Message;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.app.NotificationManager;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;


public class AlertActivity extends AbstractTopMenuActivity{
    

	private ListView lv_alerts;
    private List<Object> alerts;
    
    private DialogFragment requestAlertDialog;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            
            NotificationManager mNotificationManager =    
    				(NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE); 
            mNotificationManager.cancelAll();
            
            setContentView(R.layout.activity_alerts);
            
            lv_alerts = (ListView) findViewById(R.id.lv_alerts);  
            
            alerts = new ArrayList<Object>();
            Object alert = new Object();
            alerts.add(alert);
			
            alert = new Message();
            alerts.add(alert);
			
            alert = new Message();
            alerts.add(alert);
			
            alert = new Message();
            alerts.add(alert);
            
            alert = new Message();
            alerts.add(alert);
            
			AlertAdapter adapter = new AlertAdapter();
			lv_alerts.setAdapter(adapter);
	}
	
	private class AlertAdapter extends ArrayAdapter<Object> {
		
		public AlertAdapter() {
			super(context, R.layout.item_alert, alerts);
		}

		@Override
		public View getView(final int position, View convertView, ViewGroup parent) {				
			LayoutInflater inflater = context.getLayoutInflater();
			View item = inflater.inflate(R.layout.item_alert, null);
			
			TextView txtv_type = (TextView) item.findViewById(R.id.txtv_type);
			ImageView imgv_alert = (ImageView) item.findViewById(R.id.imgv_alert);
			LinearLayout ll_containerAlerts = (LinearLayout) 
					item.findViewById(R.id.ll_containerAlerts);
			
			if(position % 3 == 0){
				txtv_type.setText("SOLICITUD DE VIAJE");
				imgv_alert.setImageResource(R.drawable.add_user_80x80);		
				
				item.setOnClickListener(new OnClickListener(){

					@Override
					public void onClick(View v) {
						createRequestAlertDialog();
					}
					
				});
				
			}else if(position % 3 == 1){
				txtv_type.setText("ALERTA DE VIAJE");
				imgv_alert.setImageResource(R.drawable.alert_travel_80x80);
				
				item.setOnClickListener(new OnClickListener(){

					@Override
					public void onClick(View v) {
						Intent getTravel = new Intent(context,GetTravelActivity.class);
						getTravel.putExtra("idTravel", position);
						startActivity(getTravel);
					}
					
				});
				
			}else{
				txtv_type.setText("CONFIRMACIÓN DE VIAJE");
				imgv_alert.setImageResource(R.drawable.confirm_travel_80x80);
				
				item.setOnClickListener(new OnClickListener(){

					@Override
					public void onClick(View v) {
						Intent getTravel = new Intent(context,GetTravelActivity.class);
						getTravel.putExtra("idTravel", position);
						startActivity(getTravel);
					}
					
				});
			}
			
			if(position < 3){
				ll_containerAlerts.setBackgroundResource(R.drawable.btn_light_blue);
			}
			
			
			
			return item;
		}

	}
	
	private void createRequestAlertDialog(){
		if(requestAlertDialog == null){
			requestAlertDialog = new RequestAlertFragment();
			requestAlertDialog.show(getFragmentManager(), "requestAlertDialog"); 
		}
	}
		
	
	private class RequestAlertFragment extends DialogFragment {

	    @Override
	    public View onCreateView(LayoutInflater inflater, ViewGroup container,
	            Bundle savedInstanceState) {
	        View view = inflater.inflate(R.layout.dialog_request_alert, container);

	        getDialog().setTitle("Confirmar Pasajero");

	        Button btn_confirm = (Button) view.findViewById(R.id.btn_confirm);
	        btn_confirm.setOnClickListener(new OnClickListener(){

				@Override
				public void onClick(View v) {
					getDialog().dismiss();
					
				}
	        	
	        });
	        
	        return view;
	    }
	    
	    @Override
	    public void onDismiss (DialogInterface dialog){
	    	requestAlertDialog = null;
	    }
	}
}
