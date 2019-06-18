package cosmetics;

public class ReviewCommand extends Command {
	
	private static final Integer MINIMUM_SCORE = -3;
	private static final Integer MAXIMUM_SCORE = 3;
	
	private BusinessImpl businessImpl;
	
	public ReviewCommand(BusinessImpl businessImpl) {
		this.businessImpl = businessImpl;
	}
	
	@Override
	public void execute() {
		System.out.println("Executing Review");
	}

}
