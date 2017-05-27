package br.edu.utfpr.exercicio03;

import java.util.ArrayList;

import org.junit.Assert;
import org.junit.Test;
import org.mockito.Mockito;

public class RelatorioDeFuncionariosTest {

	/**
	 * Defino a classe que será testada unitáriamente.
	 */
	private RelatorioDeFuncionarios relatorioDeFuncionarios;

	/**
	 * Caso de teste: Existem 2 funcionários na categoria "tecnico" que não
	 * estão com o CPF bloqueado.
	 */
	@Test
	public void casoDeTeste01() {
		// Arrange
		// Crio um mock da interface FuncionarioDAO visto que não tenho nenhuma
		// implementação dela
		FuncionarioDAO funcionarioDao = Mockito.mock(FuncionarioDAO.class);
		// Crio uma lista de funcionários que serão utilizados pelo mock
		ArrayList<Funcionario> funcionarios = new ArrayList<Funcionario>();
		// Crio a funcionária Mariana
		Funcionario mariana = new Funcionario();
		mariana.setCpf("111111111-11");
		// Adiciono a funcionária mariana na lista de funcionários
		funcionarios.add(mariana);
		Funcionario larissa = new Funcionario();
		larissa.setCpf("222222222-22");
		funcionarios.add(larissa);
		Funcionario glauco = new Funcionario();
		glauco.setCpf("333333333-33");
		funcionarios.add(glauco);
		// Configuro o mock da interface FuncionarioDAO para retornar a lista de
		// funcionários quando o método getFuncionariosBy for invocado com o
		// parâmetro tecnico
		Mockito.when(funcionarioDao.getFuncionariosBy("tecnico")).thenReturn(funcionarios);
		// Crio uma instância da classe que será testada unitáriamente passando
		// o mock da interface FuncionarioDAO no construtor
		relatorioDeFuncionarios = new RelatorioDeFuncionarios(funcionarioDao);
		// Crio um mock da interface ReceitaFederal visto que não tenho nenhuma
		// implementação dela
		ReceitaFederal receitaFederal = Mockito.mock(ReceitaFederal.class);
		// Informo para classe RelatorioDeFuncionarios que utilizará o mock
		// receitaFederal
		relatorioDeFuncionarios.setRf(receitaFederal);
		// Configuro o mock da itnerface ReceitaFederal para retornar o valor
		// true quando o método isCPFBloqueado for invocado com o parâmetro
		// 111111111-11
		Mockito.when(receitaFederal.isCPFBloqueado("111111111-11")).thenReturn(true);
		Mockito.when(receitaFederal.isCPFBloqueado("222222222-22")).thenReturn(true);

		// Act
		// Realizo a chamada do método getFuncComCPFBloqueado passando como
		// parâmetro o valor tecnico, e armazeno o valor do retorno do método na
		// variável funcionariosComCPFBloqueado
		int funcionariosComCPFBloqueado = relatorioDeFuncionarios.getFuncComCPFBloqueado("tecnico");

		// Assert
		// Defino a quantidade de funcionários com CPF bloqueados eu espero
		// receber após ter invocado o método getFuncComCPFBloqueado
		int valorExperado = 2;
		// Verifico se o valor retornado é igual ao valor que estou esperando
		Assert.assertEquals(valorExperado, funcionariosComCPFBloqueado);
	}

	/**
	 * Caso de teste: Existe 1 funcionário na categoria "analista" que está com
	 * o CPF bloqueado.
	 */
	@Test
	public void casoDeTeste02() {
		// Arrange
		FuncionarioDAO funcionarioDao = Mockito.mock(FuncionarioDAO.class);
		ArrayList<Funcionario> funcionarios = new ArrayList<Funcionario>();
		Funcionario julio = new Funcionario();
		julio.setCpf("444444444-44");
		funcionarios.add(julio);
		Funcionario lenon = new Funcionario();
		lenon.setCpf("555555555-55");
		funcionarios.add(lenon);
		Mockito.when(funcionarioDao.getFuncionariosBy("analista")).thenReturn(funcionarios);
		relatorioDeFuncionarios = new RelatorioDeFuncionarios(funcionarioDao);

		ReceitaFederal receitaFederal = Mockito.mock(ReceitaFederal.class);
		relatorioDeFuncionarios.setRf(receitaFederal);
		Mockito.when(receitaFederal.isCPFBloqueado("444444444-44")).thenReturn(true);

		// Act
		int funcionariosComCPFBloqueado = relatorioDeFuncionarios.getFuncComCPFBloqueado("analista");

		// Assert
		int valorExperado = 1;
		Assert.assertEquals(valorExperado, funcionariosComCPFBloqueado);
	}

	/**
	 * Caso de teste: Existem 4 funcionários na categoria "gerente" com os CPFs:
	 * (123456789-00, 111222333-44, 654321987-23, 098876654-99), sendo que os
	 * CPFs 111222333-44 e 098876654-99 estão bloqueados.
	 */
	@Test
	public void casoDeTeste03() {
		// Arrange
		FuncionarioDAO funcionarioDao = Mockito.mock(FuncionarioDAO.class);
		ArrayList<Funcionario> funcionarios = new ArrayList<Funcionario>();
		Funcionario claudia = new Funcionario();
		claudia.setCpf("123456789-00");
		funcionarios.add(claudia);
		Funcionario marco = new Funcionario();
		marco.setCpf("111222333-44");
		funcionarios.add(marco);
		Funcionario daiany = new Funcionario();
		daiany.setCpf("654321987-23");
		funcionarios.add(daiany);
		Funcionario kaique = new Funcionario();
		kaique.setCpf("098876654-99");
		funcionarios.add(kaique);
		Mockito.when(funcionarioDao.getFuncionariosBy("gerente")).thenReturn(funcionarios);
		relatorioDeFuncionarios = new RelatorioDeFuncionarios(funcionarioDao);

		ReceitaFederal receitaFederal = Mockito.mock(ReceitaFederal.class);
		relatorioDeFuncionarios.setRf(receitaFederal);
		Mockito.when(receitaFederal.isCPFBloqueado("111222333-44")).thenReturn(true);
		Mockito.when(receitaFederal.isCPFBloqueado("098876654-99")).thenReturn(true);

		// Act
		int funcionariosComCPFBloqueado = relatorioDeFuncionarios.getFuncComCPFBloqueado("gerente");

		// Assert
		int valorExperado = 2;
		Assert.assertEquals(valorExperado, funcionariosComCPFBloqueado);
	}

}
