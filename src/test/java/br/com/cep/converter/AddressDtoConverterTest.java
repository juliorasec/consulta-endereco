package br.com.cep.converter;


import br.com.cep.model.Address;
import br.com.cep.model.dto.AddressDTO;
import br.com.cep.templates.model.AddressDtoTemplate;
import br.com.cep.templates.model.AddressTemplate;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

@ExtendWith(MockitoExtension.class)
public class AddressDtoConverterTest {

    private final AddressDtoTemplate addressDtoTemplate = AddressDtoTemplate.getInstance();
    private final AddressTemplate addressTemplate = AddressTemplate.getInstance();


    @InjectMocks
    private AddressDtoConverter addressDtoConverter;

    @Test
    void shouldReturnAddressByCep() {
        AddressDTO addressDTO = addressDtoTemplate.getAddressDtoValid();
        Address address = addressTemplate.getAddressValid();

        assertDoesNotThrow(() -> addressDtoConverter.converterAddressDto(address, addressDTO.getFrete()));
    }
}
