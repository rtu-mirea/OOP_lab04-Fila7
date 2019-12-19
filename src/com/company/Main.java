package com.company;

import java.io.*;
import java.nio.charset.Charset;
import java.util.ArrayList;

public class Main {

    public static void main(String[] args) throws IOException{
	//testFiles();
    //testBooks();
    testSymbols();
    }
    private static void testFiles() throws IOException
    {
        try {
            File F1 = new File("MyFile1.txt");
            F1.createNewFile();
            File F2 = new File("D:\\MyFile2.txt");
            F2.createNewFile();
            File dir = new File("D:\\papka");
            File F3 = new File("D:\\papka\\MyFile3.txt");
            dir.mkdir();
            F3.createNewFile();
            if(!F1.exists() || !F2.exists() || !F3.exists()) throw new FileNotFoundException();
        }
        catch(FileNotFoundException e )
        {
            System.out.println("Файл не был найден");
        }
        catch(IOException e)
        {
            System.out.println("Файл не создался");
        }
    }
    private static void testBooks() throws IllegalArgumentException, FileNotFoundException, IOException
    {
        Book_Files f = new Book_Files();
        ArrayList<Book> lst = new ArrayList<Book>();
        lst.add(new Book("А. Сапковский", "Последнее желание", "АСТ", 0, 320, 304));
        lst.add(new Book("А. Сапковский", "Кровь эльфов", "АСТ", 1, 320, 300));
        lst.add(new Book("А. Сапковский", "Час презрения", "АСТ", 2, 352, 300));
        lst.add(new Book("А. Сапковский", "Крещение огнём", "АСТ", 3, 384, 300));
        lst.add(new Book("А. Пушкин", "Сборник стихов", "АСТ", 101, 140, 240));
        lst.add(new Book("Ф. Достоевский", "Преступление и наказание", "Эксмо", 31, 592, 115));
        lst.add(new Book("А. Сапковский", "Башня ласточки", "АСТ", 4, 448, 321));
        lst.add(new Book("А. Сапковский", "Владычица озера", "АСТ", 5, 544, 321));
        for (int i = 0; i < lst.size(); i++) f.write(lst.get(i), "Books.txt");
        lst = new ArrayList<Book>();
        try {
            ArrayList<Book> lst1 = f.read("Books.txt");
            for (int i = 0;i<lst1.size();i++)
                if(lst1.get(i).compareAuthor("А. Сапковский"))
                    lst.add(lst1.get(i));
        }
        catch (FileNotFoundException e){
            System.out.println("Файл не был найден");
        }
        f.write_with_randAccess(lst, "rand_access_file.txt");
        f.change_publisher("rand_access_file.txt", "NicePub");
    }
    private static void testSymbols() throws FileNotFoundException, IOException
    {
        //Упражнение 1
        FileOutputStream f = new FileOutputStream("T1.txt");
        File f2 = new File("T2.txt");
        f2.createNewFile();
        f.write(("some symbols").getBytes("UTF8"));
        f.close();
        f = new FileOutputStream("T2.txt");
        BufferedReader f1 = new BufferedReader(new InputStreamReader(new FileInputStream("T1.txt"),"UTF8"));
        String s = "";
        for (int i = f1.read(); i != -1; i = f1.read()) s+= (char)i;
        f.write(s.getBytes("UTF8"));
        f.close();
        f1.close();
        //Упражнение 2
        f = new FileOutputStream("A.txt");
        s = "This is the text that contains five hundred twelve symbols and there is no useful information in it except the information about how many symbols it contains. It is so boring to write text like this and do some work instead of sleep or walking around 1234.";
        s+= "This is the text that contains five hundred twelve symbols and there is no useful information in it except the information about how many symbols it contains. It is so boring to write text like this and do some work instead of sleep or walking around 1234.";
        f.write(s.getBytes());
        BufferedReader inp = new BufferedReader(new InputStreamReader(new FileInputStream("A.txt")), 128);
        BufferedWriter out = new BufferedWriter(new FileWriter("B.txt"), 128);
        char[] buf = new char[128];
        for(int i = 0; i<4; i++)
        {
            for (int j = 0;j<128; j++)
                buf[j] = (char)inp.read();
            out.write(buf);
            out.flush();
            out.newLine();
        }
        inp.close();
        out.close();
        //Упражнение 3
        BufferedReader in = new BufferedReader(new InputStreamReader(new FileInputStream("D:\\Java\\Labs\\lab_4\\src\\com\\company\\A.txt"), "utf8"));
        System.out.println(Charset.defaultCharset().name());
        s ="";
        for (int i = in.read();i!=-1;i = in.read()) s+=(char)i;
        System.out.println(s);
    }
}