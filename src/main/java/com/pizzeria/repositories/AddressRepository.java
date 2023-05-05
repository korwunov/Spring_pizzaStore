//package com.pizzeria.repositories;
//
//import com.pizzeria.entity.classes.Address;
//import org.springframework.data.jpa.repository.JpaRepository;
//import org.springframework.data.jpa.repository.Query;
//import org.springframework.stereotype.Component;
//import org.springframework.stereotype.Repository;
//
//import java.util.List;
//@Repository
//@Component
//public interface AddressRepository extends JpaRepository<Address, Long> {
//    List<Address> findAllByCity(String city);
//    @Query(value = "select addresses.* from addresses" +
//            "join users on users.id = addresses.owner" +
//            "where users.id = :ID", nativeQuery = true)
//    List<Address> findUserAddresses(Long ID);
//}
