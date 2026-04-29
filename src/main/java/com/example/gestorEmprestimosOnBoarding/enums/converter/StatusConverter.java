package com.example.gestorEmprestimosOnBoarding.enums.converter;

import com.example.gestorEmprestimosOnBoarding.enums.StatusEquipamento;
import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;

import java.util.stream.Stream;

@Converter(autoApply = true)
public class StatusConverter implements AttributeConverter<StatusEquipamento, String> {

    // Transforma o Enum do Java para o texto do Banco
    @Override
    public String convertToDatabaseColumn(StatusEquipamento status) {
        if (status == null) {
            return null;
        }
        return status.getDescricao();
    }

    // Transforma o texto do Banco de volta para o Enum do Java
    @Override
    public StatusEquipamento convertToEntityAttribute(String descricaoBanco) {
        if (descricaoBanco == null) {
            return null;
        }
        return Stream.of(StatusEquipamento.values())
                .filter(s -> s.getDescricao().equals(descricaoBanco))
                .findFirst()
                .orElseThrow(IllegalArgumentException::new);
    }
}
