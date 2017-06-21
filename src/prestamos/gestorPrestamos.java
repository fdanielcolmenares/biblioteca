package prestamos;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Calendar;
import javax.swing.DefaultListModel;
import com.toedter.calendar.JDateChooser;
import Utilitario.Autenticacion;
import Utilitario.MostrarMensajes;

import ConexionBD.Conexion;


public class gestorPrestamos {
	private panelPrestamos panel;
	int busca=0,id_usuario_dev=0;
	private String nom_libro="",fecha_pres="",fecha_entre="";
	private int id_ejem=0,id_usu=0,id_lib=0,num_ejem=0;
	Autenticacion autenticacion;
	public Conexion conex;
	DefaultListModel listas = new DefaultListModel();  
	
	public gestorPrestamos(panelPrestamos panel,Autenticacion autenticacion){
		this.panel=panel;
		this.autenticacion = autenticacion;
		conex = new Conexion(this.autenticacion);
		conex.conectar();
	}

	public void boton_buscar_usu(String ced,int op) throws SQLException{
		panel.imagen.setVisible(true);
		boolean correcto;
	    if(ced!=null){
			StringBuilder query = new StringBuilder();
			query.append("SELECT nombres,id_usuario ")
			.append("FROM usuarios ")
			.append("WHERE cedula= ")
			.append(ced);
		
			ResultSet res = conex.consultar(query.toString());
			if(!res.next()){
				MostrarMensajes.mostrarMensaje("Usuario no registrado", MostrarMensajes.MENSAJE_ERROR);
				panel.cedula.setText(" ");
				panel.cedula1.setText(" ");
			}	
			else{	
			  if(op==1){	
				id_usu=Integer.parseInt(res.getObject("id_usuario").toString());
				panel.panelusuario.setVisible(false);
				panel.nom_usuario.setVisible(true);
				panel.imagen.setVisible(false);
				panel.libros.setVisible(true);
				panel.panel1.setVisible(true);
				panel.nombre_usu.setText(res.getObject("nombres").toString());
				//System.out.println("nombre="+res.getObject("nombres"));
			  }
			  if(op==2){
				  panel.usu_devuelve.setVisible(false);
				  panel.devolver_libro.setVisible(true);
				  devolver_libro(Integer.parseInt(res.getObject("id_usuario").toString()));
			  }
			}				
	    }		
	}
	//---------------------busca libro---------------
	public void bton_buscar_libro(String cota) throws SQLException{
		String vec_li[]=cota.split("-");
		int num=0;
		panel.jLabel5.setVisible(true);
		panel.jLabel6.setVisible(true);
		if(busca==0)
			panel.libros.setVisible(true);
		panel.imagen.setVisible(false);
		busca=1;
		if(cota==null){
			System.out.println("no Exite ese libro");			
		}
		else{
			panel.nom_usuario.setVisible(true);
			//---------------consulta
	
			StringBuilder query = new StringBuilder();
				query.append("SELECT id_libro,titulo ")
				.append("FROM libros ")
				.append("WHERE id_libro=( ")
					.append("SELECT id_libro ")
					.append("FROM libros ")
					.append("WHERE cota= ")
					.append(vec_li[0])
					.append(") ");
			ResultSet res = conex.consultar(query.toString());	
			if(!res.next()){		
				System.out.println("no encontro cota");	
				panel.jLabel6.setText("No existe cota !");
			}	
			else{
				num=Integer.parseInt(vec_li[1]);
				StringBuilder query2 = new StringBuilder();
				query2.append("SELECT id_ejemplar,numero,estado ")
				.append("FROM ejemplares ")
				.append("WHERE id_libro= ")	
				.append(res.getObject("id_libro"))
				.append(" AND numero= ")
				.append(num);
			
				ResultSet resultado = conex.consultar(query2.toString());
				if(!resultado.next())
					panel.jLabel6.setText("No existe libro");
				else{
				 
					nom_libro=res.getObject("titulo").toString();
					id_ejem=Integer.parseInt(resultado.getObject("id_ejemplar").toString());
					id_lib=Integer.parseInt(res.getObject("id_libro").toString());
					num_ejem=Integer.parseInt(resultado.getObject("numero").toString());
					System.out.println(id_usu+nom_libro+id_ejem+num_ejem);	
					panel.jLabel6.setText(resultado.getObject("estado").toString());
						if(resultado.getObject("estado").toString().equals("existe")){
							panel.Agregar.setVisible(true);	
							panel.jButton1.setVisible(false);
						}
						else
							panel.Agregar.setVisible(false);	
						
					}
			
			}
		
		}
	}
	
	//-----------------agrega libro a la BDD---------------
	public void bton_agregar_libro(){ 
		boolean resultado = false;
		panel.libros.setVisible(false);
		panel.listaprestamos.setVisible(true);
		panel.listlibros.append("Titulo: "+nom_libro+" Ejemplar "+num_ejem+"\n");
		panel.consu_san.setText(" ");
		panel.jLabel6.setText(" "); 
		panel.Agregar.setVisible(false);	
		panel.jButton1.setVisible(true);

		
		//--------------------insert------------------------------
		int anop=0,mesp=0,diap=0,anoe=0,mese=0,diae=0;
		 diap=panel.getTxtFechaprestamo().getCalendar().get(Calendar.DAY_OF_MONTH);
		 mesp=1+panel.getTxtFechaprestamo().getCalendar().get(Calendar.MONTH);
		 anop=panel.getTxtFechaprestamo().getCalendar().get(Calendar.YEAR);
		 String fechap="";
		 fechap=anop+"-"+mesp+"-"+diap;
		 diae=panel.getTxtFechaentrega().getCalendar().get(Calendar.DAY_OF_MONTH);
		 mese=1+panel.getTxtFechaentrega().getCalendar().get(Calendar.MONTH);
		 anoe=panel.getTxtFechaentrega().getCalendar().get(Calendar.YEAR);
		 String fechae="";
		 fechae=anoe+"-"+mese+"-"+diae;
		System.out.print(anop+" -  "+anoe);
		StringBuilder query_insert = new StringBuilder();
		query_insert.append("INSERT INTO prestamos (fecha_prestamo,fecha_vencimiento,id_ejemplar,id_libro,id_usuario) ")
			 .append("VALUES(")
			 .append("' ").append(fechap)
			 .append("', '").append(fechae)
			 .append("', ").append(id_ejem)
			 .append(", ").append(id_lib)
			 .append(", ").append(id_usu)
			 .append(")");
		
		resultado = conex.actualizar(query_insert.toString());		
		if(resultado)
			System.out.println("se agrego correcto");	
		else 
			System.out.println("No se agrego ****");
		
		StringBuilder query_estado = new StringBuilder();
		query_estado.append("UPDATE ejemplares SET ")
			.append("estado='prestado' " )
			.append("WHERE id_ejemplar= ")
			.append(id_ejem).append(" ")
			.append("AND id_libro= ")
			.append(id_lib);
		resultado = conex.actualizar(query_estado.toString());		
		if(resultado)
			System.out.println("se actualizo el estado");	
		else 
			System.out.println("No se actualizo el estado");
		
	}

	//--------------------------------------------------------

	public void inicio(){
		panel.panelusuario.setVisible(true);
		panel.panel1.setVisible(false);
		panel.imagen.setVisible(true);
		panel.listaprestamos.setVisible(false);		
		panel.cedula.setText("");
	}

	//******************LISTA LIBROS DEVUELTOS
	public void devolver_libro(int id_usu) throws SQLException{
		StringBuilder query = new StringBuilder();
		id_usuario_dev=id_usu;
		String vec_id_l="";
		String vec_id_e=" ";
		
		listas.clear();
		
   		int i=0,j=0;

			StringBuilder query_def = new StringBuilder();
			query_def.append("select l.cota, e.id_ejemplar, e.id_libro, l.titulo ")
			.append("from prestamos p, libros l, ejemplares e ")
			.append("where p.id_usuario= ")
			.append(id_usu)
			.append(" ")
			.append("and p.id_libro=l.id_libro ")
			.append(" AND ")
			.append("e.id_ejemplar=p.id_ejemplar")
			.append(" AND ")
			.append("p.fecha_entrega='0000-00-00'");
		
			ResultSet res2 = conex.consultar(query_def.toString());
			if(!res2.next())
				System.out.println("no entro 2");
		
			else{
				do{
					System.out.println("  "+res2.getObject("cota")+"-        -"+res2.getObject("id_ejemplar")+"-  -"+res2.getObject("id_libro")+"-  -"+res2.getObject("titulo"));
				listas.addElement("     "+res2.getObject("cota")+"-                -"+res2.getObject("id_ejemplar")+"-                 -"+res2.getObject("id_libro")+"-               -"+res2.getObject("titulo")+"\n");	
				}while(res2.next());
			}
			i++;
			j++;
		panel.jList.setModel(listas);
		if(listas.size()==0)
			MostrarMensajes.mostrarMensaje("No hay prestamos para este usuario!", MostrarMensajes.MENSAJE_ERROR);
	}	
//************   LIBROS CLICLEADOS PARA DEVOLVER....!!!!
	public void cambiar_estado_devuelto(int pos) throws SQLException{
		int ano=0,mes=0,dia=0,poss;
		
		 dia=panel.getTxtFechaentrega_usu().getCalendar().get(Calendar.DAY_OF_MONTH);
		 mes=1+panel.getTxtFechaentrega_usu().getCalendar().get(Calendar.MONTH);
		 ano=panel.getTxtFechaentrega_usu().getCalendar().get(Calendar.YEAR);
		 String fecha="";
		 fecha=ano+"-"+mes+"-"+dia;
		 String libro_selec[]=(listas.get(pos).toString()).split("-");

		 int idlibro=Integer.parseInt(libro_selec[4].trim());
		 int	idejem= Integer.parseInt(libro_selec[2].trim());//// aki tomar los id del libro de las lista selecionado******
		 boolean resultado = false;
		
	//--------ACTIALIZA ESTADO
		
		StringBuilder query_estado = new StringBuilder();
		query_estado.append("UPDATE ejemplares SET ")
			.append("estado='existe' " )
			.append("WHERE id_ejemplar= ")
			.append(idejem).append(" ")
			.append("AND id_libro= ")
			.append(idlibro);
		resultado = conex.actualizar(query_estado.toString());		
		if(resultado)
			System.out.println("se actualiso el estado");	
		else 
			System.out.println("No se actualiso el estado");
		
		//---FECHA ENTREGA
		StringBuilder query_fecha = new StringBuilder();
		query_fecha.append("UPDATE prestamos SET ")
		.append("fecha_entrega= ' ")
		.append(fecha )
		.append("' ")
		.append("WHERE id_ejemplar= ")
		.append(idejem)
		.append(" ")
		.append("AND id_libro= ")
		.append(idlibro)
		.append(" ")
		.append("AND id_usuario= ")
		.append(id_usuario_dev);
		
		
		resultado = conex.actualizar(query_fecha.toString());		
		if(resultado)
			System.out.println("se actualizo correcto");	
		else 
			System.out.println("No se actualizo ****");
		
		//***********busca para suspender!!!!!!!!!!!!!
		StringBuilder query_suspender = new StringBuilder();
		query_suspender.append("SELECT fecha_vencimiento,fecha_entrega ")
		.append("FROM prestamos ")
		.append("WHERE id_ejemplar= ")
		.append(idejem).append(" ")
		.append("AND id_libro= ")
		.append(idlibro)
		.append(" ")
		.append("AND id_usuario= ")
		.append(id_usuario_dev)
		.append(" AND fecha_entrega > ")
		.append("fecha_vencimiento ");
			
		ResultSet ressus = conex.consultar(query_suspender.toString());
		if(!ressus.next())
			System.out.println("no verifico sancion...");
		else{
				System.out.print("hay que sancionar!!!"+ressus.getObject("fecha_vencimiento"));
				sancionar(id_ejem,id_usu,id_lib,num_ejem);
		}
		if(listas.size()!=0)
			devolver_libro(id_usu);

	}
	//-------------------------------------------
	public void regresar_devolver(){
		panel.devolver_libro.setVisible(false);
		panel.usu_devuelve.setVisible(true);
		panel.cedula1.setText(" ");
	}
	//*****  funcion que llama ventana sancionar!!!!!!
	public void sancionar(int id_ejem,int id_usu,int id_lib,int num_ejem){
		panel.alerta.setVisible(true);
		System.out.print(id_ejem+"  "+id_usu+"  "+id_lib+"  "+num_ejem+"  ");
	}

}


