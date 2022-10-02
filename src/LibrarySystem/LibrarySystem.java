package LibrarySystem;

import java.util.Scanner;

public class LibrarySystem {
    static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        LibraryForAdmin.load();
        LibraryForStudents.load();
        System.out.println("欢迎来到图书馆管理系统。");
        while(true){
            System.out.println("输入1查看书籍目录，输入2借阅书籍、输入3归还书籍、输入q退出系统。");
            String number = scanner.next();
            if(number.equals("1")){
                LibraryForAdmin.bookDirectory();
            }else if(number.equals("2")){
                System.out.println("请输入需要借阅书籍对应的编号：");
                int id = scanner.nextInt();
                LibraryForStudents.bookBorrow(id);
                LibraryForStudents.save();
            }else if(number.equals("3")){
                System.out.println("请输入需要归还的图书编号：");
                int id = scanner.nextInt();
                LibraryForStudents.bookReturn(id);
            }else if(number.equals("q")){
                break;
            }else if(number.equals("s")){
                libraryAdmin();
            }
        }
    }

    /**
     * 管理员模式
     */
    public static void libraryAdmin(){
        while(true){
            System.out.println("欢迎来到图书管理员模式：");
            System.out.println("输入1录入书名，输入s保存，输入q退出");
            String num = scanner.next();
            if(num.equals("1")){
                System.out.println("请输入书名：");
                String name = scanner.next();
                System.out.println("请输入书本数量：");
                int count = scanner.nextInt();
                LibraryForAdmin.bookInput(name,count);
            }else if(num.equals("s")){
                LibraryForAdmin.save();
            }else if(num.equals("q")){
                break;
            }
        }
    }
}
