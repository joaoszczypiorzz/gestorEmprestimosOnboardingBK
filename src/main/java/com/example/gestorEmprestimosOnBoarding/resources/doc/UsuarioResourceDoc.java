package com.example.gestorEmprestimosOnBoarding.resources.doc;

import com.example.gestorEmprestimosOnBoarding.dto.UsuarioDto;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;

import java.util.List;


@Tag(name = "usuarios", description = "Rota de Usuários")
public interface UsuarioResourceDoc {

    @Operation(summary = "Deleta um usuário")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Operação realizada com sucesso (Sem conteúdo)"),
            @ApiResponse(responseCode = "404", description = "Recurso não encontrado com o ID informado")
    })
    ResponseEntity<Void> delete(Integer id);

    @Operation(summary = "Retorna um usuário a partir do Id informado")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Operação realizada com sucesso"),
            @ApiResponse(responseCode = "404", description = "Recurso não encontrado com o ID informado")
    })
    ResponseEntity<UsuarioDto> find(Integer id);

    @Operation(summary = "Retorna todos os usuários cadastrados no Banco de Dados")
    @ApiResponse(responseCode = "200", description = "Operação realizada com sucesso")
    ResponseEntity<List<UsuarioDto>> findAll();

    @Operation(summary = "Adiciona um novo usuário")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Operação realizada com sucesso"),
            @ApiResponse(responseCode = "404", description = "Recurso com dados inválidos/faltando")
    })
    ResponseEntity<Void> insert(UsuarioDto objDto);

    @Operation(summary = "Atualiza todas as informações de um Usuário")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Operação realizada com sucesso"),
            @ApiResponse(responseCode = "404", description = "Recurso com dados inválidos/faltando")
    })
    ResponseEntity<Void> update(UsuarioDto objDto, Integer id);
}
