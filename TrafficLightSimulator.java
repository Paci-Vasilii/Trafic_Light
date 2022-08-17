package com.company;

public class TrafficLightSimulator implements Runnable {
    private Thread thrd;            // поток для имитации светофора
    private TrafficLightColor tlc;  // текущий цвет светофора
    boolean stop = false;           // для остановки имитации установить в true
    boolean changed = false;        // true, если светофор переключился

    TrafficLightSimulator(TrafficLightColor init) {
        tlc = init;

        thrd = new Thread(this);
        thrd.start();
    }

    TrafficLightSimulator() {
        tlc = TrafficLightColor.RED;

        thrd = new Thread(this);
        thrd.start();
    }

    //  Запуск имитации автоматизированного светофора
    public void run() {
        while(!stop) {
            try {
                switch (tlc) {
                    case GREEN:
                        Thread.sleep(TrafficLightColor.GREEN.getDelay()); // зеленый на 10 секунд
                        break;
                    case YELLOW:
                        Thread.sleep(TrafficLightColor.YELLOW.getDelay()); // желтый на 2 секунды
                        break;
                    case RED:
                        Thread.sleep(TrafficLightColor.RED.getDelay()); // красный на 12 секунд
                        break;
                }
            }
            catch (InterruptedException exc) {
                System.out.println(exc);
            }
            changeColor();
        }
    }

    // Переключение цвета сфетофора

    synchronized void changeColor() {
        switch (tlc) {
            case RED:
                tlc = TrafficLightColor.GREEN;
                break;
            case YELLOW:
                tlc = TrafficLightColor.RED;
                break;
            case GREEN:
                tlc = TrafficLightColor.YELLOW;
        }

        changed = true;
        notify(); // уведомить о переключении светофора
    }

    synchronized void waitForChange() {
        try {
            while (!changed)
                wait(); // Ожидать переключение цвета светофора
            changed = false;
        }
        catch (InterruptedException exc) {
            System.out.println(exc);
        }
    }

    // Возврат текущего цвета
    TrafficLightColor getColor() {
        return tlc;
    }

    //Прекращение имитации светофора
    void cancel() {
        stop = true;
    }

}
