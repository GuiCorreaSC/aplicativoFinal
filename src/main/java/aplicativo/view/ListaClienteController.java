package aplicativo.view;

import java.awt.Dialog.ModalityType;
import java.sql.SQLException;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Objects;

import javax.swing.JOptionPane;
import javax.swing.SwingUtilities;
import javax.swing.WindowConstants;
import javax.swing.table.DefaultTableModel;

import aplicativo.entidade.Cliente;
import aplicativo.servico.ClienteService;
import aplicativo.servico.ClienteServiceImpl;

public class ListaClienteController {
    
    private DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
    
    private final ListaClienteView view;
    
    protected ListaClienteController(ListaClienteView view) {
        this.view = view;
        SwingUtilities.invokeLater(this::buscaTodos);
    }

    private void buscaTodos() {
        try {
            limparTabela();
            final ClienteService service = new ClienteServiceImpl();
            List<Cliente> clientes = service.buscaTodos();
            clientes.forEach(this::adicionaNaTabela);
            getModeloTabela().fireTableDataChanged();
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    public void buscaPorNome(String text) {
        try {
            limparTabela();
            final ClienteService service = new ClienteServiceImpl();
            final List<Cliente> clientes = service.buscaPorNome(text);
            clientes.forEach(this::adicionaNaTabela);
            view.getTextField().setText("");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    
    private void limparTabela() {
        final DefaultTableModel model = getModeloTabela();
        while (model.getRowCount() > 0) {
            model.removeRow(0);
        }
        model.fireTableDataChanged();
    }

    private DefaultTableModel getModeloTabela() {
        return (DefaultTableModel) view.getTable().getModel();
    }
    
    private void adicionaNaTabela(Cliente cliente) {
        getModeloTabela().addRow(new Object[] {
                cliente.getCodigo(),
                cliente.getNome(),
                Objects.nonNull(cliente.getDataNascimento()) ? formatter.format(cliente.getDataNascimento()) : ""        
        });
    }

    public Integer abreCadastroCliente(Integer codigo) {
        final CadastroClienteView dialog = new CadastroClienteView(view, codigo);
        dialog.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        dialog.setModalityType(ModalityType.DOCUMENT_MODAL);
        dialog.setLocationRelativeTo(null);
        dialog.setVisible(true);
        return dialog.getController().getCodigoCliente();
    }
    
    public void abreNovoCadastroCliente() {
        Integer codigoCliente = abreCadastroCliente(null);
        buscaTodos();
        if (Objects.nonNull(codigoCliente)) {
            selecionaClienteNaTabela(codigoCliente);
        }
    }

    public void editarCliente() {
        final int selectedRow = view.getTable().getSelectedRow();
        if (selectedRow < 0) {
            JOptionPane.showMessageDialog(view, 
                    "Selecione um cliente na lista acima", "Atenção", 
                    JOptionPane.WARNING_MESSAGE);
        } else {
            final Integer codigoCliente = 
                    (Integer) getModeloTabela().getValueAt(selectedRow, 0);
            abreCadastroCliente(codigoCliente);
            buscaTodos();
            selecionaClienteNaTabela(codigoCliente);
        }
    }

    private void selecionaClienteNaTabela(final Integer codigoCliente) {
        for (int i = 0; i < getModeloTabela().getRowCount(); i++) {
            final Integer cod = 
                    (Integer) getModeloTabela().getValueAt(i, 0);
            if (cod.equals(codigoCliente)) {
                view.getTable().setRowSelectionInterval(i, i);
                view.getTable().requestFocusInWindow();
                break;
            }
        }
    }

    public void excluirCliente() {
        final int selectedRow = view.getTable().getSelectedRow();
        if (selectedRow < 0) {
            JOptionPane.showMessageDialog(view, 
                    "Selecione um cliente na lista acima para excluir", "Atenção", 
                    JOptionPane.WARNING_MESSAGE);
        } else {
            final Integer codigoCliente = 
                    (Integer) getModeloTabela().getValueAt(selectedRow, 0);
            try {
                final int confirm = JOptionPane.showConfirmDialog(view, 
                        "Deseja realmente excluir o cliente selecionado?", 
                        "Confirmação", JOptionPane.YES_NO_OPTION);
                if (confirm == JOptionPane.YES_OPTION) {
                    final ClienteService service = new ClienteServiceImpl();
                    service.excluir(codigoCliente);
                }
            } catch (SQLException e) {
                JOptionPane.showMessageDialog(view,
                        "Erro ao excluir o cliente: " + e.getMessage(),
                        "Erro", JOptionPane.ERROR_MESSAGE);                
            }
        }
    }
    
}
