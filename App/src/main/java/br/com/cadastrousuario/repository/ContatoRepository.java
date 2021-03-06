package br.com.cadastrousuario.repository;

import java.io.Serializable;
import java.util.Collection;
import java.util.List;

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

	@SuppressWarnings("unchecked")
	public Collection<ContatoEntity> ConsultarContato(ContatoModel contatoModel) {

		try {

			// QUERY QUE VAI SER EXECUTADA (ContatoEntity.findContato)
			Query query = Uteis.JpaEntityManager().createNamedQuery("ContatoEntity.findContato");

			// PARÂMETROS DA QUERY
			query.setParameter("codigo", contatoModel.getPessoaModel().getCodigo());

			// RETORNA O USUÁRIO SE FOR LOCALIZADO
			return query.getResultList();

		} catch (Exception e) {

			return null;
		}

	}
	
	
	public void SalvarNovoRegistro(List <ContatoModel> contatoModelList,PessoaEntity pessoaEntity){
		 
		entityManager =  Uteis.JpaEntityManager();
		
		contatoEntity = new ContatoEntity();

		contatoEntity.setPessoaEntity(pessoaEntity);

		for (ContatoModel contatoModel2 : contatoModelList) {
			
				
				contatoEntity.getPessoaEntity().setCodigo(pessoaEntity.getCodigo());
				contatoEntity.setDdd(contatoModel2.getDdd());
				contatoEntity.setTelefone(contatoModel2.getTelefone());
				contatoEntity.setTipo(contatoModel2.getTipo());
//		PessoaEntity pessoaEntity = entityManager.find(PessoaEntity.class, usuarioModel.getPessoaModel().getCodigo()); 
				//usuarioEntity.setPessoaEntity(pessoaEntity);
				
				entityManager.persist(contatoEntity);
		}
	}
	
	public void AlterarRegistro(List <ContatoModel> contatoModelList,PessoaEntity pessoaEntity){
		 
		entityManager =  Uteis.JpaEntityManager();
		
		contatoEntity = new ContatoEntity();

		contatoEntity.setPessoaEntity(pessoaEntity);

		for (ContatoModel contatoModel2 : contatoModelList) {
			
				contatoEntity.setCodigo(contatoModel2.getCodigo());
				contatoEntity.getPessoaEntity().setCodigo(pessoaEntity.getCodigo());
				contatoEntity.setDdd(contatoModel2.getDdd());
				contatoEntity.setTelefone(contatoModel2.getTelefone());
				contatoEntity.setTipo(contatoModel2.getTipo());
				
				entityManager.merge(contatoEntity);
		}
	}

	@SuppressWarnings("unchecked")
	public Collection <ContatoEntity> ConsultarContato(int codigo) {

		try {

			// QUERY QUE VAI SER EXECUTADA (UsuarioEntity.findUser)
			Query query = Uteis.JpaEntityManager().createNamedQuery("ContatoEntity.findContato");

			// PARÂMETROS DA QUERY
			query.setParameter("codigo", codigo);

			// RETORNA O USUÁRIO SE FOR LOCALIZADO
			return query.getResultList();

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
 
		Collection<ContatoEntity> contatoEntity = this.ConsultarContato(codigo);
		
		for (ContatoEntity contatoEntity2 : contatoEntity) {
			
			entityManager.remove(contatoEntity2);
		}
 
	}
	
}