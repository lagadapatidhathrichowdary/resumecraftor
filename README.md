# ResumeCraftor

🚀 **ResumeCraftor** is a Java-based **Resume Builder GUI** application built using **Swing**.  
It allows users to create, save, and download resumes in **HTML format** or store them in a **MySQL database**.

## 📌 Features
- 📝 **User-friendly GUI** for entering resume details.
- 💾 **Save resume to MySQL database**.
- 📄 **Download resume as an HTML file**.
- 🎨 **Attractive UI with a light blue theme**.

## 🛠️ Technologies Used
- **Java (Swing)**
- **JDBC (MySQL Database)**
- **Visual Studio Code** (for development)
- **Git & GitHub** (for version control)

## 📂 Project Structure
ResumeCraftor/ │── ResumeCraftor.java # Main class (starts the GUI) │── ResumeCraftorGUI.java # GUI implementation │── resume.html # Sample exported resume (Generated) │── README.md # Project documentation (This file) │── .gitignore # Files to exclude from Git


## 🚀 How to Run
### **1️⃣ Clone the Repository**
```sh
git clone https://github.com/your-username/ResumeCraftor.git
cd ResumeCraftor

javac ResumeCraftorGUI.java ResumeCraftor.java
java ResumeCraftor

 Setup MySQL Database (Optional)
Create a database:
sql
Copy
Edit
CREATE DATABASE resume;
Create a table:
sql
Copy
Edit
CREATE TABLE resumes (
    id INT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(255),
    job_title VARCHAR(255),
    phone VARCHAR(20),
    email VARCHAR(255),
    linkedin VARCHAR(255),
    summary TEXT,
    skills TEXT,
    experience TEXT,
    education TEXT,
    languages TEXT
);
Update the database credentials in ResumeCraftorGUI.java.
📜 License
This project is open-source under the MIT License.
