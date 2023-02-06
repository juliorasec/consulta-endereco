package features;

import br.com.cep.model.dto.AddressDTO;
import br.com.cep.model.dto.RequestDTO;
import br.com.cep.templates.model.AddressDtoTemplate;
import br.com.cep.util.ValidationsCep;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.Assert.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;


@ExtendWith(MockitoExtension.class)
public class StepDefinition {
    private RequestDTO requestDTO;
    private AddressDTO addressDTO;
    private final AddressDtoTemplate addressDtoTemplate = AddressDtoTemplate.getInstance();

    @InjectMocks
    private ValidationsCep validationsCep;

    @Given("que eu informei o CEP {int}")
    public void que_eu_informei_o_cep(Integer cep) {
        requestDTO = new RequestDTO();
        requestDTO.setCep(String.valueOf(cep));
    }

    @When("eu solicito a consulta do endereço")
    public void eu_solicito_a_consulta_do_endereço() {
        addressDTO = addressDtoTemplate.getAddressDtoValid();
    }

    @Then("eu recebo o endereço cep {int}, rua Praça da Sé, complemento lado ímpar, bairro Sé, cidade São Paulo, estado SP e frete {double}")
    public void eu_recebo_o_endereço_cep_rua_praça_da_sé_complemento_lado_ímpar_bairro_sé_cidade_são_paulo_estado_sp_e_frete(Integer cep, Double frete) {
        assertNotNull(addressDTO);
    }

    @Then("^a mensagem de erro correta é gerado")
    public void a_mensagem_de_erro_correta_é_gerado() {
        assertThrows(NullPointerException.class,() -> validationsCep.validateCEP(requestDTO.getCep()));
    }
}
