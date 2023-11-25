package br.com.fiap.demo.gs.data;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import br.com.fiap.demo.gs.model.Sono;

public class SonoDao {

    private static final String SELECT_ALL_SONOS = "SELECT * FROM tbl_sono_rm551289";
    private static final String SELECT_SONO_BY_ID = "SELECT * FROM tbl_sono_rm551289 WHERE id_sono = ?";
    private static final String INSERT_SONO = "INSERT INTO tbl_sono_rm551289 (sleep_duration, sleep_quality, id_user) VALUES (?, ?, ?)";
    private static final String DELETE_SONO_BY_ID = "DELETE FROM tbl_sono_rm551289 WHERE id_sono = ?";
    private static final String UPDATE_SONO = "UPDATE tbl_sono_rm551289 SET sleep_duration = ?, sleep_quality = ? WHERE id_sono = ?";

    public List<Sono> listarSonos() {
        List<Sono> lista = new ArrayList<>();
        try (Connection connection = ConnectionFactory.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(SELECT_ALL_SONOS);
             ResultSet resultSet = preparedStatement.executeQuery()) {

            while (resultSet.next()) {
                lista.add(mapResultSetToSono(resultSet));
            }

        } catch (SQLException e) {
            throw new RuntimeException("Erro ao listar sonos", e);
        }
        return lista;
    }

    public Sono consultarSono(Long idSono) {
        try (Connection connection = ConnectionFactory.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(SELECT_SONO_BY_ID)) {

            preparedStatement.setLong(1, idSono);

            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                if (resultSet.next()) {
                    return mapResultSetToSono(resultSet);
                }

            }
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao consultar sono por ID", e);
        }
        return null;
    }

    public void cadastraSono(Sono sono) {
        try (Connection connection = ConnectionFactory.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(INSERT_SONO)) {

            preparedStatement.setDouble(1, sono.getSleepDuration());
            preparedStatement.setInt(2, sono.getSleepQuality());
            preparedStatement.setLong(3, sono.getIdUser());

            preparedStatement.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException("Erro ao cadastrar sono", e);
        }
    }

    public void deletaSono(Long idSono) {
        try (Connection connection = ConnectionFactory.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(DELETE_SONO_BY_ID)) {

            preparedStatement.setLong(1, idSono);
            preparedStatement.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException("Erro ao excluir sono por ID", e);
        }
    }

    public void atualizaSono(Sono sonoExistente) {
        try (Connection connection = ConnectionFactory.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(UPDATE_SONO)) {

            preparedStatement.setDouble(1, sonoExistente.getSleepDuration());
            preparedStatement.setInt(2, sonoExistente.getSleepQuality());
            preparedStatement.setLong(3, sonoExistente.getIdSono());

            preparedStatement.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException("Erro ao atualizar sono", e);
        }
    }

    private Sono mapResultSetToSono(ResultSet resultSet) throws SQLException {
        return new Sono(resultSet.getLong("id_sono"),
                resultSet.getDouble("sleep_duration"),
                resultSet.getInt("sleep_quality"),
                resultSet.getLong("id_user"));
    }
}
