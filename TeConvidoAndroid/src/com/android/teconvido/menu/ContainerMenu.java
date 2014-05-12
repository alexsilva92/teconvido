package com.android.teconvido.menu;

import java.util.HashMap;
import java.util.Map;

import com.android.teconvido.R;
import com.android.teconvido.activity.GpsExampleActivity;

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
		
		menus.put(KeyMenu.GPS, new ItemMenu("GPS",
				BitmapFactory.decodeResource(context.getResources(),R.drawable.gps_128x128),
				new OnClickListener(){
					public void onClick(View v) {
						Intent gps = new Intent(context, GpsExampleActivity.class);
						context.startActivity(gps); 
					}
				}
		));
		
		menus.put(KeyMenu.MY_TRAVELS, new ItemMenu("Mis Viajes",
				BitmapFactory.decodeResource(context.getResources(),R.drawable.my_travels_128x128),
				new OnClickListener(){
					public void onClick(View v) {
						Toast.makeText(context, "Sin Implementar", Toast.LENGTH_SHORT).show();
					}
				}
		));
		
		menus.put(KeyMenu.PROFILE, new ItemMenu("Perfil",
				BitmapFactory.decodeResource(context.getResources(),R.drawable.profile_128x128),
				new OnClickListener(){
					public void onClick(View v) {
						Toast.makeText(context, "Sin Implementar", Toast.LENGTH_SHORT).show();
					}
				}
		));
		
		menus.put(KeyMenu.SEARCH_TRAVELS, new ItemMenu("Buscar viajes",
				BitmapFactory.decodeResource(context.getResources(),R.drawable.travel_search_128x128),
				new OnClickListener(){
					public void onClick(View v) {
						Toast.makeText(context, "Sin Implementar", Toast.LENGTH_SHORT).show();
					}
				}
		));
		
		menus.put(KeyMenu.ADD_TRAVEL, new ItemMenu("Publicar viajes",
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
