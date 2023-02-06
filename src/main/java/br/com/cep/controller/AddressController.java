package br.com.cep.controller;

import br.com.cep.model.dto.AddressDTO;
import br.com.cep.service.AddressService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

@RestController
@RequestMapping("v2/consulta-endereco")
@RequiredArgsConstructor
@Slf4j
public class AddressController {

    private final AddressService service;

    @GetMapping(value = "/{cep}")
    public ResponseEntity<AddressDTO> getAddress(@NotNull @Valid @PathVariable final String cep) {
        return ResponseEntity.ok(service.getAddressByCep(cep));
    }
}

