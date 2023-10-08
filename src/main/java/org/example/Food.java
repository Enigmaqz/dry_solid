package org.example;

public class Food extends Goods implements Spoil{
    private String bestBefore;
    public Food(String name, int price) {
        super(name, price);
        this.bestBefore = "31.12.2023";
    }

    public Food(String name, int price, String goodBefore) {
        super(name, price);
        this.bestBefore = goodBefore;
    }

    @Override
    public String toString() {
        return "Товар: "
                + "Наименование: " + super.getName() + ", "
                + "Стоимость: " + super.getPrice() + ", "
                + "Годен до: " + bestBefore;
    }

    @Override
    public void setBestBefore(String bestBefore) {
        this.bestBefore = bestBefore;
    }
}
