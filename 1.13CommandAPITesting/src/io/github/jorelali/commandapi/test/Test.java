package io.github.jorelali.commandapi.test;

import java.util.LinkedHashMap;
import java.util.function.BiConsumer;

import io.github.jorelali.commandapi.api.CommandAPI;
import io.github.jorelali.commandapi.api.arguments.Argument;

public class Test {
	
	String testRequirements;
	BiConsumer<CommandAPI, LinkedHashMap<String, Argument>> register;

	public Test(String testRequirements, BiConsumer<CommandAPI, LinkedHashMap<String, Argument>> register) {
		this.testRequirements = testRequirements;
		this.register = register;
	}	
	
}
