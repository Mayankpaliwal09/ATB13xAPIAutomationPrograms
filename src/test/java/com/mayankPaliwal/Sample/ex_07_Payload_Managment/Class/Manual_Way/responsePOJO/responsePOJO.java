package com.mayankPaliwal.Sample.ex_07_Payload_Managment.Class.Manual_Way.responsePOJO;

import com.mayankPaliwal.Sample.ex_07_Payload_Managment.Class.Manual_Way.requestPOJO.Booking;

public class responsePOJO {
    private Integer bookingid;
    private Booking booking;

    public Booking getBooking() {
        return booking;
    }

    public void setBooking(Booking booking) {
        this.booking = booking;
    }

    public Integer getBookingid() {
        return bookingid;
    }

    public void setBookingid(Integer bookingid) {
        this.bookingid = bookingid;
    }


}
