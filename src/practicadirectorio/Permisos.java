
package practicadirectorio;

public class Permisos {
    private boolean lectura;
    private boolean modificar;
    private boolean ejecutar;
    public Permisos(boolean lectura, boolean modificar, boolean ejecutar){
        this.lectura=lectura;
        this.modificar=modificar;
        this.ejecutar=ejecutar;
    }

    public boolean isLectura() {
        return lectura;
    }

    public void setLectura(boolean lectura) {
        this.lectura = lectura;
    }

    public boolean isModificar() {
        return modificar;
    }

    public void setModificar(boolean modificar) {
        this.modificar = modificar;
    }

    public boolean isEjecutar() {
        return ejecutar;
    }

    public void setEjecutar(boolean ejecutar) {
        this.ejecutar = ejecutar;
    }
    
    public String mostrar(){
        if(lectura==true && modificar==true && ejecutar==true){
        return "rwx";
    }else if(lectura==true && modificar==true && ejecutar==false){
        return "rw-";
    }else if(lectura==true && modificar == false && ejecutar == false){
        return"r--";
    }else if(lectura==false && modificar==false && ejecutar == false){
        return "---";
    }else if(lectura==false && modificar== true && ejecutar ==false){
        return "-w-";
    }else if(lectura==false && modificar == true && ejecutar == true){
        return "-wx";
    }else if(lectura==true && modificar==false && ejecutar==true){
        return "r-x";
        
    }else if(lectura==false && modificar==false && ejecutar==true){
        return "--x";
    }
        return "";
        
    }
}
