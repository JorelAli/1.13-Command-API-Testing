package io.github.jorelali.commandapi.test;

import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.List;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.event.Listener;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scoreboard.Objective;
import org.bukkit.scoreboard.Scoreboard;

import io.github.jorelali.commandapi.api.CommandAPI;
import io.github.jorelali.commandapi.api.arguments.Argument;
import io.github.jorelali.commandapi.api.arguments.CustomArgument;
import io.github.jorelali.commandapi.api.arguments.CustomArgument.MessageBuilder;
import io.github.jorelali.commandapi.api.arguments.GreedyStringArgument;
import io.github.jorelali.commandapi.api.arguments.LiteralArgument;

public class Main extends JavaPlugin implements Listener {

	static List<String> arr;

	@Override
	public void onLoad() {
		

		tests = new LinkedHashMap<>();
		Arrays.stream(Tests.getTests()).forEach(t -> tests.put(t, Status.PENDING));
		
		tests.forEach((t, status) -> {
			t.register.accept(CommandAPI.getInstance(), new LinkedHashMap<>());
		});
		
		LinkedHashMap<String, Argument> arguments = new LinkedHashMap<>();
		arguments.put("option", new LiteralArgument("list"));
		CommandAPI.getInstance().register("tests", arguments, (sender, args) -> {
			sender.sendMessage("--- Tests ----------");
			tests.forEach((t, status) -> {
				ChatColor c = null;
				switch(status) {
					case FAILED:
						c = ChatColor.RED;
						break;
					case PASSED:
						c = ChatColor.GREEN;
						break;
					case PENDING:
						c = ChatColor.GRAY;
						break;
				}
				
//				sender.sendMessage(c + " " + t.description);
				sender.sendMessage("--------------------------");
			});
		});
		
		arguments.clear();
		arguments.put("command", new GreedyStringArgument());
		CommandAPI.getInstance().register("run", arguments, (sender, args) -> {
			Bukkit.dispatchCommand(sender, (String) args[0]);
		});
	}
	
	public CustomArgument<Objective> getObjectiveCustomArgWithDefaultException() {
		Scoreboard scoreboard = Bukkit.getScoreboardManager().getMainScoreboard();
		return new CustomArgument<Objective>((input) -> {
			throw new RuntimeException();
			//return scoreboard.getObjective(input);
		}).overrideSuggestions(scoreboard.getObjectives().stream().map(o -> o.getName()).toArray(String[]::new));
	}
	
	public CustomArgument<Objective> getObjectiveCustomArg() {
		Scoreboard scoreboard = Bukkit.getScoreboardManager().getMainScoreboard();
		return new CustomArgument<Objective>((input) -> {
			return scoreboard.getObjective(input);
		}).overrideSuggestions(scoreboard.getObjectives().stream().map(o -> o.getName()).toArray(String[]::new));
	}
	
	public CustomArgument<Objective> getObjectiveCustomArgWithFail() {
		Scoreboard scoreboard = Bukkit.getScoreboardManager().getMainScoreboard();
		return new CustomArgument<Objective>((input) -> {
			CustomArgument.throwError(new MessageBuilder().appendArgInput().appendHere());
			return scoreboard.getObjective(input);
		}).overrideSuggestions(scoreboard.getObjectives().stream().map(o -> o.getName()).toArray(String[]::new));
	}
	
	static enum Status {
		FAILED, PENDING, PASSED;
	}

	static LinkedHashMap<Test, Status> tests;
	
	@Override
	public void onEnable() {
		
		
		
		
		
		
		
		
		
		
		
		
		//Tests:
		/*
		 * - CustArgs
		 * - SugStrs
		 */
		
		//MinecraftServer.getServer().getLootTableRegistry().e;
		//LootTableRegistry.a;
//		MinecraftKey ka;
//		CraftLootTable a = new CraftLootTable(new NamespacedKey(ka.b(), ka.getKey()), MinecraftServer.getServer().getLootTableRegistry().getLootTable(null));
		
		
//		arguments.put("sugstr", new SuggestedStringArgument("hello", "world"));
//		CommandAPI.getInstance().register("suggest", arguments, (sender, args) -> {
//			sender.sendMessage((String) args[0]);
//		});
//		
//		arguments.clear();
//		arguments.put("newstr", new StringArgument().overrideSuggestions("hello", "world"));
//		CommandAPI.getInstance().register("suggest2", arguments, (sender, args) -> {
//			sender.sendMessage((String) args[0]);
//		});
//		
//		arguments.clear();
//		arguments.put("cust", getObjectiveCustomArgWithDefaultException());
//		CommandAPI.getInstance().register("cust", arguments, (sender, args) -> {
//			sender.sendMessage(((Objective) args[0]).getName());
//		});
//		
//		arguments.clear();
//		arguments.put("cust", getObjectiveCustomArg());
//		CommandAPI.getInstance().register("cust1", arguments, (sender, args) -> {
//			sender.sendMessage(((Objective) args[0]).getName());
//		});
//		
//		arguments.clear();
//		arguments.put("cust", getObjectiveCustomArgWithFail());
//		CommandAPI.getInstance().register("cust2", arguments, (sender, args) -> {
//			sender.sendMessage(((Objective) args[0]).getName());
//		});
		
//		arguments.put("target", new PlayerArgument());
//		CommandAPI.getInstance().register("mycmd", arguments, (sender, args) -> {
//			System.out.println("Invoked executor");
//			Player target = (Player) args[0];
//			if(target == null) {
//				sender.sendMessage("invalid target");
//			} else {
//				sender.sendMessage("targetName = " + target.getName());
//			}
//		});
//		
//		arguments.clear();
//		arguments.put("block", new LocationArgument(LocationType.BLOCK_POSITION));
//		CommandAPI.getInstance().register("break", arguments, (sender, args) -> {
//			((Location) args[0]).getBlock().setType(Material.AIR);
//		});
//		
//		arguments.clear();
//		arguments.put("pos", new LocationArgument(LocationType.PRECISE_POSITION));
//		CommandAPI.getInstance().register("getpos", arguments, (sender, args) -> {
//			sender.sendMessage(((Location) args[0]).toString());
//		});
		
		
//		CommandAPI.getInstance().register("fly", arguments, (sender, args) -> {
//			if (sender instanceof Player) {
//				((Player) sender).setFlying(true);
//			}
//		});

//		CommandAPI.getInstance().register("", null, (sender, args) -> {
//		});

		// Test ChatComponentArgument
//		arguments.clear();
//		arguments.put("rawText", new ChatComponentArgument());
//		CommandAPI.getInstance().register("raw", arguments, (sender, args) -> {
//			sender.sendMessage("a");
//			// if (sender instanceof Player) {
//			// Player player = (Player) sender;
//			// BaseComponent[] arr = (BaseComponent[]) args[0];
//			// player.spigot().sendMessage(arr);
//			// }
//		});

		// Tests ChatComponentArgument compatibility with books
//		arguments.clear();
//		arguments.put("contents", new ChatComponentArgument());
//		CommandAPI.getInstance().register("tobook", arguments, (sender, args) -> {
//			if (sender instanceof Player) {
//				Player player = (Player) sender;
//				BaseComponent[] arr = (BaseComponent[]) args[0];
//
//				ItemStack is = new ItemStack(Material.WRITTEN_BOOK);
//				BookMeta meta = (BookMeta) is.getItemMeta();
//				meta.spigot().addPage(arr);
//				is.setItemMeta(meta);
//
//				player.getInventory().addItem(is);
//			}
//		});

		// Test gamemode command literals
//		HashMap<String, GameMode> gamemodes = new HashMap<>();
//		gamemodes.put("adventure", GameMode.ADVENTURE);
//		gamemodes.put("creative", GameMode.CREATIVE);
//		gamemodes.put("spectator", GameMode.SPECTATOR);
//		gamemodes.put("survival", GameMode.SURVIVAL);
//
//		for (String key : gamemodes.keySet()) {
//			LinkedHashMap<String, Argument> myArgs = new LinkedHashMap<>();
//			myArgs.put(key, new LiteralArgument(key));
//			CommandAPI.getInstance().register("gamemode", new String[] { "gm" }, myArgs, (sender, args) -> {
//				if (sender instanceof Player) {
//					Player player = (Player) sender;
//					player.setGameMode(gamemodes.get(key));
//				}
//			});
//		}
//
//		arguments.clear();
//		arguments.put("id", new IntegerArgument(0, 3));
//		CommandAPI.getInstance().register("gamemode", new String[] { "gm" }, arguments, (sender, args) -> {
//			if (sender instanceof Player) {
//				Player player = (Player) sender;
//				GameMode targetGM = null;
//				switch ((int) args[0]) {
//					default:
//					case 0:
//						targetGM = GameMode.SURVIVAL;
//						break;
//					case 1:
//						targetGM = GameMode.CREATIVE;
//						break;
//					case 2:
//						targetGM = GameMode.ADVENTURE;
//						break;
//					case 3:
//						targetGM = GameMode.SPECTATOR;
//						break;
//				}
//				player.setGameMode(targetGM);
//			}
//		});
//
//		// Tests SuggestedStringArguments
//		arguments.clear();
//		List<String> strList = Arrays.stream(Material.values()).map(element -> element.name())
//				.collect(Collectors.toList());
//		arguments.put("test", new SuggestedStringArgument(strList));
//		CommandAPI.getInstance().register("suggest", arguments, (sender, args) -> {
//			if (sender instanceof Player) {
//				Player player = (Player) sender;
//				player.sendMessage((String) args[0]);
//			}
//		});
//
//		// Tests target entities
//		arguments.clear();
//		arguments.put("target", new EntitySelectorArgument(EntitySelector.MANY_ENTITIES));
//		CommandAPI.getInstance().register("aaa", arguments, (sender, args) -> {
//			System.out.println(args[0]);
//		});
//
//		// Tests target entities
//		arguments.clear();
//		arguments.put("target", new EntitySelectorArgument(EntitySelector.MANY_PLAYERS));
//		arguments.put("b", new TextArgument());
//		CommandAPI.getInstance().register("aaa", arguments, (sender, args) -> {
//			System.out.println(args[0]);
//		});
//
//		// Tests target entities
//		arguments.clear();
//		arguments.put("target", new EntitySelectorArgument(EntitySelector.ONE_PLAYER));
//		CommandAPI.getInstance().register("oneplayer", arguments, (sender, args) -> {
//			System.out.println(args[0]);
//		});
//
//		arguments.clear();
//		arguments.put("target", new EntitySelectorArgument(EntitySelector.ONE_ENTITY));
//		CommandAPI.getInstance().register("oneentity", arguments, (sender, args) -> {
//			System.out.println(args[0]);
//		});
//
//		arguments.clear();
//		arguments.put("yes", new FunctionArgument());
//		CommandAPI.getInstance().register("run", arguments, (sender, args) -> {
//			FunctionWrapper[] func = (FunctionWrapper[]) args[0];
//
//			for (FunctionWrapper function : func) {
//				function.run();
//			}
//
//			System.out.println(func.length + " functions found");
//			func[0].run();
//		});
//		// arguments.put("target", new
//		// EntitySelectorArgument(EntitySelector.ONE_ENTITY));
//		// CommandAPI.getInstance().register("run", arguments, (sender, args) ->
//		// {
//		// FunctionWrapper[] func = ((FunctionWrapper[]) args[0])[0];
//		// func.runAs((Entity) args[1]);
//		// });
//
//		// TODO: Test tags (groups of functions)
//		// Test goes here
//
//		arguments.clear();
//		arguments.put("loc", new LocationArgument());
//		CommandAPI.getInstance().register("myloc", arguments, (sender, args) -> {
//			Location loc = (Location) args[0];
//			sender.sendMessage(
//					"Location argument: (" + loc.getBlockX() + " " + loc.getBlockY() + " " + loc.getBlockZ() + ")");
//
//			Location currentLoc = ((Player) sender).getLocation();
//			sender.sendMessage("Current location: (" + currentLoc.getBlockX() + " " + currentLoc.getBlockY() + " "
//					+ currentLoc.getBlockZ() + ")");
//			sender.sendMessage(" ");
//		});
//
//		////////////////////////////////////////////////////////////////////////////////////////////
//		// Converter Test
//
//		System.out.println("Converting BareEssentials");
//
//		Converter.convert(Bukkit.getPluginManager().getPlugin("bE"));
//
//		////////////////////////////////////////////////////////////////////////////////////////////
//
//		arguments.clear();
//		arguments.put("motion", new LocationArgument());
//
//		CommandAPI.getInstance().register("motion", arguments, (sender, args) -> {
//			CommandSender callee = sender;
//
//			// when using execute, differentiate sender and caller
//			if (sender instanceof ProxiedCommandSender) {
//				callee = ((ProxiedCommandSender) sender).getCallee();
//				sender = ((ProxiedCommandSender) sender).getCaller();
//			}
//
//			if (callee instanceof Entity) {
//				Entity entity = (Entity) callee;
//				Location loc1 = (Location) args[0];
//				Location loc2 = entity.getLocation();
//				sender.sendMessage("Location argument: (" + loc1.getBlockX() + " " + loc1.getBlockY() + " "
//						+ loc1.getBlockZ() + ")");
//				sender.sendMessage("Current location: (" + loc2.getBlockX() + " " + loc2.getBlockY() + " "
//						+ loc2.getBlockZ() + ")");
//			}
//		});
//
//		Bukkit.getScheduler().scheduleSyncDelayedTask(this, () -> {
//			System.out.println("registering mycmd1");
//			arguments.clear();
//			CommandAPI.getInstance().register("mycmd1", arguments, (sender, args) -> {
//				System.out.println("yay");
//			});
//		}, 20L);
//
//		CommandAPI.getInstance().unregister("gamemode", true);
//
//		arguments.clear();
//		CommandAPI.getInstance().register("broadcastmsg", arguments, (sender, args) -> {
//			Bukkit.broadcastMessage("hello");
//		});
//
//		arguments.clear();
//		arguments.put("element", new StringArgument());
//		CommandAPI.getInstance().register("addToArr", arguments, (sender, args) -> {
//			arr.add((String) args[0]);
//			System.out.println("Added " + args[0] + " to list");
//		});
//
//		arguments.clear();
//
//		DynamicSuggestedStringArgument arg = new DynamicSuggestedStringArgument(() -> {
//			return arr.toArray(new String[arr.size()]);
//		});
//
//		arguments.put("suggested", arg);
//		CommandAPI.getInstance().register("dynsuggest", arguments, (sender, args) -> {
//			System.out.println((String) args[0]);
//		});
//
//		// Bukkit.getScheduler().scheduleSyncDelayedTask(this, () -> {
//		// System.out.println("unregisteringgamemode1");
//		//
//		// //Test command unregistration
//		// CommandAPI.getInstance().unregister("gamemode", true);
//		// }, 20L);
//
//		arguments.clear();
//		CommandAPI.getInstance().register("returnCrash", arguments, (sender, args) -> {
//			CommandAPI.fail("blah");
//			return 15;
//		});
//
//		arguments.clear();
//		arguments.put("intValue", new IntegerArgument());
//		CommandAPI.getInstance().register("returnInt", arguments, (sender, args) -> {
//			return (int) args[0];
//		});
//
//		arguments.clear();
//		CommandAPI.getInstance().register("permtest", CommandPermission.fromString("myperm"), arguments,
//				(sender, args) -> {
//					System.out.println("yay");
//				});
//
//		// LinkedHashMap<String, Argument> arguments = new LinkedHashMap<>();
//		arguments.clear();
//		CommandAPI.getInstance().register("library", CommandPermission.fromString("booklibrary.command.library"),
//				arguments, (sender, args) -> {
//					if (sender instanceof Player) {
//						((Player) sender).setInvulnerable(true);
//					}
//					System.out.println("Lib");
//				});
//
//		arguments.put("get", new LiteralArgument("get"));
//		arguments.put("book_id", new IntegerArgument());
//
//		CommandAPI.getInstance().register("library", CommandPermission.fromString("booklibrary.command.get"), arguments,
//				(sender, args) -> {
//					System.out.println("Lib2");
//				});
//
//		arguments.clear();
//		CommandAPI.getInstance().register("dupperms", CommandPermission.fromString("custpermdup"), arguments,
//				(sender, args) -> {
//					System.out.println("dupperms");
//				});
//
//		arguments.put("blah", new IntegerArgument());
//		CommandAPI.getInstance().register("dupperms", CommandPermission.fromString("custpermdup"), arguments,
//				(sender, args) -> {
//					System.out.println("dupperms2");
//				});
//
//		arguments.clear();
//
//		// Command with expected success: 1, result: 0
//		CommandAPI.getInstance().register("default", arguments, (sender, args) -> {
//			Bukkit.broadcastMessage("default");
//		});
//
//		// Command with expected success: 1, result: 15
//		CommandAPI.getInstance().register("withResult", arguments, (sender, args) -> {
//			Bukkit.broadcastMessage("withResult");
//			return 15;
//		});
//
//		// Command with expected success: 0, result: 0
//		CommandAPI.getInstance().register("defaultCrash", arguments, (sender, args) -> {
//			Bukkit.broadcastMessage("default");
//			CommandAPI.fail("default crash");
//		});
//
//		// Command with expected success: 0, result: 0
//		CommandAPI.getInstance().register("withResultCrash", arguments, (sender, args) -> {
//			Bukkit.broadcastMessage("withResultCrash");
//			CommandAPI.fail("with result crash");
//			return 15;
//		});
//
//		arguments.clear();
//
//		// User requires the "custompermission" permission to run this command
//		CommandAPI.getInstance().register("permissiontest", CommandPermission.fromString("custompermission"), arguments,
//				(sender, args) -> {
//					sender.sendMessage("test1 success!");
//				});
//
//		arguments.put("values", new SuggestedStringArgument("hello", "world"));
//		// User MUST have "custompermission2" in order to /SEE/ this command,
//		// regardless if they have "custompermission"
//		CommandAPI.getInstance().register("permissiontest", CommandPermission.fromString("custompermission2"),
//				arguments, (sender, args) -> {
//					sender.sendMessage("test2 success!");
//				});
//
//		arguments.put("moreVals", new SuggestedStringArgument("foo", "bar", "baz"));
//		// User MUST have "custompermission2" in order to /SEE/ this command,
//		// regardless if they have "custompermission"
//		CommandAPI.getInstance().register("permissiontest", CommandPermission.fromString("custompermission3"),
//				arguments, (sender, args) -> {
//					sender.sendMessage("test3 success!");
//				});
//
//		// arguments.put("test", new DynArg("hello", "world", "how", "are",
//		// "you"));
//		// CommandAPI.getInstance().register("ultimatecmd", arguments, (sender,
//		// args) -> {
//		// System.out.println("WOO");
//		// });
//
//		arguments.clear();
//		arguments.put("guildname", new TextArgument());
//		arguments.put("guildIDnumber", new IntegerArgument(1, 27));
//		arguments.put("guildtag", new StringArgument());
//		arguments.put("Players", new EntitySelectorArgument(EntitySelector.MANY_PLAYERS)
//				.overrideSuggestions("@a[x=-770,y=106,z=-128,dx=7,dy=4,dz=13]"));
//
//		CommandAPI.getInstance().register("createguild", arguments, (sender, args) -> {
//			// run(sender, (String) args[0], (int) args[1], (String) args[2]);
//		});
//
//		arguments.clear();
//		arguments.put("guildname", new TextArgument().withPermission(CommandPermission.fromString("hello")));
//		arguments.put("guildIDnumber",
//				new IntegerArgument(1, 27).withPermission(CommandPermission.fromString("hello")));
//		arguments.put("guildtag", new StringArgument().withPermission(CommandPermission.fromString("hello")));
//		arguments.put("Players",
//				new EntitySelectorArgument(EntitySelector.MANY_PLAYERS)
//						.overrideSuggestions("@a[x=-770,y=106,z=-128,dx=7,dy=4,dz=13]")
//						.withPermission(CommandPermission.fromString("hello")));
//
//		CommandAPI.getInstance().register("createguildperm", arguments, (sender, args) -> {
//			// run(sender, (String) args[0], (int) args[1], (String) args[2]);
//		});
//
//		arguments.clear();
//		CommandAPI.getInstance().register("death", arguments, (sender, args) -> {
//			((Player) sender).setHealth(0);
//		});
//
//		arguments.clear();
//		arguments.put("target", new PlayerArgument().withPermission(CommandPermission.OP));
//		CommandAPI.getInstance().register("death", arguments, (sender, args) -> {
//			((Player) args[0]).setHealth(0);
//		});
//
//		arguments.clear();
//		arguments.put("target", new ItemStackArgument().overrideSuggestions("minecraft:iron_ingot",
//				"minecraft:gold_ingot", "minecraft:diamond"));
//
//		CommandAPI.getInstance().register("mycommand", arguments, (sender, args) -> {
//			// code here
//		});
//		
//		arguments.clear();
//		arguments.put("custrunarg", new CommandArgument());
//		CommandAPI.getInstance().register("custrun", arguments, (sender, args) -> {
//			sender.sendMessage("z");
//		});
	}
}
