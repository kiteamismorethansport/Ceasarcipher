package main;

import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Locale;
import java.util.Scanner;

public class CeaserCipher {
    public static String alphabet = "абвгдеёжзийклмнопрстуфхцчшщъыьэюя.,”:-!? ";

    //This method encrypts the .txt file converted into String;
    public static String encrypt(String messagetoencrypt, int keytoencrypt) {
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

    //Method to decrypt the String taken from the .txt file
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

    public static String bruteforce(String messagegetobruteforce) {
        String bruteforcedMessage = "";
        int key = 0;
        while (key < 41) {
            for (int i = 0; i < messagegetobruteforce.length(); i++) {
                int positionOfChar = alphabet.indexOf(messagegetobruteforce.charAt(i));
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

    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        System.out.println("Введите строку для кодировки:");
        String s = scan.nextLine();
        System.out.println("Введите ключ для кодировки");
        int k = scan.nextInt();
        System.out.println("Спасибо, ваша строка закодирована");
        String d = encrypt(s, k);
        System.out.println(d);
    }
}
