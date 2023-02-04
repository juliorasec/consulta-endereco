package br.com.cep.controller;

import br.com.cep.model.dto.AddressDTO;
import br.com.cep.model.dto.RequestDTO;
import br.com.cep.service.AddressService;
import br.com.cep.templates.model.AddressDtoTemplate;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentMatchers;
import org.mockito.BDDMockito;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.ResponseEntity;

import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(MockitoExtension.class)
public class AddressControllerTest {

    private final AddressDtoTemplate addressDtoTemplate = AddressDtoTemplate.getInstance();

    @Mock
    private AddressService addressService;

    @InjectMocks
    private AddressController addressController;

    @Test
    void shouldReturnAddressByCep() throws Exception {

        AddressDTO addressDTO = addressDtoTemplate.getAddressDtoValid();
        RequestDTO requestDTO = new RequestDTO("01001-000");

        BDDMockito.when(addressService.getAddressByCep(ArgumentMatchers.any(RequestDTO.class))).thenReturn(addressDTO);

        ResponseEntity<AddressDTO> response = addressController.getAddress(requestDTO);

        assertEquals(200, response.getStatusCodeValue());
        assertEquals(addressDTO, response.getBody());
    }

}
