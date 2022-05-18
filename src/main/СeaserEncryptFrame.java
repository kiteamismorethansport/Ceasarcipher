package main;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Iterator;
import java.util.List;
import java.util.Scanner;

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
        String texttoEcnrypt;
    СeaserEncryptFrame.eHandler handler = new СeaserEncryptFrame.eHandler();

        public СeaserEncryptFrame () {
            this.setLayout(new FlowLayout());
            this.b1 = new JButton("Выбрать");
            this.b2 = new JButton("Выбрать");
            this.b3 = new JButton("Зашифровать");
            this.b4 = new JButton("Расшифровать");
            this.b5 = new JButton("BRUTEFORCE");
            this.l1 = new JLabel("Введите адрес файла");
            this.l2 = new JLabel("Введите ключ шифра");
            this.l3 = new JLabel("");
            this.l4 = new JLabel("");
            this.l5 = new JLabel("");
            this.l6 = new JLabel ("");
            this.t1 = new JTextField(30);
            this.t2 = new JTextField(10);
            this.add(this.b1);
            this.add(this.l1);
            this.add(this.t1);
            this.add(this.l3);
            this.add(this.b2);
            this.add(this.l2);
            this.add(this.t2);
            this.add(this.l4);
            this.add(this.b3);
            this.add(this.l5);
            this.add(this.b4);
            this.add(this.b5);
            this.add(this.l6);
            this.b1.addActionListener(this.handler);
            this.b2.addActionListener(this.handler);
            this.b3.addActionListener(this.handler);
            this.b4.addActionListener(this.handler);
            this.b5.addActionListener(this.handler);
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
                    CeaserCipher.createsBruteforcedFileFromString(CeaserCipher.bruteforce(CeaserCipher.convertIntoString(link)));
                } catch (IOException ex) {
                    ex.printStackTrace();
                }
                СeaserEncryptFrame.this.l6.setText("Файл раскодирован c помощью метода BRUTEFORCE, спасибо");
            }

        }
    }

    public static void main(String[] args) throws IOException {
        СeaserEncryptFrame app = new СeaserEncryptFrame();
        app.setVisible(true);
        app.setDefaultCloseOperation(3);
        app.setSize(600, 300);
        app.setResizable(false);
        app.setLocationRelativeTo((Component)null);
    }
}
