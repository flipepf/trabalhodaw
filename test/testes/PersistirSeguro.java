package testes;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import javax.persistence.EntityManager;
import jpa.EntityManagerUtil;
import modelo.Carro;
import modelo.Cobertura;
import modelo.Pessoa;
import modelo.Seguro;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class PersistirSeguro {
    EntityManager em;
    public PersistirSeguro() { }
    
    @Before
    public void setUp() { em = EntityManagerUtil.getEntityManager(); }
    
    @After
    public void tearDown() { em.close(); }

    @Test
    public void salvar(){
        try {
            Seguro s = new Seguro();
            s.setCorretor(em.find(Pessoa.class, 2));
            s.setCarro(em.find(Carro.class,1));
            
            SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
            Date dt = sdf.parse("14/04/2019");
            Calendar inicio = Calendar.getInstance();
            inicio.setTime(dt);
            s.setInicioVigencia(inicio);
            s.geraFimVigencia();
            
            s.setValorTotal(20000.00);
            s.setValorFipe(25000.00);
            
            Cobertura c = new Cobertura();
            c.setDescricao("Terceiros");
            c.setValor(15000.00);
            s.adicionarCobertura(c);
            
            em.getTransaction().begin();
            em.persist(s);
            em.persist(c);
            em.getTransaction().commit();
        } catch (Exception e) {
            System.out.println("Erro: " +e);
            e.printStackTrace();
        }
    }
}