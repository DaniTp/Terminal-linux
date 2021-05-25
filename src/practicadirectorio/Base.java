
package practicadirectorio;

import java.io.*;
import java.util.*;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Base {
        private File archivo = new File("base.txt");
        private Scanner entrada;
        public Base(){

        }
        
        public void creaArchivo(){
            try {
            if(archivo.exists()){
                System.out.println("Ya existe el archivo");
            }else{
                
                    archivo.createNewFile();
                }
                } catch (Exception ex) {
                    Logger.getLogger(Base.class.getName()).log(Level.SEVERE, null, ex);
                }
            
        }
        
        public void asignardatos(String tipo, String nombre,String permisos1, String permisos2, String anterior){
            try {
                BufferedWriter Fescribe = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(archivo,true)));
                Fescribe.write(tipo+" "+nombre+" "+permisos1+" "+permisos2+" "+anterior);
                Fescribe.write("\n");
                System.out.println("Guardado");
                Fescribe.close();
            } catch (Exception ex) {
                Logger.getLogger(Base.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        
        public void mostrarArchivo(){
            try {
                FileReader fr = new FileReader("base.txt");
                BufferedReader br = new BufferedReader(fr);
                String cadena;
               // while((cadena=br.readLine())!=null){
               cadena=br.readLine();
                    System.out.println(""+cadena);
               // }
            } catch (Exception ex) {
                Logger.getLogger(Base.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        
        
        public void verDato(String nombre){
            try {
                BufferedReader leer = new BufferedReader(new FileReader("base.txt"));
                String linea="";
                while(linea.indexOf(nombre)!=-1){
                    System.out.println("Se encontró");
                }
            } catch (Exception ex) {
                Logger.getLogger(Base.class.getName()).log(Level.SEVERE, null, ex);
            }
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
