package com.itii.biomarket;



import com.itii.biomarket.controler.BasketManagement;

import android.app.Activity;
import android.content.Intent;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.support.v4.widget.DrawerLayout;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;


public class ListStoreActivity extends Activity  implements NavigationDrawerFragment.NavigationDrawerCallbacks{


	private NavigationDrawerFragment mNavigationDrawerFragment;
	private String parentName;


	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_list_store);
		try
		{
			parentName = getIntent().getExtras().getString("PARENTNAME");
		}
		catch(Exception e)
		{

		}
		// Ajout du navigation drawer
		mNavigationDrawerFragment = (NavigationDrawerFragment) getFragmentManager()
				.findFragmentById(R.id.navigation_drawer);
		mNavigationDrawerFragment.setUp(R.id.navigation_drawer,
				(DrawerLayout) findViewById(R.id.drawer_layout));


		if (savedInstanceState == null) {
			getFragmentManager().beginTransaction()
			.add(R.id.container, new ListStoreFragment()).commit();
		}
	}


	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		return true;
	}

	// Actions sur l'action bar
	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		int id = item.getItemId();
		switch (id) {
		case R.id.menu_list_maps:
			Intent i;
			i = new Intent(getApplicationContext(), MapsActivity.class);;
			i.putExtra("BASKET", true);
			i.putExtra("PARENTNAME","List");
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
	public void onNavigationDrawerItemSelected(int position) {
		onSectionAttached(position+1);

	}

	// actions relatives  au navigation drawer
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
			i = new Intent(getApplicationContext(), MapsActivity.class);
			startActivity(i);
			finish();

			break;
		case 3:

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
	public void onResume() {
		// TODO Auto-generated method stub
		super.onResume();
		
		System.out.println("onResume");
		if(MainActivity.basketManagement==null)
		{
			MainActivity.basketManagement = new BasketManagement(getApplicationContext());
		}


	}





}
