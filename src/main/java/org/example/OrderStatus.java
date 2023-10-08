package org.example;

public enum OrderStatus {
    CREATED ("Создан"),
    PAYED ("Оплачен"),
    READY ("Готов к передаче в доставку"),
    DELIVERED ("Доставлен"),
    CANCELLED ("Отменен");

    private String title;
    OrderStatus (String title) {
        this.title = title;
    }

    public String getTitle() {
        return title;
    }
}
