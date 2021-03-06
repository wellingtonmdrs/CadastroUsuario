package br.com.cadastrousuario.repository.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

@Table(name = "tb_contato")
@Entity
@NamedQuery(name = "ContatoEntity.findContato", query = "SELECT u FROM ContatoEntity u WHERE u.pessoaEntity.codigo = :codigo")
public class ContatoEntity implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue
	@Column(name = "id_contato")
	private Integer codigo;
	
	@ManyToOne
	@JoinColumn(name="id_pessoa")
	private PessoaEntity pessoaEntity;
	

	@Column(name = "nu_ddd")
	private Integer ddd;

	@Column(name = "nu_telefone")
	private String telefone;
	
	@Column(name = "nu_tipo")
	private String tipo;

	public PessoaEntity getPessoaEntity() {
		return pessoaEntity;
	}
	
	public void setPessoaEntity(PessoaEntity pessoaEntity) {
		this.pessoaEntity = pessoaEntity;
	}

	public Integer getCodigo() {
		return codigo;
	}

	public void setCodigo(Integer codigo) {
		this.codigo = codigo;
	}

	public Integer getDdd() {
		return ddd;
	}

	public void setDdd(Integer ddd) {
		this.ddd = ddd;
	}

	public String getTelefone() {
		return telefone;
	}

	public void setTelefone(String telefone) {
		this.telefone = telefone;
	}

	public String getTipo() {
		return tipo;
	}

	public void setTipo(String tipo) {
		this.tipo = tipo;
	}

}	