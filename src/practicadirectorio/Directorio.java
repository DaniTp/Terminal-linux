
package practicadirectorio;

import java.util.ArrayList;

public class Directorio {
    ArrayList <Directorio> directorios;
    ArrayList <Archivo> archivos;
    Directorio anterior;
    private String nombre;
    private Permisos prop;
    private Permisos otro;
    private int id;
    private String direccion;
    private int ident;
    public Directorio(String nombre, Permisos prop, Permisos otro, int id, int ide){
        this.nombre=nombre;
        directorios= new ArrayList<Directorio>();
        archivos= new ArrayList<Archivo>();
        this.prop=prop;
        this.otro=otro;
        this.id=id;
        ident=ide;
    }
    

        public Directorio(String nombre, Directorio anterior,Permisos prop, Permisos otro, int id,String direccion, int ide){
        this.nombre=nombre;
        directorios= new ArrayList<Directorio>();
        archivos= new ArrayList<Archivo>();
        this.anterior=anterior;
        this.prop=prop;
        this.otro=otro;
        this.id=id;
        this.direccion=direccion;
        ident=ide;
    }
        
     public Permisos getProp(){
        return prop;
    }
    
    public Permisos getOtro(){
        return otro;
    }
        public void setNombre(String nombre){
        this.nombre=nombre;
    }
        public void setDireccion(String dir){
        direccion=dir;
    }
        
        public void setPermisosProp(Permisos prop){
            this.prop=prop;
        }
       public void setPermisosOtros(Permisos otro){
            this.otro=otro;
        }
       
       public void setIdentifier(int ide){
            ident=ide;
        }
        
        public Directorio getAnterior(){
            return anterior;
        }
    
    public String getNombre(){
        return nombre;
    }
    
    public int getIdentifier(){
        return ident;
    }
    
    public void setDirectorios(Directorio d1){
        directorios.add(d1);
    }
    
    public void añadirDirectorio(String name,Directorio anter,Permisos pro, Permisos otros, int id,String direccion, int ide){
        boolean repetido=false;
        for(int i=0;i<directorios.size();i++){
        if(directorios.get(i).equals(name)){
            repetido=true;
        }
    }
        if(repetido==false){
        directorios.add(new Directorio(name,anter,pro,otros,id,direccion,ide));
        }
    }
    
    public void añadirArchivo(String nombre, int tam, Permisos prop, Permisos otro,String direccion, int ide){
        archivos.add(new Archivo(nombre,tam,prop,otro,direccion,ide));
    }
    
    public int getId(){
        return id;
    }
    
    public String getDireccion(){
        return direccion;
    }
    public void mostrar(){
        if(directorios.size()==0 && archivos.size()==0){
            System.out.println("Directorio vacio");
        }
        else{
      for(int i=0;i<directorios.size();i++){
          System.out.println(/*(i+1)+*/" Directorio: "+directorios.get(i).getNombre()+"   "+directorios.get(i).getProp().mostrar()+"  "+directorios.get(i).getOtro().mostrar());
      }
      
      for(int i=0; i<archivos.size();i++){
          System.out.println("Archivo: "+archivos.get(i).getNombre()+"   "+archivos.get(i).getProp().mostrar()+"  "+archivos.get(i).getOtro().mostrar());
      }
        }
    }
    public void mostrarpermisos(){
        System.out.println("permiso: "+prop.mostrar());
    }
    
    
    
}
