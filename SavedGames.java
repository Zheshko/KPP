import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;

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
import org.eclipse.swt.widgets.List;
import org.eclipse.swt.widgets.Listener;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Text;

import sort.ScalaSort;
import sort.ScalaStatistic;

public class SavedGames {
  /** Оболочка, в которой происходит отображение программы */
  protected Shell shell;
  /** Дисплей, который использует оболочка */
  protected Display display;
  private List saveList;
  private Text statisticText;
  private Button buttonBackToMenu;
  private Button buttonReplay;
  private Button buttonDelete;

  public SavedGames(Display display, Shell shell) {
    for (Control kid : shell.getChildren()) {
      kid.dispose();
    }
    this.shell = shell;
    this.display = display;
  }

  public void Show() {
    InputStream is = null;
    try {
      is = Files.newInputStream(Paths.get("Images/back2.png"));
    } catch (IOException e) {
      e.printStackTrace();
    }
    Image backToMenuImage = new Image(display, is);
    Font buttonFont = new Font(shell.getDisplay(), new FontData("Times New Roman", 14, SWT.NORMAL));
    Font textFont = new Font(shell.getDisplay(), new FontData("Times New Roman", 12, SWT.NORMAL));
    Font listFont = new Font(shell.getDisplay(), new FontData("Arial", 25, SWT.NORMAL));
    GridLayout mainLayout = new GridLayout();
    mainLayout.numColumns = 3;
    shell.setLayout(mainLayout);
    mainLayout.marginLeft = 80;
    mainLayout.marginTop = 20;
    GridData griddataStatisticField = new GridData();
    griddataStatisticField.horizontalSpan = 3;
    griddataStatisticField.widthHint = 615;
    griddataStatisticField.heightHint = 50;
    statisticText = new Text(shell, SWT.READ_ONLY | SWT.BORDER | SWT.WRAP);
    statisticText.setBackground(shell.getDisplay().getSystemColor(SWT.COLOR_WHITE));
    statisticText.setFont(textFont);
    statisticText.setLayoutData(griddataStatisticField);
    GridData griddataSaveList = new GridData();
    griddataSaveList.horizontalSpan = 3;
    griddataSaveList.widthHint = 600;
    griddataSaveList.heightHint = 600;
    saveList = new List(shell, SWT.MULTI | SWT.BORDER | SWT.V_SCROLL);
    saveList.setLayoutData(griddataSaveList);
    saveList.setFont(listFont);
    readFiles();
    GridData griddataButtonBackToMenu = new GridData();
    griddataButtonBackToMenu.verticalIndent = 20;
    griddataButtonBackToMenu.heightHint = 80;
    griddataButtonBackToMenu.widthHint = 80;
    buttonBackToMenu = new Button(shell, SWT.PUSH);
    buttonBackToMenu.setImage(backToMenuImage);
    buttonBackToMenu.setBackground(shell.getDisplay().getSystemColor(SWT.COLOR_DARK_MAGENTA));
    buttonBackToMenu.setLayoutData(griddataButtonBackToMenu);
    GridData griddataButtonReplay = new GridData();
    griddataButtonReplay.verticalIndent = 20;
    griddataButtonReplay.horizontalIndent = 140;
    griddataButtonReplay.heightHint = 80;
    griddataButtonReplay.widthHint = 200;
    buttonReplay = new Button(shell, SWT.PUSH);
    buttonReplay.setText("Просмотреть");
    buttonReplay.setFont(buttonFont);
    buttonReplay.setBackground(shell.getDisplay().getSystemColor(SWT.COLOR_DARK_MAGENTA));
    buttonReplay.setLayoutData(griddataButtonReplay);
    GridData griddataButtonDelete = new GridData();
    griddataButtonDelete.verticalIndent = 20;
    griddataButtonDelete.heightHint = 80;
    griddataButtonDelete.widthHint = 200;
    buttonDelete = new Button(shell, SWT.PUSH);
    buttonDelete.setText("Удалить");
    buttonDelete.setFont(buttonFont);
    buttonDelete.setBackground(shell.getDisplay().getSystemColor(SWT.COLOR_DARK_MAGENTA));
    buttonDelete.setLayoutData(griddataButtonDelete);
    setListeners();
    shell.pack();
    shell.setSize(820, 920);
  }

  private void setListeners() {
    Listener listener = new Listener() {
      public void handleEvent(Event event) {
        if (event.widget == buttonBackToMenu) {
          Menu menu = new Menu(display, shell);
          menu.Show();
        } else if (event.widget == buttonReplay) {
          replayGame();
        } else if (event.widget == buttonDelete) {
          for (int i = 0; i < saveList.getSelectionCount(); i++) {
            String fileName = saveList.getSelection()[i];
            fileName = "/saves/" + fileName.substring(13, 21) + ".txt";
            FileClass.deleteFile(fileName);
          }
          saveList.removeAll();
          readFiles();
        }
      }
    };
    buttonBackToMenu.addListener(SWT.Selection, listener);
    buttonReplay.addListener(SWT.Selection, listener);
    buttonDelete.addListener(SWT.Selection, listener);
  }

  private void readFiles() {
    File[] fileList;
    File directory = new File("saves/");
    fileList = directory.listFiles();
    for (File file : fileList) {
      int count = countTurns("saves/" + file.getName());
      saveList.add("             " + file.getName().substring(0, 8) + "      " + count);
    }
    ArrayList<String> array = generateArray();
    int[] intArray = new int[array.size()];
    for (int i = 0; i < intArray.length; i++) {
      intArray[i] = Integer.parseInt(array.get(i));
    }
    long timer = System.currentTimeMillis();
    // intArray = sort(intArray);
    intArray = ScalaSort.sort(intArray);
    int[] turnArray = new int[array.size()];
    for (int i = 0; i < intArray.length; i++) {
      turnArray[i] = intArray[i] / 10000;
    }
    timer = System.currentTimeMillis() - timer;
    System.out.println("Time - " + timer);
    array.clear();
    for (int i : intArray) {
      array.add("" + i);
    }
    sortList(array);
    float[] statisticArray = ScalaStatistic.getStatistic(turnArray);
    float[] maxArray = {0.0f, 0.0f, 0.0f};
    int[] indexArray = {0, 0, 0};
    for (int i = 0; i < statisticArray.length; i++) {
      if (statisticArray[i] > maxArray[2]) {
        if (statisticArray[i] > maxArray[1]) {
          if (statisticArray[i] > maxArray[0]) {
            maxArray[2] = maxArray[1];
            indexArray[2] = indexArray[1];
            maxArray[1] = maxArray[0];
            indexArray[1] = indexArray[0];
            maxArray[0] = statisticArray[i];
            indexArray[0] = i + 1;
          } else {
            maxArray[2] = maxArray[1];
            indexArray[2] = indexArray[1];
            maxArray[1] = statisticArray[i];
            indexArray[1] = i + 1;
          }
        } else {
          maxArray[2] = statisticArray[i];
          indexArray[2] = i + 1;
        }
      }
    }
    statisticText.append("Наиболее частые количества ходов, за которые выиграл игрок: \n");
    for (int i = 0; i < 3; i++) {
      statisticText.append(indexArray[i] + " - " + (int) (maxArray[i] * 100) + "%   ");
    }
  }

  private ArrayList<String> generateArray() {
    ArrayList<String> array = new ArrayList<String>();
    for (int i = 0; i < saveList.getItemCount(); i++) {
      String arrayElement = saveList.getItem(i);
      array.add(arrayElement.substring(27) + arrayElement.substring(17, 21));
    }
    return array;
  }

  private void sortList(ArrayList<String> array) {
    saveList.removeAll();
    for (String i : array) {
      String newString = "             Game" + i.substring(i.length() - 4) + "      "
          + i.substring(0, i.length() - 4);
      saveList.add(newString);
    }
  }

  private int countTurns(String fileName) {
    int count = 0;
    FileClass file = new FileClass(fileName);
    try {
      file.openToRead();
    } catch (FileNotFoundException e) {
      e.printStackTrace();
    }
    file.readInt();
    while (!file.checkEOF()) {
      file.readString();
      file.readInt();
      file.readInt();
      count++;
    }
    file.closeDIS();
    return count / 2;
  }

  private void replayGame() {
    if (saveList.getSelectionCount() == 1) {
      String fileName = saveList.getSelection()[0];
      fileName = "saves/" + fileName.substring(13, 21) + ".txt";
      Replay replay = new Replay(display, shell, fileName);
      replay.Show();
    }
  }

  @SuppressWarnings("unused")
  private int[] sort(int[] array) {
    for (int i = array.length - 1; i >= 0; i--) {
      for (int j = 0; j < i; j++) {
        if (array[j] > array[j + 1]) {
          int t = array[j];
          array[j] = array[j + 1];
          array[j + 1] = t;
        }
      }
    }
    return array;
  }

}
