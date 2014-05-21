package com.android.teconvido.activity;

import com.android.teconvido.R;

import android.os.Bundle;
import android.view.MenuItem;
import android.app.ActionBar;
import android.app.Activity;


public class RecoverPasswordActivity extends Activity {
    

	@Override
	protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);

            setContentView(R.layout.activity_test);
            
    		ActionBar actionBar = getActionBar();
    	    actionBar.setDisplayHomeAsUpEnabled(true);
	}
	
	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
	    switch (item.getItemId()) {
	    	case android.R.id.home:
	    		finish();
	    		return true;

			default:
				 return super.onOptionsItemSelected(item);				
	    }
	}
}
