public class TicTacToe {

    public String[][] defaultField = {{"-","-","-"},{"-","-","-"},{"-","-","-"}};
    public String[][] field = {{"-","-","-"},{"-","-","-"},{"-","-","-"}};
    public static String currentPlayer = "X";
    public boolean gameIsActive = false;

    public TicTacToe() {
        this.newGame();
    }

    public void newGame() {
        currentPlayer = "X";
        this.gameIsActive = true;
        this.field = this.defaultField;
    }

    public String[][] getField() {
        return this.field;
    }

    public String makeMove(int x, int y) {
        x = x - 1;
        y = y - 1;

        if (!this.gameIsActive) {
            return "Game was ended";
        }

        if (this.field[x][y].equals("-")) {
            this.field[x][y] = currentPlayer;
            String checkGameResult = this.checkGame();
            if (checkGameResult.equals("null")) {
                return "Move completed";
            } else if (checkGameResult.equals("D")) {
                return "Draw";
            } else {
                return String.format("Player %s won", checkGameResult);
            }
        } else {
            return String.format("Cell %s, %s is already occupied", x, y);
        }
    }

    public String checkGame() {
        int[][][] winnerPositions = {
                {{1,1},{1,2},{1,3}},
                {{2,1},{2,2},{2,3}},
                {{3,1},{3,2},{3,3}},

                {{1,1},{2,1},{3,1}},
                {{1,2},{2,2},{3,2}},
                {{1,3},{2,3},{3,3}},

                {{1,1},{2,2},{3,3}},
                {{3,1},{2,2},{1,3}},
        };

        String[] current = {"-","-","-"};

        for (int i = 0; i < winnerPositions.length; i++) {
            current = new String[]{"-", "-", "-"};

            for (int j = 0; j < 3; j++) {
                current[j] = this.field[winnerPositions[i][j][0] - 1][winnerPositions[i][j][1] - 1];
            }

            if (current[0].equals(current[1]) && current[1].equals(current[2]) && !current[0].equals("-")) {
                this.gameIsActive = false;
                return currentPlayer;
            }
        }

        if (this.checkDraw()) {
            this.gameIsActive = false;
            return "D";
        }

        currentPlayer = currentPlayer.equals("X") ? "0" : "X";

        return "null";
    }

    public boolean checkDraw() {
        boolean result = true;
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (this.field[i][j].equals("-")) {
                    result = false;
                }
            }
        }

        return result;
    }
}
