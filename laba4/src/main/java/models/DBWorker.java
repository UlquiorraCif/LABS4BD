package models;

import entities.Book;

import java.sql.*;
import java.util.ArrayList;

public class DBWorker {
    private static Connection _connection;
    public Connection getConnection() {
        return _connection;
    }
    // Добавление пользователя в таблицу базы данных (CREATE)
    public Integer add(Book book) {
        Integer addCount = 0;

        try {
            String query = "INSERT INTO books (name, lastName, age, email, phone) values (?,?,?,?,?)";
            PreparedStatement statement = _connection.prepareStatement(query);
            statement.setString(1, book.getName());
            statement.setString(2, book.getPublisher());
            statement.setInt(3, book.getAge());
            statement.setString(4, book.getAuthor());
            statement.setInt(5, book.getPageCount());
            addCount = statement.executeUpdate();
        } catch (SQLException e) {
            e.getStackTrace();
        }

        return addCount;
    }
    // Получение всех пользователей из таблицы базы данных (READ)
    public ArrayList<Book> load() {
        var users = new ArrayList<Book>();

        try {
            String query = "SELECT * FROM books";
            PreparedStatement statement = _connection.prepareStatement(query);
            ResultSet items = statement.executeQuery();

            while (items.next()) {
                users.add(new Book(
                        items.getInt(1),
                        items.getString(2),
                        items.getString(3),
                        items.getInt(4),
                        items.getString(5),
                        items.getInt(6)));
            }
        } catch (SQLException e) {
            e.getStackTrace();
        }

        return users;
    }

    // Редактирование выбранного пользователя (UPDATE)
    public Integer update(Book book) {
        Integer updatedCount = 0;

        try {
            String query = "UPDATE books SET name=?, lastName=?, age=?, email=?, phone=? WHERE id=?";

            PreparedStatement statement = _connection.prepareStatement(query);
            statement.setString(1, book.getName());
            statement.setString(2, book.getPublisher());
            statement.setInt(3, book.getAge());
            statement.setString(4, book.getAuthor());
            statement.setInt(5, book.getPageCount());
            statement.setInt(6, book.getId());

            updatedCount = statement.executeUpdate();
        } catch (SQLException e) {
            e.getStackTrace();
        }

        return updatedCount;
    }

    // Удаление выбранного пользователя (DELETE)
    public Integer delete(Book book) {
        Integer deletedCount = 0;

        try {
            String query = "DELETE FROM books WHERE id= ? ";

            PreparedStatement statement = _connection.prepareStatement(query);
            statement.setInt(1, book.getId());

            deletedCount = statement.executeUpdate();
        } catch (SQLException e) {
            e.getStackTrace();
        }

        return deletedCount;
    }

    public DBWorker(Connection connection) {
        _connection = connection;
    }
}