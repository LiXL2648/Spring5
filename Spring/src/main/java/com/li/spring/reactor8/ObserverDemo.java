package com.li.spring.reactor8;

import java.util.Observable;

public class ObserverDemo extends Observable {

    public static void main(String[] args) {
        ObserverDemo observer = new ObserverDemo();

        // 添加观察者
        observer.addObserver((o, arg) -> {
            System.out.println("发送了变化");
        });

        observer.addObserver((o, arg) -> {
            System.out.println("手动被观察者通知，准备改变");
        });

        observer.setChanged(); // 监控数据变化
        observer.notifyObservers(); // 通知观察者
    }
}
