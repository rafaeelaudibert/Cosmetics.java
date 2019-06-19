package cosmetics.ui.command;

import java.util.Scanner;

import cosmetics.business.impl.BusinessImpl;

public class ShowCommand extends Command {
	
	private BusinessImpl businessImpl;
	Scanner scanner = new Scanner(System.in);
	
	public ShowCommand(BusinessImpl businessImpl) {
		this.businessImpl = businessImpl;
	}
	
	@Override
	public void execute() {		
		System.out.println("[INFO] Enter the index (starting at 1 above) of the group you want to see the products: ");
		businessImpl.printGroups();
		System.out.println();
		String userInput = scanner.nextLine();
		
		try {
			Integer groupIndex = Integer.parseInt(userInput);
			businessImpl.showGroupProducts(groupIndex);
		} catch (NumberFormatException e) {
			System.out.println("[ERROR] You didn't inserted a well behaved index. You will be redirected to the main menu");
		}
	}
	
	@Override
	public String toString() {
		return "[Show] Products Displaying";
	}

}
