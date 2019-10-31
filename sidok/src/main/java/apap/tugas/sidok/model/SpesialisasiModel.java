package apap.tugas.sidok.model;

import java.io.Serializable;
import java.math.BigInteger;
import java.sql.Date;
import java.util.List;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Entity
@Table(name="spesialisasi")
public class SpesialisasiModel implements Serializable {

	public Long getIdSpesialisasi() {
		return this.idSpesialisasi;
	}

	public void setIdSpesialisasi(Long idSpesialisasi) {
		this.idSpesialisasi = idSpesialisasi;
	}

	public String getNamaSpesialisasi() {
		return this.namaSpesialisasi;
  }
    
  public void setNamaSpesialisasi(String namaSpesialisasi) {
		this.namaSpesialisasi = namaSpesialisasi;
  }

  public String getGelar() {
		return this.gelar;
  }
    
  public void setGelar(String gelar) {
		this.gelar = gelar;
  }

  public List<DokterModel> getListDokter() {
    return listDokter;
  }

  public void setListDokter(List<DokterModel> listDokter) {
    this.listDokter = listDokter;
  }
    
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idSpesialisasi;

    @NotNull
    @Size(max=255)
    @Column(name="namaSpesialisasi", nullable = false)
    private String namaSpesialisasi;

    @NotNull
    @Size(max=255)
    @Column(name="gelar", nullable = false)
    private String gelar;

    @ManyToMany(mappedBy = "listSpesialisasi")
    private List<DokterModel> listDokter;

}

