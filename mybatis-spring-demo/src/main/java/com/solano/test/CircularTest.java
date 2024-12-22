package com.solano.test;

public class CircularTest {

    /**
     * 会导致 StackOverflowError
     */
    public static void main(String[] args) {
        new CircularTest1();
    }
}

class CircularTest1 {
    private CircularTest2 circularTest2;

    public CircularTest1() {
        this.circularTest2 = new CircularTest2();
    }
}

class CircularTest2 {
    private CircularTest1 circularTest1 = new CircularTest1();

    public CircularTest2() {
        this.circularTest1 = new CircularTest1();
    }
}
