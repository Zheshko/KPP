import java.io.FileNotFoundException;
import java.io.IOException;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;

public class Players extends GameWindow {
  private FileClass file = new FileClass("src/Temp.txt");
  /**  оличество коров в числе */
  private int cows;
  /**  оличество быков в числе */
  private int bulls;
  /** ’од игрока(1-первый игрок, 2-второй игрок */
  private int playerTurn = 1;
  /** ‘лаг победы(1-первый игрок выиграл,2-второй,3-ничь€) */
  private int win = 0;
  /** «агаданное число первого игрока */
  private String correctNumber1;
  /** «агаданное число второго игрока */
  private String correctNumber2;
  private Logic logic;

  /**
   *  онструктор, инициализирующий переменные и очищающий оболочку экрана
   * 
   * @param display- дисплей
   * @param shell- оболочка экрана, в ней отображаетс€ программа
   * @param string1- загаданное число первого игрока
   * @param string2- загаданное число второго игрока
   */
  public Players(Display display, Shell shell, String string1, String string2) {
    for (Control kid : shell.getChildren()) {
      kid.dispose();
    }
    super.loadScreen(display, shell);
    correctNumber1 = string1;
    correctNumber2 = string2;
    try {
      file.openToWrite();
      file.writeMode(1);
    } catch (FileNotFoundException e) {
      e.printStackTrace();
    } catch (IOException e) {
      e.printStackTrace();
    }
  }

  /**
   * ћетод, который, который отображает окно игры и усанавливает обработчики нажати€ кнопок
   */
  protected void MakeTurn() {
    logic = new Logic(enteredNumberLabel.getText().toCharArray(), playerTurn, correctNumber1,
        correctNumber2);
    Thread thread = new Thread(logic);
    thread.start();
    try {
      thread.join();
    } catch (InterruptedException e) {
      e.printStackTrace();
    }
    cows = logic.getCows();
    bulls = logic.getBulls();
    if (playerTurn == 1) {
      playerLabel2.setFont(activePlayerFont);
      playerLabel1.setFont(passivePlayerFont);
      playerLabel2.setForeground(shell.getDisplay().getSystemColor(SWT.COLOR_RED));
      playerLabel1.setForeground(shell.getDisplay().getSystemColor(SWT.COLOR_BLACK));
      playerText1.append("\n" + enteredNumberLabel.getText() + " " + bulls + "б. " + cows + "к.");
      try {
        file.writeTurn(enteredNumberLabel.getText(), bulls, cows);
      } catch (IOException e) {
        e.printStackTrace();
      }
      if (bulls == 4)
        win++;// если угадал 4 цифры увеличиваетс€ win
      enteredNumberLabel.setText("");
      cows = 0;
      bulls = 0;
      playerTurn = 2;// передача хода 2ому игроку
    } else// дл€ 2ого аналогично
    {
      playerLabel1.setFont(activePlayerFont);
      playerLabel2.setFont(passivePlayerFont);
      playerLabel1.setForeground(shell.getDisplay().getSystemColor(SWT.COLOR_RED));
      playerLabel2.setForeground(shell.getDisplay().getSystemColor(SWT.COLOR_BLACK));
      playerText2.append("\n" + enteredNumberLabel.getText() + " " + bulls + "б. " + cows + "к.");
      try {
        file.writeTurn(enteredNumberLabel.getText(), bulls, cows);
      } catch (IOException e) {
        e.printStackTrace();
      }
      enteredNumberLabel.setText("");
      playerTurn = 1;// передача хода 1ому игроку
      if (bulls == 4)
        win += 2;
      if (win > 0) // если хот€ бы 1 из игроков угадал число
      {
        Runnable winTimer = new Runnable() {
          @Override
          public void run() {
            Winner winner = new Winner(display, shell, win, 1);
            winner.Show();
          }
        };
        display.timerExec(1000, winTimer);
      }
    }
    digitsInLabel = 0;
  }

  @Override
  protected void extraSettings() {
    playerLabel1.setText("»грок 1");
    playerLabel2.setText("»грок 2");
  }
}
