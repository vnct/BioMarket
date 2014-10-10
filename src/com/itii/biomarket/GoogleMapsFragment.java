package com.itii.biomarket;


import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapFragment;
import com.google.android.gms.maps.MapsInitializer;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.maps.MapView;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;

/**
 * A simple {@link Fragment} subclass. Activities that contain this fragment
 * must implement the {@link GoogleMapsFragment.OnFragmentInteractionListener}
 * interface to handle interaction events. Use the
 * {@link GoogleMapsFragment#newInstance} factory method to create an instance
 * of this fragment.
 *
 */
public class GoogleMapsFragment extends Fragment  {
	/**
	 * The fragment argument representing the section number for this
	 * fragment.
	 */
	private static GoogleMap mMap;
	private static MapView mMapView;
	private static Double latitude, longitude;
	
	private static final String ARG_SECTION_NUMBER = "section_number";

	/**
	 * Returns a new instance of this fragment for the given section number.
	 */
	public static GoogleMapsFragment newInstance(int sectionNumber) {
		GoogleMapsFragment fragment = new GoogleMapsFragment();
		Bundle args = new Bundle();
		args.putInt(ARG_SECTION_NUMBER, sectionNumber);
		fragment.setArguments(args);
		return fragment;
	}

	public GoogleMapsFragment() {
	}
 	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
 		 if (container == null) {
  	        return null;
  	    }
 		 View v = (RelativeLayout) inflater.inflate(R.layout.fragment_maps, container,
 	            false);
 		 
 	  
 		 setUpMapIfNeeded(); // For setting up the MapFragment

 	  
 	    return v;
	}
 
 	public static void setUpMapIfNeeded() {
 	    // Do a null check to confirm that we have not already instantiated the map.
 	    if (mMap == null) {
 	        // Try to obtain the map from the SupportMapFragment.
 	        mMap = ((SupportMapFragment) MapsActivity.fragmentManager
 	        		.findFragmentById(R.id.location_map)).getMap();
 	        // Check if we were successful in obtaining the map.
 	        if (mMap != null)
 	            setUpMap();
 	    }
 	}

 	private static void setUpMap() {
 	    // For showing a move to my loction button
 	    mMap.setMyLocationEnabled(true);
 	    // For dropping a marker at a point on the Map
 	    mMap.addMarker(new MarkerOptions().position(new LatLng(latitude, longitude)).title("My Home").snippet("Home Address"));
 	    // For zooming automatically to the Dropped PIN Location
 	    mMap.animateCamera(CameraUpdateFactory.newLatLngZoom(new LatLng(latitude,
 	            longitude), 12.0f));
 	}

 	@Override
 	public void onViewCreated(View view, Bundle savedInstanceState) {
 	    // TODO Auto-generated method stub
 	    if (mMap != null)
 	        setUpMap();

 	    if (mMap == null) {
 	        // Try to obtain the map from the SupportMapFragment.
 	        mMap = ((SupportMapFragment) MapsActivity.fragmentManager
 	                .findFragmentById(R.id.location_map)).getMap();
 	        // Check if we were successful in obtaining the map.
 	        if (mMap != null)
 	            setUpMap();
 	    }
 	}

 	/**** The mapfragment's id must be removed from the FragmentManager
 	 **** or else if the same it is passed on the next time then 
 	 **** app will crash ****/
 	@Override
 	public void onDestroyView() {
 	    super.onDestroyView();
 	    if (mMap != null) {
 	    	MapsActivity.fragmentManager.beginTransaction()
 	            .remove(MapsActivity.fragmentManager.findFragmentById(R.id.location_map)).commit();
 	        mMap = null;
 	    }
 	}
 	

}
