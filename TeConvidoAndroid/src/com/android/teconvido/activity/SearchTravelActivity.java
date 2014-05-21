package com.android.teconvido.activity;


import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import com.android.teconvido.R;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Toast;


public class SearchTravelActivity extends AbstractTopMenuActivity implements OnClickListener{
     
	private AutoCompleteTextView edtx_origin;
	private AutoCompleteTextView edtx_destiny;
	private EditText edtx_date; 
	
	private String origin;
	private String destiny;
	
	private List<String> towns;
	
	private int year;    
    private int month;    
    private int day;   
    
    private DialogFragment dateFragment;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
            
        setContentView(R.layout.activity_search_travel);
            
        towns = new ArrayList<String>();
        towns.add("Teruel");
        towns.add("Castralvo");
            
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
        		android.R.layout.simple_dropdown_item_1line, towns);
            
        edtx_date = (EditText) findViewById(R.id.edtx_date);
            
        edtx_date.setOnClickListener(new View.OnClickListener() {
        	public void onClick(View v) {
        		createDatePickerFragment();
        	}        
	    });
            
        edtx_date.setOnFocusChangeListener(new View.OnFocusChangeListener(){
        	@Override
        	public void onFocusChange(View v, boolean hasFocus) {
        		if(hasFocus){
        			createDatePickerFragment();
        		}	
			}	
        });

        final Calendar c = Calendar.getInstance();
        year = c.get(Calendar.YEAR);
        month = c.get(Calendar.MONTH);
        day = c.get(Calendar.DAY_OF_MONTH);

        edtx_origin = (AutoCompleteTextView) findViewById(R.id.edtx_origin);
        edtx_origin.setAdapter(adapter);

        edtx_destiny = (AutoCompleteTextView) findViewById(R.id.edtx_destiny);
        edtx_destiny.setAdapter(adapter);
            
        Button bt_search = (Button) findViewById(R.id.bt_search);
        bt_search.setOnClickListener((android.view.View.OnClickListener) this);
            
    }

	@Override
	public void onClick(View view) {
		if (view.getId()==findViewById(R.id.bt_search).getId()){
			origin = edtx_origin.getText().toString().trim();
			destiny = edtx_destiny.getText().toString().trim();

			if(!towns.contains(origin)){
				Toast.makeText(context, "origin", Toast.LENGTH_SHORT).show();
			} else if(!towns.contains(destiny)){
				Toast.makeText(context, "destiny", Toast.LENGTH_SHORT).show();
			} else {}
		}
	}
		
	private void createDatePickerFragment(){
		if(dateFragment == null){
			dateFragment = new DatePickerFragment();
			dateFragment.show(getFragmentManager(), "datePicker"); 
		}
	}
		
	
	private class DatePickerFragment extends DialogFragment implements
        DatePickerDialog.OnDateSetListener {

	    @Override
	    public Dialog onCreateDialog(Bundle savedInstanceState) {
	        return new DatePickerDialog(context, this, year, month, day);
	    }
	
	    public void onDateSet(DatePicker view, int _year, int _month, int _day) {
	    	year = _year;
	    	month = _month;
	    	day = _day;
	    	
	    	edtx_date.setText(new StringBuilder().append(_day).append("-")                                     
	    			.append(_month + 1).append("-").append(_year));   
	    	dateFragment = null;
	    }
	    
	    @Override
	    public void onDismiss (DialogInterface dialog){
	    	dateFragment = null;
	    }
	}
	
}
