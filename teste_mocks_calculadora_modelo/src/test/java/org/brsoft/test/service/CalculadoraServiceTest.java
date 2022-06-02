package org.brsoft.test.service;

import java.security.InvalidParameterException;

import org.brsoft.entity.Calculadora;
import org.brsoft.service.CalculadoraService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentMatchers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ExtendWith(SpringExtension.class)
public class CalculadoraServiceTest {

	// A classe que recebe a injeção de dependencia é aquela que será testada por
	// essa classe de testa
	@InjectMocks
	private CalculadoraService servico;

	@Mock
	private Calculadora calc;

	@BeforeEach
	public void configuraMocks() {
		// cenário de teste necessário para a classe Calculadora(Mock)
		// Mockito.when(métodos e suas entradas).thenReturn(resultadoEsperado)
		Mockito.when(calc.somar(3, 2)).thenReturn(5.0);
		Mockito.when(calc.somar(2, 2)).thenReturn(2.0);
		Mockito.when(calc.somar(1, 4)).thenReturn(4.0);
		// Mockito.doThrow(resultadoEsperado).when(calc).metodo(entradas);
		//testerCalculoRetornaExceptionParaN1IgualZero
		Mockito.doThrow(InvalidParameterException.class).when(calc).somar(0, 1);
		//testerCalculoRetornaExceptionParaN2IgualZero
		Mockito.doThrow(InvalidParameterException.class).when(calc).somar(1, 0);
		Mockito.doThrow(InvalidParameterException.class).when(calc).somar(0, 0);
	}

	@Test
	public void testerCalculoN1MaiorN2() {
		// cenário de teste do método calculo da classe CalculadoraService
		double n1 = 3;
		double n2 = 2;
		double resultadoEsperado = 50;
		// execução e a comparação
		Assertions.assertEquals(resultadoEsperado, servico.calculo(n1, n2));
		Mockito.verify(calc, Mockito.times(1)).somar(3, 2);
	}

	@Test
	public void testerCalculoN1IgualN2() {
		// cenário de teste do método calculo da classe CalculadoraService
		double n1 = 2;
		double n2 = 2;
		double resultadoEsperado = 20;
		// execução e a comparação
		Assertions.assertEquals(resultadoEsperado, servico.calculo(n1, n2));
		Mockito.verify(calc, Mockito.times(1)).somar(2, 2);
	}	
	
	
	@Test
	public void testerCalculoN1MenorN2() {
		// cenário de teste do método calculo da classe CalculadoraService
		double n1 = 1;
		double n2 = 4;
		double resultadoEsperado = 40;
		// execução e a comparação
		Assertions.assertEquals(resultadoEsperado, servico.calculo(n1, n2));
		Mockito.verify(calc, Mockito.times(1)).somar(1, 4);
	}		
	
	@Test
	public void testerCalculoRetornaExceptionParaN1IgualZero() {
		// cenário de teste do método calculo da classe CalculadoraService
		double n1 = 0;
		double n2 = 1;
		Assertions.assertThrows(InvalidParameterException.class, ()->{servico.calculo(n1, n2);});
		Mockito.verify(calc, Mockito.times(1)).somar(0, 1);
	}		
}
