import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

public class FileClass {
  private String name;
  private DataOutputStream dos;
  private DataInputStream dis;
  private boolean EOF = false;

  public FileClass(String name) {
    this.name = name;
  }

  public void saveGame() {
    try {
      openToRead();
      name = createNewFileName();
      openToWrite();
      int mode = readInt();
      writeMode(mode);
      while (!checkEOF()) {
        saveTurn();
      }
    } catch (IOException e) {
      e.printStackTrace();
    }
  }

  private void saveTurn() {
    String number = readString();
    int bulls = readInt();
    int cows = readInt();
    if (number == null)
      return;
    try {
      writeTurn(number, bulls, cows);
    } catch (IOException e) {
      e.printStackTrace();
    }
  }

  public static void deleteFile(String fileName) {
    File here = new File(".");
    File file = new File(
        here.getAbsolutePath().substring(0, here.getAbsolutePath().length() - 2) + fileName);
    if (!file.delete()) {
      System.out.println("Deleting error, " + file.getAbsolutePath());
    }
  }

  public void closeDOS() {
    try {
      dos.close();
    } catch (IOException e) {
      e.printStackTrace();
    }
  }

  public void closeDIS() {
    try {
      dis.close();
    } catch (IOException e) {
      e.printStackTrace();
    }
  }

  private String createNewFileName() {
    File[] fileList;
    File directory = new File("saves/");
    fileList = directory.listFiles();
    int count, flag;
    for (count = 0; count < directory.listFiles().length; count++) {
      flag = 0;
      String compare = null;
      if (count > 999) {
        compare = new String("Game" + count + ".txt");
      } else if (count > 99) {
        compare = new String("Game0" + count + ".txt");
      } else if (count > 9) {
        compare = new String("Game00" + count + ".txt");
      } else {
        compare = new String("Game000" + count + ".txt");
      }
      String newString = new String("saves" + File.separator + compare);
      for (File file : fileList) {
        if (compare.compareTo(file.getName()) == 0) {
          flag = 1;
          break;
        }
      }
      if (flag != 1) {
        createNewFile(newString);
        return newString;
      }
    }
    count = directory.listFiles().length;
    String newString = null;
    if (count > 999) {
      newString = new String("saves" + File.separator + "Game" + count + ".txt");
    } else if (count > 99) {
      newString = new String("saves" + File.separator + "Game0" + count + ".txt");
    } else if (count > 9) {
      newString = new String("saves" + File.separator + "Game00" + count + ".txt");
    } else {
      newString = new String("saves" + File.separator + "Game000" + count + ".txt");
    }
    createNewFile(newString);
    return newString;
  }

  private void createNewFile(String path) {
    File newFile = new File(path);
    newFile.getParentFile().mkdirs();
    try {
      newFile.createNewFile();
    } catch (IOException e) {
      e.printStackTrace();
    }
  }

  public void openToWrite() throws FileNotFoundException {
    dos = new DataOutputStream(new FileOutputStream(name));
  }

  public void writeTurn(String number, int bulls, int cows) throws IOException {
    dos.writeUTF(number);
    dos.writeInt(bulls);
    dos.writeInt(cows);
  }

  public void writeMode(int mode) throws IOException {
    dos.writeInt(mode);
  }

  public void openToRead() throws FileNotFoundException {
    dis = new DataInputStream(new FileInputStream(name));
  }

  public int readInt() {
    try {
      return dis.readInt();
    } catch (IOException e) {
      EOF = true;
    }
    return -1;
  }

  public String readString() {
    try {
      return dis.readUTF();
    } catch (IOException e) {
      EOF = true;
    }
    return null;
  }

  public boolean checkEOF() {
    return EOF;
  }
}
