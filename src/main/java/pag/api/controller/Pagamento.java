package pag.api.controller;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import pag.api.dto.DadosAtualizaPagamento;
import pag.api.dto.DadosRegistroPagamento;
import pag.api.enums.MetodoDePagamento;
import pag.api.enums.StatusDoPagamento;

import java.math.BigDecimal;
@Table(name = "pagamento")
@Entity(name = "Pagamento")
@Getter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
public class Pagamento {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String codigododebito;
    private String numerodocartao;
    private String Cpf_Cnpj;
    private BigDecimal valordopagamento;
    @Enumerated(EnumType.STRING)
    private MetodoDePagamento metododepagamento;
    @Enumerated(EnumType.STRING)
    private StatusDoPagamento status;

    public Pagamento(DadosRegistroPagamento dados) {
        this.codigododebito = dados.codigododebito();
        this.numerodocartao = dados.numerodocartao();
        this.Cpf_Cnpj = dados.Cpf_Cnpj();
        this.valordopagamento = dados.valordopagamento();
        this.metododepagamento = dados.metododepagamento();
        this.status = dados.status();
    }


    public void atualizaPagamento(DadosAtualizaPagamento dados) {
        var newStatus = dados.status();
        if (newStatus == StatusDoPagamento.Processamento_Pendente){
            System.out.println("Desculpe ID " + dados.id() + " com falha no processamento ou processamento concluído.");
        } else if (newStatus == StatusDoPagamento.Processado_com_Sucesso) {
            System.out.println("Não é possível alterar o status de pagamento de ID " + dados.id() + " pagamento processado com sucesso.");
        } else if (newStatus == StatusDoPagamento.Falha_no_Processamento) {
            System.out.println("Impossível atualizar o status de pagamento com ID " + dados.id() + " pendência no processamento.");
        }
        System.out.println("Status de pagamento atualizado com sucesso.");
    }
}
