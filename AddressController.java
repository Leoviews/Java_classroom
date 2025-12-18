package com.example.demo;

import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/address")
public class AddressController {

    private final AddressService service;

    public AddressController(AddressService service) {
        this.service = service;
    }

    @PostMapping("/{accountId}")
    public Address addAddress(
            @PathVariable Long accountId,
            @RequestParam String street,
            @RequestParam String city,
            @RequestParam String country,
            @RequestParam String pincode) {

        return service.saveAddress(accountId, street, city, country, pincode);
    }

    @GetMapping("/{accountId}")
    public Address getAddress(@PathVariable Long accountId) {
        return service.getAddressByAccountId(accountId);
    }
}

