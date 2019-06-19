package cosmetics.ui.command;

import cosmetics.business.impl.BusinessImpl;

public class ExitCommand extends Command {
		
	public ExitCommand(BusinessImpl businessImpl) {	}
	
	@Override
	public void execute() {

	}
	
	@Override
	public String toString() {
		return "[Exit] Quit program";
	}

}
