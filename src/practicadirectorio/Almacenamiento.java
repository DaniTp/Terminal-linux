package practicadirectorio;
import java.util.ArrayList;
import java.util.Scanner;

public class Almacenamiento {
    static Scanner sc = new Scanner(System.in);
    static ArrayList<Archivo> archivos = new ArrayList<Archivo>();
    static int disponible=64;
    static int bloqueslibres=16;
    static ArrayList<Bloque> bloques = new ArrayList<Bloque>();
    static String vebloques= "";
    static int indice=0;
    static int indicem=0;
    static String[] disco = new String[64];
    
    static void inicializaDiscoAlmacenamiento(){
        for(int i=0;i<16;i++){
            bloques.add(new Bloque(" ",0));
        }
        for(int i =0;i<64;i++){
            disco[i]=" ";
        }
        
    }
    
    static int agregar(String nombre, int tamanio, Permisos adminPermisos, Permisos otrosPermisos,int ide){
          int bloquesusa=0;
          if((tamanio%4)!=0)
          bloquesusa=(tamanio/4)+1;
          else bloquesusa=(tamanio/4);
          
          if(bloquesusa>bloqueslibres){
              System.out.println("Error: No quedan bloques para usar...");   
              return 0;
          }else{
          
          int aux=tamanio;
          
    //       for(int j=0;j<8;j++)    {
          for(int i=0;i<16;i++){
              if(bloquesusa>0){
              if(bloques.get(i).getNombre().equals(" ")){
              int aux2=0;
          if(aux>=4){
              aux2=4;
              aux-=4;
          }else if(aux<4){
              aux2=aux;
          }
          bloques.get(i).setNombre(nombre);
          bloques.get(i).setBytes(aux2);
          bloques.get(i).setIdentifier(ide);
          bloqueslibres--;
          indice++;
          bloquesusa--;
              }
              }
          }
    //       }
          archivos.add(new Archivo(nombre,tamanio,adminPermisos,otrosPermisos,"",ide));
          disponible-=tamanio;
          
          
          return 1;
          }
  }
    
    public static void mos(){
        bloques();
        mostrarDisco();
    }
  static void eliminar(int ide){
      int pos=0;
      int[] bda=new int [16];
      int j=0;
      boolean encontrado=false;
      for(int i=0;i<archivos.size();i++){
          if(ide==archivos.get(i).getIdentifier()){
              pos=i;
              encontrado=true;
              break;
          }
      }
      if(encontrado==true){
      //    System.out.println("Se encontro");
      
      for(int i=0;i<archivos.size();i++){
             if(ide==archivos.get(i).getIdentifier()){
              disco[i]="null";
          }
      }
      for(int i=0;i<16;i++){
          if(ide==bloques.get(i).getIdentifier()){
              //System.out.println("i:"+i+" ideAlmacena:"+bloques.get(i).getIdentifier());
              bda[j]=i;
              j++;
          }
      }
      
          for (int i = 0; i < j; i++) {
              bloques.get(bda[i]).setNombre(" ");
              bloques.get(bda[i]).setBytes(0);
              bloques.get(bda[i]).setIdentifier(0);
              bloqueslibres++;
              //System.out.println("bloque a borrar:"+bda[i]);
          }
          
          indicem-=archivos.get(pos).getTamanio();
          archivos.remove(pos);
      //mostrarDisco();
      }else{
          System.out.println("No existe el nombre del archivo...");
      }
  }
  
    static void desfragmentar(){
      int contOperaciones=0;
        String aux="";
        String nomAct="";
        Boolean restoVacio=false;
        Boolean esZonaContigua=true;
        Boolean bloqueAntMayorAUno=false;
        if(bloqueslibres<16){
            for(int i=0;i<16;i++){
                esZonaContigua=true;
                if(bloqueAntMayorAUno){
                   bloqueAntMayorAUno=false;
                   i--;
               }
               nomAct=bloques.get(i).getNombre();
               if(nomAct.equals(" ")){
                   for(int j=i+1;j<16;j++){
                       if(!bloques.get(j).getNombre().equals(" ")){
                           //System.out.println("Empty: posicion i: "+i+" y pos j: "+j);
                           //muestraSlots();
                           aux=bloques.get(j).getNombre();
                           bloques.get(j).setNombre(bloques.get(i).getNombre());
                           bloques.get(i).setNombre(aux);
                           nomAct=aux;
                           contOperaciones++;
                           break;
                       }
                       if(j==15){
                           restoVacio=true;
                       }
                   }
               }
               if(restoVacio){
                   break;
               }
               for(int j=i+1;j<16;j++){
                    if((esZonaContigua)&&(!bloques.get(j).getNombre().equals(nomAct))){
                       esZonaContigua=false;
                       //System.out.println("Contigua posicion i: "+i+" y pos j: "+j);
                       //muestraSlots();
                       bloqueAntMayorAUno=true;
                       i=j;
                       j++;
                    }
                    if(!esZonaContigua){
                      if(j<16){
                        if(bloques.get(j).getNombre().equals(nomAct)){
                            //System.out.println("posicion i: "+i+" y pos j: "+j);  
                            //muestraSlots();
                            aux=bloques.get(j).getNombre();
                            bloques.get(j).setNombre(bloques.get(i).getNombre());
                            bloques.get(i).setNombre(aux);
                            i++;
                            bloqueAntMayorAUno=true;
                            contOperaciones++;
                        }
                      }
                  }
               }
            }
        }
        else{
            System.out.println("Msg!: No hay archivos almacenados");
        }
    //  bloques();
      //  mostrarDisco();
  }
    
  static void bloques(){
      vebloques="";
      for(int i=0;i<bloques.size();i++){
      vebloques+= bloques.get(i).ver();
              }
      for(int i=0;i<16;i++){
          System.out.println("->"+bloques.get(i).contenido());
      }
      int indicem2=0;
      for(int i=0;i<16;i++){
          int nb=bloques.get(i).getBytes();
          for(int j=0;j<4;j++){
              
              if(nb>0)
              disco[indicem2]=bloques.get(i).getNombre();
              
              indicem2++;
              nb--;
          }
      }
      System.out.println("");
  }
  
  static void mostrarDisco(){
      System.out.println("------------------------");
      System.out.println("Bloques: ");
      System.out.println(vebloques);
  }
}
