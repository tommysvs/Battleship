import java.util.ArrayList;

public class Player {
    ArrayList<String> usuarios = new ArrayList<String>();
    ArrayList<String> contrasenas = new ArrayList<String>();
    ArrayList<Integer> puntajes = new ArrayList<Integer>();
    ArrayList<String> ultimos_juegos = new ArrayList<String>();
    
    String usuario_loggeado = "";
    Boolean logged;

    Player() { }

    Player(String _usuario_loggeado) { 
        usuario_loggeado = _usuario_loggeado;
    }

    public void ver_datos() {
        int index = index_usuario(usuario_loggeado);

        System.out.println(usuario_loggeado + " -------------- " + puntajes.get(index));
    }

    public void agregar_usuario(String usuario, String contrasena) {
        usuarios.add(usuario);
        contrasenas.add(contrasena);
        puntajes.add(0);
    }

    public void modificar_usuario(String usuario, String nuevo, int op) {
        int index = index_usuario(usuario);

        if(op == 1) {
            usuarios.set(index, nuevo);
            set_usuario_logged(nuevo);
        }
        else if(op == 2)
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

    public void show_juegos() {
        int counter = 0;

        if(logged && !usuario_loggeado.equals(null)) {
            for(int i = 0; i < ultimos_juegos.size(); i++) {
                counter++;
                System.out.println(counter + ". " + ultimos_juegos.get(i)); 
            }
        } else {
            System.out.println("No se pudo imprimir la descripcion de los ultimos 10 juegos.");
        }
    }

    public void show_ranking() {
        int counter = 0;

        for(int i = 0; i < usuarios.size(); i++) {
            counter++;
            System.out.println(counter + ". " + usuarios.get(i) + " -------------------- " + puntajes.get(i)); 
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

    public void set_usuario_logged(String usuario) {
        usuario_loggeado = usuario;
    }

    public String get_usuario() {
        return usuario_loggeado;
    }

    public void set_logged_in() {
        logged = true;
    }

    public void set_logged_off() {
        logged = false;
    }

    public Boolean get_logged() {
        return logged;
    }

    public void set_puntaje(int index, int puntaje) {
        puntajes.add(index, puntaje);
    }
}
