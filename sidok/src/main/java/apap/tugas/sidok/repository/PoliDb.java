package apap.tugas.sidok.repository;

import apap.tugas.sidok.model.PoliModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PoliDb extends JpaRepository<PoliModel, Long> {
    List<PoliModel> findAll();
    PoliModel findByIdPoli(Long idPoli);
    PoliModel findTopByOrderByIdPoliDesc();
}