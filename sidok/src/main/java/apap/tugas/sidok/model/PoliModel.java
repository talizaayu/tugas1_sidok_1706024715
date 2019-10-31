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
@Table(name="poli")
public class PoliModel implements Serializable {


	public Long getIdPoli() {
		return this.idPoli;
	}

	public void setIdPoli(Long idPoli) {
		this.idPoli = idPoli;
	}

	public String getNamaPoli() {
		return this.namaPoli;
    }
    
    public void setNamaPoli(String namaPoli) {
		this.namaPoli = namaPoli;
	}

	public void setLokasiPoli(String lokasiPoli) {
		this.lokasiPoli = lokasiPoli;
    }
    
    public String getLokasiPoli() {
		return this.lokasiPoli;
	}
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idPoli;

    @NotNull
    @Size(max=255)
    @Column(name="namaPoli", nullable = false)
    private String namaPoli;

    @NotNull
    @Size(max=255)
    @Column(name="lokasiPoli", nullable = false)
    private String lokasiPoli;

    @OneToMany(mappedBy = "poli", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private Set<JadwalJagaModel> jadwalJagaModel;

}

