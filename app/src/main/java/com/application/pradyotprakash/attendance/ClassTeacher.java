package com.application.pradyotprakash.attendance;

/**
 * Created by pradyotprakash on 24/01/18.
 */

public class ClassTeacher {
    public String Branch;
    public String ClassName;
    public String Semester;

    public ClassTeacher(String branch, String className, String semester) {
        Branch = branch;
        ClassName = className;
        Semester = semester;
    }

    public String getBranch() {
        return Branch;
    }

    public void setBranch(String branch) {
        Branch = branch;
    }

    public String getClassName() {
        return ClassName;
    }

    public void setClassName(String className) {
        ClassName = className;
    }

    public String getSemester() {
        return Semester;
    }

    public void setSemester(String semester) {
        Semester = semester;
    }
}
