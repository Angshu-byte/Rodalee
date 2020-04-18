package com.angshumaan.rodalee;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.List;

public class AdapterAedaVendor extends ArrayAdapter<RetrieveAedaVendor> {


    Context context;
    List<RetrieveAedaVendor> arrayListAedaVendor;


    public AdapterAedaVendor(@NonNull Context context, List<RetrieveAedaVendor> arrayListAedaVendor) {
        super(context, R.layout.aedavendor_list_item,arrayListAedaVendor);

        this.context = context;
        this.arrayListAedaVendor = arrayListAedaVendor;

    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.aedavendor_list_item,null,true);

        TextView id = view.findViewById(R.id.aid);
        TextView name = view.findViewById(R.id.aname);

        id.setText(arrayListAedaVendor.get(position).getAedavendor_id());
        name.setText(arrayListAedaVendor.get(position).getAEDAVendorName());

        return view;
    }
}
