package com.itii.biomarket;



import java.util.ArrayList;
import java.util.List;

import android.os.Bundle;
import android.app.Fragment;
import android.graphics.Color;

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

		private BasketBaseAdapter basketBaseAdapter = null; 
		private List<String> toto = new ArrayList<String>();

		private ListView listViewbasket;
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

		// Création de l'action bar initiale
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
			
			
			 
		        toto.add("basket 1");
		        toto.add("basket 2");
		        toto.add("basket 3");
		        //listViewstore = (ListView)findViewById(R.id.listViewStore);
		        listViewbasket = (ListView)rootView.findViewById(R.id.listViewbasket);
		        basketBaseAdapter = new BasketBaseAdapter(getActivity(),toto);
		        listViewbasket.setAdapter(basketBaseAdapter);
		        listViewbasket.setChoiceMode(ListView.CHOICE_MODE_SINGLE);
		        listViewbasket.setOnItemClickListener(OnItemClickListenerbasket);
			
			return rootView;
		}
		
		int position1=-1;
		ActionMode actionMode;
		

		
		// En cas de clic sur un element de la liste
	    private AdapterView.OnItemClickListener OnItemClickListenerbasket = new AdapterView.OnItemClickListener() {
	        @Override
	        public void onItemClick(AdapterView<?> adapterView, View view, int position, long id) {
	            for (int j = 0; j < adapterView.getChildCount(); j++)
	                adapterView.getChildAt(j).setBackgroundColor(Color.TRANSPARENT);

	            if(position1==position)
	            {
	                actionMode.finish();
	            }
	            view.setBackgroundColor(Color.LTGRAY);
	            changeContextual(view, position);
	            position1=position;
	            

	            
	        }
	        
	        // appel de la méthode pour faire apparaitre le menu contextuel
	        private void changeContextual(View view, final int position) {
	        	final View view1= view;
	    		if(getActivity()!=null)
	    		{
	    			
	    			actionMode = getActivity().startActionMode(new ActionMode.Callback() {

	    				
						@Override
						public boolean onCreateActionMode(ActionMode mode,
								Menu menu) {
							 try
				                {
				                    MenuInflater inflater = mode.getMenuInflater();
				                    inflater.inflate(R.menu.main_contextual, menu);
				                    
				                  
				                    return true;
				                }
				                catch (NullPointerException e)
				                {
				                    return false;
				                }
						}

						@Override
						public boolean onPrepareActionMode(ActionMode mode,
								Menu menu) {
							// TODO Auto-generated method stub
							return false;
						}

						@Override
						public boolean onActionItemClicked(ActionMode mode,
								MenuItem item) {
							switch (item.getItemId()) {
							 case R.id.menu_basket_down:
								
								return true;
							 case R.id.menu_basket_up:
									
									return true;
							 case R.id.menu_basket_delete:
								 String basket_item = (String) basketBaseAdapter.getItem(position);
								 toto.remove(basket_item);
								 basketBaseAdapter = new BasketBaseAdapter(getActivity(),toto);
								 listViewbasket.setAdapter(basketBaseAdapter);
								 listViewbasket.setOnItemClickListener(OnItemClickListenerbasket);
			                        actionMode.finish();
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
	    
		
		@Override
		public boolean onOptionsItemSelected(MenuItem item) {


			
			 
		return super.onOptionsItemSelected(item);
		}
	
}
