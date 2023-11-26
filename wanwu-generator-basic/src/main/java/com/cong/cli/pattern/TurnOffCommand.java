package com.cong.cli.pattern;

/**
 * @author 86188
 * @date 2023/11/26
 */
public class TurnOffCommand implements Command {
    private Device device;

    public TurnOffCommand(Device device) {
        this.device = device;
    }

    public void execute() {
        device.turnOff();
    }
}