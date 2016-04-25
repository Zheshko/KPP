
public class Logic implements Runnable{
	private int bulls;
	private int cows;
	private char[] compareString;
	private int playerTurn;
	private String correctNumber1;
	private String correctNumber2;
	
	public Logic(char[] compareString, int playerTurn, String correctNumber1,
			String correctNumber2)
	{
		this.compareString = compareString;
		System.out.println("compare string = "+compareString.toString());
		this.playerTurn = playerTurn;
		this.correctNumber1 = correctNumber1;
		System.out.println("correctnumber1 string = "+correctNumber1);
		this.correctNumber2 = correctNumber2;
		System.out.println("correctnumber2 string = "+correctNumber2);
	}
	
	private void compare(){//сравнение хода игрока с загаданным числом
		System.out.println("Start compare");
		bulls = 0;
		cows = 0;
		char[] correctString = null;
		if(playerTurn==1) correctString=correctNumber2.toCharArray();
		else if(playerTurn==2) correctString=correctNumber1.toCharArray();
		for(int j=0;j<4;j++)
		{
			for(int k=0;k<4;k++)
			{
			  if(compareString[j]==correctString[k] && j==k) bulls++;//цифра в загаданном числе есть и стоит на своем месте-быки
			  else if(compareString[j]==correctString[k] && j!=k) cows++;//цифра в загаданном числе есть,но стоит не на своем месте-коровы
			}
		}
		System.out.println("bulls compare - "+bulls);
		System.out.println("cows compare - "+cows);
	}

	public int getBulls() {
		System.out.println("bulls - "+bulls);
		return bulls;
	}

	public int getCows() {
		System.out.println("cows - "+cows);
		return cows;
	}

	@Override
	public void run() {
		compare();
	}
	
}
