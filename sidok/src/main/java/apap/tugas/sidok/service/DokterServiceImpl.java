package apap.tugas.sidok.service;

import java.util.List;
import java.util.Date;
import java.text.DateFormat;
import java.text.SimpleDateFormat;

import javax.transaction.Transactional;
import java.util.Optional;
import apap.tugas.sidok.model.DokterModel;
import apap.tugas.sidok.repository.DokterDb;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Transactional

public class DokterServiceImpl implements DokterService {
    @Autowired
    private DokterDb dokterDb;

    @Override
    public void addDokter(DokterModel dokter) {
        dokterDb.save(dokter);
    }

    @Override
    public List<DokterModel> getDokterList() {
        return dokterDb.findAllByOrderByNamaDokterAsc();
    }

    @Override
    public Optional<DokterModel> getDokterByIdDokter(Long idDokter) {
        return dokterDb.findByIdDokter(idDokter);
    }

    public DokterModel findDokterByNikDokter(String nikDokter) {
        return dokterDb.findByNikDokter(nikDokter);
    }

    // @Override
	// public DokterModel getDokterDetailByNip(String nipDokter) {
	// 	return dokterDb.findByNipDokter(nipDokter);
    // }
    
    // @Override
    // public String generateNip(Date tanggalLahir, String jenisKelamin) {
    //     String nipBaru = "";

    //     DateFormat df = new SimpleDateFormat("ddMMYY");
		
	// 	String formatted = df.format(tanggalLahir);
	// 	System.out.println("date :" + formatted);
    // }
}