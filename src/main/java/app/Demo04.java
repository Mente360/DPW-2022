package app;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import model.Usuario;

public class Demo04 {

	public static void main(String[] args) {
		// Obj >>> búsquedas
		
		EntityManagerFactory fabrica = Persistence.createEntityManagerFactory("mysql");
		EntityManager em = fabrica.createEntityManager();

		// proceso --> búsquedas / consultas / listados
		
		// select .... from .... where ....
		Usuario u = em.find(Usuario.class, 13);
		// devuelve un Objeto si existe el ID, sino devuelve null
		
		if (u == null)
			System.err.println("ID no existe");
		else
			System.out.println(u);
		
		em.close();
	}
}
