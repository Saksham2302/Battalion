
import java.awt.Dimension;
import java.awt.Image;
import java.awt.Toolkit;
import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.sql.PreparedStatement;
import javax.swing.ImageIcon;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.table.DefaultTableModel;
import org.apache.commons.io.FilenameUtils;

public class Our_Heroes extends javax.swing.JFrame {

    private boolean go_back = true;
    Database db = new Database();
    String filepath, filename, extension1;
    path_file paths = new path_file();
    Connection con = db.create_connection(true);
    /**
     * Creates new form Our_Heroes
     */
    private int mouseX, mouseY;
    DefaultTableModel model_table, model_fullscreen;

    public Our_Heroes() {
        initComponents();

        ScrollPaneA.getVerticalScrollBar().setUnitIncrement(16);
        fullscreen_ScrollPaneA.getVerticalScrollBar().setUnitIncrement(16);
        
        menuA.setBackground(new java.awt.Color(85, 65, 118));
        menuB.setBackground(new java.awt.Color(64, 43, 100));

        panelA.setVisible(true);
        panelB.setVisible(false);
        fullscreen_panelA.setVisible(false);
        ScrollPaneC.setVisible(false);

        fullscreen_buttonA.setVisible(true);
        refresh_buttonA.setVisible(true);
        view_buttonA.setVisible(true);
        model_table = (DefaultTableModel) TableA.getModel();
        model_fullscreen = (DefaultTableModel) fullscreen_tableA.getModel();

    }

    private String retrieve_Army_No() {
        String outpass_name = "";
        try {
            Statement stmt = con.createStatement();
            if (army_number_textfieldB.getText().length() != 0) {
                String Query = "Select Name from attach_in where Service_no = " + army_number_textfieldB.getText();
                ResultSet rs = stmt.executeQuery(Query);
                String name_, rank_, unit_, trade_, company_;
                if (rs.next()) {
                    name_ = rs.getString("Name");
                    name_textfieldB.setText("> " + name_);

                    outpass_name = name_;
                } else {
                    Query = "Select Name from post_in where Service_no = " + army_number_textfieldB.getText();
                    rs = stmt.executeQuery(Query);
                    if (rs.next()) {
                        name_ = rs.getString("Name");
                        name_textfieldB.setText("> " + name_);

                        outpass_name = name_;
                    } else {
                        Query = "Select Name from new_registration where Service_no = " + army_number_textfieldB.getText();
                        rs = stmt.executeQuery(Query);
                        if (rs.next()) {
                            name_ = rs.getString("Name");
                            name_textfieldB.setText("> " + name_);

                            outpass_name = name_;
                        }
                    }
                }

                if (outpass_name.equals("")) {
                    JOptionPane.showMessageDialog(panelA, "Army Number does not exist!", "Error", JOptionPane.ERROR_MESSAGE);

                }
            } else {
                JOptionPane.showMessageDialog(panelA, "Army Number cannot be empty!", "Error", JOptionPane.ERROR_MESSAGE);

            }
        } catch (Exception e) {
                    JOptionPane.showMessageDialog(null, e);
        }
        return outpass_name;
    }

    private void viewall() {
        try {

            Statement stmt = con.createStatement();
            String Query = "select * from our_heroes order by Service_no;";

            ResultSet rs = stmt.executeQuery(Query);
            String service_no, name_, operation_, incident_;
            int sno = 0;
            model_table.setRowCount(0);
            model_fullscreen.setRowCount(0);
            while (rs.next()) {
                sno++;
                service_no = rs.getString("Service_no");
                name_ = rs.getString("name");
                operation_ = rs.getString("operation");
                incident_ = rs.getString("incident_detail");

                model_table.addRow(new Object[]{
                    sno, service_no, name_, operation_, incident_
                });
                model_fullscreen.addRow(new Object[]{
                    sno, service_no, name_, operation_, incident_
                });

            }

        } catch (Exception e) {
                    JOptionPane.showMessageDialog(null, e);
        }
    }

    private void editingData(int row) {
        scaleImage((String) TableA.getValueAt(row, 1));
        service_numberC.setText((String) TableA.getValueAt(row, 1));
        nameC.setText((String) TableA.getValueAt(row, 2));
        operationC.setText((String) TableA.getValueAt(row, 3));
        incident_textareaC.setText((String) TableA.getValueAt(row, 4));
    }

    public void scaleImage(String sno) {

        ImageIcon icon = new ImageIcon(paths.images + '/' + sno + ".jpg");
        Image img = icon.getImage();
        Image imgScale = img.getScaledInstance(photo_labelC.getWidth(), photo_labelC.getHeight(), Image.SCALE_SMOOTH);
        ImageIcon scaledIcon = new ImageIcon(imgScale);
        photo_labelC.setIcon(scaledIcon);

    }

    private void move(String str, String sno) {
        try {
            path_file paths = new path_file();
            Path temp = Files.copy(Paths.get(str), Paths.get(paths.images + "\\" + sno + ".JPG"), StandardCopyOption.REPLACE_EXISTING);
            if (temp != null) {
                System.out.println("File renamed and moved successfully");
            } else {
                System.out.println("Failed to move the file");
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "An unexpected error occurred!");
        }
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        move_panel = new javax.swing.JPanel();
        bg = new javax.swing.JPanel();
        exit_icon = new javax.swing.JLabel();
        minimize = new javax.swing.JLabel();
        side_pane = new javax.swing.JPanel();
        menuA = new javax.swing.JPanel();
        menuA_icon = new javax.swing.JLabel();
        menuA_label = new javax.swing.JLabel();
        title_label = new javax.swing.JLabel();
        title_underline = new javax.swing.JSeparator();
        title_icon = new javax.swing.JLabel();
        back_icon = new javax.swing.JLabel();
        menuB = new javax.swing.JPanel();
        menuB_icon = new javax.swing.JLabel();
        menuB_label = new javax.swing.JLabel();
        panelA = new javax.swing.JPanel();
        ScrollPaneA = new javax.swing.JScrollPane();
        TableA = new javax.swing.JTable();
        panelB = new javax.swing.JPanel();
        add_button = new javax.swing.JPanel();
        add_button_label = new javax.swing.JLabel();
        army_number_underlineA = new javax.swing.JSeparator();
        army_number_textfieldB = new javax.swing.JTextField();
        army_number_labelA = new javax.swing.JLabel();
        achievement_underlineB = new javax.swing.JSeparator();
        operation_textfieldB = new javax.swing.JTextField();
        operation_labelB = new javax.swing.JLabel();
        name_labelB = new javax.swing.JLabel();
        incident_labelB = new javax.swing.JLabel();
        incident_textfieldB = new javax.swing.JTextField();
        incident_underlineB = new javax.swing.JSeparator();
        army_number_textfieldB1 = new javax.swing.JTextField();
        name_textfieldB = new javax.swing.JTextField();
        army_number_underlineA1 = new javax.swing.JSeparator();
        upload_photo_labelB = new javax.swing.JLabel();
        select_file_buttonB = new javax.swing.JPanel();
        select_file_labelA = new javax.swing.JLabel();
        ScrollPaneC = new javax.swing.JScrollPane();
        panelC = new javax.swing.JPanel();
        service_number_labelC = new javax.swing.JLabel();
        name_labelC = new javax.swing.JLabel();
        service_numberC = new javax.swing.JLabel();
        operation_labelC = new javax.swing.JLabel();
        operationC = new javax.swing.JLabel();
        nameC = new javax.swing.JLabel();
        Photo = new javax.swing.JPanel();
        photo_labelC = new javax.swing.JLabel();
        incident_labelC = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        incident_textareaC = new javax.swing.JTextArea();
        Separator = new javax.swing.JSeparator();
        fullscreen_buttonA = new javax.swing.JPanel();
        fullscreen_labelD = new javax.swing.JLabel();
        refresh_buttonA = new javax.swing.JPanel();
        refresh_labelA = new javax.swing.JLabel();
        view_buttonA = new javax.swing.JPanel();
        view_labelA = new javax.swing.JLabel();
        fullscreen_panelA = new javax.swing.JPanel();
        exit_fullscreen_buttonA = new javax.swing.JPanel();
        exit_fullscreen_labelA = new javax.swing.JLabel();
        fullscreen_ScrollPaneA = new javax.swing.JScrollPane();
        fullscreen_tableA = new javax.swing.JTable();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setBackground(new java.awt.Color(255, 255, 255));
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

        getContentPane().add(move_panel, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, -1, -1));

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
        menuA_label.setText("List of Heroes");
        menuA.add(menuA_label, new org.netbeans.lib.awtextra.AbsoluteConstraints(68, 1, -1, 60));

        side_pane.add(menuA, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 240, 310, 60));

        title_label.setFont(new java.awt.Font("Montserrat", 0, 28)); // NOI18N
        title_label.setForeground(new java.awt.Color(255, 255, 255));
        title_label.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        title_label.setText("Our Heroes");
        side_pane.add(title_label, new org.netbeans.lib.awtextra.AbsoluteConstraints(90, 130, 180, 50));
        side_pane.add(title_underline, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 190, 260, 30));

        title_icon.setBackground(new java.awt.Color(255, 255, 255));
        title_icon.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        title_icon.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/PNGS/our heroes white.png"))); // NOI18N
        side_pane.add(title_icon, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 110, 70, 80));

        back_icon.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/PNGS/left-arrow.png"))); // NOI18N
        back_icon.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                back_iconMouseClicked(evt);
            }
        });
        side_pane.add(back_icon, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 30, -1, -1));

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
                "S. No.", "Army Number", "Name", "Operation Name", "Incident Detail"
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
        }

        panelA.add(ScrollPaneA, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 50, 780, 420));

        bg.add(panelA, new org.netbeans.lib.awtextra.AbsoluteConstraints(340, 192, 910, 480));

        panelB.setBackground(new java.awt.Color(255, 255, 255));
        panelB.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        add_button.setMaximumSize(new java.awt.Dimension(95, 25));
        add_button.setMinimumSize(new java.awt.Dimension(95, 25));
        add_button.setPreferredSize(new java.awt.Dimension(95, 25));
        add_button.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                add_buttonMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                add_buttonMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                add_buttonMouseExited(evt);
            }
        });
        add_button.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        add_button_label.setFont(new java.awt.Font("Roboto", 0, 14)); // NOI18N
        add_button_label.setForeground(new java.awt.Color(54, 33, 89));
        add_button_label.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        add_button_label.setText("ADD");
        add_button.add(add_button_label, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 0, 100, 50));

        panelB.add(add_button, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 410, 180, 50));

        army_number_underlineA.setForeground(new java.awt.Color(54, 33, 89));
        panelB.add(army_number_underlineA, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 130, 320, 20));

        army_number_textfieldB.setFont(new java.awt.Font("Roboto", 0, 24)); // NOI18N
        army_number_textfieldB.setForeground(new java.awt.Color(54, 33, 89));
        army_number_textfieldB.setBorder(null);
        army_number_textfieldB.setMargin(new java.awt.Insets(2, 4, 2, 2));
        army_number_textfieldB.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                army_number_textfieldBActionPerformed(evt);
            }
        });
        panelB.add(army_number_textfieldB, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 90, 320, 40));

        army_number_labelA.setFont(new java.awt.Font("Montserrat", 0, 28)); // NOI18N
        army_number_labelA.setForeground(new java.awt.Color(51, 51, 51));
        army_number_labelA.setText("Army Number");
        panelB.add(army_number_labelA, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 50, -1, 40));

        achievement_underlineB.setForeground(new java.awt.Color(54, 33, 89));
        panelB.add(achievement_underlineB, new org.netbeans.lib.awtextra.AbsoluteConstraints(490, 240, 320, 20));

        operation_textfieldB.setFont(new java.awt.Font("Roboto", 0, 24)); // NOI18N
        operation_textfieldB.setForeground(new java.awt.Color(54, 33, 89));
        operation_textfieldB.setBorder(null);
        operation_textfieldB.setMargin(new java.awt.Insets(2, 4, 2, 2));
        panelB.add(operation_textfieldB, new org.netbeans.lib.awtextra.AbsoluteConstraints(490, 200, 320, 40));

        operation_labelB.setFont(new java.awt.Font("Montserrat", 0, 28)); // NOI18N
        operation_labelB.setForeground(new java.awt.Color(51, 51, 51));
        operation_labelB.setText("Operation Name");
        panelB.add(operation_labelB, new org.netbeans.lib.awtextra.AbsoluteConstraints(490, 160, -1, 40));

        name_labelB.setBackground(new java.awt.Color(255, 255, 255));
        name_labelB.setFont(new java.awt.Font("Montserrat", 0, 28)); // NOI18N
        name_labelB.setForeground(new java.awt.Color(51, 51, 51));
        name_labelB.setText("Name");
        panelB.add(name_labelB, new org.netbeans.lib.awtextra.AbsoluteConstraints(490, 50, -1, 40));

        incident_labelB.setFont(new java.awt.Font("Montserrat", 0, 28)); // NOI18N
        incident_labelB.setForeground(new java.awt.Color(51, 51, 51));
        incident_labelB.setText("Incident Detail");
        panelB.add(incident_labelB, new org.netbeans.lib.awtextra.AbsoluteConstraints(77, 270, 620, 40));

        incident_textfieldB.setFont(new java.awt.Font("Roboto", 0, 24)); // NOI18N
        incident_textfieldB.setForeground(new java.awt.Color(54, 33, 89));
        incident_textfieldB.setBorder(null);
        incident_textfieldB.setMargin(new java.awt.Insets(2, 4, 2, 2));
        panelB.add(incident_textfieldB, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 310, 730, 40));

        incident_underlineB.setForeground(new java.awt.Color(54, 33, 89));
        panelB.add(incident_underlineB, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 350, 730, 20));

        army_number_textfieldB1.setFont(new java.awt.Font("Roboto", 0, 24)); // NOI18N
        army_number_textfieldB1.setForeground(new java.awt.Color(54, 33, 89));
        army_number_textfieldB1.setBorder(null);
        army_number_textfieldB1.setMargin(new java.awt.Insets(2, 4, 2, 2));
        army_number_textfieldB1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                army_number_textfieldB1ActionPerformed(evt);
            }
        });
        panelB.add(army_number_textfieldB1, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 90, 320, 40));

        name_textfieldB.setFont(new java.awt.Font("Roboto", 0, 24)); // NOI18N
        name_textfieldB.setForeground(new java.awt.Color(54, 33, 89));
        name_textfieldB.setBorder(null);
        name_textfieldB.setMargin(new java.awt.Insets(2, 4, 2, 2));
        name_textfieldB.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                name_textfieldBActionPerformed(evt);
            }
        });
        panelB.add(name_textfieldB, new org.netbeans.lib.awtextra.AbsoluteConstraints(490, 90, 320, 40));

        army_number_underlineA1.setForeground(new java.awt.Color(54, 33, 89));
        panelB.add(army_number_underlineA1, new org.netbeans.lib.awtextra.AbsoluteConstraints(490, 130, 320, 20));

        upload_photo_labelB.setBackground(new java.awt.Color(255, 255, 255));
        upload_photo_labelB.setFont(new java.awt.Font("Montserrat", 0, 28)); // NOI18N
        upload_photo_labelB.setForeground(new java.awt.Color(51, 51, 51));
        upload_photo_labelB.setText("Upload Photo");
        panelB.add(upload_photo_labelB, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 160, -1, 40));

        select_file_buttonB.setMaximumSize(new java.awt.Dimension(200, 200));
        select_file_buttonB.setMinimumSize(new java.awt.Dimension(200, 200));
        select_file_buttonB.setPreferredSize(new java.awt.Dimension(200, 200));
        select_file_buttonB.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                select_file_buttonBMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                select_file_buttonBMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                select_file_buttonBMouseExited(evt);
            }
        });
        select_file_buttonB.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        select_file_labelA.setBackground(new java.awt.Color(54, 33, 89));
        select_file_labelA.setFont(new java.awt.Font("Roboto", 0, 18)); // NOI18N
        select_file_labelA.setForeground(new java.awt.Color(54, 33, 89));
        select_file_labelA.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        select_file_labelA.setText("Select File");
        select_file_buttonB.add(select_file_labelA, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 0, 240, 40));

        panelB.add(select_file_buttonB, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 200, 320, 40));

        bg.add(panelB, new org.netbeans.lib.awtextra.AbsoluteConstraints(340, 192, 910, 480));

        ScrollPaneC.setBorder(null);
        ScrollPaneC.setHorizontalScrollBarPolicy(javax.swing.ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);

        panelC.setBackground(new java.awt.Color(255, 255, 255));
        panelC.setFocusable(false);
        panelC.setMaximumSize(new java.awt.Dimension(950, 1310));
        panelC.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        service_number_labelC.setFont(new java.awt.Font("Montserrat", 0, 28)); // NOI18N
        service_number_labelC.setForeground(new java.awt.Color(51, 51, 51));
        service_number_labelC.setText("Army Number");
        panelC.add(service_number_labelC, new org.netbeans.lib.awtextra.AbsoluteConstraints(460, 60, -1, 50));

        name_labelC.setBackground(new java.awt.Color(255, 255, 255));
        name_labelC.setFont(new java.awt.Font("Montserrat", 0, 28)); // NOI18N
        name_labelC.setForeground(new java.awt.Color(51, 51, 51));
        name_labelC.setText("Name");
        panelC.add(name_labelC, new org.netbeans.lib.awtextra.AbsoluteConstraints(460, 140, -1, 50));

        service_numberC.setFont(new java.awt.Font("Roboto Bk", 0, 24)); // NOI18N
        service_numberC.setForeground(new java.awt.Color(54, 33, 89));
        service_numberC.setText("> XXXXXXX");
        panelC.add(service_numberC, new org.netbeans.lib.awtextra.AbsoluteConstraints(460, 100, -1, 30));

        operation_labelC.setBackground(new java.awt.Color(255, 255, 255));
        operation_labelC.setFont(new java.awt.Font("Montserrat", 0, 28)); // NOI18N
        operation_labelC.setForeground(new java.awt.Color(51, 51, 51));
        operation_labelC.setText("Operation Name");
        panelC.add(operation_labelC, new org.netbeans.lib.awtextra.AbsoluteConstraints(460, 220, -1, 50));

        operationC.setFont(new java.awt.Font("Roboto Bk", 0, 24)); // NOI18N
        operationC.setForeground(new java.awt.Color(54, 33, 89));
        operationC.setText("> XXXXXXX");
        panelC.add(operationC, new org.netbeans.lib.awtextra.AbsoluteConstraints(460, 260, -1, 30));

        nameC.setFont(new java.awt.Font("Roboto Bk", 0, 24)); // NOI18N
        nameC.setForeground(new java.awt.Color(54, 33, 89));
        nameC.setText("> XXXXXXX");
        panelC.add(nameC, new org.netbeans.lib.awtextra.AbsoluteConstraints(460, 180, -1, 30));

        Photo.setMaximumSize(new java.awt.Dimension(95, 25));
        Photo.setMinimumSize(new java.awt.Dimension(95, 25));
        Photo.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                PhotoMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                PhotoMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                PhotoMouseExited(evt);
            }
        });
        Photo.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());
        Photo.add(photo_labelC, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 310, 370));

        panelC.add(Photo, new org.netbeans.lib.awtextra.AbsoluteConstraints(100, 70, 310, 370));

        incident_labelC.setBackground(new java.awt.Color(255, 255, 255));
        incident_labelC.setFont(new java.awt.Font("Montserrat", 0, 28)); // NOI18N
        incident_labelC.setForeground(new java.awt.Color(51, 51, 51));
        incident_labelC.setText("Incident Detail");
        panelC.add(incident_labelC, new org.netbeans.lib.awtextra.AbsoluteConstraints(460, 300, -1, 50));

        jScrollPane1.setHorizontalScrollBarPolicy(javax.swing.ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
        jScrollPane1.setVerticalScrollBarPolicy(javax.swing.ScrollPaneConstants.VERTICAL_SCROLLBAR_NEVER);

        incident_textareaC.setColumns(20);
        incident_textareaC.setFont(new java.awt.Font("Montserrat Medium", 0, 18)); // NOI18N
        incident_textareaC.setLineWrap(true);
        incident_textareaC.setRows(5);
        incident_textareaC.setEnabled(false);
        incident_textareaC.setMargin(new java.awt.Insets(2, 8, 2, 8));
        jScrollPane1.setViewportView(incident_textareaC);

        panelC.add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(460, 350, 360, 90));

        ScrollPaneC.setViewportView(panelC);

        bg.add(ScrollPaneC, new org.netbeans.lib.awtextra.AbsoluteConstraints(340, 192, 910, 480));

        Separator.setForeground(new java.awt.Color(54, 33, 89));
        Separator.setPreferredSize(new java.awt.Dimension(50, 20));
        bg.add(Separator, new org.netbeans.lib.awtextra.AbsoluteConstraints(360, 190, 890, 10));

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

        view_buttonA.setForeground(new java.awt.Color(240, 240, 240));
        view_buttonA.setMaximumSize(new java.awt.Dimension(200, 200));
        view_buttonA.setMinimumSize(new java.awt.Dimension(200, 200));
        view_buttonA.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                view_buttonAMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                view_buttonAMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                view_buttonAMouseExited(evt);
            }
        });
        view_buttonA.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        view_labelA.setBackground(new java.awt.Color(54, 33, 89));
        view_labelA.setFont(new java.awt.Font("Roboto Bk", 0, 14)); // NOI18N
        view_labelA.setForeground(new java.awt.Color(54, 33, 89));
        view_labelA.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        view_labelA.setText("VIEW");
        view_buttonA.add(view_labelA, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 0, 80, 50));

        bg.add(view_buttonA, new org.netbeans.lib.awtextra.AbsoluteConstraints(670, 120, 180, 50));

        getContentPane().add(bg, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, -1, -1));

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
                "S. No.", "Army No", "Name", "Operation", "Incident"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, true, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
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
        }

        fullscreen_panelA.add(fullscreen_ScrollPaneA, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 40, 1200, 580));

        getContentPane().add(fullscreen_panelA, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, -1, -1));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void move_panelMouseDragged(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_move_panelMouseDragged
        this.setLocation(this.getX() + evt.getX() - mouseX, this.getY() + evt.getY() - mouseY);
    }//GEN-LAST:event_move_panelMouseDragged

    private void move_panelMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_move_panelMousePressed
        mouseX = evt.getX();
        mouseY = evt.getY();
    }//GEN-LAST:event_move_panelMousePressed

    private void menuAFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_menuAFocusGained
        // TODO add your handling code here:
    }//GEN-LAST:event_menuAFocusGained

    private void menuAMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_menuAMouseClicked
        viewall();
        menuA.setBackground(new java.awt.Color(85, 65, 118));
        menuB.setBackground(new java.awt.Color(64, 43, 100));

        panelA.setVisible(true);
        panelB.setVisible(false);
        fullscreen_panelA.setVisible(false);
        ScrollPaneC.setVisible(false);

        fullscreen_buttonA.setVisible(true);
        refresh_buttonA.setVisible(true);
        view_buttonA.setVisible(true);


    }//GEN-LAST:event_menuAMouseClicked

    private void back_iconMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_back_iconMouseClicked
        if (go_back) {

            new home(1).setVisible(true);
            this.setVisible(false);
        }
        go_back = false;

    }//GEN-LAST:event_back_iconMouseClicked

    private void menuBMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_menuBMouseClicked
        army_number_textfieldB.setText("");
        name_textfieldB.setText("");
        operation_textfieldB.setText("");
        incident_textfieldB.setText("");
        menuB.setBackground(new java.awt.Color(85, 65, 118));
        menuA.setBackground(new java.awt.Color(64, 43, 100));

        panelA.setVisible(false);
        panelB.setVisible(true);
        fullscreen_panelA.setVisible(false);
        ScrollPaneC.setVisible(false);

        fullscreen_buttonA.setVisible(false);
        refresh_buttonA.setVisible(false);
        view_buttonA.setVisible(false);
    }//GEN-LAST:event_menuBMouseClicked

    private void add_buttonMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_add_buttonMouseClicked

        String service_no = army_number_textfieldB.getText().trim();
        String name_ = name_textfieldB.getText().trim();
        String operation = operation_textfieldB.getText().trim();
        String incident = incident_textfieldB.getText().trim();

        if (service_no.equals("")) {
            JOptionPane.showMessageDialog(null, "Army Number cannot be empty!", "Error", JOptionPane.ERROR_MESSAGE);

        } else {
            if (name_.equals("")) {
                JOptionPane.showMessageDialog(null, "Name cannot be empty!", "Error", JOptionPane.ERROR_MESSAGE);

            } else {
                if (operation.equals("")) {
                    JOptionPane.showMessageDialog(null, "Operation field cannot be empty!", "Error", JOptionPane.ERROR_MESSAGE);
                } else {
                    if (!select_file_labelA.getText().trim().equals("Select File")) {

                        try {
                            PreparedStatement pstmt = con.prepareStatement("Insert into our_heroes values(?, ?, ?, ?)");
                            pstmt.setString(1, service_no);
                            pstmt.setString(2, name_);
                            pstmt.setString(3, operation);
                            pstmt.setString(4, incident);
                            pstmt.executeUpdate();
                            move(filepath, service_no);
                            JOptionPane.showMessageDialog(null, "Entry Successful", "Successful", JOptionPane.WARNING_MESSAGE);

                            army_number_textfieldB.setText("");
                            name_textfieldB.setText("");
                            operation_textfieldB.setText("");
                            incident_textfieldB.setText("");

                        } catch (Exception e) {
                    JOptionPane.showMessageDialog(null, e);

                        }
                    }
                    else{
                        JOptionPane.showMessageDialog(null, "Photo not uploaded!", "Error", JOptionPane.ERROR_MESSAGE);

                    }
                }
            }
        }
    }//GEN-LAST:event_add_buttonMouseClicked

    private void add_buttonMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_add_buttonMouseEntered
        add_button.setBackground(new java.awt.Color(237, 224, 255));
    }//GEN-LAST:event_add_buttonMouseEntered

    private void add_buttonMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_add_buttonMouseExited
        add_button.setBackground(new java.awt.Color(240, 240, 240));
    }//GEN-LAST:event_add_buttonMouseExited

    private void exit_iconMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_exit_iconMouseClicked
        int confirmed = JOptionPane.showConfirmDialog(null, "Are you sure you want to exit the program?", "Exit Program", JOptionPane.YES_NO_OPTION);

        if (confirmed == JOptionPane.YES_OPTION) {
            dispose();
            db.create_connection(false);
        }
    }//GEN-LAST:event_exit_iconMouseClicked

    private void minimizeMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_minimizeMouseClicked
        setState(this.ICONIFIED);
    }//GEN-LAST:event_minimizeMouseClicked

    private void fullscreen_buttonAMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_fullscreen_buttonAMouseClicked
        viewall();
        fullscreen_panelA.setVisible(true);
        bg.setVisible(false);

    }//GEN-LAST:event_fullscreen_buttonAMouseClicked

    private void fullscreen_buttonAMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_fullscreen_buttonAMouseEntered
        fullscreen_buttonA.setBackground(new java.awt.Color(237, 224, 255));
    }//GEN-LAST:event_fullscreen_buttonAMouseEntered

    private void fullscreen_buttonAMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_fullscreen_buttonAMouseExited
        fullscreen_buttonA.setBackground(new java.awt.Color(240, 240, 240));
    }//GEN-LAST:event_fullscreen_buttonAMouseExited

    private void refresh_buttonAMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_refresh_buttonAMouseClicked
        viewall();
    }//GEN-LAST:event_refresh_buttonAMouseClicked

    private void refresh_buttonAMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_refresh_buttonAMouseEntered
        refresh_buttonA.setBackground(new java.awt.Color(237, 224, 255));
    }//GEN-LAST:event_refresh_buttonAMouseEntered

    private void refresh_buttonAMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_refresh_buttonAMouseExited
        refresh_buttonA.setBackground(new java.awt.Color(240, 240, 240));
    }//GEN-LAST:event_refresh_buttonAMouseExited

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

    private void view_buttonAMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_view_buttonAMouseExited
        view_buttonA.setBackground(new java.awt.Color(240, 240, 240));
    }//GEN-LAST:event_view_buttonAMouseExited

    private void view_buttonAMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_view_buttonAMouseEntered
        view_buttonA.setBackground(new java.awt.Color(237, 224, 255));
    }//GEN-LAST:event_view_buttonAMouseEntered

    private void view_buttonAMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_view_buttonAMouseClicked
        int row = TableA.getSelectedRow();
        if (row == -1) {
            JOptionPane.showMessageDialog(null, "Please select a row to view!", "Error", JOptionPane.ERROR_MESSAGE);
        } else {
            ScrollPaneC.setVisible(true);
            panelA.setVisible(false);
            panelB.setVisible(false);
            fullscreen_panelA.setVisible(false);

            fullscreen_buttonA.setVisible(false);
            refresh_buttonA.setVisible(false);
            view_buttonA.setVisible(false);
            editingData(row);
        }

    }//GEN-LAST:event_view_buttonAMouseClicked

    private void PhotoMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_PhotoMouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_PhotoMouseClicked

    private void PhotoMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_PhotoMouseEntered
        Photo.setBackground(new java.awt.Color(237, 224, 255));// TODO add your handling code here:
    }//GEN-LAST:event_PhotoMouseEntered

    private void PhotoMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_PhotoMouseExited
        Photo.setBackground(new java.awt.Color(240, 240, 240));// TODO add your handling code here:
    }//GEN-LAST:event_PhotoMouseExited

    private void army_number_textfieldBActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_army_number_textfieldBActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_army_number_textfieldBActionPerformed

    private void formWindowOpened(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowOpened
        //for a centered frame
        Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
        this.setLocation(dim.width / 2 - this.getSize().width / 2, dim.height / 2 - this.getSize().height / 2);
        //for a centered frame

        viewall();
    }//GEN-LAST:event_formWindowOpened

    private void army_number_textfieldB1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_army_number_textfieldB1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_army_number_textfieldB1ActionPerformed

    private void name_textfieldBActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_name_textfieldBActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_name_textfieldBActionPerformed

    private void select_file_buttonBMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_select_file_buttonBMouseClicked
        JFileChooser chooser = new JFileChooser();

        //applying extension filter
        chooser.setFileFilter(new FileNameExtensionFilter("Image Files", "png", "jpg", "jpeg"));

        //opening file chooser window
        chooser.showOpenDialog(null);

        File f = chooser.getSelectedFile();

        //filepath
        filepath = f.getAbsolutePath();
        filename = f.getName();
        extension1 = FilenameUtils.getExtension(filename);
        //setting the label to filename
        select_file_labelA.setText(filename);

    }//GEN-LAST:event_select_file_buttonBMouseClicked

    private void select_file_buttonBMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_select_file_buttonBMouseEntered
        select_file_buttonB.setBackground(new java.awt.Color(237, 224, 255));// TODO add your handling code here:
    }//GEN-LAST:event_select_file_buttonBMouseEntered

    private void select_file_buttonBMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_select_file_buttonBMouseExited
        select_file_buttonB.setBackground(new java.awt.Color(240, 240, 240));// TODO add your handling code here:
    }//GEN-LAST:event_select_file_buttonBMouseExited

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
            java.util.logging.Logger.getLogger(Our_Heroes.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Our_Heroes.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Our_Heroes.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Our_Heroes.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Our_Heroes().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel Photo;
    private javax.swing.JScrollPane ScrollPaneA;
    private javax.swing.JScrollPane ScrollPaneC;
    private javax.swing.JSeparator Separator;
    private javax.swing.JTable TableA;
    private javax.swing.JSeparator achievement_underlineB;
    private javax.swing.JPanel add_button;
    private javax.swing.JLabel add_button_label;
    private javax.swing.JLabel army_number_labelA;
    private javax.swing.JTextField army_number_textfieldB;
    private javax.swing.JTextField army_number_textfieldB1;
    private javax.swing.JSeparator army_number_underlineA;
    private javax.swing.JSeparator army_number_underlineA1;
    private javax.swing.JLabel back_icon;
    private javax.swing.JPanel bg;
    private javax.swing.JPanel exit_fullscreen_buttonA;
    private javax.swing.JLabel exit_fullscreen_labelA;
    private javax.swing.JLabel exit_icon;
    private javax.swing.JScrollPane fullscreen_ScrollPaneA;
    private javax.swing.JPanel fullscreen_buttonA;
    private javax.swing.JLabel fullscreen_labelD;
    private javax.swing.JPanel fullscreen_panelA;
    private javax.swing.JTable fullscreen_tableA;
    private javax.swing.JLabel incident_labelB;
    private javax.swing.JLabel incident_labelC;
    private javax.swing.JTextArea incident_textareaC;
    private javax.swing.JTextField incident_textfieldB;
    private javax.swing.JSeparator incident_underlineB;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JPanel menuA;
    private javax.swing.JLabel menuA_icon;
    private javax.swing.JLabel menuA_label;
    private javax.swing.JPanel menuB;
    private javax.swing.JLabel menuB_icon;
    private javax.swing.JLabel menuB_label;
    private javax.swing.JLabel minimize;
    private javax.swing.JPanel move_panel;
    private javax.swing.JLabel nameC;
    private javax.swing.JLabel name_labelB;
    private javax.swing.JLabel name_labelC;
    private javax.swing.JTextField name_textfieldB;
    private javax.swing.JLabel operationC;
    private javax.swing.JLabel operation_labelB;
    private javax.swing.JLabel operation_labelC;
    private javax.swing.JTextField operation_textfieldB;
    private javax.swing.JPanel panelA;
    private javax.swing.JPanel panelB;
    private javax.swing.JPanel panelC;
    private javax.swing.JLabel photo_labelC;
    private javax.swing.JPanel refresh_buttonA;
    private javax.swing.JLabel refresh_labelA;
    private javax.swing.JPanel select_file_buttonB;
    private javax.swing.JLabel select_file_labelA;
    private javax.swing.JLabel service_numberC;
    private javax.swing.JLabel service_number_labelC;
    private javax.swing.JPanel side_pane;
    private javax.swing.JLabel title_icon;
    private javax.swing.JLabel title_label;
    private javax.swing.JSeparator title_underline;
    private javax.swing.JLabel upload_photo_labelB;
    private javax.swing.JPanel view_buttonA;
    private javax.swing.JLabel view_labelA;
    // End of variables declaration//GEN-END:variables
}
