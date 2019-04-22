package modelo;

import java.io.Serializable;
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
import javax.validation.constraints.NotNull;
import org.hibernate.annotations.ForeignKey;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotBlank;

@Entity
@Table(name = "cobertura")
public class Cobertura implements Serializable{
    
    @Id
    @SequenceGenerator(name = "seq_cobertura", sequenceName = "seq_cobertura_id", allocationSize = 1)
    @GeneratedValue(generator = "seq_cobertura", strategy = GenerationType.SEQUENCE)
    private Integer id;
    
    @Length (max=50, message = "A DESCRIÇÃO NÃO PODE TER MAIS QUE {max} CARACTERES")
    @NotNull (message = "A DESCRIÇÃO NÃO PODE SER NULA")
    @NotBlank(message = "A DESCRIÇÃO SER INFORMADA")
    @Column(name="descricao", nullable = false, length = 50)
    private String descricao;
    
    @NotNull(message = "O VALOR NÃO PODE SER NULO")
    @Column(name="valor", nullable = false, columnDefinition = "numeric(10,2)")
    private Double valor;
    
    @ManyToOne
    @JoinColumn(name = "seguro", referencedColumnName = "id" , nullable = false)
    @NotNull (message = "O SEGURO DEVE SER INFORMADO")
    @ForeignKey (name ="fk_seguro")
    private Seguro seguro;
    //##################################################################################################

    public Cobertura() { }

    public Integer getId() { return id; }

    public void setId(Integer id) { this.id = id; }

    public String getDescricao() { return descricao; }

    public void setDescricao(String descricao) { this.descricao = descricao; }

    public Double getValor() { return valor; }

    public void setValor(Double valor) { this.valor = valor; }

    public Seguro getSeguro() { return seguro; }

    public void setSeguro(Seguro seguro) { this.seguro = seguro; }
    //########################################################################
    @Override
    public int hashCode() {
        int hash = 3;
        hash = 61 * hash + Objects.hashCode(this.id);
        return hash;
    }
    @Override
    public boolean equals(Object obj) {
        if (this == obj) { return true; }
        if (obj == null) { return false; }
        if (getClass() != obj.getClass()) { return false; }
        final Cobertura other = (Cobertura) obj;
        if (!Objects.equals(this.id, other.id)) { return false; }
        return true;
    }
}