package br.com.cep.service.impl;

import br.com.cep.converter.AddressDtoConverter;
import br.com.cep.util.ValidationsCep;
import br.com.cep.model.Address;
import br.com.cep.model.dto.AddressDTO;
import br.com.cep.service.partner.ViaCepClient;
import br.com.cep.templates.model.AddressDtoTemplate;
import br.com.cep.templates.model.AddressTemplate;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.BDDMockito;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

@ExtendWith(MockitoExtension.class)
public class AddressServiceImplTest {

    private final AddressDtoTemplate addressDtoTemplate = AddressDtoTemplate.getInstance();
    private final AddressTemplate addressTemplate = AddressTemplate.getInstance();

    @Mock
    private ViaCepClient viaCepClient;

    @Mock
    private AddressDtoConverter addressDtoConverter;

    @InjectMocks
    private AddressServiceImpl addressServiceImpl;

    @Mock
    private ValidationsCep validationsCep;

    @Test
    void shouldReturnAddressByCep() throws Exception {

        AddressDTO addressDTO = addressDtoTemplate.getAddressDtoValid();
        Address address = addressTemplate.getAddressValid();
        String cep = "01001-000";

        BDDMockito.when(validationsCep.validateCEP(cep)).thenReturn(true);
        BDDMockito.when(viaCepClient.findAddressByCep(cep)).thenReturn(address);
        BDDMockito.when(validationsCep.checksRegion(address.getUf())).thenReturn("Sudeste");
        BDDMockito.when(validationsCep.getValueFrete("Sudeste")).thenReturn(addressDTO.getFrete());
        BDDMockito.when(addressDtoConverter.converterAddressDto(address, addressDTO.getFrete())).thenReturn(addressDTO);

        AddressDTO response = addressServiceImpl.getAddressByCep(cep);

        assertEquals(addressDTO, response);
    }

    @Test
    void shouldReturnAddressByCepWithoutSuccess() throws Exception {
        String cep = "01001-000";

        BDDMockito.when(validationsCep.validateCEP(cep)).thenReturn(true);

        assertThrows(NullPointerException.class,() -> addressServiceImpl.getAddressByCep(cep));
    }
}
