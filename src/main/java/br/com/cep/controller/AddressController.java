package br.com.cep.controller;

import br.com.cep.model.dto.AddressDTO;
import br.com.cep.model.dto.RequestDTO;
import br.com.cep.service.AddressService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("v2/consulta-endereco")
@RequiredArgsConstructor
@Slf4j
public class AddressController {

    private final AddressService service;

    @PostMapping
    public ResponseEntity<AddressDTO> getAddress(@RequestBody RequestDTO requestDTO) {
        return ResponseEntity.ok(service.getAddressByCep(requestDTO));
    }
}

