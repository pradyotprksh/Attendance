package com.application.pradyotprakash.attendance;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.List;

/**
 * Created by pradyotprakash on 26/01/18.
 */

public class AbsentAttendanceListAdapter extends BaseAdapter{

    private Context mContext;

    public AbsentAttendanceListAdapter(Context mContext, List<AbsentAttendance> mAbsentList) {
        this.mContext = mContext;
        this.mAbsentList = mAbsentList;
    }

    private List<AbsentAttendance> mAbsentList;

    @Override
    public int getCount() {
        return mAbsentList.size();
    }

    @Override
    public Object getItem(int position) {
        return mAbsentList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View v = View.inflate(mContext, R.layout.absent_attendance_single_row_layout,null);
        TextView date = v.findViewById(R.id.dateValue);
        TextView time = v.findViewById(R.id.timeValue);
        TextView status = v.findViewById(R.id.statusValue);
        date.setText(mAbsentList.get(position).getDate());
        time.setText(mAbsentList.get(position).getTimes());
        status.setText(mAbsentList.get(position).getStatus());
        return v;
    }
}
