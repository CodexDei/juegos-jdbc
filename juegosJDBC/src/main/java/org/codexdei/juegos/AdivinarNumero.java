package org.codexdei.juegos;

import org.codexdei.exceptions.IngresoDatosExcepcion;

import javax.swing.*;
import java.util.InputMismatchException;
import java.util.Random;

public class AdivinarNumero {

    private static boolean estadoAdivinarNumero = false;
    private static int puntaje = 0;
    private static int bonus = 0;
    private static int acumulacionPuntaje = 0;

    public static void adivinarNumero() {

        do {
            try {

                int menu = Integer.parseInt(JOptionPane.showInputDialog("Ingrese la opcion que desee:\n\n"
                        + "1. JUgar Adivina el Numero\n"
                        + "2. Salir"));

                switch (menu){

                    case 1:

                        nivelHuevo();
                        break;

                    case 2:

                        estadoAdivinarNumero = true;
                        break;

                    default:

                        throw new IllegalArgumentException("Ingrese SOLO los numeros que estan en el menu");
                }

            } catch (InputMismatchException e) {

                JOptionPane.showMessageDialog(
                        null, "ERROR, Ingrese una opcion valida", "ERROR", JOptionPane.WARNING_MESSAGE);

            } catch (Exception e) {

                JOptionPane.showMessageDialog(null, "ERROR: " + e.getMessage(), "ERROR", JOptionPane.WARNING_MESSAGE);
            }

        } while (!estadoAdivinarNumero);

    }
    public static void nivelHuevo() throws IngresoDatosExcepcion {


        Random random = new Random();
        int numAleatorio;
        int intentos = 1;
        int intentosExagerados = 0;
        int[] numeroUsuario;
        StringBuilder mensaje = null;
        boolean estadoIntentos = false;
        int nivelJugador = 0;


        numAleatorio = random.nextInt(10) + 1;

        inicio:
        while (!estadoIntentos) {

            intentosExagerados++;

            mensaje = new StringBuilder("NIVEL HUEVO\n");
            mensaje.append("Adivina un numero del 1 al 10\n");
            intentos = Integer.parseInt(JOptionPane.showInputDialog(
                    mensaje.append("\n¿CUantos intentos necesitas?").append("\nSi eliges un intento ganaras muchos puntos\n")));

            if (intentos <= 0) {

                throw new IngresoDatosExcepcion("El numero debe ser mayor a cero", "Mayor a Cero");
            }

            if (intentosExagerados >= 3 && intentos > 5) {
                JOptionPane.showMessageDialog(null,
                        "El maximo de intentos es 5", "Maximo intentos",JOptionPane.WARNING_MESSAGE);
            }

            if (intentos > 5 && intentosExagerados < 3) {
                JOptionPane.showMessageDialog(null,
                        "Uiichhh no, muchos", "Demasiados Intentos", JOptionPane.WARNING_MESSAGE);
                continue inicio;
            }
            if (intentos <= 5 && intentos != 0) {
                estadoIntentos = true;
            }

        }
        //se asigna tamaño de numeroUsuario de acuerdo al numero de intentos
        numeroUsuario = new int[intentos];

        for (int i = 0; i < intentos; ++i) {

            mensaje = new StringBuilder("NIVEL HUEVO\n")
                    .append("Adivina un numero del 1 al 10\n")
                    .append("Haz dicho: " + intentos + " intentos\n")
                    .append(" (っ◕‿◕)っ\n");

            if(i > 0 ){

                mensaje.append("Haz dicho el numero: ");

                int j = 0;

                for (int k = 0 ; j <  i ; j++, k++){

                    mensaje.append(numeroUsuario[k]).append("    ");
                }
            }

            numeroUsuario[i] = Integer.parseInt(JOptionPane.showInputDialog(
                    mensaje.append("\n¿Cual es el numero?")));

            if (numeroUsuario[i] > 0 && numeroUsuario[i] <= 10) {

                nivelJugador++;

                if (numeroUsuario[i] != numAleatorio) {
                    JOptionPane.showMessageDialog(null, ("No es el numero jajaja"));
                }

                if (numeroUsuario[i] == numAleatorio) {
                    JOptionPane.showMessageDialog(
                            null, mensaje.append("\n¡¡¡FELICITACIONES!!! haz adivinado el numero\n"
                                    + "El numero era: " + numAleatorio));

                    if (intentos == 1){

                        bonus = 1000;

                        JOptionPane.showMessageDialog(
                                null,mensaje.append("\nComo adivinaste eligiendo 1 intento ganas 1000 puntos adicionales\n"));
                    }

                    if (nivelJugador == 1) {

                        JOptionPane.showMessageDialog(
                                null, mensaje.append("\nOhhhh lo lograste a la primera, nivel de jugador: PROFETA\n"));
                        puntaje = 1000;

                        JOptionPane.showMessageDialog(null, mensaje.append("Su puntaje en este nivel es: " + puntaje));
                    }
                    if (nivelJugador > 1 && nivelJugador < 5) {

                        JOptionPane.showMessageDialog(
                                null, mensaje.append("\nPor lo menos no gastaste todos los intentos, nivel del jugador: SUERTUDO\n"));
                        puntaje = 500;

                        JOptionPane.showMessageDialog(null, mensaje.append("Su puntaje en este nivel es: " + puntaje));
                    }
                    if (nivelJugador == 5) {

                        JOptionPane.showMessageDialog(
                                null, mensaje.append("\nQue de buenas, nivel del jugador: AREPERO\n"));

                        puntaje = 300;

                        JOptionPane.showMessageDialog(null, mensaje.append("\nSu puntaje en este nivel es: " + puntaje + "\n"));

                    }
                    if (intentos == 1){
                        JOptionPane.showMessageDialog(null,mensaje.append("\nBonus por 1 intento\n: " + bonus));
                    }

                    acumulacionPuntaje = puntaje + bonus;
                    JOptionPane.showMessageDialog(null, mensaje.append("\nPUNTAJE ACUMULADO: " + acumulacionPuntaje));

                    nivelPollo();
                    return;
                }

            } else {

                JOptionPane.showMessageDialog(
                        null, "Ingrese un numero entre 1 y 10, igual cuenta como intento " +
                                "por no estar concentrad@", "CONCENTRATE!!!",JOptionPane.WARNING_MESSAGE);

            }

        }//aqui termina el for externo
        int i = 0;

        if (numAleatorio != numeroUsuario[i]) {
            JOptionPane.showMessageDialog(
                    null, mensaje.append("\nHaz perdido el juego, el numero era: " + numAleatorio));

            JOptionPane.showMessageDialog(
                    null, mensaje.append("\nJuego terminado, hasta la proxima!!!\n"));
            nivelHuevo();
        }
    }

    public static void nivelPollo() throws IngresoDatosExcepcion {

        Random random = new Random();
        int numeroUsuario[];
        int numAleatorio = 0;
        int intentos = 1;
        int intentosExagerados = 0;
        StringBuilder mensaje = null;
        boolean estadoIntentos = false;

        int nivelJugador = 0;



        inicio:
        while (!estadoIntentos) {
            intentosExagerados++;

            mensaje = new StringBuilder("NIVEL POLLO\n");
            numAleatorio = random.nextInt(20) + 1;
            mensaje.append("Adivina un numero del 1 al 20\n")
                    .append("Puntaje acumulado: " + acumulacionPuntaje);

            intentos = Integer.parseInt(JOptionPane.showInputDialog(
                    mensaje.append("\n¿CUantos intentos necesitas?").append("\nSi eliges un intento ganaras muchos puntos\n")));
            if (intentos <= 0) {

                JOptionPane.showMessageDialog(null,
                        "El numero debe ser mayor a cero", "Mayor a Cero", JOptionPane.WARNING_MESSAGE);            }

            if (intentosExagerados >= 3 && intentos > 10) {
                JOptionPane.showMessageDialog(null,
                        "El maximo de intentos es 10", "Maximo intentos",JOptionPane.WARNING_MESSAGE);
            }

            if (intentos > 10 && intentosExagerados < 3) {
                JOptionPane.showMessageDialog(null,
                        "Uiichhh no, muchos", "Demasiados Intentos", JOptionPane.WARNING_MESSAGE);
                continue inicio;
            }
            if (intentos <= 10 && intentos != 0) {
                estadoIntentos = true;
            }
        }
//se asigna tamaño de numeroUsuario de acuerdo al numero de intentos
        numeroUsuario = new int[intentos];

        for (int i = 0; i < intentos; ++i) {

            mensaje = new StringBuilder("NIVEL HUEVO\n")
                    .append("Adivina un numero del 1 al 20\n")
                    .append("Haz dicho: " + intentos + " intentos\n")
                    .append("Puntaje acumulado: " + acumulacionPuntaje)
                    .append(" (っ◕‿◕)っ\n");

            if(i > 0 ){

                mensaje.append("Haz dicho el numero: ");

                int j = 0;

                for (int k = 0 ; j <  i ; j++, k++){

                    mensaje.append(numeroUsuario[k]).append("    ");
                }
            }

            numeroUsuario[i] = Integer.parseInt(JOptionPane.showInputDialog(
                    mensaje.append("\n¿Cual es el numero?")));

            if (numeroUsuario[i] > 0 && numeroUsuario[i] <= 20) {

                nivelJugador++;

                if (numeroUsuario[i] != numAleatorio) {
                    JOptionPane.showMessageDialog(null, mensaje.append("\nNo es el numero jajaja\n"));
                }

                if (numeroUsuario[i] == numAleatorio) {
                    JOptionPane.showMessageDialog(
                            null, mensaje.append("\n¡¡¡FELICITACIONES!!! haz adivinado el numero\n"
                                    + "El numero era: " + numAleatorio));


                    if (intentos == 1){

                        bonus += 3000;

                        JOptionPane.showMessageDialog(
                                null,mensaje.append("\nComo adivinaste con 1 intento ganas 3000 puntos adicionales\n"));
                    }

                    if (nivelJugador == 1) {

                        JOptionPane.showMessageDialog(
                                null, mensaje.append("\nOhhhh lo lograste a la primera, nivel de jugador: APOSTOL\n"));
                        puntaje = 3000;

                        JOptionPane.showMessageDialog(null, mensaje.append("Su puntaje es: " + puntaje));
                    }
                    if (nivelJugador > 1 && nivelJugador < 7) {

                        JOptionPane.showMessageDialog(
                                null, mensaje.append("\nPor lo menos no gastaste todos los intentos, nivel del jugador: BUENIN\n"));
                        puntaje = 1000;

                        JOptionPane.showMessageDialog(null, mensaje.append("Su puntaje es: " + puntaje));
                    }
                    if (nivelJugador == 7) {

                        JOptionPane.showMessageDialog(
                                null, mensaje.append("\nQue de buenas, nivel del jugador: AREPERO NIVEL II\n"));

                        puntaje = 500;

                        JOptionPane.showMessageDialog(null, mensaje.append("\nSu puntaje en este nivel es: " + puntaje + "\n"));
                    }
                    if (intentos == 1){
                        JOptionPane.showMessageDialog(null,mensaje.append("\nBonus por 1 intento\n: " + bonus));
                    }
                    acumulacionPuntaje = puntaje + bonus;
                    JOptionPane.showMessageDialog(null, mensaje.append("\nPUNTAJE ACUMULADO: " + acumulacionPuntaje));

                    nivelGallo();
                    return;
                }

            } else {

                JOptionPane.showMessageDialog(
                        null, "Ingrese un numero entre 1 y 20, igual cuenta como intento " +
                                "por no estar concentrad@", "CONCENTRATE!!!",JOptionPane.WARNING_MESSAGE);
            }

        }

        int i = 0;

        if (numAleatorio != numeroUsuario[i]) {
            JOptionPane.showMessageDialog(
                    null, mensaje.append("\nHaz perdido el juego, el numero era: " + numAleatorio));

            JOptionPane.showMessageDialog(
                    null, mensaje.append("\nJuego terminado, hasta la proxima!!!\n"));
            nivelHuevo();
        }
    }

    public static void nivelGallo() throws IngresoDatosExcepcion {

        Random random = new Random();
        int numeroUsuario[];
        int numAleatorio = 0;
        int intentos = 1;
        int intentosExagerados = 0;
        StringBuilder mensaje = null;
        boolean estadoIntentos = false;
        int nivelJugador = 0;

        inicio:
        while (!estadoIntentos) {
            intentosExagerados++;

            mensaje = new StringBuilder("NIVEL GALLO\n");
            numAleatorio = random.nextInt(30) + 1;
            mensaje.append("Adivina un numero del 1 al 30\n");
            intentos = Integer.parseInt(JOptionPane.showInputDialog(
                    mensaje.append("\n¿CUantos intentos necesitas?").append("\nSi eliges un intento ganaras muchos puntos\n")));

            if (intentos <= 0) {

                JOptionPane.showMessageDialog(null,
                        "El numero debe ser mayor a cero", "Mayor a Cero", JOptionPane.WARNING_MESSAGE);            }

            if (intentosExagerados >= 3 && intentos > 12) {
                JOptionPane.showMessageDialog(null,
                        "El maximo de intentos es 12", "Maximo intentos",JOptionPane.WARNING_MESSAGE);
            }

            if (intentos > 12 && intentosExagerados < 3) {

                JOptionPane.showMessageDialog(null,
                        "Uiichhh no, muchos", "Demasiados Intentos", JOptionPane.WARNING_MESSAGE);
                continue inicio;
            }
            if (intentos <= 12 && intentos != 0) {
                estadoIntentos = true;
            }
        }
//se asigna tamaño de numeroUsuario de acuerdo al numero de intentos
        numeroUsuario = new int[intentos];

        for (int i = 0; i < intentos; ++i) {

            mensaje = new StringBuilder("NIVEL HUEVO\n")
                    .append("Adivina un numero del 1 al 30\n")
                    .append("Haz dicho: " + intentos + " intentos\n")
                    .append("Puntaje acumulado: " + acumulacionPuntaje)
                    .append(" (っ◕‿◕)っ\n");

            if(i > 0 ){

                mensaje.append("Haz dicho el numero: ");

                int j = 0;

                for (int k = 0 ; j <  i ; j++, k++){

                    mensaje.append(numeroUsuario[k]).append("    ");
                }
            }

            numeroUsuario[i] = Integer.parseInt(JOptionPane.showInputDialog(
                    mensaje.append("\n¿Cual es el numero?")));


            if (numeroUsuario[i] > 0 && numeroUsuario[i] <= 30) {

                nivelJugador++;

                if (numeroUsuario[i] != numAleatorio) {
                    JOptionPane.showMessageDialog(null, mensaje.append("No es el numero jajaja"));
                }

                if (numeroUsuario[i] == numAleatorio) {
                    JOptionPane.showMessageDialog(
                            null, mensaje.append("\n¡¡¡FELICITACIONES!!! haz adivinado el numero\n"
                                    + "El numero era: " + numAleatorio));

                    if (intentos == 1){

                        puntaje = 5000;

                        JOptionPane.showMessageDialog(
                                null,mensaje.append("\nComo adivinaste a la primera ganas 5000 puntos adicionales\n"));
                    }

                    if (nivelJugador == 1) {

                        JOptionPane.showMessageDialog(
                                null, mensaje.append("\nOhhhh lo lograste a la primera, nivel de jugador: GLORIFICADO\n"));
                        puntaje = 5000;

                        JOptionPane.showMessageDialog(null, mensaje.append("Su puntaje es: " + puntaje));
                    }
                    if (nivelJugador > 1 && nivelJugador < 10) {

                        JOptionPane.showMessageDialog(
                                null, mensaje.append("\nPor lo menos no gastaste todos los intentos, nivel del jugador: PROTEGIDO\n"));
                        puntaje = 2000;

                        JOptionPane.showMessageDialog(null, mensaje.append("Su puntaje es: " + puntaje));
                    }
                    if (nivelJugador == 10) {

                        JOptionPane.showMessageDialog(
                                null, mensaje.append("\nQue de buenas, nivel del jugador: AREPERO NIVEL III\n"));

                        puntaje = 1000;

                        JOptionPane.showMessageDialog(null, mensaje.append("\nSu puntaje es: " + puntaje + "\n"));
                    }

                    JOptionPane.showMessageDialog(null, mensaje.append("\nSu puntaje en este nivel es: " + puntaje + "\n"));

                    if (intentos == 1){
                        JOptionPane.showMessageDialog(null,mensaje.append("\nBonus por 1 intento\n: " + bonus));
                    }

                    acumulacionPuntaje = puntaje + bonus;
                    JOptionPane.showMessageDialog(null, mensaje.append("\nPUNTAJE ACUMULADO: " + acumulacionPuntaje));

                    JOptionPane.showMessageDialog(null,mensaje = new StringBuilder("\nHAS RESCATADO EL JUEGO\n"


                            + "     .\".\".\".                            \n" +
                            "         (`       `)               _.-=-.     \n" +
                            "          '._.--.-;             .-`  -'  '.   \n" +
                            "         .-'`.o )  \\           /  .-_.--'  `\\ \n" +
                            "        `;---) \\    ;         /  / ;' _-_.-' `\n" +
                            "          `;\"`  ;    \\        ; .  .'   _-' \\ \n" +
                            "           (    )    |        |  / .-.-'    -`\n" +
                            "            '-.-'     \\       | .' ` '.-'-\\`  \n" +
                            "             /_./\\_.|\\_\\      ;  ' .'-'.-.    \n" +
                            "             /         '-._    \\` /  _;-,     \n" +
                            "            |         .-=-.;-._ \\  -'-,       \n" +
                            "            \\        /      `\";`-`,-\"`)       \n" +
                            "             \\       \\     '-- `\\.\\           \n" +
                            "              '.      '._ '-- '--'/           \n" +
                            "                `-._     `'----'`;            \n" +
                            "                    `\"\"\"--.____,/             \n" +
                            "                           \\\\  \\              \n" +
                            "                           // /`              \n" +
                            "                       ___// /__              \n" +
                            "             FAM     (`(`(---\"-`)             "));

                    if(acumulacionPuntaje >= 9000){
                        JOptionPane.showMessageDialog(
                                null,"OHHH! ALCANZASTE UN PUNTAJE MUY ALTO EN EL JUEGO, puedes ver la figura secreta:\n"
                                        + "                                 /T /I                     \n" +
                                        "                                   / |/ | .-~/                \n" +
                                        "                               T\\ Y  I  |/  /  _              \n" +
                                        "              /T               | \\I  |  I  Y.-~/              \n" +
                                        "             I l   /I       T\\ |  |  l  |  T  /               \n" +
                                        "          T\\ |  \\ Y l  /T   | \\I  l   \\ `  l Y                \n" +
                                        "      __  | \\l   \\l  \\I l __l  l   \\   `  _. |                \n" +
                                        "      \\ ~-l  `\\   `\\  \\  \\\\ ~\\  \\   `. .-~   |                \n" +
                                        "       \\   ~-. \"-.  `  \\  ^._ ^. \"-.  /  \\   |                \n" +
                                        "     .--~-._  ~-  `  _  ~-_.-\"-.\" ._ /._ .\" ./                \n" +
                                        "      >--.  ~-.   ._  ~>-\"    \"\\\\   7   7   ]                 \n" +
                                        "     ^.___~\"--._    ~-{  .-~ .  `\\ Y . /    |                 \n" +
                                        "      <__ ~\"-.  ~       /_/   \\   \\I  Y   : |                 \n" +
                                        "        ^-.__           ~(_/   \\   >._:   | l______           \n" +
                                        "            ^--.,___.-~\"  /_/   !  `-.~\"--l_ /     ~\"-.       \n" +
                                        "                   (_/ .  ~(   /'     \"~\"--,Y   -=b-. _)      \n" +
                                        "                    (_/ .  \\  :           / l      c\"~o \\     \n" +
                                        "                     \\ /    `.    .     .^   \\_.-~\"~--.  )    \n" +
                                        "                      (_/ .   `  /     /       !       )/     \n" +
                                        "                       / / _.   '.   .':      /        '      \n" +
                                        "                       ~(_/ .   /    _  `  .-<_               \n" +
                                        "                         /_/ . ' .-~\" `.  / \\  \\          ,z=.\n" +
                                        "                         ~( /   '  :   | K   \"-.~-.______//   \n" +
                                        "                           \"-,.    l   I/ \\_    __{--->._(==. \n" +
                                        "                            //(     \\  <    ~\"~\"     //       \n" +
                                        "                           /' /\\     \\  \\     ,v=.  ((        \n" +
                                        "                         .^. / /\\     \"  }__ //===-  `        \n" +
                                        "                        / / ' '  \"-.,__ {---(==-              \n" +
                                        "                      .^ '       :  T  ~\"   ll     -Row       \n" +
                                        "                     / .  .  . : | :!        \\\\               \n" +
                                        "                    (_/  /   | | j-\"          ~^              \n" +
                                        "                      ~-<_(_.^-~\"                             ");

                    }
                    return;
                }

            } else {

                JOptionPane.showMessageDialog(
                        null, "Ingrese un numero entre 1 y 30, igual cuenta como intento " +
                                "por no estar concentrad@", "CONCENTRATE!!!",JOptionPane.WARNING_MESSAGE);
            }

        }

        int i = 0;

        if (numAleatorio != numeroUsuario[i]) {
            JOptionPane.showMessageDialog(
                    null, mensaje.append("\nHaz perdido el juego, el numero era: " + numAleatorio));

            JOptionPane.showMessageDialog(
                    null, mensaje.append("\nJuego terminado, hasta la proxima!!!\n"));
            nivelHuevo();
        }
    }

}


