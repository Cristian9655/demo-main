package br.com.fiap.demo.gs.data;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import br.com.fiap.demo.gs.model.User;

public class UserDao {

    private static final String SELECT_ALL_USERS = "SELECT * FROM tbl_user_rm551289";
    private static final String SELECT_USER_BY_ID = "SELECT * FROM tbl_user_rm551289 WHERE id_user = ?";
    private static final String INSERT_USER = "INSERT INTO tbl_user_rm551289 (nome, senha, email, gender, age, occupation) VALUES (?, ?, ?, ?, ?, ?)";
    private static final String DELETE_USER_BY_ID = "DELETE FROM tbl_user_rm551289 WHERE id_user = ?";
    private static final String UPDATE_USER = "UPDATE tbl_user_rm551289 SET nome = ?, senha = ?, email = ?, gender = ?, age = ?, occupation = ? WHERE id_user = ?";

    public List<User> listarUsers() {
        List<User> lista = new ArrayList<>();
        try (Connection connection = ConnectionFactory.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(SELECT_ALL_USERS);
             ResultSet resultSet = preparedStatement.executeQuery()) {

            while (resultSet.next()) {
                lista.add(mapResultSetToUser(resultSet));
            }

        } catch (SQLException e) {
            throw new RuntimeException("Erro ao listar usuários", e);
        }
        return lista;
    }

    public User consultarUser(Long idUser) {
        try (Connection connection = ConnectionFactory.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(SELECT_USER_BY_ID)) {

            preparedStatement.setLong(1, idUser);

            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                if (resultSet.next()) {
                    return mapResultSetToUser(resultSet);
                }

            }
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao consultar usuário por ID", e);
        }
        return null;
    }

    public void cadastraUser(User user) {
        try (Connection connection = ConnectionFactory.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(INSERT_USER)) {

            preparedStatement.setString(1, user.getNome());
            preparedStatement.setString(2, user.getSenha());
            preparedStatement.setString(3, user.getEmail());
            preparedStatement.setString(4, user.getGender());
            preparedStatement.setInt(5, user.getAge());
            preparedStatement.setString(6, user.getOccupation());

            preparedStatement.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException("Erro ao cadastrar usuário", e);
        }
    }

    public void deletaUser(Long idUser) {
        try (Connection connection = ConnectionFactory.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(DELETE_USER_BY_ID)) {

            preparedStatement.setLong(1, idUser);
            preparedStatement.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException("Erro ao excluir usuário por ID", e);
        }
    }

    public void atualizaUser(User userExistente) {
        try (Connection connection = ConnectionFactory.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(UPDATE_USER)) {

            preparedStatement.setString(1, userExistente.getNome());
            preparedStatement.setString(2, userExistente.getSenha());
            preparedStatement.setString(3, userExistente.getEmail());
            preparedStatement.setString(4, userExistente.getGender());
            preparedStatement.setInt(5, userExistente.getAge());
            preparedStatement.setString(6, userExistente.getOccupation());
            preparedStatement.setLong(7, userExistente.getIdUser());

            preparedStatement.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException("Erro ao atualizar usuário", e);
        }
    }

    private User mapResultSetToUser(ResultSet resultSet) throws SQLException {
        return new User(
                resultSet.getLong("id_user"),
                resultSet.getString("nome"),
                resultSet.getString("senha"),
                resultSet.getString("email"),
                resultSet.getString("gender"),
                resultSet.getInt("age"),
                resultSet.getString("occupation")
        );
    }
}