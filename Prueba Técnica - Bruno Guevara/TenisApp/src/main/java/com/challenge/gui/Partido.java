package com.challenge.gui;

import com.challenge.puntuacion.NumerosRandom;
import com.challenge.puntuacion.Puntuacion;
import javax.swing.JOptionPane;
import jugadores.Jugador;



public class Partido extends javax.swing.JFrame {

    PantallaPrincipal pantalla = new PantallaPrincipal();
    NumerosRandom numerosRandom = new NumerosRandom();
    Puntuacion puntuacion = new Puntuacion();

    int sets = 0;
    int numero1;
    int numero2;
    String torneo;
    String puntosJ1;
    String puntosJ2;
    Jugador jugador1;
    Jugador jugador2;

    public Partido(String torneo, int setSeleccionado, Jugador jugador1, Jugador jugador2) {
        initComponents();
        this.sets = setSeleccionado;
        this.torneo = torneo;

        this.jugador1 = jugador1;
        this.jugador2 = jugador2;

        // Establecemos los datos ingresados por el usuario
        establecerDatosDelUsuario();

        // Definimos que jugador tiene el saque
        saqueAleatorio();

        limpiarCasillasPuntos();

    }

    public Partido() {

    }

    public void limpiarCasillasPuntos() {
        txtPuntosJ1.setText("0");
        txtPuntosJ2.setText("0");
    }

    public void mostrarPunto() {
        boolean x = true;
        puntosJ1 = "";
        puntosJ2 = "";
        while (x) {
            int J1 = numerosRandom.generarNumeroProbabilidad(jugador1.getProbabilidad());
            int J2 = numerosRandom.generarNumeroProbabilidad(jugador2.getProbabilidad());
            System.out.println("PROBABILIDAD DE J1: " + J1);
            System.out.println("PROBABILIDAD DE J2: " + J2);

            // Definimos quien se lleva el punto dependiendo las probabilidades y definiendo los empates
            // Si el jugador1 gana la probabilidad
            if (J1 > J2) {
                puntosJ1 = puntuacion.generarPuntos(txtPuntosJ1.getText());
                System.out.println("PUNTOS J1: " + puntosJ1);
                if (puntosJ1.equals("V") && !txtPuntosJ2.getText().equals("40") && !txtPuntosJ2.getText().equals("V")) {
                    puntosJ1 = "WIN";
                    System.out.println("PUNTOS J1: " + puntosJ1);
                } else if (puntosJ1.equals("V") && txtPuntosJ2.getText().equals("40")) {
                    txtPuntosJ1.setText("V");
                    return;
                }
                if (txtPuntosJ1.getText().equals("V") && puntosJ1.equals("V")) {
                    puntosJ1 = "WIN";
                } else if (puntosJ1.equals("V") && txtPuntosJ2.getText().equals("V")) {
                    txtPuntosJ1.setText("40");
                    txtPuntosJ2.setText("40");
                    return;
                }
                if (!puntosJ1.equals("WIN") && !puntosJ1.equals("V")) {
                    txtPuntosJ1.setText(puntosJ1);
                }
                x = false;
             // Si el jugador2 gana la probabilidad
            } else if (J2 > J1) {
                puntosJ2 = puntuacion.generarPuntos(txtPuntosJ2.getText());
                System.out.println("PUNTOS J2: " + puntosJ2);
                if (puntosJ2.equals("V") && !txtPuntosJ1.getText().equals("40") && !txtPuntosJ1.getText().equals("V")) {
                    puntosJ2 = "WIN";
                    System.out.println("PUNTOS J2: " + puntosJ2);
                } else if (puntosJ2.equals("V") && txtPuntosJ1.getText().equals("40")) {
                    txtPuntosJ2.setText("V");
                    return;
                }
                if (txtPuntosJ2.getText().equals("V") && puntosJ2.equals("V")) {
                    puntosJ1 = "WIN";
                } else if (puntosJ2.equals("V") && txtPuntosJ1.getText().equals("V")) {
                    txtPuntosJ1.setText("40");
                    txtPuntosJ2.setText("40");
                    return;
                }
                if (!puntosJ2.equals("WIN") && !puntosJ2.equals("V")) {
                    txtPuntosJ2.setText(puntosJ2);
                }
                x = false;
            }
        }

    }

    public void mostrarJuego() {
        // Definimos los puntos del juego del Jugador 1
        if (txtJuegosJ1.getText().equals("") || txtJuegosJ1.getText().equals("0")) {
            if (puntosJ1.equals("WIN")) {
                txtJuegosJ1.setText(puntuacion.generarJuegos(puntosJ1));
                limpiarCasillasPuntos();
                if (txtJuegosJ2.getText().equals("")) {
                    txtJuegosJ2.setText("0");
                }
                System.out.println("PUNTAJE JUEGO J1: " + puntuacion.generarJuegos(puntosJ1));
            }
        } else if (Integer.parseInt(txtJuegosJ1.getText()) > 0 && puntosJ1.equals("WIN")) {
            txtJuegosJ1.setText(Integer.toString(Integer.parseInt(txtJuegosJ1.getText()) + Integer.parseInt(puntuacion.generarJuegos(puntosJ1))));            
            limpiarCasillasPuntos();
            if (txtJuegosJ2.getText().equals("")) {
                txtJuegosJ2.setText("0");
            }
        }

        // Definimos los puntos del juego del Jugador 2
        if (txtJuegosJ2.getText().equals("") || txtJuegosJ2.getText().equals("0")) {
            if (puntosJ2.equals("WIN")) {
                txtJuegosJ2.setText(puntuacion.generarJuegos(puntosJ2));
                limpiarCasillasPuntos();                
                if (txtJuegosJ1.getText().equals("")) {
                    txtJuegosJ1.setText("0");
                }
            }
        } else if (Integer.parseInt(txtJuegosJ2.getText()) > 0 && puntosJ2.equals("WIN")) {
            txtJuegosJ2.setText(Integer.toString(Integer.parseInt(txtJuegosJ2.getText()) + Integer.parseInt(puntuacion.generarJuegos(puntosJ2))));
            System.out.println("PUNTAJE JUEGO J2 SUMA UN TOTAL DE: " + Integer.parseInt(txtJuegosJ2.getText()) + Integer.parseInt(puntuacion.generarJuegos(puntosJ1)));
            limpiarCasillasPuntos();
            if (txtJuegosJ1.getText().equals("")) {
                txtJuegosJ1.setText("0");
            }
        }

    }

    public void mostrarSet() {

        // Generamos los sets del J1
        if (txtSetsJ1.getText().equals("") && txtJuegosJ1.getText().length() > 0 && puntuacion.entrarASets(Integer.parseInt(txtJuegosJ1.getText()))) {
            txtSetsJ1.setText(Integer.toString(puntuacion.generarSets(Integer.parseInt(txtJuegosJ1.getText()), jugador1)));
            jugador2.juego.add(txtJuegosJ2.getText());
        } else if (txtSetsJ1.getText().length() > 0 && txtJuegosJ1.getText().length() > 0 && puntuacion.entrarASets(Integer.parseInt(txtJuegosJ1.getText()))) {
            txtSetsJ1.setText(Integer.toString(Integer.parseInt(txtSetsJ1.getText()) + puntuacion.generarSets(Integer.parseInt(txtJuegosJ1.getText()), jugador1)));
            jugador2.juego.add(txtJuegosJ2.getText());
        }

        // Generamos los sets del J1
        if (txtSetsJ2.getText().equals("") && txtJuegosJ2.getText().length() > 0 && puntuacion.entrarASets(Integer.parseInt(txtJuegosJ2.getText()))) {
            txtSetsJ2.setText(Integer.toString(puntuacion.generarSets(Integer.parseInt(txtJuegosJ2.getText()), jugador2)));
            jugador1.juego.add(txtJuegosJ1.getText());
        } else if (txtSetsJ2.getText().length() > 0 && txtJuegosJ2.getText().length() > 0 && puntuacion.entrarASets(Integer.parseInt(txtJuegosJ2.getText()))) {
            txtSetsJ2.setText(Integer.toString(Integer.parseInt(txtSetsJ2.getText()) + puntuacion.generarSets(Integer.parseInt(txtJuegosJ2.getText()), jugador2)));
            jugador1.juego.add(txtJuegosJ1.getText());
        }

        // Limpiamos las casillas
        if (txtJuegosJ1.getText().equals("6") || txtJuegosJ2.getText().equals("6")) {
            txtPuntosJ1.setText("0");
            txtPuntosJ2.setText("0");
            txtJuegosJ1.setText("");
            txtJuegosJ2.setText("");
        }
    }

    public void saqueAleatorio() {
        int saque = numerosRandom.generarDosNumeros();
        System.out.println("SAQUE: " + saque);
        if (saque == 1) {
            JOptionPane.showMessageDialog(null, "Empieza sacando el jugador: " + jugador1.getNombre());
        } else if (saque == 2) {
            JOptionPane.showMessageDialog(null, "Empieza sacando el jugador: " + jugador2.getNombre());
        }
    }

    public void mostrarGanador() {
        Resultado resultado = new Resultado(torneo, jugador1, jugador2);
        if ((txtSetsJ1.getText().equals("2") && sets == 3) || (txtSetsJ1.getText().equals("3") && sets == 5)) {
            JOptionPane.showMessageDialog(this, "El ganador del torneo " + torneo + " es: " + jugador1.getNombre(), "Torneo Finalizado", JOptionPane.INFORMATION_MESSAGE);
            this.dispose();
            resultado.setVisible(true);
        } else if ((txtSetsJ2.getText().equals("2") && sets == 3) || (txtSetsJ2.getText().equals("3") && sets == 5)) {
            JOptionPane.showMessageDialog(this, "El ganador del torneo " + torneo + " es: " + jugador2.getNombre(), "Torneo Finalizado", JOptionPane.INFORMATION_MESSAGE);
            this.dispose();
            resultado.setVisible(true);
        }
    }

    public void establecerDatosDelUsuario() {
        txtNombreJugador1.setText(jugador1.getNombre());
        txtNombreJugador2.setText(jugador2.getNombre());
        txtNombreTorneo.setText(torneo);
        txtPuntosJ1.setText("0");
        txtPuntosJ2.setText("0");
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel2 = new javax.swing.JPanel();
        jLabel5 = new javax.swing.JLabel();
        txtNombreTorneo = new javax.swing.JLabel();
        txtEstado = new javax.swing.JLabel();
        jPanel1 = new javax.swing.JPanel();
        txtNombreJugador2 = new javax.swing.JLabel();
        txtNombreJugador1 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jPanel3 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        txtPuntosJ1 = new javax.swing.JTextField();
        txtPuntosJ2 = new javax.swing.JTextField();
        jPanel4 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        txtJuegosJ1 = new javax.swing.JTextField();
        txtJuegosJ2 = new javax.swing.JTextField();
        jPanel5 = new javax.swing.JPanel();
        jLabel6 = new javax.swing.JLabel();
        txtSetsJ1 = new javax.swing.JTextField();
        txtSetsJ2 = new javax.swing.JTextField();
        btnResultado = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel2.setBackground(new java.awt.Color(204, 204, 255));
        jPanel2.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));

        jLabel5.setText("Semifinal  -");

        txtNombreTorneo.setText("NombreTorneo");

        txtEstado.setText("En curso");

        jPanel1.setBorder(javax.swing.BorderFactory.createEtchedBorder(java.awt.Color.black, null));

        txtNombreJugador2.setText("NombreJugador");

        txtNombreJugador1.setText("NombreJugador");

        jLabel3.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel3.setText("2  -");

        jLabel4.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel4.setText("1  -");

        jPanel3.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));

        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText("PUNTOS");

        txtPuntosJ1.setEditable(false);
        txtPuntosJ1.setHorizontalAlignment(javax.swing.JTextField.CENTER);

        txtPuntosJ2.setEditable(false);
        txtPuntosJ2.setHorizontalAlignment(javax.swing.JTextField.CENTER);

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel3Layout.createSequentialGroup()
                    .addGap(11, 11, 11)
                    .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addComponent(txtPuntosJ2)
                        .addComponent(txtPuntosJ1, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addContainerGap(13, Short.MAX_VALUE)))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(55, 55, 55)
                .addComponent(jLabel1)
                .addContainerGap(61, Short.MAX_VALUE))
            .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel3Layout.createSequentialGroup()
                    .addGap(7, 7, 7)
                    .addComponent(txtPuntosJ1, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 62, Short.MAX_VALUE)
                    .addComponent(txtPuntosJ2, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(7, 7, 7)))
        );

        jPanel4.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));

        jLabel2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel2.setText("JUEGOS");

        txtJuegosJ1.setEditable(false);
        txtJuegosJ1.setHorizontalAlignment(javax.swing.JTextField.CENTER);

        txtJuegosJ2.setEditable(false);
        txtJuegosJ2.setHorizontalAlignment(javax.swing.JTextField.CENTER);

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel2)
                .addContainerGap(15, Short.MAX_VALUE))
            .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel4Layout.createSequentialGroup()
                    .addGap(11, 11, 11)
                    .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addComponent(txtJuegosJ2)
                        .addComponent(txtJuegosJ1, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGap(53, 53, 53)
                .addComponent(jLabel2)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel4Layout.createSequentialGroup()
                    .addGap(7, 7, 7)
                    .addComponent(txtJuegosJ1, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 62, Short.MAX_VALUE)
                    .addComponent(txtJuegosJ2, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(7, 7, 7)))
        );

        jPanel5.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));

        jLabel6.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel6.setText("SETS");

        txtSetsJ1.setEditable(false);
        txtSetsJ1.setHorizontalAlignment(javax.swing.JTextField.CENTER);

        txtSetsJ2.setEditable(false);
        txtSetsJ2.setHorizontalAlignment(javax.swing.JTextField.CENTER);

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(13, Short.MAX_VALUE))
            .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel5Layout.createSequentialGroup()
                    .addGap(11, 11, 11)
                    .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addComponent(txtSetsJ2)
                        .addComponent(txtSetsJ1, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addGap(53, 53, 53)
                .addComponent(jLabel6)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel5Layout.createSequentialGroup()
                    .addGap(7, 7, 7)
                    .addComponent(txtSetsJ1, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 62, Short.MAX_VALUE)
                    .addComponent(txtSetsJ2, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(7, 7, 7)))
        );

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(1, 1, 1)
                        .addComponent(txtNombreJugador2, javax.swing.GroupLayout.PREFERRED_SIZE, 103, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtNombreJugador1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(24, 24, 24)
                .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(25, 25, 25)
                .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(34, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(24, 24, 24)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtNombreJugador1, javax.swing.GroupLayout.PREFERRED_SIZE, 21, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(txtNombreJugador2))
                .addGap(29, 29, 29))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 63, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtNombreTorneo, javax.swing.GroupLayout.PREFERRED_SIZE, 183, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(84, 84, 84)
                .addComponent(txtEstado, javax.swing.GroupLayout.PREFERRED_SIZE, 88, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel5)
                    .addComponent(txtEstado)
                    .addComponent(txtNombreTorneo))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        btnResultado.setText("Empezar");
        btnResultado.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnResultadoActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel2, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(btnResultado)
                .addGap(181, 181, 181))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnResultado, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnResultadoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnResultadoActionPerformed
        btnResultado.setText("Continuar");
        // Generamos puntos a los jugadores
        mostrarPunto();

        // Generamos los juegos a los jugadores
        mostrarJuego();

        // Generamos los sets a los jugadores
        mostrarSet();
        
        // Mostramos al ganador
        mostrarGanador();

    }//GEN-LAST:event_btnResultadoActionPerformed

    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(Partido.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Partido.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Partido.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Partido.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Partido().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnResultado;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JLabel txtEstado;
    private javax.swing.JTextField txtJuegosJ1;
    private javax.swing.JTextField txtJuegosJ2;
    private javax.swing.JLabel txtNombreJugador1;
    private javax.swing.JLabel txtNombreJugador2;
    private javax.swing.JLabel txtNombreTorneo;
    private javax.swing.JTextField txtPuntosJ1;
    private javax.swing.JTextField txtPuntosJ2;
    private javax.swing.JTextField txtSetsJ1;
    private javax.swing.JTextField txtSetsJ2;
    // End of variables declaration//GEN-END:variables
}
