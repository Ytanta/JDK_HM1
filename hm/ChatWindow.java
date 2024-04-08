package hm;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;


public class ChatWindow extends JFrame {

    /**
     * Конструктор окна
     *  WINDOW_HEIGHT - высота окна
     *  WINDOW_WIDTH - ширина окна
     *  WINDOW_WIDTH - ширина окна
     *  WINDOW_POS_X - положение окна по оси Х относительно рабочего стола
     *  WINDOW_POS_Y - положение окна по оси Y относительно рабочего стола
     *  WINDOW_NAME - имя окна
     */
    static private final int WINDOW_HEIGHT = 640;
    static private final int WINDOW_WIDTH = 720;
    static private final int WINDOW_POS_X = 300;
    static private final int WINDOW_POS_Y = 100;
    static private final String WINDOW_NAME = "Чат с сервером";
//    LoggerFile loggerFile = new LoggerFile();

    JTextArea textOutput = new JTextArea("");
    JLabel label = new JLabel("Введите сообщение серверу: ");
    JTextField textInput = new JTextField();

    //кнопка
    JButton buttonConnect = new JButton("Отправить");

    //панель
    JPanel grid = new JPanel(new GridLayout(4,1));

    /**
     * окно чата
     */
    ChatWindow(String login) throws IOException {
        //свойства окна
        setTitle(WINDOW_NAME);
        setBounds(WINDOW_POS_X, WINDOW_POS_Y, WINDOW_WIDTH, WINDOW_HEIGHT);

        textOutput.setEditable(false);
        textOutput.setBackground(Color.GRAY);
        textOutput.setAutoscrolls(true);

        textOutput.append(LoggerFile.readLog());

//        LoggerFile.readLog();

        InputMap im = buttonConnect.getInputMap();
        im.put(KeyStroke.getKeyStroke("ENTER"), "none");

        grid.add(textOutput);
        grid.add(label);
        grid.add(textInput);
        grid.add(buttonConnect);

        this.getRootPane().setDefaultButton(buttonConnect);

        //обработчик нажатия
        buttonConnect.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e){
                String msgOut = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yy/MM/dd HH:mm:ss"))
                        + " "
                        + login
                        + " : "
                        + textInput.getText()
                        + "\n";
                textOutput.append(msgOut);
                try {
                    LoggerFile.writeLog(msgOut);
                } catch (IOException ex) {
                    throw new RuntimeException(ex);
                }
            }
        });

        add(grid);
        setVisible(true);
    }
}