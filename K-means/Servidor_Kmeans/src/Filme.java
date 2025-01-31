
public class Filme {
    private int mat;
    private String nome;
    private String ano;
    private String distribuidor;

    public Filme(int matricula, String nome, String distribuidor, String ano) {
        this.mat = matricula;
        this.nome = nome;
        this.ano = ano;
        this.distribuidor = distribuidor;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getAno() {
        return ano;
    }

    public void setAno(String ano) {
        this.ano = ano;
    }

    public String getDistribuidor() {
        return distribuidor;
    }

    public void setDistribuidor(String distribuidor) {
        this.distribuidor = distribuidor;
    }

    public int getMat() {
        return mat;
    }

    public void setMat(int matricula) {
        this.mat = matricula;
    }

    @Override
    public String toString() {
        return nome;
    }
    
    
}
