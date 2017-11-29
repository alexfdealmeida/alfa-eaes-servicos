package br.com.unialfa.pos.soa.usuarioService.soap.core.model.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.unialfa.pos.soa.usuarioService.soap.core.model.entity.UsuarioTarefa;

public interface UsuarioTarefaRepository extends JpaRepository<UsuarioTarefa,Long> {

	List<UsuarioTarefa> findByIdTarefaRemotaOrderByUsuarioNome(Long idTarefaSelecionada);

	List<UsuarioTarefa> findByUsuarioId(Long id);

	List<UsuarioTarefa> findByIdTarefaRemota(Long id);

}
