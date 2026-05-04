package com.example.gestorEmprestimosOnBoarding.repositories;

import com.example.gestorEmprestimosOnBoarding.domain.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public interface UsuarioRepository extends JpaRepository <Usuario, Integer> {

    @Transactional(readOnly = true)
    boolean existsByEmail(String email);

    @Transactional
    boolean existsByEmailAndIdNot(String email, Integer id);
}
