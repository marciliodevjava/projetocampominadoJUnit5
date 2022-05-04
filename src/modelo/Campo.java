package modelo;

import java.util.ArrayList;
import java.util.List;

import excecao.ExplosaoException;

public class Campo {

	private final int linha;
	private final int coluna;

	private boolean aberto = false;
	private boolean minado = false;
	private boolean marcado = false;

	private List<Campo> vizinhos = new ArrayList<>();

	Campo(int linha, int coluna) {
		this.linha = linha;
		this.coluna = coluna;
	}

	boolean adicionarVisinho(Campo vizinho) {
		boolean linhaDiferente = this.linha != vizinho.linha;
		boolean colunhaDiferente = this.coluna != vizinho.coluna;
		boolean diagonal = linhaDiferente && colunhaDiferente;

		int deltaLinha = Math.abs(this.linha - vizinho.linha);
		int deltaColunha = Math.abs(this.coluna - vizinho.coluna);
		int deltaGeral = deltaColunha + deltaLinha;

		if (deltaGeral == 1 && !diagonal) {
			vizinhos.add(vizinho);
			return true;
		} else if (deltaGeral == 2 && diagonal) {
			vizinhos.add(vizinho);
			return true;
		} else {
			return false;
		}
	}

	void alternarMarcacao() {
		if (!this.aberto) {
			this.marcado = !this.marcado;
		}
	}

	boolean abrir() {
		if (!this.aberto && !this.marcado) {
			this.aberto = true;

			if (this.minado) {
				throw new ExplosaoException();
			}
			if (viziancaSegura()) {
				vizinhos.forEach(v -> v.abrir());
			}
			return true;
		} else {
			return false;
		}
	}

	boolean viziancaSegura() {
		return vizinhos.stream().noneMatch(v -> v.minado);
	}

	void minar() {
		this.minado = true;
	}
	
	public boolean isMinado() {
		return this.minado;
	}

	public boolean isMarcado() {
		return this.marcado;
	}

	public boolean isAberto() {
		return this.aberto;
	}

	public boolean isFechado() {
		return !isAberto();
	}

	public int getLinha() {
		return linha;
	}

	public int getColuna() {
		return coluna;
	}

	boolean objetivoAlcancado() {
		boolean desvendado = !this.minado && this.aberto;
		boolean protegido = this.minado && this.marcado;
		return desvendado || protegido;
	}

	long minasNaVizianca() {
		return vizinhos.stream().filter(v -> v.minado).count();
	}

	void reiniciar() {
		this.aberto = false;
		this.minado = false;
		this.marcado = false;
	}

	public String toString() {
		if (this.aberto) {
			return "x";
		} else if (this.aberto && this.minado) {
			return "*";
		} else if (this.aberto && minasNaVizianca() > 0) {
			return Long.toString(minasNaVizianca());
		} else if (this.aberto) {
			return " ";
		} else {
			return "?";
		}
	}

}
