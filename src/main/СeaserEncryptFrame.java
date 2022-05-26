package main;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;


public class СeaserEncryptFrame extends JFrame {
        JButton b1;
        JButton b2;
        JButton b3;
        JButton b4;
        JButton b5;
        JLabel l1;
        JLabel l2;
        JLabel l3;
        JLabel l4;
        JLabel l5;
        JLabel l6;
        JTextField t1;
        JTextField t2;
        String link;
        int key;
        СeaserEncryptFrame.eHandler handler = new СeaserEncryptFrame.eHandler();

        public СeaserEncryptFrame (String s) {
            super(s);
            setLayout(new BorderLayout());
            JPanel panel = new JPanel(null);
            add(panel,BorderLayout.CENTER);

            b1 = new JButton("Выбрать");
            b1.setBounds(10,10,150,30);
            b2 = new JButton("Выбрать");
            b2.setBounds(10,50,150,30);
            b3 = new JButton("Зашифровать");
            b3.setBounds(10,90,150,30);
            b4 = new JButton ("Расшифровать");
            b4.setBounds(205,90,150,30);
            b5 = new JButton("BRUTEFORCE");
            b5.setBounds(405, 90, 150, 30);
            l1 = new JLabel("Введите адрес файла");
            l1.setBounds(165, 10, 200,30);
            l2 = new JLabel("Введите ключ шифра");
            l2.setBounds(165, 50, 150, 30);
            l3 = new JLabel("");
            l3.setBounds(10,160, 500,15);
            l4 = new JLabel("");
            l4.setBounds(10,180, 250,15);
            l5 = new JLabel("");
            l5.setBounds(10,200, 250,15);
            l6 = new JLabel("");
            l6.setBounds(10,220, 500,15);
            t1 = new JTextField();
            t1.setBounds(305, 10, 250, 30);
            t2 = new JTextField();
            t2.setBounds(305,50,250,30);
            panel.add(b1);
            panel.add(b2);
            panel.add(b3);
            panel.add(b4);
            panel.add(b5);
            panel.add(l1);
            panel.add(l2);
            panel.add(l3);
            panel.add(l4);
            panel.add(l5);
            panel.add(l6);
            panel.add(t1);
            panel.add(t2);

            b1.addActionListener(this.handler);
            b2.addActionListener(this.handler);
            b3.addActionListener(this.handler);
            b4.addActionListener(this.handler);
            b5.addActionListener(this.handler);
        }

    public class eHandler implements ActionListener {
        public eHandler() {
        }

        public void actionPerformed(ActionEvent e) {
            if (e.getSource() == СeaserEncryptFrame.this.b1) {
                СeaserEncryptFrame.this.link= СeaserEncryptFrame.this.t1.getText();
                СeaserEncryptFrame.this.l3.setText("Спасибо, мы получили ссылку" + СeaserEncryptFrame.this.link);
            }

            if (e.getSource() == СeaserEncryptFrame.this.b2) {
                СeaserEncryptFrame.this.key = Integer.parseInt(СeaserEncryptFrame.this.t2.getText());
                СeaserEncryptFrame.this.l4.setText("Спасибо, код получен");
            }

            if (e.getSource() == СeaserEncryptFrame.this.b3 && !СeaserEncryptFrame.this.link.isEmpty()) {
                try {
                    CeaserCipher.createsEncryptedFileFromString(CeaserCipher.encrypt(CeaserCipher.convertIntoString(link), key));
                } catch (IOException ex) {
                    ex.printStackTrace();
                }
                СeaserEncryptFrame.this.l5.setText("Закодированный файл создан, спасибо");

            }

            if (e.getSource() == СeaserEncryptFrame.this.b4 && !СeaserEncryptFrame.this.link.isEmpty()) {

                try {
                   CeaserCipher.createsDecryptedFileFromString(CeaserCipher.decrypt(CeaserCipher.convertIntoString(link), key));
                } catch (IOException ex) {
                    ex.printStackTrace();
                }
                СeaserEncryptFrame.this.l5.setText("Файл раскодирован, спасибо");

            }
            if (e.getSource() == СeaserEncryptFrame.this.b5 && !СeaserEncryptFrame.this.link.isEmpty()){
                try {
                    CeaserCipher.createsBruteforcedFileFromString(CeaserCipher.bruteForce(CeaserCipher.convertIntoString(link)));
                } catch (IOException ex) {
                    ex.printStackTrace();
                }
                СeaserEncryptFrame.this.l6.setText("Файл раскодирован c помощью метода BRUTEFORCE, спасибо");
            }

        }
    }

    public static void main(String[] args) throws IOException {
        СeaserEncryptFrame app = new СeaserEncryptFrame("CeaserCipher");
        app.setVisible(true);
        app.setDefaultCloseOperation(3);
        app.setSize(580, 300);
        app.setResizable(false);
        app.setLocationRelativeTo((Component)null);
    }
}
