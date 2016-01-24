package org.hyochan.godlotto.adpaters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.Toast;

import org.hyochan.godlotto.R;
import org.hyochan.godlotto.data.RollingData;
import org.hyochan.godlotto.utils.android.RollingItem;

import java.util.ArrayList;

/**
 * Created by hyochan on 2016-01-24.
 */
public class RollingAdapter extends BaseAdapter {

    private final String TAG = "RollingAdapter";
    private Context context;
    private ArrayList<RollingData> arrRolling;
    private ViewHolder viewHolder;
    private LayoutInflater layoutInflater;

    private static class ViewHolder{
        public CheckBox checkBox;
        public RollingItem rollingItem1;
        public RollingItem rollingItem2;
        public RollingItem rollingItem3;
        public RollingItem rollingItem4;
        public RollingItem rollingItem5;
        public RollingItem rollingItem6;
    }

    public RollingAdapter(Context context, ArrayList<RollingData> arrRolling){
        this.context = context;
        this.layoutInflater = LayoutInflater.from(context);
        this.arrRolling = arrRolling;
    }

    @Override
    public int getCount() {
        return arrRolling.size();
    }

    @Override
    public RollingData getItem(int position) {
        return arrRolling.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        final RollingData rollingData = arrRolling.get(position);

        if (convertView == null){
            convertView = layoutInflater.inflate(R.layout.adapter_rolling, null);
            viewHolder = new ViewHolder();
            viewHolder.checkBox = (CheckBox) convertView.findViewById(R.id.checkbox);
            viewHolder.rollingItem1 = (RollingItem) convertView.findViewById(R.id.roll_item_1);
            viewHolder.rollingItem2 = (RollingItem) convertView.findViewById(R.id.roll_item_2);
            viewHolder.rollingItem3 = (RollingItem) convertView.findViewById(R.id.roll_item_3);
            viewHolder.rollingItem4 = (RollingItem) convertView.findViewById(R.id.roll_item_4);
            viewHolder.rollingItem5 = (RollingItem) convertView.findViewById(R.id.roll_item_5);
            viewHolder.rollingItem6 = (RollingItem) convertView.findViewById(R.id.roll_item_6);
            viewHolder.rollingItem1.roll(rollingData.getNumbers()[0]);
            viewHolder.rollingItem2.roll(rollingData.getNumbers()[1]);
            viewHolder.rollingItem3.roll(rollingData.getNumbers()[2]);
            viewHolder.rollingItem4.roll(rollingData.getNumbers()[3]);
            viewHolder.rollingItem5.roll(rollingData.getNumbers()[4]);
            viewHolder.rollingItem6.roll(rollingData.getNumbers()[5]);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }

        viewHolder.checkBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    Toast.makeText(context, "IS checked", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(context, "NOT checked", Toast.LENGTH_SHORT).show();
                }
            }
        });
        return convertView;
    }

}
