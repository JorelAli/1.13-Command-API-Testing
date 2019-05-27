package io.github.jorelali.commandapi.test;

public class Tests {

	public static Test[] getTests() {
		return new Test[] {
				
			new Test("hi", (instance, arguments) -> {
				instance.register("", arguments, (sender, args) -> {
					
				});
			})				
		};
	}
	
}
