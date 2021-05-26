package br.com.cadastrousuario.uteis;

public enum EnumTipoContato {
	
	Celular("Celular"), Residencia("Residencia");
	
	private String descricao;
	
	private EnumTipoContato(String descricao) {
		// TODO Auto-generated constructor stub
		
		this.descricao = descricao;
		
		
	}

	public String getDescricao() {
		return descricao;
	}

	
	
}
