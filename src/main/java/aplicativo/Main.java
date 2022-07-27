package aplicativo;

import java.awt.EventQueue;

import aplicativo.view.ListaClienteView;

public class Main {

    public static void main(String[] args) {
        System.out.println("Executando...");
        
        /**
         * Launch the application.
         */
        EventQueue.invokeLater(() -> {
            try {
                ListaClienteView frame = new ListaClienteView();
                frame.setLocationRelativeTo(null);
                frame.setVisible(true);
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
        
        //final ClienteService service = new ClienteServiceImpl();
        /*
        int novoCliente = service.criar(new Cliente("cliente_" + System.currentTimeMillis(), null));
        
        final Cliente cliente = service.buscaPorCodigo(novoCliente);
        if (Objects.nonNull(cliente)) {
            System.out.println("Cliente identificado com sucesso: " + cliente.getNome());
        } else {
            System.out.println("Cliente nao localizado");
        }

//        final Cliente clienteEditar = new Cliente(
//                cliente.getCodigo(), 
//                cliente.getNome() + "_alterado",
//                LocalDate.now());
        final Cliente clienteEditar = new Cliente(
                3, 
                "Ola_alterado",
                LocalDate.of(2021, 2, 2));
        
        int editar = service.editar(clienteEditar);
        if (editar > 0) {
            System.out.println("Cliente alterado com sucesso");
        }
        
        int excluido = service.excluir(4);
        if (excluido > 0) {
            System.out.println("Cliente excluido com sucesso");
        }
*/
//        System.out.println("==> Lista todos");
//        service.buscaTodos().forEach(c -> System.out.println(c.getNome()));
/*        
        System.out.println("==> Busca por nome");
        service.buscaPorNome("eder").forEach(c -> System.out.println(c.getNome()));
        
        System.out.println("Fim");
*/

    }
}
