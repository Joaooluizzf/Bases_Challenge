package com.br.test.repository;

import com.br.test.model.Client;
import com.br.test.model.Assets;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface AssetRepository extends JpaRepository<Assets, Long> {


    List<Assets> findByClient(Client client);

}
