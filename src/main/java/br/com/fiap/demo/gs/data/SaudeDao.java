package br.com.fiap.demo.gs.data;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import br.com.fiap.demo.gs.model.Saude;

public class SaudeDao {

    private static final String SELECT_ALL_SAUDE = "SELECT * FROM tbl_saude_rm551289";
    private static final String SELECT_SAUDE_BY_ID = "SELECT * FROM tbl_saude_rm551289 WHERE id_saude = ?";
    private static final String INSERT_SAUDE = "INSERT INTO tbl_saude_rm551289 (stress, bmi, blood_pressure, heart_rate, id_user) VALUES (?, ?, ?, ?, ?)";
    private static final String DELETE_SAUDE_BY_ID = "DELETE FROM tbl_saude_rm551289 WHERE id_saude = ?";
    private static final String UPDATE_SAUDE = "UPDATE tbl_saude_rm551289 SET stress = ?, bmi = ?, blood_pressure = ?, heart_rate = ? WHERE id_saude = ?";

    public List<Saude> listarSaude() {
        List<Saude> lista = new ArrayList<>();
        try (Connection connection = ConnectionFactory.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(SELECT_ALL_SAUDE);
             ResultSet resultSet = preparedStatement.executeQuery()) {

            while (resultSet.next()) {
                lista.add(mapResultSetToSaude(resultSet));
            }

        } catch (SQLException e) {
            throw new RuntimeException("Erro ao listar dados de saúde", e);
        }
        return lista;
    }

    public Saude consultarSaude(Long idSaude) {
        try (Connection connection = ConnectionFactory.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(SELECT_SAUDE_BY_ID)) {

            preparedStatement.setLong(1, idSaude);

            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                if (resultSet.next()) {
                    return mapResultSetToSaude(resultSet);
                }

            }
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao consultar dados de saúde por ID", e);
        }
        return null;
    }

    public void cadastraSaude(Saude saude) {
        try (Connection connection = ConnectionFactory.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(INSERT_SAUDE)) {

            preparedStatement.setInt(1, saude.getStress());
            preparedStatement.setString(2, saude.getBmi());
            preparedStatement.setString(3, saude.getBloodPressure());
            preparedStatement.setInt(4, saude.getHeartRate());
            preparedStatement.setLong(5, saude.getIdUser());

            preparedStatement.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException("Erro ao cadastrar dados de saúde", e);
        }
    }

    public void deletaSaude(Long idSaude) {
        try (Connection connection = ConnectionFactory.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(DELETE_SAUDE_BY_ID)) {

            preparedStatement.setLong(1, idSaude);
            preparedStatement.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException("Erro ao excluir dados de saúde por ID", e);
        }
    }

    public void atualizaSaude(Saude saudeExistente) {
        try (Connection connection = ConnectionFactory.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(UPDATE_SAUDE)) {

            preparedStatement.setInt(1, saudeExistente.getStress());
            preparedStatement.setString(2, saudeExistente.getBmi());
            preparedStatement.setString(3, saudeExistente.getBloodPressure());
            preparedStatement.setInt(4, saudeExistente.getHeartRate());
            preparedStatement.setLong(5, saudeExistente.getIdSaude());

            preparedStatement.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException("Erro ao atualizar dados de saúde", e);
        }
    }

    private Saude mapResultSetToSaude(ResultSet resultSet) throws SQLException {
        return new Saude(resultSet.getLong("id_saude"),
                resultSet.getInt("stress"),
                resultSet.getString("bmi"),
                resultSet.getString("blood_pressure"),
                resultSet.getInt("heart_rate"),
                resultSet.getLong("id_user"));
    }
}
