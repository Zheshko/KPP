import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Random;

import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;

public class ComputerPlay extends GameWindow {
  private FileClass file = new FileClass("src/Temp.txt");
  /** Количество коров в числе */
  private int cows;
  /** Количество быков в числе */
  private int bulls;
  private int turn = 1;
  /**
   * Флаг победы(1-игрок(компьютер 1) выиграл,2-выиграл компьютер(компьютер 2),3-ничья)
   */
  private int win = 0;
  /**
   * Режим игры(2-легкий уровень сложности,3-средний уровень сложности,4-тяжелый уровень сложности,
   * 5-автоматический режим)
   */
  private int mode;
  /**
   * Массив всех возможных чисел с неповторяющимися числами игрока(или компьютера 1, в зависимости
   * от режима)
   */
  private static String massiveOfNumbers1[];
  /**
   * Массив всех возможных чисел с неповторяющимися числами компьютера(или компьютера 2, в
   * зависимости от режима)
   */
  private static String massiveOfNumbers2[];
  /** Загаданное число игрока(или компьютера 1, в зависимости от режима) */
  private String correctPlayerNumber;
  /**
   * Загаданное число компьютера(или компьютера 2, в зависимости от режима)
   */
  private String correctBotNumber;
  /** Число для хода игрока(или компьютера 1, в зависимости от режима) */
  private String botNumber1;
  /** Число для хода компьютера(или компьютера 2, в зависимости от режима) */
  private String botNumber2;
  private Logic logic;

  /**
   * Конструктор, инициализирующий переменные и очищающий оболочку экрана
   * 
   * @param display- дисплей
   * @param shell- оболочка экрана, в ней отображается программа
   * @param string1- загаданное число первого игрока
   * @param string2- загаданное число второго игрока
   * @param n- режим игры
   */
  public ComputerPlay(Display display, Shell shell, String string1, String string2, int n) {
    massiveOfNumbers1 = CreateMassive();
    if (n == 5)
      massiveOfNumbers2 = CreateMassive();
    for (Control kid : shell.getChildren()) {
      kid.dispose();
    }
    super.loadScreen(display, shell);
    this.mode = n;
    correctPlayerNumber = string1;
    correctBotNumber = string2;
    try {
      file.openToWrite();
      file.writeMode(mode);
    } catch (FileNotFoundException e) {
      e.printStackTrace();
    } catch (IOException e) {
      e.printStackTrace();
    }
  }

  /**
   * Метод для анализа, подходит ли число из массива всех возможных чисел с неповторяющимися цифрами
   * для легкого уровня сложности
   */
  private boolean easyBot(String masNumber) {
    int counter = 0, k;
    for (k = 0; k < 4; k++)
      if (masNumber.toCharArray()[k] == botNumber1.toCharArray()[k])
        counter++;
    if (counter != bulls)
      return false;
    return true;
  }

  /**
   * Метод для анализа, подходит ли число из массива всех возможных чисел с неповторяющимися цифрами
   * для среднего уровня сложности
   */
  private boolean middleBot(String masNumber) {
    int counter = 0, k;
    for (k = 0; k < 4; k++)
      if (masNumber.toCharArray()[k] == botNumber1.toCharArray()[k])
        counter++;
    if (counter != bulls)
      return false;
    counter = 0;
    for (k = 0; k < 4; k++)
      if ((masNumber.toCharArray()[k] != botNumber1.toCharArray()[k])
          && (masNumber.contains(botNumber1.substring(k, k + 1))))
        counter++;
    if (counter < cows)
      return false;
    return true;
  }

  /**
   * Метод для анализа, подходит ли число из массива всех возможных чисел с неповторяющимися цифрами
   * для тяжелого уровня сложности
   */
  private boolean hardBot(String masNumber) {
    int counter = 0, k;
    for (k = 0; k < 4; k++)
      if (masNumber.toCharArray()[k] == botNumber1.toCharArray()[k])
        counter++;
    if (counter != bulls)
      return false;
    counter = 0;
    for (k = 0; k < 4; k++)
      if ((masNumber.toCharArray()[k] != botNumber1.toCharArray()[k])
          && (masNumber.contains(botNumber1.substring(k, k + 1))))
        counter++;
    if (counter != cows)
      return false;
    return true;
  }

  /**
   * Метод для анализа, подходит ли число из массива всех возможных чисел с неповторяющимися цифрами
   * для автоматического режима
   */
  private boolean hardBot(String masNumber, String botNumber) {
    int counter = 0, k;
    for (k = 0; k < 4; k++)
      if (masNumber.toCharArray()[k] == botNumber.toCharArray()[k])
        counter++;
    if (counter != bulls)
      return false;
    counter = 0;
    for (k = 0; k < 4; k++)
      if ((masNumber.toCharArray()[k] != botNumber.toCharArray()[k])
          && (masNumber.contains(botNumber.substring(k, k + 1))))
        counter++;
    if (counter != cows)
      return false;
    return true;
  }

  /**
   * Метод, в которой реализуется игра между двумя ботами
   * 
   * @return возвращает значение флага победы
   */
  public int autoBot() {
    if (turn == 1) {
      playerLabel1.setFont(activePlayerFont);
      playerLabel2.setFont(passivePlayerFont);
      playerLabel1.setForeground(shell.getDisplay().getSystemColor(SWT.COLOR_RED));
      playerLabel2.setForeground(shell.getDisplay().getSystemColor(SWT.COLOR_BLACK));
      botNumber1 = BotNumber(massiveOfNumbers1);
      logic = new Logic(botNumber1.toCharArray(), turn, correctPlayerNumber, correctBotNumber);
      Thread thread = new Thread(logic);
      thread.start();
      try {
        thread.join();
      } catch (InterruptedException e) {
        e.printStackTrace();
      }
      cows = logic.getCows();
      bulls = logic.getBulls();
      for (int j = 0; j < 5040; j++) {
        if (massiveOfNumbers1[j] != "0" && !hardBot(massiveOfNumbers1[j], botNumber1))
          massiveOfNumbers1[j] = "0";
      }
      playerText1.append("\n" + botNumber1 + " " + bulls + "б. " + cows + "к.");
      enteredNumberLabel.setText(botNumber1);
      try {
        file.writeTurn(botNumber1, bulls, cows);
      } catch (IOException e) {
        e.printStackTrace();
      }
      if (bulls == 4)
        win++;
      cows = 0;
      bulls = 0;
      turn = 2;
    } else {
      playerLabel2.setForeground(shell.getDisplay().getSystemColor(SWT.COLOR_RED));
      playerLabel1.setForeground(shell.getDisplay().getSystemColor(SWT.COLOR_BLACK));
      playerLabel2.setFont(activePlayerFont);
      playerLabel1.setFont(passivePlayerFont);
      botNumber2 = BotNumber(massiveOfNumbers2);// ход компьютера в
                                                // botNumber
      logic = new Logic(botNumber2.toCharArray(), turn, correctPlayerNumber, correctBotNumber);
      Thread thread = new Thread(logic);
      thread.start();
      try {
        thread.join();
      } catch (InterruptedException e) {
        e.printStackTrace();
      }
      cows = logic.getCows();
      bulls = logic.getBulls();
      playerText2.append("\n" + botNumber2 + " " + bulls + "б. " + cows + "к.");
      enteredNumberLabel.setText(botNumber2);
      try {
        file.writeTurn(botNumber2, bulls, cows);
      } catch (IOException e) {
        e.printStackTrace();
      }
      for (int j = 0; j < 5040; j++) {
        if (massiveOfNumbers2[j] != "0" && !hardBot(massiveOfNumbers2[j], botNumber2))
          massiveOfNumbers2[j] = "0";
      }
      turn = 1;
      if (bulls == 4)
        win += 2;
      if (win == 0) {
        cows = 0;
        bulls = 0;
      }
      return win;
    }
    return 0;
  }

  /**
   * Метод создания массива всех возможных четырехзначных чисел с неповторяющимися цифрами
   */
  private String[] CreateMassive() {
    String[] mas = new String[5040];
    int number[], temp, j = 0;
    number = new int[4];
    for (int i = 0; i < 10000; i++) {
      temp = i;
      number[0] = temp % 10;
      temp /= 10;
      number[1] = temp % 10;
      temp /= 10;
      number[2] = temp % 10;
      temp /= 10;
      number[3] = temp % 10;
      if (number[0] != number[1] && number[0] != number[2] && number[0] != number[3]
          && number[1] != number[2] && number[1] != number[3] && number[2] != number[3]) {
        mas[j] = String.valueOf(number[3]) + String.valueOf(number[2]) + String.valueOf(number[1])
            + String.valueOf(number[0]);
        j++;
      }
    }
    return mas;
  }

  /** Метод определяющий ход компьютера(выбор возможного числа из массива) */
  private String BotNumber(String[] mas) {
    Random random = new Random();
    int chosen;
    while (true) {
      chosen = random.nextInt(5040);
      if (mas[chosen] != "0") {
        return mas[chosen];
      }
    }
  }

  @Override
  protected void extraSettings() {
    if (mode == 5) {
      playerLabel1.setText("Компьютер 1");
      playerLabel2.setText("Компьютер 2");
      button0.setEnabled(false);
      button1.setEnabled(false);
      button2.setEnabled(false);
      button3.setEnabled(false);
      button4.setEnabled(false);
      button5.setEnabled(false);
      button6.setEnabled(false);
      button7.setEnabled(false);
      button8.setEnabled(false);
      button9.setEnabled(false);
      buttonDelete.setEnabled(false);
      buttonTurn.setEnabled(false);
    } else {
      playerLabel1.setText("Вы");
      playerLabel2.setText("Компьютер");
    }
  }

  @Override
  protected void MakeTurn() {
    turn = 1;
    logic = new Logic(enteredNumberLabel.getText().toCharArray(), turn, correctPlayerNumber,
        correctBotNumber);
    Thread thread = new Thread(logic);
    thread.start();
    try {
      thread.join();
    } catch (InterruptedException e1) {
      e1.printStackTrace();
    }
    cows = logic.getCows();
    bulls = logic.getBulls();
    playerLabel2.setFont(activePlayerFont);
    playerLabel1.setFont(passivePlayerFont);
    playerLabel2.setForeground(shell.getDisplay().getSystemColor(SWT.COLOR_RED));
    playerLabel1.setForeground(shell.getDisplay().getSystemColor(SWT.COLOR_BLACK));
    playerText1.append("\n" + enteredNumberLabel.getText() + " " + bulls + "б. " + cows + "к.");
    try {
      file.writeTurn(enteredNumberLabel.getText(), bulls, cows);
    } catch (IOException e1) {
      e1.printStackTrace();
    }
    if (bulls == 4)
      win++;
    turn = 2;
    enteredNumberLabel.setText("");
    digitsInLabel = 0;
    botNumber1 = BotNumber(massiveOfNumbers1);// ход компьютера в botNumber
    logic = new Logic(botNumber1.toCharArray(), turn, correctPlayerNumber, correctBotNumber);
    thread = new Thread(logic);
    thread.start();
    try {
      thread.join();
    } catch (InterruptedException e1) {
      e1.printStackTrace();
    }
    cows = logic.getCows();
    bulls = logic.getBulls();
    if (mode == 2)// если уровень легкий
      for (int j = 0; j < 5040; j++) {
        if (massiveOfNumbers1[j] != "0" && !easyBot(massiveOfNumbers1[j]))
          massiveOfNumbers1[j] = "0";// на место ставится 0
      }
    else if (mode == 3)// если уровень средний(аналогично легкому)
      for (int j = 0; j < 5040; j++) {
        if (massiveOfNumbers1[j] != "0" && !middleBot(massiveOfNumbers1[j]))
          massiveOfNumbers1[j] = "0";
      }
    else if (mode == 4)// если уровень тяжелый(аналогично легкому)
      for (int j = 0; j < 5040; j++) {
        if (massiveOfNumbers1[j] != "0" && !hardBot(massiveOfNumbers1[j]))
          massiveOfNumbers1[j] = "0";
      }
    Runnable timer = new Runnable() {
      @Override
      public void run() {
        playerText2.append("\n" + botNumber1 + " " + bulls + "б. " + cows + "к.");
        try {
          file.writeTurn(botNumber1, bulls, cows);
        } catch (IOException e1) {
          e1.printStackTrace();
        }
        playerLabel1.setFont(activePlayerFont);
        playerLabel2.setFont(passivePlayerFont);
        playerLabel1.setForeground(shell.getDisplay().getSystemColor(SWT.COLOR_RED));
        playerLabel2.setForeground(shell.getDisplay().getSystemColor(SWT.COLOR_BLACK));
      }
    };
    if (bulls == 4)
      win += 2;
    display.timerExec(1000, timer);
    if (win > 0) {
      Runnable winTimer = new Runnable() {
        @Override
        public void run() {
          Winner winner = new Winner(display, shell, win, mode);
          winner.Show();
        }
      };
      display.timerExec(3000, winTimer);
    }
  }
}
