
package practicadirectorio;


public class Usuario {
    private String usuario;
    private String pasword;
    private int tipo;
    public Usuario(String usuario, String pasword, int tipo){
        this.pasword=pasword;
        this.usuario=usuario;
        this.tipo=tipo;
    }

    public String getUsuario() {
        return usuario;
    }

   /* public void setUsuario(String usuario) {
        this.usuario = usuario;
    }*/

    public String getPasword() {
        return pasword;
    }

    public void setPasword(String pasword) {
        this.pasword = pasword;
    }

    public int getTipo() {
        return tipo;
    }

    public void setTipo(int tipo) {
        this.tipo = tipo;
    }
    
    
}
