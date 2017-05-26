package br.edu.utfpr.exercicio02;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class ClassificadorTest {
	
	private Classificador classificador;
	
	@Before
	public void setup() {
		classificador = new Classificador();
	}

	@Test(expected = IllegalArgumentException.class)
	public void deveriaValidarIdadeInvalidaMenorQueZero() {
		Pessoa pessoa = new Pessoa("Altieres", -1);
		classificador.definirFaixaEtaria(pessoa);
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void deveriaValidarIdadeInvalidaMaiorQueCentoEDez() {
		Pessoa pessoa = new Pessoa("Altieres", 111);
		classificador.definirFaixaEtaria(pessoa);
	}
	
	@Test
	public void deveriaRetornarPessoaDoTipoCrianca() {
		Pessoa pessoa = new Pessoa("Altieres", 8);
		String faixaEtaria = classificador.definirFaixaEtaria(pessoa);
		Assert.assertEquals("Altieres eh crianca", faixaEtaria);
	}
	
	@Test
	public void deveriaRetornarPessoaDoTipoAdolescente() {
		Pessoa pessoa = new Pessoa("Altieres", 15);
		String faixaEtaria = classificador.definirFaixaEtaria(pessoa);
		Assert.assertEquals("Altieres eh adolescente", faixaEtaria);
	}
	
	@Test
	public void deveriaRetornarPessoaDoTipoAdulto() {
		Pessoa pessoa = new Pessoa("Altieres", 39);
		String faixaEtaria = classificador.definirFaixaEtaria(pessoa);
		Assert.assertEquals("Altieres eh adulto", faixaEtaria);
	}
	
	@Test
	public void deveriaRetornarPessoaDoTipoIdoso() {
		Pessoa pessoa = new Pessoa("Altieres", 60);
		String faixaEtaria = classificador.definirFaixaEtaria(pessoa);
		Assert.assertEquals("Altieres eh idoso", faixaEtaria);
	}

}
