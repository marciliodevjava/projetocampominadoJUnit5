package modelo;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class CampoTeste {

	private Campo campo;

	@BeforeEach
	void iniciaCampo() {
		campo = new Campo(3, 3);
	}

	@Test
	void testeVisinhoRealDistancia1Esquerda() {
		Campo vizinho = new Campo(3, 2);
		boolean resultado = campo.adicionarVisinho(vizinho);
		assertTrue(resultado);
	}

	@Test
	void testeVisinhoDistancia1Direita() {
		Campo vizinho = new Campo(3, 4);
		boolean resultado = campo.adicionarVisinho(vizinho);
		assertTrue(resultado);
	}

	@Test
	void testeVisinhoDistancia1EmCima() {
		Campo vizinho = new Campo(2, 3);
		boolean resultado = campo.adicionarVisinho(vizinho);
		assertTrue(resultado);
	}

	@Test
	void testeVisinhoDistancia1EmBaixo() {
		Campo vizinho = new Campo(4, 3);
		boolean resultado = campo.adicionarVisinho(vizinho);
		assertTrue(resultado);
	}
	
	@Test
	void testeVisinhoDistancia2() {
		Campo vizinho = new Campo(2, 2);
		boolean resultado = campo.adicionarVisinho(vizinho);
		assertTrue(resultado);
	}
	
	@Test
	void testeNãoVisinhoDistancia() {
		Campo vizinho = new Campo(1, 1);
		boolean resultado = campo.adicionarVisinho(vizinho);
		assertFalse(resultado);
	}
}
