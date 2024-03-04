package arbol;

/**
 *
 * @author Jonathan
 */
public class Nodos {
    int dato, fe;
    
    Nodos hijoIzquierdo, hijoDerecho;
    public Nodos(int d){
        this.dato=d;
        this.fe=0;
        this.hijoIzquierdo=null;
        this.hijoDerecho=null;
    }
}
