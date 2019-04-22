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
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import javax.validation.constraints.NotNull;
import org.hibernate.annotations.ForeignKey;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotBlank;

@Entity
@Table(name = "carro")
public class Carro implements Serializable{
    @Id
    @SequenceGenerator(name = "seq_carro", sequenceName = "seq_carro_id", allocationSize = 1)
    @GeneratedValue(generator = "seq_carro", strategy = GenerationType.SEQUENCE)
    private Integer id;
    
    @Length (max=7, message = "A PLACA NÃO PODE TER MAIS QUE {max} CARACTERES")
    @NotNull(message = "A PLACA NÃO PODE SER NULA")
    @NotBlank(message = "A PLACA DEVE SER INFORMADA")
    @Column(name="placa", nullable = false, length = 7)
    private String placa;
    
    @Length (max=11, message = "O RENAVAN NÃO PODE TER MAIS QUE {max} CARACTERES")
    @NotNull(message = "O RENAVAN NÃO PODE SEL NULO")
    @NotBlank(message = "O RENAVAN DEVE SER INFORMADO")
    @Column(name="renavam", nullable = true, length = 11)
    private String renavan;
    
    @Length (max=10, message = "O MODELO NÃO PODE TER MAIS QUE {max} CARACTERES")
    @NotNull(message = "O MODELO NÃO PODE SEL NULO")
    @NotBlank(message = "O MODELO DEVE SER INFORMADO")
    @Column(name="modelo", nullable = true, length = 10)
    private String modelo;
    
    @Length (max=10, message = "O FABRICANTE NÃO PODE TER MAIS QUE {max} CARACTERES")
    @NotNull(message = "O FBRICANTE NÃO PODE SEL NULO")
    @NotBlank(message = "O FABRICANTE DEVE SER INFORMADO")
    @Column(name="fabricante", nullable = true, length = 10)
    private String fabricante;
    
    @NotNull(message = "O ANO DE FABRICAÇÃO DEVE SER INFORMADO")
    @Column(name = "ano_fabricacao", nullable = false)
    private Integer anoFabricacao;
    
    @NotNull(message = "O ANO DO MODELO DEVE SER INFORMADO")
    @Column(name = "ano_modelo", nullable = false)
    private Integer anoModelo;
    
    @ManyToOne
    @JoinColumn(name = "pessoa", referencedColumnName = "id" , nullable = false)
    @ForeignKey(name = "fk_pessoa")
    private Pessoa pessoa;
    
    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name="carros_acessorios",
            joinColumns=
            @JoinColumn(name = "carro", referencedColumnName = "id",nullable = false),
            inverseJoinColumns = 
            @JoinColumn(name ="acessorio", referencedColumnName = "id",nullable = false),
            uniqueConstraints = {@UniqueConstraint(columnNames = {"carro","acessorio"})})
    private List <Acessorio> acessorios = new ArrayList<>(); 
    
    //#############################################################################################################
     public Carro() { }

    public Integer getId() { return id; }

    public void setId(Integer id) { this.id = id; }

    public String getPlaca() { return placa; }

    public void setPlaca(String placa) { this.placa = placa; }

    public String getRenavan() { return renavan; }

    public void setRenavan(String renavan) { this.renavan = renavan; }

    public String getModelo() { return modelo; }

    public void setModelo(String modelo) { this.modelo = modelo; }

    public String getFabricante() { return fabricante; }

    public void setFabricante(String fabricante) { this.fabricante = fabricante; }

    public Integer getAnoFabricacao() { return anoFabricacao; }

    public void setAnoFabricacao(Integer anoFabricacao) { this.anoFabricacao = anoFabricacao; }

    public Integer getAnoModelo() { return anoModelo; }

    public void setAnoModelo(Integer anoModelo) { this.anoModelo = anoModelo; }

    public Pessoa getPessoa() { return pessoa; }

    public void setPessoa(Pessoa pessoa) { this.pessoa = pessoa; }
    //############################################
    public List <Acessorio> getAcessorios() { return acessorios; }

    public void setAcessorios(List <Acessorio> acessorios) { this.acessorios = acessorios; }
     
    public void adicionarAcessorio (Acessorio a){ 
        this.acessorios.add(a);
    } 
    
    public void removerAcessorio (Acessorio a) { 
        this.acessorios.remove(a);
    }
    //#########################################################################
    @Override
    public int hashCode() {
        int hash = 7;
        hash = 97 * hash + Objects.hashCode(this.id);
        return hash;
    }
    @Override
    public boolean equals(Object obj) {
        if (this == obj) { return true; }
        if (obj == null) { return false; }
        if (getClass() != obj.getClass()) { return false; }
        final Carro other = (Carro) obj;
        if (!Objects.equals(this.id, other.id)) { return false; }
        return true;
    }
    @Override
    public String toString() { return placa; }
}