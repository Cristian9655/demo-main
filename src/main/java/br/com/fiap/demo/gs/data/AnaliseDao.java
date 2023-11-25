package br.com.fiap.demo.gs.data;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import br.com.fiap.demo.gs.model.Analise;

public class AnaliseDao {

    private static final String SELECT_ALL_ANALISES = "SELECT * FROM tbl_analise_rm551289";
    private static final String SELECT_ANALISE_BY_ID = "SELECT * FROM tbl_analise_rm551289 WHERE id_analise = ?";
    private static final String INSERT_ANALISE = "INSERT INTO tbl_analise_rm551289 (sleep_disorder, diagnostic, id_user) VALUES (?, ?, ?)";
    private static final String DELETE_ANALISE_BY_ID = "DELETE FROM tbl_analise_rm551289 WHERE id_analise = ?";
    private static final String UPDATE_ANALISE = "UPDATE tbl_analise_rm551289 SET sleep_disorder = ?, diagnostic = ?, id_user = ? WHERE id_analise = ?";

    public List<Analise> listarAnalises() {
        List<Analise> lista = new ArrayList<>();
        try (Connection connection = ConnectionFactory.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(SELECT_ALL_ANALISES);
             ResultSet resultSet = preparedStatement.executeQuery()) {

            while (resultSet.next()) {
                lista.add(mapResultSetToAnalise(resultSet));
            }

        } catch (SQLException e) {
            throw new RuntimeException("Erro ao listar análises", e);
        }
        return lista;
    }

    public Analise consultarAnalise(Long idAnalise) {
        try (Connection connection = ConnectionFactory.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(SELECT_ANALISE_BY_ID)) {

            preparedStatement.setLong(1, idAnalise);

            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                if (resultSet.next()) {
                    return mapResultSetToAnalise(resultSet);
                }

            }
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao consultar análise por ID", e);
        }
        return null;
    }

    public void cadastraAnalise(Analise analise) {
        try (Connection connection = ConnectionFactory.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(INSERT_ANALISE)) {

            preparedStatement.setInt(1, analise.getSleepDisorder());
            preparedStatement.setString(2, analise.getDiagnostic());
            preparedStatement.setLong(3, analise.getIdUser());

            preparedStatement.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException("Erro ao cadastrar análise", e);
        }
    }

    public void deletaAnalise(Long idAnalise) {
        try (Connection connection = ConnectionFactory.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(DELETE_ANALISE_BY_ID)) {

            preparedStatement.setLong(1, idAnalise);
            preparedStatement.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException("Erro ao excluir análise por ID", e);
        }
    }

    public void atualizaAnalise(Analise analiseExistente) {
        try (Connection connection = ConnectionFactory.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(UPDATE_ANALISE)) {

            preparedStatement.setInt(1, analiseExistente.getSleepDisorder());
            preparedStatement.setString(2, analiseExistente.getDiagnostic());
            preparedStatement.setLong(3, analiseExistente.getIdUser());
            preparedStatement.setLong(4, analiseExistente.getIdAnalise());

            preparedStatement.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException("Erro ao atualizar análise", e);
        }
    }

    private Analise mapResultSetToAnalise(ResultSet resultSet) throws SQLException {
        return new Analise(
                resultSet.getLong("id_analise"),
                resultSet.getInt("sleep_disorder"),
                resultSet.getString("diagnostic"),
                resultSet.getLong("id_user")
        );
    }
}