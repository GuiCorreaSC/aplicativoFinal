package aplicativo.servico;

import java.sql.SQLException;
import java.util.List;

import aplicativo.entidade.Cliente;

public interface ClienteService {
    Cliente buscaPorCodigo(Integer codigo) throws SQLException;
    List<Cliente> buscaPorNome(String nome) throws SQLException;
    List<Cliente> buscaTodos() throws SQLException; 
    int criar(Cliente cliente) throws SQLException;
    int editar(Cliente cliente) throws SQLException;
    int excluir(Integer codigo) throws SQLException;
}
