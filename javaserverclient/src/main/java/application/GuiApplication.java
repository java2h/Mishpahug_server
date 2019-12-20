package application;

public class GuiApplication {
    public static void main(String[] args) {
        MainForm mainForm = new MainForm();
        mainForm.setSize(1024,768);
        mainForm.pack();
        mainForm.setVisible(true);
    }
}
