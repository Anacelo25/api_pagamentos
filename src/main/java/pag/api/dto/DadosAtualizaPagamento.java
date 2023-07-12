package pag.api.dto;

import jakarta.validation.constraints.NotNull;
import pag.api.controller.Pagamento;
import pag.api.enums.StatusDoPagamento;

public record DadosAtualizaPagamento(
        @NotNull
        Long id,
        StatusDoPagamento status
) {
        public DadosAtualizaPagamento(Pagamento pagamento){
                this(pagamento.getId(), pagamento.getStatus());
        }
}
