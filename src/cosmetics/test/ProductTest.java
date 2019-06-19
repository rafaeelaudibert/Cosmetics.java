package cosmetics.test;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import cosmetics.business.Category;
import cosmetics.business.Evaluation;
import cosmetics.business.Group;
import cosmetics.business.Product;
import cosmetics.business.User;

public class ProductTest {
	
	User userJoao, userJose, userPaulo, userMateus;
	Category creamCategory, lotionCategory, shampooCategory;
	Product cream, lotion, shampoo;
	Group group;

	@Before
	public void setUp() throws Exception{
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
		userJoao.addGroup(group);
		userMateus.addGroup(group);
		
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
	public void addEvaluationValid() throws Exception {		
		int old_size = cream.getEvaluations().size();
		Evaluation evaluation = new Evaluation(userJoao, cream);
				
		// cream.addEvaluation(evaluation); // Não rodamos essa linha de código, pois new Evaluation já roda isso
		
		assertTrue(cream.getEvaluations().contains(evaluation));
		assertTrue(cream.getEvaluations().size() > old_size);
	}
	
	@Test(expected=NullPointerException.class)
	public void addEvaluationNull() {		
		cream.addEvaluation(null);
	}
	
	@Test(expected=Exception.class)
	public void addEvaluationNonPermittedUser() throws Exception {
		Evaluation evaluation = new Evaluation(userJoao, shampoo);
		
		shampoo.addEvaluation(evaluation);
		assertFalse(shampoo.getEvaluations().contains(evaluation));
	}
	
	@Test(expected=Exception.class)
	public void addEvaluationWrongProduct() throws Exception {
		Evaluation evaluation = new Evaluation(userJoao, shampoo);
		
		lotion.addEvaluation(evaluation);
		//assertFalse(shampoo.getEvaluations().contains(evaluation));
	}
	
	// Test addScore
	
	@Test
	public void addScoreValid() throws Exception {
		int SCORE = 3;
		Evaluation evaluation = new Evaluation(userJoao, cream);
		
		cream.addEvaluation(evaluation);		
	
		cream.addScore(userJoao, SCORE);
		assertTrue(cream.getEvaluationFromUser(userJoao).getScore() == SCORE);
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
	public void getAverageScoreValid() throws Exception {
		Evaluation evaluation1 = new Evaluation(userJoao, cream);
		Evaluation evaluation2 = new Evaluation(userMateus, cream);
		
		cream.addEvaluation(evaluation1);		
		cream.addScore(userJoao, -2);
		
		cream.addEvaluation(evaluation2);
		cream.addScore(userMateus, 1);
		
		assertTrue(cream.getAverageScore() == (-2.0 + 1.0) / 2.0);
	}
	
	@Test
	public void getAverageScoreZeroEvaluations() {
		assertTrue(cream.getAverageScore().isNaN());
	}
	
	// Test isAcceptable
	@Test
	public void isAcceptableValidTrue() throws Exception {
		Evaluation evaluation1 = new Evaluation(userJoao, cream);
		Evaluation evaluation2 = new Evaluation(userMateus, cream);
		
		cream.addEvaluation(evaluation1);		
		cream.addScore(userJoao, 3);
		
		cream.addEvaluation(evaluation2);
		cream.addScore(userMateus, 0);
		
		assertTrue(cream.isAcceptable());
	}
	
	@Test
	public void isAcceptableValidFalse() throws Exception {
		Evaluation evaluation1 = new Evaluation(userJoao, cream);
		Evaluation evaluation2 = new Evaluation(userMateus, cream);
		
		cream.addEvaluation(evaluation1);		
		cream.addScore(userJoao, -3);
		
		cream.addEvaluation(evaluation2);
		cream.addScore(userMateus, 0);
		
		assertFalse(cream.isAcceptable());
	}
	
	@Test
	public void isAcceptableValidLimit() throws Exception {
		Evaluation evaluation1 = new Evaluation(userJoao, cream);
		Evaluation evaluation2 = new Evaluation(userMateus, cream);
		
		cream.addEvaluation(evaluation1);		
		cream.addScore(userJoao, -3);
		
		cream.addEvaluation(evaluation2);
		cream.addScore(userMateus, 3);
		
		assertTrue(cream.isAcceptable());
	}
	
	// Test getEvaluations
	@Test
	public void getEvaluationsValid() throws Exception {
		Evaluation evaluation = new Evaluation(userJoao, cream);
		cream.addEvaluation(evaluation);
		
		assertTrue(cream.getEvaluations().contains(evaluation));
	}
	
	// Test getEvaluationFromUser
	@Test
	public void getEvaluationFromUserValid() throws Exception {
		Evaluation evaluation = new Evaluation(userJoao, cream);
		cream.addEvaluation(evaluation);
		
		assertTrue(cream.getEvaluationFromUser(userJoao) == evaluation);
	}
	

}
