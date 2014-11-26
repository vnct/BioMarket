package com.itii.biomarket;


import com.itii.biomarket.controler.BasketManagement;

import android.app.ActionBar;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.support.v13.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;

public class MainActivity extends Activity implements
		NavigationDrawerFragment.NavigationDrawerCallbacks, ViewPager.OnPageChangeListener {

	/**
	 * Fragment managing the behaviors, interactions and presentation of the
	 * navigation drawer.
	 */
	public static Integer page=0;
	private NavigationDrawerFragment mNavigationDrawerFragment;
	/**
	 * The {@link android.support.v4.view.PagerAdapter} that will provide
	 * fragments for each of the sections. We use a {@link FragmentPagerAdapter}
	 * derivative, which will keep every loaded fragment in memory. If this
	 * becomes too memory intensive, it may be best to switch to a
	 * {@link android.support.v13.app.FragmentStatePagerAdapter}.
	 */
	private MainSectionsPagerAdapter mSectionsPagerAdapter;
	
	/**
	 * The {@link ViewPager} that will host the section contents.
	 */
	private ViewPager mViewPager;

	/**
	 * Used to store the last screen title. For use in
	 * {@link #restoreActionBar()}.
	 */
	private CharSequence mTitle;
	private int itemCurrent;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		 mTitle = getTitle();
		
		mNavigationDrawerFragment = (NavigationDrawerFragment) getFragmentManager()
				.findFragmentById(R.id.navigation_drawer);
		

		// Set up the drawer.
		mNavigationDrawerFragment.setUp(R.id.navigation_drawer,
				(DrawerLayout) findViewById(R.id.drawer_layout));
		
		mSectionsPagerAdapter = new MainSectionsPagerAdapter(getFragmentManager(),getApplicationContext());

		// Set up the ViewPager with the sections adapter.
		mViewPager = (ViewPager) findViewById(R.id.main_pager);
		mViewPager.setAdapter(mSectionsPagerAdapter);

		mViewPager.setOnPageChangeListener(this);
		
		
		 
		
		Log.println(Log.INFO, "MainActivity", "onCreate");

		
		
				

	}
	
	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		try
		{
			Log.println(Log.WARN, "Main Acitivty - onActivityResult", data.getStringExtra("RESULT"));
			mViewPager.setCurrentItem(Integer.parseInt(data.getStringExtra("RESULT")));
		}
		catch(Exception e)
		{
			mViewPager.setCurrentItem(page);
		}
	//	
		
	};
	@Override
	protected void onResume() {
		super.onResume();
	};
	

	@Override
	public void onNavigationDrawerItemSelected(int position) {
		System.out.println("My position " + position);
		onSectionAttached(position+1);
		// update the main content by replacing fragments
		/*FragmentManager fragmentManager = getFragmentManager();
		fragmentManager
				.beginTransaction()
				.replace(R.id.container,
						MainFragment.newInstance(position + 1)).commit();*/
	}

	public void onSectionAttached(int number) {
		Log.println(Log.INFO, "MainActivity", "onSectionAttached " + number);
		String parentname="Home";
		Intent i;
		switch (number) {
		case 1:

			try{
				mViewPager.setCurrentItem(0);
				Log.println(Log.INFO, "MainActivity", "onSectionAttached - case 1");
			}
			catch(Exception e)
			{
				Log.println(Log.ERROR, "MainActivity", "onSectionAttached - case 1");
			}
			mTitle = getString(R.string.title_home);
			
			break;
		case 2:
			
		    i = new Intent(getApplicationContext(), MapsActivity.class);
		    i.putExtra("PARENTNAME",parentname);
		    i.putExtra("BASKET", false);
	        startActivityForResult(i, 1);
	    
	        
			break;
		case 3:
			i = new Intent(getApplicationContext(), ListStoreActivity.class);;
			i.putExtra("PARENTNAME",parentname);
			i.putExtra("BASKET", false);
	        startActivityForResult(i, 1);
			break;
		case 4:
			mTitle = getString(R.string.title_basket);
			mViewPager.setCurrentItem(1);
			break;
		case 5:
			//mTitle = getString(R.string.title_settings);
			i = new Intent(getApplicationContext(), SettingsActivity.class);
	        startActivity(i);
			break;
		}
	}

	public void restoreActionBar() {
		ActionBar actionBar = getActionBar();
		actionBar.setNavigationMode(ActionBar.NAVIGATION_MODE_STANDARD);
		actionBar.setDisplayShowTitleEnabled(true);
		actionBar.setTitle(mTitle);
	}

	
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		
	
	    
		if (!mNavigationDrawerFragment.isDrawerOpen()) {
			// Only show items in the action bar relevant to this screen
			// if the drawer is not showing. Otherwise, let the drawer
			// decide what to show in the action bar.
			//getMenuInflater().inflate(R.menu.main, menu);
			restoreActionBar();
			return true;
		}
		//return super.onCreateOptionsMenu(menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		 int id = item.getItemId();
		 switch (id) {
		case R.id.menu_basket_new:
			mViewPager.setCurrentItem(0);
			break;
		case R.id.menu_basket_location:
			Intent i;
			i = new Intent(getApplicationContext(), MapsActivity.class);
		    i.putExtra("PARENTNAME","Home");
		    i.putExtra("BASKET", true); // on spécifie qu'on va chercher le contenu du basket
		    i.putExtra("ARTICLE", "");
	        startActivityForResult(i, 1);		
			break;
		case R.id.menu_basket_discard:
			BasketManagement basketManagement = new BasketManagement(getApplicationContext());
			basketManagement.removeBasket();
			mViewPager.setCurrentItem(0);
			break;
		case R.id.action_settings:
			i = new Intent(getApplicationContext(), SettingsActivity.class);
	        startActivity(i);
			break;
		default:
			break;
		}
		
		 
		 
		return super.onOptionsItemSelected(item);
	}

	@Override
	public void onPageScrollStateChanged(int arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onPageScrolled(int arg0, float arg1, int arg2) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onPageSelected(int arg0) {
		mViewPager = (ViewPager) findViewById(R.id.main_pager);
		mTitle = mViewPager.getAdapter().getPageTitle(arg0);
		Log.println(Log.INFO, "MainActivity", "onPageSelected " + mTitle);
		
		
	}

	

	

}
