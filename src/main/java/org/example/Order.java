package org.example;

import java.util.HashMap;
import java.util.Map;

public class Order {

    private OrderStatus status;
    private HashMap<Goods, Integer> itemsList = new HashMap<>();
    private String orderId;
    private int total;
    private DeliveryType deliveryType;

    public Order(OrderSource source) {
        this.orderId = new IdGenerator(source).getId();
        this.status = OrderStatus.CREATED;
        this.total = 0;
    }

    public void setStatus(OrderStatus status) {
        this.status = status;
    }

    public void addItem (Goods item) {
        if (itemsList.isEmpty()) {
            itemsList.put(item, 1);
        } else {
            if (itemsList.containsKey(item)) {
                int currentCount = itemsList.get(item);
                itemsList.put(item, currentCount + 1);
            } else {
                itemsList.put(item, 1);
            }
        }
        total = total + item.getPrice();
    }

    public void removeItem (Goods item) {
        if (itemsList.containsKey(item)) {
            int currentCount = itemsList.get(item);
            if (currentCount == 1) {
                itemsList.remove(item);
            } else {
                itemsList.put(item, currentCount - 1);
            }
            total = total - item.getPrice();
        }
    }

    public void printOrder() {
        int total = 0;
        System.out.println("\nЗаказ № " + this.orderId);
        System.out.println("Статус заказа: " + this.status.getTitle());
        System.out.println("Состав заказа:");
        for (Map.Entry<Goods, Integer> entry : itemsList.entrySet()) {
            String key = entry.getKey().getName();
            Integer value = entry.getValue();
            int price = entry.getKey().getPrice() * entry.getValue();
            System.out.println(key + ", количество: " + value + ", Цена: " + price);
        }
        System.out.println("ИТОГО: " + this.total);
    }

    public void setDeliveryType (DeliveryType deliveryType) {
        this.deliveryType = deliveryType;
    }

    public String getOrderId () {
        return this.orderId;
    }

    public OrderStatus getOrderStatus () {
        return this.status;
    }

    public boolean isEmpty () {
        return itemsList.isEmpty();
    }
}
