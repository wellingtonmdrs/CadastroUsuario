package br.com.cadastrousuario.usuario.controller;

import java.io.Serializable;

import javax.enterprise.context.SessionScoped;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;

import org.apache.commons.lang3.StringUtils;

import br.com.cadastrousuario.model.PessoaModel;
import br.com.cadastrousuario.model.UsuarioModel;
import br.com.cadastrousuario.repository.UsuarioRepository;
import br.com.cadastrousuario.repository.entity.UsuarioEntity;
import br.com.cadastrousuario.uteis.Uteis;

@Named(value = "usuarioController")
@SessionScoped
public class UsuarioController implements Serializable {

	private static final long serialVersionUID = 1L;

	@Inject
	private UsuarioModel usuarioModel;

	@Inject
	private UsuarioRepository usuarioRepository;

	@Inject
	private UsuarioEntity usuarioEntity;

	public UsuarioModel getUsuarioModel() {
		return usuarioModel;
	}

	public void setUsuarioModel(UsuarioModel usuarioModel) {
		this.usuarioModel = usuarioModel;
	}

	public UsuarioModel GetUsuarioSession() {
		
		FacesContext facesContext = FacesContext.getCurrentInstance();

		return (UsuarioModel) facesContext.getExternalContext().getSessionMap().get("usuarioAutenticado");
	}

	public String Logout() {

		FacesContext.getCurrentInstance().getExternalContext().invalidateSession();

		return "/index.xhtml?faces-redirect=true";
	}

	public String EfetuarLogin() {

		if (StringUtils.isEmpty(usuarioModel.getUsuario()) || StringUtils.isBlank(usuarioModel.getUsuario())) {

			Uteis.Mensagem("Favor informar o login!");
			return null;
		} else if (StringUtils.isEmpty(usuarioModel.getSenha()) || StringUtils.isBlank(usuarioModel.getSenha())) {

			Uteis.Mensagem("Favor informara senha!");
			return null;
		} else {

			usuarioEntity = usuarioRepository.ValidaUsuario(usuarioModel);

			if (usuarioEntity != null) {
				usuarioModel.setUsuario(null);
				usuarioModel.setSenha(null);
				usuarioModel.setCodigo(usuarioEntity.getCodigo());

				FacesContext facesContext = FacesContext.getCurrentInstance();

				facesContext.getExternalContext().getSessionMap().put("usuarioAutenticado", usuarioModel);

				return "sistema/home?faces-redirect=true";
			} else {

				Uteis.Mensagem("Não foi possível efetuar o login com esse usuário e senha!");
				return null;
			}
		}

	}
	
	/***
	 * EXCLUINDO UM REGISTRO
	 * @param pessoaModel
	 */
	public void ExcluirPessoa(PessoaModel pessoaModel){
 
		//EXCLUI A PESSOA DO BANCO DE DADOS
		
		
		this.usuarioRepository.ExcluirRegistro(pessoaModel.getCodigo());
 
 
	}

}