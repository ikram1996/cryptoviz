
///quick des encryption
//NOT WORKING YET

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/*
 * DESFrame2.java
 *
 * Created on Feb 2, 2010, 11:52:54 AM
 */

import javax.swing.*;
import java.io.*;

/**
 *
 * @author fro
 */
public class DESFrame2 extends javax.swing.JInternalFrame {

    /** Creates new form DESFrame2 */
    public DESFrame2() {
        setLocation(0, 0);
        initComponents();
    }

    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
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
        jLabel1 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        plainTextfield = new javax.swing.JTextArea();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jButton1 = new javax.swing.JButton();
        jLabel4 = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        cypherTextArea = new javax.swing.JTextArea();
        jLabel5 = new javax.swing.JLabel();
        encryptButton = new javax.swing.JButton();
        clearButton = new javax.swing.JButton();
        jScrollPane3 = new javax.swing.JScrollPane();
        keyTextfield = new javax.swing.JTextArea();

        setClosable(true);
        setIconifiable(true);
        setMaximizable(true);
        setResizable(true);
        setTitle("DES Encryption");

        jLabel1.setFont(new java.awt.Font("Tahoma", 0, 14));
        jLabel1.setText("Quick DES Encryption");

        plainTextfield.setColumns(20);
        plainTextfield.setRows(5);
        plainTextfield.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.LOWERED, null, java.awt.Color.white, null, null));
        plainTextfield.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                plainTextfieldKeyPressed(evt);
            }
            public void keyReleased(java.awt.event.KeyEvent evt) {
                plainTextfieldKeyReleased(evt);
            }
        });
        jScrollPane1.setViewportView(plainTextfield);

        jLabel2.setText("Enter some text:");

        jLabel3.setText("Or choose a file:");

        jButton1.setText("Browse");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jLabel4.setText("Enter a key:");

        cypherTextArea.setColumns(20);
        cypherTextArea.setRows(5);
        cypherTextArea.setBorder(null);
        jScrollPane2.setViewportView(cypherTextArea);

        jLabel5.setText("Output:");

        encryptButton.setText("Encrypt");
        encryptButton.setEnabled(false);
        encryptButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                encryptButtonActionPerformed(evt);
            }
        });

        clearButton.setText("Clear");
        clearButton.setEnabled(false);
        clearButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                clearButtonActionPerformed(evt);
            }
        });

        keyTextfield.setColumns(20);
        keyTextfield.setRows(5);
        keyTextfield.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.LOWERED));
        keyTextfield.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                keyTextfieldKeyPressed(evt);
            }
            public void keyReleased(java.awt.event.KeyEvent evt) {
                keyTextfieldKeyReleased(evt);
            }
        });
        jScrollPane3.setViewportView(keyTextfield);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(25, 25, 25)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jLabel3)
                            .addComponent(jLabel5)
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 234, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel2))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(jLabel4)
                                        .addGap(168, 168, 168))
                                    .addComponent(jScrollPane3)))
                            .addComponent(jScrollPane2)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jButton1)
                                .addGap(18, 18, 18)
                                .addComponent(clearButton, javax.swing.GroupLayout.PREFERRED_SIZE, 74, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(encryptButton, javax.swing.GroupLayout.PREFERRED_SIZE, 93, javax.swing.GroupLayout.PREFERRED_SIZE))))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(179, 179, 179)
                        .addComponent(jLabel1)))
                .addContainerGap(27, Short.MAX_VALUE))
        );

        layout.linkSize(javax.swing.SwingConstants.HORIZONTAL, new java.awt.Component[] {clearButton, encryptButton, jButton1});

        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(31, 31, 31)
                .addComponent(jLabel1)
                .addGap(37, 37, 37)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(jLabel4))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jScrollPane3)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 163, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 36, Short.MAX_VALUE)
                .addComponent(jLabel3)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton1)
                    .addComponent(clearButton)
                    .addComponent(encryptButton, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(26, 26, 26)
                .addComponent(jLabel5)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 189, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(25, 25, 25))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        String filename = File.separator+"tmp";
        JFileChooser fc = new JFileChooser(new File(filename));
        TxtFilter filter = new TxtFilter();
        fc.setFileFilter(filter);

        // Show open dialog; this method does not return until the dialog is closed
        fc.showOpenDialog(this);
        File file = fc.getSelectedFile();
        StringBuffer contents = new StringBuffer();
        BufferedReader reader = null;
        try{
            reader = new BufferedReader(new FileReader(file));
            String text = null;

                // repeat until all lines is read
            while ((text = reader.readLine()) != null)
            {
                contents.append(text)
                    .append(System.getProperty(
                        "line.separator"));
            }
        }
        catch (FileNotFoundException e)
        {
            e.printStackTrace();
        } catch (IOException e)
        {
            e.printStackTrace();
        } finally
        {
            try
            {
                if (reader != null)
                {
                    reader.close();
                }
            } catch (IOException e)
            {
                e.printStackTrace();
            }
        }
        plainTextfield.setText(contents.toString());
    }//GEN-LAST:event_jButton1ActionPerformed

    private void encryptButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_encryptButtonActionPerformed

/*
                //get key and convert to bitlist
		String key = keyTextfield.getText();
		//make it exactly 8 ascii characters long...
		while ( key.length()<8 ) key=key+" ";
		key=key.substring(0,8);
		
		BitList keyBits = ConvertString.StringToBitList(key);

                //get plaintext and convert to bitlist
                String plainText = plainTextfield.getText();
                //pad it out to a multiple of 8 with spaces
                while ((plainText.length() % 8)!=0) plainText=plainText+" ";                
                BitList input = ConvertString.StringToBitList(plainText);
                
                String cyphertext = new String();
                //cyphertext=cyphertext+keyBits+"\n";
                //cyphertext=cyphertext+input+"\n";
                //cyphertext=cyphertext+DES.encrypt(input,keyBits)+"\n";
                
		//now encrypt the input, 64 bits at a time
		for (int i=0;i<input.length();i+=64)
			cyphertext= cyphertext+ConvertString.BitListToString(DES.encrypt(input.get(i,i+64),keyBits));
		
	
                cypherTextArea.setText("");
		cypherTextArea.append(cyphertext); */
		
		
	

		String key = keyTextfield.getText();
		//must be 16 hex characters otherwise we will get major errors		
		BitList keyBits = ConvertString.HexStringToBitList(key);

                //get plaintext and convert to bitlist
                String plainText = plainTextfield.getText();
                //pad it out to a multiple of 16 with zeroes
                //while ((plainText.length() % 16)!=0) plainText=plainText+"0";                
                BitList input = ConvertString.HexStringToBitList(plainText);
                
                String cyphertext = new String();
                cyphertext=cyphertext+keyBits.toHexString()+"\n";
                cyphertext=cyphertext+input.toHexString()+"\n";
                cyphertext=cyphertext+DES.encrypt(input,keyBits).toHexString()+"\n";
                
		//now encrypt the input, 64 bits at a time
		for (int i=0;i<input.length();i+=64)
			cyphertext= cyphertext+(DES.encrypt(input.get(i,i+64),keyBits)).toHexString();
		
	
                cypherTextArea.setText("");
		cypherTextArea.append(cyphertext); 
		
		
		
		
		
    }//GEN-LAST:event_encryptButtonActionPerformed

    private void plainTextfieldKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_plainTextfieldKeyPressed
        checkForCompletion();
    }//GEN-LAST:event_plainTextfieldKeyPressed

    private void plainTextfieldKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_plainTextfieldKeyReleased
        checkForCompletion();
    }//GEN-LAST:event_plainTextfieldKeyReleased

    private void clearButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_clearButtonActionPerformed
        plainTextfield.setText("");
        keyTextfield.setText("");
        cypherTextArea.setText("");
        checkForCompletion();
    }//GEN-LAST:event_clearButtonActionPerformed

    private void keyTextfieldKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_keyTextfieldKeyPressed
        checkForCompletion();
    }//GEN-LAST:event_keyTextfieldKeyPressed

    private void keyTextfieldKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_keyTextfieldKeyReleased
       checkForCompletion();
    }//GEN-LAST:event_keyTextfieldKeyReleased

    private void checkForCompletion() {
        if(keyTextfield.getText().isEmpty())
            encryptButton.setEnabled(false);                    
        else{
            if(plainTextfield.getText().isEmpty())            
                encryptButton.setEnabled(false);                            
            else
                encryptButton.setEnabled(true);            
        }
        if(!plainTextfield.getText().isEmpty()
            || !keyTextfield.getText().isEmpty()
            || !cypherTextArea.getText().isEmpty())
            clearButton.setEnabled(true);
        else
            clearButton.setEnabled(false);
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.ButtonGroup buttonGroup1;
    private javax.swing.ButtonGroup buttonGroup2;
    private javax.swing.ButtonGroup buttonGroup3;
    private javax.swing.ButtonGroup buttonGroup4;
    private javax.swing.ButtonGroup buttonGroup5;
    private javax.swing.ButtonGroup buttonGroup6;
    private javax.swing.ButtonGroup buttonGroup7;
    private javax.swing.ButtonGroup buttonGroup8;
    private javax.swing.JButton clearButton;
    private javax.swing.JTextArea cypherTextArea;
    private javax.swing.JButton encryptButton;
    private javax.swing.JButton jButton1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JTextArea keyTextfield;
    private javax.swing.JTextArea plainTextfield;
    // End of variables declaration//GEN-END:variables

}
