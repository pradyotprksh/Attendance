package com.application.pradyotprakash.attendance;

/**
 * Created by pradyotprakash on 25/01/18.
 */

public class StudentAttendance {
    public String subjectName;
    public String percentage;

    public StudentAttendance(String subjectName, String percentage) {
        this.subjectName = subjectName;
        this.percentage = percentage;
    }

    public String getSubjectName() {
        return subjectName;
    }

    public void setSubjectName(String subjectName) {
        this.subjectName = subjectName;
    }

    public String getPercentage() {
        return percentage;
    }

    public void setPercentage(String percentage) {
        this.percentage = percentage;
    }
}
