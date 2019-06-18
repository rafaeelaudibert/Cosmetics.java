package cosmetics;

public class AllocateCommand extends Command {
	
	private static final Integer MINIMUM_MEMBERS_ALLOCATED = 2;
	private static final Integer MAXIMUM_MEMBERS_ALLOCATED = 5;
	
	private BusinessImpl businessImpl;
	
	public AllocateCommand(BusinessImpl businessImpl) {
		this.businessImpl = businessImpl;
	}
	
	@Override
	public void execute() {
		System.out.println("Executing Allocate");
		
	}

}
