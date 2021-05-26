package br.com.cadastrousuario.repository;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.Query;

import br.com.cadastrousuario.model.ContatoModel;
import br.com.cadastrousuario.model.PessoaModel;
import br.com.cadastrousuario.model.UsuarioModel;
import br.com.cadastrousuario.repository.entity.ContatoEntity;
import br.com.cadastrousuario.repository.entity.PessoaEntity;
import br.com.cadastrousuario.repository.entity.UsuarioEntity;
import br.com.cadastrousuario.uteis.EnumTipoContato;
import br.com.cadastrousuario.uteis.Uteis;

public class PessoaRepository {

	@Inject
	PessoaEntity pessoaEntity;

	EntityManager entityManager;

	/***
	 * MÉTODO RESPONSÁVEL POR SALVAR UMA NOVA PESSOA
	 * 
	 * @param pessoaModel
	 */
	public void SalvarNovoRegistro(PessoaModel pessoaModel) {

		entityManager = Uteis.JpaEntityManager();

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

		// UsuarioEntity usuarioEntity = entityManager.find(UsuarioEntity.class,
		// pessoaModel.getUsuarioModel().getCodigo());
		// pessoaEntity.setUsuarioEntity(usuarioEntity);

		entityManager.persist(pessoaEntity);

		pessoaEntity = ConsultarCpf(pessoaModel);
		UsuarioRepository usuarioRepository = new UsuarioRepository();
		pessoaModel.getUsuarioModel().setPessoaModel(pessoaModel);
		pessoaModel.getUsuarioModel().getPessoaModel().setCodigo(pessoaEntity.getCodigo());
		usuarioRepository.SalvarNovoRegistro(pessoaModel.getUsuarioModel(), pessoaEntity);

		// (99) 99999-9999
		String[] splitCelular = pessoaModel.getNumCelular().split(" ");
		String[] splitResidencial = pessoaModel.getNumResidencia().split(" ");

		ContatoModel contatoModel = new ContatoModel();
		ContatoModel contatoModelR = new ContatoModel();

		List<ContatoModel> listaContatoModel = new ArrayList<ContatoModel>();

		pessoaModel.setContatoModel(listaContatoModel);

		contatoModel.setDdd(Integer.valueOf(splitCelular[0].replace("(", "").replace(")", "")));
		contatoModel.setTelefone(splitCelular[1]);
		contatoModel.setTipo(EnumTipoContato.Celular.getDescricao());

		pessoaModel.getContatoModel().add(contatoModel);

		ContatoRepository contatoRepository = new ContatoRepository();
		contatoRepository.SalvarNovoRegistro(pessoaModel.getContatoModel(), pessoaEntity);

		contatoModelR.setDdd(Integer.valueOf(splitResidencial[0].replace("(", "").replace(")", "")));
		contatoModelR.setTelefone(splitResidencial[1]);
		contatoModelR.setTipo(EnumTipoContato.Residencia.getDescricao());

		pessoaModel.getContatoModel().clear();
		pessoaModel.getContatoModel().add(contatoModelR);

		ContatoRepository contatoRepositoryR = new ContatoRepository();
		contatoRepositoryR.SalvarNovoRegistro(pessoaModel.getContatoModel(), pessoaEntity);

	}

	public PessoaEntity ConsultarCpf(PessoaModel pessoaModel) {

		try {

			// QUERY QUE VAI SER EXECUTADA (UsuarioEntity.findUser)
			Query query = Uteis.JpaEntityManager().createNamedQuery("PessoaEntity.findCpf");

			// PARÂMETROS DA QUERY
			query.setParameter("cpf", pessoaModel.getCpf());

			// RETORNA O USUÁRIO SE FOR LOCALIZADO
			return (PessoaEntity) query.getSingleResult();

		} catch (Exception e) {

			return null;
		}

	}

	/***
	 * MÉTODO PARA CONSULTAR A PESSOA
	 * 
	 * @return
	 */
	public List<PessoaModel> ListarPessoas() {

		List<PessoaModel> pessoasModel = new ArrayList<PessoaModel>();

		entityManager = Uteis.JpaEntityManager();

		Query query = entityManager.createNamedQuery("PessoaEntity.findAll");

		@SuppressWarnings("unchecked")
		Collection<PessoaEntity> pessoasEntity = (Collection<PessoaEntity>) query.getResultList();

		PessoaModel pessoaModel = null;

		for (PessoaEntity pessoaEntity : pessoasEntity) {

			pessoaModel = new PessoaModel();
			pessoaModel.setCodigo(pessoaEntity.getCodigo());
			pessoaModel.setNome(pessoaEntity.getNome());
			pessoaModel.setCpf(pessoaEntity.getCpf());
			pessoaModel.setEmail(pessoaEntity.getEmail());
			pessoaModel.setEndereco(pessoaEntity.getEndereco());
			pessoaModel.setBairro(pessoaEntity.getBairro());
			pessoaModel.setCep(pessoaEntity.getCep());
			pessoaModel.setCidade(pessoaEntity.getCidade());
			pessoaModel.setEstado(pessoaEntity.getEstado());

			if (pessoaEntity.getSexo().equals("M"))
				pessoaModel.setSexo("Masculino");
			else
				pessoaModel.setSexo("Feminino");

			// Consultar Contato do Usuario

			ContatoRepository contatoRepository = new ContatoRepository();
			ContatoModel contatoModel = new ContatoModel();

			List<ContatoModel> listContatoModel = new ArrayList<ContatoModel>();
			contatoModel.setPessoaModel(pessoaModel);
			pessoaModel.setContatoModel(listContatoModel);

			Collection<ContatoEntity> contatoEntity = contatoRepository.ConsultarContato(contatoModel);

			for (ContatoEntity contatoEntityList : contatoEntity) {

				ContatoModel contatoModelCont = new ContatoModel();

				contatoModelCont.setDdd(contatoEntityList.getDdd());
				contatoModelCont.setTelefone(contatoEntityList.getTelefone());
				contatoModelCont.setTipo(contatoEntityList.getTipo());

				if (contatoModelCont.getTipo().equals(EnumTipoContato.Celular.getDescricao())) {

					pessoaModel.setNumCelular("(" + contatoModelCont.getDdd() + ") " + contatoModelCont.getTelefone());

				}

				else {

					pessoaModel
							.setNumResidencia("(" + contatoModelCont.getDdd() + ") " + contatoModelCont.getTelefone());

				}

				pessoaModel.getContatoModel().add(contatoModelCont);

			}

			// Consultar dados do Usuario
			UsuarioRepository usuarioRepository = new UsuarioRepository();
			UsuarioModel usuarioModel = new UsuarioModel();

			usuarioModel.setPessoaModel(pessoaModel);

			UsuarioEntity usuarioEntity = usuarioRepository.ConsultarUsuario(usuarioModel);

			usuarioModel.setUsuario(usuarioEntity.getUsuario());

			pessoaModel.setUsuarioModel(usuarioModel);

			pessoasModel.add(pessoaModel);
		}

		return pessoasModel;

	}

	/***
	 * CONSULTA UMA PESSOA CADASTRADA PELO CÓDIGO
	 * 
	 * @param codigo
	 * @return
	 */
	private PessoaEntity ConsultarPessoa(int codigo) {

		entityManager = Uteis.JpaEntityManager();

		return entityManager.find(PessoaEntity.class, codigo);
	}

	/***
	 * ALTERA UM REGISTRO CADASTRADO NO BANCO DE DADOS
	 * 
	 * @param pessoaModel
	 */
	public void AlterarRegistro(PessoaModel pessoaModel) {

		entityManager = Uteis.JpaEntityManager();

		PessoaEntity pessoaEntity = this.ConsultarPessoa(pessoaModel.getCodigo());

		pessoaEntity.setNome(pessoaModel.getNome());
		pessoaEntity.setEmail(pessoaModel.getEmail());
		pessoaEntity.setSexo(pessoaModel.getSexo());
		pessoaEntity.setEndereco(pessoaModel.getEndereco());
		pessoaEntity.setBairro(pessoaModel.getBairro());
		pessoaEntity.setCep(pessoaModel.getCep());
		pessoaEntity.setCidade(pessoaModel.getCidade());
		pessoaEntity.setEstado(pessoaModel.getEstado());
		pessoaEntity.setBairro(pessoaModel.getBairro());

		entityManager.merge(pessoaEntity);
		
		// (99) 99999-9999
		String[] splitCelular = pessoaModel.getNumCelular().split(" ");
		String[] splitResidencial = pessoaModel.getNumResidencia().split(" ");

		ContatoModel contatoModel = new ContatoModel();
		ContatoModel contatoModelR = new ContatoModel();

		List<ContatoModel> listaContatoModel = new ArrayList<ContatoModel>();

		pessoaModel.setContatoModel(listaContatoModel);

		contatoModel.setDdd(Integer.valueOf(splitCelular[0].replace("(", "").replace(")", "")));
		contatoModel.setTelefone(splitCelular[1]);
		contatoModel.setTipo(EnumTipoContato.Celular.getDescricao());


		ContatoRepository contatoRepository = new ContatoRepository();
		
		Collection <ContatoEntity> contatoEntityList = contatoRepository.ConsultarContato(pessoaModel.getCodigo());
		
		for (ContatoEntity contatoEntity : contatoEntityList) {
			
			if (contatoEntity.getTipo().equals(EnumTipoContato.Celular.getDescricao())) {
				
				contatoModel.setCodigo(contatoEntity.getCodigo());
				
			}
		}
		pessoaModel.getContatoModel().add(contatoModel);
		
		contatoRepository.AlterarRegistro(pessoaModel.getContatoModel(), pessoaEntity);

		contatoModelR.setDdd(Integer.valueOf(splitResidencial[0].replace("(", "").replace(")", "")));
		contatoModelR.setTelefone(splitResidencial[1]);
		contatoModelR.setTipo(EnumTipoContato.Residencia.getDescricao());

		for (ContatoEntity contatoEntity : contatoEntityList) {
			
			if (contatoEntity.getTipo().equals(EnumTipoContato.Residencia.getDescricao())) {
				
				contatoModelR.setCodigo(contatoEntity.getCodigo());
				
			}
		}
		
		pessoaModel.getContatoModel().clear();
		pessoaModel.getContatoModel().add(contatoModelR);

		ContatoRepository contatoRepositoryR = new ContatoRepository();
		contatoRepositoryR.AlterarRegistro(pessoaModel.getContatoModel(), pessoaEntity);

		
	}
	
	/***
	 * EXCLUI UM REGISTRO DO BANCO DE DADOS
	 * @param codigo
	 */
	public void ExcluirRegistro(int codigo){
 
		entityManager =  Uteis.JpaEntityManager();		
 
		PessoaEntity pessoaEntity = this.ConsultarPessoa(codigo);
 
		entityManager.remove(pessoaEntity);
	}
	

}