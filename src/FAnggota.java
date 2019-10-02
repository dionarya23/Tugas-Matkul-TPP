
import com.mysql.jdbc.Connection;
import java.awt.TextField;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author dionarya
 */
public class FAnggota extends javax.swing.JFrame {
    Connection conn;
    DefaultTableModel tabModel;
    /**
     * Creates new form FAnggota
     */
    public FAnggota() {
        initComponents();
        conn=koneksi.getConnection();
        setJTable();
    }
    
    private void setJTable() {
        String [] JudulKolom={"No","No Anggota","Nama Anggota","Alamat"};
    tabModel = new DefaultTableModel(null, JudulKolom){
                  boolean[] canEdit = new boolean [] { false, false, false, false};
                  @Override
                  public boolean isCellEditable(int rowIndex, int columnIndex) {
                   return canEdit [columnIndex];
                  }
              };
    TAnggota.setModel(tabModel);
    TAnggota.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
    TAnggota.getColumnModel().getColumn(0).setPreferredWidth(30);
    TAnggota.getColumnModel().getColumn(1).setPreferredWidth(100);
    TAnggota.getColumnModel().getColumn(2).setPreferredWidth(200);
    TAnggota.getColumnModel().getColumn(3).setPreferredWidth(300);

    getData();
    }
    
    private void getData(){  
  try{   
              // Membuat perintah sql 
        String sql="Select * from Anggota";
        PreparedStatement st=conn.prepareStatement(sql);  // import java.sql.PreparedStatement
              //Membuat Variabel Bertipe ResulSet
             //Kelas Resultset Berfungsi Menyimpan Dataset(Sekumpulan Data) hasil prepareStatement Query
        ResultSet rs=st.executeQuery();   // import java.sql.ResultSet;
            // Menampilkan ke JTable  melalui tabModel
        String NoAnggota,Nama,Alamat;
        int no=0;
        while(rs.next()){
         no=no+1;
         NoAnggota=rs.getString("NoAnggota");
         Nama=rs.getString("Nama");
         Alamat=rs.getString("Alamat");

         Object Data[]={no,NoAnggota,Nama,Alamat};
         tabModel.addRow(Data);
        }
    }
    catch (SQLException sqle) {                  
           System.out.println("Proses Query Gagal = " + sqle);
           System.exit(0);
    }
    catch(Exception e){
           System.out.println("Koneksi Access Gagal " +e.getMessage());
           System.exit(0);
    }

  }    // Akhir Method getData

    

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jTextField1 = new javax.swing.JTextField();
        jLabel1 = new javax.swing.JLabel();
        txtNoAnggota = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        txtNama = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        txtAlamat = new javax.swing.JTextArea();
        BTambah = new javax.swing.JButton();
        BSimpan = new javax.swing.JButton();
        BEdit = new javax.swing.JButton();
        BBatal = new javax.swing.JButton();
        BHapus = new javax.swing.JButton();
        jScrollPane2 = new javax.swing.JScrollPane();
        TAnggota = new javax.swing.JTable();

        jTextField1.setText("jTextField1");

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jLabel1.setText("No Anggota");

        txtNoAnggota.setEditable(false);

        jLabel2.setText("Nama");

        txtNama.setEditable(false);

        jLabel3.setText("Alamat");

        txtAlamat.setEditable(false);
        txtAlamat.setBackground(new java.awt.Color(250, 250, 250));
        txtAlamat.setColumns(20);
        txtAlamat.setRows(5);
        jScrollPane1.setViewportView(txtAlamat);

        BTambah.setText("Tambah");
        BTambah.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BTambahActionPerformed(evt);
            }
        });

        BSimpan.setText("Simpan");
        BSimpan.setEnabled(false);
        BSimpan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BSimpanActionPerformed(evt);
            }
        });

        BEdit.setText("Edit");
        BEdit.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BEditActionPerformed(evt);
            }
        });

        BBatal.setText("Batal");
        BBatal.setEnabled(false);
        BBatal.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BBatalActionPerformed(evt);
            }
        });

        BHapus.setText("Hapus");
        BHapus.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BHapusActionPerformed(evt);
            }
        });

        TAnggota.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "No", "No Anggota", "Nama Anggota", "Alamat"
            }
        ));
        jScrollPane2.setViewportView(TAnggota);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 452, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(layout.createSequentialGroup()
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                .addComponent(jLabel3)
                                .addComponent(jLabel2)
                                .addComponent(jLabel1))
                            .addGap(18, 18, 18)
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                .addComponent(txtNoAnggota)
                                .addComponent(txtNama)
                                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 401, Short.MAX_VALUE)))
                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                            .addComponent(BTambah)
                            .addGap(18, 18, 18)
                            .addComponent(BSimpan)
                            .addGap(33, 33, 33)
                            .addComponent(BEdit)
                            .addGap(18, 18, 18)
                            .addComponent(BBatal)
                            .addGap(31, 31, 31)
                            .addComponent(BHapus))))
                .addContainerGap(47, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(txtNoAnggota, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel2)
                    .addComponent(txtNama, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel3)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(BTambah)
                    .addComponent(BEdit)
                    .addComponent(BBatal)
                    .addComponent(BHapus)
                    .addComponent(BSimpan))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 28, Short.MAX_VALUE)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 340, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void BSimpanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BSimpanActionPerformed
        // TODO add your handling code here:
        BSimpan.setEnabled(false);
        BTambah.setEnabled(true);
        BEdit.setEnabled(true);
        BHapus.setEnabled(true);
        editAbleTrue();
        simpanData();
    }//GEN-LAST:event_BSimpanActionPerformed

    private void BTambahActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BTambahActionPerformed
        // TODO add your handling code here:
        BSimpan.setEnabled(true);
        BTambah.setEnabled(false);
        BBatal.setEnabled(true);
        BEdit.setEnabled(false);
        BHapus.setEnabled(false);
        editAbleTrue();
    }//GEN-LAST:event_BTambahActionPerformed

    private void BEditActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BEditActionPerformed
        // TODO add your handling code here:
        editData();
    }//GEN-LAST:event_BEditActionPerformed

    private void BHapusActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BHapusActionPerformed
        // TODO add your handling code here:
        hapusData();
    }//GEN-LAST:event_BHapusActionPerformed

    private void BBatalActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BBatalActionPerformed
        // TODO add your handling code here:
        txtAlamat.setEditable(false);
        txtNama.setEditable(false);
        txtNoAnggota.setEditable(false);
        BBatal.setEnabled(false);
        BSimpan.setEnabled(false);
        BTambah.setEnabled(true);
    }//GEN-LAST:event_BBatalActionPerformed
    
    private void hapusData(){
        String id = JOptionPane.showInputDialog("Masukan No Anggota Untuk Menghapus : ");
        try{   
              // Membuat perintah sql 
       String sql="DELETE FROM Anggota where NoAnggota=?";
       PreparedStatement st=conn.prepareStatement(sql);
                st.setString(1, id);
                
            int rs=st.executeUpdate();

            if(rs>0){
            JOptionPane.showMessageDialog(this,"Berhasil Menghapus Dengan NoAnggota " + id);
      	    setJTable();
           }
       
    }
    catch (SQLException sqle) {                  
           System.out.println("Proses Query Gagal = " + sqle);
           System.exit(0);
    }
    catch(Exception e){
           System.out.println("Koneksi Access Gagal " +e.getMessage());
           System.exit(0);
    }
        
    }
    
    private void editData() {
//        JOptionPane.showInputDialog()
           String inputan = JOptionPane.showInputDialog("Inputkan Sesuatu");
           JOptionPane.showMessageDialog(this, "Kamu meng-inputkan: " + inputan);
    }
    
    private void editAbleTrue() {
        txtNoAnggota.setEditable(true);
        txtNama.setEditable(true);
        txtAlamat.setEditable(true);
    }
    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(FAnggota.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(FAnggota.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(FAnggota.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(FAnggota.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new FAnggota().setVisible(true);
            }
        });
    }
    
    void simpanData(){    
     try{            
            String sql="Insert into Anggota values(?,?,?)";
            PreparedStatement st=conn.prepareStatement(sql);
                st.setString(1, txtNoAnggota.getText());
                st.setString(2, txtNama.getText());
                st.setString(3, txtAlamat.getText());
            int rs=st.executeUpdate();

            if(rs>0){
                txtNoAnggota.setText("");
                txtNama.setText("");
                txtAlamat.setText("");
            JOptionPane.showMessageDialog(this,"Input Berhasil");
      	    setJTable();
           }
        }
        catch (SQLException sqle) {
           JOptionPane.showMessageDialog(this,"Input  Gagal = " + sqle.getMessage());
        }
        catch(Exception e){
           JOptionPane.showMessageDialog(this,"Koneksi Gagal " +e.getMessage());
        }
}


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton BBatal;
    private javax.swing.JButton BEdit;
    private javax.swing.JButton BHapus;
    private javax.swing.JButton BSimpan;
    private javax.swing.JButton BTambah;
    private javax.swing.JTable TAnggota;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTextField jTextField1;
    private javax.swing.JTextArea txtAlamat;
    private javax.swing.JTextField txtNama;
    private javax.swing.JTextField txtNoAnggota;
    // End of variables declaration//GEN-END:variables
}