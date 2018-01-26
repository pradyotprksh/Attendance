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

public class ClassTeacherListAdapter extends BaseAdapter{

    private Context mContext;
    private List<ClassTeacher> mAssetList;

    public ClassTeacherListAdapter(Context mContext, List<ClassTeacher> mAssetList) {
        this.mContext = mContext;
        this.mAssetList = mAssetList;
    }

    @Override
    public int getCount() {
        return mAssetList.size();
    }

    @Override
    public Object getItem(int position) {
        return mAssetList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View v = View.inflate(mContext, R.layout.class_teacher_inforamtion_layout,null);
        TextView classData = v.findViewById(R.id.classData);
        TextView semesterData = v.findViewById(R.id.semesterData);
        TextView branchData = v.findViewById(R.id.branchData);
        classData.setText(mAssetList.get(position).getClassName());
        semesterData.setText(mAssetList.get(position).getSemester());
        branchData.setText(mAssetList.get(position).getBranch());
        return v;
    }
}
