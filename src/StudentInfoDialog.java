import javax.swing.*;
import java.awt.event.*;
import java.util.function.Consumer;

public class StudentInfoDialog extends JDialog {
    private JPanel contentPane;
    private JButton buttonOK;
    private JButton buttonCancel;
    private JTextField nameField;
    private JTextField surnameField;
    private JTextField ageField;
    private JTextField idField;
    private JTextField gradeField;
    private Consumer<Student> okListener;

    public StudentInfoDialog() {
        setContentPane(contentPane);
        setModal(true);
        getRootPane().setDefaultButton(buttonOK);

        buttonOK.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                onOK();
            }
        });

        buttonCancel.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                onCancel();
            }
        });

        // call onCancel() when cross is clicked
        setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
        addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                onCancel();
            }
        });

        // call onCancel() on ESCAPE
        contentPane.registerKeyboardAction(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                onCancel();
            }
        }, KeyStroke.getKeyStroke(KeyEvent.VK_ESCAPE, 0), JComponent.WHEN_ANCESTOR_OF_FOCUSED_COMPONENT);
    }

    public StudentInfoDialog(Student value){
        this();
        setFieldValues(value);
    }

    public StudentInfoDialog(Student value, Consumer<Student> listener){
        this();
        setFieldValues(value);
        okListener = listener;
    }

    public void setFieldValues(Student value){
        nameField.setText(value.getName());
        surnameField.setText(value.getSurname());
        ageField.setText(String.valueOf(value.getAge()));
        idField.setText(String.valueOf(value.getId()));
        gradeField.setText(value.getGrade());
    }

    private void onOK() {
        // add your code here
        okListener.accept(new Student(nameField.getText(), surnameField.getText(), gradeField.getText(), Integer.parseInt(ageField.getText()), Integer.parseInt(idField.getText())));
        dispose();
    }

    private void onCancel() {
        // add your code here if necessary
        dispose();
    }

    public static void main(String[] args) {
        StudentInfoDialog dialog = new StudentInfoDialog();
        dialog.pack();
        dialog.setVisible(true);
        System.exit(0);
    }
}
