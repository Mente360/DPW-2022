package app;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;

import model.Usuario;

public class Demo07 {

	public static void main(String[] args) {
		// Obj >>> Listado de los usuarios por tipo

		EntityManagerFactory fabrica = Persistence.createEntityManagerFactory("mysql");
		EntityManager em = fabrica.createEntityManager();

		// select * from tb_xxxxxx where .....
		TypedQuery<Usuario> consulta = 
			em.createQuery("select e from Usuario e where tipo = :qtipo", Usuario.class);
		consulta.setParameter("qtipo", 1);
		List<Usuario> lstUsuarios = consulta.getResultList();

		// realiza la lista del query
		for (Usuario u : lstUsuarios) {
			System.out.println(u);
		}
		em.close();
	}
}
