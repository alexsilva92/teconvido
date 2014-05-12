package com.android.teconvido.activity;

import java.io.IOException;

import com.android.teconvido.R;
import com.android.teconvido.preferences.ApplicationPreferences;
import com.google.android.gms.gcm.GoogleCloudMessaging;
import com.teconvido.client.TeConvidoClient;

import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;


public class LoginActivity extends Activity implements OnClickListener{
	
	public static final String SENDER_ID = "759851602835";
	public static final String SERVER_HOST = "155.210.68.155";
	public static final int SERVER_PORT = 20000;
	
	private Context context;
	
	private LoginTask loginTask;
	private ApplicationPreferences preferences;
	
	private EditText edtx_email;
	private EditText edtx_password;
	private CheckBox chbx_remember;
	
	private LinearLayout ll_loading;
	private LinearLayout ll_login;
	
	private GoogleCloudMessaging gcm;
	private String gcmId;
	
	private TeConvidoClient client;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);

            setContentView(R.layout.activity_login);
            
            context = getApplicationContext();
            
            gcm = GoogleCloudMessaging.getInstance(this);
            
            preferences = new ApplicationPreferences(context);
            
            client = new TeConvidoClient(SERVER_HOST,SERVER_PORT);

            initialization();
	}
	
	private void initialization(){
		Button bt_login = (Button) findViewById(R.id.bt_login);
        bt_login.setOnClickListener((android.view.View.OnClickListener) this);
        
        Button bt_register = (Button) findViewById(R.id.bt_register);
        bt_register.setOnClickListener((android.view.View.OnClickListener) this);
        
        TextView txtv_forgetPassword = (TextView) findViewById(R.id.txtv_forgetPassword);
        txtv_forgetPassword.setOnClickListener((android.view.View.OnClickListener) this);
        
        edtx_email = (EditText) findViewById(R.id.edtx_email);
        edtx_password = (EditText) findViewById(R.id.edtx_password);
        chbx_remember = (CheckBox) findViewById(R.id.chbx_remember);
        
        ll_login = (LinearLayout) findViewById(R.id.ll_login);
        ll_loading= (LinearLayout) findViewById(R.id.ll_loading);
       
        gcmId = preferences.getGcmId();
        
        edtx_email.setText(preferences.getEmail());
        edtx_password.setText(preferences.getPassword());
        chbx_remember.setChecked(preferences.isRememeber());
	}
	
	@Override
	protected void onRestart(){
		super.onRestart();
		ll_loading.setVisibility(View.GONE);
    	ll_login.setVisibility(View.VISIBLE);	
    	if(!chbx_remember.isChecked()){
    		edtx_email.setText("");
    		edtx_password.setText("");
    	}
	}

	@Override
	public void onClick(View view) {
		if (view.getId()==findViewById(R.id.bt_login).getId()){	
			
			String email = edtx_email.getText().toString().trim();
			String password = edtx_password.getText().toString().trim();
			
			if(email == null || password == null || email.equals("") || 
			   password.equals("") ){
				
				Toast.makeText(context, getString(R.string.toast_login_fields_empty), 
						Toast.LENGTH_SHORT).show();
				
			} else if(!android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches()){
				
				Toast.makeText(context, getString(R.string.toast_email_error), 
						Toast.LENGTH_SHORT).show();
				
			} else{
				loginTask = new LoginTask(email,password);
				loginTask.execute();
			}
		} else if(view.getId()==findViewById(R.id.bt_register).getId()){
			Intent register = new Intent(context,RegisterActivity.class);
			startActivity(register);
			
		} else if(view.getId()==findViewById(R.id.txtv_forgetPassword).getId()){
			Intent recoverPassword = new Intent(context,RecoverPasswordActivity.class);
			startActivity(recoverPassword);
		}
	}
		
	private enum ReturnLogin{CORRECT,INCORRECT,CONNECTION_ERROR};
	
	private class LoginTask extends AsyncTask<String, Void, ReturnLogin> {
		private String email;
		private String password;
		 		 
		public LoginTask(String email, String password){
			super();
		 	this.email = email;
		 	this.password = password;
		}
	    
		@Override
	    protected ReturnLogin doInBackground(String... params) {
			try {
				boolean login = client.login(email, password);
				if(login){
					if (gcmId == null || gcmId.equals("")) {
						gcmId = gcm.register(SENDER_ID);	
			        }
					client.updateGcmCode(email, gcmId);
					return ReturnLogin.CORRECT;
				}else{
					return ReturnLogin.INCORRECT;
				}
			} catch (IOException e) {
				Log.d("IOException", e.getMessage());
				return ReturnLogin.CONNECTION_ERROR;
			}  
	    }

	    @Override
	    protected void onPostExecute(ReturnLogin result) {
	    	switch(result){
	    		case CORRECT: 
	    			if(chbx_remember.isChecked()){
	    				preferences.setEmail(email);
		        		preferences.setPassword(password);
		        	}else{
		        		preferences.setEmail("");
		        		preferences.setPassword("");
		        	}
		        	preferences.setRemember(chbx_remember.isChecked());
		        		
		        	Intent main = new Intent(context,MenuActivity.class);
					startActivity(main);
		        	break;
		        
	    		case INCORRECT: 
	    			Toast.makeText(context, getString(R.string.toast_login_incorrect), Toast.LENGTH_SHORT).show();
	    			ll_loading.setVisibility(View.GONE);
		        	ll_login.setVisibility(View.VISIBLE);
	    			break;
	    		case CONNECTION_ERROR:
	    			Toast.makeText(context, getString(R.string.toast_connetion_error), Toast.LENGTH_SHORT).show();
	    			ll_loading.setVisibility(View.GONE);
		        	ll_login.setVisibility(View.VISIBLE);
	    			break;
		        default: 
		        	Toast.makeText(context, result.name(), Toast.LENGTH_SHORT).show();
		        	ll_loading.setVisibility(View.GONE);
		        	ll_login.setVisibility(View.VISIBLE);
		        	break;
	        }
	        this.cancel(true);
	    }

	    @Override
	    protected void onPreExecute() {
	        ll_loading.setVisibility(View.VISIBLE);
	        ll_login.setVisibility(View.GONE);
	    }
	}
}
