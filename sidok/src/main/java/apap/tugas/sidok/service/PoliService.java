package apap.tugas.sidok.service;

import java.util.List;

import apap.tugas.sidok.model.PoliModel;

public interface PoliService {
    List<PoliModel> getPoliList();
    PoliModel findPoliByIdPoli(Long idPoli);
}