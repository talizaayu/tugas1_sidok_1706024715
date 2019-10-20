package apap.tugas.sidok.service;

import java.util.List;
import java.util.Date;
import java.text.DateFormat;
import java.text.SimpleDateFormat;

import javax.transaction.Transactional;
import apap.tugas.sidok.model.PoliModel;
import apap.tugas.sidok.repository.PoliDb;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Transactional

public class PoliServiceImpl implements PoliService {
    @Autowired
    private PoliDb poliDb;


    @Override
    public List<PoliModel> getPoliList() {
        return poliDb.findAll();
    }

    @Override
	public PoliModel findPoliByIdPoli(Long idPoli) {
		return poliDb.findByIdPoli(idPoli);
	}

}