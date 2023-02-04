package br.com.cep.templates.model;

import br.com.cep.model.dto.AddressDTO;

import java.math.BigDecimal;

public class AddressDtoTemplate {

    private static final AddressDtoTemplate instance = new AddressDtoTemplate();

    public AddressDtoTemplate() {
    }

    public AddressDTO getAddressDtoValid() {
        return AddressDTO.builder().cep("01001-000").rua("Praça da Sé").complemento("lado ímpar").bairro("Sé").cidade("São Paulo").estado("SP").frete(BigDecimal.valueOf(7.85)).build();
    }

    public static AddressDtoTemplate getInstance() {
        return instance;
    }
}
