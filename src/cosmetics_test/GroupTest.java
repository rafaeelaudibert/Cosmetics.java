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

public class GroupTest {
	User userJoao, userAna, userManoela, userJoana, userMiguel, userBeatriz, userSuzana, userNatasha, userPedro, userCarla;
	List<User> spfAMembers, spfBMembers, spfCMembers;
	Category ddCream, ccCream, bbCream, powderSunscreen, foundationSPF, oilFreeMatteSPF;
	List<Category> categoryListEmpty, categoryListUser;
	Product lorealDDCream, avonCCCream, revolutionPowderSunscreen, maybellineBBCream, revlonFoundationSPF20, niveaMatteFaceSPF, laRocheCCCream, yvesRocherPowderSPF15, niveaBBCream, baseOBoticarioSPF20, naturaSPF20RostoMatte;
	List<Product> productListEmpty, productListSPFA, productListSPFB, productListSPFC;
	Group spfA, spfB, spfC;
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
	
		// Definindo listas de Usuários para teste
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
		userCarla 	= new User(10, "Carla", "Sp", categoryListUser);
			
		// Definindo listas de usu�rios para teste
		spfAMembers = new ArrayList<User>();
		spfAMembers.add(userJoao);
		spfAMembers.add(userAna);
		spfAMembers.add(userManoela);
		spfAMembers.add(userJoana);
		spfAMembers.add(userMiguel);
		spfAMembers.add(userBeatriz);
		spfAMembers.add(userSuzana);
		spfBMembers = new ArrayList<User>();
		spfBMembers.add(userJoao);
		spfBMembers.add(userAna);
		spfBMembers.add(userManoela);
		spfBMembers.add(userJoana);
		spfBMembers.add(userMiguel);
		spfBMembers.add(userBeatriz);
		spfBMembers.add(userSuzana);		
		spfCMembers = new ArrayList<User>();
		spfCMembers.add(userJoana);
		spfCMembers.add(userMiguel);
		spfCMembers.add(userBeatriz);
		spfCMembers.add(userSuzana);
		spfCMembers.add(userNatasha);
		spfCMembers.add(userPedro);
		spfCMembers.add(userCarla);
		
		
		// Definindo os grupos de usuarios
		lorealDDCream 				= new Product(1, "L'oreal DD Cream", userJoao, ddCream);
		avonCCCream 				= new Product(2, "Avon CC Cream", userBeatriz, ccCream);
		avonCCCream.setGroup(spfB);
		revolutionPowderSunscreen 	= new Product(3, "Revolution Powder Sunscreen", userSuzana, powderSunscreen);
		maybellineBBCream 			= new Product(4, "Maybelline BB Cream", userNatasha, bbCream);
		revlonFoundationSPF20 		= new Product(5, "Revlon Foundation+SPF20", userPedro, foundationSPF);
		niveaMatteFaceSPF 			= new Product(6, "Nivea Matte Face SPF", userCarla, oilFreeMatteSPF);
		laRocheCCCream 				= new Product(7, "La Roche CC Cream", userBeatriz, ccCream);
		yvesRocherPowderSPF15 		= new Product(8, "Yves Rocher Powder+SPF15", userSuzana, powderSunscreen);
		niveaBBCream 				= new Product(9, "Nivea BB Cream", userNatasha, bbCream);
		baseOBoticarioSPF20 		= new Product(10, "Base O Boticario SPF20", userPedro, foundationSPF);
		naturaSPF20RostoMatte 		= new Product(11, "Natura SPF20 Rosto Matte", userCarla, oilFreeMatteSPF);
		productListSPFA 			= new ArrayList<Product>();
		productListSPFB				= new ArrayList<Product>();
		productListSPFC				= new ArrayList<Product>();
		productListSPFA.add(naturaSPF20RostoMatte);
		productListSPFA.add(baseOBoticarioSPF20);
		productListSPFA.add(niveaBBCream);
		productListSPFA.add(yvesRocherPowderSPF15);
		productListSPFA.add(laRocheCCCream);
		productListSPFB.add(niveaMatteFaceSPF);
		productListSPFB.add(revlonFoundationSPF20);
		productListSPFB.add(maybellineBBCream);
		productListSPFB.add(revolutionPowderSunscreen);
		productListSPFB.add(avonCCCream);
		productListSPFC.add(lorealDDCream);
		
		spfA = new Group("SPF A", productListSPFA, spfAMembers);
		spfB = new Group("SPF B", productListSPFB, spfBMembers);
		spfC = new Group("SPF C", productListSPFC, spfCMembers);
		
		// Adicionando usuários aos grupos (não precisamos pegar a exceção pois é garantido disso funcionar)
		userJoao.addGroup(spfA);
		userJoao.addGroup(spfB);
		userAna.addGroup(spfA);
		userAna.addGroup(spfB);
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
		userCarla.addGroup(spfC);
		
		//Definindo evaluations
		//evaluation1 = new Evaluation();
			
//		spfB.allocate(2);
//		avonCCCream.addScore(userJoao, 2);
//		avonCCCream.addScore(userAna, 2);
//		revolutionPowderSunscreen.addScore(userJoana, -1);
//		revolutionPowderSunscreen.addScore(userBeatriz, 1);
//		maybellineBBCream.addScore(userJoao, 1);
//		maybellineBBCream.addScore(userManoela, 0);
//		revlonFoundationSPF20.addScore(userJoana, -3);
//		revlonFoundationSPF20.addScore(userMiguel, -3);
//		niveaMatteFaceSPF.addScore(userManoela, -1);
//		niveaMatteFaceSPF.addScore(userMiguel, 0);
		

	}
		
	@Test	
	public void allocateTest() throws Exception {
		Group group = spfA;
		int numMembers = 3;
		group.allocate(numMembers);				
		
		System.out.println("Grupo: " + group.getName() + " Numero de Membros Alocados: " + numMembers + "\n");
		assertTrue(group.testAllocate());
	}
	
	@Test
	public void getOrderedCandidateReviewersTest() {
		ArrayList<User> ordered_users = new ArrayList<User>();
		ordered_users.add(userJoao);
		ordered_users.add(userAna);
		ordered_users.add(userSuzana);
		
		assertTrue(spfB.getOrderedCandidateReviewers(avonCCCream).equals(ordered_users)); // Initial ordering
		
		Evaluation evaluation = new Evaluation(userJoao, laRocheCCCream, spfB);
		userJoao.addEvaluation(evaluation);
		laRocheCCCream.addEvaluation(evaluation);
		ordered_users.remove(0);
		ordered_users.add(userJoao);
		
		assertTrue(spfB.getOrderedCandidateReviewers(avonCCCream).equals(ordered_users)); // Joao has a product assigned, so it goes to the end of the list
		
		Evaluation evaluation2 = new Evaluation(userSuzana, avonCCCream, spfB);
		userSuzana.addEvaluation(evaluation2);
		avonCCCream.addEvaluation(evaluation2);
		ordered_users.remove(1);
		
		System.out.println(ordered_users);
		System.out.println(spfB.getOrderedCandidateReviewers(avonCCCream));
		
		assertTrue(spfB.getOrderedCandidateReviewers(avonCCCream).equals(ordered_users)); // Suzana has the current product assigned, so it should be not in the list anymore
		
	}

}

