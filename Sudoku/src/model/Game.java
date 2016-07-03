package model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Observable;

public class Game extends Observable{

	private int selectedNumber;
	private boolean isHelp;
	private int[][] solution;       //whole
	private int[][] game;           //user can input
	private boolean[][] check;
	
	/**
	 * constructor
	 */
	public Game() {
		newGame();
		this.check=new boolean[9][9];
		this.isHelp=false;
	}
	
	/**
	 * btnNew
	 */
	public void newGame() {
		solution = generateSolution(new int[9][9], 0);
        game = generateGame(copy(solution));
		setChanged();
		notifyObservers(UpdateAction.NEW_GAME);
	}
	
	/**
	 * btnCheck
	 */
    public void checkGame() {
    	//TODO
    	//selectedNumber=0;
    	for (int i = 0; i < 9; i++) {
			for (int j = 0; j < 9; j++) {
				if (game[i][j]==solution[i][j]) {
					check[i][j]=true;
				}else{
					check[i][j]=false;
				}
			}
		}
		setChanged();
		notifyObservers(UpdateAction.CHECK);
	}
    
    /**
     * determine the color of a blank when check
     */
    public boolean checkOk(int x,int y){
		return check[x][y];
	}
    
	private int[][] generateSolution(int[][] game, int index) {
		if (index > 80)
            return game;

        int x = index % 9;
        int y = index / 9;

        List<Integer> numbers = new ArrayList<Integer>();
        for (int i = 1; i <= 9; i++) numbers.add(i);
        Collections.shuffle(numbers);

        while (numbers.size() > 0) {
            int number = getNextPossibleNum(game, x, y, numbers);
            if (number == -1)
                return null;

            game[y][x] = number;
            int[][] tmpGame = generateSolution(game, index + 1);
            if (tmpGame != null)
                return tmpGame;
            game[y][x] = 0;
        }
		return null;
	}

	private int[][] generateGame(int[][] copy) {
		 List<Integer> positions = new ArrayList<Integer>();
	     for (int i = 0; i < 81; i++)
	        positions.add(i);
	     Collections.shuffle(positions);
	        
	     while (positions.size() > 0) {
	            int position = positions.remove(0);
	            int x = position % 9;
	            int y = position / 9;
	            int temp = game[y][x];
	            game[y][x] = 0;

	            if (!isValid(game, 0, new int[] { 0 }))
	                game[y][x] = temp;
	        }

	        return game;
	}

	/**
	 * put the number in the blank
	 */
	public int getNumber(int x,int y){
		return game[x][y];
	}
	public void setNumber(int x,int y,int number) {
		game[x][y]=number;
	}
	
	/**
	 *don't know its use temperarily 
	 */
	public int getNextPossibleNum(int[][] game,int x,int y,List<Integer> number){
		while (number.size() > 0) {
            int num = number.remove(0);
            if (xIsOk(game, y, num) && yIsOk(game, x, num) && isOkInBlock(game, x, y, num))
                return num;
        }
        return -1;
	}
	
	/**
	 * check if the game has solution
	 */
	private boolean isValid(int[][] gameToCheck,int index,int[] solutionNumbers){
		if(index>80){
			//TODO
			//return ++solutionNumbers[0]==1;
		}
		
		int x=index%9;
		int y=index/9;
		
		if(!isValid(gameToCheck, index+1, solutionNumbers)){
			return false;
		}
		else if(gameToCheck[x][y]==0){
			List<Integer> numbers = new ArrayList<Integer>();
            for (int i = 1; i <= 9; i++)
                numbers.add(i);

            while (numbers.size() > 0) {
                int number = getNextPossibleNum(game, x, y, numbers);
                if (number == -1)
                    break;
                game[y][x] = number;

                if (!isValid(game, index + 1,solutionNumbers)) {
                    game[y][x] = 0;
                    return false;
                }
                game[y][x] = 0;
            }
		}
		
		return true;
	}

	/**
	 * determine the blank that can be a candidate
	 */
	public boolean candidateOk(int x,int y){
		return game[x][y]==0 && xIsOk(game, x, selectedNumber) && yIsOk(game, y, selectedNumber) && isOkInBlock(game, x, y, selectedNumber);
	}
	public boolean xIsOk(int[][] game,int x,int number){
		for (int y = 0; y < 9; y++) {
			if(game[x][y]==number){
				return false;
			}
		}
		return true;
	}
	public boolean yIsOk(int[][] game,int y,int number){
		for (int x = 0; x < 9; x++) {
			if(game[x][y]==number){
				return false;
			}
		}
		return true;
	}
	public boolean isOkInBlock(int[][] game,int x,int y,int number){
		int x1=x<3 ? 0 : x<6 ? 3 : 6;
		int y1=y<3 ? 0 : y<6 ? 3 : 6;
		for (int i = x1; i < x1+3; i++) {
			for (int j = y1; j < y1+3; j++) {
				if (game[i][j]==number) {
					return false;
				}
			}
		}
		return true;
	}
	
	
	public int getSelectedNumber() {
		return selectedNumber;
	}
	public void setSelectedNumber(int selectedNumber) {
		this.selectedNumber = selectedNumber;
		setChanged();
		notifyObservers(UpdateAction.SELECTED_NUMBER);
	}
	
	public boolean isHelp() {
		return isHelp;
	}
	public void setHelp(boolean isHelp) {
		this.isHelp=isHelp;
		setChanged();
		notifyObservers(UpdateAction.HELP);
	}
	
	
	private int[][] copy(int[][] game) {
		int[][] copy=new int[9][9];
		for (int i = 0; i < 9; i++) {
			for (int j = 0; j < 9; j++) {
				copy[i][j]=game[i][j];
			}
		}
		return copy;
	}
	
}
