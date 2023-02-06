package br.com.cep.service;

import br.com.cep.model.dto.AddressDTO;

public interface AddressService {

    AddressDTO getAddressByCep(String cep);
}
