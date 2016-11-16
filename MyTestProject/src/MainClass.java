import java.util.*;

public class MainClass
{
	public static final int BOARD_WIDTH = 4;
   public static final int BOARD_HEIGHT = 4;
	
	

   public MainClass() {
   	board = new int[BOARD_WIDTH][BOARD_HEIGHT];
   }
	
	public int[][] nextBoard(int difficulty)
	{
		board = new int[BOARD_WIDTH][BOARD_HEIGHT];
		nextCell(0,0);
		makeHoles(difficulty);
		return board;

	}
	
	

	public boolean nextCell(int x, int y)
	{
		int nextX = x;
		int nextY = y;
		int[] toCheck = {1,2,3,4};
		Random r = new Random();
		int tmp = 0;
		int current = 0;
		int top = toCheck.length;

   		for(int i=top-1;i>0;i--)
		{
		    current = r.nextInt(i);
		    tmp = toCheck[current];
		    toCheck[current] = toCheck[i];
		    toCheck[i] = tmp;
    	}
		
		for(int i=0;i<toCheck.length;i++)
		{
			if(legalMove(x, y, toCheck[i]))
			{
				board[x][y] = toCheck[i];
				if(x == 3)
				{
					if(y == 3)
						return true;//We're done!  Yay!
					else
					{
						nextX = 0;
						nextY = y + 1;
					}
				}
				else
				{
					nextX = x + 1;
				}
				if(nextCell(nextX, nextY))
					return true;
			}
		}
		board[x][y] = 0;
		return false;
	}
	
	

	private boolean legalMove(int x, int y, int current) {
		for(int i=0;i<4;i++) {
			if(current == board[x][i])
				return false;
		}
		for(int i=0;i<4;i++) {
			if(current == board[i][y])
				return false;
		}
		int cornerX = 0;
		int cornerY = 0;
		if(x > 1)
			if(x > 3)
				cornerX = 4;
			else
				cornerX = 2;
		if(y > 1)
			if(y > 3)
				cornerY = 4;
			else
				cornerY = 2;
		for(int i=cornerX;i<5 && i<cornerX+1;i++)
			for(int j=cornerY;j<5 && j<cornerY+1;j++)
				if(current == board[i][j])
					return false;
		return true;
	}
	
	

	public void makeHoles(int holesToMake)
	{
		

		double remainingSquares = 16;
		double remainingHoles = (double)holesToMake;
		
		for(int i=0;i<4;i++)
			for(int j=0;j<4;j++)
			{
				double holeChance = remainingHoles/remainingSquares;
				if(Math.random() <= holeChance)
				{
					board[i][j] = 0;
					remainingHoles--;
				}
				remainingSquares--;
			}
	}
	
	

	public void print()
	{
		for(int i=0;i<4;i++)
		{
			for(int j=0;j<4;j++)
				System.out.print(board[i][j] + "  ");
			System.out.println();
		}
		System.out.println();
	}
	
	public static void main(String[] args)
	{
		MainClass sg = new MainClass();
		sg.nextBoard(5);
		sg.print();
	}
	
	int[][] board;
}