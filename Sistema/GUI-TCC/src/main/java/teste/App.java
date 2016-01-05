package teste;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import static javax.persistence.Persistence.createEntityManagerFactory;

/**
 *
 * @author Fatinha de Sousa
 */
public class App {

    public static void main(String[] args) {
        listarUsuarios("sss");
    }

    public static void listarUsuarios(String login) {
        EntityManagerFactory factory = createEntityManagerFactory("Mobile-Edu-BD");
        EntityManager em = factory.createEntityManager();
        
        System.out.println("Testando Conexao: " +em.getProperties());
        
    }
}
