# Library Management System

This is a library management system implemented in Java. It provides functionalities for managing books, patrons, and borrowing/returning books.

## Features

- Add new books to the library inventory.
- Add new patrons to the library database.
- Search for books by title, author, or ISBN.
- Search for patrons by name or ID.
- Borrow books by patrons.
- Return borrowed books.
- View list of all books.
- View list of all patrons.
- Generate reports such as overdue books, borrowed books by patrons, etc.

## Usage

To use the library management system, simply compile and run the `LibraryManagementSystem.java` file in your Java environment. Follow the on-screen instructions to perform various operations such as adding books, adding patrons, searching for books/patrons, borrowing/returning books, and generating reports.

```java
// Example usage
// Instantiate the LibraryManagementSystem class
LibraryManagementSystem library = new LibraryManagementSystem();

// Add a new book to the library
library.addBook("978-0451469519", "1984", "George Orwell", 10);

// Add a new patron to the library
library.addPatron("John Doe", "johndoe@example.com", "123 Main St");

// Search for a book
Book foundBook = library.searchBookByTitle("1984");
System.out.println(foundBook);

// Search for a patron
Patron foundPatron = library.searchPatronByName("John Doe");
System.out.println(foundPatron);

// Borrow a book
library.borrowBook("1984", "John Doe");

// Return a book
library.returnBook("1984", "John Doe");

// Generate a report
library.generateReport("overdue");
