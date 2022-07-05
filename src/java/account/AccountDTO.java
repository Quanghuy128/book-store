/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package account;

import java.io.Serializable;

/**
 *
 * @author huy
 */
public class AccountDTO implements Serializable{
    private String username;
    private String password;
    private String fullname;
    private String role;
    private String address;
    private String phone_no;
    private String sex;

    public AccountDTO(String username, String password, String fullname, String role, String address, String phone_no, String sex) {
        this.username = username;
        this.password = password;
        this.fullname = fullname;
        this.role = role;
        this.address = address;
        this.phone_no = phone_no;
        this.sex = sex;
    }

    /**
     * @return the username
     */
    public String getUsername() {
        return username;
    }

    /**
     * @param username the username to set
     */
    public void setUsername(String username) {
        this.username = username;
    }

    /**
     * @return the password
     */
    public String getPassword() {
        return password;
    }

    /**
     * @param password the password to set
     */
    public void setPassword(String password) {
        this.password = password;
    }

    /**
     * @return the fullname
     */
    public String getFullname() {
        return fullname;
    }

    /**
     * @param fullname the fullname to set
     */
    public void setFullname(String fullname) {
        this.fullname = fullname;
    }

    /**
     * @return the role
     */
    public String getRole() {
        return role;
    }

    /**
     * @param role the role to set
     */
    public void setRole(String role) {
        this.role = role;
    }

    /**
     * @return the address
     */
    public String getAddress() {
        return address;
    }

    /**
     * @param address the address to set
     */
    public void setAddress(String address) {
        this.address = address;
    }

    /**
     * @return the phone_no
     */
    public String getPhone_no() {
        return phone_no;
    }

    /**
     * @param phone_no the phone_no to set
     */
    public void setPhone_no(String phone_no) {
        this.phone_no = phone_no;
    }

    /**
     * @return the sex
     */
    public String getSex() {
        return sex;
    }

    /**
     * @param sex the sex to set
     */
    public void setSex(String sex) {
        this.sex = sex;
    }
    
}
