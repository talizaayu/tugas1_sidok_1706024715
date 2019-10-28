package apap.tugas.sidok.repository;

import apap.tugas.sidok.model.JadwalJagaModel;
import apap.tugas.sidok.model.DokterModel;
import apap.tugas.sidok.model.PoliModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface JadwalJagaDb extends JpaRepository<JadwalJagaModel, Long> {
    List<JadwalJagaModel> findJadwalJagaByDokter(DokterModel dokter);
    List<JadwalJagaModel> findJadwalJagaByPoli(PoliModel poli);
}