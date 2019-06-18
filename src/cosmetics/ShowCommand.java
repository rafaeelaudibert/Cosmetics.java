package cosmetics;

public class ShowCommand extends Command {
	
	private BusinessImpl businessImpl;
	
	public ShowCommand(BusinessImpl businessImpl) {
		this.businessImpl = businessImpl;
	}
	
	@Override
	public void execute() {
		System.out.println("Executing Show");

	}

}
