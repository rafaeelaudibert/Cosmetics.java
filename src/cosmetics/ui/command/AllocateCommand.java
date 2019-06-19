package cosmetics.ui.command;

import java.util.Scanner;

import cosmetics.business.impl.BusinessImpl;

public class AllocateCommand extends Command {
	
	private static final Integer MINIMUM_MEMBERS_ALLOCATED = 2;
	private static final Integer MAXIMUM_MEMBERS_ALLOCATED = 5;
	
	private BusinessImpl businessImpl;
	private final Scanner scanner = new Scanner(System.in);
	
	public AllocateCommand(BusinessImpl businessImpl) {
		this.businessImpl = businessImpl;
	}
	
	@Override
	public void execute() {		
		try {
			System.out.println("[INFO] Enter the index (starting at 1 above) of the group you want allocate: ");
			businessImpl.printNonAllocatedGroups();
			System.out.println();		
			Integer groupIndex = Integer.parseInt(scanner.nextLine());
			
			Integer numMembers;
			do {
				System.out.println("[INFO] Enter the number of members of this group you want to allocate for each product (in the range [" + MINIMUM_MEMBERS_ALLOCATED + ", " + MAXIMUM_MEMBERS_ALLOCATED +"])");
				numMembers = Integer.parseInt(scanner.nextLine());
			} while (numMembers < MINIMUM_MEMBERS_ALLOCATED || numMembers > MAXIMUM_MEMBERS_ALLOCATED);		
			
			businessImpl.allocate(groupIndex, numMembers);
		} catch (NumberFormatException e) {
			System.out.println("[ERROR] You didn't inserted a well behaved number. You will be redirected to the main menu");
		} catch (Exception e) {
			System.out.println("[ERROR] Oops! A fatal error occured! You will be redirected back to the main menu");
			System.out.println(e);
		}
	}
	
	@Override
	public String toString() {
		return "[Allocate] Product's evaluations allocation";
	}

}
