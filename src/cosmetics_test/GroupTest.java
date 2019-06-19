package cosmetics_test;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.junit.Before;
import org.junit.Test;

import cosmetics.Category;
import cosmetics.Evaluation;
import cosmetics.Group;
import cosmetics.Product;
import cosmetics.User;

public class GroupTest {
	User userJoao, userAna, userManoela, userJoana, userMiguel, userBeatriz, userSuzana, userNatasha, userPedro, userCarla;
	List<User> spfAMembers, spfBMembers, spfCMembers;
	Category ddCream, ccCream, bbCream, powderSunscreen, foundationSPF, oilFreeMatteSPF;
	List<Category> categoryListEmpty, categoryListUser;
	Product lorealDDCream;
	Product avonCCCream, revolutionPowderSunscreen, maybellineBBCream, revlonFoundationSPF20, niveaMatteFaceSPF, laRocheCCCream, yvesRocherPowderSPF15, niveaBBCream, baseOBoticarioSPF20, naturaSPF20RostoMatte;
	Product productNotCovered;
	List<Product> productListEmpty, productListSPFA, productListSPFB, productListSPFC;
	Group spfA, spfB, spfC,groupNEUsers;
	List<Evaluation> evaluation;
	Evaluation evaluation1;
	
	@Before
	public void setUp() throws Exception {
		// Definindo categorias para teste
		ddCream 		= new Category("DD Cream");
		ccCream 		= new Category("CC Cream");
		bbCream 		= new Category("BB Cream");
		powderSunscreen = new Category("Powder Sunscreen");
		foundationSPF	= new Category("Foundation+SPF");
		oilFreeMatteSPF	= new Category("Oil Free Matte SPF");
	
		// Definindo listas de UsuÃ¡rios para teste
		categoryListEmpty	= new ArrayList<Category>();
		categoryListUser 	= new ArrayList<Category>(); // Cria outra lista de categorias
		categoryListUser.add(bbCream);
		categoryListUser.add(ccCream);
		categoryListUser.add(ddCream);
		userJoao 	= new User(1, "Joao", "RS", categoryListUser);
		categoryListUser 	= new ArrayList<Category>(); // Cria outra lista de categorias
		categoryListUser.add(ccCream);
		categoryListUser.add(ddCream);
		categoryListUser.add(foundationSPF);
		userAna 	= new User(2, "Ana", "SP", categoryListUser);
		categoryListUser 	= new ArrayList<Category>(); // Cria outra lista de categorias
		categoryListUser.add(oilFreeMatteSPF);
		categoryListUser.add(bbCream);
		userManoela = new User(3, "Manoela", "RS", categoryListUser);
		categoryListUser 	= new ArrayList<Category>(); // Cria outra lista de categorias
		categoryListUser.add(ccCream);
		categoryListUser.add(foundationSPF);
		categoryListUser.add(bbCream);
		categoryListUser.add(powderSunscreen);
		userJoana 	= new User(4, "Joana", "CE", categoryListUser);
		categoryListUser 	= new ArrayList<Category>(); // Cria outra lista de categorias
		categoryListUser.add(foundationSPF);
		categoryListUser.add(ddCream);
		categoryListUser.add(oilFreeMatteSPF);
		userMiguel 	= new User(5, "Miguel", "RS", categoryListUser);
		categoryListUser 	= new ArrayList<Category>(); // Cria outra lista de categorias
		categoryListUser.add(oilFreeMatteSPF);
		categoryListUser.add(ccCream);
		categoryListUser.add(powderSunscreen);
		userBeatriz = new User(6, "Beatriz", "CE", categoryListUser);
		categoryListUser 	= new ArrayList<Category>(); // Cria outra lista de categorias
		categoryListUser.add(ccCream);
		categoryListUser.add(ddCream);
		categoryListUser.add(powderSunscreen);
		userSuzana 	= new User(7, "Suzana", "RS", categoryListUser);
		categoryListUser 	= new ArrayList<Category>(); // Cria outra lista de categorias
		categoryListUser.add(ccCream);
		categoryListUser.add(ddCream);
		categoryListUser.add(bbCream);
		userNatasha = new User(8, "Natasha", "CE", categoryListUser);
		categoryListUser 	= new ArrayList<Category>(); // Cria outra lista de categorias
		categoryListUser.add(foundationSPF);
		categoryListUser.add(powderSunscreen);
		userPedro 	= new User(9, "Pedro", "SP", categoryListUser);
		categoryListUser 	= new ArrayList<Category>(); // Cria outra lista de categorias
		categoryListUser.add(ccCream);
		categoryListUser.add(ddCream);
		categoryListUser.add(oilFreeMatteSPF);
		userCarla 	= new User(10, "Carla", "SP", categoryListUser);
			
		// Definindo grupos para teste
		spfA = new Group("SPF A");
		spfB = new Group("SPF B");
		spfC = new Group("SPF C");
		groupNEUsers = new Group("Grupo sem usuários suficientes");
		
		// Definindo usuarios dos grupos
		// Grupo a
		spfA.addMember(userJoao);
		spfA.addMember(userAna);
		spfA.addMember(userManoela);
		spfA.addMember(userJoana);
		spfA.addMember(userMiguel);
		spfA.addMember(userBeatriz);
		spfA.addMember(userSuzana);
		// Grupo b
		spfB.addMember(userJoao);
		spfB.addMember(userAna);
		spfB.addMember(userManoela);
		spfB.addMember(userJoana);
		spfB.addMember(userMiguel);
		spfB.addMember(userBeatriz);
		spfB.addMember(userSuzana);		
		// Grupo c
		spfC.addMember(userJoana);
		spfC.addMember(userMiguel);
		spfC.addMember(userBeatriz);
		spfC.addMember(userSuzana);
		spfC.addMember(userNatasha);
		spfC.addMember(userPedro);
		spfC.addMember(userCarla);
		
		groupNEUsers.addMember(userJoao);
		groupNEUsers.addMember(userPedro);
		groupNEUsers.addMember(userAna);
		
		// Adicionando usuÃ¡rios aos grupos (nÃ£o precisamos pegar a exceÃ§Ã£o pois Ã© garantido disso funcionar)
		userJoao.addGroup(spfA);
		userJoao.addGroup(spfB);
		userJoao.addGroup(groupNEUsers);
		userAna.addGroup(spfA);
		userAna.addGroup(spfB);
		userAna.addGroup(groupNEUsers);
		userManoela.addGroup(spfA);
		userManoela.addGroup(spfB);
		userJoana.addGroup(spfA);
		userJoana.addGroup(spfB);
		userJoana.addGroup(spfC);		
		userMiguel.addGroup(spfA);
		userMiguel.addGroup(spfB);
		userMiguel.addGroup(spfC);		
		userBeatriz.addGroup(spfA);
		userBeatriz.addGroup(spfB);
		userBeatriz.addGroup(spfC);		
		userSuzana.addGroup(spfA);
		userSuzana.addGroup(spfB);
		userSuzana.addGroup(spfC);		
		userNatasha.addGroup(spfC);		
		userPedro.addGroup(spfC);
		userPedro.addGroup(groupNEUsers);
		userCarla.addGroup(spfC);

		// Definindo os produtos
		lorealDDCream 				= new Product(1, "L'oreal DD Cream", userJoao, ddCream,spfC);
		avonCCCream 				= new Product(2, "Avon CC Cream", userBeatriz, ccCream,spfB);
		revolutionPowderSunscreen 	= new Product(3, "Revolution Powder Sunscreen", userSuzana, powderSunscreen,spfB);
		maybellineBBCream 			= new Product(4, "Maybelline BB Cream", userNatasha, bbCream,spfB);
		revlonFoundationSPF20 		= new Product(5, "Revlon Foundation+SPF20", userPedro, foundationSPF,spfB);
		niveaMatteFaceSPF 			= new Product(6, "Nivea Matte Face SPF", userCarla, oilFreeMatteSPF,spfB);
		laRocheCCCream 				= new Product(7, "La Roche CC Cream", userBeatriz, ccCream,spfA);
		yvesRocherPowderSPF15 		= new Product(8, "Yves Rocher Powder+SPF15", userSuzana, powderSunscreen,spfA);
		niveaBBCream 				= new Product(9, "Nivea BB Cream", userNatasha, bbCream, spfA);
		baseOBoticarioSPF20 		= new Product(10, "Base O Boticario SPF20", userPedro, foundationSPF,spfA);
		naturaSPF20RostoMatte 		= new Product(11,"Natura SPF20 Rosto Matte",userCarla,oilFreeMatteSPF,spfA);
		
		productNotCovered			= new Product(12,"Produto Especial",userJoao,ccCream,groupNEUsers);
		
		spfA.addProduct(naturaSPF20RostoMatte);
		spfA.addProduct(baseOBoticarioSPF20);
		spfA.addProduct(niveaBBCream);
		spfA.addProduct(yvesRocherPowderSPF15);
		spfA.addProduct(laRocheCCCream);
		
		spfB.addProduct(niveaMatteFaceSPF);
		spfB.addProduct(revlonFoundationSPF20);
		spfB.addProduct(maybellineBBCream);
		spfB.addProduct(revolutionPowderSunscreen);
		spfB.addProduct(avonCCCream);
		
		spfC.addProduct(lorealDDCream);
		
		groupNEUsers.addProduct(productNotCovered);
		
		
	}

	//Testando o método allocate
	@Test
	public void allocateTestNormalAllocationFirstProduct() throws Exception{
		//O primeiro produto da lista de produtos é alocado
		spfA.allocate(2);
		assert(spfA.getEvaluations().get(laRocheCCCream).get(0).getReviewer().equals(userJoao));
	}
	@Test
	public void allocateTestNormalAllocationMiddleProduct() throws Exception{
		//Produtos internos da lista de produtos são alocados
		spfA.allocate(2);
		assert(spfA.getEvaluations().get(yvesRocherPowderSPF15).get(0).getReviewer().equals(userJoana));
	}
	@Test
	public void allocateTestNormalAllocationLastProduct() throws Exception{
		//O útlimo produto da lista de produtos é alocado
		spfA.allocate(2);
		assert(spfA.getEvaluations().get(naturaSPF20RostoMatte).get(1).getReviewer().equals(userMiguel));
	}
	@Test
	public void allocateTestNotEnoughUsersToCoverProduct() throws Exception{
		//Não aloca usuarios a mais quando faltam usuarios para completar a alocação
		groupNEUsers.allocate(3);
		assertTrue(groupNEUsers.getEvaluations().get(productNotCovered).size() < 3);
	}
	
	@Test(expected=Exception.class)
	public void allocateTestAlreadyAllocated() throws Exception{
		//Não pode alocar um grupo que já está alocado
		spfA.allocate(2);
		spfA.allocate(1);
	}

	//Testando o método evaluationDone
	@Test
	public void evaluationDoneTestComplete() throws Exception{
		//Evaluations foram completas
		groupNEUsers.allocate(2);
		Map<Product,List<Evaluation>> evaluations = groupNEUsers.getEvaluations();
		evaluations.get(productNotCovered).get(0).setScore(3);
		assertTrue(groupNEUsers.evaluationDone());
	}
	
	@Test
	public void evaluationDoneTestNotComplete() throws Exception{
		//Evaluations não foram completas
		groupNEUsers.allocate(2);
		assertFalse(groupNEUsers.evaluationDone());
	}
	
	@Test 
	public void evalluationDoneTestPartiallyComplete() throws Exception{
		spfA.allocate(2);
		Map<Product,List<Evaluation>> evaluations = spfA.getEvaluations();
		evaluations.get(laRocheCCCream).get(0).setScore(1);
		assertFalse(spfA.evaluationDone());
	}
	@Test
	public void evaluationDoneTestNoEvaluation() throws Exception{
		//Não há evaluations
		assertFalse(groupNEUsers.evaluationDone());
	}
	
	//Testando os métodos getAcceptableProducts e getNotAcceptableProducts
	@Test
	public void getAceptableProductsTestAllAccepted() throws Exception{
		//Todo produto é aceito
		groupNEUsers.allocate(2);
		Map<Product,List<Evaluation>> evaluations = groupNEUsers.getEvaluations();
		evaluations.get(productNotCovered).get(0).setScore(3);
		assert(groupNEUsers.getAcceptableProducts().contains(productNotCovered));
	}
	@Test
	public void getNotAceptableProductsTestAllAccepted() throws Exception{
		//Nenhum produto é rejeitado
		groupNEUsers.allocate(2);
		Map<Product,List<Evaluation>> evaluations = groupNEUsers.getEvaluations();
		evaluations.get(productNotCovered).get(0).setScore(3);
		assert(groupNEUsers.getNotAcceptableProducts().size() == 0);
	}
	@Test
	public void getAcceptableProductsTestSomeAccepted() throws Exception{
		spfA.allocate(2);
		Map<Product,List<Evaluation>> evaluations = spfA.getEvaluations();
		List<Product> expectedAcceptableProducts = new ArrayList<Product>();
		expectedAcceptableProducts.add(naturaSPF20RostoMatte);
		expectedAcceptableProducts.add(baseOBoticarioSPF20);
		expectedAcceptableProducts.add(yvesRocherPowderSPF15);
		expectedAcceptableProducts.add(laRocheCCCream);
		
		//Aceitavel
		evaluations.get(laRocheCCCream).get(0).setScore(2);
		evaluations.get(laRocheCCCream).get(1).setScore(3);
		//Aceitavel
		evaluations.get(yvesRocherPowderSPF15).get(0).setScore(1);
		evaluations.get(yvesRocherPowderSPF15).get(1).setScore(-1);
		//Nao aceitavel
		evaluations.get(niveaBBCream).get(0).setScore(0);
		evaluations.get(niveaBBCream).get(1).setScore(-2);
		//Aceitavel
		evaluations.get(baseOBoticarioSPF20).get(0).setScore(-1);
		evaluations.get(baseOBoticarioSPF20).get(1).setScore(2);
		//Aceitavel
		evaluations.get(naturaSPF20RostoMatte).get(0).setScore(1);
		evaluations.get(naturaSPF20RostoMatte).get(1).setScore(2);
		
		assert(spfA.getAcceptableProducts().equals(expectedAcceptableProducts));
	}
	
	@Test
	public void getNotAcceptableProductsTestSomeAccepted() throws Exception{
		spfA.allocate(2);
		Map<Product,List<Evaluation>> evaluations = spfA.getEvaluations();
		List<Product> expectedNotAcceptableProducts = new ArrayList<Product>();
		expectedNotAcceptableProducts.add(niveaBBCream);
		
		//Aceitavel
		evaluations.get(laRocheCCCream).get(0).setScore(2);
		evaluations.get(laRocheCCCream).get(1).setScore(3);
		//Aceitavel
		evaluations.get(yvesRocherPowderSPF15).get(0).setScore(1);
		evaluations.get(yvesRocherPowderSPF15).get(1).setScore(-1);
		//Nao aceitavel
		evaluations.get(niveaBBCream).get(0).setScore(0);
		evaluations.get(niveaBBCream).get(1).setScore(-2);
		
		//Aceitavel
		evaluations.get(baseOBoticarioSPF20).get(0).setScore(-1);
		evaluations.get(baseOBoticarioSPF20).get(1).setScore(2);
		//Aceitavel
		evaluations.get(naturaSPF20RostoMatte).get(0).setScore(1);
		evaluations.get(naturaSPF20RostoMatte).get(1).setScore(2);
		
		assert(spfA.getNotAcceptableProducts().equals(expectedNotAcceptableProducts));
	}
}

