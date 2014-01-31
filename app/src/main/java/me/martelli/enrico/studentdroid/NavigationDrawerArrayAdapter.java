package me.martelli.enrico.studentdroid;

import android.content.Context;
import android.graphics.Typeface;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.List;

public class NavigationDrawerArrayAdapter extends ArrayAdapter<String> {

    public int selectedItem;

    public NavigationDrawerArrayAdapter(Context context, int resource, String[] objects) {
        super(context, resource, objects);
        selectedItem = 0;
    }

    public NavigationDrawerArrayAdapter(Context context, int resource, int textViewResourceId, String[] objects) {
        super(context, resource, textViewResourceId, objects);
        selectedItem = 0;
    }

    public void selectItem(int selectedItem){
        this.selectedItem = selectedItem;
        notifyDataSetChanged();
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        convertView = super.getView(position, convertView, parent);

        if(position == selectedItem) {
            ((TextView)convertView).setTypeface(Typeface.create("sans-serif", Typeface.BOLD));
        } else {
            ((TextView)convertView).setTypeface(Typeface.create("sans-serif-light", Typeface.NORMAL));
        }

        return convertView;
    }
}
