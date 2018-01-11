package com.jpa.hms.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
public class Controller {
    @Autowired
    RoomRepository roomRepository;
    @Autowired
    BookingRepository bookingRepository;
    @Autowired
    CustomerRepository customerRepository;
 //   @RequestMapping("/check")
//    public String check(){
//        return "OK";
//    }
//    @RequestMapping("/insertRoom")
//    public List<Room> createRoom(){
//       // roomRepository.save(new Room(true,"staasdasd"));
//       return roomRepository.findRoomsByIsAvail(false);
//        //return "done";
//    }
//
//    @RequestMapping("/check")
//    public String check(){
//        return roomRepository.findFreeRoomByType("staasdasd").toString();
//        //return "Done";
//    }
//    @RequestMapping("/allRooms")
//    public Iterable<Room> checkRoom(){
//        return roomRepository.findAll();
//    }
//    @RequestMapping("/check2")
//    public String changeAvail(){
//        roomRepository.updateIsAvail(10,false);
//        return "Done";
//    }
//    @RequestMapping("/check3")
//    public String findBFR(){
//        return bookingRepository.findBookingByRoomid(10).toString();
//    }
//    @RequestMapping("/check4")
//    public String findasd() {
//        customerRepository.deleteCustomerByBookingid(2);
//        return "done";
//    }
     @RequestMapping(value = "/createRoom", method= RequestMethod.POST)
     public String createRoomByType(@RequestParam(value="type", required = true) String type){
         roomRepository.save(new Room(true,type));
         return "Room created";
     }
    @RequestMapping(value="/deleteRoom/{id}",method=RequestMethod.DELETE)
    public String deleteRoomById(@PathVariable("id") int id){
        Room room = roomRepository.findOne(id);
        //System.out.println(room);
        if((room == null) || !room.getIsAvail()){
            return "Room Still occupied or ID not found";
        }
        roomRepository.delete(id);
        return "Room Deleted";
    }
    @RequestMapping(value="/readRoom",method=RequestMethod.GET)
    public Iterable<Room> read(){
        return roomRepository.findAll();
    }
    @RequestMapping(value="/cancelBooking",method=RequestMethod.POST)
    public String deleteBooking(@RequestParam(value="bookingid",required=true) int bookingid){
        Booking booking = bookingRepository.findOne(bookingid);
        if(booking==null){
            return "Booking not found";
        }
        //customerRepository.deleteCustomerByBookingid(bookingid);
        Room room = roomRepository.findOne(booking.getRoomId());
        if(room==null || room.getIsAvail()){
            return "This booking is inactive";
        }
        roomRepository.updateIsAvail(booking.getRoomId(),true);
        //bookingRepository.delete(bookingid);
        return "Booking Cancelled";
    }
//    @RequestMapping(value="/createBooking",method=RequestMethod.POST)
//    public String createBooking(@RequestParam(value="name",required=true) String name,@RequestParam(value="type",required=true) String type){
//        Room room = roomRepository.findFreeRoomByType(type);
//        if(room==null){
//            return "Room of selected type is not available";
//        }
//        roomRepository.updateIsAvail(room.getId(),false);
//        bookingRepository.save(new Booking(room.getId()));
//        Booking booking = bookingRepository.findBookingByRoomid(room.getId());
//        customerRepository.save(new Customer(name,booking.getId()));
//        Customer customer = customerRepository.findCustomerByBookingid(booking.getId());
//        System.out.println(customer);
//        System.out.println(room);
//        System.out.println(booking);
//        return "Booking Done!  " +
//                "Customer Id: "+customer.getId()+"  Name: "+name+"  BookingId:  "+booking.getId()+"  Room No.: "+room.getId()+
//                "  Type: " + type;
//
//    }
    @RequestMapping(value="/allCustomers",method=RequestMethod.GET)
    public Iterable<Customer> findAllCustomers(){
        return customerRepository.findAll();
    }
    @RequestMapping(value="/allBookings",method=RequestMethod.GET)
    public Iterable<Booking> findAllBookings(){
        return bookingRepository.findAll();
    }
    @RequestMapping(value="/allCustomers/{customerid}",method=RequestMethod.GET)
    public String getCustomerBookingDetail(@PathVariable("customerid") int customerid){
         Customer customer = customerRepository.findOne(customerid);
         if(customer==null){
             return "ID not present";
         }
        return customer.toString();
    }
    @RequestMapping(value="/allBookings/{bookingid}",method=RequestMethod.GET)
    public String getBookingDetail(@PathVariable("bookingid") int bookingid){
        Booking booking = bookingRepository.findOne(bookingid);
        if(booking==null){
            return "ID not present";
        }
        return booking.toString();
    }

//    @RequestMapping(value="/cancelBooking",method=RequestMethod.POST)
//    public String deleteBooking(@RequestBody PostBookingId postBookingId) {
//        Booking booking = bookingRepository.findOne(postBookingId.getBookingId());
//        if(booking==null){
//            return "Booking not found";
//        }
//        customerRepository.deleteCustomerByBookingid(postBookingId.getBookingId());
//        roomRepository.updateIsAvail(booking.getRoomId(),true);
//        bookingRepository.delete(postBookingId.getBookingId());
//        return "Booking Cancelled";
//    }
    @RequestMapping(value="/createBooking",method=RequestMethod.POST)
    public String createBooking(@RequestBody PostNameType postNameType){
        Room room = roomRepository.findFreeRoomByType(postNameType.getType());
        if(room==null){
            return "Room of selected type is not available";
        }
        roomRepository.updateIsAvail(room.getId(),false);
        bookingRepository.save(new Booking(room.getId()));
        Booking booking = bookingRepository.findBookingByRoomid(room.getId());
        customerRepository.save(new Customer(postNameType.getName(),booking.getId()));
        Customer customer = customerRepository.findCustomerByBookingid(booking.getId());
//        System.out.println(customer);
//        System.out.println(room);
//        System.out.println(booking);
        return "Booking Done!  " +
                "Customer Id: "+customer.getId()+"  Name: "+postNameType.getName()+"  BookingId:  "+booking.getId()+"  Room No.: "+room.getId()+
                "  Type: " + postNameType.getType();

    }
    @RequestMapping(value="/activeBookings",method=RequestMethod.GET)
    public List<Booking> getActiveBooking(){
         List<Booking> activeBookings = new ArrayList<Booking>();
         for(Room room:roomRepository.findRoomsByIsAvail(false)){
             activeBookings.add(bookingRepository.findBookingByRoomid(room.getId()));
         }
         return activeBookings;
    }
    @RequestMapping(value="/customerByBookingId/{bookingid}",method=RequestMethod.GET)
    public String findCustomerByBookingId(@PathVariable("bookingid") int bookingid){
         Customer customer = customerRepository.findCustomerByBookingid(bookingid);
         if(customer==null){
             return "No customer found";
         }
         return customer.toString();
    }
}
