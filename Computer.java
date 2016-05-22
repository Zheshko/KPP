import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.Font;
import org.eclipse.swt.graphics.FontData;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Event;
import org.eclipse.swt.widgets.Listener;
import org.eclipse.swt.widgets.Shell;

public class Computer {
  private Button easyButton;
  private Button mediumButton;
  private Button hardButton;
  private Button autoButton;
  private Button testButton;
  private int count;
  /** Оболочка, в которой происходит отображение программы */
  private Shell shell;
  /** Дисплей, который использует оболочка */
  private Display display;
  /**
   * Строка, в кторой хранится число, загаданное игроком(или компьютером 1)
   */
  private String string1;
  /**
   * Строка, в кторой хранится число, загаданное компьютером(или компьютером 2)
   */
  private String string2;

  /**
   * Конструктор, инициализирующий переменные
   * 
   * @param display - дисплей
   * @param shell - оболочка экрана, в ней отображается программа
   */
  public Computer(Display display, Shell shell) {
    this.shell = shell;
    this.display = display;
    count = 0;
  }

  /** Метод, отображающий окно и устанавливающий обработку нажатия кнопок */
  public void Show() {
    for (Control kid : shell.getChildren()) {
      kid.dispose();
    }
    Font buttonFont = new Font(shell.getDisplay(), new FontData("Times New Roman", 14, SWT.NORMAL));
    GridLayout mainlayout = new GridLayout();
    mainlayout.numColumns = 1;
    shell.setLayout(mainlayout);
    mainlayout.marginLeft = 220;
    mainlayout.marginTop = 270;
    GridData griddataButtonEasy = new GridData();
    griddataButtonEasy.horizontalAlignment = GridData.CENTER;
    griddataButtonEasy.heightHint = 65;
    griddataButtonEasy.widthHint = 350;
    easyButton = new Button(shell, SWT.PUSH);
    easyButton.setFont(buttonFont);
    easyButton.setText("Легкий");
    easyButton.setBackground(shell.getDisplay().getSystemColor(SWT.COLOR_DARK_MAGENTA));
    easyButton.setLayoutData(griddataButtonEasy);
    GridData griddataButtonMedium = new GridData();
    griddataButtonMedium.horizontalAlignment = GridData.CENTER;
    griddataButtonMedium.heightHint = 65;
    griddataButtonMedium.widthHint = 350;
    mediumButton = new Button(shell, SWT.PUSH);
    mediumButton.setText("Средний");
    mediumButton.setFont(buttonFont);
    mediumButton.setBackground(shell.getDisplay().getSystemColor(SWT.COLOR_DARK_MAGENTA));
    mediumButton.setLayoutData(griddataButtonMedium);
    GridData griddataButtonHard = new GridData();
    griddataButtonHard.horizontalAlignment = GridData.CENTER;
    griddataButtonHard.heightHint = 65;
    griddataButtonHard.widthHint = 350;
    hardButton = new Button(shell, SWT.PUSH);
    hardButton.setText("Тяжелый");
    hardButton.setFont(buttonFont);
    hardButton.setBackground(shell.getDisplay().getSystemColor(SWT.COLOR_DARK_MAGENTA));
    hardButton.setLayoutData(griddataButtonHard);
    GridData griddataButtonAuto = new GridData();
    griddataButtonAuto.horizontalAlignment = GridData.CENTER;
    griddataButtonAuto.heightHint = 65;
    griddataButtonAuto.widthHint = 350;
    autoButton = new Button(shell, SWT.PUSH);
    autoButton.setText("Автоматичексий");
    autoButton.setFont(buttonFont);
    autoButton.setBackground(shell.getDisplay().getSystemColor(SWT.COLOR_DARK_MAGENTA));
    autoButton.setLayoutData(griddataButtonAuto);
    GridData griddataTest = new GridData();
    griddataTest.horizontalAlignment = GridData.CENTER;
    griddataTest.heightHint = 65;
    griddataTest.widthHint = 350;
    testButton = new Button(shell, SWT.PUSH);
    testButton.setText("Тестировка");
    testButton.setFont(buttonFont);
    testButton.setBackground(shell.getDisplay().getSystemColor(SWT.COLOR_DARK_MAGENTA));
    testButton.setLayoutData(griddataTest);
    setListeners();
    shell.pack();
    shell.setSize(820, 920);
  }

  private void setListeners() {
    Listener listener = new Listener() {
      public void handleEvent(Event event) {
        if (event.widget == easyButton) {
          Number number = new Number(display, shell, 2);
          number.Show();
        } else if (event.widget == mediumButton) {
          Number number = new Number(display, shell, 3);
          number.Show();
        } else if (event.widget == hardButton) {
          Number number = new Number(display, shell, 4);
          number.Show();
        } else if (event.widget == autoButton) {
          string1 = Number.CreateBotNumber();
          string2 = Number.CreateBotNumber();
          ComputerPlay computerPlay = new ComputerPlay(display, shell, string1, string2, 5);
          computerPlay.Show();
          Runnable timer = new Runnable() {
            @Override
            public void run() {
              int win = computerPlay.autoBot();
              if (win == 0)
                display.timerExec(500, this);
              else {
                Winner winner = new Winner(display, shell, win, 5);
                winner.Show();
              }
            }
          };
          display.timerExec(500, timer);
        } else if (event.widget == testButton) {
          restartTest();
        }
      }
    };
    easyButton.addListener(SWT.Selection, listener);
    mediumButton.addListener(SWT.Selection, listener);
    hardButton.addListener(SWT.Selection, listener);
    autoButton.addListener(SWT.Selection, listener);
    testButton.addListener(SWT.Selection, listener);
  }

  private void restartTest() {
    if (count < 1000) {
      string1 = Number.CreateBotNumber();
      string2 = Number.CreateBotNumber();
      ComputerPlay computerPlay = new ComputerPlay(display, shell, string1, string2, 5);
      computerPlay.Show();
      Runnable timer = new Runnable() {
        @Override
        public void run() {
          int win = computerPlay.autoBot();
          if (win == 0)
            display.timerExec(1, this);
          else {
            FileClass file = new FileClass("src/Temp.txt");
            file.saveGame();
            file.closeDOS();
            count++;
            restartTest();
          }
        }
      };
      display.timerExec(1, timer);
    }
  }
}
