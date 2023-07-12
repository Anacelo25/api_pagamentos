package pag.api.dto;

import pag.api.controller.Pagamento;
import pag.api.enums.StatusDoPagamento;

public record FiltroPagamento(
        String codigododebito,
        String Cpf_Cnpj,
        StatusDoPagamento status
) {
    public FiltroPagamento(Pagamento pag){
        this(pag.getCodigododebito(), pag.getCpf_Cnpj(), pag.getStatus());
    }
}
