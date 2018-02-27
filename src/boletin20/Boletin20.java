package boletin20;

/**Supoñer que queredes facer o mantemento dun ficheiro dunha librería .  
 * Dos libros imos considerar 4 atributos :
 * Nome do libro
 * Autor
 * Precio
 * unidades
 * Para facer o mantemento  utiliza un menú cos seguintes puntos :
 * a) engadir  engade un novo libro ao noso ficheiro
 * b) consultar dado o título dun libro visualizar o seu precio . se o libro non o temos na librería visualizamos unha mensaxe
 * c) visualizar visualiza todos os datos do ficheiro
 * d) borrar  borra os libros que teñan 0 unidades
 * e) modificar  modifica o precio dun libro determinado
 **/

public class Boletin20 {

    public static void main(String[] args) {
        Metodos menu = new Metodos();
        menu.menu();
    }
    
}
