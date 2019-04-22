package modelo;

import java.io.Serializable;
import java.util.Objects;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotBlank;
import org.hibernate.validator.constraints.br.CPF;

@Entity
@Table(name = "pessoa")
@Inheritance(strategy = InheritanceType.JOINED)//PERMITE QUE A CLASSE SEJA HERDADA
public class Pessoa implements Serializable{
    
    @Id
    @SequenceGenerator(name = "seq_pessoa", sequenceName = "seq_pessoa_id", allocationSize = 1)
    @GeneratedValue(generator = "seq_pessoa", strategy = GenerationType.SEQUENCE)
    private Integer id;
    
    @Length (max=50, message = "O NOME NÃO PODE TER MAIS QUE {max} CARACTERES")
    @NotNull(message = "O NOME NÃO PODE SER NULO")
    @NotBlank(message = "O NOME DEVE SER INFORMADO")
    @Column(name="nome", nullable = false, length = 50)
    private String nome;
    
    @CPF(message = "CPF INVÁLIDO!")
    @NotNull(message = "O CPF NÃO PODE SER NULO")
    @NotBlank(message = "O CPF DEVE SER INFORMADO")
    @Column(name = "cpf", nullable = false, length = 14)
    private String cpf;
    
    @Length (max=10, message = "O RG NÃO PODE TER MAIS QUE {max} CARACTERES")
    @NotNull(message = "O RG NÃO PODE SER NULO")
    @NotBlank(message = "O RG DEVE SER INFORMADO")
    @Column(name = "rg", nullable = false, length = 10)
    private String rg;
    
    @Length (max=100, message = "O EMAIL NÃO PODE TER MAIS QUE {max} CARACTERES")
    @Column(name="email", nullable = true, length = 50)
    private String email;
    
    @Length (max=11, message = "O TELEFONE NÃO PODE TER MAIS QUE {max} CARACTERES")
    @NotBlank(message = "O ENDEREÇO DEVE SER INFORMADO")
    @Column(name="telefone", nullable = false, length = 11)
    private String telefone;
    //############################################################################

    public Pessoa() { }

    public Integer getId() { return id; }

    public void setId(Integer id) { this.id = id; }

    public String getNome() { return nome; }

    public void setNome(String nome) { this.nome = nome; }

    public String getCpf() { return cpf; }

    public void setCpf(String cpf) { this.cpf = cpf; }

    public String getRg() { return rg; }

    public void setRg(String rg) { this.rg = rg; }

    public String getEmail() { return email; }
 
    public void setEmail(String email) { this.email = email; }

    public String getTelefone() { return telefone; }

    public void setTelefone(String telefone) { this.telefone = telefone; }
    //##########################################################################
    @Override
    public int hashCode() {
        int hash = 5;
        hash = 73 * hash + Objects.hashCode(this.id);
        return hash;
    }
    @Override
    public boolean equals(Object obj) {
        if (this == obj) { return true; }
        if (obj == null) { return false; }
        if (getClass() != obj.getClass()) { return false; }
        final Pessoa other = (Pessoa) obj;
        if (!Objects.equals(this.id, other.id)) { return false; }
        return true;
    }
    @Override
    public String toString() {
        return nome;
    }
}
