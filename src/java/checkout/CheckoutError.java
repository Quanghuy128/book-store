/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package checkout;

import java.io.Serializable;

/**
 *
 * @author huy
 */
public class CheckoutError implements Serializable{
    private String custNameIsNullError;
    private String addressIsNullError;
    private String phoneNumberIsNullError;

    /**
     * @return the custNameIsNullError
     */
    public String getCustNameIsNullError() {
        return custNameIsNullError;
    }

    /**
     * @param custNameIsNullError the custNameIsNullError to set
     */
    public void setCustNameIsNullError(String custNameIsNullError) {
        this.custNameIsNullError = custNameIsNullError;
    }

    /**
     * @return the addressIsNullError
     */
    public String getAddressIsNullError() {
        return addressIsNullError;
    }

    /**
     * @param addressIsNullError the addressIsNullError to set
     */
    public void setAddressIsNullError(String addressIsNullError) {
        this.addressIsNullError = addressIsNullError;
    }

    /**
     * @return the phoneNumberIsNullError
     */
    public String getPhoneNumberIsNullError() {
        return phoneNumberIsNullError;
    }

    /**
     * @param phoneNumberIsNullError the phoneNumberIsNullError to set
     */
    public void setPhoneNumberIsNullError(String phoneNumberIsNullError) {
        this.phoneNumberIsNullError = phoneNumberIsNullError;
    }
    
}
