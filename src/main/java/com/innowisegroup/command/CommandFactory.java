package com.innowisegroup.command;

import java.util.Map;

public class CommandFactory {

    private Map<String, Command> commands;

    public CommandFactory(Map<String, Command> commands) {
        this.commands = commands;
    }

    public Command getCommand(String commandName) {
        return commands.getOrDefault(commandName, () -> "No such Command");
    }
}
