package com.gojek.exercise.weather.main;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.gojek.exercise.weather.R;

import java.util.List;

public class MainAdapter extends BaseAdapter {
    private Context context;
    private List<String> valueDay;
    private List<String> valueTemperature;

    public MainAdapter(Context context) {
        this.context = context;
    }

    public void setData(List<String> valueDay, List<String> valueTemperature) {
        this.valueDay = valueDay;
        this.valueTemperature = valueTemperature;
    }

    @Override
    public int getCount() {
        return valueDay.size();
    }

    @Override
    public Object getItem(int position) {
        return this.valueTemperature.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = (LayoutInflater) context
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View listView = inflater.inflate(R.layout.list_view, parent, false);
        TextView textDay = (TextView) listView.findViewById(R.id.textDay);
        TextView textTemperature = (TextView) listView.findViewById(R.id.textTemperatureList);

        String valueDayString = valueDay.get(position);
        String valueTemperatureString = valueTemperature.get(position);
        if (valueDayString != null) {
            textDay.setText(valueDayString);
        }
        if (valueTemperatureString != null) {
            textTemperature.setText(valueTemperatureString);
        }

        return listView;
    }

    @Override
    public void notifyDataSetChanged() {
        super.notifyDataSetChanged();
    }
}
