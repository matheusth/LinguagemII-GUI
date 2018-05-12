package com.company.gui;

import com.company.com.company.file.FileManipulation;
import sun.awt.X11.Screen;

import javax.swing.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;


public class MainScreen {
    public void draw() {
        ArrayList<String> lista = new ArrayList<>();
        JFrame mainFrame = new JFrame("Exercicio GUI");
        JLabel lblCPF = new JLabel("CPF:");
        JLabel lblNome = new JLabel("Nome:");
        JLabel lblDtNasc = new JLabel("Dt Nasc:");
        JLabel lblPath = new JLabel("Path:");
        JLabel lblSexo = new JLabel("Sexo:");


        JPanel oPanel = new JPanel();
        JScrollPane oScrollPane = new JScrollPane();
        oScrollPane.setBounds(100, 200, 500, 100);

        oPanel.add(oScrollPane);

        JList<Object> oListSource = new JList<>(lista.toArray());
        oListSource.setName("Minha Lista");
        oScrollPane.setViewportView(oListSource);

        mainFrame.add(oScrollPane);


        JTextField txtCPF = new JTextField();
        JTextField txtNome = new JTextField();
        JTextField txtDtNasc = new JTextField();
        JTextField txtPath = new JTextField();
        JComboBox<String> cmbSexo = new JComboBox<>();

        JButton btnSalvar = new JButton("Salvar");
        JButton btnLimpar = new JButton("Limpar");
        btnSalvar.setBounds(200, 150, 100, 20);
        btnLimpar.setBounds(330, 150, 100, 20);

        cmbSexo.setBounds(480, 60, 200, 20);
        cmbSexo.addItem("-Selecione-");
        cmbSexo.addItem("Masculino");
        cmbSexo.addItem("Feminino");


        JButton btnRemover = new JButton("Remover");

        btnRemover.setBounds(290, 10, 100, 20);
        mainFrame.setLayout(null);
        mainFrame.setBounds(500, 400, 800, 600);

        lblCPF.setBounds(38, 10, 50, 20);
        lblNome.setBounds(25, 35, 50, 20);

        lblDtNasc.setBounds(13, 60, 100, 20);
        lblPath.setBounds(33, 110, 50, 20);
        lblSexo.setBounds(430, 60, 100, 20);

        txtCPF.setBounds(80, 10, 200, 20);
        txtNome.setBounds(80, 35, 600, 20);
        txtDtNasc.setBounds(80, 60, 200, 20);
        txtPath.setBounds(80, 110, 600, 20);
        txtPath.setText("clock");

        oListSource.setListData(getLines(txtPath.getText()));


        mainFrame.setDefaultCloseOperation(2);
        mainFrame.add(txtPath);
        mainFrame.add(btnRemover);
        mainFrame.add(lblSexo);
        mainFrame.add(txtDtNasc);
        mainFrame.add(txtNome);
        mainFrame.add(txtCPF);
        mainFrame.add(lblPath);
        mainFrame.add(lblDtNasc);
        mainFrame.add(lblCPF);
        mainFrame.add(lblNome);
        mainFrame.add(cmbSexo);
        mainFrame.add(btnSalvar);
        mainFrame.add(btnLimpar);
        mainFrame.setVisible(true);

        btnSalvar.addMouseListener(new MouseAdapter() {
                                       @Override
                                       public void mouseClicked(MouseEvent mouseEvent) {
                                           if ((txtNome.getText().equals("")) || (txtCPF.getText().equals("")) || (txtDtNasc.getText().equals("")) || (cmbSexo.getSelectedIndex() == 0) || (txtPath.getText().equals(""))) {
                                               JOptionPane.showMessageDialog(mainFrame, "Preencha todos os campos", "Error", JOptionPane.ERROR_MESSAGE);
                                           } else {
                                               try {
                                                   FileManipulation.write(txtPath.getText(), txtCPF.getText().trim() + " | " + txtNome.getText().trim() + " | " + txtDtNasc.getText().trim() + " | " + cmbSexo.getSelectedItem().toString().trim());
                                                   oListSource.setListData(getLines(txtPath.getText()));
                                                   oListSource.repaint();
                                               } catch (IOException e) {
                                                   e.printStackTrace();
                                               }
                                           }
                                       }
                                   }

        );
        btnRemover.addMouseListener(new MouseAdapter() {
                                        @Override
                                        public void mouseClicked(MouseEvent mouseEvent) {
                                            try {
                                                if (!txtCPF.getText().equals("") && !txtPath.getText().equals(""))
                                                    FileManipulation.delete(txtPath.getText(), txtCPF.getText());
                                                oListSource.setListData(getLines(txtPath.getText()));
                                            } catch (IOException e) {
                                                e.printStackTrace();
                                            }

                                        }
                                    }

        );
        btnLimpar.addMouseListener(new MouseAdapter() {
                                       @Override
                                       public void mouseClicked(MouseEvent mouseEvent) {
                                           txtCPF.setText("");
                                           txtNome.setText("");
                                           txtDtNasc.setText("");
                                           cmbSexo.setSelectedIndex(0);
                                       }
                                   }

        );


    }

    String[] getLines(String filePath) {
        String[] result = new String[0];
        try {
            result = FileManipulation.readFile(filePath).split("\n");
        } catch (IOException e) {
            e.printStackTrace();
        }
        return result;
    }
}


