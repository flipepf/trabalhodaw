package modelo;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.Table;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotBlank;

@Entity
@Inheritance(strategy = InheritanceType.JOINED)
@Table(name = "corretor")
public class Corretor extends Pessoa implements Serializable{
    
    @Min(0)
    @Column(name = "percentual_comissao", nullable = false)
    private double percentualComissao;
    
    @Length(max = 20, message = "O NOME DE USUÁRIO NÃO PODE TER MAIS QUE {max} CARACTERES")
    @NotNull(message = "O NOME DE USUÁRIO NÃO PODE SER NULO")
    @NotBlank(message = "O NOME DE USUÁRIO DEVE SER INFORMADO")
    @Column(name = "nome_usuario", nullable = false, length = 20)
    private String nomeUsuario;
    
    @Length (max=8, message = "A SENHA NÃO PODE TER MAIS QUE {max} CARACTERES")
    @NotNull(message = "A SENHA NÃO PODE SER NULA")
    @NotBlank(message = "A SENHA DEVE SER INFORMADA")
    @Column(name = "senha", nullable = false, length = 8)
    private String senha;
    //##########################################################################################

    public Corretor() { }

    public double getPercentualComissao() {
        return percentualComissao;
    }

    public void setPercentualComissao(double percentualComissao) {
        this.percentualComissao = percentualComissao;
    }

    public String getNomeUsuario() {
        return nomeUsuario;
    }

    public void setNomeUsuario(String nomeUsuario) {
        this.nomeUsuario = nomeUsuario;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }
}
