package com.example.gestorEmprestimosOnBoarding.services;

import com.example.gestorEmprestimosOnBoarding.domain.Emprestimo;
import com.example.gestorEmprestimosOnBoarding.domain.Equipamento;
import com.example.gestorEmprestimosOnBoarding.domain.Usuario;
import com.example.gestorEmprestimosOnBoarding.dto.EmprestimoDto;
import com.example.gestorEmprestimosOnBoarding.enums.StatusEmprestimo;
import com.example.gestorEmprestimosOnBoarding.enums.StatusEquipamento;
import com.example.gestorEmprestimosOnBoarding.repositories.EmprestimoRepository;
import com.example.gestorEmprestimosOnBoarding.repositories.EquipamentoRepository;
import com.example.gestorEmprestimosOnBoarding.repositories.UsuarioRepository;
import com.example.gestorEmprestimosOnBoarding.services.exceptions.IllegalEmprestimoState;
import com.example.gestorEmprestimosOnBoarding.services.exceptions.MultipleObjectsNotFoundException;
import com.example.gestorEmprestimosOnBoarding.services.exceptions.ObjNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class EmprestimoService {

    @Autowired
    private EmprestimoRepository repo;

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private EquipamentoRepository equipamentoRepository;

    public Emprestimo insertEmprestimo(Emprestimo obj) throws IllegalStateException{
        if(!obj.getEquipamento().getStatus().equals(StatusEquipamento.DISPONIVEL)){
            throw new IllegalEmprestimoState("Erro: Equipamento não está disponível para empréstimos!");
        }
        obj.setId(null);
        obj.getEquipamento().setStatus(StatusEquipamento.EM_USO);
        obj.setDataDevolucaoPrevista(LocalDate.from(LocalDateTime.now().plusDays(7)));
        obj.setStatus(StatusEmprestimo.ATIVO);
        obj = repo.save(obj);
        return obj;
    }

    public Emprestimo fromDto(EmprestimoDto objDto) throws MultipleObjectsNotFoundException{
        List<String> erros = new ArrayList<>();
        Emprestimo entidade = new Emprestimo();

        Optional<Usuario> optUser = usuarioRepository.findById(objDto.getUserId());
        Optional<Equipamento> optEquipamento = equipamentoRepository.findById(objDto.getEquipamentoId());
        if (optUser.isEmpty()) {
            erros.add("Usuário não encontrado para o ID: " + objDto.getUserId());
        }
        if (optEquipamento.isEmpty()) {
            erros.add("Equipamento não encontrado para o ID: " + objDto.getEquipamentoId());
        }
        if (!erros.isEmpty()) {
            throw new MultipleObjectsNotFoundException(erros);
        }


        entidade.setUser(optUser.get());
        entidade.setEquipamento(optEquipamento.get());

        return entidade;
    }

    public List<Emprestimo> findAll(){
        return repo.findAll();
    }

    public Emprestimo find(Integer id) throws ObjNotFoundException{
        Optional<Emprestimo> obj = repo.findById(id);
        return obj.orElseThrow(() -> new ObjNotFoundException(
                "Id informado inválido ou inexistente!"));
    }

    public void delete(Integer id){
        find(id);
        repo.deleteById(id);
    }

    @Transactional
    public Emprestimo devolucao(Integer id) throws ObjNotFoundException{
        Emprestimo emprestimo = find(id);

        emprestimo.setDataDevolucaoReal(LocalDateTime.now());
        emprestimo.getEquipamento().setStatus(StatusEquipamento.DISPONIVEL);
        emprestimo.setStatus(StatusEmprestimo.INATIVO);

        Integer atraso = emprestimo.calcDiasAtraso();
        emprestimo.setMulta(atraso * 5.00);

        return repo.save(emprestimo);
    }


}
