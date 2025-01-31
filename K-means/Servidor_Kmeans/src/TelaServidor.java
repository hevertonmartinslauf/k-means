
import java.io.ByteArrayOutputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.Date;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

public class TelaServidor extends javax.swing.JFrame {
    
    ArrayList listaFilmes;//lista de filmes que será carregada do arquivo
    ArrayList listaUsuarios;//lista de usuarios logados
    RecebeMsgS ouvindo;//thread para ficar ouvindo as mensagens
    Kmeans kmeans;//thread para ficar verificando se tem um perfil parecido com algum usuario online e enviar sugestão
    int qtFilmes;//quantidade filmes
    int qtUsuarios=10;//quantidade de usuarios que poderão logar no sistema
    double[][] matrizUxF;//matriz usuarios/filmes:(usuarios-> linhas - filmes -> colunas)
    
    int contUser=0;

    public TelaServidor() {
        initComponents();
        btnAtivServ.setEnabled(false);
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        btnCarregFilmes = new javax.swing.JButton();
        btnAtivServ = new javax.swing.JButton();
        jPanel1 = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        txtLog = new javax.swing.JTextArea();
        jPanel3 = new javax.swing.JPanel();
        jScrollPane3 = new javax.swing.JScrollPane();
        txtUsuarios = new javax.swing.JTextArea();
        jPanel2 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        txtMatriz = new javax.swing.JTextArea();
        jPanel4 = new javax.swing.JPanel();
        jScrollPane4 = new javax.swing.JScrollPane();
        txtFilmes = new javax.swing.JTextArea();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel1.setText("SERVIDOR");

        btnCarregFilmes.setText("Carregar Filmes");
        btnCarregFilmes.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCarregFilmesActionPerformed(evt);
            }
        });

        btnAtivServ.setText("Ativar Servidor");
        btnAtivServ.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAtivServActionPerformed(evt);
            }
        });

        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder("Logs"));

        txtLog.setEditable(false);
        txtLog.setColumns(20);
        txtLog.setFont(new java.awt.Font("Monospaced", 1, 12)); // NOI18N
        txtLog.setRows(5);
        jScrollPane2.setViewportView(txtLog);

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 596, javax.swing.GroupLayout.PREFERRED_SIZE)
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 635, Short.MAX_VALUE)
        );

        jPanel3.setBorder(javax.swing.BorderFactory.createTitledBorder("Usuarios"));

        txtUsuarios.setEditable(false);
        txtUsuarios.setColumns(20);
        txtUsuarios.setFont(new java.awt.Font("Monospaced", 1, 12)); // NOI18N
        txtUsuarios.setRows(5);
        jScrollPane3.setViewportView(txtUsuarios);

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane3, javax.swing.GroupLayout.DEFAULT_SIZE, 325, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 263, javax.swing.GroupLayout.PREFERRED_SIZE)
        );

        jPanel2.setBorder(javax.swing.BorderFactory.createTitledBorder("Matriz"));

        txtMatriz.setEditable(false);
        txtMatriz.setColumns(20);
        txtMatriz.setFont(new java.awt.Font("Monospaced", 1, 12)); // NOI18N
        txtMatriz.setRows(5);
        jScrollPane1.setViewportView(txtMatriz);

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 354, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 3, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 560, Short.MAX_VALUE)
        );

        jPanel4.setBorder(javax.swing.BorderFactory.createTitledBorder("Filmes"));

        txtFilmes.setEditable(false);
        txtFilmes.setColumns(20);
        txtFilmes.setFont(new java.awt.Font("Monospaced", 1, 12)); // NOI18N
        txtFilmes.setRows(5);
        jScrollPane4.setViewportView(txtFilmes);

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane4)
                .addContainerGap())
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane4, javax.swing.GroupLayout.DEFAULT_SIZE, 343, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(jLabel1)
                        .addGap(138, 138, 138))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(57, 57, 57)
                        .addComponent(btnCarregFilmes)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(btnAtivServ))
                    .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(54, 54, 54))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                        .addGroup(layout.createSequentialGroup()
                            .addContainerGap()
                            .addComponent(jLabel1)
                            .addGap(18, 18, 18)
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(btnCarregFilmes)
                                .addComponent(btnAtivServ))
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                            .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnCarregFilmesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCarregFilmesActionPerformed
        
        btnAtivServ.setEnabled(true);
        btnCarregFilmes.setEnabled(false);
        
        try {
            listaFilmes = new ArrayList<Filme>();
            carregarFilmes(listaFilmes);
            qtFilmes = listaFilmes.size();
            matrizUxF = new double[qtUsuarios][qtFilmes];//MATRIZ USUARIOS/FILMES
            inicializaMatriz(matrizUxF);
            
        } catch (FileNotFoundException ex) {
            Logger.getLogger(TelaServidor.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_btnCarregFilmesActionPerformed

    private void btnAtivServActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAtivServActionPerformed
       
        listaUsuarios = new ArrayList<Usuario>();
       
       addLog("Servidor ativado");
       addMatriz();
       addFilmes();
       
       btnAtivServ.setEnabled(false);
       
       ouvindo = new RecebeMsgS(txtLog,listaUsuarios,listaFilmes,contUser,matrizUxF,txtMatriz,qtUsuarios,txtUsuarios);
       ouvindo.start();
       
       kmeans = new Kmeans(matrizUxF,qtUsuarios,qtFilmes,listaUsuarios,listaFilmes,txtLog,txtMatriz);
       kmeans.start();
       
       
    }//GEN-LAST:event_btnAtivServActionPerformed

    public void inicializaMatriz(double mat[][]){
        for(int i=0;i<qtUsuarios;i++){
            for(int j=0;j<qtFilmes;j++){
                mat[i][j] = 0;
            }
        }
    }
    
    public void carregarFilmes(ArrayList listaFilmes) throws FileNotFoundException{
        String linha, sep[];
        Filme filme;
        
        FileInputStream fil = new FileInputStream("filmes.txt");
        Scanner scan = new Scanner(fil);
        
        while(scan.hasNext()){
            linha = scan.nextLine();
            
            sep = linha.split(";");
            
            filme = new Filme(Integer.parseInt(sep[0]), sep[1], sep[2], sep[3]);
            
            listaFilmes.add(filme);
        }
        
        addLog("Lista de Filmes Carregada com Sucesso");
    }
    
    public void addLog(String msg){
        Date data = new Date();
        txtLog.append(data.getHours() + ":" + data.getMinutes() + ":" + data.getSeconds() + " - " + msg + "\n");
    }
    
    public void addFilmes(){
         PrintStream original = System.out;
         System.setOut(new PrintStream(new OutputStream() {
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            @Override
            public void write(int b) throws IOException {
                baos.write(b);
            }
            @Override
            public void flush() throws IOException {
                txtFilmes.append(baos.toString());
                baos.reset();
            }
            @Override
            public void close() throws IOException {
                txtFilmes.append(baos.toString());
                baos.reset();
            }
        }, true)); 
         
         txtFilmes.setText("");
         
         for(int i=0;i<listaFilmes.size();i++){
             System.out.println("filme["+i+"]: "+listaFilmes.get(i).toString());
         }
         
         System.setOut(original);
     }
    
    public void addMatriz(){
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
    
    public static void main(String args[]) {
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Windows".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(TelaServidor.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(TelaServidor.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(TelaServidor.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(TelaServidor.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new TelaServidor().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnAtivServ;
    private javax.swing.JButton btnCarregFilmes;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JTextArea txtFilmes;
    private javax.swing.JTextArea txtLog;
    private javax.swing.JTextArea txtMatriz;
    private javax.swing.JTextArea txtUsuarios;
    // End of variables declaration//GEN-END:variables
}
