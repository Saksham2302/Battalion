
import java.awt.Desktop;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.io.File;
import java.io.FileOutputStream;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.sql.PreparedStatement;
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
public class State_AMN extends javax.swing.JFrame {

    private String nomenclature;
    private boolean go_back = true;
    Database db = new Database();
    Connection con = db.create_connection(true);
    /**
     * Creates new form State_AMN
     */
    DefaultTableModel model_table, fullscreen_table;
    private int mouseX, mouseY;

    public State_AMN() {

        initComponents();
        
        ScrollPaneA.getVerticalScrollBar().setUnitIncrement(16);
        ScrollPaneB.getVerticalScrollBar().setUnitIncrement(16);
        ScrollPaneC.getVerticalScrollBar().setUnitIncrement(16);
        fullscreen_ScrollPaneB.getVerticalScrollBar().setUnitIncrement(16);
        

        insert_buttonA.setVisible(true);
        print_buttonB.setVisible(false);
        edit_buttonB.setVisible(false);
        refresh_buttonB.setVisible(false);
        fullscreen_buttonB.setVisible(false);
        model_table = (DefaultTableModel) TableB.getModel();
        fullscreen_table = (DefaultTableModel) fullscreen_tableB.getModel();

    }

    private boolean viewByNomenclature() {
        boolean flag = false;
        try {
            Statement stmt = con.createStatement();
            nomenclature = nomenclatureB.getText().trim();

            if (nomenclature.length() != 0) {
                String Query = "Select auth, on_demand, defi, cont_no, cont_dt, remarks from State_AMN where nomenclature='" + nomenclature + "'";
                ResultSet rs = stmt.executeQuery(Query);
                String auth, demand, defi, cont_no, cont_dt, remarks;
                int sno = 0;
                model_table.setRowCount(0);
                fullscreen_table.setRowCount(0);
                while (rs.next()) {
                    flag = true;
                    sno++;
                    auth = rs.getString("auth");
                    demand = rs.getString("on_demand");
                    defi = rs.getString("defi");
                    cont_no = rs.getString("cont_no");
                    cont_dt = rs.getString("cont_dt");
                    remarks = rs.getString("remarks");

                    model_table.addRow(new Object[]{
                        sno, nomenclature, auth, demand, defi, cont_no, cont_dt, remarks
                    });
                    fullscreen_table.addRow(new Object[]{
                        sno, nomenclature, auth, demand, defi, cont_no, cont_dt, remarks
                    });
                }
            }

            if (flag == false) {
                JOptionPane.showMessageDialog(null, "Nomenclature does not exists!", "Error", JOptionPane.ERROR_MESSAGE);

            }

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
        }
        return flag;
    }

    private void viewall() {
        try {
            Statement stmt = con.createStatement();
            String Query = "Select * from State_AMN;";
            ResultSet rs = stmt.executeQuery(Query);
            String nomen, auth, demand, defi, cont_no, cont_dt, remarks;
            int sno = 0;
            model_table.setRowCount(0);
            fullscreen_table.setRowCount(0);
            while (rs.next()) {
                sno++;
                nomen = rs.getString("Nomenclature");
                auth = rs.getString("auth");
                demand = rs.getString("on_demand");
                defi = rs.getString("defi");
                cont_no = rs.getString("cont_no");
                cont_dt = rs.getString("cont_dt");
                remarks = rs.getString("remarks");

                model_table.addRow(new Object[]{
                    sno, nomen, auth, demand, defi, cont_no, cont_dt, remarks
                });
                fullscreen_table.addRow(new Object[]{
                    sno, nomen, auth, demand, defi, cont_no, cont_dt, remarks
                });
            }

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
        }
    }

    private void editingData(int row) {
        String nomen = (String) TableB.getValueAt(row, 1);
        nomenclature_textfieldC.setText(nomen);
        nomenclatureC.setText(nomen);
        auth_textfieldC.setText((String) TableB.getValueAt(row, 2));
        on_demand_textfieldC.setText((String) TableB.getValueAt(row, 3));
        DEFI_textfieldC.setText((String) TableB.getValueAt(row, 4));
        CONT_NO_textfieldC.setText((String) TableB.getValueAt(row, 5));
        CONT_DT_textfieldC.setText((String) TableB.getValueAt(row, 6));
        remarks_textfieldC.setText((String) TableB.getValueAt(row, 7));
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
        title_label = new javax.swing.JLabel();
        title_icon = new javax.swing.JLabel();
        menuB = new javax.swing.JPanel();
        menuB_icon = new javax.swing.JLabel();
        menuB_label = new javax.swing.JLabel();
        ScrollPaneA = new javax.swing.JScrollPane();
        panelA = new javax.swing.JPanel();
        remarks_labelA = new javax.swing.JLabel();
        on_demand_labelA = new javax.swing.JLabel();
        on_demand_textfieldA = new javax.swing.JTextField();
        underlineA2 = new javax.swing.JSeparator();
        remarks_textfieldA = new javax.swing.JTextField();
        underlineA1 = new javax.swing.JSeparator();
        nomenclature_textfieldA = new javax.swing.JTextField();
        nomenclature_labelA = new javax.swing.JLabel();
        DEFI_labelA = new javax.swing.JLabel();
        DEFI_textfieldA = new javax.swing.JTextField();
        underlineA3 = new javax.swing.JSeparator();
        CONT_DT_labelA = new javax.swing.JLabel();
        CONT_NO_labelA = new javax.swing.JLabel();
        auth_labelA = new javax.swing.JLabel();
        auth_textfieldA = new javax.swing.JTextField();
        underlineA4 = new javax.swing.JSeparator();
        underlineA5 = new javax.swing.JSeparator();
        CONT_NO_textfieldA = new javax.swing.JTextField();
        underlineA6 = new javax.swing.JSeparator();
        CONT_DT_textfieldA = new javax.swing.JTextField();
        underlineA7 = new javax.swing.JSeparator();
        panelB = new javax.swing.JPanel();
        nomenclature_labelB = new javax.swing.JLabel();
        nomenclatureB = new javax.swing.JTextField();
        nomenclature_underlineB = new javax.swing.JSeparator();
        okayB = new javax.swing.JLabel();
        ScrollPaneB = new javax.swing.JScrollPane();
        TableB = new javax.swing.JTable();
        ScrollPaneC = new javax.swing.JScrollPane();
        panelC = new javax.swing.JPanel();
        nomenclature_labelC0 = new javax.swing.JLabel();
        nomenclatureC = new javax.swing.JLabel();
        changing_for_label = new javax.swing.JLabel();
        panelA_bottom_line1 = new javax.swing.JSeparator();
        midC = new javax.swing.JSeparator();
        remarks_labelC = new javax.swing.JLabel();
        on_demand_labelC = new javax.swing.JLabel();
        on_demand_textfieldC = new javax.swing.JTextField();
        underlineA8 = new javax.swing.JSeparator();
        remarks_textfieldC = new javax.swing.JTextField();
        underlineA9 = new javax.swing.JSeparator();
        nomenclature_textfieldC = new javax.swing.JTextField();
        nomenclature_labelC = new javax.swing.JLabel();
        DEFI_labelC = new javax.swing.JLabel();
        DEFI_textfieldC = new javax.swing.JTextField();
        underlineA10 = new javax.swing.JSeparator();
        CONT_DT_labelC = new javax.swing.JLabel();
        CONT_NO_labelC = new javax.swing.JLabel();
        auth_labelC = new javax.swing.JLabel();
        auth_textfieldC = new javax.swing.JTextField();
        underlineA11 = new javax.swing.JSeparator();
        underlineA12 = new javax.swing.JSeparator();
        CONT_NO_textfieldC = new javax.swing.JTextField();
        underlineA13 = new javax.swing.JSeparator();
        CONT_DT_textfieldC = new javax.swing.JTextField();
        underlineA14 = new javax.swing.JSeparator();
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
        exit_fullscreen_buttonB = new javax.swing.JPanel();
        exit_fullscreen_labelB = new javax.swing.JLabel();
        fullscreen_ScrollPaneB = new javax.swing.JScrollPane();
        fullscreen_tableB = new javax.swing.JTable();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setBackground(new java.awt.Color(255, 255, 255));
        setMaximizedBounds(new java.awt.Rectangle(0, 0, 1280, 720));
        setMaximumSize(new java.awt.Dimension(1280, 720));
        setMinimumSize(new java.awt.Dimension(1280, 720));
        setUndecorated(true);
        setPreferredSize(new java.awt.Dimension(1280, 720));
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
                .addContainerGap(135, Short.MAX_VALUE))
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

        title_label.setFont(new java.awt.Font("Montserrat", 0, 28)); // NOI18N
        title_label.setForeground(new java.awt.Color(255, 255, 255));
        title_label.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        title_label.setText("AMN");
        side_pane.add(title_label, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 135, 100, 40));

        title_icon.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        title_icon.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/PNGS/ammunition_white.png"))); // NOI18N
        side_pane.add(title_icon, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 110, 80, 70));

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
        menuB_label.setText("View by Nomenclature");

        javax.swing.GroupLayout menuBLayout = new javax.swing.GroupLayout(menuB);
        menuB.setLayout(menuBLayout);
        menuBLayout.setHorizontalGroup(
            menuBLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(menuBLayout.createSequentialGroup()
                .addGap(27, 27, 27)
                .addComponent(menuB_icon)
                .addGap(33, 33, 33)
                .addComponent(menuB_label)
                .addContainerGap(67, Short.MAX_VALUE))
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

        bg.add(side_pane, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 320, 740));

        ScrollPaneA.setBorder(null);
        ScrollPaneA.setHorizontalScrollBarPolicy(javax.swing.ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);

        panelA.setBackground(new java.awt.Color(255, 255, 255));
        panelA.setFocusable(false);
        panelA.setMaximumSize(new java.awt.Dimension(950, 1310));
        panelA.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        remarks_labelA.setBackground(new java.awt.Color(255, 255, 255));
        remarks_labelA.setFont(new java.awt.Font("Montserrat", 0, 28)); // NOI18N
        remarks_labelA.setForeground(new java.awt.Color(66, 50, 77));
        remarks_labelA.setText("Remarks");
        panelA.add(remarks_labelA, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 380, -1, 40));

        on_demand_labelA.setBackground(new java.awt.Color(255, 255, 255));
        on_demand_labelA.setFont(new java.awt.Font("Montserrat", 0, 28)); // NOI18N
        on_demand_labelA.setForeground(new java.awt.Color(51, 51, 51));
        on_demand_labelA.setText("On Demand");
        panelA.add(on_demand_labelA, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 160, -1, 40));

        on_demand_textfieldA.setFont(new java.awt.Font("Roboto", 0, 24)); // NOI18N
        on_demand_textfieldA.setForeground(new java.awt.Color(54, 33, 89));
        on_demand_textfieldA.setBorder(null);
        on_demand_textfieldA.setMargin(new java.awt.Insets(2, 4, 2, 2));
        on_demand_textfieldA.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                on_demand_textfieldAActionPerformed(evt);
            }
        });
        panelA.add(on_demand_textfieldA, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 200, 330, 40));

        underlineA2.setForeground(new java.awt.Color(54, 33, 89));
        panelA.add(underlineA2, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 240, 330, 20));

        remarks_textfieldA.setFont(new java.awt.Font("Roboto", 0, 24)); // NOI18N
        remarks_textfieldA.setForeground(new java.awt.Color(54, 33, 89));
        remarks_textfieldA.setBorder(null);
        remarks_textfieldA.setMargin(new java.awt.Insets(2, 4, 2, 2));
        remarks_textfieldA.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                remarks_textfieldAActionPerformed(evt);
            }
        });
        panelA.add(remarks_textfieldA, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 420, 330, 40));

        underlineA1.setForeground(new java.awt.Color(54, 33, 89));
        panelA.add(underlineA1, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 130, 330, 20));

        nomenclature_textfieldA.setFont(new java.awt.Font("Roboto", 0, 24)); // NOI18N
        nomenclature_textfieldA.setForeground(new java.awt.Color(54, 33, 89));
        nomenclature_textfieldA.setBorder(null);
        nomenclature_textfieldA.setMargin(new java.awt.Insets(2, 4, 2, 2));
        panelA.add(nomenclature_textfieldA, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 90, 330, 40));

        nomenclature_labelA.setFont(new java.awt.Font("Montserrat", 0, 28)); // NOI18N
        nomenclature_labelA.setForeground(new java.awt.Color(51, 51, 51));
        nomenclature_labelA.setText("Nomenclature");
        panelA.add(nomenclature_labelA, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 50, -1, 40));

        DEFI_labelA.setBackground(new java.awt.Color(255, 255, 255));
        DEFI_labelA.setFont(new java.awt.Font("Montserrat", 0, 28)); // NOI18N
        DEFI_labelA.setForeground(new java.awt.Color(51, 51, 51));
        DEFI_labelA.setText("DEFI");
        panelA.add(DEFI_labelA, new org.netbeans.lib.awtextra.AbsoluteConstraints(480, 160, -1, 40));

        DEFI_textfieldA.setFont(new java.awt.Font("Roboto", 0, 24)); // NOI18N
        DEFI_textfieldA.setForeground(new java.awt.Color(54, 33, 89));
        DEFI_textfieldA.setBorder(null);
        DEFI_textfieldA.setMargin(new java.awt.Insets(2, 4, 2, 2));
        DEFI_textfieldA.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                DEFI_textfieldAActionPerformed(evt);
            }
        });
        panelA.add(DEFI_textfieldA, new org.netbeans.lib.awtextra.AbsoluteConstraints(480, 200, 330, 40));

        underlineA3.setForeground(new java.awt.Color(54, 33, 89));
        panelA.add(underlineA3, new org.netbeans.lib.awtextra.AbsoluteConstraints(480, 240, 330, 20));

        CONT_DT_labelA.setBackground(new java.awt.Color(255, 255, 255));
        CONT_DT_labelA.setFont(new java.awt.Font("Montserrat", 0, 28)); // NOI18N
        CONT_DT_labelA.setForeground(new java.awt.Color(51, 51, 51));
        CONT_DT_labelA.setText("CONT DT");
        panelA.add(CONT_DT_labelA, new org.netbeans.lib.awtextra.AbsoluteConstraints(480, 270, -1, 40));

        CONT_NO_labelA.setBackground(new java.awt.Color(255, 255, 255));
        CONT_NO_labelA.setFont(new java.awt.Font("Montserrat", 0, 28)); // NOI18N
        CONT_NO_labelA.setForeground(new java.awt.Color(51, 51, 51));
        CONT_NO_labelA.setText("CONT NO");
        panelA.add(CONT_NO_labelA, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 270, -1, 40));

        auth_labelA.setBackground(new java.awt.Color(255, 255, 255));
        auth_labelA.setFont(new java.awt.Font("Montserrat", 0, 28)); // NOI18N
        auth_labelA.setForeground(new java.awt.Color(51, 51, 51));
        auth_labelA.setText("Auth");
        panelA.add(auth_labelA, new org.netbeans.lib.awtextra.AbsoluteConstraints(480, 50, -1, 40));

        auth_textfieldA.setFont(new java.awt.Font("Roboto", 0, 24)); // NOI18N
        auth_textfieldA.setForeground(new java.awt.Color(54, 33, 89));
        auth_textfieldA.setBorder(null);
        auth_textfieldA.setMargin(new java.awt.Insets(2, 4, 2, 2));
        auth_textfieldA.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                auth_textfieldAActionPerformed(evt);
            }
        });
        panelA.add(auth_textfieldA, new org.netbeans.lib.awtextra.AbsoluteConstraints(480, 90, 330, 40));

        underlineA4.setForeground(new java.awt.Color(54, 33, 89));
        panelA.add(underlineA4, new org.netbeans.lib.awtextra.AbsoluteConstraints(480, 130, 330, 20));

        underlineA5.setForeground(new java.awt.Color(54, 33, 89));
        panelA.add(underlineA5, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 460, 330, 20));

        CONT_NO_textfieldA.setFont(new java.awt.Font("Roboto", 0, 24)); // NOI18N
        CONT_NO_textfieldA.setForeground(new java.awt.Color(54, 33, 89));
        CONT_NO_textfieldA.setBorder(null);
        CONT_NO_textfieldA.setMargin(new java.awt.Insets(2, 4, 2, 2));
        CONT_NO_textfieldA.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                CONT_NO_textfieldAActionPerformed(evt);
            }
        });
        panelA.add(CONT_NO_textfieldA, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 310, 330, 40));

        underlineA6.setForeground(new java.awt.Color(54, 33, 89));
        panelA.add(underlineA6, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 350, 330, 20));

        CONT_DT_textfieldA.setFont(new java.awt.Font("Roboto", 0, 24)); // NOI18N
        CONT_DT_textfieldA.setForeground(new java.awt.Color(54, 33, 89));
        CONT_DT_textfieldA.setBorder(null);
        CONT_DT_textfieldA.setMargin(new java.awt.Insets(2, 4, 2, 2));
        CONT_DT_textfieldA.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                CONT_DT_textfieldAActionPerformed(evt);
            }
        });
        panelA.add(CONT_DT_textfieldA, new org.netbeans.lib.awtextra.AbsoluteConstraints(480, 310, 330, 40));

        underlineA7.setForeground(new java.awt.Color(54, 33, 89));
        panelA.add(underlineA7, new org.netbeans.lib.awtextra.AbsoluteConstraints(480, 350, 330, 20));

        ScrollPaneA.setViewportView(panelA);

        bg.add(ScrollPaneA, new org.netbeans.lib.awtextra.AbsoluteConstraints(340, 192, 910, 480));

        panelB.setBackground(new java.awt.Color(255, 255, 255));
        panelB.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        nomenclature_labelB.setBackground(new java.awt.Color(255, 255, 255));
        nomenclature_labelB.setFont(new java.awt.Font("Montserrat", 0, 28)); // NOI18N
        nomenclature_labelB.setForeground(new java.awt.Color(51, 51, 51));
        nomenclature_labelB.setText("Nomenclature");
        panelB.add(nomenclature_labelB, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 50, -1, 40));

        nomenclatureB.setFont(new java.awt.Font("Roboto", 0, 24)); // NOI18N
        nomenclatureB.setForeground(new java.awt.Color(54, 33, 89));
        nomenclatureB.setBorder(null);
        nomenclatureB.setMargin(new java.awt.Insets(2, 4, 2, 2));
        nomenclatureB.setSelectionColor(new java.awt.Color(204, 204, 255));
        nomenclatureB.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                nomenclatureBMouseClicked(evt);
            }
        });
        nomenclatureB.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                nomenclatureBActionPerformed(evt);
            }
        });
        nomenclatureB.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                nomenclatureBKeyPressed(evt);
            }
        });
        panelB.add(nomenclatureB, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 90, 400, 40));

        nomenclature_underlineB.setForeground(new java.awt.Color(54, 33, 89));
        panelB.add(nomenclature_underlineB, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 130, 400, 10));

        okayB.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/PNGS/check-mark.png"))); // NOI18N
        okayB.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                okayBMouseClicked(evt);
            }
        });
        panelB.add(okayB, new org.netbeans.lib.awtextra.AbsoluteConstraints(490, 90, -1, 40));

        ScrollPaneB.setBackground(new java.awt.Color(255, 255, 255));
        ScrollPaneB.setVerticalScrollBarPolicy(javax.swing.ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);

        TableB.setFont(new java.awt.Font("Roboto", 0, 18)); // NOI18N
        TableB.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "S. No.", "nomen", "Auth", "On Demand", "DEFI", "CONT NO", "CONT DT", "Remarks"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, false, false
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
        ScrollPaneB.setViewportView(TableB);
        if (TableB.getColumnModel().getColumnCount() > 0) {
            TableB.getColumnModel().getColumn(0).setMinWidth(50);
            TableB.getColumnModel().getColumn(0).setPreferredWidth(50);
            TableB.getColumnModel().getColumn(0).setMaxWidth(50);
        }

        panelB.add(ScrollPaneB, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 170, 780, 300));

        bg.add(panelB, new org.netbeans.lib.awtextra.AbsoluteConstraints(340, 192, 910, 480));

        ScrollPaneC.setBorder(null);
        ScrollPaneC.setHorizontalScrollBarPolicy(javax.swing.ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
        ScrollPaneC.setVerticalScrollBarPolicy(javax.swing.ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);

        panelC.setBackground(new java.awt.Color(255, 255, 255));
        panelC.setFocusable(false);
        panelC.setMaximumSize(new java.awt.Dimension(950, 1310));
        panelC.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        nomenclature_labelC0.setFont(new java.awt.Font("Montserrat", 0, 28)); // NOI18N
        nomenclature_labelC0.setForeground(new java.awt.Color(51, 51, 51));
        nomenclature_labelC0.setText("Nomenclature");
        panelC.add(nomenclature_labelC0, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 70, -1, 40));

        nomenclatureC.setFont(new java.awt.Font("Roboto Bk", 0, 24)); // NOI18N
        nomenclatureC.setForeground(new java.awt.Color(54, 33, 89));
        nomenclatureC.setText("> XXXXXXX");
        panelC.add(nomenclatureC, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 100, -1, 40));

        changing_for_label.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        changing_for_label.setText("Changing For");
        panelC.add(changing_for_label, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 50, -1, 20));

        panelA_bottom_line1.setForeground(new java.awt.Color(54, 33, 89));
        panelC.add(panelA_bottom_line1, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 700, 910, -1));

        midC.setForeground(new java.awt.Color(204, 204, 255));
        panelC.add(midC, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 180, 570, 10));

        remarks_labelC.setBackground(new java.awt.Color(255, 255, 255));
        remarks_labelC.setFont(new java.awt.Font("Montserrat", 0, 28)); // NOI18N
        remarks_labelC.setForeground(new java.awt.Color(66, 50, 77));
        remarks_labelC.setText("Remarks");
        panelC.add(remarks_labelC, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 550, -1, 40));

        on_demand_labelC.setBackground(new java.awt.Color(255, 255, 255));
        on_demand_labelC.setFont(new java.awt.Font("Montserrat", 0, 28)); // NOI18N
        on_demand_labelC.setForeground(new java.awt.Color(51, 51, 51));
        on_demand_labelC.setText("On Demand");
        panelC.add(on_demand_labelC, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 330, -1, 40));

        on_demand_textfieldC.setFont(new java.awt.Font("Roboto", 0, 24)); // NOI18N
        on_demand_textfieldC.setForeground(new java.awt.Color(54, 33, 89));
        on_demand_textfieldC.setBorder(null);
        on_demand_textfieldC.setMargin(new java.awt.Insets(2, 4, 2, 2));
        on_demand_textfieldC.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                on_demand_textfieldCActionPerformed(evt);
            }
        });
        panelC.add(on_demand_textfieldC, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 370, 330, 40));

        underlineA8.setForeground(new java.awt.Color(54, 33, 89));
        panelC.add(underlineA8, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 410, 330, 20));

        remarks_textfieldC.setFont(new java.awt.Font("Roboto", 0, 24)); // NOI18N
        remarks_textfieldC.setForeground(new java.awt.Color(54, 33, 89));
        remarks_textfieldC.setBorder(null);
        remarks_textfieldC.setMargin(new java.awt.Insets(2, 4, 2, 2));
        remarks_textfieldC.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                remarks_textfieldCActionPerformed(evt);
            }
        });
        panelC.add(remarks_textfieldC, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 590, 330, 40));

        underlineA9.setForeground(new java.awt.Color(54, 33, 89));
        panelC.add(underlineA9, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 300, 330, 20));

        nomenclature_textfieldC.setFont(new java.awt.Font("Roboto", 0, 24)); // NOI18N
        nomenclature_textfieldC.setForeground(new java.awt.Color(54, 33, 89));
        nomenclature_textfieldC.setBorder(null);
        nomenclature_textfieldC.setMargin(new java.awt.Insets(2, 4, 2, 2));
        panelC.add(nomenclature_textfieldC, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 260, 330, 40));

        nomenclature_labelC.setFont(new java.awt.Font("Montserrat", 0, 28)); // NOI18N
        nomenclature_labelC.setForeground(new java.awt.Color(51, 51, 51));
        nomenclature_labelC.setText("Nomenclature");
        panelC.add(nomenclature_labelC, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 220, -1, 40));

        DEFI_labelC.setBackground(new java.awt.Color(255, 255, 255));
        DEFI_labelC.setFont(new java.awt.Font("Montserrat", 0, 28)); // NOI18N
        DEFI_labelC.setForeground(new java.awt.Color(51, 51, 51));
        DEFI_labelC.setText("DEFI");
        panelC.add(DEFI_labelC, new org.netbeans.lib.awtextra.AbsoluteConstraints(480, 330, -1, 40));

        DEFI_textfieldC.setFont(new java.awt.Font("Roboto", 0, 24)); // NOI18N
        DEFI_textfieldC.setForeground(new java.awt.Color(54, 33, 89));
        DEFI_textfieldC.setBorder(null);
        DEFI_textfieldC.setMargin(new java.awt.Insets(2, 4, 2, 2));
        DEFI_textfieldC.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                DEFI_textfieldCActionPerformed(evt);
            }
        });
        panelC.add(DEFI_textfieldC, new org.netbeans.lib.awtextra.AbsoluteConstraints(480, 370, 330, 40));

        underlineA10.setForeground(new java.awt.Color(54, 33, 89));
        panelC.add(underlineA10, new org.netbeans.lib.awtextra.AbsoluteConstraints(480, 410, 330, 20));

        CONT_DT_labelC.setBackground(new java.awt.Color(255, 255, 255));
        CONT_DT_labelC.setFont(new java.awt.Font("Montserrat", 0, 28)); // NOI18N
        CONT_DT_labelC.setForeground(new java.awt.Color(51, 51, 51));
        CONT_DT_labelC.setText("CONT DT");
        panelC.add(CONT_DT_labelC, new org.netbeans.lib.awtextra.AbsoluteConstraints(480, 440, -1, 40));

        CONT_NO_labelC.setBackground(new java.awt.Color(255, 255, 255));
        CONT_NO_labelC.setFont(new java.awt.Font("Montserrat", 0, 28)); // NOI18N
        CONT_NO_labelC.setForeground(new java.awt.Color(51, 51, 51));
        CONT_NO_labelC.setText("CONT NO");
        panelC.add(CONT_NO_labelC, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 440, -1, 40));

        auth_labelC.setBackground(new java.awt.Color(255, 255, 255));
        auth_labelC.setFont(new java.awt.Font("Montserrat", 0, 28)); // NOI18N
        auth_labelC.setForeground(new java.awt.Color(51, 51, 51));
        auth_labelC.setText("Auth");
        panelC.add(auth_labelC, new org.netbeans.lib.awtextra.AbsoluteConstraints(480, 220, -1, 40));

        auth_textfieldC.setFont(new java.awt.Font("Roboto", 0, 24)); // NOI18N
        auth_textfieldC.setForeground(new java.awt.Color(54, 33, 89));
        auth_textfieldC.setBorder(null);
        auth_textfieldC.setMargin(new java.awt.Insets(2, 4, 2, 2));
        auth_textfieldC.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                auth_textfieldCActionPerformed(evt);
            }
        });
        panelC.add(auth_textfieldC, new org.netbeans.lib.awtextra.AbsoluteConstraints(480, 260, 330, 40));

        underlineA11.setForeground(new java.awt.Color(54, 33, 89));
        panelC.add(underlineA11, new org.netbeans.lib.awtextra.AbsoluteConstraints(480, 300, 330, 20));

        underlineA12.setForeground(new java.awt.Color(54, 33, 89));
        panelC.add(underlineA12, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 630, 330, 20));

        CONT_NO_textfieldC.setFont(new java.awt.Font("Roboto", 0, 24)); // NOI18N
        CONT_NO_textfieldC.setForeground(new java.awt.Color(54, 33, 89));
        CONT_NO_textfieldC.setBorder(null);
        CONT_NO_textfieldC.setMargin(new java.awt.Insets(2, 4, 2, 2));
        CONT_NO_textfieldC.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                CONT_NO_textfieldCActionPerformed(evt);
            }
        });
        panelC.add(CONT_NO_textfieldC, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 480, 330, 40));

        underlineA13.setForeground(new java.awt.Color(54, 33, 89));
        panelC.add(underlineA13, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 520, 330, 20));

        CONT_DT_textfieldC.setFont(new java.awt.Font("Roboto", 0, 24)); // NOI18N
        CONT_DT_textfieldC.setForeground(new java.awt.Color(54, 33, 89));
        CONT_DT_textfieldC.setBorder(null);
        CONT_DT_textfieldC.setMargin(new java.awt.Insets(2, 4, 2, 2));
        CONT_DT_textfieldC.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                CONT_DT_textfieldCActionPerformed(evt);
            }
        });
        panelC.add(CONT_DT_textfieldC, new org.netbeans.lib.awtextra.AbsoluteConstraints(480, 480, 330, 40));

        underlineA14.setForeground(new java.awt.Color(54, 33, 89));
        panelC.add(underlineA14, new org.netbeans.lib.awtextra.AbsoluteConstraints(480, 520, 330, 20));

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

        getContentPane().add(bg, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, -1, 730));

        fullscreen_panelB.setBackground(new java.awt.Color(255, 255, 255));
        fullscreen_panelB.setMaximumSize(new java.awt.Dimension(1280, 720));
        fullscreen_panelB.setMinimumSize(new java.awt.Dimension(1280, 720));
        fullscreen_panelB.setPreferredSize(new java.awt.Dimension(1280, 720));
        fullscreen_panelB.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

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

        fullscreen_ScrollPaneB.setBackground(new java.awt.Color(255, 255, 255));
        fullscreen_ScrollPaneB.setHorizontalScrollBarPolicy(javax.swing.ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);

        fullscreen_tableB.setFont(new java.awt.Font("Roboto", 0, 18)); // NOI18N
        fullscreen_tableB.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "S. No.", "Nomenclature", "Auth", "On Demand", "DEFI", "CONT ON", "CONT DT", "Remarks"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, false, false
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

        fullscreen_panelB.add(fullscreen_ScrollPaneB, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 40, 1200, 580));

        getContentPane().add(fullscreen_panelB, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 1280, 720));

        pack();
    }// </editor-fold>//GEN-END:initComponents

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
            new Modules(0).setVisible(true);
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

        ScrollPaneA.setVisible(true);
        panelB.setVisible(false);
        ScrollPaneC.setVisible(false);
        fullscreen_panelB.setVisible(false);
        nomenclature_textfieldA.setText("");
        auth_textfieldA.setText("");
        on_demand_textfieldA.setText("");
        DEFI_textfieldA.setText("");
        CONT_NO_textfieldA.setText("");
        CONT_DT_textfieldA.setText("");
        remarks_textfieldA.setText("");
    }//GEN-LAST:event_menuAMouseClicked

    private void menuBMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_menuBMouseClicked
        menuB.setBackground(new java.awt.Color(85, 65, 118));
        menuA.setBackground(new java.awt.Color(64, 43, 100));

        print_buttonB.setVisible(true);
        edit_buttonB.setVisible(true);
        refresh_buttonB.setVisible(true);
        fullscreen_buttonB.setVisible(true);
        insert_buttonA.setVisible(false);

        ScrollPaneA.setVisible(false);
        panelB.setVisible(true);
        ScrollPaneC.setVisible(false);
        fullscreen_panelB.setVisible(false);
        nomenclatureB.setText("");
        viewall();

    }//GEN-LAST:event_menuBMouseClicked

    private void okayBMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_okayBMouseClicked
        if (!nomenclatureB.getText().trim().equals(""))
            viewByNomenclature();
        else
            viewall();
    }//GEN-LAST:event_okayBMouseClicked

    private void refresh_buttonBMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_refresh_buttonBMouseClicked
        if (nomenclatureB.getText().trim().equals("")) {
            viewall();
        } else
            viewByNomenclature();
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
            ResultSet resultSet = statement.executeQuery("select * from state_Amn");
            XSSFWorkbook workbook = new XSSFWorkbook();
            XSSFSheet spreadsheet = null;
            spreadsheet = workbook.createSheet("State_Amn");

            XSSFRow row = spreadsheet.createRow(0);
            XSSFCell cell;
            cell = row.createCell(0);
            cell.setCellValue("Nomenclature");
            cell = row.createCell(1);
            cell.setCellValue("Auth");
            cell = row.createCell(2);
            cell.setCellValue("On demand");
            cell = row.createCell(3);
            cell.setCellValue("DEFI");
            cell = row.createCell(4);
            cell.setCellValue("Cont NO");
            cell = row.createCell(5);
            cell.setCellValue("Cont DT");
            cell = row.createCell(6);
            cell.setCellValue("Remarks");

            int i = 1;

            while (resultSet.next()) {
                row = spreadsheet.createRow(i);
                cell = row.createCell(0);
                cell.setCellValue(resultSet.getString("nomenclature"));
                cell = row.createCell(1);
                cell.setCellValue(resultSet.getString("auth"));
                cell = row.createCell(2);
                cell.setCellValue(resultSet.getString("on_demand"));
                cell = row.createCell(3);
                cell.setCellValue(resultSet.getString("defi"));
                cell = row.createCell(4);
                cell.setCellValue(resultSet.getString("cont_no"));
                cell = row.createCell(5);
                cell.setCellValue(resultSet.getString("cont_dt"));
                cell = row.createCell(6);
                cell.setCellValue(resultSet.getString("Remarks"));
                i++;
            }
            path_file paths = new path_file();
            FileOutputStream out = new FileOutputStream(new File(paths.csv + "\\State_AMN.xlsx"));
            workbook.write(out);
            JOptionPane.showMessageDialog(null, "State_amn.xlsx written successfully");
            Desktop.getDesktop().open(new File(paths.csv + "/State_AMN.xlsx"));

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
        if (!nomenclatureB.getText().trim().equals("")) {
            if (viewByNomenclature()) {
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

    private void insert_buttonAMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_insert_buttonAMouseClicked
        // TODO add your handling code here:
        String nomen = nomenclature_textfieldA.getText().trim();
        String auth = auth_textfieldA.getText().trim();
        String on_demand = on_demand_textfieldA.getText().trim();
        String defi = DEFI_textfieldA.getText().trim();
        String cont_no = CONT_NO_textfieldA.getText().trim();
        String cont_dt = CONT_DT_textfieldA.getText().trim();
        String remarks = remarks_textfieldA.getText().trim();
        String Query = "";
        boolean flag = false;
        try {
            if (!nomen.equals("")) {
                if (auth.equals("")) {
                    Query = Query + "Auth cannot be empty!\n";
                    flag = true;
                }
                if (on_demand.equals("")) {
                    Query = Query + "On-Demand cannot be empty!\n";
                    flag = true;
                }
                if (defi.equals("")) {
                    Query = Query + "DEFI cannot be empty!\n";
                    flag = true;
                }
                if (cont_no.equals("")) {
                    Query = Query + "CONT NO cannot be empty!\n";
                    flag = true;
                }
                if (cont_dt.equals("")) {
                    Query = Query + "CONT DT cannot be empty!\n";
                    flag = true;
                }
                if (flag == true) {
                    JOptionPane.showMessageDialog(null, Query, "Error", JOptionPane.ERROR_MESSAGE);
                } else {
                    PreparedStatement pstmt = con.prepareStatement("insert into State_AMN values(?, ?, ?, ?, ?, ?, ?)");
                    pstmt.setString(1, nomen);
                    pstmt.setString(2, auth);
                    pstmt.setString(3, on_demand);
                    pstmt.setString(4, defi);
                    pstmt.setString(5, cont_no);
                    pstmt.setString(6, cont_dt);
                    pstmt.setString(7, remarks);
                    pstmt.executeUpdate();
                    JOptionPane.showMessageDialog(null, "Entry Successful", "Successful", JOptionPane.WARNING_MESSAGE);

                    nomenclature_textfieldA.setText("");
                    auth_textfieldA.setText("");
                    on_demand_textfieldA.setText("");
                    DEFI_textfieldA.setText("");
                    CONT_NO_textfieldA.setText("");
                    CONT_DT_textfieldA.setText("");
                    remarks_textfieldA.setText("");

                }
            } else {
                JOptionPane.showMessageDialog(null, "Nomenclature cannot be empty!", "Error", JOptionPane.ERROR_MESSAGE);
            }
        } catch (java.sql.SQLIntegrityConstraintViolationException sqle) {
            JOptionPane.showMessageDialog(null, "Nomenclature already exists", "Successful", JOptionPane.WARNING_MESSAGE);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
        }
    }//GEN-LAST:event_insert_buttonAMouseClicked

    private void insert_buttonAMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_insert_buttonAMouseEntered
        // TODO add your handling code here:
        insert_buttonA.setBackground(new java.awt.Color(237, 224, 255));

    }//GEN-LAST:event_insert_buttonAMouseEntered

    private void insert_buttonAMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_insert_buttonAMouseExited
        // TODO add your handling code here:
        insert_buttonA.setBackground(new java.awt.Color(240, 240, 240));

    }//GEN-LAST:event_insert_buttonAMouseExited

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

    private void move_panelMouseDragged(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_move_panelMouseDragged
        this.setLocation(this.getX() + evt.getX() - mouseX, this.getY() + evt.getY() - mouseY);
    }//GEN-LAST:event_move_panelMouseDragged

    private void move_panelMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_move_panelMousePressed
        mouseX = evt.getX();
        mouseY = evt.getY();
    }//GEN-LAST:event_move_panelMousePressed

    private void on_demand_textfieldAActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_on_demand_textfieldAActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_on_demand_textfieldAActionPerformed

    private void remarks_textfieldAActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_remarks_textfieldAActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_remarks_textfieldAActionPerformed

    private void DEFI_textfieldAActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_DEFI_textfieldAActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_DEFI_textfieldAActionPerformed

    private void auth_textfieldAActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_auth_textfieldAActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_auth_textfieldAActionPerformed

    private void CONT_NO_textfieldAActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_CONT_NO_textfieldAActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_CONT_NO_textfieldAActionPerformed

    private void CONT_DT_textfieldAActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_CONT_DT_textfieldAActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_CONT_DT_textfieldAActionPerformed

    private void formWindowOpened(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowOpened
        //for a centered frame
        Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
        this.setLocation(dim.width / 2 - this.getSize().width / 2, dim.height / 2 - this.getSize().height / 2);
        //for a centered frame

        menuA.setBackground(new java.awt.Color(85, 65, 118));
        menuB.setBackground(new java.awt.Color(64, 43, 100));

        insert_buttonA.setVisible(true);
        print_buttonB.setVisible(false);
        edit_buttonB.setVisible(false);
        refresh_buttonB.setVisible(false);
        fullscreen_buttonB.setVisible(false);

        ScrollPaneA.setVisible(true);
        panelB.setVisible(false);
        ScrollPaneC.setVisible(false);
        fullscreen_panelB.setVisible(false);

    }//GEN-LAST:event_formWindowOpened

    private void nomenclatureBActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_nomenclatureBActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_nomenclatureBActionPerformed

    private void edit_buttonBMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_edit_buttonBMouseClicked
        int row = TableB.getSelectedRow();
        if (row == -1) {
            JOptionPane.showMessageDialog(null, "Please select a row to update", "Error", JOptionPane.ERROR_MESSAGE);
        } else {
            ScrollPaneA.setVisible(false);
            panelB.setVisible(false);
            ScrollPaneC.setVisible(true);
            fullscreen_panelB.setVisible(false);

            insert_buttonA.setVisible(false);
            print_buttonB.setVisible(false);
            edit_buttonB.setVisible(false);
            refresh_buttonB.setVisible(false);
            fullscreen_buttonB.setVisible(false);
            update_buttonC.setVisible(true);
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
        String nomen = nomenclature_textfieldC.getText().trim();
        String auth = auth_textfieldC.getText().trim();
        String on_demand = on_demand_textfieldC.getText().trim();
        String defi = DEFI_textfieldC.getText().trim();
        String cont_no = CONT_NO_textfieldC.getText().trim();
        String cont_dt = CONT_DT_textfieldC.getText().trim();
        String remarks = remarks_textfieldC.getText().trim();
        String Query = "";
        boolean flag = false;
        try {
            if (!nomen.equals("")) {
                if (auth.equals("")) {
                    Query = Query + "Auth cannot be empty!\n";
                    flag = true;
                }
                if (on_demand.equals("")) {
                    Query = Query + "On-Demand cannot be empty!\n";
                    flag = true;
                }
                if (defi.equals("")) {
                    Query = Query + "DEFI cannot be empty!\n";
                    flag = true;
                }
                if (cont_no.equals("")) {
                    Query = Query + "CONT_NO cannot be empty!\n";
                    flag = true;
                }
                if (cont_dt.equals("")) {
                    Query = Query + "CONT_DT cannot be empty\n";
                    flag = true;
                }
                if (flag == true) {
                    JOptionPane.showMessageDialog(null, Query, "Error", JOptionPane.ERROR_MESSAGE);
                } else {
                    PreparedStatement pstmt = con.prepareStatement("update State_AMN set nomenclature=?, auth=?, on_demand=?, defi=?, cont_no=?, cont_dt=?, remarks=? where nomenclature=? ");

                    pstmt.setString(1, nomen);
                    pstmt.setString(2, auth);
                    pstmt.setString(3, on_demand);
                    pstmt.setString(4, defi);
                    pstmt.setString(5, cont_no);
                    pstmt.setString(6, cont_dt);
                    pstmt.setString(7, remarks);
                    pstmt.setString(8, nomenclatureC.getText().trim());
                    pstmt.executeUpdate();
                    JOptionPane.showMessageDialog(null, "Updation Successful", "Successful", JOptionPane.WARNING_MESSAGE);

                    nomenclatureC.setText("> XXXXXXX");
                    nomenclature_textfieldC.setText("");
                    auth_textfieldC.setText("");
                    on_demand_textfieldC.setText("");
                    DEFI_textfieldC.setText("");
                    CONT_NO_textfieldC.setText("");
                    CONT_DT_textfieldC.setText("");
                    remarks_textfieldC.setText("");
                    menuBMouseClicked(null);
                }
            } else {
                JOptionPane.showMessageDialog(null, "Nomenclature cannot be empty!", "Error", JOptionPane.ERROR_MESSAGE);
            }
        } catch (java.sql.SQLIntegrityConstraintViolationException sqle) {
            JOptionPane.showMessageDialog(null, "Nomenclature already exists", "Successful", JOptionPane.WARNING_MESSAGE);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
        }
    }//GEN-LAST:event_update_buttonCMouseClicked

    private void update_buttonCMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_update_buttonCMouseEntered
        update_buttonC.setBackground(new java.awt.Color(237, 224, 255));
    }//GEN-LAST:event_update_buttonCMouseEntered

    private void update_buttonCMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_update_buttonCMouseExited
        update_buttonC.setBackground(new java.awt.Color(240, 240, 240));
    }//GEN-LAST:event_update_buttonCMouseExited

    private void on_demand_textfieldCActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_on_demand_textfieldCActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_on_demand_textfieldCActionPerformed

    private void remarks_textfieldCActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_remarks_textfieldCActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_remarks_textfieldCActionPerformed

    private void DEFI_textfieldCActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_DEFI_textfieldCActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_DEFI_textfieldCActionPerformed

    private void auth_textfieldCActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_auth_textfieldCActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_auth_textfieldCActionPerformed

    private void CONT_NO_textfieldCActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_CONT_NO_textfieldCActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_CONT_NO_textfieldCActionPerformed

    private void CONT_DT_textfieldCActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_CONT_DT_textfieldCActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_CONT_DT_textfieldCActionPerformed

    private void nomenclatureBMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_nomenclatureBMouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_nomenclatureBMouseClicked

    private void nomenclatureBKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_nomenclatureBKeyPressed
        if (evt.getKeyCode() == java.awt.event.KeyEvent.VK_ENTER) {
            okayBMouseClicked(null);
        }
    }//GEN-LAST:event_nomenclatureBKeyPressed

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
            java.util.logging.Logger.getLogger(State_AMN.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(State_AMN.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(State_AMN.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(State_AMN.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new State_AMN().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel CONT_DT_labelA;
    private javax.swing.JLabel CONT_DT_labelC;
    private javax.swing.JTextField CONT_DT_textfieldA;
    private javax.swing.JTextField CONT_DT_textfieldC;
    private javax.swing.JLabel CONT_NO_labelA;
    private javax.swing.JLabel CONT_NO_labelC;
    private javax.swing.JTextField CONT_NO_textfieldA;
    private javax.swing.JTextField CONT_NO_textfieldC;
    private javax.swing.JLabel DEFI_labelA;
    private javax.swing.JLabel DEFI_labelC;
    private javax.swing.JTextField DEFI_textfieldA;
    private javax.swing.JTextField DEFI_textfieldC;
    private javax.swing.JScrollPane ScrollPaneA;
    private javax.swing.JScrollPane ScrollPaneB;
    private javax.swing.JScrollPane ScrollPaneC;
    private javax.swing.JSeparator Separator;
    private javax.swing.JTable TableB;
    private javax.swing.JLabel auth_labelA;
    private javax.swing.JLabel auth_labelC;
    private javax.swing.JTextField auth_textfieldA;
    private javax.swing.JTextField auth_textfieldC;
    private javax.swing.JPanel bg;
    private javax.swing.JLabel changing_for_label;
    private javax.swing.JPanel edit_buttonB;
    private javax.swing.JLabel edit_labelB;
    private javax.swing.JPanel exit_fullscreen_buttonB;
    private javax.swing.JLabel exit_fullscreen_labelB;
    private javax.swing.JLabel exit_icon;
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
    private javax.swing.JSeparator midC;
    private javax.swing.JLabel minimize;
    private javax.swing.JPanel move_panel;
    private javax.swing.JTextField nomenclatureB;
    private javax.swing.JLabel nomenclatureC;
    private javax.swing.JLabel nomenclature_labelA;
    private javax.swing.JLabel nomenclature_labelB;
    private javax.swing.JLabel nomenclature_labelC;
    private javax.swing.JLabel nomenclature_labelC0;
    private javax.swing.JTextField nomenclature_textfieldA;
    private javax.swing.JTextField nomenclature_textfieldC;
    private javax.swing.JSeparator nomenclature_underlineB;
    private javax.swing.JLabel okayB;
    private javax.swing.JLabel on_demand_labelA;
    private javax.swing.JLabel on_demand_labelC;
    private javax.swing.JTextField on_demand_textfieldA;
    private javax.swing.JTextField on_demand_textfieldC;
    private javax.swing.JPanel panelA;
    private javax.swing.JSeparator panelA_bottom_line1;
    private javax.swing.JPanel panelB;
    private javax.swing.JPanel panelC;
    private javax.swing.JPanel print_buttonB;
    private javax.swing.JLabel print_labelB;
    private javax.swing.JPanel refresh_buttonB;
    private javax.swing.JLabel refresh_labelB;
    private javax.swing.JLabel remarks_labelA;
    private javax.swing.JLabel remarks_labelC;
    private javax.swing.JTextField remarks_textfieldA;
    private javax.swing.JTextField remarks_textfieldC;
    private javax.swing.JPanel side_pane;
    private javax.swing.JLabel title_icon;
    private javax.swing.JLabel title_label;
    private javax.swing.JSeparator underlineA1;
    private javax.swing.JSeparator underlineA10;
    private javax.swing.JSeparator underlineA11;
    private javax.swing.JSeparator underlineA12;
    private javax.swing.JSeparator underlineA13;
    private javax.swing.JSeparator underlineA14;
    private javax.swing.JSeparator underlineA2;
    private javax.swing.JSeparator underlineA3;
    private javax.swing.JSeparator underlineA4;
    private javax.swing.JSeparator underlineA5;
    private javax.swing.JSeparator underlineA6;
    private javax.swing.JSeparator underlineA7;
    private javax.swing.JSeparator underlineA8;
    private javax.swing.JSeparator underlineA9;
    private javax.swing.JPanel update_buttonC;
    private javax.swing.JLabel update_labelC;
    // End of variables declaration//GEN-END:variables
}
