package br.com.cadastrousuario.repository.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Table(name = "tb_usuario")
@Entity
@NamedQueries({
	@NamedQuery(name = "UsuarioEntity.findUser", query = "SELECT u FROM UsuarioEntity u WHERE u.usuario = :usuario AND u.senha = :senha"),
	@NamedQuery(name = "UsuarioEntity.findUsuario", query = "SELECT u FROM UsuarioEntity u WHERE u.pessoaEntity.codigo = :codigo")
})
public class UsuarioEntity implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue
	@Column(name = "id_usuario")
	private Integer codigo;
	
	@OneToOne
	@JoinColumn(name="id_pessoa")
	private PessoaEntity pessoaEntity;
	
	public PessoaEntity getPessoaEntity() {
		return pessoaEntity;
	}

	public void setPessoaEntity(PessoaEntity pessoaEntity) {
		this.pessoaEntity = pessoaEntity;
	}

	@Column(name = "ds_login")
	private String usuario;

	@Column(name = "ds_senha")
	private String senha;

	public Integer getCodigo() {
		return codigo;
	}

	public void setCodigo(Integer codigo) {
		this.codigo = codigo;
	}

	public String getUsuario() {
		return usuario;
	}

	public void setUsuario(String usuario) {
		this.usuario = usuario;
	}

	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}

}	