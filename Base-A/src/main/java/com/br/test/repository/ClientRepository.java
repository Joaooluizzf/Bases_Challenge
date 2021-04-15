package com.br.test.repository;

import java.util.List;

import com.br.test.model.Client;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ClientRepository extends JpaRepository<Client, Long> {

  Client findByCpf(String cpf);

}
