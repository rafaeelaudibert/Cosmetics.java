package cosmetics;

import java.util.Scanner;

public class ReviewCommand extends Command {
	
	private static final Integer MINIMUM_SCORE = -3;
	private static final Integer MAXIMUM_SCORE = 3;
	
	private BusinessImpl businessImpl;
	private final Scanner scanner = new Scanner(System.in);
	
	public ReviewCommand(BusinessImpl businessImpl) {
		this.businessImpl = businessImpl;
	}
	
	@Override
	public void execute() {
		System.out.println("[INFO] Enter the id of the product you want to evaluate: ");
		businessImpl.printProducts();
		System.out.println();
		
		try {		
			Integer productId = Integer.parseInt(scanner.nextLine());
			
			System.out.println("[INFO] Enter the id of the evaluator you want to set the score: ");
			businessImpl.printProductEvaluators(productId);
			Integer evaluatorId = Integer.parseInt(scanner.nextLine());
						
			Integer score;
			do {
				System.out.println("[INFO] Enter the score for this user (in the range [" + MINIMUM_SCORE + ", " + MAXIMUM_SCORE + "])");
				score = Integer.parseInt(scanner.nextLine());
			} while (score < MINIMUM_SCORE || score > MAXIMUM_SCORE);			
			
			businessImpl.reviewProduct(productId, evaluatorId, score);
		} catch (NumberFormatException e) {
			System.out.println("[ERROR] You didn't inserted a well behaved number. You will be redirected back to the main menu");
		} catch (Exception e) {
			System.out.println("[ERROR] Oops! A fatal error occured! You will be redirected back to the main menu");
		}
	}
	
	@Override
	public String toString() {
		return "[Review] Product's Grading";
	}

}
