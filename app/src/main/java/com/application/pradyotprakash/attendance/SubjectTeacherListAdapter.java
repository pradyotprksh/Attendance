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

public class SubjectTeacherListAdapter extends BaseAdapter{

    private Context mContext;
    private List<SubjectTeacher> mSubjectList;

    public SubjectTeacherListAdapter(Context mContext, List<SubjectTeacher> mSubjectList) {
        this.mContext = mContext;
        this.mSubjectList = mSubjectList;
    }

    @Override
    public int getCount() {
        return mSubjectList.size();
    }

    @Override
    public Object getItem(int position) {
        return mSubjectList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View v = View.inflate(mContext, R.layout.subject_teacher_information_layout,null);
        TextView branchData = v.findViewById(R.id.branchSubject);
        TextView semesterData = v.findViewById(R.id.semesterSubject);
        TextView classData = v.findViewById(R.id.classSubject);
        TextView subjectData = v.findViewById(R.id.subjectSubject);
        classData.setText(mSubjectList.get(position).getClassName());
        semesterData.setText(mSubjectList.get(position).getSemester());
        branchData.setText(mSubjectList.get(position).getBranch());
        subjectData.setText(mSubjectList.get(position).getSubject());
        return v;
    }
}
