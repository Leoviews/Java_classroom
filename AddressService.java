package com.example.demo;

import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AddressService {

    private final AddressRepository addressRepo;
    private final AccountRepository service;

    public AddressService(AddressRepository addressRepo, AccountRepository service) {
        this.addressRepo = addressRepo;
        this.service = service;
    }

    // Add or Update Address for Account
    public Address saveAddress(Long accountId, String street,
                               String city, String country, String pincode) {

        Account account = service.findById(accountId)
                .orElseThrow(() -> new RuntimeException("Account not found"));

        Address address = new Address();
        address.setStreet(street);
        address.setCity(city);
        address.setCountry(country);
        address.setPincode(pincode);
        address.setAccount(account);

        return addressRepo.save(address);
    }

    // Get Address by Account ID
    public Address getAddressByAccountId(Long accountId) {
        return addressRepo.findByAccount_AccountId(accountId);
    }

//    public void deleteAddressByAccountId(Long accountId) {
//
//        Optional<Account> address = service.findByAccountId(accountId);
//
//        if (address == null) {
//            throw new RuntimeException("Address not found for account");
//        }
//
//        service.delete(address.getAccount());
//    }
}

