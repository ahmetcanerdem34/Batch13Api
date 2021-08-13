package com.techproed.pojos;

public class ActualBookingPojo {

    private int bookingid;
    private BookingPojo booking;

    //setter getter olustur

    public int getBookingid() {
        return bookingid;
    }

    public void setBookingid(int bookingid) {
        this.bookingid = bookingid;
    }

    public BookingPojo getBooking() {
        return booking;
    }

    public void setBooking(BookingPojo booking) {
        this.booking = booking;
    }
    //parametresiz const olustur

    public ActualBookingPojo() {

    }

    //parametreli const olustur

    public ActualBookingPojo(int bookingid, BookingPojo booking) {
        this.bookingid = bookingid;
        this.booking = booking;
    }
    //toString olustur

    @Override
    public String toString() {
        return "ActualBookingPojo{" +
                "bookingid=" + bookingid +
                ", booking=" + booking +
                '}';
    }
}
