
/**
 * Этот класс используется для вывода меню.
 * 
 * @author Anastasia
 * @version 0.1
 */
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.Font;
import org.eclipse.swt.graphics.FontData;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Event;
import org.eclipse.swt.widgets.Listener;
import org.eclipse.swt.widgets.Shell;

public class Menu {
  private Button buttonRules;
  private Button buttonReplay;
  private Button buttonComputer;
  private Button buttonPlayers;
  private FileClass file = new FileClass("src/Temp.txt");
  /** Оболочка, в которой происходит отображение программы */
  private Shell shell;
  /** Дисплей, который использует оболочка */
  private Display display;

  /**
   * Конструктор, инициализирующий переменные
   * 
   * @param display - дисплей
   * @param shell - оболочка экрана, в ней отображается программа
   */
  Menu(Display display, Shell shell) {
    this.shell = shell;
    this.display = display;
  }

  /**
   * Метод, который, который отображает окно меню и настраивает обработку кнопок
   */
  public void Show() {
    for (Control kid : shell.getChildren()) {
      kid.dispose();
    }
    InputStream is = null;
    try {
      is = Files.newInputStream(Paths.get("Images/menu.jpg"));
    } catch (IOException e) {
      e.printStackTrace();
    }
    Image backgroundImage = new Image(display, is);
    try {
      is.close();
    } catch (IOException e) {
      e.printStackTrace();
    }
    Font buttonFont = new Font(shell.getDisplay(), new FontData("Times New Roman", 14, SWT.NORMAL));
    shell.setBackgroundImage(backgroundImage);
    GridLayout mainlayout = new GridLayout();
    mainlayout.numColumns = 1;
    shell.setLayout(mainlayout);
    mainlayout.marginLeft = 220;
    mainlayout.marginTop = 315;
    GridData griddataButtonRules = new GridData();
    griddataButtonRules.horizontalAlignment = GridData.CENTER;
    griddataButtonRules.heightHint = 65;
    griddataButtonRules.widthHint = 350;
    buttonRules = new Button(shell, SWT.PUSH);
    buttonRules.setFont(buttonFont);
    buttonRules.setText("Правила игры");
    buttonRules.setBackground(shell.getDisplay().getSystemColor(SWT.COLOR_DARK_MAGENTA));
    buttonRules.setLayoutData(griddataButtonRules);
    GridData griddataButtonReplay = new GridData();
    griddataButtonReplay.horizontalAlignment = GridData.CENTER;
    griddataButtonReplay.heightHint = 65;
    griddataButtonReplay.widthHint = 350;
    buttonReplay = new Button(shell, SWT.PUSH);
    buttonReplay.setText("Сохраненные игры");
    buttonReplay.setFont(buttonFont);
    buttonReplay.setBackground(shell.getDisplay().getSystemColor(SWT.COLOR_DARK_MAGENTA));
    buttonReplay.setLayoutData(griddataButtonReplay);
    try {
      file.openToRead();
    } catch (FileNotFoundException e1) {
      e1.printStackTrace();
    }
    file.readInt();
    if (file.checkEOF()) {
      buttonReplay.setEnabled(false);
    }
    GridData griddataButtonComputer = new GridData();
    griddataButtonComputer.horizontalAlignment = GridData.CENTER;
    griddataButtonComputer.heightHint = 65;
    griddataButtonComputer.widthHint = 350;
    buttonComputer = new Button(shell, SWT.PUSH);
    buttonComputer.setText("Игрок против компьютера");
    buttonComputer.setFont(buttonFont);
    buttonComputer.setBackground(shell.getDisplay().getSystemColor(SWT.COLOR_DARK_MAGENTA));
    buttonComputer.setLayoutData(griddataButtonComputer);
    GridData griddataButtonPlayers = new GridData();
    griddataButtonPlayers.horizontalAlignment = GridData.CENTER;
    griddataButtonPlayers.heightHint = 65;
    griddataButtonPlayers.widthHint = 350;
    buttonPlayers = new Button(shell, SWT.PUSH);
    buttonPlayers.setText("Игрок против игрока");
    buttonPlayers.setFont(buttonFont);
    buttonPlayers.setBackground(shell.getDisplay().getSystemColor(SWT.COLOR_DARK_MAGENTA));
    buttonPlayers.setLayoutData(griddataButtonPlayers);
    setListeners();
    shell.pack();
    shell.setSize(820, 920);
  }

  private void setListeners() {
    Listener listener = new Listener() {
      public void handleEvent(Event event) {
        if (event.widget == buttonRules) {
          Rules rules = new Rules(display, shell);
          rules.Show();
        } else if (event.widget == buttonReplay) {
          try {
            file.openToRead();
          } catch (FileNotFoundException e) {
            e.printStackTrace();
          }
          SavedGames savedGames = new SavedGames(display, shell);
          savedGames.Show();
        } else if (event.widget == buttonComputer) {
          Computer computer = new Computer(display, shell);
          computer.Show();
        } else if (event.widget == buttonPlayers) {
          Number number = new Number(display, shell, 0);
          number.Show();
        }
      }
    };
    buttonRules.addListener(SWT.Selection, listener);
    buttonReplay.addListener(SWT.Selection, listener);
    buttonComputer.addListener(SWT.Selection, listener);
    buttonPlayers.addListener(SWT.Selection, listener);
  }
}
