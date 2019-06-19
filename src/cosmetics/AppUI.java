package cosmetics;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Scanner;

public final class AppUI {
	private Map<String, Command> commands = new LinkedHashMap<String, Command>();
	private BusinessImpl businessImpl = new BusinessImpl();
	private Scanner scanner = new Scanner(System.in);
	
	public AppUI() {		
		// Build commands map
		commands.put("allocate", new AllocateCommand(businessImpl));
		commands.put("review", new ReviewCommand(businessImpl));
		commands.put("show", new ShowCommand(businessImpl));
		commands.put("exit", new ExitCommand(businessImpl));
	}
	
	public void createAndShowUI() {	
		
		while(true) {
			Command command = getCommand();
			
			if (command.getClass() == ExitCommand.class) {
				System.out.println("[INFO] Exiting program");
				break;
			} else {
				command.execute();
			}
		}
			
	}
	
	private Command getCommand() {
		Command command;
		String userInput;
		
		do {
			System.out.println("[INFO] Please choose a valid command below");
			commands.values().forEach((Command c) -> System.out.println(c));
			System.out.println();
			
			// Read user input, and fetch the command
			command = commands.get(scanner.nextLine().toLowerCase());
			
			if (command == null) {
				System.out.println("[ERROR] You chose an invalid command option");
			}
		} while(command == null);
				
		return command;
	}
}
