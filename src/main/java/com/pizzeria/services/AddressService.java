package com.pizzeria.services;

import com.pizzeria.entity.classes.Address;
import com.pizzeria.entity.classes.User;
import com.pizzeria.entity.interfaces.AddressInterface;
import com.pizzeria.repositories.AddressRepository;
import com.pizzeria.repositories.UserRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class AddressService implements AddressInterface {
    @Autowired
    private final AddressRepository addressRepository;
    @Autowired
    private final UserRepository userRepository;
    @Transactional
    public void addAddress(Long ID, Address a) {
        User u = userRepository.findById(ID).get();
        a.setOwner(u);
        addressRepository.save(a);
    }
    @Transactional
    public List<Address> getAllAddresses() {
        return addressRepository.findAll();
    }
    @Transactional
    public List<Address> getAddressByUserID(Long ID){
        return addressRepository.findUserAddresses(userRepository.findById(ID).get().getID());
    }

    public Address getAddressByID(Long ID) {
        return addressRepository.findById(ID).get();
    }
    public void deleteAddress(Long ID) {
        addressRepository.delete(addressRepository.findById(ID).get());
    }
}
