package com.company;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;

public class Book_Files {
    private BufferedReader fileIn;
    private FileOutputStream fileOut;
    private RandomAccessFile rfileOut;
    public Book_Files() {fileIn = null; fileOut = null;}
    public void write(Book b, String fileName) throws FileNotFoundException, IOException
    {

        try {
            fileOut = new FileOutputStream(fileName, true);
        }
        catch (FileNotFoundException e) {System.out.println("Файл не был найден");}
        fileOut.write((b.toString() + '\n').getBytes());
        fileOut.close();
    }
    public ArrayList<Book> read(String fileName) throws FileNotFoundException, IOException
    {
        FileInputStream in = new FileInputStream(fileName);
        fileIn = new BufferedReader(new InputStreamReader(in,"UTF8"));
        String s = "";
        for (int i = fileIn.read(); i != -1; i = fileIn.read()) s+= (char)i;
        String[] res = s.split("\n");
        ArrayList<Book> list = new ArrayList<Book>();
        for (int i = 0; i < res.length; i+=7)
            list.add(new Book(res[i], res[i+1], res[i+2], Integer.parseInt(res[i+5]), Integer.parseInt(res[i+4]), Integer.parseInt(res[i+3])));
        fileIn.close();
        return list;
    }
    public void write_with_randAccess(ArrayList<Book> lst, String fileName) throws FileNotFoundException, IOException
    {
        rfileOut = new RandomAccessFile(fileName, "rw");
        rfileOut.seek(rfileOut.length());
        for (Book info:lst)
        {
            String[] data = info.toString().split("\n");
            for (String c:data)
            {
                while (c.length() < 25) c+=" ";
                byte[] b = (c + "\n").getBytes("UTF16");
                rfileOut.write(b);
            }
        }
        rfileOut.close();
    }
    public void change_publisher(String fileName, String new_publisher) throws FileNotFoundException, IOException
    {
        while (new_publisher.length() < 25) new_publisher+=" ";
        byte[] b = (new_publisher).getBytes("UTF16");
        rfileOut = new RandomAccessFile(fileName, "rw");
        for(int i = 108; i < rfileOut.length(); i+=270) {
            rfileOut.seek(i);
            rfileOut.write(b);
        }
        rfileOut.close();
    }
}
