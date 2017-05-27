package br.edu.utfpr.exercicio02;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class ClassificadorTest {

	/**
	 * Defino a classe que ser� testada unit�riamente.
	 */
	private Classificador classificador;

	/**
	 * Antes da execu��o de cada m�todo criamos sempre uma nova inst�ncia da
	 * classe a ser testada, com intuido de gerar problemas no teste devido a
	 * "sujeira" deixada por outros testes.
	 */
	@Before
	public void setup() {
		classificador = new Classificador();
	}

	/**
	 * Com este caso de teste validamos se ao passar uma idade inv�lida, ou
	 * seja, menor que zero, temos uma rejei��o do valor. Caso o valor seja
	 * rejeitado, receberemos uma exception do tipo IllegalArgumentException. Se
	 * uma exception do tipo IllegalArgumentException n�o for lan�ada pelo
	 * m�todo que estamos testando o teste falhar�.
	 * 
	 * (i) um valor de idade inv�lido;
	 */
	@Test(expected = IllegalArgumentException.class)
	public void deveriaValidarIdadeInvalidaMenorQueZero() {
		// Arrange
		Pessoa pessoa = new Pessoa("Altieres", -1);
		// Act
		classificador.definirFaixaEtaria(pessoa);
		// Assert
		Assert.fail("N�o deveria chegar at� aqui");
	}

	/**
	 * Com este caso de teste validamos se ao passar uma idade inv�lida, ou
	 * seja, maior que cento e dez, temos uma rejei��o do valor. Caso o valor
	 * seja rejeitado, receberemos uma exception do tipo
	 * IllegalArgumentException. Se uma exception do tipo
	 * IllegalArgumentException n�o for lan�ada pelo m�todo que estamos testando
	 * o teste falhar�.
	 * 
	 * (i) um valor de idade inv�lido;
	 */
	@Test(expected = IllegalArgumentException.class)
	public void deveriaValidarIdadeInvalidaMaiorQueCentoEDez() {
		// Arrange
		Pessoa pessoa = new Pessoa("Altieres", 111);
		// Act
		classificador.definirFaixaEtaria(pessoa);
		// Assert
		Assert.fail("N�o deveria chegar at� aqui");
	}

	/**
	 * Neste caso de teste informamos uma idade v�lida para faixa et�ria de
	 * crian�as e validamos se a mesma esta de acordo.
	 * 
	 * (ii) uma pessoa que � crian�a;
	 */
	@Test
	public void deveriaRetornarPessoaDoTipoCrianca() {
		// Arrange
		Pessoa pessoa = new Pessoa("Altieres", 8);
		// Act
		String faixaEtaria = classificador.definirFaixaEtaria(pessoa);
		// Assert
		Assert.assertEquals("Altieres eh crianca", faixaEtaria);
	}

	/**
	 * Neste caso de teste informamos uma idade v�lida para faixa et�ria de
	 * adolescentes e validamos se a mesma esta de acordo.
	 * 
	 * (iii) uma pessoa que � adolescente;
	 */
	@Test
	public void deveriaRetornarPessoaDoTipoAdolescente() {
		// Arrange
		Pessoa pessoa = new Pessoa("Altieres", 15);
		// Act
		String faixaEtaria = classificador.definirFaixaEtaria(pessoa);
		// Assert
		Assert.assertEquals("Altieres eh adolescente", faixaEtaria);
	}

	/**
	 * Neste caso de teste informamos uma idade v�lida para faixa et�ria de
	 * adultos e validamos se a mesma esta de acordo.
	 * 
	 * (iv) uma pessoa que � adulta;
	 */
	@Test
	public void deveriaRetornarPessoaDoTipoAdulto() {
		// Arrange
		Pessoa pessoa = new Pessoa("Altieres", 39);
		// Act
		String faixaEtaria = classificador.definirFaixaEtaria(pessoa);
		// Assert
		Assert.assertEquals("Altieres eh adulto", faixaEtaria);
	}

	/**
	 * Neste caso de teste informamos uma idade v�lida para faixa et�ria de
	 * idosos e validamos se a mesma esta de acordo.
	 * 
	 * (ii) uma pessoa que � idosa.
	 */
	@Test
	public void deveriaRetornarPessoaDoTipoIdoso() {
		// Arrange
		Pessoa pessoa = new Pessoa("Altieres", 60);
		// Act
		String faixaEtaria = classificador.definirFaixaEtaria(pessoa);
		// Assert
		Assert.assertEquals("Altieres eh idoso", faixaEtaria);
	}

}
