package modelo;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Objects;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import org.hibernate.annotations.ForeignKey;

@Entity
@Table(name = "seguro")
public class Seguro implements Serializable{
    @Id
    @SequenceGenerator(name = "seq_seguro", sequenceName = "seq_seguro_id", allocationSize = 1)
    @GeneratedValue(generator = "seq_seguro", strategy = GenerationType.SEQUENCE)
    private Integer id;
    
    @Temporal(TemporalType.DATE)
    @Column(name = "data", nullable = false)
    private Calendar data;
    
    @Temporal(TemporalType.DATE)
    @Column(name = "inicio_vigencia", nullable = false)
    private Calendar inicioVigencia;
    
    @Temporal(TemporalType.DATE)
    @Column(name = "fim_vigencia", nullable = false)
    private Calendar fimVigencia;
    
    @NotNull (message = "O VALOR TOTAL NÃO PODE SER SER NULO")
    @Column(name="valor_total", nullable = false)
    private Double valorTotal;
    
    @NotNull (message = "O VALOR DA FIPE NÃO PODE SER SER NULO")
    @Column(name="valor_fipe", nullable = false)
    private Double valorFipe;
    
    @ManyToOne
    @JoinColumn(name = "pessoa", referencedColumnName = "id" , nullable = false)
    @ForeignKey(name = "fk_corretor")
    private Pessoa corretor;
     
    @ManyToOne
    @JoinColumn(name = "carro", referencedColumnName = "id" , nullable = false)
    @ForeignKey(name = "fk_carro")
    private Carro carro;
    
    @OneToMany(mappedBy = "seguro", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
    private List<Cobertura> coberturas = new ArrayList<>();
    
    @OneToMany(mappedBy = "seguro", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
    private List<Sinistro> sinistros = new ArrayList<>();
    
    //##########################################################################

    public Seguro() {
        this.data = Calendar.getInstance();
    }
    
     public Integer getId() { return id; }

    public void setId(Integer id) { this.id = id; }

    public Calendar getData() { return data; }

    //public void setData(Calendar data) { this.data = data; }

    public Calendar getInicioVigencia() { return inicioVigencia; }

    public void setInicioVigencia(Calendar inicioVigencia) { this.inicioVigencia = inicioVigencia; }
    
    public void geraFimVigencia(){
        Calendar fimV = (Calendar)this.inicioVigencia.clone();
        fimV.add(Calendar.YEAR, 1);
        this.setFimVigencia(fimV);
    }

    public Calendar getFimVigencia() { return fimVigencia; }

    public void setFimVigencia(Calendar fimVigencia) { this.fimVigencia = fimVigencia; }

    public Double getValorTotal() { return valorTotal; }

    public void setValorTotal(Double valorTotal) { this.valorTotal = valorTotal; }

    public Double getValorFipe() { return valorFipe; }

    public void setValorFipe(Double valorFipe) { this.valorFipe = valorFipe; }

    public Pessoa getCorretor() { return corretor; }

    public void setCorretor(Pessoa corretor) { this.corretor = corretor; }

    public Carro getCarro() { return carro; }

    public void setCarro(Carro carro) { this.carro = carro; }
    //###########################################################
    public List<Cobertura> getCoberturas() { return coberturas; }

    public void setCoberturas(List<Cobertura> coberturas) { this.coberturas = coberturas; }

    public void adicionarCobertura (Cobertura c){
        this.coberturas.add(c);
        c.setSeguro(this); 
    }
     
    public void removerCobertura (Cobertura c){ coberturas.remove(c); }
    
    public void removerCobertura (int index){ coberturas.remove(index); }
    //#########################################################################
    public List<Sinistro> getSinistros() { return sinistros; }

    public void setSinistros(List<Sinistro> sinistros) { this.sinistros = sinistros; }
    
    public void adicionarSinistros (Sinistro s){
        this.sinistros.add(s);
        s.setSeguro(this); 
    }
     
    public void removerSinistro (Sinistro s){ sinistros.remove(s); }
    
    public void removerSinistro (int index){ sinistros.remove(index); }

//#########################################################################
    @Override
    public int hashCode() {
        int hash = 5;
        hash = 59 * hash + Objects.hashCode(this.id);
        return hash;
    }
    @Override
    public boolean equals(Object obj) {
        if (this == obj) { return true; }
        if (obj == null) { return false; }
        if (getClass() != obj.getClass()) { return false; }
        final Seguro other = (Seguro) obj;
        if (!Objects.equals(this.id, other.id)) { return false; }
        return true;
    }
}