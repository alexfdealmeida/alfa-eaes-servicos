package br.com.unialfa.pos.soa.rest.core.model.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import br.com.unialfa.pos.soa.rest.core.model.entity.UsuarioTarefa;

public interface UsuarioTarefaRepository extends JpaRepository<UsuarioTarefa, Long> {

//	List<UsuarioTarefa> findByTarefaId(Long idTarefaSelecionada);

	List<UsuarioTarefa> findByRemoteUsuarioId(Long remoteUsuarioId);

	Page<UsuarioTarefa> findByTarefaId(Pageable pageable, Long id);

	List<UsuarioTarefa> findByTarefaId(Long id);

}
