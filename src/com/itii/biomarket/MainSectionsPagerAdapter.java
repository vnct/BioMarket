package com.itii.biomarket;

import java.util.Locale;

import android.app.Fragment;
import android.app.FragmentManager;
import android.content.Context;
import android.support.v13.app.FragmentPagerAdapter;

/**
 * A simple {@link Fragment} subclass. Activities that contain this fragment
 * must implement the {@link BlankFragment.OnFragmentInteractionListener}
 * interface to handle interaction events. Use the
 * {@link BlankFragment#newInstance} factory method to create an instance of
 * this fragment.
 *
 */
public class MainSectionsPagerAdapter extends FragmentPagerAdapter {
	
	private Context mContext;
	
	public MainSectionsPagerAdapter(FragmentManager fm,Context context) {
		super(fm);
		 mContext = context;
		// TODO Auto-generated constructor stub
	}

	@Override
	public Fragment getItem(int position) {
		// getItem is called to instantiate the fragment for the given page.
		// Return a PlaceholderFragment (defined as a static inner class
		// below).
		System.out.println("getItem " +  position);
		switch (position) {
		case 0:
			return MainFragment.newInstance(position + 1);
		case 1:
			return BasketFragment.newInstance(position + 1);
		default:
			System.out.println(position);
			break;
		}
		return null;
		
			
		
	}

	@Override
	public int getCount() {
		// Show 3 total pages.
		return 2;
	}

	@Override
	public CharSequence getPageTitle(int position) {
		Locale l = Locale.getDefault();
		System.out.println("getPageTitle " +  position);
		switch (position) {
		case 0:
			return mContext.getResources().getString(R.string.title_home).toUpperCase(l);
		case 1:
			return mContext.getResources().getString(R.string.title_basket).toUpperCase(l);
		}
		return "toto";
	}

}
