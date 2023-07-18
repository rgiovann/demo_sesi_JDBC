package dao;

import org.example.dao.EventoDAO;
import org.example.model.Evento;
import org.junit.jupiter.api.*;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Date;

/**
 * Junit class test to validate EventoDAO methods.
 *
 * @author Giovanni Leopoldo Rozza
 * @version 1.0
 * @since 2023-07-15
 */
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class EventoDAOTest {

    private static  Connection connection;
    private static Evento evento;
    private static EventoDAO dao;
    private static Integer id;
    private  String nomeAlterado;


    @BeforeAll
    public static void iniciarClasse() throws SQLException {
        connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/controle_eventos" +
                "?useTimezone=true&serverTimezone=UTC", "developer", "ranger19");

        evento = new Evento(null, "Evento inserido Teste JDBC","Armando Salazar", new Date());

        // Criando a instância do DAO.
        dao = new EventoDAO(connection);

    }

    @AfterAll
    public static void encerrarClasse() throws SQLException {
        connection.close();
    }

    @Order(1)
    @DisplayName("001 - Insere um registro no Banco de dados e verifica que o id retornado não é nulo.")
    @Test public void insereRegistroBancoDados(){
        // Fazendo a inserção e recuperando o identificador.
        id = dao.salvar(evento);
        Assertions.assertNotNull(id, "Identificador foi retornado como NULL.");

    }
    @Order(2)
    @DisplayName("002 - Verifica que o registro inserido foi realmente para o banco de dados.")
    @Test public void verificaSeInsercaoRegistroBancoDadosBemSucedida(){
        // Atribuindo o identificador retornado ao atributo "id".
        evento.setId(id);

        // Verificando se o registro realmente foi para o banco de dados.
        Evento eventoBD = dao.buscar(evento.getId());
        Assertions.assertNotNull(evento, "Evento nulo.");
        Assertions.assertEquals(eventoBD.getNome(), evento.getNome(), "O nome foi inserido corretamente.");


    }

    @Order(3)
    @DisplayName("003 - Verifica se o campo <nome> foi  alterado no banco de dados.")
    @Test public void VerificaAtualizacaoRegistroNoBancoDados() {
        // Atualizando o registro no banco de dados.
        nomeAlterado = evento.getNome() + " alterado";
        evento.setNome(nomeAlterado);
        dao.atualizar(evento);
        // Verificando se atualização ocorreu com sucesso.
        evento = dao.buscar(evento.getId());
        Assertions.assertEquals(nomeAlterado, evento.getNome(), "O nome não foi atualizado corretamente.");
    }

    @Order(4)
    @DisplayName("004 - Verifica se o registro foi deletado no banco de dados.")
    @Test public void verificaDelecaoRegistroBancoDeDados() {

        // Removendo o registro.
        dao.deletar(2);
        // O registro não existe mais. O método "buscar" deve retornar nulo.
        evento = dao.buscar(2);
        Assertions.assertNull(evento, "Evento ainda existe e não deveria.");
    }


}