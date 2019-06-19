package cosmetics;

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
