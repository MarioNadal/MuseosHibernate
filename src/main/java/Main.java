import database.EmfSingleton;
import entities.ObrasEntity;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;

import java.util.List;

public class Main {
    static EntityManager em = EmfSingleton.getInstance().getEmf().createEntityManager();
    public static void main(String[]args){
        String guiones = "-".repeat(20);
        int menu;
        boolean salir = false;
        do{
            System.out.println(guiones);
            System.out.println("1.Listar Obras");
            System.out.println("0.Salir");
            System.out.println(guiones);
            menu = libs.Leer.introduceEntero("Introduce el número del menú:");
            System.out.println(guiones);

            switch (menu){
                case 1 -> listarObras();
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
        String obraIntroducida;
        ObrasEntity e  = em.createQuery("from ObrasEntity where titulo like , ObrasEntity.class).getSingleResult();

    }

}
