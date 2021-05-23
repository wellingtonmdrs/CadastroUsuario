package br.com.cadastrousuario.repository;

import java.io.Serializable;

import javax.persistence.Query;

import br.com.cadastrousuario.model.ContatoModel;
import br.com.cadastrousuario.repository.entity.ContatoEntity;
import br.com.cadastrousuario.uteis.Uteis;

public class ContatoRepository implements Serializable {

	private static final long serialVersionUID = 1L;

	public ContatoEntity ValidaContato(ContatoModel contatoModel) {

		try {

			// QUERY QUE VAI SER EXECUTADA (UsuarioEntity.findUser)
			Query query = Uteis.JpaEntityManager().createNamedQuery("ContatoEntity.findContato");

			// PARÂMETROS DA QUERY
			query.setParameter("codigoPessoa", contatoModel.getPessoaModel().getCodigo());

			// RETORNA O USUÁRIO SE FOR LOCALIZADO
			return (ContatoEntity) query.getSingleResult();

		} catch (Exception e) {

			return null;
		}

	}
}