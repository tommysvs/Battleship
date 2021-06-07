import java.util.ArrayList;

public class Player {
    ArrayList<String> usuarios = new ArrayList<String>();
    ArrayList<String> contrasenas = new ArrayList<String>();
    ArrayList<Integer> puntajes = new ArrayList<Integer>();
    ArrayList<String> reportes = new ArrayList<>();
    
    String usuario_loggeado = "";

    public void ver_datos() {

    }

    public void agregar_usuario(String usuario, String contrasena) {
        usuarios.add(usuario);
        contrasenas.add(contrasena);
        puntajes.add(0);
    }

    public void modificar_usuario(String usuario, String nuevo, int op) {
        int index = index_usuario(usuario);

        if(op == 1)
            usuarios.set(index, nuevo);
        else
            contrasenas.set(index, nuevo);
    }

    public boolean eliminar_usuario(String usuario) {
        int index = index_usuario(usuario);

        if(usuarios.size() > 1) {
            usuarios.remove(index);
            contrasenas.remove(index);
            puntajes.remove(index);

            return true;
        }else {
            return false;
        }
    }

    public boolean usuario_existe(String usuario) {
        for(String x : usuarios) {
            if(x.equals(usuario)) {
                return true;
            }
        }

        return false;
    }

    public int index_usuario(String usuario) {
        return usuarios.indexOf(usuario);
    }

    public int index_contrasena(String contrasena) {
        return contrasenas.indexOf(contrasena);
    }

    public void set_usuario(String usuario) {
        usuario_loggeado = usuario;
    }

    public String get_usuario() {
        return usuario_loggeado;
    }
}
