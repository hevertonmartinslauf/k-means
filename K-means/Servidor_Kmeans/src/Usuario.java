
import java.net.InetAddress;
import java.util.ArrayList;

public class Usuario {
    private int id;
    private String nome;
    private InetAddress ip;
    private int porta;
    private ArrayList sequencia;
    private int ativo;//0 inativo e 1 ativo

    public Usuario(String nome, InetAddress ip, int porta, ArrayList sequencia, int id) {
        this.nome = nome;
        this.ip = ip;
        this.porta = porta;
        this.sequencia = sequencia;
        this.ativo = 1;
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public InetAddress getIp() {
        return ip;
    }

    public void setIp(InetAddress ip) {
        this.ip = ip;
    }

    public int getPorta() {
        return porta;
    }

    public void setPorta(int porta) {
        this.porta = porta;
    }

    @Override
    public String toString() {
        return nome;
    }

    public ArrayList getSequencia() {
        return sequencia;
    }

    public void setSequencia(ArrayList sequencia) {
        this.sequencia = sequencia;
    }

    public int getAtivo() {
        return ativo;
    }

    public void setAtivo(int ativo) {
        this.ativo = ativo;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
    
    
    
}
