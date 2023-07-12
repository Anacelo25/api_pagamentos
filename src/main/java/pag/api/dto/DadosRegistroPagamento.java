package pag.api.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import pag.api.enums.MetodoDePagamento;
import pag.api.enums.StatusDoPagamento;

import java.math.BigDecimal;

public record DadosRegistroPagamento(
        @NotBlank
        String codigododebito,
        @NotBlank
        String numerodocartao,
        @NotBlank
        @Pattern(regexp = "\\d{11,20}")
        String Cpf_Cnpj,
        @NotNull
        BigDecimal valordopagamento,
        @NotNull
        MetodoDePagamento metododepagamento,
        @NotNull
        StatusDoPagamento status
) {
}
