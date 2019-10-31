package apap.tugas.sidok.service;

import java.util.List;
import java.util.Optional;
import java.util.Date;

import apap.tugas.sidok.model.DokterModel;

public interface DokterService {
    void addDokter(DokterModel dokter);
    List<DokterModel> getDokterList();
    DokterModel getDokterByIdDokter(Long idDokter);
    DokterModel findDokterByNikDokter(String nikDokter);
    // DokterModel getDokterDetailByNip(String nipDokter);
    // String generateNip(Date tanggalLahir, String jenisKelamin);
    DokterModel findDokterByIdDokter(Long idDokter);
    DokterModel updateDokter(DokterModel dokterModel);
    void deleteDokter(Long idDokter);
    DokterModel findDokterByNipDokter(String nipDokter);
    DokterModel getDokterByNikDokter(String nikDokter);
}