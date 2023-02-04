package br.com.cep.converter;


import br.com.cep.model.Address;
import br.com.cep.model.dto.AddressDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;

@Component
@RequiredArgsConstructor
public class AddressDtoConverter {

    public AddressDTO converterAddressDto(Address address, BigDecimal frete) {
        return AddressDTO.builder()
                .cep(address.getCep())
                .bairro(address.getBairro())
                .complemento(address.getComplemento())
                .rua(address.getLogradouro())
                .cidade(address.getLocalidade())
                .estado(address.getUf())
                .frete(frete)
                .build();
    }
}
