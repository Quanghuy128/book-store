/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao.account;

import java.io.Serializable;

/**
 *
 * @author huy
 */
public class InvalidAccountHandling implements Serializable{
    private String usernameLengthError;
    private String passwordLenghtError;
    private String confirmNotMatched;
    private String fullNameLengthError;
    private String usernameExisted;
    private String addressLengthError;
    private String phoneNumberLengthError;
    private String sexLengthError;

    public InvalidAccountHandling() {
    }
    
    
    /**
     * @return the usernameLengthError
     */
    public String getUsernameLengthError() {
        return usernameLengthError;
    }

    /**
     * @param usernameLengthError the usernameLengthError to set
     */
    public void setUsernameLengthError(String usernameLengthError) {
        this.usernameLengthError = usernameLengthError;
    }

    /**
     * @return the passwordLenghtError
     */
    public String getPasswordLenghtError() {
        return passwordLenghtError;
    }

    /**
     * @param passwordLenghtError the passwordLenghtError to set
     */
    public void setPasswordLenghtError(String passwordLenghtError) {
        this.passwordLenghtError = passwordLenghtError;
    }

    /**
     * @return the confirmNotMatched
     */
    public String getConfirmNotMatched() {
        return confirmNotMatched;
    }

    /**
     * @param confirmNotMatched the confirmNotMatched to set
     */
    public void setConfirmNotMatched(String confirmNotMatched) {
        this.confirmNotMatched = confirmNotMatched;
    }

    /**
     * @return the fullNameLengthError
     */
    public String getFullNameLengthError() {
        return fullNameLengthError;
    }

    /**
     * @param fullNameLengthError the fullNameLengthError to set
     */
    public void setFullNameLengthError(String fullNameLengthError) {
        this.fullNameLengthError = fullNameLengthError;
    }

    /**
     * @return the usernameExisted
     */
    public String getUsernameExisted() {
        return usernameExisted;
    }

    /**
     * @param usernameExisted the usernameExisted to set
     */
    public void setUsernameExisted(String usernameExisted) {
        this.usernameExisted = usernameExisted;
    }

    /**
     * @return the addressLengthError
     */
    public String getAddressLengthError() {
        return addressLengthError;
    }

    /**
     * @param addressLengthError the addressLengthError to set
     */
    public void setAddressLengthError(String addressLengthError) {
        this.addressLengthError = addressLengthError;
    }

    /**
     * @return the phoneNumberLengthError
     */
    public String getPhoneNumberLengthError() {
        return phoneNumberLengthError;
    }

    /**
     * @param phoneNumberLengthError the phoneNumberLengthError to set
     */
    public void setPhoneNumberLengthError(String phoneNumberLengthError) {
        this.phoneNumberLengthError = phoneNumberLengthError;
    }

    /**
     * @return the sexLengthError
     */
    public String getSexLengthError() {
        return sexLengthError;
    }

    /**
     * @param sexLengthError the sexLengthError to set
     */
    public void setSexLengthError(String sexLengthError) {
        this.sexLengthError = sexLengthError;
    }
}
