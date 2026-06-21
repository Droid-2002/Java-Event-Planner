package ui;

import java.awt.BorderLayout;
import java.awt.Dialog;
import java.awt.FlowLayout;
import java.awt.Frame;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;

import service.AppState;

// Modal de login (RF01 / DT05). Aparece na inicializacao, ANTES da tela
// principal, e so libera o acesso depois que o usuario escolhe quem ele e
// num JComboBox e confirma. Tudo na EDT, sem thread nenhuma.
public class LoginDialog extends JDialog {

    private final AppState state;
    private final JComboBox<String> userCombo;

    public LoginDialog(AppState state) {
        // dialog sem janela-pai (a principal ainda nao existe) e modal,
        // entao setVisible(true) bloqueia ate confirmar.
        super((Frame) null, "Java Event Planner - Login", true);
        this.state = state;

        List<String> users = state.getUsers();
        userCombo = new JComboBox<>(users.toArray(new String[0]));
        if (!users.isEmpty()) {
            userCombo.setSelectedIndex(0);
        }

        JLabel label = new JLabel("Selecione o Usuario Ativo:");

        JButton enterButton = new JButton("Entrar");
        enterButton.addActionListener(e -> confirm());

        JPanel center = new JPanel(new FlowLayout(FlowLayout.LEFT, 8, 8));
        center.add(label);
        center.add(userCombo);

        JPanel south = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        south.add(enterButton);

        JPanel content = new JPanel(new BorderLayout());
        content.setBorder(BorderFactory.createEmptyBorder(12, 12, 12, 12));
        content.add(center, BorderLayout.CENTER);
        content.add(south, BorderLayout.SOUTH);
        setContentPane(content);

        // o Enter no teclado tambem confirma
        getRootPane().setDefaultButton(enterButton);

        // fechar no X sem escolher = nao ha acesso liberado (RF01): encerra o app.
        setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                System.exit(0);
            }
        });

        setModalityType(Dialog.ModalityType.APPLICATION_MODAL);
        pack();
        setLocationRelativeTo(null);
    }

    // grava o usuario escolhido no estado global e fecha o modal.
    // o combo sempre tem um item selecionado, entao a selecao e garantida.
    private void confirm() {
        String selected = (String) userCombo.getSelectedItem();
        state.setActiveUser(selected);
        dispose();
    }
}
