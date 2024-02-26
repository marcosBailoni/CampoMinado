package campo_minado.modelo;

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
}