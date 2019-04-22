package testes;

import javax.persistence.EntityManager;
import jpa.EntityManagerUtil;
import modelo.Acessorio;
import modelo.Carro;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class PersistirAcessorio {
    EntityManager em;
    public PersistirAcessorio() { }
    
    @Before
    public void setUp() { em = EntityManagerUtil.getEntityManager(); }
    
    @After
    public void tearDown() { em.close(); }

    @Test
    public void salvar(){
        try {
            Acessorio a = new Acessorio();
            a.setDescricao("VIDRO ELÉTRICO");
            
            Carro c = em.find(Carro.class, 4);
            a.adicionarCarro(c);
            
            em.getTransaction().begin();
            em.persist(a);
            em.getTransaction().commit();
        } catch (Exception e) {
            System.out.println("Erro: " +e);
            e.printStackTrace();
        }
    }
}