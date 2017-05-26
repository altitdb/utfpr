package br.edu.utfpr.exercicio03;

import java.util.ArrayList;

import org.junit.Assert;
import org.junit.Test;
import org.mockito.Mockito;

public class RelatorioDeFuncionariosTest {

	private RelatorioDeFuncionarios relatorioDeFuncionarios;
	
	@Test
	public void casoDeTeste01() {
		FuncionarioDAO funcionarioDao = Mockito.mock(FuncionarioDAO.class);
		ArrayList<Funcionario> funcionarios = new ArrayList<Funcionario>();
		Funcionario mariana = new Funcionario();
		mariana.setCpf("111111111-11");
		funcionarios.add(mariana);
		Funcionario larissa = new Funcionario();
		larissa.setCpf("222222222-22");
		funcionarios.add(larissa);
		Funcionario glauco = new Funcionario();
		glauco.setCpf("333333333-33");
		funcionarios.add(glauco);
		Mockito.when(funcionarioDao.getFuncionariosBy("tecnico")).thenReturn(funcionarios);
		relatorioDeFuncionarios = new RelatorioDeFuncionarios(funcionarioDao);
		
		ReceitaFederal receitaFederal = Mockito.mock(ReceitaFederal.class);
		relatorioDeFuncionarios.setRf(receitaFederal);
		Mockito.when(receitaFederal.isCPFBloqueado("111111111-11")).thenReturn(true);
		Mockito.when(receitaFederal.isCPFBloqueado("222222222-22")).thenReturn(true);
		
		int funcionariosComCPFBloqueado = relatorioDeFuncionarios.getFuncComCPFBloqueado("tecnico");
		int valorExperado = 2;
		Assert.assertEquals(valorExperado , funcionariosComCPFBloqueado);
	}
	
	@Test
	public void casoDeTeste02() {
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
		
		int funcionariosComCPFBloqueado = relatorioDeFuncionarios.getFuncComCPFBloqueado("analista");
		int valorExperado = 1;
		Assert.assertEquals(valorExperado , funcionariosComCPFBloqueado);
	}
	
	@Test
	public void casoDeTeste03() {
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
		
		int funcionariosComCPFBloqueado = relatorioDeFuncionarios.getFuncComCPFBloqueado("gerente");
		int valorExperado = 2;
		Assert.assertEquals(valorExperado , funcionariosComCPFBloqueado);
	}

}
