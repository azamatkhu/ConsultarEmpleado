import java.sql.*;
import java.util.Scanner;

public class MainConsultarEmpleado {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        try {
            Connection connection = DriverManager.getConnection(
                    "jdbc:oracle:thin:@localhost:1521:xe",
                    "RIBERA",
                    "ribera"
            );

            System.out.println("Conectado!");

            System.out.println("Introduce el id de departamento: ");
            int id = sc.nextInt();

            PreparedStatement preparedStatement = connection.prepareStatement("SELECT NOMBRE AS nombre, SALARIO AS salario FROM EMPLEADO WHERE DEPARTAMENTO_ID = ?");
            preparedStatement.setInt(1, id);

            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                String empleadoNombre =  resultSet.getString("nombre");
                double salario = resultSet.getDouble("salario");
                System.out.println(empleadoNombre + " " + salario);
            }


        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}