package com.ennoblesoft.arif.jsonreadfromassets.adapters;

import android.app.Activity;
import android.content.res.Resources;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.ennoblesoft.arif.jsonreadfromassets.R;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by Arif on 7/5/2018.
 */

public class ContactAdapter extends RecyclerView.Adapter<ContactAdapter.ContactViewHolder> {
    private ArrayList<HashMap<String, String>> data;
    private static LayoutInflater inflater = null;
    public Resources res;

    public ContactAdapter(Activity activity, ArrayList<HashMap<String, String>> data) {
        this.data = data;
        inflater = LayoutInflater.from(activity);
    }

    @Override
    public ContactViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.item_contact, parent, false);
        return new ContactViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final ContactViewHolder holder, final int position) {
        String name = data.get(position).get("name");
        String number = data.get(position).get("number");

        holder.tvName.setText(name);
        holder.tvNumber.setText(number);
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    class ContactViewHolder extends RecyclerView.ViewHolder {
        TextView tvName, tvNumber;

        ContactViewHolder(View itemView) {
            super(itemView);
            tvName = itemView.findViewById(R.id.tv_name);
            tvNumber = itemView.findViewById(R.id.tv_number);
        }
    }
}
