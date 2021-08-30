package assignment.pkg4;

import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.FileNotFoundException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.GroupLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.LayoutStyle;
import javax.swing.border.EmptyBorder;

// Variables declaration - do not modify                     
public class Ass_3 extends JFrame {

    private JPanel contentPane;
    private JTextField textField;

    /**
     * Launch the application.
     *
     * @param args
     */
    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    Ass_3 frame = new Ass_3();
                    frame.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    /**
     * Create the frame.
     */
    public Ass_3() {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 400,280);
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);

        //JLabel lblNewLabel = new JLabel("Enter Your Choose :");

        //textField = new JTextField();
        //textField.setColumns(10);

        JButton btnCompress = new JButton("Compress");
        btnCompress.setFont(new Font("Arial", Font.PLAIN, 18));

        btnCompress.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                Floating x = new Floating();
                try {
                    x.compress();
                } catch (FileNotFoundException ex) {
                    Logger.getLogger(Ass_3.class.getName()).log(Level.SEVERE, null, ex);
                }
                JOptionPane.showMessageDialog(null, "Compressed successfully!");
            }
        });

        JButton btnDecompress = new JButton("Decompress");
                btnDecompress.setFont(new Font("Arial", Font.PLAIN, 18));

        btnDecompress.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                Floating x = new Floating();
                try {
                    x.decompress();
                } catch (FileNotFoundException ex) {
                    Logger.getLogger(Ass_3.class.getName()).log(Level.SEVERE, null, ex);
                }
                JOptionPane.showMessageDialog(null, "Decompressed successfully!");
            }
        });
        GroupLayout gl_contentPane = new GroupLayout(contentPane);
        gl_contentPane.setHorizontalGroup(
                gl_contentPane.createParallelGroup(GroupLayout.Alignment.LEADING)
                        .addGroup(gl_contentPane.createSequentialGroup()
                                .addGap(50)
                                .addGroup(gl_contentPane.createParallelGroup(GroupLayout.Alignment.LEADING)
                                        .addGroup(gl_contentPane.createSequentialGroup()
                                                //.addComponent(lblNewLabel)
                                                .addGap(90)
                                                //.addComponent(textField, GroupLayout.PREFERRED_SIZE, 224, GroupLayout.PREFERRED_SIZE)
                                                .addContainerGap(38, Short.MAX_VALUE))
                                        .addGroup(gl_contentPane.createSequentialGroup()
                                                .addComponent(btnCompress)
                                                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED, 127, Short.MAX_VALUE)
                                                .addComponent(btnDecompress)
                                                .addGap(60))))
        );
        gl_contentPane.setVerticalGroup(
                gl_contentPane.createParallelGroup(GroupLayout.Alignment.LEADING)
                        .addGroup(gl_contentPane.createSequentialGroup()
                                .addGap(86)
                                .addGroup(gl_contentPane.createParallelGroup(GroupLayout.Alignment.BASELINE)
                                        //.addComponent(lblNewLabel)
                                        //.addComponent(textField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
                                .addGap(68)
                                .addGroup(gl_contentPane.createParallelGroup(GroupLayout.Alignment.BASELINE)
                                        .addComponent(btnCompress)
                                        .addComponent(btnDecompress))
                                //.addContainerGap(92, Short.MAX_VALUE))
        )));
        contentPane.setLayout(gl_contentPane);
    }

}
