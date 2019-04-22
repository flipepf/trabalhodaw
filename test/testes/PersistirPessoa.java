package testes;

import javax.persistence.EntityManager;
import jpa.EntityManagerUtil;
import modelo.Pessoa;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class PersistirPessoa {
    EntityManager em;
    public PersistirPessoa() { }
    
    @Before
    public void setUp() { em = EntityManagerUtil.getEntityManager(); }
    
    @After
    public void tearDown() { em.close(); }

    @Test
    public void salvar(){
        try {
            Pessoa p = new Pessoa();
            p.setNome("JAVE");
            p.setCpf("001.647.440-61");
            p.setRg("3062945997");
            p.setTelefone("999673319");
            p.setEmail("fulano@email.com");
            em.getTransaction().begin();
            em.persist(p);
            em.getTransaction().commit();
        } catch (Exception e) {
            System.out.println("Erro: " +e);
            e.printStackTrace();
        }
    }
}
