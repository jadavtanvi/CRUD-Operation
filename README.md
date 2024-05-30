# Book Shop CRUD Application

This is a simple CRUD application for a book shop, built using PHP, MySQL, and XAMPP. The application allows you to create, read, update,search and delete book records.

## Features

- Add new book records
- View existing book records
- search book records
- Update book records
- Delete book records

## Prerequisites

Before you begin, ensure you have met the following requirements:

- You have installed [XAMPP](https://www.apachefriends.org/download.html).
- You have a basic understanding of PHP and MySQL.

## Installation

1. **Clone the Repository**

   ```sh
   git clone https://github.com/jadavtanvi/CRUD-Operation.git

2. **Start XAMPP**

Open XAMPP and start the Apache and MySQL modules.

3. **Create the Database**
Open phpMyAdmin by visiting http://localhost/phpmyadmin/ in your web browser.

## Configuration
1. **Database Connection**

Open the config.php file and update the database connection details:
      ```
      <?php
      $servername = "localhost";
      $username = "root"; // Default XAMPP MySQL username
      $password = ""; // Default XAMPP MySQL password
      $dbname = "program1";
      
      // Create connection
      $conn = new mysqli($servername, $username, $password, $dbname);
      
      // Check connection
      if ($conn->connect_error) {
          die("Connection failed: " . $conn->connect_error);
      }
      ?>
      ```
## Usage
1. **Perform CRUD Operations**

To add a new book, fill out the form on the homepage and click "Add Book".
To view all books, navigate to the "Books List" page.
To Search a book, click the "Search" button. 
To update a book, click the "Update" button next to the book you want to update, make your changes, and click "Update".
To delete a book, click the "Delete" button next to the book you want to delete.

2. **Database Structure**
The database contains a single table named books with the following structure:

id (INT) - Primary Key, Auto Increment
name (VARCHAR) - The title of the book
edition (INT) - The author of the book
price (DECIMAL) - The price of the book

By linking directly to the download page, you ensure that users can easily find and download the software required to run your application.
