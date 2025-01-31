
import java.awt.Color;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.EOFException;
import java.io.IOException;
import java.net.InetAddress;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

public class TelaCliente extends javax.swing.JFrame {
    
    private int porta;
    String cod_usuario;
    String mat_filme;
    String server;

    public TelaCliente() {
        initComponents();
        btnBom.setEnabled(false);
        btnNunca.setEnabled(false);
        btnOtimo.setEnabled(false);
        btnRuim.setEnabled(false);
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        txtNome = new javax.swing.JTextField();
        btnLogar = new javax.swing.JButton();
        jPanel2 = new javax.swing.JPanel();
        btnRuim = new javax.swing.JButton();
        btnNunca = new javax.swing.JButton();
        lblDist = new javax.swing.JLabel();
        lblano = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        btnOtimo = new javax.swing.JButton();
        btnBom = new javax.swing.JButton();
        lblNomeFilme = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        lblAvaliacao = new javax.swing.JLabel();
        lblSugestao = new javax.swing.JLabel();
        jPanel3 = new javax.swing.JPanel();
        btnRuimS = new javax.swing.JButton();
        btnNuncaS = new javax.swing.JButton();
        lblDistS = new javax.swing.JLabel();
        lblanoS = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        btnOtimoS = new javax.swing.JButton();
        btnBomS = new javax.swing.JButton();
        lblNomeFilmeS = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder("Informações do Usuário"));

        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel1.setText("Usuário:");

        btnLogar.setText("LOGAR");
        btnLogar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnLogarActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btnLogar)
                    .addComponent(txtNome, javax.swing.GroupLayout.PREFERRED_SIZE, 124, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(33, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtNome, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel1))
                .addGap(18, 18, 18)
                .addComponent(btnLogar)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel2.setBorder(javax.swing.BorderFactory.createTitledBorder("Informação do Filme"));

        btnRuim.setText("Considero Ruim");
        btnRuim.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnRuimActionPerformed(evt);
            }
        });

        btnNunca.setText("Nunca Assisti");
        btnNunca.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnNuncaActionPerformed(evt);
            }
        });

        jLabel8.setText("Distribuidor: ");

        jLabel6.setText("ano:");

        btnOtimo.setText("Considero Ótimo");
        btnOtimo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnOtimoActionPerformed(evt);
            }
        });

        btnBom.setText("Considero Bom");
        btnBom.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnBomActionPerformed(evt);
            }
        });

        jLabel4.setText("nome:");

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(jLabel8)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(lblDist))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel4)
                            .addComponent(jLabel6))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(lblano)
                            .addComponent(lblNomeFilme)))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(btnNunca, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(btnBom))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(btnOtimo, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(btnRuim, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
                .addGap(0, 40, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(lblNomeFilme))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel6)
                    .addComponent(lblano))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel8)
                    .addComponent(lblDist))
                .addGap(18, 18, 18)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnNunca, javax.swing.GroupLayout.PREFERRED_SIZE, 57, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnRuim, javax.swing.GroupLayout.PREFERRED_SIZE, 57, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnBom, javax.swing.GroupLayout.PREFERRED_SIZE, 57, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnOtimo, javax.swing.GroupLayout.PREFERRED_SIZE, 57, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(19, Short.MAX_VALUE))
        );

        lblAvaliacao.setFont(new java.awt.Font("Tahoma", 1, 13)); // NOI18N
        lblAvaliacao.setText("Avaliação de Filmes: Em andamento");

        lblSugestao.setFont(new java.awt.Font("Tahoma", 1, 13)); // NOI18N
        lblSugestao.setText("Sugestão de Filme");

        jPanel3.setBorder(javax.swing.BorderFactory.createTitledBorder("Informação do Filme"));

        btnRuimS.setText("Considero Ruim");
        btnRuimS.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnRuimSActionPerformed(evt);
            }
        });

        btnNuncaS.setText("Nunca Assisti");
        btnNuncaS.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnNuncaSActionPerformed(evt);
            }
        });

        jLabel9.setText("Distribuidor: ");

        jLabel7.setText("ano:");

        btnOtimoS.setText("Considero Ótimo");
        btnOtimoS.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnOtimoSActionPerformed(evt);
            }
        });

        btnBomS.setText("Considero Bom");
        btnBomS.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnBomSActionPerformed(evt);
            }
        });

        jLabel5.setText("nome:");

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(jLabel9)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(lblDistS))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel5)
                            .addComponent(jLabel7))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(lblanoS)
                            .addComponent(lblNomeFilmeS)))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(btnNuncaS, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(btnBomS))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(btnOtimoS, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(btnRuimS, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
                .addGap(0, 37, Short.MAX_VALUE))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel5)
                    .addComponent(lblNomeFilmeS))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel7)
                    .addComponent(lblanoS))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel9)
                    .addComponent(lblDistS))
                .addGap(18, 18, 18)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnNuncaS, javax.swing.GroupLayout.PREFERRED_SIZE, 57, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnRuimS, javax.swing.GroupLayout.PREFERRED_SIZE, 57, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnBomS, javax.swing.GroupLayout.PREFERRED_SIZE, 57, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnOtimoS, javax.swing.GroupLayout.PREFERRED_SIZE, 57, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 23, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(10, 10, 10)
                        .addComponent(lblAvaliacao)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(lblSugestao)
                        .addGap(110, 110, 110))))
            .addGroup(layout.createSequentialGroup()
                .addGap(192, 192, 192)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(4, 4, 4)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblAvaliacao)
                    .addComponent(lblSugestao))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnLogarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnLogarActionPerformed
        btnLogar.setEnabled(false);
        txtNome.setEnabled(false);
        btnBom.setEnabled(true);
        btnNunca.setEnabled(true);
        btnOtimo.setEnabled(true);
        btnRuim.setEnabled(true);
        try {
            server = JOptionPane.showInputDialog(this, "Insira o nome do servidor: ", InetAddress.getLocalHost().getHostAddress());
            logarServidor("1;"+txtNome.getText());
            RecSugestao ouvindoS = new RecSugestao(lblNomeFilmeS,lblanoS,lblDistS,porta,btnBomS,btnNuncaS,btnOtimoS,btnRuim);
            ouvindoS.start();
        } catch (UnknownHostException ex) {
            Logger.getLogger(TelaCliente.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_btnLogarActionPerformed

    private void btnNuncaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnNuncaActionPerformed
        String mensagem = "3;"+mat_filme+";1;"+cod_usuario;
        try {
            //3 indicador da resposta
            //mat filme matricula do filme
            //1 nunca
            //codigo do usuario
            respFilme(mensagem);
        } catch (UnknownHostException ex) {
            Logger.getLogger(TelaCliente.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_btnNuncaActionPerformed

    private void btnBomActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBomActionPerformed
        String mensagem = "3;"+mat_filme+";3;"+cod_usuario;
        try {
            //3 indicador da resposta
            //mat filme matricula do filme
            //3 bom
            //codigo do usuario
            respFilme(mensagem);
        } catch (UnknownHostException ex) {
            Logger.getLogger(TelaCliente.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_btnBomActionPerformed

    private void btnOtimoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnOtimoActionPerformed
        String mensagem = "3;"+mat_filme+";4;"+cod_usuario;
        try {
            //3 indicador da resposta
            //mat filme matricula do filme
            //4 otimo
            //codigo do usuario
            respFilme(mensagem);
        } catch (UnknownHostException ex) {
            Logger.getLogger(TelaCliente.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_btnOtimoActionPerformed

    private void btnRuimActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnRuimActionPerformed
        String mensagem = "3;"+mat_filme+";2;"+cod_usuario;
        try {
            //3 indicador da resposta
            //mat filme matricula do filme
            //2 ruim
            //codigo do usuario
            respFilme(mensagem);
        } catch (UnknownHostException ex) {
            Logger.getLogger(TelaCliente.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_btnRuimActionPerformed

    private void btnRuimSActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnRuimSActionPerformed
        String mensagem = "4;"+lblNomeFilmeS.getText()+";2;"+cod_usuario;
        respSugestao(mensagem);
    }//GEN-LAST:event_btnRuimSActionPerformed

    private void btnNuncaSActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnNuncaSActionPerformed
        String mensagem = "4;"+lblNomeFilmeS.getText()+";1;"+cod_usuario;
        respSugestao(mensagem);
    }//GEN-LAST:event_btnNuncaSActionPerformed

    private void btnOtimoSActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnOtimoSActionPerformed
        String mensagem = "4;"+lblNomeFilmeS.getText()+";4;"+cod_usuario;
        respSugestao(mensagem);
        
    }//GEN-LAST:event_btnOtimoSActionPerformed

    private void btnBomSActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBomSActionPerformed
        String mensagem = "4;"+lblNomeFilmeS.getText()+";3;"+cod_usuario;
        respSugestao(mensagem);
        
    }//GEN-LAST:event_btnBomSActionPerformed

    public void respSugestao(String mensagem){
       Socket s = null;
       
       try{
           int serverPort = 7896;
           s = new Socket(server,serverPort);
           
           DataOutputStream out = new DataOutputStream(s.getOutputStream());
           DataInputStream in = new DataInputStream((s.getInputStream()));
           out.writeUTF(mensagem);
           
           String data = in.readUTF();
           
           String sep[] = data.split(";");
           
           lblNomeFilmeS.setText(sep[1]);
           lblDistS.setText(sep[2]);
           lblanoS.setText(sep[3]);
           
       }
       catch(UnknownHostException e){
           System.out.println("Socket: "+e.getMessage());
       }
       
       catch(EOFException e){
           System.out.println("EOF: "+ e.getMessage());
       }
       
       catch(IOException e){
           System.out.println("IO: "+ e.getMessage());
       }
       
       finally{
           if(s!= null){
               try{s.close();}
               
               catch(IOException e){
                   //*close falhou*/
               }
           }
       } 
    }
    
    public void respFilme(String mensagem) throws UnknownHostException{
      
       Socket s = null;
       
       try{
           int serverPort = 7896;
           s = new Socket(server,serverPort);
           
           DataInputStream in = new DataInputStream(s.getInputStream());
           DataOutputStream out = new DataOutputStream(s.getOutputStream());
           out.writeUTF(mensagem);
           
           String data = in.readUTF();
           
           String sep[] = data.split(";");
           
           mat_filme = sep[0];
           lblNomeFilme.setText(sep[1]);
           lblDist.setText(sep[2]);
           lblano.setText(sep[3]);
           
           if(" ".equals(mat_filme)){
               btnBom.setEnabled(false);
               btnNunca.setEnabled(false);
               btnOtimo.setEnabled(false);
               btnRuim.setEnabled(false);
               lblAvaliacao.setText("Avalição de Filmes: ENCERRADA");
               lblAvaliacao.setForeground(Color.red);
           }
       }
       catch(UnknownHostException e){
           System.out.println("Socket: "+e.getMessage());
       }
       
       catch(EOFException e){
           System.out.println("EOF: "+ e.getMessage());
       }
       
       catch(IOException e){
           System.out.println("IO: "+ e.getMessage());
       }
       
       finally{
           if(s!= null){
               try{s.close();}
               
               catch(IOException e){
                   //*close falhou*/
               }
           }
       } 
        
    }
   
    public void logarServidor(String mensagem) throws UnknownHostException{
       
       Socket s = null;
       
       try{
           int serverPort = 7896;
           s = new Socket(server,serverPort);
           
           DataInputStream in = new DataInputStream(s.getInputStream());
           DataOutputStream out = new DataOutputStream(s.getOutputStream());
           out.writeUTF(mensagem);
           
           String data = in.readUTF();
           
           String sep[] = data.split(";");
           
           mat_filme = sep[0];
           lblNomeFilme.setText(sep[1]);
           lblDist.setText(sep[2]);
           lblano.setText(sep[3]);
           cod_usuario = sep[4];
           porta = Integer.parseInt(sep[5]);
           
       }
       catch(UnknownHostException e){
           System.out.println("Socket: "+e.getMessage());
       }
       
       catch(EOFException e){
           System.out.println("EOF: "+ e.getMessage());
       }
       
       catch(IOException e){
           System.out.println("IO: "+ e.getMessage());
       }
       
       finally{
           if(s!= null){
               try{s.close();}
               
               catch(IOException e){
                   //*close falhou*/
               }
           }
       }
    }
    
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Windows".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(TelaCliente.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(TelaCliente.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(TelaCliente.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(TelaCliente.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new TelaCliente().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnBom;
    private javax.swing.JButton btnBomS;
    private javax.swing.JButton btnLogar;
    private javax.swing.JButton btnNunca;
    private javax.swing.JButton btnNuncaS;
    private javax.swing.JButton btnOtimo;
    private javax.swing.JButton btnOtimoS;
    private javax.swing.JButton btnRuim;
    private javax.swing.JButton btnRuimS;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JLabel lblAvaliacao;
    private javax.swing.JLabel lblDist;
    private javax.swing.JLabel lblDistS;
    private javax.swing.JLabel lblNomeFilme;
    private javax.swing.JLabel lblNomeFilmeS;
    private javax.swing.JLabel lblSugestao;
    private javax.swing.JLabel lblano;
    private javax.swing.JLabel lblanoS;
    private javax.swing.JTextField txtNome;
    // End of variables declaration//GEN-END:variables

    public int getPorta() {
        return porta;
    }

    public void setPorta(int porta) {
        this.porta = porta;
    }
}
