/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package components;

import java.awt.Dimension;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.io.Serializable;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

/**
 *
 * @author Rafael
 */
public class InputAutovalidante extends JPanel implements Serializable {

    private String tipoValidacion;

    // Componentes
    private JLabel label;
    private JTextField textField;

    public InputAutovalidante() {
        initComponents();

        this.add(textField);
        this.add(label);
    }

    /**
     * Inicia los componetes y define las configuraciones por defecto de cada
     * uno
     */
    private void initComponents() {
        label = new JLabel();
        textField = new JTextField();

        textField.setPreferredSize(new Dimension(250, 25));
        label.setPreferredSize(new Dimension(250, 25));

        textField.addFocusListener(new FocusListener() {
            @Override
            public void focusGained(FocusEvent e) {
                label.setVisible(false);
            }

            @Override
            public void focusLost(FocusEvent e) {
                if (!tipoValidacion.isEmpty()) {
                    if (!textField.getText().isEmpty()) {
                        switch (tipoValidacion) {
                            case "cp":
                                if (!validarCP(textField.getText())) {
                                    label.setText("Solo números entre 00000 - 99999");
                                    label.setVisible(true);
                                } else {
                                    label.setText("Correcto.");
                                    label.setVisible(true);
                                }
                                break;
                            case "tel":
                                if (!validarTel(textField.getText())) {
                                    label.setText("El Telefono no está correcto.");
                                    label.setVisible(true);
                                } else {
                                    label.setText("Correcto.");
                                    label.setVisible(true);
                                }
                                break;
                            case "dni":
                                if (!validarDNI(textField.getText())) {
                                    label.setText("El DNI no está correcto.");
                                    label.setVisible(true);
                                } else {
                                    label.setText("Correcto.");
                                    label.setVisible(true);
                                }
                                break;
                            default:
                                throw new IllegalStateException("Unexpected value: " + tipoValidacion
                                        + ", valores admitidos: tel, dni, cp"
                                );
                        }
                    } else {
                        label.setText("El campo no puede estar vacío.");
                        label.setVisible(true);
                    }
                } else {
                    label.setText("No se ha definido un tipo de validación");
                    label.setVisible(true);
                }
            }
        });

    }

    private boolean validarDNI(String DNI) {
        if (DNI.length() == 9) {
            try {
                Integer.parseInt(DNI.substring(0, 8));
            } catch (NumberFormatException nfe) {
                return false;
            }
            try {
                char letra = DNI.charAt(8);
                Integer.parseInt(letra + "");
                return false;
            } catch (NumberFormatException nfe) {
                return true;
            }
        } else {
            return false;
        }
    }

    /**
     * Comprueba si un Codigo posta esta correcto o no.
     *
     * @param CP Codigo postal a comprobar.
     * @return true si esta bien, false en caso contrario.
     */
    private boolean validarCP(String CP) {
        if (CP.length() != 5) {
            return false;
        } else {
            try {
                Integer.parseInt(CP);
                return true;
            } catch (NumberFormatException nfe) {
                return false;
            }
        }
    }

    /**
     * Compueba si un telefono esta bien escrito.
     *
     * @param tel Telefono a comprobar
     * @return true si esta bien, false en caso contrario.
     */
    private boolean validarTel(String tel) {
        if (tel.length() != 9) {
            return false;
        } else {
            try {
                Integer.parseInt(tel);
                return true;
            } catch (NumberFormatException nfe) {
                return false;
            }
        }

    }

    public String getTipoValidacion() {
        return tipoValidacion;
    }

    public void setTipoValidacion(String tipoValidacion) {
        this.tipoValidacion = tipoValidacion;
    }

}
