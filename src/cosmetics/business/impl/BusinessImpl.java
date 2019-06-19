package cosmetics.business.impl;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

import cosmetics.business.Business;
import cosmetics.business.Evaluation;
import cosmetics.business.Group;
import cosmetics.business.Product;
import cosmetics.business.User;
import cosmetics.business.database.Database;

public class BusinessImpl implements Business {
	private Database database;
	
	public BusinessImpl() {
		try {
			this.database = new Database();
		} catch (Exception e) {
			System.out.println("[ERROR] Error when creating database");
			e.printStackTrace();
		}
	}
	
	// First stage
	public void allocate(Integer groupIndex, Integer numMembers) throws Exception {
		List<Group> groups = database.getNonAllocatedGroups();
		
		// If the index is not in the allowed range, throw an exception to be caught upstream
		if (groupIndex <= 0 || groupIndex > groups.size()) {
			throw new NumberFormatException();
		}

		groups.get(groupIndex-1).allocate(numMembers);
	}
	
	// Second stage
	public void reviewProduct(Integer productId, Integer evaluatorId, Integer score) throws Exception {
		Product product = database.getProduct(productId);
		User evaluator = database.getUser(evaluatorId);
		
		product.addScore(evaluator, score);
	}
	
	// Third stage
	public void showGroupProducts(Integer groupIndex) {
		List<Group> groups = database.getGroups();
		
		// If the index is not in the allowed range, throw an exception to be caught upstream
		if (groupIndex <= 0 || groupIndex > groups.size()) {
			throw new NumberFormatException();
		}
		
		Group group = groups.get(groupIndex - 1);
		if (!group.isAllocated()) {
			System.out.println("[WARN] This group has not been allocated yet");
		} else if (!group.evaluationDone()) {
			System.out.println("[WARN] This group's evaluations have not been completely filled with grades yet");
		} else {
			this.printAcceptableProducts(group.getAcceptableProducts());
			this.printNotAcceptableProducts(group.getNotAcceptableProducts());
		}
	}
	
	/* PRINTING FUNCTIONS */
	public void printGroups() {
		List<Group> groups = database.getGroups();
		groups.forEach((Group g) -> System.out.println(g));
	}
	
	public void printNonAllocatedGroups() {
		List<Group> groups = database.getNonAllocatedGroups();
		groups.forEach((Group g) -> System.out.println(g));
	}
	
	public void printProducts() {
		List<Product> products = database.getProducts();
		products.forEach((Product p) -> System.out.println(p));
	}	
	
	public void printProductEvaluators(Integer productId) {
		List<User> evaluators = database.getProduct(productId)
				.getEvaluations()
				.parallelStream()
				.filter((Evaluation e) -> !e.isDone())
				.map((Evaluation e) -> e.getReviewer())
				.distinct()
				.collect(Collectors.toCollection(ArrayList::new));
		evaluators.forEach((User u) -> System.out.println(u + " -> Id " + u.getId()));
	}
	
	private void printAcceptableProducts(List<Product> products) {
		System.out.println("[INFO] Products with acceptable grade (in ASCENDING order):");
		
		products.parallelStream()
			.sorted(Comparator.comparing(Product::getAverageScore))
			.forEach((Product p) -> System.out.println(p.toStringWithGrade()));
		
		System.out.println();
	}
	
	private void printNotAcceptableProducts(List<Product> products) {
		System.out.println("[INFO] Products with NOT acceptable grade (in DESCENDING order):");
		
		products.parallelStream()
				.sorted(Comparator.comparing(Product::getAverageScore).reversed())
				.forEach((Product p) -> System.out.println(p.toStringWithGrade()));
		
		System.out.println();
	}
}
