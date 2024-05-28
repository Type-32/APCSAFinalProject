import javax.swing.*;
import javax.swing.event.TreeSelectionEvent;
import javax.swing.event.TreeSelectionListener;
import javax.swing.tree.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.function.Consumer;

public class MainUI extends JFrame {
    private JButton add;
    private JButton remove;
    private JButton quit;
    private JPanel mainWindow;
    private JTree studentPanel;
    private JButton look;
    private JButton print;

    public static Roster roster = new Roster();

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
                showStudentInfoDialog(p -> {
                    roster.addRoster(p);
                    refreshTree();
                });
            }
        });
        remove.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                showSpecificInfoDialog(p -> {
                    if (!roster.removeRoster(p) || !roster.removeRoster(p.getId()) || !roster.removeRoster(p.getSurname())){
                        showErrorDialog("Unable to remove the Student.");
                        return;
                    }
                    refreshTree();
                });
            }
        });
        look.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                showSpecificInfoDialog(p -> {
                    if(roster.lookForRoster(p.getSurname()) != null)
                        studentPanel.setSelectionPath(new TreePath(roster.lookForRoster(p.getSurname())));
                    else if (roster.lookForRoster(p.getId()) != null)
                        studentPanel.setSelectionPath(new TreePath(roster.lookForRoster(p.getId())));
                    else
                        showErrorDialog("Student not found.");
                });
            }
        });
        print.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(studentPanel.getSelectionPath() != null) {
//                    Student obj = ((TreeNode) studentPanel.getLastSelectedPathComponent()).;
                    String path = studentPanel.getSelectionPath().toString().substring(1, studentPanel.getSelectionPath().toString().length()-1);
                    String[] nodes = path.split(", ");

                    // If only two nodes, then the user must've selected a Student node.
                    if(nodes.length == 2) {
                        showInfoDialog("Print Output", roster.lookForRoster(nodes[1]).toString());
                    } else if(nodes.length == 3) {
                        showInfoDialog("Print Output", nodes[2]);
                    } else {
                        showErrorDialog("Please select a valid student.");
                    }

//                    showInfoDialog("Print Output", studentPanel.getSelectionPath().toString());
                } else {
                    showErrorDialog("Please select a student in the database.");
                }
            }
        });
        quit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });

        refreshTree();
    }

    private void showStudentInfoDialog(Student student, Consumer<Student> actionListener) {
        StudentInfoDialog dialog = new StudentInfoDialog(student, actionListener);

        dialog.pack();
        dialog.setLocationRelativeTo(this);
        dialog.setVisible(true);
    }

    private void showStudentInfoDialog(Consumer<Student> actionListener) {
        showStudentInfoDialog(new Student("", "", "", 0, 0), actionListener);
    }

    private void showSpecificInfoDialog(Student student, Consumer<Student> actionListener) {
        SpecificInfoDialog dialog = new SpecificInfoDialog(student, actionListener);

        dialog.pack();
        dialog.setLocationRelativeTo(this);
        dialog.setVisible(true);
    }

    private void showSpecificInfoDialog(Consumer<Student> actionListener) {
        showSpecificInfoDialog(new Student("", "", "", 0, 0), actionListener);
    }

    private void showErrorDialog(String message) {
        showInfoDialog("Error", message);
    }

    private void showInfoDialog(String title, String message) {
        InfoDialog dialog = new InfoDialog(title, message);

        dialog.pack();
        dialog.setLocationRelativeTo(this);
        dialog.setVisible(true);
    }

    private void refreshTree(){
        studentPanel.setEditable(true);
        studentPanel.clearSelection();
        studentPanel.removeAll();
        studentPanel.repaint();
        studentPanel.revalidate();

        DefaultMutableTreeNode root = new DefaultMutableTreeNode("Students Database");
        roster.getMyRoster().forEach(i -> {
            DefaultMutableTreeNode node = new DefaultMutableTreeNode(String.format("%s", i.getSurname()));
            node.add(new DefaultMutableTreeNode(i));
            root.add(node);
        });

        studentPanel.clearSelection();
        studentPanel.getSelectionModel().setSelectionMode(TreeSelectionModel.SINGLE_TREE_SELECTION);
        studentPanel.addTreeSelectionListener(new TreeSelectionListener() {
            @Override
            public void valueChanged(TreeSelectionEvent e) {
                DefaultMutableTreeNode node = (DefaultMutableTreeNode)
                        studentPanel.getLastSelectedPathComponent();

                if (node == null)
                    //Nothing is selected.
                    return;

//                Object nodeInfo = node.getUserObject();
//                if (node.isLeaf()) {
//                    BookInfo book = (BookInfo)nodeInfo;
//                    displayURL(book.bookURL);
//                } else {
//                    displayURL(helpURL);
//                }
            }
        });

        studentPanel.setModel(new DefaultTreeModel(root));
        studentPanel.updateUI();

        studentPanel.repaint();
        studentPanel.setEditable(false);
    }
}
