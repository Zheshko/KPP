public class Logic implements Runnable {
  private int bulls;
  private int cows;
  private char[] compareString;
  private int playerTurn;
  private String correctNumber1;
  private String correctNumber2;

  public Logic(char[] compareString, int playerTurn, String correctNumber1, String correctNumber2) {
    this.compareString = compareString;
    this.playerTurn = playerTurn;
    this.correctNumber1 = correctNumber1;
    this.correctNumber2 = correctNumber2;
  }

  private void compare() {// сравнение хода игрока с загаданным числом
    bulls = 0;
    cows = 0;
    char[] correctString = null;
    if (playerTurn == 1)
      correctString = correctNumber2.toCharArray();
    else if (playerTurn == 2)
      correctString = correctNumber1.toCharArray();
    for (int j = 0; j < 4; j++) {
      for (int k = 0; k < 4; k++) {
        if (compareString[j] == correctString[k] && j == k)
          bulls++;// цифра в загаданном числе есть и стоит на своем
                  // месте-быки
        else if (compareString[j] == correctString[k] && j != k)
          cows++;// цифра в загаданном числе есть,но стоит не на своем
                 // месте-коровы
      }
    }
  }

  public int getBulls() {
    return bulls;
  }

  public int getCows() {
    return cows;
  }

  @Override
  public void run() {
    compare();
  }
}
