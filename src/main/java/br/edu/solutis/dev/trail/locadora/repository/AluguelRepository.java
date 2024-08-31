package br.edu.solutis.dev.trail.locadora.repository;

import br.edu.solutis.dev.trail.locadora.model.entity.Aluguel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository

public interface AluguelRepository extends JpaRepository<Aluguel, Long> {
    @Query("SELECT a FROM Aluguel a WHERE a.carro.id = :carroId AND " +
            "((a.dataEntrega <= :dataFim AND a.dataDevolucao >= :dataInicio) OR " +
            "(a.dataEntrega <= :dataInicio AND a.dataDevolucao >= :dataFim))")
    List<Aluguel> findConflictingAlugueis(@Param("carroId") Long carroId,
                                          @Param("dataInicio") LocalDate dataInicio,
                                          @Param("dataFim") LocalDate dataFim);
}