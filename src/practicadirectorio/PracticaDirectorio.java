
package practicadirectorio;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.StringTokenizer;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Usx
 */
public class PracticaDirectorio {
    Directorio raiz;
    Directorio actual;
    String dr="root/";
    String drsinroot="";
    Scanner sc;
    ArrayList<String> cmd = new ArrayList<>();
    ArrayList<Usuario> usuarios = new ArrayList<>();
    Usuario usactual;
    //Base guardar = new Base();
    File guardar = new File("base.txt");
    Scanner entrada;
    int id=1;
    static Permisos prodefecto = new Permisos(true,true,true);
    static Permisos otrosdefecto = new Permisos(true,false,true);
    static Permisos otrosdefecto1 = new Permisos(true,true,true);
    static int identifier=0;
    PracticaDirectorio(){
        raiz= new Directorio("root",prodefecto,otrosdefecto1,0,1);
        actual=raiz;
        sc= new Scanner(System.in);
    }

    public static void main(String[] args) {
        Almacenamiento.inicializaDiscoAlmacenamiento();
        PracticaDirectorio p1= new PracticaDirectorio();
        p1.menu();
    }
    
    
    void menu(){                
        Usuario root = new Usuario("root","root123",1);
    Usuario Daniel = new Usuario("danieltp","daniel123",0);
    Usuario Lili = new Usuario("lilicz","lili123",0);
    usuarios.add(root);
    usuarios.add(Daniel);
    usuarios.add(Lili);
        String instruccion;
        int opc=0;
        verDato();
        limpiarArchivo();
        login();
        do{
            System.out.println("");
            System.out.print(dr);
            instruccion= sc.nextLine();
            
            
            opc=ObtenInstruccion(instruccion);
          //  System.out.println(opc);
            switch(opc){
                case 1:{
                    crear();
                    restaurar();
                    break;
                }
                case 2:{
                 //   System.out.println(dr);
                    crearArchivo();
                   // System.out.println(dr);
                    break;
                }
                case 3:{
                    ingresarDirectorio(cmd.get(1),0);
                    break;
                }
                case 4:{
                    mostrar();
                    break;
                }
                case 5:{
                    
                    actual=raiz;
                    dr="root/";
                    drsinroot="";
                    break;
                }
                case 6:{
                    if(actual.getNombre()!="root"){
                  //      System.out.println("entre");
            StringTokenizer tokens = new StringTokenizer(dr,"/");
            StringTokenizer tokens1 = new StringTokenizer(drsinroot,"/");
            ArrayList<String> dire = new ArrayList<>();
            while(tokens.hasMoreTokens()){
          
            String dir=tokens.nextToken();
            dire.add(dir);
          
        }
            dr="";
            drsinroot="";
            for(int i=0;i < dire.size()-1;i++){
                dr+=dire.get(i)+"/";
                if(!dire.get(i).equals("root"))
                drsinroot+=dire.get(i)+"/";
            }
                    actual= actual.anterior;
                    }else{
                        System.out.println("Error");
                    }
                    break;
                }
                case 7:{ 
                        eliminarArchivo(cmd.get(1));
                    break;
                }
                case 8:{
                    login();
                    break;
                }
                case 9:{
                    cambiarpermisos();
                    break;
                }
                case 10:{
                    eliminarDirectorio(cmd.get(1));
                    break;
                }
               case 11:{
                    CambiarNombre();
                    break;
                }
               case 12:{
                   verbloq();
               }
            }
        
        }while(opc!=0);
    }
    
    public void login(){
               String us="";
        String cont="";
        boolean usencontrado=false;
         do{
        System.out.println("Ingrese usuario...");
        us=sc.nextLine();
        if(us.equals("exit")){
            guardarRecursivo(raiz);
            System.exit(0);
            
        }
        System.out.println("Ingrese password");
        cont=sc.nextLine();
        for(int i=0;i<usuarios.size();i++){
            if(usuarios.get(i).getUsuario().equals(us) && usuarios.get(i).getPasword().equals(cont)){
                usactual=usuarios.get(i);
                usencontrado=true;
                break;
            }
        }
        if(usencontrado==false){
            System.out.println("Verifique el nombre de usuario o contraseña");
        }
        }while(usencontrado==false);
         
         
    }
    void crear(){
        
        boolean permitido=verificapermisosdirectorio(1);
        if(permitido){
        if(cmd.size()==2){
        String name=cmd.get(0);
        boolean repetido=false;
       // do{
            repetido=false;
       // System.out.println("Ingrese el nombre del nuevo directorio a crear en la carpeta: "+actual.getNombre());
        name=cmd.get(1);
        for(int i=0;i<actual.directorios.size();i++){
            if(actual.directorios.get(i).getNombre().equals(name)){
                System.out.println("Error... Ya existe un directorio con ese nombre");
                repetido=true;
            }
        }
        
     //   }while(repetido==true);
     if(repetido==false){
        identifier++;
        if(Almacenamiento.agregar(name, 1, prodefecto, otrosdefecto,identifier)==1){
            actual.añadirDirectorio(name,actual,prodefecto,otrosdefecto,id,drsinroot,identifier);
            id++;
            System.out.println("Se creo el directorio "+ name);
        }
     }
        }else if(cmd.size()==3){
            Directorio aux=actual;
          //  System.out.println(cmd.get(1));
            boolean encontrado=ingresarDirectorio(cmd.get(1),1);
            if(encontrado==true){
                        String name=cmd.get(2);
        boolean repetido=false;
        //do{
            repetido=false;
       // System.out.println("Ingrese el nombre del nuevo directorio a crear en la carpeta: "+actual.getNombre());
       // name=sc.next();
        for(int i=0;i<actual.directorios.size();i++){
            if(actual.directorios.get(i).getNombre().equals(name)){
                System.out.println("Error... Ya existe un directorio con ese nombre");
                repetido=true;
            }
        }
        
        //}while(repetido==true);
        if(repetido==true){
            System.out.println("Error, ya existe un directorio con ese nombre...");
        }else{
            identifier++;
            if(Almacenamiento.agregar(name, 1, prodefecto, otrosdefecto,identifier)==1){
            actual.añadirDirectorio(name,actual,prodefecto,otrosdefecto,id,drsinroot,identifier);
            id++;
            actual=aux;
            System.out.println("Se creo el directorio "+ name);
        }
        
        }
            }
            
        }
        }else System.out.println("No tienes permisos");
    }
       
    void mostrar(){
        actual.mostrar();
        
    }
    
    
    boolean ingresarDirectorio(String direccion, int quedar){
      //  String direccion;
        boolean encontrado=false;
    //    System.out.println("Ingrese el directorio al que desea acceder, o la dirección...");
      //  direccion= sc.next();
        StringTokenizer tokens = new StringTokenizer(direccion,"/");
        boolean permitido=false;
        while(tokens.hasMoreTokens()){
            encontrado=false;
            String dir=tokens.nextToken();
            for(int i =0;i<actual.directorios.size();i++){
                if(actual.directorios.get(i).getNombre().equals(dir)){
                    switch(usactual.getTipo()){
                        case 1:{
                                if(actual.directorios.get(i).getProp().isLectura()==true){
                                permitido=true;
                                }else{
                                    permitido=false;
                                }
                                        
                            break;
                        }
                        case 0:{
                          if(actual.directorios.get(i).getOtro().isLectura()==true){
                                permitido=true;
                                }else{
                                    permitido=false;
                                }
                                        
                            break;
                        }
                    }
                    if(permitido==true){
                    actual=actual.directorios.get(i);
                    if(quedar!=1){
                    dr+=actual.getNombre()+"/";
                    drsinroot+=actual.getNombre()+"/";
                    }
                    encontrado=true;
                    }else{
                        System.out.println("No cuentas con permisos para acceder al directorio");
                        break;
                    }
                }
            }
            
            
            if(encontrado==false){
                actual=raiz;
                System.out.println("La dirección no existe");
                break;
            }
            
        }
        return encontrado;
    }
    
        boolean ingresarDirectorioInicio(String direccion, int quedar){
      //  String direccion;
        boolean encontrado=false;
    //    System.out.println("Ingrese el directorio al que desea acceder, o la dirección...");
      //  direccion= sc.next();
        StringTokenizer tokens = new StringTokenizer(direccion,"/");
        boolean permitido=false;
        while(tokens.hasMoreTokens()){
            encontrado=false;
            String dir=tokens.nextToken();
            for(int i =0;i<actual.directorios.size();i++){
                if(actual.directorios.get(i).getNombre().equals(dir)){
                    
                    actual=actual.directorios.get(i);
                    if(quedar!=1){
                    dr+=actual.getNombre()+"/";
                    drsinroot+=actual.getNombre()+"/";
                    }
                    encontrado=true;

                }
            }
            
            
            if(encontrado==false){
                actual=raiz;
                System.out.println("La dirección no existe");
                break;
            }
            
        }
        return encontrado;
    }
    
        void crearArchivo(){
       boolean permitido=verificapermisosdirectorio(1);
       if(permitido){
            if(cmd.size()==2){
        String name=cmd.get(1);
        int tamanio=0;
        boolean repetido=false;
            repetido=false;
        
        for(int i=0;i<actual.archivos.size();i++){
            if(actual.archivos.get(i).getNombre().equals(name)){
                System.out.println("Ya existe un archivo con ese nombre...");
                repetido=true;
            }
        }
     if(repetido==true){
         System.out.println("Ya existe un archivo con ese nombre");
     }else{
        System.out.println("Ingrese el tamaño del archivo: ");
        tamanio=sc.nextInt();
        identifier++;
        if(Almacenamiento.agregar(name, tamanio, prodefecto, otrosdefecto,identifier)==1){
            actual.añadirArchivo(name,tamanio,prodefecto,otrosdefecto,drsinroot,identifier);
        } 
     }
            }
            
       }else{
           System.out.println("No tienes permisos");
       }
    }
        
        void eliminarDirectorio(String direc){
                boolean permitido1=verificapermisosdirectorio(1);
                if(permitido1){
            boolean encontrado=false;
            int pos=0;
            
            for(int i=0;i<actual.directorios.size();i++){
                if(actual.directorios.get(i).getNombre().equals(direc)){
                    pos=i;
                    encontrado=true;
                }
            }
            
            if(encontrado==true){
                boolean permitido=verificapermisos(direc, 1, 0);
                if(permitido==true){
                String name=actual.directorios.get(pos).getNombre();
                Almacenamiento.eliminar(actual.directorios.get(pos).getIdentifier());
                Almacenamiento.desfragmentar();
                actual.directorios.remove(pos);
                
                System.out.println("Se ha eliminado el directorio "+name);
                }
                else{
                    System.out.println("No tienes los permisos");
                }
            }else{
                System.out.println("No se encontró el archivo");
            }
                }else{
                    System.out.println("No tienes permisos en el directorio");
                }
        }
        
        void eliminarArchivo(String direc){
            
                boolean permitido1=verificapermisosdirectorio(1);
                if(permitido1){
             boolean encontrado=false;
            int pos=Buscar(direc,1);
            
            if(pos<9000){
                switch(usactual.getTipo()){
                    case 0:{
                        if(actual.archivos.get(pos).getOtro().isModificar()==true){
                        Almacenamiento.eliminar(actual.archivos.get(pos).getIdentifier());
                        Almacenamiento.desfragmentar();
                        actual.archivos.remove(pos);    
                        
                        }else{
                            System.out.println("No tienes permisos para modificar el archivo");
                        }
                              
                        break;
                    }
                    case 1:{
                        if(actual.archivos.get(pos).getProp().isModificar()==true){
                        Almacenamiento.eliminar(actual.archivos.get(pos).getIdentifier());
                        Almacenamiento.desfragmentar();
                        actual.archivos.remove(pos);
                        }else{
                            System.out.println("No tienes permisos para modificar el archivo");
                        }
                        break;
                    }
                }
                
                
            }else{
                System.out.println("No se encontro el archivo");
            }
                }else{
                    System.out.println("No tienes permisos");
                }
        }
        
            public void CambiarNombre(){
             boolean permitido1=verificapermisosdirectorio(1);
                if(permitido1){
            System.out.println("Digite 1 para directorio 2 para archivo");
            int que = sc.nextInt();
            int n=0;
            int pos=0;
            
            if(que==1){
                pos=Buscar(cmd.get(1), 0);               
            }else{
                pos=Buscar(cmd.get(1),1);
            }

            if(n!=9999){
                if(que==1){//directorio
                                    int tipo=que;
                if(que==1){
                    tipo=0;
                }else tipo=1;
                
                boolean permitido = verificapermisos(cmd.get(1), 1, tipo);
                if(permitido){
                    System.out.println("Me fue permitido"+permitido);
                    actual.directorios.get(pos).setNombre(cmd.get(2));
                    System.out.println("Nombre actualizado "+ cmd.get(2));
                }else{
                    System.out.println("No cuentas con permisos");
                }
                }else if(que==2){
                int tipo=que;
                if(que==1){
                    tipo=0;
                }else tipo=1;
                boolean permitido = verificapermisos(cmd.get(1), 1, tipo);
                if(permitido){
                    System.out.println("Me fue permitido"+permitido);
                    actual.archivos.get(pos).setNombre(cmd.get(2));
                    System.out.println("Nombre actualizado "+ cmd.get(2));
                }else{
                    System.out.println("No cuentas con permisos");
                }
                
                }

                
                
                
            }
                }  
        }
        
        
        
        public void cambiarpermisos(){
            boolean permitido1=verificapermisosdirectorio(1);
                if(permitido1){
            int opc=0;
            System.out.println("0.- De un directorio");
            System.out.println("1.- De un archivo");
            opc= sc.nextInt();
            
            //System.out.println(cmd.size());
            int pos=9999;
            if(cmd.size()==3){
              
                ArrayList comando= dividepalabra(cmd.get(1),"+");
                     pos=Buscar(cmd.get(2), opc);
                   //  System.out.println("adsf"+cmd.get(2));
                     boolean permitido=verificapermisos(cmd.get(2), 1, opc);
                     if(permitido==true){
                  //   System.out.println("pos "+pos); 
                     
                permisos(opc, comando,pos);
             //    System.out.println("Permisoscambiado al ");
                     }else System.out.println("No tienes los permisos");
            }else if(cmd.size()==4){//cambio permisos tanto del propietario como otros
                pos=Buscar(cmd.get(3), opc);
                //System.out.println("pos "+pos);
                boolean permitido=verificapermisos(cmd.get(2), 1, opc);
                if(permitido==true){
                ArrayList comando1= dividepalabra(cmd.get(1),"+");
                permisos(opc, comando1,pos);
                //System.out.println("Permisoscambiado");
                ArrayList comando2= dividepalabra(cmd.get(2),"+");
                permisos(opc, comando2,pos);
                }else{
                    System.out.println("No tienes los permisos");
                }
            }else{
                System.out.println("Error en número de argumentos");
            }
                }else{
                    System.out.println("No tienes permisos");
                }
        }
        
        
        public int Buscar(String nombre,int tipo){
            int pos=9999;
            boolean encontrado=false;
            if(tipo==0){//busca directorios
                for(int i=0;i<actual.directorios.size();i++){
                    if(actual.directorios.get(i).getNombre().equals(nombre)){
                    pos=i;
                    encontrado=true;
                    return pos;
                        
                }
                }
            }else if(tipo==1){//buscaarchivos
                for(int i=0;i<actual.archivos.size();i++){
                    if(actual.archivos.get(i).getNombre().equals(nombre)){
                    pos=i;
                    encontrado=true;
                    return pos;
                }
            }
        }
        return pos;
        }
        
        public ArrayList dividepalabra(String palabra, String signo){
            StringTokenizer tokens = new StringTokenizer(palabra,signo);
            ArrayList<String> palabradividida = new ArrayList<>();
            while(tokens.hasMoreTokens()){
          
            String dir=tokens.nextToken();
            palabradividida.add(dir);
          
        }
            return palabradividida;
            
        }
        
        public boolean verificapermisos(String dir, int permiso,int tipo){
            
            boolean permitido=false;
            boolean encontrado=false;
            
            if(tipo==0){ //Si es directorio
                for(int i =0;i<actual.directorios.size();i++){
                if(actual.directorios.get(i).getNombre().equals(dir)){
                    switch(permiso){
                        case 0:{    //Solocita permiso de lectura
                            switch(usactual.getTipo()){
                                case 0:{
                               if(actual.directorios.get(i).getOtro().isLectura()==true){
                                permitido=true;
                                }else{
                                    permitido=false;
                                }
                                        
                            break;
                                }
                                case 1:{
                               if(actual.directorios.get(i).getProp().isLectura()==true){
                                permitido=true;
                                }else{
                                    permitido=false;
                                }
                                }
                            }

                                        
                            break;
                        }
                        case 1:{//solicita permiso de modificar
                            switch(usactual.getTipo()){
                                case 0:{
                               if(actual.directorios.get(i).getOtro().isModificar()==true){
                                permitido=true;
                                }else{
                                    permitido=false;
                                }
                                        
                            break;
                                }
                                case 1:{
                               if(actual.directorios.get(i).getProp().isModificar()==true){
                                permitido=true;
                                }else{
                                    permitido=false;
                                }
                                }
                            }

                                        
                            break;
                        }
                        case 2:{//solicita permiso de ejecucion
                             switch(usactual.getTipo()){
                                case 0:{
                               if(actual.directorios.get(i).getOtro().isEjecutar()==true){
                                permitido=true;
                                }else{
                                    permitido=false;
                                }
                                        
                            break;
                                }
                                case 1:{
                               if(actual.directorios.get(i).getProp().isEjecutar()==true){
                                permitido=true;
                                }else{
                                    permitido=false;
                                }
                                }
                            }

                                        
                            break;
                            
                        }
                    }
                }
            }
            
        }else if(tipo==1){ //para archivos
                          for(int i =0;i<actual.archivos.size();i++){
                if(actual.archivos.get(i).getNombre().equals(dir)){
                    switch(permiso){
                        case 0:{    //Solocita permiso de lectura
                            switch(usactual.getTipo()){
                                case 0:{
                               if(actual.archivos.get(i).getOtro().isLectura()==true){
                                permitido=true;
                                }else{
                                    permitido=false;
                                }
                                        
                            break;
                                }
                                case 1:{
                               if(actual.archivos.get(i).getProp().isLectura()==true){
                                permitido=true;
                                }else{
                                    permitido=false;
                                }
                                }
                            }

                                        
                            break;
                        }
                        case 1:{//solicita permiso de modificar
                            switch(usactual.getTipo()){
                                case 0:{
                               if(actual.archivos.get(i).getOtro().isModificar()==true){
                                permitido=true;
                                }else{
                                    permitido=false;
                                }
                                        
                            break;
                                }
                                case 1:{
                               if(actual.archivos.get(i).getProp().isModificar()==true){
                                permitido=true;
                                }else{
                                    permitido=false;
                                }
                                }
                            }

                                        
                            break;
                        }
                        case 2:{//solicita permiso de ejecucion
                             switch(usactual.getTipo()){
                                case 0:{
                               if(actual.archivos.get(i).getOtro().isEjecutar()==true){
                                permitido=true;
                                }else{
                                    permitido=false;
                                }
                                        
                            break;
                                }
                                case 1:{
                               if(actual.archivos.get(i).getProp().isEjecutar()==true){
                                permitido=true;
                                }else{
                                    permitido=false;
                                }
                                }
                            }

                                        
                            break;
                            
                        }
                    }
                }
            }
            
        }
            return permitido;
        }
        
        public boolean verificapermisosdirectorio(int permiso){
                        
            boolean permitido=false;
            boolean encontrado=false;
            
     
  if(usactual.getTipo()==1){
                if(actual.getProp().isModificar()){
                permitido=true;
            }
  }else{
      if(actual.getOtro().isModificar()){
                permitido=true;
            }
  }
         return permitido;
        }
        
        public void permisos(int opc, ArrayList comando, int pos){
              
            if(opc==0){
                if(comando.get(0).equals("u")){
                  //  System.out.println("Entre en la u");
            //        System.out.println("pos "+pos);
                    if(comando.get(1).equals("rwx")){
                      Permisos p1= new Permisos(true,true,true);
                      actual.directorios.get(pos).setPermisosProp(p1);
                    //    actual.directorios.get(pos).getProp().setLectura(true);
                    //    actual.directorios.get(pos).getProp().setModificar(true);
                    //    actual.directorios.get(pos).getProp().setEjecutar(true);
                    //    actual.directorios.get(pos).mostrarpermisos();
                    }else if(comando.get(1).equals("rw-")){
                        Permisos p1= new Permisos(true,true,false);
                      actual.directorios.get(pos).setPermisosProp(p1);
                    }else if(comando.get(1).equals("r-x")){
                        Permisos p1= new Permisos(true,false,true);
                      actual.directorios.get(pos).setPermisosProp(p1);
                    }else if(comando.get(1).equals("-wx")){
                        Permisos p1= new Permisos(false,true,true);
                      actual.directorios.get(pos).setPermisosProp(p1);
                    }else if(comando.get(1).equals("r--")){
                        Permisos p1= new Permisos(true,false,false);
                      actual.directorios.get(pos).setPermisosProp(p1);
                    }else if(comando.get(1).equals("--x")){
                        Permisos p1= new Permisos(false,false,true);
                      actual.directorios.get(pos).setPermisosProp(p1);
                    }
                    else if(comando.get(1).equals("---")){
                        Permisos p1= new Permisos(false,false,false);
                      actual.directorios.get(pos).setPermisosProp(p1);
                    }else if(comando.get(1).equals("-w-")){
                        Permisos p1= new Permisos(false,true,false);
                      actual.directorios.get(pos).setPermisosProp(p1);
                    }
                }else if(comando.get(0).equals("o")){
                  //  System.out.println("Entre en la o");
                    if(comando.get(1).equals("rwx")){
                        Permisos p1= new Permisos(true,true,true);
                      actual.directorios.get(pos).setPermisosOtros(p1);
                        
                    }else if(comando.get(1).equals("rw-")){
                        Permisos p1= new Permisos(true,true,false);
                      actual.directorios.get(pos).setPermisosOtros(p1);
                    }else if(comando.get(1).equals("r-x")){
                        Permisos p1= new Permisos(true,false,true);
                      actual.directorios.get(pos).setPermisosOtros(p1);
                        
                    }else if(comando.get(1).equals("-wx")){
                        Permisos p1= new Permisos(false,true,true);
                      actual.directorios.get(pos).setPermisosOtros(p1);
                    }else if(comando.get(1).equals("r--")){
                        Permisos p1= new Permisos(true,false,false);
                      actual.directorios.get(pos).setPermisosOtros(p1);
                    
                    }else if(comando.get(1).equals("--x")){
                        Permisos p1= new Permisos(false,false,true);
                      actual.directorios.get(pos).setPermisosOtros(p1);
                    }
                       else if(comando.get(1).equals("---")){
                        Permisos p1= new Permisos(false,false,false);
                      actual.directorios.get(pos).setPermisosOtros(p1);
                    }else if(comando.get(1).equals("-w-")){
                        Permisos p1= new Permisos(false,true,false);
                      actual.directorios.get(pos).setPermisosOtros(p1);
                    }
                }else{
            System.out.println("Comando no valido...");
        }
            }
            else{
                                if(comando.get(0).equals("u")){
                  //  System.out.println("Entre en la u");
            //        System.out.println("pos "+pos);
                    if(comando.get(1).equals("rwx")){
                      Permisos p1= new Permisos(true,true,true);
                      actual.archivos.get(pos).setProp(p1);
                    //    actual.directorios.get(pos).getProp().setLectura(true);
                    //    actual.directorios.get(pos).getProp().setModificar(true);
                    //    actual.directorios.get(pos).getProp().setEjecutar(true);
                    //    actual.directorios.get(pos).mostrarpermisos();
                    }else if(comando.get(1).equals("rw-")){
                        Permisos p1= new Permisos(true,true,false);
                      actual.archivos.get(pos).setProp(p1);
                    }else if(comando.get(1).equals("r-x")){
                        Permisos p1= new Permisos(true,false,true);
                      actual.archivos.get(pos).setProp(p1);
                    }else if(comando.get(1).equals("-wx")){
                        Permisos p1= new Permisos(false,true,true);
                      actual.archivos.get(pos).setProp(p1);
                    }else if(comando.get(1).equals("r--")){
                        Permisos p1= new Permisos(true,false,false);
                      actual.archivos.get(pos).setProp(p1);
                    }else if(comando.get(1).equals("--x")){
                        Permisos p1= new Permisos(false,false,true);
                      actual.archivos.get(pos).setProp(p1);
                    }
                    else if(comando.get(1).equals("---")){
                        Permisos p1= new Permisos(false,false,false);
                      actual.archivos.get(pos).setProp(p1);
                    }else if(comando.get(1).equals("-w-")){
                        Permisos p1= new Permisos(false,true,false);
                      actual.archivos.get(pos).setProp(p1);
                    }
                }else if(comando.get(0).equals("o")){
                   // System.out.println("Entre en la o");
                    if(comando.get(1).equals("rwx")){
                        Permisos p1= new Permisos(true,true,true);
                      actual.archivos.get(pos).setOtro(p1);
                        
                    }else if(comando.get(1).equals("rw-")){
                        Permisos p1= new Permisos(true,true,false);
                      actual.archivos.get(pos).setOtro(p1);
                    }else if(comando.get(1).equals("r-x")){
                        Permisos p1= new Permisos(true,false,true);
                      actual.archivos.get(pos).setOtro(p1);
                        
                    }else if(comando.get(1).equals("-wx")){
                        Permisos p1= new Permisos(false,true,true);
                      actual.archivos.get(pos).setOtro(p1);
                    }else if(comando.get(1).equals("r--")){
                        Permisos p1= new Permisos(true,false,false);
                      actual.archivos.get(pos).setOtro(p1);
                    
                    }else if(comando.get(1).equals("--x")){
                        Permisos p1= new Permisos(false,false,true);
                      actual.archivos.get(pos).setOtro(p1);
                    }
                       else if(comando.get(1).equals("---")){
                        Permisos p1= new Permisos(false,false,false);
                      actual.archivos.get(pos).setOtro(p1);
                    }else if(comando.get(1).equals("-w-")){
                        Permisos p1= new Permisos(false,true,false);
                      actual.archivos.get(pos).setOtro(p1);
                    }
                }else{
            System.out.println("Comando no valido...");
        }
            }
        
        

        }     
        
        
        int ObtenInstruccion(String instruccion){
            cmd.removeAll(cmd);
    /*           String direccion;
        boolean encontrado=false;
        System.out.println("Ingrese el directorio al que desea acceder, o la dirección...");
        direccion= sc.next();*/
        //StringTokenizer tokens = new StringTokenizer(instruccion,"\\s");
        String linea = instruccion;
        String [] campos = linea.split("\\s+");
        
        for(int i=0;i<campos.length;i++){
            cmd.add(campos[i]);
        }
        //    System.out.println(cmd.size());
        if(cmd.get(0).equals("mkdir")){
            if(cmd.size()>3 || cmd.size()<1){
                System.out.println("Error en numero de argumentos");
            }else if(cmd.size()==2 || cmd.size()==3){
             //   System.out.println("HOla");
                return 1;
            }
                
        }else if(cmd.get(0).equals("touch")){
             if(cmd.size()>3 || cmd.size()<1){
                System.out.println("Error en numero de argumentos");
            }else if(cmd.size()==2 || cmd.size()==3){
             //   System.out.println("HOla");
                return 2;
            }
        }
        else if(cmd.get(0).equals("cd") && cmd.size()!=1) {
            if(!cmd.get(1).equals("..")){
            if(cmd.size()==2){
            return 3;
            }
            else{
                System.out.println("Error en numero de argumentos...");
            }
        }else{
            return 6;
        }
        }else if(cmd.get(0).equals("ls") && cmd.size()==1){
            return 4;
        }else if(cmd.get(0).equals("exit")){
            return 8;
        }else if(cmd.get(0).equals("cd") && cmd.size()==1){
            
            return 5;
            
        }else if(cmd.get(0).equals("rm")){
            return 7;
        }
        else if(cmd.get(0).equals("chmod")){
            return 9;
        }
        else if(cmd.get(0).equals("rmdir")){
            return 10;
        }else if(cmd.get(0).equals("movename")){
            if(cmd.size()==3){
            return 11;
            }else System.out.println("Error...Faltan argumentos");
        }else if(cmd.get(0).equals("ls") && cmd.size()>1) {
            if(cmd.get(1).equals("all")){
                return 12;
            }
        }
        return 10000;
        
        }
        
public void verbloq(){
    Almacenamiento.mos();
}
        
        public void guardarRecursivo(Directorio d){            
            for(int j=0;j<d.archivos.size();j++){
                    asignarArchivo("a",d.archivos.get(j).getNombre(), d.archivos.get(j).getProp().mostrar(), d.archivos.get(j).getOtro().mostrar(),d.archivos.get(j).getTamanio(), d.archivos.get(j).getDireccion(),d.archivos.get(j).getIdentifier());
                }
            for(int i=0;i<d.directorios.size();i++){
                asignardatos("d", d.directorios.get(i).getNombre(), d.directorios.get(i).getProp().mostrar(), d.directorios.get(i).getOtro().mostrar(),d.directorios.get(i).getAnterior().getNombre(),d.directorios.get(i).getAnterior().getId(),d.directorios.get(i).getId(),d.directorios.get(i).getDireccion(),d.directorios.get(i).getIdentifier());             
                guardarRecursivo(d.directorios.get(i)) ;
            }
        }

        
         public void restaurar(){
             mostrarArchivo();
         }
         
         
         
         
         //Cosas de archivo pa guardar
                 public void creaArchivo(){
            try {
            if(guardar.exists()){
                System.out.println("Ya existe el archivo");
            }else{
                
                    guardar.createNewFile();
                }
                } catch (Exception ex) {
                    Logger.getLogger(Base.class.getName()).log(Level.SEVERE, null, ex);
                }
            
        }
                 
          public void asignarArchivo(String tipo, String nombre,String permisos1, String permisos2,int tamanio,String direccion,int ide){
            try {
                BufferedWriter Fescribe = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(guardar,true)));
                Fescribe.write(tipo+" "+nombre+" "+permisos1+" "+permisos2+" "+tamanio+" "+direccion+" "+ide);
                Fescribe.write("\n");
                
                Fescribe.close();
            } catch (Exception ex) {
                Logger.getLogger(Base.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
                 
      public void asignardatos(String tipo, String nombre,String permisos1, String permisos2, String anterior,int id,int idmio,String direccion, int ide){  
          try {
                BufferedWriter Fescribe = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(guardar,true)));
                Fescribe.write(tipo+" "+nombre+" "+permisos1+" "+permisos2+" "+anterior+" "+id+" "+idmio+" "+direccion+" "+ide);
                Fescribe.write("\n");
                
                Fescribe.close();
            } catch (Exception ex) {
                Logger.getLogger(Base.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
                         
    public void limpiarArchivo(){
        try {
            BufferedWriter bw = new BufferedWriter(new FileWriter(guardar));
            bw.write("");
            bw.close();
        } catch (IOException ex) {
            Logger.getLogger(PracticaDirectorio.class.getName()).log(Level.SEVERE, null, ex);
        }
                         }
        
        public void mostrarArchivo(){
            try {
                FileReader fr = new FileReader("base.txt");
                BufferedReader br = new BufferedReader(fr);
                String cadena;
                while((cadena=br.readLine())!=null){
               cadena=br.readLine();
                    System.out.println(""+cadena);
                }
            } catch (Exception ex) {
                Logger.getLogger(Base.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        
        
        public void verDato(){
            try {
             /*   BufferedReader leer = new BufferedReader(new FileReader("base.txt"));
                String linea="";
                while(linea.indexOf(nombre)!=-1){
                    System.out.println("Se encontró");
                }*/
                FileReader fr = new FileReader("base.txt");
                BufferedReader br = new BufferedReader(fr);
                String cadena;
                if(guardar.length()>0){
                while((cadena=br.readLine())!=null){ 
              //  System.out.println(""+drsinroot);
                String [] campos = cadena.split("\\s+");
                if(campos[0].equals("d")){
                    Permisos propios=transformacionstringpermiso(campos[2]);
                    Permisos otros=transformacionstringpermiso(campos[3]);
                    int idmio= Integer.parseInt(campos[6]);
                    int idanterior=Integer.parseInt(campos[5]);
                    String direccion="";
                    int ide;
                  //  System.out.println("asdfasdf"+campos.length);
                    if(campos.length>=9){
                    direccion= campos[7];
                    
                    }
                if(campos.length>=9){
                       ide= Integer.parseInt(campos[8]);
                }else{
                    ide= Integer.parseInt(campos[7]);
                }
                    if(identifier<ide){
                        identifier=ide;
                    }
                    if(direccion.equals(drsinroot)){
                    Directorio nuevo = new Directorio(campos[1],actual,propios, otros,idmio,direccion,ide);
   
                    if(Almacenamiento.agregar(campos[1],1,propios, otros,ide)==1){
                      actual.setDirectorios(nuevo);
        }
                    
                    }
                    else{
                       if( ingresarDirectorioInicio(direccion, 1)){
                    Directorio nuevo = new Directorio(campos[1],actual,propios, otros,idmio,direccion,ide);
                                        if(Almacenamiento.agregar(campos[1],1,propios, otros,ide)==1){
                      actual.setDirectorios(nuevo);
        }
                 
                    actual=raiz;
                    drsinroot="";
                       }
                        
                    }
                           
                }else if(campos[0].equals("a")){
                    Permisos propios=transformacionstringpermiso(campos[2]);
                    Permisos otros=transformacionstringpermiso(campos[3]);
                    String direccion="";
                    int ide;
                    int tamanio = Integer.parseInt(campos[4]);
                    
                    direccion= campos[5];
                    ide=Integer.parseInt(campos[6]);
                    
                    if(direccion.equals(drsinroot)){                    
                    if(Almacenamiento.agregar(campos[1],1,propios, otros,ide)==1){
                        
                    if(Almacenamiento.agregar(campos[1],tamanio,propios, otros,ide)==1){
                        actual.añadirArchivo(campos[1],tamanio,propios,otros,direccion,ide);
                        }
                    
                    }
                    
                    }
                    else{
                       if( ingresarDirectorioInicio(direccion, 1)){                  
           if(Almacenamiento.agregar(campos[1],tamanio,propios, otros,ide)==1){
                      actual.añadirArchivo(campos[1],tamanio,propios,otros,direccion,ide);
        }
                    actual=raiz;
                    drsinroot="";
                       }
                        
                    }
                }       
                       
                }
                }
            } catch (Exception ex) {
                Logger.getLogger(Base.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        
        Permisos transformacionstringpermiso(String rwx){
            Permisos p1;
            if(rwx.equals("rwx")){
                        p1= new Permisos(true,true,true);                       
                    }else if(rwx.equals("rw-")){
                        p1= new Permisos(true,true,false);
                    
                    }else if(rwx.equals("r-x")){
                        p1= new Permisos(true,false,true);
                   
                        
                    }else if(rwx.equals("-wx")){
                         p1= new Permisos(false,true,true);
                     
                    }else if(rwx.equals("r--")){
                       p1= new Permisos(true,false,false);
                     
                    
                    }else if(rwx.equals("--x")){
                        p1= new Permisos(false,false,true);
                     
                    }
                       else if(rwx.equals("---")){
                       p1= new Permisos(false,false,false);
                    
                    }else if(rwx.equals("-w-")){
                       p1= new Permisos(false,true,false);   
                    }else{
                        p1= new Permisos(true,true,true);
                    }
                            
            
            return p1;
        }
        
        public void consultadato(String dato){
            try {
                entrada = new Scanner(new File("base.txt"));
                BufferedReader leer= new BufferedReader(new FileReader("base.txt"));
                String id = entrada.next();
                double saldo = entrada.nextDouble();
                
                
            } catch (Exception ex) {
                Logger.getLogger(Base.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
}
