package cosmetics;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

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
	public void allocate(Integer groupIndex, Integer numMembers) {
		
	}
	
	// Second stage
	public void reviewProduct(Integer productId, Integer evaluatorId, Integer score) {
		
	}
	
	// Third stage
	public void showGroupProducts(Integer groupIndex) {
		
	}
	
	/* PRINTING FUNCTIONS */
	public void printGroups() {
		List<Group> groups = database.getGroups();
	}
	
	public void printProducts() {
		List<Product> products = database.getProducts();
	}
	
	public void printProductEvaluators(Integer productId) {
		List<User> evaluators = database.getProduct(productId)
				.getEvaluations()
				.parallelStream()
				.filter((Evaluation e) -> !e.isDone())
				.map((Evaluation e) -> e.getReviewer())
				.distinct()
				.collect(Collectors.toCollection(ArrayList::new));
	}
	
	private void printAcceptableProducts(List<Product> products) {
		List<Product> acceptableProducts = products.parallelStream()
				.filter((Product p) -> p.isAcceptable())
				.collect(Collectors.toCollection(ArrayList::new));
	}
	
	private void printNotAcceptableProducts(List<Product> products) {
		List<Product> notAcceptableProducts = products.parallelStream()
				.filter((Product p) -> !p.isAcceptable())
				.collect(Collectors.toCollection(ArrayList::new));
	}
}
