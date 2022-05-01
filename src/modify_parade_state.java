/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.sql.*;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

/**
 *
 * @author Aditya Pandey
 */
public class modify_parade_state extends javax.swing.JFrame {

    /**
     * Creates new form Update
     */
    Database db = new Database();
    Connection c =db.create_connection(true);
    Statement s;
    boolean flag = true;
    private int mouseX, mouseY;
    public modify_parade_state() {
        initComponents();
        
        //for a centered frame
        Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
        this.setLocation(dim.width/2-this.getSize().width/2, dim.height/2-this.getSize().height/2);
        //for a centered frame
         try {
            s = c.createStatement();
        } catch (Exception ex) {
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

        bg = new javax.swing.JPanel();
        exit_icon = new javax.swing.JLabel();
        exit_icon1 = new javax.swing.JLabel();
        side_pane = new javax.swing.JPanel();
        title_label = new javax.swing.JLabel();
        title_separator = new javax.swing.JSeparator();
        title_icon_label = new javax.swing.JLabel();
        go_back_label = new javax.swing.JLabel();
        menuA = new javax.swing.JPanel();
        menuA_icon = new javax.swing.JLabel();
        menuA_label = new javax.swing.JLabel();
        menuB = new javax.swing.JPanel();
        menuB_icon = new javax.swing.JLabel();
        menuB_label = new javax.swing.JLabel();
        panelA = new javax.swing.JPanel();
        current_jco = new javax.swing.JLabel();
        current_jco_label = new javax.swing.JLabel();
        current_other_ranks_label = new javax.swing.JLabel();
        current_officers_label = new javax.swing.JLabel();
        current_other_ranks = new javax.swing.JLabel();
        current_officers = new javax.swing.JLabel();
        SeparatorA = new javax.swing.JSeparator();
        modify_button = new javax.swing.JPanel();
        modify_label = new javax.swing.JLabel();
        panelB = new javax.swing.JPanel();
        JCO_labelB = new javax.swing.JLabel();
        update_button = new javax.swing.JPanel();
        update_company_label = new javax.swing.JLabel();
        JCO_underlineB = new javax.swing.JSeparator();
        JCO_textfieldB = new javax.swing.JTextField();
        other_ranks_underlineB = new javax.swing.JSeparator();
        other_ranks_textfieldB = new javax.swing.JTextField();
        other_ranks_labelB = new javax.swing.JLabel();
        officers_underlineB = new javax.swing.JSeparator();
        officers_textfieldB = new javax.swing.JTextField();
        officers_labelB = new javax.swing.JLabel();
        SeparatorB = new javax.swing.JSeparator();
        move_panel = new javax.swing.JPanel();

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

        bg.setBackground(new java.awt.Color(255, 255, 255));
        bg.setMaximumSize(new java.awt.Dimension(1280, 720));
        bg.setMinimumSize(new java.awt.Dimension(1280, 720));
        bg.setPreferredSize(new java.awt.Dimension(1280, 720));
        bg.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        exit_icon.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/PNGS/minimize-purple.png"))); // NOI18N
        exit_icon.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                exit_iconMouseClicked(evt);
            }
        });
        bg.add(exit_icon, new org.netbeans.lib.awtextra.AbsoluteConstraints(1170, 30, -1, 40));

        exit_icon1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/PNGS/remove_purple.png"))); // NOI18N
        exit_icon1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                exit_icon1MouseClicked(evt);
            }
        });
        bg.add(exit_icon1, new org.netbeans.lib.awtextra.AbsoluteConstraints(1215, 30, -1, -1));

        side_pane.setBackground(new java.awt.Color(54, 33, 89));
        side_pane.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        title_label.setFont(new java.awt.Font("Montserrat", 0, 28)); // NOI18N
        title_label.setForeground(new java.awt.Color(255, 255, 255));
        title_label.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        title_label.setText("Parade State");
        side_pane.add(title_label, new org.netbeans.lib.awtextra.AbsoluteConstraints(100, 130, 190, 50));
        side_pane.add(title_separator, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 190, 260, 50));

        title_icon_label.setForeground(new java.awt.Color(255, 255, 255));
        title_icon_label.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        title_icon_label.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/PNGS/parade module white.png"))); // NOI18N
        side_pane.add(title_icon_label, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 120, 70, 70));

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
        menuA_label.setText("Parade State");

        javax.swing.GroupLayout menuALayout = new javax.swing.GroupLayout(menuA);
        menuA.setLayout(menuALayout);
        menuALayout.setHorizontalGroup(
            menuALayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(menuALayout.createSequentialGroup()
                .addGap(27, 27, 27)
                .addComponent(menuA_icon)
                .addGap(33, 33, 33)
                .addComponent(menuA_label))
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

        menuB.setBackground(new java.awt.Color(64, 43, 100));
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
        menuB_label.setText("Modify");

        javax.swing.GroupLayout menuBLayout = new javax.swing.GroupLayout(menuB);
        menuB.setLayout(menuBLayout);
        menuBLayout.setHorizontalGroup(
            menuBLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(menuBLayout.createSequentialGroup()
                .addGap(27, 27, 27)
                .addComponent(menuB_icon)
                .addGap(33, 33, 33)
                .addComponent(menuB_label)
                .addContainerGap(197, Short.MAX_VALUE))
        );
        menuBLayout.setVerticalGroup(
            menuBLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(menuB_label, javax.swing.GroupLayout.DEFAULT_SIZE, 60, Short.MAX_VALUE)
            .addComponent(menuB_icon, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        side_pane.add(menuB, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 300, 320, 60));

        bg.add(side_pane, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 320, 740));

        panelA.setBackground(new java.awt.Color(255, 255, 255));
        panelA.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        current_jco.setFont(new java.awt.Font("Roboto Bk", 0, 24)); // NOI18N
        current_jco.setForeground(new java.awt.Color(54, 33, 89));
        current_jco.setText("> XXXXXXX");
        panelA.add(current_jco, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 390, -1, -1));

        current_jco_label.setFont(new java.awt.Font("Montserrat", 0, 28)); // NOI18N
        current_jco_label.setForeground(new java.awt.Color(51, 51, 51));
        current_jco_label.setText("Number of JCOs");
        panelA.add(current_jco_label, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 350, -1, 40));

        current_other_ranks_label.setFont(new java.awt.Font("Montserrat", 0, 28)); // NOI18N
        current_other_ranks_label.setForeground(new java.awt.Color(51, 51, 51));
        current_other_ranks_label.setText("Number of Other Ranks");
        panelA.add(current_other_ranks_label, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 450, -1, 40));

        current_officers_label.setFont(new java.awt.Font("Montserrat", 0, 28)); // NOI18N
        current_officers_label.setForeground(new java.awt.Color(51, 51, 51));
        current_officers_label.setText("Number of Officers");
        panelA.add(current_officers_label, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 250, -1, 40));

        current_other_ranks.setFont(new java.awt.Font("Roboto Bk", 0, 24)); // NOI18N
        current_other_ranks.setForeground(new java.awt.Color(54, 33, 89));
        current_other_ranks.setText("> XXXXXXX");
        panelA.add(current_other_ranks, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 490, -1, -1));

        current_officers.setFont(new java.awt.Font("Roboto Bk", 0, 24)); // NOI18N
        current_officers.setForeground(new java.awt.Color(54, 33, 89));
        current_officers.setText("> XXXXXXX");
        panelA.add(current_officers, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 290, -1, -1));

        SeparatorA.setForeground(new java.awt.Color(54, 33, 89));
        SeparatorA.setPreferredSize(new java.awt.Dimension(50, 20));
        panelA.add(SeparatorA, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 192, 880, 10));

        modify_button.setForeground(new java.awt.Color(240, 240, 240));
        modify_button.setMaximumSize(new java.awt.Dimension(95, 25));
        modify_button.setMinimumSize(new java.awt.Dimension(95, 25));
        modify_button.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                modify_buttonMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                modify_buttonMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                modify_buttonMouseExited(evt);
            }
        });
        modify_button.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        modify_label.setBackground(new java.awt.Color(64, 43, 100));
        modify_label.setFont(new java.awt.Font("Roboto", 0, 14)); // NOI18N
        modify_label.setForeground(new java.awt.Color(64, 43, 100));
        modify_label.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        modify_label.setText("MODIFY");
        modify_button.add(modify_label, new org.netbeans.lib.awtextra.AbsoluteConstraints(52, 13, 71, 24));

        panelA.add(modify_button, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 590, 180, 50));

        bg.add(panelA, new org.netbeans.lib.awtextra.AbsoluteConstraints(306, -2, 1010, 740));

        panelB.setBackground(new java.awt.Color(255, 255, 255));
        panelB.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        JCO_labelB.setFont(new java.awt.Font("Montserrat", 0, 28)); // NOI18N
        JCO_labelB.setForeground(new java.awt.Color(51, 51, 51));
        JCO_labelB.setText("Number of JCOs");
        panelB.add(JCO_labelB, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 390, -1, 40));

        update_button.setForeground(new java.awt.Color(240, 240, 240));
        update_button.setMaximumSize(new java.awt.Dimension(95, 25));
        update_button.setMinimumSize(new java.awt.Dimension(95, 25));
        update_button.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                update_buttonMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                update_buttonMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                update_buttonMouseExited(evt);
            }
        });
        update_button.setLayout(null);

        update_company_label.setBackground(new java.awt.Color(64, 43, 100));
        update_company_label.setFont(new java.awt.Font("Roboto", 0, 14)); // NOI18N
        update_company_label.setForeground(new java.awt.Color(64, 43, 100));
        update_company_label.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        update_company_label.setText("UPDATE");
        update_button.add(update_company_label);
        update_company_label.setBounds(58, 0, 60, 50);

        panelB.add(update_button, new org.netbeans.lib.awtextra.AbsoluteConstraints(680, 570, 180, 50));

        JCO_underlineB.setForeground(new java.awt.Color(54, 33, 89));
        panelB.add(JCO_underlineB, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 480, 400, 30));

        JCO_textfieldB.setFont(new java.awt.Font("Roboto", 0, 24)); // NOI18N
        JCO_textfieldB.setForeground(new java.awt.Color(54, 33, 89));
        JCO_textfieldB.setBorder(null);
        JCO_textfieldB.setMargin(new java.awt.Insets(2, 4, 2, 2));
        JCO_textfieldB.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                JCO_textfieldBFocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                JCO_textfieldBFocusLost(evt);
            }
        });
        panelB.add(JCO_textfieldB, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 440, 400, 40));

        other_ranks_underlineB.setForeground(new java.awt.Color(54, 33, 89));
        panelB.add(other_ranks_underlineB, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 620, 400, 20));

        other_ranks_textfieldB.setFont(new java.awt.Font("Roboto", 0, 24)); // NOI18N
        other_ranks_textfieldB.setForeground(new java.awt.Color(54, 33, 89));
        other_ranks_textfieldB.setBorder(null);
        other_ranks_textfieldB.setMargin(new java.awt.Insets(2, 4, 2, 2));
        other_ranks_textfieldB.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                other_ranks_textfieldBFocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                other_ranks_textfieldBFocusLost(evt);
            }
        });
        panelB.add(other_ranks_textfieldB, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 580, 400, 40));

        other_ranks_labelB.setFont(new java.awt.Font("Montserrat", 0, 28)); // NOI18N
        other_ranks_labelB.setForeground(new java.awt.Color(51, 51, 51));
        other_ranks_labelB.setText("Number of Other Ranks");
        panelB.add(other_ranks_labelB, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 530, -1, 40));

        officers_underlineB.setForeground(new java.awt.Color(54, 33, 89));
        panelB.add(officers_underlineB, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 340, 400, 30));

        officers_textfieldB.setFont(new java.awt.Font("Roboto", 0, 24)); // NOI18N
        officers_textfieldB.setForeground(new java.awt.Color(54, 33, 89));
        officers_textfieldB.setBorder(null);
        officers_textfieldB.setMargin(new java.awt.Insets(2, 4, 2, 2));
        officers_textfieldB.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                officers_textfieldBFocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                officers_textfieldBFocusLost(evt);
            }
        });
        panelB.add(officers_textfieldB, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 300, 400, 40));

        officers_labelB.setFont(new java.awt.Font("Montserrat", 0, 28)); // NOI18N
        officers_labelB.setForeground(new java.awt.Color(51, 51, 51));
        officers_labelB.setText("Number of Officers");
        panelB.add(officers_labelB, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 250, -1, 40));

        SeparatorB.setForeground(new java.awt.Color(54, 33, 89));
        SeparatorB.setPreferredSize(new java.awt.Dimension(50, 20));
        panelB.add(SeparatorB, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 192, 870, 10));

        bg.add(panelB, new org.netbeans.lib.awtextra.AbsoluteConstraints(306, -2, 990, 740));

        getContentPane().add(bg, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 1280, 720));

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

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void exit_iconMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_exit_iconMouseClicked
        setState(this.ICONIFIED);// TODO add your handling code here:
    }//GEN-LAST:event_exit_iconMouseClicked

    private void exit_icon1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_exit_icon1MouseClicked
        int confirmed = JOptionPane.showConfirmDialog(null, "Are you sure you want to exit the program?", "Exit Program", JOptionPane.YES_NO_OPTION);

        if (confirmed == JOptionPane.YES_OPTION) {
            dispose();
            try {
                c.close();
            } catch (SQLException ex) {
                System.out.println("connection ended");
            }
        }
    }//GEN-LAST:event_exit_icon1MouseClicked

    private void go_back_labelMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_go_back_labelMouseClicked
        if(flag){
            new Parade_Module().setVisible(true);
            this.setVisible(false);// TODO add your handling code here:
            flag = false;
        }
    }//GEN-LAST:event_go_back_labelMouseClicked

    private void menuAMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_menuAMouseClicked
        menuA.setBackground(new java.awt.Color(85,65,118));
        menuB.setBackground(new java.awt.Color(64,43,100));

        panelA.setVisible(true);
        panelB.setVisible(false);
    }//GEN-LAST:event_menuAMouseClicked

    private void menuBMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_menuBMouseClicked
        menuB.setBackground(new java.awt.Color(85,65,118));
        menuA.setBackground(new java.awt.Color(64,43,100));

        panelA.setVisible(false);
        panelB.setVisible(true);
    }//GEN-LAST:event_menuBMouseClicked

    private void update_buttonMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_update_buttonMouseExited
        update_button.setBackground(new java.awt.Color(240,240,240));// TODO add your handling code here:
    }//GEN-LAST:event_update_buttonMouseExited

    private void update_buttonMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_update_buttonMouseEntered
        update_button.setBackground(new java.awt.Color(237, 224, 255));// TODO add your handling code here:
    }//GEN-LAST:event_update_buttonMouseEntered

    private void update_buttonMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_update_buttonMouseClicked
        // TODO add your handling code here:
        try {
            // TODO add your handling code here:
            String query1 = "select * from Parade_State";
            ResultSet rs    = s.executeQuery(query1);
            if(rs.next() == false){
                s.executeUpdate("insert into parade_state values('0','0','0');");
            }
            rs    = s.executeQuery(query1); // Retrieving last stored values in database
            String res = null;
            while (rs.next()) {
                res = rs.getString("JCO") + " " + rs.getString("Others") + " " + rs.getString("Officers"); // Storing previou password in String "res"
                    
            }
            String[] out = res.split(" ");
            current_jco.setText(out[0]);
            current_officers.setText(out[1]);
            current_other_ranks.setText(out[2]);
                String query = "update Parade_State set JCO = ? where JCO = ?";
                java.sql.PreparedStatement preparedStmt = c.prepareStatement(query); // Preparing Update Statement
                preparedStmt.setString(2, out[0]);
                preparedStmt.setString(1, JCO_textfieldB.getText());
                preparedStmt.executeUpdate(); // Executing statement to change in database
                
                query = "update Parade_State set Others = ? where Others = ?";
                preparedStmt = c.prepareStatement(query); // Preparing Update Statement
                preparedStmt.setString(2, out[1]);
                preparedStmt.setString(1, other_ranks_textfieldB.getText());
                preparedStmt.executeUpdate(); // Executing statement to change in database
                
                query = "update Parade_State set Officers = ? where Officers = ?";
                preparedStmt = c.prepareStatement(query); // Preparing Update Statement
                preparedStmt.setString(2, out[2]);
                preparedStmt.setString(1, officers_textfieldB.getText());

      // execute the java preparedstatement
                preparedStmt.executeUpdate(); // Executing statement to change in database
                
            query1 = "select * from Parade_State";
            rs    = s.executeQuery(query1); // Retrieving last stored values in database
            res = null;
            while (rs.next()) {
                res = rs.getString("JCO") + " " + rs.getString("Others") + " " + rs.getString("Officers"); // Storing previou password in String "res"
                    
            }
            out = res.split(" ");
                    current_jco.setText(out[0]);
                    current_other_ranks.setText(out[1]);
                    current_officers.setText(out[2]);
      

                    JCO_textfieldB.setText(out[0]);
                    JCO_textfieldB.setForeground(Color.decode("#a9a9a9"));


                    other_ranks_textfieldB.setText(out[1]);
                    other_ranks_textfieldB.setForeground(Color.decode("#a9a9a9"));


                    officers_textfieldB.setText(out[2]);
                    officers_textfieldB.setForeground(Color.decode("#a9a9a9"));
                    
                
                JOptionPane.showMessageDialog(rootPane, "PARADE STATE CHANGED SUCCESSFULLY.");
                menuA.setBackground(new java.awt.Color(85,65,118));
            menuB.setBackground(new java.awt.Color(64,43,100));

            panelA.setVisible(true);
            panelB.setVisible(false);
                
            
        } catch (SQLException ex) {
            Logger.getLogger(modify_parade_state.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_update_buttonMouseClicked

    private void formWindowOpened(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowOpened
        panelA.setVisible(true);
        panelB.setVisible(false);
        
        menuA.setBackground(new java.awt.Color(85,65,118));
        menuB.setBackground(new java.awt.Color(64,43,100));
        // TODO add your handling code here:
        try{
           
            String query1 = "select * from Parade_State";
            ResultSet rs    = s.executeQuery(query1); // Retrieving last stored password in database
            String res = null;
            while (rs.next()) {
                res = rs.getString("JCO") + " " + rs.getString("Others") + " " + rs.getString("Officers"); // Storing previou password in String "res"
                    
            }
            String[] out = res.split(" ");
            current_jco.setText(out[0]);
            current_other_ranks.setText(out[1]);
            current_officers.setText(out[2]);
            JCO_textfieldB.setText(out[0]);
            JCO_textfieldB.setForeground(Color.decode("#a9a9a9"));
            other_ranks_textfieldB.setText(out[1]);
            other_ranks_textfieldB.setForeground(Color.decode("#a9a9a9"));
            officers_textfieldB.setText(out[2]);
            officers_textfieldB.setForeground(Color.decode("#a9a9a9"));
            }catch(SQLException ex) {
                Logger.getLogger(modify_parade_state.class.getName()).log(Level.SEVERE, null, ex);
            }
    }//GEN-LAST:event_formWindowOpened

    private void modify_buttonMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_modify_buttonMouseClicked
        menuB.setBackground(new java.awt.Color(85,65,118));
        menuA.setBackground(new java.awt.Color(64,43,100));

        panelA.setVisible(false);
        panelB.setVisible(true);
    }//GEN-LAST:event_modify_buttonMouseClicked

    private void modify_buttonMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_modify_buttonMouseEntered
        modify_button.setBackground(new java.awt.Color(237, 224, 255));// TODO add your handling code here:
    }//GEN-LAST:event_modify_buttonMouseEntered

    private void modify_buttonMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_modify_buttonMouseExited
        modify_button.setBackground(new java.awt.Color(240,240,240));// TODO add your handling code here:
    }//GEN-LAST:event_modify_buttonMouseExited

    private void officers_textfieldBFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_officers_textfieldBFocusGained
        // TODO add your handling code here:
        try {
                String query1 = "select * from Parade_State";
                ResultSet rs    = s.executeQuery(query1); // Retrieving last stored password in database
                String res = null;
                while (rs.next()) {
                    res = rs.getString("Officers"); // Storing previou password in String "res"
                    
                }
                if(officers_textfieldB.getText().equals(res)){
                    officers_textfieldB.setText("");
                    officers_textfieldB.setForeground(Color.decode("#000000"));
                }
            } catch (SQLException ex) {
                Logger.getLogger(modify_parade_state.class.getName()).log(Level.SEVERE, null, ex);
            }
    }//GEN-LAST:event_officers_textfieldBFocusGained

    private void officers_textfieldBFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_officers_textfieldBFocusLost
        // TODO add your handling code here:
        try {
                String query1 = "select * from Parade_State";
                ResultSet rs    = s.executeQuery(query1); // Retrieving last stored password in database
                String res = null;
                while (rs.next()) {
                    res = rs.getString("Officers"); // Storing previou password in String "res"
                    
                }
                if(officers_textfieldB.getText().equals("")){
                    officers_textfieldB.setText(res);
                    officers_textfieldB.setForeground(Color.decode("#a9a9a9"));
                }
            } catch (SQLException ex) {
                Logger.getLogger(modify_parade_state.class.getName()).log(Level.SEVERE, null, ex);
            }
    }//GEN-LAST:event_officers_textfieldBFocusLost

    private void JCO_textfieldBFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_JCO_textfieldBFocusGained
        // TODO add your handling code here:
        try {
                String query1 = "select * from Parade_State";
                ResultSet rs    = s.executeQuery(query1); // Retrieving last stored password in database
                String res = null;
                while (rs.next()) {
                    res = rs.getString("JCO"); // Storing previou password in String "res"
                    
                }
                if(JCO_textfieldB.getText().equals(res)){
                    JCO_textfieldB.setText("");
                    JCO_textfieldB.setForeground(Color.decode("#000000"));
                }
            } catch (SQLException ex) {
                Logger.getLogger(modify_parade_state.class.getName()).log(Level.SEVERE, null, ex);
            }
    }//GEN-LAST:event_JCO_textfieldBFocusGained

    private void JCO_textfieldBFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_JCO_textfieldBFocusLost
        // TODO add your handling code here:
        try {
                String query1 = "select * from Parade_State";
                ResultSet rs    = s.executeQuery(query1); // Retrieving last stored password in database
                String res = null;
                while (rs.next()) {
                    res = rs.getString("JCO"); // Storing previou password in String "res"
                    
                }
                if(JCO_textfieldB.getText().equals("")){
                    JCO_textfieldB.setText(res);
                    JCO_textfieldB.setForeground(Color.decode("#a9a9a9"));
                }
            } catch (SQLException ex) {
                Logger.getLogger(modify_parade_state.class.getName()).log(Level.SEVERE, null, ex);
            }
    }//GEN-LAST:event_JCO_textfieldBFocusLost

    private void other_ranks_textfieldBFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_other_ranks_textfieldBFocusGained
        // TODO add your handling code here:
        try {
                String query1 = "select * from Parade_State";
                ResultSet rs    = s.executeQuery(query1); // Retrieving last stored password in database
                String res = null;
                while (rs.next()) {
                    res = rs.getString("Others"); // Storing previou password in String "res"
                    
                }
                if(other_ranks_textfieldB.getText().equals(res)){
                    other_ranks_textfieldB.setText("");
                    other_ranks_textfieldB.setForeground(Color.decode("#000000"));
                }
            } catch (SQLException ex) {
                Logger.getLogger(modify_parade_state.class.getName()).log(Level.SEVERE, null, ex);
            }
    }//GEN-LAST:event_other_ranks_textfieldBFocusGained

    private void other_ranks_textfieldBFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_other_ranks_textfieldBFocusLost
        // TODO add your handling code here:
        try {
                String query1 = "select * from Parade_State";
                ResultSet rs    = s.executeQuery(query1); // Retrieving last stored password in database
                String res = null;
                while (rs.next()) {
                    res = rs.getString("Others"); // Storing previou password in String "res"
                    
                }
                if(other_ranks_textfieldB.getText().equals("")){
                    other_ranks_textfieldB.setText(res);
                    other_ranks_textfieldB.setForeground(Color.decode("#a9a9a9"));
                }
            } catch (SQLException ex) {
                Logger.getLogger(modify_parade_state.class.getName()).log(Level.SEVERE, null, ex);
            }
    }//GEN-LAST:event_other_ranks_textfieldBFocusLost

    private void move_panelMouseDragged(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_move_panelMouseDragged
        this.setLocation(this.getX() +evt.getX() -mouseX, this.getY() + evt.getY() -mouseY);
    }//GEN-LAST:event_move_panelMouseDragged

    private void move_panelMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_move_panelMousePressed
        mouseX =evt.getX();
        mouseY =evt.getY();
    }//GEN-LAST:event_move_panelMousePressed

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
            java.util.logging.Logger.getLogger(modify_parade_state.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(modify_parade_state.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(modify_parade_state.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(modify_parade_state.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new modify_parade_state().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel JCO_labelB;
    private javax.swing.JTextField JCO_textfieldB;
    private javax.swing.JSeparator JCO_underlineB;
    private javax.swing.JSeparator SeparatorA;
    private javax.swing.JSeparator SeparatorB;
    private javax.swing.JPanel bg;
    private javax.swing.JLabel current_jco;
    private javax.swing.JLabel current_jco_label;
    private javax.swing.JLabel current_officers;
    private javax.swing.JLabel current_officers_label;
    private javax.swing.JLabel current_other_ranks;
    private javax.swing.JLabel current_other_ranks_label;
    private javax.swing.JLabel exit_icon;
    private javax.swing.JLabel exit_icon1;
    private javax.swing.JLabel go_back_label;
    private javax.swing.JPanel menuA;
    private javax.swing.JLabel menuA_icon;
    private javax.swing.JLabel menuA_label;
    private javax.swing.JPanel menuB;
    private javax.swing.JLabel menuB_icon;
    private javax.swing.JLabel menuB_label;
    private javax.swing.JPanel modify_button;
    private javax.swing.JLabel modify_label;
    private javax.swing.JPanel move_panel;
    private javax.swing.JLabel officers_labelB;
    private javax.swing.JTextField officers_textfieldB;
    private javax.swing.JSeparator officers_underlineB;
    private javax.swing.JLabel other_ranks_labelB;
    private javax.swing.JTextField other_ranks_textfieldB;
    private javax.swing.JSeparator other_ranks_underlineB;
    private javax.swing.JPanel panelA;
    private javax.swing.JPanel panelB;
    private javax.swing.JPanel side_pane;
    private javax.swing.JLabel title_icon_label;
    private javax.swing.JLabel title_label;
    private javax.swing.JSeparator title_separator;
    private javax.swing.JPanel update_button;
    private javax.swing.JLabel update_company_label;
    // End of variables declaration//GEN-END:variables
}