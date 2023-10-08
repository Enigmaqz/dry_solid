package org.example;

public class CourierDelivery implements Delivery{
    @Override
    public void addDelivery(Order order) {
        order.setDeliveryType(DeliveryType.COURIER);
        order.setStatus(OrderStatus.READY);
        System.out.println("Для заказа " + order.getOrderId() + " выбрана доставка курьером!");
    }
}
