package com.chenfeng.demo2;

import com.chenfeng.demo2.model.Book;
import com.chenfeng.demo2.model.HistoryItem;
import com.chenfeng.demo2.service.BookService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.Scanner;

@SpringBootApplication
public class Demo2Application implements CommandLineRunner {

    Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        SpringApplication.run(Demo2Application.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        BookService.load();
        HistoryManager.load();

        while (true){
            printHelp();
            String next = scanner.next();
            if(next.equals("1")){
                Book[] books = BookService.getBooks();
                for (Book book : books) {
                    if(book == null){
                        break;
                    }
                    System.out.println(book.getId()+"\t"+book.getName()+"\t"
                    +book.getAuthor()+"\t"+ BookService.remains(book.getId()));
                }
            }else if(next.equals("2")){
                System.out.println("请输入图书编号：");
                int id = scanner.nextInt();
                BookService.borrowBook(id,1,1);
            }else if(next.equals("3")){
                System.out.println("请输入图书编号：");
                int id = scanner.nextInt();
                BookService.returnBook(id,1,1);
            }else if(next.equals("s")){
                adminModel();
            }else{
                BookService.save();
                HistoryManager.save();
                break;
            }
        }
    }

    public void printHelp(){
        System.out.println("欢迎使用柠檬图书馆管理系统");
        System.out.println("输入1查看图书列表");
        System.out.println("输入2借书");
        System.out.println("输入3还书");
    }

    public void adminMenu(){
        System.out.println("输入1入库");
        System.out.println("输入2查看图书历史记录");
        System.out.println("输入s保存");
        System.out.println("输入q退出");
    }

    public void adminModel(){
        while (true){
            adminMenu();
            String next = scanner.next();
            if(next.equals("1")){
                newBook();
            }else if(next.equals("2")){
                HistoryItem[] historyItems = HistoryManager.getHistoryItems();
                for (HistoryItem item : historyItems) {
                    if(item==null)
                        break;
                    Book book = BookService.getBookById(item.getBookId());
                    String t = "归还";
                    if(item.getType()==0){
                        t= "入库";
                    }else if(item.getType()==1){
                        t= "借出";
                    }
                    System.out.println(item.getTime()+"\t"+t+"\t"+book.getName()+"\t"+item.getCount()+"本");
                }
            }else if(next.equals("s")){
                BookService.save();
                HistoryManager.save();
            }else{
                break;
            }
        }
    }

    public void newBook(){
        System.out.println("请输入图书名称：");
        String name = scanner.next();
        System.out.println("请输入图书编号：");
        int id = scanner.nextInt();
        System.out.println("请输入图书作者：");
        String author = scanner.next();
        System.out.println("请输入图书价格：");
        double price = scanner.nextDouble();
        System.out.println("请输入图书分类：");
        int category = scanner.nextInt();
        System.out.println("请输入图书数量：");
        int count = scanner.nextInt();
        BookService.newBook(id,name,author,category,price,count);
    }



}
