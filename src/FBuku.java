
import java.sql.Connection;
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
public class FBuku extends javax.swing.JFrame {
 Connection conn;
    DefaultTableModel tabModel;

    public FBuku() {
        initComponents();
        conn = koneksi.getConnection();
        setJTable();
    }
    
    private void setJTable(){
    String [] JudulKolom={"No","KodeBuku","Judul","Penulis","Penerbit","TahunTerbit","Status"};
    tabModel = new DefaultTableModel(null, JudulKolom){
                  boolean[] canEdit = new boolean [] { false, false, false, false, false, false};
                  @Override
                  public boolean isCellEditable(int rowIndex, int columnIndex) {
                   return canEdit [columnIndex];
                  }
              };
    TBuku.setModel(tabModel);
    TBuku.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
    TBuku.getColumnModel().getColumn(0).setPreferredWidth(30);
    TBuku.getColumnModel().getColumn(1).setPreferredWidth(150);
    TBuku.getColumnModel().getColumn(2).setPreferredWidth(150);
    TBuku.getColumnModel().getColumn(3).setPreferredWidth(150);
    TBuku.getColumnModel().getColumn(4).setPreferredWidth(150);
    TBuku.getColumnModel().getColumn(5).setPreferredWidth(150);
    getData();
} // Akhir setJTable
    
    private void getData(){
          try{   
              // Membuat perintah sql 
        String sql="Select * from Buku";
        PreparedStatement st=conn.prepareStatement(sql);  // import java.sql.PreparedStatement
              //Membuat Variabel Bertipe ResulSet
             //Kelas Resultset Berfungsi Menyimpan Dataset(Sekumpulan Data) hasil prepareStatement Query
        ResultSet rs=st.executeQuery();   // import java.sql.ResultSet;
            // Menampilkan ke JTable  melalui tabModel
        String KodeBuku,Judul,Penulis,Penerbit,TahunTerbit,Status;
        int no=0;
        while(rs.next()){
         no=no+1;
         KodeBuku=rs.getString("KodeBuku");
         Judul=rs.getString("Judul");
         Penulis=rs.getString("Penulis");
         Penerbit=rs.getString("Penerbit");
         TahunTerbit=rs.getString("TahunTerbit");
         Status=rs.getString("Status");

         Object Data[]={no,KodeBuku,Judul,Penulis,Penerbit,TahunTerbit,Status};
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
    } // Akhir Method getDatae
    
    void simpanData(){    
     try{            
            String sql="Insert into Buku values(?,?,?,?,?,?)";
            PreparedStatement st=conn.prepareStatement(sql);
                st.setString(1, txtKodeBuku.getText());
                st.setString(2, txtJudul.getText());
                st.setString(3, txtPenulis.getText());
                st.setString(4, txtPenerbit.getText());
                st.setString(5, txtTahunTerbit.getText());
                st.setString(6, txtStatus.getText());
            int rs=st.executeUpdate();

            if(rs>0){
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
    
    private void editData() {
//        JOptionPane.showInputDialog()
           String inputan = JOptionPane.showInputDialog("Masukan Kode Buku yang akan di edit : ");
           try{   
              // Membuat perintah sql 
       String sql="SELECT * FROM Buku WHERE KodeBuku=?";
       PreparedStatement st=conn.prepareStatement(sql);
                st.setString(1, inputan);
                
             ResultSet rs=st.executeQuery();

               while(rs.next()) {
                   
                   txtJudul.setText(rs.getString("Judul"));
                   txtKodeBuku.setText(rs.getString("KodeBuku"));
                   txtPenerbit.setText(rs.getString("Penerbit"));
                   txtPenulis.setText(rs.getString("Penulis"));
                   txtStatus.setText(rs.getString("Status"));
                   txtTahunTerbit.setText(rs.getString("TahunTerbit"));
                   setEnableFieldTrue();
                   BUpdate.setEnabled(true);
                   BEdit.setEnabled(false);
                   BBatal.setEnabled(true);
                   BTambah.setEnabled(false);
                   BHapus.setEnabled(false);
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
    
    private void updateData() {
        try{   
              // Membuat perintah sql 
       String sql="UPDATE Buku Set Judul=?, Penulis=?, Penerbit=?, TahunTerbit=?, Status=?  WHERE KodeBuku=?";
       PreparedStatement st=conn.prepareStatement(sql);
                st.setString(1, txtJudul.getText());
                st.setString(2, txtPenulis.getText());
                st.setString(3, txtPenerbit.getText());
                st.setString(4, txtTahunTerbit.getText());
                st.setString(5,txtStatus.getText());
                st.setString(6, txtKodeBuku.getText());


            int rs=st.executeUpdate();

            if(rs>0){
            JOptionPane.showMessageDialog(this,"Berhasil Mengupdate data");
            BUpdate.setEnabled(false);
            BBatal.setEnabled(false);
            BEdit.setEnabled(true);
            BHapus.setEnabled(true);
            BTambah.setEnabled(true);
            
            setEnableFieldFalse();
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
    
    private void hapusData() {
          String id = JOptionPane.showInputDialog("Masukan No Buku Untuk Menghapus : ");
        try{   
              // Membuat perintah sql 
       String sql="DELETE FROM Buku where KodeBuku=?";
       PreparedStatement st=conn.prepareStatement(sql);
                st.setString(1, id);
                
            int rs=st.executeUpdate();

            if(rs>0){
            JOptionPane.showMessageDialog(this,"Berhasil Menghapus Dengan Kode Buku " + id);
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
    
    private void setEnableFieldFalse() {
        txtJudul.setEditable(false);
        txtKodeBuku.setEditable(false);
        txtPenerbit.setEditable(false);
        txtPenulis.setEditable(false);
        txtStatus.setEditable(false);
        txtTahunTerbit.setEditable(false);
        
        txtJudul.setText("");
        txtKodeBuku.setText("");
        txtPenerbit.setText("");
        txtPenulis.setText("");
        txtStatus.setText("");
        txtTahunTerbit.setText("");
    }
    
    private void setEnableFieldTrue() {
        txtJudul.setEditable(true);
        txtKodeBuku.setEditable(true);
        txtPenerbit.setEditable(true);
        txtPenulis.setEditable(true);
        txtStatus.setEditable(true);
        txtTahunTerbit.setEditable(true);
    }
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        buttonGroup1 = new javax.swing.ButtonGroup();
        buttonGroup2 = new javax.swing.ButtonGroup();
        buttonGroup3 = new javax.swing.ButtonGroup();
        buttonGroup4 = new javax.swing.ButtonGroup();
        buttonGroup5 = new javax.swing.ButtonGroup();
        buttonGroup6 = new javax.swing.ButtonGroup();
        buttonGroup7 = new javax.swing.ButtonGroup();
        buttonGroup8 = new javax.swing.ButtonGroup();
        buttonGroup9 = new javax.swing.ButtonGroup();
        buttonGroup10 = new javax.swing.ButtonGroup();
        buttonGroup11 = new javax.swing.ButtonGroup();
        buttonGroup12 = new javax.swing.ButtonGroup();
        txtKodeBuku = new javax.swing.JTextField();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        txtJudul = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        txtPenulis = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        txtPenerbit = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        txtTahunTerbit = new javax.swing.JTextField();
        jLabel6 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        TBuku = new javax.swing.JTable();
        BTambah = new javax.swing.JButton();
        BSimpan = new javax.swing.JButton();
        BUpdate = new javax.swing.JButton();
        BEdit = new javax.swing.JButton();
        BBatal = new javax.swing.JButton();
        BHapus = new javax.swing.JButton();
        txtStatus = new javax.swing.JTextField();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        txtKodeBuku.setEditable(false);
        txtKodeBuku.setOpaque(false);

        jLabel1.setText("Kode Buku");

        jLabel2.setText("Judul");

        txtJudul.setEditable(false);
        txtJudul.setOpaque(false);

        jLabel3.setText("Penulis");

        txtPenulis.setEditable(false);
        txtPenulis.setOpaque(false);

        jLabel4.setText("Penerbit");

        txtPenerbit.setEditable(false);
        txtPenerbit.setOpaque(false);

        jLabel5.setText("Tahun Terbit");

        txtTahunTerbit.setEditable(false);
        txtTahunTerbit.setOpaque(false);

        jLabel6.setText("Status");

        TBuku.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        jScrollPane1.setViewportView(TBuku);

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

        BUpdate.setText("Update");
        BUpdate.setEnabled(false);
        BUpdate.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BUpdateActionPerformed(evt);
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

        txtStatus.setEditable(false);
        txtStatus.setOpaque(false);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(19, 19, 19)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel5)
                            .addComponent(jLabel4)
                            .addComponent(jLabel3)
                            .addComponent(jLabel1)
                            .addComponent(jLabel2)
                            .addComponent(jLabel6))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(txtKodeBuku)
                            .addComponent(txtJudul)
                            .addComponent(txtPenulis)
                            .addComponent(txtPenerbit, javax.swing.GroupLayout.DEFAULT_SIZE, 194, Short.MAX_VALUE)
                            .addComponent(txtTahunTerbit)
                            .addComponent(txtStatus)))
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(BTambah)
                        .addGap(18, 18, 18)
                        .addComponent(BSimpan)
                        .addGap(18, 18, 18)
                        .addComponent(BUpdate)
                        .addGap(18, 18, 18)
                        .addComponent(BEdit)
                        .addGap(18, 18, 18)
                        .addComponent(BBatal)
                        .addGap(18, 18, 18)
                        .addComponent(BHapus))
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jScrollPane1)))
                .addContainerGap(84, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtKodeBuku, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel1))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(txtJudul, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(txtPenulis, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(txtPenerbit, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel5)
                    .addComponent(txtTahunTerbit, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel6)
                    .addComponent(txtStatus, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(BTambah)
                    .addComponent(BSimpan)
                    .addComponent(BUpdate)
                    .addComponent(BEdit)
                    .addComponent(BBatal)
                    .addComponent(BHapus))
                .addGap(13, 13, 13)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void BTambahActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BTambahActionPerformed
        // TODO add your handling code here:
        setEnableFieldTrue();
        BSimpan.setEnabled(true);
        BBatal.setEnabled(true);
        BEdit.setEnabled(false);
        BHapus.setEnabled(false);
        BTambah.setEnabled(false);
    }//GEN-LAST:event_BTambahActionPerformed

    private void BBatalActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BBatalActionPerformed
        // TODO add your handling code here:
        setEnableFieldFalse();
        BEdit.setEnabled(true);
        BHapus.setEnabled(true);
        BSimpan.setEnabled(false);
        BTambah.setEnabled(true);
        BBatal.setEnabled(false);
    }//GEN-LAST:event_BBatalActionPerformed

    private void BSimpanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BSimpanActionPerformed
        // TODO add your handling code here:
        simpanData();
        setEnableFieldFalse();
        BEdit.setEnabled(true);
        BHapus.setEnabled(true);
        BSimpan.setEnabled(false);
        BTambah.setEnabled(true);
        BBatal.setEnabled(false);
    }//GEN-LAST:event_BSimpanActionPerformed

    private void BEditActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BEditActionPerformed
        // TODO add your handling code here:
        editData();
    }//GEN-LAST:event_BEditActionPerformed

    private void BUpdateActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BUpdateActionPerformed
        // TODO add your handling code here:
        updateData();
    }//GEN-LAST:event_BUpdateActionPerformed

    private void BHapusActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BHapusActionPerformed
        // TODO add your handling code here:
        hapusData();
    }//GEN-LAST:event_BHapusActionPerformed

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
            java.util.logging.Logger.getLogger(FBuku.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(FBuku.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(FBuku.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(FBuku.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(() -> {
            new FBuku().setVisible(true);
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton BBatal;
    private javax.swing.JButton BEdit;
    private javax.swing.JButton BHapus;
    private javax.swing.JButton BSimpan;
    private javax.swing.JButton BTambah;
    private javax.swing.JButton BUpdate;
    private javax.swing.JTable TBuku;
    private javax.swing.ButtonGroup buttonGroup1;
    private javax.swing.ButtonGroup buttonGroup10;
    private javax.swing.ButtonGroup buttonGroup11;
    private javax.swing.ButtonGroup buttonGroup12;
    private javax.swing.ButtonGroup buttonGroup2;
    private javax.swing.ButtonGroup buttonGroup3;
    private javax.swing.ButtonGroup buttonGroup4;
    private javax.swing.ButtonGroup buttonGroup5;
    private javax.swing.ButtonGroup buttonGroup6;
    private javax.swing.ButtonGroup buttonGroup7;
    private javax.swing.ButtonGroup buttonGroup8;
    private javax.swing.ButtonGroup buttonGroup9;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTextField txtJudul;
    private javax.swing.JTextField txtKodeBuku;
    private javax.swing.JTextField txtPenerbit;
    private javax.swing.JTextField txtPenulis;
    private javax.swing.JTextField txtStatus;
    private javax.swing.JTextField txtTahunTerbit;
    // End of variables declaration//GEN-END:variables
}
