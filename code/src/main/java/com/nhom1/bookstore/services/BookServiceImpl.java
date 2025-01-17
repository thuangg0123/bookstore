package com.nhom1.bookstore.services;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.nhom1.bookstore.entity.Book;
import com.nhom1.bookstore.repositories.BookDAOController;

@Service
public class BookServiceImpl implements BookService {
    private final BookDAOController bookDAOController;

    public BookServiceImpl(BookDAOController bookDAOController) {
        this.bookDAOController = bookDAOController;
    }

    @Override
    public List<Book> getBookList() {
        return bookDAOController.getBookList();
    }

    @Override
    public Book getBook(String id) {
        return bookDAOController.getBook(id);
    }

    @Override
    public void editBook(Book newBook) {
        bookDAOController.editBook(newBook);
    }

    @Override
    public List<Book> search(String tuKhoa) {
        return bookDAOController.search(tuKhoa);
    }

    @Override
    public void deleteBook(String id) {
        bookDAOController.deleteBook(id);
    }

    @Override
    public void addBook(Book newBook) {
        bookDAOController.addBook(newBook);
    }

    @Override
    public String fileToFilePathConverter(MultipartFile file) {
        String uploadFolder = "src\\main\\resources\\static\\img\\product";
        String filePath = "/img/product/";
        Path uploadPath = Paths.get(uploadFolder).toAbsolutePath().normalize();
        String fileName = System.currentTimeMillis() + "_" + file.getOriginalFilename();
        Path targetLocation = uploadPath.resolve(fileName);
        try {
            Files.copy(file.getInputStream(), targetLocation);
            filePath += fileName;
            return filePath;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return "";
    }

    @Override
    public List<Book> getTopSelling() {
        List<Book> booklist = bookDAOController.getBookList();
        Collections.sort(booklist, Comparator.comparingInt(Book::getDaBan).reversed());

        return booklist.subList(0, Math.min(5, booklist.size()));
    }

    @Override
    public void updateSoldQuantity(String id, int daBan) {
        bookDAOController.updateSoldQuantity(id, daBan);
    }

    // @Override
    // public boolean bookExists(String name, String author, String publisher) {
    // List<Book> existingBooks = bookDAOController.search(name);
    // for (Book book : existingBooks) {
    // if (book.getTacGia().equals(author) &&
    // book.getNhaCungCap().equals(publisher)) {
    // return true;
    // }
    // }
    // return false;
    // }
}
