package apap.tugas.sidok.service;

import java.util.List;

import javax.transaction.Transactional;
import java.util.Optional;
import apap.tugas.sidok.model.SpesialisasiModel;
import apap.tugas.sidok.repository.SpesialisasiDb;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Transactional

public class SpesialisasiServiceImpl implements SpesialisasiService {
    @Autowired
    private SpesialisasiDb spesialisasiDb;

    @Override
    public List<SpesialisasiModel> getSpesialisasiList() {
        return spesialisasiDb.findAll();
    }

    @Override
    public Optional<SpesialisasiModel> getSpesialisasiByIdSpesialisasi(Long idSpesialisasi) {
        return spesialisasiDb.findByIdSpesialisasi(idSpesialisasi);
    }
}