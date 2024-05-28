import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MainUI extends JFrame {
    private JButton add;
    private JButton remove;
    private JButton quit;
    private JPanel mainWindow;
    private JTree studentPanel;
    private JButton look;
    private JButton print;

    public static Roster roster;

    public MainUI(){
        setContentPane(mainWindow);
        setTitle("School Manager");
        setSize(800, 600);
        setVisible(true);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//        setLocation(thi);

        add.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                showStudentInfoDialog();
            }
        });
        remove.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                showSpecificInfoDialog();
            }
        });
        quit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });
    }

    private void showStudentInfoDialog(Student student) {
        StudentInfoDialog dialog = new StudentInfoDialog(student);

        dialog.pack();
        dialog.setLocationRelativeTo(this);
        dialog.setVisible(true);
    }

    private void showStudentInfoDialog() {
        showStudentInfoDialog(new Student("", "", "", 0, 0));
    }

    private void showSpecificInfoDialog(Student student) {
        SpecificInfoDialog dialog = new SpecificInfoDialog(student);

        dialog.pack();
        dialog.setLocationRelativeTo(this);
        dialog.setVisible(true);
    }

    private void showSpecificInfoDialog() {
        showSpecificInfoDialog(new Student("", "", "", 0, 0));
    }
}
