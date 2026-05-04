package com.example.gestorEmprestimosOnBoarding.resources.doc;

import com.example.gestorEmprestimosOnBoarding.domain.Emprestimo;
import com.example.gestorEmprestimosOnBoarding.dto.EmprestimoDto;


import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;

import java.util.List;

@Tag(name = "emprestimos", description = "Rota de Empréstimos")
public interface EmprestimoResourceDoc {

    @Operation(summary = "Cria um novo Empréstimo")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Operação realizada com sucesso"),
            @ApiResponse(responseCode = "404", description = "Recurso com dados dados inválidos/faltando")
    })
    public ResponseEntity<Void> insert(EmprestimoDto objDto);

    @Operation(summary = "Retorna todos os empréstimos cadastrados no Banco de Dados")
    @ApiResponse(responseCode = "200", description = "Operação realizada com sucesso")
    ResponseEntity<List<Emprestimo>> findAll();

    @Operation(summary = "Realiza um Empréstimo")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Operação realizada com sucesso (Sem Conteúdo)"),
            @ApiResponse(responseCode = "404", description = "Id informado inválido ou inexistente")
    })
    ResponseEntity<Void> devolucao(Integer id);
}
