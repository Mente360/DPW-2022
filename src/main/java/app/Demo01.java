package app;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import model.Usuario;

public class Demo01 {
	
	public static void main(String[] args) {
		// -- Datos del nuevo usuario
		Usuario u = new Usuario();
		u.setCodigo(10);
		u.setNombre("Juan");
		u.setApellido("Pérez");
		u.setUsuario("jperez@mail.com");
		u.setClave("12345");
		u.setFchnacim("2000/01/05");
		u.setTipo(1);
		u.setEstado(1);
		
		// grabar o registrar el usuario en la BD --> usando la unidad de persistencia
		EntityManagerFactory fabrica = Persistence.createEntityManagerFactory("mysql");
		EntityManager em = fabrica.createEntityManager();
		
		// proceso
		em.getTransaction().begin();
		
		// insert .....
		em.persist(u); // registrar
		
		em.getTransaction().commit();
		
		em.close();
	}
	
}
