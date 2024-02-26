package campo_minado.modelo;

import java.util.List;

import campo_minado.excecao.ExplosaoException;

import java.util.ArrayList;

public class Campo {

	private final int linha;
	private final int coluna;
	
	
	private boolean aberto;
	private boolean minado;
	private boolean marcado;
	
	private List<Campo> vizinhos = new ArrayList<>();
	
	Campo(int linha, int coluna) {
		this.linha = linha;
		this.coluna = coluna;
	}
	
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
	
	
	public boolean isMarcado() {
		return marcado;
	}
	
	public boolean isAberto() {
		return aberto;
	}
	
	public boolean isFechado() {
		return !aberto;
	}
 }

