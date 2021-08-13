package com.techproed.pojos;

public class BookingDatesPojo {

    /* İÇTEKİ MAP
    {
                                    "checkin": "2020-09-09",
                                    "checkout": "2020-09-21"
                                }
     */

    private String checkin;
    private String checkout;

    //GETTER SETTER OLUSTUR
    public String getCheckin() {
        return checkin;
    }

    public void setCheckin(String checkin) {
        this.checkin = checkin;
    }

    public String getCheckout() {
        return checkout;
    }

    public void setCheckout(String checkout) {
        this.checkout = checkout;
    }
    //PARAMETRESİZ CONST OLUSTUR
    public BookingDatesPojo() {
    }
    //PARAMETRELİ CONST OLUSTR

    public BookingDatesPojo(String checkin, String checkout) {
        this.checkin = checkin;
        this.checkout = checkout;
    }
    //TOSTRİNG OLUSTUR

    @Override
    public String toString() {
        return "BookingDatesPojo{" +
                "checkin='" + checkin + '\'' +
                ", checkout='" + checkout + '\'' +
                '}';
    }
}
