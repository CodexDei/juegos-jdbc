package org.codexdei.juegos;

import javax.swing.*;
import java.util.InputMismatchException;
import java.util.Random;

public class Salvado {

    private static boolean estado = false;
    private static int puntaje = 0;
    private static int acumulacionPuntaje = 0;
    private static int bonus = 0;
    private static StringBuilder mensaje;

    public static void salvado() {

        do {

            try {

                String menu = JOptionPane.showInputDialog("Ingrese una opcion valida:\n\n"
                        + "1. Jugar org.codexdei.juegos.Salvado\n"
                        + "2. Salir").trim();

                if (menu.isBlank() || menu == null){
                    JOptionPane.showMessageDialog(null,
                            "No puede ingresar un valor en blanco o vacio, ingrese una letra",
                            "INGRESE UN VALOR VALIDO",JOptionPane.WARNING_MESSAGE);
                    continue;
                }

                int opcionMenu = Integer.parseInt(menu);

                switch (opcionMenu) {

                    case 1:

                        nivelFacil();
                        break;

                    case 2:

                        estado = true;
                        break;


                    default:

                        throw new IllegalArgumentException("Ingrese solo las opciones del menu");
                }

            } catch (InputMismatchException e) {

                JOptionPane.showMessageDialog(null,
                        "INGRESE UN VALOR VALIDO", "ERROR VALOR INVALIDO", JOptionPane.WARNING_MESSAGE);

            } catch (Exception e) {

                JOptionPane.showMessageDialog(null,
                        "ERROR: " + e.getMessage(), "ERROR", JOptionPane.WARNING_MESSAGE);

                e.printStackTrace();
            }

        } while (!estado);
    }

    private static void nivelFacil() {

        // Array de palabras para adivinar
        String[] palabras = {
                "casa", "perro", "gato", "libro", "sol", "mesa", "silla", "plato", "arbol", "flor", "biblia", "paz", "perdon",
                "agua", "mano", "dedo", "pie", "nariz", "boca", "ojos", "cara", "luna", "estrella", "culto", "sol",
                "papel", "lapiz", "goma", "lapicera", "hoja", "botella", "telefono", "computadora", "television", "radio",
                "piso", "pared", "puerta", "ventana", "techo", "escalera", "cocina", "baño", "habitacion", "dormitorio",
                "cielo", "tierra", "mar", "rio", "montaña", "pais", "ciudad", "calle", "camino", "carro", "biblia",
                "pera", "manzana", "banana", "sandia", "uva", "limon", "naranja", "kiwi", "melon", "coco", "perdon",
                "elefante", "leon", "tigre", "jirafa", "cebra", "rinoceronte", "hipopotamo", "cocodrilo", "serpiente", "tortuga",
                "abeja", "mariposa", "mosquito", "araña", "mosca", "hormiga", "avispa", "escarabajo", "grillo", "cucaracha",
                "mesa", "silla", "sofa", "sillon", "mesita", "escritorio", "estante", "armario", "cajonera", "espejo",
                "pantalon", "camisa", "camiseta", "chaqueta", "abrigo", "vestido", "falda", "zapatos", "botas", "zapatillas"
        };

        acumulacionPuntaje = 0;

//         Elegir una palabra aleatoria del array
//    String palabra = palabras[(int)(Math.random() * palabras.length)];

//        Otra forma de elegir una palabra aleatoria del array

        Random random = new Random();

        int palabraAleatoria = random.nextInt(palabras.length);

        String palabra = palabras[palabraAleatoria];

        // Crear un array de caracteres para representar la palabra oculta
        char[] palabraOculta = new char[palabra.length()];
        for (int i = 0; i < palabraOculta.length; i++) {
            palabraOculta[i] = '☺';
        }

        // Contador de intentos restantes, intentos sera igual al doble de letras
        int intentos = palabra.length() * 2;
        while (intentos > 0) {

            String letraUsuario = JOptionPane.showInputDialog("NIVEL BALBUCEO\n" + "Adivina la palabra: "
                    + new String(palabraOculta) + "\n" + "Tienes: " + intentos + " intentos\n" + "Ingresa una letra").trim();

            //si ingresa un vacio o espacio
            if (letraUsuario.isBlank()){
                JOptionPane.showMessageDialog(null,
                        "No puede ingresar un valor en blanco o vacio, ingrese una letra",
                        "INGRESE UN VALOR VALIDO",JOptionPane.WARNING_MESSAGE);
                continue;
            }


            if (letraUsuario.length() != 1) {

                JOptionPane.showMessageDialog(
                        null, "Ingrese solo una letra tramposin, por trampos@ pierdes un intento",
                        "CUIDADITO", JOptionPane.WARNING_MESSAGE);
                --intentos;
                continue;//Vuelve al ciclo
            }
            // Obtener la letra ingresada por el usuario
            char letra = letraUsuario.charAt(0);
            boolean letraEncontrada = false;

            // Verificar si la letra está en la palabra y actualizar la palabra oculta
            for (int i = 0; i < palabra.length(); i++) {
                if (palabra.charAt(i) == letra) {
                    palabraOculta[i] = letra;
                    letraEncontrada = true;
                }
            }

            // Decrementar el contador de intentos si la letra no se encontró en la palabra
            if (!letraEncontrada) {
                intentos--;
                JOptionPane.showMessageDialog(null,
                        "La letra " + "'" + letraUsuario + "'" + " no esta en la palabra, intente de nuevo"
                        , "Letra incorrecta", JOptionPane.WARNING_MESSAGE);

            } else if (new String(palabraOculta).equals(palabra)) {
                // Mostrar mensaje de victoria si el usuario adivinó la palabra
                JOptionPane.showMessageDialog(null,
                        "¡Felicidades! Has adivinado la palabra: " + palabra);


                mensaje = new StringBuilder("NIVEL BALBUCEO SUPERADO\n");

                //puntuacion de acuerdo al numero de intentos
                if (intentos == (palabra.length() * 2)) {

                    mensaje.append("NIVEL DEL JUGADOR: 'LINGÜISTA' (por lograrlo SIN gastar intentos)\n");
                    puntaje = 1000;

                } else if (intentos > palabra.length() && intentos < (palabra.length() * 2)) {

                    mensaje.append("NIVEL DEL JUGADOR: 'PROFE LENGUAJE' (por lograrlo a los " + intentos + " intentos" + "\n");
                    puntaje = 500;

                } else if ((intentos <= palabra.length()) && (intentos > 1)) {

                    mensaje.append("NIVEL DEL JUGADOR: 'ESTUDIANTE DE ESPAÑOL' (por lograrlo a los " + intentos + " intentos" + "\n");
                    puntaje = 250;
                } else if (intentos == 1) {

                    mensaje.append("NIVEL DEL JUGADOR: 'Bebe Suertudo' (por lograrlo en el ultimo intento)\n");
                    puntaje = 100;
                }

                acumulacionPuntaje += puntaje;

                mensaje.append("PUNTAJE: " + puntaje + "\n" + " PUNTAJE ACUMULADO: " + acumulacionPuntaje + "\n");

                JOptionPane.showMessageDialog(null,
                        mensaje, "NIVEL BALBUCEO SUPERADO, pasaste al siguiente nivel", JOptionPane.INFORMATION_MESSAGE);

                nivelMedio();
                return;
            }
        }

        // Mostrar mensaje de derrota si el usuario se quedó sin intentos
        if (intentos == 0) {
            JOptionPane.showMessageDialog(
                    null, "¡Oh no! Te has quedado sin intentos. La palabra era: " + palabra);
        }
    }

    public static void nivelMedio() {

        String[] palabras = {
                "mochila", "carpeta", "computadora", "manzana", "Ricardo", "naranja", "Marina", "teclado", "bicicleta", "Samantha",
                "pantalon", "Alejandro", "zapatos", "reloj", "cuchillo", "tenedor", "cuchara", "dinosaurio", "elefante", "jirafa",
                "cuaderno", "cartuchera", "escritorio", "calendario", "calculadora", "guitarra", "violoncello", "piano", "microfono", "televisor",
                "camioneta", "motocicleta", "helicoptero", "Jorge", "Maryenny", "Oscar", "bicicleta", "motocicleta", "velero", "lancha",
                "circulo", "cuadrado", "rectangulo", "triangulo", "rombo", "hexagono", "octagono", "heptagono", "pentagono", "estrella",
                "manzana", "pera", "banano", "sandia", "fresa", "uva", "mango", "papaya", "coco", "kiwi",
                "Jazmin", "Jesus", "mandarina", "tamarindo", "ciruela", "cereza", "durazno", "Damasco", "melon", "higo",
                "sol", "luna", "estrella", "Juan", "lluvia", "viento", "tormenta", "rayo", "trueno", "arcoiris",
                "rio", "mar", "lago", "laguna", "cascada", "charco", "pozo", "pantano", "lagarto", "cocodrilo",
                "David", "revista", "periodico", "novela", "poesia", "Jhoel", "drama", "comedia", "tragedia", "accion"
        };

        //Random simple
//        String palabra = palabras[((int)(Math.random() * palabras.length))];
        //Random con clase Random
        Random random = new Random();

        int indicePalabras = random.nextInt(palabras.length);

        String palabra = palabras[indicePalabras];

        char[] palabraOculta = new char[palabra.length()];
        for (int i = 0; i < palabraOculta.length; i++) {
            palabraOculta[i] = '♫';
        }

        int intentos = palabra.length();
        while (intentos > 0) {

            String letraUsuario = JOptionPane.showInputDialog("NIVEL ESTUDIANTE DE ESPAÑOL\n"
                    + "PUNTAJE ACUMULADO: " + acumulacionPuntaje + "\n" + "Intentos: " + intentos + "\n"
                    + "Adivina la Palabra: " + new String(palabraOculta) + "\n"
                    + "Ingrese una letra: (si es un nombre la primera letra es mayuscula)").trim();

            if (letraUsuario.isBlank()){
                JOptionPane.showMessageDialog(null,
                        "No puede ingresar un valor en blanco o vacio, ingrese una letra",
                        "INGRESE UN VALOR VALIDO",JOptionPane.WARNING_MESSAGE);
                continue;
            }

            if (letraUsuario.length() != 1) {
                JOptionPane.showMessageDialog(null,
                        "Ingrese solo una letra tramposin, se te quitara 1 intento por tramposo",
                        "TRAMPOSO", JOptionPane.WARNING_MESSAGE);
                --intentos;
                continue;
            }

            char letra = letraUsuario.charAt(0);
            boolean letraEncontrada = false;

            //valida si la letra esta en la palabra
            for (int i = 0; i < palabra.length(); i++) {

                if (palabra.charAt(i) == letra) {
                    palabraOculta[i] = letra;
                    letraEncontrada = true;
                }
            }
            if (!letraEncontrada) {
                --intentos;
                JOptionPane.showMessageDialog(null,
                        "La letra " + "'" + letraUsuario + "'" + " no esta en la palabra, intente de nuevo"
                        , "Letra incorrecta", JOptionPane.WARNING_MESSAGE);

            } else if (new String(palabraOculta).equals(palabra)) {
                JOptionPane.showMessageDialog(null,
                        "Felicitaciones, adivinaste la palabra: " + palabra, "ADIVINASTE LA PALABRA", JOptionPane.INFORMATION_MESSAGE);
                mensaje = new StringBuilder("NIVEL BALBUCEO SUPERADO\n");

                //puntuacion de acuerdo al numero de intentos
                if (intentos == palabra.length()) {

                    mensaje.append("NIVEL DEL JUGADOR: 'LINGÜISTA' (por lograrlo SIN gastar intentos)\n");
                    puntaje = 2000;

                } else if ( intentos <= palabra.length() && (intentos > 1) ) {

                    mensaje.append("NIVEL DEL JUGADOR: 'PROFE LENGUAJE' (por lograrlo a los " + intentos + " intentos" + "\n");
                    puntaje = 1000;

                } else if (intentos == 1) {

                    mensaje.append("NIVEL DEL JUGADOR: 'Bebe Suertudo' (por lograrlo en el ultimo intento)\n");
                    puntaje = 500;
                }

                acumulacionPuntaje += puntaje;

                mensaje.append("PUNTAJE: " + puntaje + "\n" + " PUNTAJE ACUMULADO: " + acumulacionPuntaje + "\n");

                JOptionPane.showMessageDialog(null,
                        mensaje, "NIVEL BALBUCEO SUPERADO, pasaste al siguiente nivel", JOptionPane.INFORMATION_MESSAGE);

                nivelDificil();
                return;
            }


        }
        // Mostrar mensaje de derrota si el usuario se quedó sin intentos
        if (intentos == 0) {
            JOptionPane.showMessageDialog(
                    null, "¡Oh no! Te has quedado sin intentos. La palabra era: " + palabra);

            JOptionPane.showMessageDialog(null,
                    "PUNTAJE: " + puntaje + "\n" + " PUNTAJE ACUMULADO: " + acumulacionPuntaje + "\n");
        }
        //Reiniciar juego
//        salvado();
    }

    public static void nivelDificil(){

        // Array de palabras para adivinar
        String[] palabras = {
                "accesibilidad", "acogedor", "acoplamiento", "complemento", "conciliacion", "confidencialidad", "consistencia", "democracia",
                "desarrollo", "destornillador", "discriminacion", "educacion", "efectividad", "eficiencia", "electrodomestico", "enumeracion",
                "estadistica", "exclusividad", "extorsion", "falacia","financiamiento", "fragmentacion", "fundamentalismo", "homologacion",
                "hospitalizacion", "incomunicacion", "independencia", "indiscrecion", "industrializacion", "inestabilidad","infraestructura",
                "intelectualidad", "inteligibilidad", "interdependencia", "interrupcion", "manipulacion", "multiculturalismo", "nacionalizacion",
                "nomenclatura", "objecion", "parlamentarismo", "participacion", "perpendicularidad", "postergacion", "precariedad", "protagonismo",
                "provisionalidad", "reconstruccion", "reestructuracion", "regularizacion", "sacrilegio", "sensibilidad", "situacion", "sostenibilidad",
                "subordinacion", "superposicion", "supervivencia", "susceptibilidad", "tecnologia", "trascendencia","ubicacion", "uniformidad",
                "universalidad", "utilidad", "valoracion", "variabilidad", "veracidad", "voluntad", "vulnerabilidad", "sabiduria"
        };

//         Elegir una palabra aleatoria del array
//    String palabra = palabras[(int)(Math.random() * palabras.length)];

//        Otra forma de elegir una palabra aleatoria del array

        Random random = new Random();

        int palabraAleatoria = random.nextInt(palabras.length);

        String palabra = palabras[palabraAleatoria];

        // Crear un array de caracteres para representar la palabra oculta
        char[] palabraOculta = new char[palabra.length()];
        for (int i = 0; i < palabraOculta.length; i++) {
            palabraOculta[i] = '_';
        }

        // Contador de intentos restantes, intentos sera igual al doble de letras
        int intentos = (palabra.length() / 4);
        while (intentos > 0) {

            String letraUsuario = JOptionPane.showInputDialog("NIVEL LINGÜISTA\n" + "Adivina la palabra: "
                    + new String(palabraOculta) + "\n" + "Tienes: " + intentos + " intentos\n" + "Ingresa una letra").trim();

            if (letraUsuario.isBlank()){
                JOptionPane.showMessageDialog(null,
                        "No puede ingresar un valor en blanco o vacio, ingrese una letra",
                        "INGRESE UN VALOR VALIDO",JOptionPane.WARNING_MESSAGE);
                continue;
            }

            if (letraUsuario.length() != 1) {

                JOptionPane.showMessageDialog(
                        null, "Ingrese solo una letra tramposin, por trampos@ pierdes un intento",
                        "CUIDADITO", JOptionPane.WARNING_MESSAGE);
                --intentos;
                continue;//Vuelve al ciclo
            }
            // Obtener la letra ingresada por el usuario
            char letra = letraUsuario.charAt(0);
            boolean letraEncontrada = false;

            // Verificar si la letra está en la palabra y actualizar la palabra oculta
            for (int i = 0; i < palabra.length(); i++) {
                if (palabra.charAt(i) == letra) {
                    palabraOculta[i] = letra;
                    letraEncontrada = true;
                }
            }

            // Decrementar el contador de intentos si la letra no se encontró en la palabra
            if (!letraEncontrada) {
                intentos--;
                JOptionPane.showMessageDialog(null,
                        "La letra " + "'" + letraUsuario + "'" + " no esta en la palabra, intente de nuevo"
                        , "Letra incorrecta", JOptionPane.WARNING_MESSAGE);

            } else if (new String(palabraOculta).equals(palabra)) {
                // Mostrar mensaje de victoria si el usuario adivinó la palabra
                JOptionPane.showMessageDialog(null,
                        "¡Felicidades! Has adivinado la palabra: " + palabra);


                mensaje = new StringBuilder("NIVEL LINGÜISTA SUPERADO\n");

                //puntuacion de acuerdo al numero de intentos
                if (intentos == (palabra.length() / 4)) {

                    mensaje.append("NIVEL DEL JUGADOR: 'LINGÜISTA' (por lograrlo SIN gastar intentos)\n");
                    puntaje = 3000;

                } else if (intentos <= (palabra.length() / 4) && (intentos > 1)) {

                    mensaje.append("NIVEL DEL JUGADOR: 'PROFE LENGUAJE' (por lograrlo a los " + intentos + " intentos" + "\n");
                    puntaje = 1500;

                } else if (intentos == 1) {

                    mensaje.append("NIVEL DEL JUGADOR: 'Bebe Suertudo' (por lograrlo en el ultimo intento)\n");
                    puntaje = 700;
                }

                acumulacionPuntaje += puntaje;

                mensaje.append("PUNTAJE: " + puntaje + "\n" + " PUNTAJE ACUMULADO: " + acumulacionPuntaje + "\n");

                JOptionPane.showMessageDialog(null,
                        mensaje, "HAS RESCATADO EL JUEGO\n\n", JOptionPane.INFORMATION_MESSAGE);

                JOptionPane.showMessageDialog(null,

                        mensaje.append("ⒼⒶⓃⒶⓈⓉⒺ, ⒹⒾⓄⓈ ⓉⒺ ⒷⒺⓃⒹⒾⒼⒶ\n"));

                JOptionPane.showMessageDialog(null,
                        "ⒻⒶⓂⒾⓁⒾⒶ ⒶⒸⓄⓈⓉⒶ ⓂⓄⓇⒶ");

                return;
            }
        }

        // Mostrar mensaje de derrota si el usuario se quedó sin intentos
        if (intentos == 0) {
            JOptionPane.showMessageDialog(
                    null, "¡Oh no! Te has quedado sin intentos. La palabra era: " + palabra);

            JOptionPane.showMessageDialog(null,
                    "PUNTAJE: " + puntaje + "\n" + " PUNTAJE ACUMULADO: " + acumulacionPuntaje + "\n");
        }
    }


}