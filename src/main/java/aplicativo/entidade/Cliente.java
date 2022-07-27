package aplicativo.entidade;

import java.time.LocalDate;

public class Cliente {
    private Integer codigo;
    private String nome;
    private LocalDate dataNascimento;

    public Cliente(Integer codigo, String nome, LocalDate dataNascimento) {
        this.codigo = codigo;
        this.nome = nome;
        this.dataNascimento = dataNascimento;
    }

    public Cliente(String nome, LocalDate dataNascimento) {
        this(null, nome, dataNascimento);
    }
   
    public Integer getCodigo() {
        return codigo;
    }
    
    public String getNome() {
        return nome;
    }
    
    public LocalDate getDataNascimento() {
        return dataNascimento;
    }
    
}
