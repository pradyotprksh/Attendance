package com.application.pradyotprakash.attendance;

/**
 * Created by pradyotprakash on 24/01/18.
 */

public class SubjectTeacher {
    public String branch;
    public String semester;
    public String className;
    public String subject;

    public SubjectTeacher(String branch, String semester, String className, String subject) {
        this.branch = branch;
        this.semester = semester;
        this.className = className;
        this.subject = subject;
    }

    public String getBranch() {
        return branch;
    }

    public void setBranch(String branch) {
        this.branch = branch;
    }

    public String getSemester() {
        return semester;
    }

    public void setSemester(String semester) {
        this.semester = semester;
    }

    public String getClassName() {
        return className;
    }

    public void setClassName(String className) {
        this.className = className;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }
}
