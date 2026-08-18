package com.nitech.accounts.service.impl;

import com.nitech.accounts.constants.AccountsConstants;
import com.nitech.accounts.dto.AccountsDto;
import com.nitech.accounts.dto.CustomerDto;
import com.nitech.accounts.entity.Accounts;
import com.nitech.accounts.entity.Customer;
import com.nitech.accounts.exception.CustomerAlreadyExistsException;
import com.nitech.accounts.exception.ResourceNotFoundException;
import com.nitech.accounts.mapper.AccountsMapper;
import com.nitech.accounts.mapper.CustomerMapper;
import com.nitech.accounts.repository.AccountsRepository;
import com.nitech.accounts.repository.CustomerRepository;
import com.nitech.accounts.service.IAccountsService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;
import java.util.Random;

@Service
@AllArgsConstructor
public class AccountsServiceImpl implements IAccountsService {

    private AccountsRepository accountsRepository;
    private CustomerRepository customerRepository;

    /**
     * @param customerDto - CustomerDto Object
     */
    @Override
    public void createAccount(CustomerDto customerDto) {
        Customer customer= CustomerMapper.mapToCustomer(customerDto, new Customer());
        Optional<Customer>optionalCustomer = customerRepository.findByMobileNumber(customerDto.getMobileNumber());
        if(optionalCustomer.isPresent()){
            throw new CustomerAlreadyExistsException("Customer already registered with given mobile number"+customerDto.getMobileNumber());
        }
        customer.setCreatedAt(LocalDateTime.now());
        customer.setCreatedBy("Anonymous");
  //    customer.setCreatedAt(LocalDateTime.now());
  //    customer.setCreatedBy("Anonymous");
        // replace this by using auditor
        Customer savedCustomer= customerRepository.save(customer);
        accountsRepository.save(createNewAccount(savedCustomer));
    }


    /**
     *
     * @param customer - Customer Object
     * @return the new account details
     */

    private Accounts createNewAccount(Customer customer){
        Accounts newAccount = new Accounts();
        newAccount.setCustomerId(customer.getCustomerId());
        long randomAccNumber = 1000000000L+new Random().nextInt(900000000);

        newAccount.setAccountNumber(randomAccNumber);
        newAccount.setAccountType(AccountsConstants.SAVING);
        newAccount.setBranchAddress(AccountsConstants.ADDRESS);

        // newAccount.setCreatedAt(LocalDateTime.now());
        // newAccount.setCreatedBy("Anonymous");
        //replace this by using auditor
        return newAccount;
    }

    /**
     * @param mobileNumber - input mobile number
     * @return Account Details based on a given mobile number
     */
    @Override
    public CustomerDto fetchAccount(String mobileNumber) {
        Customer customer = customerRepository.findByMobileNumber(mobileNumber).orElseThrow(
                ()->new ResourceNotFoundException("Customer","mobileNumber",mobileNumber)
        );

        Accounts accounts = accountsRepository.findByCustomerId(customer.getCustomerId()).orElseThrow(
                ()->new ResourceNotFoundException("Account","customerId",customer.getCustomerId().toString())
        );

        CustomerDto customerDto = CustomerMapper.mapToCustomerDto(customer,new CustomerDto());
        customerDto.setAccountsDto(AccountsMapper.mapToAccountsDto(accounts,new AccountsDto()));
        return customerDto;
    }

    @Override
    public boolean updateAccount(CustomerDto customerDto) {
        boolean isUpdated=false;
        AccountsDto accountsDto=customerDto.getAccountsDto();
        if (accountsDto!=null){
            Accounts accounts=accountsRepository.findById(accountsDto.getAccountNumber()).orElseThrow(
                    ()->new ResourceNotFoundException("Account","AccountNumber",accountsDto.getAccountNumber().toString())
            );
            AccountsMapper.mapToAccounts(accountsDto,accounts);
            accounts=accountsRepository.save(accounts);

            Long customerId=accounts.getCustomerId();
            Customer customer=customerRepository.findById(customerId).orElseThrow(
                    ()->new ResourceNotFoundException("Customer","CustomerId",customerId.toString())
            );
            CustomerMapper.mapToCustomer(customerDto,customer);
            customer=customerRepository.save(customer);
            isUpdated=true;
        }
        return isUpdated;
    }

    /**
     * @param mobileNumber - Intput Mobile Number
     * @return boolean indicatimg if the update of account details successfully or not
     */
    @Override
    public boolean deleteAccount(String mobileNumber) {
        Customer customer=customerRepository.findByMobileNumber(mobileNumber).orElseThrow(
                ()->new ResourceNotFoundException("Customer","mobileNumber",mobileNumber)
        );
        accountsRepository.deleteByCustomerId(customer.getCustomerId());
        customerRepository.deleteById(customer.getCustomerId());
    return true;
    }


}
