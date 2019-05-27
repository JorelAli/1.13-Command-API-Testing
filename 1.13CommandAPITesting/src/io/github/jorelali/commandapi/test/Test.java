package io.github.jorelali.commandapi.test;

import java.util.LinkedHashMap;
import java.util.function.BiConsumer;

import io.github.jorelali.commandapi.api.CommandAPI;
import io.github.jorelali.commandapi.api.arguments.Argument;

public class Test {
	
	String title;
	String command;
	String[] acceptingCases;
	BiConsumer<CommandAPI, LinkedHashMap<String, Argument>> register;

	public Test(String title, String command, String[] acceptingCases, BiConsumer<CommandAPI, LinkedHashMap<String, Argument>> register) {
		this.title = title;
		this.command = command;
		this.acceptingCases = acceptingCases;
		this.register = register;
	}	
	
}
