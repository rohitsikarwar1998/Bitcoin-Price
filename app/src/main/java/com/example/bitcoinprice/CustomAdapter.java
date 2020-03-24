package com.example.bitcoinprice;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;

public class CustomAdapter extends ArrayAdapter<CountryCurrency> {

    public CustomAdapter(Context context, ArrayList<CountryCurrency> mCountryCurrency){
        super(context,0,mCountryCurrency);
    }

    @Override
    public View getView(int position, View convertView,  ViewGroup parent) {
        return initView(convertView, parent,position );
    }

    @Override
    public View getDropDownView(int position,  View convertView, ViewGroup parent) {
        return initView(convertView, parent,position );
    }

    private View initView(View convertView,ViewGroup parent,int position){
        if(convertView==null) {
            convertView= LayoutInflater.from(getContext()).inflate(R.layout.spinner_item,parent,false);
        }
        TextView item=convertView.findViewById(R.id.item);

        CountryCurrency countryCurrency=getItem(position);

        if(countryCurrency!=null)
            item.setText(countryCurrency.getCurrencyCode());

        return convertView;
    }
}
