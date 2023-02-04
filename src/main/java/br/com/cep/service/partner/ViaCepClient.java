package br.com.cep.service.partner;

import br.com.cep.model.Address;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(
        name = "corp-endereco",
        path = "/",
        url = "https://viacep.com.br")
public interface ViaCepClient {

    @GetMapping("/ws/{cep}/json")
    Address findAddressByCep(@PathVariable("cep") String cep);
}
