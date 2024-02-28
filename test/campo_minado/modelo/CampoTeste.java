package campo_minado.modelo;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import campo_minado.excecao.ExplosaoException;





public class CampoTeste {
	
	private Campo campo;
	
	@BeforeEach
	void iniciarCampo() {
		campo = new Campo(3, 3);
	}
	
	@Test
	void TesteVizinhoRealDistanciaEsquerda() {
		Campo vizinho = new Campo(3,2);
		boolean resultado = campo.adicionarVizinho(vizinho);
		assertTrue(resultado);
	}
		
	@Test
	void testevizinhoRealDistancia1Direita() {
		Campo vizinho = new Campo (3, 4);
		boolean resultado = campo.adicionarVizinho(vizinho);
		assertTrue(resultado);
	}
	
	@Test
	void testevizinhoRealDistancia1Cima() {
		Campo vizinho = new Campo (2, 3);
		boolean resultado = campo.adicionarVizinho(vizinho);
		assertTrue(resultado);
	}
	@Test
	void testevizinhoRealDistancia1Baixo() {
		Campo vizinho = new Campo (4, 3);
		boolean resultado = campo.adicionarVizinho(vizinho);
		assertTrue(resultado);
	}
	
	@Test
	void TesteValorPadraoAtributoMarcado() {
		assertFalse(campo.isMarcado());
	}

	@Test
	void TesteAlternarMarcado() {
		campo.alternarMarcacao();
		assertTrue(campo.isMarcado());
	}
	
	@Test
	void TesteAlternarMarcadoDuasChamadas() {
		campo.alternarMarcacao();
		campo.alternarMarcacao();
		assertFalse(campo.isMarcado());
	}
	
	@Test
	void TesteAbrirCampoNaoMarcadoNaoMinado() {
		assertTrue(campo.abrir());
	}
	
	@Test
	void TesteAbrirCampoMarcadoNaoMinado() {
		campo.alternarMarcacao();
		assertFalse(campo.abrir());
		
	}
	@Test
	void TesteAbrirCampoMarcadoMinado() {
		campo.alternarMarcacao();
		campo.minar();
		assertFalse(campo.abrir());
	}
	
	@Test
	void TesteAbrirCampoNaoMarcadoMinado() {
		campo.minar();
		
		assertThrows(ExplosaoException.class, () -> {
			assertFalse(campo.abrir());
		});
		
	}
	
	
	//Abrir campo 33 e verificar se o campo 22 abre e em seguida campo 11 abre 
	@Test
	void TesteAbrirCamposVizinhos() {
		
		Campo campo11 = new Campo(1,1);
		Campo campo22 = new Campo(2,2);
		
		campo.adicionarVizinho(campo22);
		campo22.adicionarVizinho(campo11);
		
		campo.abrir();
		assertTrue(campo11.isAberto() && campo22.isAberto());
	}
	
	@Test
	void TesteAbrirCamposVizinhos2() {
		
		Campo campo11 = new Campo(1,1);
		Campo campo12 = new Campo(1,2);
		campo12.minar();
		
		Campo campo22 = new Campo(2,2);
		
		campo22.adicionarVizinho(campo11);
		campo22.adicionarVizinho(campo12);
		
		
		campo.adicionarVizinho(campo22);
		campo.abrir();
		
		assertTrue(campo11.isFechado() && campo22.isAberto());
	}
	@Test
	void TesteGetLinha() {
		assertTrue(campo.getLinha() == 3);
	}
	@Test
	void TesteGetColuna() {
		assertTrue(campo.getColuna() == 3);
	}
	
	@Test
	void TesteObjetivoAlcandoCampo() {
		campo.minar();
		campo.alternarMarcacao();
		assertTrue(campo.objetivoAlcancado());
	}
	@Test
	void TesteObjetivoAlcandoCampo2() {
		campo.abrir();
		assertTrue(campo.objetivoAlcancado());
	}
	
	@Test
	void TesteMinasVizinhas( ) {
		Campo campo22 = new Campo(2,2);
		campo22.minar();
		assertTrue(campo22.isMinado());

		campo.adicionarVizinho(campo22);
		assertTrue(campo.minasNaVizinhanca() == 1);
		
	}
	@Test
	void TesteRetornoString() {
		campo.alternarMarcacao();
		assertTrue(campo.toString() == "x");
	}
	
	@Test
	void TesteRetornoString2() {
		try {
			campo.minar();
			campo.abrir();
		} catch (ExplosaoException e) {
			e.getMessage();
		}
		
		assertTrue(campo.isAberto());
		assertTrue(campo.toString() == "*");
		
	}
	
	
	
	@Test
	void TesteRetornoString3() {
		Campo campo22 = new Campo (2, 2);
		campo22.minar();		
		
		campo.adicionarVizinho(campo22);		
		campo.abrir();
		
		assertEquals("1", campo.toString());
		

	}
	
	@Test
	void TesteRetornoString4() {
		campo.abrir();
		
		assertEquals(" ", campo.toString());
	}
	
	@Test
	void TesteToString5() {
		assertEquals("?", campo.toString());
	}
	
	@Test
	void TesteReiniciando() {
		campo.reiniciar();
		boolean testeAberto = campo.isAberto();
		boolean testeMinado= campo.isMinado();
		boolean testeMarcado = campo.isMarcado();
		
		assertFalse(testeAberto && testeMinado && testeMarcado);
	}
		
}