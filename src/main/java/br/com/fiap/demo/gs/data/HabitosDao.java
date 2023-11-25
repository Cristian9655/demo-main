package br.com.fiap.demo.gs.data;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import br.com.fiap.demo.gs.model.Habitos;

public class HabitosDao {

    private static final String SELECT_ALL_HABITOS = "SELECT * FROM tbl_habitos_rm551289";
    private static final String SELECT_HABITOS_BY_ID = "SELECT * FROM tbl_habitos_rm551289 WHERE id_habitos = ?";
    private static final String INSERT_HABITOS = "INSERT INTO tbl_habitos_rm551289 (physical_act, daily_steps, id_user) VALUES (?, ?, ?)";
    private static final String DELETE_HABITOS_BY_ID = "DELETE FROM tbl_habitos_rm551289 WHERE id_habitos = ?";
    private static final String UPDATE_HABITOS = "UPDATE tbl_habitos_rm551289 SET physical_act = ?, daily_steps = ? WHERE id_habitos = ?";

    public List<Habitos> listarHabitos() {
        List<Habitos> lista = new ArrayList<>();
        try (Connection connection = ConnectionFactory.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(SELECT_ALL_HABITOS);
             ResultSet resultSet = preparedStatement.executeQuery()) {

            while (resultSet.next()) {
                lista.add(mapResultSetToHabitos(resultSet));
            }

        } catch (SQLException e) {
            throw new RuntimeException("Erro ao listar hábitos", e);
        }
        return lista;
    }

    public Habitos consultarHabitos(Long idHabitos) {
        try (Connection connection = ConnectionFactory.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(SELECT_HABITOS_BY_ID)) {

            preparedStatement.setLong(1, idHabitos);

            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                if (resultSet.next()) {
                    return mapResultSetToHabitos(resultSet);
                }

            }
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao consultar hábitos por ID", e);
        }
        return null;
    }

    public void cadastraHabitos(Habitos habito) {
        try (Connection connection = ConnectionFactory.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(INSERT_HABITOS)) {

            preparedStatement.setInt(1, habito.getPhysicalActivity());
            preparedStatement.setInt(2, habito.getDailySteps());
            preparedStatement.setLong(3, habito.getIdUser());

            preparedStatement.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException("Erro ao cadastrar hábitos", e);
        }
    }

    public void deletaHabitos(Long idHabitos) {
        try (Connection connection = ConnectionFactory.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(DELETE_HABITOS_BY_ID)) {

            preparedStatement.setLong(1, idHabitos);
            preparedStatement.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException("Erro ao excluir hábitos por ID", e);
        }
    }

    public void atualizaHabitos(Habitos habitoExistente) {
        try (Connection connection = ConnectionFactory.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(UPDATE_HABITOS)) {

            preparedStatement.setInt(1, habitoExistente.getPhysicalActivity());
            preparedStatement.setInt(2, habitoExistente.getDailySteps());
            preparedStatement.setLong(3, habitoExistente.getIdHabitos());

            preparedStatement.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException("Erro ao atualizar hábitos", e);
        }
    }

    private Habitos mapResultSetToHabitos(ResultSet resultSet) throws SQLException {
        return new Habitos(resultSet.getLong("id_habitos"),
                resultSet.getInt("physical_act"),
                resultSet.getInt("daily_steps"),
                resultSet.getLong("id_user"));
    }
}
