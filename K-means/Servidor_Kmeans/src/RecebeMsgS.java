
import java.io.ByteArrayOutputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JTextArea;


public class RecebeMsgS extends Thread {
    
    private final JTextArea txtLog;
    private final JTextArea txtMatriz;
    private final JTextArea txtUsuarios;
    private final ArrayList listaUsuarios;
    private final ArrayList listaFilmes;
    private int contUser=0;
    private double[][] matrizUxF;
    private int qtUsuarios;

    public RecebeMsgS(JTextArea txtLog, ArrayList lUsuarios, ArrayList lFilmes, int contUser, double[][] matrizUxF, JTextArea txtMatriz, int QtUsuarios,JTextArea txtUsuarios) {
        this.txtLog = txtLog;
        this.listaFilmes = lFilmes;
        this.listaUsuarios = lUsuarios;
        this.contUser = contUser;
        this.matrizUxF = matrizUxF;
        this.txtMatriz = txtMatriz;
        this.qtUsuarios = QtUsuarios;
        this.txtUsuarios = txtUsuarios;
    }

    @Override
    public void run() {
        ouvirPorta();
    }
    
    public void ouvirPorta(){
        
        String sep[], cabecalho, usuario,resposta;
        Filme fRand;
        int filme,usr,avalia;
        
        int serverPort = 7896;
        DataInputStream in;
        DataOutputStream out;
        
        try {
            ServerSocket ouvindoSocket = new ServerSocket(serverPort);
            while(true){
                Socket cliSocket = ouvindoSocket.accept();
                in = new DataInputStream(cliSocket.getInputStream());
                out = new DataOutputStream(cliSocket.getOutputStream());
                String data = in.readUTF();
                   
                sep= data.split(";");
                cabecalho = sep[0];
                usuario = sep[1];
                
                switch(cabecalho){
                    case"1"://usuários solicitando login
                        ArrayList sequencia = new ArrayList<Integer>();
                        embaralharSequencia(sequencia);
                        Usuario usrn = new Usuario(usuario,cliSocket.getInetAddress(),cliSocket.getPort(),sequencia,contUser); 
                        listaUsuarios.add(usrn);
                        addLog("usuario: "+usuario+" logado"); 
                        addUsuarios();
                        fRand = randomFilme(usrn);
                        usrn.getSequencia().remove(0);
                        resposta = fRand.getMat()+";"+fRand.getNome()+";"+fRand.getDistribuidor()+";"+fRand.getAno()+";"+contUser+";"+cliSocket.getPort();
                        out.writeUTF(resposta);
                        contUser++;
                    break;
                        
                    case"3"://avaliação de fil;
                        filme = Integer.parseInt(sep[1]);
                        usr = Integer.parseInt(sep[3]);
                        avalia = Integer.parseInt(sep[2]);
                        Usuario user;
                        
                        user = (Usuario)listaUsuarios.get(usr);
                        
                        if(user.getSequencia().isEmpty()){//se usuário ja avaliou todos os filmes da lista
                            user.setAtivo(0);//setando usuario inativo
                            listaUsuarios.set(usr,user);//setando usuario inativo
                            //System.out.println("usuario: "+listaUsuarios.get(usr).toString()+" inativo");
                            matrizUxF[usr][filme] = avalia;//inserindo na matriz
                            addMatriz();
                            addLog(listaUsuarios.get(usr).toString()+" avaliou filme "+listaFilmes.get(filme).toString()+" como: "+opiniao(avalia));
                            addLog(listaUsuarios.get(usr).toString()+" avaliou todos os filmes");
                            resposta = " ; ; ; ";  
                            out.writeUTF(resposta);
                        }
                        else
                        {
                            matrizUxF[usr][filme] = avalia;//inserindo na matriz
                            addMatriz();
                            addLog(listaUsuarios.get(usr).toString()+" avaliou filme "+listaFilmes.get(filme).toString()+" como: "+opiniao(avalia));
                            out = new DataOutputStream(cliSocket.getOutputStream());    
                            fRand = randomFilme(user);
                            user.getSequencia().remove(0);
                            resposta = fRand.getMat()+";"+fRand.getNome()+";"+fRand.getDistribuidor()+";"+fRand.getAno();
                            out.writeUTF(resposta);   
                        }
                    break;
                        
                    case"4":
                        //System.out.println("chegou resposta sugestao de filme");
                        
                        filme = convNomeIdFilme(sep[1]);
                        System.out.println("THREAD SERVIDOR chegou mat filme: "+sep[1]+" "+filme);
                        usr = Integer.parseInt(sep[3]);
                        avalia = Integer.parseInt(sep[2]);
                        
                        matrizUxF[usr][filme] = avalia;//inserindo na matriz
                        addMatriz();
                        addLog(listaUsuarios.get(usr).toString()+" avaliou SUGESTAO filme "+listaFilmes.get(filme).toString()+" como: "+opiniao(avalia));
                        resposta = " ; ; ; ";  
                        out.writeUTF(resposta);
                        
                    break;    
                }
            }
            
        } catch (IOException ex) {
            Logger.getLogger(RecebeMsgS.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
    
    public int convNomeIdFilme(String nome){
        int indice = 0;
        Filme filme;
        
        
        for(int i=0;i<listaFilmes.size();i++){
            filme = (Filme)listaFilmes.get(i);
            
            if(filme.getNome().equals(nome)){
                indice = i;
                break;
            }
        }
        
        return indice;
    }
    
    public String opiniao(int indice){
        
        String resp = null;
        
        switch(indice){
            case 1:
                resp = "Nunca Assisti";
            break;
            case 2:
                resp= "Considero Ruim";
            break;
            case 3:
                resp = "Considero Bom";
            break;
            case 4:
                resp = "Considero Ótimo";
            break;    
        }
        
        return resp;
    }
    
    public Filme randomFilme(Usuario user){
         
         Filme filmeE;
         
         filmeE = (Filme)listaFilmes.get((Integer)user.getSequencia().get(0));
         
         return filmeE;
         
     }
    
    public void embaralharSequencia(ArrayList sequencia){
        for(int i=0;i<listaFilmes.size();i++){
            sequencia.add(i);
        }
        
        Collections.shuffle(sequencia);
        
    }
    
     public void addLog(String msg){
        Date data = new Date();
        txtLog.append(data.getHours() + ":" + data.getMinutes() + ":" + data.getSeconds() + " - " + msg + "\n");
    }
     
     public void addMatriz(){//adiciona impressão da matriz no texteArea matriz
         PrintStream original = System.out;
         System.setOut(new PrintStream(new OutputStream() {
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            @Override
            public void write(int b) throws IOException {
                baos.write(b);
            }
            @Override
            public void flush() throws IOException {
                txtMatriz.append(baos.toString());
                baos.reset();
            }
            @Override
            public void close() throws IOException {
                txtMatriz.append(baos.toString());
                baos.reset();
            }
        }, true)); 
         
         txtMatriz.setText("");
         
         impMatriz();
         
         System.setOut(original);
     }
     
     public void impMatriz(){
         System.out.println("     1 = NUNCA / 2=RUIM / 3 = BOM / 4 = OTIMO\n");
                
        System.out.print("  Filme:   ");
        
        for(int i=0;i<listaFilmes.size();i++){
            System.out.print(i+"   ");
        }
        System.out.println();
        for(int i=0;i<qtUsuarios;i++){
            System.out.print("user["+i+"]:   ");
            for(int j=0;j<listaFilmes.size();j++){
                System.out.print((int)matrizUxF[i][j]+ "   ");
            }
            System.out.println();
        }
     }
     
     public void addUsuarios(){
         PrintStream original = System.out;
         System.setOut(new PrintStream(new OutputStream() {
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            @Override
            public void write(int b) throws IOException {
                baos.write(b);
            }
            @Override
            
            public void flush() throws IOException {
                txtUsuarios.append(baos.toString());
                baos.reset();
            }
            @Override
            public void close() throws IOException {
                txtUsuarios.append(baos.toString());
                baos.reset();
            }
        }, true)); 
         
         txtUsuarios.setText("");
         
         for(int i=0;i<listaUsuarios.size();i++){
                System.out.println("user["+i+"]: "+listaUsuarios.get(i).toString());
            }
         
         System.setOut(original);
     }
     
}
