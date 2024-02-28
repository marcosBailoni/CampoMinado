package campo_minado.modelo;

import java.util.List;

import campo_minado.excecao.ExplosaoException;

import java.util.ArrayList;

public class Campo {
	
	//Constantes:
	private final int linha;
	private final int coluna;
	
	//Variaveis
	private boolean aberto;
	private boolean minado;
	private boolean marcado;
		
	//Construtor:
	Campo(int linha, int coluna) {
		this.linha = linha;
		this.coluna = coluna;
	}
	
	//Gets abaixo: 
	public boolean isMinado() {
		return minado;
	}
	
	public boolean isMarcado() {
		return marcado;
	}
	
	public boolean isAberto() {
		return aberto;
	}
	
	public boolean isFechado() {
		return !aberto;
	}

	public int getLinha() {
		return linha;
	}

	public int getColuna() {
		return coluna;
	}
	
	//Lista vizinhos:
	private List<Campo> vizinhos = new ArrayList<>();
	
	//Método para definir os campos vizinhos de um campo:
	boolean adicionarVizinho (Campo vizinho) {
		
		// verificar se são campos diferentes ou se é o mesmo
		boolean camposDiferentes = linha != vizinho.linha || coluna != vizinho.coluna; 
		
		//verificar se as linhas (x) são no máximo -1 (voltando uma pra esquerda) ou 1 (indo uma pra direita)
		boolean diferencaLinha = linha - vizinho.linha >= -1 && linha - vizinho.linha <= 1;
		
		//verificar se as colunas (y) são no máximo -1 (voltando uma pra cima) ou +1 (avançando uma pra baixo)
		boolean diferencaColuna = coluna - vizinho.coluna >= -1 && coluna - vizinho.coluna <= 1;
		
		if (diferencaLinha && diferencaColuna && camposDiferentes) {
			vizinhos.add(vizinho);
			return true;
		} else {
			return false;
		}
	}
	
	//Se o campo estiver fechado == mudar o status de marcado:
	void alternarMarcacao() {
		if(!aberto) {
			marcado = !marcado;
		} 
	}
	
//	Metodo para abrir um campo: 
//	verificar se está fechado e não pode estar marcado, 
//	se estiver minado, lançar uma explosão exception
//	verificar se algum vizinho é minado, caso não seja, abrir todo os vizinhos e rodar o abrir em cada um deles (rodar tudo isso novamente para cada vizinho) 		
//	caso abra, retornar verdadeiro
//	caso não abra, retornar falso
	
	
	boolean abrir() {
		if(!aberto && !marcado) {
			aberto = true;
			
			if(minado) {
				throw new ExplosaoException();
				
			}
			
			if(vizinhancaSegura()) {
				vizinhos.forEach(v -> v.abrir());
			}
			
			return true;
			
		} else {
			return false;
		}
	}
	
	boolean vizinhancaSegura() {
		return vizinhos.stream().noneMatch(v -> v.minado);		
	}
	
	void minar() {
		
		minado = true;
	}
	
	
	boolean objetivoAlcancado() {
		boolean desvendado = !minado && aberto;
		boolean protegido = minado && marcado;
		return desvendado || protegido;
	}
	
	long minasNaVizinhanca() {
		return vizinhos.stream().filter(v -> v.minado).count();
		
	}
	
	void reiniciar() {
		aberto = false;
		minado = false;
		marcado = false;
	}
	
	public String toString() {
		if(marcado) {
			return "x";
			
		} else if (aberto && minado) {
			return "*";
			
		} else if (aberto && minasNaVizinhanca() > 0) {
			return Long.toString(minasNaVizinhanca());
			
		} else if(aberto) {
			return " ";
		} else {
			return "?";
		}
	}
 }

