package com.application.pradyotprakash.attendance;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.List;

/**
 * Created by pradyotprakash on 24/01/18.
 */

public class StudentListAdapter extends BaseAdapter{

    private Context mContext;
    private List<StudentUsn> mStudentList;

    public StudentListAdapter(Context mContext, List<StudentUsn> mStudentList) {
        this.mContext = mContext;
        this.mStudentList = mStudentList;
    }

    @Override
    public int getCount() {
        return mStudentList.size();
    }

    @Override
    public Object getItem(int position) {
        return mStudentList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View v = View.inflate(mContext, R.layout.attendence_single_row_layout,null);
        TextView usn = v.findViewById(R.id.usnStudentValue);
        TextView name = v.findViewById(R.id.nameStudentValue);
        TextView phn = v.findViewById(R.id.phoneStudentValue);
        TextView email = v.findViewById(R.id.emailStudentValue);
        TextView pphn = v.findViewById(R.id.phoneParentValue);
        usn.setText(mStudentList.get(position).getUsn());
        name.setText(mStudentList.get(position).getName());
        phn.setText(mStudentList.get(position).getPhone());
        email.setText(mStudentList.get(position).getEmail());
        pphn.setText(mStudentList.get(position).getParentNumber());
        return v;
    }
}
