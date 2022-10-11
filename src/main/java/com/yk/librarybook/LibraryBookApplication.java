package com.yk.librarybook;

import com.yk.librarybook.model.Book;
import com.yk.librarybook.model.HistoryItem;
import com.yk.librarybook.service.BookService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.List;
import java.util.Scanner;

@SpringBootApplication
public class LibraryBookApplication implements CommandLineRunner {

    public static void main(String[] args) {
        SpringApplication.run(LibraryBookApplication.class, args);
    }

    static Scanner scanner =new Scanner(System.in);

    @Override
    public void run(String... args) throws Exception {
        while(true){
            printHelp();
            String str = scanner.next();
            if(str.equals("1")){
                List<Book> bookList = BookService.getBookList();
                for(Book book : bookList){
                    System.out.println(book.getId() +"\t" + book.getName() + "\t" + book.getAuthor() + "\t"
                            + book.getCount() + "\t" + book.getPrice());
                }
            }else if(str.equals("2")){
                System.out.println("输入你的的id");
                int userId = scanner.nextInt();
                System.out.println("输入书的id");
                int id = scanner.nextInt();
                BookService.borrowBook(userId,id);
            }else if(str.equals("3")){
                System.out.println("输入你的的id");
                int userId = scanner.nextInt();
                System.out.println("输入书的id");
                int id = scanner.nextInt();
                BookService.returnBook(userId,id);
            }else if(str.equals("s")){
                admin();
            }
        }
    }

    public static void admin(){
        while(true){
            adminHelp();
            String str = scanner.next();
            if(str.equals("1")){
                System.out.println("输入书的id");
                int id = scanner.nextInt();
                System.out.println("请输入书的名字");
                String name = scanner.next();
                System.out.println("请输入书的作者");
                String author = scanner.next();
                System.out.println("请输入书的数量");
                int count = scanner.nextInt();
                System.out.println("请输入书的价格");
                double price = scanner.nextDouble();
                BookService.newBook(id,name,author,count,price,-1);
            }else if(str.equals("2")){
                List<HistoryItem> historyItemList = BookService.getHistoryItemList();
                for(HistoryItem historyItem : historyItemList){
                    String t = "归还";
                    if(historyItem.getType() == 0){
                        t = "上架";
                    }else if(historyItem.getType() == 1){
                        t = "借取";
                    }
                    System.out.println(historyItem.getTime() + "\t" + historyItem.getUserId() +  t + "\t" + "\t" + historyItem.getBookName()
                            + "\t" + historyItem.getCount());
                }
            }else if(str.equals("q")){
                break;
            }
        }
    }

    public static void printHelp(){
        System.out.println("欢迎来到图书馆图书系统");
        System.out.println("输入1 查看图书列表");
        System.out.println("输入2 借书");
        System.out.println("输入3 还书");
    }

    public static void adminHelp(){
        System.out.println("欢迎来到图书馆管理系统");
        System.out.println("输入1 上架图书");
        System.out.println("输入2 查看历史记录");
        System.out.println("输入q 退出");
    }
}
