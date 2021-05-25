/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package practicadirectorio;


public class Archivo {
     private String nombre;
   private int bytes;
   private Permisos prop;
   private Permisos otro;
   private String direccion;
   private int ident;
   public Archivo(String nombre, int bytes,Permisos prop, Permisos otro,String direccion,int ide){
       this.nombre=nombre;
       this.bytes=bytes;
       this.prop=prop;
       this.otro=otro;  
       this.direccion=direccion;
       ident=ide;
   }

    public Permisos getProp() {
        return prop;
    }

    public void setProp(Permisos prop) {
        this.prop = prop;
    }

    public String getDireccion(){
        return direccion;
    }
    public Permisos getOtro() {
        return otro;
    }
    
    public int getIdentifier() {
        return ident;
    }

    public void setOtro(Permisos otro) {
        this.otro = otro;
    }
    
    public void setIdentifier(int ide) {
        ident = ide;
    }
   
   public void bytes(int nuevo){
       bytes=nuevo;
   }
   
   public String getNombre(){
       return nombre;
   }
   
   public void setNombre(String nombre){
       this.nombre=nombre;
   }
   public int getTamanio(){
       return bytes;
   }
}
