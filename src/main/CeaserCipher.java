package main;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;
import java.util.Locale;
import java.util.Scanner;

public class CeaserCipher {
    public static String alphabet = "абвгдеёжзийклмнопрстуфхцчшщъыьэюя.,”:-!? ";

     //This method converts txt. file into a String
    public static String convert ( String link) throws IOException {
       Path path = Path.of(link);
        List<String> list = Files.readAllLines(path);
        String messagetoencrypt = "";
        for (String input : list) {
            messagetoencrypt += input + "\n";
        }
        return messagetoencrypt;
    }

    //This method encrypts the String using Ceaser's cipher
    public static String encrypt (String messagetoencrypt, int keytoencrypt) {
        String encryptedMessage = "";
        String messagetoencryptlowercase = messagetoencrypt.toLowerCase();

        for (int i = 0; i < messagetoencryptlowercase.length(); i++) {
            int positionOfChar = alphabet.indexOf(messagetoencryptlowercase.charAt(i));
            int encryptedIndexofChar = (keytoencrypt + positionOfChar) % 41;
            char encryptedChar = alphabet.charAt(encryptedIndexofChar);
            encryptedMessage += encryptedChar;
        }
        return encryptedMessage;
    }

    //Method to decrypt the String using Ceasers cipher
    public static String decrypt(String messagetodecrypt, int keytodecrypt) {
        String decryptedMessage = "";
        for (int i = 0; i < messagetodecrypt.length(); i++) {
            int positionOfChar = alphabet.indexOf(messagetodecrypt.charAt(i));
            int decryptedIndexofChar = (positionOfChar - keytodecrypt) % 41;
            if (decryptedIndexofChar < 0) {
                decryptedIndexofChar = alphabet.length() + decryptedIndexofChar;
            }
            char decryptedChar = alphabet.charAt(decryptedIndexofChar);
            decryptedMessage += decryptedChar;
        }
        return decryptedMessage;
    }
// This method decrypts String by using a bruteforce method
    public static String bruteforce(String messagetobruteforce) {
        String bruteforcedMessage = "";
        int key = 0;
        while (key < 41) {
            for (int i = 0; i < messagetobruteforce.length(); i++) {
                int positionOfChar = alphabet.indexOf(messagetobruteforce.charAt(i));
                int bruteforcedIndexOfChar = (positionOfChar - key) % 41;
                if (bruteforcedIndexOfChar < 0) {
                    bruteforcedIndexOfChar = alphabet.length() + bruteforcedIndexOfChar;
                }
                char bruteforcedChar = alphabet.charAt(bruteforcedIndexOfChar);
                bruteforcedMessage += bruteforcedChar;
            }
            if (bruteforcedMessage.contains(". ") || bruteforcedMessage.contains(", ")) {

                break;
            }
            bruteforcedMessage = "";
            key++;
        }
        return bruteforcedMessage;
    }

    public static void main(String[] args) throws IOException {

        Scanner scan = new Scanner(System.in);
        System.out.println("Введите ссылку на файл, который хотите закодировать: ");
        String s = scan.nextLine();

        System.out.println("Введите ключ для кодировки");
        int k = scan.nextInt();
        System.out.println("Спасибо, ваша строка закодирована");
        String d = encrypt(convert(s), k);
        System.out.println(d);
    }
}
