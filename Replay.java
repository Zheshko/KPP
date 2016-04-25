import java.io.FileNotFoundException;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;
public class Replay extends GameWindow{
	private FileClass file=new FileClass("src/Temp.txt");
	private int turn = 1;
	private int bulls, cows;
	private int winner = 0;
	/**����� ����(2-������ ������� ���������,3-������� ������� ���������,4-������� ������� ���������,
	 * 5-�������������� �����)*/
	private int mode;
	/**����� ��� ����� ������� �����*/
	public Replay(int mode,Display display,Shell shell)
	{
		for (Control kid : shell.getChildren())
		{
			kid.dispose();
		}
		super.loadScreen(display, shell);
		this.mode=mode;
	}
	private void startReplay()
	{
		try {
			file.openToRead();
			mode = file.readInt();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		Runnable timer = new Runnable(){

			@Override
			public void run() {
				loadTurn();
				loadToText();
				if(!file.checkEOF())
					display.timerExec(500, this);
				else
				{
					Winner win = new Winner(display, shell, winner, mode);
					win.Show();
				}
			}
		};
		display.timerExec(500, timer);
	}
	private void loadTurn()
	{
		String temp = file.readString();
		if(temp == null)
			return;
		enteredNumberLabel.setText(temp);
		bulls = file.readInt();
		if(bulls == 4)
		{
			if(turn == 1)
				winner += 1;
			else if(turn == 2)
				winner += 2;
		}
		cows = file.readInt();
	}
	private void loadToText()
	{
		if(file.checkEOF())
			return;
		else if(turn == 1)
		{
			playerLabel2.setFont(passivePlayerFont);
			playerLabel1.setFont(activePlayerFont);
			playerLabel2.setForeground(shell.getDisplay().getSystemColor(SWT.COLOR_BLACK));
			playerLabel1.setForeground(shell.getDisplay().getSystemColor(SWT.COLOR_RED));
			playerText1.append("\n"+enteredNumberLabel.getText()+" "+ bulls+"�. "+cows+"�.");
			turn = 2;
		}
		else
		{
			playerLabel2.setFont(activePlayerFont);
			playerLabel1.setFont(passivePlayerFont);
			playerLabel2.setForeground(shell.getDisplay().getSystemColor(SWT.COLOR_RED));
			playerLabel1.setForeground(shell.getDisplay().getSystemColor(SWT.COLOR_BLACK));
			playerText2.append("\n"+enteredNumberLabel.getText()+" "+ bulls+"�. "+cows+"�.");
			turn = 1;
		}
	}
	@Override
	protected void extraSettings() {
		if(mode==1)
		{
			playerLabel1.setText("����� 1");
			playerLabel2.setText("����� 2");
		}
		else if(mode==5)
		{
			playerLabel1.setText("��������� 1");
			playerLabel2.setText("��������� 2");
		}
		else
		{
			playerLabel1.setText("��");
			playerLabel2.setText("���������");
		}	
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
		startReplay();
	}
	@Override
	protected void MakeTurn() {
	}
}
