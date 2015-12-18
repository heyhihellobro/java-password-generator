import javax.print.DocFlavor;
import javax.script.Invocable;
import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import javax.script.ScriptException;
import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.TitledBorder;
import java.awt.*;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.StringSelection;
import java.awt.event.*;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Objects;
import java.util.Random;

/**
 * Created by heyhihello on 20.10.2015.
 * Project name: PasswordGenerator
 * Twitter: @heyhihellobro
 */

public class PasswordGenerator extends JFrame {

    ImageIcon[] img = new ImageIcon[4];

    /**
     * General Settings
     */
    public static int WINDOW_WIDTH = 1500;
    public static int WINDOW_HEIGHT = 1000;

    public static int MIN_SLIDER_VALUE = 4;
    public static int MAX_SLIDER_VALUE = 32;
    public static int INIT_SLIDER_VALUE = 6;

    public static String first_option = "a-z";
    public static String second_option = "A-Z";
    public static String third_option = "0-9";
    public static String fourth_option = "Special chars";

    Color textColor = new Color(255,255,255);
    Color textColorFront = new Color(0,0,0);
    Color generateButtonColor = new Color(0,102,204);
    Color generateButtonColorFront = new Color(255,255,255);
    Color copyButtonColor = new Color(0,102,204);
    Color copyButtonColorFront = new Color(255,255,255);


    JButton copyButton = new JButton();
    JButton generateButton = new JButton();
    final JTextField text = new JTextField();

    /**
     * Default Constructor
     * Runs initUserInterface();
     */
    PasswordGenerator() {
        initUserInterface();

    }

    /**
     * Init Graphic User Interfrace
     */
    private void initUserInterface() {
        String windowTitle = "Vladimir Rodin - PasswordGenerator";
        setTitle(windowTitle);
        setSize(WINDOW_WIDTH, WINDOW_HEIGHT);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        createMenuBar();
        createPasswordGeneratorContent();
    }

    /**
     * Function for changing generated-text backgroung color
     */
    private void initTextSettingsWindow() {
        JFrame frame = new JFrame ("Text Field Settings");
        frame.setLayout(new GridLayout(3, 1));

        final JColorChooser colorPicker = new JColorChooser();

        final JButton changeTextButton = new JButton();
        changeTextButton.setFont(new Font("Calibri", Font.BOLD, 72));
        changeTextButton.setText("Изменить цвет text-filed");
        changeTextButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JFrame frame = new JFrame("Change color");
                final JColorChooser colorPicker = new JColorChooser();

                final JColorChooser colorPickerFront = new JColorChooser();

                JButton button = new JButton();
                button.setText("Применить цвет");
                button.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        textColor = colorPicker.getColor();
                        textColorFront = colorPickerFront.getColor();
                        text.setBackground(textColor);
                        text.setForeground(textColorFront);
                    }
                });

                frame.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
                frame.add(colorPicker, BorderLayout.NORTH);
                frame.add(colorPickerFront, BorderLayout.CENTER);
                frame.add(button, BorderLayout.SOUTH);
                frame.pack();
                frame.setVisible(true);
            }
        });

        JButton changeGenerateButton = new JButton();
        changeGenerateButton.setFont(new Font("Calibri", Font.BOLD, 72));
        changeGenerateButton.setText("Изменить цвет кнопки Generate");
        changeGenerateButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JFrame frame = new JFrame("Change color");
                final JColorChooser colorPicker = new JColorChooser();

                final JColorChooser colorPickerFront = new JColorChooser();

                JButton button = new JButton();
                button.setText("Применить цвет");
                button.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        generateButtonColor = colorPicker.getColor();
                        generateButtonColorFront = colorPickerFront.getColor();
                        generateButton.setBackground(generateButtonColor);
                        generateButton.setForeground(generateButtonColorFront);
                    }
                });

                frame.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
                frame.add(colorPicker, BorderLayout.NORTH);
                frame.add(colorPickerFront, BorderLayout.CENTER);
                frame.add(button, BorderLayout.SOUTH);
                frame.pack();
                frame.setVisible(true);
            }
        });

        JButton changeCopyButton = new JButton();
        changeCopyButton.setFont(new Font("Calibri", Font.BOLD, 72));
        changeCopyButton.setText("Изменить цвет кнопки Copy");
        changeCopyButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JFrame frame = new JFrame("Change color");
                final JColorChooser colorPicker = new JColorChooser();

                final JColorChooser colorPickerFront = new JColorChooser();

                JButton button = new JButton();
                button.setText("Применить цвет");
                button.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        copyButtonColor = colorPicker.getColor();
                        copyButtonColorFront = colorPickerFront.getColor();
                        copyButton.setBackground(copyButtonColor);
                        copyButton.setForeground(copyButtonColorFront);
                    }
                });

                frame.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
                frame.add(colorPicker, BorderLayout.NORTH);
                frame.add(colorPickerFront, BorderLayout.CENTER);
                frame.add(button, BorderLayout.SOUTH);
                frame.pack();
                frame.setVisible(true);
            }
        });




        frame.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        frame.setSize(WINDOW_WIDTH, WINDOW_HEIGHT);
//        frame.add(colorPicker);
        frame.add(changeTextButton);
        frame.add(changeGenerateButton);
        frame.add(changeCopyButton);
        frame.setVisible(true);
    }

    private void initButtonGenerateSettingsWindow() {
        JFrame frame = new JFrame("Generate Button Settings");
        JColorChooser colorPicker = new JColorChooser();
        JButton saveButton = new JButton();
        saveButton.setText("Изменить цвет");
        saveButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
            }
        });
    }

    /**
     * Initializing Program Version Window
     * */
    private void initVersionWindow() {
        JFrame frame = new JFrame("About Password Generator");
        frame.setLayout(new GridLayout(1, 1, 5, 5));
        JLabel label = new JLabel("<html><span style='font-size: 16px; text-align: center;'>Version: 1.0 Beta <br> " +
                "Author: Vladimir Rodin <br>" +
                "Twitter: @heyhihellbro <br>" +
                "Website: <a href='http://ocd-design.com/'>http://ocd-design.com/</a> </span></html>");
        label.setHorizontalAlignment(JLabel.CENTER);


        TitledBorder titled = new TitledBorder("About Password Generator");
        label.setBorder(titled);


        frame.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        frame.add(label);
        frame.setSize(800,400);
        frame.setVisible(true);
    }

    /**
     * Generate new password
     * @param numberOfDigits
     * @param state
     * @return pass
     */
    public static String generate(int numberOfDigits, int state) {
        String pass = "";
        Random r = new Random();

        for (int i = 0; i < numberOfDigits; ++i) {
            char next = 0;
            int range = 10;

            switch(state) {
                case 0: switch (r.nextInt(1)) {
                    case 0: {next = 'a';} break;
                } break;

                case 1: switch (r.nextInt(2)) {
                    case 0: {next = 'a';} break;
                    case 1: {next = 'A';} break;
                } break;

                case 2: switch (r.nextInt(3)) {
                    case 0: {next = 'a';} break;
                    case 1: {next = 'A';} break;
                    case 2: {next = '0';} break;
                } break;

                case 3: switch (r.nextInt(4)) {
                    case 0: {next = 'a';} break;
                    case 1: {next = 'A';} break;
                    case 2: {next = '0';} break;
                    case 3: {next = ':';} break;
                } break;

            }
            pass += (char)((r.nextInt(range)) + next);
        }
        return pass;
    }

    /**
     * Function that creates Menu Bar
     */
    private void createMenuBar() {
        JMenuBar menubar = new JMenuBar();
        JMenu file = new JMenu("File");
        JMenu edit = new JMenu("Edit");
        JMenu tools = new JMenu("Tools");
        JMenu help = new JMenu("Help");

        JMenuItem exitItem = new JMenuItem("Exit");
        exitItem.setMnemonic(KeyEvent.VK_E);
        exitItem.setToolTipText("Exit");
        exitItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent event) {
                System.exit(0);
            }
        });

        JMenuItem copyItem = new JMenuItem("Copy password");
        copyItem.setToolTipText("Copy password");
        copyItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String textToCopy = text.getText();
                StringSelection selection = new StringSelection(textToCopy);
                Clipboard clipboard = Toolkit.getDefaultToolkit().getSystemClipboard();
                clipboard.setContents(selection, selection);
            }
        });

        JMenuItem textOptionsItem = new JMenuItem("Change colors");
        textOptionsItem.setToolTipText("Change colors");
        textOptionsItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // options window
                initTextSettingsWindow();
            }
        });

        JMenuItem generateButtonOptionItem = new JMenuItem("Change generation button text");
        generateButtonOptionItem.setToolTipText("Change generation button text");
        generateButtonOptionItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                initButtonGenerateSettingsWindow();
            }
        });

        help.setMnemonic(KeyEvent.VK_F1);
        JMenuItem versionItem = new JMenuItem("Info about program");
        versionItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                initVersionWindow();
            }
        });
        help.add(versionItem);

        file.add(exitItem);
        edit.add(copyItem);
        tools.add(textOptionsItem);
        menubar.add(file);
        menubar.add(edit);
        menubar.add(tools);
        menubar.add(help);
        setJMenuBar(menubar);
    }

    /**
     * Main function that creates password generator user interface
     */
    private void createPasswordGeneratorContent() {
        JPanel panel = new JPanel();
        Container contentPane = getContentPane();
        contentPane.add(panel, BorderLayout.PAGE_START);

        Border optionsBorder = BorderFactory.createTitledBorder("<html><span style='font-size: 24px;'>Символы и знаки</span></html>");
        panel.setBorder(optionsBorder);

        /* Adding checkboxes */
        String options[] = {first_option, second_option, third_option, fourth_option};
        final JCheckBox boxes[] = new JCheckBox[options.length];

        for (int i = 0; i < boxes.length; i++) {
            boxes[i] = new JCheckBox(options[i]);
            panel.add(boxes[i]);
        }
        boxes[0].setSelected(true);

        /* Adding password label */

        String initPassword = generate(INIT_SLIDER_VALUE, 0);
        text.setFont(new Font("Calibri", Font.BOLD, 72));
        text.setText(initPassword);
        text.setHorizontalAlignment(SwingConstants.CENTER);
        text.setEditable(false);
        text.setBackground(textColor);
        text.setForeground(textColorFront);
        add(text, BorderLayout.CENTER);

        /* Adding jslider */
        final JSlider slider = new JSlider(JSlider.HORIZONTAL, MIN_SLIDER_VALUE, MAX_SLIDER_VALUE, INIT_SLIDER_VALUE);
        slider.setMajorTickSpacing(1);
        slider.setMinorTickSpacing(1);
        slider.setPaintLabels(true);
        slider.setPaintTicks(true);
        add(slider, BorderLayout.SOUTH);

        /* Adding button "Generate password" */

        generateButton.setFont(new Font("Calibri", Font.BOLD, 36));
        generateButton.setText("Сгенерировать новый пароль");
        generateButton.setMargin(new Insets(2, 0, 2, 0));
        generateButton.setBackground(generateButtonColor);
        generateButton.setForeground(generateButtonColorFront);
        generateButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                for (JCheckBox box : boxes) {
                    if (box.isSelected() && Objects.equals(box.getText(), "a-z")) {
                        int state = 0;
                        String pass = generate(slider.getValue(), state);
                        text.setText(pass);
                    } else if (box.isSelected() && Objects.equals(box.getText(), "A-Z")) {
                        int state = 1;
                        String pass = generate(slider.getValue(), state);
                        text.setText(pass);
                    } else if (box.isSelected() && Objects.equals(box.getText(), "0-9")) {
                        int state = 2;
                        String pass = generate(slider.getValue(), state);
                        text.setText(pass);
                    } else if (box.isSelected() && Objects.equals(box.getText(), "Special chars")) {
                        int state = 3;
                        String pass = generate(slider.getValue(), state);
                        text.setText(pass);
                    }
                }

            }
        });
        add( generateButton, BorderLayout.WEST);


        copyButton.setText("Скопировать в буфер");
        copyButton.setMargin(new Insets(2, 0, 2, 0));
        copyButton.setFont(new Font("Calibri", Font.BOLD, 36));
        copyButton.setBackground(copyButtonColor);
        copyButton.setForeground(copyButtonColorFront);
        copyButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String textToCopy = text.getText();
                StringSelection selection = new StringSelection(textToCopy);
                Clipboard clipboard = Toolkit.getDefaultToolkit().getSystemClipboard();
                clipboard.setContents(selection, selection);

                /* call external js */
                ScriptEngineManager manager = new ScriptEngineManager();
                ScriptEngine script = manager.getEngineByName("JavaScript");

                /* read script */
                try {
                    script.eval(Files.newBufferedReader(Paths.get("D:/XAMPP/htdocs/javaproject/js/main.js"), StandardCharsets.UTF_8));
                } catch (ScriptException a) {
                    a.printStackTrace();
                } catch (IOException b) {
                    b.printStackTrace();
                }

                Invocable inv = (Invocable) script;
                try {
                    inv.invokeFunction("getPassword", text.getText());
                } catch (ScriptException e1) {
                    e1.printStackTrace();
                } catch (NoSuchMethodException e1) {
                    e1.printStackTrace();
                }
            }
        });
        add(copyButton, BorderLayout.EAST);

    }

    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                PasswordGenerator passwordGenerator = new PasswordGenerator();
                passwordGenerator.setVisible(true);
            }
        });
    }

}