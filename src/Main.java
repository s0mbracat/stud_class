import java.util.*;
import java.util.stream.*;

class Book {
    private final String title;
    private final  int year;
    private final int pages;

    public Book(String title, int year, int pages) {
        this.title = title;
        this.year = year;
        this.pages = pages;
    }

    public int getYear() { return year; }
    public int getPages() { return pages; }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Book book = (Book) o;
        return pages == book.pages && year == book.year && Objects.equals(title, book.title);
    }

    @Override
    public int hashCode() {
        return Objects.hash(title, year, pages);
    }

    @Override
    public String toString() {
        return title + " (" + year + ") - " + pages + " стр.";
    }
}

class Student {
    private final String name;
    private final List<Book> books;

    public Student(String name, List<Book> books) {
        this.name = name;
        this.books = books;
    }

    public List<Book> getBooks() { return books; }

    @Override
    public String toString() {
        return "Студент: " + name + " (книг: " + books.size() + ")";
    }
}

public class Main {
    public static void main(String[] args) {
        List<Student> students = Arrays.asList(
                new Student("Иван", Arrays.asList(
                        new Book("Malware Basics", 1996, 300),
                        new Book("Malware Algorithms", 2006, 450),
                        new Book("Malware Structures", 1997, 280),
                        new Book("Malware Development", 1996, 500),
                        new Book("Malware Design", 1995, 350)
                )),
                new Student("Мария", Arrays.asList(
                        new Book("Malware Learning", 1999, 600),
                        new Book("Malware Programming", 1800, 400),
                        new Book("Malware", 2001, 320),
                        new Book("Malware Deep Learning", 1808, 700),
                        new Book("Malware", 2001, 320)
                )),
                new Student("Петр", Arrays.asList(
                        new Book("Malware Networks", 2002, 380),
                        new Book("Malware Systems", 2005, 420),
                        new Book("Malware Engineering", 1890, 480),
                        new Book("Malware Mobile Development", 1212, 520),
                        new Book("Malware Computing", 1992, 450)
                ))
        );

        students.stream()
                .peek(System.out::println)
                .flatMap(student -> student.getBooks().stream())
                .sorted(Comparator.comparingInt(Book::getPages))
                .distinct()
                .filter(book -> book.getYear() > 2000)
                .limit(3)
                .map(Book::getYear)
                .findFirst()
                .ifPresentOrElse(
                        year -> System.out.println("Год выпуска найденной книги: " + year),
                        () -> System.out.println("Книга отсутствует")
                );
    }
}