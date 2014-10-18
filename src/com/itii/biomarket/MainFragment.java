package com.itii.biomarket;

import android.app.Activity;
import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.GridLayout;
import android.widget.MultiAutoCompleteTextView;

/**
 * A simple {@link Fragment} subclass. Activities that contain this fragment
 * must implement the {@link MainFragment.OnFragmentInteractionListener}
 * interface to handle interaction events. Use the
 * {@link MainFragment#newInstance} factory method to create an instance of this
 * fragment.
 *
 */
public class MainFragment extends Fragment {
	/**
	 * The fragment argument representing the section number for this
	 * fragment.
	 */
	private static final String ARG_SECTION_NUMBER = "section_number";

	/**
	 * Returns a new instance of this fragment for the given section number.
	 */
	public static  MainFragment newInstance(int sectionNumber) {
		MainFragment fragment = new MainFragment();
		Bundle args = new Bundle();
		args.putInt(ARG_SECTION_NUMBER, sectionNumber);
		fragment.setArguments(args);
		return fragment;
	}
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View rootView = inflater.inflate(R.layout.fragment_main, container,
				false);
		
		//GridLayout gl = (GridLayout) rootView.findViewById(R.id.GridLayout1);
		AutoCompleteTextView autoComplete = (AutoCompleteTextView)rootView.findViewById(R.id.autoCompleteTextView1);

		
		String[] autoCompletString = getResources().getStringArray(R.array.autoCompletion);
		
//		String autoCompletString[]={"Arun","Mathev","Vishnu","Vishal","Arjun",
//        		"Arul","Balaji","Babu","Boopathy","Godwin","Nagaraj"};
 
		ArrayAdapter<String> adp=new ArrayAdapter<String>(this.getActivity(),
        		android.R.layout.simple_dropdown_item_1line,autoCompletString);
 
		autoComplete.setAdapter(adp);
		 
		
		
		
		
		
		
		
		
		
		return rootView;
	}

	@Override
	public void onAttach(Activity activity) {
		super.onAttach(activity);
		((MainActivity) activity).onSectionAttached(getArguments().getInt(
				ARG_SECTION_NUMBER));
	}

}
