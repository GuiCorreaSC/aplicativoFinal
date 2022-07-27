package aplicativo.view;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumnModel;

public class ListaClienteView extends JFrame {
    private static final long serialVersionUID = 1L;

    private ListaClienteController controller;

    private JPanel contentPane;
    private JPanel panel;
    private JButton btnSair;
    private JButton btnNovo;
    private JScrollPane scrollPane;
    private JTable table;
    private JTextField textField;
    private JButton btnEditar;
    private JButton btnExcluir;

    /**
     * Create the frame.
     */
    public ListaClienteView() {
        setSize(new Dimension(650, 450));
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        contentPane.setLayout(new BorderLayout(0, 0));
        setContentPane(contentPane);
        contentPane.add(getPanel(), BorderLayout.SOUTH);
        contentPane.add(getScrollPane(), BorderLayout.CENTER);
        controller = new ListaClienteController(this);
    }

    private JPanel getPanel() {
        if (panel == null) {
        	panel = new JPanel();
        	FlowLayout flowLayout = (FlowLayout) panel.getLayout();
        	flowLayout.setAlignment(FlowLayout.RIGHT);
        	panel.add(getTextField());
        	panel.add(getBtnNovo());
        	panel.add(getBtnEditar());
        	panel.add(getBtnExcluir());
        	panel.add(getBtnSair());
        }
        return panel;
    }
    
    private JButton getBtnSair() {
        if (btnSair == null) {
        	btnSair = new JButton("Sair");
        	btnSair.addActionListener((ActionEvent e) -> dispose());
        }
        return btnSair;
    }
    
    private JButton getBtnNovo() {
        if (btnNovo == null) {
        	btnNovo = new JButton("Novo");
        	btnNovo.addActionListener((ActionEvent e) -> {
        	    controller.abreNovoCadastroCliente();
        	});
        }
        return btnNovo;
    }
    
    private JScrollPane getScrollPane() {
        if (scrollPane == null) {
        	scrollPane = new JScrollPane();
        	scrollPane.setViewportView(getTable());
        }
        return scrollPane;
    }
    
    protected JTable getTable() {
        if (table == null) {
        	table = new JTable();
        	table.setModel(new DefaultTableModel(
        	        new Object[0][], 
        	        new String[] {"Código", "Nome", "Data nascto"})
        	);

            final DefaultTableCellRenderer rendererCentro = new DefaultTableCellRenderer();
            rendererCentro.setHorizontalAlignment(0);
            final DefaultTableCellRenderer rendererDireita = new DefaultTableCellRenderer();
            rendererDireita.setHorizontalAlignment(4);
            final DefaultTableCellRenderer rendererEsquerda = new DefaultTableCellRenderer();
            rendererEsquerda.setHorizontalAlignment(2);
 
            final TableColumnModel modeloDaColuna = table.getColumnModel();
            modeloDaColuna.getColumn(0).setCellRenderer(rendererDireita);
            modeloDaColuna.getColumn(1).setCellRenderer(rendererEsquerda);
            modeloDaColuna.getColumn(2).setCellRenderer(rendererCentro);
       
            modeloDaColuna.getColumn(0).setMaxWidth(100);
            modeloDaColuna.getColumn(0).setMinWidth(75);
            modeloDaColuna.getColumn(1).setMinWidth(200);
            modeloDaColuna.getColumn(2).setMaxWidth(150);
            modeloDaColuna.getColumn(2).setMinWidth(100);
        
        }
        return table;
    }
    
    protected JTextField getTextField() {
        if (textField == null) {
        	textField = new JTextField();
        	textField.addKeyListener(new KeyAdapter() {
        	    @Override
        	    public void keyTyped(KeyEvent e) {
        	        if (e.getKeyChar() == '\n') {
        	            controller.buscaPorNome(getTextField().getText());
        	        }
        	    }
        	});
        	textField.setColumns(10);
        }
        return textField;
    }
    private JButton getBtnEditar() {
        if (btnEditar == null) {
        	btnEditar = new JButton("Editar");
        	btnEditar.addActionListener(e -> controller.editarCliente());
        }
        return btnEditar;
    }
    private JButton getBtnExcluir() {
        if (btnExcluir == null) {
        	btnExcluir = new JButton("Excluir");
        	btnExcluir.addActionListener(e -> controller.excluirCliente());
        }
        return btnExcluir;
    }
}
