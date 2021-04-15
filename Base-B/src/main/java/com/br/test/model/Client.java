package com.br.test.model;

import javax.persistence.*;

@Entity
@Table(name = "Client")
public class Client {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;

	@Column(name = "cpf", length = 11, unique=true)
	private String cpf;

	@Column(name = "idade")
	private String idade;

	@Column(name = "endereco")
	private String address;

	@Column(name = "renda")
	private long income;

	public Client() {

	}

	public Client(String cpf, String idade, String address, long income) {
		this.cpf=cpf;
		this.idade = idade;
		this.address = address;
		this.income=income;
	}

	public String getCpf() {
		return cpf;
	}

	public String getIdade() {
		return idade;
	}

	public void setIdade(String idade) {
		this.idade = idade;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public long getIncome(){return income;}

	public void setIncome(long income){this.income=income;}

}
