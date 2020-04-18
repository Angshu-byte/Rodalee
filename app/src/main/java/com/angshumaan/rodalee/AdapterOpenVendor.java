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

public class AdapterOpenVendor extends ArrayAdapter<RetrieveOpenVendor>{

    Context context;
    List<RetrieveOpenVendor> arrayListOpenVendor;


    public AdapterOpenVendor(@NonNull Context context, List<RetrieveOpenVendor> arrayListOpenVendor) {
        super(context, R.layout.openvendor_list_item,arrayListOpenVendor);

        this.context = context;
        this.arrayListOpenVendor = arrayListOpenVendor;

    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.openvendor_list_item,null,true);

        TextView tvID = view.findViewById(R.id.txt_id);
        TextView tvName = view.findViewById(R.id.txt_name);

        tvID.setText(arrayListOpenVendor.get(position).getOpenVendor_id());
        tvName.setText(arrayListOpenVendor.get(position).getOpenVendorName());

        return view;
    }
}
