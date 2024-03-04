package campo_minado.modelo;

import java.util.ArrayList;

import java.util.List;
import java.util.function.Predicate;



public class Tabuleiro {
	
	private int linhas;
	private int colunas;
	private int minas;
	
	private final List<Campo> campos = new ArrayList<>();
	
	public Tabuleiro(int linhas, int colunas, int minas) {
		this.linhas = linhas;
		this.colunas = colunas;
		this.minas = minas;

		gerarCampos();
		associarOsVizinhos();
		sortearMinas();
	}
	
	public void abrir(int linha, int coluna) {
		campos.parallelStream()
		.filter(c -> c.getLinha() == linha && c.getColuna() == coluna)
		.findFirst()
		.ifPresent(c -> c.abrir());
	}

	public void alternarMarcarcao(int linha, int coluna) {
		campos.parallelStream()
		.filter(c -> c.getLinha() == linha && c.getColuna() == coluna)
		.findFirst()
		.ifPresent(c -> c.alternarMarcacao());
	}
	
	private void gerarCampos() {
		for(int linha = 0; linha < linhas; linha++) {
			for(int coluna = 0; coluna < colunas; coluna++) {
				campos.add(new Campo(linha, coluna));
			}
		}
	}
	
//	metodo abaixo: 
//	Para cada campo (chamado de c1) em campos (lista)
//	Percorra cada campo (chamado de c2) em campos (lista)
//	Adicione cada campo da segunda pesquisa como vizinho de cada campo da primeira pesquisa 
//	Campo 0 adicionar vizinho, campo 0 // campo 0 adicionar vizinho campo1...... 
//	campo 1 adicionar vizinho, campo 0 // campo 1 adicionar vizinho campo 1.......
//	........
//	em código: c2, adicionado como vizinho de c1
//	
//	O metodo abaixo em resumo é a mesma coisa que: 
	
//	private void associarOsVizinhos() {
//	    for(int i = 0; i < campos.length; i++) {
//	        Campo c1 = campos[i];
//	        for(int j = 0; j < campos.length; j++) {
//	            Campo c2 = campos[j];
//	            c1.adicionarVizinho(c2);
//	        }
//	    }
//	}
	private void associarOsVizinhos() {
		for(Campo c1: campos) {
			for(Campo c2: campos) {
				c1.adicionarVizinho(c2);
			}
		}
	}

	private void sortearMinas() {
		long minasArmadas = 0;
		Predicate<Campo> minado = c -> c.isMinado();
		
		do {
			minasArmadas = campos.stream().filter(minado).count();
			int aleatorio = (int) (Math.random() * campos.size());
			campos.get(aleatorio).minar();
		} while(minasArmadas < minas);
	}
	
	public boolean objetivoAlcancado() {
		return campos.stream().allMatch(c -> c.objetivoAlcancado());
	}
	
	public void reiniciar() {
		campos.stream().forEach(c -> c.reiniciar());
		sortearMinas();
	}
	
	public String toString() {
		StringBuilder sb = new StringBuilder();
		
		int i = 0;
		for (int l = 0; l < linhas; l++) {
			
			for (int c = 0; c < colunas; c++) {
				sb.append(" ");
				sb.append(campos.get(i));
				sb.append(" ");	
				i++;
			}
			sb.append("\n");
		}
		
		return sb.toString();
	}
}
