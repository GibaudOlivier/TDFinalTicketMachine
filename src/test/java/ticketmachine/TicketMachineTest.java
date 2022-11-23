package ticketmachine;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.*;

public class TicketMachineTest {
	private static final int PRICE = 50; // Une constante

	private TicketMachine machine; // l'objet à tester

	@BeforeEach
	public void setUp() {
		machine = new TicketMachine(PRICE); // On initialise l'objet à tester
	}

	@Test
	// On vérifie que le prix affiché correspond au paramètre passé lors de l'initialisation
	// S1 : le prix affiché correspond à l’initialisation.
	public void priceIsCorrectlyInitialized() {
		// Paramètres : valeur attendue, valeur effective, message si erreur
		assertEquals(PRICE, machine.getPrice(), "Initialisation incorrecte du prix");
	}

	@Test
	// S2 : la balance change quand on insère de l’argent
	public void insertMoneyChangesBalance() {
		machine.insertMoney(10);
		machine.insertMoney(20);
                // Les montants ont été correctement additionnés  
		assertEquals(10 + 20, machine.getBalance(), "La balance n'est pas correctement mise à jour");              
	}
	@Test
	//S3 : On n'imprime pas le ticket si le montant inséré est insuffisament.
	public void nImprimePasSIPasAssezDArgent() {
		machine.insertMoney(PRICE-1);
		assertFalse(machine.printTicket(),"Pas assez d'argent, la machine ne doit pas imprimer");
	}
	@Test
	//S4 On imprime le ticket si le montant inséré est suffisant
	public void imprimeSiSuffisant(){
		machine.insertMoney(PRICE);
		assertTrue(machine.printTicket(), "Ticket Ok");
	}
	@Test
	//S5 Quand on imprime un ticket la balance est décrémentée du prix du ticket
	public void imprimeEtDecremente(){
		machine.insertMoney(PRICE+50);
		machine.printTicket();
		assertEquals(machine.getBalance(),50);
	}
	@Test
	//S6 Le montant collecté est mis à jour quand on imprime un ticket(pas avant)
	public void imprimeEtMaJ(){
		machine.insertMoney(PRICE+50);
		assertEquals(machine.getBalance(),PRICE+50);
	}
	@Test
	//S7 refund() rend correctement la monnaie
	public void refundRenduCorrect(){
		machine.insertMoney(50);
		assertEquals(machine.refund(),50);
	}
	@Test
	//S8 refund() remet la balance à zéro
	public void refundRemiseAZero(){
		machine.insertMoney(50);
		machine.refund();
		assertEquals(machine.getBalance(),0);
	}

}
