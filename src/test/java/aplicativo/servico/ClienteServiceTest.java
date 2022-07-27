package aplicativo.servico;

import static org.junit.Assert.assertNotNull;

import java.sql.SQLException;

import org.junit.Test;

import aplicativo.entidade.Cliente;

public class ClienteServiceTest {
    
    @Test
    public void testCriar() throws SQLException {
        final ClienteService service = new ClienteServiceImpl();
        int novoCliente = service.criar(new Cliente("cliente_" + System.currentTimeMillis(), null));
        assertNotNull(novoCliente);
    }

}
