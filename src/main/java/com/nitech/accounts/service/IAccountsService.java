package com.nitech.accounts.service;

import com.nitech.accounts.dto.CustomerDto;
import com.nitech.accounts.entity.Customer;

public interface IAccountsService {

    /**
     *
     * @param customerDto - CustomerDto Object
     */
    void createAccount(CustomerDto customerDto);

    /**
     *
     * @param mobileNumber - input mobile number
     * @return Account Details based on a given mobile number
     */
    CustomerDto fetchAccount(String mobileNumber);

    /**
     *
     * @param customerDto - CustomerDto Object
     * @return - boolean indicating if the update of account details successful or not
     */
    boolean updateAccount (CustomerDto customerDto);

    /**
     *
     * @param mobileNumber - Intput Mobile Number
     * @return boolean indicatimg if the update of account details successfully or not
     */
    boolean deleteAccount(String mobileNumber);

}
