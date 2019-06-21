package cosmetics.business.database;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import cosmetics.business.BusinessException;
import cosmetics.business.Category;
import cosmetics.business.Evaluation;
import cosmetics.business.Group;
import cosmetics.business.Product;
import cosmetics.business.User;

public class Database {
	private List<Category> categories;
	private List<Group> groups;
	private List<User> users;
	private List<Product> products;
	
	public Database() throws BusinessException {				
		// Create pre-defined Category instances
		categories = new ArrayList<Category>();
		categories.add(new Category("DD Cream"));
		categories.add(new Category("CC Cream"));
		categories.add(new Category("Powder Sunscreen"));
		categories.add(new Category("BB Cream"));
		categories.add(new Category("Foundation+SPF"));
		categories.add(new Category("Oil Free Matte SPF"));
		
		// Create pre-defined Group instances
		groups = new ArrayList<Group>();
		groups.add(new Group("SPF A"));
		groups.add(new Group("SPF B"));
		groups.add(new Group("SPF C"));
		
		// Create pre-defined User instances
		users = new ArrayList<User>();
		users.add(new User(1, "João", "RS", new ArrayList<>(Arrays.asList(getCategory("BB Cream"), getCategory("CC Cream"), getCategory("DD Cream")))));
		users.add(new User(2, "Ana", "SP", new ArrayList<>(Arrays.asList(getCategory("Foundation+SPF"), getCategory("DD Cream"), getCategory("CC Cream")))));
		users.add(new User(3, "Manoela", "RS", new ArrayList<>(Arrays.asList(getCategory("BB Cream"), getCategory("Oil Free Matte SPF")))));
		users.add(new User(4, "Joana", "CE", new ArrayList<>(Arrays.asList(getCategory("BB Cream"), getCategory("CC Cream"), getCategory("Foundation+SPF"), getCategory("Powder Sunscreen")))));
		users.add(new User(5, "Miguel", "RS", new ArrayList<>(Arrays.asList(getCategory("Foundation+SPF"), getCategory("DD Cream"), getCategory("Oil Free Matte SPF")))));
		users.add(new User(6, "Beatriz", "CE", new ArrayList<>(Arrays.asList(getCategory("CC Cream"), getCategory("Oil Free Matte SPF"), getCategory("Powder Sunscreen")))));
		users.add(new User(7, "Suzana", "RS", new ArrayList<>(Arrays.asList(getCategory("Powder Sunscreen"), getCategory("DD Cream"), getCategory("CC Cream")))));
		users.add(new User(8, "Natasha", "CE", new ArrayList<>(Arrays.asList(getCategory("DD Cream"), getCategory("CC Cream"), getCategory("BB Cream")))));
		users.add(new User(9, "Pedro", "SP", new ArrayList<>(Arrays.asList(getCategory("Powder Sunscreen"), getCategory("Foundation+SPF")))));
		users.add(new User(10, "Carla", "SP", new ArrayList<>(Arrays.asList(getCategory("CC Cream"), getCategory("DD Cream"), getCategory("Oil Free Matte SPF")))));
		
		// Add users to groups
		this.getGroup("SPF A").addMember(this.getUser(1));
		this.getGroup("SPF A").addMember(this.getUser(2));
		this.getGroup("SPF A").addMember(this.getUser(3));
		this.getGroup("SPF A").addMember(this.getUser(4));
		this.getGroup("SPF A").addMember(this.getUser(5));
		this.getGroup("SPF A").addMember(this.getUser(6));
		this.getGroup("SPF A").addMember(this.getUser(7));
		this.getGroup("SPF B").addMember(this.getUser(1));
		this.getGroup("SPF B").addMember(this.getUser(2));
		this.getGroup("SPF B").addMember(this.getUser(3));
		this.getGroup("SPF B").addMember(this.getUser(4));
		this.getGroup("SPF B").addMember(this.getUser(5));
		this.getGroup("SPF B").addMember(this.getUser(6));
		this.getGroup("SPF B").addMember(this.getUser(7));
		this.getGroup("SPF C").addMember(this.getUser(4));
		this.getGroup("SPF C").addMember(this.getUser(5));
		this.getGroup("SPF C").addMember(this.getUser(6));
		this.getGroup("SPF C").addMember(this.getUser(7));
		this.getGroup("SPF C").addMember(this.getUser(8));
		this.getGroup("SPF C").addMember(this.getUser(9));
		this.getGroup("SPF C").addMember(this.getUser(10));
			
		this.getUser(1).addGroup(this.getGroup("SPF A"));
		this.getUser(2).addGroup(this.getGroup("SPF A"));
		this.getUser(3).addGroup(this.getGroup("SPF A"));
		this.getUser(4).addGroup(this.getGroup("SPF A"));
		this.getUser(5).addGroup(this.getGroup("SPF A"));
		this.getUser(6).addGroup(this.getGroup("SPF A"));
		this.getUser(7).addGroup(this.getGroup("SPF A"));
		
		this.getUser(1).addGroup(this.getGroup("SPF B"));
		this.getUser(2).addGroup(this.getGroup("SPF B"));
		this.getUser(3).addGroup(this.getGroup("SPF B"));
		this.getUser(4).addGroup(this.getGroup("SPF B"));
		this.getUser(5).addGroup(this.getGroup("SPF B"));
		this.getUser(6).addGroup(this.getGroup("SPF B"));
		this.getUser(7).addGroup(this.getGroup("SPF B"));
		
		this.getUser(4).addGroup(this.getGroup("SPF C"));
		this.getUser(5).addGroup(this.getGroup("SPF C"));
		this.getUser(6).addGroup(this.getGroup("SPF C"));
		this.getUser(7).addGroup(this.getGroup("SPF C"));
		this.getUser(8).addGroup(this.getGroup("SPF C"));
		this.getUser(9).addGroup(this.getGroup("SPF C"));
		this.getUser(10).addGroup(this.getGroup("SPF C"));
		
		// Create pre-defined Product instances
		products = new ArrayList<Product>();
		products.add(new Product(1, "L'oreal DD Cream", getUser(1), getCategory("DD Cream"), getGroup("SPF C")));
		products.add(new Product(2, "Avon CC Cream", getUser(6), getCategory("CC Cream"), getGroup("SPF B")));
		products.add(new Product(3, "Revolution Powder Sunscreen", getUser(7), getCategory("Powder Sunscreen"), getGroup("SPF B")));
		products.add(new Product(4, "Maybelline BB Cream", getUser(8), getCategory("BB Cream"), getGroup("SPF B")));
		products.add(new Product(5, "Revlon Foundation+SPF20", getUser(9), getCategory("Foundation+SPF"), getGroup("SPF B")));
		products.add(new Product(6, "Nivea Matte Face SPF", getUser(10), getCategory("Oil Free Matte SPF"), getGroup("SPF B")));
		products.add(new Product(7, "La Roche CC Cream", getUser(6), getCategory("CC Cream"), getGroup("SPF A")));
		products.add(new Product(8, "Yves Rocher Powder+SPF15", getUser(7), getCategory("Powder Sunscreen"), getGroup("SPF A")));
		products.add(new Product(9, "Nivea BB Cream", getUser(8), getCategory("BB Cream"), getGroup("SPF A")));
		products.add(new Product(10, "Base O Boticário SPF20", getUser(9), getCategory("Foundation+SPF"), getGroup("SPF A")));
		products.add(new Product(11, "Natura SPF20 Rosto Matte", getUser(10), getCategory("Oil Free Matte SPF"), getGroup("SPF A")));
		
		// Add products to group
		products.forEach((Product p) -> p.getGroup().addProduct(p));
		
		// Create pre-defined Evaluation instances
		Evaluation evaluation;
		evaluation = new Evaluation(getUser(8), getProduct(1));
		evaluation.setScore(2);
		
		evaluation = new Evaluation(getUser(10), getProduct(1));
		
		evaluation = new Evaluation(getUser(7), getProduct(2));
		evaluation.setScore(2);
		
		evaluation = new Evaluation(getUser(2), getProduct(2));
		evaluation.setScore(3);
		
		evaluation = new Evaluation(getUser(4), getProduct(3));
		evaluation.setScore(-1);
		
		evaluation = new Evaluation(getUser(6), getProduct(3));
		evaluation.setScore(1);
		
		evaluation = new Evaluation(getUser(1), getProduct(4));
		evaluation.setScore(1);
		
		evaluation = new Evaluation(getUser(3), getProduct(4));
		evaluation.setScore(0);
		
		evaluation = new Evaluation(getUser(4), getProduct(5));
		evaluation.setScore(-3);
		
		evaluation = new Evaluation(getUser(5), getProduct(5));
		evaluation.setScore(-3);
		
		evaluation = new Evaluation(getUser(3), getProduct(6));
		evaluation.setScore(-1);
		
		evaluation = new Evaluation(getUser(6), getProduct(6));
		evaluation.setScore(0);
		
		// Configure allocated groups
		this.getGroup("SPF B").setAllocated();
		this.getGroup("SPF C").setAllocated();
		
	}
	
	public List<User> getUsers(){
		return users;
	}
	
	public List<Product> getProducts(){
		return products;
	}
	
	public List<Group> getGroups(){
		return groups;
	}
	
	public List<Group> getNonAllocatedGroups(){
		return getGroups().parallelStream()
				.filter((Group g) -> !g.isAllocated())
				.collect(Collectors.toCollection(ArrayList::new));
	}
	
	public List<Category> getCategories(){
		return categories;
	}
	
	public Product getProduct(Integer productId) {
		return this.getProducts()
				.parallelStream()
				.filter((Product p) -> p.getId() == productId)
				.findFirst()
				.orElseThrow(NullPointerException::new);
	}
	
	public User getUser(Integer userId) {
		return this.getUsers()
				.parallelStream()
				.filter((User u) -> u.getId() == userId)
				.findFirst()
				.orElseThrow(NullPointerException::new);
	}
	
	public Group getGroup(Integer groupIndex) {
		return this.getGroups()
				.get(groupIndex);
	}
	
	public Group getGroup(String groupName) {
		return this.getGroups()
				.parallelStream()
				.filter((Group g) -> g.getName() == groupName)
				.findFirst()
				.orElseThrow(NullPointerException::new);
	}
	
	public Category getCategory(String categoryName) {
		return this.getCategories()
				.parallelStream()
				.filter((Category c) -> c.getName() == categoryName)
				.findFirst()
				.orElseThrow(NullPointerException::new);
	}
	
}
