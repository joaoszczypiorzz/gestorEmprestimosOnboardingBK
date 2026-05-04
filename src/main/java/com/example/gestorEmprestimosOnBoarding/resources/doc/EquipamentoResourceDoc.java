package com.example.gestorEmprestimosOnBoarding.resources.doc;

import com.example.gestorEmprestimosOnBoarding.dto.EquipamentoDto;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;

import java.util.List;

@Tag(name = "equipamentos", description = "Rota de Equipamentos")
public interface EquipamentoResourceDoc {

    @Operation(summary = "Deleta um Equipamento")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Operação realizada com sucesso (Sem conteúdo)"),
            @ApiResponse(responseCode = "404", description = "Recurso não encontrado com o ID informado")
    })
    ResponseEntity<Void> delete(Integer id);

    @Operation(summary = "Retorna um Equipamento a partir do Id informado")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Operação realizada com sucesso"),
            @ApiResponse(responseCode = "404", description = "Recurso não encontrado com o ID informado")
    })
    ResponseEntity<EquipamentoDto> find(Integer id);

    @Operation(summary = "Retorna todos os Equipamentos cadastrados no Banco de Dados")
    @ApiResponse(responseCode = "200", description = "Operação realizada com sucesso")
    ResponseEntity<List<EquipamentoDto>> findAll();

    @Operation(summary = "Cria um novo Equipamento")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Operação realizada com sucesso"),
            @ApiResponse(responseCode = "404", description = "Recurso com dados inválidos/faltando")
    })
    ResponseEntity<Void> insert(EquipamentoDto objDto);

    @Operation(summary = "Atualiza todas as informações de um Equipamento")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Operação realizada com sucesso"),
            @ApiResponse(responseCode = "404", description = "Recurso com dados inválidos/faltando")
    })
    ResponseEntity<Void> update(EquipamentoDto objDto, Integer id);
}
