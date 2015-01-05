package pl.karbar.smskompresor.util;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.HashMap;

import pl.karbar.smskompresor.R;

/**
 * Created by Karol on 2015-01-02.
 */
public class ContactAdapter extends BaseAdapter {

    private Activity activity;
    public ArrayList<HashMap<String, String>> data;
    private static LayoutInflater inflater=null;
    private View listView;
    public View vi;


    public ContactAdapter(Activity a, ArrayList<HashMap<String, String>> d, View list) {
        activity = a;
        data=d;
        inflater = (LayoutInflater)activity.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        listView = list;
    }

    public ArrayList<HashMap<String, String>> getData(){
        return data;
    }
    @Override
    public int getCount() {
        return data.size();
    }

    @Override
    public Object getItem(int position) {
        return data.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }


    public View getListView(){
        return listView;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
            HashMap<String, String> map = new HashMap<String, String>();
            vi=convertView;
            if(convertView==null) {
                vi = inflater.inflate(R.layout.line_contact, null);
            }
            TextView title = (TextView)vi.findViewById(R.id.contactName); // title
            View listview = getListView();


            map = data.get(position);
            title.setText(map.get(Constant.CONTACT_NAME));

            listview.setVerticalFadingEdgeEnabled(false);
            listview.setVerticalScrollBarEnabled(true);


        return vi;
    }
}