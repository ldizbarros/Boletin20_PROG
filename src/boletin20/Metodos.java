package boletin20;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

public class Metodos {
    
    File ficheiro;
    FileWriter escribir;
    PrintWriter esc;
    Scanner sc;
    
    public void menu(){
        System.out.println("****************** MENU ******************\n"
                         + "*   1)Añadir Libro                       *\n"
                         + "*   2)Mostrar precio de un libro         *\n"
                         + "*   3)Visualizar libros disponibles      *\n"
                         + "*   4)Borrar libros con 0 unidades       *\n"
                         + "*   5)Modificar prezo dun libro          *\n"
                         + "*   0)Salir                              *\n"
                         + "******************************************");
        
        int opcion = Integer.parseInt(JOptionPane.showInputDialog("Intruduce una opcion"));
        
        while(opcion!=0){
            switch(opcion){
                case 1: try {
                            this.engadirLibro();
                        } catch (IOException ex) {
                            Logger.getLogger(Metodos.class.getName()).log(Level.SEVERE, null, ex);
                        }
                        break;
                case 2: ArrayList <Libro> libros = this.crearArrayList();
                        this.visualizarPrezo(libros);
                        break;
                case 3: this.visualizarLibros();
                        break;
                case 4: try {
                            this.borrarLibros();
                        } catch (IOException ex) {
                            Logger.getLogger(Metodos.class.getName()).log(Level.SEVERE, null, ex);
                        }
                        break;
                case 5: try {
                            this.cambiarPrezoLibro();
                        } catch (IOException ex) {
                            Logger.getLogger(Metodos.class.getName()).log(Level.SEVERE, null, ex);
                        }
                        break;
                case 0: break;
                default: System.out.println("Opcion no valida");
            }
            
            System.out.println("****************** MENU ******************\n"
                         + "*   1)Añadir Libro                       *\n"
                         + "*   2)Mostrar precio de un libro         *\n"
                         + "*   3)Visualizar libros disponibles      *\n"
                         + "*   4)Borrar libros con 0 unidades       *\n"
                         + "*   5)Modificar prezo dun libro          *\n"
                         + "*   0)Salir                              *\n"
                         + "******************************************");
            opcion = Integer.parseInt(JOptionPane.showInputDialog("Intruduce una opcion"));
        }
    }
    
    public void engadirLibro() throws IOException{
        String titulo = JOptionPane.showInputDialog("Nombre del libro");
        String autor = JOptionPane.showInputDialog("Nombre del autor");
        float precio = Float.parseFloat(JOptionPane.showInputDialog("Precio del libro"));
        int unidades = Integer.parseInt(JOptionPane.showInputDialog("Numero de unidades"));
        
        Libro nuevoEjemplar = new Libro(titulo,autor,precio,unidades);
        
        try {
            ficheiro = new File("libros.txt");
            escribir = new FileWriter(ficheiro, true);
            escribir.write(nuevoEjemplar.guardarLibro()+"\n");
        } catch (FileNotFoundException ex) {
            System.out.println("ERROR! "+ex.getMessage());
        }
        finally{
            escribir.close();
        }
    }
    
    public ArrayList <Libro> crearArrayList(){
        String linea;
        String [] lista = new String[4];
        ArrayList <Libro> listaLibros = new ArrayList();
        Libro ejemplar;
        try {
            sc =  new Scanner(new File("libros.txt"));
            while(sc.hasNextLine()){
                linea = sc.nextLine();
                lista=linea.split(";");
                ejemplar = new Libro(lista[0],lista[1],Float.parseFloat(lista[2]),Integer.parseInt(lista[3]));
                listaLibros.add(ejemplar);
            }    
        } catch (FileNotFoundException ex) {
            System.out.println("ERROR! "+ex.getMessage());
        }
        sc.close();
        return listaLibros;
    }
    
    public void visualizarLibros(){
        ArrayList <Libro> listaLibros = this.crearArrayList();
        Iterator it = listaLibros.iterator();
        while(it.hasNext()){
            System.out.print(it.next());
        }
    }
    
    public void visualizarPrezo(ArrayList <Libro> listaLibros){
        String titulo = JOptionPane.showInputDialog("Introduce el titulo del libro");
        boolean encontrado = false;
        Iterator it = listaLibros.iterator();
        while(it.hasNext()){
            Libro ejemplar= (Libro) it.next();
            if (ejemplar.getNome().equalsIgnoreCase(titulo)){
                encontrado=true;
                System.out.println("El precio de del libro "+ejemplar.getNome()+" es de "+ejemplar.getPrecio()+" €");
            }
        }
        if (encontrado==false){
            System.out.println("No hay ningun libro en la libreria con ese titulo");
        }
    }
    
    public void borrarLibros() throws IOException{
        boolean encontrado = false;
        ArrayList <Libro> libros = this.crearArrayList();
        Iterator it = libros.iterator();
        while(it.hasNext()){
            Libro ejemplar = (Libro) it.next();
            if (ejemplar.getUnidades()==0){
                it.remove();
                encontrado=true;
            }
        }
        try {
            ficheiro = new File("libros.txt");
            escribir = new FileWriter(ficheiro);
            Iterator it2 = libros.iterator();
            while(it2.hasNext()){
                Libro ejemplar = (Libro) it2.next();
                escribir.write(ejemplar.guardarLibro()+"\n");
            }            
        } catch (FileNotFoundException ex) {
            System.out.println("ERROR! "+ex.getMessage());
        }
        finally{
            escribir.close();
        }
        if (encontrado==false){
            System.out.println("No hay ningun ejemplar con 0 unidades");
        }
        else{
            System.out.println("Se han eliminado los ejemplares con exito");
        }
    }
    
    public void cambiarPrezoLibro() throws IOException{
        boolean encontrado = false;
        String titulo = JOptionPane.showInputDialog("Introduce el titulo del libro");
        float prezo = Float.parseFloat(JOptionPane.showInputDialog("Introduce el nuevo precio"));
        ArrayList <Libro> libros = this.crearArrayList();
        Iterator it = libros.iterator();
        while(it.hasNext()){
            Libro ejemplar = (Libro) it.next();
            if (ejemplar.getNome().equalsIgnoreCase(titulo)){
                ejemplar.setPrecio(prezo);
                encontrado=true;
            }
        }
        try {
            ficheiro = new File("libros.txt");
            escribir = new FileWriter(ficheiro);
            Iterator it2 = libros.iterator();
            while(it2.hasNext()){
                Libro ejemplar = (Libro) it2.next();
                escribir.write(ejemplar.guardarLibro()+"\n");
            }            
        } catch (FileNotFoundException ex) {
            System.out.println("ERROR! "+ex.getMessage());
        }
        finally{
            escribir.close();
        }
        if (encontrado==false){
            System.out.println("No se ha encontrado ningun ejemplar con ese titulo");
        }
        else{
            System.out.println("Se ha cambiado el precio con exito");
        }
    }
}
