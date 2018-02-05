package com.pinakbanik.leadingapps;

/**
 * Created by pronab on 1/1/17.
 */

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.RatingBar;
import android.widget.TextView;

import java.util.ArrayList;

public class CustomAdapter2 extends BaseAdapter {

    private ArrayList<Contacts2> _Contacts2;
    private Activity context;
    private LayoutInflater inflater;
    private ArrayList<Contacts2> mStringFilterList2;


    public CustomAdapter2(Activity context, ArrayList<Contacts2> _Contacts2) {
        super();
        this.context = context;
        this._Contacts2 = _Contacts2;
        mStringFilterList2 =  _Contacts2;
        this.inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public int getCount() {
        return _Contacts2.size();
    }

    @Override
    public Object getItem(int position) {
        return _Contacts2.get(position).getId();

    }

    @Override
    public long getItemId(int position) {
        return 0;

    }

    public void clearData() {
        // clear the data
        _Contacts2.clear();
    }





    public class ViewHolder {
        TextView name,msg,date,cate;

    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder;
        if (convertView == null) {
            holder = new ViewHolder();
            convertView = inflater.inflate(R.layout.list_item2, null);
            holder.name = (TextView) convertView.findViewById(R.id.foodname);
            holder.cate = (TextView) convertView.findViewById(R.id.cate);
            holder.msg = (TextView) convertView.findViewById(R.id.hotelname);
            holder.date= (TextView) convertView.findViewById(R.id.catagory);
            convertView.setTag(holder);
        } else
            holder = (ViewHolder) convertView.getTag();
        holder.name.setText("" + _Contacts2.get(position).getName());
        holder.cate.setText("" + _Contacts2.get(position).getCaty());
        holder.msg.setText("" + _Contacts2.get(position).getMsg());
        holder.date.setText("Date:" + _Contacts2.get(position).getDate());

        return convertView;
    }




}
