package com.itii.biomarket;


import android.app.DialogFragment;

import android.content.Intent;

import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.widget.DrawerLayout;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.GoogleMap.OnMarkerClickListener;

import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;

import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

public class MapsActivity extends FragmentActivity implements
NavigationDrawerFragment.NavigationDrawerCallbacks{

    private GoogleMap mMap; // Might be null if Google Play services APK is not available.
	private NavigationDrawerFragment mNavigationDrawerFragment;
	private String parentName = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);
        try
		{
        	parentName = getIntent().getExtras().getString("PARENTNAME");
        	Log.println(Log.WARN, "PUTEXTRA", parentName);
		}
		catch(Exception e)
		{
			
		}
        
      //  mMap.setMyLocationEnabled(true);

        mNavigationDrawerFragment = (NavigationDrawerFragment) getFragmentManager()
				.findFragmentById(R.id.navigation_drawer);
		mNavigationDrawerFragment.setUp(R.id.navigation_drawer,
				(DrawerLayout) findViewById(R.id.drawer_layout));
        setUpMapIfNeeded();
    }

    @Override
    protected void onResume() {
        super.onResume();
        setUpMapIfNeeded();
    }
    @Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.maps, menu);
		return true;
	}

    /**
     * Sets up the map if it is possible to do so (i.e., the Google Play services APK is correctly
     * installed) and the map has not already been instantiated.. This will ensure that we only ever
     * call {@link #setUpMap()} once when {@link #mMap} is not null.
     * <p>
     * If it isn't installed {@link SupportMapFragment} (and
     * {@link com.google.android.gms.maps.MapView MapView}) will show a prompt for the user to
     * install/update the Google Play services APK on their device.
     * <p>
     * A user can return to this FragmentActivity after following the prompt and correctly
     * installing/updating/enabling the Google Play services. Since the FragmentActivity may not
     * have been completely destroyed during this process (it is likely that it would only be
     * stopped or paused), {@link #onCreate(Bundle)} may not be called again so we should call this
     * method in {@link #onResume()} to guarantee that it will be called.
     */
    
    private void setUpMapIfNeeded() {
        // Do a null check to confirm that we have not already instantiated the map.
        if (mMap == null) {
            // Try to obtain the map from the SupportMapFragment.
            mMap = ((SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.map))
                    .getMap();
            // Check if we were successful in obtaining the map.
            if (mMap != null) {
            	mMap.setMyLocationEnabled(true);
                setUpMap();
                mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(new LatLng(44, 5), 4.0f));
              
            }
        }
    }

    /**
     * This is where we can add markers or lines, add listeners or move the camera. In this case, we
     * just add a marker near Africa.
     * <p>
     * This should only be called once and when we are sure that {@link #mMap} is not null.
     */
    private void setUpMap() {
   	
    	// TODO RECUPERER LES STORES AVEC LEURS NOMS
        mMap.addMarker(new MarkerOptions().position(new LatLng(40, 0)).title("Marker"));
        mMap.setOnMarkerClickListener(new OnMarkerClickListener() {
			
			@Override
			public boolean onMarkerClick(Marker arg0) {
				
				try
				{
					DialogFragment newFragment = MapsAlertDialogFragment.newInstance(
				            R.string.alert_dialog_title,String.valueOf(arg0.getPosition().latitude),String.valueOf(arg0.getPosition().longitude));
				    newFragment.show(getFragmentManager(), "dialog");
					/*Intent i = new Intent(Intent.ACTION_VIEW, 
				     			Uri.parse("google.navigation:q=" + String.valueOf(arg0.getPosition().latitude)+","+String.valueOf(arg0.getPosition().longitude))); 
				     			startActivity(i);*/
				}
	             catch(Exception e)
	             {
	            	 
	             }
	     						
	               
				return false;
			
				
			}
			
		});
    }

	@Override
	public void onNavigationDrawerItemSelected(int position) {
		onSectionAttached(position+1);		
	}

	private void onSectionAttached(int number) {
		
		Log.println(Log.INFO, "MainActivity", "onSectionAttached");
		Intent i;
		switch (number) {
		case 1:

			if(parentName.equals("Home"))
			{
				Intent returnIntent = new Intent();
				returnIntent.putExtra("RESULT","0");
				setResult(RESULT_OK, returnIntent);
				finish();
			}
			else
			{
				i = new Intent(getApplicationContext(), MainActivity.class);
				i.putExtra("RESULT", "0");
				MainActivity.page=0;
				setResult(RESULT_OK, i);
				finish();
			}
			break;
		case 2:
			
		  
			break;
		case 3:
			i = new Intent(getApplicationContext(), ListStoreActivity.class);
	        startActivity(i);
	        finish();
			break;
		case 4:
			if(parentName.equals("Home"))
			{
				Intent returnIntent = new Intent();
				returnIntent.putExtra("RESULT","1");
				setResult(RESULT_OK, returnIntent);
				
				finish();
			}
			else
			{
				i = new Intent(getApplicationContext(), MainActivity.class);
				i.putExtra("RESULT", "1");
				MainActivity.page=1;
				setResult(RESULT_OK, i);
				//startActivity(i);
				finish();
			}
			break;
		case 5:
			//mTitle = getString(R.string.title_settings);
			i = new Intent(getApplicationContext(), SettingsActivity.class);
	        startActivity(i);
			break;
		}
		
	}
	
	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		int id = item.getItemId();
		 switch (id) {
		 case R.id.menu_maps_list:
			 Intent i;
			 i = new Intent(getApplicationContext(), ListStoreActivity.class);;
			 i.putExtra("BASKET", true);
			 i.putExtra("PARENTNAME","Maps");
			 startActivity(i);
		     finish();
			 break;
		 case R.id.action_settings:
			 i = new Intent(getApplicationContext(), SettingsActivity.class);
		     startActivity(i);
			 break;
		 }
		return super.onOptionsItemSelected(item);
	}



	
}