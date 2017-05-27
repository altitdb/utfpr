package br.edu.utfpr.exercicio02;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class ClassificadorTest {

	/**
	 * Defino a classe que será testada unitáriamente.
	 */
	private Classificador classificador;

	/**
	 * Antes da execução de cada método criamos sempre uma nova instância da
	 * classe a ser testada, com intuido de gerar problemas no teste devido a
	 * "sujeira" deixada por outros testes.
	 */
	@Before
	public void setup() {
		classificador = new Classificador();
	}

	/**
	 * Com este caso de teste validamos se ao passar uma idade inválida, ou
	 * seja, menor que zero, temos uma rejeição do valor. Caso o valor seja
	 * rejeitado, receberemos uma exception do tipo IllegalArgumentException. Se
	 * uma exception do tipo IllegalArgumentException não for lançada pelo
	 * método que estamos testando o teste falhará.
	 * 
	 * (i) um valor de idade inválido;
	 */
	@Test(expected = IllegalArgumentException.class)
	public void deveriaValidarIdadeInvalidaMenorQueZero() {
		// Arrange
		Pessoa pessoa = new Pessoa("Altieres", -1);
		// Act
		classificador.definirFaixaEtaria(pessoa);
		// Assert
		Assert.fail("Não deveria chegar até aqui");
	}

	/**
	 * Com este caso de teste validamos se ao passar uma idade inválida, ou
	 * seja, maior que cento e dez, temos uma rejeição do valor. Caso o valor
	 * seja rejeitado, receberemos uma exception do tipo
	 * IllegalArgumentException. Se uma exception do tipo
	 * IllegalArgumentException não for lançada pelo método que estamos testando
	 * o teste falhará.
	 * 
	 * (i) um valor de idade inválido;
	 */
	@Test(expected = IllegalArgumentException.class)
	public void deveriaValidarIdadeInvalidaMaiorQueCentoEDez() {
		// Arrange
		Pessoa pessoa = new Pessoa("Altieres", 111);
		// Act
		classificador.definirFaixaEtaria(pessoa);
		// Assert
		Assert.fail("Não deveria chegar até aqui");
	}

	/**
	 * Neste caso de teste informamos uma idade válida para faixa etária de
	 * crianças e validamos se a mesma esta de acordo.
	 * 
	 * (ii) uma pessoa que é criança;
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
	 * Neste caso de teste informamos uma idade válida para faixa etária de
	 * adolescentes e validamos se a mesma esta de acordo.
	 * 
	 * (iii) uma pessoa que é adolescente;
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
	 * Neste caso de teste informamos uma idade válida para faixa etária de
	 * adultos e validamos se a mesma esta de acordo.
	 * 
	 * (iv) uma pessoa que é adulta;
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
	 * Neste caso de teste informamos uma idade válida para faixa etária de
	 * idosos e validamos se a mesma esta de acordo.
	 * 
	 * (ii) uma pessoa que é idosa.
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
