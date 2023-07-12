package pag.api.controller;


import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;
import pag.api.dto.DadosAtualizaPagamento;
import pag.api.dto.DadosRegistroPagamento;
import pag.api.dto.FiltroPagamento;
import pag.api.enums.StatusDoPagamento;
import pag.api.resposi.PagamentoRepository;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("pagamento")
public class ControllerPagamento {

    @Autowired
    private PagamentoRepository repository;

    @PostMapping
    @Transactional
    public ResponseEntity registro(@RequestBody @Valid DadosRegistroPagamento dados)
    {
        var pagar = new Pagamento(dados);
        repository.save(pagar);
        return ResponseEntity.status(HttpStatus.CREATED).build();
         }

    @GetMapping
    public ResponseEntity<List<FiltroPagamento>> filtroPagamentos(@RequestParam(value = "codigododebito", required = false) String codigododebito,
                                                  @RequestParam(value = "Cpf_Cnpj", required = false) String Cpf_Cnpj,
                                                  @RequestParam(value = "status", required = false) String status){
            Specification<FiltroPagamento> specification = Specification.where(null);
            if (codigododebito != null){
                specification.and((root, query, criteriaBuilder) -> criteriaBuilder.equal(root.get("codigododebito"),codigododebito));
                    }
                        if (Cpf_Cnpj != null){
                            specification.and((root, query, criteriaBuilder) -> criteriaBuilder.equal(root.get("Cpf_Cnpj"),Cpf_Cnpj));
                                }
                                    if (status != null){
                        specification.and((root, query, criteriaBuilder) -> criteriaBuilder.equal(root.get("status"),status));
        }
        var filtro = repository.findAll().stream().map(FiltroPagamento::new).toList();
        return ResponseEntity.ok(filtro);
    }

    @PutMapping
    @Transactional
    public ResponseEntity atual(@RequestBody @Valid DadosAtualizaPagamento dados){
        var atualizar = repository.getReferenceById(dados.id());
        atualizar.atualizaPagamento(dados);
        return ResponseEntity.ok(new DadosAtualizaPagamento(atualizar));
    }

    @DeleteMapping("/{id}")
    @Transactional
    public ResponseEntity<String> excluir(@PathVariable Long id){
        Optional<Pagamento> optional = repository.findById(id);
        if (optional.isPresent()){
            Pagamento pagamento = optional.get();
            if (pagamento.getStatus().equals(StatusDoPagamento.Processamento_Pendente)){
                repository.delete(pagamento);
                System.out.println("Pagamento deletado com sucesso.");
            } else if (pagamento.getStatus().equals(StatusDoPagamento.Processado_com_Sucesso)) {
                System.out.println("Pagamento não pode ser deletado, pagamento processado com sucesso.");
            } else {
                System.out.println("Pagamento não encontrado.");
            }
        }
                return ResponseEntity.noContent().build();
    }

}
