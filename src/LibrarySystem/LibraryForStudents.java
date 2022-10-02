package LibrarySystem;

import org.apache.commons.io.FileUtils;

import java.io.File;
import java.io.IOException;

public class LibraryForStudents {
    static int[] readerBookIds = new int[100];
    static int[] readerBookCounts = new int[100];
    static int bookSize = 0;
    static File readerBookFile = new File
            (FileUtils.getUserDirectoryPath()
            + "/Library/Reader/bookInformation.dat");


    /**
     * 借阅书籍信息录入
     */
    public static void bookBorrow(int id){
        if(LibraryForAdmin.bookCounts[id] > 0){
            LibraryForAdmin.bookCounts[id]--;
            readerBookIds[bookSize] = id;
            readerBookCounts[bookSize]++;
            bookSize++;
        }else{
            System.out.println("对不起，本书库存不足，借阅失败。");
        }
    }


    /**
     * 归还借阅书籍
     * @param id 书籍目录
     */
    public static void bookReturn(int id){
        LibraryForAdmin.bookCounts[id]++;
    }

    /**
     * 保存借阅书籍信息
     */
    public static void save(){
            try {
                for(int i = 0;i < bookSize;i++){
                FileUtils.write(readerBookFile,readerBookIds[i] + "\n","utf8",true);
                }
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
    }

    /**
     * 读取借阅信息
     */
    public static void load(){
        try {
            String readerBookInformation = FileUtils.readFileToString(readerBookFile,"utf8");
            String[] readerBookLines = readerBookInformation.split("\n");
            for(int i = 0;i < readerBookLines.length;i++){
                int id = Integer.getInteger(readerBookLines[i]);
                bookBorrow(id);
            }
        } catch (IOException e) {
            //避难第一次输入时，由于没有文件导致系统报错的情况。
        }
    }
}
