package br.com.cadastrousuario.pessoa.controller;

import java.time.LocalDateTime;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;

import br.com.cadastrousuario.model.PessoaModel;
import br.com.cadastrousuario.repository.PessoaRepository;
import br.com.cadastrousuario.usuario.controller.UsuarioController;
import br.com.cadastrousuario.uteis.Uteis;
import br.com.cadastrousuario.uteis.ValidaCPF;

@Named(value = "cadastrarPessoaController")
@RequestScoped
public class CadastrarPessoaController {

	Boolean bloquearCampos = false;

	public Boolean getBloquearCampos() {
		return bloquearCampos;
	}

	public void setBloquearCampos(Boolean bloquearCampos) {
		this.bloquearCampos = bloquearCampos;
	}

	@Inject
	PessoaModel pessoaModel;

	@Inject
	UsuarioController usuarioController;

	@Inject
	PessoaRepository pessoaRepository;

	public PessoaModel getPessoaModel() {
		return pessoaModel;
	}

	public void setPessoaModel(PessoaModel pessoaModel) {
		this.pessoaModel = pessoaModel;
	}

	/**
	 * SALVA UM NOVO REGISTRO VIA INPUT
	 */
	public void SalvarNovaPessoa() {

		LocalDateTime dtAtual = LocalDateTime.now();
		pessoaModel.setUsuarioModel(this.usuarioController.getUsuarioModel());
		pessoaModel.setCadastro(dtAtual);

		if (ValidaCPF.isCPF(getPessoaModel().getCpf()) == true) {

			if (pessoaRepository.ConsultarCpf(getPessoaModel()) == null) {
				
				if (pessoaModel.getUsuarioModel().getSenha().equals(pessoaModel.getUsuarioModel().getConfirmarSenha())) {
					
					pessoaRepository.SalvarNovoRegistro(this.pessoaModel);
					
					bloquearCampos = true;
					Uteis.MensagemInfo("Registro cadastrado com sucesso");
				}
				else {
					
					Uteis.MensagemAtencao("As senhas informadas estão diferentes");
					
				}

			} else {

				Uteis.MensagemAtencao("Usuário com o CPF informado já existe");

			}

		}
		
		else {
			
			Uteis.MensagemAtencao("CPF informado não é válido");
			
		}
		
		pessoaModel.getUsuarioModel().setUsuario(null);
		//this.usuarioController.getUsuarioModel().setUsuario(null);
		
	}
	
	
	
	

}