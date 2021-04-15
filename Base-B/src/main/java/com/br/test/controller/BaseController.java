package com.br.test.controller;

import java.util.List;
import java.util.Optional;

import com.br.test.model.Client;
import com.br.test.model.Assets;
import com.br.test.repository.ClientRepository;
import com.br.test.repository.AssetRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin(origins = "http://localhost:8082")
@RestController
@RequestMapping("/api")
public class BaseController {

	@Autowired
	ClientRepository clientRepository;

	@Autowired
	AssetRepository assetRepository;

	@GetMapping("/score/{cpf}")
	public ResponseEntity<List<Assets>> getScore(@PathVariable("cpf") long cpf) {
		try{
			Optional<Client> data = clientRepository.findById(cpf);
			//Calculo de score
			return new ResponseEntity<>(HttpStatus.OK);

		}catch (Exception e ){
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@GetMapping("/client/{cpf}")
	public ResponseEntity<Client> getClientByCpf(@PathVariable("cpf") String cpf) {
		Client clientData = clientRepository.findByCpf(cpf);
		if (clientData != null) {
			return new ResponseEntity<>(clientData, HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}

	@PostMapping("/clients")
	public ResponseEntity<Client> createClient(@RequestBody Client client) {
		try {
			Client clientData = clientRepository
					.save(new Client(client.getCpf(), client.getIdade(), client.getAddress(), client.getIncome()));
			return new ResponseEntity<>(clientData, HttpStatus.CREATED);
		} catch (Exception e) {
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@PutMapping("/client/{cpf}")
	public ResponseEntity<Client> updateClient(@PathVariable("cpf") String cpf, @RequestBody Client client) {
		Client clientData = clientRepository.findByCpf(cpf);
		if (clientData != null) {
			clientData.setIdade(client.getIdade());
			clientData.setAddress(client.getAddress());
			return new ResponseEntity<>(clientRepository.save(clientData), HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}

	@DeleteMapping("/client/{id}")
	public ResponseEntity<HttpStatus> deleteClient(@PathVariable("id") long id) {
		try {
			clientRepository.deleteById(id);
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@PostMapping("/asset/{cpf}")
	public ResponseEntity<Assets> addAsset(@PathVariable("id") String cpf, @RequestBody Assets assets) {
		try {
			Client clientData = clientRepository.findByCpf(cpf);
			Assets assetsData = assetRepository.save(new Assets(clientData, assets.getValue(), assets.getDescription()));
			return new ResponseEntity<>(assetsData, HttpStatus.CREATED);
		} catch (Exception e) {
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@GetMapping("/asset/{id}")
	public ResponseEntity<List<Assets>> listAssets(@PathVariable("cpf") String cpf) {
		try{
			Client data = clientRepository.findByCpf(cpf);
			return new ResponseEntity<>(assetRepository.findByClient(data), HttpStatus.OK);

		}catch (Exception e ){
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@DeleteMapping("/asset/{id}")
	public ResponseEntity<HttpStatus> deleteAsset(@PathVariable("id") long id) {
		try {
			assetRepository.deleteById(id);
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}



}
