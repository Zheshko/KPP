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
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Text;

public class Rules {
	/** Оболочка, в которой происходит отображение программы*/
	private Shell shell;
	/** Дисплей, который использует оболочка*/
	private Display display;
	/** Конструктор, инициализирующий переменные класса
	 *  @param display - дисплей
	 *  @param shell - оболочка экрана, в ней отображается программа
	 *  */
	Rules(Display display, Shell shell)
	{
		this.shell=shell;
		this.display=display;
	}
	/**Метод, который, который отображает окно с описанием правил*/
	public void Show()
	{
		for (Control kid : shell.getChildren())
		{
			kid.dispose();
		}
		InputStream is=null;
		try {
			is = Files.newInputStream(Paths.get("Images/back.png"));
		} catch (IOException e) {
			e.printStackTrace();
		}
		Image backToMenuImage = new Image(display, is); 
		try {
			is.close();
		} catch (IOException e) {
			e.printStackTrace();
		}		
		GridLayout mainLayout=new GridLayout();
		mainLayout.marginTop=30;
		mainLayout.marginLeft=30;
		mainLayout.numColumns=1;
		shell.setLayout(mainLayout);
		Font textFont = new Font( shell.getDisplay(), new FontData( "Arial", 11, SWT.NORMAL ) );
		GridData griddataText=new GridData();
		griddataText.heightHint=700;
		griddataText.widthHint=700;
		Text text=new Text(shell,SWT.READ_ONLY|SWT.BORDER|SWT.WRAP);
		text.setText("\tКаждый из игроков задумывает тайное четырехзначное число с неповторяющимися"
				+ " цифрами.\n\n\tИгрок, который начинает игру, делает первую попытку отгадать "
				+ "число. Попытка — это 4-значное число с неповторяющимися цифрами, сообщаемое"
				+ " противнику. Противник сообщает в ответ, сколько цифр угадано без совпадения"
				+ " с их позициями в тайном числе (то есть количество коров) и сколько угадано "
				+ "вплоть до позиции в тайном числе (то есть количество быков).\n\n\t Например:"
				+ "Задумано тайное число «3219».\n\n\tПопытка: «2310».\n\n\tРезультат: две «коровы»"
				+ " (две цифры: «2» и «3» — угаданы на неверных позициях) и один «бык» (одна цифра"
				+ " «1» угадана вплоть до позиции).\n\n\tИгроки делают попытки угадать по очереди."
				+ " Побеждает тот, кто угадает число первым, при условии, что он не начинал игру."
				+ " Если же отгадавший начинал игру — его противнику предоставляется последний шанс"
				+ " угадать последовательность.\n\n\tПри игре против компьютера игрок вводит "
				+ "комбинации одну за другой, пока не отгадает всю последовательность.");
		text.setFont(textFont);
		text.setBackground(shell.getDisplay().getSystemColor(SWT.COLOR_INFO_BACKGROUND));
		text.setLayoutData(griddataText);
		GridData griddataButtonBackToMenu=new GridData();
		griddataButtonBackToMenu.verticalIndent=15;
		griddataButtonBackToMenu.heightHint=60;
		griddataButtonBackToMenu.widthHint=60;
		Button buttonBackToMenu=new Button(shell,SWT.NONE);
		buttonBackToMenu.setBackground(shell.getDisplay().getSystemColor(SWT.COLOR_DARK_MAGENTA));
		buttonBackToMenu.setImage(backToMenuImage);
		buttonBackToMenu.setLayoutData(griddataButtonBackToMenu);
		buttonBackToMenu.addSelectionListener(
	            new org.eclipse.swt.events.SelectionAdapter() {
	                public void widgetSelected(
	                        org.eclipse.swt.events.SelectionEvent e) {
	                	Menu menu=new Menu(display, shell);
	                	menu.Show();
	                }
	            });
		shell.pack();
		shell.setSize(820, 920);
	}
}
