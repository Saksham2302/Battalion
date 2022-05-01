
import java.awt.Desktop;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.io.File;
import java.io.FileOutputStream;
import java.sql.Connection;
import java.sql.Statement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author chise
 */
public class Competition_Record extends javax.swing.JFrame {

    private String comp_type_panelC = "";
    private boolean go_back;
    Database db = new Database();
    Connection con = db.create_connection(true);
    /**
     * Creates new form Competition_Record
     */
    DefaultTableModel model_table, fullscreen_table;
    private int mouseX, mouseY;

    public Competition_Record() {
        initComponents();
        
        ScrollPaneC.getVerticalScrollBar().setUnitIncrement(16);
        ScrollPaneA.getVerticalScrollBar().setUnitIncrement(16);
        ScrollPaneB.getVerticalScrollBar().setUnitIncrement(16);
        fullscreen_ScrollPaneB.getVerticalScrollBar().setUnitIncrement(16);
        
        insert_buttonA.setVisible(true);
        print_buttonB.setVisible(false);
        edit_buttonB.setVisible(false);
        refresh_buttonB.setVisible(false);
        fullscreen_buttonB.setVisible(false);
        model_table = (DefaultTableModel) TableB.getModel();
        fullscreen_table = (DefaultTableModel) fullscreen_tableB.getModel();
    }

    private String validate_date(String day_, String month_, String year_) {

        boolean flag = true;
        String join_date = "";

        if ("DD".equals(day_.trim()) || "MM".equals(month_.trim()) || "YYYY".equals(year_.trim())) {
            flag = false;
        } else {
            flag = false;
            int day = Integer.parseInt(day_);
            int month = Integer.parseInt(month_);
            int year = Integer.parseInt(year_);
            if ((month == 1 || month == 3 || month == 5 || month == 7 || month == 8 || month == 10 || month == 12) && day <= 31) {
                flag = true;
            } else if ((month == 4 || month == 6 || month == 9 || month == 11) && day <= 30) {
                flag = true;
            } else if (month == 2 && ((year % 4 == 0 && day <= 29) || (year % 4 != 0 && day <= 28))) {
                flag = true;
            }
            if (flag == true) {
                join_date = year_ + "" + month_ + "" + day_;
            }

        }
        return join_date;
    }

    private void current_date(String date) {
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd-MM-yyyy");
        LocalDateTime now = LocalDateTime.now();
        String[] day = dtf.format(now).split("-");
        if (date.equals("from")) {
            from_date_comboboxA.setSelectedItem(day[0]);
            from_month_comboboxA.setSelectedItem(day[1]);
            from_year_comboboxA.setSelectedItem(day[2]);
        }
        if (date.equals("to")) {
            to_date_comboboxA.setSelectedItem(day[0]);
            to_month_comboboxA.setSelectedItem(day[1]);
            to_year_comboboxA.setSelectedItem(day[2]);
        }
    }

    private boolean viewByCompType() {
        boolean flag = false;
        try {
            Statement stmt = con.createStatement();
            comp_type_panelC = comp_typeB.getText().trim();

            if (comp_type_panelC.length() != 0) {
                String Query = "Select * from competition_record where comp_type='" + comp_type_panelC + "' order by from_date, to_date, comp_type";
                ResultSet rs = stmt.executeQuery(Query);
                String trg_year, from_date, to_date, offr, jco, or, pos1, pos2, pos3;
                int sno = 0;
                model_table.setRowCount(0);
                fullscreen_table.setRowCount(0);
                while (rs.next()) {
                    flag = true;
                    sno++;
                    trg_year = rs.getString("trg_year");
                    from_date = rs.getString("from_date");
                    to_date = rs.getString("to_date");
                    offr = rs.getString("offr");
                    jco = rs.getString("jco");
                    or = rs.getString("or_");
                    pos1 = rs.getString("pos1");
                    pos2 = rs.getString("pos2");
                    pos3 = rs.getString("pos3");

                    model_table.addRow(new Object[]{
                        sno, comp_type_panelC, trg_year, from_date, to_date, offr, jco, or, pos1, pos2, pos3
                    });
                    fullscreen_table.addRow(new Object[]{
                        sno, comp_type_panelC, trg_year, from_date, to_date, offr, jco, or, pos1, pos2, pos3
                    });
                }
            }

            if (flag == false) {
                JOptionPane.showMessageDialog(null, "Competition Type does not exists!", "Error", JOptionPane.ERROR_MESSAGE);
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);

        }
        return flag;
    }

    private void viewall() {
        try {
            Statement stmt = con.createStatement();
            String Query = "Select * from competition_record order by from_date, to_date, comp_type";
            ResultSet rs = stmt.executeQuery(Query);
            String comp_type, trg_year, from_date, to_date, offr, jco, or, pos1, pos2, pos3;
            int sno = 0;
            model_table.setRowCount(0);
            fullscreen_table.setRowCount(0);
            while (rs.next()) {
                sno++;
                comp_type = rs.getString("comp_type");
                trg_year = rs.getString("trg_year");
                from_date = rs.getString("from_date");
                to_date = rs.getString("to_date");
                offr = rs.getString("offr");
                jco = rs.getString("jco");
                or = rs.getString("or_");
                pos1 = rs.getString("pos1");
                pos2 = rs.getString("pos2");
                pos3 = rs.getString("pos3");

                model_table.addRow(new Object[]{
                    sno, comp_type, trg_year, from_date, to_date, offr, jco, or, pos1, pos2, pos3
                });
                fullscreen_table.addRow(new Object[]{
                    sno, comp_type, trg_year, from_date, to_date, offr, jco, or, pos1, pos2, pos3
                });
            }

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);

        }
    }

    private void editingData(int row) {
        String from = (String) TableB.getValueAt(row, 3);
        String to = (String) TableB.getValueAt(row, 4);
        comp_nameC.setText((String) TableB.getValueAt(row, 1));
        type_textfieldC.setText((String) TableB.getValueAt(row, 1));
        year_textfieldC.setText((String) TableB.getValueAt(row, 2));
        from_dateC.setText(from);
        to_dateC.setText(to);
        OFFR_textfieldC.setText((String) TableB.getValueAt(row, 5));
        JCO_textfieldC.setText((String) TableB.getValueAt(row, 6));
        OR_textfieldC.setText((String) TableB.getValueAt(row, 7));
        POS1_textfieldC.setText((String) TableB.getValueAt(row, 8));
        POS2_textfieldC.setText((String) TableB.getValueAt(row, 9));
        POS3_textfieldC.setText((String) TableB.getValueAt(row, 10));

        from_date_comboboxC.setSelectedItem(from.substring(8, 10));
        from_month_comboboxC.setSelectedItem(from.substring(5, 7));
        from_year_comboboxC.setSelectedItem(from.substring(0, 4));

        to_date_comboboxC.setSelectedItem(to.substring(8, 10));
        to_month_comboboxC.setSelectedItem(to.substring(5, 7));
        to_year_comboboxC.setSelectedItem(to.substring(0, 4));
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
        Separator = new javax.swing.JSeparator();
        side_pane = new javax.swing.JPanel();
        jSeparator1 = new javax.swing.JSeparator();
        go_back_label = new javax.swing.JLabel();
        menuA = new javax.swing.JPanel();
        menuA_icon = new javax.swing.JLabel();
        menuA_label = new javax.swing.JLabel();
        menuB = new javax.swing.JPanel();
        menuB_icon = new javax.swing.JLabel();
        menuB_label = new javax.swing.JLabel();
        title_icon = new javax.swing.JLabel();
        title_label = new javax.swing.JLabel();
        title_label1 = new javax.swing.JLabel();
        ScrollPaneA = new javax.swing.JScrollPane();
        panelA = new javax.swing.JPanel();
        from_labelA = new javax.swing.JLabel();
        underlineA2 = new javax.swing.JSeparator();
        underlineA1 = new javax.swing.JSeparator();
        type_textfieldA = new javax.swing.JTextField();
        type_labelA = new javax.swing.JLabel();
        to_labelA = new javax.swing.JLabel();
        JCO_labelA = new javax.swing.JLabel();
        OFFR_labelA = new javax.swing.JLabel();
        year_labelA = new javax.swing.JLabel();
        year_textfieldA = new javax.swing.JTextField();
        underlineA5 = new javax.swing.JSeparator();
        OFFR_textfieldA = new javax.swing.JTextField();
        JCO_textfieldA = new javax.swing.JTextField();
        underlineA7 = new javax.swing.JSeparator();
        OR_labelA = new javax.swing.JLabel();
        OR_textfieldA = new javax.swing.JTextField();
        underlineA6 = new javax.swing.JSeparator();
        POSN_labelA = new javax.swing.JLabel();
        panelA_bottom_line = new javax.swing.JSeparator();
        pos2_labelA = new javax.swing.JLabel();
        pos3_labelA = new javax.swing.JLabel();
        pos1_labelA = new javax.swing.JLabel();
        midA1 = new javax.swing.JSeparator();
        pos1_textfield = new javax.swing.JTextField();
        POS_underline1 = new javax.swing.JSeparator();
        pos2_textfield = new javax.swing.JTextField();
        POS_underline2 = new javax.swing.JSeparator();
        pos3_textfield = new javax.swing.JTextField();
        POS_underline3 = new javax.swing.JSeparator();
        from_date_comboboxA = new javax.swing.JComboBox<>();
        from_month_comboboxA = new javax.swing.JComboBox<>();
        from_year_comboboxA = new javax.swing.JComboBox<>();
        current_dateA = new javax.swing.JLabel();
        to_date_comboboxA = new javax.swing.JComboBox<>();
        to_month_comboboxA = new javax.swing.JComboBox<>();
        to_year_comboboxA = new javax.swing.JComboBox<>();
        current_dateA1 = new javax.swing.JLabel();
        panelB = new javax.swing.JPanel();
        ScrollPaneB = new javax.swing.JScrollPane();
        TableB = new javax.swing.JTable();
        comp_labelB = new javax.swing.JLabel();
        comp_typeB = new javax.swing.JTextField();
        comp_type_underlineB = new javax.swing.JSeparator();
        okayB = new javax.swing.JLabel();
        ScrollPaneC = new javax.swing.JScrollPane();
        panelC = new javax.swing.JPanel();
        comp_labelC = new javax.swing.JLabel();
        comp_nameC = new javax.swing.JLabel();
        changing_for_label = new javax.swing.JLabel();
        midC = new javax.swing.JSeparator();
        from_labelC = new javax.swing.JLabel();
        type_textfieldC = new javax.swing.JTextField();
        type_labelC = new javax.swing.JLabel();
        to_labelC = new javax.swing.JLabel();
        JCO_labelC = new javax.swing.JLabel();
        OFFR_labelC = new javax.swing.JLabel();
        year_labelC = new javax.swing.JLabel();
        year_textfieldC = new javax.swing.JTextField();
        OFFR_textfieldC = new javax.swing.JTextField();
        JCO_textfieldC = new javax.swing.JTextField();
        OR_labelC = new javax.swing.JLabel();
        OR_textfieldC = new javax.swing.JTextField();
        panelA_bottom_line2 = new javax.swing.JSeparator();
        underlineC1 = new javax.swing.JSeparator();
        underlineC2 = new javax.swing.JSeparator();
        underlineC6 = new javax.swing.JSeparator();
        underlineC5 = new javax.swing.JSeparator();
        underlineC7 = new javax.swing.JSeparator();
        from_date_comboboxC = new javax.swing.JComboBox<>();
        from_month_comboboxC = new javax.swing.JComboBox<>();
        from_year_comboboxC = new javax.swing.JComboBox<>();
        to_date_comboboxC = new javax.swing.JComboBox<>();
        to_month_comboboxC = new javax.swing.JComboBox<>();
        to_year_comboboxC = new javax.swing.JComboBox<>();
        from_labelC0 = new javax.swing.JLabel();
        to_labelC0 = new javax.swing.JLabel();
        to_dateC = new javax.swing.JLabel();
        from_dateC = new javax.swing.JLabel();
        midF4 = new javax.swing.JSeparator();
        POSN_labelA1 = new javax.swing.JLabel();
        pos2_label1 = new javax.swing.JLabel();
        pos3_label1 = new javax.swing.JLabel();
        pos1_label1 = new javax.swing.JLabel();
        midA2 = new javax.swing.JSeparator();
        POS1_textfieldC = new javax.swing.JTextField();
        underline4 = new javax.swing.JSeparator();
        POS2_textfieldC = new javax.swing.JTextField();
        underline5 = new javax.swing.JSeparator();
        POS3_textfieldC = new javax.swing.JTextField();
        underline6 = new javax.swing.JSeparator();
        insert_buttonA = new javax.swing.JPanel();
        insert_labelA = new javax.swing.JLabel();
        refresh_buttonB = new javax.swing.JPanel();
        refresh_labelB = new javax.swing.JLabel();
        print_buttonB = new javax.swing.JPanel();
        print_labelB = new javax.swing.JLabel();
        fullscreen_buttonB = new javax.swing.JPanel();
        fullscreen_labelB = new javax.swing.JLabel();
        edit_buttonB = new javax.swing.JPanel();
        edit_labelB = new javax.swing.JLabel();
        update_buttonC = new javax.swing.JPanel();
        update_labelC = new javax.swing.JLabel();
        fullscreen_panelB = new javax.swing.JPanel();
        fullscreen_ScrollPaneB = new javax.swing.JScrollPane();
        fullscreen_tableB = new javax.swing.JTable();
        exit_fullscreen_buttonB = new javax.swing.JPanel();
        exit_fullscreen_labelB = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
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

        Separator.setForeground(new java.awt.Color(54, 33, 89));
        Separator.setPreferredSize(new java.awt.Dimension(50, 20));
        bg.add(Separator, new org.netbeans.lib.awtextra.AbsoluteConstraints(360, 190, 890, 10));

        side_pane.setBackground(new java.awt.Color(54, 33, 89));
        side_pane.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());
        side_pane.add(jSeparator1, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 190, 280, 50));

        go_back_label.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/PNGS/left-arrow.png"))); // NOI18N
        go_back_label.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                go_back_labelMouseClicked(evt);
            }
        });
        side_pane.add(go_back_label, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 30, -1, -1));

        menuA.setBackground(new java.awt.Color(85, 65, 118));
        menuA.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                menuAMouseClicked(evt);
            }
        });

        menuA_icon.setForeground(new java.awt.Color(255, 255, 255));
        menuA_icon.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        menuA_icon.setText("A");

        menuA_label.setFont(new java.awt.Font("Roboto", 1, 18)); // NOI18N
        menuA_label.setForeground(new java.awt.Color(255, 255, 255));
        menuA_label.setText("Create Record");

        javax.swing.GroupLayout menuALayout = new javax.swing.GroupLayout(menuA);
        menuA.setLayout(menuALayout);
        menuALayout.setHorizontalGroup(
            menuALayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(menuALayout.createSequentialGroup()
                .addGap(27, 27, 27)
                .addComponent(menuA_icon)
                .addGap(33, 33, 33)
                .addComponent(menuA_label)
                .addContainerGap(137, Short.MAX_VALUE))
        );
        menuALayout.setVerticalGroup(
            menuALayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(menuALayout.createSequentialGroup()
                .addGap(1, 1, 1)
                .addGroup(menuALayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(menuA_label, javax.swing.GroupLayout.DEFAULT_SIZE, 60, Short.MAX_VALUE)
                    .addComponent(menuA_icon, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
        );

        side_pane.add(menuA, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 240, 330, 60));

        menuB.setBackground(new java.awt.Color(85, 65, 118));
        menuB.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                menuBMouseClicked(evt);
            }
        });

        menuB_icon.setForeground(new java.awt.Color(255, 255, 255));
        menuB_icon.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        menuB_icon.setText("B");

        menuB_label.setFont(new java.awt.Font("Roboto", 1, 18)); // NOI18N
        menuB_label.setForeground(new java.awt.Color(255, 255, 255));
        menuB_label.setText("View");

        javax.swing.GroupLayout menuBLayout = new javax.swing.GroupLayout(menuB);
        menuB.setLayout(menuBLayout);
        menuBLayout.setHorizontalGroup(
            menuBLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(menuBLayout.createSequentialGroup()
                .addGap(27, 27, 27)
                .addComponent(menuB_icon)
                .addGap(33, 33, 33)
                .addComponent(menuB_label)
                .addContainerGap(220, Short.MAX_VALUE))
        );
        menuBLayout.setVerticalGroup(
            menuBLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(menuBLayout.createSequentialGroup()
                .addGap(1, 1, 1)
                .addGroup(menuBLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(menuB_label, javax.swing.GroupLayout.DEFAULT_SIZE, 60, Short.MAX_VALUE)
                    .addComponent(menuB_icon, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
        );

        side_pane.add(menuB, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 300, 330, 60));

        title_icon.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        title_icon.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/PNGS/competition_white.png"))); // NOI18N
        side_pane.add(title_icon, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 115, 70, 60));

        title_label.setFont(new java.awt.Font("Montserrat", 0, 28)); // NOI18N
        title_label.setForeground(new java.awt.Color(255, 255, 255));
        title_label.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        title_label.setText("Competition");
        side_pane.add(title_label, new org.netbeans.lib.awtextra.AbsoluteConstraints(90, 110, 200, 40));

        title_label1.setFont(new java.awt.Font("Montserrat", 0, 28)); // NOI18N
        title_label1.setForeground(new java.awt.Color(255, 255, 255));
        title_label1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        title_label1.setText("Record");
        side_pane.add(title_label1, new org.netbeans.lib.awtextra.AbsoluteConstraints(90, 140, 130, 40));

        bg.add(side_pane, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 320, 740));

        ScrollPaneA.setBorder(null);
        ScrollPaneA.setHorizontalScrollBarPolicy(javax.swing.ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
        ScrollPaneA.setVerticalScrollBarPolicy(javax.swing.ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);

        panelA.setBackground(new java.awt.Color(255, 255, 255));
        panelA.setFocusable(false);
        panelA.setMaximumSize(new java.awt.Dimension(950, 1310));
        panelA.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        from_labelA.setBackground(new java.awt.Color(255, 255, 255));
        from_labelA.setFont(new java.awt.Font("Montserrat", 0, 28)); // NOI18N
        from_labelA.setForeground(new java.awt.Color(51, 51, 51));
        from_labelA.setText("From");
        panelA.add(from_labelA, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 160, -1, 40));

        underlineA2.setForeground(new java.awt.Color(54, 33, 89));
        panelA.add(underlineA2, new org.netbeans.lib.awtextra.AbsoluteConstraints(490, 130, 320, 20));

        underlineA1.setForeground(new java.awt.Color(54, 33, 89));
        panelA.add(underlineA1, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 130, 320, 20));

        type_textfieldA.setFont(new java.awt.Font("Roboto", 0, 24)); // NOI18N
        type_textfieldA.setForeground(new java.awt.Color(54, 33, 89));
        type_textfieldA.setBorder(null);
        type_textfieldA.setMargin(new java.awt.Insets(2, 4, 2, 2));
        panelA.add(type_textfieldA, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 90, 320, 40));

        type_labelA.setFont(new java.awt.Font("Montserrat", 0, 28)); // NOI18N
        type_labelA.setForeground(new java.awt.Color(51, 51, 51));
        type_labelA.setText("Competition Type");
        panelA.add(type_labelA, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 50, -1, 40));

        to_labelA.setBackground(new java.awt.Color(255, 255, 255));
        to_labelA.setFont(new java.awt.Font("Montserrat", 0, 28)); // NOI18N
        to_labelA.setForeground(new java.awt.Color(51, 51, 51));
        to_labelA.setText("To");
        panelA.add(to_labelA, new org.netbeans.lib.awtextra.AbsoluteConstraints(490, 160, -1, 40));

        JCO_labelA.setBackground(new java.awt.Color(255, 255, 255));
        JCO_labelA.setFont(new java.awt.Font("Montserrat", 0, 28)); // NOI18N
        JCO_labelA.setForeground(new java.awt.Color(51, 51, 51));
        JCO_labelA.setText("JCO");
        panelA.add(JCO_labelA, new org.netbeans.lib.awtextra.AbsoluteConstraints(490, 280, -1, 40));

        OFFR_labelA.setBackground(new java.awt.Color(255, 255, 255));
        OFFR_labelA.setFont(new java.awt.Font("Montserrat", 0, 28)); // NOI18N
        OFFR_labelA.setForeground(new java.awt.Color(51, 51, 51));
        OFFR_labelA.setText("OFFR");
        panelA.add(OFFR_labelA, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 280, -1, 40));

        year_labelA.setBackground(new java.awt.Color(255, 255, 255));
        year_labelA.setFont(new java.awt.Font("Montserrat", 0, 28)); // NOI18N
        year_labelA.setForeground(new java.awt.Color(51, 51, 51));
        year_labelA.setText("TRG Year");
        panelA.add(year_labelA, new org.netbeans.lib.awtextra.AbsoluteConstraints(490, 50, -1, 40));

        year_textfieldA.setFont(new java.awt.Font("Roboto", 0, 24)); // NOI18N
        year_textfieldA.setForeground(new java.awt.Color(54, 33, 89));
        year_textfieldA.setBorder(null);
        year_textfieldA.setMargin(new java.awt.Insets(2, 4, 2, 2));
        year_textfieldA.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                year_textfieldAActionPerformed(evt);
            }
        });
        panelA.add(year_textfieldA, new org.netbeans.lib.awtextra.AbsoluteConstraints(490, 90, 320, 40));

        underlineA5.setForeground(new java.awt.Color(54, 33, 89));
        panelA.add(underlineA5, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 360, 320, 20));

        OFFR_textfieldA.setFont(new java.awt.Font("Roboto", 0, 24)); // NOI18N
        OFFR_textfieldA.setForeground(new java.awt.Color(54, 33, 89));
        OFFR_textfieldA.setBorder(null);
        OFFR_textfieldA.setMargin(new java.awt.Insets(2, 4, 2, 2));
        OFFR_textfieldA.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                OFFR_textfieldAActionPerformed(evt);
            }
        });
        panelA.add(OFFR_textfieldA, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 320, 320, 40));

        JCO_textfieldA.setFont(new java.awt.Font("Roboto", 0, 24)); // NOI18N
        JCO_textfieldA.setForeground(new java.awt.Color(54, 33, 89));
        JCO_textfieldA.setBorder(null);
        JCO_textfieldA.setMargin(new java.awt.Insets(2, 4, 2, 2));
        JCO_textfieldA.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                JCO_textfieldAActionPerformed(evt);
            }
        });
        panelA.add(JCO_textfieldA, new org.netbeans.lib.awtextra.AbsoluteConstraints(490, 320, 320, 40));

        underlineA7.setForeground(new java.awt.Color(54, 33, 89));
        panelA.add(underlineA7, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 470, 320, 20));

        OR_labelA.setBackground(new java.awt.Color(255, 255, 255));
        OR_labelA.setFont(new java.awt.Font("Montserrat", 0, 28)); // NOI18N
        OR_labelA.setForeground(new java.awt.Color(66, 50, 77));
        OR_labelA.setText("OR");
        panelA.add(OR_labelA, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 390, -1, 40));

        OR_textfieldA.setFont(new java.awt.Font("Roboto", 0, 24)); // NOI18N
        OR_textfieldA.setForeground(new java.awt.Color(54, 33, 89));
        OR_textfieldA.setBorder(null);
        OR_textfieldA.setMargin(new java.awt.Insets(2, 4, 2, 2));
        OR_textfieldA.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                OR_textfieldAActionPerformed(evt);
            }
        });
        panelA.add(OR_textfieldA, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 430, 320, 40));

        underlineA6.setForeground(new java.awt.Color(54, 33, 89));
        panelA.add(underlineA6, new org.netbeans.lib.awtextra.AbsoluteConstraints(490, 360, 320, 20));

        POSN_labelA.setBackground(new java.awt.Color(255, 255, 255));
        POSN_labelA.setFont(new java.awt.Font("Montserrat", 0, 28)); // NOI18N
        POSN_labelA.setForeground(new java.awt.Color(66, 50, 77));
        POSN_labelA.setText("POSN");
        panelA.add(POSN_labelA, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 520, -1, 40));

        panelA_bottom_line.setForeground(new java.awt.Color(54, 33, 89));
        panelA.add(panelA_bottom_line, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 720, 910, -1));

        pos2_labelA.setBackground(new java.awt.Color(255, 255, 255));
        pos2_labelA.setFont(new java.awt.Font("Montserrat", 0, 24)); // NOI18N
        pos2_labelA.setForeground(new java.awt.Color(66, 50, 77));
        pos2_labelA.setText("2nd");
        panelA.add(pos2_labelA, new org.netbeans.lib.awtextra.AbsoluteConstraints(340, 580, -1, 40));

        pos3_labelA.setBackground(new java.awt.Color(255, 255, 255));
        pos3_labelA.setFont(new java.awt.Font("Montserrat", 0, 24)); // NOI18N
        pos3_labelA.setForeground(new java.awt.Color(66, 50, 77));
        pos3_labelA.setText("3rd");
        panelA.add(pos3_labelA, new org.netbeans.lib.awtextra.AbsoluteConstraints(600, 580, -1, 40));

        pos1_labelA.setBackground(new java.awt.Color(255, 255, 255));
        pos1_labelA.setFont(new java.awt.Font("Montserrat", 0, 24)); // NOI18N
        pos1_labelA.setForeground(new java.awt.Color(66, 50, 77));
        pos1_labelA.setText("1st");
        panelA.add(pos1_labelA, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 580, -1, 40));

        midA1.setForeground(new java.awt.Color(204, 204, 255));
        panelA.add(midA1, new org.netbeans.lib.awtextra.AbsoluteConstraints(200, 540, 510, 10));

        pos1_textfield.setFont(new java.awt.Font("Roboto", 0, 24)); // NOI18N
        pos1_textfield.setForeground(new java.awt.Color(54, 33, 89));
        pos1_textfield.setBorder(null);
        pos1_textfield.setMargin(new java.awt.Insets(2, 4, 2, 2));
        pos1_textfield.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                pos1_textfieldActionPerformed(evt);
            }
        });
        panelA.add(pos1_textfield, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 620, 210, 40));

        POS_underline1.setForeground(new java.awt.Color(54, 33, 89));
        panelA.add(POS_underline1, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 660, 210, 20));

        pos2_textfield.setFont(new java.awt.Font("Roboto", 0, 24)); // NOI18N
        pos2_textfield.setForeground(new java.awt.Color(54, 33, 89));
        pos2_textfield.setBorder(null);
        pos2_textfield.setMargin(new java.awt.Insets(2, 4, 2, 2));
        pos2_textfield.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                pos2_textfieldActionPerformed(evt);
            }
        });
        panelA.add(pos2_textfield, new org.netbeans.lib.awtextra.AbsoluteConstraints(340, 620, 210, 40));

        POS_underline2.setForeground(new java.awt.Color(54, 33, 89));
        panelA.add(POS_underline2, new org.netbeans.lib.awtextra.AbsoluteConstraints(340, 660, 210, 20));

        pos3_textfield.setFont(new java.awt.Font("Roboto", 0, 24)); // NOI18N
        pos3_textfield.setForeground(new java.awt.Color(54, 33, 89));
        pos3_textfield.setBorder(null);
        pos3_textfield.setMargin(new java.awt.Insets(2, 4, 2, 2));
        pos3_textfield.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                pos3_textfieldActionPerformed(evt);
            }
        });
        panelA.add(pos3_textfield, new org.netbeans.lib.awtextra.AbsoluteConstraints(600, 620, 210, 40));

        POS_underline3.setForeground(new java.awt.Color(54, 33, 89));
        panelA.add(POS_underline3, new org.netbeans.lib.awtextra.AbsoluteConstraints(600, 660, 210, 20));

        from_date_comboboxA.setFont(new java.awt.Font("Roboto", 0, 18)); // NOI18N
        from_date_comboboxA.setForeground(new java.awt.Color(44, 62, 80));
        from_date_comboboxA.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "DD", "01", "02", "03", "04", "05", "06", "07", "08", "09", "10", "11", "12", "13", "14", "15", "16", "17", "18", "19", "20", "21", "22", "23", "24", "25", "26", "27", "28", "29", "30", "31" }));
        from_date_comboboxA.setBorder(null);
        panelA.add(from_date_comboboxA, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 200, 100, 40));

        from_month_comboboxA.setFont(new java.awt.Font("Roboto", 0, 18)); // NOI18N
        from_month_comboboxA.setForeground(new java.awt.Color(44, 62, 80));
        from_month_comboboxA.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "MM", "01", "02", "03", "04", "05", "06", "07", "08", "09", "10", "11", "12" }));
        from_month_comboboxA.setBorder(null);
        panelA.add(from_month_comboboxA, new org.netbeans.lib.awtextra.AbsoluteConstraints(190, 200, 100, 40));

        from_year_comboboxA.setFont(new java.awt.Font("Roboto", 0, 18)); // NOI18N
        from_year_comboboxA.setForeground(new java.awt.Color(44, 62, 80));
        from_year_comboboxA.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "YYYY", "1950", "1951", "1952", "1953", "1954", "1955", "1956", "1957", "1958", "1959", "1960", "1961", "1962", "1963", "1964", "1965", "1966", "1967", "1968", "1969", "1970", "1971", "1972", "1973", "1974", "1975", "1976", "1977", "1978", "1979", "1980", "1981", "1982", "1983", "1984", "1985", "1986", "1987", "1988", "1989", "1990", "1991", "1992", "1993", "1994", "1995", "1996", "1997", "1998", "1999", "2000", "2001", "2002", "2003", "2004", "2005", "2006", "2007", "2008", "2009", "2010", "2011", "2012", "2013", "2014", "2015", "2016", "2017", "2018", "2019", "2020", "2021", "2022", "2023", "2024", "2025", "2026", "2027", "2028", "2029", "2030", "2031", "2032", "2033", "2034", "2035", "2036", "2037", "2038", "2039", "2040", "2041", "2042", "2043", "2044", "2045", "2046", "2047", "2048", "2049", "2050", "2051", "2052", "2053", "2054", "2055", "2056", "2057", "2058", "2059", "2060", "2061", "2062", "2063", "2064", "2065", "2066", "2067", "2068", "2069", "2070", "2071", "2072", "2073", "2074", "2075", "2076", "2077", "2078", "2079", "2080", "2081", "2082", "2083", "2084", "2085", "2086", "2087", "2088", "2089", "2090", "2091", "2092", "2093", "2094", "2095", "2096", "2097", "2098", "2099", "2100", " " }));
        from_year_comboboxA.setBorder(null);
        panelA.add(from_year_comboboxA, new org.netbeans.lib.awtextra.AbsoluteConstraints(300, 200, 100, 40));

        current_dateA.setBackground(new java.awt.Color(255, 255, 255));
        current_dateA.setFont(new java.awt.Font("Segoe UI", 0, 13)); // NOI18N
        current_dateA.setForeground(new java.awt.Color(60, 63, 65));
        current_dateA.setText("Get Current Date");
        current_dateA.setOpaque(true);
        current_dateA.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                current_dateAMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                current_dateAMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                current_dateAMouseExited(evt);
            }
        });
        panelA.add(current_dateA, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 250, -1, -1));

        to_date_comboboxA.setFont(new java.awt.Font("Roboto", 0, 18)); // NOI18N
        to_date_comboboxA.setForeground(new java.awt.Color(44, 62, 80));
        to_date_comboboxA.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "DD", "01", "02", "03", "04", "05", "06", "07", "08", "09", "10", "11", "12", "13", "14", "15", "16", "17", "18", "19", "20", "21", "22", "23", "24", "25", "26", "27", "28", "29", "30", "31" }));
        to_date_comboboxA.setBorder(null);
        panelA.add(to_date_comboboxA, new org.netbeans.lib.awtextra.AbsoluteConstraints(490, 200, 100, 40));

        to_month_comboboxA.setFont(new java.awt.Font("Roboto", 0, 18)); // NOI18N
        to_month_comboboxA.setForeground(new java.awt.Color(44, 62, 80));
        to_month_comboboxA.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "MM", "01", "02", "03", "04", "05", "06", "07", "08", "09", "10", "11", "12" }));
        to_month_comboboxA.setBorder(null);
        panelA.add(to_month_comboboxA, new org.netbeans.lib.awtextra.AbsoluteConstraints(600, 200, 100, 40));

        to_year_comboboxA.setFont(new java.awt.Font("Roboto", 0, 18)); // NOI18N
        to_year_comboboxA.setForeground(new java.awt.Color(44, 62, 80));
        to_year_comboboxA.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "YYYY", "1950", "1951", "1952", "1953", "1954", "1955", "1956", "1957", "1958", "1959", "1960", "1961", "1962", "1963", "1964", "1965", "1966", "1967", "1968", "1969", "1970", "1971", "1972", "1973", "1974", "1975", "1976", "1977", "1978", "1979", "1980", "1981", "1982", "1983", "1984", "1985", "1986", "1987", "1988", "1989", "1990", "1991", "1992", "1993", "1994", "1995", "1996", "1997", "1998", "1999", "2000", "2001", "2002", "2003", "2004", "2005", "2006", "2007", "2008", "2009", "2010", "2011", "2012", "2013", "2014", "2015", "2016", "2017", "2018", "2019", "2020", "2021", "2022", "2023", "2024", "2025", "2026", "2027", "2028", "2029", "2030", "2031", "2032", "2033", "2034", "2035", "2036", "2037", "2038", "2039", "2040", "2041", "2042", "2043", "2044", "2045", "2046", "2047", "2048", "2049", "2050", "2051", "2052", "2053", "2054", "2055", "2056", "2057", "2058", "2059", "2060", "2061", "2062", "2063", "2064", "2065", "2066", "2067", "2068", "2069", "2070", "2071", "2072", "2073", "2074", "2075", "2076", "2077", "2078", "2079", "2080", "2081", "2082", "2083", "2084", "2085", "2086", "2087", "2088", "2089", "2090", "2091", "2092", "2093", "2094", "2095", "2096", "2097", "2098", "2099", "2100", " " }));
        to_year_comboboxA.setBorder(null);
        panelA.add(to_year_comboboxA, new org.netbeans.lib.awtextra.AbsoluteConstraints(710, 200, 100, 40));

        current_dateA1.setBackground(new java.awt.Color(255, 255, 255));
        current_dateA1.setFont(new java.awt.Font("Segoe UI", 0, 13)); // NOI18N
        current_dateA1.setForeground(new java.awt.Color(60, 63, 65));
        current_dateA1.setText("Get Current Date");
        current_dateA1.setOpaque(true);
        current_dateA1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                current_dateA1MouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                current_dateA1MouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                current_dateA1MouseExited(evt);
            }
        });
        panelA.add(current_dateA1, new org.netbeans.lib.awtextra.AbsoluteConstraints(490, 250, -1, -1));

        ScrollPaneA.setViewportView(panelA);

        bg.add(ScrollPaneA, new org.netbeans.lib.awtextra.AbsoluteConstraints(340, 192, 910, 480));

        panelB.setBackground(new java.awt.Color(255, 255, 255));
        panelB.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        ScrollPaneB.setBackground(new java.awt.Color(255, 255, 255));
        ScrollPaneB.setVerticalScrollBarPolicy(javax.swing.ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);

        TableB.setFont(new java.awt.Font("Roboto", 0, 18)); // NOI18N
        TableB.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "S. No.", "Comp Type", "TRG Year", "FROM", "TO", "OFFR", "JCO", "OR", "Pos 1", "Pos 2", "Pos 3"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        TableB.setAutoResizeMode(javax.swing.JTable.AUTO_RESIZE_ALL_COLUMNS);
        TableB.setFocusable(false);
        TableB.setGridColor(new java.awt.Color(255, 255, 255));
        TableB.setMaximumSize(null);
        TableB.setRowHeight(30);
        TableB.setSelectionBackground(new java.awt.Color(54, 33, 89));
        TableB.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        TableB.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                TableBMouseClicked(evt);
            }
        });
        ScrollPaneB.setViewportView(TableB);
        if (TableB.getColumnModel().getColumnCount() > 0) {
            TableB.getColumnModel().getColumn(0).setMinWidth(40);
            TableB.getColumnModel().getColumn(0).setPreferredWidth(40);
            TableB.getColumnModel().getColumn(0).setMaxWidth(40);
        }

        panelB.add(ScrollPaneB, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 170, 780, 300));

        comp_labelB.setBackground(new java.awt.Color(255, 255, 255));
        comp_labelB.setFont(new java.awt.Font("Montserrat", 0, 28)); // NOI18N
        comp_labelB.setForeground(new java.awt.Color(51, 51, 51));
        comp_labelB.setText("Competition Type");
        panelB.add(comp_labelB, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 50, -1, 40));

        comp_typeB.setFont(new java.awt.Font("Roboto", 0, 24)); // NOI18N
        comp_typeB.setForeground(new java.awt.Color(54, 33, 89));
        comp_typeB.setBorder(null);
        comp_typeB.setMargin(new java.awt.Insets(2, 4, 2, 2));
        comp_typeB.setSelectionColor(new java.awt.Color(204, 204, 255));
        comp_typeB.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                comp_typeBKeyPressed(evt);
            }
        });
        panelB.add(comp_typeB, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 90, 330, 40));

        comp_type_underlineB.setForeground(new java.awt.Color(54, 33, 89));
        panelB.add(comp_type_underlineB, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 130, 330, 10));

        okayB.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/PNGS/check-mark.png"))); // NOI18N
        okayB.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                okayBMouseClicked(evt);
            }
        });
        panelB.add(okayB, new org.netbeans.lib.awtextra.AbsoluteConstraints(420, 90, -1, 40));

        bg.add(panelB, new org.netbeans.lib.awtextra.AbsoluteConstraints(340, 192, 910, 480));

        ScrollPaneC.setBorder(null);
        ScrollPaneC.setHorizontalScrollBarPolicy(javax.swing.ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
        ScrollPaneC.setVerticalScrollBarPolicy(javax.swing.ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);

        panelC.setBackground(new java.awt.Color(255, 255, 255));
        panelC.setFocusable(false);
        panelC.setMaximumSize(new java.awt.Dimension(950, 1310));
        panelC.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        comp_labelC.setFont(new java.awt.Font("Montserrat", 0, 28)); // NOI18N
        comp_labelC.setForeground(new java.awt.Color(51, 51, 51));
        comp_labelC.setText("Type");
        panelC.add(comp_labelC, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 70, -1, 40));

        comp_nameC.setFont(new java.awt.Font("Roboto Bk", 0, 24)); // NOI18N
        comp_nameC.setForeground(new java.awt.Color(54, 33, 89));
        comp_nameC.setText("> XXXXXXX");
        panelC.add(comp_nameC, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 100, -1, 40));

        changing_for_label.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        changing_for_label.setText("Changing For");
        panelC.add(changing_for_label, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 50, -1, 20));

        midC.setForeground(new java.awt.Color(204, 204, 255));
        panelC.add(midC, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 180, 570, 10));

        from_labelC.setBackground(new java.awt.Color(255, 255, 255));
        from_labelC.setFont(new java.awt.Font("Montserrat", 0, 28)); // NOI18N
        from_labelC.setForeground(new java.awt.Color(51, 51, 51));
        from_labelC.setText("From");
        panelC.add(from_labelC, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 330, -1, 40));

        type_textfieldC.setFont(new java.awt.Font("Roboto", 0, 24)); // NOI18N
        type_textfieldC.setForeground(new java.awt.Color(54, 33, 89));
        type_textfieldC.setBorder(null);
        type_textfieldC.setMargin(new java.awt.Insets(2, 4, 2, 2));
        panelC.add(type_textfieldC, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 260, 320, 40));

        type_labelC.setFont(new java.awt.Font("Montserrat", 0, 28)); // NOI18N
        type_labelC.setForeground(new java.awt.Color(51, 51, 51));
        type_labelC.setText("Competition Type");
        panelC.add(type_labelC, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 220, -1, 40));

        to_labelC.setBackground(new java.awt.Color(255, 255, 255));
        to_labelC.setFont(new java.awt.Font("Montserrat", 0, 28)); // NOI18N
        to_labelC.setForeground(new java.awt.Color(51, 51, 51));
        to_labelC.setText("To");
        panelC.add(to_labelC, new org.netbeans.lib.awtextra.AbsoluteConstraints(490, 330, -1, 40));

        JCO_labelC.setBackground(new java.awt.Color(255, 255, 255));
        JCO_labelC.setFont(new java.awt.Font("Montserrat", 0, 28)); // NOI18N
        JCO_labelC.setForeground(new java.awt.Color(51, 51, 51));
        JCO_labelC.setText("JCO");
        panelC.add(JCO_labelC, new org.netbeans.lib.awtextra.AbsoluteConstraints(490, 440, -1, 40));

        OFFR_labelC.setBackground(new java.awt.Color(255, 255, 255));
        OFFR_labelC.setFont(new java.awt.Font("Montserrat", 0, 28)); // NOI18N
        OFFR_labelC.setForeground(new java.awt.Color(51, 51, 51));
        OFFR_labelC.setText("OFFR");
        panelC.add(OFFR_labelC, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 440, -1, 40));

        year_labelC.setBackground(new java.awt.Color(255, 255, 255));
        year_labelC.setFont(new java.awt.Font("Montserrat", 0, 28)); // NOI18N
        year_labelC.setForeground(new java.awt.Color(51, 51, 51));
        year_labelC.setText("TRG Year");
        panelC.add(year_labelC, new org.netbeans.lib.awtextra.AbsoluteConstraints(490, 220, -1, 40));

        year_textfieldC.setFont(new java.awt.Font("Roboto", 0, 24)); // NOI18N
        year_textfieldC.setForeground(new java.awt.Color(54, 33, 89));
        year_textfieldC.setBorder(null);
        year_textfieldC.setMargin(new java.awt.Insets(2, 4, 2, 2));
        year_textfieldC.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                year_textfieldCActionPerformed(evt);
            }
        });
        panelC.add(year_textfieldC, new org.netbeans.lib.awtextra.AbsoluteConstraints(490, 260, 320, 40));

        OFFR_textfieldC.setFont(new java.awt.Font("Roboto", 0, 24)); // NOI18N
        OFFR_textfieldC.setForeground(new java.awt.Color(54, 33, 89));
        OFFR_textfieldC.setBorder(null);
        OFFR_textfieldC.setMargin(new java.awt.Insets(2, 4, 2, 2));
        OFFR_textfieldC.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                OFFR_textfieldCActionPerformed(evt);
            }
        });
        panelC.add(OFFR_textfieldC, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 480, 320, 40));

        JCO_textfieldC.setFont(new java.awt.Font("Roboto", 0, 24)); // NOI18N
        JCO_textfieldC.setForeground(new java.awt.Color(54, 33, 89));
        JCO_textfieldC.setBorder(null);
        JCO_textfieldC.setMargin(new java.awt.Insets(2, 4, 2, 2));
        JCO_textfieldC.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                JCO_textfieldCActionPerformed(evt);
            }
        });
        panelC.add(JCO_textfieldC, new org.netbeans.lib.awtextra.AbsoluteConstraints(490, 480, 320, 40));

        OR_labelC.setBackground(new java.awt.Color(255, 255, 255));
        OR_labelC.setFont(new java.awt.Font("Montserrat", 0, 28)); // NOI18N
        OR_labelC.setForeground(new java.awt.Color(66, 50, 77));
        OR_labelC.setText("OR");
        panelC.add(OR_labelC, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 550, -1, 40));

        OR_textfieldC.setFont(new java.awt.Font("Roboto", 0, 24)); // NOI18N
        OR_textfieldC.setForeground(new java.awt.Color(54, 33, 89));
        OR_textfieldC.setBorder(null);
        OR_textfieldC.setMargin(new java.awt.Insets(2, 4, 2, 2));
        OR_textfieldC.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                OR_textfieldCActionPerformed(evt);
            }
        });
        panelC.add(OR_textfieldC, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 590, 320, 40));

        panelA_bottom_line2.setForeground(new java.awt.Color(54, 33, 89));
        panelC.add(panelA_bottom_line2, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 890, 910, -1));

        underlineC1.setForeground(new java.awt.Color(54, 33, 89));
        panelC.add(underlineC1, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 300, 320, 20));

        underlineC2.setForeground(new java.awt.Color(54, 33, 89));
        panelC.add(underlineC2, new org.netbeans.lib.awtextra.AbsoluteConstraints(490, 300, 320, 20));

        underlineC6.setForeground(new java.awt.Color(54, 33, 89));
        panelC.add(underlineC6, new org.netbeans.lib.awtextra.AbsoluteConstraints(490, 520, 320, 20));

        underlineC5.setForeground(new java.awt.Color(54, 33, 89));
        panelC.add(underlineC5, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 520, 320, 20));

        underlineC7.setForeground(new java.awt.Color(54, 33, 89));
        panelC.add(underlineC7, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 630, 320, 20));

        from_date_comboboxC.setFont(new java.awt.Font("Roboto", 0, 18)); // NOI18N
        from_date_comboboxC.setForeground(new java.awt.Color(44, 62, 80));
        from_date_comboboxC.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "DD", "01", "02", "03", "04", "05", "06", "07", "08", "09", "10", "11", "12", "13", "14", "15", "16", "17", "18", "19", "20", "21", "22", "23", "24", "25", "26", "27", "28", "29", "30", "31" }));
        from_date_comboboxC.setBorder(null);
        panelC.add(from_date_comboboxC, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 370, 100, 40));

        from_month_comboboxC.setFont(new java.awt.Font("Roboto", 0, 18)); // NOI18N
        from_month_comboboxC.setForeground(new java.awt.Color(44, 62, 80));
        from_month_comboboxC.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "MM", "01", "02", "03", "04", "05", "06", "07", "08", "09", "10", "11", "12" }));
        from_month_comboboxC.setBorder(null);
        panelC.add(from_month_comboboxC, new org.netbeans.lib.awtextra.AbsoluteConstraints(190, 370, 100, 40));

        from_year_comboboxC.setFont(new java.awt.Font("Roboto", 0, 18)); // NOI18N
        from_year_comboboxC.setForeground(new java.awt.Color(44, 62, 80));
        from_year_comboboxC.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "YYYY", "1950", "1951", "1952", "1953", "1954", "1955", "1956", "1957", "1958", "1959", "1960", "1961", "1962", "1963", "1964", "1965", "1966", "1967", "1968", "1969", "1970", "1971", "1972", "1973", "1974", "1975", "1976", "1977", "1978", "1979", "1980", "1981", "1982", "1983", "1984", "1985", "1986", "1987", "1988", "1989", "1990", "1991", "1992", "1993", "1994", "1995", "1996", "1997", "1998", "1999", "2000", "2001", "2002", "2003", "2004", "2005", "2006", "2007", "2008", "2009", "2010", "2011", "2012", "2013", "2014", "2015", "2016", "2017", "2018", "2019", "2020", "2021", "2022", "2023", "2024", "2025", "2026", "2027", "2028", "2029", "2030", "2031", "2032", "2033", "2034", "2035", "2036", "2037", "2038", "2039", "2040", "2041", "2042", "2043", "2044", "2045", "2046", "2047", "2048", "2049", "2050", "2051", "2052", "2053", "2054", "2055", "2056", "2057", "2058", "2059", "2060", "2061", "2062", "2063", "2064", "2065", "2066", "2067", "2068", "2069", "2070", "2071", "2072", "2073", "2074", "2075", "2076", "2077", "2078", "2079", "2080", "2081", "2082", "2083", "2084", "2085", "2086", "2087", "2088", "2089", "2090", "2091", "2092", "2093", "2094", "2095", "2096", "2097", "2098", "2099", "2100", " " }));
        from_year_comboboxC.setBorder(null);
        panelC.add(from_year_comboboxC, new org.netbeans.lib.awtextra.AbsoluteConstraints(300, 370, 100, 40));

        to_date_comboboxC.setFont(new java.awt.Font("Roboto", 0, 18)); // NOI18N
        to_date_comboboxC.setForeground(new java.awt.Color(44, 62, 80));
        to_date_comboboxC.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "DD", "01", "02", "03", "04", "05", "06", "07", "08", "09", "10", "11", "12", "13", "14", "15", "16", "17", "18", "19", "20", "21", "22", "23", "24", "25", "26", "27", "28", "29", "30", "31" }));
        to_date_comboboxC.setBorder(null);
        panelC.add(to_date_comboboxC, new org.netbeans.lib.awtextra.AbsoluteConstraints(490, 370, 100, 40));

        to_month_comboboxC.setFont(new java.awt.Font("Roboto", 0, 18)); // NOI18N
        to_month_comboboxC.setForeground(new java.awt.Color(44, 62, 80));
        to_month_comboboxC.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "MM", "01", "02", "03", "04", "05", "06", "07", "08", "09", "10", "11", "12" }));
        to_month_comboboxC.setBorder(null);
        panelC.add(to_month_comboboxC, new org.netbeans.lib.awtextra.AbsoluteConstraints(600, 370, 100, 40));

        to_year_comboboxC.setFont(new java.awt.Font("Roboto", 0, 18)); // NOI18N
        to_year_comboboxC.setForeground(new java.awt.Color(44, 62, 80));
        to_year_comboboxC.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "YYYY", "1950", "1951", "1952", "1953", "1954", "1955", "1956", "1957", "1958", "1959", "1960", "1961", "1962", "1963", "1964", "1965", "1966", "1967", "1968", "1969", "1970", "1971", "1972", "1973", "1974", "1975", "1976", "1977", "1978", "1979", "1980", "1981", "1982", "1983", "1984", "1985", "1986", "1987", "1988", "1989", "1990", "1991", "1992", "1993", "1994", "1995", "1996", "1997", "1998", "1999", "2000", "2001", "2002", "2003", "2004", "2005", "2006", "2007", "2008", "2009", "2010", "2011", "2012", "2013", "2014", "2015", "2016", "2017", "2018", "2019", "2020", "2021", "2022", "2023", "2024", "2025", "2026", "2027", "2028", "2029", "2030", "2031", "2032", "2033", "2034", "2035", "2036", "2037", "2038", "2039", "2040", "2041", "2042", "2043", "2044", "2045", "2046", "2047", "2048", "2049", "2050", "2051", "2052", "2053", "2054", "2055", "2056", "2057", "2058", "2059", "2060", "2061", "2062", "2063", "2064", "2065", "2066", "2067", "2068", "2069", "2070", "2071", "2072", "2073", "2074", "2075", "2076", "2077", "2078", "2079", "2080", "2081", "2082", "2083", "2084", "2085", "2086", "2087", "2088", "2089", "2090", "2091", "2092", "2093", "2094", "2095", "2096", "2097", "2098", "2099", "2100", " " }));
        to_year_comboboxC.setBorder(null);
        panelC.add(to_year_comboboxC, new org.netbeans.lib.awtextra.AbsoluteConstraints(710, 370, 100, 40));

        from_labelC0.setBackground(new java.awt.Color(255, 255, 255));
        from_labelC0.setFont(new java.awt.Font("Montserrat", 0, 28)); // NOI18N
        from_labelC0.setForeground(new java.awt.Color(51, 51, 51));
        from_labelC0.setText("From");
        panelC.add(from_labelC0, new org.netbeans.lib.awtextra.AbsoluteConstraints(390, 70, -1, 40));

        to_labelC0.setBackground(new java.awt.Color(255, 255, 255));
        to_labelC0.setFont(new java.awt.Font("Montserrat", 0, 28)); // NOI18N
        to_labelC0.setForeground(new java.awt.Color(51, 51, 51));
        to_labelC0.setText("To");
        panelC.add(to_labelC0, new org.netbeans.lib.awtextra.AbsoluteConstraints(610, 70, -1, 40));

        to_dateC.setFont(new java.awt.Font("Roboto Bk", 0, 24)); // NOI18N
        to_dateC.setForeground(new java.awt.Color(54, 33, 89));
        to_dateC.setText("> XXXXXXX");
        panelC.add(to_dateC, new org.netbeans.lib.awtextra.AbsoluteConstraints(610, 100, -1, 40));

        from_dateC.setFont(new java.awt.Font("Roboto Bk", 0, 24)); // NOI18N
        from_dateC.setForeground(new java.awt.Color(54, 33, 89));
        from_dateC.setText("> XXXXXXX");
        panelC.add(from_dateC, new org.netbeans.lib.awtextra.AbsoluteConstraints(390, 100, -1, 40));

        midF4.setForeground(new java.awt.Color(204, 204, 255));
        midF4.setOrientation(javax.swing.SwingConstants.VERTICAL);
        panelC.add(midF4, new org.netbeans.lib.awtextra.AbsoluteConstraints(335, 85, 20, 40));

        POSN_labelA1.setBackground(new java.awt.Color(255, 255, 255));
        POSN_labelA1.setFont(new java.awt.Font("Montserrat", 0, 28)); // NOI18N
        POSN_labelA1.setForeground(new java.awt.Color(66, 50, 77));
        POSN_labelA1.setText("POSN");
        panelC.add(POSN_labelA1, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 680, -1, 40));

        pos2_label1.setBackground(new java.awt.Color(255, 255, 255));
        pos2_label1.setFont(new java.awt.Font("Montserrat", 0, 24)); // NOI18N
        pos2_label1.setForeground(new java.awt.Color(66, 50, 77));
        pos2_label1.setText("2nd");
        panelC.add(pos2_label1, new org.netbeans.lib.awtextra.AbsoluteConstraints(340, 740, -1, 40));

        pos3_label1.setBackground(new java.awt.Color(255, 255, 255));
        pos3_label1.setFont(new java.awt.Font("Montserrat", 0, 24)); // NOI18N
        pos3_label1.setForeground(new java.awt.Color(66, 50, 77));
        pos3_label1.setText("3rd");
        panelC.add(pos3_label1, new org.netbeans.lib.awtextra.AbsoluteConstraints(600, 740, -1, 40));

        pos1_label1.setBackground(new java.awt.Color(255, 255, 255));
        pos1_label1.setFont(new java.awt.Font("Montserrat", 0, 24)); // NOI18N
        pos1_label1.setForeground(new java.awt.Color(66, 50, 77));
        pos1_label1.setText("1st");
        panelC.add(pos1_label1, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 740, -1, 40));

        midA2.setForeground(new java.awt.Color(204, 204, 255));
        panelC.add(midA2, new org.netbeans.lib.awtextra.AbsoluteConstraints(200, 700, 510, 10));

        POS1_textfieldC.setFont(new java.awt.Font("Roboto", 0, 24)); // NOI18N
        POS1_textfieldC.setForeground(new java.awt.Color(54, 33, 89));
        POS1_textfieldC.setBorder(null);
        POS1_textfieldC.setMargin(new java.awt.Insets(2, 4, 2, 2));
        POS1_textfieldC.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                POS1_textfieldCActionPerformed(evt);
            }
        });
        panelC.add(POS1_textfieldC, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 780, 210, 40));

        underline4.setForeground(new java.awt.Color(54, 33, 89));
        panelC.add(underline4, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 820, 210, 20));

        POS2_textfieldC.setFont(new java.awt.Font("Roboto", 0, 24)); // NOI18N
        POS2_textfieldC.setForeground(new java.awt.Color(54, 33, 89));
        POS2_textfieldC.setBorder(null);
        POS2_textfieldC.setMargin(new java.awt.Insets(2, 4, 2, 2));
        POS2_textfieldC.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                POS2_textfieldCActionPerformed(evt);
            }
        });
        panelC.add(POS2_textfieldC, new org.netbeans.lib.awtextra.AbsoluteConstraints(340, 780, 210, 40));

        underline5.setForeground(new java.awt.Color(54, 33, 89));
        panelC.add(underline5, new org.netbeans.lib.awtextra.AbsoluteConstraints(340, 820, 210, 20));

        POS3_textfieldC.setFont(new java.awt.Font("Roboto", 0, 24)); // NOI18N
        POS3_textfieldC.setForeground(new java.awt.Color(54, 33, 89));
        POS3_textfieldC.setBorder(null);
        POS3_textfieldC.setMargin(new java.awt.Insets(2, 4, 2, 2));
        POS3_textfieldC.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                POS3_textfieldCActionPerformed(evt);
            }
        });
        panelC.add(POS3_textfieldC, new org.netbeans.lib.awtextra.AbsoluteConstraints(600, 780, 210, 40));

        underline6.setForeground(new java.awt.Color(54, 33, 89));
        panelC.add(underline6, new org.netbeans.lib.awtextra.AbsoluteConstraints(600, 820, 210, 20));

        ScrollPaneC.setViewportView(panelC);

        bg.add(ScrollPaneC, new org.netbeans.lib.awtextra.AbsoluteConstraints(340, 192, 910, 480));

        insert_buttonA.setForeground(new java.awt.Color(240, 240, 240));
        insert_buttonA.setMaximumSize(new java.awt.Dimension(200, 200));
        insert_buttonA.setMinimumSize(new java.awt.Dimension(200, 200));
        insert_buttonA.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                insert_buttonAMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                insert_buttonAMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                insert_buttonAMouseExited(evt);
            }
        });
        insert_buttonA.setLayout(null);

        insert_labelA.setBackground(new java.awt.Color(54, 33, 89));
        insert_labelA.setFont(new java.awt.Font("Roboto", 0, 14)); // NOI18N
        insert_labelA.setForeground(new java.awt.Color(54, 33, 89));
        insert_labelA.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        insert_labelA.setText("INSERT");
        insert_buttonA.add(insert_labelA);
        insert_labelA.setBounds(30, 0, 120, 50);

        bg.add(insert_buttonA, new org.netbeans.lib.awtextra.AbsoluteConstraints(1070, 120, 180, 50));

        refresh_buttonB.setForeground(new java.awt.Color(240, 240, 240));
        refresh_buttonB.setMaximumSize(new java.awt.Dimension(200, 200));
        refresh_buttonB.setMinimumSize(new java.awt.Dimension(200, 200));
        refresh_buttonB.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                refresh_buttonBMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                refresh_buttonBMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                refresh_buttonBMouseExited(evt);
            }
        });
        refresh_buttonB.setLayout(null);

        refresh_labelB.setBackground(new java.awt.Color(54, 33, 89));
        refresh_labelB.setFont(new java.awt.Font("Roboto", 0, 14)); // NOI18N
        refresh_labelB.setForeground(new java.awt.Color(54, 33, 89));
        refresh_labelB.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        refresh_labelB.setText("REFRESH");
        refresh_buttonB.add(refresh_labelB);
        refresh_labelB.setBounds(50, 0, 80, 50);

        bg.add(refresh_buttonB, new org.netbeans.lib.awtextra.AbsoluteConstraints(870, 120, 180, 50));

        print_buttonB.setForeground(new java.awt.Color(240, 240, 240));
        print_buttonB.setMaximumSize(new java.awt.Dimension(200, 200));
        print_buttonB.setMinimumSize(new java.awt.Dimension(200, 200));
        print_buttonB.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                print_buttonBMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                print_buttonBMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                print_buttonBMouseExited(evt);
            }
        });
        print_buttonB.setLayout(null);

        print_labelB.setBackground(new java.awt.Color(54, 33, 89));
        print_labelB.setFont(new java.awt.Font("Roboto", 0, 14)); // NOI18N
        print_labelB.setForeground(new java.awt.Color(54, 33, 89));
        print_labelB.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        print_labelB.setText("PRINT");
        print_buttonB.add(print_labelB);
        print_labelB.setBounds(50, 0, 80, 50);

        bg.add(print_buttonB, new org.netbeans.lib.awtextra.AbsoluteConstraints(670, 120, 180, 50));

        fullscreen_buttonB.setForeground(new java.awt.Color(240, 240, 240));
        fullscreen_buttonB.setMaximumSize(new java.awt.Dimension(200, 200));
        fullscreen_buttonB.setMinimumSize(new java.awt.Dimension(200, 200));
        fullscreen_buttonB.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                fullscreen_buttonBMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                fullscreen_buttonBMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                fullscreen_buttonBMouseExited(evt);
            }
        });
        fullscreen_buttonB.setLayout(null);

        fullscreen_labelB.setBackground(new java.awt.Color(54, 33, 89));
        fullscreen_labelB.setFont(new java.awt.Font("Roboto", 0, 14)); // NOI18N
        fullscreen_labelB.setForeground(new java.awt.Color(54, 33, 89));
        fullscreen_labelB.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        fullscreen_labelB.setText("FULLSCREEN");
        fullscreen_buttonB.add(fullscreen_labelB);
        fullscreen_labelB.setBounds(30, 0, 120, 50);

        bg.add(fullscreen_buttonB, new org.netbeans.lib.awtextra.AbsoluteConstraints(1070, 120, 180, 50));

        edit_buttonB.setForeground(new java.awt.Color(240, 240, 240));
        edit_buttonB.setMaximumSize(new java.awt.Dimension(200, 200));
        edit_buttonB.setMinimumSize(new java.awt.Dimension(200, 200));
        edit_buttonB.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                edit_buttonBMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                edit_buttonBMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                edit_buttonBMouseExited(evt);
            }
        });
        edit_buttonB.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        edit_labelB.setBackground(new java.awt.Color(54, 33, 89));
        edit_labelB.setFont(new java.awt.Font("Roboto Bk", 0, 14)); // NOI18N
        edit_labelB.setForeground(new java.awt.Color(54, 33, 89));
        edit_labelB.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        edit_labelB.setText("EDIT");
        edit_buttonB.add(edit_labelB, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 0, 80, 50));

        bg.add(edit_buttonB, new org.netbeans.lib.awtextra.AbsoluteConstraints(470, 120, 180, 50));

        update_buttonC.setForeground(new java.awt.Color(240, 240, 240));
        update_buttonC.setMaximumSize(new java.awt.Dimension(200, 200));
        update_buttonC.setMinimumSize(new java.awt.Dimension(200, 200));
        update_buttonC.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                update_buttonCMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                update_buttonCMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                update_buttonCMouseExited(evt);
            }
        });
        update_buttonC.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        update_labelC.setBackground(new java.awt.Color(54, 33, 89));
        update_labelC.setFont(new java.awt.Font("Roboto Bk", 0, 14)); // NOI18N
        update_labelC.setForeground(new java.awt.Color(54, 33, 89));
        update_labelC.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        update_labelC.setText("UPDATE");
        update_buttonC.add(update_labelC, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 0, 120, 50));

        bg.add(update_buttonC, new org.netbeans.lib.awtextra.AbsoluteConstraints(1070, 120, 180, 50));

        getContentPane().add(bg, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 1280, 720));

        fullscreen_panelB.setBackground(new java.awt.Color(255, 255, 255));
        fullscreen_panelB.setMaximumSize(new java.awt.Dimension(1280, 720));
        fullscreen_panelB.setMinimumSize(new java.awt.Dimension(1280, 720));
        fullscreen_panelB.setPreferredSize(new java.awt.Dimension(1280, 720));
        fullscreen_panelB.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        fullscreen_ScrollPaneB.setBackground(new java.awt.Color(255, 255, 255));
        fullscreen_ScrollPaneB.setHorizontalScrollBarPolicy(javax.swing.ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);

        fullscreen_tableB.setFont(new java.awt.Font("Roboto", 0, 18)); // NOI18N
        fullscreen_tableB.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "S. No.", "Comp Type", "TRG year", "From", "To", "OFFR", "JCO", "OR", "Pos 1", "Pos 2", "Pos 3"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        fullscreen_tableB.setAutoResizeMode(javax.swing.JTable.AUTO_RESIZE_ALL_COLUMNS);
        fullscreen_tableB.setFocusable(false);
        fullscreen_tableB.setGridColor(new java.awt.Color(255, 255, 255));
        fullscreen_tableB.setMaximumSize(new java.awt.Dimension(300, 60));
        fullscreen_tableB.setMinimumSize(new java.awt.Dimension(300, 60));
        fullscreen_tableB.setRowHeight(30);
        fullscreen_tableB.setSelectionBackground(new java.awt.Color(54, 33, 89));
        fullscreen_ScrollPaneB.setViewportView(fullscreen_tableB);
        if (fullscreen_tableB.getColumnModel().getColumnCount() > 0) {
            fullscreen_tableB.getColumnModel().getColumn(0).setMinWidth(50);
            fullscreen_tableB.getColumnModel().getColumn(0).setPreferredWidth(50);
            fullscreen_tableB.getColumnModel().getColumn(0).setMaxWidth(50);
        }

        fullscreen_panelB.add(fullscreen_ScrollPaneB, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 40, 1200, 580));

        exit_fullscreen_buttonB.setForeground(new java.awt.Color(240, 240, 240));
        exit_fullscreen_buttonB.setMaximumSize(new java.awt.Dimension(200, 200));
        exit_fullscreen_buttonB.setMinimumSize(new java.awt.Dimension(200, 200));
        exit_fullscreen_buttonB.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                exit_fullscreen_buttonBMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                exit_fullscreen_buttonBMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                exit_fullscreen_buttonBMouseExited(evt);
            }
        });
        exit_fullscreen_buttonB.setLayout(null);

        exit_fullscreen_labelB.setBackground(new java.awt.Color(54, 33, 89));
        exit_fullscreen_labelB.setFont(new java.awt.Font("Roboto Bk", 0, 14)); // NOI18N
        exit_fullscreen_labelB.setForeground(new java.awt.Color(54, 33, 89));
        exit_fullscreen_labelB.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        exit_fullscreen_labelB.setText("GO BACK");
        exit_fullscreen_buttonB.add(exit_fullscreen_labelB);
        exit_fullscreen_labelB.setBounds(30, 0, 120, 50);

        fullscreen_panelB.add(exit_fullscreen_buttonB, new org.netbeans.lib.awtextra.AbsoluteConstraints(1060, 640, 180, 50));

        getContentPane().add(fullscreen_panelB, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, -1, -1));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void formWindowOpened(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowOpened
        //for a centered frame
        Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
        this.setLocation(dim.width / 2 - this.getSize().width / 2, dim.height / 2 - this.getSize().height / 2);
        //for a centered frame

        menuA.setBackground(new java.awt.Color(85, 65, 118));
        menuB.setBackground(new java.awt.Color(64, 43, 100));

        ScrollPaneA.setVisible(true);
        panelB.setVisible(false);
        ScrollPaneC.setVisible(false);
        fullscreen_panelB.setVisible(false);

        go_back = true;
    }//GEN-LAST:event_formWindowOpened

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

    private void go_back_labelMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_go_back_labelMouseClicked
        if (go_back) {
            new Modules(1).setVisible(true);
            this.setVisible(false);

        }

        go_back = false;
    }//GEN-LAST:event_go_back_labelMouseClicked

    private void menuAMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_menuAMouseClicked
        menuA.setBackground(new java.awt.Color(85, 65, 118));
        menuB.setBackground(new java.awt.Color(64, 43, 100));

        insert_buttonA.setVisible(true);
        print_buttonB.setVisible(false);
        edit_buttonB.setVisible(false);
        refresh_buttonB.setVisible(false);
        fullscreen_buttonB.setVisible(false);
        update_buttonC.setVisible(false);

        ScrollPaneA.setVisible(true);
        panelB.setVisible(false);
        ScrollPaneC.setVisible(false);
        fullscreen_panelB.setVisible(false);
//        type_textfieldA.setText("");
//        year_textfieldA.setText("");
//        JCO_textfieldA.setText("");
//        OFFR_textfieldA.setText("");
//        OR_textfieldA.setText("");
//        from_date_comboboxA.setSelectedItem("DD");
//        from_month_comboboxA.setSelectedItem("MM");
//        from_year_comboboxA.setSelectedItem("YYYY");
//        to_date_comboboxA.setSelectedItem("DD");
//        to_month_comboboxA.setSelectedItem("MM");
//        to_year_comboboxA.setSelectedItem("YYYY");

    }//GEN-LAST:event_menuAMouseClicked

    private void menuBMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_menuBMouseClicked
        menuB.setBackground(new java.awt.Color(85, 65, 118));
        menuA.setBackground(new java.awt.Color(64, 43, 100));

        print_buttonB.setVisible(true);
        edit_buttonB.setVisible(true);
        refresh_buttonB.setVisible(true);
        fullscreen_buttonB.setVisible(true);
        insert_buttonA.setVisible(false);
        update_buttonC.setVisible(false);

        ScrollPaneA.setVisible(false);
        panelB.setVisible(true);
        ScrollPaneC.setVisible(false);
        fullscreen_panelB.setVisible(false);
        comp_typeB.setText("");
        viewall();
    }//GEN-LAST:event_menuBMouseClicked

    private void year_textfieldAActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_year_textfieldAActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_year_textfieldAActionPerformed

    private void OFFR_textfieldAActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_OFFR_textfieldAActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_OFFR_textfieldAActionPerformed

    private void JCO_textfieldAActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_JCO_textfieldAActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_JCO_textfieldAActionPerformed

    private void OR_textfieldAActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_OR_textfieldAActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_OR_textfieldAActionPerformed

    private void okayBMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_okayBMouseClicked
        if (comp_typeB.getText().trim().equals("")) {
            viewByCompType();
        } else {
            viewall();
        }

    }//GEN-LAST:event_okayBMouseClicked

    private void insert_buttonAMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_insert_buttonAMouseClicked
        // TODO add your handling code here:
        String query = "";
        String comp_type = type_textfieldA.getText().trim();
        String trg_year = year_textfieldA.getText().trim();
        String JCO = JCO_textfieldA.getText().trim();
        String OFFR = OFFR_textfieldA.getText().trim();
        String OR = OR_textfieldA.getText().trim();
        String from_date = (String) from_date_comboboxA.getSelectedItem();
        String from_month = (String) from_month_comboboxA.getSelectedItem();
        String from_year = (String) from_year_comboboxA.getSelectedItem();
        String to_date = (String) to_date_comboboxA.getSelectedItem();
        String to_month = (String) to_month_comboboxA.getSelectedItem();
        String to_year = (String) to_year_comboboxA.getSelectedItem();
        String from_day = validate_date(from_date, from_month, from_year);
        String to_day = validate_date(to_date, to_month, to_year);
        String pos1 = null, pos2 = null, pos3 = null;
        boolean flag = false;

        if (!type_textfieldA.getText().trim().equals("")) {
            if (year_textfieldA.getText().trim().equals("")) {
                query = query + " TRG Year field cannot be empty!\n";
                flag = true;
            }
            if (from_day.equals("")) {
                query = query + "From date is not valid!\n";
                flag = true;
            }
            if (to_day.equals("")) {
                query = query + "To date is not valid!\n";
                flag = true;
            }
            if (OFFR_textfieldA.getText().trim().equals("")) {
                query = query + " OFFR Year field cannot be empty!\n";
                flag = true;
            }
            if (JCO_textfieldA.getText().trim().equals("")) {
                query = query + " JCO Year field cannot be empty!\n";
                flag = true;
            }
            if (OR_textfieldA.getText().trim().equals("")) {
                query = query + " OR Year field cannot be empty!\n";
                flag = true;
            }
            if (flag == true) {
                JOptionPane.showMessageDialog(null, query, "Error", JOptionPane.ERROR_MESSAGE);

            } else {
                try {
                    PreparedStatement pstmt;
                    if (!pos1_textfield.getText().trim().equals("")) {
                        pos1 = pos1_textfield.getText().trim();
                    }
                    if (!pos2_textfield.getText().trim().equals("")) {
                        pos2 = pos2_textfield.getText().trim();
                    }
                    if (!pos3_textfield.getText().trim().equals("")) {
                        pos3 = pos3_textfield.getText().trim();
                    }

                    pstmt = con.prepareStatement("insert into Competition_Record values(?, ?, ?, ?, ?, ?, ?, ?, ?, ?)");
                    pstmt.setString(1, comp_type);
                    pstmt.setString(2, trg_year);
                    pstmt.setString(3, from_day);
                    pstmt.setString(4, to_day);
                    pstmt.setString(5, OFFR);
                    pstmt.setString(6, JCO);
                    pstmt.setString(7, OR);
                    pstmt.setString(8, pos1);
                    pstmt.setString(9, pos2);
                    pstmt.setString(10, pos3);

                    pstmt.executeUpdate();
                    JOptionPane.showMessageDialog(null, "Entry Successful", "Successful", JOptionPane.WARNING_MESSAGE);

                    type_textfieldA.setText("");
                    year_textfieldA.setText("");
                    JCO_textfieldA.setText("");
                    OFFR_textfieldA.setText("");
                    OR_textfieldA.setText("");
                    from_date_comboboxA.setSelectedItem("DD");
                    from_month_comboboxA.setSelectedItem("MM");
                    from_year_comboboxA.setSelectedItem("YYYY");
                    to_date_comboboxA.setSelectedItem("DD");
                    to_month_comboboxA.setSelectedItem("MM");
                    to_year_comboboxA.setSelectedItem("YYYY");
                    pos1_textfield.setText("");
                    pos2_textfield.setText("");
                    pos3_textfield.setText("");
                } catch (java.sql.SQLIntegrityConstraintViolationException sqle) {
                    JOptionPane.showMessageDialog(null, "Competiton Type, From and To combination already exists", "Successful", JOptionPane.WARNING_MESSAGE);
                    System.out.println(sqle);
                } catch (Exception e) {
                    JOptionPane.showMessageDialog(null, e);

                }

            }

        } else {
            JOptionPane.showMessageDialog(null, "Competition Type cannot be empty!", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_insert_buttonAMouseClicked

    private void insert_buttonAMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_insert_buttonAMouseEntered
        insert_buttonA.setBackground(new java.awt.Color(237, 224, 255));
    }//GEN-LAST:event_insert_buttonAMouseEntered

    private void insert_buttonAMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_insert_buttonAMouseExited
        insert_buttonA.setBackground(new java.awt.Color(240, 240, 240));
    }//GEN-LAST:event_insert_buttonAMouseExited

    private void refresh_buttonBMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_refresh_buttonBMouseClicked
        if (!comp_typeB.getText().trim().equals("")) {
            viewByCompType();
        } else {
            viewall();
        }
    }//GEN-LAST:event_refresh_buttonBMouseClicked

    private void refresh_buttonBMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_refresh_buttonBMouseEntered
        refresh_buttonB.setBackground(new java.awt.Color(237, 224, 255));
    }//GEN-LAST:event_refresh_buttonBMouseEntered

    private void refresh_buttonBMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_refresh_buttonBMouseExited
        refresh_buttonB.setBackground(new java.awt.Color(240, 240, 240));
    }//GEN-LAST:event_refresh_buttonBMouseExited

    private void print_buttonBMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_print_buttonBMouseClicked
        try {
            Statement statement = con.createStatement();
            ResultSet resultSet = statement.executeQuery("select * from Competition_record");
            XSSFWorkbook workbook = new XSSFWorkbook();
            XSSFSheet spreadsheet = null;
            spreadsheet = workbook.createSheet("Competition_record");

            XSSFRow row = spreadsheet.createRow(0);
            XSSFCell cell;
            cell = row.createCell(0);
            cell.setCellValue("Competition Type");
            cell = row.createCell(1);
            cell.setCellValue("TRG Year");
            cell = row.createCell(2);
            cell.setCellValue("From");
            cell = row.createCell(3);
            cell.setCellValue("To");
            cell = row.createCell(4);
            cell.setCellValue("Offr");
            cell = row.createCell(5);
            cell.setCellValue("JCO");
            cell = row.createCell(6);
            cell.setCellValue("OR");
            cell = row.createCell(7);
            cell.setCellValue("Pos 1");
            cell = row.createCell(8);
            cell.setCellValue("Pos 2");
            cell = row.createCell(9);
            cell.setCellValue("Pos 3");

            int i = 1;

            while (resultSet.next()) {
                row = spreadsheet.createRow(i);
                cell = row.createCell(0);
                cell.setCellValue(resultSet.getString("comp_type"));
                cell = row.createCell(1);
                cell.setCellValue(resultSet.getString("trg_year"));
                cell = row.createCell(2);
                cell.setCellValue(resultSet.getString("from_date"));
                cell = row.createCell(3);
                cell.setCellValue(resultSet.getString("to_date"));
                cell = row.createCell(4);
                cell.setCellValue(resultSet.getString("offr"));
                cell = row.createCell(5);
                cell.setCellValue(resultSet.getString("JCO"));
                cell = row.createCell(6);
                cell.setCellValue(resultSet.getString("OR_"));
                cell = row.createCell(7);
                cell.setCellValue(resultSet.getString("Pos1"));
                cell = row.createCell(8);
                cell.setCellValue(resultSet.getString("Pos2"));
                cell = row.createCell(9);
                cell.setCellValue(resultSet.getString("Pos3f"));
                i++;
            }
            path_file paths = new path_file();
            FileOutputStream out = new FileOutputStream(new File(paths.csv + "\\Competition_record.xlsx"));
            workbook.write(out);
            JOptionPane.showMessageDialog(null, "Competition_record.xlsx written successfully");
            Desktop.getDesktop().open(new File(paths.csv + "\\Competition_record.xlsx"));

        } catch (Exception e) {
            System.err.format("Error! Cannot Print.", e.getMessage());
        }
    }//GEN-LAST:event_print_buttonBMouseClicked

    private void print_buttonBMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_print_buttonBMouseEntered
        print_buttonB.setBackground(new java.awt.Color(237, 224, 255));
    }//GEN-LAST:event_print_buttonBMouseEntered

    private void print_buttonBMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_print_buttonBMouseExited
        print_buttonB.setBackground(new java.awt.Color(240, 240, 240));
    }//GEN-LAST:event_print_buttonBMouseExited

    private void fullscreen_buttonBMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_fullscreen_buttonBMouseClicked
        if (!comp_typeB.getText().trim().equals("")) {
            if (viewByCompType()) {
                fullscreen_panelB.setVisible(true);
                bg.setVisible(false);
            }
        } else {
            viewall();
            fullscreen_panelB.setVisible(true);
            bg.setVisible(false);
        }


    }//GEN-LAST:event_fullscreen_buttonBMouseClicked

    private void fullscreen_buttonBMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_fullscreen_buttonBMouseEntered
        fullscreen_buttonB.setBackground(new java.awt.Color(237, 224, 255));
    }//GEN-LAST:event_fullscreen_buttonBMouseEntered

    private void fullscreen_buttonBMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_fullscreen_buttonBMouseExited
        fullscreen_buttonB.setBackground(new java.awt.Color(240, 240, 240));
    }//GEN-LAST:event_fullscreen_buttonBMouseExited

    private void edit_buttonBMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_edit_buttonBMouseClicked
        int row = TableB.getSelectedRow();
        if (row == -1) {
            JOptionPane.showMessageDialog(null, "Please select a row to update", "Error", JOptionPane.ERROR_MESSAGE);
        } else {
            insert_buttonA.setVisible(false);
            print_buttonB.setVisible(false);
            edit_buttonB.setVisible(false);
            refresh_buttonB.setVisible(false);
            fullscreen_buttonB.setVisible(false);
            update_buttonC.setVisible(true);

            ScrollPaneA.setVisible(false);
            panelB.setVisible(false);
            ScrollPaneC.setVisible(true);
            fullscreen_panelB.setVisible(false);
            editingData(row);
        }
    }//GEN-LAST:event_edit_buttonBMouseClicked

    private void edit_buttonBMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_edit_buttonBMouseEntered
        edit_buttonB.setBackground(new java.awt.Color(237, 224, 255));
    }//GEN-LAST:event_edit_buttonBMouseEntered

    private void edit_buttonBMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_edit_buttonBMouseExited
        edit_buttonB.setBackground(new java.awt.Color(240, 240, 240));
    }//GEN-LAST:event_edit_buttonBMouseExited

    private void update_buttonCMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_update_buttonCMouseClicked
        // TODO add your handling code here:
        String query = "";
        String comp_type = type_textfieldC.getText().trim();
        String trg_year = year_textfieldC.getText().trim();
        String JCO = JCO_textfieldC.getText().trim();
        String OFFR = OFFR_textfieldC.getText().trim();
        String OR = OR_textfieldC.getText().trim();
        String from_date = (String) from_date_comboboxC.getSelectedItem();
        String from_month = (String) from_month_comboboxC.getSelectedItem();
        String from_year = (String) from_year_comboboxC.getSelectedItem();
        String to_date = (String) to_date_comboboxC.getSelectedItem();
        String to_month = (String) to_month_comboboxC.getSelectedItem();
        String to_year = (String) to_year_comboboxC.getSelectedItem();
        String from_day = validate_date(from_date, from_month, from_year);
        String to_day = validate_date(to_date, to_month, to_year);
        String from_date_label = from_dateC.getText().trim().replace("-", "");
        String to_date_label = to_dateC.getText().trim().replace("-", "");
        String pos1 = null, pos2 = null, pos3 = null;
        boolean flag = false;

        if (!comp_type.equals("")) {
            if (trg_year.equals("")) {
                query = query + " TRG Year field cannot be empty!\n";
                flag = true;
            }
            if (from_day.equals("")) {
                query = query + "From date is not valid!\n";
                flag = true;
            }
            if (to_day.equals("")) {
                query = query + "To date is not valid!\n";
                flag = true;
            }
            if (OFFR.equals("")) {
                query = query + " OFFR Year field cannot be empty!\n";
                flag = true;
            }
            if (JCO.equals("")) {
                query = query + " JCO Year field cannot be empty!\n";
                flag = true;
            }
            if (OR.equals("")) {
                query = query + " OR Year field cannot be empty!\n";
                flag = true;
            }
            if (flag == true) {
                JOptionPane.showMessageDialog(null, query, "Error", JOptionPane.ERROR_MESSAGE);

            } else {
                try {
                    PreparedStatement pstmt;
                    if (!POS1_textfieldC.getText().trim().equals("")) {
                        pos1 = POS1_textfieldC.getText().trim();
                    }
                    if (!POS2_textfieldC.getText().trim().equals("")) {
                        pos2 = POS2_textfieldC.getText().trim();
                    }
                    if (!POS3_textfieldC.getText().trim().equals("")) {
                        pos3 = POS3_textfieldC.getText().trim();
                    }

                    pstmt = con.prepareStatement("update Competition_Record set comp_type = ?, trg_year = ?, from_date = ?, to_date = ?, OFFR = ?, JCO = ?, OR_ = ?, pos1 = ?, pos2 = ?, pos3 = ? where comp_type=? and from_date=? and to_date=?");
                    pstmt.setString(1, comp_type);
                    pstmt.setString(2, trg_year);
                    pstmt.setString(3, from_day);
                    pstmt.setString(4, to_day);
                    pstmt.setString(5, OFFR);
                    pstmt.setString(6, JCO);
                    pstmt.setString(7, OR);
                    pstmt.setString(8, pos1);
                    pstmt.setString(9, pos2);
                    pstmt.setString(10, pos3);
                    pstmt.setString(11, comp_nameC.getText().trim());
                    pstmt.setString(12, from_date_label);
                    pstmt.setString(13, to_date_label);

                    pstmt.executeUpdate();
                    JOptionPane.showMessageDialog(null, "Updation Successful", "Successful", JOptionPane.WARNING_MESSAGE);
                    menuBMouseClicked(null);

                    type_textfieldC.setText("");
                    year_textfieldC.setText("");
                    JCO_textfieldC.setText("");
                    OFFR_textfieldC.setText("");
                    OR_textfieldC.setText("");
                    from_date_comboboxC.setSelectedItem("DD");
                    from_month_comboboxC.setSelectedItem("MM");
                    from_year_comboboxC.setSelectedItem("YYYY");
                    to_date_comboboxC.setSelectedItem("DD");
                    to_month_comboboxC.setSelectedItem("MM");
                    to_year_comboboxC.setSelectedItem("YYYY");
                    POS1_textfieldC.setText("");
                    POS2_textfieldC.setText("");
                    POS3_textfieldC.setText("");
                    comp_nameC.setText("> XXXXXXX");
                    from_dateC.setText("> XXXXXXX");
                    to_dateC.setText("> XXXXXXX");

                } catch (java.sql.SQLIntegrityConstraintViolationException sqle) {
                    JOptionPane.showMessageDialog(null, "Competiton type, FROM and TO combination already exists", "Successful", JOptionPane.WARNING_MESSAGE);
                    System.out.println(sqle);
                } catch (Exception e) {
                    JOptionPane.showMessageDialog(null, e);

                }

            }

        } else {
            JOptionPane.showMessageDialog(null, "Competition Type cannot be empty!", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_update_buttonCMouseClicked

    private void update_buttonCMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_update_buttonCMouseEntered
        update_buttonC.setBackground(new java.awt.Color(237, 224, 255));
    }//GEN-LAST:event_update_buttonCMouseEntered

    private void update_buttonCMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_update_buttonCMouseExited
        update_buttonC.setBackground(new java.awt.Color(240, 240, 240));
    }//GEN-LAST:event_update_buttonCMouseExited

    private void move_panelMouseDragged(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_move_panelMouseDragged
        this.setLocation(this.getX() + evt.getX() - mouseX, this.getY() + evt.getY() - mouseY);
    }//GEN-LAST:event_move_panelMouseDragged

    private void move_panelMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_move_panelMousePressed
        mouseX = evt.getX();
        mouseY = evt.getY();
    }//GEN-LAST:event_move_panelMousePressed

    private void exit_fullscreen_buttonBMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_exit_fullscreen_buttonBMouseClicked
        fullscreen_panelB.setVisible(false);
        bg.setVisible(true);
    }//GEN-LAST:event_exit_fullscreen_buttonBMouseClicked

    private void exit_fullscreen_buttonBMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_exit_fullscreen_buttonBMouseEntered
        exit_fullscreen_buttonB.setBackground(new java.awt.Color(237, 224, 255));
    }//GEN-LAST:event_exit_fullscreen_buttonBMouseEntered

    private void exit_fullscreen_buttonBMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_exit_fullscreen_buttonBMouseExited
        exit_fullscreen_buttonB.setBackground(new java.awt.Color(240, 240, 240));
    }//GEN-LAST:event_exit_fullscreen_buttonBMouseExited

    private void year_textfieldCActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_year_textfieldCActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_year_textfieldCActionPerformed

    private void OFFR_textfieldCActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_OFFR_textfieldCActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_OFFR_textfieldCActionPerformed

    private void JCO_textfieldCActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_JCO_textfieldCActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_JCO_textfieldCActionPerformed

    private void OR_textfieldCActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_OR_textfieldCActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_OR_textfieldCActionPerformed

    private void pos1_textfieldActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_pos1_textfieldActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_pos1_textfieldActionPerformed

    private void pos2_textfieldActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_pos2_textfieldActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_pos2_textfieldActionPerformed

    private void pos3_textfieldActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_pos3_textfieldActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_pos3_textfieldActionPerformed

    private void current_dateAMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_current_dateAMouseClicked
        current_date("from");
    }//GEN-LAST:event_current_dateAMouseClicked

    private void current_dateAMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_current_dateAMouseEntered
        current_dateA.setForeground(new java.awt.Color(54, 33, 89));
        current_dateA.setBackground(new java.awt.Color(237, 224, 255));
    }//GEN-LAST:event_current_dateAMouseEntered

    private void current_dateAMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_current_dateAMouseExited
        current_dateA.setForeground(new java.awt.Color(60, 63, 65));
        current_dateA.setBackground(new java.awt.Color(255, 255, 255));
    }//GEN-LAST:event_current_dateAMouseExited

    private void current_dateA1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_current_dateA1MouseClicked
        // TODO add your handling code here:
        current_date("to");
    }//GEN-LAST:event_current_dateA1MouseClicked

    private void current_dateA1MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_current_dateA1MouseEntered
        current_dateA1.setForeground(new java.awt.Color(54, 33, 89));
        current_dateA1.setBackground(new java.awt.Color(237, 224, 255));
    }//GEN-LAST:event_current_dateA1MouseEntered

    private void current_dateA1MouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_current_dateA1MouseExited
        current_dateA1.setForeground(new java.awt.Color(60, 63, 65));
        current_dateA1.setBackground(new java.awt.Color(255, 255, 255));
    }//GEN-LAST:event_current_dateA1MouseExited

    private void POS1_textfieldCActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_POS1_textfieldCActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_POS1_textfieldCActionPerformed

    private void POS2_textfieldCActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_POS2_textfieldCActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_POS2_textfieldCActionPerformed

    private void POS3_textfieldCActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_POS3_textfieldCActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_POS3_textfieldCActionPerformed

    private void TableBMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_TableBMouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_TableBMouseClicked

    private void comp_typeBKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_comp_typeBKeyPressed
        if (evt.getKeyCode() == java.awt.event.KeyEvent.VK_ENTER) {
            okayBMouseClicked(null);
        }
    }//GEN-LAST:event_comp_typeBKeyPressed

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
            java.util.logging.Logger.getLogger(Competition_Record.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Competition_Record.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Competition_Record.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Competition_Record.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Competition_Record().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel JCO_labelA;
    private javax.swing.JLabel JCO_labelC;
    private javax.swing.JTextField JCO_textfieldA;
    private javax.swing.JTextField JCO_textfieldC;
    private javax.swing.JLabel OFFR_labelA;
    private javax.swing.JLabel OFFR_labelC;
    private javax.swing.JTextField OFFR_textfieldA;
    private javax.swing.JTextField OFFR_textfieldC;
    private javax.swing.JLabel OR_labelA;
    private javax.swing.JLabel OR_labelC;
    private javax.swing.JTextField OR_textfieldA;
    private javax.swing.JTextField OR_textfieldC;
    private javax.swing.JTextField POS1_textfieldC;
    private javax.swing.JTextField POS2_textfieldC;
    private javax.swing.JTextField POS3_textfieldC;
    private javax.swing.JLabel POSN_labelA;
    private javax.swing.JLabel POSN_labelA1;
    private javax.swing.JSeparator POS_underline1;
    private javax.swing.JSeparator POS_underline2;
    private javax.swing.JSeparator POS_underline3;
    private javax.swing.JScrollPane ScrollPaneA;
    private javax.swing.JScrollPane ScrollPaneB;
    private javax.swing.JScrollPane ScrollPaneC;
    private javax.swing.JSeparator Separator;
    private javax.swing.JTable TableB;
    private javax.swing.JPanel bg;
    private javax.swing.JLabel changing_for_label;
    private javax.swing.JLabel comp_labelB;
    private javax.swing.JLabel comp_labelC;
    private javax.swing.JLabel comp_nameC;
    private javax.swing.JTextField comp_typeB;
    private javax.swing.JSeparator comp_type_underlineB;
    private javax.swing.JLabel current_dateA;
    private javax.swing.JLabel current_dateA1;
    private javax.swing.JPanel edit_buttonB;
    private javax.swing.JLabel edit_labelB;
    private javax.swing.JPanel exit_fullscreen_buttonB;
    private javax.swing.JLabel exit_fullscreen_labelB;
    private javax.swing.JLabel exit_icon;
    private javax.swing.JLabel from_dateC;
    private javax.swing.JComboBox<String> from_date_comboboxA;
    private javax.swing.JComboBox<String> from_date_comboboxC;
    private javax.swing.JLabel from_labelA;
    private javax.swing.JLabel from_labelC;
    private javax.swing.JLabel from_labelC0;
    private javax.swing.JComboBox<String> from_month_comboboxA;
    private javax.swing.JComboBox<String> from_month_comboboxC;
    private javax.swing.JComboBox<String> from_year_comboboxA;
    private javax.swing.JComboBox<String> from_year_comboboxC;
    private javax.swing.JScrollPane fullscreen_ScrollPaneB;
    private javax.swing.JPanel fullscreen_buttonB;
    private javax.swing.JLabel fullscreen_labelB;
    private javax.swing.JPanel fullscreen_panelB;
    private javax.swing.JTable fullscreen_tableB;
    private javax.swing.JLabel go_back_label;
    private javax.swing.JPanel insert_buttonA;
    private javax.swing.JLabel insert_labelA;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JPanel menuA;
    private javax.swing.JLabel menuA_icon;
    private javax.swing.JLabel menuA_label;
    private javax.swing.JPanel menuB;
    private javax.swing.JLabel menuB_icon;
    private javax.swing.JLabel menuB_label;
    private javax.swing.JSeparator midA1;
    private javax.swing.JSeparator midA2;
    private javax.swing.JSeparator midC;
    private javax.swing.JSeparator midF4;
    private javax.swing.JLabel minimize;
    private javax.swing.JPanel move_panel;
    private javax.swing.JLabel okayB;
    private javax.swing.JPanel panelA;
    private javax.swing.JSeparator panelA_bottom_line;
    private javax.swing.JSeparator panelA_bottom_line2;
    private javax.swing.JPanel panelB;
    private javax.swing.JPanel panelC;
    private javax.swing.JLabel pos1_label1;
    private javax.swing.JLabel pos1_labelA;
    private javax.swing.JTextField pos1_textfield;
    private javax.swing.JLabel pos2_label1;
    private javax.swing.JLabel pos2_labelA;
    private javax.swing.JTextField pos2_textfield;
    private javax.swing.JLabel pos3_label1;
    private javax.swing.JLabel pos3_labelA;
    private javax.swing.JTextField pos3_textfield;
    private javax.swing.JPanel print_buttonB;
    private javax.swing.JLabel print_labelB;
    private javax.swing.JPanel refresh_buttonB;
    private javax.swing.JLabel refresh_labelB;
    private javax.swing.JPanel side_pane;
    private javax.swing.JLabel title_icon;
    private javax.swing.JLabel title_label;
    private javax.swing.JLabel title_label1;
    private javax.swing.JLabel to_dateC;
    private javax.swing.JComboBox<String> to_date_comboboxA;
    private javax.swing.JComboBox<String> to_date_comboboxC;
    private javax.swing.JLabel to_labelA;
    private javax.swing.JLabel to_labelC;
    private javax.swing.JLabel to_labelC0;
    private javax.swing.JComboBox<String> to_month_comboboxA;
    private javax.swing.JComboBox<String> to_month_comboboxC;
    private javax.swing.JComboBox<String> to_year_comboboxA;
    private javax.swing.JComboBox<String> to_year_comboboxC;
    private javax.swing.JLabel type_labelA;
    private javax.swing.JLabel type_labelC;
    private javax.swing.JTextField type_textfieldA;
    private javax.swing.JTextField type_textfieldC;
    private javax.swing.JSeparator underline4;
    private javax.swing.JSeparator underline5;
    private javax.swing.JSeparator underline6;
    private javax.swing.JSeparator underlineA1;
    private javax.swing.JSeparator underlineA2;
    private javax.swing.JSeparator underlineA5;
    private javax.swing.JSeparator underlineA6;
    private javax.swing.JSeparator underlineA7;
    private javax.swing.JSeparator underlineC1;
    private javax.swing.JSeparator underlineC2;
    private javax.swing.JSeparator underlineC5;
    private javax.swing.JSeparator underlineC6;
    private javax.swing.JSeparator underlineC7;
    private javax.swing.JPanel update_buttonC;
    private javax.swing.JLabel update_labelC;
    private javax.swing.JLabel year_labelA;
    private javax.swing.JLabel year_labelC;
    private javax.swing.JTextField year_textfieldA;
    private javax.swing.JTextField year_textfieldC;
    // End of variables declaration//GEN-END:variables
}
