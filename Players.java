import java.io.FileNotFoundException;
import java.io.IOException;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;

public class Players extends GameWindow {
  private FileClass file = new FileClass("src/Temp.txt");
  /** ���������� ����� � ����� */
  private int cows;
  /** ���������� ����� � ����� */
  private int bulls;
  /** ��� ������(1-������ �����, 2-������ ����� */
  private int playerTurn = 1;
  /** ���� ������(1-������ ����� �������,2-������,3-�����) */
  private int win = 0;
  /** ���������� ����� ������� ������ */
  private String correctNumber1;
  /** ���������� ����� ������� ������ */
  private String correctNumber2;
  private Logic logic;

  /**
   * �����������, ���������������� ���������� � ��������� �������� ������
   * 
   * @param display- �������
   * @param shell- �������� ������, � ��� ������������ ���������
   * @param string1- ���������� ����� ������� ������
   * @param string2- ���������� ����� ������� ������
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
   * �����, �������, ������� ���������� ���� ���� � ������������ ����������� ������� ������
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
      playerText1.append("\n" + enteredNumberLabel.getText() + " " + bulls + "�. " + cows + "�.");
      try {
        file.writeTurn(enteredNumberLabel.getText(), bulls, cows);
      } catch (IOException e) {
        e.printStackTrace();
      }
      if (bulls == 4)
        win++;// ���� ������ 4 ����� ������������� win
      enteredNumberLabel.setText("");
      cows = 0;
      bulls = 0;
      playerTurn = 2;// �������� ���� 2��� ������
    } else// ��� 2��� ����������
    {
      playerLabel1.setFont(activePlayerFont);
      playerLabel2.setFont(passivePlayerFont);
      playerLabel1.setForeground(shell.getDisplay().getSystemColor(SWT.COLOR_RED));
      playerLabel2.setForeground(shell.getDisplay().getSystemColor(SWT.COLOR_BLACK));
      playerText2.append("\n" + enteredNumberLabel.getText() + " " + bulls + "�. " + cows + "�.");
      try {
        file.writeTurn(enteredNumberLabel.getText(), bulls, cows);
      } catch (IOException e) {
        e.printStackTrace();
      }
      enteredNumberLabel.setText("");
      playerTurn = 1;// �������� ���� 1��� ������
      if (bulls == 4)
        win += 2;
      if (win > 0) // ���� ���� �� 1 �� ������� ������ �����
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
    playerLabel1.setText("����� 1");
    playerLabel2.setText("����� 2");
  }
}
