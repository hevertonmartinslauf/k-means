
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JTextArea;

public class Kmeans extends Thread{
    
    private double[][] matrizUxF;
    private final int qtFilmes;
    private final int qtUsuarios;
    private Cluster c1;
    private Cluster c2;
    private Cluster c3;
    private ArrayList ListaDoc;//lista geral de documentos
    private ArrayList ListaUsuarios;
    private ArrayList ListaFilmes;
    private JTextArea txtLog;
    private final JTextArea txtMatriz;

    public Kmeans(double[][] matrizUxF, int qtFilmes, int qtUsuarios, ArrayList lUsuarios, ArrayList lFilmes, JTextArea txtLog, JTextArea txtMatriz) {
        this.matrizUxF = matrizUxF;
        this.qtFilmes = qtFilmes;
        this.qtUsuarios = qtUsuarios;
        this.ListaUsuarios = lUsuarios;
        this.ListaFilmes = lFilmes;
        this.txtLog = txtLog;
        this.txtMatriz = txtMatriz;
    }

    @Override
    public void run() {
        rodandoKmeans();
    }
    
    public void rodandoKmeans(){
        
        while(true){
        
        ListaDoc = gerarListaDoc();
        
        //impListaDoc(ListaDoc);
        
        prepararClusters();//centroides(docs): doc 1(cluster1), doc 2(cluster2) e doc3 (cluster3) ou seja K=3
        
        do{
        
        clusterizar();  
        
        recalcularCentroide();
        
        atualizaListaDoc();
        
            try {
                Thread.sleep(4000);
            } catch (InterruptedException ex) {
                Logger.getLogger(Kmeans.class.getName()).log(Level.SEVERE, null, ex);
            }
        
        }while(!taClusterizado());
        
        System.out.println("ta clusterizado");
        
        verificarSugestao();
        
        }   
    }
    
    public void verificarSugestao(){
        
        for(int i=0;i<ListaUsuarios.size();i++){
            Usuario user = (Usuario)ListaUsuarios.get(i);
            if(user.getAtivo() == 1){
                System.out.println("selecionado usuario: "+user.getNome());
                enviarSugestao(user);
            }
            else{
                System.out.println("usuario "+user.getNome()+" inativo");
            }
        }
        
    }
    
    public void enviarSugestao(Usuario u){
        
        double termosU[] = null;
        int clusterU = -1;
        
        for(int i=0;i<ListaDoc.size();i++){//encontrar documento do usuario online passado no metodo
            Documento doc = (Documento)ListaDoc.get(i);
            
            if(doc.getIndice() == u.getId()){
                //System.out.println("documento "+u.getNome()+" encontrado");
                termosU = doc.getTermos();
                clusterU = doc.getClusterAtual();
                break;
            }
        }
        
        if(termosU != null){
            for(int j=0;j<ListaDoc.size();j++){//encontrar um documento do mesmo cluster e verificar se tem documento(filme) para ser sugerido
                Documento doc = (Documento)ListaDoc.get(j);
                    if((doc.getClusterAtual() == clusterU) && (doc.getIndice() != u.getId())){
                        //System.out.println("doc "+doc.getIndice()+" tem mesmo cluster do doc :" +u.getId()+" cluster: "+clusterU);
                        sugestao(u,termosU,doc.getTermos());
                    }
            }
        }
    }
    
    public void sugestao(Usuario u, double[] termosU, double[] termosOU){
        
        for(int i=0;i<qtFilmes;i++){
            if((termosOU[i]==3 || termosOU[i] == 4) && (termosU[i] == 0)){
                retiraFilmeSequencia(u, i);
                sendSugestao(u,i);
                break;
            }
        }
    }
    
    public void retiraFilmeSequencia(Usuario u,int i){
        
        Usuario uTemp = u;
        
        ArrayList seq = u.getSequencia();
        
        for(int j=0;j<seq.size();j++){
            if(i == (Integer)seq.get(j)){
                seq.remove(j);
                break;
            }
        }
        
        u.setSequencia(seq);
        
        for(int k=0;k<ListaUsuarios.size();k++){
            if(uTemp.equals((Usuario)ListaUsuarios.get(k))){
                ListaUsuarios.set(k, u);
            }
        }
    }
    
    public void sendSugestao(Usuario u, int indFilme){
        
        Socket s = null;
        String mensagem = null;
        Filme filme = (Filme)ListaFilmes.get(indFilme);
        
        addLog("**SUGESTAO FILME** "+filme.getNome()+" SUGERIDO A "+u.getNome());
        System.out.println("**SUGESTAO FILME** "+filme.getNome()+" SUGERIDO A "+u.getNome());
        
        int serverPort = u.getPorta();
        
        try {
            s = new Socket(u.getIp(), serverPort);
            
            DataOutputStream out = new DataOutputStream(s.getOutputStream());
            
            mensagem = "4;"+filme.getMat()+";"+filme.getNome()+";"+filme.getDistribuidor()+";"+filme.getAno();

            out.writeUTF(mensagem);
            

        } catch (IOException ex) {
            Logger.getLogger(Kmeans.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
    
    public void atualizaListaDoc(){
        
        for(int i=0;i<qtUsuarios;i++){
            double[] pontos = new double [qtFilmes];
            for(int j=0;j<qtFilmes;j++){
                pontos[j] = matrizUxF[i][j];
            }
            
            if(i==1){
                c1.getCentroide().setTermos(pontos);
            }
            else if(i==2){
                c2.getCentroide().setTermos(pontos);
            }
            else if(i==3){
                c3.getCentroide().setTermos(pontos);
            }
            else{
                atualizaDoc(i,pontos);
            }   
        }
    }
    
    public void atualizaDoc(int i, double pontos[]){
        
        for(int j=0;j<ListaDoc.size();j++){
            
            Documento doc = (Documento)ListaDoc.get(j);
            
            if(doc.getIndice() == i){
                doc.setTermos(pontos);
                ListaDoc.set(j, doc);
                break;
            }
        }
    }
    
    public void recalcularCentroide(){
        int qtDocC1 = qtItensCluster(1);//quantidade de documentos no cluster 1
        int qtDocC2 = qtItensCluster(2);//quantidade de documentos no cluster 2
        int qtDocC3 = qtItensCluster(3);//quantidade de documentos no cluster 3
        
        refinaCentroide(qtDocC1,c1);
        refinaCentroide(qtDocC2,c2);
        refinaCentroide(qtDocC3,c3);
    }
    
    public void refinaCentroide(int qtDoc, Cluster c){
        if(qtDoc != 0){
            double termos[] = c.getCentroide().getTermos();
            
            for(int i=0;i<qtFilmes;i++){
                termos[i] = (1/qtDoc)*termos[i];
            }
            
            c.getCentroide().setTermos(termos);
            
        }
    }
    
    public int qtItensCluster(int c){
        int qt=0;
        
        Documento doc;
        
        for (int i=0;i<ListaDoc.size();i++){
            doc=(Documento)ListaDoc.get(i);
            
            if(doc.getClusterAtual() == c){
                qt++;
            }
        }
        
        return qt;
    }
    
    public void addLog(String msg){
        Date data = new Date();
        txtLog.append(data.getHours() + ":" + data.getMinutes() + ":" + data.getSeconds() + " - " + msg + "\n");
    }
       
    public boolean taClusterizado(){//verificar se lista de documentos já está clusterizada
        
        atualizaListaDoc();
        
        Documento doc;
        
        for(int i=0;i<ListaDoc.size();i++){
            
            doc = (Documento)ListaDoc.get(i);
            
            System.out.println("doc "+doc.getIndice()+": ClusterAtual: "+doc.getClusterAtual()+" Cluster Ant: "+doc.getClusterAnt());
            
            if(doc.getClusterAtual() !=-1){
                if(doc.getClusterAtual() != doc.getClusterAnt()){
                    return false;
                }
            }
        }
        
        return true;
    }
    
    public void clusterizar(){
        for(int i=0;i<ListaDoc.size();i++){
            sim((Documento)ListaDoc.get(i));
        }  
        System.out.println("---------------------------------------");
        
    }
    
    public void sim(Documento doc){//Sim(Centroide,documento)
       
       double simC1, simC2, simC3;//resultado das similaridades com cada centroide(cluster)
        
       double calc1=0, calc2=0, calc3=0;//tratamento divisão por zero
       
       calc1 = (etapaB(c1.getCentroide())*etapaC(doc));//Tratar divisão por zero
       calc2 = (etapaB(c2.getCentroide())*etapaC(doc));
       calc3 = (etapaB(c3.getCentroide())*etapaC(doc));
       
       if(calc1 !=0){
           simC1 = etapaA(c1.getCentroide(),doc)/(etapaB(c1.getCentroide())*etapaC(doc));
       }
       
       else{
           simC1 = 0;
       }
       
       if(calc2 != 0){
           simC2 = etapaA(c2.getCentroide(),doc)/(etapaB(c2.getCentroide())*etapaC(doc));
       }
       
       else{
           simC2 = 0;
       }
       
       if(calc3 !=0){
           simC3 = etapaA(c3.getCentroide(),doc)/(etapaB(c3.getCentroide())*etapaC(doc));
       }
       
       else{
           simC3 = 0;
       }
      
       System.out.println("Doc["+doc.getIndice()+"] - sim com C1 = "+simC1);
       System.out.println("Doc["+doc.getIndice()+"] - sim com C2 = "+simC2);
       System.out.println("Doc["+doc.getIndice()+"] - sim com C3 = "+simC3);
      
       alocarNoCluster(simC1,simC2,simC3,doc);
       
    }
    
    public void alocarNoCluster(double simC1, double simC2, double simC3,Documento doc){
        double similar;
        
        if((simC3 > simC1) && (simC3 > simC2)){
            similar = simC3;
            if(doc.getClusterAtual() == 0){
                doc.setClusterAtual(3);
                System.out.println("Documento: "+doc.getIndice()+" alocado cluster: "+3);
            }
            else{
                doc.setClusterAnt(doc.getClusterAtual());
                doc.setClusterAtual(3);
                System.out.println("Documento: "+doc.getIndice()+" alocado cluster: "+3);
            }
        } 
        
        if((simC2 > simC1) && (simC2 > simC3)){
            similar = simC2;
            if(doc.getClusterAtual() == 0){
                doc.setClusterAtual(2);
                System.out.println("Documento: "+doc.getIndice()+" alocado cluster: "+2);
            }
            else{
                doc.setClusterAnt(doc.getClusterAtual());
                doc.setClusterAtual(2);
                System.out.println("Documento: "+doc.getIndice()+" alocado cluster: "+2);
            }
        }
        
        if((simC1 > simC2) && (simC1 > simC3)){
            similar = simC1;
            if(doc.getClusterAtual() == 0){
                doc.setClusterAtual(1);
                System.out.println("Documento: "+doc.getIndice()+" alocado cluster: "+1);
            }
            else{
                doc.setClusterAnt(doc.getClusterAtual());
                doc.setClusterAtual(1);
                System.out.println("Documento: "+doc.getIndice()+" alocado cluster: "+1);
            }
        }
   
    }
    
    public double etapaA(Documento centroide,Documento doc){
        double etapaA = 0;
        
        double[] vetCentroide = centroide.getTermos();
        double[] vetDocumento = doc.getTermos();
        
        for(int i=0;i<qtFilmes;i++){
            etapaA = etapaA+(vetCentroide[i]*vetDocumento[i]);
        }
        
        return etapaA;
    }
    
    public double etapaB(Documento centroide){//etapa B poderia ser mesmo metodo etapaC. Fiz dois para melhor visualização
        double etapaB = 0;
        
        double[] vetCentroide = centroide.getTermos();
        
        for(int i=0;i<qtFilmes;i++){
            etapaB = etapaB + Math.pow(vetCentroide[i], 2);
        }
        
        return Math.sqrt(etapaB);
    }
    
    public double etapaC(Documento doc){
        double etapaC=0;
        
        double[] vetDoc = doc.getTermos();
        
        for(int i=0;i<qtFilmes;i++){
            etapaC = etapaC + Math.pow(vetDoc[i], 2);
        }
        
        return Math.sqrt(etapaC);
              
    }
    
    public void prepararClusters(){
        c1 = new Cluster(1,(Documento)ListaDoc.get(1));//criando cluster 1 e setando centroide 1
        
        c2 = new Cluster(2,(Documento)ListaDoc.get(2));//criando cluster 2 e setando centroide 2
        
        c3 = new Cluster(3,(Documento)ListaDoc.get(3));//criando cluster 3 e setando centroide 3
        
        ListaDoc.remove(c1.getCentroide());//retirar centroides da lista de objetos que serão calculados na similariedade
                                           //para evitar de fazer calculos de centroide com centroide
        ListaDoc.remove(c2.getCentroide());
        
        ListaDoc.remove(c3.getCentroide());
        
    }
    
    public ArrayList<Documento> gerarListaDoc(){//Gerar Documentos através da matriz principal(cada linha com seus pontos = 1 documento
        
        ArrayList listaDoc = new ArrayList<Documento>();
        
        for(int i=0;i<qtUsuarios;i++){
            double[] pontos = new double [qtFilmes];
            for(int j=0;j<qtFilmes;j++){
                pontos[j] = matrizUxF[i][j];
            }
            
            Documento doc = new Documento(i, pontos);
            listaDoc.add(doc);
            
        }
        return listaDoc;
    }
    
    public void impListaDoc(ArrayList Doc){
        Documento doc;
        double[] pontos;
        
        for(int i=0;i<Doc.size();i++){
            doc = (Documento)Doc.get(i);
            pontos = doc.getTermos();
            
            for(int j=0;j<qtFilmes;j++){
                System.out.print(" ["+pontos[j]+"] ");
            }
            System.out.println();
            System.out.println();
        }
    }
    
}
