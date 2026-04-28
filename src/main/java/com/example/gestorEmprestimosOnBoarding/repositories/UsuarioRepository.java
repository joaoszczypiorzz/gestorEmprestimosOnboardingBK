package com.example.gestorEmprestimosOnBoarding.repositories;

import com.example.gestorEmprestimosOnBoarding.domain.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UsuarioRepository extends JpaRepository <Usuario, Integer> {

}
