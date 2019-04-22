package modelo;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import javax.validation.constraints.NotNull;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotBlank;

@Entity
@Table(name = "acessorio")
public class Acessorio implements Serializable {
    
    @Id
    @SequenceGenerator(name = "seq_acessorio", sequenceName = "seq_acessorio_id", allocationSize = 1)
    @GeneratedValue(generator = "seq_acessorio", strategy = GenerationType.SEQUENCE)
    private Integer id;
    
    @Length (max=30, message = "A DESCRICAO NÃO PODE TER MAIS QUE {max} CARACTERES")
    @NotNull(message = "A DESCRIÇÃO NÃO PODE SER NULA")
    @NotBlank(message = "A DESCRIÇÃO DEVE SER INFORMADA")
    @Column(name = "descricao", nullable = false, length = 30)
    private String descricao;
    
    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name="carros_acessorios",
            joinColumns=
            @JoinColumn(name = "acessorio", referencedColumnName = "id",nullable = false),
            inverseJoinColumns = 
            @JoinColumn(name ="carro", referencedColumnName = "id",nullable = false),
            uniqueConstraints = {@UniqueConstraint(columnNames = {"carro","acessorio"})})
    private List <Carro> carros = new ArrayList<>(); 
    //####################################################################################

    public Acessorio() { }

    public Integer getId() { return id; }

    public void setId(Integer id) { this.id = id; }

    public String getDescricao() { return descricao; }

    public void setDescricao(String descricao) { this.descricao = descricao; }

    public List <Carro> getCarros() { return carros; }
    //###########################################
    
    public void setCarros(List <Carro> carros) { this.carros = carros; }
    
    public void adicionarCarro (Carro c){ 
        this.carros.add(c);
    } 
    
    public void removerCarro (Carro c) { 
        this.carros.remove(c);
    }
    //##########################################################################
    @Override
    public int hashCode() {
        int hash = 3;
        hash = 97 * hash + Objects.hashCode(this.id);
        return hash;
    }
    @Override
    public boolean equals(Object obj) {
        if (this == obj) { return true; }
        if (obj == null) { return false; }
        if (getClass() != obj.getClass()) { return false; }
        final Acessorio other = (Acessorio) obj;
        if (!Objects.equals(this.id, other.id)) { return false; }
        return true;
    }
    @Override
    public String toString() {
        return descricao;
    }
}