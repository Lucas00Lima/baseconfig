package com.example;

import java.io.IOException;
import javax.swing.JOptionPane;

public class App {
    public static void main(String[] args) throws IOException, InterruptedException {
        String[] options = { "Restaurante", "Varejo" };
        int response = JOptionPane.showOptionDialog(null, "Escolha", "Aviso", JOptionPane.DEFAULT_OPTION,
                JOptionPane.QUESTION_MESSAGE, null, options, options);
        Restaurante restaurante = new Restaurante();
        Servico servico = new Servico();
        if (response == 0) {
            if (restaurante.exist() == true) {
                System.out.println("Existe");
                JOptionPane.showMessageDialog(null, "Você já esta utilizando o modo Restaurante",
                        "Erro", JOptionPane.ERROR_MESSAGE);
            } else if (restaurante.exist() == false) {
                restaurante.restaurante();
                restaurante.renameFiles();
                JOptionPane.showMessageDialog(null, "A mudança para o modo de Restaurante FUNCIONOU, reinicie o sistema SOMA", "Concluido",
                        JOptionPane.INFORMATION_MESSAGE, null);
            }
        } else if (response == 1) {
            if (servico.exist() == true) {
                System.out.println("Existe");
                JOptionPane.showMessageDialog(null, "Você já esta utilizando o modo Varejo",
                        "Erro", JOptionPane.ERROR_MESSAGE);
            } else if (servico.exist() == false) {
                servico.servico();
                servico.renameFiles();
                JOptionPane.showMessageDialog(null, "A mudança para o modo de Varejo FUNCIONOU, reinicie o sistema SOMA", "Concluido",
                        JOptionPane.INFORMATION_MESSAGE, null);
            }
        }
    }
}