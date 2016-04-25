import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

public class FileClass {
	private String name;
	private DataOutputStream dos;
	private DataInputStream dis;
	private boolean EOF = false;
	public FileClass(String name)
	{
		this.name = name;
	}
	public void openToWrite() throws FileNotFoundException
	{
		dos = new DataOutputStream(new FileOutputStream(name));
	}
	public void writeTurn(String number, int bulls, int cows) throws IOException
	{
		dos.writeUTF(number);
		dos.writeInt(bulls);
		dos.writeInt(cows);
	}
	public void writeMode(int mode) throws IOException
	{
		dos.writeInt(mode);
	}
	//////////////////////////////////////////////////////
	public void openToRead() throws FileNotFoundException
	{
		dis = new DataInputStream(new FileInputStream(name));
	}
	public int readInt()
	{
		try {
			return dis.readInt();
		} catch (IOException e) {
			EOF = true;
		}
		return -1;
	}
	public String readString()
	{
		try {
			return dis.readUTF();
		} catch (IOException e) {
			EOF = true;
		}
		return null;
	}
	public boolean checkEOF()
	{
		return EOF;
	}
}
