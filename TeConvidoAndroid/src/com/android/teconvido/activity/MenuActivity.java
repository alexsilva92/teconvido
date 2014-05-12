package com.android.teconvido.activity;

import java.util.ArrayList;
import java.util.List;

import com.android.teconvido.R;
import com.android.teconvido.menu.ContainerMenu;
import com.android.teconvido.menu.ContainerMenu.ItemMenu;
import com.android.teconvido.menu.ContainerMenu.KeyMenu;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.TextView;
import android.app.ActionBar;


public class MenuActivity extends AbstractTopMenuActivity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_menu);
            
        GridView gridview = (GridView) findViewById(R.id.gv_menu);
        gridview.setAdapter(new MenuAdapter());
            
        ActionBar actionBar = getActionBar();
        actionBar.setDisplayShowHomeEnabled(true);
	}
	
	@Override
	protected void onResume() {
		super.onResume();
	}
	
	public class MenuAdapter extends BaseAdapter {
	    
	    private List<ItemMenu> items;
	    private ContainerMenu containerMenu;

	    public MenuAdapter() {
	    	
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
}
