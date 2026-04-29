package com.example.gestorEmprestimosOnBoarding.repositories;

import com.example.gestorEmprestimosOnBoarding.domain.Usuario;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UsuarioRepository extends JpaRepository <Usuario, Integer> {

    @Transactional(readOnly = true)
    Usuario findByEmail(String email);
}
