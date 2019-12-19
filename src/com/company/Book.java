package com.company;

import java.util.ArrayList;

public class Book {
    private String author, name, publisher;
    private Integer number, pages, price;
    public Book() { author = ""; name = ""; publisher = ""; number = 0; pages = 0; price = 0; }

    public Book(String author, String name, String publisher, int number, int pages, int price) throws IllegalArgumentException
    {
        if(author.equals("") || name.equals("") || publisher.equals("") || number<0 || pages <=0 || price <0) throw new IllegalArgumentException("Введите корректное значение");
        this.author = author; this.name = name; this.publisher = publisher; this.number = number; this.pages = pages; this.price = price;
    }
    public void setData(String author, String name, String publisher, int number, int pages, int price) throws IllegalArgumentException
    {
        if(author.equals("") || name.equals("") || publisher.equals("") || number<0 || pages <=0 || price <0) throw new IllegalArgumentException("Введите корректное значение");
        this.author = author; this.name = name; this.publisher = publisher; this.number = number; this.pages = pages; this.price = price;
    }
    @Override
    public String toString()
    {
        return author + '\n' + name + '\n' + publisher + '\n' + price.toString()  + '\n' + pages.toString() + '\n' + number.toString() + '\n';
    }
    public int getNumber(ArrayList<Book> lst, String author, String name) throws IllegalArgumentException
    {
        for (int i = 0; i<lst.size(); i++) if(this.author.equals(author) && this.name.equals(name)) return this.number;
        throw new IllegalArgumentException("Такой книги нет");
    }
    public boolean comparePublisher(Book other)
    {
        return this.publisher.equals(other.publisher);
    }
    public boolean compareAuthor(String author) {return author.equals(this.author);}
}
