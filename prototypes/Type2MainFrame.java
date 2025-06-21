
package view;

import controller.AuthorController;
import controller.BookController;
import model.Author;
import model.Book;
import model.User;
// import util.Result; //resultun olduğu yere eklenecek

import javax.swing.*;
import java.awt.*;

public class Type2MainFrame extends JFrame {
    private final User loggedInUser;
    private final int sessionId;

    public Type2MainFrame(User user, int sessionId) {
        this.loggedInUser = user;
        this.sessionId = sessionId;

        setTitle("MyLibrary - Hoş Geldiniz, " + loggedInUser.getUsername());
        setSize(900, 700);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        JTabbedPane tabbedPane = new JTabbedPane();

      
        tabbedPane.addTab("Kitap/Yazar Ara", createSearchPanel());
        tabbedPane.addTab("Kütüphanem", createMyLibraryPanel());

        add(tabbedPane);
    }
    
  
    private JPanel createSearchPanel() {
        JPanel panel = new JPanel(new BorderLayout(10, 10));
        panel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        
       
        JSplitPane splitPane = new JSplitPane(JSplitPane.VERTICAL_SPLIT);
        
        JPanel topSearchPanel = new JPanel(new GridLayout(0, 3, 10, 10));
        JTextField bookIdField = new JTextField();
        JButton displayBookButton = new JButton("Kitap Bilgisi Getir");
        JTextField authorNameField = new JTextField();
        JButton searchAuthorButton = new JButton("Yazar Ara");
        
        topSearchPanel.add(new JLabel("Kitap ID:"));
        topSearchPanel.add(bookIdField);
        topSearchPanel.add(displayBookButton);
        topSearchPanel.add(new JLabel("Yazar Adı:"));
        topSearchPanel.add(authorNameField);
        topSearchPanel.add(searchAuthorButton);
        
      
        JTextArea resultArea = new JTextArea(15, 40);
        resultArea.setEditable(false);
        resultArea.setFont(new Font("Monospaced", Font.PLAIN, 14));
        
        splitPane.setTopComponent(topSearchPanel);
        splitPane.setBottomComponent(new JScrollPane(resultArea));
        splitPane.setDividerLocation(100);
        
        panel.add(splitPane, BorderLayout.CENTER);
        
        displayBookButton.addActionListener(e -> {
            //yazar ara fonskiyonu örnek
            JOptionPane.showMessageDialog(this, "Kitap Görüntüleme fonksiyonu buraya bağlanacak.");
        });
        
        ,,
        searchAuthorButton.addActionListener(e -> {
            String authorName = authorNameField.getText();
            if(authorName.trim().isEmpty()){
                JOptionPane.showMessageDialog(this, "Lütfen bir yazar adı girin.", "Uyarı", JOptionPane.WARNING_MESSAGE);
                return;
            }
            
            setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
            
            new SwingWorker<Result, Void>() {
                @Override
                protected Result doInBackground() throws Exception {
                    return AuthorController.searchAuthor(authorName);
                }
                
                @Override
                protected void done() {
                    try {
                        Result result = get();
                        if(result.getStatus().equals("success")){
                            Author author = (Author) result.getData();
                            String info = String.format(
                                "BULUNAN YAZAR\n------------------\nID\t: %d\nAdı\t: %s\nSoyadı\t: %s\nWebsite\t: %s",
                                author.getAuthorId(), author.getName(), author.getSurname(), author.getWebsite()
                            );
                            resultArea.setText(info);
                        } else {
                            resultArea.setText("");
                            JOptionPane.showMessageDialog(Type2MainFrame.this, result.getMessage(), "Bulunamadı", JOptionPane.INFORMATION_MESSAGE);
                        }
                    } catch (Exception ex) {
                        ex.printStackTrace();
                    } finally {
                        setCursor(Cursor.getDefaultCursor());
                    }
                }
            }.execute();
        });
        
        return panel;
    }
    

    private JPanel createMyLibraryPanel() {
        JPanel panel = new JPanel(new BorderLayout(10, 10));
        panel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        buttonPanel.add(new JButton("Favori Kitaplarımı Göster (Fonksiyon 6)"));
        buttonPanel.add(new JButton("Favori Yazarlarımı Göster (Fonksiyon 7)"));
        buttonPanel.add(new JButton("Okunmamış Kitaplarımı Göster (Fonksiyon 8)"));
        
        JTable resultsTable = new JTable();
        
        panel.add(buttonPanel, BorderLayout.NORTH);
        panel.add(new JScrollPane(resultsTable), BorderLayout.CENTER);
        
        //projenin tam bitmediği için eksik bırakıldı
        
        return panel;
    }
}
