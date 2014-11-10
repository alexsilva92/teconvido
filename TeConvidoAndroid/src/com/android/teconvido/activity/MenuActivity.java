package com.android.teconvido.activity;

import java.util.ArrayList;
import java.util.List;

import com.android.teconvido.R;
import com.android.teconvido.menu.ContainerMenu;
import com.android.teconvido.menu.ContainerMenu.ItemMenu;
import com.android.teconvido.menu.ContainerMenu.KeyMenu;

import android.os.Bundle;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.View.OnClickListener;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;


public class MenuActivity extends AbstractTopMenuActivity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setIsMenu(true);

        setContentView(R.layout.activity_menu);
            
        GridView gridview = (GridView) findViewById(R.id.gv_menu);
        gridview.setAdapter(new MenuAdapter());
        
        LinearLayout ll_nextTravel = (LinearLayout) findViewById(R.id.ll_nextTravel);
        ll_nextTravel.setOnClickListener(
        	new OnClickListener(){
        		public void onClick(View v) {
        			Intent currentTravel = new Intent(context,CurrentTravelActivity.class);
        			currentTravel.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | 
	    					   Intent.FLAG_ACTIVITY_SINGLE_TOP);
					context.startActivity(currentTravel);
				}
		});
            
        //ActionBar actionBar = getActionBar();
        //actionBar.setDisplayShowHomeEnabled(true);
	}
	
	@Override
	protected void onResume() {
		super.onResume();
	}
	
	public class MenuAdapter extends BaseAdapter {
	    
	    private List<ItemMenu> items;
	    private ContainerMenu containerMenu;

	    public MenuAdapter() {
	    	super();
	        containerMenu = new ContainerMenu(context);
	        items = new ArrayList<ItemMenu>();
	        items.add(containerMenu.getItemMenu(KeyMenu.SEARCH_TRAVELS));
	        items.add(containerMenu.getItemMenu(KeyMenu.ADD_TRAVEL));
	        items.add(containerMenu.getItemMenu(KeyMenu.MY_TRAVELS));
	        items.add(containerMenu.getItemMenu(KeyMenu.PROFILE));
	    }

	    public int getCount() {
	        return items.size();
	    }

	    public View getView(int position, View convertView, ViewGroup parent) {

	        LayoutInflater inflater = context.getLayoutInflater();
	        View item = inflater.inflate(R.layout.item_menu, null);

			ImageView imgv_image = (ImageView) item.findViewById(R.id.imgv_image);
			TextView txtv_name = (TextView) item.findViewById(R.id.txtv_name);
			
	        imgv_image.setImageBitmap(items.get(position).getImage());
	        
	        txtv_name.setText(items.get(position).getName());
	        
	        item.setOnClickListener(items.get(position).getClickListener());
	        
	        return item;
	    }

		@Override
		public Object getItem(int position) {
			return items.get(position);
		}

		@Override
		public long getItemId(int position) {
			return position;
		}
	}
	
	@Override
	protected void pushButtonHome(){}
	
	@Override
	protected boolean isEnableButtonHome(){
	    return false;
	}
	
	@Override
	public boolean onKeyDown(int keyCode, KeyEvent event){
		if ((keyCode == KeyEvent.KEYCODE_BACK)){	
			AlertDialog.Builder sessionDialog = new AlertDialog.Builder(this);  
			sessionDialog.setTitle(getString(R.string.session_close_title));  
	        sessionDialog.setMessage(getString(R.string.session_close_message));            
	        sessionDialog.setCancelable(true);  
	        sessionDialog.setPositiveButton(getString(R.string.confirm), new DialogInterface.OnClickListener() {  
	            public void onClick(DialogInterface dialog, int id) {  
	                finish();
	            }  
	        });  
	        sessionDialog.setNegativeButton(getString(R.string.cancel), new DialogInterface.OnClickListener() {  
	            public void onClick(DialogInterface dialog, int id) {  }  
	        });            
	        sessionDialog.show();
			return true;
		}

		return super.onKeyDown(keyCode, event);
	}
}
