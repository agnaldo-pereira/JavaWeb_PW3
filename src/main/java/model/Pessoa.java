package model;

import java.io.Serializable;
import java.util.Date;



public class Pessoa implements Serializable{
	
	private static final long serialVersionUID = 1L;
	//id, nome, email
	
	private Integer id;
	private String nome;
	private String email;
	private String senha;
	private String imagem;
	private Integer codigo;
	private String status;
	private Date data;
	
	//construtores
	
	public Pessoa() {};
	
	public Pessoa(Integer id, String nome, String email, String senha, String imagem, Integer codigo, String status, Date data) {
		super();
		this.id = id;
		this.nome = nome;
		this.email = email;
		this.senha = senha;
		this.imagem = imagem;
		this.codigo = codigo;
		this.status = status;
		this.data = data;
	}
	
	public Pessoa(Integer id, String nome, String email) {
		super();
		this.id = id;
		this.nome = nome;
		this.email = email;
	}

	//getters e setters	
	
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}
	
	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}
	
	public String getImagem() {
		return imagem;
	}

	public void setImagem(String imagem) {
		this.imagem = imagem;
	}

	public Integer getCodigo() {
		return codigo;
	}

	public void setCodigo(Integer codigo) {
		this.codigo = codigo;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public Date getData() {
		return data;
	}

	public void setData(Date data) {
		this.data = data;
	}

	@Override
	public String toString() {
		return "Pessoa [id=" + id + ", nome=" + nome + ", email=" + email + ", senha=" + senha +"]";
	};
	
	
}
