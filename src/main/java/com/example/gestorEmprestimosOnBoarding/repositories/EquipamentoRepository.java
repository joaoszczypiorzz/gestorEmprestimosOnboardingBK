package com.example.gestorEmprestimosOnBoarding.repositories;

import com.example.gestorEmprestimosOnBoarding.domain.Equipamento;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public interface EquipamentoRepository extends JpaRepository <Equipamento, Integer> {
    @Transactional(readOnly = true)
    boolean existsByPatrimonio(String patrimonio);

    @Transactional(readOnly = true)
    boolean existsByPatrimonioAndIdNot(String patrimonio, Integer id);
}
