package aplicativo.view;

import java.sql.SQLException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Objects;

import javax.swing.JOptionPane;

import aplicativo.entidade.Cliente;
import aplicativo.exeptions.DataNascimentoInvalidaException;
import aplicativo.servico.ClienteService;
import aplicativo.servico.ClienteServiceImpl;

public class CadastroClienteController {

    private DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");

    private final CadastroClienteView view;
    private Integer codigoCliente;

    public CadastroClienteController(CadastroClienteView view, Integer codigo) {
        this.view = view;
        if (Objects.nonNull(codigo)) {
            buscaClientePorCodigo(codigo);
        }
    }

    private void buscaClientePorCodigo(Integer codigo) {
        final ClienteService service = new ClienteServiceImpl();
        try {
            final Cliente cliente = service.buscaPorCodigo(codigo);
            if (Objects.nonNull(cliente)) {
                view.getFieldCodigo().setText(cliente.getCodigo().toString());
                view.getFieldNome().setText(cliente.getNome());
                view.getFieldDataNascimento().setText(
                        Objects.nonNull(cliente.getDataNascimento()) 
                            ? formatter.format(cliente.getDataNascimento()) : "");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void limparTela() {
        view.getFieldCodigo().setText("");
        view.getFieldNome().setText("");
        view.getFieldDataNascimento().setText("");

        view.getFieldNome().requestFocusInWindow();
    }

    public void salvarCliente() {
        final ClienteService service = new ClienteServiceImpl();
        try {
            
            final LocalDate dataNascimento = getDataNascimento();

            final String sCodigoCliente = view.getFieldCodigo().getText();
            if (sCodigoCliente.trim().isEmpty()) {
                final Cliente cliente = new Cliente(
                        view.getFieldNome().getText(), 
                        dataNascimento);
                codigoCliente = service.criar(cliente);
            } else {
                final Cliente cliente = new Cliente(
                        Integer.valueOf(sCodigoCliente),
                        view.getFieldNome().getText(), 
                        dataNascimento);
                    service.editar(cliente);
                codigoCliente = Integer.valueOf(sCodigoCliente);
            }
                
            view.setVisible(false);
                
        } catch (Exception e) {
            JOptionPane.showMessageDialog(view,
                    "Erro ao cadastrar o cliente: " + e.getMessage(),
                    "Erro", JOptionPane.ERROR_MESSAGE);
        }
    }

    private LocalDate getDataNascimento() throws DataNascimentoInvalidaException {
        try {
            final String sDataNascimento = view.getFieldDataNascimento().getText();
            return sDataNascimento.trim().isEmpty() ? null
                    : LocalDate.parse(view.getFieldDataNascimento().getText(), formatter);
        } catch (Exception e) {
            throw new DataNascimentoInvalidaException(e);
        }
    }
    
    public Integer getCodigoCliente() {
        return codigoCliente;
    }
}
