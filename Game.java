import java.util.Scanner;

public class Game {
    private static final Scanner scanner = new Scanner(System.in);

    public static void startGame() {
        // Список уровней

        char[][][] levels = {
            Maze.generateLevel1(),
            Maze.generateLevel2(),
            Maze.generateLevel3()
        };

        // Переход между уровнями
        for (int i = 0; i < levels.length; i++) {
            System.out.println("Уровень " + (i + 1));
            if (!playLevel(levels[i])) {
                System.out.println("Игра завершена.");
                return;
            }
        }
        System.out.println("");
        System.out.println("==============================");
        System.out.println("Поздравляем! Вы прошли все уровни!");
        System.out.println("==============================");
        System.out.println("Хотите сыграть ещё раз?");
        System.out.println("");
    }

    // Логика одного уровня
    private static boolean playLevel(char[][] maze) {
        int playerX = 1, playerY = 1; // Начальная позиция игрока

        while (true) {
            Maze.printMaze(maze);
            System.out.println("");
            System.out.println("Введите направление (W/A/S/D): ");
            char move = getUserInput();

            int newX = playerX, newY = playerY;
            switch (move) {
                case 'W': newX--; break;
                case 'A': newY--; break;
                case 'S': newX++; break;
                case 'D': newY++; break;
                default:
                    System.out.println("Неверное направление. Попробуйте снова.");
                    continue;
            }

            // Проверка столкновения
            if (maze[newX][newY] == '#') {
                System.out.println("Вы врезались в стену!");
            } else if (maze[newX][newY] == 'E') {
                System.out.println("Вы нашли выход!");
                return true; // Завершить уровень
            } else {
                // Обновление позиции игрока
                maze[playerX][playerY] = ' '; // Очистить старую позицию
                playerX = newX;
                playerY = newY;
                maze[playerX][playerY] = 'P'; // Новая позиция игрока
            }
        }
    }

    // Метод для считывания ввода
    private static char getUserInput() {
        try {
            return scanner.nextLine().toUpperCase().charAt(0);
        } catch (Exception e) {
            return ' ';
        }
    }
}
