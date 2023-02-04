package br.com.cep.service.impl;

import br.com.cep.converter.AddressDtoConverter;
import br.com.cep.util.ValidationsCep;
import br.com.cep.model.Address;
import br.com.cep.model.dto.AddressDTO;
import br.com.cep.model.dto.RequestDTO;
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
    private ValidationsCep validationsCepConverter;

    @Test
    void shouldReturnAddressByCep() throws Exception {

        AddressDTO addressDTO = addressDtoTemplate.getAddressDtoValid();
        Address address = addressTemplate.getAddressValid();
        RequestDTO requestDTO = new RequestDTO("01001-000");

        BDDMockito.when(validationsCepConverter.validateCEP(requestDTO.getCep())).thenReturn(true);
        BDDMockito.when(viaCepClient.findAddressByCep(requestDTO.getCep())).thenReturn(address);
        BDDMockito.when(validationsCepConverter.checksRegion(address.getUf())).thenReturn("Sudeste");
        BDDMockito.when(validationsCepConverter.getValueFrete("Sudeste")).thenReturn(addressDTO.getFrete());
        BDDMockito.when(addressDtoConverter.converterAddressDto(address, addressDTO.getFrete())).thenReturn(addressDTO);

        AddressDTO response = addressServiceImpl.getAddressByCep(requestDTO);

        assertEquals(addressDTO, response);
    }

    @Test
    void shouldReturnAddressByCepWithoutSuccess() throws Exception {
        RequestDTO requestDTO = new RequestDTO("01001-000");

        BDDMockito.when(validationsCepConverter.validateCEP(requestDTO.getCep())).thenReturn(true);

        assertThrows(NullPointerException.class,() -> addressServiceImpl.getAddressByCep(requestDTO));
    }
}
