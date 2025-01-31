
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JTextArea;

public class RecSugestao extends Thread{
    
    private JLabel lblNome;
    private JLabel lblAno;
    private JLabel lblDist;
    private JButton btnBomS;
    private JButton btnNuncaS;
    private JButton btnOtimoS;
    private JButton btnRuimS;
    private int porta;

    public RecSugestao(JLabel nome, JLabel ano, JLabel dist,int porta, JButton bom, JButton ruim, JButton otimo, JButton nunca) {
        this.lblNome = nome;
        this.lblAno = ano;
        this.lblDist = dist;
        this.porta = porta;
        this.btnBomS = bom;
        this.btnNuncaS = nunca;
        this.btnOtimoS = otimo;
        this.btnRuimS = ruim;
    }

    @Override
    public void run() {
        ouvirPorta();
    }
    
    public void ouvirPorta(){
        String sep[], cabecalho;
        
        DataInputStream in;
        
        try {
            
            ServerSocket ouvindoSocket = new ServerSocket(porta);
            
            while(true){
                Socket cliSocket = ouvindoSocket.accept();
                in = new DataInputStream(cliSocket.getInputStream());
               
                String data = in.readUTF();
                
                sep = data.split(";");
           
                cabecalho = sep[0];
           
                if("4".equals(cabecalho)){
                     lblNome.setText(sep[2]);
                     lblDist.setText(sep[3]);
                     lblAno.setText(sep[4]);
                     btnBomS.setEnabled(true);
                     btnNuncaS.setEnabled(true);
                     btnOtimoS.setEnabled(true);
                     btnRuimS.setEnabled(true);  
                }
                
                try {
                    Thread.sleep(5000);
                } catch (InterruptedException ex) {
                    Logger.getLogger(RecSugestao.class.getName()).log(Level.SEVERE, null, ex);
                }
           
           }
            
        } catch (IOException ex) {
            Logger.getLogger(RecSugestao.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
