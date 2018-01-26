package com.application.pradyotprakash.attendance;

/**
 * Created by pradyotprakash on 26/01/18.
 */

public class AbsentAttendance {
    public String date;
    public String times;
    public String status;

    public AbsentAttendance(String date, String times, String status) {
        this.date = date;
        this.times = times;
        this.status = status;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getTimes() {
        return times;
    }

    public void setTimes(String times) {
        this.times = times;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
