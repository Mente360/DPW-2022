package app;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;

import model.Usuario;

public class Demo06 {

	public static void main(String[] args) {
		// Obj >>> Listado de TODOS los usuarios
		
		EntityManagerFactory fabrica = Persistence.createEntityManagerFactory("mysql");
		EntityManager em = fabrica.createEntityManager();

		// select * from tb_xxxxxx                    select u.*
		TypedQuery<Usuario> consulta = em.createQuery("select e from Usuario e", Usuario.class);
		
		List<Usuario> lstUsuarios = consulta.getResultList();
		
		// realiza la lista del query
		for (Usuario u : lstUsuarios) {
			System.out.println(u);
		}
		em.close();
	}
}
