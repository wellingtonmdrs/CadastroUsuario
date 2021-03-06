package br.com.cadastrousuario.pessoa.controller;


import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.enterprise.inject.Produces;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import br.com.cadastrousuario.model.PessoaModel;
import br.com.cadastrousuario.repository.ContatoRepository;
import br.com.cadastrousuario.repository.PessoaRepository;
import br.com.cadastrousuario.repository.UsuarioRepository;
import br.com.cadastrousuario.uteis.Uteis;

@Named(value="consultarPessoaController")
@ViewScoped
public class ConsultarPessoaController implements Serializable {

	private static final long serialVersionUID = 1L;

	@Inject transient
	private PessoaModel pessoaModel;

	@Produces 
	private List<PessoaModel> pessoas;

	@Inject transient
	private PessoaRepository pessoaRepository;
	@Inject transient
	private ContatoRepository contatoRepository;
	@Inject transient
	private UsuarioRepository usuarioRepository;

	public List<PessoaModel> getPessoas() {
		return pessoas;
	}
	public void setPessoas(List<PessoaModel> pessoas) {
		this.pessoas = pessoas;
	}		
	public PessoaModel getPessoaModel() {
		return pessoaModel;
	}
	public void setPessoaModel(PessoaModel pessoaModel) {
		this.pessoaModel = pessoaModel;
	}

	/***
	 * CARREGA AS PESSOAS NA INICIALIZAÇÃO 
	 */
	@PostConstruct
	public void init(){

		//RETORNAR AS PESSOAS CADASTRADAS
		this.pessoas = pessoaRepository.ListarPessoas();
	}
	
	
	
	
	
	
	
	
	
	/***
	 * CARREGA INFORMAÇÕES DE UMA PESSOA PARA SER EDITADA
	 * @param pessoaModel
	 */
	public void Editar(PessoaModel pessoaModel){
 
		/*PEGA APENAS A PRIMEIRA LETRA DO SEXO PARA SETAR NO CAMPO(M OU F)*/
		pessoaModel.setSexo(pessoaModel.getSexo().substring(0, 1));
 
		this.pessoaModel = pessoaModel;
 
	}
 
	/***
	 * ATUALIZA O REGISTRO QUE FOI ALTERADO
	 */
	public void AtualizarDados(){
 
		this.pessoaRepository.AlterarRegistro(this.pessoaModel);	
		
		Uteis.MensagemInfo("Registro cadastrado com sucesso");
 
		/*RECARREGA OS REGISTROS*/
		this.init();
	}
	
	
	
	/***
	 * EXCLUINDO UM REGISTRO
	 * @param pessoaModel
	 */
	public void ExcluirPessoa(PessoaModel pessoaModel){
 
		//EXCLUI A PESSOA DO BANCO DE DADOS
		this.usuarioRepository.ExcluirRegistro(pessoaModel.getCodigo());
		this.contatoRepository.ExcluirRegistro(pessoaModel.getCodigo());
		this.pessoaRepository.ExcluirRegistro(pessoaModel.getCodigo());
 
		//REMOVENDO A PESSOA DA LISTA
		//ASSIM QUE É A PESSOA É REMOVIDA DA LISTA O DATATABLE É ATUALIZADO
		this.pessoas.remove(pessoaModel);
 
	}
	
	
	
	
	
	


}