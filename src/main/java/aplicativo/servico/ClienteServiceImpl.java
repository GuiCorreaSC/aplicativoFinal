package aplicativo.servico;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import aplicativo.entidade.Cliente;
import aplicativo.exeptions.NomeInvalidoException;

public class ClienteServiceImpl extends Service implements ClienteService {

    private Cliente rsToCliente(ResultSet result) throws SQLException {
        return new Cliente(
                result.getInt("codigo"),
                result.getString("nome"),
                toLocalDate(result.getDate("data_nascimento")));
    }
    
    private void setLocalDateParameter(PreparedStatement preparedStatement, int parameterIndex, LocalDate date)
            throws SQLException {
        if (Objects.nonNull(date)) {
            preparedStatement.setDate(parameterIndex, Date.valueOf(date));
        } else {
            preparedStatement.setNull(parameterIndex, java.sql.Types.NULL);
        }
    }

    @Override
    public int criar(Cliente cliente) throws SQLException {
        
        if (cliente.getNome().trim().isEmpty()) {
            throw new NomeInvalidoException();
        }
        
        try (Connection connection = getConnection();
                PreparedStatement preparedStatement = connection.prepareStatement(
                        "insert into clientes (codigo, nome, data_nascimento) values (?, ?, ?)")
                ) {
            
            Integer novoCodigo = cliente.getCodigo();
            if (Objects.isNull(novoCodigo)) {
                novoCodigo = max("clientes", "codigo") + 1;
            }

            preparedStatement.setInt(1, novoCodigo);
            preparedStatement.setString(2, cliente.getNome());
            setLocalDateParameter(preparedStatement, 3, cliente.getDataNascimento());
            
            preparedStatement.executeUpdate();
            
            return novoCodigo;
        }
    }

    @Override
    public Cliente buscaPorCodigo(Integer codigo) throws SQLException {
        try (Connection connection = getConnection();
                PreparedStatement preparedStatement = connection.prepareStatement(
                        "select * from clientes where codigo = ?")
                ) {
            preparedStatement.setInt(1, codigo);
            try (ResultSet result = preparedStatement.executeQuery()) {
                if (result.next()) {
                    return rsToCliente(result);
                }
                return null;
            }
        }
    }

    @Override
    public List<Cliente> buscaTodos() throws SQLException {
        final List<Cliente> clientes = new ArrayList<>();
        try (Connection connection = getConnection();
                PreparedStatement preparedStatement = connection.prepareStatement(
                        "select * from clientes order by nome");
                ResultSet result = preparedStatement.executeQuery()
                ) {
                while (result.next()) {
                    clientes.add(rsToCliente(result));
                }
        }
        return clientes;
    }
    
    @Override
    public List<Cliente> buscaPorNome(String nome) throws SQLException {
        final List<Cliente> clientes = new ArrayList<>();
        try (Connection connection = getConnection();
                PreparedStatement preparedStatement = connection.prepareStatement(
                        "select * from clientes where nome ilike ? order by nome");
                ) {
            preparedStatement.setString(1, "%" + nome + "%");
            try (ResultSet result = preparedStatement.executeQuery()) {
                while (result.next()) {
                    clientes.add(rsToCliente(result));
                }
            }
        }
        return clientes;
    }

    @Override
    public int editar(Cliente cliente) throws SQLException {
        try (Connection connection = getConnection();
                PreparedStatement preparedStatement = connection.prepareStatement(
                        "update clientes set nome = ?, data_nascimento = ? where codigo = ?")
                ) {
            
            preparedStatement.setString(1, cliente.getNome());
            setLocalDateParameter(preparedStatement, 2, cliente.getDataNascimento());
            preparedStatement.setInt(3, cliente.getCodigo());
            
            return preparedStatement.executeUpdate();
        }
    }

    @Override
    public int excluir(Integer codigo) throws SQLException {
        try (Connection connection = getConnection();
                PreparedStatement preparedStatement = connection.prepareStatement(
                        "delete from clientes where codigo = ?")
                ) {
            preparedStatement.setInt(1, codigo);
            return preparedStatement.executeUpdate();
        }
    }
}
