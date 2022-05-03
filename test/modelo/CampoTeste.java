package modelo;

import static org.junit.Assert.assertTrue;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class CampoTeste {

	private Campo campo;

	@BeforeEach
	void iniciaCampo() {
		campo = new Campo(3, 3);
	}

	@Test
	void testeVisinhoRealDistancia1() {
		Campo vizinho = new Campo(3, 2);
		boolean resultado = campo.adicionarVisinho(vizinho);
		assertTrue(resultado);
	}
}
