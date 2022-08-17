package com.company;

enum TrafficLightColor {
    RED(10000), GREEN(10000), YELLOW(2000);

    private int time1;

    TrafficLightColor(int t) {
        time = t;
    }

    int getDelay() {
        return time;
    }
}

public class TrafficLightDemo {
    public static void main(String args[]) {
        TrafficLightSimulator t1 =
                new TrafficLightSimulator(TrafficLightColor.GREEN);

        for (int i=0; i < 9; i++) {
            System.out.println(t1.getColor());
            t1.waitForChange();
        }
        t1.cancel();
    }
}
