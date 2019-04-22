package testes;

import javax.persistence.EntityManager;
import jpa.EntityManagerUtil;
import modelo.Corretor;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class PersistirCorretor {
    EntityManager em;
    public PersistirCorretor() { }
    
    @Before
    public void setUp() { em = EntityManagerUtil.getEntityManager(); }
    
    @After
    public void tearDown() { em.close(); }

    @Test
    public void salvar(){
        try {
            Corretor c = new Corretor();
            c.setNome("CORRETOR");
            c.setCpf("001.647.440-61");
            c.setRg("3062945997");
            c.setTelefone("999673319");
            c.setEmail("fulano@email.com");
            c.setPercentualComissao(0.20);
            c.setNomeUsuario("c.souza");
            c.setSenha("12345");
            em.getTransaction().begin();
            em.persist(c);
            em.getTransaction().commit();
        } catch (Exception e) {
            System.out.println("Erro: " +e);
            e.printStackTrace();
        }
    }
}
