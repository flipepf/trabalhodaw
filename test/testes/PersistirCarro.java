package testes;

import javax.persistence.EntityManager;
import jpa.EntityManagerUtil;
import modelo.Acessorio;
import modelo.Carro;
import modelo.Pessoa;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class PersistirCarro {
    EntityManager em;
    public PersistirCarro() { }
    
    @Before
    public void setUp() { em = EntityManagerUtil.getEntityManager(); }
    
    @After
    public void tearDown() { em.close(); }

    @Test
    public void salvar(){
        try {
            Carro c = new Carro();
            c.setPlaca("AAA0001");
            c.setRenavan("00164744061");
            c.setModelo("CELTA");
            c.setFabricante("CHEVROLET");
            c.setAnoFabricacao(2009);
            c.setAnoModelo(2009);
            c.setPessoa(em.find(Pessoa.class, 1));
            
            Acessorio a = new Acessorio();
            a.setDescricao("AR CONDICIONADO");
            c.adicionarAcessorio(a);
            
            Acessorio a2 = em.find(Acessorio.class, 1);
            c.adicionarAcessorio(a2);
            
            em.getTransaction().begin();
            em.persist(a);
            em.persist(c);
            em.getTransaction().commit();
        } catch (Exception e) {
            System.out.println("Erro: " +e);
            e.printStackTrace();
        }
    }
}