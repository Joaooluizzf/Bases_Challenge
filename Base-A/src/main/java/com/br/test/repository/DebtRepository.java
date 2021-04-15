package com.br.test.repository;

import com.br.test.model.Client;
import com.br.test.model.Debt;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface DebtRepository extends JpaRepository<Debt, Long> {


    List<Debt> findByClient(Client client);

}
