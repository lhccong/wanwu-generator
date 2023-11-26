package com.cong.cli.pattern;

/**
 * 遥控
 *
 * @author 86188
 * @date 2023/11/26
 */
public class RemoteControl {
    private Command command;

    public void setCommand(Command command) {
        this.command = command;
    }

    public void pressButton() {
        command.execute();
    }
}