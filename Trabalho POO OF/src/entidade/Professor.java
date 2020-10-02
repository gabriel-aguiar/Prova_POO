package entidade;

import java.sql.Date;

public class Professor {

	private int id;
	private String nome;
	private String email;
	private Double peso;
	private Date data_admissao;
	
	public Professor() {
		
	}
	
	public Professor(int id, String nome, String email, Double peso, Date data_admissao) {
		super();
		this.id = id;
		this.nome = nome;
		this.email = email;
		this.peso = peso;
		this.data_admissao = data_admissao;
	}

	public Professor(String nome, String email, Double peso, Date data_admissao) {
		super();
		this.nome = nome;
		this.email = email;
		this.peso = peso;
		this.data_admissao = data_admissao;
	}
	
	

	public int getId() {
		return id;
	}

	public void setId(int id) {
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

	public Double getPeso() {
		return peso;
	}

	public void setPeso(Double peso) {
		this.peso = peso;
	}

	public Date getData_admissao() {
		return data_admissao;
	}

	public void setData_admissao(Date data_admissao) {
		this.data_admissao = data_admissao;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((data_admissao == null) ? 0 : data_admissao.hashCode());
		result = prime * result + ((email == null) ? 0 : email.hashCode());
		result = prime * result + id;
		result = prime * result + ((nome == null) ? 0 : nome.hashCode());
		result = prime * result + ((peso == null) ? 0 : peso.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Professor other = (Professor) obj;
		if (data_admissao == null) {
			if (other.data_admissao != null)
				return false;
		} else if (!data_admissao.equals(other.data_admissao))
			return false;
		if (email == null) {
			if (other.email != null)
				return false;
		} else if (!email.equals(other.email))
			return false;
		if (id != other.id)
			return false;
		if (nome == null) {
			if (other.nome != null)
				return false;
		} else if (!nome.equals(other.nome))
			return false;
		if (peso == null) {
			if (other.peso != null)
				return false;
		} else if (!peso.equals(other.peso))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Professor id =" + id + ", nome =" + nome + ", email =" + email + ", peso =" + peso + ", data_admissao ="
				+ data_admissao;
	}
	
	
}
