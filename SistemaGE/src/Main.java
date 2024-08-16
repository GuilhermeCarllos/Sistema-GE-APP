import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Main extends JFrame {
    private Local[] locais;
    private JTextArea textArea;
    private JScrollPane scrollPane; // Adiciona o JScrollPane
    private JTextField tfNomeEvento, tfDataEvento, tfNomeParticipante, tfEmailParticipante;
    private JComboBox<String> cbLocaisEvento, cbEventos;

    public Main() {
        locais = new Local[3]; // Atualiza o tamanho do array para 3
        locais[0] = new Local("Auditório IFCE", 120);
        locais[1] = new Local("LAB INFOR 1", 24);
        locais[2] = new Local("LAB INFOR 2", 30); // Adiciona o novo local

        // Configurações da Janela Principal
        setTitle("Sistema GE");
        setSize(800, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(null);

        // Área de Texto para Mostrar Informações
        textArea = new JTextArea();
        textArea.setEditable(false);

        // Adiciona o JScrollPane
        scrollPane = new JScrollPane(textArea);
        scrollPane.setBounds(10, 10, 760, 250);
        add(scrollPane);

        // Campos para Cadastro de Eventos
        JLabel lblNomeEvento = new JLabel("Nome do Evento:");
        lblNomeEvento.setBounds(10, 270, 150, 25);
        add(lblNomeEvento);

        tfNomeEvento = new JTextField();
        tfNomeEvento.setBounds(160, 270, 200, 25);
        add(tfNomeEvento);

        JLabel lblDataEvento = new JLabel("Data do Evento:");
        lblDataEvento.setBounds(10, 300, 150, 25);
        add(lblDataEvento);

        tfDataEvento = new JTextField();
        tfDataEvento.setBounds(160, 300, 200, 25);
        add(tfDataEvento);

        JLabel lblLocalEvento = new JLabel("Local do Evento:");
        lblLocalEvento.setBounds(10, 330, 150, 25);
        add(lblLocalEvento);

        cbLocaisEvento = new JComboBox<>(new String[]{locais[0].nome, locais[1].nome, locais[2].nome}); // Atualiza o JComboBox com o novo local
        cbLocaisEvento.setBounds(160, 330, 200, 25);
        add(cbLocaisEvento);

        JButton btnCadastrarEvento = new JButton("Cadastrar Evento");
        btnCadastrarEvento.setBounds(380, 270, 180, 30);
        add(btnCadastrarEvento);

        btnCadastrarEvento.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                cadastrarEvento();
            }
        });

        // Campos para Associar Participantes a Eventos
        JLabel lblNomeParticipante = new JLabel("Nome do Participante:");
        lblNomeParticipante.setBounds(10, 360, 150, 25);
        add(lblNomeParticipante);

        tfNomeParticipante = new JTextField();
        tfNomeParticipante.setBounds(160, 360, 200, 25);
        add(tfNomeParticipante);

        JLabel lblEmailParticipante = new JLabel("Email do Participante:");
        lblEmailParticipante.setBounds(10, 390, 150, 25);
        add(lblEmailParticipante);

        tfEmailParticipante = new JTextField();
        tfEmailParticipante.setBounds(160, 390, 200, 25);
        add(tfEmailParticipante);

        JLabel lblEventoParticipante = new JLabel("Evento:");
        lblEventoParticipante.setBounds(380, 360, 150, 25);
        add(lblEventoParticipante);

        cbEventos = new JComboBox<>();
        cbEventos.setBounds(530, 360, 200, 25);
        add(cbEventos);

        JButton btnAssociarParticipante = new JButton("Associar Participante");
        btnAssociarParticipante.setBounds(380, 390, 180, 30);
        add(btnAssociarParticipante);

        btnAssociarParticipante.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                associarParticipante();
            }
        });

        // Botão para Listar Eventos
        JButton btnListarEventos = new JButton("Listar Eventos");
        btnListarEventos.setBounds(10, 420, 150, 30);
        add(btnListarEventos);

        btnListarEventos.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                listarEventos();
            }
        });

        // Inicializar Eventos no ComboBox
        atualizarEventos();
    }

    private void cadastrarEvento() {
        String nomeEvento = tfNomeEvento.getText();
        String dataEvento = tfDataEvento.getText();
        int localIndex = cbLocaisEvento.getSelectedIndex();
        Local localEscolhido = locais[localIndex];

        Evento novoEvento = new Evento(nomeEvento, dataEvento, localEscolhido, 10);
        localEscolhido.adicionarEvento(novoEvento);
        textArea.append("Evento cadastrado: " + nomeEvento + "\n");

        tfNomeEvento.setText("");
        tfDataEvento.setText("");
        atualizarEventos();
    }

    private void associarParticipante() {
        String nomeParticipante = tfNomeParticipante.getText();
        String emailParticipante = tfEmailParticipante.getText();
        int eventoIndex = cbEventos.getSelectedIndex();
        Evento eventoEscolhido = getEventoPorIndex(eventoIndex);

        Participante novoParticipante = new Participante(nomeParticipante, emailParticipante);
        eventoEscolhido.adicionarParticipante(novoParticipante);
        textArea.append("Participante associado: " + nomeParticipante + "\n");

        tfNomeParticipante.setText("");
        tfEmailParticipante.setText("");
    }

    private Evento getEventoPorIndex(int index) {
        int totalEventos = 0;
        for (Local local : locais) {
            if (index < totalEventos + local.numeroEventos) {
                return local.eventos[index - totalEventos];
            }
            totalEventos += local.numeroEventos;
        }
        return null;
    }

    private void atualizarEventos() {
        cbEventos.removeAllItems();
        for (Local local : locais) {
            for (Evento evento : local.eventos) {
                if (evento != null) {
                    cbEventos.addItem(evento.nome + " (" + local.nome + ")");
                }
            }
        }
    }

    private void listarEventos() {
        textArea.setText("");
        for (Local local : locais) {
            textArea.append("Eventos no " + local.nome + ":\n");
            for (Evento evento : local.eventos) {
                if (evento != null) {
                    textArea.append("Evento: " + evento.nome + ", Data: " + evento.data + "\n");
                    textArea.append("Participantes:\n");
                    for (Participante participante : evento.participantes) {
                        if (participante != null) {
                            textArea.append(participante.toString() + "\n");
                        }
                    }
                }
            }
            textArea.append("\n");
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new Main().setVisible(true));
    }
}
