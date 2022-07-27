package aplicativo.servico;

import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.Objects;

public class Service {

    public Connection getConnection() throws SQLException {
        final String url = "jdbc:postgresql://localhost:5432/aplicativo";
        //final String url = "jdbc:postgresql://192.168.188.101:5432/aplicativo";
        return DriverManager.getConnection(url, "postgres", "postgres");       
    }
    
    public int max(String tabela, String coluna) throws SQLException {
        try (Connection connection = getConnection();
                PreparedStatement preparedStatement = connection.prepareStatement(
                        String.format("select max(%s) maior from %s", coluna, tabela));
                ResultSet resultSet = preparedStatement.executeQuery()
                ) {
            if (resultSet.next()) {
                return resultSet.getInt("maior");
            }
            return 0;
        }
    }
    
    public LocalDate toLocalDate(Date data) {
        if (Objects.nonNull(data)) {
            return data.toLocalDate();
        }
        return null;
    }

}
