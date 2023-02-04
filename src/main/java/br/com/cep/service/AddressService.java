package br.com.cep.service;

import br.com.cep.model.dto.AddressDTO;
import br.com.cep.model.dto.RequestDTO;

public interface AddressService {

    AddressDTO getAddressByCep(RequestDTO requestDTO);
}
