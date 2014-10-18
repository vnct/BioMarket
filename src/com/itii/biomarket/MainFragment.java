package com.itii.biomarket;

import java.util.List;

import android.app.Activity;
import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;

import com.itii.biomarket.model.Article;
import com.itii.biomarket.model.StoreManagement;

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

		
//		String[] autoCompletString = getResources().getStringArray(R.array.autoCompletion);
		
//		String autoCompletString[]={"Arun","Mathev","Vishnu","Vishal","Arjun",
//        		"Arul","Balaji","Babu","Boopathy","Godwin","Nagaraj"};
 
		
		
		
		StoreManagement DAOS = new StoreManagement(this.getActivity());
        DAOS.open();

        List<Article> ArticleGet = DAOS.getArticles();
        
        String[] autoCompletString = new String[ArticleGet.size()];
        
        if(ArticleGet != null) {
        	int i = 0;
        	
			for(Article m : ArticleGet)
			{
				autoCompletString[i] = m.getNom();
				i++;
			}
        }

		ArrayAdapter<String> adp=new ArrayAdapter<String>(this.getActivity(),
        		android.R.layout.simple_dropdown_item_1line,autoCompletString);
 
		autoComplete.setAdapter(adp);
		
		return rootView;
	}

	@Override
	public void onAttach(Activity activity) {
		super.onAttach(activity);
		System.out.println("clic");
		((MainActivity) activity).onSectionAttached(getArguments().getInt(
				ARG_SECTION_NUMBER));
	}

}
