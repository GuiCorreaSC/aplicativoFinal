package aplicativo.view;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.Window;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

public class CadastroClienteView extends JDialog {
    private static final long serialVersionUID = 1L;

    private CadastroClienteController controller;

    private JPanel pnlBotoes;
    private JPanel pnlCampos;
    private JButton btnSair;
    private JButton btnLimpar;
    private JButton btnSalvar;
    private JLabel lblNewLabel;
    private JLabel lblNewLabel_1;
    private JLabel lblNewLabel_2;
    private JTextField fieldCodigo;
    private JTextField fieldNome;
    private InputDate fieldDataNascimento;

    /**
     * Create the dialog.
     */
    public CadastroClienteView(Window owner, Integer codigo) {
        super(owner);
        setBounds(100, 100, 490, 175);
        getContentPane().add(getPnlBotoes(), BorderLayout.SOUTH);
        getContentPane().add(getPnlCampos(), BorderLayout.CENTER);
        this.controller = new CadastroClienteController(this, codigo);
    }

    private JPanel getPnlBotoes() {
        if (pnlBotoes == null) {
            pnlBotoes = new JPanel();
            FlowLayout flowLayout = (FlowLayout) pnlBotoes.getLayout();
            flowLayout.setAlignment(FlowLayout.RIGHT);
            pnlBotoes.add(getBtnSalvar());
            pnlBotoes.add(getBtnLimpar());
            pnlBotoes.add(getBtnSair());
        }
        return pnlBotoes;
    }

    private JPanel getPnlCampos() {
        if (pnlCampos == null) {
            pnlCampos = new JPanel();
            pnlCampos.setBorder(new EmptyBorder(5, 5, 0, 5));
            GridBagLayout gbl_pnlCampos = new GridBagLayout();
            gbl_pnlCampos.columnWidths = new int[] {0, 0, 0, 0, 0};
            gbl_pnlCampos.rowHeights = new int[] {0, 0, 0, 0};
            gbl_pnlCampos.columnWeights = new double[] {0.0, 0.0, 0.0, 1.0, Double.MIN_VALUE};
            gbl_pnlCampos.rowWeights = new double[] {0.0, 0.0, 0.0, Double.MIN_VALUE};
            pnlCampos.setLayout(gbl_pnlCampos);
            GridBagConstraints gbc_lblNewLabel = new GridBagConstraints();
            gbc_lblNewLabel.anchor = GridBagConstraints.EAST;
            gbc_lblNewLabel.insets = new Insets(0, 0, 5, 5);
            gbc_lblNewLabel.gridx = 0;
            gbc_lblNewLabel.gridy = 0;
            pnlCampos.add(getLblNewLabel(), gbc_lblNewLabel);
            GridBagConstraints gbc_fieldCodigo = new GridBagConstraints();
            gbc_fieldCodigo.insets = new Insets(0, 0, 5, 5);
            gbc_fieldCodigo.fill = GridBagConstraints.HORIZONTAL;
            gbc_fieldCodigo.gridx = 1;
            gbc_fieldCodigo.gridy = 0;
            pnlCampos.add(getFieldCodigo(), gbc_fieldCodigo);
            GridBagConstraints gbc_lblNewLabel_1 = new GridBagConstraints();
            gbc_lblNewLabel_1.anchor = GridBagConstraints.EAST;
            gbc_lblNewLabel_1.insets = new Insets(0, 0, 5, 5);
            gbc_lblNewLabel_1.gridx = 0;
            gbc_lblNewLabel_1.gridy = 1;
            pnlCampos.add(getLblNewLabel_1(), gbc_lblNewLabel_1);
            GridBagConstraints gbc_fieldNome = new GridBagConstraints();
            gbc_fieldNome.gridwidth = 3;
            gbc_fieldNome.insets = new Insets(0, 0, 5, 5);
            gbc_fieldNome.fill = GridBagConstraints.HORIZONTAL;
            gbc_fieldNome.gridx = 1;
            gbc_fieldNome.gridy = 1;
            pnlCampos.add(getFieldNome(), gbc_fieldNome);
            GridBagConstraints gbc_lblNewLabel_2 = new GridBagConstraints();
            gbc_lblNewLabel_2.anchor = GridBagConstraints.EAST;
            gbc_lblNewLabel_2.insets = new Insets(0, 0, 0, 5);
            gbc_lblNewLabel_2.gridx = 0;
            gbc_lblNewLabel_2.gridy = 2;
            pnlCampos.add(getLblNewLabel_2(), gbc_lblNewLabel_2);
            GridBagConstraints gbc_fieldDataNascimento = new GridBagConstraints();
            gbc_fieldDataNascimento.gridwidth = 2;
            gbc_fieldDataNascimento.insets = new Insets(0, 0, 0, 5);
            gbc_fieldDataNascimento.fill = GridBagConstraints.HORIZONTAL;
            gbc_fieldDataNascimento.gridx = 1;
            gbc_fieldDataNascimento.gridy = 2;
            pnlCampos.add(getFieldDataNascimento(), gbc_fieldDataNascimento);
        }
        return pnlCampos;
    }

    private JButton getBtnSair() {
        if (btnSair == null) {
            btnSair = new JButton("Sair");
            btnSair.addActionListener(e -> this.setVisible(false));
        }
        return btnSair;
    }

    private JButton getBtnLimpar() {
        if (btnLimpar == null) {
            btnLimpar = new JButton("Limpar");
            btnLimpar.addActionListener(e -> controller.limparTela());
        }
        return btnLimpar;
    }

    private JButton getBtnSalvar() {
        if (btnSalvar == null) {
            btnSalvar = new JButton("Salvar");
            btnSalvar.addActionListener(e -> controller.salvarCliente());
        }
        return btnSalvar;
    }

    private JLabel getLblNewLabel() {
        if (lblNewLabel == null) {
            lblNewLabel = new JLabel("Código:");
        }
        return lblNewLabel;
    }

    private JLabel getLblNewLabel_1() {
        if (lblNewLabel_1 == null) {
            lblNewLabel_1 = new JLabel("Nome:");
        }
        return lblNewLabel_1;
    }

    private JLabel getLblNewLabel_2() {
        if (lblNewLabel_2 == null) {
            lblNewLabel_2 = new JLabel("Data nascto:");
        }
        return lblNewLabel_2;
    }

    protected JTextField getFieldCodigo() {
        if (fieldCodigo == null) {
            fieldCodigo = new JTextField();
            fieldCodigo.setEnabled(false);
            fieldCodigo.setMinimumSize(new Dimension(50, 20));
            fieldCodigo.setColumns(10);
        }
        return fieldCodigo;
    }

    protected JTextField getFieldNome() {
        if (fieldNome == null) {
            fieldNome = new JTextField();
            fieldNome.setColumns(10);
        }
        return fieldNome;
    }

    protected JTextField getFieldDataNascimento() {
        if (fieldDataNascimento == null) {
            fieldDataNascimento = new InputDate();
            fieldDataNascimento.setMinimumSize(new Dimension(70, 20));
            fieldDataNascimento.setColumns(10);
        }
        return fieldDataNascimento;
    }
    
    public CadastroClienteController getController() {
        return controller;
    }
}
