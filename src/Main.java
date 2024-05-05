import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        while (true) {
            startGame();
        }

    }

    private static void startGame() {
        Scanner sc = new Scanner(System.in);
        boolean flag = false;

        while (!flag) {
            System.out.println("[N]ew game or [E]xit");
            String button = sc.nextLine();
            {
                if (button.equals("N") || button.equals("n")) {
                    System.out.println("Игра начата");
                    gamePlay(getRandomWordFromDictionary());
                    flag = true;
                } else if (button.equals("E") || button.equals("e")) {
                    System.out.println("Игра закончилась");
                    System.exit(0);

                } else {
                    System.out.println("Для начала игры нажмите N, для выхода E");

                }
            }
        }

    }

    private static String getPath() {
        String separator = File.separator;
        String path = "src" + separator + "dictionary" + separator + "dictionary.txt";
        return path;
    }

    private static ArrayList<String> readDictionaryFromFile() {
        Scanner scanner;
        try {
            scanner = new Scanner(new File(getPath()));
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }


        ArrayList<String> dictionaryList = new ArrayList();

        while (scanner.hasNextLine()) {
            dictionaryList.add(scanner.nextLine());
        }
        scanner.close();
        return dictionaryList;
    }

    private static String getRandomWordFromDictionary() {
        ArrayList<String> dictionaryList = readDictionaryFromFile();
        Random random = new Random();

        int index = random.nextInt(dictionaryList.size());

        String playWord = dictionaryList.get(index);

        return playWord;
    }

    private static char [] guessedLetters (String playWord) {

        char[] guessedLetters = new char[playWord.length()];

        Arrays.fill(guessedLetters, '*');
        System.out.println(guessedLetters);

        return guessedLetters;

    }

    private static void updateGuessedLetters(String playWord, char[] guessedLetters, char guess) {
        for (int i = 0; i < playWord.length(); i++) {
            if (guess == playWord.charAt(i)) {

                guessedLetters[i] = guess;

            }
        }
        System.out.println(guessedLetters);
    }

    public static boolean allLettersGuessed(char[] guessedLetters) {
        for (char letter : guessedLetters) {
            if (letter == '*') {
                return false;
            }
        }
        return true;
    }
    private static void gamePlay(String playWord) {
       char[] guessedLetters = guessedLetters(playWord);
        Scanner scanner = new Scanner(System.in);
        int mistakes = 0;
        do {
            System.out.println("Введите букву");
            String playerInput = scanner.nextLine();
            char guess = playerInput.charAt(0);
            if (playWord.contains(playerInput)) {
                drawHang(mistakes);
                System.out.println("Количество ошибок:" + mistakes);
                updateGuessedLetters(playWord,guessedLetters, guess);
                if (allLettersGuessed(guessedLetters)) {
                    System.out.println("Вы выиграли");
                    return;
                }
            } else {
                mistakes++;
                drawHang(mistakes);
                System.out.println("Неверно. Количество ошибок:" + mistakes);
                System.out.println(guessedLetters);
            }


        } while (mistakes < 6);
        System.out.println("Вы проиграли. Загаданное слово: " + playWord);
        System.out.println();
        System.out.println();

    }

    private static void drawHang(int mistakes) {

        switch (mistakes) {
            case 0:
                System.out.println("  --------");
                System.out.println("  |      |");
                System.out.println("         |");
                System.out.println("         |");
                System.out.println("         |");
                System.out.println("         |");
                System.out.println("         |");
                System.out.println(" ==========");
                break;
            case 1:
                System.out.println("  --------");
                System.out.println("  |      |");
                System.out.println("  O      |");
                System.out.println("         |");
                System.out.println("         |");
                System.out.println("         |");
                System.out.println("         |");
                System.out.println(" ==========");
                break;
            case 2:
                System.out.println("  --------");
                System.out.println("  |      |");
                System.out.println("  O      |");
                System.out.println("  |      |");
                System.out.println("         |");
                System.out.println("         |");
                System.out.println("         |");
                System.out.println(" ==========");
                break;
            case 3:
                System.out.println("  --------");
                System.out.println("  |      |");
                System.out.println("  O      |");
                System.out.println(" \\|      |");
                System.out.println("         |");
                System.out.println("         |");
                System.out.println("         |");
                System.out.println(" ==========");
                break;
            case 4:
                System.out.println("  --------");
                System.out.println("  |      |");
                System.out.println("  O      |");
                System.out.println(" \\|/     |");
                System.out.println("         |");
                System.out.println("         |");
                System.out.println("         |");
                System.out.println(" =========");
                break;
            case 5:
                System.out.println("  --------");
                System.out.println("  |      |");
                System.out.println("  O      |");
                System.out.println(" \\|/     |");
                System.out.println("  |      |");
                System.out.println(" /       |");
                System.out.println("         |");
                System.out.println(" =========");
                break;
            case 6:
                System.out.println("  --------");
                System.out.println("  |      |");
                System.out.println("  O      |");
                System.out.println(" \\|/     |");
                System.out.println("  |      |");
                System.out.println(" / \\     |");
                System.out.println("         |");
                System.out.println(" =========");
                break;

        }
    }


}



