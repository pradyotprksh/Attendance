package com.application.pradyotprakash.attendance;

/**
 * Created by pradyotprakash on 24/01/18.
 */

public class StudentUsn {
    public String Email;
    public String Name;
    public String ParentNumber;
    public String Phone;
    public String Usn;

    public StudentUsn(String email, String name, String parentNumber, String phone, String usn) {
        Email = email;
        Name = name;
        ParentNumber = parentNumber;
        Phone = phone;
        Usn = usn;
    }

    public String getEmail() {
        return Email;
    }

    public void setEmail(String email) {
        Email = email;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public String getParentNumber() {
        return ParentNumber;
    }

    public void setParentNumber(String parentNumber) {
        ParentNumber = parentNumber;
    }

    public String getPhone() {
        return Phone;
    }

    public void setPhone(String phone) {
        Phone = phone;
    }

    public String getUsn() {
        return Usn;
    }

    public void setUsn(String usn) {
        Usn = usn;
    }
}
