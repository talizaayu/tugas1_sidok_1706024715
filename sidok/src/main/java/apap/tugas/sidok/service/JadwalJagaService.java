package apap.tugas.sidok.service;

import java.util.List;

import apap.tugas.sidok.model.JadwalJagaModel;
import apap.tugas.sidok.model.DokterModel;
import apap.tugas.sidok.model.PoliModel;

public interface JadwalJagaService {
    JadwalJagaModel addJadwalJaga(JadwalJagaModel jadwalJaga);
    List<JadwalJagaModel> getJadwalJagaByDokter(DokterModel dokter);
	List<JadwalJagaModel> getJadwalJagaByPoli(PoliModel poli);
}