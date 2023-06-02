package entities;

public class Book {
    private Integer _id;
    public Integer getId() {
        return _id;
    }
    public void setId(Integer id) {
        _id = id;
    }
    private String _name;
    public String getName() {
        return _name;
    }
    public void setName(String name) {
        _name = name;
    }

    private String _publisher;
    public String getPublisher() {
        return _publisher;
    }
    public void setPublisher(String publisher) {
        _publisher = publisher;
    }

    private Integer _age;
    public Integer getAge() {
        return _age;
    }
    public void setAge(Integer age) {
        _age = age;
    }

    private String _author;
    public String getAuthor() {
        return _author;
    }
    public void setAuthor(String author) {
        _author = author;
    }

    private Integer _pageCount;
    public Integer getPageCount() {
        return _pageCount;
    }
    public void setPageCount(Integer pageCount) {
        _pageCount = pageCount;
    }

    public Book(Integer id, String name, String publisher, Integer age, String author, Integer pageCount) {
        _id = id;
        _name = name;
        _publisher = publisher;
        _age = age;
        _author = author;
        _pageCount = pageCount;
    }

    public Book() { }
}