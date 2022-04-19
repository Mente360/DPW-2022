package app;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import model.Usuario;

public class Demo05 {
	
	public static void main(String[] args) {
		// Obj >> Versión mejorada de eliminar un Usuario
		
		EntityManagerFactory fabrica = Persistence.createEntityManagerFactory("mysql");
		EntityManager em = fabrica.createEntityManager();

		// proceso
		Usuario u = em.find(Usuario.class, 13);
		
		if (u == null)
			System.err.println("ID no existe");
		else {
			System.out.println(u);
			em.getTransaction().begin();
			em.remove(u); 
			em.getTransaction().commit();
		}

		em.close();
	}
}
