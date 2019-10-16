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
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.format.annotation.DateTimeFormat.ISO;

@Entity
@Table(name="dokter")
public class DokterModel implements Serializable {


	public Long getIdDokter() {
		return this.idDokter;
	}

	public void setIdDokter(Long idDokter) {
		this.idDokter = idDokter;
	}

	public String getNamaDokter() {
		return this.namaDokter;
	}

	public void setNamaDokter(String namaDokter) {
		this.namaDokter = namaDokter;
	}

	public String getNipDokter() {
		return this.nipDokter;
	}

	public void setNipDokter(String nipDokter) {
		this.nipDokter = nipDokter;
	}

	public String getNikDokter() {
		return this.nikDokter;
	}

	public void setNikDokter(String nikDokter) {
		this.nikDokter = nikDokter;
	}

	public Date getTanggalLahir() {
		return this.tanggalLahir;
	}

	public void setTanggalLahir(Date tanggalLahir) {
		this.tanggalLahir = tanggalLahir;
	}

	public String getTempatLahir() {
		return this.tempatLahir;
	}

	public void setTempatLahir(String tempatLahir) {
		this.tempatLahir = tempatLahir;
	}

	public Integer getJenisKelamin() {
		return this.jenisKelamin;
	}

	public void setJenisKelamin(Integer jenisKelamin) {
		this.jenisKelamin = jenisKelamin;
	}
	
	public Set<JadwalJagaModel> getJadwalJagaModel() {
		return this.jadwalJagaModel;
	}

	public void setJadwalJagaModel(Set<JadwalJagaModel> jadwalJagaModel) {
		this.jadwalJagaModel = jadwalJagaModel;
	}

	public List<SpesialisasiModel> getListSpesialisasi() {
		return this.listSpesialisasi;
	}

	public void setListSpesialisasi(List<SpesialisasiModel> listSpesialisasi) {
		this.listSpesialisasi = listSpesialisasi;
	}
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idDokter;

    @NotNull
    @Size(max=255)
    @Column(name="namaDokter", nullable = false)
    private String namaDokter;


    @NotNull
    @Size(max=255)
    @Column(name="nipDokter", nullable = false)
    private String nipDokter;

    @NotNull
    @Size(max=255)
    @Column(name="nikDokter", nullable = false)
    private String nikDokter;

    @DateTimeFormat(iso = ISO.DATE)
    @Column(name="tanggalLahir", nullable = false)
    private Date tanggalLahir;

    @NotNull
    @Size(max=255)
    @Column(name="tempatLahir", nullable = false)
    private String tempatLahir;

    @NotNull
    @Column(name = "jenisKelamin", nullable = false)
    private Integer jenisKelamin;

    @OneToMany(mappedBy = "dokter", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private Set<JadwalJagaModel> jadwalJagaModel;

    @ManyToMany
    @JoinTable(name="spesialisasiDokter", joinColumns = @JoinColumn(name = "idDokter"), inverseJoinColumns = @JoinColumn(name = "idSpesialisasi"))
    private List<SpesialisasiModel> listSpesialisasi;


}

