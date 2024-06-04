package br.com.javaricci;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

public class MainFrame extends JFrame {
    private FuncionarioDAO funcionarioDAO;
    private JTextField txtNome, txtSalario, txtHorasBase, txtSalHora, txtSalDia, txtId;
    private JTextArea txtAreaOutput;

    public MainFrame() {
        funcionarioDAO = new FuncionarioDAO();
        initComponents();
    }

    private void initComponents() {
        setTitle("CRUD-Arquivo JSON Banco Dados");
        setSize(600, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        JMenuBar menuBar = new JMenuBar();
        JMenu menu = new JMenu("Operações");

        JMenuItem menuAdd = new JMenuItem("Adicionar");
        JMenuItem menuUpdate = new JMenuItem("Alterar");
        JMenuItem menuDelete = new JMenuItem("Deletar");
        JMenuItem menuGetById = new JMenuItem("Consultar Por ID");
        JMenuItem menuGetAll = new JMenuItem("Consultar Todos");
        JMenuItem menuGeneratePDF = new JMenuItem("Gerar PDF");

        menuAdd.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                addFuncionario();
            }
        });

        menuUpdate.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                updateFuncionario();
            }
        });

        menuDelete.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                deleteFuncionario();
            }
        });

        menuGetById.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                getFuncionarioById();
            }
        });

        menuGetAll.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                getAllFuncionarios();
            }
        });

        menuGeneratePDF.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                generatePDF();
            }
        });

        menu.add(menuAdd);
        menu.add(menuUpdate);
        menu.add(menuDelete);
        menu.add(menuGetById);
        menu.add(menuGetAll);
        menu.add(menuGeneratePDF);
        menuBar.add(menu);
        setJMenuBar(menuBar);

        JPanel panel = new JPanel(new GridLayout(6, 2));
        panel.add(new JLabel("ID:"));
        txtId = new JTextField();
        panel.add(txtId);
        panel.add(new JLabel("Nome:"));
        txtNome = new JTextField();
        panel.add(txtNome);
        panel.add(new JLabel("Salário:"));
        txtSalario = new JTextField();
        panel.add(txtSalario);
        panel.add(new JLabel("Horas Base:"));
        txtHorasBase = new JTextField();
        panel.add(txtHorasBase);
        panel.add(new JLabel("Salário Hora:"));
        txtSalHora = new JTextField();
        panel.add(txtSalHora);
        panel.add(new JLabel("Salário Dia:"));
        txtSalDia = new JTextField();
        panel.add(txtSalDia);

        txtAreaOutput = new JTextArea();
        txtAreaOutput.setEditable(false);
        JScrollPane scrollPane = new JScrollPane(txtAreaOutput);

        add(panel, BorderLayout.NORTH);
        add(scrollPane, BorderLayout.CENTER);
    }

    private void addFuncionario() {
        Funcionario func = new Funcionario();
        func.setNomeFunc(txtNome.getText());
        func.setSalarioFunc(Double.parseDouble(txtSalario.getText()));
        func.setHorasBaseFunc(Integer.parseInt(txtHorasBase.getText()));
        func.setSalHoraFunc(Double.parseDouble(txtSalHora.getText()));
        func.setSalDiaFunc(Double.parseDouble(txtSalDia.getText()));
        funcionarioDAO.addFuncionario(func);
        clearFields();
        txtAreaOutput.setText("Funcionário adicionado com sucesso!");
    }

    private void updateFuncionario() {
        Funcionario func = new Funcionario();
        func.setIdFunc(Integer.parseInt(txtId.getText()));
        func.setNomeFunc(txtNome.getText());
        func.setSalarioFunc(Double.parseDouble(txtSalario.getText()));
        func.setHorasBaseFunc(Integer.parseInt(txtHorasBase.getText()));
        func.setSalHoraFunc(Double.parseDouble(txtSalHora.getText()));
        func.setSalDiaFunc(Double.parseDouble(txtSalDia.getText()));
        funcionarioDAO.updateFuncionario(func);
        clearFields();
        txtAreaOutput.setText("Funcionário alterado com sucesso!");
    }

    private void deleteFuncionario() {
        int idFunc = Integer.parseInt(txtId.getText());
        funcionarioDAO.deleteFuncionario(idFunc);
        clearFields();
        txtAreaOutput.setText("Funcionário deletado com sucesso!");
    }

    private void getFuncionarioById() {
        int idFunc = Integer.parseInt(txtId.getText());
        Funcionario func = funcionarioDAO.getFuncionarioById(idFunc);
        if (func != null) {
            txtNome.setText(func.getNomeFunc());
            txtSalario.setText(String.valueOf(func.getSalarioFunc()));
            txtHorasBase.setText(String.valueOf(func.getHorasBaseFunc()));
            txtSalHora.setText(String.valueOf(func.getSalHoraFunc()));
            txtSalDia.setText(String.valueOf(func.getSalDiaFunc()));
        } else {
            txtAreaOutput.setText("Funcionário não encontrado!");
        }
    }

    private void getAllFuncionarios() {
        List<Funcionario> funcionarios = funcionarioDAO.getAllFuncionarios();
        StringBuilder sb = new StringBuilder();
        for (Funcionario func : funcionarios) {
            sb.append(func.getIdFunc()).append(" - ").append(func.getNomeFunc()).append("\n");
        }
        txtAreaOutput.setText(sb.toString());
    }

    private void generatePDF() {
        // Implemente a lógica para gerar o PDF
        PDFGenerator.generate(funcionarioDAO.getAllFuncionarios());
        txtAreaOutput.setText("Arquivo PDF gerado com sucesso!");
    }

    private void clearFields() {
        txtId.setText("");
        txtNome.setText("");
        txtSalario.setText("");
        txtHorasBase.setText("");
        txtSalHora.setText("");
        txtSalDia.setText("");
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new MainFrame().setVisible(true);
            }
        });
    }
}
