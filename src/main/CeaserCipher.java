package main;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;
import java.util.Scanner;

public class CeaserCipher {
    public static String alphabet = "абвгдеёжзийклмнопрстуфхцчшщъыьэюя.,”:-!? ";
    public static String [] listofwords =
            {"год", "человек", "время", "дело", "жизнь", "день", "рука", "раз", "работа", "слово",
            "место", "лицо", "друг", "глаз", "вопрос", "дом", "сторона", "мир", "случай", "ребенок",
            "сила", "конец", "вид", "система", "часть", "город", "отношение", "женщина", "деньги",
            "земля", "машина", "вода", "отец", "проблема", "час", "право", "нога", "решение", "дверь",
            "образ", "история", "власть", "закон", "война", "бог", "голос", "тысяча", "книга",
            "возможность", "результат", "ночь", "стол", "имя", "область", "статья", "число", "компания",
            "народ", "жена", "группа", "развитие", "процесс", "суд", "условие", "средство", "начало",
            "свет", "пора", "путь", "душа", "уровень", "форма", "связь", "минута", "улица", "вечер",
            "качество", "мысль", "дорога", "мать", "действие", "месяц", "государство", "язык",
            "любовь", "взгляд", "мама", "век", "школа", "цель", "общество", "деятельность", "организация",
            "президент", "комната", "порядок", "момент", "театр"};

     //This method converts txt. file into a String
    public static String convertIntoString ( String link) throws IOException {
       Path path = Path.of(link);
        List<String> list = Files.readAllLines(path);
        String messagetoencrypt = "";
        String separator = System.getProperty("line.separator");
        for (String input : list) {
            messagetoencrypt = messagetoencrypt + input + separator;
        }
        return messagetoencrypt;
    }
    //This method creates encrypted txt file from the String
    public static void createsEncryptedFileFromString(String encryptedtext) throws IOException {
        Path path = Path.of("C:\\Users\\фермер\\IdeaProjects\\Ceasarcipher\\src\\main\\textfiles\\encryptedfile.txt");
        Files.write(path, encryptedtext.getBytes(StandardCharsets.UTF_8));
    }

    public static void createsDecryptedFileFromString(String encryptedtext) throws IOException {
        Path path = Path.of("C:\\Users\\фермер\\IdeaProjects\\Ceasarcipher\\src\\main\\textfiles\\decryptedfile.txt");
        Files.write(path, encryptedtext.getBytes(StandardCharsets.UTF_8));
    }

    public static void createsBruteforcedFileFromString(String encryptedtext) throws IOException {
        Path path = Path.of("C:\\Users\\фермер\\IdeaProjects\\Ceasarcipher\\src\\main\\textfiles\\bruteforcedfile.txt");
        Files.write(path, encryptedtext.getBytes(StandardCharsets.UTF_8));
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
            for (int k = 0; k<listofwords.length; k++){
                if (bruteforcedMessage.contains(listofwords[k])){
                    break;

                }
            }
            if (bruteforcedMessage.contains(". ") && bruteforcedMessage.contains(", ")) {
                break;

            }
            else
            bruteforcedMessage = "";
            key++;
        }
        return bruteforcedMessage;
    }
}
