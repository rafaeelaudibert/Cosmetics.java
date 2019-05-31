package cosmetics_test;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import cosmetics.Product;
import cosmetics.ProductCategory;
import cosmetics.User;

public class UserTest {

	User user1,user2;
	ProductCategory category1,category2;
	Product product1,product2;
	List<ProductCategory> categories1,categories2;
	
	@Before
	public void setUp() {
		category1 = new ProductCategory("cat"); //Cria uma nova categoria
		category2 = new ProductCategory("categoria");	//Cria outra categoria
		categories1 = new ArrayList<ProductCategory>();	//Cria uma nova lista de categorias
		categories1.add(category1);	//Adiciona a categoria criada a esta lista
		categories2 = new ArrayList<ProductCategory>();	//Cria outra lista de categorias
		user1 = new User(01,"Jo�o","RS",categories1);	//Cria um novo usu�rio
		user2 = new User(02,"Mario","MG",categories2);
		product1 = new Product(00,"x",category1);	//Cria um novo produto
		product2 = new Product(01,"y",category2);	//Cria outro produto
		
		
	}
	//Testando canEvaluate
	@Test
	public void canEvaluateTestInList() {
		assertTrue(user1.canEvaluate(product1));
	}
	@Test
	public void canEvaluateTestNotInList() {
		assertFalse(user1.canEvaluate(product2));
	}
	@Test
	public void canEvaluateTestNullProduct() {
		assertFalse(user1.canEvaluate(null));
	}
	@Test
	public void canEvaluateTestEmptyList() {
		assertFalse(user2.canEvaluate(product1));
	}

	//Testando addEvaluation
	@Test
	public void addEvaluationTest1() {
		try {
		
		}
		catch(Exception e) {
			System.out.println(e);
		}
	}
}
