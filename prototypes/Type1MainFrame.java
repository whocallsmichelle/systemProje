// Dosya Adı: view/Type1MainFrame.java
package view;

import controller.BookController;
import model.Book;
import model.User;
// import util.Result; //resulta direkt bağlanacak

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.ArrayList;
import java.util.Date;

public class Type1MainFrame extends JFrame {
    private final User loggedInUser;
    private final int sessionId;
    private Book bookToUpdate; // Güncellenecek kitabı tutmak için

    public Type1MainFrame(User user, int sessionId) {
        this.loggedInUser = user;
        this.sessionId = sessionId;

        setTitle("MyLibrary Yönetim Paneli - Hoş Geldiniz, " + loggedInUser.getUsername());
        setSize(950, 750);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        JTabbedPane tabbedPane = new JTabbedPane();

        tabbedPane.addTab("Kitap Görüntüle", createDisplayBookPanel());
        tabbedPane.addTab("Kitap Yönetimi", createManagementPanel());
        tabbedPane.addTab("Kütüphane Raporları", createReportPanel());
        
        add(tabbedPane);
    }

    
    private JPanel createDisplayBookPanel() {
         JPanel panel = new JPanel(new BorderLayout(10, 10));
        panel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        

        JPanel topPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        JTextField bookIdField = new JTextField(10);
        JButton displayButton = new JButton("Kitap Bilgilerini Getir");
        topPanel.add(new JLabel("Kitap ID:"));
        topPanel.add(bookIdField);
        topPanel.add(displayButton);
        
        
        JTextArea bookInfoArea = new JTextArea(10, 40);
        bookInfoArea.setEditable(false);
        bookInfoArea.setFont(new Font("Monospaced", Font.PLAIN, 14));
        
        panel.add(topPanel, BorderLayout.NORTH);
        panel.add(new JScrollPane(bookInfoArea), BorderLayout.CENTER);
        
    
        displayButton.addActionListener(e -> {
            try {
                int bookId = Integer.parseInt(bookIdField.getText());
               
                new SwingWorker<Result, Void>() {
                    @Override
                    protected Result doInBackground() {
                        return BookController.displayBook(sessionId, bookId);
                    }
                    @Override
                    protected void done() {
                        try {
                            Result result = get();
                            if(result.getStatus().equals("success")){
                                Book book = (Book) result.getData();
                                String info = String.format(
                                    "ID\t: %d\nBaşlık\t: %s\n\nYazar\t: %s %s\nYıl\t: %d\nSayfa\t: %d\n\nHakkında:\n%s",
                                    book.getBookID(), book.getTitle(), book.getAuthorName(), book.getAuthorSurname(),
                                    book.getYear(), book.getNumberOfPages(), book.getAbout()
                                );
                                bookInfoArea.setText(info);
                            } else {
                                JOptionPane.showMessageDialog(Type1MainFrame.this, result.getMessage(), "Hata", JOptionPane.ERROR_MESSAGE);
                            }
                        } catch (Exception ex) {
                            ex.printStackTrace();
                        }
                    }
                }.execute();
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(Type1MainFrame.this, "Lütfen geçerli bir sayısal ID girin.", "Giriş Hatası", JOptionPane.WARNING_MESSAGE);
            }
        });
        
        return panel;
    }

    private JPanel createManagementPanel() {
        JPanel panel = new JPanel(new BorderLayout());
        JTabbedPane managementTabs = new JTabbedPane();

        
        managementTabs.addTab("Kitap Ekle", createAddBookPanel());
        managementTabs.addTab("Kitap Sil", createDeleteBookPanel());
        managementTabs.addTab("Kitap Güncelle", createUpdateBookPanel());

        panel.add(managementTabs, BorderLayout.CENTER);
        return panel;
    }

  
    private JPanel createAddBookPanel() {
       JPanel panel = new JPanel(new GridLayout(0, 2, 10, 10));
        panel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        
    
        JTextField titleField = new JTextField();
        JTextField authorIdField = new JTextField();
        JTextField yearField = new JTextField();
        JTextField pagesField = new JTextField();
        JTextArea aboutArea = new JTextArea(5, 20);
        JButton addButton = new JButton("Kitabı Ekle");

        
        panel.add(new JLabel("Başlık:"));
        panel.add(titleField);
        panel.add(new JLabel("Yazar ID:"));
        panel.add(authorIdField);
        panel.add(new JLabel("Yıl:"));
        panel.add(yearField);
        panel.add(new JLabel("Sayfa Sayısı:"));
        panel.add(pagesField);
        panel.add(new JLabel("Hakkında:"));
        panel.add(new JScrollPane(aboutArea));
        panel.add(new JLabel()); 
        panel.add(addButton);
        

        addButton.addActionListener(e -> {
            try {
                Book newBook = new Book();
                newBook.setTitle(titleField.getText());
                newBook.setAuthorID(Integer.parseInt(authorIdField.getText()));
                newBook.setYear(Integer.parseInt(yearField.getText()));
                newBook.setNumberOfPages(Integer.parseInt(pagesField.getText()));
                newBook.setAbout(aboutArea.getText());
                newBook.setCover("default.jpg"); // Şimdilik varsayılan
                newBook.setReleaseDate(new Date()); // Şimdilik varsayılan

               
                new SwingWorker<Result, Void>() {
                    @Override
                    protected Result doInBackground() {
                        return BookController.addBook(sessionId, newBook);
                    }
                    @Override
                    protected void done() {
                       try {
                           Result result = get();
                           JOptionPane.showMessageDialog(Type1MainFrame.this, result.getMessage());
                           if(result.getStatus().equals("success")){
                            
                               titleField.setText("");
                               authorIdField.setText("");
                               yearField.setText("");
                               pagesField.setText("");
                               aboutArea.setText("");
                           }
                       } catch (Exception ex) {
                           ex.printStackTrace();
                       }
                    }
                }.execute();

            } catch (NumberFormatException ex){
                JOptionPane.showMessageDialog(Type1MainFrame.this, "ID, Yıl ve Sayfa Sayısı sayısal olmalı.", "Giriş Hatası", JOptionPane.WARNING_MESSAGE);
            }
        });
        
        return panel;
    }
}

    private JPanel createDeleteBookPanel() {
        JPanel panel = new JPanel(new FlowLayout(FlowLayout.LEFT, 10, 10));
        panel.setBorder(BorderFactory.createTitledBorder("Kitap Sil (Fonksiyon 2)"));

        JTextField bookIdField = new JTextField(15);
        JButton deleteButton = new JButton("Bu ID'li Kitabı Sil");

        panel.add(new JLabel("Silinecek Kitap ID:"));
        panel.add(bookIdField);
        panel.add(deleteButton);

        deleteButton.addActionListener(e -> {
            String bookIdText = bookIdField.getText();
            int confirmation = JOptionPane.showConfirmDialog(this,
                    "ID: " + bookIdText + " olan kitabı silmek istediğinizden emin misiniz?",
                    "Silme Onayı", JOptionPane.YES_NO_OPTION);

            if (confirmation == JOptionPane.YES_OPTION) {
                try {
                    int bookId = Integer.parseInt(bookIdText);
                    new SwingWorker<Result, Void>() {
                        @Override protected Result doInBackground() { return BookController.deleteBook(sessionId, bookId); }
                        @Override protected void done() {
                            try {
                                Result result = get();
                                JOptionPane.showMessageDialog(Type1MainFrame.this, result.getMessage());
                                if (result.getStatus().equals("success")) bookIdField.setText("");
                            } catch (Exception ex) { ex.printStackTrace(); }
                        }
                    }.execute();
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(this, "Lütfen geçerli bir ID girin.", "Hata", JOptionPane.ERROR_MESSAGE);
                }
            }
        });
        return panel;
    }


    private JPanel createUpdateBookPanel() {
        JPanel panel = new JPanel(new BorderLayout(10, 10));
        panel.setBorder(BorderFactory.createTitledBorder("Kitap Güncelle (Fonksiyon 5)"));

       
        JPanel fetchPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        JTextField fetchIdField = new JTextField(10);
        JButton fetchButton = new JButton("Düzenlemek İçin Getir");
        fetchPanel.add(new JLabel("Kitap ID:"));
        fetchPanel.add(fetchIdField);
        fetchPanel.add(fetchButton);

        
        JPanel formPanel = new JPanel(new GridLayout(0, 2, 5, 5));
        JTextField titleField = new JTextField();
        JTextField yearField = new JTextField();
        JTextField pagesField = new JTextField();
        JButton updateButton = new JButton("Değişiklikleri Kaydet");
        updateButton.setEnabled(false);

        formPanel.add(new JLabel("Yeni Başlık:"));
        formPanel.add(titleField);
        formPanel.add(new JLabel("Yeni Yıl:"));
        formPanel.add(yearField);
        formPanel.add(new JLabel("Yeni Sayfa Sayısı:"));
        formPanel.add(pagesField);
        formPanel.add(new JLabel());
        formPanel.add(updateButton);

        panel.add(fetchPanel, BorderLayout.NORTH);
        panel.add(formPanel, BorderLayout.CENTER);

        
        fetchButton.addActionListener(e -> {
            try {
                int bookId = Integer.parseInt(fetchIdField.getText());
                new SwingWorker<Result, Void>() {
                    @Override protected Result doInBackground() { return BookController.displayBook(sessionId, bookId); }
                    @Override protected void done() {
                        try {
                            Result result = get();
                            if (result.getStatus().equals("success")) {
                                bookToUpdate = (Book) result.getData();
                                titleField.setText(bookToUpdate.getTitle());
                                yearField.setText(String.valueOf(bookToUpdate.getYear()));
                                pagesField.setText(String.valueOf(bookToUpdate.getNumberOfPages()));
                                updateButton.setEnabled(true);
                            } else {
                                JOptionPane.showMessageDialog(Type1MainFrame.this, result.getMessage(), "Hata", JOptionPane.ERROR_MESSAGE);
                            }
                        } catch (Exception ex) { ex.printStackTrace(); }
                    }
                }.execute();
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(this, "Lütfen geçerli bir ID girin.", "Hata", JOptionPane.ERROR_MESSAGE);
            }
        });

        
        updateButton.addActionListener(e -> {
            if (bookToUpdate == null) return;

            
            bookToUpdate.setTitle(titleField.getText());
            bookToUpdate.setYear(Integer.parseInt(yearField.getText()));
            bookToUpdate.setNumberOfPages(Integer.parseInt(pagesField.getText()));

            new SwingWorker<Result, Void>() {
                @Override protected Result doInBackground() { return BookController.updateBook(sessionId, bookToUpdate); }
                @Override protected void done() {
                    try {
                        Result result = get();
                        JOptionPane.showMessageDialog(Type1MainFrame.this, result.getMessage());
                        if(result.getStatus().equals("success")){

                            
                            titleField.setText("");
                            yearField.setText("");
                            pagesField.setText("");
                            fetchIdField.setText("");
                            bookToUpdate = null;
                            updateButton.setEnabled(false);
                        }
                    } catch (Exception ex) { ex.printStackTrace(); }
                }
            }.execute();
        });

        return panel;
    }

    
    private JPanel createReportPanel() {
        JPanel panel = new JPanel(new BorderLayout(10, 10));
        panel.setBorder(BorderFactory.createTitledBorder("Listeleme İşlemleri"));

        
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        JButton favBooksButton = new JButton("Favori Kitaplar (F6)");
        JButton favAuthorsButton = new JButton("Favori Yazarlar (F7)");
        JButton unreadBooksButton = new JButton("Okunmamış Kitaplar (F8)");
        buttonPanel.add(favBooksButton);
        buttonPanel.add(favAuthorsButton);
        buttonPanel.add(unreadBooksButton);

        
        DefaultTableModel tableModel = new DefaultTableModel();
        JTable resultsTable = new JTable(tableModel);

        panel.add(buttonPanel, BorderLayout.NORTH);
        panel.add(new JScrollPane(resultsTable), BorderLayout.CENTER);

        
        favBooksButton.addActionListener(e -> {
            
            tableModel.setColumnIdentifiers(new String[]{"ID", "Başlık", "Yazar", "Puan"});
           
            new SwingWorker<Result, Void>() {
                @Override protected Result doInBackground() { return BookController.getFavoriteBooks(sessionId); }
                @Override protected void done() {
                    try {
                        Result result = get();
                        tableModel.setRowCount(0); // Tabloyu temizle
                        if(result.getStatus().equals("success")) {
                            ArrayList<Book> books = (ArrayList<Book>) result.getData();
                            for(Book b : books) {
                                tableModel.addRow(new Object[]{b.getBookID(), b.getTitle(), b.getAuthorName(), b.getRating()});
                            }
                        }
                    } catch (Exception ex) { ex.printStackTrace(); }
                }
            }.execute();
        });
        
        // Diğer butonlar da (favAuthorsButton, unreadBooksButton) benzer şekilde SwingWorker kullanarak kendi controller metotlarını çağıracaklar.

        return panel;
    }
}
