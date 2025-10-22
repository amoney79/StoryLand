 📚 StoryLand — JavaFX Novel Reading App

> A complete JavaFX clone of the NovelUp reading platform with modern UI, real-time features, user profiles, notifications, admin tools, and full database integration.


 Overview

**NovelUp** is a modern **novel reading application** built entirely in **JavaFX** (frontend) and **MySQL** (backend).
It allows users to explore, read, manage, and track novels with an immersive reading experience — complete with:

* Dynamic content loading
* Library and history management
* Profile customization
* Real-time notifications
* Admin panel for managing novels and users

This project replicates the NovelUp ecosystem from **user registration → reading → history tracking → profile management** — fully functional and database-connected.

 Tech Stack

| Layer               | Technology                   |
| ------------------- | ---------------------------- |
| **Frontend (UI)**   | JavaFX, RichTextFX           |
| **Backend**         | Java (Core + DAO Pattern)    |
| **Database**        | MySQL                        |
| **Build Tool**      | Maven                        |
| **IDE**             | Apache NetBeans              |
| **Notifications**   | Firebase (optional)          |
| **Styling**         | iOS-style CSS, Custom Themes |
| **Version Control** | Git + GitHub                 |


 Project Structure

```
NovelUp/
│
├── src/
│   ├── Main.java
│   ├── Models/
│   │   ├── User.java
│   │   ├── Novel.java
│   │   ├── NovelHistoryItem.java
│   │   └── Notification.java
│   │
│   ├── Controller/
│   │   ├── NovelController.java
│   │   ├── UserController.java
│   │   ├── NotificationController.java
│   │   └── SettingsController.java
│   │
│   ├── db/
│   │   ├── DBConnection.java
│   │   ├── UserDAO.java
│   │   ├── NovelDAO.java
│   │   ├── HistoryDAO.java
│   │   └── SettingsDAO.java
│   │
│   ├── UI/
│   │   ├── LoginPage.java
│   │   ├── LandingPage.java
│   │   ├── LibraryPane.java
│   │   ├── HistoryScreenPane.java
│   │   ├── ProfileSettingsPane.java
│   │   ├── NotificationScreenPane.java
│   │   ├── NovelAdminScreenPane.java
│   │   ├── ChapterListScreenPane.java
│   │   └── HelpFeedbackScreenPane.java
│   │
│   ├── utils/
│   │   ├── ThemeManager.java
│   │   ├── DialogUtils.java
│   │   └── FileUtils.java
│   │
│   └── resources/
│       ├── styles.css
│       └── app_icon.png
│
└── pom.xml
```



 Setup Instructions

 1. Prerequisites

* **Java 17 or higher**
* **Apache NetBeans** (or IntelliJ/Eclipse)
* **MySQL Server**
* **Maven**

 2. Clone Repository

```bash
git clone https://github.com/yourusername/NovelUp.git
cd NovelUp
```

 3. Configure Database

Create a MySQL database named `novelup_db` and run:

```sql
CREATE DATABASE novelup_db;
USE novelup_db;

-- Users table
CREATE TABLE users (
  id INT AUTO_INCREMENT PRIMARY KEY,
  name VARCHAR(100),
  email VARCHAR(100) UNIQUE,
  password VARCHAR(255),
  joined_on TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

-- Novels table
CREATE TABLE novels (
  id INT AUTO_INCREMENT PRIMARY KEY,
  title VARCHAR(200),
  author VARCHAR(100),
  description TEXT,
  cover_url VARCHAR(255),
  file_path VARCHAR(255),
  status ENUM('reading','unread','finished') DEFAULT 'unread'
);

-- History table
CREATE TABLE novel_history (
  id INT AUTO_INCREMENT PRIMARY KEY,
  user_id INT,
  novel_id INT,
  last_read TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
  FOREIGN KEY (user_id) REFERENCES users(id),
  FOREIGN KEY (novel_id) REFERENCES novels(id)
);

-- Notifications
CREATE TABLE notifications (
  id INT AUTO_INCREMENT PRIMARY KEY,
  title VARCHAR(200),
  message TEXT,
  type VARCHAR(50),
  is_read BOOLEAN DEFAULT FALSE,
  created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);
```

 4. Configure Database Connection

Edit **`db/DBConnection.java`**:

```java
private static final String URL = "jdbc:mysql://localhost:3306/novelup_db";
private static final String USER = "root";
private static final String PASSWORD = "your_password";
```

 5. Run the App

In NetBeans:

* Right-click the project → **Run**
* Or via terminal:

```bash
mvn clean javafx:run
```

---

 Features Breakdown

 Landing Page

* Displays novels in scrollable grid format.
* Auto-loads more novels on reaching the bottom (no button required).
* Borders and padding adjusted for minimal look.

 Library Section

* Tabs: **All**, **Reading**, **Unread**, **Finished**
* Allows switching between categories seamlessly.
* Displays saved novels per user.

 History Section

* Tracks every novel a user reads.
* Displays date/time and title.
* Sorted by most recent reading activity.

 Profile Settings Pane

* **Google/Apple/Email Sign-in**
* **Edit Profile** and **Preferences**
* **Dark Mode toggle** (via `ThemeManager`)
* **History, Downloads, Notifications, About, Help & Feedback**
* Buttons appear on left, content loads dynamically on the right.

 Notification Screen

* Displays updates, new chapters, and suggestions.
* **Mark as read/unread**
* **Real-time toast popups**
* **Badge count for unread**
* **Clear all notifications**
* Persistent storage and optional Firebase integration.

 Chapter List Screen

* Scrollable chapter list from database.
* **Slider navigation** for next/previous.
* **Content/Notes popup** view.
* **Reading settings panel** with:

  * Brightness, font size, line spacing, background color.
  * Page flip effect.
* **Long-press** on text → highlight, bookmark, add notes.
* WhatsApp-style **RichTextFX** reader with emojis.

 Admin Panel

* Admin can manage novels, users, and categories.
* Displays real-time data and integrates into `ProfileSettingsPane` using `showContent()` method.



 Utilities

* **ThemeManager.java** – toggles dark/light themes globally.
* **FileUtils.java** – handles file loading and PDF paths.
* **DialogUtils.java** – standard alert, confirmation, and toast handling.



 Testing & Debugging

Use the built-in `SettingsDAO` and `HistoryDAO` logs to verify:

* Database connections
* Auto-loading behavior
* Notification refresh cycle
* Real data rendering in panes



 UI Highlights

* Rounded buttons, toggles, and panels (iOS style)
* Soft shadows and minimal borders
* Smooth animations and transitions
* Adaptive layout for all resolutions



 Maven Dependencies (pom.xml)

Key dependencies used:

```xml
<dependencies>
    <!-- JavaFX -->
    <dependency>
        <groupId>org.openjfx</groupId>
        <artifactId>javafx-controls</artifactId>
        <version>21</version>
    </dependency>

    <!-- RichTextFX -->
    <dependency>
        <groupId>org.fxmisc.richtext</groupId>
        <artifactId>richtextfx</artifactId>
        <version>0.11.1</version>
    </dependency>

    <!-- MySQL -->
    <dependency>
        <groupId>mysql</groupId>
        <artifactId>mysql-connector-java</artifactId>
        <version>8.0.33</version>
    </dependency>

    <!-- Maven JavaFX Plugin -->
    <dependency>
        <groupId>org.openjfx</groupId>
        <artifactId>javafx-maven-plugin</artifactId>
        <version>0.0.8</version>
    </dependency>
</dependencies>
```



 Developer

**Author:** Joseph Amani
**Role:** Full-Stack Java Developer
**Focus:** JavaFX, MySQL, REST API Integration, UI/UX polish



 Roadmap

* [x] Profile Settings & Library Integration
* [x] Notification System
* [x] Admin Panel
* [x] Reading Screen with RichTextFX
* [ ] Cloud sync via REST API
* [ ] Multi-user collaboration features



 License

This project is released under the **MIT License**.
You’re free to use, modify, and distribute it with attribution.



⭐ Support

If you find this project helpful:

⭐ Star it on GitHub

🐛 Report issues or suggest features

☕ Buy the developer a coffee 😄


