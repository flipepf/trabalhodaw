package modelo;

import java.io.Serializable;
import java.util.Calendar;
import java.util.Objects;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import org.hibernate.annotations.ForeignKey;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotBlank;

@Entity
@Table(name = "sinistro")
public class Sinistro implements Serializable{
    @Id
    @SequenceGenerator(name = "seq_sinistro", sequenceName = "seq_sinistro_id", allocationSize = 1)
    @GeneratedValue(generator = "seq_sinistro", strategy = GenerationType.SEQUENCE)
    private Integer id;
    
    @NotNull (message = "A DESCRIÇÃO NÃO PODE SER NULA")
    @NotBlank(message = "A DESCRIÇÃO SER INFORMADA")
    @Column(name="descricao", nullable = false, columnDefinition = "text")
    private String descricao;
    
    @Temporal(TemporalType.DATE)
    @NotNull (message = "A DATA NÃO PODE SER NULA")
    @Column(name = "data", nullable = false)
    private Calendar data;
    
    @Length (max=20, message = "A CIDADE NÃO PODE TER MAIS QUE {max} CARACTERES")
    @NotNull(message = "A CIDADE NÃO PODE SER NULA")
    @NotBlank(message = "A CIDADE DEVE SER INFORMADA")
    @Column(name="cidade", nullable = false, length = 20)
    private String cidade;
    
    @Length (max=2, message = "O ESTADO NÃO PODE TER MAIS QUE {max} CARACTERES")
    @NotNull(message = "O ESTADO NÃO PODE SER NULO")
    @NotBlank(message = "O ESTADO DEVE SER INFORMADO")
    @Column(name="estado", nullable = false, length = 2)
    private String estado;
    
    @ManyToOne
    @JoinColumn(name = "seguro", referencedColumnName = "id" , nullable = false)
    @NotNull (message = "O SEGURO DEVE SER INFORMADO")
    @ForeignKey (name ="fk_seguro")
    private Seguro seguro;
//#############################################################################

    public Sinistro() { }

    public Integer getId() { return id; }

    public void setId(Integer id) { this.id = id; }

    public String getDescricao() { return descricao; }

    public void setDescricao(String descricao) { this.descricao = descricao; }

    public Calendar getData() { return data; }

    public void setData(Calendar data) { this.data = data; }

    public String getCidade() { return cidade; }

    public void setCidade(String cidade) { this.cidade = cidade; }

    public String getEstado() { return estado; }

    public void setEstado(String estado) { this.estado = estado; }

    public Seguro getSeguro() { return seguro; }

    public void setSeguro(Seguro seguro) { this.seguro = seguro; }
//###################################################################################
    @Override
    public int hashCode() {
        int hash = 3;
        hash = 37 * hash + Objects.hashCode(this.id);
        return hash;
    }
    @Override
    public boolean equals(Object obj) {
        if (this == obj) { return true; }
        if (obj == null) { return false; }
        if (getClass() != obj.getClass()) { return false; }
        final Sinistro other = (Sinistro) obj;
        if (!Objects.equals(this.id, other.id)) { return false; }
        return true;
    }
}