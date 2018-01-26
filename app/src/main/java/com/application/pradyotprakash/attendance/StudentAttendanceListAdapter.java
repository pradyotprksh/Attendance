package com.application.pradyotprakash.attendance;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.List;

/**
 * Created by pradyotprakash on 25/01/18.
 */

public class StudentAttendanceListAdapter extends BaseAdapter{

    private Context mContext;
    private List<StudentAttendance> mAttendanceList;

    public StudentAttendanceListAdapter(Context mContext, List<StudentAttendance> mAttendanceList) {
        this.mContext = mContext;
        this.mAttendanceList = mAttendanceList;
    }

    @Override
    public int getCount() {
        return mAttendanceList.size();
    }

    @Override
    public Object getItem(int position) {
        return mAttendanceList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View v = View.inflate(mContext, R.layout.student_single_subject_attendance,null);
        TextView subjectName = v.findViewById(R.id.subjectValueClick);
        TextView percentage = v.findViewById(R.id.percentageValueClick);
        subjectName.setText(mAttendanceList.get(position).getSubjectName());
        percentage.setText(mAttendanceList.get(position).getPercentage());
        return v;
    }
}
