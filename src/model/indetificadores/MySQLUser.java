package model.indetificadores;

public class MySQLUser {
    private String usuario = "root";
    private String senha = "root";

    public MySQLUser(String usuario, String senha) {
        if(!usuario.isEmpty())
            this.usuario = usuario;
        if(!senha.isEmpty())
            this.senha = senha;
    }

    public String getUsuario() {
        return usuario;
    }

    public String getSenha() {
        return senha;
    }

    @Override
    public String toString() {
        return "MySQLUser{" +
                " usuario='" + usuario + '\'' +
                ", senha='" + senha + '\'' +
                '}';
    }
}
