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
 * Created by Karol on 2015-01-03.
 */
public class MessagesAdapter extends BaseAdapter {

    private Activity activity;
    public ArrayList<HashMap<String, String>> data;
    private static LayoutInflater inflater=null;
    private View listView;
    public View vi;


    public MessagesAdapter(Activity a, ArrayList<HashMap<String, String>> d, View list) {
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
            vi = inflater.inflate(R.layout.line_single_message, null);
        }
        TextView author = (TextView)vi.findViewById(R.id.messageAuthor);
        TextView message = (TextView)vi.findViewById(R.id.messageText);
        View listview = getListView();


        map = data.get(position);
        author.setText(map.get(Constant.CONTACT_NAME));
        message.setText(map.get(Constant.MESSAGE_TEXT));

        listview.setVerticalFadingEdgeEnabled(false);
        listview.setVerticalScrollBarEnabled(true);


        return vi;
    }
}