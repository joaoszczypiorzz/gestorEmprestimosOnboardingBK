package com.example.gestorEmprestimosOnBoarding.domain;

import com.example.gestorEmprestimosOnBoarding.enums.StatusEquipamento;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity(name = "equipamentos")
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
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
