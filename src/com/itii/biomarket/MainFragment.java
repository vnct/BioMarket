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
		
		/** Mise en place de l'autocomplétion dans la barre de recherche **/
		AutoCompleteTextView autoComplete = (AutoCompleteTextView)rootView.findViewById(R.id.autoCompleteTextView1);
 
		/** Instanciation de l'objet permettant de gérer le magasin et les articles **/
		StoreManagement DAOS = new StoreManagement(this.getActivity());
        DAOS.open();

        /** Déclaration d'une liste d'objet Article **/
        List<Article> ArticleGet = DAOS.getArticles();
        
        /** Déclaration d'un tableau de String de la taille de la liste précédente **/
        String[] autoCompletString = new String[ArticleGet.size()];
        
        /** Récupération des noms des articles de la liste pou les mettre dans le tableau de String  **/
        if(ArticleGet != null) {
        	int i = 0;
        	
			for(Article m : ArticleGet)
			{
				autoCompletString[i] = m.getNom();
				i++;
			}
        }

        /** ArrayAdaptater permet un affichage de la liste avec le style Android **/
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
