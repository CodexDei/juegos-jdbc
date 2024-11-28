package org.codexdei.runnable;

import org.codexdei.juegos.Salvado;

import javax.swing.*;

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

        try {
            Thread.sleep(time);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        JOptionPane.showMessageDialog(null,
                "TIEMPO TERMINADO!","GAME OVER",JOptionPane.WARNING_MESSAGE);
    }
}
