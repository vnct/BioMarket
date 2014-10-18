package com.itii.biomarket;


import java.util.ArrayList;
import java.util.List;

import android.app.Activity;
import android.app.ListFragment;
import android.net.Uri;
import android.os.Bundle;
import android.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

/**
 * A simple {@link Fragment} subclass. Activities that contain this fragment
 * must implement the {@link BasketFragment.OnFragmentInteractionListener}
 * interface to handle interaction events. Use the
 * {@link ListStoreFragment#newInstance} factory method to create an instance of
 * this fragment.
 *
 */
public class ListStoreFragment extends Fragment {


	public ListStoreFragment() {
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View rootView = inflater.inflate(R.layout.fragment_list_store,
				container, false);


        List<String> toto = new ArrayList<String>();
        toto.add("List 1");
        toto.add("List 2");
        toto.add("List 3");
        ListView listViewstore = (ListView)rootView.findViewById(R.id.listViewStore);
        listViewstore.setAdapter(new StoreBaseAdapter(getActivity(),toto));
		return rootView;
	}
}
