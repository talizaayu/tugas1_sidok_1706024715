package apap.tugas.sidok.repository;

import apap.tugas.sidok.model.SpesialisasiModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface SpesialisasiDb extends JpaRepository<SpesialisasiModel, Long> {
    List<SpesialisasiModel> findAll();
    Optional<SpesialisasiModel> findByIdSpesialisasi(Long idSpesialisasi);
}