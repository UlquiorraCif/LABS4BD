package controlers;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import entities.Book;
import models.DBConnector;
import models.DBWorker;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.stream.Collectors;

@WebServlet("/MainServlet")
public class MainServlet extends HttpServlet {
    private DBWorker _dbworker;
    public MainServlet() {
        var connection = DBConnector.connect();

        if (connection != null) {
            System.out.println("Соединение установлено!");
            _dbworker = new DBWorker(connection);
        }
        else {
            System.err.println("Не удалось установить соединение!");
        }
    }
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        var users = _dbworker.load();

        // Серелизация в JSON
        ObjectMapper mapper = new ObjectMapper();
        String data = mapper.writeValueAsString(users);
        resp.setContentType("application/json");
        PrintWriter out = resp.getWriter();
        out.println(data);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // Получение JSON
        BufferedReader reader = new BufferedReader(new InputStreamReader(req.getInputStream()));
        String data = reader.lines().collect(Collectors.joining()); // Сам JSON
        reader.close();

        ObjectMapper objectMapper = new ObjectMapper();
        JsonNode jsonNode = objectMapper.readTree(data);

        var userNode = jsonNode.get("User");
        Book book = new Book();
        book.setId(userNode.get("id").asInt());
        book.setName(userNode.get("name").asText());
        book.setPublisher(userNode.get("publisher").asText());
        book.setAge(userNode.get("age").asInt());
        book.setAuthor(userNode.get("author").asText());
        book.setPageCount(userNode.get("pageCount").asInt());

        String action = jsonNode.get("action").asText();

        switch (action) {
            case "add" -> {
                _dbworker.add(book);
                resp.getWriter().write("Новый пользователь добавлен!");
            }
            case "edit" -> {
                _dbworker.update(book);
                resp.getWriter().write("Пользователь изменён!");
            }
            case "delete" -> {
                _dbworker.delete(book);
                resp.getWriter().write("Пользователь удалён!");
            }
        }
    }
}