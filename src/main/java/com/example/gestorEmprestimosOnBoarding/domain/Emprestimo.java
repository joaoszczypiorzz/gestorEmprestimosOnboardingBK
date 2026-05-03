package com.example.gestorEmprestimosOnBoarding.domain;


import com.example.gestorEmprestimosOnBoarding.enums.StatusEmprestimo;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;

@Entity(name = "emprestimos")
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@NoArgsConstructor
@Getter
@Setter
public class Emprestimo implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @EqualsAndHashCode.Include
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "usuario_id")
    private Usuario user;

    @ManyToOne
    @JoinColumn(name = "equipamento_id")
    private Equipamento equipamento;

    @JsonFormat(pattern = "dd/MM/yyyy HH:mm:ss")
    @CreationTimestamp
    private LocalDateTime dataEmprestimo;

    @JsonFormat(pattern = "dd/MM/yyyy")
    private LocalDate dataDevolucaoPrevista;

    @JsonFormat(pattern = "dd/MM/yyyy")
    private LocalDate dataDevolucaoReal;

    @Enumerated(EnumType.STRING)
    private StatusEmprestimo status;

    private double multa;

    public Emprestimo(Usuario usu, Equipamento equi, LocalDateTime dataEmpre, LocalDate dataDevolucaoPre,
                     LocalDate dataDevolucaoRea, StatusEmprestimo statusEmprestimo, double multas){
        user = usu;
        equipamento = equi;
        dataEmprestimo = dataEmpre;
        dataDevolucaoPrevista = dataDevolucaoPre;
        dataDevolucaoReal = dataDevolucaoRea;
        status = statusEmprestimo;
        multa = multas;
    }

    public Integer calcDiasAtraso(){
        Long diasAtraso = ChronoUnit.DAYS.between(this.dataDevolucaoPrevista,this.dataDevolucaoReal);

        if(diasAtraso < 0){
            return 0;
        }

        return Math.toIntExact(diasAtraso);
    }
}
