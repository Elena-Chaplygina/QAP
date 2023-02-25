package guru.qa.io;

import javax.swing.*;

public interface View {
    String APP_NAME="QAP";
    Icon DEER_ICON=new ImageIcon(View.class.getClassLoader().getResource("icon/deer.png"));
    Icon SANTA_ICON=new ImageIcon(View.class.getClassLoader().getResource("icon/sad_santa.png"));


}
