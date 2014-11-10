package com.android.teconvido.activity;

import java.util.ArrayList;
import java.util.List;

import com.android.teconvido.R;
import com.teconvido.bd.modelo.Message;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.View.OnClickListener;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.app.NotificationManager;
import android.content.Context;
import android.content.Intent;


public class MessageActivity extends AbstractTopMenuActivity {
    
	private ListView lv_messages;
    private List<Message> messages;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            
            NotificationManager mNotificationManager =    
    				(NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE); 
            mNotificationManager.cancelAll();
            
            setContentView(R.layout.activity_messages);
            
            lv_messages = (ListView) findViewById(R.id.lv_messages);  
            
			messages = new ArrayList<Message>();
			Message message = new Message();
			messages.add(message);
			
			message = new Message();
			messages.add(message);
			
			message = new Message();
			messages.add(message);
			
			message = new Message();
			messages.add(message);
			
			message = new Message();
			messages.add(message);
			
			message = new Message();
			messages.add(message);

			MessageAdapter adapter = new MessageAdapter();
			lv_messages.setAdapter(adapter);
	}
	
	
	private class MessageAdapter extends ArrayAdapter<Message> {
		
		public MessageAdapter() {
			super(context, R.layout.item_message, messages);
		}

		@Override
		public View getView(final int position, View convertView, ViewGroup parent) {				
			LayoutInflater inflater = context.getLayoutInflater();
			View item = inflater.inflate(R.layout.item_message, null);
			
			ImageView imgv_message = (ImageView) item.findViewById(R.id.imgv_message);
			LinearLayout ll_containerMessages = (LinearLayout) 
					item.findViewById(R.id.ll_containerMessages);
			TextView txtv_subject = (TextView) item.findViewById(R.id.txtv_subject);
			TextView txtv_user = (TextView) item.findViewById(R.id.txtv_user);
			TextView txtv_date = (TextView) item.findViewById(R.id.txtv_date);
			
			if(position > 3){
				imgv_message.setImageResource(R.drawable.message_already_read_80x80);
			}else {
				imgv_message.setImageResource(R.drawable.new_message_80x80);
				ll_containerMessages.setBackgroundResource(R.drawable.btn_light_blue);
			}
			
			item.setOnClickListener(new OnClickListener(){

				@Override
				public void onClick(View v) {
					Intent getMessage = new Intent(context,GetMessageActivity.class);
					getMessage.putExtra("idMessage", position);
					startActivity(getMessage);
				}
				
			});
			
			return item;
		}

	}
}
