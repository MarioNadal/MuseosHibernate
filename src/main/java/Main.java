import database.EmfSingleton;
import entities.MuseosEntity;
import entities.ObrasEntity;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;
import libs.Leer;

import java.util.List;

public class Main {
    static EntityManager em = EmfSingleton.getInstance().getEmf().createEntityManager();
    static EntityTransaction transaction = em.getTransaction();
    public static void main(String[]args){
        String guiones = "-".repeat(20);
        int menu;
        boolean salir = false;
        do{
            System.out.println(guiones);
            System.out.println("1.Listar Obras");
            System.out.println("2.Modificar Museo");
            System.out.println("0.Salir");
            System.out.println(guiones);
            menu = libs.Leer.introduceEntero("Introduce el número del menú:");
            System.out.println(guiones);

            switch (menu){
                case 1 -> listarObras();
                case 2 -> modificarMuseo();
                case 0 -> salir = true;
                default -> System.out.println("Ese número no esta en el menú, introduzca un número del menu.");
            }

        }while(!salir);
        EmfSingleton.getInstance().getEmf().close();
    }

    public static void listarObras(){
        List<ObrasEntity> listaObras = em.createQuery(" from ObrasEntity ", ObrasEntity.class).getResultList();
        for(ObrasEntity obras: listaObras){
            System.out.println("Obra: " + obras.getTitulo() + "   Autor: " + obras.getAutoresByIdAutor().getNombre().toString() +  "   Museo: " + obras.getMuseosByIdMuseo().getNombre().toString());
        }
    }

    public static void modificarMuseo(){
        String obraSeleccionada="", museoSeleccionado="";
        boolean validarObra=false, validarMuseo=false;
        int idMuseoSeleccionado = 0;
        List<MuseosEntity> listaMuseos = null;
        transaction.begin();
        while(!validarObra){
            List<ObrasEntity> listaObras = em.createQuery("from ObrasEntity", ObrasEntity.class).getResultList();
            System.out.println("OBRAS:");
            System.out.println("--------------------------------------------------------------------------");
            for(ObrasEntity obras: listaObras){
                System.out.println("Obra: " + obras.getTitulo());
            }
            obraSeleccionada = Leer.introduceString("Introduce la obra que quiere modificar el museo");
            for(ObrasEntity obras:listaObras){
                if(obras.getTitulo().equals(obraSeleccionada)){
                    validarObra=true;
                }
            }
            if(!validarObra){
                System.out.println("Introduce una obra válida");
            }
        }
        while(!validarMuseo) {
            listaMuseos = em.createQuery("from MuseosEntity", MuseosEntity.class).getResultList();
            System.out.println("MUSEOS:");
            System.out.println("----------------------------------------------------------------------");

            for (MuseosEntity museos : listaMuseos) {
                System.out.println(museos.getNombre());
            }
            museoSeleccionado = Leer.introduceString("Introduce el museo que quieres para la obra");
            for(MuseosEntity museos : listaMuseos){
                if(museos.getNombre().equals(museoSeleccionado)){
                    validarMuseo=true;
                    idMuseoSeleccionado = museos.getId();
                }
            }
            if(!validarMuseo){
                System.out.println("Introduce un museo válido");
            }
        }
        ObrasEntity obraAModificar = em.createQuery("from ObrasEntity where titulo like ?1", ObrasEntity.class).setParameter(1, obraSeleccionada).getSingleResult();
        obraAModificar.setIdMuseo(idMuseoSeleccionado);
        System.out.println("----------------------------------------------------------------------");
        System.out.println("Museo de " + obraAModificar.getTitulo() + " modificado correctamente.");
        System.out.println("----------------------------------------------------------------------");
        transaction.commit();
        //Hay que refrescar el contexto para que se pueda ver después al listar las obras
        em.refresh(obraAModificar);
    }
}
