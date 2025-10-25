
<div align="center">

<img src="/assets/storyland_banner.png" width="100%" alt="StoryLand Banner"/>

# 📚 **StoryLand — Where Stories Meet Technology**

### A Complete JavaFX Novel Reading Ecosystem by **StoryLand Studios**

[![JavaFX](https://img.shields.io/badge/JavaFX-21%2B-blue?logo=java&logoColor=white)](#)
[![License: MIT](https://img.shields.io/badge/License-MIT-green.svg)](LICENSE)
[![Build Status](https://img.shields.io/badge/build-passing-brightgreen.svg)](#)
[![MySQL](https://img.shields.io/badge/Database-MySQL-orange?logo=mysql&logoColor=white)](#)
[![Maven](https://img.shields.io/badge/Build-Maven-red?logo=apachemaven&logoColor=white)](#)
[![Twitter Follow](https://img.shields.io/twitter/follow/Ma_ck_enzie_J?style=social)](https://x.com/Ma_ck_enzie_J)

---

</div>

## 🌍 Overview

**StoryLand** is a next-generation **JavaFX-based novel reading platform** that bridges creativity and technology.  
It replicates the **NovelUp ecosystem** with complete functionality — from user registration, novel exploration, immersive reading, history tracking, to profile management — all powered by a **MySQL backend** and designed with a sleek, iOS-style interface.

> ✨ Built for readers. Designed for developers. Backed by innovation.

---

## 🧠 Core Features

| Area | Description |
|------|--------------|
| 🎨 **Modern UI** | Smooth, minimal, and iOS-inspired layout with custom themes |
| 📖 **Immersive Reader** | Chapter navigation slider, notes popup, emoji-rich chat-style text |
| 📚 **Library System** | Manage All, Reading, Unread, and Finished novels |
| 🕒 **History Tracking** | Smart logging of every reading activity |
| 👤 **Profile Settings** | Google/Apple/email sign-in, dark mode, reading preferences |
| 🔔 **Notifications** | Real-time updates, toasts, badges, Firebase-ready |
| ⚙️ **Admin Dashboard** | Manage novels, users, categories, and reports |
| ☁️ **Database Integration** | Full DAO-based MySQL backend |
| 💬 **Feedback & Support** | Built-in help & feedback system |

---

## 🧱 Tech Stack

| Layer | Technology |
|-------|-------------|
| **Frontend (UI)** | JavaFX, RichTextFX |
| **Backend** | Java (Core + DAO Pattern) |
| **Database** | MySQL |
| **Build Tool** | Maven |
| **IDE** | Apache NetBeans |
| **Styling** | iOS-style CSS, ThemeManager |
| **Notifications** | Firebase (optional) |
| **Version Control** | Git + GitHub |

---

## 🗂️ Project Structure

```

StoryLand/
│
├── src/
│   ├── Main.java
│   ├── Models/
│   │   ├── User.java
│   │   ├── Novel.java
│   │   ├── Notification.java
│   │   └── NovelHistoryItem.java
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
│   │   ├── ProfileSettingsPane.java
│   │   ├── HistoryScreenPane.java
│   │   ├── NotificationScreenPane.java
│   │   ├── NovelAdminScreenPane.java
│   │   ├── ChapterListScreenPane.java
│   │   └── HelpFeedbackScreenPane.java
│   │
│   └── utils/
│       ├── ThemeManager.java
│       ├── DialogUtils.java
│       └── FileUtils.java
│
└── pom.xml

````

---

## ⚙️ Installation & Setup

### Prerequisites
- Java 17+
- Apache NetBeans or IntelliJ IDEA
- MySQL Server
- Maven

### Clone the Repository
```bash
git clone https://github.com/amoney79/StoryLand.git
cd StoryLand
````

### Configure Database

```sql
CREATE DATABASE storyland_db;
USE storyland_db;

CREATE TABLE users (...);
CREATE TABLE novels (...);
CREATE TABLE novel_history (...);
CREATE TABLE notifications (...);
```

Then edit in `db/DBConnection.java`:

```java
private static final String URL = "jdbc:mysql://localhost:3306/storyland_db";
private static final String USER = "root";
private static final String PASSWORD = "your_password";
```

### Run the App

```bash
mvn clean javafx:run
```

---

## 🧩 UI Highlights

✨ **Landing Page**

* Auto-loads novels on scroll
* Minimal, borderless display

📚 **Library Pane**

* Filter by All / Reading / Unread / Finished

🕒 **History Screen**

* Displays reading timeline per user

👤 **Profile Settings**

* Dynamic content switching
* Dark mode toggle
* About, Help, Feedback, and Rate Us integrated

🔔 **Notification Screen**

* Realtime updates, badges, and clear-all button

📖 **Chapter Screen**

* RichTextFX reader with highlights, notes, emojis, and page flip

⚙️ **Admin Panel**

* Real-time novel/user management

---

## 💼 For Investors & Business Partners

**StoryLand Studios** envisions the future of intelligent reading — a space where literature meets adaptive technology.

### Why Invest?

* 📈 Rapidly growing e-reading market
* 💡 Proprietary JavaFX reading engine
* 🌐 Expandable to cloud & mobile platforms
* 🤝 Transparent data & user analytics integration

**We are open to:**

* Investment partnerships 💰
* Strategic collaborations 🤝
* Joint publishing ventures 📚

📧 **Contact:** [mackenziejoseph396@gmail.com](mailto:mackenziejoseph396@gmail.com)

---

## 👨‍💻 For Developers

Interested in contributing? Here’s how:

```bash
# Fork and clone
git clone https://github.com/amoney79/StoryLand.git

# Create your branch
git checkout -b feature/awesome-feature

# Commit and push
git commit -m "Add awesome feature"
git push origin feature/awesome-feature
```

Then open a Pull Request!

---

## 💰 For Sponsors

Support open-source innovation with **StoryLand Studios** 🌍
Your sponsorship helps us build:

* Real-time sync APIs
* Mobile extensions
* AI-based recommendation systems

[![Sponsor](https://img.shields.io/badge/Sponsor%20on-GitHub-ff69b4?logo=github)](https://github.com/sponsors/amoney79)

---

## 🧑‍💼 Developer Profile

**👨‍💻 Author:** [Joseph Makenzi](https://github.com/amoney79)
**💼 Role:** Full-Stack Java Developer
**📧 Contact:** [mackenziejoseph396@gmail.com](mailto:mackenziejoseph396@gmail.com)
**🐦 Twitter:** [@Ma_ck_enzie_J](https://x.com/Ma_ck_enzie_J)
**🏢 Organization:** StoryLand Studios

---

## 📜 License

Licensed under the **MIT License** — feel free to use, modify, and distribute with proper credit.

---

<div align="center">

⭐ **If you love StoryLand, star this repository to support development!** ⭐ <br/> <img src="/assets/storyland_footer.png" width="70%" alt="StoryLand Footer"/>

</div>
```
