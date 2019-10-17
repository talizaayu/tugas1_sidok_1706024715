package apap.tugas.sidok.service;

import java.util.List;
import java.util.Optional;

import apap.tugas.sidok.model.SpesialisasiModel;

public interface SpesialisasiService {
    List<SpesialisasiModel> getSpesialisasiList();
    Optional<SpesialisasiModel> getSpesialisasiByIdSpesialisasi(Long idSpesialisasi);
}