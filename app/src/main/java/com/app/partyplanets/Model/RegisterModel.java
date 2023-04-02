package com.app.partyplanets.Model;

public class RegisterModel
{
    String fname,lname,email,password,cpassword;

    public RegisterModel(String fname, String lname, String email, String password, String cpassword)
    {
        this.fname = fname;
        this.lname = lname;
        this.email = email;
        this.password = password;
        this.cpassword = cpassword;
    }

    public String getFname() {
        return fname;
    }

    public void setFname(String fname) {
        this.fname = fname;
    }

    public String getLname() {
        return lname;
    }

    public void setLname(String lname) {
        this.lname = lname;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getCpassword() {
        return cpassword;
    }

    public void setCpassword(String cpassword) {
        this.cpassword = cpassword;
    }
}
