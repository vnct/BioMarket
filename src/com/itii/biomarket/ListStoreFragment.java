package com.itii.biomarket;

import android.os.Bundle;
import android.app.Fragment;
import android.app.ListFragment;
import android.database.DataSetObserver;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListAdapter;
import android.widget.ListView;

/**
 * A simple {@link Fragment} subclass. Activities that contain this fragment
 * must implement the {@link ListStoreFragment.OnFragmentInteractionListener}
 * interface to handle interaction events. Use the
 * {@link ListStoreFragment#newInstance} factory method to create an instance of
 * this fragment.
 *
 */
public class ListStoreFragment extends Fragment {
	/**
	 * The fragment argument representing the section number for this
	 * fragment.
	 */
	private static final String ARG_SECTION_NUMBER = "section_number";

	/**
	 * Returns a new instance of this fragment for the given section number.
	 */
	public static ListStoreFragment newInstance(int sectionNumber) {
		ListStoreFragment fragment = new ListStoreFragment();
		Bundle args = new Bundle();
		args.putInt(ARG_SECTION_NUMBER, sectionNumber);
		fragment.setArguments(args);
		return fragment;
	}

	public ListStoreFragment() {
	}

	
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View rootView = inflater.inflate(R.layout.fragment_list_store, container,
				false);
	//	ListView listView = (ListView) rootView.findViewById(R.id.listView1);
	//	String[] listeStrings = {"France","Allemagne","Russie"};
		
	

		return rootView;
	}

}
