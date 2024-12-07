package org.codexdei.runnable;

import org.codexdei.juegos.Salvado;

import javax.swing.*;
import java.awt.*;

public class PlayTime implements Runnable {

    private long time;


    public PlayTime(long time){

        this.time = time;
    }

    public long getTime() {
        return time;
    }


    @Override
    public void run() {

        Toolkit toolkit = Toolkit.getDefaultToolkit();

        try {
            Thread.sleep(time);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        //sonido al terminar el tiempo
        toolkit.beep();
        JOptionPane.showMessageDialog(null,
                "¡TIEMPO APUNTO DE TERMINAR!","¡WARNING WARNING!",JOptionPane.WARNING_MESSAGE);
    }
}
