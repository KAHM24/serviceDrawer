package com.example.serviciodrawer;

import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.os.IBinder;
import android.view.View;
import android.widget.TextView;

public class MiServicio extends Service implements LocationListener {
	
	private Context ctx;
	Double latitud;
	Double longitud;
	Location location;
	Boolean gpsActivo;
	TextView texto;
	LocationManager locationManager;
	
	public MiServicio (){
		super();
		this.ctx = this.getApplicationContext();
	}
	
	public MiServicio (Context c){
		super();
		this.ctx =c;
		getLocation();
	}
	
	public void setView (View v){
		texto = (TextView)v;
		texto.setText("Coordenadas: "+latitud+", "+longitud);
	}
	
	public void getLocation(){
		try {
			locationManager = (LocationManager)this.ctx.getSystemService(LOCATION_SERVICE);
			gpsActivo= locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER);
		} catch (Exception e) {
			// TODO: handle exception
		}
		if(gpsActivo){
			locationManager.requestLocationUpdates(locationManager.GPS_PROVIDER, 
				1000*60, 10, this);
			
			location= locationManager.getLastKnownLocation(locationManager.GPS_PROVIDER);
			latitud = location.getLatitude();
			longitud = location.getLongitude();
		}
	}
	
	@Override
	public IBinder onBind(Intent int1) {
		// TODO Auto-generated method stub
		
		return null;
	}
	@Override
	public void onLocationChanged(Location location) {
		// TODO Auto-generated method stub
		
		
	}
	@Override
	public void onStatusChanged(String provider, int status, Bundle extras) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void onProviderEnabled(String provider) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void onProviderDisabled(String provider) {
		// TODO Auto-generated method stub
		
	}

}
