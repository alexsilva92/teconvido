package com.android.teconvido.activity;

import com.android.teconvido.R;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

public abstract class AbstractTopMenuActivity extends Activity {
	
	protected static AbstractTopMenuActivity context;
	private MenuItem itemAlert;
	private MenuItem itemMessages;
	
	
	private boolean isAlert;
	private boolean isMessages;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		context = this;
		getActionBar().setDisplayHomeAsUpEnabled(isEnableButtonHome());
	}
	
	@Override
	protected void onResume() {
		super.onResume();
		
		if(context != this)
			context = this;
		
		setAlert(AbstractTopMenuActivity.getInstance().isAlert);
		setMessages(AbstractTopMenuActivity.getInstance().isMessages);
	}
	
	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
	    switch (item.getItemId()) {
	    	case android.R.id.home:
	    		pushButtonHome();
	    		return true;
	    	case R.id.Alert:
	    		Intent alert = new Intent(context,AlertActivity.class);
	    		alert.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | 
	    					   Intent.FLAG_ACTIVITY_SINGLE_TOP);
				startActivity(alert);
				return true;
	    	case R.id.Message:
	    		Intent message = new Intent(context,MessageActivity.class);
	    		message.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | 
 					   Intent.FLAG_ACTIVITY_SINGLE_TOP);
				startActivity(message);
				return true;
			default:
				 return super.onOptionsItemSelected(item);				
	    }
	}
	
	protected void pushButtonHome(){
	    finish();
	}
	
	protected boolean isEnableButtonHome(){
	    return true;
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		crearMenu(menu);
		return true;
	}
	
	private void crearMenu(Menu menu){
		itemAlert = menu.add(0,R.id.Alert,1,R.string.alert_title);
		itemAlert.setIcon(R.drawable.no_alert_48x48);
		itemAlert.setShowAsAction(MenuItem.SHOW_AS_ACTION_IF_ROOM);
		
		itemMessages = menu.add(0,R.id.Message,0,R.string.message_title);
		itemMessages.setIcon(R.drawable.no_message_48x48);
		itemMessages.setShowAsAction(MenuItem.SHOW_AS_ACTION_IF_ROOM);
		
	}
	
	
	public void setAlert(boolean _isAlert){
		isAlert = _isAlert;
		if(itemAlert != null){
			if(_isAlert)
				itemAlert.setIcon(R.drawable.alert_48x48);
			else
				itemAlert.setIcon(R.drawable.no_alert_48x48);
		}
	}
	
	public void setMessages(boolean _isMessage){
		isMessages = _isMessage;
		if(itemAlert != null){
			if(isMessages)
				itemMessages.setIcon(R.drawable.message_48x48);
			else
				itemMessages.setIcon(R.drawable.no_message_48x48);
		}
	}
	
	public boolean isAlert(){
		return isAlert;
	}
	
	public boolean isMessages(){
		return isMessages;
	}
	

	public static AbstractTopMenuActivity getInstance(){
		return context;
	}
}
