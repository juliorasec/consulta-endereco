package br.com.cep.util;

import br.com.cep.model.Address;
import br.com.cep.templates.model.AddressDtoTemplate;
import br.com.cep.templates.model.AddressTemplate;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

@ExtendWith(MockitoExtension.class)
public class ValidationsCepTest {

    private final AddressTemplate addressTemplate = AddressTemplate.getInstance();
    private final AddressDtoTemplate addressDtoTemplate = AddressDtoTemplate.getInstance();
    private final String cepValid = "01001-000";
    private final String cepInvalid = "0100100000";

    @InjectMocks
    private ValidationsCep validationsCep;

    @Test
    void shouldReturnRegionSudeste() {
        Address address = addressTemplate.getAddressValid();

        String region = validationsCep.checksRegion(address.getUf());
        assertEquals(region, "Sudeste");
    }

    @Test
    void shouldReturnCepValid() {
        boolean value = validationsCep.validateCEP(cepValid);
        assertEquals(true, value);
    }

    @Test
    void shouldReturnCepInvalid() {
        assertThrows(IllegalArgumentException.class,() -> validationsCep.validateCEP(cepInvalid));
    }

    @Test
    void shouldReturnFreteValueSudeste() {
        Address address = addressTemplate.getAddressValid();
        String region = validationsCep.checksRegion(address.getUf());
        BigDecimal valueFrete = validationsCep.getValueFrete(region);

        assertEquals(BigDecimal.valueOf(7.85), valueFrete);
    }
}
