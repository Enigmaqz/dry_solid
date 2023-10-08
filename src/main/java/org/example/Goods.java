package org.example;

public class Goods {
    private String name;
    private int price;

    public Goods (String name, int price) {
        this.name = name;
        this.price = price;
    }

    public String getName() {
        return this.name;
    }

    public int getPrice() {
        return this.price;
    }

    @Override
    public String toString() {
        return "Товар: "
                + "Наименование: " + name + ", "
                + "Стоимость: " + price;
    }
}
