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

public class AdapterApdclVendor extends ArrayAdapter<RetrieveApdclVendor> {

    Context context;
    List<RetrieveApdclVendor> arrayListApdclVendor;


    public AdapterApdclVendor(@NonNull Context context, List<RetrieveApdclVendor> arrayListApdclVendor) {
        super(context, R.layout.apdclvendor_list_item,arrayListApdclVendor);

        this.context = context;
        this.arrayListApdclVendor = arrayListApdclVendor;

    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.apdclvendor_list_item,null,true);

        TextView ID = view.findViewById(R.id.id);
        TextView Name = view.findViewById(R.id.name);

        ID.setText(arrayListApdclVendor.get(position).getApdclvendor_id());
        Name.setText(arrayListApdclVendor.get(position).getAPDCLVendorName());

        return view;
    }
}
