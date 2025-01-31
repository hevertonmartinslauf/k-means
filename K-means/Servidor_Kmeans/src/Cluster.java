
public class Cluster {
    
    private int indice;
    private Documento centroide;

    public Cluster(int indice, Documento centroide) {
        this.indice = indice;
        this.centroide = centroide;
    }
   
    public Documento getCentroide() {
        return centroide;
    }

    public void setCentroide(Documento centroide) {
        this.centroide = centroide;
    }

    public int getIndice() {
        return indice;
    }

    public void setIndice(int indice) {
        this.indice = indice;
    }
    
}
