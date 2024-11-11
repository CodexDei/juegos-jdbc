package org.codexdei;

import org.codexdei.juegos.AdivinarNumero;
import org.codexdei.juegos.Salvado;

import javax.swing.*;
import javax.swing.plaf.FontUIResource;
import java.awt.*;
import java.util.InputMismatchException;

public class Start {

    private static boolean estado = false;


    public static void main(String[] args) {

        UIManager.put("OptionPane.messageFont", new FontUIResource(new Font("Dialog", Font.PLAIN, 20)));


        do {

            try{

                String menu = JOptionPane.showInputDialog
                        ("Ingrese el numero del juego que desea jugar:\n\n"
                                + "1. Adivina numero\n"
                                + "2. Adivina texto\n"
                                + "3. Salir").trim();

                if (menu.isBlank()
                ){
                    JOptionPane.showMessageDialog(null,
                            "No puede ingresar un valor en blanco o vacio, ingrese un numero del menu",
                            "INGRESE UN VALOR VALIDO",JOptionPane.WARNING_MESSAGE);
                    continue;
                }

                int opcionMenu = Integer.parseInt(menu);

                switch (opcionMenu){

                    case 1:

                        AdivinarNumero.adivinarNumero();
                        break;

                    case 2:

                        Salvado.salvado();
                        break;

                    case 3:

                        estado = true;
                        break;

                    default:

                        throw new IllegalArgumentException("Ingrese un numero valido");
                }

            }catch (InputMismatchException e){

                e.printStackTrace();
                JOptionPane.showMessageDialog(
                        null, "ERROR, Ingrese una opcion valida", "ERROR",JOptionPane.WARNING_MESSAGE);

            }catch (Exception e){

                e.printStackTrace();
                JOptionPane.showMessageDialog(null,"ERROR: "+ e.getMessage(),"ERROR",JOptionPane.WARNING_MESSAGE);
            }

        }while (!estado);
    }

}