package com.example.gestorEmprestimosOnBoarding.domain;

import com.example.gestorEmprestimosOnBoarding.enums.StatusEquipamento;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.DynamicInsert;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@Entity(name = "equipamentos")
@DynamicInsert
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@JsonPropertyOrder({"id", "patrimonio", "nome", "tipo", "categoria", "status"})
public class Equipamento implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @EqualsAndHashCode.Include
    private Integer id;

    @Column(unique = true)
    private String patrimonio;

    private String nome;
    private String tipo;
    private String categoria;


    private StatusEquipamento status;

    @OneToMany(mappedBy = "equipamento")
    private List<Emprestimo> emprestimos = new ArrayList<>();

    public Equipamento(Integer id, String patrimonio, String nome, String tipo, String categoria, StatusEquipamento status) {
        this.id = id;
        this.patrimonio = patrimonio;
        this.nome = nome;
        this.tipo = tipo;
        this.categoria = categoria;
        this.status = status;
    }
}
