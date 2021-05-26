CREATE DATABASE db_cadastro_usuario;

CREATE TABLE tb_pessoa(
 
    id_pessoa           INT AUTO_INCREMENT PRIMARY KEY NOT NULL COMMENT 'CÓDIGO DA PESSOA',
    nm_pessoa           VARCHAR(70)  NOT NULL COMMENT 'NOME DA PESSOA',
	nu_cpf				VARCHAR(15)  NOT NULL COMMENT 'NUMERO DO CPF',
    fl_sexo	        	CHAR(1)	     NOT NULL COMMENT 'INFORMAR M OU F',
    dt_cadastro         DATETIME     NOT NULL COMMENT 'DATA DE CADASTRO DO REGISTRO',
    ds_email	        VARCHAR(80)  NOT NULL COMMENT 'EMAIL DA PESSOA',
    ds_endereco         VARCHAR(100) NOT NULL COMMENT 'DESCRIÇÃO DO ENDEREÇO',
    ds_bairro			VARCHAR(100) NOT NULL COMMENT 'DESCRIÇÃO DO BAIRRO',
	ds_cep				VARCHAR(10) NOT NULL COMMENT 'DESCRIÇÃO DO CEP',			
	ds_cidade			VARCHAR(100) NOT NULL COMMENT 'DESCRIÇÃO DO CIDADE',
	ds_estado			VARCHAR(2) NOT NULL COMMENT 'DESCRIÇÃO DO ESTADO'
	
);

CREATE TABLE tb_contato(

	id_contato		INT AUTO_INCREMENT PRIMARY KEY NOT NULL COMMENT 'CÓDIGO DO CONTATO',
    id_pessoa           INT NOT NULL COMMENT 'CÓDIGO DA PESSOA',
	nu_ddd			INT NOT NULL COMMENT 'NUMERO DO DDD',
	nu_telefone		VARCHAR(10) NOT NULL COMMENT 'NUMERO DO TELEFONE',
	nu_tipo			VARCHAR(10) NOT NULL COMMENT 'TIPO DO TELEFONE'

);

CREATE TABLE tb_usuario(
 
	id_usuario INT AUTO_INCREMENT PRIMARY KEY NOT NULL COMMENT 'CÓDIGO DO USUÁRIO',
	id_pessoa           INT NOT NULL COMMENT 'CÓDIGO DA PESSOA',
	ds_login   VARCHAR(30) NOT NULL COMMENT 'LOGIN DO USUÁRIO PARA ACESSO AO SISTEMA',
	ds_senha   VARCHAR(30) NOT NULL COMMENT 'SENHA DO USUÁRIO PARA ACESSO AO SISTEMA'
	
);

 ALTER TABLE tb_usuario ADD FOREIGN KEY (id_pessoa) REFERENCES tb_pessoa(id_pessoa);
 
 ALTER TABLE tb_contato ADD FOREIGN KEY (id_pessoa) REFERENCES tb_pessoa(id_pessoa);
 
INSERT INTO tb_pessoa (nm_pessoa,nu_cpf,fl_sexo,dt_cadastro,ds_email,ds_endereco,ds_bairro,ds_cep,ds_cidade,ds_estado)
VALUES('teste teste','191.000.000-00','M',NOW(),'teste@teste.com','rua teste nº007','bairro teste','54765-138','cidade teste','PE');

INSERT INTO tb_contato (id_pessoa,nu_ddd,nu_telefone,nu_tipo)
VALUES('1','81','98765-4321','celular');

INSERT INTO tb_contato (id_pessoa,nu_ddd,nu_telefone,nu_tipo)
VALUES('1','81','3044-3030','fixo');

INSERT INTO tb_usuario (id_pessoa,ds_login,ds_senha)
VALUES('1','teste@teste.com','123456789'); 