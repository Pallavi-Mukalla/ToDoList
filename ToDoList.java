//mini project
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class ToDoList {
     private JFrame frame;
     private ArrayList<String> tasksList = new ArrayList<>();

    public ToDoList() {
        frame = new JFrame();
        frame.setTitle("TO DO LIST APPLICATION");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLocation(500,100);
        frame.setLayout(null);

        JButton B1 = new JButton("add task");
        JButton B2 = new JButton("delete task");
        JButton B3 = new JButton("display tasks");
        JButton B4 = new JButton("mark as complete");
        B1.setBounds(100, 100, 150, 30);
        B2.setBounds(100, 150, 150, 30);
        B3.setBounds(100, 200, 150, 30);
        B4.setBounds(100, 250, 150, 30);
        frame.add(B1);
        frame.add(B2);
        frame.add(B3);
        frame.add(B4);

        JTextField tasks =new JTextField("enter your task here");
        tasks.setBounds(100, 10, 500, 50);
        frame.add(tasks);

        B1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String task = tasks.getText().trim();
                if (!task.isEmpty() && !task.equals("enter your task here")) {
                    tasksList.add(task);
                    JOptionPane.showMessageDialog(frame, "Task added: " + task, "Task Added", JOptionPane.INFORMATION_MESSAGE);
                    tasks.setText("");
                }
            }
        });

        B3.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JTextArea displayArea = new JTextArea();
                for (String task : tasksList) {
                    displayArea.append(task + "\n");
                }
                JScrollPane scrollPane = new JScrollPane(displayArea);
                JOptionPane.showMessageDialog(frame, scrollPane, "Tasks List", JOptionPane.PLAIN_MESSAGE);
            }
        });

        B2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                showTasksToDelete();
            }
        });

        B4.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                showTasksToMarkComplete();
            }
        });

        frame.pack(); // Adjusts the frame size based on the components
        frame.setVisible(true);
    }
    private void showTasksToDelete() {
        JFrame deleteFrame = new JFrame("Delete Tasks");
        deleteFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        deleteFrame.setLayout(new BorderLayout());

        JPanel panel = new JPanel(new GridLayout(tasksList.size(), 1));
        ArrayList<JCheckBox> checkBoxes = new ArrayList<>();

        for (String task : tasksList) {
            JCheckBox checkBox = new JCheckBox(task);
            checkBoxes.add(checkBox);
            panel.add(checkBox);
        }

        JButton deleteButton = new JButton("Delete Selected Tasks");
        deleteButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int count = 0;
                for (JCheckBox checkBox : checkBoxes) {
                    if (checkBox.isSelected()) {
                        tasksList.remove(checkBox.getText());
                        count++;
                    }
                }
                JOptionPane.showMessageDialog(deleteFrame, count + " tasks deleted.", "Tasks Deleted", JOptionPane.INFORMATION_MESSAGE);
                deleteFrame.dispose();
            }
        });

        deleteFrame.add(new JScrollPane(panel), BorderLayout.CENTER);
        deleteFrame.add(deleteButton, BorderLayout.SOUTH);
        deleteFrame.pack();
        deleteFrame.setLocationRelativeTo(frame);
        deleteFrame.setVisible(true);
    }

    private void showTasksToMarkComplete() {
        JFrame completeFrame = new JFrame("Mark Tasks as Complete");
        completeFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        completeFrame.setLayout(new BorderLayout());
    
        JPanel panel = new JPanel(new GridLayout(tasksList.size(), 2));
    
        for (String task : tasksList) {
            JLabel taskLabel = new JLabel(task);
            JButton completeButton = new JButton("Mark as Complete");
            completeButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    completeButton.setText("Completed");
                    completeButton.setEnabled(false); // Disable the button after marking as completed
                }
            });
            panel.add(taskLabel);
            panel.add(completeButton);
        }
    
        completeFrame.add(new JScrollPane(panel), BorderLayout.CENTER);
        completeFrame.pack();
        completeFrame.setLocationRelativeTo(frame);
        completeFrame.setVisible(true);
    }
    


    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new ToDoList();
            }
        });
    }
}
