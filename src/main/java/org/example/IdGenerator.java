package org.example;

public class IdGenerator {
    private int sourceNum;

    public IdGenerator(OrderSource source) {

        if (source == OrderSource.APP) {
            this.sourceNum = 1;
        } else {
            this.sourceNum = 2;
        }
    }

    public String getId() {
        return String.valueOf(sourceNum) + String.valueOf(System.currentTimeMillis());
    }

}