package com.android.teconvido.menu;

import java.util.HashMap;
import java.util.Map;

import com.android.teconvido.R;
import com.android.teconvido.activity.SearchTravelActivity;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Toast;

public class ContainerMenu {
	
	private Activity context;
	
	private Map<KeyMenu,ItemMenu> menus;
	
	public enum KeyMenu{GPS,PROFILE,MY_TRAVELS,SEARCH_TRAVELS, ADD_TRAVEL,
		};
	
	public class ItemMenu {

		private OnClickListener clickListener;
		private String name;
		private Bitmap image;
		
		public ItemMenu(String name, Bitmap image,
				OnClickListener clickListener){
			this.name = name;
			this.image = image;
			this.clickListener = clickListener;
		}
		
		public OnClickListener getClickListener(){
			return clickListener;
		}
		
		public String getName(){
			return name;
		}
		
		public Bitmap getImage(){
			return image;
		}
	}
	
	public ContainerMenu(Activity _context){
		context = _context;
		
		menus = new HashMap<KeyMenu,ItemMenu>();
		menus.put(KeyMenu.MY_TRAVELS, new ItemMenu(
				context.getString(R.string.my_travels_title),
				BitmapFactory.decodeResource(context.getResources(),R.drawable.my_travels_128x128),
				new OnClickListener(){
					public void onClick(View v) {
						Toast.makeText(context, "Sin Implementar", Toast.LENGTH_SHORT).show();
					}
				}
		));
		
		menus.put(KeyMenu.PROFILE, new ItemMenu(
				context.getString(R.string.profile_title),
				BitmapFactory.decodeResource(context.getResources(),R.drawable.profile_128x128),
				new OnClickListener(){
					public void onClick(View v) {
						Toast.makeText(context, "Sin Implementar", Toast.LENGTH_SHORT).show();
					}
				}
		));
		
		menus.put(KeyMenu.SEARCH_TRAVELS, new ItemMenu(
				context.getString(R.string.search_travel_title),
				BitmapFactory.decodeResource(context.getResources(),R.drawable.travel_search_128x128),
				new OnClickListener(){
					public void onClick(View v) {
						Intent searchTravel = new Intent(context,SearchTravelActivity.class);
						searchTravel.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | 
		    					   Intent.FLAG_ACTIVITY_SINGLE_TOP);
						context.startActivity(searchTravel);
					}
				}
		));
		
		menus.put(KeyMenu.ADD_TRAVEL, new ItemMenu(
				context.getString(R.string.publish_travel_title),
				BitmapFactory.decodeResource(context.getResources(),R.drawable.add_travel_128),
				new OnClickListener(){
					public void onClick(View v) {
						Toast.makeText(context, "Sin Implementar", Toast.LENGTH_SHORT).show();
					}
				}
		));
	}
	
	public ItemMenu getItemMenu(KeyMenu key){
		return menus.get(key);
	}
}
