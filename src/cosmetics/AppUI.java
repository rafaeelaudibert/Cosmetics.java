package cosmetics;

import java.util.HashMap;
import java.util.Map;

public final class AppUI {
	private Map<String, Command> commands;
	private BusinessImpl businessImpl;
	
	public AppUI() {
		// Build businessImpl
		businessImpl = new BusinessImpl();
		
		// Build commands map
		commands = new HashMap<String, Command>();
		commands.put("allocate", new AllocateCommand(businessImpl));
		commands.put("review", new ReviewCommand(businessImpl));
		commands.put("show", new ShowCommand(businessImpl));
	}
	
	public void createAndShowUI() {		
		getCommand().execute();
	}
	
	private Command getCommand() {
		return commands.get("review");
	}
}
