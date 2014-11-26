package com.itii.biomarket;


import java.util.ArrayList;
import java.util.List;


















import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.LocationSource;
import com.google.android.gms.maps.SupportMapFragment;
import com.itii.biomarket.controler.BasketManagement;
import com.itii.biomarket.controler.StoreManagement;
import com.itii.biomarket.model.Article;
import com.itii.biomarket.model.Commercant;

import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.app.Fragment;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
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
public class ListStoreFragment extends Fragment implements LocationListener	{

	
	private List<Commercant> listCommercant;
	public ListStoreFragment() {
	}
	private StoreBaseAdapter storeBaseAdapter = null;
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		setHasOptionsMenu(true);
		View rootView = inflater.inflate(R.layout.fragment_list_store,
				container, false);
		
		LocationManager locationManager = (LocationManager) getActivity().getSystemService(Context.LOCATION_SERVICE);
		Location location = locationManager.getLastKnownLocation(LocationManager.GPS_PROVIDER);
		locationManager.requestLocationUpdates(LocationManager.NETWORK_PROVIDER, 0, 0, this);
		StoreManagement storeManagement = new StoreManagement(getActivity());
		BasketManagement basketManagement = new BasketManagement(getActivity());
		
		List<Article> articles = basketManagement.getBasket();
		if(articles!=null)
		{
			listCommercant = storeManagement.orderbyPertinence(articles);
		}
		else
		{
			SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(getActivity());
	    	Integer distance_max = prefs.getInt("seekBar", 50);
	    	listCommercant= storeManagement.getMagasinsDansPerimetre((float)location.getLatitude(), (float)location.getLongitude(),(float)(distance_max*100.0));
	        
			
		}
		
		
		if(listCommercant!=null)
		{
			
			ListView listViewstore = (ListView)rootView.findViewById(R.id.listViewStore);
	        storeBaseAdapter = new StoreBaseAdapter(getActivity(),listCommercant);
	        listViewstore.setAdapter(storeBaseAdapter);
	        listViewstore.setChoiceMode(ListView.CHOICE_MODE_SINGLE);
	    	listViewstore.setOnItemClickListener(OnItemClickListenerViewstore);
		}
		
       
        
       
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
	  // Clic sur un element de la liste déclenche cette action
	private AdapterView.OnItemClickListener OnItemClickListenerViewstore = new AdapterView.OnItemClickListener() {
        @Override
        public void onItemClick(AdapterView<?> adapterView, View view, int position, long id) {
            for (int j = 0; j < adapterView.getChildCount(); j++)
                adapterView.getChildAt(j).setBackgroundColor(Color.TRANSPARENT);

            if(position1==position)
            {
            //	System.out.println(actionMode.toString());
                actionMode.finish();
            }
            view.setBackgroundColor(Color.LTGRAY);
            changeContextual(view, position);
            position1=position;

        }
    
        // apparation du menu contextuel et de ses actions associées
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
                	  	Commercant store = (Commercant) storeBaseAdapter.getItem(position);
                	  	// TODO PASSER LES PARAMETRES
                	  	i = new Intent(getActivity().getApplicationContext(), MapsActivity.class);
                	  	i.putExtra("NAME", store.getNom());
                	  	i.putExtra("LONGITUDE", store.getLongitude_dg());
                	  	i.putExtra("LATITUDE", store.getLatitude_dg());
                	  	i.putExtra("PARENTNAME","Store");
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
	private double latitude;
	private double longitude;
	

	@Override
	public void onLocationChanged(Location location) {
		latitude = location.getLatitude();
		longitude = location.getLongitude();
	
	}

	@Override
	public void onStatusChanged(String provider, int status, Bundle extras) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onProviderEnabled(String provider) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onProviderDisabled(String provider) {
		// TODO Auto-generated method stub
		
	}



	
	
}
