package com.itii.biomarket;

import java.util.List;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

public class StoreBaseAdapter extends BaseAdapter{

	 private LayoutInflater mInflater;
	 private Context mContext;
	 List<String> myStoreList;
	    
    public List<String> getMyStoreList() {
		return myStoreList;
	}


	public void setMyStoreList(List<String> myStoreList) {
		this.myStoreList = myStoreList;
	}


	public StoreBaseAdapter(Context context, List<String> storeList){
		myStoreList = storeList;
		mContext = context;
        mInflater = LayoutInflater.from(mContext);
    }   
	    
	    
	@Override
	public int getCount() {
		return myStoreList.size();
	}

	@Override
	public Object getItem(int position) {
		 return myStoreList.get(position);
	}

	@Override
	public long getItemId(int position) {
		 return position;
	}

	// Permet de créer la tete de la liste a afficher dans la storelist
	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		 View vue = null;
	        if(convertView != null)
	        {
	            vue = convertView;
	        }
	        else
	        {
	            vue = mInflater.inflate(R.layout.storebaseadapter, parent, false);
	        }
	
	        TextView textViewName = (TextView) vue.findViewById(R.id.textViewStoreName);
	        TextView textViewCompatibility = (TextView) vue.findViewById(R.id.textViewStoreCompatibility);
	        TextView textViewPosition = (TextView) vue.findViewById(R.id.textViewStorePosition);
	        textViewName.setText(myStoreList.get(position));
	        textViewCompatibility.setText(myStoreList.get(position)); 
	        textViewPosition.setText(myStoreList.get(position));
	        return vue;
	}

}
