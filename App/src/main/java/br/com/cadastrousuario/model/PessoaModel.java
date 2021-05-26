package br.com.cadastrousuario.model;
 
import java.time.LocalDateTime;
import java.util.List;
 
public class PessoaModel {
 
	private Integer codigo;
	private String  nome;
	private String  cpf;	
	private String	sexo;
	private LocalDateTime cadastro;
	private String  email;
	private String  endereco;
	private String  bairro;
	private String  cep;
	private String  cidade;
	private String  estado;
	private List<ContatoModel>  contatoModel;
	private String numCelular;
	private String numResidencia;
	
	public String getNumCelular() {
		return numCelular;
	}
	public void setNumCelular(String numCelular) {
		this.numCelular = numCelular;
	}
	public String getNumResidencia() {
		return numResidencia;
	}
	public void setNumResidencia(String numResidencia) {
		this.numResidencia = numResidencia;
	}
	public List<ContatoModel> getContatoModel() {
		return contatoModel;
	}
	public void setContatoModel(List<ContatoModel> contatoModel) {
		this.contatoModel = contatoModel;
	}
	private UsuarioModel  usuarioModel;
	
			public UsuarioModel getUsuarioModel() {
		return usuarioModel;
	}
	public void setUsuarioModel(UsuarioModel usuarioModel) {
		this.usuarioModel = usuarioModel;
	}
	public Integer getCodigo() {
		return codigo;
	}
	public void setCodigo(Integer codigo) {
		this.codigo = codigo;
	}
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public String getCpf() {
		return cpf;
	}
	public void setCpf(String cpf) {
		this.cpf = cpf;
	}
	public String getSexo() {
		return sexo;
	}
	public void setSexo(String sexo) {
		this.sexo = sexo;
	}
	public LocalDateTime getCadastro() {
		return cadastro;
	}
	public void setCadastro(LocalDateTime cadastro) {
		this.cadastro = cadastro;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getEndereco() {
		return endereco;
	}
	public void setEndereco(String endereco) {
		this.endereco = endereco;
	}
	public String getBairro() {
		return bairro;
	}
	public void setBairro(String bairro) {
		this.bairro = bairro;
	}
	public String getCep() {
		return cep;
	}
	public void setCep(String cep) {
		this.cep = cep;
	}
	public String getCidade() {
		return cidade;
	}
	public void setCidade(String cidade) {
		this.cidade = cidade;
	}
	public String getEstado() {
		return estado;
	}
	public void setEstado(String estado) {
		this.estado = estado;
	}
 
}