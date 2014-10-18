package com.itii.biomarket;

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
import android.view.Menu;
import android.view.MenuItem;

public class MainActivity extends Activity implements
		NavigationDrawerFragment.NavigationDrawerCallbacks, ViewPager.OnPageChangeListener {

	/**
	 * Fragment managing the behaviors, interactions and presentation of the
	 * navigation drawer.
	 */
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
		Log.println(Log.INFO, "MainActivity", "onSectionAttached");
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
	        startActivity(i);
			break;
		case 3:
			i = new Intent(getApplicationContext(), ListStoreActivity.class);;
	        startActivity(i);
			break;
		case 4:
			mTitle = getString(R.string.title_basket);
			mViewPager.setCurrentItem(1);
			break;
		case 5:
			mTitle = getString(R.string.title_settings);
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
			getMenuInflater().inflate(R.menu.main, menu);
			restoreActionBar();
			return true;
		}
		return super.onCreateOptionsMenu(menu);
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		int id = item.getItemId();
		if (id == R.id.action_settings) {
			return true;
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
