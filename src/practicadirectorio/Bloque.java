
package practicadirectorio;

public class Bloque {
    private String nombre;
    private int bytes=0;
    private int ident=0;
    public Bloque(){
        
    }
    public Bloque(String nombre, int bytes){
        this.nombre=nombre;
        this.bytes=this.bytes-bytes;
    }
    
    public void setNombre(String n){
        nombre=n;
    }
    
    public void setIdentifier(int ide){
        ident=ide;
    }
    
    public String getNombre(){
        return nombre;
    }

    public int getBytes() {
        return bytes;
    }
    public int getIdentifier() {
        return ident;
    }
    

    public void setBytes(int bytes) {
        this.bytes = bytes;
    }
    
    public String ver(){
        return "|"+nombre+"|";
    }
    public String contenido(){
        return "Bytes ocupados: "+bytes;
    }
    
    public void sumar(int n){
        bytes=bytes+n;
    }
}
