package hm;

// – Создать окно клиента чата.
// Окно должно содержать JtextField для ввода логина,
// пароля, IP-адреса сервера, порта подключения к серверу,
// область ввода сообщений,
// JTextArea область просмотра сообщений чата и
// JButton подключения к серверу и отправки сообщения в чат.
// Желательно сразу сгруппировать
// компоненты, относящиеся к серверу сгруппировать
// на JPanel сверху экрана, а
// компоненты, относящиеся к отправке сообщения –
// на JPanel снизу.

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

public class ConnectWindow extends JFrame {

    /**
     * Конструктор окна
     *
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
    static private final String WINDOW_NAME = "Подключение к серверу";

    /**
     * запрос и ввод текста
     */

    JTextField loginField = new JTextField("Введите ваш логин: ");
    JTextField passwordField = new JTextField("Введите ваш пароль: ");
    JTextField serverField = new JTextField("Введите адрес сервера и порт для подключения: ");

    /**
     * кнопка подключения
     */
    JButton buttonConnect = new JButton("Подключиться");

    /**
     * панель
     */
    JPanel grid = new JPanel(new GridLayout(4,1));

    ConnectWindow(){
        /**
         * свойства окна
         */
        //позволяет изменить текст в строке заголовка
        setTitle(WINDOW_NAME);

        //устанавливает положение и размер окна
        setBounds(WINDOW_POS_X, WINDOW_POS_Y, WINDOW_WIDTH, WINDOW_HEIGHT);

        //определяет действие, которое необходимо выполнить при "выходе из программы"
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        //добавление введенных данных в панель grid
        grid.add(loginField);
        grid.add(passwordField);
        grid.add(serverField);
        grid.add(buttonConnect);

        add(grid);

        //обработчик нажатия
        buttonConnect.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e){
                String login = loginField.getText().replace("Введите ваш логин: ", "");
                try {
                    new ChatWindow(login);
                } catch (IOException ex) {
                    throw new RuntimeException(ex);
                }
            }
        });

        setVisible(true);
    }
}