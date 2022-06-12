
import java.util.List;
import java.util.Scanner;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import pojos.Localidades;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author alumno
 */
public class Main {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        Localidades loc;

        SessionFactory sf = HibernateUtil.sessionFactory();
        Session sesion = sf.openSession();
        Transaction t = sesion.beginTransaction();

        String cod,
                nombre,
                nombreProv;
        int censo,
                habitantes;

        System.out.println("1-//mostrar tabla \n"
                + "2-//Añadir una nueva localidad \n"
                + "3-//consultar \n"
                + "4-//actualizar censo \n"
                + "5-//eliminar localidad \n"
                + "6-//mostrar tabla actualizada \n"
                + "7-//Salir");
        System.out.println("seleciona una opcion");

        int opcion = sc.nextInt();

        switch (opcion) {
            case 1://mostrar tabla

                Query qSelectLocal = sesion.createQuery("From Localidades");
                List<Localidades> listaLocalidades = qSelectLocal.list();

                for (int i = 0; i < listaLocalidades.size(); i++) {
                    System.out.println(listaLocalidades.get(i).toString());
                }
                break;
            case 2://Añadir una nueva localidad, cuyos datos se pedirán por teclado

                System.out.println("introduce cod");
                cod = sc.nextLine();
                System.out.println("introduce nombre");
                nombre = sc.nextLine();
                System.out.println("introduce censo");
                censo = sc.nextInt();
                System.out.println("introduce habitantes");
                habitantes = sc.nextInt();
                System.out.println("introduce nombreProv");
                nombreProv = sc.next();
                loc = new Localidades(cod, nombre, censo, habitantes, nombreProv);
                sesion.save(loc);
                break;
            case 3://consultar
                //consultar
                //tener en cuenta si el id es string o int

                System.out.println("intruce id de localidad");
                cod = sc.nextLine();
                loc = (Localidades) sesion.get(Localidades.class, "305");//este es el id
                if (loc == null) {
                    System.out.println("Esa localidad no existe");
                } else {

                    System.out.println(loc.toString());
                }
                break;
            case 4: //actualizar censo

                System.out.println("intruce id de localidad");
                cod = sc.nextLine();
                System.out.println("intruce censo nuevo");
                censo = sc.nextInt();
                loc = (Localidades) sesion.get(Localidades.class, cod);
                if (loc == null) {
                    System.out.println("no existe esa localidad");
                } else {
                    loc.setCenso(censo);

                    sesion.update(loc);
                    System.out.println("Actualizado correctamente");

                }
                break;
            case 5://eliminar localidad

                System.out.println("intruce id de localidad");
                cod = sc.next();
                loc = (Localidades) sesion.get(Localidades.class, cod);
                if (loc == null) {
                    System.out.println("no existe esa localidad");
                } else {

                    sesion.delete(loc);
                    System.out.println("eliminado correctamente");
                }

                break;
            case 6:
                Query qSelectLocalAc = sesion.createQuery("From Localidades");
                List<Localidades> listaLocalidadess = qSelectLocalAc.list();

                for (int i = 0; i < listaLocalidadess.size(); i++) {
                    System.out.println(listaLocalidadess.get(i).toString());
                }

                break;

        }

        t.commit();
        sesion.close();
        System.exit(0);

    }

}
