package com.itii.biomarket;


import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import android.app.DialogFragment;
import android.content.Context;
import android.content.Intent;
import android.content.Loader.ForceLoadContentObserver;
import android.content.SharedPreferences;
import android.location.Location;
import android.location.LocationManager;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v4.app.FragmentActivity;
import android.support.v4.widget.DrawerLayout;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.GooglePlayServicesClient;
import com.google.android.gms.location.LocationClient;
import com.google.android.gms.location.LocationListener;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.GoogleMap.OnMarkerClickListener;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.maps.GeoPoint;
import com.itii.biomarket.controler.BasketManagement;
import com.itii.biomarket.controler.StoreManagement;
import com.itii.biomarket.model.Article;
import com.itii.biomarket.model.Commercant;

public class MapsActivity extends FragmentActivity implements
NavigationDrawerFragment.NavigationDrawerCallbacks, GoogleMap.OnMyLocationChangeListener {

	private GoogleMap mMap; // Might be null if Google Play services APK is not available.
	private NavigationDrawerFragment mNavigationDrawerFragment;
	private String parentName = "";
	private Location location;
	private String storeName;
	private Float storeLongitude;
	private Float storeLatitude;
	private boolean basketSearch;
	private String articleSearch;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_maps);
		basketSearch = false;
		try
		{
			parentName = getIntent().getExtras().getString("PARENTNAME");
			if(parentName.equals("Store"))
			{
				storeName = getIntent().getExtras().getString("NAME");
				storeLongitude = getIntent().getExtras().getFloat("LONGITUDE");
				storeLatitude  = getIntent().getExtras().getFloat("LATITUDE");
			}
			if(parentName.equals("Home"))
			{
				basketSearch = getIntent().getExtras().getBoolean("BASKET");
				if(basketSearch==true)
				{

					articleSearch = getIntent().getExtras().getString("ARTICLE");
				}

			}
			if(parentName.equals("List"))
			{
				basketSearch = getIntent().getExtras().getBoolean("BASKET");
				articleSearch = "";
			}

			Log.println(Log.WARN, "PUTEXTRA", parentName);
		}
		catch(Exception e)
		{

		}

		//  mMap.setMyLocationEnabled(true);
		// Ajoute le navigation drawer
		mNavigationDrawerFragment = (NavigationDrawerFragment) getFragmentManager()
				.findFragmentById(R.id.navigation_drawer);
		mNavigationDrawerFragment.setUp(R.id.navigation_drawer,
				(DrawerLayout) findViewById(R.id.drawer_layout));


		LocationManager locationManager = (LocationManager) getApplication().getSystemService(Context.LOCATION_SERVICE);
		location = locationManager.getLastKnownLocation(LocationManager.GPS_PROVIDER);


	}
	@Override
	protected void onStart() {
		super.onStart();


	}
	@Override
	public void onStop() {
		
		super.onStop();
	};

	@Override
	protected void onResume() {
		super.onResume();
		setUpMapIfNeeded();
	}
	// Créer le menu
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
			mMap.setMyLocationEnabled(true);

			// Check if we were successful in obtaining the map.
			if (mMap != null) {
				mMap.setMyLocationEnabled(true);
				mMap.setOnMyLocationChangeListener(this);
				Log.d("mMap","mMap n'est pas nul");
				setUpMap();
				mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(new LatLng(43, 7), 6.0f));

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
		if(parentName.equals("List")||parentName.equals("Home"))
		{
			if(basketSearch==true)
			{
				StoreManagement storeManagement = new StoreManagement(getApplicationContext());
				//	BasketManagement basketManagement = new BasketManagement(getApplicationContext());

				List<Article> articles = new ArrayList<Article>();

				if(articleSearch.length()>0)
				{
					//System.out.println(articleSearch);
					try {
						articles = MainActivity.basketManagement.getArticle(articleSearch);
					} catch (SQLException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}


				}
				else
				{

					articles = MainActivity.basketManagement.getBasket();

				}

				if((articles!=null)&&(articles.size()>0))
				{
					List<Commercant> commercants = storeManagement.findAllCommercants(articles);
					for(Commercant commercant : commercants)
					{
						//System.out.println(commercant.getNom() + " --> " + commercant.getLatitude_dg() + " --> " + commercant.getLongitude_dg());
						Float latitude = commercant.getLatitude_dg();
						Float longitude = commercant.getLongitude_dg();
						mMap.addMarker(new MarkerOptions().position(new LatLng(latitude, longitude)).title(commercant.getNom()));
					};


				}
				else
				{
					List<Commercant> commercants = storeManagement.getAllCommercant();
					for(Commercant commercant : commercants)
					{
						//System.out.println(commercant.getNom() + " --> " + commercant.getLatitude_dg() + " --> " + commercant.getLongitude_dg());
						Float latitude = commercant.getLatitude_dg();
						Float longitude = commercant.getLongitude_dg();
						mMap.addMarker(new MarkerOptions().position(new LatLng(latitude, longitude)).title(commercant.getNom()));
					};
				}



			}
			else
			{
				StoreManagement storeManagement = new StoreManagement(getApplicationContext());
				SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(this);
				Integer distance_max = prefs.getInt("seekBar", 50);
				List<Commercant> list = new ArrayList<Commercant>();
				try{
					list = storeManagement.getMagasinsDansPerimetre((float)location.getLatitude(), (float)location.getLongitude(),(float)(distance_max*100.0));

				}
				catch(Exception e)
				{
					list = storeManagement.getAllCommercant();
					//Log.d("MAPS","getMagasinsDansPerimetre fail");
				}
				if(list!=null)
				{
					for(Commercant commercant : list)
					{
						//System.out.println(commercant.getNom() + " --> " + commercant.getLatitude_dg() + " --> " + commercant.getLongitude_dg());
						Float latitude = commercant.getLatitude_dg();
						Float longitude = commercant.getLongitude_dg();

						mMap.addMarker(new MarkerOptions().position(new LatLng(latitude, longitude)).title(commercant.getNom()));
					};
				}

			}

		}
		if(parentName.equals("Store"))
		{
			mMap.addMarker(new MarkerOptions().position(new LatLng(storeLatitude, storeLongitude)).title(storeName));
		}

		//      mMap.addMarker(new MarkerOptions().position(new LatLng(43.6, 7.1)).title("toto"));

		mMap.setOnMarkerClickListener(new OnMarkerClickListener() {

			// En cas de clic sur un market
			@Override
			public boolean onMarkerClick(Marker arg0) {

				try
				{ 
					// on ouvre une texte box
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


	// Permet de créer les actions en fonction des clics que le navigation drawer
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

	// Permet de créer les actions en fonction des clics que l'action bar
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



	@Override
	public void onMyLocationChange(Location arg0) {
		location = arg0;
	}






}