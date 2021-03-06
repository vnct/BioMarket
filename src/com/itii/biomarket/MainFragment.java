package com.itii.biomarket;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import android.app.Activity;
import android.app.Fragment;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.Toast;

import com.itii.biomarket.controler.BasketManagement;
import com.itii.biomarket.model.Article;
import com.itii.biomarket.model.BasketDB;

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
	private Button addButton;
	private Button buyButton;
	private AutoCompleteTextView autoComplete;
	private    List<Article> articleList = new ArrayList<Article>();


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

		/* Mise en place de l'autocomplétion dans la barre de recherche **/
		autoComplete = (AutoCompleteTextView)rootView.findViewById(R.id.autoCompleteTextView1);
		addButton = (Button) rootView.findViewById(R.id.button2);

		buyButton = (Button) rootView.findViewById(R.id.button1);

		/*
		 * Action lors du clic sur AJOUTER AU PANIER
		 *
		 * 
		 */
		addButton.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {

				try {
					//basketManagement.addItem(autoComplete.getText().toString().toLowerCase());
					Boolean articleadd;
					String article = autoComplete.getText().toString().trim().toLowerCase();
						
					articleadd = MainActivity.basketManagement.addItem(article);
					autoComplete.getText().clear();
					//Log.d("MAIN",  articleadd+"");
					if(!articleadd)
					{
						//Log.d("MAIN", "TOAST");
						Toast.makeText(getActivity(), R.string.fragment_main_toast, Toast.LENGTH_SHORT).show();
					}

				} catch (Exception e) {


				}

			}
		});
		/*
		 * Action lors du clic ACHETER
		 * 
		 */
		buyButton.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				String article = autoComplete.getText().toString().trim().toLowerCase();
				autoComplete.getText().clear();
				Intent i;
				i = new Intent(getActivity(), MapsActivity.class);
				i.putExtra("PARENTNAME","Home");
				i.putExtra("BASKET", true);
				i.putExtra("ARTICLE",article );
				startActivityForResult(i, 1);

			}
		});

		/* Instanciation de l'objet permettant de gérer le magasin et les articles **/
		//BasketManagement basketManagement = new BasketManagement(getActivity());


		/* Déclaration d'une liste d'objet Article **/

		try {
			articleList = MainActivity.basketManagement.getArticles();

			/* Déclaration d'un tableau de String de la taille de la liste précédente **/
			String[] autoCompletString = new String[articleList.size()];

			/* Récupération des noms des articles de la liste pou les mettre dans le tableau de String  **/
			if(articleList != null) {
				int i = 0;

				for(Article m : articleList)
				{
					autoCompletString[i] = m.getNom();
					i++;
				}
			}

			/* ArrayAdaptater permet un affichage de la liste avec le style Android **/
			ArrayAdapter<String> adp=new ArrayAdapter<String>(this.getActivity(),
					android.R.layout.simple_dropdown_item_1line,autoCompletString);

			autoComplete.setAdapter(adp);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return rootView;
	}



	@Override
	public void onAttach(Activity activity) {
		super.onAttach(activity);
		((MainActivity) activity).onSectionAttached(getArguments().getInt(
				ARG_SECTION_NUMBER));
	}

}
