package com.br.test.model;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "Client")
public class Client {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;

	@Column(name = "cpf", length = 11, unique=true)
	private String cpf;

	@Column(name = "nome")
	private String name;

	@Column(name = "endereco")
	private String address;

	public Client() {

	}

	public Client(String cpf, String name, String address) {
		this.cpf=cpf;
		this.name = name;
		this.address = address;
	}

	public String getCpf() {
		return cpf;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}


}
