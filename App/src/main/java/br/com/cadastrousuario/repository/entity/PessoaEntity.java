package br.com.cadastrousuario.repository.entity;
 
import java.time.LocalDateTime;
 
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
 
 
@Entity
@Table(name="tb_pessoa")

@NamedQueries({
	@NamedQuery(name = "PessoaEntity.findAll",query= "SELECT p FROM PessoaEntity p"),
	@NamedQuery(name = "PessoaEntity.findCpf", query = "SELECT u FROM PessoaEntity u WHERE u.cpf = :cpf")
})

public class PessoaEntity {
 
	@Id
	@GeneratedValue
	@Column(name = "id_pessoa")
	private Integer codigo;
 
	@Column(name = "nm_pessoa")
	private String  nome;
 
	@Column(name = "nu_cpf")
	private String  cpf;	
 
	@Column(name = "fl_sexo")
	private String	sexo;
 
	@Column(name = "dt_cadastro")
	private LocalDateTime cadastro;
 
	@Column(name = "ds_email")
	private String  email;
 
	@Column(name = "ds_endereco")
	private String  endereco;
	
	@Column(name = "ds_bairro")
	private String  bairro;
	
	@Column(name = "ds_cep")
	private String  cep;
	
	@Column(name = "ds_cidade")
	private String  cidade;
	
	@Column(name = "ds_estado")
	private String  estado;

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