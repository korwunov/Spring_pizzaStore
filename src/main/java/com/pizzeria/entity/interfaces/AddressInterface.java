package com.pizzeria.entity.interfaces;

import com.pizzeria.entity.classes.Address;

import java.util.List;

public interface AddressInterface {
    void addAddress(Long ID, Address a);
    List<Address> getAllAddresses();
    List<Address> getAddressByUserID(Long ID);
    Address getAddressByID(Long ID);
    void deleteAddress(Long ID);
}
