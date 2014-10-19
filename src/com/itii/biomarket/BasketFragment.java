package com.itii.biomarket;



import java.util.ArrayList;
import java.util.List;

import android.app.Activity;
import android.net.Uri;
import android.os.Bundle;
import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

/**
 * A simple {@link Fragment} subclass. Activities that contain this fragment
 * must implement the {@link BasketFragment.OnFragmentInteractionListener}
 * interface to handle interaction events. Use the
 * {@link BasketFragment#newInstance} factory method to create an instance of
 * this fragment.
 *
 */
public class BasketFragment extends Fragment {

		/**
		 * The fragment argument representing the section number for this
		 * fragment.
		 */
		private static final String ARG_SECTION_NUMBER = "section_number";

		
		/**
		 * Returns a new instance of this fragment for the given section number.
		 */
		public static BasketFragment newInstance(int sectionNumber) {
			BasketFragment fragment = new BasketFragment();
			Bundle args = new Bundle();
			args.putInt(ARG_SECTION_NUMBER, sectionNumber);
			fragment.setArguments(args);
			return fragment;
		}

		public BasketFragment() {
		}

		
		 @Override
		    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
		        super.onCreateOptionsMenu(menu, inflater);
		        inflater.inflate(R.menu.main, menu);
		        MenuItem menu_basket_discard = menu.findItem(R.id.menu_basket_discard);
		        MenuItem menu_basket_location = menu.findItem(R.id.menu_basket_location);
		        MenuItem menu_basket_new = menu.findItem(R.id.menu_basket_new);
		        MenuItem menu_action_settings = menu.findItem(R.id.action_settings);
		        menu_basket_discard.setVisible(true);
		        menu_basket_location.setVisible(true);
		        menu_basket_new.setVisible(true);
		        menu_action_settings.setVisible(false);
		 }
		
		
		@Override
		public View onCreateView(LayoutInflater inflater, ViewGroup container,
				Bundle savedInstanceState) {
			setHasOptionsMenu(true);
			View rootView = inflater.inflate(R.layout.fragment_basket,
					container, false);
			
			
			 List<String> toto = new ArrayList<String>();
		        toto.add("basket 1");
		        toto.add("basket 2");
		        toto.add("basket 3");
		        //listViewstore = (ListView)findViewById(R.id.listViewStore);
		        ListView listViewbasket = (ListView)rootView.findViewById(R.id.listViewbasket);
		        listViewbasket.setAdapter(new BasketBaseAdapter(getActivity(),toto));
			
			return rootView;
		}
		
		@Override
		public boolean onOptionsItemSelected(MenuItem item) {


			
			 
		return super.onOptionsItemSelected(item);
		}
	
}
