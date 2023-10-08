package org.example;

public enum DeliveryType {
    PICKUP ("Самовывоз"),
    COURIER ("Курьер");

    private String title;
    DeliveryType (String title) {
        this.title = title;
    }

    public String getTitle() {
        return title;
    }

}
