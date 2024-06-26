import javax.swing.*;
import java.awt.event.*;
import java.util.function.Consumer;

public class SpecificInfoDialog extends JDialog {
    private JPanel contentPane;
    private JButton buttonOK;
    private JButton buttonCancel;
    private JTextField surnameField;
    private JTextField idField;
    private Consumer<Student> okListener;

    public SpecificInfoDialog() {
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

    public SpecificInfoDialog(Student value){
        this();
        setFieldValues(value);
    }

    public SpecificInfoDialog(Student value, Consumer<Student> listener){
        this();
        setFieldValues(value);
        okListener = listener;
    }

    public void setFieldValues(Student value){
        surnameField.setText(value.getSurname());
        idField.setText(String.valueOf(value.getId()));
    }

    private void onOK() {
        // add your code here
        okListener.accept(new Student("", surnameField.getText(), "", 0, Integer.parseInt(idField.getText())));
        dispose();
    }

    private void onCancel() {
        // add your code here if necessary
        dispose();
    }

    public static void main(String[] args) {
        SpecificInfoDialog dialog = new SpecificInfoDialog();
        dialog.pack();
        dialog.setVisible(true);
        System.exit(0);
    }
}
