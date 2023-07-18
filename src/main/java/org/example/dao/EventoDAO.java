package org.example.dao;

import java.sql.*;
import org.example.model.Evento;
import java.util.ArrayList;
import java.util.List;

/**
 * Wrap CRUD (Create, Read, Update, Delete) operations on the Evento entity or data model
 *
 * @author Giovanni Leopoldo Rozza
 * @version 1.0
 * @since 2023-07-15
 */
public class EventoDAO {

    private final Connection connection;

    /**
     * Constructs an EventoDAO object with the specified database connection.
     *
     * @param connection The database connection to be used by the EventoDAO.
     */
    public EventoDAO(Connection connection) {
        this.connection = connection;
    }

    public Integer salvar(Evento evento) {
        String sql = "insert into evento (id, nome,nome_admin, data) values (null, ?, ?,?)";

        try (PreparedStatement preparedStatement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            preparedStatement.setString(1, evento.getNome());
            preparedStatement.setString(2, evento.getNomeAdmin());
            preparedStatement.setDate(3, new Date(evento.getData().getTime()));

            preparedStatement.executeUpdate();

            try (ResultSet resultSet = preparedStatement.getGeneratedKeys()) {
                resultSet.next();

                return resultSet.getInt(1);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void atualizar(Evento evento) {
        String sql = "update evento set nome = ?, nome_admin = ?,data = ? where id = ?";

        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setString(1, evento.getNome());
            preparedStatement.setString(2, evento.getNomeAdmin());
            preparedStatement.setDate(3, new Date(evento.getData().getTime()));
            preparedStatement.setInt(4, evento.getId());

            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public Evento buscar(Integer id) {
        String sql = "select * from evento where id = ?";

        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setInt(1, id);

            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                if(!resultSet.next()) {
                    return null;
                }

                return new Evento(id, resultSet.getString("nome"),
                                      resultSet.getString("nome_admin"),
                                      resultSet.getDate("data"));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public List<Evento> listar() {
        String sql = "select * from evento";

        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                List<Evento> eventos = new ArrayList<Evento>();

                while(resultSet.next()) {
                    eventos.add(new Evento(resultSet.getInt("id"),
                            resultSet.getString("nome"),
                            resultSet.getString("nome_admin"),
                            resultSet.getDate("data")));
                }

                return eventos;
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void deletar(Integer id) {
        String sql = "delete from evento where id = ?";

        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setInt(1, id);

            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
