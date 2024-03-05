package campo_minado.visao;

import java.util.Scanner;

import campo_minado.excecao.SairException;
import campo_minado.modelo.Tabuleiro;

public class TabuleiroConsole {

	private Tabuleiro tabuleiro;
	private Scanner entrada = new Scanner(System.in);

	public TabuleiroConsole(Tabuleiro tabuleiro) {
		this.tabuleiro = tabuleiro;

		executarJogo();
	}

	private void executarJogo() {	
		try {
			boolean continuar = true;
			
			while (continuar) {
				
			}
				System.out.println("Outra partida ? (S/n");
				String resposta = entrada.nextLine();
				
				if(resposta.equalsIgnoreCase("n")) {
					continuar = false;
				} else {	
					tabuleiro.reiniciar();
				} 				
			} catch (SairException e) {
				System.out.println("Tchau!!!");
			} finally {
				entrada.close();
			}
		}	
	}