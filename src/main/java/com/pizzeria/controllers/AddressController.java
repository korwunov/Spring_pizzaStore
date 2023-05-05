//package com.pizzeria.controllers;
//
//import com.pizzeria.entity.classes.Address;
//import com.pizzeria.services.AddressService;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.http.HttpStatus;
//import org.springframework.stereotype.Controller;
//import org.springframework.web.bind.annotation.*;
//
//import java.util.List;
//
//@Controller
//@RequestMapping(value = "/address")
//public class AddressController {
//    @Autowired
//    public AddressService addressService;
//
//    @PostMapping("/add{ID}")
//    public HttpStatus addUserAddress(@RequestBody Address a, @RequestParam Long ID) {
//        try {
//            addressService.addAddress(ID, a);
//            return HttpStatus.CREATED;
//        }
//        catch(Exception e) {
//            return HttpStatus.BAD_REQUEST;
//        }
//    }
//
//    @GetMapping("/getAll")
//    public List<Address> getAllAddresses() {
//        return addressService.getAllAddresses();
//    }
//
//    @GetMapping("/get{ID}")
//    public Address getAddressByID(@RequestParam Long ID) {
//        return addressService.getAddressByID(ID);
//    }
//
//    @DeleteMapping("/delete{id}")
//    public HttpStatus deleteAddress (@RequestParam Long ID) {
//        try {
//            addressService.deleteAddress(ID);
//            return HttpStatus.OK;
//        }
//        catch(Exception e) {
//            return HttpStatus.NOT_FOUND;
//        }
//    }
//
////    @GetMapping("/getUserAddresses/{ID}")
////    public List<Address> getUserAddresses(@RequestParam Long ID) {
////        return addressService.getAddressByUserID(ID);
////    }
//}
