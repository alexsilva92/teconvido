package com.android.utilities.gps;

import android.content.Context;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.util.Log;


public class ServiceGps implements LocationListener{

	private static final long MIN_DISTANCE_CHANGE_FOR_UPDATES = 10; // 10 meters
	private static final long MIN_TIME_BW_UPDATES = 1000 * 60 * 1; // 1 minute

	private Location location;
	
	private LocationManager locationManager;
	private long distanceUpdate;
	private long timeUpdate;
	
	private boolean isGPSEnabled;
	private boolean isNetworkEnabled;
	
	public ServiceGps(Context context,long _distanceUpdate,long _timeUpdate){
		locationManager = (LocationManager) context.getSystemService(
				Context.LOCATION_SERVICE);
		this.distanceUpdate = _distanceUpdate;
		this.timeUpdate = _timeUpdate;
		
		isGPSEnabled = locationManager.isProviderEnabled(
				LocationManager.GPS_PROVIDER);

		isNetworkEnabled = locationManager.isProviderEnabled(
				LocationManager.NETWORK_PROVIDER);
	}
	
	public ServiceGps(Context context){
		this(context,MIN_DISTANCE_CHANGE_FOR_UPDATES,MIN_TIME_BW_UPDATES);
	}


	@Override
	public void onLocationChanged(Location location) {
		this.location = location;
	}


	@Override
	public void onProviderDisabled(String provider) {
		if(LocationManager.GPS_PROVIDER.equals(provider)){
			isGPSEnabled = false;
		}else{
			isNetworkEnabled = false;
		}
	}


	@Override
	public void onProviderEnabled(String provider) {
		locationManager.requestLocationUpdates(provider,timeUpdate,
				distanceUpdate, this);
		if(locationManager.getLastKnownLocation(provider) != null)
			location = locationManager.getLastKnownLocation(provider);
		if(LocationManager.GPS_PROVIDER.equals(provider)){
			isGPSEnabled = true;
		}else{
			isNetworkEnabled = true;
		}
	}


	@Override
	public void onStatusChanged(String provider, int status, Bundle extras) {
		
	}
	
	public double getLatitude(){
		if(location != null){
			return location.getLatitude();
		} else{
			return 0.0;
		}
	}
	
	public double getLongitude(){
		if(location != null){
			return location.getLongitude();
		} else{
			return 0.0;
		}
	}
	
	public boolean IsGpsEnabled(){
		return isGPSEnabled;
	}
	
	public boolean IsNetworkEnabled(){
		return isNetworkEnabled;
	}
	
	public void start(){		
		if(isNetworkEnabled){
			Log.d("GPS", "NETWORK ENABLED");
			locationManager.requestLocationUpdates(LocationManager.NETWORK_PROVIDER,
					timeUpdate,distanceUpdate, this);
			location = locationManager.getLastKnownLocation(
					LocationManager.NETWORK_PROVIDER);
		}
			
		if(isGPSEnabled){
			Log.d("GPS", "GPS ENABLED");
			locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER,
					timeUpdate,distanceUpdate, this);
			location = locationManager.getLastKnownLocation(
					LocationManager.GPS_PROVIDER);
		}
	}
	
	public void stop(){
		locationManager.removeUpdates(this);
	}
}
