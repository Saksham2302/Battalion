
import java.awt.Desktop;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.print.Book;
import java.awt.print.PageFormat;
import java.awt.print.Printable;
import java.awt.print.PrinterException;
import java.awt.print.PrinterJob;
import java.io.File;
import java.io.PrintWriter;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import java.sql.Connection;
import javax.swing.ImageIcon;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author chise
 */
public class Mov_Order_Perm extends javax.swing.JFrame {

    /**
     * Creates new form Mov_Order_Perm
     */
    private int mouseX, mouseY;
    Database db = new Database();
    Connection con=db.create_connection(true);
    boolean back_button = true;
    DefaultTableModel small, big;

    public Mov_Order_Perm() {
        initComponents();
        
        ScrollPaneC.getVerticalScrollBar().setUnitIncrement(16);
        ScrollPaneA.getVerticalScrollBar().setUnitIncrement(16);
        ScrollPaneB.getVerticalScrollBar().setUnitIncrement(16);
        fullscreen_ScrollPaneB.getVerticalScrollBar().setUnitIncrement(16);
        
        big = (DefaultTableModel) fullscreen_tableB.getModel();
        small = (DefaultTableModel) TableB.getModel();
        print_buttonA.setVisible(true);
        insert_buttonA.setVisible(true);
        print_buttonB.setVisible(false);
        edit_buttonB.setVisible(false);
        refresh_buttonB.setVisible(false);
        fullscreen_buttonB.setVisible(false);
    }

    class Page1 implements Printable {

        public int print(Graphics graphics, PageFormat pageFormat, int pageIndex) throws PrinterException {

            // Check If No Printable Content
            if (pageIndex >= 2) {
                return Printable.NO_SUCH_PAGE;
            }
            Graphics2D graphics2D = (Graphics2D) graphics;

            graphics2D.setClip(0, 0, 690, 790); //shapes the printing rectangle in respect to translate
            graphics2D.translate(10, 0); //marks the top of the page

            graphics2D.scale(0.68, 0.7);
            panelA.paint(graphics2D);

            // return if page exists
            return Printable.PAGE_EXISTS;
        }
    }

    class Page2 implements Printable {

        public int print(Graphics graphics, PageFormat pageFormat, int pageIndex) throws PrinterException {
            Graphics2D graphics2D = (Graphics2D) graphics;

            graphics2D.setClip(0, 25, 690, 750); //shapes the printing rectangle in respect to translate
            graphics2D.translate(10, -770); //marks the top of the page

            graphics2D.scale(0.68, 0.7);
            panelA.paint(graphics2D);

            // return if page exists
            return Printable.PAGE_EXISTS;
        }
    }

    public void scaleImage() {
        String sno = service_number_textfield.getText();
        if (sno == null || sno.trim().isEmpty()) {
            JOptionPane.showMessageDialog(null, "Enter Army Number");
        } else {
            path_file paths=new path_file();
            ImageIcon icon=new ImageIcon(paths.images +"\\"+sno+".jPG");
            Image img = icon.getImage();
            Image imgScale = img.getScaledInstance(photo_label.getWidth(), photo_label.getHeight(), Image.SCALE_SMOOTH);
            ImageIcon scaledIcon = new ImageIcon(imgScale);
            photo_label.setIcon(scaledIcon);
        }
    }

    private void PrintRecord() {
        PrinterJob printerjob = PrinterJob.getPrinterJob();
        PageFormat pageformat = printerjob.defaultPage();
        Book bk = new Book();

        bk.append(new Page1(), pageformat);
        bk.append(new Page2(), pageformat);

        // Pass the book to the PrinterJob      
        printerjob.setPageable(bk);
        if (printerjob.printDialog()) {
            try {
                printerjob.print();
            } catch (Exception e) {
                JOptionPane.showMessageDialog(null, "Could not print");
            }
        }
    }

    private void view_table(String a) {
        try {
            Statement st = con.createStatement();
            String query = "select *from mov_perm";
            ResultSet rs = st.executeQuery(query);
            small.setRowCount(0);
            big.setRowCount(0);
            String service_no, dod, dor, to, fn, offr, sors, sos, auth, purp, rept, lve_type, gtd_lve, cl, al;
            int count = 0;
            //String five_run, vrope, hrope, sixtym, ninem, result1, two_km, chinup, fivem_shuttle, situp, result2, hundred_m, name_;
            while (rs.next()) {
                count = count + 1;
                service_no = rs.getString("Service_no");
                dod = rs.getString("DOD");
                dor = rs.getString("D_Rationed");
                to = rs.getString("to_unit");
                fn = rs.getString("fn_an");
                offr = rs.getString("offr");
                sors = rs.getString("sors");
                sos = rs.getString("sos");
                auth = rs.getString("auth");
                purp = rs.getString("purpose_duty");
                rept = rs.getString("report_to");
                lve_type = rs.getString("type_lve");
                gtd_lve = rs.getString("gtd_lve");
                cl = rs.getString("cl");
                al = rs.getString("al");
                small.addRow(new Object[]{
                    count,
                    service_no,
                    dod,
                    dor,
                    auth, purp
                });
                big.addRow(new Object[]{
                    count,
                    service_no,
                    dod,
                    dor, to, fn, offr, sors, sos, auth, purp, rept, lve_type, gtd_lve, cl, al
                });

            }
            if (a != null) {
                count = 0;
                String sno = service_numberB.getText();
                // arrange=100;
                String diff_query = "select *from mov_perm where service_no='" + sno + "'";
                rs = st.executeQuery(diff_query);
                small.setRowCount(0);
                big.setRowCount(0);
                while (rs.next()) {
                    count = count + 1;
                    service_no = rs.getString("Service_no");
                    dod = rs.getString("DOD");
                    dor = rs.getString("D_Rationed");
                    to = rs.getString("to_unit");
                    fn = rs.getString("fn_an");
                    offr = rs.getString("offr");
                    sors = rs.getString("sors");
                    sos = rs.getString("sos");
                    auth = rs.getString("auth");
                    purp = rs.getString("purpose_duty");
                    rept = rs.getString("report_to");
                    lve_type = rs.getString("type_lve");
                    gtd_lve = rs.getString("gtd_lve");
                    cl = rs.getString("cl");
                    al = rs.getString("al");

                    small.addRow(new Object[]{
                        count,
                        service_no,
                        dod,
                        dor,
                        auth, purp
                    });
                    big.addRow(new Object[]{
                        count,
                        service_no,
                        dod,
                        dor, to, fn, offr, sors, sos, auth, purp, rept, lve_type, gtd_lve, cl, al
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
        title_label1 = new javax.swing.JLabel();
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
        okay = new javax.swing.JLabel();
        name_labelA = new javax.swing.JLabel();
        nameA = new javax.swing.JLabel();
        rank_labelA = new javax.swing.JLabel();
        rankA = new javax.swing.JLabel();
        company_labelA = new javax.swing.JLabel();
        companyA = new javax.swing.JLabel();
        trade_labelA = new javax.swing.JLabel();
        tradeA = new javax.swing.JLabel();
        unit_labelA = new javax.swing.JLabel();
        unitA = new javax.swing.JLabel();
        DOB_labelA = new javax.swing.JLabel();
        DOB_A = new javax.swing.JLabel();
        to_unit_labelA = new javax.swing.JLabel();
        to_unit_textfieldA = new javax.swing.JTextField();
        to_unit_underlineA = new javax.swing.JSeparator();
        Offr_labelA = new javax.swing.JLabel();
        Offr_textfieldA = new javax.swing.JTextField();
        Offr_underlineA = new javax.swing.JSeparator();
        DOD_labelA = new javax.swing.JLabel();
        DOD_date_comboboxA = new javax.swing.JComboBox<>();
        DOD_month_comboboxA = new javax.swing.JComboBox<>();
        DOD_year_comboboxA = new javax.swing.JComboBox<>();
        SORS_labelA = new javax.swing.JLabel();
        SORS_underlineA = new javax.swing.JSeparator();
        SORS_textfieldA = new javax.swing.JTextField();
        rationed_labelA = new javax.swing.JLabel();
        rationed_date_comboboxA = new javax.swing.JComboBox<>();
        rationed_month_comboboxA = new javax.swing.JComboBox<>();
        rationed_year_comboboxA = new javax.swing.JComboBox<>();
        authority_labelA = new javax.swing.JLabel();
        authority_textfieldA = new javax.swing.JTextField();
        authority_underlineA = new javax.swing.JSeparator();
        contact_labelA = new javax.swing.JLabel();
        ICard_mobA = new javax.swing.JLabel();
        CO_mob_labelA = new javax.swing.JLabel();
        CO_mobA = new javax.swing.JLabel();
        indl_mob_labelA = new javax.swing.JLabel();
        indl_mobA = new javax.swing.JLabel();
        adjt_labelA = new javax.swing.JLabel();
        adjt_mobA = new javax.swing.JLabel();
        ICard_labelA = new javax.swing.JLabel();
        midA1 = new javax.swing.JSeparator();
        Photo_individual = new javax.swing.JPanel();
        photo_label = new javax.swing.JLabel();
        purpose_labelA = new javax.swing.JLabel();
        purpose_textfieldA = new javax.swing.JTextField();
        purpose_underlineA = new javax.swing.JSeparator();
        report_labelA = new javax.swing.JLabel();
        report_textfieldA = new javax.swing.JTextField();
        report_underlineA = new javax.swing.JSeparator();
        panelA_bottom_line = new javax.swing.JSeparator();
        FNAN_labelA = new javax.swing.JLabel();
        FNAN_textfieldA = new javax.swing.JTextField();
        FNAN_underlineA = new javax.swing.JSeparator();
        SOS_labelA = new javax.swing.JLabel();
        SOS_textfieldA = new javax.swing.JTextField();
        SOS_underlineA = new javax.swing.JSeparator();
        NOK_name_labelA = new javax.swing.JLabel();
        NOK_nameA = new javax.swing.JLabel();
        NOK_relation_labelA = new javax.swing.JLabel();
        NOK_relationA = new javax.swing.JLabel();
        NOK_mobile_labelA = new javax.swing.JLabel();
        NOK_mobileA = new javax.swing.JLabel();
        address_labelA = new javax.swing.JLabel();
        villA = new javax.swing.JLabel();
        PO_labelA = new javax.swing.JLabel();
        PO_A = new javax.swing.JLabel();
        dist_labelA = new javax.swing.JLabel();
        distA = new javax.swing.JLabel();
        state_labelA = new javax.swing.JLabel();
        stateA = new javax.swing.JLabel();
        teh_labelA = new javax.swing.JLabel();
        tehsilA = new javax.swing.JLabel();
        PIN_labelA = new javax.swing.JLabel();
        PIN_A = new javax.swing.JLabel();
        vill_labelA = new javax.swing.JLabel();
        midA4 = new javax.swing.JSeparator();
        midA3 = new javax.swing.JSeparator();
        midA2 = new javax.swing.JSeparator();
        lve_gtd_labelA = new javax.swing.JLabel();
        MR_textfieldA2 = new javax.swing.JTextField();
        MR_underlineA2 = new javax.swing.JSeparator();
        CL_labelA = new javax.swing.JLabel();
        CL_textfieldA = new javax.swing.JTextField();
        CL_underlineA = new javax.swing.JSeparator();
        type_labelA = new javax.swing.JLabel();
        type_textfieldA = new javax.swing.JTextField();
        type_underlineA = new javax.swing.JSeparator();
        AL_labelA = new javax.swing.JLabel();
        AL_textfieldA = new javax.swing.JTextField();
        AL_underlineA = new javax.swing.JSeparator();
        PAN_A = new javax.swing.JLabel();
        aadhar_labelA = new javax.swing.JLabel();
        aadhar_A = new javax.swing.JLabel();
        bank_name_labelA = new javax.swing.JLabel();
        bank_nameA = new javax.swing.JLabel();
        branch_label_A = new javax.swing.JLabel();
        branchA = new javax.swing.JLabel();
        joint_AC_labelA = new javax.swing.JLabel();
        joint_AC_A = new javax.swing.JLabel();
        IFSC_labelA = new javax.swing.JLabel();
        IFSC_A = new javax.swing.JLabel();
        PAN_labelA = new javax.swing.JLabel();
        mail_labelA = new javax.swing.JLabel();
        mail_A = new javax.swing.JLabel();
        panelB = new javax.swing.JPanel();
        ScrollPaneB = new javax.swing.JScrollPane();
        TableB = new javax.swing.JTable();
        service_number_labelB = new javax.swing.JLabel();
        service_numberB = new javax.swing.JTextField();
        textfield_underline = new javax.swing.JSeparator();
        okayB = new javax.swing.JLabel();
        ScrollPaneC = new javax.swing.JScrollPane();
        panelC = new javax.swing.JPanel();
        service_number_labelC = new javax.swing.JLabel();
        name_labelC = new javax.swing.JLabel();
        army_numberC = new javax.swing.JLabel();
        rank_labelC = new javax.swing.JLabel();
        rankC = new javax.swing.JLabel();
        nameC = new javax.swing.JLabel();
        midC4 = new javax.swing.JSeparator();
        changing_for_label = new javax.swing.JLabel();
        midC0 = new javax.swing.JSeparator();
        panelC_bottom_line = new javax.swing.JSeparator();
        to_unit_labelC = new javax.swing.JLabel();
        to_unit_textfieldC = new javax.swing.JTextField();
        to_unit_underlineC = new javax.swing.JSeparator();
        Offr_labelC = new javax.swing.JLabel();
        Offr_textfieldC = new javax.swing.JTextField();
        Offr_underlineC = new javax.swing.JSeparator();
        DOD_labelC = new javax.swing.JLabel();
        DOD_date_comboboxC = new javax.swing.JComboBox<>();
        DOD_month_comboboxC = new javax.swing.JComboBox<>();
        DOD_year_comboboxC = new javax.swing.JComboBox<>();
        SORS_labelC = new javax.swing.JLabel();
        SORS_underlineC = new javax.swing.JSeparator();
        SORS_textfieldC = new javax.swing.JTextField();
        rationed_labelC = new javax.swing.JLabel();
        rationed_date_comboboxC = new javax.swing.JComboBox<>();
        rationed_month_comboboxC = new javax.swing.JComboBox<>();
        rationed_year_comboboxC = new javax.swing.JComboBox<>();
        authority_labelC = new javax.swing.JLabel();
        authority_textfieldC = new javax.swing.JTextField();
        authority_underlineC = new javax.swing.JSeparator();
        purpose_labelC = new javax.swing.JLabel();
        purpose_textfieldC = new javax.swing.JTextField();
        purpose_underlineC = new javax.swing.JSeparator();
        report_labelC = new javax.swing.JLabel();
        report_textfieldC = new javax.swing.JTextField();
        report_underlineC = new javax.swing.JSeparator();
        FNAN_labelC = new javax.swing.JLabel();
        FNAN_textfieldC = new javax.swing.JTextField();
        FNAN_underlineC = new javax.swing.JSeparator();
        SOS_labelC = new javax.swing.JLabel();
        SOS_textfieldC = new javax.swing.JTextField();
        SOS_underlineC = new javax.swing.JSeparator();
        lve_gtd_labelC = new javax.swing.JLabel();
        MR_textfieldC = new javax.swing.JTextField();
        MR_underlineC = new javax.swing.JSeparator();
        CL_labelC = new javax.swing.JLabel();
        CL_textfieldC = new javax.swing.JTextField();
        CL_underlineC = new javax.swing.JSeparator();
        type_labelC = new javax.swing.JLabel();
        type_textfieldC = new javax.swing.JTextField();
        type_underlineC = new javax.swing.JSeparator();
        AL_labelC = new javax.swing.JLabel();
        AL_textfieldC = new javax.swing.JTextField();
        AL_underlineC = new javax.swing.JSeparator();
        midC5 = new javax.swing.JSeparator();
        print_buttonA = new javax.swing.JPanel();
        print_label = new javax.swing.JLabel();
        insert_buttonA = new javax.swing.JPanel();
        insert_label = new javax.swing.JLabel();
        edit_buttonB = new javax.swing.JPanel();
        edit_labelB = new javax.swing.JLabel();
        refresh_buttonB = new javax.swing.JPanel();
        refresh_labelB = new javax.swing.JLabel();
        print_buttonB = new javax.swing.JPanel();
        print_labelB = new javax.swing.JLabel();
        update_buttonC = new javax.swing.JPanel();
        update_labelC = new javax.swing.JLabel();
        fullscreen_buttonB = new javax.swing.JPanel();
        fullscreen_labelB = new javax.swing.JLabel();
        fullscreen_panelB = new javax.swing.JPanel();
        fullscreen_ScrollPaneB = new javax.swing.JScrollPane();
        fullscreen_tableB = new javax.swing.JTable();
        exit_fullscreen_buttonB = new javax.swing.JPanel();
        exit_fullscreen_labelB = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
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

        getContentPane().add(move_panel, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, -1, -1));

        bg.setBackground(new java.awt.Color(255, 255, 255));
        bg.setPreferredSize(new java.awt.Dimension(93, 93));
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
        title_label.setText("Permanent");
        side_pane.add(title_label, new org.netbeans.lib.awtextra.AbsoluteConstraints(90, 110, 200, 40));

        title_label1.setFont(new java.awt.Font("Montserrat", 0, 28)); // NOI18N
        title_label1.setForeground(new java.awt.Color(255, 255, 255));
        title_label1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        title_label1.setText("Move Order");
        side_pane.add(title_label1, new org.netbeans.lib.awtextra.AbsoluteConstraints(90, 140, 200, 40));

        title_icon.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        title_icon.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/PNGS/mov_perm_white.png"))); // NOI18N
        side_pane.add(title_icon, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 100, 60, 90));

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

        okay.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/PNGS/check-mark.png"))); // NOI18N
        okay.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                okayMouseClicked(evt);
            }
        });
        panelA.add(okay, new org.netbeans.lib.awtextra.AbsoluteConstraints(490, 90, -1, 40));

        name_labelA.setBackground(new java.awt.Color(255, 255, 255));
        name_labelA.setFont(new java.awt.Font("Montserrat", 0, 28)); // NOI18N
        name_labelA.setForeground(new java.awt.Color(51, 51, 51));
        name_labelA.setText("Name");
        panelA.add(name_labelA, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 170, -1, 40));

        nameA.setFont(new java.awt.Font("Roboto Bk", 0, 24)); // NOI18N
        nameA.setForeground(new java.awt.Color(54, 33, 89));
        nameA.setText("> XXXXXXX");
        panelA.add(nameA, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 210, -1, 30));

        rank_labelA.setBackground(new java.awt.Color(255, 255, 255));
        rank_labelA.setFont(new java.awt.Font("Montserrat", 0, 28)); // NOI18N
        rank_labelA.setForeground(new java.awt.Color(51, 51, 51));
        rank_labelA.setText("Rank");
        panelA.add(rank_labelA, new org.netbeans.lib.awtextra.AbsoluteConstraints(350, 170, -1, 40));

        rankA.setFont(new java.awt.Font("Roboto Bk", 0, 24)); // NOI18N
        rankA.setForeground(new java.awt.Color(54, 33, 89));
        rankA.setText("> XXXXXXX");
        panelA.add(rankA, new org.netbeans.lib.awtextra.AbsoluteConstraints(350, 210, -1, 30));

        company_labelA.setBackground(new java.awt.Color(255, 255, 255));
        company_labelA.setFont(new java.awt.Font("Montserrat", 0, 28)); // NOI18N
        company_labelA.setForeground(new java.awt.Color(51, 51, 51));
        company_labelA.setText("Company");
        panelA.add(company_labelA, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 260, -1, 40));

        companyA.setFont(new java.awt.Font("Roboto Bk", 0, 24)); // NOI18N
        companyA.setForeground(new java.awt.Color(54, 33, 89));
        companyA.setText("> XXXXXXX");
        panelA.add(companyA, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 300, -1, 30));

        trade_labelA.setBackground(new java.awt.Color(255, 255, 255));
        trade_labelA.setFont(new java.awt.Font("Montserrat", 0, 28)); // NOI18N
        trade_labelA.setForeground(new java.awt.Color(51, 51, 51));
        trade_labelA.setText("Trade");
        panelA.add(trade_labelA, new org.netbeans.lib.awtextra.AbsoluteConstraints(350, 350, -1, 40));

        tradeA.setFont(new java.awt.Font("Roboto Bk", 0, 24)); // NOI18N
        tradeA.setForeground(new java.awt.Color(54, 33, 89));
        tradeA.setText("> XXXXXXX");
        panelA.add(tradeA, new org.netbeans.lib.awtextra.AbsoluteConstraints(350, 390, -1, 30));

        unit_labelA.setBackground(new java.awt.Color(255, 255, 255));
        unit_labelA.setFont(new java.awt.Font("Montserrat", 0, 28)); // NOI18N
        unit_labelA.setForeground(new java.awt.Color(51, 51, 51));
        unit_labelA.setText("Unit");
        panelA.add(unit_labelA, new org.netbeans.lib.awtextra.AbsoluteConstraints(350, 260, -1, 40));

        unitA.setFont(new java.awt.Font("Roboto Bk", 0, 24)); // NOI18N
        unitA.setForeground(new java.awt.Color(54, 33, 89));
        unitA.setText("> XXXXXXX");
        panelA.add(unitA, new org.netbeans.lib.awtextra.AbsoluteConstraints(350, 300, -1, 30));

        DOB_labelA.setBackground(new java.awt.Color(255, 255, 255));
        DOB_labelA.setFont(new java.awt.Font("Montserrat", 0, 28)); // NOI18N
        DOB_labelA.setForeground(new java.awt.Color(51, 51, 51));
        DOB_labelA.setText("DOB");
        panelA.add(DOB_labelA, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 350, -1, 40));

        DOB_A.setFont(new java.awt.Font("Roboto Bk", 0, 24)); // NOI18N
        DOB_A.setForeground(new java.awt.Color(54, 33, 89));
        DOB_A.setText("> XXXXXXX");
        panelA.add(DOB_A, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 390, -1, 30));

        to_unit_labelA.setFont(new java.awt.Font("Montserrat", 0, 28)); // NOI18N
        to_unit_labelA.setForeground(new java.awt.Color(51, 51, 51));
        to_unit_labelA.setText("To Unit");
        panelA.add(to_unit_labelA, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 460, -1, 40));

        to_unit_textfieldA.setFont(new java.awt.Font("Roboto", 0, 24)); // NOI18N
        to_unit_textfieldA.setForeground(new java.awt.Color(54, 33, 89));
        to_unit_textfieldA.setBorder(null);
        to_unit_textfieldA.setMargin(new java.awt.Insets(2, 4, 2, 2));
        panelA.add(to_unit_textfieldA, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 500, 320, 40));

        to_unit_underlineA.setForeground(new java.awt.Color(54, 33, 89));
        panelA.add(to_unit_underlineA, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 540, 320, 20));

        Offr_labelA.setFont(new java.awt.Font("Montserrat", 0, 28)); // NOI18N
        Offr_labelA.setForeground(new java.awt.Color(51, 51, 51));
        Offr_labelA.setText("Offr/ JCO/ OR ");
        panelA.add(Offr_labelA, new org.netbeans.lib.awtextra.AbsoluteConstraints(480, 570, -1, 40));

        Offr_textfieldA.setFont(new java.awt.Font("Roboto", 0, 24)); // NOI18N
        Offr_textfieldA.setForeground(new java.awt.Color(54, 33, 89));
        Offr_textfieldA.setBorder(null);
        Offr_textfieldA.setMargin(new java.awt.Insets(2, 4, 2, 2));
        panelA.add(Offr_textfieldA, new org.netbeans.lib.awtextra.AbsoluteConstraints(480, 610, 320, 40));

        Offr_underlineA.setForeground(new java.awt.Color(54, 33, 89));
        panelA.add(Offr_underlineA, new org.netbeans.lib.awtextra.AbsoluteConstraints(480, 650, 320, 20));

        DOD_labelA.setBackground(new java.awt.Color(255, 255, 255));
        DOD_labelA.setFont(new java.awt.Font("Montserrat", 0, 28)); // NOI18N
        DOD_labelA.setForeground(new java.awt.Color(51, 51, 51));
        DOD_labelA.setText("Departure Date ");
        panelA.add(DOD_labelA, new org.netbeans.lib.awtextra.AbsoluteConstraints(480, 460, -1, 40));

        DOD_date_comboboxA.setFont(new java.awt.Font("Roboto", 0, 18)); // NOI18N
        DOD_date_comboboxA.setForeground(new java.awt.Color(44, 62, 80));
        DOD_date_comboboxA.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "DD", "01", "02", "03", "04", "05", "06", "07", "08", "09", "10", "11", "12", "13", "14", "15", "16", "17", "18", "19", "20", "21", "22", "23", "24", "25", "26", "27", "28", "29", "30", "31" }));
        DOD_date_comboboxA.setBorder(null);
        panelA.add(DOD_date_comboboxA, new org.netbeans.lib.awtextra.AbsoluteConstraints(480, 500, 100, 40));

        DOD_month_comboboxA.setFont(new java.awt.Font("Roboto", 0, 18)); // NOI18N
        DOD_month_comboboxA.setForeground(new java.awt.Color(44, 62, 80));
        DOD_month_comboboxA.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "MM", "01", "02", "03", "04", "05", "06", "07", "08", "09", "10", "11", "12" }));
        DOD_month_comboboxA.setBorder(null);
        panelA.add(DOD_month_comboboxA, new org.netbeans.lib.awtextra.AbsoluteConstraints(590, 500, 100, 40));

        DOD_year_comboboxA.setFont(new java.awt.Font("Roboto", 0, 18)); // NOI18N
        DOD_year_comboboxA.setForeground(new java.awt.Color(44, 62, 80));
        DOD_year_comboboxA.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "YYYY", "1950", "1951", "1952", "1953", "1954", "1955", "1956", "1957", "1958", "1959", "1960", "1961", "1962", "1963", "1964", "1965", "1966", "1967", "1968", "1969", "1970", "1971", "1972", "1973", "1974", "1975", "1976", "1977", "1978", "1979", "1980", "1981", "1982", "1983", "1984", "1985", "1986", "1987", "1988", "1989", "1990", "1991", "1992", "1993", "1994", "1995", "1996", "1997", "1998", "1999", "2000", "2001", "2002", "2003", "2004", "2005", "2006", "2007", "2008", "2009", "2010", "2011", "2012", "2013", "2014", "2015", "2016", "2017", "2018", "2019", "2020", "2021", "2022", "2023", "2024", "2025", "2026", "2027", "2028", "2029", "2030", "2031", "2032", "2033", "2034", "2035", "2036", "2037", "2038", "2039", "2040", "2041", "2042", "2043", "2044", "2045", "2046", "2047", "2048", "2049", "2050", "2051", "2052", "2053", "2054", "2055", "2056", "2057", "2058", "2059", "2060", "2061", "2062", "2063", "2064", "2065", "2066", "2067", "2068", "2069", "2070", "2071", "2072", "2073", "2074", "2075", "2076", "2077", "2078", "2079", "2080", "2081", "2082", "2083", "2084", "2085", "2086", "2087", "2088", "2089", "2090", "2091", "2092", "2093", "2094", "2095", "2096", "2097", "2098", "2099", "2100", " " }));
        DOD_year_comboboxA.setBorder(null);
        panelA.add(DOD_year_comboboxA, new org.netbeans.lib.awtextra.AbsoluteConstraints(700, 500, 100, 40));

        SORS_labelA.setFont(new java.awt.Font("Montserrat", 0, 28)); // NOI18N
        SORS_labelA.setForeground(new java.awt.Color(51, 51, 51));
        SORS_labelA.setText("SORS ");
        panelA.add(SORS_labelA, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 680, -1, 40));

        SORS_underlineA.setForeground(new java.awt.Color(54, 33, 89));
        panelA.add(SORS_underlineA, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 760, 320, 20));

        SORS_textfieldA.setFont(new java.awt.Font("Roboto", 0, 24)); // NOI18N
        SORS_textfieldA.setForeground(new java.awt.Color(54, 33, 89));
        SORS_textfieldA.setBorder(null);
        SORS_textfieldA.setMargin(new java.awt.Insets(2, 4, 2, 2));
        panelA.add(SORS_textfieldA, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 720, 320, 40));

        rationed_labelA.setBackground(new java.awt.Color(255, 255, 255));
        rationed_labelA.setFont(new java.awt.Font("Montserrat", 0, 28)); // NOI18N
        rationed_labelA.setForeground(new java.awt.Color(51, 51, 51));
        rationed_labelA.setText("Rationed Up to ");
        panelA.add(rationed_labelA, new org.netbeans.lib.awtextra.AbsoluteConstraints(480, 790, -1, 40));

        rationed_date_comboboxA.setFont(new java.awt.Font("Roboto", 0, 18)); // NOI18N
        rationed_date_comboboxA.setForeground(new java.awt.Color(44, 62, 80));
        rationed_date_comboboxA.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "DD", "01", "02", "03", "04", "05", "06", "07", "08", "09", "10", "11", "12", "13", "14", "15", "16", "17", "18", "19", "20", "21", "22", "23", "24", "25", "26", "27", "28", "29", "30", "31" }));
        rationed_date_comboboxA.setBorder(null);
        panelA.add(rationed_date_comboboxA, new org.netbeans.lib.awtextra.AbsoluteConstraints(480, 830, 100, 40));

        rationed_month_comboboxA.setFont(new java.awt.Font("Roboto", 0, 18)); // NOI18N
        rationed_month_comboboxA.setForeground(new java.awt.Color(44, 62, 80));
        rationed_month_comboboxA.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "MM", "01", "02", "03", "04", "05", "06", "07", "08", "09", "10", "11", "12" }));
        rationed_month_comboboxA.setBorder(null);
        panelA.add(rationed_month_comboboxA, new org.netbeans.lib.awtextra.AbsoluteConstraints(590, 830, 100, 40));

        rationed_year_comboboxA.setFont(new java.awt.Font("Roboto", 0, 18)); // NOI18N
        rationed_year_comboboxA.setForeground(new java.awt.Color(44, 62, 80));
        rationed_year_comboboxA.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "YYYY", "1950", "1951", "1952", "1953", "1954", "1955", "1956", "1957", "1958", "1959", "1960", "1961", "1962", "1963", "1964", "1965", "1966", "1967", "1968", "1969", "1970", "1971", "1972", "1973", "1974", "1975", "1976", "1977", "1978", "1979", "1980", "1981", "1982", "1983", "1984", "1985", "1986", "1987", "1988", "1989", "1990", "1991", "1992", "1993", "1994", "1995", "1996", "1997", "1998", "1999", "2000", "2001", "2002", "2003", "2004", "2005", "2006", "2007", "2008", "2009", "2010", "2011", "2012", "2013", "2014", "2015", "2016", "2017", "2018", "2019", "2020", "2021", "2022", "2023", "2024", "2025", "2026", "2027", "2028", "2029", "2030", "2031", "2032", "2033", "2034", "2035", "2036", "2037", "2038", "2039", "2040", "2041", "2042", "2043", "2044", "2045", "2046", "2047", "2048", "2049", "2050", "2051", "2052", "2053", "2054", "2055", "2056", "2057", "2058", "2059", "2060", "2061", "2062", "2063", "2064", "2065", "2066", "2067", "2068", "2069", "2070", "2071", "2072", "2073", "2074", "2075", "2076", "2077", "2078", "2079", "2080", "2081", "2082", "2083", "2084", "2085", "2086", "2087", "2088", "2089", "2090", "2091", "2092", "2093", "2094", "2095", "2096", "2097", "2098", "2099", "2100", " " }));
        rationed_year_comboboxA.setBorder(null);
        panelA.add(rationed_year_comboboxA, new org.netbeans.lib.awtextra.AbsoluteConstraints(700, 830, 100, 40));

        authority_labelA.setFont(new java.awt.Font("Montserrat", 0, 28)); // NOI18N
        authority_labelA.setForeground(new java.awt.Color(51, 51, 51));
        authority_labelA.setText("Authority for Mov ");
        panelA.add(authority_labelA, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 790, -1, 40));

        authority_textfieldA.setFont(new java.awt.Font("Roboto", 0, 24)); // NOI18N
        authority_textfieldA.setForeground(new java.awt.Color(54, 33, 89));
        authority_textfieldA.setBorder(null);
        authority_textfieldA.setMargin(new java.awt.Insets(2, 4, 2, 2));
        panelA.add(authority_textfieldA, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 830, 320, 40));

        authority_underlineA.setForeground(new java.awt.Color(54, 33, 89));
        panelA.add(authority_underlineA, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 870, 320, 20));

        contact_labelA.setBackground(new java.awt.Color(255, 255, 255));
        contact_labelA.setFont(new java.awt.Font("Montserrat", 0, 28)); // NOI18N
        contact_labelA.setForeground(new java.awt.Color(51, 51, 51));
        contact_labelA.setText("Contact");
        panelA.add(contact_labelA, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 1260, -1, 50));

        ICard_mobA.setFont(new java.awt.Font("Roboto Bk", 0, 24)); // NOI18N
        ICard_mobA.setForeground(new java.awt.Color(54, 33, 89));
        ICard_mobA.setText("> XXXXXXX");
        panelA.add(ICard_mobA, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 1360, -1, 30));

        CO_mob_labelA.setBackground(new java.awt.Color(255, 255, 255));
        CO_mob_labelA.setFont(new java.awt.Font("Montserrat", 0, 24)); // NOI18N
        CO_mob_labelA.setForeground(new java.awt.Color(66, 50, 77));
        CO_mob_labelA.setText("CO Mob");
        panelA.add(CO_mob_labelA, new org.netbeans.lib.awtextra.AbsoluteConstraints(350, 1320, -1, 50));

        CO_mobA.setFont(new java.awt.Font("Roboto Bk", 0, 24)); // NOI18N
        CO_mobA.setForeground(new java.awt.Color(54, 33, 89));
        CO_mobA.setText("> XXXXXXX");
        panelA.add(CO_mobA, new org.netbeans.lib.awtextra.AbsoluteConstraints(350, 1360, -1, 30));

        indl_mob_labelA.setBackground(new java.awt.Color(255, 255, 255));
        indl_mob_labelA.setFont(new java.awt.Font("Montserrat", 0, 24)); // NOI18N
        indl_mob_labelA.setForeground(new java.awt.Color(66, 50, 77));
        indl_mob_labelA.setText("Indl Mob");
        panelA.add(indl_mob_labelA, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 1400, -1, 50));

        indl_mobA.setFont(new java.awt.Font("Roboto Bk", 0, 24)); // NOI18N
        indl_mobA.setForeground(new java.awt.Color(54, 33, 89));
        indl_mobA.setText("> XXXXXXX");
        panelA.add(indl_mobA, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 1440, -1, 30));

        adjt_labelA.setBackground(new java.awt.Color(255, 255, 255));
        adjt_labelA.setFont(new java.awt.Font("Montserrat", 0, 24)); // NOI18N
        adjt_labelA.setForeground(new java.awt.Color(66, 50, 77));
        adjt_labelA.setText("Adjt Mob");
        panelA.add(adjt_labelA, new org.netbeans.lib.awtextra.AbsoluteConstraints(600, 1320, -1, 50));

        adjt_mobA.setFont(new java.awt.Font("Roboto Bk", 0, 24)); // NOI18N
        adjt_mobA.setForeground(new java.awt.Color(54, 33, 89));
        adjt_mobA.setText("> XXXXXXX");
        panelA.add(adjt_mobA, new org.netbeans.lib.awtextra.AbsoluteConstraints(600, 1360, -1, 30));

        ICard_labelA.setBackground(new java.awt.Color(255, 255, 255));
        ICard_labelA.setFont(new java.awt.Font("Montserrat", 0, 24)); // NOI18N
        ICard_labelA.setForeground(new java.awt.Color(66, 50, 77));
        ICard_labelA.setText("I-Card Number");
        panelA.add(ICard_labelA, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 1320, -1, 50));

        midA1.setForeground(new java.awt.Color(204, 204, 255));
        panelA.add(midA1, new org.netbeans.lib.awtextra.AbsoluteConstraints(210, 1285, 440, 10));

        Photo_individual.setMaximumSize(new java.awt.Dimension(95, 25));
        Photo_individual.setMinimumSize(new java.awt.Dimension(95, 25));
        Photo_individual.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                Photo_individualMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                Photo_individualMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                Photo_individualMouseExited(evt);
            }
        });
        Photo_individual.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());
        Photo_individual.add(photo_label, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 180, 200));

        panelA.add(Photo_individual, new org.netbeans.lib.awtextra.AbsoluteConstraints(620, 80, 180, 200));

        purpose_labelA.setFont(new java.awt.Font("Montserrat", 0, 28)); // NOI18N
        purpose_labelA.setForeground(new java.awt.Color(51, 51, 51));
        purpose_labelA.setText("Purpose of Duty");
        panelA.add(purpose_labelA, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 910, -1, 40));

        purpose_textfieldA.setFont(new java.awt.Font("Roboto", 0, 24)); // NOI18N
        purpose_textfieldA.setForeground(new java.awt.Color(54, 33, 89));
        purpose_textfieldA.setBorder(null);
        purpose_textfieldA.setMargin(new java.awt.Insets(2, 4, 2, 2));
        panelA.add(purpose_textfieldA, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 950, 320, 40));

        purpose_underlineA.setForeground(new java.awt.Color(54, 33, 89));
        panelA.add(purpose_underlineA, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 990, 320, 20));

        report_labelA.setFont(new java.awt.Font("Montserrat", 0, 28)); // NOI18N
        report_labelA.setForeground(new java.awt.Color(51, 51, 51));
        report_labelA.setText("Report To");
        panelA.add(report_labelA, new org.netbeans.lib.awtextra.AbsoluteConstraints(480, 910, 190, 40));

        report_textfieldA.setFont(new java.awt.Font("Roboto", 0, 24)); // NOI18N
        report_textfieldA.setForeground(new java.awt.Color(54, 33, 89));
        report_textfieldA.setBorder(null);
        report_textfieldA.setMargin(new java.awt.Insets(2, 4, 2, 2));
        panelA.add(report_textfieldA, new org.netbeans.lib.awtextra.AbsoluteConstraints(480, 950, 320, 40));

        report_underlineA.setForeground(new java.awt.Color(54, 33, 89));
        panelA.add(report_underlineA, new org.netbeans.lib.awtextra.AbsoluteConstraints(480, 990, 320, 20));

        panelA_bottom_line.setForeground(new java.awt.Color(54, 33, 89));
        panelA.add(panelA_bottom_line, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 2230, 910, 10));

        FNAN_labelA.setFont(new java.awt.Font("Montserrat", 0, 28)); // NOI18N
        FNAN_labelA.setForeground(new java.awt.Color(51, 51, 51));
        FNAN_labelA.setText("FN/AN");
        panelA.add(FNAN_labelA, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 570, -1, 40));

        FNAN_textfieldA.setFont(new java.awt.Font("Roboto", 0, 24)); // NOI18N
        FNAN_textfieldA.setForeground(new java.awt.Color(54, 33, 89));
        FNAN_textfieldA.setBorder(null);
        FNAN_textfieldA.setMargin(new java.awt.Insets(2, 4, 2, 2));
        panelA.add(FNAN_textfieldA, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 610, 320, 40));

        FNAN_underlineA.setForeground(new java.awt.Color(54, 33, 89));
        panelA.add(FNAN_underlineA, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 650, 320, 20));

        SOS_labelA.setFont(new java.awt.Font("Montserrat", 0, 28)); // NOI18N
        SOS_labelA.setForeground(new java.awt.Color(51, 51, 51));
        SOS_labelA.setText("SOS");
        panelA.add(SOS_labelA, new org.netbeans.lib.awtextra.AbsoluteConstraints(480, 680, -1, 40));

        SOS_textfieldA.setFont(new java.awt.Font("Roboto", 0, 24)); // NOI18N
        SOS_textfieldA.setForeground(new java.awt.Color(54, 33, 89));
        SOS_textfieldA.setBorder(null);
        SOS_textfieldA.setMargin(new java.awt.Insets(2, 4, 2, 2));
        panelA.add(SOS_textfieldA, new org.netbeans.lib.awtextra.AbsoluteConstraints(480, 720, 320, 40));

        SOS_underlineA.setForeground(new java.awt.Color(54, 33, 89));
        panelA.add(SOS_underlineA, new org.netbeans.lib.awtextra.AbsoluteConstraints(480, 760, 320, 20));

        NOK_name_labelA.setBackground(new java.awt.Color(255, 255, 255));
        NOK_name_labelA.setFont(new java.awt.Font("Montserrat", 0, 24)); // NOI18N
        NOK_name_labelA.setForeground(new java.awt.Color(51, 51, 51));
        NOK_name_labelA.setText("NOK Name");
        panelA.add(NOK_name_labelA, new org.netbeans.lib.awtextra.AbsoluteConstraints(350, 1550, -1, 50));

        NOK_nameA.setFont(new java.awt.Font("Roboto Bk", 0, 24)); // NOI18N
        NOK_nameA.setForeground(new java.awt.Color(54, 33, 89));
        NOK_nameA.setText("> XXXXXXX");
        panelA.add(NOK_nameA, new org.netbeans.lib.awtextra.AbsoluteConstraints(350, 1590, -1, 30));

        NOK_relation_labelA.setBackground(new java.awt.Color(255, 255, 255));
        NOK_relation_labelA.setFont(new java.awt.Font("Montserrat", 0, 24)); // NOI18N
        NOK_relation_labelA.setForeground(new java.awt.Color(51, 51, 51));
        NOK_relation_labelA.setText("NOK Relation");
        panelA.add(NOK_relation_labelA, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 1550, -1, 50));

        NOK_relationA.setFont(new java.awt.Font("Roboto Bk", 0, 24)); // NOI18N
        NOK_relationA.setForeground(new java.awt.Color(54, 33, 89));
        NOK_relationA.setText("> XXXXXXX");
        panelA.add(NOK_relationA, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 1590, -1, 30));

        NOK_mobile_labelA.setBackground(new java.awt.Color(255, 255, 255));
        NOK_mobile_labelA.setFont(new java.awt.Font("Montserrat", 0, 24)); // NOI18N
        NOK_mobile_labelA.setForeground(new java.awt.Color(51, 51, 51));
        NOK_mobile_labelA.setText("NOK Mob.");
        panelA.add(NOK_mobile_labelA, new org.netbeans.lib.awtextra.AbsoluteConstraints(600, 1550, -1, 50));

        NOK_mobileA.setFont(new java.awt.Font("Roboto Bk", 0, 24)); // NOI18N
        NOK_mobileA.setForeground(new java.awt.Color(54, 33, 89));
        NOK_mobileA.setText("> XXXXXXX");
        panelA.add(NOK_mobileA, new org.netbeans.lib.awtextra.AbsoluteConstraints(600, 1590, -1, 30));

        address_labelA.setBackground(new java.awt.Color(255, 255, 255));
        address_labelA.setFont(new java.awt.Font("Montserrat", 0, 28)); // NOI18N
        address_labelA.setForeground(new java.awt.Color(51, 51, 51));
        address_labelA.setText("Address");
        panelA.add(address_labelA, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 1650, -1, 50));

        villA.setFont(new java.awt.Font("Roboto Bk", 0, 24)); // NOI18N
        villA.setForeground(new java.awt.Color(54, 33, 89));
        villA.setText("> XXXXXXX");
        panelA.add(villA, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 1750, -1, 30));

        PO_labelA.setBackground(new java.awt.Color(255, 255, 255));
        PO_labelA.setFont(new java.awt.Font("Montserrat", 0, 24)); // NOI18N
        PO_labelA.setForeground(new java.awt.Color(66, 50, 77));
        PO_labelA.setText("PO");
        panelA.add(PO_labelA, new org.netbeans.lib.awtextra.AbsoluteConstraints(350, 1710, -1, 50));

        PO_A.setFont(new java.awt.Font("Roboto Bk", 0, 24)); // NOI18N
        PO_A.setForeground(new java.awt.Color(54, 33, 89));
        PO_A.setText("> XXXXXXX");
        panelA.add(PO_A, new org.netbeans.lib.awtextra.AbsoluteConstraints(350, 1750, -1, 30));

        dist_labelA.setBackground(new java.awt.Color(255, 255, 255));
        dist_labelA.setFont(new java.awt.Font("Montserrat", 0, 24)); // NOI18N
        dist_labelA.setForeground(new java.awt.Color(66, 50, 77));
        dist_labelA.setText("District");
        panelA.add(dist_labelA, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 1790, -1, 50));

        distA.setFont(new java.awt.Font("Roboto Bk", 0, 24)); // NOI18N
        distA.setForeground(new java.awt.Color(54, 33, 89));
        distA.setText("> XXXXXXX");
        panelA.add(distA, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 1830, -1, 30));

        state_labelA.setBackground(new java.awt.Color(255, 255, 255));
        state_labelA.setFont(new java.awt.Font("Montserrat", 0, 24)); // NOI18N
        state_labelA.setForeground(new java.awt.Color(66, 50, 77));
        state_labelA.setText("State");
        panelA.add(state_labelA, new org.netbeans.lib.awtextra.AbsoluteConstraints(350, 1790, -1, 50));

        stateA.setFont(new java.awt.Font("Roboto Bk", 0, 24)); // NOI18N
        stateA.setForeground(new java.awt.Color(54, 33, 89));
        stateA.setText("> XXXXXXX");
        panelA.add(stateA, new org.netbeans.lib.awtextra.AbsoluteConstraints(350, 1830, -1, 30));

        teh_labelA.setBackground(new java.awt.Color(255, 255, 255));
        teh_labelA.setFont(new java.awt.Font("Montserrat", 0, 24)); // NOI18N
        teh_labelA.setForeground(new java.awt.Color(66, 50, 77));
        teh_labelA.setText("Tehsil");
        panelA.add(teh_labelA, new org.netbeans.lib.awtextra.AbsoluteConstraints(600, 1710, -1, 50));

        tehsilA.setFont(new java.awt.Font("Roboto Bk", 0, 24)); // NOI18N
        tehsilA.setForeground(new java.awt.Color(54, 33, 89));
        tehsilA.setText("> XXXXXXX");
        panelA.add(tehsilA, new org.netbeans.lib.awtextra.AbsoluteConstraints(600, 1750, -1, 30));

        PIN_labelA.setBackground(new java.awt.Color(255, 255, 255));
        PIN_labelA.setFont(new java.awt.Font("Montserrat", 0, 24)); // NOI18N
        PIN_labelA.setForeground(new java.awt.Color(66, 50, 77));
        PIN_labelA.setText("PIN");
        panelA.add(PIN_labelA, new org.netbeans.lib.awtextra.AbsoluteConstraints(600, 1790, -1, 50));

        PIN_A.setFont(new java.awt.Font("Roboto Bk", 0, 24)); // NOI18N
        PIN_A.setForeground(new java.awt.Color(54, 33, 89));
        PIN_A.setText("> XXXXXXX");
        panelA.add(PIN_A, new org.netbeans.lib.awtextra.AbsoluteConstraints(600, 1830, -1, 30));

        vill_labelA.setBackground(new java.awt.Color(255, 255, 255));
        vill_labelA.setFont(new java.awt.Font("Montserrat", 0, 24)); // NOI18N
        vill_labelA.setForeground(new java.awt.Color(66, 50, 77));
        vill_labelA.setText("Village");
        panelA.add(vill_labelA, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 1710, -1, 50));

        midA4.setForeground(new java.awt.Color(204, 204, 255));
        panelA.add(midA4, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 1910, 570, 10));

        midA3.setForeground(new java.awt.Color(204, 204, 255));
        panelA.add(midA3, new org.netbeans.lib.awtextra.AbsoluteConstraints(210, 1675, 440, 10));

        midA2.setForeground(new java.awt.Color(204, 204, 255));
        panelA.add(midA2, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 1520, 570, 10));

        lve_gtd_labelA.setFont(new java.awt.Font("Montserrat", 0, 28)); // NOI18N
        lve_gtd_labelA.setForeground(new java.awt.Color(51, 51, 51));
        lve_gtd_labelA.setText("Lve Gtd ");
        panelA.add(lve_gtd_labelA, new org.netbeans.lib.awtextra.AbsoluteConstraints(480, 1020, -1, 40));

        MR_textfieldA2.setFont(new java.awt.Font("Roboto", 0, 24)); // NOI18N
        MR_textfieldA2.setForeground(new java.awt.Color(54, 33, 89));
        MR_textfieldA2.setBorder(null);
        MR_textfieldA2.setMargin(new java.awt.Insets(2, 4, 2, 2));
        panelA.add(MR_textfieldA2, new org.netbeans.lib.awtextra.AbsoluteConstraints(480, 1060, 320, 40));

        MR_underlineA2.setForeground(new java.awt.Color(54, 33, 89));
        panelA.add(MR_underlineA2, new org.netbeans.lib.awtextra.AbsoluteConstraints(480, 1100, 320, 20));

        CL_labelA.setFont(new java.awt.Font("Montserrat", 0, 28)); // NOI18N
        CL_labelA.setForeground(new java.awt.Color(51, 51, 51));
        CL_labelA.setText("CL Availed");
        panelA.add(CL_labelA, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 1130, -1, 40));

        CL_textfieldA.setFont(new java.awt.Font("Roboto", 0, 24)); // NOI18N
        CL_textfieldA.setForeground(new java.awt.Color(54, 33, 89));
        CL_textfieldA.setBorder(null);
        CL_textfieldA.setMargin(new java.awt.Insets(2, 4, 2, 2));
        panelA.add(CL_textfieldA, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 1170, 320, 40));

        CL_underlineA.setForeground(new java.awt.Color(54, 33, 89));
        panelA.add(CL_underlineA, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 1210, 320, 20));

        type_labelA.setFont(new java.awt.Font("Montserrat", 0, 28)); // NOI18N
        type_labelA.setForeground(new java.awt.Color(51, 51, 51));
        type_labelA.setText("Type of Lve Gtd ");
        panelA.add(type_labelA, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 1020, -1, 40));

        type_textfieldA.setFont(new java.awt.Font("Roboto", 0, 24)); // NOI18N
        type_textfieldA.setForeground(new java.awt.Color(54, 33, 89));
        type_textfieldA.setBorder(null);
        type_textfieldA.setMargin(new java.awt.Insets(2, 4, 2, 2));
        panelA.add(type_textfieldA, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 1060, 320, 40));

        type_underlineA.setForeground(new java.awt.Color(54, 33, 89));
        panelA.add(type_underlineA, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 1100, 320, 20));

        AL_labelA.setFont(new java.awt.Font("Montserrat", 0, 28)); // NOI18N
        AL_labelA.setForeground(new java.awt.Color(51, 51, 51));
        AL_labelA.setText("AL Availed");
        panelA.add(AL_labelA, new org.netbeans.lib.awtextra.AbsoluteConstraints(480, 1130, -1, 40));

        AL_textfieldA.setFont(new java.awt.Font("Roboto", 0, 24)); // NOI18N
        AL_textfieldA.setForeground(new java.awt.Color(54, 33, 89));
        AL_textfieldA.setBorder(null);
        AL_textfieldA.setMargin(new java.awt.Insets(2, 4, 2, 2));
        panelA.add(AL_textfieldA, new org.netbeans.lib.awtextra.AbsoluteConstraints(480, 1170, 320, 40));

        AL_underlineA.setForeground(new java.awt.Color(54, 33, 89));
        panelA.add(AL_underlineA, new org.netbeans.lib.awtextra.AbsoluteConstraints(480, 1210, 320, 20));

        PAN_A.setFont(new java.awt.Font("Roboto Bk", 0, 24)); // NOI18N
        PAN_A.setForeground(new java.awt.Color(54, 33, 89));
        PAN_A.setText("> XXXXXXX");
        panelA.add(PAN_A, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 1990, -1, 30));

        aadhar_labelA.setBackground(new java.awt.Color(255, 255, 255));
        aadhar_labelA.setFont(new java.awt.Font("Montserrat", 0, 24)); // NOI18N
        aadhar_labelA.setForeground(new java.awt.Color(66, 50, 77));
        aadhar_labelA.setText("Aadhar");
        panelA.add(aadhar_labelA, new org.netbeans.lib.awtextra.AbsoluteConstraints(350, 1950, -1, 50));

        aadhar_A.setFont(new java.awt.Font("Roboto Bk", 0, 24)); // NOI18N
        aadhar_A.setForeground(new java.awt.Color(54, 33, 89));
        aadhar_A.setText("> XXXXXXX");
        panelA.add(aadhar_A, new org.netbeans.lib.awtextra.AbsoluteConstraints(350, 1990, -1, 30));

        bank_name_labelA.setBackground(new java.awt.Color(255, 255, 255));
        bank_name_labelA.setFont(new java.awt.Font("Montserrat", 0, 24)); // NOI18N
        bank_name_labelA.setForeground(new java.awt.Color(66, 50, 77));
        bank_name_labelA.setText("Bank Name");
        panelA.add(bank_name_labelA, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 2030, -1, 50));

        bank_nameA.setFont(new java.awt.Font("Roboto Bk", 0, 24)); // NOI18N
        bank_nameA.setForeground(new java.awt.Color(54, 33, 89));
        bank_nameA.setText("> XXXXXXX");
        panelA.add(bank_nameA, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 2070, -1, 30));

        branch_label_A.setBackground(new java.awt.Color(255, 255, 255));
        branch_label_A.setFont(new java.awt.Font("Montserrat", 0, 24)); // NOI18N
        branch_label_A.setForeground(new java.awt.Color(66, 50, 77));
        branch_label_A.setText("Branch");
        panelA.add(branch_label_A, new org.netbeans.lib.awtextra.AbsoluteConstraints(350, 2030, -1, 50));

        branchA.setFont(new java.awt.Font("Roboto Bk", 0, 24)); // NOI18N
        branchA.setForeground(new java.awt.Color(54, 33, 89));
        branchA.setText("> XXXXXXX");
        panelA.add(branchA, new org.netbeans.lib.awtextra.AbsoluteConstraints(350, 2070, -1, 30));

        joint_AC_labelA.setBackground(new java.awt.Color(255, 255, 255));
        joint_AC_labelA.setFont(new java.awt.Font("Montserrat", 0, 24)); // NOI18N
        joint_AC_labelA.setForeground(new java.awt.Color(66, 50, 77));
        joint_AC_labelA.setText("Joint A/C");
        panelA.add(joint_AC_labelA, new org.netbeans.lib.awtextra.AbsoluteConstraints(600, 1950, -1, 50));

        joint_AC_A.setFont(new java.awt.Font("Roboto Bk", 0, 24)); // NOI18N
        joint_AC_A.setForeground(new java.awt.Color(54, 33, 89));
        joint_AC_A.setText("> XXXXXXX");
        panelA.add(joint_AC_A, new org.netbeans.lib.awtextra.AbsoluteConstraints(600, 1990, -1, 30));

        IFSC_labelA.setBackground(new java.awt.Color(255, 255, 255));
        IFSC_labelA.setFont(new java.awt.Font("Montserrat", 0, 24)); // NOI18N
        IFSC_labelA.setForeground(new java.awt.Color(66, 50, 77));
        IFSC_labelA.setText("IFSC");
        panelA.add(IFSC_labelA, new org.netbeans.lib.awtextra.AbsoluteConstraints(600, 2030, -1, 50));

        IFSC_A.setFont(new java.awt.Font("Roboto Bk", 0, 24)); // NOI18N
        IFSC_A.setForeground(new java.awt.Color(54, 33, 89));
        IFSC_A.setText("> XXXXXXX");
        panelA.add(IFSC_A, new org.netbeans.lib.awtextra.AbsoluteConstraints(600, 2070, -1, 30));

        PAN_labelA.setBackground(new java.awt.Color(255, 255, 255));
        PAN_labelA.setFont(new java.awt.Font("Montserrat", 0, 24)); // NOI18N
        PAN_labelA.setForeground(new java.awt.Color(66, 50, 77));
        PAN_labelA.setText("PAN");
        panelA.add(PAN_labelA, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 1950, -1, 50));

        mail_labelA.setBackground(new java.awt.Color(255, 255, 255));
        mail_labelA.setFont(new java.awt.Font("Montserrat", 0, 24)); // NOI18N
        mail_labelA.setForeground(new java.awt.Color(66, 50, 77));
        mail_labelA.setText("E-mail");
        panelA.add(mail_labelA, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 2110, -1, 50));

        mail_A.setFont(new java.awt.Font("Roboto Bk", 0, 24)); // NOI18N
        mail_A.setForeground(new java.awt.Color(54, 33, 89));
        mail_A.setText("> XXXXXXX");
        panelA.add(mail_A, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 2150, -1, 30));

        ScrollPaneA.setViewportView(panelA);

        bg.add(ScrollPaneA, new org.netbeans.lib.awtextra.AbsoluteConstraints(340, 192, 910, 500));

        panelB.setBackground(new java.awt.Color(255, 255, 255));
        panelB.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        ScrollPaneB.setBackground(new java.awt.Color(255, 255, 255));
        ScrollPaneB.setVerticalScrollBarPolicy(javax.swing.ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);

        TableB.setFont(new java.awt.Font("Roboto", 0, 18)); // NOI18N
        TableB.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "S. No.", "Army No.", "Date of Departure", "Date of Ration", "Authority", "Duty Purpose"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false
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

        ScrollPaneC.setBorder(null);
        ScrollPaneC.setHorizontalScrollBarPolicy(javax.swing.ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);

        panelC.setBackground(new java.awt.Color(255, 255, 255));
        panelC.setFocusable(false);
        panelC.setMaximumSize(new java.awt.Dimension(950, 1310));
        panelC.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        service_number_labelC.setFont(new java.awt.Font("Montserrat", 0, 28)); // NOI18N
        service_number_labelC.setForeground(new java.awt.Color(51, 51, 51));
        service_number_labelC.setText("Army Number");
        panelC.add(service_number_labelC, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 70, -1, 40));

        name_labelC.setBackground(new java.awt.Color(255, 255, 255));
        name_labelC.setFont(new java.awt.Font("Montserrat", 0, 28)); // NOI18N
        name_labelC.setForeground(new java.awt.Color(51, 51, 51));
        name_labelC.setText("Name");
        panelC.add(name_labelC, new org.netbeans.lib.awtextra.AbsoluteConstraints(400, 70, -1, 40));

        army_numberC.setFont(new java.awt.Font("Roboto Bk", 0, 24)); // NOI18N
        army_numberC.setForeground(new java.awt.Color(54, 33, 89));
        army_numberC.setText("> XXXXXXX");
        panelC.add(army_numberC, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 100, -1, 40));

        rank_labelC.setBackground(new java.awt.Color(255, 255, 255));
        rank_labelC.setFont(new java.awt.Font("Montserrat", 0, 28)); // NOI18N
        rank_labelC.setForeground(new java.awt.Color(51, 51, 51));
        rank_labelC.setText("RANK");
        panelC.add(rank_labelC, new org.netbeans.lib.awtextra.AbsoluteConstraints(610, 70, -1, 40));

        rankC.setFont(new java.awt.Font("Roboto Bk", 0, 24)); // NOI18N
        rankC.setForeground(new java.awt.Color(54, 33, 89));
        rankC.setText("> XXXXXXX");
        panelC.add(rankC, new org.netbeans.lib.awtextra.AbsoluteConstraints(610, 100, -1, 40));

        nameC.setFont(new java.awt.Font("Roboto Bk", 0, 24)); // NOI18N
        nameC.setForeground(new java.awt.Color(54, 33, 89));
        nameC.setText("> XXXXXXX");
        panelC.add(nameC, new org.netbeans.lib.awtextra.AbsoluteConstraints(400, 100, -1, 40));

        midC4.setForeground(new java.awt.Color(204, 204, 255));
        midC4.setOrientation(javax.swing.SwingConstants.VERTICAL);
        panelC.add(midC4, new org.netbeans.lib.awtextra.AbsoluteConstraints(570, 85, 20, 40));

        changing_for_label.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        changing_for_label.setText("Changing For");
        panelC.add(changing_for_label, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 50, -1, 20));

        midC0.setForeground(new java.awt.Color(204, 204, 255));
        panelC.add(midC0, new org.netbeans.lib.awtextra.AbsoluteConstraints(260, 190, 390, 10));

        panelC_bottom_line.setForeground(new java.awt.Color(54, 33, 89));
        panelC.add(panelC_bottom_line, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 1040, 910, -1));

        to_unit_labelC.setFont(new java.awt.Font("Montserrat", 0, 28)); // NOI18N
        to_unit_labelC.setForeground(new java.awt.Color(51, 51, 51));
        to_unit_labelC.setText("To Unit");
        panelC.add(to_unit_labelC, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 230, -1, 40));

        to_unit_textfieldC.setFont(new java.awt.Font("Roboto", 0, 24)); // NOI18N
        to_unit_textfieldC.setForeground(new java.awt.Color(54, 33, 89));
        to_unit_textfieldC.setBorder(null);
        to_unit_textfieldC.setMargin(new java.awt.Insets(2, 4, 2, 2));
        panelC.add(to_unit_textfieldC, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 270, 320, 40));

        to_unit_underlineC.setForeground(new java.awt.Color(54, 33, 89));
        panelC.add(to_unit_underlineC, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 310, 320, 20));

        Offr_labelC.setFont(new java.awt.Font("Montserrat", 0, 28)); // NOI18N
        Offr_labelC.setForeground(new java.awt.Color(51, 51, 51));
        Offr_labelC.setText("Offr/ JCO/ OR ");
        panelC.add(Offr_labelC, new org.netbeans.lib.awtextra.AbsoluteConstraints(480, 340, -1, 40));

        Offr_textfieldC.setFont(new java.awt.Font("Roboto", 0, 24)); // NOI18N
        Offr_textfieldC.setForeground(new java.awt.Color(54, 33, 89));
        Offr_textfieldC.setBorder(null);
        Offr_textfieldC.setMargin(new java.awt.Insets(2, 4, 2, 2));
        panelC.add(Offr_textfieldC, new org.netbeans.lib.awtextra.AbsoluteConstraints(480, 380, 320, 40));

        Offr_underlineC.setForeground(new java.awt.Color(54, 33, 89));
        panelC.add(Offr_underlineC, new org.netbeans.lib.awtextra.AbsoluteConstraints(480, 420, 320, 20));

        DOD_labelC.setBackground(new java.awt.Color(255, 255, 255));
        DOD_labelC.setFont(new java.awt.Font("Montserrat", 0, 28)); // NOI18N
        DOD_labelC.setForeground(new java.awt.Color(51, 51, 51));
        DOD_labelC.setText("Departure Date ");
        panelC.add(DOD_labelC, new org.netbeans.lib.awtextra.AbsoluteConstraints(480, 230, -1, 40));

        DOD_date_comboboxC.setFont(new java.awt.Font("Roboto", 0, 18)); // NOI18N
        DOD_date_comboboxC.setForeground(new java.awt.Color(44, 62, 80));
        DOD_date_comboboxC.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "DD", "01", "02", "03", "04", "05", "06", "07", "08", "09", "10", "11", "12", "13", "14", "15", "16", "17", "18", "19", "20", "21", "22", "23", "24", "25", "26", "27", "28", "29", "30", "31" }));
        DOD_date_comboboxC.setBorder(null);
        panelC.add(DOD_date_comboboxC, new org.netbeans.lib.awtextra.AbsoluteConstraints(480, 270, 100, 40));

        DOD_month_comboboxC.setFont(new java.awt.Font("Roboto", 0, 18)); // NOI18N
        DOD_month_comboboxC.setForeground(new java.awt.Color(44, 62, 80));
        DOD_month_comboboxC.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "MM", "01", "02", "03", "04", "05", "06", "07", "08", "09", "10", "11", "12" }));
        DOD_month_comboboxC.setBorder(null);
        panelC.add(DOD_month_comboboxC, new org.netbeans.lib.awtextra.AbsoluteConstraints(590, 270, 100, 40));

        DOD_year_comboboxC.setFont(new java.awt.Font("Roboto", 0, 18)); // NOI18N
        DOD_year_comboboxC.setForeground(new java.awt.Color(44, 62, 80));
        DOD_year_comboboxC.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "YYYY", "1950", "1951", "1952", "1953", "1954", "1955", "1956", "1957", "1958", "1959", "1960", "1961", "1962", "1963", "1964", "1965", "1966", "1967", "1968", "1969", "1970", "1971", "1972", "1973", "1974", "1975", "1976", "1977", "1978", "1979", "1980", "1981", "1982", "1983", "1984", "1985", "1986", "1987", "1988", "1989", "1990", "1991", "1992", "1993", "1994", "1995", "1996", "1997", "1998", "1999", "2000", "2001", "2002", "2003", "2004", "2005", "2006", "2007", "2008", "2009", "2010", "2011", "2012", "2013", "2014", "2015", "2016", "2017", "2018", "2019", "2020", "2021", "2022", "2023", "2024", "2025", "2026", "2027", "2028", "2029", "2030", "2031", "2032", "2033", "2034", "2035", "2036", "2037", "2038", "2039", "2040", "2041", "2042", "2043", "2044", "2045", "2046", "2047", "2048", "2049", "2050", "2051", "2052", "2053", "2054", "2055", "2056", "2057", "2058", "2059", "2060", "2061", "2062", "2063", "2064", "2065", "2066", "2067", "2068", "2069", "2070", "2071", "2072", "2073", "2074", "2075", "2076", "2077", "2078", "2079", "2080", "2081", "2082", "2083", "2084", "2085", "2086", "2087", "2088", "2089", "2090", "2091", "2092", "2093", "2094", "2095", "2096", "2097", "2098", "2099", "2100", " " }));
        DOD_year_comboboxC.setBorder(null);
        panelC.add(DOD_year_comboboxC, new org.netbeans.lib.awtextra.AbsoluteConstraints(700, 270, 100, 40));

        SORS_labelC.setFont(new java.awt.Font("Montserrat", 0, 28)); // NOI18N
        SORS_labelC.setForeground(new java.awt.Color(51, 51, 51));
        SORS_labelC.setText("SORS ");
        panelC.add(SORS_labelC, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 450, -1, 40));

        SORS_underlineC.setForeground(new java.awt.Color(54, 33, 89));
        panelC.add(SORS_underlineC, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 530, 320, 20));

        SORS_textfieldC.setFont(new java.awt.Font("Roboto", 0, 24)); // NOI18N
        SORS_textfieldC.setForeground(new java.awt.Color(54, 33, 89));
        SORS_textfieldC.setBorder(null);
        SORS_textfieldC.setMargin(new java.awt.Insets(2, 4, 2, 2));
        panelC.add(SORS_textfieldC, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 490, 320, 40));

        rationed_labelC.setBackground(new java.awt.Color(255, 255, 255));
        rationed_labelC.setFont(new java.awt.Font("Montserrat", 0, 28)); // NOI18N
        rationed_labelC.setForeground(new java.awt.Color(51, 51, 51));
        rationed_labelC.setText("Rationed Up to ");
        panelC.add(rationed_labelC, new org.netbeans.lib.awtextra.AbsoluteConstraints(480, 560, -1, 40));

        rationed_date_comboboxC.setFont(new java.awt.Font("Roboto", 0, 18)); // NOI18N
        rationed_date_comboboxC.setForeground(new java.awt.Color(44, 62, 80));
        rationed_date_comboboxC.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "DD", "01", "02", "03", "04", "05", "06", "07", "08", "09", "10", "11", "12", "13", "14", "15", "16", "17", "18", "19", "20", "21", "22", "23", "24", "25", "26", "27", "28", "29", "30", "31" }));
        rationed_date_comboboxC.setBorder(null);
        panelC.add(rationed_date_comboboxC, new org.netbeans.lib.awtextra.AbsoluteConstraints(480, 600, 100, 40));

        rationed_month_comboboxC.setFont(new java.awt.Font("Roboto", 0, 18)); // NOI18N
        rationed_month_comboboxC.setForeground(new java.awt.Color(44, 62, 80));
        rationed_month_comboboxC.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "MM", "01", "02", "03", "04", "05", "06", "07", "08", "09", "10", "11", "12" }));
        rationed_month_comboboxC.setBorder(null);
        panelC.add(rationed_month_comboboxC, new org.netbeans.lib.awtextra.AbsoluteConstraints(590, 600, 100, 40));

        rationed_year_comboboxC.setFont(new java.awt.Font("Roboto", 0, 18)); // NOI18N
        rationed_year_comboboxC.setForeground(new java.awt.Color(44, 62, 80));
        rationed_year_comboboxC.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "YYYY", "1950", "1951", "1952", "1953", "1954", "1955", "1956", "1957", "1958", "1959", "1960", "1961", "1962", "1963", "1964", "1965", "1966", "1967", "1968", "1969", "1970", "1971", "1972", "1973", "1974", "1975", "1976", "1977", "1978", "1979", "1980", "1981", "1982", "1983", "1984", "1985", "1986", "1987", "1988", "1989", "1990", "1991", "1992", "1993", "1994", "1995", "1996", "1997", "1998", "1999", "2000", "2001", "2002", "2003", "2004", "2005", "2006", "2007", "2008", "2009", "2010", "2011", "2012", "2013", "2014", "2015", "2016", "2017", "2018", "2019", "2020", "2021", "2022", "2023", "2024", "2025", "2026", "2027", "2028", "2029", "2030", "2031", "2032", "2033", "2034", "2035", "2036", "2037", "2038", "2039", "2040", "2041", "2042", "2043", "2044", "2045", "2046", "2047", "2048", "2049", "2050", "2051", "2052", "2053", "2054", "2055", "2056", "2057", "2058", "2059", "2060", "2061", "2062", "2063", "2064", "2065", "2066", "2067", "2068", "2069", "2070", "2071", "2072", "2073", "2074", "2075", "2076", "2077", "2078", "2079", "2080", "2081", "2082", "2083", "2084", "2085", "2086", "2087", "2088", "2089", "2090", "2091", "2092", "2093", "2094", "2095", "2096", "2097", "2098", "2099", "2100", " " }));
        rationed_year_comboboxC.setBorder(null);
        panelC.add(rationed_year_comboboxC, new org.netbeans.lib.awtextra.AbsoluteConstraints(700, 600, 100, 40));

        authority_labelC.setFont(new java.awt.Font("Montserrat", 0, 28)); // NOI18N
        authority_labelC.setForeground(new java.awt.Color(51, 51, 51));
        authority_labelC.setText("Authority for Mov ");
        panelC.add(authority_labelC, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 560, -1, 40));

        authority_textfieldC.setFont(new java.awt.Font("Roboto", 0, 24)); // NOI18N
        authority_textfieldC.setForeground(new java.awt.Color(54, 33, 89));
        authority_textfieldC.setBorder(null);
        authority_textfieldC.setMargin(new java.awt.Insets(2, 4, 2, 2));
        panelC.add(authority_textfieldC, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 600, 320, 40));

        authority_underlineC.setForeground(new java.awt.Color(54, 33, 89));
        panelC.add(authority_underlineC, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 640, 320, 20));

        purpose_labelC.setFont(new java.awt.Font("Montserrat", 0, 28)); // NOI18N
        purpose_labelC.setForeground(new java.awt.Color(51, 51, 51));
        purpose_labelC.setText("Purpose of Duty");
        panelC.add(purpose_labelC, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 680, -1, 40));

        purpose_textfieldC.setFont(new java.awt.Font("Roboto", 0, 24)); // NOI18N
        purpose_textfieldC.setForeground(new java.awt.Color(54, 33, 89));
        purpose_textfieldC.setBorder(null);
        purpose_textfieldC.setMargin(new java.awt.Insets(2, 4, 2, 2));
        panelC.add(purpose_textfieldC, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 720, 320, 40));

        purpose_underlineC.setForeground(new java.awt.Color(54, 33, 89));
        panelC.add(purpose_underlineC, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 760, 320, 20));

        report_labelC.setFont(new java.awt.Font("Montserrat", 0, 28)); // NOI18N
        report_labelC.setForeground(new java.awt.Color(51, 51, 51));
        report_labelC.setText("Report To");
        panelC.add(report_labelC, new org.netbeans.lib.awtextra.AbsoluteConstraints(480, 680, 190, 40));

        report_textfieldC.setFont(new java.awt.Font("Roboto", 0, 24)); // NOI18N
        report_textfieldC.setForeground(new java.awt.Color(54, 33, 89));
        report_textfieldC.setBorder(null);
        report_textfieldC.setMargin(new java.awt.Insets(2, 4, 2, 2));
        panelC.add(report_textfieldC, new org.netbeans.lib.awtextra.AbsoluteConstraints(480, 720, 320, 40));

        report_underlineC.setForeground(new java.awt.Color(54, 33, 89));
        panelC.add(report_underlineC, new org.netbeans.lib.awtextra.AbsoluteConstraints(480, 760, 320, 20));

        FNAN_labelC.setFont(new java.awt.Font("Montserrat", 0, 28)); // NOI18N
        FNAN_labelC.setForeground(new java.awt.Color(51, 51, 51));
        FNAN_labelC.setText("FN/AN");
        panelC.add(FNAN_labelC, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 340, -1, 40));

        FNAN_textfieldC.setFont(new java.awt.Font("Roboto", 0, 24)); // NOI18N
        FNAN_textfieldC.setForeground(new java.awt.Color(54, 33, 89));
        FNAN_textfieldC.setBorder(null);
        FNAN_textfieldC.setMargin(new java.awt.Insets(2, 4, 2, 2));
        panelC.add(FNAN_textfieldC, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 380, 320, 40));

        FNAN_underlineC.setForeground(new java.awt.Color(54, 33, 89));
        panelC.add(FNAN_underlineC, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 420, 320, 20));

        SOS_labelC.setFont(new java.awt.Font("Montserrat", 0, 28)); // NOI18N
        SOS_labelC.setForeground(new java.awt.Color(51, 51, 51));
        SOS_labelC.setText("SOS");
        panelC.add(SOS_labelC, new org.netbeans.lib.awtextra.AbsoluteConstraints(480, 450, -1, 40));

        SOS_textfieldC.setFont(new java.awt.Font("Roboto", 0, 24)); // NOI18N
        SOS_textfieldC.setForeground(new java.awt.Color(54, 33, 89));
        SOS_textfieldC.setBorder(null);
        SOS_textfieldC.setMargin(new java.awt.Insets(2, 4, 2, 2));
        panelC.add(SOS_textfieldC, new org.netbeans.lib.awtextra.AbsoluteConstraints(480, 490, 320, 40));

        SOS_underlineC.setForeground(new java.awt.Color(54, 33, 89));
        panelC.add(SOS_underlineC, new org.netbeans.lib.awtextra.AbsoluteConstraints(480, 530, 320, 20));

        lve_gtd_labelC.setFont(new java.awt.Font("Montserrat", 0, 28)); // NOI18N
        lve_gtd_labelC.setForeground(new java.awt.Color(51, 51, 51));
        lve_gtd_labelC.setText("Lve Gtd ");
        panelC.add(lve_gtd_labelC, new org.netbeans.lib.awtextra.AbsoluteConstraints(480, 790, -1, 40));

        MR_textfieldC.setFont(new java.awt.Font("Roboto", 0, 24)); // NOI18N
        MR_textfieldC.setForeground(new java.awt.Color(54, 33, 89));
        MR_textfieldC.setBorder(null);
        MR_textfieldC.setMargin(new java.awt.Insets(2, 4, 2, 2));
        panelC.add(MR_textfieldC, new org.netbeans.lib.awtextra.AbsoluteConstraints(480, 830, 320, 40));

        MR_underlineC.setForeground(new java.awt.Color(54, 33, 89));
        panelC.add(MR_underlineC, new org.netbeans.lib.awtextra.AbsoluteConstraints(480, 870, 320, 20));

        CL_labelC.setFont(new java.awt.Font("Montserrat", 0, 28)); // NOI18N
        CL_labelC.setForeground(new java.awt.Color(51, 51, 51));
        CL_labelC.setText("CL Availed");
        panelC.add(CL_labelC, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 900, -1, 40));

        CL_textfieldC.setFont(new java.awt.Font("Roboto", 0, 24)); // NOI18N
        CL_textfieldC.setForeground(new java.awt.Color(54, 33, 89));
        CL_textfieldC.setBorder(null);
        CL_textfieldC.setMargin(new java.awt.Insets(2, 4, 2, 2));
        panelC.add(CL_textfieldC, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 940, 320, 40));

        CL_underlineC.setForeground(new java.awt.Color(54, 33, 89));
        panelC.add(CL_underlineC, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 980, 320, 20));

        type_labelC.setFont(new java.awt.Font("Montserrat", 0, 28)); // NOI18N
        type_labelC.setForeground(new java.awt.Color(51, 51, 51));
        type_labelC.setText("Type of Lve Gtd ");
        panelC.add(type_labelC, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 790, -1, 40));

        type_textfieldC.setFont(new java.awt.Font("Roboto", 0, 24)); // NOI18N
        type_textfieldC.setForeground(new java.awt.Color(54, 33, 89));
        type_textfieldC.setBorder(null);
        type_textfieldC.setMargin(new java.awt.Insets(2, 4, 2, 2));
        panelC.add(type_textfieldC, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 830, 320, 40));

        type_underlineC.setForeground(new java.awt.Color(54, 33, 89));
        panelC.add(type_underlineC, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 870, 320, 20));

        AL_labelC.setFont(new java.awt.Font("Montserrat", 0, 28)); // NOI18N
        AL_labelC.setForeground(new java.awt.Color(51, 51, 51));
        AL_labelC.setText("AL Availed");
        panelC.add(AL_labelC, new org.netbeans.lib.awtextra.AbsoluteConstraints(480, 900, -1, 40));

        AL_textfieldC.setFont(new java.awt.Font("Roboto", 0, 24)); // NOI18N
        AL_textfieldC.setForeground(new java.awt.Color(54, 33, 89));
        AL_textfieldC.setBorder(null);
        AL_textfieldC.setMargin(new java.awt.Insets(2, 4, 2, 2));
        panelC.add(AL_textfieldC, new org.netbeans.lib.awtextra.AbsoluteConstraints(480, 940, 320, 40));

        AL_underlineC.setForeground(new java.awt.Color(54, 33, 89));
        panelC.add(AL_underlineC, new org.netbeans.lib.awtextra.AbsoluteConstraints(480, 980, 320, 20));

        midC5.setForeground(new java.awt.Color(204, 204, 255));
        midC5.setOrientation(javax.swing.SwingConstants.VERTICAL);
        panelC.add(midC5, new org.netbeans.lib.awtextra.AbsoluteConstraints(335, 85, 20, 40));

        ScrollPaneC.setViewportView(panelC);

        bg.add(ScrollPaneC, new org.netbeans.lib.awtextra.AbsoluteConstraints(340, 192, 910, 480));

        print_buttonA.setForeground(new java.awt.Color(240, 240, 240));
        print_buttonA.setMaximumSize(new java.awt.Dimension(95, 25));
        print_buttonA.setMinimumSize(new java.awt.Dimension(95, 25));
        print_buttonA.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                print_buttonAMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                print_buttonAMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                print_buttonAMouseExited(evt);
            }
        });
        print_buttonA.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        print_label.setBackground(new java.awt.Color(54, 33, 89));
        print_label.setFont(new java.awt.Font("Roboto", 0, 14)); // NOI18N
        print_label.setForeground(new java.awt.Color(54, 33, 89));
        print_label.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        print_label.setText("PRINT");
        print_label.setFocusable(false);
        print_label.setRequestFocusEnabled(false);
        print_label.setVerifyInputWhenFocusTarget(false);
        print_buttonA.add(print_label, new org.netbeans.lib.awtextra.AbsoluteConstraints(52, 0, 71, 50));

        bg.add(print_buttonA, new org.netbeans.lib.awtextra.AbsoluteConstraints(1070, 120, 180, 50));

        insert_buttonA.setForeground(new java.awt.Color(240, 240, 240));
        insert_buttonA.setMaximumSize(new java.awt.Dimension(95, 25));
        insert_buttonA.setMinimumSize(new java.awt.Dimension(95, 25));
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
        insert_buttonA.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        insert_label.setFont(new java.awt.Font("Roboto", 0, 14)); // NOI18N
        insert_label.setForeground(new java.awt.Color(54, 33, 89));
        insert_label.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        insert_label.setText("INSERT");
        insert_buttonA.add(insert_label, new org.netbeans.lib.awtextra.AbsoluteConstraints(52, 13, 71, 24));

        bg.add(insert_buttonA, new org.netbeans.lib.awtextra.AbsoluteConstraints(870, 120, 180, 50));

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
        refresh_buttonB.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        refresh_labelB.setBackground(new java.awt.Color(54, 33, 89));
        refresh_labelB.setFont(new java.awt.Font("Roboto Bk", 0, 14)); // NOI18N
        refresh_labelB.setForeground(new java.awt.Color(54, 33, 89));
        refresh_labelB.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        refresh_labelB.setText("REFRESH");
        refresh_buttonB.add(refresh_labelB, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 0, 80, 50));

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
        print_buttonB.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        print_labelB.setBackground(new java.awt.Color(54, 33, 89));
        print_labelB.setFont(new java.awt.Font("Roboto Bk", 0, 14)); // NOI18N
        print_labelB.setForeground(new java.awt.Color(54, 33, 89));
        print_labelB.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        print_labelB.setText("PRINT");
        print_buttonB.add(print_labelB, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 0, 80, 50));

        bg.add(print_buttonB, new org.netbeans.lib.awtextra.AbsoluteConstraints(670, 120, 180, 50));

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
        fullscreen_buttonB.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        fullscreen_labelB.setBackground(new java.awt.Color(54, 33, 89));
        fullscreen_labelB.setFont(new java.awt.Font("Roboto Bk", 0, 14)); // NOI18N
        fullscreen_labelB.setForeground(new java.awt.Color(54, 33, 89));
        fullscreen_labelB.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        fullscreen_labelB.setText("FULLSCREEN");
        fullscreen_buttonB.add(fullscreen_labelB, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 0, 120, 50));

        bg.add(fullscreen_buttonB, new org.netbeans.lib.awtextra.AbsoluteConstraints(1070, 120, 180, -1));

        getContentPane().add(bg, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 1300, 740));

        fullscreen_panelB.setBackground(new java.awt.Color(255, 255, 255));
        fullscreen_panelB.setMaximumSize(new java.awt.Dimension(1280, 720));
        fullscreen_panelB.setMinimumSize(new java.awt.Dimension(1280, 720));
        fullscreen_panelB.setPreferredSize(new java.awt.Dimension(1280, 720));
        fullscreen_panelB.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        fullscreen_ScrollPaneB.setBackground(new java.awt.Color(255, 255, 255));
        fullscreen_ScrollPaneB.setHorizontalScrollBarPolicy(javax.swing.ScrollPaneConstants.HORIZONTAL_SCROLLBAR_ALWAYS);
        fullscreen_ScrollPaneB.setVerticalScrollBarPolicy(javax.swing.ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);

        fullscreen_tableB.setFont(new java.awt.Font("Roboto", 0, 18)); // NOI18N
        fullscreen_tableB.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "S. No.", "Army No.", "DOD", "RATION DATE", "TO UNIT", "FN/AN", "OFFER", "SORS", "SOS", "AUTH", "PURPOSE DUTY", "REPORT TO", "LVE TYPE", "GTD LVE", "CL", "AL"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false
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

    private void move_panelMouseDragged(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_move_panelMouseDragged
        this.setLocation(this.getX() + evt.getX() - mouseX, this.getY() + evt.getY() - mouseY);
    }//GEN-LAST:event_move_panelMouseDragged

    private void move_panelMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_move_panelMousePressed
        mouseX = evt.getX();
        mouseY = evt.getY();
    }//GEN-LAST:event_move_panelMousePressed

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
        if (back_button) {
            new Modules(0).setVisible(true);
            this.setVisible(false);

            back_button = false;
        }
    }//GEN-LAST:event_go_back_labelMouseClicked

    private void menuAMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_menuAMouseClicked
        menuA.setBackground(new java.awt.Color(85, 65, 118));
        menuB.setBackground(new java.awt.Color(64, 43, 100));

        print_buttonA.setVisible(true);
        insert_buttonA.setVisible(true);
        print_buttonB.setVisible(false);
        edit_buttonB.setVisible(false);
        refresh_buttonB.setVisible(false);
        fullscreen_buttonB.setVisible(false);

        ScrollPaneA.setVisible(true);
        panelB.setVisible(false);
        ScrollPaneC.setVisible(false);
        fullscreen_panelB.setVisible(false);
    }//GEN-LAST:event_menuAMouseClicked

    private void menuBMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_menuBMouseClicked
        menuB.setBackground(new java.awt.Color(85, 65, 118));
        menuA.setBackground(new java.awt.Color(64, 43, 100));
        view_table(null);
        print_buttonA.setVisible(false);
        insert_buttonA.setVisible(false);
        print_buttonB.setVisible(true);
        edit_buttonB.setVisible(true);
        refresh_buttonB.setVisible(true);
        fullscreen_buttonB.setVisible(true);
        update_buttonC.setVisible(false);

        ScrollPaneA.setVisible(false);
        panelB.setVisible(true);
        ScrollPaneC.setVisible(false);
        fullscreen_panelB.setVisible(false);
    }//GEN-LAST:event_menuBMouseClicked

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
                    scaleImage();
                    System.out.print("\nFound in attach_in");
                    rs = st.executeQuery(q1);
                    System.out.println("Inside iff");
                    while (rs.next()) {
                        nameA.setText(rs.getString("Name"));
                        rankA.setText(rs.getString("Rank_"));
                        companyA.setText(rs.getString("Company"));
                        unitA.setText(rs.getString("Unit"));
                        tradeA.setText(rs.getString("Trade"));
                        DOB_A.setText(rs.getString("DOB"));
                        NOK_relationA.setText(rs.getString("kin_relation"));
                        NOK_nameA.setText(rs.getString("kin_name"));
                        NOK_mobileA.setText(rs.getString("kin_contact"));
                        ICard_mobA.setText(rs.getString("indl_id"));
                        indl_mobA.setText(rs.getString("contact_no"));

                        PAN_A.setText(rs.getString("indl_pan"));
                        aadhar_A.setText(rs.getString("indl_aadhar"));
                        joint_AC_A.setText(rs.getString("indl_bank_acc"));
                        bank_nameA.setText(rs.getString("indl_bank_name"));
                        branchA.setText(rs.getString("indl_branch_name"));
                        IFSC_A.setText(rs.getString("indl_ifsc"));
                        mail_A.setText(rs.getString("indl_email"));

                        String c = rs.getString("Address");
                        String[] ad = c.split(",");
                        villA.setText(ad[0]);
                        PO_A.setText(ad[3]);
                        tehsilA.setText(ad[2]);
                        distA.setText(ad[1]);
                        PIN_A.setText(ad[5]);
                        stateA.setText(ad[4]);

                    }

                    // System.out.println(namee+" "+unitt+" "+tradee+" "+rankk+" "+companyy);
                }
                if (count2 != 0 || count3 != 0) {
                    if (count2 != 0) {
                        rs = st.executeQuery(q2);
                    } else {
                        rs = st.executeQuery(q3);
                    }
                    scaleImage();
                    System.out.println("Inside if");
                    System.out.print("\nFound in post or new");
                    while (rs.next()) {
                        nameA.setText(rs.getString("Name"));
                        rankA.setText(rs.getString("Rank_"));
                        companyA.setText(rs.getString("Company"));
                        if(count2==0){
                        DOB_A.setText(rs.getString("DOB"));
                        NOK_nameA.setText(rs.getString("kin_name"));
                        NOK_mobileA.setText(rs.getString("kin_contact"));
                        ICard_mobA.setText(rs.getString("indl_id"));
                        indl_mobA.setText(rs.getString("contact_no"));
                        joint_AC_A.setText(rs.getString("indl_bank_acc"));
                        bank_nameA.setText(rs.getString("indl_bank_name"));
                        branchA.setText(rs.getString("indl_branch_name"));
                        }
                        else{
                        DOB_A.setText(rs.getString("birth_date"));
                        NOK_nameA.setText(rs.getString("nok"));
                        NOK_mobileA.setText(rs.getString("kin_contact_no"));
                        ICard_mobA.setText(rs.getString("indl_icard_number"));
                        indl_mobA.setText(rs.getString("indl_contact_no"));
                        joint_AC_A.setText(rs.getString("indl_acc_no"));
                        bank_nameA.setText(rs.getString("indl_bank_name"));
                        branchA.setText(rs.getString("indl_bank_branch"));
                        }
                        NOK_relationA.setText(rs.getString("kin_relation"));

                        PAN_A.setText(rs.getString("indl_pan"));
                        aadhar_A.setText(rs.getString("indl_aadhar"));
                        IFSC_A.setText(rs.getString("indl_ifsc"));
                        mail_A.setText(rs.getString("indl_email"));

                        String c = rs.getString("Address");
                        String[] ad = c.split(",");
                        villA.setText(ad[0]);
                        PO_A.setText(ad[3]);
                        tehsilA.setText(ad[2]);
                        distA.setText(ad[1]);
                        PIN_A.setText(ad[5]);
                        stateA.setText(ad[4]);

                        tradeA.setText("> XXXXXXX");
                        unitA.setText("> XXXXXXX");
                    }
                }
                if (count == 0 && count2 == 0 && count3 == 0) {
                    JOptionPane.showMessageDialog(null, "Entered Army number can't be inserted");
                }
            } catch (Exception e) {
                System.out.print("\n" + e);
                JOptionPane.showMessageDialog(null, e);
            }
        }
    }//GEN-LAST:event_okayMouseClicked

    private void Photo_individualMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_Photo_individualMouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_Photo_individualMouseClicked

    private void Photo_individualMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_Photo_individualMouseEntered
        // TODO add your handling code here:
    }//GEN-LAST:event_Photo_individualMouseEntered

    private void Photo_individualMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_Photo_individualMouseExited
        // TODO add your handling code here:
    }//GEN-LAST:event_Photo_individualMouseExited

    private void okayBMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_okayBMouseClicked
        String sno = service_numberB.getText();
        try {
            Statement st = con.createStatement();
            ResultSet rs = null;
            String q = "select count(*) as count from mov_perm where Service_no='" + sno + "'";
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

    private void print_buttonAMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_print_buttonAMouseClicked
        title_label.grabFocus();
        PrintRecord();
    }//GEN-LAST:event_print_buttonAMouseClicked

    private void print_buttonAMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_print_buttonAMouseEntered
        print_buttonA.setBackground(new java.awt.Color(237, 224, 255));
    }//GEN-LAST:event_print_buttonAMouseEntered

    private void print_buttonAMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_print_buttonAMouseExited
        print_buttonA.setBackground(new java.awt.Color(240, 240, 240));
    }//GEN-LAST:event_print_buttonAMouseExited

    private void insert_buttonAMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_insert_buttonAMouseEntered
        insert_buttonA.setBackground(new java.awt.Color(237, 224, 255));
    }//GEN-LAST:event_insert_buttonAMouseEntered

    private void insert_buttonAMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_insert_buttonAMouseExited
        insert_buttonA.setBackground(new java.awt.Color(240, 240, 240));
    }//GEN-LAST:event_insert_buttonAMouseExited

    private void edit_buttonBMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_edit_buttonBMouseClicked

        try {
            String sno = service_numberB.getText();
            Statement st = con.createStatement();
            ResultSet rs = null;
            int row_ = TableB.getSelectedRow();
            if ((sno == null || sno.trim().isEmpty()) && row_ == -1) {
                JOptionPane.showMessageDialog(null, "Please enter Army Number");
            }
            if (row_ == -1 && sno != null && !sno.trim().isEmpty()) {
                JOptionPane.showMessageDialog(null, "Please select a row");
            } else if (row_ != -1) {
                ScrollPaneA.setVisible(false);
                panelB.setVisible(false);
                ScrollPaneC.setVisible(true);
                fullscreen_panelB.setVisible(false);
                sno = big.getValueAt(row_, 1).toString();
                String query1 = "select count(*) as count from attach_in where service_no='" + sno + "'";
                String query2 = "select count(*) as count from new_registration where service_no='" + sno + "'";
                String query3 = "select count(*) as count from post_in where service_no='" + sno + "'";
                String att = "select Name,rank_ from attach_in where service_no='" + sno + "'";
                String new_ = "select Name,rank_ from new_registration where service_no='" + sno + "'";
                String post_ = "select Name,rank_ from post_in where service_no='" + sno + "'";
                int count1 = 0, count2 = 0, count3 = 0;
                String name = "", rank = "";
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
                if (count1 != 0) {
                    rs = st.executeQuery(att);
                    while (rs.next()) {
                        name = rs.getString("Name");
                        rank = rs.getString("Rank_");
                    }
                }
                if (count2 != 0) {
                    rs = st.executeQuery(new_);
                    while (rs.next()) {
                        name = rs.getString("Name");
                        rank = rs.getString("Rank_");
                    }
                }
                if (count3 != 0) {
                    rs = st.executeQuery(post_);
                    while (rs.next()) {
                        name = rs.getString("Name");
                        rank = rs.getString("Rank_");
                    }
                }
                if (count1 == 0 && count2 == 0 && count3 == 0) {
                    JOptionPane.showMessageDialog(null, "Details not found");
                }

                if (count1 != 0 || count2 != 0 || count3 != 0) {

                    army_numberC.setText(sno);
                    nameC.setText(name);
                    rankC.setText(rank);
                    String lg = "select * from mov_perm where Service_no='" + sno + "'";
                    rs = st.executeQuery(lg);
                    while (rs.next()) {
                        to_unit_textfieldC.setText(rs.getString("to_unit"));
                        FNAN_textfieldC.setText(rs.getString("fn_an"));
                        Offr_textfieldC.setText(rs.getString("offr"));
                        SORS_textfieldC.setText(rs.getString("sors"));
                        SOS_textfieldC.setText(rs.getString("sos"));
                        authority_textfieldC.setText(rs.getString("auth"));
                        purpose_textfieldC.setText(rs.getString("purpose_duty"));
                        report_textfieldC.setText(rs.getString("report_to"));
                        type_textfieldC.setText(rs.getString("type_lve"));
                        MR_textfieldC.setText(rs.getString("gtd_lve"));
                        CL_textfieldC.setText(rs.getString("cl"));
                        AL_textfieldC.setText(rs.getString("al"));
                        String sp = rs.getString("DOD");
                        String[] d = sp.split("-");
                        DOD_date_comboboxC.setSelectedItem(d[0]);
                        DOD_month_comboboxC.setSelectedItem(d[1]);
                        DOD_year_comboboxC.setSelectedItem(d[2]);

                        sp = rs.getString("D_Rationed");
                        d = sp.split("-");
                        rationed_date_comboboxC.setSelectedItem(d[0]);
                        rationed_month_comboboxC.setSelectedItem(d[1]);
                        rationed_year_comboboxC.setSelectedItem(d[2]);

                        print_buttonA.setVisible(false);
                        insert_buttonA.setVisible(false);
                        print_buttonB.setVisible(false);
                        edit_buttonB.setVisible(false);
                        refresh_buttonB.setVisible(false);
                        fullscreen_buttonB.setVisible(false);
                        update_buttonC.setVisible(true);
                    }
                }
            }
        } catch (Exception e) {
            System.out.print("\n" + e);
            JOptionPane.showMessageDialog(null, "Error");
        }
    }//GEN-LAST:event_edit_buttonBMouseClicked

    private void edit_buttonBMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_edit_buttonBMouseEntered
        edit_buttonB.setBackground(new java.awt.Color(237, 224, 255));
    }//GEN-LAST:event_edit_buttonBMouseEntered

    private void edit_buttonBMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_edit_buttonBMouseExited
        edit_buttonB.setBackground(new java.awt.Color(240, 240, 240));
    }//GEN-LAST:event_edit_buttonBMouseExited

    private void refresh_buttonBMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_refresh_buttonBMouseClicked
        view_table(null);
    }//GEN-LAST:event_refresh_buttonBMouseClicked

    private void refresh_buttonBMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_refresh_buttonBMouseEntered
        refresh_buttonB.setBackground(new java.awt.Color(237, 224, 255));
    }//GEN-LAST:event_refresh_buttonBMouseEntered

    private void refresh_buttonBMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_refresh_buttonBMouseExited
        refresh_buttonB.setBackground(new java.awt.Color(240, 240, 240));
    }//GEN-LAST:event_refresh_buttonBMouseExited

    private void print_buttonBMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_print_buttonBMouseClicked
        try {
            path_file paths=new path_file();
            PrintWriter pw=new PrintWriter(new File((paths.csv + "\\mov_perm.csv")));
            StringBuilder sb = new StringBuilder();
            String query = "Select * from mov_perm";

            sb.append("Service_no      \t");
            sb.append(",");
            sb.append("Date of Departure            \t");
            sb.append(",");
            sb.append("Ration Date            \t");
            sb.append(",");
            sb.append("To Unit             \t");
            sb.append(",");
            sb.append("fN/AN          \t");
            sb.append(",");
            sb.append("OFFR         \t");
            sb.append(",");
            sb.append("SORS     \t");
            sb.append(",");
            sb.append("SOS        \t");
            sb.append(",");
            sb.append("Authority     \t");
            sb.append(",");
            sb.append("Duty Purpose          \t");
            sb.append(",");
            sb.append("Report TO   \t");
            sb.append(",");
            sb.append("Leave Type          \t");
            sb.append(",");
            sb.append("GTD Type          \t");
            sb.append(",");
            sb.append("CL         \t");
            sb.append(",");
            sb.append("AL          \t");
            sb.append("\r\n");
            ResultSet rs = null;
            Statement st = con.createStatement();
            rs = st.executeQuery(query);

            while (rs.next()) {
                sb.append(rs.getString("Service_no"));
                sb.append(",");
                sb.append(rs.getString("DOD"));
                sb.append(",");
                sb.append(rs.getString("D_Rationed"));
                sb.append(",");
                sb.append(rs.getString("to_unit"));
                sb.append(",");
                sb.append(rs.getString("fn_an"));
                sb.append(",");
                sb.append(rs.getString("offr"));
                sb.append(",");
                sb.append(rs.getString("sors"));
                sb.append(",");
                sb.append(rs.getString("sos"));
                sb.append(",");
                sb.append(rs.getString("auth"));
                sb.append(",");
                sb.append(rs.getString("purpose_duty"));
                sb.append(",");
                sb.append(rs.getString("report_to"));
                sb.append(",");
                sb.append(rs.getString("type_lve"));
                sb.append(",");
                sb.append(rs.getString("gtd_lve"));
                sb.append(",");
                sb.append(rs.getString("cl"));
                sb.append(",");
                sb.append(rs.getString("al"));
                sb.append("\r\n");
            }
            pw.write(sb.toString());
            pw.close();
            JOptionPane.showMessageDialog(null, "Successfully Converted to CSV");
            
            Desktop.getDesktop().open(new File(paths.csv + "/mov_perm.csv"));
        } catch (Exception e) {
            System.out.print("\n" + e);
            String er = e.getMessage();
            if (er.contains("The process cannot access the file because it is being used by another process")) {
                JOptionPane.showMessageDialog(null, "Please close the CSV file first!");
            }
        }
    }//GEN-LAST:event_print_buttonBMouseClicked

    private void print_buttonBMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_print_buttonBMouseEntered
        print_buttonB.setBackground(new java.awt.Color(237, 224, 255));
    }//GEN-LAST:event_print_buttonBMouseEntered

    private void print_buttonBMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_print_buttonBMouseExited
        print_buttonB.setBackground(new java.awt.Color(240, 240, 240));
    }//GEN-LAST:event_print_buttonBMouseExited

    private void update_buttonCMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_update_buttonCMouseEntered
        update_buttonC.setBackground(new java.awt.Color(237, 224, 255));
    }//GEN-LAST:event_update_buttonCMouseEntered

    private void update_buttonCMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_update_buttonCMouseExited
        update_buttonC.setBackground(new java.awt.Color(240, 240, 240));
    }//GEN-LAST:event_update_buttonCMouseExited

    private void fullscreen_buttonBMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_fullscreen_buttonBMouseClicked
        fullscreen_panelB.setVisible(true);
        bg.setVisible(false);
    }//GEN-LAST:event_fullscreen_buttonBMouseClicked

    private void fullscreen_buttonBMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_fullscreen_buttonBMouseEntered
        fullscreen_buttonB.setBackground(new java.awt.Color(237, 224, 255));
    }//GEN-LAST:event_fullscreen_buttonBMouseEntered

    private void fullscreen_buttonBMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_fullscreen_buttonBMouseExited
        fullscreen_buttonB.setBackground(new java.awt.Color(240, 240, 240));
    }//GEN-LAST:event_fullscreen_buttonBMouseExited

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

    private void formWindowOpened(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowOpened
        //for a centered frame
        Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
        this.setLocation(dim.width / 2 - this.getSize().width / 2, dim.height / 2 - this.getSize().height / 2);
        //for a centered frame
        menuA.setBackground(new java.awt.Color(85, 65, 118));
        menuB.setBackground(new java.awt.Color(64, 43, 100));

        print_buttonA.setVisible(true);
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

    private void insert_buttonAMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_insert_buttonAMouseClicked
        // TODO add your handling code here:
        String sno = service_number_textfield.getText();
        String query1 = "Select count(*) as count from attach_in where service_no = '" + sno + "'";
        String query2 = "Select count(*) as count from new_registration where service_no = '" + sno + "'";
        String query3 = "Select count(*) as count from post_in where service_no = '" + sno + "'";
        int count1 = 0;
        int count2 = 0;
        int count3 = 0;
        try {
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
        } catch (Exception e) {
        }

        if (sno == null || sno.trim().isEmpty()) {
            JOptionPane.showMessageDialog(null, "Pleae Enter Army Number");
        } else if (count1 == 0 && count2 == 0 && count3 == 0 && sno != null && !sno.trim().isEmpty()) {
            JOptionPane.showMessageDialog(null, "Army Number does not exist");
        } else {
            try {

                PreparedStatement stmt = null;
                String query = "insert into mov_perm " + "values(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";

                //Ration Date
                String dor_year = (String) rationed_year_comboboxA.getSelectedItem();
                String dor_month = (String) rationed_month_comboboxA.getSelectedItem();
                String dor_date = (String) rationed_date_comboboxA.getSelectedItem();
                String date_of_ration = dor_date + "-" + dor_month + "-" + dor_year;
                int y = Integer.parseInt(dor_year);
                int m = Integer.parseInt(dor_month);
                int d = Integer.parseInt(dor_date);

                //date of departure
                String dod_year = (String) DOD_year_comboboxA.getSelectedItem();
                String dod_month = (String) DOD_month_comboboxA.getSelectedItem();
                String dod_date = (String) DOD_date_comboboxA.getSelectedItem();
                int dy = Integer.parseInt(dod_year);
                int dm = Integer.parseInt(dod_month);
                int dd = Integer.parseInt(dod_date);
                String date_of_departure = dod_date + "-" + dod_month + "-" + dod_year;

                String to = to_unit_textfieldA.getText();
                String fn = FNAN_textfieldA.getText();
                String offer = Offr_textfieldA.getText();
                String sors = SORS_textfieldA.getText();
                String sos = SOS_textfieldA.getText();
                String auth = authority_textfieldA.getText();
                String purp = purpose_textfieldA.getText();
                String rept = report_textfieldA.getText();
                String type_lve = type_textfieldA.getText();
                String gtd_lve = MR_textfieldA2.getText();
                String cl = CL_textfieldA.getText();
                String al = AL_textfieldA.getText();
                String err = "";
                boolean check_all_tf = (to != null && !to.trim().isEmpty()) && (fn != null && !fn.trim().isEmpty()) && (offer != null && !offer.trim().isEmpty()) && (sors != null && !sors.trim().isEmpty()) && (sos != null && !sos.trim().isEmpty()) && (auth != null && !auth.trim().isEmpty()) && (purp != null && !purp.trim().isEmpty()) && (rept != null && !rept.trim().isEmpty()) && (type_lve != null && !type_lve.trim().isEmpty()) && (gtd_lve != null && !gtd_lve.trim().isEmpty()) && (cl != null && !cl.trim().isEmpty()) && (al != null && !al.trim().isEmpty());
                boolean check_all_dd1 = !((y % 4 == 0 && m == 2 && (d > 29)) || ((m == 2 || m == 4 || m == 6 || m == 9 || m == 11) && d == 31) || (m == 2 && d > 28 && y % 4 != 0)) && !((dy % 4 == 0 && dm == 2 && (dd > 29)) || ((dm == 2 || dm == 4 || dm == 6 || dm == 9 || dm == 11) && dd == 31) || (dm == 2 && dd > 28 && dy % 4 != 0));
                boolean check_all_dd2 = (dod_year.matches("[0-9]+") && dod_month.matches("[0-9]+") && dod_date.matches("[0-9]+")) && (dor_year.matches("[0-9]+") && dor_month.matches("[0-9]+") && dor_date.matches("[0-9]+"));
                if (check_all_tf && check_all_dd1 && check_all_dd2) {
                    System.out.print("\n Inserton if of mov_perm");
                    stmt = con.prepareStatement(query);
                    stmt.setString(1, sno);
                    stmt.setString(2, date_of_departure);
                    stmt.setString(3, date_of_ration);
                    stmt.setString(4, to);
                    stmt.setString(5, fn);
                    stmt.setString(6, offer);
                    stmt.setString(7, sors);
                    stmt.setString(8, sos);
                    stmt.setString(9, auth);
                    stmt.setString(10, purp);
                    stmt.setString(11, rept);
                    stmt.setString(12, type_lve);
                    stmt.setString(13, gtd_lve);
                    stmt.setString(14, cl);
                    stmt.setString(15, al);
                    stmt.execute();
                    JOptionPane.showMessageDialog(null, "Data inserted successfully");
                    to_unit_textfieldA.setText("");
                    FNAN_textfieldA.setText("");
                    Offr_textfieldA.setText("");
                    SORS_textfieldA.setText("");
                    SOS_textfieldA.setText("");
                    authority_textfieldA.setText("");
                    purpose_textfieldA.setText("");
                    report_textfieldA.setText("");
                    type_textfieldA.setText("");
                    MR_textfieldA2.setText("");
                    CL_textfieldA.setText("");
                    AL_textfieldA.setText("");

                    service_number_textfield.setText("");
                    //date of departure
                    DOD_year_comboboxA.setSelectedIndex(0);
                    DOD_month_comboboxA.setSelectedIndex(0);
                    DOD_date_comboboxA.setSelectedIndex(0);

                    //date of ration
                    rationed_year_comboboxA.setSelectedIndex(0);
                    rationed_month_comboboxA.setSelectedIndex(0);
                    rationed_date_comboboxA.setSelectedIndex(0);

                    nameA.setText("> XXXXXXX");
                    rankA.setText("> XXXXXXX");
                    companyA.setText("> XXXXXXX");
                    unitA.setText("> XXXXXXX");
                    tradeA.setText("> XXXXXXX");
                    DOB_A.setText("> XXXXXXX");
                    NOK_relationA.setText("> XXXXXXX");
                    NOK_nameA.setText("> XXXXXXX");
                    NOK_mobileA.setText("> XXXXXXX");
                    villA.setText("> XXXXXXX");
                    PO_A.setText("> XXXXXXX");
                    tehsilA.setText("> XXXXXXX");
                    distA.setText("> XXXXXXX");
                    PIN_A.setText("> XXXXXXX");
                    stateA.setText("> XXXXXXX");
                    PAN_A.setText("> XXXXXXX");
                    aadhar_A.setText("> XXXXXXX");
                    joint_AC_A.setText("> XXXXXXX");
                    bank_nameA.setText("> XXXXXXX");
                    branchA.setText("> XXXXXXX");
                    IFSC_A.setText("> XXXXXXX");
                    mail_A.setText("> XXXXXXX");
                    

                } else {
                    if (to == null || to.trim().isEmpty()) {
                        err = err + "\nTo Unit Field Empty";
                    }
                    if (fn == null || fn.trim().isEmpty()) {
                        err = err + "\nFN/AN Field Empty";
                    }
                    if (offer == null || offer.trim().isEmpty()) {
                        err = err + "\nOFFR/JCO/OR Field Empty";
                    }
                    if (sors == null || sors.trim().isEmpty()) {
                        err = err + "\nSORS Field Empty";
                    }
                    if (sos == null || sos.trim().isEmpty()) {
                        err = err + "\nSOS Field Empty";
                    }
                    if (auth == null || auth.trim().isEmpty()) {
                        err = err + "\nAuthprity for MOV Field Empty";
                    }
                    if (purp == null || purp.trim().isEmpty()) {
                        err = err + "\nPurpose Field Empty";
                    }
                    if (rept == null || rept.trim().isEmpty()) {
                        err = err + "\nReport TO Field Empty";
                    }
                    if (type_lve == null || type_lve.trim().isEmpty()) {
                        err = err + "\nType of lve Field Empty";
                    }
                    if (gtd_lve == null || gtd_lve.trim().isEmpty()) {
                        err = err + "\nLVE GTD Field Empty";
                    }
                    if (cl == null || cl.trim().isEmpty()) {
                        err = err + "\nCL Availed Field Empty";
                    }
                    if (al == null || al.trim().isEmpty()) {
                        err = err + "\nAL Availed Field Empty";
                    }
                    if (((y % 4 == 0 && m == 2 && (d > 29)) || ((m == 2 || m == 4 || m == 6 || m == 9 || m == 11) && d == 31) || (m == 2 && d > 28 && y % 4 != 0))) {
                        err = err + "\nInvalid Date of Ration";
                    }
                    if (((dy % 4 == 0 && dm == 2 && (dd > 29)) || ((dm == 2 || dm == 4 || dm == 6 || dm == 9 || dm == 11) && dd == 31) || (dm == 2 && dd > 28 && dy % 4 != 0))) {
                        err = err + "\nInvalid Date of Departure";
                    }

                    if (!err.trim().isEmpty()) {
                        JOptionPane.showMessageDialog(null, err);
                    }
                }
            } catch (Exception e) {
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

    private void update_buttonCMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_update_buttonCMouseClicked
        // TODO add your handling code here:
        String sno = army_numberC.getText();
        try {
            Statement st = con.createStatement();
            ResultSet rs = null;
            PreparedStatement stmt = null;
            String query = "update mov_perm set DOD=?,D_Rationed=?,to_unit=?,fn_an=?,offr=?,sors=?,sos=?,auth=?,purpose_duty=?,report_to=?,type_lve=?,gtd_lve=?,cl=?,al=? where Service_no='" + sno + "'";

            //Ration Date
            String dor_year = (String) rationed_year_comboboxC.getSelectedItem();
            String dor_month = (String) rationed_month_comboboxC.getSelectedItem();
            String dor_date = (String) rationed_date_comboboxC.getSelectedItem();
            String date_of_ration = dor_date + "-" + dor_month + "-" + dor_year;
            int y = Integer.parseInt(dor_year);
            int m = Integer.parseInt(dor_month);
            int d = Integer.parseInt(dor_date);

            //date of departure
            String dod_year = (String) DOD_year_comboboxC.getSelectedItem();
            String dod_month = (String) DOD_month_comboboxC.getSelectedItem();
            String dod_date = (String) DOD_date_comboboxC.getSelectedItem();
            int dy = Integer.parseInt(dod_year);
            int dm = Integer.parseInt(dod_month);
            int dd = Integer.parseInt(dod_date);
            String date_of_departure = dod_date + "-" + dod_month + "-" + dod_year;

            String to = to_unit_textfieldC.getText();
            String fn = FNAN_textfieldC.getText();
            String offer = Offr_textfieldC.getText();
            String sors = SORS_textfieldC.getText();
            String sos = SOS_textfieldC.getText();
            String auth = authority_textfieldC.getText();
            String purp = purpose_textfieldC.getText();
            String rept = report_textfieldC.getText();
            String type_lve = type_textfieldC.getText();
            String gtd_lve = MR_textfieldC.getText();
            String cl = CL_textfieldC.getText();
            String al = AL_textfieldC.getText();
            String err = "";
            boolean check_all_tf = (to != null && !to.trim().isEmpty()) && (fn != null && !fn.trim().isEmpty()) && (offer != null && !offer.trim().isEmpty()) && (sors != null && !sors.trim().isEmpty()) && (sos != null && !sos.trim().isEmpty()) && (auth != null && !auth.trim().isEmpty()) && (purp != null && !purp.trim().isEmpty()) && (rept != null && !rept.trim().isEmpty()) && (type_lve != null && !type_lve.trim().isEmpty()) && (gtd_lve != null && !gtd_lve.trim().isEmpty()) && (cl != null && !cl.trim().isEmpty()) && (al != null && !al.trim().isEmpty());
            boolean check_all_dd1 = !((y % 4 == 0 && m == 2 && (d > 29)) || ((m == 2 || m == 4 || m == 6 || m == 9 || m == 11) && d == 31) || (m == 2 && d > 28 && y % 4 != 0)) && !((dy % 4 == 0 && dm == 2 && (dd > 29)) || ((dm == 2 || dm == 4 || dm == 6 || dm == 9 || dm == 11) && dd == 31) || (dm == 2 && dd > 28 && dy % 4 != 0));
            boolean check_all_dd2 = (dod_year.matches("[0-9]+") && dod_month.matches("[0-9]+") && dod_date.matches("[0-9]+")) && (dor_year.matches("[0-9]+") && dor_month.matches("[0-9]+") && dor_date.matches("[0-9]+"));
            if (check_all_tf && check_all_dd1 && check_all_dd2) {
                System.out.print("\n Updation if of mov_perm");
                stmt = con.prepareStatement(query);
                stmt.setString(1, date_of_departure);
                stmt.setString(2, date_of_ration);
                stmt.setString(3, to);
                stmt.setString(4, fn);
                stmt.setString(5, offer);
                stmt.setString(6, sors);
                stmt.setString(7, sos);
                stmt.setString(8, auth);
                stmt.setString(9, purp);
                stmt.setString(10, rept);
                stmt.setString(11, type_lve);
                stmt.setString(12, gtd_lve);
                stmt.setString(13, cl);
                stmt.setString(14, al);
                stmt.execute();
                JOptionPane.showMessageDialog(null, "Data Updated successfully");
                to_unit_textfieldC.setText("");
                FNAN_textfieldC.setText("");
                Offr_textfieldC.setText("");
                SORS_textfieldC.setText("");
                SOS_textfieldC.setText("");
                authority_textfieldC.setText("");
                purpose_textfieldC.setText("");
                report_textfieldC.setText("");
                type_textfieldC.setText("");
                MR_textfieldC.setText("");
                CL_textfieldC.setText("");
                AL_textfieldC.setText("");

                //date of departure
                DOD_year_comboboxC.setSelectedIndex(0);
                DOD_month_comboboxC.setSelectedIndex(0);
                DOD_date_comboboxC.setSelectedIndex(0);

                //date of ration
                rationed_year_comboboxC.setSelectedIndex(0);
                rationed_month_comboboxC.setSelectedIndex(0);
                rationed_date_comboboxC.setSelectedIndex(0);

                nameC.setText("> XXXXXXX");
                rankC.setText("> XXXXXXX");
                army_numberC.setText("> XXXXXXX");
                menuB.setBackground(new java.awt.Color(85, 65, 118));
                menuA.setBackground(new java.awt.Color(64, 43, 100));
                view_table(null);
                print_buttonA.setVisible(false);
                insert_buttonA.setVisible(false);
                print_buttonB.setVisible(true);
                edit_buttonB.setVisible(true);
                refresh_buttonB.setVisible(true);
                fullscreen_buttonB.setVisible(true);
                update_buttonC.setVisible(false);

                ScrollPaneA.setVisible(false);
                panelB.setVisible(true);
                ScrollPaneC.setVisible(false);
                fullscreen_panelB.setVisible(false);

            } else {
                if (to == null || to.trim().isEmpty()) {
                    err = err + "\nTo Unit Field Empty";
                }
                if (fn == null || fn.trim().isEmpty()) {
                    err = err + "\nFN/AN Field Empty";
                }
                if (offer == null || offer.trim().isEmpty()) {
                    err = err + "\nOFFR/JCO/OR Field Empty";
                }
                if (sors == null || sors.trim().isEmpty()) {
                    err = err + "\nSORS Field Empty";
                }
                if (sos == null || sos.trim().isEmpty()) {
                    err = err + "\nSOS Field Empty";
                }
                if (auth == null || auth.trim().isEmpty()) {
                    err = err + "\nAuthprity for MOV Field Empty";
                }
                if (purp == null || purp.trim().isEmpty()) {
                    err = err + "\nPurpose Field Empty";
                }
                if (rept == null || rept.trim().isEmpty()) {
                    err = err + "\nReport TO Field Empty";
                }
                if (type_lve == null || type_lve.trim().isEmpty()) {
                    err = err + "\nType of lve Field Empty";
                }
                if (gtd_lve == null || gtd_lve.trim().isEmpty()) {
                    err = err + "\nLVE GTD Field Empty";
                }
                if (cl == null || cl.trim().isEmpty()) {
                    err = err + "\nCL Availed Field Empty";
                }
                if (al == null || al.trim().isEmpty()) {
                    err = err + "\nAL Availed Field Empty";
                }
                if (((y % 4 == 0 && m == 2 && (d > 29)) || ((m == 2 || m == 4 || m == 6 || m == 9 || m == 11) && d == 31) || (m == 2 && d > 28 && y % 4 != 0))) {
                    err = err + "\nInvalid Date of Ration";
                }
                if (((dy % 4 == 0 && dm == 2 && (dd > 29)) || ((dm == 2 || dm == 4 || dm == 6 || dm == 9 || dm == 11) && dd == 31) || (dm == 2 && dd > 28 && dy % 4 != 0))) {
                    err = err + "\nInvalid Date of Departure";
                }

                if (!err.trim().isEmpty()) {
                    JOptionPane.showMessageDialog(null, err);
                }
            }
        } catch (Exception e) {
            System.out.print("\n" + e);
            String exe = e.getMessage();
            if (exe.contains("Duplicate entry")) {
                JOptionPane.showMessageDialog(null, "Data not inserted!\nEntered ARMY NUMBER exists!");
            } else if (exe.contains("For input")) {
                JOptionPane.showMessageDialog(null, "\nPlease Re-Check Date Formats");
            } else {
                JOptionPane.showMessageDialog(null, "Data not Updated!");
            }
        }
    }//GEN-LAST:event_update_buttonCMouseClicked

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
            java.util.logging.Logger.getLogger(Mov_Order_Perm.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Mov_Order_Perm.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Mov_Order_Perm.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Mov_Order_Perm.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Mov_Order_Perm().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel AL_labelA;
    private javax.swing.JLabel AL_labelC;
    private javax.swing.JTextField AL_textfieldA;
    private javax.swing.JTextField AL_textfieldC;
    private javax.swing.JSeparator AL_underlineA;
    private javax.swing.JSeparator AL_underlineC;
    private javax.swing.JLabel CL_labelA;
    private javax.swing.JLabel CL_labelC;
    private javax.swing.JTextField CL_textfieldA;
    private javax.swing.JTextField CL_textfieldC;
    private javax.swing.JSeparator CL_underlineA;
    private javax.swing.JSeparator CL_underlineC;
    private javax.swing.JLabel CO_mobA;
    private javax.swing.JLabel CO_mob_labelA;
    private javax.swing.JLabel DOB_A;
    private javax.swing.JLabel DOB_labelA;
    private javax.swing.JComboBox<String> DOD_date_comboboxA;
    private javax.swing.JComboBox<String> DOD_date_comboboxC;
    private javax.swing.JLabel DOD_labelA;
    private javax.swing.JLabel DOD_labelC;
    private javax.swing.JComboBox<String> DOD_month_comboboxA;
    private javax.swing.JComboBox<String> DOD_month_comboboxC;
    private javax.swing.JComboBox<String> DOD_year_comboboxA;
    private javax.swing.JComboBox<String> DOD_year_comboboxC;
    private javax.swing.JLabel FNAN_labelA;
    private javax.swing.JLabel FNAN_labelC;
    private javax.swing.JTextField FNAN_textfieldA;
    private javax.swing.JTextField FNAN_textfieldC;
    private javax.swing.JSeparator FNAN_underlineA;
    private javax.swing.JSeparator FNAN_underlineC;
    private javax.swing.JLabel ICard_labelA;
    private javax.swing.JLabel ICard_mobA;
    private javax.swing.JLabel IFSC_A;
    private javax.swing.JLabel IFSC_labelA;
    private javax.swing.JTextField MR_textfieldA2;
    private javax.swing.JTextField MR_textfieldC;
    private javax.swing.JSeparator MR_underlineA2;
    private javax.swing.JSeparator MR_underlineC;
    private javax.swing.JLabel NOK_mobileA;
    private javax.swing.JLabel NOK_mobile_labelA;
    private javax.swing.JLabel NOK_nameA;
    private javax.swing.JLabel NOK_name_labelA;
    private javax.swing.JLabel NOK_relationA;
    private javax.swing.JLabel NOK_relation_labelA;
    private javax.swing.JLabel Offr_labelA;
    private javax.swing.JLabel Offr_labelC;
    private javax.swing.JTextField Offr_textfieldA;
    private javax.swing.JTextField Offr_textfieldC;
    private javax.swing.JSeparator Offr_underlineA;
    private javax.swing.JSeparator Offr_underlineC;
    private javax.swing.JLabel PAN_A;
    private javax.swing.JLabel PAN_labelA;
    private javax.swing.JLabel PIN_A;
    private javax.swing.JLabel PIN_labelA;
    private javax.swing.JLabel PO_A;
    private javax.swing.JLabel PO_labelA;
    private javax.swing.JPanel Photo_individual;
    private javax.swing.JLabel SORS_labelA;
    private javax.swing.JLabel SORS_labelC;
    private javax.swing.JTextField SORS_textfieldA;
    private javax.swing.JTextField SORS_textfieldC;
    private javax.swing.JSeparator SORS_underlineA;
    private javax.swing.JSeparator SORS_underlineC;
    private javax.swing.JLabel SOS_labelA;
    private javax.swing.JLabel SOS_labelC;
    private javax.swing.JTextField SOS_textfieldA;
    private javax.swing.JTextField SOS_textfieldC;
    private javax.swing.JSeparator SOS_underlineA;
    private javax.swing.JSeparator SOS_underlineC;
    private javax.swing.JScrollPane ScrollPaneA;
    private javax.swing.JScrollPane ScrollPaneB;
    private javax.swing.JScrollPane ScrollPaneC;
    private javax.swing.JSeparator Separator;
    private javax.swing.JTable TableB;
    private javax.swing.JLabel aadhar_A;
    private javax.swing.JLabel aadhar_labelA;
    private javax.swing.JLabel address_labelA;
    private javax.swing.JLabel adjt_labelA;
    private javax.swing.JLabel adjt_mobA;
    private javax.swing.JLabel army_numberC;
    private javax.swing.JLabel authority_labelA;
    private javax.swing.JLabel authority_labelC;
    private javax.swing.JTextField authority_textfieldA;
    private javax.swing.JTextField authority_textfieldC;
    private javax.swing.JSeparator authority_underlineA;
    private javax.swing.JSeparator authority_underlineC;
    private javax.swing.JLabel bank_nameA;
    private javax.swing.JLabel bank_name_labelA;
    private javax.swing.JPanel bg;
    private javax.swing.JLabel branchA;
    private javax.swing.JLabel branch_label_A;
    private javax.swing.JLabel changing_for_label;
    private javax.swing.JLabel companyA;
    private javax.swing.JLabel company_labelA;
    private javax.swing.JLabel contact_labelA;
    private javax.swing.JLabel distA;
    private javax.swing.JLabel dist_labelA;
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
    private javax.swing.JLabel indl_mobA;
    private javax.swing.JLabel indl_mob_labelA;
    private javax.swing.JPanel insert_buttonA;
    private javax.swing.JLabel insert_label;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JLabel joint_AC_A;
    private javax.swing.JLabel joint_AC_labelA;
    private javax.swing.JLabel lve_gtd_labelA;
    private javax.swing.JLabel lve_gtd_labelC;
    private javax.swing.JLabel mail_A;
    private javax.swing.JLabel mail_labelA;
    private javax.swing.JPanel menuA;
    private javax.swing.JLabel menuA_icon;
    private javax.swing.JLabel menuA_label;
    private javax.swing.JPanel menuB;
    private javax.swing.JLabel menuB_icon;
    private javax.swing.JLabel menuB_label;
    private javax.swing.JSeparator midA1;
    private javax.swing.JSeparator midA2;
    private javax.swing.JSeparator midA3;
    private javax.swing.JSeparator midA4;
    private javax.swing.JSeparator midC0;
    private javax.swing.JSeparator midC4;
    private javax.swing.JSeparator midC5;
    private javax.swing.JLabel minimize;
    private javax.swing.JPanel move_panel;
    private javax.swing.JLabel nameA;
    private javax.swing.JLabel nameC;
    private javax.swing.JLabel name_labelA;
    private javax.swing.JLabel name_labelC;
    private javax.swing.JLabel okay;
    private javax.swing.JLabel okayB;
    private javax.swing.JPanel panelA;
    private javax.swing.JSeparator panelA_bottom_line;
    private javax.swing.JPanel panelB;
    private javax.swing.JPanel panelC;
    private javax.swing.JSeparator panelC_bottom_line;
    private javax.swing.JLabel photo_label;
    private javax.swing.JPanel print_buttonA;
    private javax.swing.JPanel print_buttonB;
    private javax.swing.JLabel print_label;
    private javax.swing.JLabel print_labelB;
    private javax.swing.JLabel purpose_labelA;
    private javax.swing.JLabel purpose_labelC;
    private javax.swing.JTextField purpose_textfieldA;
    private javax.swing.JTextField purpose_textfieldC;
    private javax.swing.JSeparator purpose_underlineA;
    private javax.swing.JSeparator purpose_underlineC;
    private javax.swing.JLabel rankA;
    private javax.swing.JLabel rankC;
    private javax.swing.JLabel rank_labelA;
    private javax.swing.JLabel rank_labelC;
    private javax.swing.JComboBox<String> rationed_date_comboboxA;
    private javax.swing.JComboBox<String> rationed_date_comboboxC;
    private javax.swing.JLabel rationed_labelA;
    private javax.swing.JLabel rationed_labelC;
    private javax.swing.JComboBox<String> rationed_month_comboboxA;
    private javax.swing.JComboBox<String> rationed_month_comboboxC;
    private javax.swing.JComboBox<String> rationed_year_comboboxA;
    private javax.swing.JComboBox<String> rationed_year_comboboxC;
    private javax.swing.JPanel refresh_buttonB;
    private javax.swing.JLabel refresh_labelB;
    private javax.swing.JLabel report_labelA;
    private javax.swing.JLabel report_labelC;
    private javax.swing.JTextField report_textfieldA;
    private javax.swing.JTextField report_textfieldC;
    private javax.swing.JSeparator report_underlineA;
    private javax.swing.JSeparator report_underlineC;
    private javax.swing.JTextField service_numberB;
    private javax.swing.JLabel service_number_label;
    private javax.swing.JLabel service_number_labelB;
    private javax.swing.JLabel service_number_labelC;
    private javax.swing.JTextField service_number_textfield;
    private javax.swing.JSeparator service_number_underline;
    private javax.swing.JPanel side_pane;
    private javax.swing.JLabel stateA;
    private javax.swing.JLabel state_labelA;
    private javax.swing.JLabel teh_labelA;
    private javax.swing.JLabel tehsilA;
    private javax.swing.JSeparator textfield_underline;
    private javax.swing.JLabel title_icon;
    private javax.swing.JLabel title_label;
    private javax.swing.JLabel title_label1;
    private javax.swing.JLabel to_unit_labelA;
    private javax.swing.JLabel to_unit_labelC;
    private javax.swing.JTextField to_unit_textfieldA;
    private javax.swing.JTextField to_unit_textfieldC;
    private javax.swing.JSeparator to_unit_underlineA;
    private javax.swing.JSeparator to_unit_underlineC;
    private javax.swing.JLabel tradeA;
    private javax.swing.JLabel trade_labelA;
    private javax.swing.JLabel type_labelA;
    private javax.swing.JLabel type_labelC;
    private javax.swing.JTextField type_textfieldA;
    private javax.swing.JTextField type_textfieldC;
    private javax.swing.JSeparator type_underlineA;
    private javax.swing.JSeparator type_underlineC;
    private javax.swing.JLabel unitA;
    private javax.swing.JLabel unit_labelA;
    private javax.swing.JPanel update_buttonC;
    private javax.swing.JLabel update_labelC;
    private javax.swing.JLabel villA;
    private javax.swing.JLabel vill_labelA;
    // End of variables declaration//GEN-END:variables
}
