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
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Entity
@Table(name="jadwalJaga")
public class JadwalJagaModel implements Serializable {

	public Long getIdJadwalJaga() {
		return this.idJadwalJaga;
	}

	public void setIdJadwalJaga(Long idJadwalJaga) {
		this.idJadwalJaga = idJadwalJaga;
	}

	public String getHari() {
		return this.hari;
    }
    
    public void setHari(String hari) {
		this.hari = hari;
    }
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idJadwalJaga;

    @NotNull
    @Size(max=255)
    @Column(name="hari", nullable = false)
    private String hari;

	@ManyToOne
    @JoinColumn(name = "idDokter")
    private DokterModel dokter;

    @ManyToOne
    @JoinColumn(name = "idPoli")
    private PoliModel poli;
}

