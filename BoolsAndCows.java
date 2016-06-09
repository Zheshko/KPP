import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;

public class BoolsAndCows {
  public static void main(String[] args) {
    Display display = new Display();
    Shell shell = new Shell(display, SWT.DIALOG_TRIM);
    shell.open();
    Menu menu = new Menu(display, shell);
    menu.Show();
    while (!shell.isDisposed()) {
      if (!display.readAndDispatch())
        display.sleep();
    }
    display.dispose();
  }
}
