import java.util.Arrays;
import java.util.Random;

public class minePlacement {

	// global variables
	menuFrame m = new menuFrame();
	int boardSize = m.getSize();
	int numMines = boardSize;
	
	/*
	 * generate random minesweeper board
	 */
	public char[][] createBoard() {
		char[][] board = new char[boardSize][boardSize];
		if (boardSize == 25)
			numMines = 80;
		Random random = new Random();

		// randomize where the mines are
		for (int i = 0; i < numMines; i++) {
			int randRow = random.nextInt(boardSize);
			int randCol = random.nextInt(boardSize);

			if (board[randRow][randCol] == '*') {
				i--;
			} else {
				board[randRow][randCol] = '*';
			}

		}
		
		// count surronding mines
		for (int row = 0; row < boardSize; row++) {

			for (int col = 0; col < boardSize; col++) {

				if (board[row][col] != '*') {
					int surroundingMines = 0;
					if (row > 0 && col > 0 && board[row - 1][col - 1] == '*')
						surroundingMines++;

					if (row > 0 && board[row - 1][col] == '*')
						surroundingMines++;

					if (row > 0 && col < boardSize - 1 && board[row - 1][col + 1] == '*')
						surroundingMines++;

					if (col > 0 && board[row][col - 1] == '*')
						surroundingMines++;

					if (col < boardSize - 1 && board[row][col + 1] == '*')
						surroundingMines++;

					if (row < boardSize - 1 && col > 0 && board[row + 1][col - 1] == '*')
						surroundingMines++;

					if (row < boardSize - 1 && board[row + 1][col] == '*')
						surroundingMines++;

					if (row < boardSize - 1 && col < boardSize - 1 && board[row + 1][col + 1] == '*')
						surroundingMines++;

					board[row][col] = (char) ('0' + surroundingMines);
				}
			}
		}

		return board;
	}

	public static void main(String[] args) {

	}

}
