package com.example.whereami;

import java.io.IOException;
import java.util.List;

import android.location.Address;
import android.location.Criteria;
import android.location.Geocoder;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.app.Activity;
import android.content.Context;
import android.view.Menu;
import android.view.View;
import android.view.WindowManager;

import com.google.android.glass.app.Card;

public class MainActivity extends Activity implements LocationListener{
private LocationManager locationManager;
private Card card;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		//setContentView(R.layout.activity_main);
		card = new Card(this.getApplicationContext());
        card.setText("Getting your location...");
        View cardView = card.toView();
        setContentView(cardView);

        locationManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);

        Criteria criteria = new Criteria();
        criteria.setAccuracy(Criteria.ACCURACY_FINE);
        criteria.setAltitudeRequired(true);

        List<String> providers = locationManager.getProviders(
                criteria, true /* enabledOnly */);

        for (String provider : providers) {
            locationManager.requestLocationUpdates(provider, 1000,
                    10, this);
        }
        
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);

	
	
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}
	
	
	@Override
    public void onLocationChanged(Location arg0) {
		//show lat long to user
        String lat = String.valueOf(arg0.getLatitude());
        String lon = String.valueOf(arg0.getLongitude());
        
        card.setText("your location is " + lat + " " + lon);

        
        Geocoder geocoder = new Geocoder(this);
        
        try {
			List<Address> addresses = geocoder.getFromLocation(arg0.getLatitude(), arg0.getLongitude(), 1);
			if(addresses.size() > 0)
			{
				Address address = addresses.get(0);
				String addressString = "";
				
				for(int i = 0; i < address.getMaxAddressLineIndex(); i++)
				{
					addressString += address.getAddressLine(i) + " ";
				}
			card.setFootnote(addressString);
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        
        View cardView = card.toView();
        setContentView(cardView);
	}
	
	
	
	@Override
	public void onProviderDisabled(String arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onProviderEnabled(String arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onStatusChanged(String arg0, int arg1, Bundle arg2) {
		// TODO Auto-generated method stub
		
	}
	
}


