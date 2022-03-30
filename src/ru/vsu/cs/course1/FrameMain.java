package ru.vsu.cs.course1;

import com.intellij.uiDesigner.core.GridConstraints;
import com.intellij.uiDesigner.core.GridLayoutManager;
import com.intellij.uiDesigner.core.Spacer;
import com.jgoodies.forms.layout.CellConstraints;
import com.jgoodies.forms.layout.FormLayout;
import ru.vsu.cs.util.JTableUtils;
import ru.vsu.cs.util.SwingUtils;

import javax.swing.*;
import javax.swing.filechooser.FileFilter;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.util.ArrayDeque;
import java.util.Queue;

import static ru.vsu.cs.course1.Task.*;

public class FrameMain extends JFrame {
    private JPanel panelMain;
    private JTable tableOutput;
    private JTable tableInput;
    private JButton buttonLoadInputFromFile;
    private JButton buttonSaveInputInfoFile;
    private JButton buttonProgram;
    private JButton buttonSaveOutputIntoFile;
    private JTextField textFieldFirstMoney;
    private JTextField textFieldSecondMoney;
    private JTextField textFieldMinRating;
    private JTextField textFieldMinMemory;
    private JTable tableCardsLeft;
    private JTable tableCards;
    private javax.swing.JScrollPane JScrollPane;
    private JButton meButton;
    private JTextField textFieldSMinMemory;


    private JFileChooser fileChooserOpen;
    private JFileChooser fileChooserSave;
    private JMenuBar menuBarMain;
    private JMenu menuLookAndFeel;

    private final String[] tableHeader = {"Название моделм", "Объем памяти", "Рейтинг", "Цена"};

    public FrameMain() {
        this.setTitle("FrameMain");
        this.setContentPane(panelMain);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.pack();

        JTableUtils.initJTableForArray(true, tableInput, 140, false, true, true, false);
        JTableUtils.initJTableForArray(false, tableOutput, 140, false, true, false, false);
        JTableUtils.initJTableForArray(false, tableCards, 140, false, true, false, false);
        //tableOutput.setEnabled(false);
        tableInput.setRowHeight(25);
        tableOutput.setRowHeight(25);
        tableCards.setRowHeight(25);

        fileChooserOpen = new JFileChooser();
        fileChooserSave = new JFileChooser();
        fileChooserOpen.setCurrentDirectory(new File("."));
        fileChooserSave.setCurrentDirectory(new File("."));
        FileFilter filter = new FileNameExtensionFilter("Text files", "txt");
        fileChooserOpen.addChoosableFileFilter(filter);
        fileChooserSave.addChoosableFileFilter(filter);

        fileChooserSave.setAcceptAllFileFilterUsed(false);
        fileChooserSave.setDialogType(JFileChooser.SAVE_DIALOG);
        fileChooserSave.setApproveButtonText("Save");

        menuBarMain = new JMenuBar();
        setJMenuBar(menuBarMain);

        menuLookAndFeel = new JMenu();
        menuLookAndFeel.setText("Вид");
        menuBarMain.add(menuLookAndFeel);
        SwingUtils.initLookAndFeelMenu(menuLookAndFeel);
        JTableUtils.writeArrayToJTable(tableInput, new String[][]{{"", "", ""}, {"", "", ""}});
        JTableUtils.writeArrayToJTable(tableOutput, new String[][]{{"", "", ""}, {"", "", ""}});
        JTableUtils.writeArrayToJTable(tableCards, new String[][]{{"", "", ""}, {"", "", ""}});

        this.pack();
//        buttonLoadInputFromFile.addActionListener(new ActionListener() {
//            @Override
//            public void actionPerformed(ActionEvent actionEvent) {
//                try {
//                    if (fileChooserOpen.showOpenDialog(panelMain) == JFileChooser.APPROVE_OPTION) {
//                        String[][] arr = Reader.readStringArray2FromFile(fileChooserOpen.getSelectedFile().getPath());
//                        JTableUtils.writeArrayToJTable(tableInput, arr);
//                    }
//                } catch (Exception e) {
//                    SwingUtils.showErrorMessageBox(e);
//                }
//            }
//        });
//        buttonSaveInputInfoFile.addActionListener(new ActionListener() {
//            @Override
//            public void actionPerformed(ActionEvent actionEvent) {
//                try {
//                    if (fileChooserSave.showSaveDialog(panelMain) == JFileChooser.APPROVE_OPTION) {
//                        String[][] matrix = JTableUtils.readStringMatrixFromJTable(tableInput);
//                        String file = fileChooserSave.getSelectedFile().getPath();
//                        if (!file.toLowerCase().endsWith(".txt")) {
//                            file += ".txt";
//                        }
//                        Reader.writeArrayToFile(file, matrix);
//                    }
//                } catch (Exception e) {
//                    SwingUtils.showErrorMessageBox(e);
//                }
//            }
//        });
        buttonSaveOutputIntoFile.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                try {
                    if (fileChooserSave.showSaveDialog(panelMain) == JFileChooser.APPROVE_OPTION) {
                        String[][] matrix = JTableUtils.readStringMatrixFromJTable(tableInput);
                        String file = fileChooserSave.getSelectedFile().getPath();
                        if (!file.toLowerCase().endsWith(".txt")) {
                            file += ".txt";
                        }
                        Reader.writeArrayToFile(file, matrix);
                    }
                } catch (Exception e) {
                    SwingUtils.showErrorMessageBox(e);
                }
            }
        });

        meButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                try {
                    SimpleLinkedListQueue2<Card> queue1;
                    SimpleLinkedListQueue2<Card> queueTemp = new SimpleLinkedListQueue2<>();

                    queue1 = SimpleTask.simpleFillingInTheQueue1();
                    queue1 = SimpleTask.simppleRandomize(queue1);

                    for (int i = 0; i < queue1.count(); i++) {
                        queueTemp.add(queue1.get(i));
                    }

                    String[][] matrixRandomed = Reader.simpleAnswerQueueToString21(queueTemp);
                    JTableUtils.writeArrayToJTable(tableInput, matrixRandomed);

                    SimpleTask.SimpleOutput out = SimpleTask.simpleProcess(queue1);

                    String[][] matrixOnTable = Reader.simpleAnswerQueueToString21(out.cardsOnTable);
                    String[][] matrixOnGraveyard = Reader.simpleAnswerStackToString21(out.cardsOnGraveYard);

                    JTableUtils.writeArrayToJTable(tableCards, matrixOnTable);
                    JTableUtils.writeArrayToJTable(tableOutput, matrixOnGraveyard);
                    textFieldSecondMoney.setText(String.valueOf(out.numberOfMovies));
                } catch (Exception e) {
                    SwingUtils.showErrorMessageBox(e);
                }
            }
        });

        buttonProgram.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                try {
                    Queue<Card> queue1;
                    Queue<Card> queueTemp = new ArrayDeque<>();

                    queue1 = fillingInTheQueue1();
                    queue1 = randomize(queue1);

                    for (Card element : queue1) {
                        queueTemp.add(element);
                    }

                    String[][] matrixRandomed = Reader.answerQueueToString21(queueTemp);
                    JTableUtils.writeArrayToJTable(tableInput, matrixRandomed);

                    Output out = process(queue1);

                    String[][] matrixOnTable = Reader.answerQueueToString21(out.cardsOnTable);
                    String[][] matrixOnGraveyard = Reader.answerStackToString21(out.cardsOnGraveYard);

                    JTableUtils.writeArrayToJTable(tableCards, matrixOnTable);
                    JTableUtils.writeArrayToJTable(tableOutput, matrixOnGraveyard);
                    textFieldSecondMoney.setText(String.valueOf(out.numberOfMovies));
                } catch (Exception e) {
                    SwingUtils.showErrorMessageBox(e);
                }
            }
        });

    }


    private void createUIComponents() {
        // TODO: place custom component creation code here
    }

    {
// GUI initializer generated by IntelliJ IDEA GUI Designer
// >>> IMPORTANT!! <<<
// DO NOT EDIT OR ADD ANY CODE HERE!
        $$$setupUI$$$();
    }

    /**
     * Method generated by IntelliJ IDEA GUI Designer
     * >>> IMPORTANT!! <<<
     * DO NOT edit this method OR call it in your code!
     *
     * @noinspection ALL
     */
    private void $$$setupUI$$$() {
        panelMain = new JPanel();
        panelMain.setLayout(new GridLayoutManager(9, 3, new Insets(0, 0, 0, 0), -1, -1));
        panelMain.setBackground(new Color(-2829100));
        panelMain.setForeground(new Color(-2829100));
        final javax.swing.JScrollPane scrollPane1 = new JScrollPane();
        scrollPane1.setBackground(new Color(-2829100));
        panelMain.add(scrollPane1, new GridConstraints(1, 0, 2, 2, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_BOTH, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_WANT_GROW, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_WANT_GROW, new Dimension(1, 1), new Dimension(354, 200), new Dimension(430, 200), 0, false));
        tableInput = new JTable();
        tableInput.setMinimumSize(new Dimension(430, 200));
        tableInput.setPreferredScrollableViewportSize(new Dimension(430, 200));
        tableInput.setPreferredSize(new Dimension(430, 200));
        tableInput.setShowVerticalLines(true);
        scrollPane1.setViewportView(tableInput);
        final JPanel panel1 = new JPanel();
        panel1.setLayout(new GridLayoutManager(1, 2, new Insets(0, 0, 0, 0), -1, -1));
        panel1.setBackground(new Color(-2829100));
        panelMain.add(panel1, new GridConstraints(3, 0, 1, 2, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_BOTH, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_WANT_GROW, null, new Dimension(354, 34), null, 0, false));
        buttonProgram = new JButton();
        buttonProgram.setLabel("Выполнить стандартной реализацией очереди");
        buttonProgram.setText("Выполнить стандартной реализацией очереди");
        panel1.add(buttonProgram, new GridConstraints(0, 0, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_WANT_GROW, null, null, null, 0, false));
        meButton = new JButton();
        meButton.setText("Выполнить самостоятельной реализацией очереди");
        panel1.add(meButton, new GridConstraints(0, 1, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        final JLabel label1 = new JLabel();
        label1.setBackground(new Color(-12828863));
        label1.setIconTextGap(15);
        label1.setText("Колода карт после перемешивания");
        panelMain.add(label1, new GridConstraints(0, 0, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_WANT_GROW, null, new Dimension(354, 42), null, 0, false));
        final JPanel panel2 = new JPanel();
        panel2.setLayout(new GridLayoutManager(1, 2, new Insets(0, 0, 0, 0), -1, -1));
        panel2.setBackground(new Color(-2829100));
        panelMain.add(panel2, new GridConstraints(8, 0, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_BOTH, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_WANT_GROW, null, new Dimension(354, 34), null, 0, false));
        buttonSaveOutputIntoFile = new JButton();
        buttonSaveOutputIntoFile.setText("Сохранить в файл");
        panel2.add(buttonSaveOutputIntoFile, new GridConstraints(0, 0, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_WANT_GROW, GridConstraints.SIZEPOLICY_WANT_GROW, null, null, null, 0, false));
        final Spacer spacer1 = new Spacer();
        panel2.add(spacer1, new GridConstraints(0, 1, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_WANT_GROW, 1, null, null, null, 0, false));
        textFieldSecondMoney = new JTextField();
        panelMain.add(textFieldSecondMoney, new GridConstraints(8, 1, 1, 2, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_WANT_GROW, GridConstraints.SIZEPOLICY_FIXED, null, new Dimension(150, -1), null, 0, false));
        final JPanel panel3 = new JPanel();
        panel3.setLayout(new FormLayout("fill:d:grow", "center:d:grow"));
        panelMain.add(panel3, new GridConstraints(4, 0, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_BOTH, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, null, null, null, 0, false));
        final JLabel label2 = new JLabel();
        label2.setText("Порядок, в котором карты оказываются на столе");
        CellConstraints cc = new CellConstraints();
        panel3.add(label2, cc.xy(1, 1));
        final JLabel label3 = new JLabel();
        label3.setText("Сделано ходов");
        panelMain.add(label3, new GridConstraints(7, 1, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        final javax.swing.JScrollPane scrollPane2 = new JScrollPane();
        panelMain.add(scrollPane2, new GridConstraints(5, 1, 2, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_BOTH, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_WANT_GROW, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_WANT_GROW, null, null, null, 0, false));
        tableCards = new JTable();
        scrollPane2.setViewportView(tableCards);
        final JLabel label4 = new JLabel();
        label4.setText("Порядок, в котором карты остались в колоде");
        panelMain.add(label4, new GridConstraints(4, 1, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        JScrollPane = new JScrollPane();
        panelMain.add(JScrollPane, new GridConstraints(5, 0, 2, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_BOTH, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_WANT_GROW, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_WANT_GROW, null, null, null, 0, false));
        tableOutput = new JTable();
        JScrollPane.setViewportView(tableOutput);
    }

    /**
     * @noinspection ALL
     */
    public JComponent $$$getRootComponent$$$() {
        return panelMain;
    }

}

