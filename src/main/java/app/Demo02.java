package app;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import model.Usuario;

public class Demo02 {
	public static void main(String[] args) {
		// -- Datos del usuario a cambiar
		Usuario u = new Usuario();
		u.setCodigo(13);
		u.setNombre("Carla");
		u.setApellido("Toro");
		u.setUsuario("U013@gmail.com"); // Constraint UNIQUE
		u.setClave("10002");
		u.setFchnacim("2022-03-21");
		u.setTipo(2);
		u.setEstado(1);

		// grabar o registrar el usuario en la BD --> usando la unidad de persistencia
		EntityManagerFactory fabrica = Persistence.createEntityManagerFactory("mysql");
		EntityManager em = fabrica.createEntityManager();

		// proceso
		em.getTransaction().begin();

		// update .....
		Usuario ok = em.merge(u); // actualizar, si el ID existe / sino lo agrega

		em.getTransaction().commit();

		em.close();
	}
}
