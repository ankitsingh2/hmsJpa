package com.jpa.hms.demo;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;

@Repository
public interface CustomerRepository extends CrudRepository<Customer,Integer>{
    Customer findCustomerByBookingid(int bookingid);
    @Transactional
    void deleteCustomerByBookingid(int bookingid);
}
