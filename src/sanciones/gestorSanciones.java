package sanciones;

import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.DefaultListModel;

import org.apache.commons.lang.StringEscapeUtils;
import org.omg.PortableServer.ID_UNIQUENESS_POLICY_ID;

import Utilitario.Autenticacion;

import ConexionBD.Conexion;


public class gestorSanciones {
	private panelsanciones panel;
	Autenticacion autenticacion;
	public Conexion conex;
	boolean conexion;
	DefaultListModel id_usua = new DefaultListModel();  
	DefaultListModel id_sancion = new DefaultListModel();  
	DefaultListModel id_prestamo = new DefaultListModel();  
	DefaultListModel fecha_ven = new DefaultListModel();  
	DefaultListModel fecha_pres = new DefaultListModel();  
	DefaultListModel modelo = new DefaultListModel(); 
	DefaultListModel nombreyape = new DefaultListModel(); 
	public gestorSanciones(panelsanciones panel, Autenticacion autenticacion){
		this.autenticacion = autenticacion;
		conex = new Conexion(autenticacion);
		conex.conectar();
		this.panel=panel;
	}
	public void san_guardar(String cedula,int campo){
		 modelo.clear();
		boolean correcto;
		StringBuilder query = new StringBuilder();
		query.append("INSERT INTO sancion_prestamo (estado,fecha_final,id_sancion,id_prestamo,id_usuario) ")
			.append("VALUES(")
			.append("'s'")
			.append(", '2012-10-03'")
			.append(", 0")
			.append(", ").append(id_prestamo.get(campo))
			.append(", ").append(id_usua.get(campo))
			.append(")");
		/*	.append(" WHERE id_sancion=")
			.append(id_sancion.get(campo))
			.append(" and id_prestamo=")
			.append(id_prestamo.get(campo))
			.append(" and id_usuario=")
			.append((id_usua.get(campo)));*/
				
		   

		System.out.println(query);
		correcto = conex.actualizar(query.toString());
		if(correcto==false)
			System.out.println("no esntro");
		
		
		
	}
	@SuppressWarnings("deprecation")
	public void boton_ok(String cedula,int tipo) throws SQLException{
		 modelo.clear();
		 nombreyape.clear();
		panel.getSan_ok().setEnabled(false);
		boolean entro=false;
		//if(!("".equals(cedula))){
		if(cedula!=null){
			StringBuilder query = new StringBuilder();
		
			query.append("SELECT u.nombres,sp.id_usuario,sp.id_sancion,sp.id_prestamo, u.apellidos, sp.fecha_final,s.descripcion,s.dias_sancion,sp.estado,p.fecha_prestamo,p.fecha_vencimiento ")
			.append("FROM usuarios u,sanciones s, sancion_prestamo sp,prestamos p ")
			 .append("WHERE cedula=")
			 .append(cedula)
			  .append(" and u.id_usuario=sp.id_usuario and sp.id_prestamo=p.id_prestamo and sp.id_sancion=s.id_sancion");
			ResultSet res = conex.consultar(query.toString());
			
			if(!res.next()){
				if(tipo==0){
				panel.getSan_guar().setEnabled(false);
				panel.getSan_ok().setEnabled(false);
				panel.alerta.setVisible(true);
				panel.jLabel2.setText("cedula no encontrada");
				panel.jTabbedPane.enable(false);}
				if(tipo==1){
				nombreyape.clear();
				panel.getJButton1().setEnabled(false);
				panel.alerta1.setVisible(true);
				}
				if(tipo==2){
					panel.getSan_elimi().setEnabled(false);
					panel.jLabel22.setText("cedula no encontrada");
					panel.alerta2.setVisible(true);
					}
				 entro=true;
			}
				
			res.beforeFirst();
			while (res.next())
			{	
			nombreyape.addElement(res.getObject("nombres"));
			modelo.addElement(res.getObject("fecha_prestamo")+"                 "+res.getObject("fecha_vencimiento")+"                 "+res.getObject("estado"));
			id_usua.addElement(res.getObject("id_usuario"));	
		     id_sancion.addElement(res.getObject("id_sancion"));
		     id_prestamo.addElement(res.getObject("id_prestamo"));
			
		    if(tipo==0) 
			 panel.jLabel.setText(res.getObject("nombres").toString()+" "+res.getObject("apellidos").toString());	
		   	
				 
			}
			if(tipo==0){
			 panel.lista.setModel(modelo);
			 if(entro==false)
				 panel.getSan_guar().setEnabled(true);
			}
			if(tipo==1){
			panel.getSan_guar().setEnabled(false);	
			panel.lista1.setModel(modelo);	
			panel.jList.setModel(nombreyape);}
			if(tipo==2){
				panel.lista2.setModel(modelo);
				if(entro==false)
					panel.getSan_elimi().setEnabled(true);
			}
		
		}else{
			if(tipo==0){
				panel.getSan_guar().setEnabled(false);
				panel.getSan_ok().setEnabled(false);
				panel.alerta.setVisible(true);
				panel.jTabbedPane.enable(false);}
			if(tipo==1){
				panel.getJButton1().setEnabled(false);
				panel.alerta1.setVisible(true);
				}
			
		}
		
			 
			 
			 
		 
	
	}
	public void boton_buscar(String cedula) throws SQLException{
		 modelo.clear();
		 nombreyape.clear();
		System.out.println(cedula);
		
		if(cedula!=null){
		StringBuilder query = new StringBuilder();
		query.append("SELECT u.nombres, sp.fecha_final,s.descripcion,s.dias_sancion,sp.estado ")
		.append("FROM usuarios u,sanciones s, sancion_prestamo sp ")
		 .append("WHERE cedula=")
		 .append(cedula)
		  .append(" and u.id_usuario=sp.id_usuario and sp.id_sancion=s.id_sancion");
		ResultSet res = conex.consultar(query.toString());
		while (res.next())
		{
			
		   System.out.println("nombre="+res.getObject("nombres"));
		 
		  if(res.getObject("estado").toString().compareTo("s")==0)
			//  panel.jLabel6.setText("sancionado");
			  System.out.println("asa");
		  else
			  System.out.println("asa");
			//  panel.jLabel6.setText("libre");
		  
		
			  
		}
		//panel.lista2.setModel(modelo);
		}
		 
		
		
		
		
	}
	public void san_buscar(String cedula) throws SQLException{
		 modelo.clear();
		 nombreyape.clear();
		System.out.println(cedula);
		
		if(cedula!=null){
		StringBuilder query = new StringBuilder();
		query.append("SELECT u.nombres,sp.id_usuario,sp.id_sancion,sp.id_prestamo, u.apellidos, sp.fecha_final,s.descripcion,s.dias_sancion,sp.estado,p.fecha_prestamo,p.fecha_vencimiento ")
		.append("FROM usuarios u,sanciones s, sancion_prestamo sp,prestamos p ")
		.append("WHERE u.id_usuario=sp.id_usuario and sp.id_prestamo=p.id_prestamo and sp.id_sancion=s.id_sancion ");
		ResultSet res = conex.consultar(query.toString());
		while (res.next())
		{
			
		  modelo.addElement(res.getObject("fecha_prestamo")+"                 "+res.getObject("fecha_prestamo")+"                 "+res.getObject("estado"));
		  nombreyape.addElement(res.getObject("nombres"));
			  
		}
		panel.lista1.setModel(modelo);
		panel.jList.setModel(nombreyape);
		}
		 
		
		
		
		
	}
public void boton_buscar_modi(String cedula,int opc){

	 nombreyape.clear();
	 modelo.clear();
	boolean correcto=false;
	StringBuilder query = new StringBuilder();
	query.append("DELETE from sancion_prestamo")
	.append(" WHERE id_prestamo=")
	.append(id_prestamo.get(opc))
	.append(" and id_usuario=")
	.append((id_usua.get(opc)));
	
		correcto = conex.actualizar(query.toString());
		if(correcto==false)
			panel.jLabel22.setText("no se pudo eliminar");
		else{
			panel.jLabel22.setText("se elimino correctamente");
			panel.getAlerta2().setVisible(true);
		}
		System.out.println(query);
	}
public void san_aler(){
	panel.getSan_guar().setEnabled(true);
	panel.getSan_ok().setEnabled(true);
	panel.alerta.setVisible(false);
	panel.jTabbedPane.enable(true);;
	}
public void san_cance(int u){
	if(u==0){
	panel.getSan_ok().setEnabled(true);
	panel.san_guar.setEnabled(false);
	modelo.clear();
	panel.lista.setModel(modelo);
	panel.nombre.setText("");}
	else{
		panel.getSan_ok().setEnabled(true);
		modelo.clear();
		panel.lista.setModel(modelo);
		panel.nombre.setText("");	
		panel.jLabel2.setText("se ah guardado");
		panel.alerta.setVisible(true);
	}
	
	}
public void san_bus_agre(String cedula){
	id_prestamo.clear();
	id_usua.clear();
	 modelo.clear();
	 nombreyape.clear();
	boolean entro=false;

	if(cedula!=null){
		StringBuilder query = new StringBuilder();
	
		/*query.append("SELECT p.id_usuario,p.id_prestamo, u.nombres, p.fecha_prestamo, p.fecha_vencimiento ")
		.append("FROM usuarios u, prestamos p ")
		 .append("WHERE cedula=")
		 .append(cedula)
		  .append(" and p.id_usuario=u.id_usuario");*/
		query.append("SELECT DISTINCT l.titulo,p.id_usuario,p.id_prestamo, u.nombres, p.fecha_prestamo, p.fecha_vencimiento ")
		.append("FROM usuarios u, prestamos p,libros l,ejemplares e ")
		 .append("WHERE cedula=")
		 .append(cedula)
		  .append(" and p.id_usuario=u.id_usuario and l.id_libro=p.id_libro and p.fecha_vencimiento < p.fecha_entrega");
		ResultSet res = conex.consultar(query.toString());
		
		try {
			if(!res.next()){
				entro=true;
				panel.getSan_guar().setEnabled(false);
				panel.getSan_ok().setEnabled(false);
				panel.alerta.setVisible(true);
				panel.jLabel2.setText("cedula no encontrada");
				panel.jTabbedPane.enable(false);}
			
			res.beforeFirst();
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		try {
			while (res.next())
			{
				
			  modelo.addElement(res.getObject("fecha_prestamo")+"                 "+res.getObject("fecha_vencimiento")+"               "+res.getObject("titulo"));
			  id_usua.addElement(res.getObject("id_usuario"));
			  id_prestamo.addElement(res.getObject("id_prestamo"));
			  fecha_ven.addElement(res.getObject("fecha_vencimiento"));
			  fecha_pres.addElement(res.getObject("fecha_prestamo"));
			  panel.jLabel.setText(res.getObject("nombres").toString());	
				  
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		panel.lista.setModel(modelo);
		
		 if(entro==false)
			 panel.getSan_guar().setEnabled(true);
		
	}
}

public void san_ale2(){
	panel.alerta1.setVisible(false);
	panel.getJButton1().setEnabled(true);
}
public void san_ale3(){
	
	panel.alerta2.setVisible(true);
	panel.jLabel22.setText("se gurado correctamente");

}
public void limpiar(){
	
modelo.clear();
nombreyape.clear();

}
}
