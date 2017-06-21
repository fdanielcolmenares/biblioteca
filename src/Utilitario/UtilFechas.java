package Utilitario;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

public class UtilFechas {

    public static final String DD_MM_YYYY = "dd-MM-yyyy";
    public static final String YYYY_MM_DD = "yyyy-MM-dd";
    public static final int DIAS = Calendar.DATE;
    public static final int MESES = Calendar.MONTH;
    public static final int ANHOS = Calendar.YEAR;
    public static final int SEGUNDOS = Calendar.SECOND;

    public static String convertirFecha(String fecha, String formatoOrigen, String formatoDestino) {
        try {
            Date d = new SimpleDateFormat(formatoOrigen).parse(fecha);
            return convertirFecha(d, formatoDestino);
        } catch (ParseException e) {
        }
        return "";
    }

    public static String convertirFecha(Calendar fecha, String formatoDestino) {
        try {
            return convertirFecha(fecha.getTime(), formatoDestino);
        } catch (Exception e) {
        }
        return "";
    }

    public static String convertirFecha(Date d, String formatoDestino) {
    	if(d == null){
    		return "";
    	}
    	
    	Calendar c = new GregorianCalendar();
    	c.setTime(d);
    	String fecha = "";
        String dd = String.valueOf(c.get(Calendar.DAY_OF_MONTH));
        String mm = String.valueOf(c.get(Calendar.MONTH) + 1);
        String yy = String.valueOf(c.get(Calendar.YEAR));
        if (dd.length() == 1) {
            dd = "0" + dd;
        }
        if (mm.length() == 1) {
            mm = "0" + mm;
        }

        if (formatoDestino.compareToIgnoreCase("dd-MM-yyyy") == 0) {
            fecha = dd + "-" + mm + "-" + yy;
        } else {
            fecha = yy + "-" + mm + "-" + dd;
        }
        return fecha;
    }

    public static String convertirFechaHora(Date d, String formatoDestino) {
    	Calendar c = new GregorianCalendar();
    	c.setTime(d);
    	String dd = String.valueOf(c.get(Calendar.DAY_OF_MONTH));
        String mm = String.valueOf(c.get(Calendar.MONTH) + 1);
        String yy = String.valueOf(c.get(Calendar.YEAR));
        if (dd.length() == 1) {
            dd = "0" + dd;
        }
        if (mm.length() == 1) {
            mm = "0" + mm;
        }
        String fecha = "";

        if (formatoDestino.compareToIgnoreCase("dd-MM-yyyy") == 0) {
            fecha = dd + "-" + mm + "-" + yy;
        } else {
            fecha = yy + "-" + mm + "-" + dd;
        }

        String h = String.valueOf(c.get(Calendar.HOUR_OF_DAY));
        String m = String.valueOf(c.get(Calendar.MINUTE));

        if (h.length() == 1) {
            h = "0" + h;
        }
        if (m.length() == 1) {
            m = "0" + m;
        }


        fecha = fecha + " " + h + ":" + m;

        return fecha;
    }

    public static Date convertir_a_Date(String fecha, String formatoOrigen) {
        try {
            Date d = new SimpleDateFormat(formatoOrigen).parse(fecha);
            return d;
        } catch (Exception e) {
        }
        return null;
    }

    public static String getFechaHoraActual(){
        Calendar c = Calendar.getInstance();
        String d = String.valueOf(c.get(Calendar.DAY_OF_MONTH));
        String m = String.valueOf(c.get(Calendar.MONTH)+1);
        String a = String.valueOf(c.get(Calendar.YEAR));

        if (d.length() == 1) {
            d = "0" + d;
        }
        if (m.length() == 1) {
            m = "0" + m;
        }
        if (a.length() == 1) {
            a = "0" + a;
        }

        String fecha;
        fecha = "" + d + "" + m + "" + a;

        String h = String.valueOf(c.get(Calendar.HOUR_OF_DAY));
        m = String.valueOf(c.get(Calendar.MINUTE));
        String s = String.valueOf(c.get(Calendar.SECOND));
        String ms = String.valueOf(c.get(Calendar.MILLISECOND));

        if (h.length() == 1) {
            h = "0" + h;
        }
        if (m.length() == 1) {
            m = "0" + m;
        }
        if (s.length() == 1) {
            s = "0" + s;
        }
        if (ms.length() == 1) {
            ms = "0" + ms;
        }

        String hora;
        hora = "" + h + "" + m + "" + s + "" + ms;

        return fecha + "" + hora;
    }

    public static String getHoraActual() {
        Calendar c = Calendar.getInstance();
        String h = String.valueOf(c.get(Calendar.HOUR_OF_DAY));
        String m = String.valueOf(c.get(Calendar.MINUTE));
        String s = String.valueOf(c.get(Calendar.SECOND));

        if (h.length() == 1) {
            h = "0" + h;
        }
        if (m.length() == 1) {
            m = "0" + m;
        }
        if (s.length() == 1) {
            s = "0" + s;
        }

        String hora;
        hora = "" + h + ":" + m + ":" + s;

        return hora;
    }

    /**
     *
     * @param inicio Fecha inicio para comenzar a contar
     * @param cantidad Cantidad de dias o meses a aumentar
     * @param tipo Especifica si se aumentan dias o meses
     * @return la fecha luego de la cantidad de dias o meses
     */
    public static Date avanzarFecha(Date inicio, int cantidad, int tipo) {
        Calendar fin = GregorianCalendar.getInstance();
        fin.setTime(inicio);
        fin.add(tipo, cantidad);

        return fin.getTime();
    }

    /**
     *
     * @param inicio Fecha inicio para comenzar a contar
     * @param cantidad Cantidad de dias o meses a retroceder
     * @param tipo Especifica si se aumentan dias o meses
     * @return la fecha luego de la cantidad de dias o meses
     */
    public static Date retrocederFecha(Date inicio, int cantidad, int tipo) {
        Calendar fin = GregorianCalendar.getInstance();
        fin.setTime(inicio);
        fin.add(tipo, -cantidad);

        return fin.getTime();
    }

    public static int getCampo(Date fecha, int campo){
        int f = 0;
        Calendar c = new GregorianCalendar();
    	c.setTime(fecha);
        if(campo == DIAS){
            f = c.get(Calendar.DAY_OF_MONTH);
        }
        if(campo == MESES){
            f = c.get(Calendar.MONTH) + 1;
        }
        if(campo == ANHOS){
            f = c.get(Calendar.YEAR);
        }

        return f;
    }

    public static String getStringCampo(Date fecha, int campo){
        int f = 0;
        
        f = getCampo(fecha, campo);

        String res = String.valueOf(f);
        if(res.length()==1){
            res = "0"+res;
        }

        return res;
    }

    public static int getMaxDia_del_mes(Date fecha){
        int f = 0;
        int m = getCampo(fecha, MESES)-1;
        int a = getCampo(fecha, ANHOS);
        GregorianCalendar calendar = new GregorianCalendar();
        switch(m){
            case 0: case 2: case 4: case 6: case 7: case 9: case 11:
                f=31;
                break;
            case 1:
                if(calendar.isLeapYear(a)&&(a%1000!=0))
                    f=29;
                else
                    f=28;
                break;
            default:
                f=30;
        }

        return f;
    }

    public static int getMaxDia_del_mes(int mes, int anho){
        int f = 0;
        int m = mes;
        int a = anho;
        GregorianCalendar calendar = new GregorianCalendar();
        switch(m){
            case 0: case 2: case 4: case 6: case 7: case 9: case 11:
                f=31;
                break;
            case 1:
                if(calendar.isLeapYear(a)&&(a%1000!=0))
                    f=29;
                else
                    f=28;
                break;
            default:
                f=30;
        }

        return f;
    }

    public static String getNombreMes(int mes){
        String nom = "";

        switch(mes){
            case 0: nom = "Enero";
                    break;
            case 1: nom = "Febrero";
                    break;
            case 2: nom = "Marzo";
                    break;
            case 3: nom = "Abril";
                    break;
            case 4: nom = "Mayo";
                    break;
            case 5: nom = "Junio";
                    break;
            case 6: nom = "Julio";
                    break;
            case 7: nom = "Agosto";
                    break;
            case 8: nom = "Septiembre";
                    break;
            case 9: nom = "Octubre";
                    break;
            case 10: nom = "Noviembre";
                    break;
            case 11: nom = "Diciembre";
                    break;
        }

        return nom;
    }

    /*public static Date setMaximaHora(Date d){
        d.setHours(23);
        d.setMinutes(59);
        d.setSeconds(59);
        return d;
    }*/

    public static Date getFechaAntigua() {
        return convertir_a_Date("01-01-2000", DD_MM_YYYY);
    }

    public static String getStringFechaAntigua() {
        return convertirFecha("01-01-2000", DD_MM_YYYY, YYYY_MM_DD);
    }

    public static Date getFechaFutura(){
        return convertir_a_Date("01-01-2200", DD_MM_YYYY);
    }

    public static String getStringFechaFutura() {
        return convertirFecha("01-01-2200", DD_MM_YYYY, YYYY_MM_DD);
    }

    public static int getDiasEntre(Date d1, Date d2){
        long ms = d1.getTime() - d2.getTime();
        
        int dias = (int)(ms/(3600*24*1000));
        
        return dias;
    }

}
