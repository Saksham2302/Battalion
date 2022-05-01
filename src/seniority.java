import java.awt.Desktop;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.io.File;
import java.io.FileOutputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
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
public class seniority extends javax.swing.JFrame {

    /**
     * Creates new form seniority
     */
    private int mouseX,mouseY;
    boolean disp_msg=true;
    boolean disp_msg2= true;
    boolean back_button=true;
    String final_sno=null;
    Database db=new Database();
    Connection con=db.create_connection(true);
    
    DefaultTableModel small, big;
    public seniority(int x) {
        initComponents();
        
        ScrollPaneA.getVerticalScrollBar().setUnitIncrement(16);
        ScrollPaneB.getVerticalScrollBar().setUnitIncrement(16);
        fullscreen_ScrollPaneB.getVerticalScrollBar().setUnitIncrement(16);
        
        big = (DefaultTableModel) fullscreen_tableB.getModel();
        small = (DefaultTableModel) TableG_out.getModel();
        if(x==0){
            menuAMouseClicked(null);
        }
        else if(x==1){
            menuBMouseClicked(null);
        }
    }
    
    private void view_table(String a) {
        try {
            Statement st = con.createStatement();
            String query = "select *from seniority";
            ResultSet rs = st.executeQuery(query);
            small.setRowCount(0);
            big.setRowCount(0);
            int count = 0;
            //all variables initialised are in sequence with the table
            String service_no,doe,doc,ttt,mr,ace,sns,ss,dns,ds,lnk,nkdop,nkdos,lhav,havdop,havdos,nbsubdop,nbsubdos,subdop,subdos,submajdop,submajdos,macp1,seplnk,date_seplnk,macp2,bd_nk,date_bd_nk,macp3,bd_hav,date_bd_hav;
            while (rs.next()) {
                count = count + 1;
                service_no = rs.getString("Service_no");
                doe = rs.getString("DOE");
                doc = rs.getString("DOC");
                ttt = rs.getString("TTT");
                mr = rs.getString("MR");
                ace=rs.getString("ACE");
                sns=rs.getString("Screening_nb_sub");
                ss=rs.getString("Screening_sub");
                dns=rs.getString("due_date_nb_sub");
                ds=rs.getString("due_date_sub");
                lnk=rs.getString("LNK_dop_date");
                nkdop=rs.getString("NK_dop_date");
                nkdos=rs.getString("NK_dos_date");
                lhav=rs.getString("LHAV_dop_date");
                havdop=rs.getString("HAV_dop_date");
                havdos=rs.getString("HAV_dos_date");
                macp1=rs.getString("MACP_1");
                macp2=rs.getString("MACP_2");
                macp3=rs.getString("MACP_3");
                

                small.addRow(new Object[]{
                    count,
                    service_no,
                    doe,
                    doc,
                    ttt,mr,ace,macp1,macp2
                });
                big.addRow(new Object[]{
                    count,
                    service_no,
                    doe,
                    doc,
                    ttt,mr,ace,sns,ss,dns,ds,lnk,nkdop,nkdos,lhav,havdop,havdos,macp1,macp2,macp3
                });

            }
            if (a != null) {
                count = 0;
                String sno = service_numberB.getText();
                // arrange=100;
                String diff_query = "select *from seniority where service_no='" + sno + "'";
                rs = st.executeQuery(diff_query);
                small.setRowCount(0);
                big.setRowCount(0);
                while (rs.next()) {
                    count = count + 1;
                    service_no = rs.getString("Service_no");
                    doe = rs.getString("DOE");
                    doc = rs.getString("DOC");
                    ttt = rs.getString("TTT");
                    mr = rs.getString("MR");
                    ace=rs.getString("ACE");
                    sns=rs.getString("Screening_nb_sub");
                    ss=rs.getString("Screening_sub");
                    dns=rs.getString("due_date_nb_sub");
                    ds=rs.getString("due_date_sub");
                    lnk=rs.getString("LNK_dop_date");
                    nkdop=rs.getString("NK_dop_date");
                    nkdos=rs.getString("NK_dos_date");
                    lhav=rs.getString("LHAV_dop_date");
                    havdop=rs.getString("HAV_dop_date");
                    havdos=rs.getString("HAV_dos_date");
                    macp1=rs.getString("MACP_1");
                    macp2=rs.getString("MACP_2");
                    macp3=rs.getString("MACP_3");

                    small.addRow(new Object[]{
                        count,
                        service_no,
                        doe,
                        doc,
                        ttt,mr,ace,macp1,macp2
                    });
                    big.addRow(new Object[]{
                        count,
                        service_no,
                        doe,
                        doc,
                        ttt,mr,ace,sns,ss,dns,ds,lnk,nkdop,nkdos,lhav,havdop,havdos,macp1,macp2,macp3
                    });
                }

            }

        } catch (Exception e) {
            System.out.print("\n" + e);
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
        Separator = new javax.swing.JSeparator();
        side_pane = new javax.swing.JPanel();
        jSeparator1 = new javax.swing.JSeparator();
        go_back_label = new javax.swing.JLabel();
        title_label = new javax.swing.JLabel();
        title_icon = new javax.swing.JLabel();
        menuA = new javax.swing.JPanel();
        menuA_icon = new javax.swing.JLabel();
        menuA_label = new javax.swing.JLabel();
        menuB = new javax.swing.JPanel();
        menuB_icon = new javax.swing.JLabel();
        menuB_label = new javax.swing.JLabel();
        ScrollPaneA = new javax.swing.JScrollPane();
        panelA = new javax.swing.JPanel();
        service_number_underline = new javax.swing.JSeparator();
        service_number_textfield = new javax.swing.JTextField();
        service_number_label = new javax.swing.JLabel();
        panelA_bottom_line = new javax.swing.JSeparator();
        okay = new javax.swing.JLabel();
        name_label = new javax.swing.JLabel();
        name = new javax.swing.JLabel();
        rank_label = new javax.swing.JLabel();
        rank = new javax.swing.JLabel();
        company_label = new javax.swing.JLabel();
        company = new javax.swing.JLabel();
        trade_label = new javax.swing.JLabel();
        DOB = new javax.swing.JLabel();
        unit_label = new javax.swing.JLabel();
        unit = new javax.swing.JLabel();
        screening_labelA1 = new javax.swing.JLabel();
        DD_labelA1 = new javax.swing.JLabel();
        DOS_labelA = new javax.swing.JLabel();
        midA1 = new javax.swing.JSeparator();
        midA2 = new javax.swing.JSeparator();
        DOB_label = new javax.swing.JLabel();
        DOE_labelA = new javax.swing.JLabel();
        DOE_date_comboboxA = new javax.swing.JComboBox<>();
        DOE_month_comboboxA = new javax.swing.JComboBox<>();
        DOE_year_comboboxA = new javax.swing.JComboBox<>();
        NK_textfield = new javax.swing.JTextField();
        cadre_underline1 = new javax.swing.JSeparator();
        screening_textfieldA1 = new javax.swing.JTextField();
        screening_underlineA1 = new javax.swing.JSeparator();
        trade = new javax.swing.JLabel();
        screening_label2 = new javax.swing.JLabel();
        HAV_textfield1 = new javax.swing.JTextField();
        screening_underlineA2 = new javax.swing.JSeparator();
        TTT_labelA = new javax.swing.JLabel();
        TTT_textfieldA = new javax.swing.JTextField();
        TTT_underlineA = new javax.swing.JSeparator();
        MR_labelA = new javax.swing.JLabel();
        MR_textfieldA = new javax.swing.JTextField();
        MR_underlineA = new javax.swing.JSeparator();
        screening_undertextA2 = new javax.swing.JLabel();
        screening_undertextA1 = new javax.swing.JLabel();
        DD_date_comboboxA1 = new javax.swing.JComboBox<>();
        DD_month_comboboxA1 = new javax.swing.JComboBox<>();
        DD_year_comboboxA1 = new javax.swing.JComboBox<>();
        midA3 = new javax.swing.JSeparator();
        vill_label1 = new javax.swing.JLabel();
        LNK_labelA = new javax.swing.JLabel();
        DOP_labelA = new javax.swing.JLabel();
        DOP_date_combobox1 = new javax.swing.JComboBox<>();
        DOP_month_combobox1 = new javax.swing.JComboBox<>();
        DOP_year_combobox1 = new javax.swing.JComboBox<>();
        rank_labelA = new javax.swing.JLabel();
        LHAV_labelA = new javax.swing.JLabel();
        DOS_date_combobox1 = new javax.swing.JComboBox<>();
        DOS_month_combobox1 = new javax.swing.JComboBox<>();
        DOS_year_combobox1 = new javax.swing.JComboBox<>();
        DOP_date_combobox2 = new javax.swing.JComboBox<>();
        DOP_month_combobox2 = new javax.swing.JComboBox<>();
        DOP_year_combobox2 = new javax.swing.JComboBox<>();
        NK_labelA = new javax.swing.JLabel();
        DOP_date_combobox3 = new javax.swing.JComboBox<>();
        DOP_month_combobox3 = new javax.swing.JComboBox<>();
        DOP_year_combobox3 = new javax.swing.JComboBox<>();
        HAV_labelA = new javax.swing.JLabel();
        DOP_date_combobox4 = new javax.swing.JComboBox<>();
        DOP_month_combobox4 = new javax.swing.JComboBox<>();
        DOP_year_combobox4 = new javax.swing.JComboBox<>();
        NB_SUB_labelA = new javax.swing.JLabel();
        DOP_date_combobox5 = new javax.swing.JComboBox<>();
        DOP_month_combobox5 = new javax.swing.JComboBox<>();
        DOP_year_combobox5 = new javax.swing.JComboBox<>();
        DOP_date_combobox6 = new javax.swing.JComboBox<>();
        DOP_month_combobox6 = new javax.swing.JComboBox<>();
        DOP_year_combobox6 = new javax.swing.JComboBox<>();
        SUB_labelA = new javax.swing.JLabel();
        DOP_year_combobox7 = new javax.swing.JComboBox<>();
        SUB_MAJ_labelA = new javax.swing.JLabel();
        DOP_date_combobox7 = new javax.swing.JComboBox<>();
        DOP_month_combobox7 = new javax.swing.JComboBox<>();
        DOS_date_combobox2 = new javax.swing.JComboBox<>();
        DOS_month_combobox2 = new javax.swing.JComboBox<>();
        DOS_year_combobox2 = new javax.swing.JComboBox<>();
        DOS_month_combobox3 = new javax.swing.JComboBox<>();
        DOS_year_combobox3 = new javax.swing.JComboBox<>();
        DOS_date_combobox3 = new javax.swing.JComboBox<>();
        DOS_month_combobox4 = new javax.swing.JComboBox<>();
        DOS_year_combobox4 = new javax.swing.JComboBox<>();
        DOS_date_combobox4 = new javax.swing.JComboBox<>();
        DOS_year_combobox5 = new javax.swing.JComboBox<>();
        DOS_date_combobox5 = new javax.swing.JComboBox<>();
        DOS_month_combobox5 = new javax.swing.JComboBox<>();
        screening_labelA3 = new javax.swing.JLabel();
        screening_textfieldA3 = new javax.swing.JTextField();
        screening_underlineA3 = new javax.swing.JSeparator();
        screening_undertextA3 = new javax.swing.JLabel();
        MACP1_labelA1 = new javax.swing.JLabel();
        MACP1_textfieldA = new javax.swing.JTextField();
        MACP1_underlineA = new javax.swing.JSeparator();
        DD_labelA2 = new javax.swing.JLabel();
        DD_date_comboboxA2 = new javax.swing.JComboBox<>();
        DD_month_comboboxA2 = new javax.swing.JComboBox<>();
        DD_year_comboboxA2 = new javax.swing.JComboBox<>();
        DD_labelA4 = new javax.swing.JLabel();
        DD_date_comboboxA3 = new javax.swing.JComboBox<>();
        DD_month_comboboxA3 = new javax.swing.JComboBox<>();
        DD_year_comboboxA3 = new javax.swing.JComboBox<>();
        screening_labelA4 = new javax.swing.JLabel();
        screening_textfieldA4 = new javax.swing.JTextField();
        screening_underlineA4 = new javax.swing.JSeparator();
        screening_undertextA4 = new javax.swing.JLabel();
        MACP2_labelA = new javax.swing.JLabel();
        MACP2_textfieldA = new javax.swing.JTextField();
        MACP2_underlineA = new javax.swing.JSeparator();
        DD_labelA5 = new javax.swing.JLabel();
        DD_date_comboboxA4 = new javax.swing.JComboBox<>();
        DD_month_comboboxA4 = new javax.swing.JComboBox<>();
        DD_year_comboboxA4 = new javax.swing.JComboBox<>();
        screening_labelA5 = new javax.swing.JLabel();
        screening_textfieldA5 = new javax.swing.JTextField();
        screening_underlineA5 = new javax.swing.JSeparator();
        screening_undertextA5 = new javax.swing.JLabel();
        MACP3_labelA = new javax.swing.JLabel();
        MACP3_textfieldA = new javax.swing.JTextField();
        MACP3_underlineA = new javax.swing.JSeparator();
        DD_labelA6 = new javax.swing.JLabel();
        DD_date_comboboxA5 = new javax.swing.JComboBox<>();
        DD_month_comboboxA5 = new javax.swing.JComboBox<>();
        DD_year_comboboxA5 = new javax.swing.JComboBox<>();
        DOC_labelA = new javax.swing.JLabel();
        DOC_date_comboboxA = new javax.swing.JComboBox<>();
        DOC_month_comboboxA = new javax.swing.JComboBox<>();
        DOC_year_comboboxA = new javax.swing.JComboBox<>();
        panelB = new javax.swing.JPanel();
        ScrollPaneB = new javax.swing.JScrollPane();
        TableG_out = new javax.swing.JTable();
        service_number_labelB = new javax.swing.JLabel();
        service_numberB = new javax.swing.JTextField();
        textfield_underline = new javax.swing.JSeparator();
        okayB = new javax.swing.JLabel();
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
        fullscreen_panelB = new javax.swing.JPanel();
        fullscreen_ScrollPaneB = new javax.swing.JScrollPane();
        fullscreen_tableB = new javax.swing.JTable();
        exit_fullscreen_buttonB = new javax.swing.JPanel();
        exit_fullscreen_labelB = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setMaximizedBounds(new java.awt.Rectangle(0, 0, 1280, 720));
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

        title_label.setFont(new java.awt.Font("Montserrat", 0, 28)); // NOI18N
        title_label.setForeground(new java.awt.Color(255, 255, 255));
        title_label.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        title_label.setText("Seniority Roll");
        side_pane.add(title_label, new org.netbeans.lib.awtextra.AbsoluteConstraints(100, 135, 190, 40));

        title_icon.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        title_icon.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/PNGS/seniority_roll_white.png"))); // NOI18N
        side_pane.add(title_icon, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 110, 70, 80));

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
        menuB_label.setText("View by Army Number");

        javax.swing.GroupLayout menuBLayout = new javax.swing.GroupLayout(menuB);
        menuB.setLayout(menuBLayout);
        menuBLayout.setHorizontalGroup(
            menuBLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(menuBLayout.createSequentialGroup()
                .addGap(27, 27, 27)
                .addComponent(menuB_icon)
                .addGap(33, 33, 33)
                .addComponent(menuB_label)
                .addContainerGap(69, Short.MAX_VALUE))
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

        service_number_underline.setForeground(new java.awt.Color(54, 33, 89));
        panelA.add(service_number_underline, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 130, 400, 20));

        service_number_textfield.setFont(new java.awt.Font("Roboto", 0, 24)); // NOI18N
        service_number_textfield.setForeground(new java.awt.Color(54, 33, 89));
        service_number_textfield.setBorder(null);
        service_number_textfield.setMargin(new java.awt.Insets(2, 4, 2, 2));
        service_number_textfield.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                service_number_textfieldKeyPressed(evt);
            }
        });
        panelA.add(service_number_textfield, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 90, 400, 40));

        service_number_label.setFont(new java.awt.Font("Montserrat", 0, 28)); // NOI18N
        service_number_label.setForeground(new java.awt.Color(51, 51, 51));
        service_number_label.setText("Army Number");
        panelA.add(service_number_label, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 50, -1, 40));

        panelA_bottom_line.setForeground(new java.awt.Color(54, 33, 89));
        panelA.add(panelA_bottom_line, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 1880, 910, 5));

        okay.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/PNGS/check-mark.png"))); // NOI18N
        okay.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                okayMouseClicked(evt);
            }
        });
        panelA.add(okay, new org.netbeans.lib.awtextra.AbsoluteConstraints(490, 90, -1, 40));

        name_label.setBackground(new java.awt.Color(255, 255, 255));
        name_label.setFont(new java.awt.Font("Montserrat", 0, 28)); // NOI18N
        name_label.setForeground(new java.awt.Color(51, 51, 51));
        name_label.setText("Name");
        panelA.add(name_label, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 170, -1, 40));

        name.setFont(new java.awt.Font("Roboto Bk", 0, 24)); // NOI18N
        name.setForeground(new java.awt.Color(54, 33, 89));
        name.setText("> XXXXXXX");
        panelA.add(name, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 210, -1, 30));

        rank_label.setBackground(new java.awt.Color(255, 255, 255));
        rank_label.setFont(new java.awt.Font("Montserrat", 0, 28)); // NOI18N
        rank_label.setForeground(new java.awt.Color(51, 51, 51));
        rank_label.setText("Rank");
        panelA.add(rank_label, new org.netbeans.lib.awtextra.AbsoluteConstraints(350, 170, -1, 40));

        rank.setFont(new java.awt.Font("Roboto Bk", 0, 24)); // NOI18N
        rank.setForeground(new java.awt.Color(54, 33, 89));
        rank.setText("> XXXXXXX");
        panelA.add(rank, new org.netbeans.lib.awtextra.AbsoluteConstraints(350, 210, -1, 30));

        company_label.setBackground(new java.awt.Color(255, 255, 255));
        company_label.setFont(new java.awt.Font("Montserrat", 0, 28)); // NOI18N
        company_label.setForeground(new java.awt.Color(51, 51, 51));
        company_label.setText("Company");
        panelA.add(company_label, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 260, -1, 40));

        company.setFont(new java.awt.Font("Roboto Bk", 0, 24)); // NOI18N
        company.setForeground(new java.awt.Color(54, 33, 89));
        company.setText("> XXXXXXX");
        panelA.add(company, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 300, -1, 30));

        trade_label.setBackground(new java.awt.Color(255, 255, 255));
        trade_label.setFont(new java.awt.Font("Montserrat", 0, 28)); // NOI18N
        trade_label.setForeground(new java.awt.Color(51, 51, 51));
        trade_label.setText("Trade");
        panelA.add(trade_label, new org.netbeans.lib.awtextra.AbsoluteConstraints(620, 260, -1, 40));

        DOB.setFont(new java.awt.Font("Roboto Bk", 0, 24)); // NOI18N
        DOB.setForeground(new java.awt.Color(54, 33, 89));
        DOB.setText("> XXXXXXX");
        panelA.add(DOB, new org.netbeans.lib.awtextra.AbsoluteConstraints(620, 210, -1, 30));

        unit_label.setBackground(new java.awt.Color(255, 255, 255));
        unit_label.setFont(new java.awt.Font("Montserrat", 0, 28)); // NOI18N
        unit_label.setForeground(new java.awt.Color(51, 51, 51));
        unit_label.setText("Unit");
        panelA.add(unit_label, new org.netbeans.lib.awtextra.AbsoluteConstraints(350, 260, -1, 40));

        unit.setFont(new java.awt.Font("Roboto Bk", 0, 24)); // NOI18N
        unit.setForeground(new java.awt.Color(54, 33, 89));
        unit.setText("> XXXXXXX");
        panelA.add(unit, new org.netbeans.lib.awtextra.AbsoluteConstraints(350, 300, -1, 30));

        screening_labelA1.setBackground(new java.awt.Color(255, 255, 255));
        screening_labelA1.setFont(new java.awt.Font("Montserrat", 0, 24)); // NOI18N
        screening_labelA1.setForeground(new java.awt.Color(66, 50, 77));
        screening_labelA1.setText("Screening");
        panelA.add(screening_labelA1, new org.netbeans.lib.awtextra.AbsoluteConstraints(320, 670, -1, 40));

        DD_labelA1.setBackground(new java.awt.Color(255, 255, 255));
        DD_labelA1.setFont(new java.awt.Font("Montserrat", 0, 24)); // NOI18N
        DD_labelA1.setForeground(new java.awt.Color(66, 50, 77));
        DD_labelA1.setText("Due date");
        panelA.add(DD_labelA1, new org.netbeans.lib.awtextra.AbsoluteConstraints(560, 670, -1, 40));

        DOS_labelA.setBackground(new java.awt.Color(255, 255, 255));
        DOS_labelA.setFont(new java.awt.Font("Montserrat", 0, 24)); // NOI18N
        DOS_labelA.setForeground(new java.awt.Color(66, 50, 77));
        DOS_labelA.setText("DOS");
        panelA.add(DOS_labelA, new org.netbeans.lib.awtextra.AbsoluteConstraints(560, 1000, -1, 40));

        midA1.setForeground(new java.awt.Color(204, 204, 255));
        panelA.add(midA1, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 950, 570, 10));

        midA2.setForeground(new java.awt.Color(204, 204, 255));
        panelA.add(midA2, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 1450, 570, 10));

        DOB_label.setBackground(new java.awt.Color(255, 255, 255));
        DOB_label.setFont(new java.awt.Font("Montserrat", 0, 28)); // NOI18N
        DOB_label.setForeground(new java.awt.Color(51, 51, 51));
        DOB_label.setText("DOB");
        panelA.add(DOB_label, new org.netbeans.lib.awtextra.AbsoluteConstraints(620, 170, -1, 40));

        DOE_labelA.setBackground(new java.awt.Color(255, 255, 255));
        DOE_labelA.setFont(new java.awt.Font("Montserrat", 0, 28)); // NOI18N
        DOE_labelA.setForeground(new java.awt.Color(51, 51, 51));
        DOE_labelA.setText("Date of Enrolment");
        panelA.add(DOE_labelA, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 370, -1, 40));

        DOE_date_comboboxA.setFont(new java.awt.Font("Roboto", 0, 18)); // NOI18N
        DOE_date_comboboxA.setForeground(new java.awt.Color(44, 62, 80));
        DOE_date_comboboxA.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "DD", "01", "02", "03", "04", "05", "06", "07", "08", "09", "10", "11", "12", "13", "14", "15", "16", "17", "18", "19", "20", "21", "22", "23", "24", "25", "26", "27", "28", "29", "30", "31" }));
        DOE_date_comboboxA.setBorder(null);
        panelA.add(DOE_date_comboboxA, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 410, 100, 40));

        DOE_month_comboboxA.setFont(new java.awt.Font("Roboto", 0, 18)); // NOI18N
        DOE_month_comboboxA.setForeground(new java.awt.Color(44, 62, 80));
        DOE_month_comboboxA.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "MM", "01", "02", "03", "04", "05", "06", "07", "08", "09", "10", "11", "12" }));
        DOE_month_comboboxA.setBorder(null);
        panelA.add(DOE_month_comboboxA, new org.netbeans.lib.awtextra.AbsoluteConstraints(190, 410, 100, 40));

        DOE_year_comboboxA.setFont(new java.awt.Font("Roboto", 0, 18)); // NOI18N
        DOE_year_comboboxA.setForeground(new java.awt.Color(44, 62, 80));
        DOE_year_comboboxA.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "YYYY", "1950", "1951", "1952", "1953", "1954", "1955", "1956", "1957", "1958", "1959", "1960", "1961", "1962", "1963", "1964", "1965", "1966", "1967", "1968", "1969", "1970", "1971", "1972", "1973", "1974", "1975", "1976", "1977", "1978", "1979", "1980", "1981", "1982", "1983", "1984", "1985", "1986", "1987", "1988", "1989", "1990", "1991", "1992", "1993", "1994", "1995", "1996", "1997", "1998", "1999", "2000", "2001", "2002", "2003", "2004", "2005", "2006", "2007", "2008", "2009", "2010", "2011", "2012", "2013", "2014", "2015", "2016", "2017", "2018", "2019", "2020", "2021", "2022", "2023", "2024", "2025", "2026", "2027", "2028", "2029", "2030", "2031", "2032", "2033", "2034", "2035", "2036", "2037", "2038", "2039", "2040", "2041", "2042", "2043", "2044", "2045", "2046", "2047", "2048", "2049", "2050", "2051", "2052", "2053", "2054", "2055", "2056", "2057", "2058", "2059", "2060", "2061", "2062", "2063", "2064", "2065", "2066", "2067", "2068", "2069", "2070", "2071", "2072", "2073", "2074", "2075", "2076", "2077", "2078", "2079", "2080", "2081", "2082", "2083", "2084", "2085", "2086", "2087", "2088", "2089", "2090", "2091", "2092", "2093", "2094", "2095", "2096", "2097", "2098", "2099", "2100", " " }));
        DOE_year_comboboxA.setBorder(null);
        panelA.add(DOE_year_comboboxA, new org.netbeans.lib.awtextra.AbsoluteConstraints(300, 410, 100, 40));

        NK_textfield.setFont(new java.awt.Font("Roboto", 0, 24)); // NOI18N
        NK_textfield.setForeground(new java.awt.Color(54, 33, 89));
        NK_textfield.setBorder(null);
        NK_textfield.setMargin(new java.awt.Insets(2, 4, 2, 2));
        NK_textfield.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                NK_textfieldActionPerformed(evt);
            }
        });
        panelA.add(NK_textfield, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 710, 210, 40));

        cadre_underline1.setForeground(new java.awt.Color(54, 33, 89));
        panelA.add(cadre_underline1, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 750, 210, 20));

        screening_textfieldA1.setFont(new java.awt.Font("Roboto", 0, 24)); // NOI18N
        screening_textfieldA1.setForeground(new java.awt.Color(54, 33, 89));
        screening_textfieldA1.setBorder(null);
        screening_textfieldA1.setMargin(new java.awt.Insets(2, 4, 2, 2));
        screening_textfieldA1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                screening_textfieldA1ActionPerformed(evt);
            }
        });
        panelA.add(screening_textfieldA1, new org.netbeans.lib.awtextra.AbsoluteConstraints(320, 710, 210, 40));

        screening_underlineA1.setForeground(new java.awt.Color(54, 33, 89));
        panelA.add(screening_underlineA1, new org.netbeans.lib.awtextra.AbsoluteConstraints(320, 750, 210, 20));

        trade.setFont(new java.awt.Font("Roboto Bk", 0, 24)); // NOI18N
        trade.setForeground(new java.awt.Color(54, 33, 89));
        trade.setText("> XXXXXXX");
        panelA.add(trade, new org.netbeans.lib.awtextra.AbsoluteConstraints(620, 300, -1, 30));

        screening_label2.setBackground(new java.awt.Color(255, 255, 255));
        screening_label2.setFont(new java.awt.Font("Montserrat", 0, 24)); // NOI18N
        screening_label2.setForeground(new java.awt.Color(66, 50, 77));
        screening_label2.setText("Screening");
        panelA.add(screening_label2, new org.netbeans.lib.awtextra.AbsoluteConstraints(320, 790, -1, 40));

        HAV_textfield1.setFont(new java.awt.Font("Roboto", 0, 24)); // NOI18N
        HAV_textfield1.setForeground(new java.awt.Color(54, 33, 89));
        HAV_textfield1.setBorder(null);
        HAV_textfield1.setMargin(new java.awt.Insets(2, 4, 2, 2));
        HAV_textfield1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                HAV_textfield1ActionPerformed(evt);
            }
        });
        panelA.add(HAV_textfield1, new org.netbeans.lib.awtextra.AbsoluteConstraints(320, 830, 210, 40));

        screening_underlineA2.setForeground(new java.awt.Color(54, 33, 89));
        panelA.add(screening_underlineA2, new org.netbeans.lib.awtextra.AbsoluteConstraints(320, 870, 210, 20));

        TTT_labelA.setFont(new java.awt.Font("Montserrat", 0, 28)); // NOI18N
        TTT_labelA.setForeground(new java.awt.Color(51, 51, 51));
        TTT_labelA.setText("TTT");
        panelA.add(TTT_labelA, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 480, -1, 40));

        TTT_textfieldA.setFont(new java.awt.Font("Roboto", 0, 24)); // NOI18N
        TTT_textfieldA.setForeground(new java.awt.Color(54, 33, 89));
        TTT_textfieldA.setBorder(null);
        TTT_textfieldA.setMargin(new java.awt.Insets(2, 4, 2, 2));
        panelA.add(TTT_textfieldA, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 520, 330, 40));

        TTT_underlineA.setForeground(new java.awt.Color(54, 33, 89));
        panelA.add(TTT_underlineA, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 560, 330, 20));

        MR_labelA.setFont(new java.awt.Font("Montserrat", 0, 28)); // NOI18N
        MR_labelA.setForeground(new java.awt.Color(51, 51, 51));
        MR_labelA.setText("MR");
        panelA.add(MR_labelA, new org.netbeans.lib.awtextra.AbsoluteConstraints(490, 480, -1, 40));

        MR_textfieldA.setFont(new java.awt.Font("Roboto", 0, 24)); // NOI18N
        MR_textfieldA.setForeground(new java.awt.Color(54, 33, 89));
        MR_textfieldA.setBorder(null);
        MR_textfieldA.setMargin(new java.awt.Insets(2, 4, 2, 2));
        panelA.add(MR_textfieldA, new org.netbeans.lib.awtextra.AbsoluteConstraints(490, 520, 330, 40));

        MR_underlineA.setForeground(new java.awt.Color(54, 33, 89));
        panelA.add(MR_underlineA, new org.netbeans.lib.awtextra.AbsoluteConstraints(490, 560, 330, 20));

        screening_undertextA2.setText("Bd Sub");
        panelA.add(screening_undertextA2, new org.netbeans.lib.awtextra.AbsoluteConstraints(320, 880, -1, -1));

        screening_undertextA1.setText("Bd Nb Sub");
        panelA.add(screening_undertextA1, new org.netbeans.lib.awtextra.AbsoluteConstraints(320, 760, -1, -1));

        DD_date_comboboxA1.setFont(new java.awt.Font("Roboto", 0, 18)); // NOI18N
        DD_date_comboboxA1.setForeground(new java.awt.Color(44, 62, 80));
        DD_date_comboboxA1.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "DD", "01", "02", "03", "04", "05", "06", "07", "08", "09", "10", "11", "12", "13", "14", "15", "16", "17", "18", "19", "20", "21", "22", "23", "24", "25", "26", "27", "28", "29", "30", "31" }));
        DD_date_comboboxA1.setBorder(null);
        panelA.add(DD_date_comboboxA1, new org.netbeans.lib.awtextra.AbsoluteConstraints(560, 710, 80, 40));

        DD_month_comboboxA1.setFont(new java.awt.Font("Roboto", 0, 18)); // NOI18N
        DD_month_comboboxA1.setForeground(new java.awt.Color(44, 62, 80));
        DD_month_comboboxA1.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "MM", "01", "02", "03", "04", "05", "06", "07", "08", "09", "10", "11", "12" }));
        DD_month_comboboxA1.setBorder(null);
        panelA.add(DD_month_comboboxA1, new org.netbeans.lib.awtextra.AbsoluteConstraints(650, 710, 80, 40));

        DD_year_comboboxA1.setFont(new java.awt.Font("Roboto", 0, 18)); // NOI18N
        DD_year_comboboxA1.setForeground(new java.awt.Color(44, 62, 80));
        DD_year_comboboxA1.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "YYYY", "1950", "1951", "1952", "1953", "1954", "1955", "1956", "1957", "1958", "1959", "1960", "1961", "1962", "1963", "1964", "1965", "1966", "1967", "1968", "1969", "1970", "1971", "1972", "1973", "1974", "1975", "1976", "1977", "1978", "1979", "1980", "1981", "1982", "1983", "1984", "1985", "1986", "1987", "1988", "1989", "1990", "1991", "1992", "1993", "1994", "1995", "1996", "1997", "1998", "1999", "2000", "2001", "2002", "2003", "2004", "2005", "2006", "2007", "2008", "2009", "2010", "2011", "2012", "2013", "2014", "2015", "2016", "2017", "2018", "2019", "2020", "2021", "2022", "2023", "2024", "2025", "2026", "2027", "2028", "2029", "2030", "2031", "2032", "2033", "2034", "2035", "2036", "2037", "2038", "2039", "2040", "2041", "2042", "2043", "2044", "2045", "2046", "2047", "2048", "2049", "2050", "2051", "2052", "2053", "2054", "2055", "2056", "2057", "2058", "2059", "2060", "2061", "2062", "2063", "2064", "2065", "2066", "2067", "2068", "2069", "2070", "2071", "2072", "2073", "2074", "2075", "2076", "2077", "2078", "2079", "2080", "2081", "2082", "2083", "2084", "2085", "2086", "2087", "2088", "2089", "2090", "2091", "2092", "2093", "2094", "2095", "2096", "2097", "2098", "2099", "2100", " " }));
        DD_year_comboboxA1.setBorder(null);
        panelA.add(DD_year_comboboxA1, new org.netbeans.lib.awtextra.AbsoluteConstraints(740, 710, 80, 40));

        midA3.setForeground(new java.awt.Color(204, 204, 255));
        panelA.add(midA3, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 630, 560, 10));

        vill_label1.setBackground(new java.awt.Color(255, 255, 255));
        vill_label1.setFont(new java.awt.Font("Montserrat", 0, 24)); // NOI18N
        vill_label1.setForeground(new java.awt.Color(66, 50, 77));
        vill_label1.setText("ACE");
        panelA.add(vill_label1, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 670, -1, 40));

        LNK_labelA.setBackground(new java.awt.Color(255, 255, 255));
        LNK_labelA.setFont(new java.awt.Font("Montserrat", 0, 24)); // NOI18N
        LNK_labelA.setForeground(new java.awt.Color(66, 50, 77));
        LNK_labelA.setText("LNK");
        panelA.add(LNK_labelA, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 1050, -1, 40));

        DOP_labelA.setBackground(new java.awt.Color(255, 255, 255));
        DOP_labelA.setFont(new java.awt.Font("Montserrat", 0, 24)); // NOI18N
        DOP_labelA.setForeground(new java.awt.Color(66, 50, 77));
        DOP_labelA.setText("DOP");
        panelA.add(DOP_labelA, new org.netbeans.lib.awtextra.AbsoluteConstraints(230, 1000, -1, 40));

        DOP_date_combobox1.setFont(new java.awt.Font("Roboto", 0, 18)); // NOI18N
        DOP_date_combobox1.setForeground(new java.awt.Color(44, 62, 80));
        DOP_date_combobox1.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "DD", "01", "02", "03", "04", "05", "06", "07", "08", "09", "10", "11", "12", "13", "14", "15", "16", "17", "18", "19", "20", "21", "22", "23", "24", "25", "26", "27", "28", "29", "30", "31" }));
        DOP_date_combobox1.setBorder(null);
        panelA.add(DOP_date_combobox1, new org.netbeans.lib.awtextra.AbsoluteConstraints(230, 1050, 80, 40));

        DOP_month_combobox1.setFont(new java.awt.Font("Roboto", 0, 18)); // NOI18N
        DOP_month_combobox1.setForeground(new java.awt.Color(44, 62, 80));
        DOP_month_combobox1.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "MM", "01", "02", "03", "04", "05", "06", "07", "08", "09", "10", "11", "12" }));
        DOP_month_combobox1.setBorder(null);
        panelA.add(DOP_month_combobox1, new org.netbeans.lib.awtextra.AbsoluteConstraints(320, 1050, 80, 40));

        DOP_year_combobox1.setFont(new java.awt.Font("Roboto", 0, 18)); // NOI18N
        DOP_year_combobox1.setForeground(new java.awt.Color(44, 62, 80));
        DOP_year_combobox1.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "YYYY", "1950", "1951", "1952", "1953", "1954", "1955", "1956", "1957", "1958", "1959", "1960", "1961", "1962", "1963", "1964", "1965", "1966", "1967", "1968", "1969", "1970", "1971", "1972", "1973", "1974", "1975", "1976", "1977", "1978", "1979", "1980", "1981", "1982", "1983", "1984", "1985", "1986", "1987", "1988", "1989", "1990", "1991", "1992", "1993", "1994", "1995", "1996", "1997", "1998", "1999", "2000", "2001", "2002", "2003", "2004", "2005", "2006", "2007", "2008", "2009", "2010", "2011", "2012", "2013", "2014", "2015", "2016", "2017", "2018", "2019", "2020", "2021", "2022", "2023", "2024", "2025", "2026", "2027", "2028", "2029", "2030", "2031", "2032", "2033", "2034", "2035", "2036", "2037", "2038", "2039", "2040", "2041", "2042", "2043", "2044", "2045", "2046", "2047", "2048", "2049", "2050", "2051", "2052", "2053", "2054", "2055", "2056", "2057", "2058", "2059", "2060", "2061", "2062", "2063", "2064", "2065", "2066", "2067", "2068", "2069", "2070", "2071", "2072", "2073", "2074", "2075", "2076", "2077", "2078", "2079", "2080", "2081", "2082", "2083", "2084", "2085", "2086", "2087", "2088", "2089", "2090", "2091", "2092", "2093", "2094", "2095", "2096", "2097", "2098", "2099", "2100", " " }));
        DOP_year_combobox1.setBorder(null);
        panelA.add(DOP_year_combobox1, new org.netbeans.lib.awtextra.AbsoluteConstraints(410, 1050, 80, 40));

        rank_labelA.setBackground(new java.awt.Color(255, 255, 255));
        rank_labelA.setFont(new java.awt.Font("Montserrat", 0, 24)); // NOI18N
        rank_labelA.setForeground(new java.awt.Color(66, 50, 77));
        rank_labelA.setText("Rank");
        panelA.add(rank_labelA, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 1000, -1, 40));

        LHAV_labelA.setBackground(new java.awt.Color(255, 255, 255));
        LHAV_labelA.setFont(new java.awt.Font("Montserrat", 0, 24)); // NOI18N
        LHAV_labelA.setForeground(new java.awt.Color(66, 50, 77));
        LHAV_labelA.setText("LHAV");
        panelA.add(LHAV_labelA, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 1150, -1, 40));

        DOS_date_combobox1.setFont(new java.awt.Font("Roboto", 0, 18)); // NOI18N
        DOS_date_combobox1.setForeground(new java.awt.Color(44, 62, 80));
        DOS_date_combobox1.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "DD", "01", "02", "03", "04", "05", "06", "07", "08", "09", "10", "11", "12", "13", "14", "15", "16", "17", "18", "19", "20", "21", "22", "23", "24", "25", "26", "27", "28", "29", "30", "31" }));
        DOS_date_combobox1.setBorder(null);
        panelA.add(DOS_date_combobox1, new org.netbeans.lib.awtextra.AbsoluteConstraints(560, 1100, 80, 40));

        DOS_month_combobox1.setFont(new java.awt.Font("Roboto", 0, 18)); // NOI18N
        DOS_month_combobox1.setForeground(new java.awt.Color(44, 62, 80));
        DOS_month_combobox1.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "MM", "01", "02", "03", "04", "05", "06", "07", "08", "09", "10", "11", "12" }));
        DOS_month_combobox1.setBorder(null);
        panelA.add(DOS_month_combobox1, new org.netbeans.lib.awtextra.AbsoluteConstraints(650, 1100, 80, 40));

        DOS_year_combobox1.setFont(new java.awt.Font("Roboto", 0, 18)); // NOI18N
        DOS_year_combobox1.setForeground(new java.awt.Color(44, 62, 80));
        DOS_year_combobox1.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "YYYY", "1950", "1951", "1952", "1953", "1954", "1955", "1956", "1957", "1958", "1959", "1960", "1961", "1962", "1963", "1964", "1965", "1966", "1967", "1968", "1969", "1970", "1971", "1972", "1973", "1974", "1975", "1976", "1977", "1978", "1979", "1980", "1981", "1982", "1983", "1984", "1985", "1986", "1987", "1988", "1989", "1990", "1991", "1992", "1993", "1994", "1995", "1996", "1997", "1998", "1999", "2000", "2001", "2002", "2003", "2004", "2005", "2006", "2007", "2008", "2009", "2010", "2011", "2012", "2013", "2014", "2015", "2016", "2017", "2018", "2019", "2020", "2021", "2022", "2023", "2024", "2025", "2026", "2027", "2028", "2029", "2030", "2031", "2032", "2033", "2034", "2035", "2036", "2037", "2038", "2039", "2040", "2041", "2042", "2043", "2044", "2045", "2046", "2047", "2048", "2049", "2050", "2051", "2052", "2053", "2054", "2055", "2056", "2057", "2058", "2059", "2060", "2061", "2062", "2063", "2064", "2065", "2066", "2067", "2068", "2069", "2070", "2071", "2072", "2073", "2074", "2075", "2076", "2077", "2078", "2079", "2080", "2081", "2082", "2083", "2084", "2085", "2086", "2087", "2088", "2089", "2090", "2091", "2092", "2093", "2094", "2095", "2096", "2097", "2098", "2099", "2100", " " }));
        DOS_year_combobox1.setBorder(null);
        panelA.add(DOS_year_combobox1, new org.netbeans.lib.awtextra.AbsoluteConstraints(740, 1100, 80, 40));

        DOP_date_combobox2.setFont(new java.awt.Font("Roboto", 0, 18)); // NOI18N
        DOP_date_combobox2.setForeground(new java.awt.Color(44, 62, 80));
        DOP_date_combobox2.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "DD", "01", "02", "03", "04", "05", "06", "07", "08", "09", "10", "11", "12", "13", "14", "15", "16", "17", "18", "19", "20", "21", "22", "23", "24", "25", "26", "27", "28", "29", "30", "31" }));
        DOP_date_combobox2.setBorder(null);
        panelA.add(DOP_date_combobox2, new org.netbeans.lib.awtextra.AbsoluteConstraints(230, 1100, 80, 40));

        DOP_month_combobox2.setFont(new java.awt.Font("Roboto", 0, 18)); // NOI18N
        DOP_month_combobox2.setForeground(new java.awt.Color(44, 62, 80));
        DOP_month_combobox2.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "MM", "01", "02", "03", "04", "05", "06", "07", "08", "09", "10", "11", "12" }));
        DOP_month_combobox2.setBorder(null);
        panelA.add(DOP_month_combobox2, new org.netbeans.lib.awtextra.AbsoluteConstraints(320, 1100, 80, 40));

        DOP_year_combobox2.setFont(new java.awt.Font("Roboto", 0, 18)); // NOI18N
        DOP_year_combobox2.setForeground(new java.awt.Color(44, 62, 80));
        DOP_year_combobox2.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "YYYY", "1950", "1951", "1952", "1953", "1954", "1955", "1956", "1957", "1958", "1959", "1960", "1961", "1962", "1963", "1964", "1965", "1966", "1967", "1968", "1969", "1970", "1971", "1972", "1973", "1974", "1975", "1976", "1977", "1978", "1979", "1980", "1981", "1982", "1983", "1984", "1985", "1986", "1987", "1988", "1989", "1990", "1991", "1992", "1993", "1994", "1995", "1996", "1997", "1998", "1999", "2000", "2001", "2002", "2003", "2004", "2005", "2006", "2007", "2008", "2009", "2010", "2011", "2012", "2013", "2014", "2015", "2016", "2017", "2018", "2019", "2020", "2021", "2022", "2023", "2024", "2025", "2026", "2027", "2028", "2029", "2030", "2031", "2032", "2033", "2034", "2035", "2036", "2037", "2038", "2039", "2040", "2041", "2042", "2043", "2044", "2045", "2046", "2047", "2048", "2049", "2050", "2051", "2052", "2053", "2054", "2055", "2056", "2057", "2058", "2059", "2060", "2061", "2062", "2063", "2064", "2065", "2066", "2067", "2068", "2069", "2070", "2071", "2072", "2073", "2074", "2075", "2076", "2077", "2078", "2079", "2080", "2081", "2082", "2083", "2084", "2085", "2086", "2087", "2088", "2089", "2090", "2091", "2092", "2093", "2094", "2095", "2096", "2097", "2098", "2099", "2100", " " }));
        DOP_year_combobox2.setBorder(null);
        panelA.add(DOP_year_combobox2, new org.netbeans.lib.awtextra.AbsoluteConstraints(410, 1100, 80, 40));

        NK_labelA.setBackground(new java.awt.Color(255, 255, 255));
        NK_labelA.setFont(new java.awt.Font("Montserrat", 0, 24)); // NOI18N
        NK_labelA.setForeground(new java.awt.Color(66, 50, 77));
        NK_labelA.setText("NK");
        panelA.add(NK_labelA, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 1100, -1, 40));

        DOP_date_combobox3.setFont(new java.awt.Font("Roboto", 0, 18)); // NOI18N
        DOP_date_combobox3.setForeground(new java.awt.Color(44, 62, 80));
        DOP_date_combobox3.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "DD", "01", "02", "03", "04", "05", "06", "07", "08", "09", "10", "11", "12", "13", "14", "15", "16", "17", "18", "19", "20", "21", "22", "23", "24", "25", "26", "27", "28", "29", "30", "31" }));
        DOP_date_combobox3.setBorder(null);
        panelA.add(DOP_date_combobox3, new org.netbeans.lib.awtextra.AbsoluteConstraints(230, 1150, 80, 40));

        DOP_month_combobox3.setFont(new java.awt.Font("Roboto", 0, 18)); // NOI18N
        DOP_month_combobox3.setForeground(new java.awt.Color(44, 62, 80));
        DOP_month_combobox3.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "MM", "01", "02", "03", "04", "05", "06", "07", "08", "09", "10", "11", "12" }));
        DOP_month_combobox3.setBorder(null);
        panelA.add(DOP_month_combobox3, new org.netbeans.lib.awtextra.AbsoluteConstraints(320, 1150, 80, 40));

        DOP_year_combobox3.setFont(new java.awt.Font("Roboto", 0, 18)); // NOI18N
        DOP_year_combobox3.setForeground(new java.awt.Color(44, 62, 80));
        DOP_year_combobox3.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "YYYY", "1950", "1951", "1952", "1953", "1954", "1955", "1956", "1957", "1958", "1959", "1960", "1961", "1962", "1963", "1964", "1965", "1966", "1967", "1968", "1969", "1970", "1971", "1972", "1973", "1974", "1975", "1976", "1977", "1978", "1979", "1980", "1981", "1982", "1983", "1984", "1985", "1986", "1987", "1988", "1989", "1990", "1991", "1992", "1993", "1994", "1995", "1996", "1997", "1998", "1999", "2000", "2001", "2002", "2003", "2004", "2005", "2006", "2007", "2008", "2009", "2010", "2011", "2012", "2013", "2014", "2015", "2016", "2017", "2018", "2019", "2020", "2021", "2022", "2023", "2024", "2025", "2026", "2027", "2028", "2029", "2030", "2031", "2032", "2033", "2034", "2035", "2036", "2037", "2038", "2039", "2040", "2041", "2042", "2043", "2044", "2045", "2046", "2047", "2048", "2049", "2050", "2051", "2052", "2053", "2054", "2055", "2056", "2057", "2058", "2059", "2060", "2061", "2062", "2063", "2064", "2065", "2066", "2067", "2068", "2069", "2070", "2071", "2072", "2073", "2074", "2075", "2076", "2077", "2078", "2079", "2080", "2081", "2082", "2083", "2084", "2085", "2086", "2087", "2088", "2089", "2090", "2091", "2092", "2093", "2094", "2095", "2096", "2097", "2098", "2099", "2100", " " }));
        DOP_year_combobox3.setBorder(null);
        panelA.add(DOP_year_combobox3, new org.netbeans.lib.awtextra.AbsoluteConstraints(410, 1150, 80, 40));

        HAV_labelA.setBackground(new java.awt.Color(255, 255, 255));
        HAV_labelA.setFont(new java.awt.Font("Montserrat", 0, 24)); // NOI18N
        HAV_labelA.setForeground(new java.awt.Color(66, 50, 77));
        HAV_labelA.setText("HAV");
        panelA.add(HAV_labelA, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 1200, -1, 40));

        DOP_date_combobox4.setFont(new java.awt.Font("Roboto", 0, 18)); // NOI18N
        DOP_date_combobox4.setForeground(new java.awt.Color(44, 62, 80));
        DOP_date_combobox4.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "DD", "01", "02", "03", "04", "05", "06", "07", "08", "09", "10", "11", "12", "13", "14", "15", "16", "17", "18", "19", "20", "21", "22", "23", "24", "25", "26", "27", "28", "29", "30", "31" }));
        DOP_date_combobox4.setBorder(null);
        panelA.add(DOP_date_combobox4, new org.netbeans.lib.awtextra.AbsoluteConstraints(230, 1200, 80, 40));

        DOP_month_combobox4.setFont(new java.awt.Font("Roboto", 0, 18)); // NOI18N
        DOP_month_combobox4.setForeground(new java.awt.Color(44, 62, 80));
        DOP_month_combobox4.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "MM", "01", "02", "03", "04", "05", "06", "07", "08", "09", "10", "11", "12" }));
        DOP_month_combobox4.setBorder(null);
        panelA.add(DOP_month_combobox4, new org.netbeans.lib.awtextra.AbsoluteConstraints(320, 1200, 80, 40));

        DOP_year_combobox4.setFont(new java.awt.Font("Roboto", 0, 18)); // NOI18N
        DOP_year_combobox4.setForeground(new java.awt.Color(44, 62, 80));
        DOP_year_combobox4.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "YYYY", "1950", "1951", "1952", "1953", "1954", "1955", "1956", "1957", "1958", "1959", "1960", "1961", "1962", "1963", "1964", "1965", "1966", "1967", "1968", "1969", "1970", "1971", "1972", "1973", "1974", "1975", "1976", "1977", "1978", "1979", "1980", "1981", "1982", "1983", "1984", "1985", "1986", "1987", "1988", "1989", "1990", "1991", "1992", "1993", "1994", "1995", "1996", "1997", "1998", "1999", "2000", "2001", "2002", "2003", "2004", "2005", "2006", "2007", "2008", "2009", "2010", "2011", "2012", "2013", "2014", "2015", "2016", "2017", "2018", "2019", "2020", "2021", "2022", "2023", "2024", "2025", "2026", "2027", "2028", "2029", "2030", "2031", "2032", "2033", "2034", "2035", "2036", "2037", "2038", "2039", "2040", "2041", "2042", "2043", "2044", "2045", "2046", "2047", "2048", "2049", "2050", "2051", "2052", "2053", "2054", "2055", "2056", "2057", "2058", "2059", "2060", "2061", "2062", "2063", "2064", "2065", "2066", "2067", "2068", "2069", "2070", "2071", "2072", "2073", "2074", "2075", "2076", "2077", "2078", "2079", "2080", "2081", "2082", "2083", "2084", "2085", "2086", "2087", "2088", "2089", "2090", "2091", "2092", "2093", "2094", "2095", "2096", "2097", "2098", "2099", "2100", " " }));
        DOP_year_combobox4.setBorder(null);
        DOP_year_combobox4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                DOP_year_combobox4ActionPerformed(evt);
            }
        });
        panelA.add(DOP_year_combobox4, new org.netbeans.lib.awtextra.AbsoluteConstraints(410, 1200, 80, 40));

        NB_SUB_labelA.setBackground(new java.awt.Color(255, 255, 255));
        NB_SUB_labelA.setFont(new java.awt.Font("Montserrat", 0, 24)); // NOI18N
        NB_SUB_labelA.setForeground(new java.awt.Color(66, 50, 77));
        NB_SUB_labelA.setText("NB SUB");
        panelA.add(NB_SUB_labelA, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 1250, -1, 40));

        DOP_date_combobox5.setFont(new java.awt.Font("Roboto", 0, 18)); // NOI18N
        DOP_date_combobox5.setForeground(new java.awt.Color(44, 62, 80));
        DOP_date_combobox5.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "DD", "01", "02", "03", "04", "05", "06", "07", "08", "09", "10", "11", "12", "13", "14", "15", "16", "17", "18", "19", "20", "21", "22", "23", "24", "25", "26", "27", "28", "29", "30", "31" }));
        DOP_date_combobox5.setBorder(null);
        panelA.add(DOP_date_combobox5, new org.netbeans.lib.awtextra.AbsoluteConstraints(230, 1250, 80, 40));

        DOP_month_combobox5.setFont(new java.awt.Font("Roboto", 0, 18)); // NOI18N
        DOP_month_combobox5.setForeground(new java.awt.Color(44, 62, 80));
        DOP_month_combobox5.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "MM", "01", "02", "03", "04", "05", "06", "07", "08", "09", "10", "11", "12" }));
        DOP_month_combobox5.setBorder(null);
        panelA.add(DOP_month_combobox5, new org.netbeans.lib.awtextra.AbsoluteConstraints(320, 1250, 80, 40));

        DOP_year_combobox5.setFont(new java.awt.Font("Roboto", 0, 18)); // NOI18N
        DOP_year_combobox5.setForeground(new java.awt.Color(44, 62, 80));
        DOP_year_combobox5.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "YYYY", "1950", "1951", "1952", "1953", "1954", "1955", "1956", "1957", "1958", "1959", "1960", "1961", "1962", "1963", "1964", "1965", "1966", "1967", "1968", "1969", "1970", "1971", "1972", "1973", "1974", "1975", "1976", "1977", "1978", "1979", "1980", "1981", "1982", "1983", "1984", "1985", "1986", "1987", "1988", "1989", "1990", "1991", "1992", "1993", "1994", "1995", "1996", "1997", "1998", "1999", "2000", "2001", "2002", "2003", "2004", "2005", "2006", "2007", "2008", "2009", "2010", "2011", "2012", "2013", "2014", "2015", "2016", "2017", "2018", "2019", "2020", "2021", "2022", "2023", "2024", "2025", "2026", "2027", "2028", "2029", "2030", "2031", "2032", "2033", "2034", "2035", "2036", "2037", "2038", "2039", "2040", "2041", "2042", "2043", "2044", "2045", "2046", "2047", "2048", "2049", "2050", "2051", "2052", "2053", "2054", "2055", "2056", "2057", "2058", "2059", "2060", "2061", "2062", "2063", "2064", "2065", "2066", "2067", "2068", "2069", "2070", "2071", "2072", "2073", "2074", "2075", "2076", "2077", "2078", "2079", "2080", "2081", "2082", "2083", "2084", "2085", "2086", "2087", "2088", "2089", "2090", "2091", "2092", "2093", "2094", "2095", "2096", "2097", "2098", "2099", "2100", " " }));
        DOP_year_combobox5.setBorder(null);
        DOP_year_combobox5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                DOP_year_combobox5ActionPerformed(evt);
            }
        });
        panelA.add(DOP_year_combobox5, new org.netbeans.lib.awtextra.AbsoluteConstraints(410, 1250, 80, 40));

        DOP_date_combobox6.setFont(new java.awt.Font("Roboto", 0, 18)); // NOI18N
        DOP_date_combobox6.setForeground(new java.awt.Color(44, 62, 80));
        DOP_date_combobox6.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "DD", "01", "02", "03", "04", "05", "06", "07", "08", "09", "10", "11", "12", "13", "14", "15", "16", "17", "18", "19", "20", "21", "22", "23", "24", "25", "26", "27", "28", "29", "30", "31" }));
        DOP_date_combobox6.setBorder(null);
        panelA.add(DOP_date_combobox6, new org.netbeans.lib.awtextra.AbsoluteConstraints(230, 1300, 80, 40));

        DOP_month_combobox6.setFont(new java.awt.Font("Roboto", 0, 18)); // NOI18N
        DOP_month_combobox6.setForeground(new java.awt.Color(44, 62, 80));
        DOP_month_combobox6.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "MM", "01", "02", "03", "04", "05", "06", "07", "08", "09", "10", "11", "12" }));
        DOP_month_combobox6.setBorder(null);
        panelA.add(DOP_month_combobox6, new org.netbeans.lib.awtextra.AbsoluteConstraints(320, 1300, 80, 40));

        DOP_year_combobox6.setFont(new java.awt.Font("Roboto", 0, 18)); // NOI18N
        DOP_year_combobox6.setForeground(new java.awt.Color(44, 62, 80));
        DOP_year_combobox6.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "YYYY", "1950", "1951", "1952", "1953", "1954", "1955", "1956", "1957", "1958", "1959", "1960", "1961", "1962", "1963", "1964", "1965", "1966", "1967", "1968", "1969", "1970", "1971", "1972", "1973", "1974", "1975", "1976", "1977", "1978", "1979", "1980", "1981", "1982", "1983", "1984", "1985", "1986", "1987", "1988", "1989", "1990", "1991", "1992", "1993", "1994", "1995", "1996", "1997", "1998", "1999", "2000", "2001", "2002", "2003", "2004", "2005", "2006", "2007", "2008", "2009", "2010", "2011", "2012", "2013", "2014", "2015", "2016", "2017", "2018", "2019", "2020", "2021", "2022", "2023", "2024", "2025", "2026", "2027", "2028", "2029", "2030", "2031", "2032", "2033", "2034", "2035", "2036", "2037", "2038", "2039", "2040", "2041", "2042", "2043", "2044", "2045", "2046", "2047", "2048", "2049", "2050", "2051", "2052", "2053", "2054", "2055", "2056", "2057", "2058", "2059", "2060", "2061", "2062", "2063", "2064", "2065", "2066", "2067", "2068", "2069", "2070", "2071", "2072", "2073", "2074", "2075", "2076", "2077", "2078", "2079", "2080", "2081", "2082", "2083", "2084", "2085", "2086", "2087", "2088", "2089", "2090", "2091", "2092", "2093", "2094", "2095", "2096", "2097", "2098", "2099", "2100", " " }));
        DOP_year_combobox6.setBorder(null);
        DOP_year_combobox6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                DOP_year_combobox6ActionPerformed(evt);
            }
        });
        panelA.add(DOP_year_combobox6, new org.netbeans.lib.awtextra.AbsoluteConstraints(410, 1300, 80, 40));

        SUB_labelA.setBackground(new java.awt.Color(255, 255, 255));
        SUB_labelA.setFont(new java.awt.Font("Montserrat", 0, 24)); // NOI18N
        SUB_labelA.setForeground(new java.awt.Color(66, 50, 77));
        SUB_labelA.setText("SUB");
        panelA.add(SUB_labelA, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 1300, -1, 40));

        DOP_year_combobox7.setFont(new java.awt.Font("Roboto", 0, 18)); // NOI18N
        DOP_year_combobox7.setForeground(new java.awt.Color(44, 62, 80));
        DOP_year_combobox7.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "YYYY", "1950", "1951", "1952", "1953", "1954", "1955", "1956", "1957", "1958", "1959", "1960", "1961", "1962", "1963", "1964", "1965", "1966", "1967", "1968", "1969", "1970", "1971", "1972", "1973", "1974", "1975", "1976", "1977", "1978", "1979", "1980", "1981", "1982", "1983", "1984", "1985", "1986", "1987", "1988", "1989", "1990", "1991", "1992", "1993", "1994", "1995", "1996", "1997", "1998", "1999", "2000", "2001", "2002", "2003", "2004", "2005", "2006", "2007", "2008", "2009", "2010", "2011", "2012", "2013", "2014", "2015", "2016", "2017", "2018", "2019", "2020", "2021", "2022", "2023", "2024", "2025", "2026", "2027", "2028", "2029", "2030", "2031", "2032", "2033", "2034", "2035", "2036", "2037", "2038", "2039", "2040", "2041", "2042", "2043", "2044", "2045", "2046", "2047", "2048", "2049", "2050", "2051", "2052", "2053", "2054", "2055", "2056", "2057", "2058", "2059", "2060", "2061", "2062", "2063", "2064", "2065", "2066", "2067", "2068", "2069", "2070", "2071", "2072", "2073", "2074", "2075", "2076", "2077", "2078", "2079", "2080", "2081", "2082", "2083", "2084", "2085", "2086", "2087", "2088", "2089", "2090", "2091", "2092", "2093", "2094", "2095", "2096", "2097", "2098", "2099", "2100", " " }));
        DOP_year_combobox7.setBorder(null);
        DOP_year_combobox7.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                DOP_year_combobox7ActionPerformed(evt);
            }
        });
        panelA.add(DOP_year_combobox7, new org.netbeans.lib.awtextra.AbsoluteConstraints(410, 1350, 80, 40));

        SUB_MAJ_labelA.setBackground(new java.awt.Color(255, 255, 255));
        SUB_MAJ_labelA.setFont(new java.awt.Font("Montserrat", 0, 24)); // NOI18N
        SUB_MAJ_labelA.setForeground(new java.awt.Color(66, 50, 77));
        SUB_MAJ_labelA.setText("SUB MAJ");
        panelA.add(SUB_MAJ_labelA, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 1350, -1, 40));

        DOP_date_combobox7.setFont(new java.awt.Font("Roboto", 0, 18)); // NOI18N
        DOP_date_combobox7.setForeground(new java.awt.Color(44, 62, 80));
        DOP_date_combobox7.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "DD", "01", "02", "03", "04", "05", "06", "07", "08", "09", "10", "11", "12", "13", "14", "15", "16", "17", "18", "19", "20", "21", "22", "23", "24", "25", "26", "27", "28", "29", "30", "31" }));
        DOP_date_combobox7.setBorder(null);
        panelA.add(DOP_date_combobox7, new org.netbeans.lib.awtextra.AbsoluteConstraints(230, 1350, 80, 40));

        DOP_month_combobox7.setFont(new java.awt.Font("Roboto", 0, 18)); // NOI18N
        DOP_month_combobox7.setForeground(new java.awt.Color(44, 62, 80));
        DOP_month_combobox7.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "MM", "01", "02", "03", "04", "05", "06", "07", "08", "09", "10", "11", "12" }));
        DOP_month_combobox7.setBorder(null);
        panelA.add(DOP_month_combobox7, new org.netbeans.lib.awtextra.AbsoluteConstraints(320, 1350, 80, 40));

        DOS_date_combobox2.setFont(new java.awt.Font("Roboto", 0, 18)); // NOI18N
        DOS_date_combobox2.setForeground(new java.awt.Color(44, 62, 80));
        DOS_date_combobox2.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "DD", "01", "02", "03", "04", "05", "06", "07", "08", "09", "10", "11", "12", "13", "14", "15", "16", "17", "18", "19", "20", "21", "22", "23", "24", "25", "26", "27", "28", "29", "30", "31" }));
        DOS_date_combobox2.setBorder(null);
        panelA.add(DOS_date_combobox2, new org.netbeans.lib.awtextra.AbsoluteConstraints(560, 1200, 80, 40));

        DOS_month_combobox2.setFont(new java.awt.Font("Roboto", 0, 18)); // NOI18N
        DOS_month_combobox2.setForeground(new java.awt.Color(44, 62, 80));
        DOS_month_combobox2.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "MM", "01", "02", "03", "04", "05", "06", "07", "08", "09", "10", "11", "12" }));
        DOS_month_combobox2.setBorder(null);
        panelA.add(DOS_month_combobox2, new org.netbeans.lib.awtextra.AbsoluteConstraints(650, 1200, 80, 40));

        DOS_year_combobox2.setFont(new java.awt.Font("Roboto", 0, 18)); // NOI18N
        DOS_year_combobox2.setForeground(new java.awt.Color(44, 62, 80));
        DOS_year_combobox2.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "YYYY", "1950", "1951", "1952", "1953", "1954", "1955", "1956", "1957", "1958", "1959", "1960", "1961", "1962", "1963", "1964", "1965", "1966", "1967", "1968", "1969", "1970", "1971", "1972", "1973", "1974", "1975", "1976", "1977", "1978", "1979", "1980", "1981", "1982", "1983", "1984", "1985", "1986", "1987", "1988", "1989", "1990", "1991", "1992", "1993", "1994", "1995", "1996", "1997", "1998", "1999", "2000", "2001", "2002", "2003", "2004", "2005", "2006", "2007", "2008", "2009", "2010", "2011", "2012", "2013", "2014", "2015", "2016", "2017", "2018", "2019", "2020", "2021", "2022", "2023", "2024", "2025", "2026", "2027", "2028", "2029", "2030", "2031", "2032", "2033", "2034", "2035", "2036", "2037", "2038", "2039", "2040", "2041", "2042", "2043", "2044", "2045", "2046", "2047", "2048", "2049", "2050", "2051", "2052", "2053", "2054", "2055", "2056", "2057", "2058", "2059", "2060", "2061", "2062", "2063", "2064", "2065", "2066", "2067", "2068", "2069", "2070", "2071", "2072", "2073", "2074", "2075", "2076", "2077", "2078", "2079", "2080", "2081", "2082", "2083", "2084", "2085", "2086", "2087", "2088", "2089", "2090", "2091", "2092", "2093", "2094", "2095", "2096", "2097", "2098", "2099", "2100", " " }));
        DOS_year_combobox2.setBorder(null);
        panelA.add(DOS_year_combobox2, new org.netbeans.lib.awtextra.AbsoluteConstraints(740, 1200, 80, 40));

        DOS_month_combobox3.setFont(new java.awt.Font("Roboto", 0, 18)); // NOI18N
        DOS_month_combobox3.setForeground(new java.awt.Color(44, 62, 80));
        DOS_month_combobox3.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "MM", "01", "02", "03", "04", "05", "06", "07", "08", "09", "10", "11", "12" }));
        DOS_month_combobox3.setBorder(null);
        panelA.add(DOS_month_combobox3, new org.netbeans.lib.awtextra.AbsoluteConstraints(650, 1250, 80, 40));

        DOS_year_combobox3.setFont(new java.awt.Font("Roboto", 0, 18)); // NOI18N
        DOS_year_combobox3.setForeground(new java.awt.Color(44, 62, 80));
        DOS_year_combobox3.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "YYYY", "1950", "1951", "1952", "1953", "1954", "1955", "1956", "1957", "1958", "1959", "1960", "1961", "1962", "1963", "1964", "1965", "1966", "1967", "1968", "1969", "1970", "1971", "1972", "1973", "1974", "1975", "1976", "1977", "1978", "1979", "1980", "1981", "1982", "1983", "1984", "1985", "1986", "1987", "1988", "1989", "1990", "1991", "1992", "1993", "1994", "1995", "1996", "1997", "1998", "1999", "2000", "2001", "2002", "2003", "2004", "2005", "2006", "2007", "2008", "2009", "2010", "2011", "2012", "2013", "2014", "2015", "2016", "2017", "2018", "2019", "2020", "2021", "2022", "2023", "2024", "2025", "2026", "2027", "2028", "2029", "2030", "2031", "2032", "2033", "2034", "2035", "2036", "2037", "2038", "2039", "2040", "2041", "2042", "2043", "2044", "2045", "2046", "2047", "2048", "2049", "2050", "2051", "2052", "2053", "2054", "2055", "2056", "2057", "2058", "2059", "2060", "2061", "2062", "2063", "2064", "2065", "2066", "2067", "2068", "2069", "2070", "2071", "2072", "2073", "2074", "2075", "2076", "2077", "2078", "2079", "2080", "2081", "2082", "2083", "2084", "2085", "2086", "2087", "2088", "2089", "2090", "2091", "2092", "2093", "2094", "2095", "2096", "2097", "2098", "2099", "2100", " " }));
        DOS_year_combobox3.setBorder(null);
        panelA.add(DOS_year_combobox3, new org.netbeans.lib.awtextra.AbsoluteConstraints(740, 1250, 80, 40));

        DOS_date_combobox3.setFont(new java.awt.Font("Roboto", 0, 18)); // NOI18N
        DOS_date_combobox3.setForeground(new java.awt.Color(44, 62, 80));
        DOS_date_combobox3.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "DD", "01", "02", "03", "04", "05", "06", "07", "08", "09", "10", "11", "12", "13", "14", "15", "16", "17", "18", "19", "20", "21", "22", "23", "24", "25", "26", "27", "28", "29", "30", "31" }));
        DOS_date_combobox3.setBorder(null);
        panelA.add(DOS_date_combobox3, new org.netbeans.lib.awtextra.AbsoluteConstraints(560, 1250, 80, 40));

        DOS_month_combobox4.setFont(new java.awt.Font("Roboto", 0, 18)); // NOI18N
        DOS_month_combobox4.setForeground(new java.awt.Color(44, 62, 80));
        DOS_month_combobox4.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "MM", "01", "02", "03", "04", "05", "06", "07", "08", "09", "10", "11", "12" }));
        DOS_month_combobox4.setBorder(null);
        panelA.add(DOS_month_combobox4, new org.netbeans.lib.awtextra.AbsoluteConstraints(650, 1300, 80, 40));

        DOS_year_combobox4.setFont(new java.awt.Font("Roboto", 0, 18)); // NOI18N
        DOS_year_combobox4.setForeground(new java.awt.Color(44, 62, 80));
        DOS_year_combobox4.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "YYYY", "1950", "1951", "1952", "1953", "1954", "1955", "1956", "1957", "1958", "1959", "1960", "1961", "1962", "1963", "1964", "1965", "1966", "1967", "1968", "1969", "1970", "1971", "1972", "1973", "1974", "1975", "1976", "1977", "1978", "1979", "1980", "1981", "1982", "1983", "1984", "1985", "1986", "1987", "1988", "1989", "1990", "1991", "1992", "1993", "1994", "1995", "1996", "1997", "1998", "1999", "2000", "2001", "2002", "2003", "2004", "2005", "2006", "2007", "2008", "2009", "2010", "2011", "2012", "2013", "2014", "2015", "2016", "2017", "2018", "2019", "2020", "2021", "2022", "2023", "2024", "2025", "2026", "2027", "2028", "2029", "2030", "2031", "2032", "2033", "2034", "2035", "2036", "2037", "2038", "2039", "2040", "2041", "2042", "2043", "2044", "2045", "2046", "2047", "2048", "2049", "2050", "2051", "2052", "2053", "2054", "2055", "2056", "2057", "2058", "2059", "2060", "2061", "2062", "2063", "2064", "2065", "2066", "2067", "2068", "2069", "2070", "2071", "2072", "2073", "2074", "2075", "2076", "2077", "2078", "2079", "2080", "2081", "2082", "2083", "2084", "2085", "2086", "2087", "2088", "2089", "2090", "2091", "2092", "2093", "2094", "2095", "2096", "2097", "2098", "2099", "2100", " " }));
        DOS_year_combobox4.setBorder(null);
        panelA.add(DOS_year_combobox4, new org.netbeans.lib.awtextra.AbsoluteConstraints(740, 1300, 80, 40));

        DOS_date_combobox4.setFont(new java.awt.Font("Roboto", 0, 18)); // NOI18N
        DOS_date_combobox4.setForeground(new java.awt.Color(44, 62, 80));
        DOS_date_combobox4.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "DD", "01", "02", "03", "04", "05", "06", "07", "08", "09", "10", "11", "12", "13", "14", "15", "16", "17", "18", "19", "20", "21", "22", "23", "24", "25", "26", "27", "28", "29", "30", "31" }));
        DOS_date_combobox4.setBorder(null);
        panelA.add(DOS_date_combobox4, new org.netbeans.lib.awtextra.AbsoluteConstraints(560, 1300, 80, 40));

        DOS_year_combobox5.setFont(new java.awt.Font("Roboto", 0, 18)); // NOI18N
        DOS_year_combobox5.setForeground(new java.awt.Color(44, 62, 80));
        DOS_year_combobox5.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "YYYY", "1950", "1951", "1952", "1953", "1954", "1955", "1956", "1957", "1958", "1959", "1960", "1961", "1962", "1963", "1964", "1965", "1966", "1967", "1968", "1969", "1970", "1971", "1972", "1973", "1974", "1975", "1976", "1977", "1978", "1979", "1980", "1981", "1982", "1983", "1984", "1985", "1986", "1987", "1988", "1989", "1990", "1991", "1992", "1993", "1994", "1995", "1996", "1997", "1998", "1999", "2000", "2001", "2002", "2003", "2004", "2005", "2006", "2007", "2008", "2009", "2010", "2011", "2012", "2013", "2014", "2015", "2016", "2017", "2018", "2019", "2020", "2021", "2022", "2023", "2024", "2025", "2026", "2027", "2028", "2029", "2030", "2031", "2032", "2033", "2034", "2035", "2036", "2037", "2038", "2039", "2040", "2041", "2042", "2043", "2044", "2045", "2046", "2047", "2048", "2049", "2050", "2051", "2052", "2053", "2054", "2055", "2056", "2057", "2058", "2059", "2060", "2061", "2062", "2063", "2064", "2065", "2066", "2067", "2068", "2069", "2070", "2071", "2072", "2073", "2074", "2075", "2076", "2077", "2078", "2079", "2080", "2081", "2082", "2083", "2084", "2085", "2086", "2087", "2088", "2089", "2090", "2091", "2092", "2093", "2094", "2095", "2096", "2097", "2098", "2099", "2100", " " }));
        DOS_year_combobox5.setBorder(null);
        panelA.add(DOS_year_combobox5, new org.netbeans.lib.awtextra.AbsoluteConstraints(740, 1350, 80, 40));

        DOS_date_combobox5.setFont(new java.awt.Font("Roboto", 0, 18)); // NOI18N
        DOS_date_combobox5.setForeground(new java.awt.Color(44, 62, 80));
        DOS_date_combobox5.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "DD", "01", "02", "03", "04", "05", "06", "07", "08", "09", "10", "11", "12", "13", "14", "15", "16", "17", "18", "19", "20", "21", "22", "23", "24", "25", "26", "27", "28", "29", "30", "31" }));
        DOS_date_combobox5.setBorder(null);
        panelA.add(DOS_date_combobox5, new org.netbeans.lib.awtextra.AbsoluteConstraints(560, 1350, 80, 40));

        DOS_month_combobox5.setFont(new java.awt.Font("Roboto", 0, 18)); // NOI18N
        DOS_month_combobox5.setForeground(new java.awt.Color(44, 62, 80));
        DOS_month_combobox5.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "MM", "01", "02", "03", "04", "05", "06", "07", "08", "09", "10", "11", "12" }));
        DOS_month_combobox5.setBorder(null);
        panelA.add(DOS_month_combobox5, new org.netbeans.lib.awtextra.AbsoluteConstraints(650, 1350, 80, 40));

        screening_labelA3.setBackground(new java.awt.Color(255, 255, 255));
        screening_labelA3.setFont(new java.awt.Font("Montserrat", 0, 24)); // NOI18N
        screening_labelA3.setForeground(new java.awt.Color(66, 50, 77));
        screening_labelA3.setText("Screening");
        panelA.add(screening_labelA3, new org.netbeans.lib.awtextra.AbsoluteConstraints(320, 1490, -1, 40));

        screening_textfieldA3.setFont(new java.awt.Font("Roboto", 0, 24)); // NOI18N
        screening_textfieldA3.setForeground(new java.awt.Color(54, 33, 89));
        screening_textfieldA3.setBorder(null);
        screening_textfieldA3.setMargin(new java.awt.Insets(2, 4, 2, 2));
        screening_textfieldA3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                screening_textfieldA3ActionPerformed(evt);
            }
        });
        panelA.add(screening_textfieldA3, new org.netbeans.lib.awtextra.AbsoluteConstraints(320, 1530, 210, 40));

        screening_underlineA3.setForeground(new java.awt.Color(54, 33, 89));
        panelA.add(screening_underlineA3, new org.netbeans.lib.awtextra.AbsoluteConstraints(320, 1570, 210, 10));

        screening_undertextA3.setText("Bd SEP/LNK");
        panelA.add(screening_undertextA3, new org.netbeans.lib.awtextra.AbsoluteConstraints(320, 1580, -1, -1));

        MACP1_labelA1.setBackground(new java.awt.Color(255, 255, 255));
        MACP1_labelA1.setFont(new java.awt.Font("Montserrat", 0, 24)); // NOI18N
        MACP1_labelA1.setForeground(new java.awt.Color(66, 50, 77));
        MACP1_labelA1.setText("MACP I");
        panelA.add(MACP1_labelA1, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 1490, -1, 40));

        MACP1_textfieldA.setFont(new java.awt.Font("Roboto", 0, 24)); // NOI18N
        MACP1_textfieldA.setForeground(new java.awt.Color(54, 33, 89));
        MACP1_textfieldA.setBorder(null);
        MACP1_textfieldA.setMargin(new java.awt.Insets(2, 4, 2, 2));
        MACP1_textfieldA.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                MACP1_textfieldAActionPerformed(evt);
            }
        });
        panelA.add(MACP1_textfieldA, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 1530, 210, 40));

        MACP1_underlineA.setForeground(new java.awt.Color(54, 33, 89));
        panelA.add(MACP1_underlineA, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 1570, 210, 20));

        DD_labelA2.setBackground(new java.awt.Color(255, 255, 255));
        DD_labelA2.setFont(new java.awt.Font("Montserrat", 0, 24)); // NOI18N
        DD_labelA2.setForeground(new java.awt.Color(66, 50, 77));
        DD_labelA2.setText("Due date");
        panelA.add(DD_labelA2, new org.netbeans.lib.awtextra.AbsoluteConstraints(560, 790, -1, 40));

        DD_date_comboboxA2.setFont(new java.awt.Font("Roboto", 0, 18)); // NOI18N
        DD_date_comboboxA2.setForeground(new java.awt.Color(44, 62, 80));
        DD_date_comboboxA2.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "DD", "01", "02", "03", "04", "05", "06", "07", "08", "09", "10", "11", "12", "13", "14", "15", "16", "17", "18", "19", "20", "21", "22", "23", "24", "25", "26", "27", "28", "29", "30", "31" }));
        DD_date_comboboxA2.setBorder(null);
        panelA.add(DD_date_comboboxA2, new org.netbeans.lib.awtextra.AbsoluteConstraints(560, 830, 80, 40));

        DD_month_comboboxA2.setFont(new java.awt.Font("Roboto", 0, 18)); // NOI18N
        DD_month_comboboxA2.setForeground(new java.awt.Color(44, 62, 80));
        DD_month_comboboxA2.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "MM", "01", "02", "03", "04", "05", "06", "07", "08", "09", "10", "11", "12" }));
        DD_month_comboboxA2.setBorder(null);
        panelA.add(DD_month_comboboxA2, new org.netbeans.lib.awtextra.AbsoluteConstraints(650, 830, 80, 40));

        DD_year_comboboxA2.setFont(new java.awt.Font("Roboto", 0, 18)); // NOI18N
        DD_year_comboboxA2.setForeground(new java.awt.Color(44, 62, 80));
        DD_year_comboboxA2.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "YYYY", "1950", "1951", "1952", "1953", "1954", "1955", "1956", "1957", "1958", "1959", "1960", "1961", "1962", "1963", "1964", "1965", "1966", "1967", "1968", "1969", "1970", "1971", "1972", "1973", "1974", "1975", "1976", "1977", "1978", "1979", "1980", "1981", "1982", "1983", "1984", "1985", "1986", "1987", "1988", "1989", "1990", "1991", "1992", "1993", "1994", "1995", "1996", "1997", "1998", "1999", "2000", "2001", "2002", "2003", "2004", "2005", "2006", "2007", "2008", "2009", "2010", "2011", "2012", "2013", "2014", "2015", "2016", "2017", "2018", "2019", "2020", "2021", "2022", "2023", "2024", "2025", "2026", "2027", "2028", "2029", "2030", "2031", "2032", "2033", "2034", "2035", "2036", "2037", "2038", "2039", "2040", "2041", "2042", "2043", "2044", "2045", "2046", "2047", "2048", "2049", "2050", "2051", "2052", "2053", "2054", "2055", "2056", "2057", "2058", "2059", "2060", "2061", "2062", "2063", "2064", "2065", "2066", "2067", "2068", "2069", "2070", "2071", "2072", "2073", "2074", "2075", "2076", "2077", "2078", "2079", "2080", "2081", "2082", "2083", "2084", "2085", "2086", "2087", "2088", "2089", "2090", "2091", "2092", "2093", "2094", "2095", "2096", "2097", "2098", "2099", "2100", " " }));
        DD_year_comboboxA2.setBorder(null);
        panelA.add(DD_year_comboboxA2, new org.netbeans.lib.awtextra.AbsoluteConstraints(740, 830, 80, 40));

        DD_labelA4.setBackground(new java.awt.Color(255, 255, 255));
        DD_labelA4.setFont(new java.awt.Font("Montserrat", 0, 24)); // NOI18N
        DD_labelA4.setForeground(new java.awt.Color(66, 50, 77));
        DD_labelA4.setText("Due date");
        panelA.add(DD_labelA4, new org.netbeans.lib.awtextra.AbsoluteConstraints(560, 1490, -1, 40));

        DD_date_comboboxA3.setFont(new java.awt.Font("Roboto", 0, 18)); // NOI18N
        DD_date_comboboxA3.setForeground(new java.awt.Color(44, 62, 80));
        DD_date_comboboxA3.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "DD", "01", "02", "03", "04", "05", "06", "07", "08", "09", "10", "11", "12", "13", "14", "15", "16", "17", "18", "19", "20", "21", "22", "23", "24", "25", "26", "27", "28", "29", "30", "31" }));
        DD_date_comboboxA3.setBorder(null);
        panelA.add(DD_date_comboboxA3, new org.netbeans.lib.awtextra.AbsoluteConstraints(560, 1530, 80, 40));

        DD_month_comboboxA3.setFont(new java.awt.Font("Roboto", 0, 18)); // NOI18N
        DD_month_comboboxA3.setForeground(new java.awt.Color(44, 62, 80));
        DD_month_comboboxA3.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "MM", "01", "02", "03", "04", "05", "06", "07", "08", "09", "10", "11", "12" }));
        DD_month_comboboxA3.setBorder(null);
        panelA.add(DD_month_comboboxA3, new org.netbeans.lib.awtextra.AbsoluteConstraints(650, 1530, 80, 40));

        DD_year_comboboxA3.setFont(new java.awt.Font("Roboto", 0, 18)); // NOI18N
        DD_year_comboboxA3.setForeground(new java.awt.Color(44, 62, 80));
        DD_year_comboboxA3.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "YYYY", "1950", "1951", "1952", "1953", "1954", "1955", "1956", "1957", "1958", "1959", "1960", "1961", "1962", "1963", "1964", "1965", "1966", "1967", "1968", "1969", "1970", "1971", "1972", "1973", "1974", "1975", "1976", "1977", "1978", "1979", "1980", "1981", "1982", "1983", "1984", "1985", "1986", "1987", "1988", "1989", "1990", "1991", "1992", "1993", "1994", "1995", "1996", "1997", "1998", "1999", "2000", "2001", "2002", "2003", "2004", "2005", "2006", "2007", "2008", "2009", "2010", "2011", "2012", "2013", "2014", "2015", "2016", "2017", "2018", "2019", "2020", "2021", "2022", "2023", "2024", "2025", "2026", "2027", "2028", "2029", "2030", "2031", "2032", "2033", "2034", "2035", "2036", "2037", "2038", "2039", "2040", "2041", "2042", "2043", "2044", "2045", "2046", "2047", "2048", "2049", "2050", "2051", "2052", "2053", "2054", "2055", "2056", "2057", "2058", "2059", "2060", "2061", "2062", "2063", "2064", "2065", "2066", "2067", "2068", "2069", "2070", "2071", "2072", "2073", "2074", "2075", "2076", "2077", "2078", "2079", "2080", "2081", "2082", "2083", "2084", "2085", "2086", "2087", "2088", "2089", "2090", "2091", "2092", "2093", "2094", "2095", "2096", "2097", "2098", "2099", "2100", " " }));
        DD_year_comboboxA3.setBorder(null);
        panelA.add(DD_year_comboboxA3, new org.netbeans.lib.awtextra.AbsoluteConstraints(740, 1530, 80, 40));

        screening_labelA4.setBackground(new java.awt.Color(255, 255, 255));
        screening_labelA4.setFont(new java.awt.Font("Montserrat", 0, 24)); // NOI18N
        screening_labelA4.setForeground(new java.awt.Color(66, 50, 77));
        screening_labelA4.setText("Screening");
        panelA.add(screening_labelA4, new org.netbeans.lib.awtextra.AbsoluteConstraints(320, 1610, -1, 40));

        screening_textfieldA4.setFont(new java.awt.Font("Roboto", 0, 24)); // NOI18N
        screening_textfieldA4.setForeground(new java.awt.Color(54, 33, 89));
        screening_textfieldA4.setBorder(null);
        screening_textfieldA4.setMargin(new java.awt.Insets(2, 4, 2, 2));
        screening_textfieldA4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                screening_textfieldA4ActionPerformed(evt);
            }
        });
        panelA.add(screening_textfieldA4, new org.netbeans.lib.awtextra.AbsoluteConstraints(320, 1650, 210, 40));

        screening_underlineA4.setForeground(new java.awt.Color(54, 33, 89));
        panelA.add(screening_underlineA4, new org.netbeans.lib.awtextra.AbsoluteConstraints(320, 1690, 210, 10));

        screening_undertextA4.setText("Bd NK");
        panelA.add(screening_undertextA4, new org.netbeans.lib.awtextra.AbsoluteConstraints(320, 1700, -1, -1));

        MACP2_labelA.setBackground(new java.awt.Color(255, 255, 255));
        MACP2_labelA.setFont(new java.awt.Font("Montserrat", 0, 24)); // NOI18N
        MACP2_labelA.setForeground(new java.awt.Color(66, 50, 77));
        MACP2_labelA.setText("MACP II");
        panelA.add(MACP2_labelA, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 1610, -1, 40));

        MACP2_textfieldA.setFont(new java.awt.Font("Roboto", 0, 24)); // NOI18N
        MACP2_textfieldA.setForeground(new java.awt.Color(54, 33, 89));
        MACP2_textfieldA.setBorder(null);
        MACP2_textfieldA.setMargin(new java.awt.Insets(2, 4, 2, 2));
        MACP2_textfieldA.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                MACP2_textfieldAActionPerformed(evt);
            }
        });
        panelA.add(MACP2_textfieldA, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 1650, 210, 40));

        MACP2_underlineA.setForeground(new java.awt.Color(54, 33, 89));
        panelA.add(MACP2_underlineA, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 1690, 210, 20));

        DD_labelA5.setBackground(new java.awt.Color(255, 255, 255));
        DD_labelA5.setFont(new java.awt.Font("Montserrat", 0, 24)); // NOI18N
        DD_labelA5.setForeground(new java.awt.Color(66, 50, 77));
        DD_labelA5.setText("Due date");
        panelA.add(DD_labelA5, new org.netbeans.lib.awtextra.AbsoluteConstraints(560, 1610, -1, 40));

        DD_date_comboboxA4.setFont(new java.awt.Font("Roboto", 0, 18)); // NOI18N
        DD_date_comboboxA4.setForeground(new java.awt.Color(44, 62, 80));
        DD_date_comboboxA4.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "DD", "01", "02", "03", "04", "05", "06", "07", "08", "09", "10", "11", "12", "13", "14", "15", "16", "17", "18", "19", "20", "21", "22", "23", "24", "25", "26", "27", "28", "29", "30", "31" }));
        DD_date_comboboxA4.setBorder(null);
        panelA.add(DD_date_comboboxA4, new org.netbeans.lib.awtextra.AbsoluteConstraints(560, 1650, 80, 40));

        DD_month_comboboxA4.setFont(new java.awt.Font("Roboto", 0, 18)); // NOI18N
        DD_month_comboboxA4.setForeground(new java.awt.Color(44, 62, 80));
        DD_month_comboboxA4.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "MM", "01", "02", "03", "04", "05", "06", "07", "08", "09", "10", "11", "12" }));
        DD_month_comboboxA4.setBorder(null);
        panelA.add(DD_month_comboboxA4, new org.netbeans.lib.awtextra.AbsoluteConstraints(650, 1650, 80, 40));

        DD_year_comboboxA4.setFont(new java.awt.Font("Roboto", 0, 18)); // NOI18N
        DD_year_comboboxA4.setForeground(new java.awt.Color(44, 62, 80));
        DD_year_comboboxA4.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "YYYY", "1950", "1951", "1952", "1953", "1954", "1955", "1956", "1957", "1958", "1959", "1960", "1961", "1962", "1963", "1964", "1965", "1966", "1967", "1968", "1969", "1970", "1971", "1972", "1973", "1974", "1975", "1976", "1977", "1978", "1979", "1980", "1981", "1982", "1983", "1984", "1985", "1986", "1987", "1988", "1989", "1990", "1991", "1992", "1993", "1994", "1995", "1996", "1997", "1998", "1999", "2000", "2001", "2002", "2003", "2004", "2005", "2006", "2007", "2008", "2009", "2010", "2011", "2012", "2013", "2014", "2015", "2016", "2017", "2018", "2019", "2020", "2021", "2022", "2023", "2024", "2025", "2026", "2027", "2028", "2029", "2030", "2031", "2032", "2033", "2034", "2035", "2036", "2037", "2038", "2039", "2040", "2041", "2042", "2043", "2044", "2045", "2046", "2047", "2048", "2049", "2050", "2051", "2052", "2053", "2054", "2055", "2056", "2057", "2058", "2059", "2060", "2061", "2062", "2063", "2064", "2065", "2066", "2067", "2068", "2069", "2070", "2071", "2072", "2073", "2074", "2075", "2076", "2077", "2078", "2079", "2080", "2081", "2082", "2083", "2084", "2085", "2086", "2087", "2088", "2089", "2090", "2091", "2092", "2093", "2094", "2095", "2096", "2097", "2098", "2099", "2100", " " }));
        DD_year_comboboxA4.setBorder(null);
        panelA.add(DD_year_comboboxA4, new org.netbeans.lib.awtextra.AbsoluteConstraints(740, 1650, 80, 40));

        screening_labelA5.setBackground(new java.awt.Color(255, 255, 255));
        screening_labelA5.setFont(new java.awt.Font("Montserrat", 0, 24)); // NOI18N
        screening_labelA5.setForeground(new java.awt.Color(66, 50, 77));
        screening_labelA5.setText("Screening");
        panelA.add(screening_labelA5, new org.netbeans.lib.awtextra.AbsoluteConstraints(320, 1730, -1, 40));

        screening_textfieldA5.setFont(new java.awt.Font("Roboto", 0, 24)); // NOI18N
        screening_textfieldA5.setForeground(new java.awt.Color(54, 33, 89));
        screening_textfieldA5.setBorder(null);
        screening_textfieldA5.setMargin(new java.awt.Insets(2, 4, 2, 2));
        screening_textfieldA5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                screening_textfieldA5ActionPerformed(evt);
            }
        });
        panelA.add(screening_textfieldA5, new org.netbeans.lib.awtextra.AbsoluteConstraints(320, 1770, 210, 40));

        screening_underlineA5.setForeground(new java.awt.Color(54, 33, 89));
        panelA.add(screening_underlineA5, new org.netbeans.lib.awtextra.AbsoluteConstraints(320, 1810, 210, 10));

        screening_undertextA5.setText("Bd Hav");
        panelA.add(screening_undertextA5, new org.netbeans.lib.awtextra.AbsoluteConstraints(320, 1820, -1, -1));

        MACP3_labelA.setBackground(new java.awt.Color(255, 255, 255));
        MACP3_labelA.setFont(new java.awt.Font("Montserrat", 0, 24)); // NOI18N
        MACP3_labelA.setForeground(new java.awt.Color(66, 50, 77));
        MACP3_labelA.setText("MACP III");
        panelA.add(MACP3_labelA, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 1730, -1, 40));

        MACP3_textfieldA.setFont(new java.awt.Font("Roboto", 0, 24)); // NOI18N
        MACP3_textfieldA.setForeground(new java.awt.Color(54, 33, 89));
        MACP3_textfieldA.setBorder(null);
        MACP3_textfieldA.setMargin(new java.awt.Insets(2, 4, 2, 2));
        MACP3_textfieldA.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                MACP3_textfieldAActionPerformed(evt);
            }
        });
        panelA.add(MACP3_textfieldA, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 1770, 210, 40));

        MACP3_underlineA.setForeground(new java.awt.Color(54, 33, 89));
        panelA.add(MACP3_underlineA, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 1810, 210, 20));

        DD_labelA6.setBackground(new java.awt.Color(255, 255, 255));
        DD_labelA6.setFont(new java.awt.Font("Montserrat", 0, 24)); // NOI18N
        DD_labelA6.setForeground(new java.awt.Color(66, 50, 77));
        DD_labelA6.setText("Due date");
        panelA.add(DD_labelA6, new org.netbeans.lib.awtextra.AbsoluteConstraints(560, 1730, -1, 40));

        DD_date_comboboxA5.setFont(new java.awt.Font("Roboto", 0, 18)); // NOI18N
        DD_date_comboboxA5.setForeground(new java.awt.Color(44, 62, 80));
        DD_date_comboboxA5.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "DD", "01", "02", "03", "04", "05", "06", "07", "08", "09", "10", "11", "12", "13", "14", "15", "16", "17", "18", "19", "20", "21", "22", "23", "24", "25", "26", "27", "28", "29", "30", "31" }));
        DD_date_comboboxA5.setBorder(null);
        panelA.add(DD_date_comboboxA5, new org.netbeans.lib.awtextra.AbsoluteConstraints(560, 1770, 80, 40));

        DD_month_comboboxA5.setFont(new java.awt.Font("Roboto", 0, 18)); // NOI18N
        DD_month_comboboxA5.setForeground(new java.awt.Color(44, 62, 80));
        DD_month_comboboxA5.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "MM", "01", "02", "03", "04", "05", "06", "07", "08", "09", "10", "11", "12" }));
        DD_month_comboboxA5.setBorder(null);
        panelA.add(DD_month_comboboxA5, new org.netbeans.lib.awtextra.AbsoluteConstraints(650, 1770, 80, 40));

        DD_year_comboboxA5.setFont(new java.awt.Font("Roboto", 0, 18)); // NOI18N
        DD_year_comboboxA5.setForeground(new java.awt.Color(44, 62, 80));
        DD_year_comboboxA5.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "YYYY", "1950", "1951", "1952", "1953", "1954", "1955", "1956", "1957", "1958", "1959", "1960", "1961", "1962", "1963", "1964", "1965", "1966", "1967", "1968", "1969", "1970", "1971", "1972", "1973", "1974", "1975", "1976", "1977", "1978", "1979", "1980", "1981", "1982", "1983", "1984", "1985", "1986", "1987", "1988", "1989", "1990", "1991", "1992", "1993", "1994", "1995", "1996", "1997", "1998", "1999", "2000", "2001", "2002", "2003", "2004", "2005", "2006", "2007", "2008", "2009", "2010", "2011", "2012", "2013", "2014", "2015", "2016", "2017", "2018", "2019", "2020", "2021", "2022", "2023", "2024", "2025", "2026", "2027", "2028", "2029", "2030", "2031", "2032", "2033", "2034", "2035", "2036", "2037", "2038", "2039", "2040", "2041", "2042", "2043", "2044", "2045", "2046", "2047", "2048", "2049", "2050", "2051", "2052", "2053", "2054", "2055", "2056", "2057", "2058", "2059", "2060", "2061", "2062", "2063", "2064", "2065", "2066", "2067", "2068", "2069", "2070", "2071", "2072", "2073", "2074", "2075", "2076", "2077", "2078", "2079", "2080", "2081", "2082", "2083", "2084", "2085", "2086", "2087", "2088", "2089", "2090", "2091", "2092", "2093", "2094", "2095", "2096", "2097", "2098", "2099", "2100", " " }));
        DD_year_comboboxA5.setBorder(null);
        panelA.add(DD_year_comboboxA5, new org.netbeans.lib.awtextra.AbsoluteConstraints(740, 1770, 80, 40));

        DOC_labelA.setBackground(new java.awt.Color(255, 255, 255));
        DOC_labelA.setFont(new java.awt.Font("Montserrat", 0, 28)); // NOI18N
        DOC_labelA.setForeground(new java.awt.Color(51, 51, 51));
        DOC_labelA.setText("Date of Completion");
        panelA.add(DOC_labelA, new org.netbeans.lib.awtextra.AbsoluteConstraints(490, 370, -1, 40));

        DOC_date_comboboxA.setFont(new java.awt.Font("Roboto", 0, 18)); // NOI18N
        DOC_date_comboboxA.setForeground(new java.awt.Color(44, 62, 80));
        DOC_date_comboboxA.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "DD", "01", "02", "03", "04", "05", "06", "07", "08", "09", "10", "11", "12", "13", "14", "15", "16", "17", "18", "19", "20", "21", "22", "23", "24", "25", "26", "27", "28", "29", "30", "31" }));
        DOC_date_comboboxA.setBorder(null);
        panelA.add(DOC_date_comboboxA, new org.netbeans.lib.awtextra.AbsoluteConstraints(490, 410, 100, 40));

        DOC_month_comboboxA.setFont(new java.awt.Font("Roboto", 0, 18)); // NOI18N
        DOC_month_comboboxA.setForeground(new java.awt.Color(44, 62, 80));
        DOC_month_comboboxA.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "MM", "01", "02", "03", "04", "05", "06", "07", "08", "09", "10", "11", "12" }));
        DOC_month_comboboxA.setBorder(null);
        panelA.add(DOC_month_comboboxA, new org.netbeans.lib.awtextra.AbsoluteConstraints(600, 410, 100, 40));

        DOC_year_comboboxA.setFont(new java.awt.Font("Roboto", 0, 18)); // NOI18N
        DOC_year_comboboxA.setForeground(new java.awt.Color(44, 62, 80));
        DOC_year_comboboxA.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "YYYY", "1950", "1951", "1952", "1953", "1954", "1955", "1956", "1957", "1958", "1959", "1960", "1961", "1962", "1963", "1964", "1965", "1966", "1967", "1968", "1969", "1970", "1971", "1972", "1973", "1974", "1975", "1976", "1977", "1978", "1979", "1980", "1981", "1982", "1983", "1984", "1985", "1986", "1987", "1988", "1989", "1990", "1991", "1992", "1993", "1994", "1995", "1996", "1997", "1998", "1999", "2000", "2001", "2002", "2003", "2004", "2005", "2006", "2007", "2008", "2009", "2010", "2011", "2012", "2013", "2014", "2015", "2016", "2017", "2018", "2019", "2020", "2021", "2022", "2023", "2024", "2025", "2026", "2027", "2028", "2029", "2030", "2031", "2032", "2033", "2034", "2035", "2036", "2037", "2038", "2039", "2040", "2041", "2042", "2043", "2044", "2045", "2046", "2047", "2048", "2049", "2050", "2051", "2052", "2053", "2054", "2055", "2056", "2057", "2058", "2059", "2060", "2061", "2062", "2063", "2064", "2065", "2066", "2067", "2068", "2069", "2070", "2071", "2072", "2073", "2074", "2075", "2076", "2077", "2078", "2079", "2080", "2081", "2082", "2083", "2084", "2085", "2086", "2087", "2088", "2089", "2090", "2091", "2092", "2093", "2094", "2095", "2096", "2097", "2098", "2099", "2100", " " }));
        DOC_year_comboboxA.setBorder(null);
        panelA.add(DOC_year_comboboxA, new org.netbeans.lib.awtextra.AbsoluteConstraints(710, 410, 100, 40));

        ScrollPaneA.setViewportView(panelA);

        bg.add(ScrollPaneA, new org.netbeans.lib.awtextra.AbsoluteConstraints(340, 192, 910, 480));

        panelB.setBackground(new java.awt.Color(255, 255, 255));
        panelB.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        ScrollPaneB.setBackground(new java.awt.Color(255, 255, 255));
        ScrollPaneB.setVerticalScrollBarPolicy(javax.swing.ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);

        TableG_out.setFont(new java.awt.Font("Roboto", 0, 18)); // NOI18N
        TableG_out.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "S. No.", "Army No.", "DOE", "DOC", "TTT", "MR", "ACE", "MACP1", "MACP2"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        TableG_out.setAutoResizeMode(javax.swing.JTable.AUTO_RESIZE_ALL_COLUMNS);
        TableG_out.setFocusable(false);
        TableG_out.setGridColor(new java.awt.Color(255, 255, 255));
        TableG_out.setMaximumSize(null);
        TableG_out.setRowHeight(30);
        TableG_out.setSelectionBackground(new java.awt.Color(54, 33, 89));
        TableG_out.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        ScrollPaneB.setViewportView(TableG_out);
        if (TableG_out.getColumnModel().getColumnCount() > 0) {
            TableG_out.getColumnModel().getColumn(0).setMinWidth(40);
            TableG_out.getColumnModel().getColumn(0).setPreferredWidth(40);
            TableG_out.getColumnModel().getColumn(0).setMaxWidth(40);
            TableG_out.getColumnModel().getColumn(1).setMinWidth(100);
            TableG_out.getColumnModel().getColumn(1).setPreferredWidth(100);
            TableG_out.getColumnModel().getColumn(1).setMaxWidth(100);
            TableG_out.getColumnModel().getColumn(2).setMinWidth(100);
            TableG_out.getColumnModel().getColumn(2).setPreferredWidth(100);
            TableG_out.getColumnModel().getColumn(2).setMaxWidth(100);
            TableG_out.getColumnModel().getColumn(3).setMinWidth(100);
            TableG_out.getColumnModel().getColumn(3).setPreferredWidth(100);
            TableG_out.getColumnModel().getColumn(3).setMaxWidth(100);
        }

        panelB.add(ScrollPaneB, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 170, 780, 300));

        service_number_labelB.setBackground(new java.awt.Color(255, 255, 255));
        service_number_labelB.setFont(new java.awt.Font("Montserrat", 0, 28)); // NOI18N
        service_number_labelB.setForeground(new java.awt.Color(51, 51, 51));
        service_number_labelB.setText("Army Number");
        panelB.add(service_number_labelB, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 50, -1, 40));

        service_numberB.setFont(new java.awt.Font("Roboto", 0, 24)); // NOI18N
        service_numberB.setForeground(new java.awt.Color(54, 33, 89));
        service_numberB.setBorder(null);
        service_numberB.setMargin(new java.awt.Insets(2, 4, 2, 2));
        service_numberB.setSelectionColor(new java.awt.Color(204, 204, 255));
        service_numberB.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                service_numberBKeyPressed(evt);
            }
        });
        panelB.add(service_numberB, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 90, 400, 40));

        textfield_underline.setForeground(new java.awt.Color(54, 33, 89));
        panelB.add(textfield_underline, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 130, 400, 10));

        okayB.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/PNGS/check-mark.png"))); // NOI18N
        okayB.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                okayBMouseClicked(evt);
            }
        });
        panelB.add(okayB, new org.netbeans.lib.awtextra.AbsoluteConstraints(490, 90, -1, 40));

        bg.add(panelB, new org.netbeans.lib.awtextra.AbsoluteConstraints(340, 192, 910, 480));

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

        getContentPane().add(bg, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, -1, 730));

        fullscreen_panelB.setBackground(new java.awt.Color(255, 255, 255));
        fullscreen_panelB.setMaximumSize(new java.awt.Dimension(1280, 720));
        fullscreen_panelB.setMinimumSize(new java.awt.Dimension(1280, 720));
        fullscreen_panelB.setPreferredSize(new java.awt.Dimension(1280, 720));
        fullscreen_panelB.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        fullscreen_ScrollPaneB.setBackground(new java.awt.Color(255, 255, 255));
        fullscreen_ScrollPaneB.setHorizontalScrollBarPolicy(javax.swing.ScrollPaneConstants.HORIZONTAL_SCROLLBAR_ALWAYS);

        fullscreen_tableB.setFont(new java.awt.Font("Roboto", 0, 18)); // NOI18N
        fullscreen_tableB.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "S. No.", "Army No.", "DOE", "DOC", "TTT", "MR", "ACE", "SCREENING NB SUB", "SCREENING SUB", "DUE DATE NB SUB", "DUE DATE  SUB", "LNK DOP", "NK DOP", "NK DOS", "LHAV DOP", "HAV DOP", "HAV DOS", "MACP1", "MACP2", "MACP3"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, true, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        fullscreen_tableB.setAutoResizeMode(javax.swing.JTable.AUTO_RESIZE_OFF);
        fullscreen_tableB.setFocusable(false);
        fullscreen_tableB.setGridColor(new java.awt.Color(255, 255, 255));
        fullscreen_tableB.setMaximumSize(new java.awt.Dimension(40000, 40000));
        fullscreen_tableB.setMinimumSize(new java.awt.Dimension(1000, 1000));
        fullscreen_tableB.setRowHeight(30);
        fullscreen_tableB.setSelectionBackground(new java.awt.Color(54, 33, 89));
        fullscreen_ScrollPaneB.setViewportView(fullscreen_tableB);
        if (fullscreen_tableB.getColumnModel().getColumnCount() > 0) {
            fullscreen_tableB.getColumnModel().getColumn(0).setMinWidth(50);
            fullscreen_tableB.getColumnModel().getColumn(0).setPreferredWidth(50);
            fullscreen_tableB.getColumnModel().getColumn(0).setMaxWidth(50);
            fullscreen_tableB.getColumnModel().getColumn(1).setMinWidth(150);
            fullscreen_tableB.getColumnModel().getColumn(1).setPreferredWidth(150);
            fullscreen_tableB.getColumnModel().getColumn(1).setMaxWidth(150);
            fullscreen_tableB.getColumnModel().getColumn(2).setMinWidth(150);
            fullscreen_tableB.getColumnModel().getColumn(2).setPreferredWidth(150);
            fullscreen_tableB.getColumnModel().getColumn(2).setMaxWidth(150);
            fullscreen_tableB.getColumnModel().getColumn(3).setMinWidth(150);
            fullscreen_tableB.getColumnModel().getColumn(3).setPreferredWidth(150);
            fullscreen_tableB.getColumnModel().getColumn(3).setMaxWidth(150);
            fullscreen_tableB.getColumnModel().getColumn(4).setMinWidth(150);
            fullscreen_tableB.getColumnModel().getColumn(4).setPreferredWidth(150);
            fullscreen_tableB.getColumnModel().getColumn(4).setMaxWidth(150);
            fullscreen_tableB.getColumnModel().getColumn(5).setMinWidth(150);
            fullscreen_tableB.getColumnModel().getColumn(5).setPreferredWidth(150);
            fullscreen_tableB.getColumnModel().getColumn(5).setMaxWidth(150);
            fullscreen_tableB.getColumnModel().getColumn(6).setMinWidth(150);
            fullscreen_tableB.getColumnModel().getColumn(6).setPreferredWidth(150);
            fullscreen_tableB.getColumnModel().getColumn(6).setMaxWidth(150);
            fullscreen_tableB.getColumnModel().getColumn(7).setMinWidth(150);
            fullscreen_tableB.getColumnModel().getColumn(7).setPreferredWidth(150);
            fullscreen_tableB.getColumnModel().getColumn(7).setMaxWidth(150);
            fullscreen_tableB.getColumnModel().getColumn(8).setMinWidth(150);
            fullscreen_tableB.getColumnModel().getColumn(8).setPreferredWidth(150);
            fullscreen_tableB.getColumnModel().getColumn(8).setMaxWidth(150);
            fullscreen_tableB.getColumnModel().getColumn(9).setMinWidth(150);
            fullscreen_tableB.getColumnModel().getColumn(9).setPreferredWidth(150);
            fullscreen_tableB.getColumnModel().getColumn(9).setMaxWidth(150);
            fullscreen_tableB.getColumnModel().getColumn(10).setMinWidth(150);
            fullscreen_tableB.getColumnModel().getColumn(10).setPreferredWidth(150);
            fullscreen_tableB.getColumnModel().getColumn(10).setMaxWidth(150);
            fullscreen_tableB.getColumnModel().getColumn(11).setMinWidth(150);
            fullscreen_tableB.getColumnModel().getColumn(11).setPreferredWidth(150);
            fullscreen_tableB.getColumnModel().getColumn(11).setMaxWidth(150);
            fullscreen_tableB.getColumnModel().getColumn(12).setMinWidth(150);
            fullscreen_tableB.getColumnModel().getColumn(12).setPreferredWidth(150);
            fullscreen_tableB.getColumnModel().getColumn(12).setMaxWidth(150);
            fullscreen_tableB.getColumnModel().getColumn(13).setMinWidth(150);
            fullscreen_tableB.getColumnModel().getColumn(13).setPreferredWidth(150);
            fullscreen_tableB.getColumnModel().getColumn(13).setMaxWidth(150);
            fullscreen_tableB.getColumnModel().getColumn(14).setMinWidth(150);
            fullscreen_tableB.getColumnModel().getColumn(14).setPreferredWidth(150);
            fullscreen_tableB.getColumnModel().getColumn(14).setMaxWidth(150);
            fullscreen_tableB.getColumnModel().getColumn(15).setMinWidth(150);
            fullscreen_tableB.getColumnModel().getColumn(15).setPreferredWidth(150);
            fullscreen_tableB.getColumnModel().getColumn(15).setMaxWidth(150);
            fullscreen_tableB.getColumnModel().getColumn(16).setMinWidth(150);
            fullscreen_tableB.getColumnModel().getColumn(16).setPreferredWidth(150);
            fullscreen_tableB.getColumnModel().getColumn(16).setMaxWidth(150);
            fullscreen_tableB.getColumnModel().getColumn(17).setMinWidth(150);
            fullscreen_tableB.getColumnModel().getColumn(17).setPreferredWidth(150);
            fullscreen_tableB.getColumnModel().getColumn(17).setMaxWidth(150);
            fullscreen_tableB.getColumnModel().getColumn(18).setMinWidth(150);
            fullscreen_tableB.getColumnModel().getColumn(18).setPreferredWidth(150);
            fullscreen_tableB.getColumnModel().getColumn(18).setMaxWidth(150);
            fullscreen_tableB.getColumnModel().getColumn(19).setMinWidth(150);
            fullscreen_tableB.getColumnModel().getColumn(19).setPreferredWidth(150);
            fullscreen_tableB.getColumnModel().getColumn(19).setMaxWidth(150);
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

        insert_buttonA.setVisible(true);
        print_buttonB.setVisible(false);
        edit_buttonB.setVisible(false);
        refresh_buttonB.setVisible(false);
        fullscreen_buttonB.setVisible(false);

        ScrollPaneA.setVisible(true);
        panelB.setVisible(false);
        fullscreen_panelB.setVisible(false);
    }//GEN-LAST:event_formWindowOpened

    private void move_panelMouseDragged(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_move_panelMouseDragged
        this.setLocation(this.getX() +evt.getX() -mouseX, this.getY() + evt.getY() -mouseY);
    }//GEN-LAST:event_move_panelMouseDragged

    private void move_panelMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_move_panelMousePressed
        mouseX =evt.getX();
        mouseY =evt.getY();
    }//GEN-LAST:event_move_panelMousePressed

    private void exit_fullscreen_buttonBMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_exit_fullscreen_buttonBMouseClicked
        fullscreen_panelB.setVisible(false);
        bg.setVisible(true);
    }//GEN-LAST:event_exit_fullscreen_buttonBMouseClicked

    private void exit_fullscreen_buttonBMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_exit_fullscreen_buttonBMouseEntered
        exit_fullscreen_buttonB.setBackground(new java.awt.Color(237, 224, 255));
    }//GEN-LAST:event_exit_fullscreen_buttonBMouseEntered

    private void exit_fullscreen_buttonBMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_exit_fullscreen_buttonBMouseExited
        exit_fullscreen_buttonB.setBackground(new java.awt.Color(240,240,240));
    }//GEN-LAST:event_exit_fullscreen_buttonBMouseExited

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
        if(back_button){
            new Modules(0).setVisible(true);
            this.setVisible(false);
            back_button=false;
        }
    }//GEN-LAST:event_go_back_labelMouseClicked

    private void menuAMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_menuAMouseClicked
        menuA.setBackground(new java.awt.Color(85,65,118));
        menuB.setBackground(new java.awt.Color(64,43,100));

        insert_buttonA.setVisible(true);
        print_buttonB.setVisible(false);
        edit_buttonB.setVisible(false);
        refresh_buttonB.setVisible(false);
        fullscreen_buttonB.setVisible(false);

        ScrollPaneA.setVisible(true);
        panelB.setVisible(false);
        fullscreen_panelB.setVisible(false);
    }//GEN-LAST:event_menuAMouseClicked

    private void menuBMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_menuBMouseClicked
        menuB.setBackground(new java.awt.Color(85,65,118));
        menuA.setBackground(new java.awt.Color(64,43,100));
        view_table(null);
        insert_buttonA.setVisible(false);
        print_buttonB.setVisible(true);
        edit_buttonB.setVisible(true);
        refresh_buttonB.setVisible(true);
        fullscreen_buttonB.setVisible(true);

        ScrollPaneA.setVisible(false);
        panelB.setVisible(true);
        fullscreen_panelB.setVisible(false);
    }//GEN-LAST:event_menuBMouseClicked

    private void insert_buttonAMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_insert_buttonAMouseExited
        insert_buttonA.setBackground(new java.awt.Color(240,240,240));
    }//GEN-LAST:event_insert_buttonAMouseExited

    private void insert_buttonAMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_insert_buttonAMouseEntered

        insert_buttonA.setBackground(new java.awt.Color(237, 224, 255));
    }//GEN-LAST:event_insert_buttonAMouseEntered

    private void insert_buttonAMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_insert_buttonAMouseClicked
        // TODO add your handling code here:
        String sno = service_number_textfield.getText();
        disp_msg=true;
        disp_msg2=true;
        String query1 = "Select count(*) as count from attach_in where service_no = '" + sno + "'";
        String query2 = "Select count(*) as count from new_registration where service_no = '" + sno + "'";
        String query3 = "Select count(*) as count from post_in where service_no = '" + sno + "'";
        int count1 = 0;
        int count2 = 0;
        int count3 = 0;
        try{
            Statement st = con.createStatement();
            ResultSet rs = null;
            
            rs = st.executeQuery(query1);
            if (rs.next()) {
                count1 = rs.getInt("count");
            }
            rs = st.executeQuery(query2);
            if (rs.next()) {
                count2 = rs.getInt("count");
            }
            rs = st.executeQuery(query3);
            if (rs.next()) {
                count3 = rs.getInt("count");
            }
        }
        catch(Exception e){
        }
        
        
        if (sno == null || sno.trim().isEmpty()) {
            JOptionPane.showMessageDialog(null, "Pleae Enter Army Number");
        }
        else if(count1==0 && count2==0 &&count3==0  &&sno!=null &&!sno.trim().isEmpty()){
            JOptionPane.showMessageDialog(null, "Army Number does not exist");
        }
        else {
            try {
                Statement st = con.createStatement();
                ResultSet rs = null;
                PreparedStatement stmt = null;
                String query = "insert into seniority " + "values(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
                //date of enrollment
                String doe_year = (String) DOE_year_comboboxA.getSelectedItem();
                String doe_month = (String) DOE_month_comboboxA.getSelectedItem();
                String doe_date = (String) DOE_date_comboboxA.getSelectedItem();
                String date_of_enrollment = doe_date + "-" + doe_month + "-" + doe_year;
                
                //date of completion
                String doc_year = (String) DOC_year_comboboxA.getSelectedItem();
                String doc_month = (String) DOC_month_comboboxA.getSelectedItem();
                String doc_date = (String) DOC_date_comboboxA.getSelectedItem();
                String date_of_comp = doc_date + "-" + doc_month + "-" + doc_year;
                
                String ttt=TTT_textfieldA.getText();
                String mr=MR_textfieldA.getText();
                String ace=NK_textfield.getText();
                String scr_nb_sub=screening_textfieldA1.getText();
                String scr_sub=HAV_textfield1.getText();
                
                //screening nb sub date
                String scr_nb_year = (String) DD_year_comboboxA1.getSelectedItem();
                String scr_nb_month = (String) DD_month_comboboxA1.getSelectedItem();
                String scr_nb_date = (String) DD_date_comboboxA1.getSelectedItem();
                String date_scr_nb_sub = scr_nb_date + "-" + scr_nb_month + "-" + scr_nb_year;
                
                //screening bd sub date
                String scr_sub_year = (String) DD_year_comboboxA2.getSelectedItem();
                String scr_sub_month = (String) DD_month_comboboxA2.getSelectedItem();
                String scr_sub_date = (String) DD_date_comboboxA2.getSelectedItem();
                String date_scr_sub = scr_sub_date + "-" + scr_sub_month + "-" + scr_sub_year;
                
                //LNK DOP date
                String lnk_dop_year = (String) DOP_year_combobox1.getSelectedItem();
                String lnk_dop_month = (String) DOP_month_combobox1.getSelectedItem();
                String lnk_dop_date = (String) DOP_date_combobox1.getSelectedItem();
                String lnk_dop = lnk_dop_date + "-" + lnk_dop_month + "-" + lnk_dop_year;
                
                //NK DOP date
                String nk_dop_year = (String) DOP_year_combobox2.getSelectedItem();
                String nk_dop_month = (String) DOP_month_combobox2.getSelectedItem();
                String nk_dop_date = (String) DOP_date_combobox2.getSelectedItem();
                String nk_dop = nk_dop_date + "-" + nk_dop_month + "-" + nk_dop_year;
                
                //NK DOS date
                String nk_dos_year = (String) DOS_year_combobox1.getSelectedItem();
                String nk_dos_month = (String) DOS_month_combobox1.getSelectedItem();
                String nk_dos_date = (String) DOS_date_combobox1.getSelectedItem();
                String nk_dos = nk_dos_date + "-" + nk_dos_month + "-" + nk_dos_year;
                
                //LHAV DOP date
                String lhav_dop_year = (String) DOP_year_combobox3.getSelectedItem();
                String lhav_dop_month = (String) DOP_month_combobox3.getSelectedItem();
                String lhav_dop_date = (String) DOP_date_combobox3.getSelectedItem();
                String lhav_dop = lhav_dop_date + "-" + lhav_dop_month + "-" + lhav_dop_year;
                
                //HAV DOP date
                String hav_dop_year = (String) DOP_year_combobox4.getSelectedItem();
                String hav_dop_month = (String) DOP_month_combobox4.getSelectedItem();
                String hav_dop_date = (String) DOP_date_combobox4.getSelectedItem();
                String hav_dop = hav_dop_date + "-" + hav_dop_month + "-" + hav_dop_year;
                
                //HAV DOS date
                String hav_dos_year = (String) DOS_year_combobox2.getSelectedItem();
                String hav_dos_month = (String) DOS_month_combobox2.getSelectedItem();
                String hav_dos_date = (String) DOS_date_combobox2.getSelectedItem();
                String hav_dos = hav_dos_date + "-" + hav_dos_month + "-" + hav_dos_year;
                
                //NB SUB DOP date
                String nb_sub_dop_year = (String) DOP_year_combobox5.getSelectedItem();
                String nb_sub_dop_month = (String) DOP_month_combobox5.getSelectedItem();
                String nb_sub_dop_date = (String) DOP_date_combobox5.getSelectedItem();
                String nb_sub_dop = nb_sub_dop_date + "-" + nb_sub_dop_month + "-" + nb_sub_dop_year;
                
                //NB SUB DOS date
                String nb_sub_dos_year = (String) DOS_year_combobox3.getSelectedItem();
                String nb_sub_dos_month = (String) DOS_month_combobox3.getSelectedItem();
                String nb_sub_dos_date = (String) DOS_date_combobox3.getSelectedItem();
                String nb_sub_dos = nb_sub_dos_date + "-" + nb_sub_dos_month + "-" + nb_sub_dos_year;
                
                // SUB DOP date
                String sub_dop_year = (String) DOP_year_combobox6.getSelectedItem();
                String sub_dop_month = (String) DOP_month_combobox6.getSelectedItem();
                String sub_dop_date = (String) DOP_date_combobox6.getSelectedItem();
                String sub_dop = sub_dop_date + "-" + sub_dop_month + "-" + sub_dop_year;
                
                // SUB DOS date
                String sub_dos_year = (String) DOS_year_combobox4.getSelectedItem();
                String sub_dos_month = (String) DOS_month_combobox4.getSelectedItem();
                String sub_dos_date = (String) DOS_date_combobox4.getSelectedItem();
                String sub_dos = sub_dos_date + "-" + sub_dos_month + "-" + sub_dos_year;
                
                // SUB MAJ DOP date
                String sub_maj_dop_year = (String) DOP_year_combobox7.getSelectedItem();
                String sub_maj_dop_month = (String) DOP_month_combobox7.getSelectedItem();
                String sub_maj_dop_date = (String) DOP_date_combobox7.getSelectedItem();
                String sub_maj_dop = sub_maj_dop_date + "-" + sub_maj_dop_month + "-" + sub_maj_dop_year;
                
                // SUB MAJ DOS date
                String sub_maj_dos_year = (String) DOS_year_combobox5.getSelectedItem();
                String sub_maj_dos_month = (String) DOS_month_combobox5.getSelectedItem();
                String sub_maj_dos_date = (String) DOS_date_combobox5.getSelectedItem();
                String sub_maj_dos = sub_maj_dos_date + "-" + sub_dos_month + "-" + sub_maj_dos_year;
                
                String macp1=MACP1_textfieldA.getText();
                String macp2=MACP2_textfieldA.getText();
                String macp3=MACP3_textfieldA.getText();
                String scr_sep_lnk=screening_textfieldA3.getText();
                String bd_nk=screening_textfieldA4.getText();
                String bd_hav=screening_textfieldA5.getText();
                
                // DUE DATE SEP LNK
                String scr_sep_lnk_year = (String) DD_year_comboboxA3.getSelectedItem();
                String scr_sep_lnk_month = (String) DD_month_comboboxA3.getSelectedItem();
                String scr_sep_lnk_date = (String) DD_date_comboboxA3.getSelectedItem();
                String date_scr_sep_lnk = scr_sep_lnk_date + "-" + scr_sep_lnk_month + "-" + scr_sep_lnk_year;
                
                // DUE DATE BD NK
                String scr_bd_nk_year = (String) DD_year_comboboxA4.getSelectedItem();
                String scr_bd_nk_month = (String) DD_month_comboboxA4.getSelectedItem();
                String scr_bd_nk_date = (String) DD_date_comboboxA4.getSelectedItem();
                String date_scr_bd_nk = scr_bd_nk_date + "-" + scr_bd_nk_month + "-" + scr_bd_nk_year;
                
                // DUE DATE BD HAV
                String scr_bd_hav_year = (String) DD_year_comboboxA5.getSelectedItem();
                String scr_bd_hav_month = (String) DD_month_comboboxA5.getSelectedItem();
                String scr_bd_hav_date = (String) DD_date_comboboxA5.getSelectedItem();
                String date_scr_bd_hav = scr_bd_hav_date + "-" + scr_bd_hav_month + "-" + scr_bd_hav_year;
                
                String err="";
                boolean check_all_tf = (ttt != null && !ttt.trim().isEmpty()) && (mr != null && !mr.trim().isEmpty()) && (scr_nb_sub != null && !scr_nb_sub.trim().isEmpty()) && (ace != null && !ace.trim().isEmpty()) && (scr_sub != null && !scr_sub.trim().isEmpty()) && (macp1 != null && !macp1.trim().isEmpty())&& (macp2 != null && !macp2.trim().isEmpty())&& (macp3 != null && !macp3.trim().isEmpty())&& (scr_sep_lnk != null && !scr_sep_lnk.trim().isEmpty())&& (bd_nk != null && !bd_nk.trim().isEmpty())&& (bd_hav != null && !bd_hav.trim().isEmpty());
                boolean check_doe=check_d(date_of_enrollment);
                boolean check_doc=check_d(date_of_comp);
                boolean screen_nb_sub=check_d(date_scr_nb_sub);
                boolean screen_src_sub=check_d(date_scr_sub);
                boolean check_lnk_dop=check_d(lnk_dop);
                boolean check_nk_dop=check_d(nk_dop);
                boolean check_nk_dos=check_d(nk_dos);
                boolean check_lhav_dop=check_d(lhav_dop);
                boolean check_hav_dop=check_d(hav_dop);
                boolean check_hav_dos=check_d(hav_dos);
                boolean check_nb_sub_dop=check_d(nb_sub_dop);
                boolean check_nb_sub_dos=check_d(nb_sub_dos);
                boolean check_sub_dop=check_d(sub_dop);
                boolean check_sub_dos=check_d(sub_dos);
                boolean check_sub_maj_dop=check_d(sub_maj_dop);
                boolean check_sub_maj_dos=check_d(sub_maj_dos);
                boolean check_date_scr_sep_lnk=check_d(date_scr_sep_lnk);
                boolean check_date_scr_bd_nk=check_d(date_scr_bd_nk);
                boolean check_date_scr_bd_hav=check_d(date_scr_bd_hav);
                
                if(check_all_tf &&check_date_scr_bd_hav&&check_date_scr_bd_nk&& check_date_scr_sep_lnk&&check_doe && check_doc && screen_nb_sub && screen_src_sub && check_lnk_dop &&check_nk_dop &&check_nk_dos && check_lhav_dop && check_hav_dop && check_hav_dos && check_nb_sub_dop && check_nb_sub_dos && check_sub_dop && check_sub_dos && check_sub_maj_dop && check_sub_maj_dos){
                    System.out.print("\n Inserton if of seniority");
                    stmt = con.prepareStatement(query);
                    stmt.setString(1, sno);
                    stmt.setString(2, date_of_enrollment);
                    stmt.setString(3, date_of_comp);
                    stmt.setString(4, ttt);
                    stmt.setString(5, mr);
                    stmt.setString(6, ace);
                    stmt.setString(7, scr_nb_sub);
                    stmt.setString(8, scr_sub);
                    stmt.setString(9, date_scr_nb_sub);
                    stmt.setString(10, date_scr_sub);
                    stmt.setString(11, lnk_dop);
                    stmt.setString(12, nk_dop);
                    stmt.setString(13, nk_dos);
                    stmt.setString(14, lhav_dop);
                    stmt.setString(15, hav_dop);
                    stmt.setString(16, hav_dos);
                    stmt.setString(17, nb_sub_dop);
                    stmt.setString(18, nb_sub_dos);
                    stmt.setString(19, sub_dop);
                    stmt.setString(20, sub_dos);
                    stmt.setString(21, sub_maj_dop);
                    stmt.setString(22, sub_maj_dos);
                    stmt.setString(23, macp1);
                    stmt.setString(24, scr_sep_lnk);
                    stmt.setString(25, date_scr_sep_lnk);
                    stmt.setString(26, macp2);
                    stmt.setString(27, bd_nk);
                    stmt.setString(28, date_scr_bd_nk);
                    stmt.setString(29, macp3);
                    stmt.setString(30, bd_hav);
                    stmt.setString(31, date_scr_bd_hav);
                    stmt.execute();
                    JOptionPane.showMessageDialog(null, "Data inserted successfully");
                    service_number_textfield.setText("");
                    name.setText("> XXXXXXX");
                    rank.setText("> XXXXXXX");
                    company.setText("> XXXXXXX");
                    unit.setText("> XXXXXXX");
                    trade.setText("> XXXXXXX");
                    DOB.setText("> XXXXXXX");
                    TTT_textfieldA.setText("");
                    MR_textfieldA.setText("");
                    NK_textfield.setText("");
                    screening_textfieldA1.setText("");
                    HAV_textfield1.setText("");
                    MACP1_textfieldA.setText("");
                    MACP2_textfieldA.setText("");
                    MACP3_textfieldA.setText("");
                    screening_textfieldA3.setText("");
                    screening_textfieldA4.setText("");
                    screening_textfieldA5.setText("");
                    
                    DOE_year_comboboxA.setSelectedIndex(0);
                    DOE_month_comboboxA.setSelectedIndex(0);
                    DOE_date_comboboxA.setSelectedIndex(0);
                    
                    //DD_year_comboboxA1
                    DOC_year_comboboxA.setSelectedIndex(0);
                    DOC_month_comboboxA.setSelectedIndex(0);
                    DOC_date_comboboxA.setSelectedIndex(0);
                    
                    DD_year_comboboxA1.setSelectedIndex(0);
                    DD_month_comboboxA1.setSelectedIndex(0);
                    DD_date_comboboxA1.setSelectedIndex(0);
                    
                    DD_year_comboboxA2.setSelectedIndex(0);
                    DD_month_comboboxA2.setSelectedIndex(0);
                    DD_date_comboboxA2.setSelectedIndex(0);
                    
                    DD_year_comboboxA3.setSelectedIndex(0);
                    DD_month_comboboxA3.setSelectedIndex(0);
                    DD_date_comboboxA3.setSelectedIndex(0);
                    
                    DD_year_comboboxA4.setSelectedIndex(0);
                    DD_month_comboboxA4.setSelectedIndex(0);
                    DD_date_comboboxA4.setSelectedIndex(0);
                    
                    DD_year_comboboxA5.setSelectedIndex(0);
                    DD_month_comboboxA5.setSelectedIndex(0);
                    DD_date_comboboxA5.setSelectedIndex(0);
                    
                    //DOP_year_combobox7
                    DOP_year_combobox7.setSelectedIndex(0);
                    DOP_month_combobox7.setSelectedIndex(0);
                    DOP_date_combobox7.setSelectedIndex(0);
                    
                    DOP_year_combobox6.setSelectedIndex(0);
                    DOP_month_combobox6.setSelectedIndex(0);
                    DOP_date_combobox6.setSelectedIndex(0);
                    
                    DOP_year_combobox5.setSelectedIndex(0);
                    DOP_month_combobox5.setSelectedIndex(0);
                    DOP_date_combobox5.setSelectedIndex(0);
                    
                    DOP_year_combobox4.setSelectedIndex(0);
                    DOP_month_combobox4.setSelectedIndex(0);
                    DOP_date_combobox4.setSelectedIndex(0);
                    
                    DOP_year_combobox3.setSelectedIndex(0);
                    DOP_month_combobox3.setSelectedIndex(0);
                    DOP_date_combobox3.setSelectedIndex(0);
                    
                    DOP_year_combobox2.setSelectedIndex(0);
                    DOP_month_combobox2.setSelectedIndex(0);
                    DOP_date_combobox2.setSelectedIndex(0);
                    
                    DOP_year_combobox1.setSelectedIndex(0);
                    DOP_month_combobox1.setSelectedIndex(0);
                    DOP_date_combobox1.setSelectedIndex(0);
                    
                    //DOS_year_combobox5
                    DOS_year_combobox5.setSelectedIndex(0);
                    DOS_month_combobox5.setSelectedIndex(0);
                    DOS_date_combobox5.setSelectedIndex(0);
                    
                    DOS_year_combobox4.setSelectedIndex(0);
                    DOS_month_combobox4.setSelectedIndex(0);
                    DOS_date_combobox4.setSelectedIndex(0);
                    
                    DOS_year_combobox3.setSelectedIndex(0);
                    DOS_month_combobox3.setSelectedIndex(0);
                    DOS_date_combobox3.setSelectedIndex(0);
                    
                    DOS_year_combobox2.setSelectedIndex(0);
                    DOS_month_combobox2.setSelectedIndex(0);
                    DOS_date_combobox2.setSelectedIndex(0);
                    
                    DOS_year_combobox1.setSelectedIndex(0);
                    DOS_month_combobox1.setSelectedIndex(0);
                    DOS_date_combobox1.setSelectedIndex(0);
                    
                }
                else{
                    if (ttt == null || ttt.trim().isEmpty()) {
                        err = err + "\nTTT Field Empty";
                    }
                    if (mr == null || mr.trim().isEmpty()) {
                        err = err + "\nMR Field Empty";
                    }

                    if (ace == null || ace.trim().isEmpty()) {
                        err = err + "\nACE Field Empty";
                    }
                    if (scr_nb_sub == null || scr_nb_sub.trim().isEmpty()) {
                        err = err + "\nScreening BD NB SUB Field Empty";
                    }
                    if (scr_sub == null || scr_sub.trim().isEmpty()) {
                        err = err + "\nScreening BD SUB Field Empty";
                    }
                    if (macp1 == null || macp1.trim().isEmpty()) {
                        err = err + "\nMacp1 Field Empty";
                    }
                    if (macp2 == null || macp2.trim().isEmpty()) {
                        err = err + "\nMacp2 Field Empty";
                    }
                    if (macp3 == null || macp3.trim().isEmpty()) {
                        err = err + "\nMacp3 Field Empty";
                    }
                    if (scr_sep_lnk == null || scr_sep_lnk.trim().isEmpty()) {
                        err = err + "\nScreening SEP/LNK Field Empty";
                    }
                    if (bd_nk == null || bd_nk.trim().isEmpty()) {
                        err = err + "\nScreening BD NK Field Empty";
                    }
                    if (bd_hav == null || bd_hav.trim().isEmpty()) {
                        err = err + "\nScreening BD HAV Field Empty";
                    }
                    if(check_doe==false){
                        err = err + "\nIncorrect Date of Enrollment";
                    }
                    if(check_doc==false){
                        err = err + "\nIncorrect Date of Completion";
                    }
                    if(screen_nb_sub==false){
                        err = err + "\nIncorrect Due Date of SCREENING BD NB SUB";
                    }
                    if(screen_src_sub==false){
                        err = err + "\nIncorrect Due Date of SCREENING BD SUB";
                    }
                    if(check_lnk_dop==false){
                        err = err + "\nIncorrect LNK DOP";
                    }
                    if(check_nk_dop==false){
                        err = err + "\nIncorrect NK DOP";
                    }
                    if(check_nk_dos==false){
                        err = err + "\nIncorrect NK DOS";
                    }
                    if(check_lhav_dop==false){
                        err = err + "\nIncorrect LHAV DOP";
                    }
                    if(check_hav_dop==false){
                        err = err + "\nIncorrect HAV DOP";
                    }
                    if(check_hav_dos==false){
                        err = err + "\nIncorrect HAV DOS";
                    }
                    if(check_nb_sub_dop==false){
                        err = err + "\nIncorrect NB SUB DOP";
                    }
                    if(check_nb_sub_dos==false){
                        err = err + "\nIncorrect NB SUB DOS";
                    }
                    if(check_sub_dop==false){
                        err = err + "\nIncorrect SUB DOP";
                    }
                    if(check_sub_dos==false){
                        err = err + "\nIncorrect SUB DOS";
                    }
                    if(check_sub_maj_dop==false){
                        err = err + "\nIncorrect SUB MAJ DOP";
                    }
                    if(check_sub_maj_dos==false){
                        err = err + "\nIncorrect SUB MAJ DOS";
                    }
                    //check_date_scr_sep_lnk
                    if(check_date_scr_sep_lnk==false){
                        err = err + "\nIncorrect Due Date of SCREENING SEP/LNK";
                    }
                    if(check_date_scr_bd_nk==false){
                        err = err + "\nIncorrect Due Date of SCREENING BD NK";
                    }
                    if(check_date_scr_bd_hav==false){
                        err = err + "\nIncorrect Due Date of SCREENING BD HAV";
                    }
                    if (!err.trim().isEmpty()) {
                        JOptionPane.showMessageDialog(null, err);
                    }
                    
                }
                
                
                
                } 
             
            catch (Exception e) {
                System.out.print("\n" + e);
                String exe = e.getMessage();
                if (exe.contains("Duplicate entry")) {
                    JOptionPane.showMessageDialog(null, "Data not inserted!\nEntered ARMY NUMBER exists!");
                } else if (exe.contains("For input")) {
                    JOptionPane.showMessageDialog(null, "\nPlease Re-Check Date Formats");
                } else {
                    JOptionPane.showMessageDialog(null, "Data not inserted!");
                }
            }
        }
    }//GEN-LAST:event_insert_buttonAMouseClicked
    private boolean check_d(String date){
        int check=0;
        int y=0,m=0,d=0;
        try{
            
            String[] sp=date.split("-");
            if(!sp[2].contains("YYYY") && !sp[1].contains("MM") && !sp[0].contains("DD")){
                y = Integer.parseInt(sp[2]);
                m = Integer.parseInt(sp[1]);
                d = Integer.parseInt(sp[0]);
            }
            else if(sp[2].contains("YYYY") && sp[1].contains("MM") && sp[0].contains("DD")){
               y=100; 
            }
            else{
               if(disp_msg2){
                   JOptionPane.showMessageDialog(null, "\nPlease Re-Check Date Formats");
                    disp_msg2=false;
               } 
            }
            boolean check_all_dd1 = !((y % 4 == 0 && m == 2 && (d > 29)) || ((m == 2 || m == 4 || m == 6 || m == 9 || m == 11) && d == 31) || (m == 2 && d > 28 && y % 4 != 0));
            if(check_all_dd1 && y!=0){
                //return true;
                check=1;
            }    
            
        }
        catch(Exception e){
            String exe = e.getMessage();
            if ((exe.contains("For input") &&disp_msg)) {
                JOptionPane.showMessageDialog(null, "\nPlease Re-Check Date Formats");
                disp_msg=false;
            }        
        }
        if(check==1){
            return true;
        }
        else{
            return false;
        }
    }
    private void print_buttonBMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_print_buttonBMouseExited
        print_buttonB.setBackground(new java.awt.Color(240,240,240));
    }//GEN-LAST:event_print_buttonBMouseExited

    private void print_buttonBMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_print_buttonBMouseEntered
        print_buttonB.setBackground(new java.awt.Color(237, 224, 255));
    }//GEN-LAST:event_print_buttonBMouseEntered

    private void print_buttonBMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_print_buttonBMouseClicked
        try{

        Statement statement = con.createStatement();
            ResultSet resultSet = statement.executeQuery("select * from seniority");
            XSSFWorkbook workbook = new XSSFWorkbook();
            XSSFSheet spreadsheet =null;
            spreadsheet = workbook.createSheet("seniority");
            
            
            XSSFRow row = spreadsheet.createRow(0);
            XSSFCell cell;
            cell = row.createCell(0);
            cell.setCellValue("Army No");
            cell = row.createCell(1);
            cell.setCellValue("DOE");
            cell = row.createCell(2);
            cell.setCellValue("DOC");
            cell = row.createCell(3);
            cell.setCellValue("TTT");
            cell = row.createCell(4);
            cell.setCellValue("MR");
            cell = row.createCell(5);
            cell.setCellValue("ACE");
            cell = row.createCell(6);
            cell.setCellValue("Screening nb sub");
            cell = row.createCell(7);
            cell.setCellValue("Screening sub");
            cell = row.createCell(8);
            cell.setCellValue("Due date nb sub");
            cell = row.createCell(9);
            cell.setCellValue("Due date sub");
            cell = row.createCell(10);
            cell.setCellValue("LKN DOP");
            cell = row.createCell(11);
            cell.setCellValue("NK DOP");
            cell = row.createCell(12);
            cell.setCellValue("NK DOS");
            cell = row.createCell(13);
            cell.setCellValue("LHAV DOP");
            cell = row.createCell(14);
            cell.setCellValue("HAV DOP");
            cell = row.createCell(15);
            cell.setCellValue("HAV DOS");
            cell = row.createCell(16);
            cell.setCellValue("NB SUB DOP");
            cell = row.createCell(17);
            cell.setCellValue("NB SUB DOS");
            cell = row.createCell(18);
            cell.setCellValue("SUB DOP");
            cell = row.createCell(19);
            cell.setCellValue("SUB DOS");
            cell = row.createCell(20);//
            cell.setCellValue("SUB MAJ DOP");
            cell = row.createCell(21);
            cell.setCellValue("SUB MAJ DOS");
            cell = row.createCell(22);
            cell.setCellValue("MACP1");
            cell = row.createCell(23);
            cell.setCellValue("Screening sep/lnk");
            cell = row.createCell(24);
            cell.setCellValue("Due date sep/lnk");
            cell = row.createCell(25);
            cell.setCellValue("Macp2");
            cell = row.createCell(26);
            cell.setCellValue("Screening bd nk");
            cell = row.createCell(27);
            cell.setCellValue("Due date bd_nk");
            cell = row.createCell(28);
            cell.setCellValue("MACP3");
            cell = row.createCell(29);
            cell.setCellValue("Screening bd hav");
            cell = row.createCell(30);
            cell.setCellValue("Due date bd_hav");
            int i = 1;
            
            while(resultSet.next()) {
                row = spreadsheet.createRow(i);
                cell = row.createCell(0);
                cell.setCellValue(resultSet.getString("Service_no"));
                cell = row.createCell(1);
                cell.setCellValue(resultSet.getString("DOE"));
                cell = row.createCell(2);
                cell.setCellValue(resultSet.getString("DOC"));
                cell = row.createCell(3);
                cell.setCellValue(resultSet.getString("TTT"));
                cell = row.createCell(4);
                cell.setCellValue(resultSet.getString("MR"));
                cell = row.createCell(5);
                cell.setCellValue(resultSet.getString("ACE"));
                cell = row.createCell(6);
                cell.setCellValue(resultSet.getString("Screening_nb_sub"));
                cell = row.createCell(7);
                cell.setCellValue(resultSet.getString("Screening_sub"));
                cell = row.createCell(8);
                cell.setCellValue(resultSet.getString("due_date_nb_sub"));
                cell = row.createCell(9);
                cell.setCellValue(resultSet.getString("due_date_sub"));
                cell = row.createCell(10);
                cell.setCellValue(resultSet.getString("LNK_dop_date"));
                cell = row.createCell(11);
                cell.setCellValue(resultSet.getString("NK_dop_date"));
                cell = row.createCell(12);
                cell.setCellValue(resultSet.getString("NK_dos_date"));
                cell = row.createCell(13);
                cell.setCellValue(resultSet.getString("LHAV_dop_date"));
                cell = row.createCell(14);
                cell.setCellValue(resultSet.getString("HAV_dop_date"));
                cell = row.createCell(15);
                cell.setCellValue(resultSet.getString("HAV_dos_date"));
                cell = row.createCell(16);
                cell.setCellValue(resultSet.getString("NB_SUB_dop_date"));
                cell = row.createCell(17);
                cell.setCellValue(resultSet.getString("NB_SUB_dos_date"));
                cell = row.createCell(18);
                cell.setCellValue(resultSet.getString("SUB_dop_date"));
                cell = row.createCell(19);
                cell.setCellValue(resultSet.getString("SUB_dos_date"));
                cell = row.createCell(20);
                cell.setCellValue(resultSet.getString("SUB_MAJ_dop_date"));
                cell = row.createCell(21);
                cell.setCellValue(resultSet.getString("SUB_MAJ_dos_date"));
                cell = row.createCell(22);
                cell.setCellValue(resultSet.getString("MACP_1"));
                cell = row.createCell(23);
                cell.setCellValue(resultSet.getString("Screening_sep_lnk"));
                cell = row.createCell(24);
                cell.setCellValue(resultSet.getString("due_date_sep_lnk"));
                cell = row.createCell(25);
                cell.setCellValue(resultSet.getString("MACP_2"));
                cell = row.createCell(26);
                cell.setCellValue(resultSet.getString("Screening_bd_nk"));
                cell = row.createCell(27);
                cell.setCellValue(resultSet.getString("due_date_bd_nk"));
                cell = row.createCell(28);
                cell.setCellValue(resultSet.getString("MACP_3"));
                cell = row.createCell(29);
                cell.setCellValue(resultSet.getString("Screening_bd_hav"));
                cell = row.createCell(30);
                cell.setCellValue(resultSet.getString("due_date_bd_hav"));
                i++;
            }
            path_file paths = new path_file();
            FileOutputStream out = new FileOutputStream(new File(paths.csv +"\\seniority.xlsx"));
            workbook.write(out);
            //System.out.println("csd.xlsx written successfully");
            JOptionPane.showMessageDialog(null,"seniority.xlsx written successfully");
            Desktop.getDesktop().open(new File(paths.csv + "/seniority.xlsx"));
        }  
        catch (Exception  e) {
            System.out.println(e);
            JOptionPane.showMessageDialog(null,"Error !Could not converted to xlsx");
        }
    }//GEN-LAST:event_print_buttonBMouseClicked

    private void fullscreen_buttonBMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_fullscreen_buttonBMouseExited
        fullscreen_buttonB.setBackground(new java.awt.Color(240,240,240));
    }//GEN-LAST:event_fullscreen_buttonBMouseExited

    private void fullscreen_buttonBMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_fullscreen_buttonBMouseEntered
        fullscreen_buttonB.setBackground(new java.awt.Color(237, 224, 255));
    }//GEN-LAST:event_fullscreen_buttonBMouseEntered

    private void fullscreen_buttonBMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_fullscreen_buttonBMouseClicked
        fullscreen_panelB.setVisible(true);
        bg.setVisible(false);
    }//GEN-LAST:event_fullscreen_buttonBMouseClicked

    private void refresh_buttonBMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_refresh_buttonBMouseExited
        refresh_buttonB.setBackground(new java.awt.Color(240,240,240));
    }//GEN-LAST:event_refresh_buttonBMouseExited

    private void refresh_buttonBMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_refresh_buttonBMouseEntered
        refresh_buttonB.setBackground(new java.awt.Color(237, 224, 255));
    }//GEN-LAST:event_refresh_buttonBMouseEntered

    private void refresh_buttonBMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_refresh_buttonBMouseClicked
        view_table(null);
    }//GEN-LAST:event_refresh_buttonBMouseClicked

    private void edit_buttonBMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_edit_buttonBMouseExited
        edit_buttonB.setBackground(new java.awt.Color(240,240,240));
    }//GEN-LAST:event_edit_buttonBMouseExited

    private void edit_buttonBMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_edit_buttonBMouseEntered
        edit_buttonB.setBackground(new java.awt.Color(237, 224, 255));
    }//GEN-LAST:event_edit_buttonBMouseEntered

    private void edit_buttonBMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_edit_buttonBMouseClicked
        try{

            String sno = service_numberB.getText();
            Statement st = con.createStatement();
            ResultSet rs = null;
            int row_ = TableG_out.getSelectedRow();
            if ((sno == null || sno.trim().isEmpty()) && row_ == -1) {
                JOptionPane.showMessageDialog(null, "Please enter Army Number");
            }
            if (row_ == -1 && sno != null && !sno.trim().isEmpty()) {
                JOptionPane.showMessageDialog(null, "Please select a row");
            } else if (row_ != -1) {
                sno = small.getValueAt(row_, 1).toString();
                new update_seniority(sno).setVisible(true);
                this.setVisible(false);
            }
        }
        catch(Exception e){
            System.out.print("\n" + e);
            JOptionPane.showMessageDialog(null, "Error");
        }
    }//GEN-LAST:event_edit_buttonBMouseClicked

    private void okayMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_okayMouseClicked
        
        String sno = service_number_textfield.getText();
        if (sno == null || sno.trim().isEmpty()) {
            JOptionPane.showMessageDialog(null, "Pleae enter Army Number");
        } else {
            try {
                System.out.print("\n1");
                Statement st = con.createStatement();
                String query = "Select count(*) as count from attach_in where service_no = '" + sno + "'";
                String query2 = "Select count(*) as count from new_registration where service_no = '" + sno + "'";
                String query3 = "Select count(*) as count from post_in where service_no = '" + sno + "'";
                String q1 = "select * from attach_in where Service_no ='" + sno + "'";
                String q2 = "select * from new_registration where Service_no ='" + sno + "'";
                String q3 = "select * from post_in where Service_no ='" + sno + "'";
                ResultSet rs = st.executeQuery(query);
                int count = 0;
                int count2 = 0;
                int count3 = 0;
                
                if (rs.next()) {
                    count = rs.getInt("count");
                }
                rs = st.executeQuery(query2);
                if (rs.next()) {
                    count2 = rs.getInt("count");
                }
                rs = st.executeQuery(query3);
                if (rs.next()) {
                    count3 = rs.getInt("count");
                }
                if (count != 0) {
                    System.out.print("\nFound in attach_in");
                    rs = st.executeQuery(q1);
                    System.out.println("Inside iff");
                    while (rs.next()) {
                        name.setText(rs.getString("Name"));
                        rank.setText(rs.getString("Rank_"));
                        company.setText(rs.getString("Company"));
                        unit.setText(rs.getString("Unit"));
                        trade.setText(rs.getString("Trade"));
                        DOB.setText(rs.getString("DOB"));
                        
                    }

                    // System.out.println(namee+" "+unitt+" "+tradee+" "+rankk+" "+companyy);
                }
                if (count2 != 0 || count3 != 0) {
                    if (count2 != 0) {
                        rs = st.executeQuery(q2);
                    } else {
                        rs = st.executeQuery(q3);
                    }
                    System.out.println("Inside if");
                    System.out.print("\nFound in post or new");
                    while (rs.next()) {
                        name.setText(rs.getString("Name"));
                        rank.setText(rs.getString("Rank_"));
                        company.setText(rs.getString("Company"));
                        if(count2==0){
                            DOB.setText(rs.getString("DOB"));
                        }
                        else{
                            DOB.setText(rs.getString("birth_date"));
                        }
                        
                    }
                }
                if (count == 0 && count2 == 0 && count3 == 0) {
                    JOptionPane.showMessageDialog(null, "Entered Army number can't be inserted");
                }
            } catch (Exception e) {
                System.out.print("\n" + e);
                JOptionPane.showMessageDialog(null, "Error Occured");
            }
        }
    }//GEN-LAST:event_okayMouseClicked

    private void NK_textfieldActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_NK_textfieldActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_NK_textfieldActionPerformed

    private void screening_textfieldA1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_screening_textfieldA1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_screening_textfieldA1ActionPerformed

    private void HAV_textfield1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_HAV_textfield1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_HAV_textfield1ActionPerformed

    private void screening_textfieldA3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_screening_textfieldA3ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_screening_textfieldA3ActionPerformed

    private void MACP1_textfieldAActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_MACP1_textfieldAActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_MACP1_textfieldAActionPerformed

    private void screening_textfieldA4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_screening_textfieldA4ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_screening_textfieldA4ActionPerformed

    private void MACP2_textfieldAActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_MACP2_textfieldAActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_MACP2_textfieldAActionPerformed

    private void screening_textfieldA5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_screening_textfieldA5ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_screening_textfieldA5ActionPerformed

    private void MACP3_textfieldAActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_MACP3_textfieldAActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_MACP3_textfieldAActionPerformed

    private void okayBMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_okayBMouseClicked
        String sno = service_numberB.getText();
        try {
            Statement st = con.createStatement();
            ResultSet rs = null;
            String q = "select count(*) as count from seniority where Service_no='" + sno + "'";
            rs = st.executeQuery(q);
            int count = 0;
            if (rs.next()) {
                count = rs.getInt("count");
            }

            if (sno != null && !sno.trim().isEmpty()) {
                if (count != 0) {
                    view_table("o");
                } else {
                    JOptionPane.showMessageDialog(null, "No records of " + sno + " found");
                }
            } else {
                JOptionPane.showMessageDialog(null, "Enter Army Number");
            }
        } catch (Exception e) {
            System.out.print("\n" + e);
        }
    }//GEN-LAST:event_okayBMouseClicked

    private void DOP_year_combobox4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_DOP_year_combobox4ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_DOP_year_combobox4ActionPerformed

    private void DOP_year_combobox6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_DOP_year_combobox6ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_DOP_year_combobox6ActionPerformed

    private void DOP_year_combobox5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_DOP_year_combobox5ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_DOP_year_combobox5ActionPerformed

    private void DOP_year_combobox7ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_DOP_year_combobox7ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_DOP_year_combobox7ActionPerformed

    private void service_number_textfieldKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_service_number_textfieldKeyPressed
        if (evt.getKeyCode() == java.awt.event.KeyEvent.VK_ENTER) {
            okayMouseClicked(null);
        }
    }//GEN-LAST:event_service_number_textfieldKeyPressed

    private void service_numberBKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_service_numberBKeyPressed
        if (evt.getKeyCode() == java.awt.event.KeyEvent.VK_ENTER) {
            okayBMouseClicked(null);
        }
    }//GEN-LAST:event_service_numberBKeyPressed

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
            java.util.logging.Logger.getLogger(seniority.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(seniority.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(seniority.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(seniority.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new seniority(0).setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JComboBox<String> DD_date_comboboxA1;
    private javax.swing.JComboBox<String> DD_date_comboboxA2;
    private javax.swing.JComboBox<String> DD_date_comboboxA3;
    private javax.swing.JComboBox<String> DD_date_comboboxA4;
    private javax.swing.JComboBox<String> DD_date_comboboxA5;
    private javax.swing.JLabel DD_labelA1;
    private javax.swing.JLabel DD_labelA2;
    private javax.swing.JLabel DD_labelA4;
    private javax.swing.JLabel DD_labelA5;
    private javax.swing.JLabel DD_labelA6;
    private javax.swing.JComboBox<String> DD_month_comboboxA1;
    private javax.swing.JComboBox<String> DD_month_comboboxA2;
    private javax.swing.JComboBox<String> DD_month_comboboxA3;
    private javax.swing.JComboBox<String> DD_month_comboboxA4;
    private javax.swing.JComboBox<String> DD_month_comboboxA5;
    private javax.swing.JComboBox<String> DD_year_comboboxA1;
    private javax.swing.JComboBox<String> DD_year_comboboxA2;
    private javax.swing.JComboBox<String> DD_year_comboboxA3;
    private javax.swing.JComboBox<String> DD_year_comboboxA4;
    private javax.swing.JComboBox<String> DD_year_comboboxA5;
    private javax.swing.JLabel DOB;
    private javax.swing.JLabel DOB_label;
    private javax.swing.JComboBox<String> DOC_date_comboboxA;
    private javax.swing.JLabel DOC_labelA;
    private javax.swing.JComboBox<String> DOC_month_comboboxA;
    private javax.swing.JComboBox<String> DOC_year_comboboxA;
    private javax.swing.JComboBox<String> DOE_date_comboboxA;
    private javax.swing.JLabel DOE_labelA;
    private javax.swing.JComboBox<String> DOE_month_comboboxA;
    private javax.swing.JComboBox<String> DOE_year_comboboxA;
    private javax.swing.JComboBox<String> DOP_date_combobox1;
    private javax.swing.JComboBox<String> DOP_date_combobox2;
    private javax.swing.JComboBox<String> DOP_date_combobox3;
    private javax.swing.JComboBox<String> DOP_date_combobox4;
    private javax.swing.JComboBox<String> DOP_date_combobox5;
    private javax.swing.JComboBox<String> DOP_date_combobox6;
    private javax.swing.JComboBox<String> DOP_date_combobox7;
    private javax.swing.JLabel DOP_labelA;
    private javax.swing.JComboBox<String> DOP_month_combobox1;
    private javax.swing.JComboBox<String> DOP_month_combobox2;
    private javax.swing.JComboBox<String> DOP_month_combobox3;
    private javax.swing.JComboBox<String> DOP_month_combobox4;
    private javax.swing.JComboBox<String> DOP_month_combobox5;
    private javax.swing.JComboBox<String> DOP_month_combobox6;
    private javax.swing.JComboBox<String> DOP_month_combobox7;
    private javax.swing.JComboBox<String> DOP_year_combobox1;
    private javax.swing.JComboBox<String> DOP_year_combobox2;
    private javax.swing.JComboBox<String> DOP_year_combobox3;
    private javax.swing.JComboBox<String> DOP_year_combobox4;
    private javax.swing.JComboBox<String> DOP_year_combobox5;
    private javax.swing.JComboBox<String> DOP_year_combobox6;
    private javax.swing.JComboBox<String> DOP_year_combobox7;
    private javax.swing.JComboBox<String> DOS_date_combobox1;
    private javax.swing.JComboBox<String> DOS_date_combobox2;
    private javax.swing.JComboBox<String> DOS_date_combobox3;
    private javax.swing.JComboBox<String> DOS_date_combobox4;
    private javax.swing.JComboBox<String> DOS_date_combobox5;
    private javax.swing.JLabel DOS_labelA;
    private javax.swing.JComboBox<String> DOS_month_combobox1;
    private javax.swing.JComboBox<String> DOS_month_combobox2;
    private javax.swing.JComboBox<String> DOS_month_combobox3;
    private javax.swing.JComboBox<String> DOS_month_combobox4;
    private javax.swing.JComboBox<String> DOS_month_combobox5;
    private javax.swing.JComboBox<String> DOS_year_combobox1;
    private javax.swing.JComboBox<String> DOS_year_combobox2;
    private javax.swing.JComboBox<String> DOS_year_combobox3;
    private javax.swing.JComboBox<String> DOS_year_combobox4;
    private javax.swing.JComboBox<String> DOS_year_combobox5;
    private javax.swing.JLabel HAV_labelA;
    private javax.swing.JTextField HAV_textfield1;
    private javax.swing.JLabel LHAV_labelA;
    private javax.swing.JLabel LNK_labelA;
    private javax.swing.JLabel MACP1_labelA1;
    private javax.swing.JTextField MACP1_textfieldA;
    private javax.swing.JSeparator MACP1_underlineA;
    private javax.swing.JLabel MACP2_labelA;
    private javax.swing.JTextField MACP2_textfieldA;
    private javax.swing.JSeparator MACP2_underlineA;
    private javax.swing.JLabel MACP3_labelA;
    private javax.swing.JTextField MACP3_textfieldA;
    private javax.swing.JSeparator MACP3_underlineA;
    private javax.swing.JLabel MR_labelA;
    private javax.swing.JTextField MR_textfieldA;
    private javax.swing.JSeparator MR_underlineA;
    private javax.swing.JLabel NB_SUB_labelA;
    private javax.swing.JLabel NK_labelA;
    private javax.swing.JTextField NK_textfield;
    private javax.swing.JLabel SUB_MAJ_labelA;
    private javax.swing.JLabel SUB_labelA;
    private javax.swing.JScrollPane ScrollPaneA;
    private javax.swing.JScrollPane ScrollPaneB;
    private javax.swing.JSeparator Separator;
    private javax.swing.JLabel TTT_labelA;
    private javax.swing.JTextField TTT_textfieldA;
    private javax.swing.JSeparator TTT_underlineA;
    private javax.swing.JTable TableG_out;
    private javax.swing.JPanel bg;
    private javax.swing.JSeparator cadre_underline1;
    private javax.swing.JLabel company;
    private javax.swing.JLabel company_label;
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
    private javax.swing.JSeparator midA1;
    private javax.swing.JSeparator midA2;
    private javax.swing.JSeparator midA3;
    private javax.swing.JLabel minimize;
    private javax.swing.JPanel move_panel;
    private javax.swing.JLabel name;
    private javax.swing.JLabel name_label;
    private javax.swing.JLabel okay;
    private javax.swing.JLabel okayB;
    private javax.swing.JPanel panelA;
    private javax.swing.JSeparator panelA_bottom_line;
    private javax.swing.JPanel panelB;
    private javax.swing.JPanel print_buttonB;
    private javax.swing.JLabel print_labelB;
    private javax.swing.JLabel rank;
    private javax.swing.JLabel rank_label;
    private javax.swing.JLabel rank_labelA;
    private javax.swing.JPanel refresh_buttonB;
    private javax.swing.JLabel refresh_labelB;
    private javax.swing.JLabel screening_label2;
    private javax.swing.JLabel screening_labelA1;
    private javax.swing.JLabel screening_labelA3;
    private javax.swing.JLabel screening_labelA4;
    private javax.swing.JLabel screening_labelA5;
    private javax.swing.JTextField screening_textfieldA1;
    private javax.swing.JTextField screening_textfieldA3;
    private javax.swing.JTextField screening_textfieldA4;
    private javax.swing.JTextField screening_textfieldA5;
    private javax.swing.JSeparator screening_underlineA1;
    private javax.swing.JSeparator screening_underlineA2;
    private javax.swing.JSeparator screening_underlineA3;
    private javax.swing.JSeparator screening_underlineA4;
    private javax.swing.JSeparator screening_underlineA5;
    private javax.swing.JLabel screening_undertextA1;
    private javax.swing.JLabel screening_undertextA2;
    private javax.swing.JLabel screening_undertextA3;
    private javax.swing.JLabel screening_undertextA4;
    private javax.swing.JLabel screening_undertextA5;
    private javax.swing.JTextField service_numberB;
    private javax.swing.JLabel service_number_label;
    private javax.swing.JLabel service_number_labelB;
    private javax.swing.JTextField service_number_textfield;
    private javax.swing.JSeparator service_number_underline;
    private javax.swing.JPanel side_pane;
    private javax.swing.JSeparator textfield_underline;
    private javax.swing.JLabel title_icon;
    private javax.swing.JLabel title_label;
    private javax.swing.JLabel trade;
    private javax.swing.JLabel trade_label;
    private javax.swing.JLabel unit;
    private javax.swing.JLabel unit_label;
    private javax.swing.JLabel vill_label1;
    // End of variables declaration//GEN-END:variables
}
