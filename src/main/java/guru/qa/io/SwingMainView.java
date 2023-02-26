package guru.qa.io;

import guru.qa.data.MessageRepository;
import guru.qa.domain.Message;
import guru.qa.domain.User;

import javax.swing.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.text.SimpleDateFormat;
import java.util.Comparator;
import java.util.Date;
import java.util.stream.Collectors;

import static javax.swing.JOptionPane.*;

public class SwingMainView implements MainView {

    private static final int NUM_COLUMNS_FOR_CONTACTS = 2;
    private static final SimpleDateFormat SDF = new SimpleDateFormat("dd/MM/yyyy hh:mm:ss");
    private static final int ROW_INDEX_USERNAME = 0;
    private static final int ROW_INDEX_ICQ = 1;

    private final MessageRepository messageRepository;

    public SwingMainView(MessageRepository messageRepository) {
        this.messageRepository = messageRepository;
    }

    @Override
    public void showError(Throwable t) {
        JOptionPane.showMessageDialog(
                null,
                t.getMessage(),
                APP_NAME,
                JOptionPane.ERROR_MESSAGE,
                SANTA_ICON);

    }

    @Override
    public void showMainFrame(User user, int initContactPosition) {
        JPanel frame = new JPanel();
        JTextArea messageHistory = new JTextArea(50, 1);

        Object[] header = getContactListHeader();
        Object[][] data = getContactListData(user);
        JTable contactsTable = new JTable(data, header);
        contactsTable.changeSelection(initContactPosition, 0, false, false);
        contactsTable.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                //понять куда кликнули
                int rowIndex = contactsTable.rowAtPoint(e.getPoint());
                //достать того по кому кликнули
                int icq = getIcqNumberFromSelectedRowInTable(data, rowIndex);
                //получить его сообщения
                String messages = getAllMessageHistory(user, icq);
                //отрисовать их в таблице
                messageHistory.setText(messages);
                frame.revalidate();
                frame.repaint();
            }
        });


        messageHistory.setText(getAllMessageHistory(user, getIcqNumberFromSelectedRowInTable(data, initContactPosition)));//msges from first contact
        JLabel inputLabel = new JLabel("Ваше сообщение: ");
        JTextField inputMessageField = new JTextField(50);

        frame.add(messageHistory);
        frame.add(contactsTable);
        frame.add(inputLabel);
        frame.add(inputMessageField);


        int selectedButton = JOptionPane.showOptionDialog(null,
                frame,
                APP_NAME,
                YES_NO_OPTION,
                DEFAULT_OPTION,
                DEER_ICON,
                getWindowButtons(),
                null);

        if (selectedButton == YES_OPTION) {
            int selectedIcq = getIcqNumberFromSelectedRowInTable(data, contactsTable.getSelectedRow());

            String newMessage = inputMessageField.getText();
            Message msg = new Message(newMessage, user.getIcqNumber(), selectedIcq, new Date());
            messageRepository.sendMessages(msg);
            showMainFrame(user, contactsTable.getSelectedRow());

        } else {
            messageRepository.persistData();
        }

    }

    @Override
    public void startMessaging(User user) {

    }


    private String getAllMessageHistory(User user, int icqFrom) {
        return messageRepository.getAllMessages(user).stream()
                .sorted(Comparator.comparing(Message::getCreationDate))


                .filter(msg -> msg.getFrom() == icqFrom || (msg.getTo() == icqFrom && msg.getFrom() == user.getIcqNumber()))
                .map(message -> "Сообщение от: " + message.getFrom()
                        + " (" + SDF.format(message.getCreationDate()) + "):"
                        + "\n"
                        + message.getText())
                .collect(Collectors.joining("\n\n"));


    }

    private int getIcqNumberFromSelectedRowInTable(Object[][] data, int rowIndex) {
        Object[] rowData = data[rowIndex];
        return (int) rowData[ROW_INDEX_ICQ];
    }


    private Object[] getContactListHeader() {
        return new String[]{"Имя пользователя", "Номер icq"};
    }

    private String[] getWindowButtons() {
        return new String[]{"Отправить сообщение", "Выйти"};
    }

    private Object[][] getContactListData(User user) {
        return user.getContactList().stream()
                .map(usr -> new Object[]{usr.getUserName(), usr.getIcqNumber()})
                .toArray(Object[][]::new);
    }


}
