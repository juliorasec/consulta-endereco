package br.com.cep.util;

import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.ControllerAdvice;

import java.math.BigDecimal;

@Component
@ControllerAdvice
public class ValidationsCep {

    public String checksRegion(String uf ) {
        if (Util.SUDESTE.contains(uf)) {
            return "Sudeste";
        } else if (Util.CENTRO_OESTE.contains(uf)) {
            return "Centro-Oeste";
        } else if (Util.NORDESTE.contains(uf)) {
            return "Nordeste";
        } else if (Util.SUL.contains(uf)) {
            return "Sul";
        } else if (Util.NORTE.contains(uf)) {
            return "Norte";
        }
        return "Região não encontrada";
    }

    public boolean validateCEP(String cep) {
        String cepRegex = "\\d{5}-\\d{3}";
        String cepRegexSemHifen = "\\d{8}";
        boolean value =  cep.matches(cepRegex) || cep.matches(cepRegexSemHifen);
        if(!value) {
            throw new IllegalArgumentException("CEP com valores incorretos, cep tem que possuir 8 dígitos");
        }
        return value;
    }

    public BigDecimal getValueFrete(String region) {
        switch (region) {
            case "Sudeste":
                return BigDecimal.valueOf(7.85);
            case "Centro-Oeste":
                return BigDecimal.valueOf(12.50);
            case "Nordeste":
                return BigDecimal.valueOf(15.98);
            case "Sul":
                return BigDecimal.valueOf(17.30);
            case "Norte":
                return BigDecimal.valueOf(20.83);
            default:
                return BigDecimal.ZERO;
        }
    }
}
