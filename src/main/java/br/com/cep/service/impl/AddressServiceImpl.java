package br.com.cep.service.impl;

import br.com.cep.converter.AddressDtoConverter;
import br.com.cep.util.ValidationsCep;
import br.com.cep.model.Address;
import br.com.cep.model.dto.AddressDTO;
import br.com.cep.model.dto.RequestDTO;
import br.com.cep.service.AddressService;
import br.com.cep.service.partner.ViaCepClient;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.ControllerAdvice;

import java.util.Objects;

@Service
@RequiredArgsConstructor
@ControllerAdvice
public class AddressServiceImpl implements AddressService {

    private final ViaCepClient client;
    private final AddressDtoConverter converter;
    private final ValidationsCep validationsCep;

    public AddressDTO getAddressByCep(RequestDTO requestDTO) {
        try {
            validationsCep.validateCEP(requestDTO.getCep());
            Address address = client.findAddressByCep(requestDTO.getCep());
            if (Objects.isNull(address.getCep())) {
                throw new NullPointerException("CEP não foi encontrado, tente com um cep válido.");
            }
            String region = validationsCep.checksRegion(address.getUf());
            return converter.converterAddressDto(address, validationsCep.getValueFrete(region));
        }catch (IllegalArgumentException e) {
            throw new IllegalArgumentException(e.getMessage());
        }
    }


}
