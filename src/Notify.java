
import java.awt.Desktop;
import java.sql.Connection;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.table.DefaultTableModel;
import org.apache.commons.io.FilenameUtils;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author Aditya Pandey
 */
public class Notify extends javax.swing.JFrame {

    private boolean go_back = true;
    private String filepath, filename, extension1, new_filename;

    private int i = 0;
    Database db = new Database();
    path_file paths = new path_file();
    private Connection con = db.create_connection(true);
    private ResultSet rs = null;
    private LocalDateTime now = null;
    DefaultTableModel model_fullscreen, model_table;
    private int mouseX, mouseY;
    
    String time_fromtable;
    String date_fromtable;
    String file_name = "";

    public Notify(int k) {
        initComponents();

        ScrollPaneA.getVerticalScrollBar().setUnitIncrement(16);
        fullscreen_ScrollPaneA.getVerticalScrollBar().setUnitIncrement(16);

        model_fullscreen = (DefaultTableModel) fullscreen_tableA.getModel();
        model_table = (DefaultTableModel) TableA.getModel();
    }

    private void viewall() {
        try {
            Statement stmt = con.createStatement();
            rs = stmt.executeQuery("Select * from notify");
            String current_date, current_time, message, status;
            boolean true_false;
            model_table.setRowCount(0);
            model_fullscreen.setRowCount(0);
            i = 1;
            while (rs.next()) {
                current_date = rs.getString("Date");
                current_time = rs.getString("Time");
                message = rs.getString("Message");
                status = rs.getString("status");

                if (status.equals("0")) {
                    status = "Unread";
                } else if (status.equals("1")) {
                    status = "Read";
                }
                
                model_table.addRow(new Object[]{
                    i, current_date, current_time, message, status
                });
                model_fullscreen.addRow(new Object[]{
                    i, current_date, current_time, message, status
                });
                i++;
            };
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    private void get_filename() {
        time_fromtable = TableA.getValueAt(TableA.getSelectedRow(), 2).toString();
        date_fromtable = TableA.getValueAt(TableA.getSelectedRow(), 1).toString();

        //getting filename
        try {
            Statement stmt = con.createStatement();
            rs = stmt.executeQuery("Select file_name from notify where date='" + date_fromtable + "'and time='" + time_fromtable + "'");

            while (rs.next()) {
                file_name = rs.getString("file_name");
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, e);
        }
        
    }

    //----------------------------------FOR PICKING & MOVING IMAGES -start
    private void move(String source_loc) {
        try {

            Path temp = Files.copy(Paths.get(source_loc), Paths.get(paths.notification_files + "/" + new_filename), StandardCopyOption.REPLACE_EXISTING);
            if (temp != null) {
                System.out.println("File renamed and moved successfully");
            } else {
                System.out.println("Failed to move the file");
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
        }
    }
    // >>>called in insert/add buttons
    //----------------------------------FOR PICKING & MOVING IMAGES -end

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        move_panel = new javax.swing.JPanel();
        bg = new javax.swing.JPanel();
        exit_icon = new javax.swing.JLabel();
        minimize = new javax.swing.JLabel();
        side_pane = new javax.swing.JPanel();
        title_label = new javax.swing.JLabel();
        title_icon = new javax.swing.JLabel();
        back_icon = new javax.swing.JLabel();
        title_underline = new javax.swing.JSeparator();
        menuA = new javax.swing.JPanel();
        menuA_icon = new javax.swing.JLabel();
        menuA_label = new javax.swing.JLabel();
        menuB = new javax.swing.JPanel();
        menuB_icon = new javax.swing.JLabel();
        menuB_label = new javax.swing.JLabel();
        panelA = new javax.swing.JPanel();
        ScrollPaneA = new javax.swing.JScrollPane();
        TableA = new javax.swing.JTable();
        Separator = new javax.swing.JSeparator();
        panelB = new javax.swing.JPanel();
        message_label = new javax.swing.JLabel();
        expiry_date_label = new javax.swing.JLabel();
        message_field = new javax.swing.JTextField();
        add_message_button = new javax.swing.JPanel();
        add_button_label = new javax.swing.JLabel();
        select_file_buttonA = new javax.swing.JPanel();
        select_file_labelA = new javax.swing.JLabel();
        army_number_underlineA = new javax.swing.JSeparator();
        fullscreen_buttonA = new javax.swing.JPanel();
        fullscreen_labelD = new javax.swing.JLabel();
        refresh_buttonA = new javax.swing.JPanel();
        refresh_labelA = new javax.swing.JLabel();
        remove_buttonA = new javax.swing.JPanel();
        remove_labelA = new javax.swing.JLabel();
        open_file_buttonA = new javax.swing.JPanel();
        open_file_labelA = new javax.swing.JLabel();
        mark_read_button = new javax.swing.JLabel();
        fullscreen_panelA = new javax.swing.JPanel();
        exit_fullscreen_buttonA = new javax.swing.JPanel();
        exit_fullscreen_labelA = new javax.swing.JLabel();
        fullscreen_ScrollPaneA = new javax.swing.JScrollPane();
        fullscreen_tableA = new javax.swing.JTable();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setLocation(new java.awt.Point(0, 0));
        setMaximizedBounds(new java.awt.Rectangle(0, 0, 1280, 720));
        setMaximumSize(new java.awt.Dimension(1280, 720));
        setMinimumSize(new java.awt.Dimension(1280, 720));
        setUndecorated(true);
        setResizable(false);
        setSize(new java.awt.Dimension(1280, 720));
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowOpened(java.awt.event.WindowEvent evt) {
                formWindowOpened(evt);
            }
        });
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        move_panel.setFocusable(false);
        move_panel.setOpaque(false);
        move_panel.addMouseMotionListener(new java.awt.event.MouseMotionAdapter() {
            public void mouseDragged(java.awt.event.MouseEvent evt) {
                move_panelMouseDragged(evt);
            }
        });
        move_panel.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                move_panelMousePressed(evt);
            }
        });

        javax.swing.GroupLayout move_panelLayout = new javax.swing.GroupLayout(move_panel);
        move_panel.setLayout(move_panelLayout);
        move_panelLayout.setHorizontalGroup(
            move_panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 1280, Short.MAX_VALUE)
        );
        move_panelLayout.setVerticalGroup(
            move_panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 30, Short.MAX_VALUE)
        );

        getContentPane().add(move_panel, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 1280, 30));

        bg.setBackground(new java.awt.Color(255, 255, 255));
        bg.setMaximumSize(new java.awt.Dimension(1280, 720));
        bg.setMinimumSize(new java.awt.Dimension(1280, 720));
        bg.setPreferredSize(new java.awt.Dimension(1280, 720));
        bg.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        exit_icon.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/PNGS/remove_purple.png"))); // NOI18N
        exit_icon.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                exit_iconMouseClicked(evt);
            }
        });
        bg.add(exit_icon, new org.netbeans.lib.awtextra.AbsoluteConstraints(1215, 30, -1, -1));

        minimize.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/PNGS/minimize-purple.png"))); // NOI18N
        minimize.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                minimizeMouseClicked(evt);
            }
        });
        bg.add(minimize, new org.netbeans.lib.awtextra.AbsoluteConstraints(1170, 30, -1, 40));

        side_pane.setBackground(new java.awt.Color(54, 33, 89));
        side_pane.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        title_label.setFont(new java.awt.Font("Montserrat", 0, 28)); // NOI18N
        title_label.setForeground(new java.awt.Color(255, 255, 255));
        title_label.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        title_label.setText("Notify");
        side_pane.add(title_label, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 130, 100, 50));

        title_icon.setBackground(new java.awt.Color(255, 255, 255));
        title_icon.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        title_icon.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/PNGS/ringing small.png"))); // NOI18N
        side_pane.add(title_icon, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 110, 70, 80));

        back_icon.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/PNGS/left-arrow.png"))); // NOI18N
        back_icon.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                back_iconMouseClicked(evt);
            }
        });
        side_pane.add(back_icon, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 30, -1, -1));
        side_pane.add(title_underline, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 190, 260, 30));

        menuA.setBackground(new java.awt.Color(85, 65, 118));
        menuA.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                menuAFocusGained(evt);
            }
        });
        menuA.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                menuAMouseClicked(evt);
            }
        });
        menuA.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        menuA_icon.setForeground(new java.awt.Color(255, 255, 255));
        menuA_icon.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        menuA_icon.setText("A");
        menuA.add(menuA_icon, new org.netbeans.lib.awtextra.AbsoluteConstraints(27, 1, -1, 60));

        menuA_label.setFont(new java.awt.Font("Roboto", 1, 18)); // NOI18N
        menuA_label.setForeground(new java.awt.Color(255, 255, 255));
        menuA_label.setText("Notifications");
        menuA.add(menuA_label, new org.netbeans.lib.awtextra.AbsoluteConstraints(68, 1, -1, 60));

        side_pane.add(menuA, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 240, 310, 60));

        menuB.setBackground(new java.awt.Color(64, 43, 100));
        menuB.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                menuBMouseClicked(evt);
            }
        });
        menuB.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        menuB_icon.setForeground(new java.awt.Color(255, 255, 255));
        menuB_icon.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        menuB_icon.setText("B");
        menuB.add(menuB_icon, new org.netbeans.lib.awtextra.AbsoluteConstraints(27, 0, -1, 60));

        menuB_label.setFont(new java.awt.Font("Roboto", 1, 18)); // NOI18N
        menuB_label.setForeground(new java.awt.Color(255, 255, 255));
        menuB_label.setText("Add New");
        menuB.add(menuB_label, new org.netbeans.lib.awtextra.AbsoluteConstraints(67, 0, 94, 60));

        side_pane.add(menuB, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 300, 320, 60));

        bg.add(side_pane, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 320, 740));

        panelA.setBackground(new java.awt.Color(255, 255, 255));
        panelA.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        ScrollPaneA.setVerticalScrollBarPolicy(javax.swing.ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);

        TableA.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        TableA.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "S. No.", "Date", "Time", "Message", "Status"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        TableA.setFocusable(false);
        TableA.setGridColor(new java.awt.Color(255, 255, 255));
        TableA.setMaximumSize(new java.awt.Dimension(300, 80));
        TableA.setMinimumSize(new java.awt.Dimension(300, 80));
        TableA.setRowHeight(30);
        TableA.setSelectionBackground(new java.awt.Color(54, 33, 89));
        ScrollPaneA.setViewportView(TableA);
        if (TableA.getColumnModel().getColumnCount() > 0) {
            TableA.getColumnModel().getColumn(0).setMinWidth(50);
            TableA.getColumnModel().getColumn(0).setPreferredWidth(50);
            TableA.getColumnModel().getColumn(0).setMaxWidth(50);
            TableA.getColumnModel().getColumn(1).setMinWidth(150);
            TableA.getColumnModel().getColumn(1).setPreferredWidth(150);
            TableA.getColumnModel().getColumn(1).setMaxWidth(150);
            TableA.getColumnModel().getColumn(2).setMinWidth(150);
            TableA.getColumnModel().getColumn(2).setPreferredWidth(150);
            TableA.getColumnModel().getColumn(2).setMaxWidth(150);
            TableA.getColumnModel().getColumn(4).setMinWidth(80);
            TableA.getColumnModel().getColumn(4).setPreferredWidth(80);
            TableA.getColumnModel().getColumn(4).setMaxWidth(80);
        }

        panelA.add(ScrollPaneA, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 50, 780, 420));

        bg.add(panelA, new org.netbeans.lib.awtextra.AbsoluteConstraints(340, 192, 910, 480));

        Separator.setForeground(new java.awt.Color(54, 33, 89));
        Separator.setPreferredSize(new java.awt.Dimension(50, 20));
        bg.add(Separator, new org.netbeans.lib.awtextra.AbsoluteConstraints(360, 190, 890, 10));

        panelB.setBackground(new java.awt.Color(255, 255, 255));
        panelB.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        message_label.setFont(new java.awt.Font("Roboto Bk", 0, 24)); // NOI18N
        message_label.setText("Message");
        panelB.add(message_label, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 50, -1, 40));

        expiry_date_label.setFont(new java.awt.Font("Roboto Bk", 0, 24)); // NOI18N
        expiry_date_label.setText("Add Document");
        panelB.add(expiry_date_label, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 120, -1, 40));

        message_field.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        message_field.setBorder(null);
        message_field.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                message_fieldActionPerformed(evt);
            }
        });
        panelB.add(message_field, new org.netbeans.lib.awtextra.AbsoluteConstraints(240, 50, 560, 40));

        add_message_button.setMaximumSize(new java.awt.Dimension(95, 25));
        add_message_button.setMinimumSize(new java.awt.Dimension(95, 25));
        add_message_button.setPreferredSize(new java.awt.Dimension(95, 25));
        add_message_button.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                add_message_buttonMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                add_message_buttonMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                add_message_buttonMouseExited(evt);
            }
        });
        add_message_button.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        add_button_label.setFont(new java.awt.Font("Roboto", 0, 14)); // NOI18N
        add_button_label.setForeground(new java.awt.Color(54, 33, 89));
        add_button_label.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        add_button_label.setText("ADD");
        add_message_button.add(add_button_label, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 0, 100, 50));

        panelB.add(add_message_button, new org.netbeans.lib.awtextra.AbsoluteConstraints(240, 220, 180, 50));

        select_file_buttonA.setMaximumSize(new java.awt.Dimension(200, 200));
        select_file_buttonA.setMinimumSize(new java.awt.Dimension(200, 200));
        select_file_buttonA.setPreferredSize(new java.awt.Dimension(200, 200));
        select_file_buttonA.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                select_file_buttonAMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                select_file_buttonAMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                select_file_buttonAMouseExited(evt);
            }
        });
        select_file_buttonA.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        select_file_labelA.setBackground(new java.awt.Color(54, 33, 89));
        select_file_labelA.setFont(new java.awt.Font("Roboto", 0, 18)); // NOI18N
        select_file_labelA.setForeground(new java.awt.Color(54, 33, 89));
        select_file_labelA.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        select_file_labelA.setText("Select File");
        select_file_buttonA.add(select_file_labelA, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 0, 270, 40));

        panelB.add(select_file_buttonA, new org.netbeans.lib.awtextra.AbsoluteConstraints(240, 120, 330, 40));

        army_number_underlineA.setForeground(new java.awt.Color(54, 33, 89));
        panelB.add(army_number_underlineA, new org.netbeans.lib.awtextra.AbsoluteConstraints(240, 90, 560, 20));

        bg.add(panelB, new org.netbeans.lib.awtextra.AbsoluteConstraints(340, 192, 910, 480));

        fullscreen_buttonA.setMaximumSize(new java.awt.Dimension(200, 200));
        fullscreen_buttonA.setMinimumSize(new java.awt.Dimension(200, 200));
        fullscreen_buttonA.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                fullscreen_buttonAMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                fullscreen_buttonAMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                fullscreen_buttonAMouseExited(evt);
            }
        });
        fullscreen_buttonA.setLayout(null);

        fullscreen_labelD.setBackground(new java.awt.Color(54, 33, 89));
        fullscreen_labelD.setFont(new java.awt.Font("Roboto Bk", 0, 14)); // NOI18N
        fullscreen_labelD.setForeground(new java.awt.Color(54, 33, 89));
        fullscreen_labelD.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        fullscreen_labelD.setText("FULLSCREEN");
        fullscreen_buttonA.add(fullscreen_labelD);
        fullscreen_labelD.setBounds(30, 0, 120, 50);

        bg.add(fullscreen_buttonA, new org.netbeans.lib.awtextra.AbsoluteConstraints(1070, 120, 180, 50));

        refresh_buttonA.setForeground(new java.awt.Color(240, 240, 240));
        refresh_buttonA.setMaximumSize(new java.awt.Dimension(200, 200));
        refresh_buttonA.setMinimumSize(new java.awt.Dimension(200, 200));
        refresh_buttonA.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                refresh_buttonAMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                refresh_buttonAMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                refresh_buttonAMouseExited(evt);
            }
        });
        refresh_buttonA.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        refresh_labelA.setBackground(new java.awt.Color(54, 33, 89));
        refresh_labelA.setFont(new java.awt.Font("Roboto Bk", 0, 14)); // NOI18N
        refresh_labelA.setForeground(new java.awt.Color(54, 33, 89));
        refresh_labelA.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        refresh_labelA.setText("REFRESH");
        refresh_buttonA.add(refresh_labelA, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 0, 80, 50));

        bg.add(refresh_buttonA, new org.netbeans.lib.awtextra.AbsoluteConstraints(870, 120, 180, 50));

        remove_buttonA.setForeground(new java.awt.Color(240, 240, 240));
        remove_buttonA.setMaximumSize(new java.awt.Dimension(200, 200));
        remove_buttonA.setMinimumSize(new java.awt.Dimension(200, 200));
        remove_buttonA.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                remove_buttonAMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                remove_buttonAMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                remove_buttonAMouseExited(evt);
            }
        });
        remove_buttonA.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        remove_labelA.setBackground(new java.awt.Color(54, 33, 89));
        remove_labelA.setFont(new java.awt.Font("Roboto Bk", 0, 14)); // NOI18N
        remove_labelA.setForeground(new java.awt.Color(54, 33, 89));
        remove_labelA.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        remove_labelA.setText("REMOVE");
        remove_buttonA.add(remove_labelA, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 0, 80, 50));

        bg.add(remove_buttonA, new org.netbeans.lib.awtextra.AbsoluteConstraints(470, 120, 180, 50));

        open_file_buttonA.setForeground(new java.awt.Color(240, 240, 240));
        open_file_buttonA.setMaximumSize(new java.awt.Dimension(200, 200));
        open_file_buttonA.setMinimumSize(new java.awt.Dimension(200, 200));
        open_file_buttonA.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                open_file_buttonAMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                open_file_buttonAMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                open_file_buttonAMouseExited(evt);
            }
        });
        open_file_buttonA.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        open_file_labelA.setBackground(new java.awt.Color(54, 33, 89));
        open_file_labelA.setFont(new java.awt.Font("Roboto Bk", 0, 14)); // NOI18N
        open_file_labelA.setForeground(new java.awt.Color(54, 33, 89));
        open_file_labelA.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        open_file_labelA.setText("OPEN FILE");
        open_file_buttonA.add(open_file_labelA, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 0, 80, 50));

        bg.add(open_file_buttonA, new org.netbeans.lib.awtextra.AbsoluteConstraints(670, 120, 180, 50));

        mark_read_button.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/PNGS/check.png"))); // NOI18N
        mark_read_button.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                mark_read_buttonMouseClicked(evt);
            }
        });
        bg.add(mark_read_button, new org.netbeans.lib.awtextra.AbsoluteConstraints(360, 120, -1, -1));

        getContentPane().add(bg, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 1280, 720));

        fullscreen_panelA.setBackground(new java.awt.Color(255, 255, 255));
        fullscreen_panelA.setMaximumSize(new java.awt.Dimension(1280, 720));
        fullscreen_panelA.setMinimumSize(new java.awt.Dimension(1280, 720));
        fullscreen_panelA.setPreferredSize(new java.awt.Dimension(1280, 720));
        fullscreen_panelA.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        exit_fullscreen_buttonA.setMaximumSize(new java.awt.Dimension(200, 200));
        exit_fullscreen_buttonA.setMinimumSize(new java.awt.Dimension(200, 200));
        exit_fullscreen_buttonA.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                exit_fullscreen_buttonAMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                exit_fullscreen_buttonAMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                exit_fullscreen_buttonAMouseExited(evt);
            }
        });
        exit_fullscreen_buttonA.setLayout(null);

        exit_fullscreen_labelA.setBackground(new java.awt.Color(54, 33, 89));
        exit_fullscreen_labelA.setFont(new java.awt.Font("Roboto Bk", 0, 14)); // NOI18N
        exit_fullscreen_labelA.setForeground(new java.awt.Color(54, 33, 89));
        exit_fullscreen_labelA.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        exit_fullscreen_labelA.setText("GO BACK");
        exit_fullscreen_buttonA.add(exit_fullscreen_labelA);
        exit_fullscreen_labelA.setBounds(30, 0, 120, 50);

        fullscreen_panelA.add(exit_fullscreen_buttonA, new org.netbeans.lib.awtextra.AbsoluteConstraints(1060, 640, 180, 50));

        fullscreen_ScrollPaneA.setBackground(new java.awt.Color(255, 255, 255));
        fullscreen_ScrollPaneA.setToolTipText("");
        fullscreen_ScrollPaneA.setVerticalScrollBarPolicy(javax.swing.ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);

        fullscreen_tableA.setFont(new java.awt.Font("Roboto", 0, 18)); // NOI18N
        fullscreen_tableA.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "S. No.", "Date", "Time", "Message"
            }
        ));
        fullscreen_tableA.setFocusable(false);
        fullscreen_tableA.setGridColor(new java.awt.Color(255, 255, 255));
        fullscreen_tableA.setMaximumSize(new java.awt.Dimension(300, 60));
        fullscreen_tableA.setMinimumSize(new java.awt.Dimension(300, 60));
        fullscreen_tableA.setRowHeight(30);
        fullscreen_tableA.setSelectionBackground(new java.awt.Color(54, 33, 89));
        fullscreen_ScrollPaneA.setViewportView(fullscreen_tableA);
        if (fullscreen_tableA.getColumnModel().getColumnCount() > 0) {
            fullscreen_tableA.getColumnModel().getColumn(0).setMinWidth(50);
            fullscreen_tableA.getColumnModel().getColumn(0).setPreferredWidth(50);
            fullscreen_tableA.getColumnModel().getColumn(0).setMaxWidth(50);
            fullscreen_tableA.getColumnModel().getColumn(1).setMinWidth(150);
            fullscreen_tableA.getColumnModel().getColumn(1).setPreferredWidth(150);
            fullscreen_tableA.getColumnModel().getColumn(1).setMaxWidth(150);
            fullscreen_tableA.getColumnModel().getColumn(2).setMinWidth(150);
            fullscreen_tableA.getColumnModel().getColumn(2).setPreferredWidth(150);
            fullscreen_tableA.getColumnModel().getColumn(2).setMaxWidth(150);
        }

        fullscreen_panelA.add(fullscreen_ScrollPaneA, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 40, 1200, 580));

        getContentPane().add(fullscreen_panelA, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 1280, 720));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void menuAFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_menuAFocusGained
        // TODO add your handling code here:
    }//GEN-LAST:event_menuAFocusGained

    private void menuAMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_menuAMouseClicked

        menuA.setBackground(new java.awt.Color(85, 65, 118));
        menuB.setBackground(new java.awt.Color(64, 43, 100));

        panelA.setVisible(true);
        panelB.setVisible(false);
        fullscreen_panelA.setVisible(false);

        fullscreen_buttonA.setVisible(true);
        remove_buttonA.setVisible(true);
        refresh_buttonA.setVisible(true);
        open_file_buttonA.setVisible(true);
        mark_read_button.setVisible(true);

        viewall();
    }//GEN-LAST:event_menuAMouseClicked

    private void back_iconMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_back_iconMouseClicked
        if (go_back) {
            new home(1).setVisible(true);
            this.setVisible(false);
        }
        go_back = false;
    }//GEN-LAST:event_back_iconMouseClicked

    private void menuBMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_menuBMouseClicked

        menuB.setBackground(new java.awt.Color(85, 65, 118));
        menuA.setBackground(new java.awt.Color(64, 43, 100));

        panelA.setVisible(false);
        panelB.setVisible(true);
        fullscreen_panelA.setVisible(false);

        fullscreen_buttonA.setVisible(false);
        remove_buttonA.setVisible(false);
        refresh_buttonA.setVisible(false);
        open_file_buttonA.setVisible(false);
        mark_read_button.setVisible(false);
    }//GEN-LAST:event_menuBMouseClicked

    private void formWindowOpened(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowOpened
        //for a centered frame
        Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
        this.setLocation(dim.width / 2 - this.getSize().width / 2, dim.height / 2 - this.getSize().height / 2);
        //for a centered frame

        menuA.setBackground(new java.awt.Color(85, 65, 118));
        menuB.setBackground(new java.awt.Color(64, 43, 100));

        panelA.setVisible(true);
        panelB.setVisible(false);
        fullscreen_panelA.setVisible(false);

        fullscreen_buttonA.setVisible(true);
        remove_buttonA.setVisible(true);
        refresh_buttonA.setVisible(true);
        open_file_buttonA.setVisible(true);
        mark_read_button.setVisible(true);

        viewall();
    }//GEN-LAST:event_formWindowOpened

    private void select_file_buttonAMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_select_file_buttonAMouseClicked

        JFileChooser chooser = new JFileChooser();

        //applying extension filter
        chooser.setFileFilter(new FileNameExtensionFilter("Document Files", "docx", "doc", "pdf", "xls", "xlsx"));

        //opening file chooser window
        chooser.showOpenDialog(null);

        File f = chooser.getSelectedFile();

        //filepath & filename
        filepath = f.getAbsolutePath();
        filename = f.getName();
        extension1 = FilenameUtils.getExtension(filename);

        //setting the label to filename
        select_file_labelA.setText(filename);
        chooser.setSelectedFile(null);

    }//GEN-LAST:event_select_file_buttonAMouseClicked

    private void select_file_buttonAMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_select_file_buttonAMouseEntered
        select_file_buttonA.setBackground(new java.awt.Color(237, 224, 255));// TODO add your handling code here:
    }//GEN-LAST:event_select_file_buttonAMouseEntered

    private void select_file_buttonAMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_select_file_buttonAMouseExited
        select_file_buttonA.setBackground(new java.awt.Color(240, 240, 240));// TODO add your handling code here:
    }//GEN-LAST:event_select_file_buttonAMouseExited

    private void message_fieldActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_message_fieldActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_message_fieldActionPerformed

    private void exit_iconMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_exit_iconMouseClicked
        int confirmed = JOptionPane.showConfirmDialog(null, "Are you sure you want to exit the program?", "Exit Program", JOptionPane.YES_NO_OPTION);

        if (confirmed == JOptionPane.YES_OPTION) {
            dispose();
        }
    }//GEN-LAST:event_exit_iconMouseClicked

    private void minimizeMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_minimizeMouseClicked
        setState(this.ICONIFIED);
    }//GEN-LAST:event_minimizeMouseClicked

    private void exit_fullscreen_buttonAMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_exit_fullscreen_buttonAMouseClicked
        fullscreen_panelA.setVisible(false);
        bg.setVisible(true);
    }//GEN-LAST:event_exit_fullscreen_buttonAMouseClicked

    private void exit_fullscreen_buttonAMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_exit_fullscreen_buttonAMouseEntered
        exit_fullscreen_buttonA.setBackground(new java.awt.Color(237, 224, 255));
    }//GEN-LAST:event_exit_fullscreen_buttonAMouseEntered

    private void exit_fullscreen_buttonAMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_exit_fullscreen_buttonAMouseExited
        exit_fullscreen_buttonA.setBackground(new java.awt.Color(240, 240, 240));
    }//GEN-LAST:event_exit_fullscreen_buttonAMouseExited

    private void fullscreen_buttonAMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_fullscreen_buttonAMouseClicked
        fullscreen_panelA.setVisible(true);
        bg.setVisible(false);

    }//GEN-LAST:event_fullscreen_buttonAMouseClicked

    private void move_panelMouseDragged(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_move_panelMouseDragged
        this.setLocation(this.getX() + evt.getX() - mouseX, this.getY() + evt.getY() - mouseY);
    }//GEN-LAST:event_move_panelMouseDragged

    private void move_panelMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_move_panelMousePressed
        mouseX = evt.getX();
        mouseY = evt.getY();
    }//GEN-LAST:event_move_panelMousePressed

    private void fullscreen_buttonAMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_fullscreen_buttonAMouseEntered
        fullscreen_buttonA.setBackground(new java.awt.Color(237, 224, 255));
    }//GEN-LAST:event_fullscreen_buttonAMouseEntered

    private void fullscreen_buttonAMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_fullscreen_buttonAMouseExited
        fullscreen_buttonA.setBackground(new java.awt.Color(240, 240, 240));
    }//GEN-LAST:event_fullscreen_buttonAMouseExited

    private void add_message_buttonMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_add_message_buttonMouseEntered
        add_message_button.setBackground(new java.awt.Color(237, 224, 255));
    }//GEN-LAST:event_add_message_buttonMouseEntered

    private void add_message_buttonMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_add_message_buttonMouseExited
        add_message_button.setBackground(new java.awt.Color(240, 240, 240));
    }//GEN-LAST:event_add_message_buttonMouseExited

    private void add_message_buttonMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_add_message_buttonMouseClicked

        if (message_field.getText().equals("")) {
            JOptionPane.showMessageDialog(this, "Please enter a message before Adding.");
        } else {
            DateTimeFormatter dtf1 = DateTimeFormatter.ofPattern("yyyyMMdd HH:mm:ss"); //for time & date in database
            DateTimeFormatter dtf2 = DateTimeFormatter.ofPattern("yyyyMMdd_HH_mm_ss"); //for new_filename
            now = LocalDateTime.now();

            String[] date_time = dtf1.format(now).split(" ");
            new_filename = dtf2.format(now) + "." + extension1;

            //when file is added along with the text message
            if (filepath != null) {
                try {
                    java.sql.PreparedStatement st = con.prepareStatement("insert into notify " + "values(?,?,?,?,false)");
                    st.setString(1, date_time[0]);
                    st.setString(2, date_time[1]);
                    st.setString(3, message_field.getText());
                    st.setString(4, new_filename);
                    st.execute();

                    JOptionPane.showMessageDialog(this, "Notification Added");
                } catch (SQLException ex) {
                    Logger.getLogger(Notify.class.getName()).log(Level.SEVERE, null, ex);
                }
                move(filepath);
            }
            
            //when file isn't added along with the text message
            else {
                try {
                    java.sql.PreparedStatement st = con.prepareStatement("insert into notify " + "values(?,?,?,?,false)");
                    st.setString(1, date_time[0]);
                    st.setString(2, date_time[1]);
                    st.setString(3, message_field.getText());
                    st.setString(4, null);
                    st.execute();

                    JOptionPane.showMessageDialog(this, "Notification Added");
                } catch (SQLException ex) {
                    JOptionPane.showMessageDialog(this, ex);
                }
            }
            message_field.setText("");
            select_file_labelA.setText("Select File");
            filepath=null;
            filename=null;
            extension1=null;
        }
    }//GEN-LAST:event_add_message_buttonMouseClicked

    private void refresh_buttonAMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_refresh_buttonAMouseClicked
        viewall();
    }//GEN-LAST:event_refresh_buttonAMouseClicked

    private void refresh_buttonAMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_refresh_buttonAMouseEntered
        refresh_buttonA.setBackground(new java.awt.Color(237, 224, 255));
    }//GEN-LAST:event_refresh_buttonAMouseEntered

    private void refresh_buttonAMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_refresh_buttonAMouseExited
        refresh_buttonA.setBackground(new java.awt.Color(240, 240, 240));
    }//GEN-LAST:event_refresh_buttonAMouseExited

    private void remove_buttonAMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_remove_buttonAMouseClicked
        
        get_filename();
        
        //row not selected
        if (TableA.getSelectedRow() == -1) {
            JOptionPane.showMessageDialog(this, "First select a row to delete");
        }
        
        //row is selected
        else {
            try {

                String sql_query = "Delete from notify where date='" + date_fromtable + "'and time='" + time_fromtable + "'";

                Statement st = con.createStatement();
                st.executeUpdate(sql_query);

                path_file paths = new path_file();

                if (file_name != null) {
                    try {
                        String filename_fromtable = TableA.getValueAt(TableA.getSelectedRow(), 4).toString();
                        Files.delete(Paths.get(paths.notification_files + "/" + file_name));
                        System.out.println(file_name);
                    } catch (Exception e) {
                        JOptionPane.showMessageDialog(this, e);
                    }
                }
                JOptionPane.showMessageDialog(this, "Deletion confirmed.");
            } catch (SQLException sQLException) {
                JOptionPane.showMessageDialog(this, sQLException);
            }
        }
        
        refresh_buttonAMouseClicked(null);
    }//GEN-LAST:event_remove_buttonAMouseClicked

    private void remove_buttonAMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_remove_buttonAMouseEntered
        remove_buttonA.setBackground(new java.awt.Color(237, 224, 255));
    }//GEN-LAST:event_remove_buttonAMouseEntered

    private void remove_buttonAMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_remove_buttonAMouseExited
        remove_buttonA.setBackground(new java.awt.Color(240, 240, 240));
    }//GEN-LAST:event_remove_buttonAMouseExited

    private void open_file_buttonAMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_open_file_buttonAMouseClicked
        
        get_filename();
        
        if (TableA.getSelectedRow() == -1)
            JOptionPane.showMessageDialog(null, "Please select element from table to edit.");
        else if (file_name ==null) {
            JOptionPane.showMessageDialog(this, "No related file");
        } else {
            try {
                Desktop.getDesktop().open(new File(paths.notification_files + "/" + file_name));
            } catch (IOException ex) {
                JOptionPane.showMessageDialog(this, ex);
            }
        }
    }//GEN-LAST:event_open_file_buttonAMouseClicked

    private void open_file_buttonAMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_open_file_buttonAMouseEntered
        open_file_buttonA.setBackground(new java.awt.Color(237, 224, 255));
    }//GEN-LAST:event_open_file_buttonAMouseEntered

    private void open_file_buttonAMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_open_file_buttonAMouseExited
        open_file_buttonA.setBackground(new java.awt.Color(240, 240, 240));
    }//GEN-LAST:event_open_file_buttonAMouseExited

    private void mark_read_buttonMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_mark_read_buttonMouseClicked
        PreparedStatement pstmt;
        get_filename();
        
        if (TableA.getSelectedRow() == -1) {
            JOptionPane.showMessageDialog(null, "Please select element from table to edit.");
        } else {
            try {

                pstmt = con.prepareStatement("update notify set status=true where date='" + date_fromtable + "'and time='" + time_fromtable + "'");
                pstmt.executeUpdate();

                refresh_buttonAMouseClicked(null);

            } catch (SQLException ex) {
                Logger.getLogger(Notify.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }//GEN-LAST:event_mark_read_buttonMouseClicked

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Windows".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(Notify.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Notify.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Notify.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Notify.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Notify(0).setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JScrollPane ScrollPaneA;
    private javax.swing.JSeparator Separator;
    private javax.swing.JTable TableA;
    private javax.swing.JLabel add_button_label;
    private javax.swing.JPanel add_message_button;
    private javax.swing.JSeparator army_number_underlineA;
    private javax.swing.JLabel back_icon;
    private javax.swing.JPanel bg;
    private javax.swing.JPanel exit_fullscreen_buttonA;
    private javax.swing.JLabel exit_fullscreen_labelA;
    private javax.swing.JLabel exit_icon;
    private javax.swing.JLabel expiry_date_label;
    private javax.swing.JScrollPane fullscreen_ScrollPaneA;
    private javax.swing.JPanel fullscreen_buttonA;
    private javax.swing.JLabel fullscreen_labelD;
    private javax.swing.JPanel fullscreen_panelA;
    private javax.swing.JTable fullscreen_tableA;
    private javax.swing.JLabel mark_read_button;
    private javax.swing.JPanel menuA;
    private javax.swing.JLabel menuA_icon;
    private javax.swing.JLabel menuA_label;
    private javax.swing.JPanel menuB;
    private javax.swing.JLabel menuB_icon;
    private javax.swing.JLabel menuB_label;
    private javax.swing.JTextField message_field;
    private javax.swing.JLabel message_label;
    private javax.swing.JLabel minimize;
    private javax.swing.JPanel move_panel;
    private javax.swing.JPanel open_file_buttonA;
    private javax.swing.JLabel open_file_labelA;
    private javax.swing.JPanel panelA;
    private javax.swing.JPanel panelB;
    private javax.swing.JPanel refresh_buttonA;
    private javax.swing.JLabel refresh_labelA;
    private javax.swing.JPanel remove_buttonA;
    private javax.swing.JLabel remove_labelA;
    private javax.swing.JPanel select_file_buttonA;
    private javax.swing.JLabel select_file_labelA;
    private javax.swing.JPanel side_pane;
    private javax.swing.JLabel title_icon;
    private javax.swing.JLabel title_label;
    private javax.swing.JSeparator title_underline;
    // End of variables declaration//GEN-END:variables
}
