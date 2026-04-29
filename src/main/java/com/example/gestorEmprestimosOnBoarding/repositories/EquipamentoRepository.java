package com.example.gestorEmprestimosOnBoarding.repositories;

import com.example.gestorEmprestimosOnBoarding.domain.Equipamento;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EquipamentoRepository extends JpaRepository <Equipamento, Integer> {

}
