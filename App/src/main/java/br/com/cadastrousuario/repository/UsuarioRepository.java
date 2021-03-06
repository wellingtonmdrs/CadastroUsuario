package br.com.cadastrousuario.repository;

import java.io.Serializable;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.Query;

import br.com.cadastrousuario.model.UsuarioModel;
import br.com.cadastrousuario.repository.entity.PessoaEntity;
import br.com.cadastrousuario.repository.entity.UsuarioEntity;
import br.com.cadastrousuario.uteis.Uteis;

public class UsuarioRepository implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	@Inject
	UsuarioEntity usuarioEntity;
	EntityManager entityManager;

	public UsuarioEntity ValidaUsuario(UsuarioModel usuarioModel) {

		try {

			// QUERY QUE VAI SER EXECUTADA (UsuarioEntity.findUser)
			Query query = Uteis.JpaEntityManager().createNamedQuery("UsuarioEntity.findUser");

			// PARÂMETROS DA QUERY
			query.setParameter("usuario", usuarioModel.getUsuario());
			query.setParameter("senha", usuarioModel.getSenha());

			// RETORNA O USUÁRIO SE FOR LOCALIZADO
			return (UsuarioEntity) query.getSingleResult();

		} catch (Exception e) {

			return null;
		}

	}
	
	public void SalvarNovoRegistro(UsuarioModel usuarioModel,PessoaEntity pessoaEntity){
		 
		entityManager =  Uteis.JpaEntityManager();
		
		usuarioEntity = new UsuarioEntity();
		usuarioEntity.setPessoaEntity(pessoaEntity);
		usuarioEntity.getPessoaEntity().setCodigo(usuarioModel.getPessoaModel().getCodigo());
		usuarioEntity.setUsuario(usuarioModel.getUsuario());
		usuarioEntity.setSenha(usuarioModel.getSenha());
 
//		PessoaEntity pessoaEntity = entityManager.find(PessoaEntity.class, usuarioModel.getPessoaModel().getCodigo()); 
		//usuarioEntity.setPessoaEntity(pessoaEntity);
 
		entityManager.persist(usuarioEntity);
 
	}
	
	public UsuarioEntity ConsultarUsuario(UsuarioModel usuarioModel) {

		try {

			// QUERY QUE VAI SER EXECUTADA (UsuarioEntity.findUser)
			Query query = Uteis.JpaEntityManager().createNamedQuery("UsuarioEntity.findUsuario");

			// PARÂMETROS DA QUERY
			query.setParameter("codigo", usuarioModel.getPessoaModel().getCodigo());

			// RETORNA O USUÁRIO SE FOR LOCALIZADO
			return (UsuarioEntity) query.getSingleResult();

		} catch (Exception e) {

			return null;
		}

	}
	
	public UsuarioEntity ConsultarUsuario(int codigo) {

		try {

			// QUERY QUE VAI SER EXECUTADA (UsuarioEntity.findUser)
			Query query = Uteis.JpaEntityManager().createNamedQuery("UsuarioEntity.findUsuario");

			// PARÂMETROS DA QUERY
			query.setParameter("codigo", codigo);

			// RETORNA O USUÁRIO SE FOR LOCALIZADO
			return (UsuarioEntity) query.getSingleResult();

		} catch (Exception e) {

			return null;
		}

	}
	
	/***
	 * EXCLUI UM REGISTRO DO BANCO DE DADOS
	 * @param codigo
	 */
	public void ExcluirRegistro(int codigo){
 
		entityManager =  Uteis.JpaEntityManager();		
 
		UsuarioEntity usuarioEntity = this.ConsultarUsuario(codigo);
 
		entityManager.remove(usuarioEntity);
	}
	
}