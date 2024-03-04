package arbol;

import javax.swing.JOptionPane;

/**
 *
 * @author Jonathan
 */
public class Ordenamientos {
    private Nodos raiz;
    public String ordenamiento = "";
    
    public Ordenamientos(){
        raiz=null;
    }
    
    public Nodos obtenerRaiz(){
        return raiz;
    }
    
    //buscar
    public Nodos buscarNodos(int dato, Nodos r){
        if(raiz==null){
            return null;
        } else if(r.dato==dato){
            return r;
        } else if(r.dato<dato){
            return buscarNodos(dato, r.hijoDerecho);
        } else{
            return buscarNodos(dato, r.hijoIzquierdo);
        }
    }
    
    //obtener el factor de equilibrio
    public int obtenerFE(Nodos x){
        if(x==null){
            return -1;
        } else{
            return x.fe;
        }
    }
    
    //rotación simple Izquierda
    public Nodos rotacionIzquierda(Nodos c){
        Nodos auxiliar=c.hijoIzquierdo;
        
        c.hijoIzquierdo=auxiliar.hijoDerecho;
        
        auxiliar.hijoDerecho=c;
        
        c.fe=Math.max(obtenerFE(c.hijoIzquierdo), obtenerFE(c.hijoDerecho))+1;
        auxiliar.fe=Math.max(obtenerFE(auxiliar.hijoIzquierdo), obtenerFE(auxiliar.hijoDerecho))+1;
        
        return auxiliar;
    }
    
    //rotación simple Derecha
    public Nodos rotacionDerecha(Nodos c){
        Nodos auxiliar=c.hijoDerecho;
        
        c.hijoDerecho=auxiliar.hijoIzquierdo;
        
        auxiliar.hijoIzquierdo=c;
        
        c.fe=Math.max(obtenerFE(c.hijoIzquierdo), obtenerFE(c.hijoDerecho))+1;
        auxiliar.fe=Math.max(obtenerFE(auxiliar.hijoIzquierdo), obtenerFE(auxiliar.hijoDerecho))+1;
        
        return auxiliar;
    }
    
    //Rotacion doble a la derecha
    public Nodos rotacionDobleIzquierda(Nodos c){
        Nodos auxiliar;
        c.hijoIzquierdo=rotacionDerecha(c.hijoIzquierdo);
        auxiliar=rotacionIzquierda(c);
        return auxiliar;
    }
    
     //Rotacion doble a la derecha
    public Nodos rotacionDobleDerecha(Nodos c){
        Nodos auxiliar;
        c.hijoDerecho=rotacionIzquierda(c.hijoDerecho);
        auxiliar=rotacionDerecha(c);
        return auxiliar;
    }
    
    //metodo para insertar AVL
    public Nodos insertarAVL(Nodos nuevo, Nodos subArbol){
        Nodos nuevoPadre = subArbol;
        if(nuevo.dato<subArbol.dato){
            if(subArbol.hijoIzquierdo==null){
                subArbol.hijoIzquierdo=nuevo;
            } else{
                subArbol.hijoIzquierdo=insertarAVL(nuevo, subArbol.hijoIzquierdo);
                if((obtenerFE(subArbol.hijoIzquierdo) - obtenerFE(subArbol.hijoDerecho)==2)){
                    if(nuevo.dato<subArbol.hijoIzquierdo.dato){
                        nuevoPadre=rotacionIzquierda(subArbol);
                    } else{
                        nuevoPadre=rotacionDobleIzquierda(subArbol);
                    }
                }
            }
        } else if(nuevo.dato>subArbol.dato){
            if(subArbol.hijoDerecho==null){
                subArbol.hijoDerecho=nuevo;
            } else {
                subArbol.hijoDerecho=insertarAVL(nuevo, subArbol.hijoDerecho);
                if((obtenerFE(subArbol.hijoDerecho) - obtenerFE(subArbol.hijoIzquierdo)==2)){
                    if(nuevo.dato>subArbol.hijoDerecho.dato){
                        nuevoPadre=rotacionDerecha(subArbol);
                    } else{
                        nuevoPadre=rotacionDobleDerecha(subArbol);
                    }
                }     
            }
        } else {
            System.out.println("Nodo Duplicado");
            JOptionPane.showMessageDialog(null, "Nodo Duplicado");
        }
        
        //actualizando el factor de equilibrio
        if((subArbol.hijoIzquierdo==null) && (subArbol.hijoDerecho!=null)){
            subArbol.fe=subArbol.hijoDerecho.fe+1;
        } else if((subArbol.hijoDerecho==null) && (subArbol.hijoIzquierdo!=null)){
            subArbol.fe=subArbol.hijoIzquierdo.fe+1;
        } else{
            subArbol.fe=Math.max(obtenerFE(subArbol.hijoIzquierdo), obtenerFE(subArbol.hijoDerecho))+1;
        }
        
        return nuevoPadre;
    }
    
    //Metodo para insertar
    public void insertar(int dato){
        Nodos nuevo = new Nodos(dato);
        
        if(raiz==null){
            raiz=nuevo;
        } else{
            raiz=insertarAVL(nuevo, raiz);
        }
    }
    
    //Recorridos
    public void inOrden(Nodos r){
        if(r!=null){
            inOrden(r.hijoIzquierdo);
            
            System.out.print(r.dato + ", ");
            ordenamiento += Integer.toString(r.dato) + ", ";
            
            inOrden(r.hijoDerecho);
        }
    }
    
    public void postOrden(Nodos r){
        if(r!=null){
            postOrden(r.hijoIzquierdo);
            postOrden(r.hijoDerecho);
            
            System.out.print(r.dato + ", ");
            ordenamiento += Integer.toString(r.dato) + ", ";
        }
    }
    
    public void preOrden(Nodos r){
        if(r!=null){
            System.out.print(r.dato + ", ");
            ordenamiento += Integer.toString(r.dato) + ", ";
            
            preOrden(r.hijoIzquierdo);
            preOrden(r.hijoDerecho);
        }
    }
}
