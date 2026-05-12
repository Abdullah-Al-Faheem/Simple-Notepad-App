package notepad;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.io.*;
import javax.swing.filechooser.*;

public class Notepad extends JFrame implements ActionListener {

    private final JTextArea area;
    String text = "";

    public Notepad()
    {
        setTitle("NOTEBOOK");

        setSize(1200, 860);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());

        JMenuBar menuBar = new JMenuBar();

        JMenu file = new JMenu("File");

        JMenuItem newdoc = new JMenuItem("New");
        newdoc.addActionListener(this);

        JMenuItem open = new JMenuItem("Open");
        open.addActionListener(this);

        JMenuItem save = new JMenuItem("Save");
        save.addActionListener(this);

        JMenuItem exit = new JMenuItem("Exit");
        exit.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_ESCAPE, 0));
        exit.addActionListener(this);

        JMenu edit = new JMenu("Edit");

        JMenuItem copy = new JMenuItem("Copy");
        copy.addActionListener(this);

        JMenuItem paste = new JMenuItem("Paste");
        paste.addActionListener(this);

        JMenuItem cut = new JMenuItem("Cut");
        cut.addActionListener(this);

        JMenuItem selectall = new JMenuItem("Select All");
        selectall.addActionListener(this);

        JMenuItem notepad = new JMenuItem("About Notepad");
        notepad.addActionListener(this);

        area = new JTextArea();
        area.setFont(new Font("SAN_SERIF", Font.PLAIN, 20));
        area.setLineWrap(true);
        area.setWrapStyleWord(true);

        JScrollPane scpane = new JScrollPane(area);
        scpane.setBorder(BorderFactory.createEmptyBorder());

        setJMenuBar(menuBar);
        menuBar.add(file);
        menuBar.add(edit);

        file.add(newdoc);
        file.add(open);
        file.add(save);
        file.add(exit);

        edit.add(copy);
        edit.add(paste);
        edit.add(cut);
        edit.add(selectall);

        add(scpane, BorderLayout.CENTER);
        setVisible(true);
    }

    public void actionPerformed(ActionEvent ACC) {
        if (ACC.getActionCommand().equals("New")) {
            area.setText("");
        }

        else if (ACC.getActionCommand().equals("Open")) {
            JFileChooser chooser = new JFileChooser(System.getProperty("user.home") + "/Documents/textFiles");
            chooser.setAcceptAllFileFilterUsed(false);
            FileNameExtensionFilter restrict = new FileNameExtensionFilter("Only .txt files", "txt");
            chooser.addChoosableFileFilter(restrict);

            int result = chooser.showOpenDialog(this);

            if (result == JFileChooser.APPROVE_OPTION) {
                File file = chooser.getSelectedFile();

                try (BufferedReader br = new BufferedReader(new FileReader(file))) {
                    area.read(br, null);
                    area.requestFocus();
                } catch (Exception e) {
                    e.printStackTrace();
                    JOptionPane.showMessageDialog(this, "Error opening file.", "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        }

        else if (ACC.getActionCommand().equals("Save")) {
            JFileChooser SaveAs = new JFileChooser(System.getProperty("user.home") + "/Documents/textFiles");
            SaveAs.setApproveButtonText("Save");
            int actionDialog = SaveAs.showSaveDialog(this);

            if (actionDialog != JFileChooser.APPROVE_OPTION) {
                return;
            }

            File file = SaveAs.getSelectedFile();
            String path = file.getAbsolutePath();
            if (!path.endsWith(".txt")) {
                path += ".txt";
            }

            try (BufferedWriter outFile = new BufferedWriter(new FileWriter(path))) {
                area.write(outFile);
                JOptionPane.showMessageDialog(this, "File saved successfully.");
            } catch (IOException e) {
                e.printStackTrace();
                JOptionPane.showMessageDialog(this, "Error saving file.", "Error", JOptionPane.ERROR_MESSAGE);
            }
        }

        else if (ACC.getActionCommand().equals("Exit")) {
            System.exit(0); // You can replace this with login page if you have one
        }

        else if (ACC.getActionCommand().equals("Copy")) {
            text = area.getSelectedText();
        }

        else if (ACC.getActionCommand().equals("Paste")) {
            area.insert(text, area.getCaretPosition());
        }

        else if (ACC.getActionCommand().equals("Cut")) {
            text = area.getSelectedText();
            area.replaceRange("", area.getSelectionStart(), area.getSelectionEnd());
        }

        else if (ACC.getActionCommand().equals("Select All")) {
            area.selectAll();
        }
    }

    public static void main(String[] args) {
        new Notepad();
    }
}