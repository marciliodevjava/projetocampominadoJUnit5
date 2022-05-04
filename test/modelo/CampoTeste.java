package modelo;

import static org.junit.Assert.assertFalse;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import excecao.ExplosaoException;

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

	@Test
	void testeValorPadraoAtributoMarcado() {
		assertFalse(campo.isMarcado());
	}

	@Test
	void testeAlternarMarcacao() {
		campo.alternarMarcacao();
		assertTrue(campo.isMarcado());
	}

	@Test
	void testeAlternarMarcacaoDuasChamadas() {
		campo.alternarMarcacao();
		campo.alternarMarcacao();
		assertFalse(campo.abrir());
	}

	@Test
	void testeAbrirNaoMinadoNaoMarcado() {
		assertTrue(campo.abrir());
	}

	@Test
	void testeAbrirNaoMinadoMarcado() {
		campo.alternarMarcacao();
		assertFalse(campo.abrir());
	}

	@Test
	void testeAbrirMinadoMarcado() {
		campo.alternarMarcacao();
		campo.minar();
		assertFalse(campo.abrir());
	}

	@Test
	void testeAbrirMinadoNaoMarcado() {
		campo.minar();
		assertThrows(ExplosaoException.class, () -> campo.abrir());
	}

	@Test
	void testeAbrirComVizinhos1() {
		Campo campo11 = new Campo(1, 1);
		Campo campo22 = new Campo(2, 2);
		campo22.adicionarVisinho(campo11);

		campo.adicionarVisinho(campo22);
		campo.abrir();

		assertTrue(campo22.isAberto() && campo11.isAberto());
	}

	@Test
	void testeAbrirComVizinhos2() {
		Campo campo11 = new Campo(1, 1);
		Campo campo12 = new Campo(1, 1);
		campo12.minar();

		Campo campo22 = new Campo(2, 2);
		campo22.adicionarVisinho(campo11);
		campo22.adicionarVisinho(campo12);

		campo.adicionarVisinho(campo22);
		campo.abrir();

		assertTrue(campo22.isAberto() && campo11.isFechado());
	}
}
