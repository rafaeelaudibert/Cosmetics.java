package cosmetics.test;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import cosmetics.business.BusinessException;
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
	
	static Integer LOWER_BOUND = -3;
	static Integer UPPER_BOUND = 3;

	@Before
	public void setUp() throws BusinessException {
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
		cream = new Product(01, "Creme X", userPaulo, creamCategory, group);
		lotion = new Product(02, "Locao Y", userJose, lotionCategory, group);
		shampoo = new Product(03, "Shampoo Z", userJoao, shampooCategory, group);

		// Definindo listas de produtos para teste;
		List<Product> productsList = new ArrayList<Product>();
		productsList.add(cream);
		productsList.add(lotion);

	}

	// Test addEvaluation
	@Test
	public void addEvaluationValidTest() throws BusinessException {
		Integer old_size = cream.getEvaluations().size();
		Evaluation evaluation = new Evaluation(userJoao, cream);
		
		// We don't need to run this LOC, because new Evaluation already calls this function in a valid way
		// cream.addEvaluation(evaluation);

		assertTrue(cream.getEvaluations().contains(evaluation));
		assertTrue(cream.getEvaluations().size() > old_size);
	}

	@Test(expected = BusinessException.class)
	public void addEvaluationNullTest() throws BusinessException {
		cream.addEvaluation(null);
	}

	@Test(expected = BusinessException.class)
	public void addEvaluationNonPermittedUserTest() throws BusinessException {
		Evaluation evaluation = null;
		
		try {
			evaluation = new Evaluation(userJoao, shampoo);
		} catch (BusinessException e) {
			assertFalse(shampoo.getEvaluations().contains(evaluation));
			throw e;
		}
	}

	// Test addScore
	@Test
	public void addScoreValidTest() throws BusinessException {
		Integer SCORE = 3;
		new Evaluation(userJoao, cream); // Already calls cream.addEvaluation(this);

		cream.addScore(userJoao, SCORE);
		assertTrue(cream.getEvaluationFromUser(userJoao).getScore() == SCORE);
	}

	@Test(expected = BusinessException.class)
	public void addScoreWithEmptyUserTest() throws BusinessException {
		cream.addScore(null, 0);
	}

	@Test(expected = BusinessException.class)
	public void addScoreWithNullScoreTest() throws BusinessException {
		new Evaluation(userJoao, cream);  // Already calls cream.addEvaluation(this);
 
		cream.addScore(userJoao, null);
	}

	@Test(expected = BusinessException.class)
	public void addScoreWithLowerScoreTest() throws BusinessException {
		new Evaluation(userJoao, cream);  // Already calls cream.addEvaluation(this);

		cream.addScore(userJoao, LOWER_BOUND - 1);
	}

	@Test(expected = BusinessException.class)
	public void addScoreWithHigherScoreTest() throws BusinessException {
		new Evaluation(userJoao, cream);  // Already calls cream.addEvaluation(this);

		cream.addScore(userJoao, UPPER_BOUND + 1);
	}

	@Test
	public void addScoreWithLowerBoundScoreTest() throws BusinessException {
		Evaluation evaluation = new Evaluation(userJoao, cream);  // Already calls cream.addEvaluation(this);

		cream.addScore(userJoao, LOWER_BOUND);
		assertTrue(evaluation.getScore() == LOWER_BOUND);
	}

	@Test
	public void addScoreWithUpperBoundScoreTest() throws BusinessException {
		Evaluation evaluation = new Evaluation(userJoao, cream);  // Already calls cream.addEvaluation(this);

		cream.addScore(userJoao, UPPER_BOUND);
		assertTrue(evaluation.getScore() == UPPER_BOUND);
	}

	// Test getAverageScore
	@Test
	public void getAverageScoreValidTest() throws BusinessException {
		new Evaluation(userJoao, cream);    // Already calls cream.addEvaluation(this);
		new Evaluation(userMateus, cream);  // Already calls cream.addEvaluation(this);

		cream.addScore(userJoao, -2);
		cream.addScore(userMateus, 1);

		assertTrue(cream.getAverageScore() == (-2.0 + 1.0) / 2.0);
	}

	@Test
	public void getAverageScoreZeroEvaluationsTest() {
		assertTrue(cream.getAverageScore().isNaN());
	}

	// Test isAcceptable
	@Test
	public void isAcceptableValidTrueTest() throws BusinessException {
		new Evaluation(userJoao, cream);  	 // Already calls cream.addEvaluation(this);
		new Evaluation(userMateus, cream);  // Already calls cream.addEvaluation(this);

		cream.addScore(userJoao, 3);
		cream.addScore(userMateus, 0);

		assertTrue(cream.isAcceptable());
	}

	@Test
	public void isAcceptableValidFalseTest() throws BusinessException {
		new Evaluation(userJoao, cream);   // Already calls cream.addEvaluation(this);
		new Evaluation(userMateus, cream); // Already calls cream.addEvaluation(this);

		cream.addScore(userJoao, -3);
		cream.addScore(userMateus, 0);

		assertFalse(cream.isAcceptable());
	}

	@Test
	public void isAcceptableValidLimitTest() throws BusinessException {
		new Evaluation(userJoao, cream);   // Already calls cream.addEvaluation(this);
		new Evaluation(userMateus, cream); // Already calls cream.addEvaluation(this);

		cream.addScore(userJoao, -3);
		cream.addScore(userMateus, 3);

		assertTrue(cream.isAcceptable());
	}

	// Test getEvaluations
	@Test
	public void getEvaluationsValidTest() throws BusinessException {
		Integer old_list_size = cream.getEvaluations().size();
		Evaluation evaluation = new Evaluation(userJoao, cream);  // Already calls cream.addEvaluation(this);

		assertTrue(cream.getEvaluations().contains(evaluation));
		assertTrue(old_list_size + 1 == cream.getEvaluations().size());
	}

	// Test getEvaluationFromUser
	@Test
	public void getEvaluationFromUserValidTest() throws BusinessException {
		Evaluation evaluation = new Evaluation(userJoao, cream);  // Already calls cream.addEvaluation(this);

		assertTrue(cream.getEvaluationFromUser(userJoao) == evaluation);
	}
	
	@Test(expected = BusinessException.class)
	public void getEvaluationFromUserNullTest() throws BusinessException {
		cream.getEvaluationFromUser(null);
	}

}
