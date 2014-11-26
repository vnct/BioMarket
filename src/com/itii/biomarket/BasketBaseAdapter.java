package com.itii.biomarket;

import java.util.List;

import com.itii.biomarket.model.Article;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

public class BasketBaseAdapter extends BaseAdapter{

	private LayoutInflater mInflater;
    private Context mContext;
    List<Article> mybasketList;
    
    

    public List<Article> getBasketList() {
		return mybasketList;
	}
	public void setBasketList(List<Article> basketList) {
		this.mybasketList = basketList;
	}
	public BasketBaseAdapter(Context context, List<Article> basketList){
        mContext = context;
        mybasketList = basketList;
        mInflater = LayoutInflater.from(mContext);
    }
	@Override
	public int getCount() {
		   return mybasketList.size();
	}

	@Override
	public Object getItem(int position) {
		return mybasketList.get(position);
	}

	@Override
	public long getItemId(int position) {
		 return position;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		  View vue = null;
	        if(convertView != null)
	        {
	            vue = convertView;
	        }
	        else
	        {
	            vue = mInflater.inflate(R.layout.basketbaseadapter, parent, false);
	        }
	        TextView textViewQuantity = (TextView) vue.findViewById(R.id.textViewBasketQuantity);
	        TextView textViewName = (TextView) vue.findViewById(R.id.textViewBasketName);
	        textViewName.setText(mybasketList.get(position).getNom());
	        textViewQuantity.setText(mybasketList.get(position).getCategorie());
	       

	        return vue;
	}

}
