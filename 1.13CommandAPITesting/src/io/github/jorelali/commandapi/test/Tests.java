package io.github.jorelali.commandapi.test;

import org.bukkit.Bukkit;

import io.github.jorelali.commandapi.api.arguments.IntegerArgument;

public class Tests {

	public static void output(Object o) {
		Bukkit.broadcastMessage(String.valueOf(o));
	}
	
	public static Test[] getTests() {
		return new Test[] {
				
			new Test("Ranged arguments (unbounded)","unboundedrange", null, (instance, arguments) -> {
				arguments.put("arg", new IntegerArgument());
				instance.register("unboundedrange", arguments, (sender, args) -> {
					output(args[0]);
				});
			}),
			
			new Test("Ranged arguments (arg >= 0)","0range", new String[] {"Accept 0", "Don't accept -1", "Accept 5"}, (instance, arguments) -> {
				arguments.put("arg", new IntegerArgument(0));
				instance.register("0range", arguments, (sender, args) -> {
					output(args[0]);
				});
			}),
			
			new Test("Ranged arguments (0 <= arg <= 100)","0100range", new String[] {"Accept 0", "Accept 100", "Accept 50", "Don't accept -1", "Don't accept 101"}, (instance, arguments) -> {
				arguments.put("arg", new IntegerArgument(0, 100));
				instance.register("0100range", arguments, (sender, args) -> {
					output(args[0]);
				});
			})
		};
	}
	
}
