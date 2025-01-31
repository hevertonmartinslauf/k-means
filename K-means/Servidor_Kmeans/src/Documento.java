
public class Documento {
    
    private int indice;
    private double [] termos;
    private int clusterAnt;
    private int clusterAtual;

    public int getIndice() {
        return indice;
    }

    public void setIndice(int indice) {
        this.indice = indice;
    }

    public double[] getTermos() {
        return termos;
    }

    public void setTermos(double[] termos) {
        this.termos = termos;
    }

    public Documento(int indice, double[] termos) {
        this.indice = indice;
        this.termos = termos;
        this.clusterAnt = 0;
        this.clusterAtual = -1;
    }

    public int getClusterAnt() {
        return clusterAnt;
    }

    public void setClusterAnt(int clusterAnt) {
        this.clusterAnt = clusterAnt;
    }

    public int getClusterAtual() {
        return clusterAtual;
    }

    public void setClusterAtual(int clusterAtual) {
        this.clusterAtual = clusterAtual;
    }

}


