package com.book;

import java.util.List;
import java.util.Scanner;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;

public class Test {

	public static void main(String[] args) {
EntityManagerFactory entityManagerFactory=Persistence.createEntityManagerFactory("JPA-PU");
		
		EntityManager entityManager=entityManagerFactory.createEntityManager();
		
		entityManager.getTransaction().begin();
		
		String qryAll="from Book";
		String qryAuthorName="from Book where author.authorName=?";
		String qryPriceRange="from Book where price between 500 and 1000";
		String qryBookId="select authorName from Author where book.bookId=?";
		
		System.out.println("Enter your choice:");
		System.out.println("1.Query all books in database");
		System.out.println("2.Query all books written by author name");
		System.out.println("3.Lis all books with prices 500-1000");
		System.out.println("4. List authors for given book id");
		
		Scanner sc=new Scanner(System.in);
		
		Byte choice=Byte.parseByte(sc.next());
		
		switch(choice){
		case 1:
			TypedQuery<Book> qry=entityManager.createQuery(qryAll,Book.class);
			
			List<Book> list=qry.getResultList();
			
			for (Book book : list) {
				System.out.println(book.getTitle()+" "+book.getPrice());
			}

			
		break;
			
		case 2:
			System.out.println("Enter the author name:");
			String authorName=sc.nextLine();
			
			TypedQuery<Book> qry1=entityManager.createQuery(qryAuthorName,Book.class);
			qry1.setParameter(1, authorName);
			
			List<Book> list1=qry1.getResultList();
			for (Book book : list1) {
				System.out.println(book.getTitle()+" "+book.getPrice());
			}
		break;
		
		case 3:
			TypedQuery<Book> qry2=entityManager.createQuery(qryPriceRange, Book.class);
			List<Book> list2=qry2.getResultList();
			for (Book book : list2) {
				System.out.println(book.getTitle()+" "+book.getPrice());
			}
		break;
		
		case 4:
			System.out.println("Enter Book Id:");
			int bookId=sc.nextInt();
			
			TypedQuery<Author> qry3=entityManager.createQuery(qryBookId,Author.class);
			qry3.setParameter(1, bookId);
			
			List<Author> list3=qry3.getResultList();
			for (Author author : list3) {
				System.out.println(author.getAuthorName());
			}
						
		break;
		
		
		
		
		}
		
		
	
		
		
		entityManager.getTransaction().commit();
	
		entityManager.close();
		entityManagerFactory.close();

	}

}
