import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

    public class ResumeCraftorGUI {
        private JFrame frame;
        private JTextField nameField, jobTitleField, phoneField, emailField, linkedinField;
        private JTextArea summaryArea, skillsArea, experienceArea, educationArea, languagesArea;

        public void createAndShowGUI() {
            frame = new JFrame("ResumeCraftor");
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.setSize(600, 900);
            frame.setLayout(new BorderLayout());

            JPanel panel = new JPanel();
            panel.setLayout(new GridLayout(12, 1));
            panel.setBackground(new Color(240, 248, 255)); // Light blue background

            // Welcome Message
            JPanel welcomePanel = new JPanel(new GridLayout(3, 1));
            welcomePanel.setBackground(new Color(240, 248, 255)); // Light blue background
            JLabel appNameLabel = new JLabel("ResumeCraftor", SwingConstants.CENTER);
            appNameLabel.setFont(new Font("Arial", Font.BOLD, 24));
            appNameLabel.setForeground(new Color(0, 0, 128)); // Navy text color
            welcomePanel.add(appNameLabel);

            JLabel taglineLabel = new JLabel("Online Resume Builder", SwingConstants.CENTER);
            taglineLabel.setFont(new Font("Arial", Font.ITALIC, 18));
            taglineLabel.setForeground(new Color(0, 0, 128)); // Navy text color
            welcomePanel.add(taglineLabel);

            JLabel descriptionLabel = new JLabel("<html>Only 2% of resumes make it past the first round. Be in the top 2%<br>Use professional field-tested resume templates that follow the exact ‘resume rules’ employers look for. Easy to use and done within minutes - try now for free!</html>", SwingConstants.CENTER);
            descriptionLabel.setFont(new Font("Arial", Font.PLAIN, 14));
            descriptionLabel.setForeground(new Color(0, 0, 128)); // Navy text color
            welcomePanel.add(descriptionLabel);

            frame.add(welcomePanel, BorderLayout.NORTH);

            // Name
            panel.add(createLabeledField("Name:", nameField = new JTextField()));

            // Job Title
            panel.add(createLabeledField("Job Title:", jobTitleField = new JTextField()));

            // Phone
            panel.add(createLabeledField("Phone:", phoneField = new JTextField()));

            // Email
            panel.add(createLabeledField("Email:", emailField = new JTextField()));

            // LinkedIn
            panel.add(createLabeledField("LinkedIn:", linkedinField = new JTextField()));

            // Professional Summary
            panel.add(createLabeledArea("Professional Summary:", summaryArea = new JTextArea(3, 20)));

            // Skills
            panel.add(createLabeledArea("Skills:", skillsArea = new JTextArea(3, 20)));

            // Experience
            panel.add(createLabeledArea("Experience:", experienceArea = new JTextArea(3, 20)));

            // Education
            panel.add(createLabeledArea("Education:", educationArea = new JTextArea(3, 20)));

            // Languages
            panel.add(createLabeledArea("Languages:", languagesArea = new JTextArea(3, 20)));

            // Buttons Panel
            JPanel buttonsPanel = new JPanel();
            buttonsPanel.setBackground(new Color(240, 248, 255)); // Match panel background

            // Save Button
            JButton saveButton = new JButton("Save Resume");
            saveButton.setBackground(new Color(173, 216, 230)); // Light blue button
            saveButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    saveResume();
                }
            });
            buttonsPanel.add(saveButton);

            // Download Button
            JButton downloadButton = new JButton("Download Resume");
            downloadButton.setBackground(new Color(173, 216, 230)); // Light blue button
            downloadButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    downloadResume();
                }
            });
            buttonsPanel.add(downloadButton);

            panel.add(buttonsPanel);

            frame.add(panel, BorderLayout.CENTER);
            frame.setVisible(true);
        }

        private JPanel createLabeledField(String labelText, JTextField textField) {
            JPanel panel = new JPanel(new BorderLayout());
            panel.setBackground(new Color(240, 248, 255)); // Light blue background
            JLabel label = new JLabel(labelText);
            label.setForeground(new Color(0, 0, 128)); // Navy text color
            panel.add(label, BorderLayout.WEST);
            panel.add(textField, BorderLayout.CENTER);
            return panel;
        }

        private JPanel createLabeledArea(String labelText, JTextArea textArea) {
            JPanel panel = new JPanel(new BorderLayout());
            panel.setBackground(new Color(240, 248, 255)); // Light blue background
            JLabel label = new JLabel(labelText);
            label.setForeground(new Color(0, 0, 128)); // Navy text color
            panel.add(label, BorderLayout.NORTH);
            panel.add(new JScrollPane(textArea), BorderLayout.CENTER);
            return panel;
        }

        private void saveResume() {
            String name = nameField.getText();
            String jobTitle = jobTitleField.getText();
            String phone = phoneField.getText();
            String email = emailField.getText();
            String linkedin = linkedinField.getText();
            String summary = summaryArea.getText();
            String skills = skillsArea.getText();
            String experience = experienceArea.getText();
            String education = educationArea.getText();
            String languages = languagesArea.getText();

            // Database connection details
            String url = "jdbc:mysql://localhost:3306/resume";
            String user = "root";
            String password = "Reshma@10";

            String query = "INSERT INTO resumes (name, job_title, phone, email, linkedin, summary, skills, experience, education, languages) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

            try (Connection conn = DriverManager.getConnection(url, user, password);
                 PreparedStatement stmt = conn.prepareStatement(query)) {

                stmt.setString(1, name);
                stmt.setString(2, jobTitle);
                stmt.setString(3, phone);
                stmt.setString(4, email);
                stmt.setString(5, linkedin);
                stmt.setString(6, summary);
                stmt.setString(7, skills);
                stmt.setString(8, experience);
                stmt.setString(9, education);
                stmt.setString(10, languages);

                stmt.executeUpdate();
                JOptionPane.showMessageDialog(frame, "Resume saved to database successfully!");

            } catch (SQLException e) {
                e.printStackTrace();
                JOptionPane.showMessageDialog(frame, "Error saving resume to database: " + e.getMessage());
            }
        }

        private void downloadResume() {
            String name = nameField.getText();
            String jobTitle = jobTitleField.getText();
            String phone = phoneField.getText();
            String email = emailField.getText();
            String linkedin = linkedinField.getText();
            String summary = summaryArea.getText();
            String skills = skillsArea.getText();
            String experience = experienceArea.getText();
            String education = educationArea.getText();
            String languages = languagesArea.getText();

            String resumeContent = String.format(
                    "<html><body>" +
                            "<h1>%s</h1>" +
                            "<h2>%s</h2>" +
                            "<p><strong>Phone:</strong> %s</p>" +
                            "<p><strong>Email:</strong> %s</p>" +
                            "<p><strong>LinkedIn:</strong> %s</p>" +
                            "<h3>Professional Summary</h3>" +
                            "<p>%s</p>" +
                            "<h3>Skills</h3>" +
                            "<p>%s</p>" +
                            "<h3>Experience</h3>" +
                            "<p>%s</p>" +
                            "<h3>Education</h3>" +
                            "<p>%s</p>" +
                            "<h3>Languages</h3>" +
                            "<p>%s</p>" +
                            "</body></html>",
                    name, jobTitle, phone, email, linkedin, summary, skills, experience, education, languages
            );

            try (BufferedWriter writer = new BufferedWriter(new FileWriter("resume.html"))) {
                writer.write(resumeContent);
                JOptionPane.showMessageDialog(frame, "Resume downloaded successfully!");
            } catch (IOException e) {
                JOptionPane.showMessageDialog(frame, "Error downloading resume: " + e.getMessage());
            }
        }
    }