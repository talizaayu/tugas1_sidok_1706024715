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
    public DokterModel getDokterByIdDokter(Long idDokter) {
        return dokterDb.findByIdDokter(idDokter);
    }

    @Override
    public DokterModel findDokterByNikDokter(String nikDokter) {
        return dokterDb.findByNikDokter(nikDokter);
    }

    @Override
	public DokterModel findDokterByIdDokter(Long id) {
		return dokterDb.findByIdDokter(id);
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

    @Override
    public DokterModel updateDokter(DokterModel dokterModel) {
        DokterModel targetDokter = dokterDb.findById(dokterModel.getIdDokter()).get();

        try{
            targetDokter.setNamaDokter(dokterModel.getNamaDokter());
            targetDokter.setNikDokter(dokterModel.getNikDokter());
            targetDokter.setNipDokter(dokterModel.getNipDokter());
            targetDokter.setTanggalLahir(dokterModel.getTanggalLahir());
            targetDokter.setTempatLahir(dokterModel.getTempatLahir());
            targetDokter.setJenisKelamin(dokterModel.getJenisKelamin());
            dokterDb.save(targetDokter);
            return targetDokter;
        }
        catch (NullPointerException nullException) {
            return null;
        }
    }

    @Override
    public void deleteDokter(Long idDokter) {
        DokterModel deleteDok = dokterDb.findByIdDokter(idDokter);
		dokterDb.delete(deleteDok);
    }

    @Override
    public DokterModel findDokterByNipDokter(String nipDokter) {
        return dokterDb.findByNipDokter(nipDokter);
    }

    @Override
    public DokterModel getDokterByNikDokter(String nikDokter) {
        return dokterDb.findByNikDokter(nikDokter);
    }


}