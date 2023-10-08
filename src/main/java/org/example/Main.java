package org.example;

import java.util.*;

public class Main {
    public static void main(String[] args) {

        List<Goods> goodsList = new ArrayList<Goods>();
        HashMap <String, Order> orderMap = new HashMap<>();

        goodsList.add(new Furniture("Стул деревянный", 300));
        goodsList.add(new Furniture("Стул стальной", 150));
        goodsList.add(new Furniture("Стол деревянный", 600));
        goodsList.add(new Furniture("Стол стальной", 500));
        goodsList.add(new Food("Молоко", 15));
        goodsList.add(new Food("Хлеб", 20, "20.10.2023"));

        Scanner scanner = new Scanner(System.in);

        while (true) {
            printMainMenu();

            String action = scanner.nextLine();

            if (action.equals("end")) {
                break;
            } else {
                switch (action) {
                    case ("0"):
                        System.out.println("Список заказов:");
                        printOrderMap(orderMap);
                        break;
                    case ("1"):
                        goodsList.forEach((good) -> System.out.println(good));
                        break;
                    case ("2"):
                        Order order = new Order(OrderSource.APP);
                        while (true) {
                            System.out.println("\nВыберите товар:");
                            for(int i = 0; i < goodsList.size(); i++) {
                                System.out.println(i + 1 + ". " + goodsList.get(i));
                            }
                            System.out.println("------------------------------------");
                            System.out.println("Если заказ сформирован - введите end");

                            String selection = scanner.nextLine();
                            if (selection.equals("end")) {
                                if (order.isEmpty()) {
                                    order = null;
                                } else {
                                    orderMap.put(order.getOrderId(), order);
                                }
                                break;
                            } else {
                                if (Integer.parseInt(selection) - 1 > goodsList.size()) {
                                    System.out.println("Введен некорректный номер!");
                                } else {
                                    System.out.println("В заказ добавлено:");
                                    System.out.println(goodsList.get(Integer.parseInt(selection) - 1));
                                    order.addItem(goodsList.get(Integer.parseInt(selection) - 1));
                                }
                            }
                        }
                        break;
                    case ("3"):
                        while (true) {
                            System.out.println("Список заказов:");
                            printOrderMap(orderMap);
                            System.out.println("------------------------------------");
                            System.out.println("Введите id заказа для оплаты:");
                            System.out.println("Для отмены действия - введите end");
                            String selection = scanner.nextLine();
                            if (selection.equals("end")) {
                                break;
                            } else {
                                boolean foundOrder = false;
                                for (Map.Entry<String, Order> entry : orderMap.entrySet()) {
                                    String key = entry.getKey();
                                    Order orderElement = entry.getValue();
                                    if (key.equals(selection)) {
                                        System.out.println("Заказ № " + key + " оплачен!");
                                        orderElement.setStatus(OrderStatus.PAYED);
                                        foundOrder = true;
                                        break;
                                    }
                                }
                                if (!foundOrder) {
                                    System.out.println("Заказ не найден, повторите ввод!");
                                } else {
                                    break;
                                }
                            }
                        }
                        break;
                    case ("4"):
                        while (true) {
                            System.out.println("Выберите метод доставки (введите):");
                            for (DeliveryType dType : DeliveryType.values()) {
                                System.out.println(dType.getTitle());
                            }
                            System.out.println("Для отмены действия - введите end");

                            String selection = scanner.nextLine();
                            if (selection.equals("end")) {
                                break;
                            } else {
                                //не знаю как перейти на динамический вызов класса в зависимости от выбранного enum
                                switch (selection) {
                                    case ("Курьер"):
                                        while (true) {
                                            System.out.println("Список заказов:");
                                            printOrderMap(orderMap);
                                            System.out.println("------------------------------------");
                                            System.out.println("Введите id заказа для применения метода доставки:");
                                            System.out.println("Для отмены действия - введите end");

                                            String selection2 = scanner.nextLine();

                                            if (!selection2.equals("end")) {
                                                boolean foundOrder = false;

                                                for (Map.Entry<String, Order> entry : orderMap.entrySet()) {
                                                    String key = entry.getKey();
                                                    Order orderElement = entry.getValue();
                                                    if (key.equals(selection2)) {
                                                        if (orderElement.getOrderStatus() != OrderStatus.PAYED) {
                                                            System.out.println("Выбор способа доставки возможен только для Заявок в статусе " + OrderStatus.PAYED.getTitle());
                                                        } else {
                                                            Delivery delivery = new CourierDelivery();
                                                            delivery.addDelivery(orderElement);
                                                        }
                                                        foundOrder = true;
                                                        break;
                                                    }
                                                }
                                                if (!foundOrder) {
                                                    System.out.println("Заказ не найден, повторите ввод!");
                                                } else {
                                                    break;
                                                }
                                            }
                                        }
                                        break;
                                    case ("Самовывоз"):
                                        while (true) {
                                            System.out.println("Список заказов:");
                                            printOrderMap(orderMap);
                                            System.out.println("------------------------------------");
                                            System.out.println("Введите id заказа для применения метода доставки:");
                                            System.out.println("Для отмены действия - введите end");

                                            String selection2 = scanner.nextLine();

                                            if (!selection2.equals("end")) {
                                            boolean foundOrder = false;

                                            for (Map.Entry<String, Order> entry : orderMap.entrySet()) {
                                                String key = entry.getKey();
                                                Order orderElement = entry.getValue();
                                                if (key.equals(selection2)) {
                                                    if (orderElement.getOrderStatus() != OrderStatus.PAYED) {
                                                        System.out.println("Выбор способа доставки возможен только для Заявок в статусе " + OrderStatus.PAYED.getTitle());
                                                    } else {
                                                        Delivery delivery = new PickupDelivery();
                                                        delivery.addDelivery(orderElement);
                                                    }
                                                    foundOrder = true;
                                                    break;
                                                }
                                            }
                                                if (!foundOrder) {
                                                    System.out.println("Заказ не найден, повторите ввод!");
                                                } else {
                                                    break;
                                                }
                                            }
                                        }
                                        break;
                                    default:
                                        System.out.println("Не найден введенный метод доставки!");
                                        break;
                                }
                            }
                        }
                        break;
                    default:
                        System.out.println("Выбранное действие непонятно. Введите заново");
                        break;
                }
            }
        }
    }

    public static void printMainMenu() {
        System.out.println("\nВыберите действие");
        System.out.println("1 - Посмотреть товары магазина;");
        System.out.println("2 - Сформировать заказ;");
        System.out.println("3 - Оплатить заказ;");
        System.out.println("4 - Выбрать метод доставки заказа;");
        System.out.println("0 - Посмотреть список заказов;");
        System.out.println("end - Завершить работу с магазином.");
    }

    public static void printOrderMap(HashMap <String, Order> orderMap) {
        for (Map.Entry<String, Order> entry : orderMap.entrySet()) {
            Order orderElement = entry.getValue();
            orderElement.printOrder();
        }
    }

}
