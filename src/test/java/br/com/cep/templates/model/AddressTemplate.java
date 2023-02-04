package br.com.cep.templates.model;

import br.com.cep.model.Address;

public class AddressTemplate {

    private static final AddressTemplate instance = new AddressTemplate();

    public AddressTemplate() {
    }

    public Address getAddressValid() {
        return Address.builder().cep("01001-000").logradouro("Praça da Sé").complemento("lado ímpar").bairro("Sé").localidade("São Paulo").uf("SP").build();
    }

    public static AddressTemplate getInstance() {
        return instance;
    }
}
