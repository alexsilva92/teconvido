package com.android.teconvido.preferences;

import android.content.Context;
import android.content.SharedPreferences;

public class ApplicationPreferences{
		
	private final String SHARED_PREFS_FILE = "preferences";
	private final String KEY_EMAIL = "email";
	private final String KEY_PASSWORD = "password";
	private final String KEY_REMEMBER = "remember";
	private final String KEY_GCM_ID = "gcm_id";
	
	private Context context;
	
	public ApplicationPreferences(Context context){
		this.context = context;
	}
	
	private SharedPreferences getSettings(){
	      return context.getSharedPreferences(SHARED_PREFS_FILE, Context.MODE_PRIVATE);
	}
	
	public String getEmail(){
		return getSettings().getString(KEY_EMAIL, "");
	}
	
	public String getGcmId(){
		return getSettings().getString(KEY_GCM_ID, "");
	}
	
	public String getPassword(){
		return getSettings().getString(KEY_PASSWORD, "");
	}
	
	public boolean isRememeber(){
		return getSettings().getBoolean(KEY_REMEMBER, true);
	}
	
	public void setEmail(String email){
		SharedPreferences.Editor editor = getSettings().edit();    
	    editor.putString(KEY_EMAIL, email);
	    editor.commit();
	}
	
	public void setPassword(String password){
		SharedPreferences.Editor editor = getSettings().edit();    
	    editor.putString(KEY_PASSWORD, password);
	    editor.commit();
	}
	
	public void setRemember(boolean recordar){
		SharedPreferences.Editor editor = getSettings().edit();    
	    editor.putBoolean(KEY_REMEMBER, recordar);
	    editor.commit();
	}
	
	public void setGcmId(String gcmId){
		SharedPreferences.Editor editor = getSettings().edit();    
	    editor.putString(KEY_GCM_ID, gcmId);
	    editor.commit();
	}
}	
