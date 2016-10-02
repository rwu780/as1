package assignment1.rwu4_habittracker;

import android.content.Context;
import android.graphics.Color;
import android.os.Parcelable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.CheckBox;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * Created by Rui on 2016-09-29.
 * Code get from http://www.open-open.com/lib/view/open1408951725963.html
 */
public class ListViewAdapter extends BaseAdapter {

    private Context context;
    private static List<String> dayList;
    private static List<Integer> daySelected;

    // Use HashMap to record day selected and check
    private static HashMap<Integer, Boolean> isSelected;

    class ViewHolder {

        TextView tvName;
        CheckBox cb;
    }

    public ListViewAdapter(Context context, List<String> days) {
        // TODO Auto-generated constructor stub
        dayList = days;

        this.context = context;
        isSelected = new HashMap<Integer, Boolean>();

        //Unchecked all checkbox
        initDate();
    }
    // Initial isSelected
    private void initDate() {
        for (int i = 0; i < dayList.size(); i++) {
            getIsSelected().put(i, false);
        }
    }

    public List<String> getDayList(){
        return dayList;
    }

    @Override
    public int getCount() {
        // TODO Auto-generated method stub
        return dayList.size();
    }

    @Override
    public Object getItem(int position) {
        // TODO Auto-generated method stub
        return dayList.get(position);
    }

    @Override
    public long getItemId(int position) {
        // TODO Auto-generated method stub
        return position;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        // TODO Auto-generated method stub

        ViewHolder holder;
        String bean = dayList.get(position);
        LayoutInflater inflater = LayoutInflater.from(context);
        if (convertView == null) {
            convertView = inflater.inflate(
                    R.layout.listviewitem, null);
            holder = new ViewHolder();
            holder.cb = (CheckBox) convertView.findViewById(R.id.item_cb);
            holder.tvName = (TextView) convertView
                    .findViewById(R.id.item_tv);
            holder.cb.setTextColor(Color.BLACK);
            holder.cb.setHighlightColor(Color.GRAY);
            holder.tvName.setTextColor(Color.BLACK);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }

        holder.tvName.setText(bean);

        // Monitor checkbox
        holder.cb.setOnClickListener(new View.OnClickListener() {

            public void onClick(View v) {

                if (isSelected.get(position)) {
                    isSelected.put(position, false);
                    setIsSelected(isSelected);
                } else {
                    isSelected.put(position, true);
                    setIsSelected(isSelected);
                }

            }
        });

        // Update checkbox
        holder.cb.setChecked(getIsSelected().get(position));
        return convertView;
    }

    public static HashMap<Integer, Boolean> getIsSelected() {
        return isSelected;
    }

    public static List<Integer> getDaySelected(){
        daySelected = new ArrayList<Integer>();

        //Code get from http://stackoverflow.com/questions/1066589/iterate-through-a-hashmap
        Iterator i = isSelected.entrySet().iterator();
        while (i.hasNext()){
            Map.Entry pair = (Map.Entry) i.next();
            Boolean b = (Boolean) pair.getValue();
            if(b){
                int index = Integer.parseInt(pair.getKey().toString());
                daySelected.add(index+1);
            }
        }

        return daySelected;
    }

    public static void setIsSelected(HashMap<Integer, Boolean> isSelected) {
        ListViewAdapter.isSelected = isSelected;
    }
}
