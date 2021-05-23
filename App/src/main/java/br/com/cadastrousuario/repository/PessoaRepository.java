package br.com.cadastrousuario.repository;
 
import java.time.LocalDateTime;
 
import javax.inject.Inject;
import javax.persistence.EntityManager;
 
import br.com.cadastrousuario.model.PessoaModel;
import br.com.cadastrousuario.repository.entity.PessoaEntity;
import br.com.cadastrousuario.repository.entity.UsuarioEntity;
import br.com.cadastrousuario.uteis.Uteis;
 
public class PessoaRepository {
 
	@Inject
	PessoaEntity pessoaEntity;
 
	EntityManager entityManager;
 
	/***
	 * MÉTODO RESPONSÁVEL POR SALVAR UMA NOVA PESSOA
	 * @param pessoaModel
	 */
	public void SalvarNovoRegistro(PessoaModel pessoaModel){
 
		entityManager =  Uteis.JpaEntityManager();
 
		pessoaEntity = new PessoaEntity();
		pessoaEntity.setNome(pessoaModel.getNome());
		pessoaEntity.setCpf(pessoaModel.getCpf());
		pessoaEntity.setSexo(pessoaModel.getSexo());
		pessoaEntity.setCadastro(pessoaModel.getCadastro());
		pessoaEntity.setEmail(pessoaModel.getEmail());
		pessoaEntity.setEndereco(pessoaModel.getEndereco());
		pessoaEntity.setBairro(pessoaModel.getBairro());
		pessoaEntity.setCep(pessoaModel.getCep());
		pessoaEntity.setCidade(pessoaModel.getCidade());
		pessoaEntity.setEstado(pessoaModel.getEstado());
		
 
	//	UsuarioEntity usuarioEntity = entityManager.find(UsuarioEntity.class, pessoaModel.getUsuarioModel().getCodigo()); 
	//	pessoaEntity.setUsuarioEntity(usuarioEntity);
 
		entityManager.persist(pessoaEntity);
 
	}
}