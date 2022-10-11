package com.yk.librarybook.service;

import com.yk.librarybook.dao.BookDao;
import com.yk.librarybook.dao.HistoryDao;
import com.yk.librarybook.model.Book;
import com.yk.librarybook.model.HistoryItem;

import java.util.Date;
import java.util.List;

public class BookService {
    static List<Book> bookList = BookDao.load();
    static List<HistoryItem> historyItemList = HistoryDao.load();

    public static void newBook(int id,String name,String author,int count,double price,int userId){
        Book book =new Book();
        book.setId(id);
        book.setName(name);
        book.setAuthor(author);
        book.setCount(count);
        book.setPrice(price);
        book.setOriginalCount(count);
        bookList.add(book);
        BookDao.save(bookList);

        HistoryItem historyItem = new HistoryItem();
        historyItem.setBookId(id);
        historyItem.setBookName(name);
        historyItem.setTime(new Date());
        historyItem.setType(0);
        historyItem.setUserId(userId);
        historyItem.setCount(count);
        historyItemList.add(historyItem);
        HistoryDao.save(historyItemList);
    }

    public static List<Book> getBookList() {
        return bookList;
    }

    public static List<HistoryItem> getHistoryItemList() {
        return historyItemList;
    }

    public static void borrowBook(int userId,int id){
        HistoryItem historyItem = new HistoryItem();
        for(Book book : bookList){
            if(book.getId() == id && book.getCount() > 0){
                historyItem.setBookId(id);
                historyItem.setUserId(userId);
                historyItem.setType(1);
                historyItem.setBookName(book.getName());
                historyItem.setTime(new Date());
                historyItem.setCount(1);
                historyItemList.add(historyItem);
                HistoryDao.save(historyItemList);
                book.setCount(book.getCount() - 1);
                BookDao.save(bookList);
            }else if(book.getId() == id && book.getCount() < 1){
                System.out.println("对不起，该书库存不足，请借其他书籍");
            }
        }
    }

    public static void returnBook(int userId,int id){
        HistoryItem historyItem = new HistoryItem();
        for(Book book : bookList){
            if(book.getId() == id){
                historyItem.setUserId(userId);
                historyItem.setType(2);
                historyItem.setBookName(book.getName());
                historyItem.setTime(new Date());
                historyItem.setCount(1);
                historyItemList.add(historyItem);
                HistoryDao.save(historyItemList);
                book.setCount(book.getCount() + 1);
                BookDao.save(bookList);
            }
        }
    }


}
