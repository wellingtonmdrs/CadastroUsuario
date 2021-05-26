package br.com.cadastrousuario.repository;

import java.io.Serializable;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.Query;

import br.com.cadastrousuario.model.ContatoModel;
import br.com.cadastrousuario.repository.entity.ContatoEntity;
import br.com.cadastrousuario.repository.entity.PessoaEntity;
import br.com.cadastrousuario.uteis.Uteis;

public class ContatoRepository implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@Inject
	ContatoEntity contatoEntity;
	EntityManager entityManager;

	public ContatoEntity ValidaContato(ContatoModel contatoModel) {

		try {

			// QUERY QUE VAI SER EXECUTADA (UsuarioEntity.findUser)
			Query query = Uteis.JpaEntityManager().createNamedQuery("ContatoEntity.findContato");

			// PARÂMETROS DA QUERY
		//	query.setParameter("codigoPessoa", contatoModel.getPessoaModel().getCodigo());

			// RETORNA O USUÁRIO SE FOR LOCALIZADO
			return (ContatoEntity) query.getSingleResult();

		} catch (Exception e) {

			return null;
		}

	}
	
	public void SalvarNovoRegistro(ContatoModel contatoModel){
		 
		entityManager =  Uteis.JpaEntityManager();
		
		contatoEntity = new ContatoEntity();
		contatoEntity.setDdd(contatoModel.getDdd());
		contatoEntity.setTelefone(contatoModel.getTelefone());
		contatoEntity.setTipo(contatoModel.getTipo());
 
		//PessoaEntity pessoaEntity = entityManager.find(PessoaEntity.class, contatoModel.getPessoaModel().getCodigo()); 
		//contatoEntity.setPessoaEntity(pessoaEntity);
 
		entityManager.persist(contatoEntity);
 
	}
	
}