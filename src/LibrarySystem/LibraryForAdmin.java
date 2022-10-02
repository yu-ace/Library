package LibrarySystem;

import org.apache.commons.io.FileUtils;

import java.io.File;
import java.io.IOException;

public class LibraryForAdmin {
    static String[] bookNames = new String[100];
    static int[] bookCounts = new int[100];
    static int size = 0;

    static File bookFile = new File(FileUtils.getUserDirectoryPath() + "/Library/Admin/bookInformation.dat");


    /**
     * 展示图书馆书籍目录
     */
    public static void bookDirectory(){
        for(int i = 0;i < size;i++){
            System.out.println(bookNames[i] + "\t" + bookCounts[i]);
        }
    }

    /**
     * 图书馆书籍信息录入
     * @param name 书籍名字
     * @param count 书籍数量
     */
    public static void bookInput(String name,int count){
        if(size > bookNames.length / 2){
            String[] newBookNames = new String[bookNames.length + 50];
            int[] newBookCounts = new int[bookCounts.length + 50];
            for(int i = 0;i <bookNames.length;i++){
                newBookNames[i] = bookNames[i];
                newBookCounts[i] = bookCounts[i];
            }
            bookNames = newBookNames;
            bookCounts = newBookCounts;
        }
        bookNames[size] = name;
        bookCounts[size] = count;
        size++;
    }

    /**
     * 书籍信息保存
     */
    public static void save(){
        FileUtils.deleteQuietly(bookFile);
        for(int i = 0;i < size;i++){
            String bookInformation = bookNames[i] + "\t" + bookCounts[i];
            try {
                FileUtils.write(bookFile,bookInformation + "\n","utf8",true);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * 书籍信息读取
     */
    public static void load(){
        try {
            String bookInformationFile = FileUtils.readFileToString(bookFile,"utf8");
            String[] bookLines = bookInformationFile.split("\n");
            for(int i = 0 ;i <bookLines.length;i++){
                String[] bookContents = bookLines[i].split("\t");
                String name = bookContents[0];
                int count = Integer.getInteger(bookContents[i]);
                bookInput(name,count);
            }
        } catch (IOException e) {
            //避难第一次输入时，由于没有文件导致系统报错的情况。
        }
    }


}
