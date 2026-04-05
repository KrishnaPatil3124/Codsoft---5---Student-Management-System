# 🎓 Student Management System (Java + SQLite)

A desktop-based **Student Management System** developed using **Java Swing** and **SQLite**.
This project was built as part of the **CodSoft Java Internship (Task 5)** to demonstrate core Java concepts, GUI development, and database integration.

---

## 🚀 Features

### 🔐 User Authentication

* Secure Login & Signup system
* Password hashing using SHA-256
* Protection against SQL Injection using PreparedStatements

### 👨‍🎓 Student Management

* Add, Edit, Delete student records
* View all students in a structured table
* Double-click row to edit student

### 🔍 Search Functionality

* Search students by name or roll number
* Fast and user-friendly filtering

### 🖥 Graphical User Interface

* Built using Java Swing
* Clean and responsive layout
* Keyboard support (Enter for actions)

### 💾 Database Integration

* SQLite database using JDBC
* Automatic table creation on first run
* Persistent data storage

### 🎨 User Experience Enhancements

* Confirmation dialogs (delete/edit)
* Input validation and error handling
* Non-editable table cells
* Transparent watermark at bottom

---

## 🛠 Technologies Used

* Java (JDK 8 or higher)
* Swing (GUI Framework)
* SQLite (Database)
* JDBC (Database Connectivity)

---

## 📂 Project Structure

```id="struct1"
Student-Management-System/
│
├── src/                      # Source code
│   ├── Main.java
│   ├── Database.java
│   ├── LoginFrame.java
│   ├── SignupFrame.java
│   ├── DashboardFrame.java
│   ├── StudentFormDialog.java
│   ├── Student.java
│   ├── User.java
│
├── data/                     # Database folder
│   └── students.db
│
├── lib/                      # External libraries
│   └── sqlite-jdbc-3.51.1.0.jar
│
└── README.md
```

---

## ⚙️ How to Run

1. **Clone the repository**

```id="run1"
git clone https://github.com/KrishnaPatil3124/Codsoft---5---Student-Management-System.git
```

2. **Open in IDE**

* Open in VS Code or IntelliJ IDEA

3. **Setup Project**

* Ensure `src` is marked as Source Folder
* Add SQLite JDBC JAR to project libraries

4. **Run Application**

```id="run2"
Run Main.java (inside src folder)
```

---

## 🔐 Default Behavior

* On first run, the database and tables are automatically created
* Users must sign up before logging in

---

## 💡 Key Improvements

* Secure password storage using SHA-256 hashing
* Use of PreparedStatements to prevent SQL Injection
* Clean and responsive user interface
* Enhanced user experience with keyboard shortcuts
* Data validation and error handling
* Persistent database with no data loss

---

## 📸 Screenshots (Optional)

You can add screenshots here:

* Login Screen
* 
* Signup Screen
* Dashboard (Student Table)

---

## 📌 Future Enhancements

* 📄 Export student data to PDF
* 🌙 Dark mode support
* 📊 Sorting and advanced filtering
* 👤 Role-based authentication (Admin/User)

---

## 👨‍💻 Author

**Krishna Patil**
Java Developer | Student

---

## 🏁 Internship

This project was developed as part of the
**CodSoft Java Internship - Task 5**
