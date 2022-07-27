package aplicativo.exeptions;

public class NomeInvalidoException extends RuntimeException {
    private static final long serialVersionUID = 1L;

    public NomeInvalidoException() {
        super("Nome inválido");
    }
    
}
