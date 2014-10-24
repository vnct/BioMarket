package com.itii.biomarket;


import java.util.ArrayList;
import java.util.List;


import android.os.Bundle;
import android.app.Fragment;
import android.content.Intent;
import android.graphics.Color;
import android.util.Log;
import android.view.ActionMode;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
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
	private StoreBaseAdapter storeBaseAdapter = null;
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		setHasOptionsMenu(true);
		View rootView = inflater.inflate(R.layout.fragment_list_store,
				container, false);

	
	
		List<String> toto = new ArrayList<String>();
        toto.add("List 1");
        toto.add("List 2");
        toto.add("List 3");
        ListView listViewstore = (ListView)rootView.findViewById(R.id.listViewStore);
        storeBaseAdapter = new StoreBaseAdapter(getActivity(),toto);
        listViewstore.setAdapter(storeBaseAdapter);
        listViewstore.setChoiceMode(ListView.CHOICE_MODE_SINGLE);
    	listViewstore.setOnItemClickListener(OnItemClickListenerViewstore);
		return rootView;
	}
	ActionMode actionMode;
	  int position1=-1;
	  
	  @Override
	public void onResume() {
		// TODO Auto-generated method stub
		super.onResume();
		  if(actionMode!=null)
	        {
	            actionMode.finish();
	        }
	}
	 
	  @Override
	    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
		  inflater.inflate(R.menu.list_store, menu);
		 
		 // actionMode.
		  super.onCreateOptionsMenu(menu, inflater);

	  }
	private AdapterView.OnItemClickListener OnItemClickListenerViewstore = new AdapterView.OnItemClickListener() {
        @Override
        public void onItemClick(AdapterView<?> adapterView, View view, int position, long id) {
            for (int j = 0; j < adapterView.getChildCount(); j++)
                adapterView.getChildAt(j).setBackgroundColor(Color.TRANSPARENT);

            if(position1==position)
            {
            	System.out.println(actionMode.toString());
                actionMode.finish();
            }
            view.setBackgroundColor(Color.LTGRAY);
            changeContextual(view, position);
            position1=position;

        }
    
	public void changeContextual(View view, final int position) 
	{
		final View view1= view;
		if(getActivity()!=null)
		{
		actionMode = getActivity().startActionMode(new ActionMode.Callback() {

			@Override
			public boolean onCreateActionMode(ActionMode mode, Menu menu) {
				 try
	                {
	                    MenuInflater inflater = mode.getMenuInflater();
	                    inflater.inflate(R.menu.list_store_contextual, menu);
	                    //actionMode.finish();
	                    return true;
	                }
	                catch (NullPointerException e)
	                {
	                	Log.println(Log.ERROR, "Action Mode", e.toString());
	                    return false;
	                }
			}

			@Override
			public boolean onPrepareActionMode(ActionMode mode, Menu menu) {
				// TODO Auto-generated method stub
				return false;
			}

			@Override
			public boolean onActionItemClicked(ActionMode mode, MenuItem item) {
				  switch (item.getItemId()) {
                  case R.id.menu_store_maps:
                	  	Intent i;
                	  	String store = (String) storeBaseAdapter.getItem(position);
                	  	// TODO PASSER LES PARAMETRES
                	  	i = new Intent(getActivity().getApplicationContext(), MapsActivity.class);
	          	        startActivity(i);
	          	        getActivity().finish();
                	  return true;
                	  
				  }
				  return false;
			}

			@Override
			public void onDestroyActionMode(ActionMode mode) {
				   view1.setBackgroundColor(Color.TRANSPARENT);
				
			 }
        });
		
	}
	}
	};
	
}
