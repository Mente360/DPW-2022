package app;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import model.Categoria;
import model.Producto;
import model.Proveedor;
//import model.Producto;
import model.Usuario;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.util.List;
import java.awt.event.ActionEvent;
import javax.swing.JTextArea;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JComboBox;

public class FrmManteProd extends JFrame {

	private JPanel contentPane;

	private JTextArea txtSalida;
	private JTextField txtCódigo;
	private JComboBox cboCategorias;
	private JComboBox cboProveedor;
	private JTextField txtDescripcion;
	private JTextField txtStock;
	private JTextField txtPrecio;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					FrmManteProd frame = new FrmManteProd();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public FrmManteProd() {
		setTitle("Mantenimiento de Productos");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 461, 413);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JButton btnNewButton = new JButton("Registrar");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				registrar();
			}
		});
		btnNewButton.setBounds(324, 26, 100, 23);
		contentPane.add(btnNewButton);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 171, 414, 143);
		contentPane.add(scrollPane);

		txtSalida = new JTextArea();
		scrollPane.setViewportView(txtSalida);

		JButton btnListado = new JButton("Listado");
		btnListado.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				listado();
			}
		});
		btnListado.setBounds(172, 337, 89, 23);
		contentPane.add(btnListado);

		txtCódigo = new JTextField();
		txtCódigo.setBounds(122, 11, 86, 20);
		contentPane.add(txtCódigo);
		txtCódigo.setColumns(10);

		JLabel lblCodigo = new JLabel("Id. Producto :");
		lblCodigo.setBounds(10, 14, 102, 14);
		contentPane.add(lblCodigo);

		cboCategorias = new JComboBox();
		cboCategorias.setBounds(122, 70, 144, 22);
		contentPane.add(cboCategorias);

		JLabel lblCategora = new JLabel("Categor\u00EDa");
		lblCategora.setBounds(10, 74, 102, 14);
		contentPane.add(lblCategora);

		JLabel lblNomProducto = new JLabel("Nom. Producto :");
		lblNomProducto.setBounds(10, 45, 102, 14);
		contentPane.add(lblNomProducto);

		txtDescripcion = new JTextField();
		txtDescripcion.setColumns(10);
		txtDescripcion.setBounds(122, 42, 144, 20);
		contentPane.add(txtDescripcion);

		JLabel lblStock = new JLabel("Stock:");
		lblStock.setBounds(10, 106, 102, 14);
		contentPane.add(lblStock);

		txtStock = new JTextField();
		txtStock.setColumns(10);
		txtStock.setBounds(122, 103, 77, 20);
		contentPane.add(txtStock);

		JLabel lblPrecio = new JLabel("Precio:");
		lblPrecio.setBounds(10, 134, 102, 14);
		contentPane.add(lblPrecio);

		txtPrecio = new JTextField();
		txtPrecio.setColumns(10);
		txtPrecio.setBounds(122, 131, 77, 20);
		contentPane.add(txtPrecio);

		JLabel lblProveedor = new JLabel("Proveedor:");
		lblProveedor.setBounds(250, 134, 59, 14);
		contentPane.add(lblProveedor);

		cboProveedor = new JComboBox();
		cboProveedor.setBounds(324, 130, 100, 22);
		contentPane.add(cboProveedor);

		JButton btnBuscar = new JButton("Buscar");
		btnBuscar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				buscarProducto();
			}
		});
		btnBuscar.setBounds(324, 59, 100, 21);
		contentPane.add(btnBuscar);

		JButton btnActualizar = new JButton("Actualizar");
		btnActualizar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				actualizarProducto();
			}
		});
		btnActualizar.setBounds(324, 90, 100, 21);
		contentPane.add(btnActualizar);

		llenaCombo();
	}

	void actualizarProducto() {
		
		Producto p = new Producto();
		p.setId_prod(leerCodigoProducto());
		p.setDes_prod(leerDescripcion());
		p.setIdcategoria(leerCboCategoria());
		p.setEst_prod(leerEstado());
		p.setStk_prod(leerStrock());
		p.setPre_prod(leerPrecio());
		p.setIdprovedor(leerCboProveedor());
		
		EntityManagerFactory fabrica = Persistence.createEntityManagerFactory("mysql");
		EntityManager em = fabrica.createEntityManager();
		em.getTransaction().begin();
		Producto ok = em.merge(p);
		em.getTransaction().commit();
		em.close();
		JOptionPane.showMessageDialog(this, "Prodcuto actualizado");
		

	}

	void buscarProducto() {

		EntityManagerFactory fabrica = Persistence.createEntityManagerFactory("mysql");
		EntityManager em = fabrica.createEntityManager();

		// proceso --> búsquedas / consultas / listados

		// select .... from .... where ....
		Producto p = em.find(Producto.class, leerCodigoProducto());
		// devuelve un Objeto si existe el ID, sino devuelve null

		txtSalida.setText("");

		if (p == null)

			txtSalida.setText("Producto no existe");
		else {
			txtDescripcion.setText(p.getDes_prod());
			txtStock.setText("" + p.getStk_prod());
			txtPrecio.setText("" + p.getPre_prod());
			cboCategorias.setSelectedIndex(p.getIdcategoria());
			cboProveedor.setSelectedIndex(p.getIdprovedor());
		}

		em.close();

	}

	void llenaCombo() {
		EntityManagerFactory fabrica = Persistence.createEntityManagerFactory("mysql");
		EntityManager em = fabrica.createEntityManager();

		// llenar combo categoria

		// select * from tb_xxxxxx select u.*
		TypedQuery<Categoria> consulta = em.createQuery("select c from Categoria c", Categoria.class);

		List<Categoria> lstCategoria = consulta.getResultList();

		// dar un nombre al Item
		cboCategorias.addItem("Seleccione..");

		// llenando combobox
		for (Categoria c : lstCategoria) {
			cboCategorias.addItem(c.getDescripcion());

		}
		// llenar combo de proveedor
		TypedQuery<Proveedor> consultaP = em.createQuery("select p from Proveedor p", Proveedor.class);

		List<Proveedor> lstProveedor = consultaP.getResultList();

		// dar un nombre al Item
		cboProveedor.addItem("Seleccione..");

		// llenando combobox
		for (Proveedor p : lstProveedor) {
			cboProveedor.addItem(p.getNombre_rs());
		}
		em.close();

	}

	void listado() {
		EntityManagerFactory fabrica = Persistence.createEntityManagerFactory("mysql");
		EntityManager em = fabrica.createEntityManager();

		// listar productos

		// select * from tb_xxxxxx select u.*
		TypedQuery<Producto> consulta = em.createQuery("select d from Producto d", Producto.class);

		List<Producto> lstProductos = consulta.getResultList();

		// llenando lista
		txtSalida.setText("");
		for (Producto d : lstProductos) {
			txtSalida.append("Id producto : " + d.getId_prod() + "\n");
			txtSalida.append("Descripción : " + d.getDes_prod() + "\n");
			txtSalida.append("Stock : " + d.getStk_prod() + "\n");
			txtSalida.append("Precio : " + d.getPre_prod() + "\n");
			txtSalida.append("Id categoria : " + d.getIdcategoria() + "\n");
			txtSalida.append("Categoria : " + d.getCategoriaDescription().getDescripcion() + "\n");
			txtSalida.append("Estado : " + d.getEst_prod() + "\n");
			txtSalida.append("Id proveedor : " + d.getIdprovedor() + "\n");
			txtSalida.append("Proveedor : " + d.getProveedorDescription().getNombre_rs() + "\n");
			txtSalida.append("***************************************\n");

		}
		em.close();

	}

	void registrar() {

		Producto p = new Producto();
		p.setId_prod(leerCodigoProducto());
		p.setDes_prod(leerDescripcion());
		p.setStk_prod(leerStrock());
		p.setPre_prod(leerPrecio());
		p.setIdcategoria(leerCboCategoria());
		p.setEst_prod(leerEstado());
		p.setIdprovedor(leerCboProveedor());

		EntityManagerFactory fabrica = Persistence.createEntityManagerFactory("mysql");
		EntityManager em = fabrica.createEntityManager();

		// limpiar
		txtSalida.setText("");
		// proceso
		em.getTransaction().begin();

		// insert .....
		em.persist(p); // registrar

		em.getTransaction().commit();

		em.close();
		JOptionPane.showMessageDialog(this, "Producto agregado");

	}

	private String leerCodigoProducto() {
		return txtCódigo.getText().trim();
	}

	private String leerDescripcion() {
		return txtDescripcion.getText().trim();
	}

	private int leerStrock() {
		return Integer.parseInt(txtStock.getText().trim());
	}

	private double leerPrecio() {
		return Double.parseDouble(txtPrecio.getText().trim());
	}

	private int leerCboCategoria() {
		return cboCategorias.getSelectedIndex();
	}

	private int leerEstado() {
		return 1;
	}

	private int leerCboProveedor() {
		return cboProveedor.getSelectedIndex();
	}
}
