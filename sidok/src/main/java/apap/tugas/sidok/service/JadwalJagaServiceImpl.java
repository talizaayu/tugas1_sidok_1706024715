package apap.tugas.sidok.service;

import java.util.List;

import javax.transaction.Transactional;
import apap.tugas.sidok.model.JadwalJagaModel;
import apap.tugas.sidok.model.DokterModel;
import apap.tugas.sidok.model.PoliModel;
import apap.tugas.sidok.repository.JadwalJagaDb;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Transactional

public class JadwalJagaServiceImpl implements JadwalJagaService {
    @Autowired
    private JadwalJagaDb jadwalJagaDb;

    @Override
    public List<JadwalJagaModel> getJadwalJagaByDokter(DokterModel dokter) {
        return jadwalJagaDb.findJadwalJagaByDokter(dokter);
    }

    @Override
    public List<JadwalJagaModel> getJadwalJagaByPoli(PoliModel poli) {
        return jadwalJagaDb.findJadwalJagaByPoli(poli);
    }

    @Override
    public JadwalJagaModel addJadwalJaga(JadwalJagaModel jadwalJaga) {
        return jadwalJagaDb.save(jadwalJaga);
    }
}
