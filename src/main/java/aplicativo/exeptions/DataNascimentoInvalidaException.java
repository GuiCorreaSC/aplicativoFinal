package aplicativo.exeptions;

public class DataNascimentoInvalidaException extends Exception {
    private static final long serialVersionUID = 1L;

    public DataNascimentoInvalidaException(Throwable cause) {
        super("Data de nascimento inválida", cause);
    }
    
}
