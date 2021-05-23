package br.com.cadastrousuario.repository;

import java.io.Serializable;

import javax.persistence.Query;

import br.com.cadastrousuario.model.UsuarioModel;
import br.com.cadastrousuario.repository.entity.UsuarioEntity;
import br.com.cadastrousuario.uteis.Uteis;

public class UsuarioRepository implements Serializable {

	private static final long serialVersionUID = 1L;

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
}