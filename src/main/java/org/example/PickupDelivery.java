package org.example;

public class PickupDelivery implements Delivery{
    @Override
    public void addDelivery(Order order) {
        order.setDeliveryType(DeliveryType.PICKUP);
        order.setStatus(OrderStatus.READY);
        System.out.println("Для заказа " + order.getOrderId() + " выбрана доставка самовывозом!");
    }
}
