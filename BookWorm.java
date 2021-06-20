import java.util.Scanner;
import java.util.regex.Pattern;

public class BookWorm {
    private static String input;

    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        input = scan.nextLine();

        int size = Integer.parseInt(scan.nextLine());
        char[][] matrix = new char[size][size];

        int[] position = new int[2];
        fillMatrix(matrix, scan, position);

        String command = scan.nextLine();
        while (!command.equals("end")) {
            switch (command) {
                case "up":
                    moveAction(matrix, position, -1, 0);
                    break;
                case "down":
                    moveAction(matrix, position, 1, 0);
                    break;
                case "left":
                    moveAction(matrix, position, 0, -1);
                    break;
                case "right":
                    moveAction(matrix, position, 0, 1);
                    break;
            }
            command = scan.nextLine();
        }

        StringBuilder builder = new StringBuilder();
        builder.append(input).append(System.lineSeparator());
        for (char[] chars : matrix) {
            builder.append(chars).append(System.lineSeparator());
        }
        System.out.println(builder);


    }

    private static void moveAction(char[][] matrix, int[] position, int rowModificator, int colModificator) {
        int row = position[0];
        int col = position[1];
        int newRow = indexCheck(row, rowModificator, matrix.length);
        int newCol = indexCheck(col, colModificator, matrix.length);
        if((row + rowModificator != -1 ) && (col + colModificator != -1)){
            if ((matrix[newRow][newCol] >= 'A' && matrix[newRow][newCol] != 'P' && matrix[newRow][newCol] <= 'Z') || (matrix[newRow][newCol] >= 'a' && matrix[newRow][newCol] <= 'z')) {
                input += matrix[newRow][newCol];
            }

        }
        position[0] = newRow;
        position[1] = newCol;
        matrix[row][col] = '-';
        matrix[newRow][newCol] = 'P';
}

    private static int indexCheck(int index, int modifier, int bound) {
        index = index + modifier;
        if (index < 0 || index >= bound) {
            if(input.length() > 0){
                input = input.substring(0, input.length() - 1);
            }
            return index - modifier;
        }
        return index;
    }

    private static void fillMatrix(char[][] matrix, Scanner scan, int[] position) {
        for (int row = 0; row < matrix.length; row++) {
            String line = scan.nextLine();
            matrix[row] = line.toCharArray();
            if (line.contains("P")) {
                position[0] = row;
                position[1] = line.indexOf("P");
            }
        }
    }
}
