import javax.swing.*;

public class ResumeCraftor {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new ResumeCraftorGUI().createAndShowGUI();
            }
        });
    }
}
