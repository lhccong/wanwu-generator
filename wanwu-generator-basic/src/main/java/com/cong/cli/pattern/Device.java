package com.cong.cli.pattern;

/**
 * 装置
 *
 * @author 86188
 * @date 2023/11/26
 */
public class Device {
    private String name;

    public Device(String name) {
        this.name = name;
    }

    public void turnOn() {
        System.out.println(name + " 设备打开");
    }

    public void turnOff() {
        System.out.println(name + " 设备关闭");
    }
}