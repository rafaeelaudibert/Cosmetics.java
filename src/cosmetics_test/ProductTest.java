package cosmetics_test;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import cosmetics.Category;
import cosmetics.Evaluation;
import cosmetics.Group;
import cosmetics.Product;
import cosmetics.User;

public class ProductTest {
	
	User userJoao, userJose, userPaulo, userMateus;
	Category creamCategory, lotionCategory, shampooCategory;
	Product cream, lotion, shampoo;
	Group group;

	@Before
	public void setUp() {
		// Definindo categorias para teste
		creamCategory = new Category("creme");
		lotionCategory = new Category("locao");
		shampooCategory = new Category("shampoo");

		// Definindo listas de categorias para teste
		List<Category> categoryListEmpty = new ArrayList<Category>();
		List<Category> categoryListCream = new ArrayList<Category>(); // Cria outra lista de categorias
		categoryListCream.add(creamCategory);
		List<Category> categoryListCreamShampoo = new ArrayList<Category>(); // Cria terceira lista de categorias
		categoryListCreamShampoo.add(creamCategory);
		categoryListCreamShampoo.add(shampooCategory);
		
		// Definindo usu�rios para teste
		userJoao = new User(01, "Joao", "RS", categoryListCream);
		userMateus = new User(02, "Mateus", "BA", categoryListCream);
		userJose = new User(03, "Jose", "RS", categoryListCreamShampoo);
		userPaulo = new User(04, "Paulo", "MG", categoryListEmpty);

		// Definindo grupos para teste
		group = new Group("Grupo");
		group.addMember(userJoao);
		group.addMember(userMateus);
		
		
		// Definindo produtos para teste
		cream = new Product(01, "Creme X", userPaulo, creamCategory,group);
		lotion = new Product(02, "Locao Y", userJose, lotionCategory,group);
		shampoo = new Product(03, "Shampoo Z", userJoao, shampooCategory,group);

		// Definindo listas de produtos para teste;
		List<Product> productsList = new ArrayList<Product>();
		productsList.add(cream);
		productsList.add(lotion);

	}
	
	// Testando addEvaluation
	@Test
	public void addEvaluationValid() {
		Evaluation evaluation = new Evaluation(userJoao, cream);
		
		int old_size = cream.getEvaluations().size();
		cream.addEvaluation(evaluation);
		
		assertTrue(cream.getEvaluations().contains(evaluation));
		assertTrue(cream.getEvaluations().size() > old_size);
	}
	
	@Test(expected=NullPointerException.class)
	public void addEvaluationNull() {		
		cream.addEvaluation(null);
	}
	
	@Test
	public void addEvaluationNonPermittedUser() {
		Evaluation evaluation = new Evaluation(userJoao, shampoo);
		
		shampoo.addEvaluation(evaluation);
		assertFalse(shampoo.getEvaluations().contains(evaluation));
	}
	
	@Test
	public void addEvaluationWrongProduct() {
		Evaluation evaluation = new Evaluation(userJoao, shampoo);
		
		lotion.addEvaluation(evaluation);
		assertFalse(shampoo.getEvaluations().contains(evaluation));
	}
	
	// Test addScore
	
	@Test
	public void addScoreValid() {
		int SCORE = 3;
		Evaluation evaluation = new Evaluation(userJoao, cream);
		
		cream.addEvaluation(evaluation);		
		
		try {
			cream.addScore(userJoao, SCORE);
			assertTrue(cream.getEvaluationFromUser(userJoao).getScore() == SCORE);
		} catch(Exception e) {
			fail(); // If it raises an Exception, doesn't work, it is an error
		}
	}
	
	@Test(expected=Exception.class)
	public void addScoreWithEmptyUser() throws Exception {
		cream.addScore(null, 0);
	}
	
	@Test(expected=Exception.class)
	public void addScoreWithNullScore() throws Exception {
		Evaluation evaluation = new Evaluation(userJoao, cream);
		
		cream.addEvaluation(evaluation);		
		cream.addScore(userJoao, null);
	}
	
	@Test(expected=Exception.class)
	public void addScoreWithLowerScore() throws Exception {
		int LOWER_BOUND = -3;
		Evaluation evaluation = new Evaluation(userJoao, cream);
		
		cream.addEvaluation(evaluation);		
		cream.addScore(userJoao, LOWER_BOUND - 1);
	}
	
	@Test(expected=Exception.class)
	public void addScoreWithHigherScore() throws Exception {
		int UPPER_BOUND = 3;
		Evaluation evaluation = new Evaluation(userJoao, cream);
		
		cream.addEvaluation(evaluation);		
		cream.addScore(userJoao, UPPER_BOUND + 1);
	}
	
	@Test
	public void addScoreWithLowerBoundScore() throws Exception {
		int LOWER_BOUND = -3;
		Evaluation evaluation = new Evaluation(userJoao, cream);
		
		cream.addEvaluation(evaluation);		
		cream.addScore(userJoao, LOWER_BOUND);
		assertTrue(cream.getEvaluationFromUser(userJoao).getScore() == LOWER_BOUND);
	}
	
	@Test
	public void addScoreWithUpperBoundScore() throws Exception {
		int UPPER_BOUND = 3;
		Evaluation evaluation = new Evaluation(userJoao, cream);
		
		cream.addEvaluation(evaluation);		
		cream.addScore(userJoao, UPPER_BOUND);
		assertTrue(cream.getEvaluationFromUser(userJoao).getScore() == UPPER_BOUND);
	}
	
	// Test getAverageScore
	@Test
	public void getAverageScoreValid() {
		try {
			Evaluation evaluation1 = new Evaluation(userJoao, cream);
			Evaluation evaluation2 = new Evaluation(userMateus, cream);
			
			cream.addEvaluation(evaluation1);		
			cream.addScore(userJoao, -2);
			
			cream.addEvaluation(evaluation2);
			cream.addScore(userMateus, 1);
			
			assertTrue(cream.getAverageScore() == (-2.0 + 1.0) / 2.0);
		} catch (Exception e) {
			fail(); // Shouldn't fail with this code
		}
	}
	
	@Test
	public void getAverageScoreZeroEvaluations() {
		assertTrue(cream.getAverageScore().isNaN());
	}
	
	// Test isAcceptable
	@Test
	public void isAcceptableValidTrue() {
		try {
			Evaluation evaluation1 = new Evaluation(userJoao, cream);
			Evaluation evaluation2 = new Evaluation(userMateus, cream);
			
			cream.addEvaluation(evaluation1);		
			cream.addScore(userJoao, 3);
			
			cream.addEvaluation(evaluation2);
			cream.addScore(userMateus, 0);
			
			assertTrue(cream.isAcceptable());
		} catch (Exception e) {
			fail(); // Shouldn't fail with this code
		}
	}
	
	@Test
	public void isAcceptableValidFalse() {
		try {
			Evaluation evaluation1 = new Evaluation(userJoao, cream);
			Evaluation evaluation2 = new Evaluation(userMateus, cream);
			
			cream.addEvaluation(evaluation1);		
			cream.addScore(userJoao, -3);
			
			cream.addEvaluation(evaluation2);
			cream.addScore(userMateus, 0);
			
			assertFalse(cream.isAcceptable());
		} catch (Exception e) {
			fail(); // Shouldn't fail with this code
		}
	}
	
	@Test
	public void isAcceptableValidLimit() {
		try {
			Evaluation evaluation1 = new Evaluation(userJoao, cream);
			Evaluation evaluation2 = new Evaluation(userMateus, cream);
			
			cream.addEvaluation(evaluation1);		
			cream.addScore(userJoao, -3);
			
			cream.addEvaluation(evaluation2);
			cream.addScore(userMateus, 3);
			
			assertTrue(cream.isAcceptable());
		} catch (Exception e) {
			fail(); // Shouldn't fail with this code
		}
	}
	
	// Test getEvaluations
	@Test
	public void getEvaluationsValid() {
		Evaluation evaluation = new Evaluation(userJoao, cream);
		cream.addEvaluation(evaluation);
		
		assertTrue(cream.getEvaluations().contains(evaluation));
	}
	
	// Test getEvaluationFromUser
	@Test
	public void getEvaluationFromUserValid() {
		Evaluation evaluation = new Evaluation(userJoao, cream);
		cream.addEvaluation(evaluation);
		
		assertTrue(cream.getEvaluationFromUser(userJoao) == evaluation);
	}
	

}
