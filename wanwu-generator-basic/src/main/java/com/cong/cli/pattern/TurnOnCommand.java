package com.cong.cli.pattern;

/**
 * 打开命令
 *
 * @author 86188
 * @date 2023/11/26
 */
public class TurnOnCommand implements Command {
    private Device device;

    public TurnOnCommand(Device device) {
        this.device = device;
    }

    public void execute() {
        device.turnOn();
    }
}