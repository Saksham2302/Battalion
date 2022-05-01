
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
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import java.sql.Connection;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.swing.ImageIcon;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.filechooser.FileNameExtensionFilter;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author chise
 */
public class Long_Roll extends javax.swing.JFrame {

    /**
     * Creates new form Long_Roll
     */
    private int mouseX, mouseY;
    boolean back_button=true;
    Database db=new Database();
    Connection con=db.create_connection(true);
    String filepath;
    DefaultTableModel small, big;
    public Long_Roll() {
        initComponents();
        
        ScrollPaneC.getVerticalScrollBar().setUnitIncrement(16);
        ScrollPaneA.getVerticalScrollBar().setUnitIncrement(16);
        ScrollPaneB.getVerticalScrollBar().setUnitIncrement(16);
        fullscreen_ScrollPaneB.getVerticalScrollBar().setUnitIncrement(16);
        
        big = (DefaultTableModel) fullscreen_tableB.getModel();
        small = (DefaultTableModel) TableG_out.getModel();
        print_buttonA.setVisible(true);
        insert_buttonA.setVisible(true);
        print_buttonB.setVisible(false);
        edit_buttonB.setVisible(false);
        refresh_buttonB.setVisible(false);
        fullscreen_buttonB.setVisible(false);
    }
    class Page1 implements Printable  
    {    
            public int print(Graphics graphics, PageFormat pageFormat, int pageIndex) throws PrinterException {
                
                // Check If No Printable Content
                if (pageIndex >= 2) return Printable.NO_SUCH_PAGE;
                Graphics2D graphics2D = (Graphics2D)graphics;

                graphics2D.setClip(0, 0, 690, 760); //shapes the printing rectangle in respect to translate
                graphics2D.translate(10, 0); //marks the top of the page
                
                graphics2D.scale(0.68,0.7);
                panelA.paint(graphics2D);
                
                // return if page exists
                return Printable.PAGE_EXISTS;
            }
    }    
    class Page2 implements Printable  
    {    
            public int print(Graphics graphics, PageFormat pageFormat, int pageIndex) throws PrinterException {
                Graphics2D graphics2D = (Graphics2D)graphics;

                graphics2D.setClip(0, 25, 690, 700); //shapes the printing rectangle in respect to translate
                graphics2D.translate(10, -740); //marks the top of the page
                
                graphics2D.scale(0.68,0.7);
                panelA.paint(graphics2D);
                
                // return if page exists
                return Printable.PAGE_EXISTS;
            }
    }

    private void PrintRecord()  
    {         
    PrinterJob printerjob = PrinterJob.getPrinterJob();          
    PageFormat pageformat = printerjob.defaultPage();     
    Book bk = new Book();    
    
    bk.append(new Page1(), pageformat);
    bk.append(new Page2(), pageformat);
    
    // Pass the book to the PrinterJob      
    printerjob.setPageable(bk);         
    if (printerjob.printDialog())  
    {
      try { printerjob.print(); }  
      catch (Exception e) { JOptionPane.showMessageDialog(null, "Could not print"); }      
    }}

    public void scaleImage(JLabel label, String name_photo){
        String sno=service_number_textfield.getText();
        if(sno==null ||sno.trim().isEmpty()){
            JOptionPane.showMessageDialog(null,"Enter Army Number");
        }
        else{
            path_file paths=new path_file();
            ImageIcon icon=new ImageIcon(paths.images +"\\"+name_photo+".jPG");
            Image img=icon.getImage();
            Image imgScale=img.getScaledInstance(label.getWidth(), label.getHeight(), Image.SCALE_SMOOTH);
            ImageIcon scaledIcon =new ImageIcon(imgScale);
            label.setIcon(scaledIcon);
        }
    }
    
    public void scaleImagefamily(JLabel label, String name_photo){
        String sno=service_number_textfield.getText();
        if(sno==null ||sno.trim().isEmpty()){
            JOptionPane.showMessageDialog(null,"Enter Army Number");
        }
        else{
            path_file paths=new path_file();
            ImageIcon icon=new ImageIcon(name_photo);
            Image img=icon.getImage();
            Image imgScale=img.getScaledInstance(label.getWidth(), label.getHeight(), Image.SCALE_SMOOTH);
            ImageIcon scaledIcon =new ImageIcon(imgScale);
            label.setIcon(scaledIcon);
        }
    }
    private void view_table(String a) {
        try {
            Statement st = con.createStatement();
            String query = "select *from long_roll";
            ResultSet rs = st.executeQuery(query);
            small.setRowCount(0);
            big.setRowCount(0);
            String service_no, doe, doc, doi, ddate, ttt, mr, id, honour, child, bg;
            int count = 0;
            String five_run, vrope, hrope, sixtym, ninem, result1, two_km, chinup, fivem_shuttle, situp, result2, hundred_m, name_;
            while (rs.next()) {
                count = count + 1;
                service_no = rs.getString("Service_no");
                doe = rs.getString("DOE");
                doc = rs.getString("DOC");
                doi = rs.getString("DOI");
                ddate = rs.getString("Date_");
                ttt = rs.getString("TTT");
                mr = rs.getString("MR");
                id = rs.getString("i_card");
                honour = rs.getString("honour_award");
                child = rs.getString("child_details");
                bg = rs.getString("blood_group");

                small.addRow(new Object[]{
                    count,
                    service_no,
                    doe,
                    doc,
                    doi
                });
                big.addRow(new Object[]{
                    count,
                    service_no,
                    doe,
                    doc,
                    doi,
                    ddate, ttt, mr, id, honour, child, bg
                });

            }
            if (a != null) {
                count = 0;
                String sno = service_numberB.getText();
                // arrange=100;
                String diff_query = "select *from long_roll where service_no='" + sno + "'";
                rs = st.executeQuery(diff_query);
                small.setRowCount(0);
                big.setRowCount(0);
                while (rs.next()) {
                    count = count + 1;
                    service_no = rs.getString("Service_no");
                    doe = rs.getString("DOE");
                    doc = rs.getString("DOC");
                    doi = rs.getString("DOI");
                    ddate = rs.getString("Date_");
                    ttt = rs.getString("TTT");
                    mr = rs.getString("MR");
                    id = rs.getString("i_card");
                    honour = rs.getString("honour_award");
                    child = rs.getString("child_details");
                    bg = rs.getString("blood_group");

                    small.addRow(new Object[]{
                        count,
                        service_no,
                        doe,
                        doc,
                        doi
                    });
                    big.addRow(new Object[]{
                        count,
                        service_no,
                        doe,
                        doc,
                        doi,
                        ddate, ttt, mr, id, honour, child, bg
                    });
                }

            }

        } catch (Exception e) {
            System.out.print("\n" + e);
        }
    }
    
    private void move(String str) {
        try {
            path_file paths = new path_file();
            Path temp = Files.copy(Paths.get(str), Paths.get(paths.images + "/" + service_number_textfield.getText()+"_family" + ".jpg"), StandardCopyOption.REPLACE_EXISTING);
            if (temp != null) {
                System.out.println("File renamed and moved successfully");
            } else {
                System.out.println("Failed to move the file");
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
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
        okay = new javax.swing.JLabel();
        name_label = new javax.swing.JLabel();
        name = new javax.swing.JLabel();
        rank_label = new javax.swing.JLabel();
        rank = new javax.swing.JLabel();
        company_label = new javax.swing.JLabel();
        company = new javax.swing.JLabel();
        trade_label = new javax.swing.JLabel();
        trade = new javax.swing.JLabel();
        unit_label = new javax.swing.JLabel();
        unit = new javax.swing.JLabel();
        DOB_label = new javax.swing.JLabel();
        DOB = new javax.swing.JLabel();
        DOE_labelA = new javax.swing.JLabel();
        DOE_date_comboboxA = new javax.swing.JComboBox<>();
        DOE_month_comboboxA = new javax.swing.JComboBox<>();
        DOE_year_comboboxA = new javax.swing.JComboBox<>();
        TTT_labelA = new javax.swing.JLabel();
        TTT_textfieldA = new javax.swing.JTextField();
        TTT_underlineA = new javax.swing.JSeparator();
        MR_labelA = new javax.swing.JLabel();
        MR_textfieldA = new javax.swing.JTextField();
        MR_underlineA = new javax.swing.JSeparator();
        DOC_labelA = new javax.swing.JLabel();
        DOC_date_comboboxA = new javax.swing.JComboBox<>();
        DOC_month_comboboxA = new javax.swing.JComboBox<>();
        DOC_year_comboboxA = new javax.swing.JComboBox<>();
        TTT_labelA1 = new javax.swing.JLabel();
        TTT_textfieldA1 = new javax.swing.JTextField();
        TTT_underlineA1 = new javax.swing.JSeparator();
        DOI_labelA = new javax.swing.JLabel();
        DOI_date_comboboxA = new javax.swing.JComboBox<>();
        DOI_month_comboboxA = new javax.swing.JComboBox<>();
        DOI_year_comboboxA = new javax.swing.JComboBox<>();
        TTT_labelA2 = new javax.swing.JLabel();
        TTT_textfieldA2 = new javax.swing.JTextField();
        TTT_underlineA2 = new javax.swing.JSeparator();
        DOI_labelA1 = new javax.swing.JLabel();
        DOI_date_comboboxA1 = new javax.swing.JComboBox<>();
        DOI_month_comboboxA1 = new javax.swing.JComboBox<>();
        DOI_year_comboboxA1 = new javax.swing.JComboBox<>();
        current_date = new javax.swing.JLabel();
        NOK_name_labelB = new javax.swing.JLabel();
        NOK_nameB = new javax.swing.JLabel();
        NOK_relation_labelB = new javax.swing.JLabel();
        NOK_relationB = new javax.swing.JLabel();
        NOK_mobile_labelB = new javax.swing.JLabel();
        NOK_mobileB = new javax.swing.JLabel();
        address_labelB = new javax.swing.JLabel();
        villC = new javax.swing.JLabel();
        PO_labelB = new javax.swing.JLabel();
        PO_B = new javax.swing.JLabel();
        dist_labelB = new javax.swing.JLabel();
        distB = new javax.swing.JLabel();
        state_labelB = new javax.swing.JLabel();
        stateB = new javax.swing.JLabel();
        teh_labelB = new javax.swing.JLabel();
        tehsil = new javax.swing.JLabel();
        PIN_labelB = new javax.swing.JLabel();
        PIN_B = new javax.swing.JLabel();
        vill_labelB = new javax.swing.JLabel();
        midB1 = new javax.swing.JSeparator();
        midB2 = new javax.swing.JSeparator();
        Photo_joint = new javax.swing.JPanel();
        joint_photo_label = new javax.swing.JLabel();
        Photo_individual = new javax.swing.JPanel();
        photo_label = new javax.swing.JLabel();
        midB3 = new javax.swing.JSeparator();
        TTT_labelA3 = new javax.swing.JLabel();
        TTT_textfieldA3 = new javax.swing.JTextField();
        TTT_underlineA3 = new javax.swing.JSeparator();
        TTT_labelA4 = new javax.swing.JLabel();
        TTT_textfieldA4 = new javax.swing.JTextField();
        TTT_underlineA4 = new javax.swing.JSeparator();
        panelC_bottom_line1 = new javax.swing.JSeparator();
        upload_photo_labelA = new javax.swing.JLabel();
        select_file_buttonA = new javax.swing.JPanel();
        select_file_labelA = new javax.swing.JLabel();
        panelB = new javax.swing.JPanel();
        ScrollPaneB = new javax.swing.JScrollPane();
        TableG_out = new javax.swing.JTable();
        service_number_labelB = new javax.swing.JLabel();
        service_numberB = new javax.swing.JTextField();
        textfield_underline = new javax.swing.JSeparator();
        okayB = new javax.swing.JLabel();
        ScrollPaneC = new javax.swing.JScrollPane();
        panelC = new javax.swing.JPanel();
        service_number_labelC = new javax.swing.JLabel();
        panelC_bottom_line = new javax.swing.JSeparator();
        name_labelC = new javax.swing.JLabel();
        army_numberC = new javax.swing.JLabel();
        rank_labelC = new javax.swing.JLabel();
        rankC = new javax.swing.JLabel();
        nameC = new javax.swing.JLabel();
        midC4 = new javax.swing.JSeparator();
        changing_for_label = new javax.swing.JLabel();
        midC0 = new javax.swing.JSeparator();
        DOE_labelA1 = new javax.swing.JLabel();
        DOE_date_comboboxA1 = new javax.swing.JComboBox<>();
        DOE_month_comboboxA1 = new javax.swing.JComboBox<>();
        DOE_year_comboboxA1 = new javax.swing.JComboBox<>();
        TTT_labelA5 = new javax.swing.JLabel();
        TTT_textfieldA5 = new javax.swing.JTextField();
        TTT_underlineA5 = new javax.swing.JSeparator();
        MR_labelA1 = new javax.swing.JLabel();
        MR_textfieldA1 = new javax.swing.JTextField();
        MR_underlineA1 = new javax.swing.JSeparator();
        DOC_labelA1 = new javax.swing.JLabel();
        DOC_date_comboboxA1 = new javax.swing.JComboBox<>();
        DOC_month_comboboxA1 = new javax.swing.JComboBox<>();
        DOC_year_comboboxA1 = new javax.swing.JComboBox<>();
        TTT_labelA6 = new javax.swing.JLabel();
        TTT_textfieldA6 = new javax.swing.JTextField();
        TTT_underlineA6 = new javax.swing.JSeparator();
        DOI_labelA2 = new javax.swing.JLabel();
        DOI_date_comboboxA2 = new javax.swing.JComboBox<>();
        DOI_month_comboboxA2 = new javax.swing.JComboBox<>();
        DOI_year_comboboxA2 = new javax.swing.JComboBox<>();
        TTT_labelA7 = new javax.swing.JLabel();
        TTT_textfieldA7 = new javax.swing.JTextField();
        TTT_underlineA7 = new javax.swing.JSeparator();
        DOI_labelA3 = new javax.swing.JLabel();
        DOI_date_comboboxA3 = new javax.swing.JComboBox<>();
        DOI_month_comboboxA3 = new javax.swing.JComboBox<>();
        DOI_year_comboboxA3 = new javax.swing.JComboBox<>();
        current_date1 = new javax.swing.JLabel();
        TTT_labelA8 = new javax.swing.JLabel();
        TTT_textfieldA8 = new javax.swing.JTextField();
        TTT_underlineA8 = new javax.swing.JSeparator();
        TTT_labelA9 = new javax.swing.JLabel();
        TTT_textfieldA9 = new javax.swing.JTextField();
        TTT_underlineA9 = new javax.swing.JSeparator();
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
        title_label.setText("Long Roll");
        side_pane.add(title_label, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 130, 160, 50));

        title_icon.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        title_icon.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/PNGS/longRoll_white.png"))); // NOI18N
        side_pane.add(title_icon, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 110, 70, 80));

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
        panelA.add(trade_label, new org.netbeans.lib.awtextra.AbsoluteConstraints(350, 350, -1, 40));

        trade.setFont(new java.awt.Font("Roboto Bk", 0, 24)); // NOI18N
        trade.setForeground(new java.awt.Color(54, 33, 89));
        trade.setText("> XXXXXXX");
        panelA.add(trade, new org.netbeans.lib.awtextra.AbsoluteConstraints(350, 390, -1, 30));

        unit_label.setBackground(new java.awt.Color(255, 255, 255));
        unit_label.setFont(new java.awt.Font("Montserrat", 0, 28)); // NOI18N
        unit_label.setForeground(new java.awt.Color(51, 51, 51));
        unit_label.setText("Unit");
        panelA.add(unit_label, new org.netbeans.lib.awtextra.AbsoluteConstraints(350, 260, -1, 40));

        unit.setFont(new java.awt.Font("Roboto Bk", 0, 24)); // NOI18N
        unit.setForeground(new java.awt.Color(54, 33, 89));
        unit.setText("> XXXXXXX");
        panelA.add(unit, new org.netbeans.lib.awtextra.AbsoluteConstraints(350, 300, -1, 30));

        DOB_label.setBackground(new java.awt.Color(255, 255, 255));
        DOB_label.setFont(new java.awt.Font("Montserrat", 0, 28)); // NOI18N
        DOB_label.setForeground(new java.awt.Color(51, 51, 51));
        DOB_label.setText("DOB");
        panelA.add(DOB_label, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 350, -1, 40));

        DOB.setFont(new java.awt.Font("Roboto Bk", 0, 24)); // NOI18N
        DOB.setForeground(new java.awt.Color(54, 33, 89));
        DOB.setText("> XXXXXXX");
        panelA.add(DOB, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 390, -1, 30));

        DOE_labelA.setBackground(new java.awt.Color(255, 255, 255));
        DOE_labelA.setFont(new java.awt.Font("Montserrat", 0, 28)); // NOI18N
        DOE_labelA.setForeground(new java.awt.Color(51, 51, 51));
        DOE_labelA.setText("Date of Enrolment");
        panelA.add(DOE_labelA, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 460, -1, 40));

        DOE_date_comboboxA.setFont(new java.awt.Font("Roboto", 0, 18)); // NOI18N
        DOE_date_comboboxA.setForeground(new java.awt.Color(44, 62, 80));
        DOE_date_comboboxA.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "DD", "01", "02", "03", "04", "05", "06", "07", "08", "09", "10", "11", "12", "13", "14", "15", "16", "17", "18", "19", "20", "21", "22", "23", "24", "25", "26", "27", "28", "29", "30", "31" }));
        DOE_date_comboboxA.setBorder(null);
        panelA.add(DOE_date_comboboxA, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 500, 100, 40));

        DOE_month_comboboxA.setFont(new java.awt.Font("Roboto", 0, 18)); // NOI18N
        DOE_month_comboboxA.setForeground(new java.awt.Color(44, 62, 80));
        DOE_month_comboboxA.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "MM", "01", "02", "03", "04", "05", "06", "07", "08", "09", "10", "11", "12" }));
        DOE_month_comboboxA.setBorder(null);
        panelA.add(DOE_month_comboboxA, new org.netbeans.lib.awtextra.AbsoluteConstraints(190, 500, 100, 40));

        DOE_year_comboboxA.setFont(new java.awt.Font("Roboto", 0, 18)); // NOI18N
        DOE_year_comboboxA.setForeground(new java.awt.Color(44, 62, 80));
        DOE_year_comboboxA.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "YYYY", "1950", "1951", "1952", "1953", "1954", "1955", "1956", "1957", "1958", "1959", "1960", "1961", "1962", "1963", "1964", "1965", "1966", "1967", "1968", "1969", "1970", "1971", "1972", "1973", "1974", "1975", "1976", "1977", "1978", "1979", "1980", "1981", "1982", "1983", "1984", "1985", "1986", "1987", "1988", "1989", "1990", "1991", "1992", "1993", "1994", "1995", "1996", "1997", "1998", "1999", "2000", "2001", "2002", "2003", "2004", "2005", "2006", "2007", "2008", "2009", "2010", "2011", "2012", "2013", "2014", "2015", "2016", "2017", "2018", "2019", "2020", "2021", "2022", "2023", "2024", "2025", "2026", "2027", "2028", "2029", "2030", "2031", "2032", "2033", "2034", "2035", "2036", "2037", "2038", "2039", "2040", "2041", "2042", "2043", "2044", "2045", "2046", "2047", "2048", "2049", "2050", "2051", "2052", "2053", "2054", "2055", "2056", "2057", "2058", "2059", "2060", "2061", "2062", "2063", "2064", "2065", "2066", "2067", "2068", "2069", "2070", "2071", "2072", "2073", "2074", "2075", "2076", "2077", "2078", "2079", "2080", "2081", "2082", "2083", "2084", "2085", "2086", "2087", "2088", "2089", "2090", "2091", "2092", "2093", "2094", "2095", "2096", "2097", "2098", "2099", "2100", " " }));
        DOE_year_comboboxA.setBorder(null);
        panelA.add(DOE_year_comboboxA, new org.netbeans.lib.awtextra.AbsoluteConstraints(300, 500, 100, 40));

        TTT_labelA.setFont(new java.awt.Font("Montserrat", 0, 28)); // NOI18N
        TTT_labelA.setForeground(new java.awt.Color(51, 51, 51));
        TTT_labelA.setText("TTT");
        panelA.add(TTT_labelA, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 570, -1, 40));

        TTT_textfieldA.setFont(new java.awt.Font("Roboto", 0, 24)); // NOI18N
        TTT_textfieldA.setForeground(new java.awt.Color(54, 33, 89));
        TTT_textfieldA.setBorder(null);
        TTT_textfieldA.setMargin(new java.awt.Insets(2, 4, 2, 2));
        panelA.add(TTT_textfieldA, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 610, 320, 40));

        TTT_underlineA.setForeground(new java.awt.Color(54, 33, 89));
        panelA.add(TTT_underlineA, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 650, 320, 20));

        MR_labelA.setFont(new java.awt.Font("Montserrat", 0, 28)); // NOI18N
        MR_labelA.setForeground(new java.awt.Color(51, 51, 51));
        MR_labelA.setText("MR");
        panelA.add(MR_labelA, new org.netbeans.lib.awtextra.AbsoluteConstraints(480, 570, -1, 40));

        MR_textfieldA.setFont(new java.awt.Font("Roboto", 0, 24)); // NOI18N
        MR_textfieldA.setForeground(new java.awt.Color(54, 33, 89));
        MR_textfieldA.setBorder(null);
        MR_textfieldA.setMargin(new java.awt.Insets(2, 4, 2, 2));
        panelA.add(MR_textfieldA, new org.netbeans.lib.awtextra.AbsoluteConstraints(480, 610, 320, 40));

        MR_underlineA.setForeground(new java.awt.Color(54, 33, 89));
        panelA.add(MR_underlineA, new org.netbeans.lib.awtextra.AbsoluteConstraints(480, 650, 320, 20));

        DOC_labelA.setBackground(new java.awt.Color(255, 255, 255));
        DOC_labelA.setFont(new java.awt.Font("Montserrat", 0, 28)); // NOI18N
        DOC_labelA.setForeground(new java.awt.Color(51, 51, 51));
        DOC_labelA.setText("Date of Completion");
        panelA.add(DOC_labelA, new org.netbeans.lib.awtextra.AbsoluteConstraints(480, 460, -1, 40));

        DOC_date_comboboxA.setFont(new java.awt.Font("Roboto", 0, 18)); // NOI18N
        DOC_date_comboboxA.setForeground(new java.awt.Color(44, 62, 80));
        DOC_date_comboboxA.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "DD", "01", "02", "03", "04", "05", "06", "07", "08", "09", "10", "11", "12", "13", "14", "15", "16", "17", "18", "19", "20", "21", "22", "23", "24", "25", "26", "27", "28", "29", "30", "31" }));
        DOC_date_comboboxA.setBorder(null);
        panelA.add(DOC_date_comboboxA, new org.netbeans.lib.awtextra.AbsoluteConstraints(480, 500, 100, 40));

        DOC_month_comboboxA.setFont(new java.awt.Font("Roboto", 0, 18)); // NOI18N
        DOC_month_comboboxA.setForeground(new java.awt.Color(44, 62, 80));
        DOC_month_comboboxA.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "MM", "01", "02", "03", "04", "05", "06", "07", "08", "09", "10", "11", "12" }));
        DOC_month_comboboxA.setBorder(null);
        panelA.add(DOC_month_comboboxA, new org.netbeans.lib.awtextra.AbsoluteConstraints(590, 500, 100, 40));

        DOC_year_comboboxA.setFont(new java.awt.Font("Roboto", 0, 18)); // NOI18N
        DOC_year_comboboxA.setForeground(new java.awt.Color(44, 62, 80));
        DOC_year_comboboxA.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "YYYY", "1950", "1951", "1952", "1953", "1954", "1955", "1956", "1957", "1958", "1959", "1960", "1961", "1962", "1963", "1964", "1965", "1966", "1967", "1968", "1969", "1970", "1971", "1972", "1973", "1974", "1975", "1976", "1977", "1978", "1979", "1980", "1981", "1982", "1983", "1984", "1985", "1986", "1987", "1988", "1989", "1990", "1991", "1992", "1993", "1994", "1995", "1996", "1997", "1998", "1999", "2000", "2001", "2002", "2003", "2004", "2005", "2006", "2007", "2008", "2009", "2010", "2011", "2012", "2013", "2014", "2015", "2016", "2017", "2018", "2019", "2020", "2021", "2022", "2023", "2024", "2025", "2026", "2027", "2028", "2029", "2030", "2031", "2032", "2033", "2034", "2035", "2036", "2037", "2038", "2039", "2040", "2041", "2042", "2043", "2044", "2045", "2046", "2047", "2048", "2049", "2050", "2051", "2052", "2053", "2054", "2055", "2056", "2057", "2058", "2059", "2060", "2061", "2062", "2063", "2064", "2065", "2066", "2067", "2068", "2069", "2070", "2071", "2072", "2073", "2074", "2075", "2076", "2077", "2078", "2079", "2080", "2081", "2082", "2083", "2084", "2085", "2086", "2087", "2088", "2089", "2090", "2091", "2092", "2093", "2094", "2095", "2096", "2097", "2098", "2099", "2100", " " }));
        DOC_year_comboboxA.setBorder(null);
        panelA.add(DOC_year_comboboxA, new org.netbeans.lib.awtextra.AbsoluteConstraints(700, 500, 100, 40));

        TTT_labelA1.setFont(new java.awt.Font("Montserrat", 0, 28)); // NOI18N
        TTT_labelA1.setForeground(new java.awt.Color(51, 51, 51));
        TTT_labelA1.setText("I-Card Number");
        panelA.add(TTT_labelA1, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 680, -1, 40));

        TTT_textfieldA1.setFont(new java.awt.Font("Roboto", 0, 24)); // NOI18N
        TTT_textfieldA1.setForeground(new java.awt.Color(54, 33, 89));
        TTT_textfieldA1.setBorder(null);
        TTT_textfieldA1.setMargin(new java.awt.Insets(2, 4, 2, 2));
        panelA.add(TTT_textfieldA1, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 720, 320, 40));

        TTT_underlineA1.setForeground(new java.awt.Color(54, 33, 89));
        panelA.add(TTT_underlineA1, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 760, 320, 20));

        DOI_labelA.setBackground(new java.awt.Color(255, 255, 255));
        DOI_labelA.setFont(new java.awt.Font("Montserrat", 0, 28)); // NOI18N
        DOI_labelA.setForeground(new java.awt.Color(51, 51, 51));
        DOI_labelA.setText("Date of Issue");
        panelA.add(DOI_labelA, new org.netbeans.lib.awtextra.AbsoluteConstraints(480, 680, -1, 40));

        DOI_date_comboboxA.setFont(new java.awt.Font("Roboto", 0, 18)); // NOI18N
        DOI_date_comboboxA.setForeground(new java.awt.Color(44, 62, 80));
        DOI_date_comboboxA.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "DD", "01", "02", "03", "04", "05", "06", "07", "08", "09", "10", "11", "12", "13", "14", "15", "16", "17", "18", "19", "20", "21", "22", "23", "24", "25", "26", "27", "28", "29", "30", "31" }));
        DOI_date_comboboxA.setBorder(null);
        panelA.add(DOI_date_comboboxA, new org.netbeans.lib.awtextra.AbsoluteConstraints(480, 720, 100, 40));

        DOI_month_comboboxA.setFont(new java.awt.Font("Roboto", 0, 18)); // NOI18N
        DOI_month_comboboxA.setForeground(new java.awt.Color(44, 62, 80));
        DOI_month_comboboxA.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "MM", "01", "02", "03", "04", "05", "06", "07", "08", "09", "10", "11", "12" }));
        DOI_month_comboboxA.setBorder(null);
        panelA.add(DOI_month_comboboxA, new org.netbeans.lib.awtextra.AbsoluteConstraints(590, 720, 100, 40));

        DOI_year_comboboxA.setFont(new java.awt.Font("Roboto", 0, 18)); // NOI18N
        DOI_year_comboboxA.setForeground(new java.awt.Color(44, 62, 80));
        DOI_year_comboboxA.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "YYYY", "1950", "1951", "1952", "1953", "1954", "1955", "1956", "1957", "1958", "1959", "1960", "1961", "1962", "1963", "1964", "1965", "1966", "1967", "1968", "1969", "1970", "1971", "1972", "1973", "1974", "1975", "1976", "1977", "1978", "1979", "1980", "1981", "1982", "1983", "1984", "1985", "1986", "1987", "1988", "1989", "1990", "1991", "1992", "1993", "1994", "1995", "1996", "1997", "1998", "1999", "2000", "2001", "2002", "2003", "2004", "2005", "2006", "2007", "2008", "2009", "2010", "2011", "2012", "2013", "2014", "2015", "2016", "2017", "2018", "2019", "2020", "2021", "2022", "2023", "2024", "2025", "2026", "2027", "2028", "2029", "2030", "2031", "2032", "2033", "2034", "2035", "2036", "2037", "2038", "2039", "2040", "2041", "2042", "2043", "2044", "2045", "2046", "2047", "2048", "2049", "2050", "2051", "2052", "2053", "2054", "2055", "2056", "2057", "2058", "2059", "2060", "2061", "2062", "2063", "2064", "2065", "2066", "2067", "2068", "2069", "2070", "2071", "2072", "2073", "2074", "2075", "2076", "2077", "2078", "2079", "2080", "2081", "2082", "2083", "2084", "2085", "2086", "2087", "2088", "2089", "2090", "2091", "2092", "2093", "2094", "2095", "2096", "2097", "2098", "2099", "2100", " " }));
        DOI_year_comboboxA.setBorder(null);
        panelA.add(DOI_year_comboboxA, new org.netbeans.lib.awtextra.AbsoluteConstraints(700, 720, 100, 40));

        TTT_labelA2.setFont(new java.awt.Font("Montserrat", 0, 28)); // NOI18N
        TTT_labelA2.setForeground(new java.awt.Color(51, 51, 51));
        TTT_labelA2.setText("Honours & Awards");
        panelA.add(TTT_labelA2, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 790, -1, 40));

        TTT_textfieldA2.setFont(new java.awt.Font("Roboto", 0, 24)); // NOI18N
        TTT_textfieldA2.setForeground(new java.awt.Color(54, 33, 89));
        TTT_textfieldA2.setBorder(null);
        TTT_textfieldA2.setMargin(new java.awt.Insets(2, 4, 2, 2));
        panelA.add(TTT_textfieldA2, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 830, 320, 40));

        TTT_underlineA2.setForeground(new java.awt.Color(54, 33, 89));
        panelA.add(TTT_underlineA2, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 870, 320, 20));

        DOI_labelA1.setBackground(new java.awt.Color(255, 255, 255));
        DOI_labelA1.setFont(new java.awt.Font("Montserrat", 0, 28)); // NOI18N
        DOI_labelA1.setForeground(new java.awt.Color(51, 51, 51));
        DOI_labelA1.setText("Date");
        panelA.add(DOI_labelA1, new org.netbeans.lib.awtextra.AbsoluteConstraints(480, 790, -1, 40));

        DOI_date_comboboxA1.setFont(new java.awt.Font("Roboto", 0, 18)); // NOI18N
        DOI_date_comboboxA1.setForeground(new java.awt.Color(44, 62, 80));
        DOI_date_comboboxA1.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "DD", "01", "02", "03", "04", "05", "06", "07", "08", "09", "10", "11", "12", "13", "14", "15", "16", "17", "18", "19", "20", "21", "22", "23", "24", "25", "26", "27", "28", "29", "30", "31" }));
        DOI_date_comboboxA1.setBorder(null);
        panelA.add(DOI_date_comboboxA1, new org.netbeans.lib.awtextra.AbsoluteConstraints(480, 830, 100, 40));

        DOI_month_comboboxA1.setFont(new java.awt.Font("Roboto", 0, 18)); // NOI18N
        DOI_month_comboboxA1.setForeground(new java.awt.Color(44, 62, 80));
        DOI_month_comboboxA1.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "MM", "01", "02", "03", "04", "05", "06", "07", "08", "09", "10", "11", "12" }));
        DOI_month_comboboxA1.setBorder(null);
        panelA.add(DOI_month_comboboxA1, new org.netbeans.lib.awtextra.AbsoluteConstraints(590, 830, 100, 40));

        DOI_year_comboboxA1.setFont(new java.awt.Font("Roboto", 0, 18)); // NOI18N
        DOI_year_comboboxA1.setForeground(new java.awt.Color(44, 62, 80));
        DOI_year_comboboxA1.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "YYYY", "1950", "1951", "1952", "1953", "1954", "1955", "1956", "1957", "1958", "1959", "1960", "1961", "1962", "1963", "1964", "1965", "1966", "1967", "1968", "1969", "1970", "1971", "1972", "1973", "1974", "1975", "1976", "1977", "1978", "1979", "1980", "1981", "1982", "1983", "1984", "1985", "1986", "1987", "1988", "1989", "1990", "1991", "1992", "1993", "1994", "1995", "1996", "1997", "1998", "1999", "2000", "2001", "2002", "2003", "2004", "2005", "2006", "2007", "2008", "2009", "2010", "2011", "2012", "2013", "2014", "2015", "2016", "2017", "2018", "2019", "2020", "2021", "2022", "2023", "2024", "2025", "2026", "2027", "2028", "2029", "2030", "2031", "2032", "2033", "2034", "2035", "2036", "2037", "2038", "2039", "2040", "2041", "2042", "2043", "2044", "2045", "2046", "2047", "2048", "2049", "2050", "2051", "2052", "2053", "2054", "2055", "2056", "2057", "2058", "2059", "2060", "2061", "2062", "2063", "2064", "2065", "2066", "2067", "2068", "2069", "2070", "2071", "2072", "2073", "2074", "2075", "2076", "2077", "2078", "2079", "2080", "2081", "2082", "2083", "2084", "2085", "2086", "2087", "2088", "2089", "2090", "2091", "2092", "2093", "2094", "2095", "2096", "2097", "2098", "2099", "2100", " " }));
        DOI_year_comboboxA1.setBorder(null);
        panelA.add(DOI_year_comboboxA1, new org.netbeans.lib.awtextra.AbsoluteConstraints(700, 830, 100, 40));

        current_date.setBackground(new java.awt.Color(255, 255, 255));
        current_date.setFont(new java.awt.Font("Segoe UI", 0, 13)); // NOI18N
        current_date.setForeground(new java.awt.Color(60, 63, 65));
        current_date.setText("Get Current Date");
        current_date.setOpaque(true);
        current_date.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                current_dateMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                current_dateMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                current_dateMouseExited(evt);
            }
        });
        panelA.add(current_date, new org.netbeans.lib.awtextra.AbsoluteConstraints(480, 880, -1, -1));

        NOK_name_labelB.setBackground(new java.awt.Color(255, 255, 255));
        NOK_name_labelB.setFont(new java.awt.Font("Montserrat", 0, 28)); // NOI18N
        NOK_name_labelB.setForeground(new java.awt.Color(51, 51, 51));
        NOK_name_labelB.setText("NOK Name");
        panelA.add(NOK_name_labelB, new org.netbeans.lib.awtextra.AbsoluteConstraints(350, 1090, -1, 50));

        NOK_nameB.setFont(new java.awt.Font("Roboto Bk", 0, 24)); // NOI18N
        NOK_nameB.setForeground(new java.awt.Color(54, 33, 89));
        NOK_nameB.setText("> XXXXXXX");
        panelA.add(NOK_nameB, new org.netbeans.lib.awtextra.AbsoluteConstraints(350, 1130, -1, 30));

        NOK_relation_labelB.setBackground(new java.awt.Color(255, 255, 255));
        NOK_relation_labelB.setFont(new java.awt.Font("Montserrat", 0, 28)); // NOI18N
        NOK_relation_labelB.setForeground(new java.awt.Color(51, 51, 51));
        NOK_relation_labelB.setText("NOK Relation");
        panelA.add(NOK_relation_labelB, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 1090, -1, 50));

        NOK_relationB.setFont(new java.awt.Font("Roboto Bk", 0, 24)); // NOI18N
        NOK_relationB.setForeground(new java.awt.Color(54, 33, 89));
        NOK_relationB.setText("> XXXXXXX");
        panelA.add(NOK_relationB, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 1130, -1, 30));

        NOK_mobile_labelB.setBackground(new java.awt.Color(255, 255, 255));
        NOK_mobile_labelB.setFont(new java.awt.Font("Montserrat", 0, 28)); // NOI18N
        NOK_mobile_labelB.setForeground(new java.awt.Color(51, 51, 51));
        NOK_mobile_labelB.setText("NOK Mob.");
        panelA.add(NOK_mobile_labelB, new org.netbeans.lib.awtextra.AbsoluteConstraints(600, 1090, -1, 50));

        NOK_mobileB.setFont(new java.awt.Font("Roboto Bk", 0, 24)); // NOI18N
        NOK_mobileB.setForeground(new java.awt.Color(54, 33, 89));
        NOK_mobileB.setText("> XXXXXXX");
        panelA.add(NOK_mobileB, new org.netbeans.lib.awtextra.AbsoluteConstraints(600, 1130, -1, 30));

        address_labelB.setBackground(new java.awt.Color(255, 255, 255));
        address_labelB.setFont(new java.awt.Font("Montserrat", 0, 28)); // NOI18N
        address_labelB.setForeground(new java.awt.Color(51, 51, 51));
        address_labelB.setText("Address");
        panelA.add(address_labelB, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 1190, -1, 50));

        villC.setFont(new java.awt.Font("Roboto Bk", 0, 24)); // NOI18N
        villC.setForeground(new java.awt.Color(54, 33, 89));
        villC.setText("> XXXXXXX");
        panelA.add(villC, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 1290, -1, 30));

        PO_labelB.setBackground(new java.awt.Color(255, 255, 255));
        PO_labelB.setFont(new java.awt.Font("Montserrat", 0, 24)); // NOI18N
        PO_labelB.setForeground(new java.awt.Color(66, 50, 77));
        PO_labelB.setText("PO");
        panelA.add(PO_labelB, new org.netbeans.lib.awtextra.AbsoluteConstraints(350, 1250, -1, 50));

        PO_B.setFont(new java.awt.Font("Roboto Bk", 0, 24)); // NOI18N
        PO_B.setForeground(new java.awt.Color(54, 33, 89));
        PO_B.setText("> XXXXXXX");
        panelA.add(PO_B, new org.netbeans.lib.awtextra.AbsoluteConstraints(350, 1290, -1, 30));

        dist_labelB.setBackground(new java.awt.Color(255, 255, 255));
        dist_labelB.setFont(new java.awt.Font("Montserrat", 0, 24)); // NOI18N
        dist_labelB.setForeground(new java.awt.Color(66, 50, 77));
        dist_labelB.setText("District");
        panelA.add(dist_labelB, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 1330, -1, 50));

        distB.setFont(new java.awt.Font("Roboto Bk", 0, 24)); // NOI18N
        distB.setForeground(new java.awt.Color(54, 33, 89));
        distB.setText("> XXXXXXX");
        panelA.add(distB, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 1370, -1, 30));

        state_labelB.setBackground(new java.awt.Color(255, 255, 255));
        state_labelB.setFont(new java.awt.Font("Montserrat", 0, 24)); // NOI18N
        state_labelB.setForeground(new java.awt.Color(66, 50, 77));
        state_labelB.setText("State");
        panelA.add(state_labelB, new org.netbeans.lib.awtextra.AbsoluteConstraints(350, 1330, -1, 50));

        stateB.setFont(new java.awt.Font("Roboto Bk", 0, 24)); // NOI18N
        stateB.setForeground(new java.awt.Color(54, 33, 89));
        stateB.setText("> XXXXXXX");
        panelA.add(stateB, new org.netbeans.lib.awtextra.AbsoluteConstraints(350, 1370, -1, 30));

        teh_labelB.setBackground(new java.awt.Color(255, 255, 255));
        teh_labelB.setFont(new java.awt.Font("Montserrat", 0, 24)); // NOI18N
        teh_labelB.setForeground(new java.awt.Color(66, 50, 77));
        teh_labelB.setText("Tehsil");
        panelA.add(teh_labelB, new org.netbeans.lib.awtextra.AbsoluteConstraints(600, 1250, -1, 50));

        tehsil.setFont(new java.awt.Font("Roboto Bk", 0, 24)); // NOI18N
        tehsil.setForeground(new java.awt.Color(54, 33, 89));
        tehsil.setText("> XXXXXXX");
        panelA.add(tehsil, new org.netbeans.lib.awtextra.AbsoluteConstraints(600, 1290, -1, 30));

        PIN_labelB.setBackground(new java.awt.Color(255, 255, 255));
        PIN_labelB.setFont(new java.awt.Font("Montserrat", 0, 24)); // NOI18N
        PIN_labelB.setForeground(new java.awt.Color(66, 50, 77));
        PIN_labelB.setText("PIN");
        panelA.add(PIN_labelB, new org.netbeans.lib.awtextra.AbsoluteConstraints(600, 1330, -1, 50));

        PIN_B.setFont(new java.awt.Font("Roboto Bk", 0, 24)); // NOI18N
        PIN_B.setForeground(new java.awt.Color(54, 33, 89));
        PIN_B.setText("> XXXXXXX");
        panelA.add(PIN_B, new org.netbeans.lib.awtextra.AbsoluteConstraints(600, 1370, -1, 30));

        vill_labelB.setBackground(new java.awt.Color(255, 255, 255));
        vill_labelB.setFont(new java.awt.Font("Montserrat", 0, 24)); // NOI18N
        vill_labelB.setForeground(new java.awt.Color(66, 50, 77));
        vill_labelB.setText("Village");
        panelA.add(vill_labelB, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 1250, -1, 50));

        midB1.setForeground(new java.awt.Color(204, 204, 255));
        panelA.add(midB1, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 1450, 570, 10));

        midB2.setForeground(new java.awt.Color(204, 204, 255));
        panelA.add(midB2, new org.netbeans.lib.awtextra.AbsoluteConstraints(210, 1210, 440, 10));

        Photo_joint.setMaximumSize(new java.awt.Dimension(95, 25));
        Photo_joint.setMinimumSize(new java.awt.Dimension(95, 25));
        Photo_joint.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                Photo_jointMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                Photo_jointMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                Photo_jointMouseExited(evt);
            }
        });
        Photo_joint.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());
        Photo_joint.add(joint_photo_label, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 180, 100));

        panelA.add(Photo_joint, new org.netbeans.lib.awtextra.AbsoluteConstraints(620, 310, 180, 101));

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

        midB3.setForeground(new java.awt.Color(204, 204, 255));
        panelA.add(midB3, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 1040, 570, 10));

        TTT_labelA3.setFont(new java.awt.Font("Montserrat", 0, 28)); // NOI18N
        TTT_labelA3.setForeground(new java.awt.Color(51, 51, 51));
        TTT_labelA3.setText("Details of Children");
        panelA.add(TTT_labelA3, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 1490, -1, 40));

        TTT_textfieldA3.setFont(new java.awt.Font("Roboto", 0, 24)); // NOI18N
        TTT_textfieldA3.setForeground(new java.awt.Color(54, 33, 89));
        TTT_textfieldA3.setBorder(null);
        TTT_textfieldA3.setMargin(new java.awt.Insets(2, 4, 2, 2));
        panelA.add(TTT_textfieldA3, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 1530, 410, 40));

        TTT_underlineA3.setForeground(new java.awt.Color(54, 33, 89));
        panelA.add(TTT_underlineA3, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 1570, 410, 20));

        TTT_labelA4.setFont(new java.awt.Font("Montserrat", 0, 28)); // NOI18N
        TTT_labelA4.setForeground(new java.awt.Color(51, 51, 51));
        TTT_labelA4.setText("Blood Group");
        panelA.add(TTT_labelA4, new org.netbeans.lib.awtextra.AbsoluteConstraints(600, 1490, -1, 40));

        TTT_textfieldA4.setFont(new java.awt.Font("Roboto", 0, 24)); // NOI18N
        TTT_textfieldA4.setForeground(new java.awt.Color(54, 33, 89));
        TTT_textfieldA4.setBorder(null);
        TTT_textfieldA4.setMargin(new java.awt.Insets(2, 4, 2, 2));
        panelA.add(TTT_textfieldA4, new org.netbeans.lib.awtextra.AbsoluteConstraints(600, 1530, 180, 40));

        TTT_underlineA4.setForeground(new java.awt.Color(54, 33, 89));
        panelA.add(TTT_underlineA4, new org.netbeans.lib.awtextra.AbsoluteConstraints(600, 1570, 180, 20));

        panelC_bottom_line1.setForeground(new java.awt.Color(54, 33, 89));
        panelA.add(panelC_bottom_line1, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 1630, 910, -1));

        upload_photo_labelA.setBackground(new java.awt.Color(255, 255, 255));
        upload_photo_labelA.setFont(new java.awt.Font("Montserrat", 0, 28)); // NOI18N
        upload_photo_labelA.setForeground(new java.awt.Color(51, 51, 51));
        upload_photo_labelA.setText("Upload Photo");
        panelA.add(upload_photo_labelA, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 900, -1, 40));

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
        select_file_buttonA.add(select_file_labelA, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 0, 260, 40));

        panelA.add(select_file_buttonA, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 940, 320, 40));

        ScrollPaneA.setViewportView(panelA);

        bg.add(ScrollPaneA, new org.netbeans.lib.awtextra.AbsoluteConstraints(340, 192, 910, 500));

        panelB.setBackground(new java.awt.Color(255, 255, 255));
        panelB.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        ScrollPaneB.setBackground(new java.awt.Color(255, 255, 255));
        ScrollPaneB.setVerticalScrollBarPolicy(javax.swing.ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);

        TableG_out.setFont(new java.awt.Font("Roboto", 0, 18)); // NOI18N
        TableG_out.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "S. No.", "Army No.", "Date of Enrollment", "Date of Completion", "Date of Issue"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false
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
            TableG_out.getColumnModel().getColumn(0).setMinWidth(50);
            TableG_out.getColumnModel().getColumn(0).setPreferredWidth(50);
            TableG_out.getColumnModel().getColumn(0).setMaxWidth(50);
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

        panelC_bottom_line.setForeground(new java.awt.Color(54, 33, 89));
        panelC.add(panelC_bottom_line, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 840, 910, -1));

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
        rank_labelC.setText("Rank");
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
        panelC.add(midC4, new org.netbeans.lib.awtextra.AbsoluteConstraints(335, 85, 20, 40));

        changing_for_label.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        changing_for_label.setText("Changing For");
        panelC.add(changing_for_label, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 50, -1, 20));

        midC0.setForeground(new java.awt.Color(204, 204, 255));
        panelC.add(midC0, new org.netbeans.lib.awtextra.AbsoluteConstraints(280, 190, 390, 10));

        DOE_labelA1.setBackground(new java.awt.Color(255, 255, 255));
        DOE_labelA1.setFont(new java.awt.Font("Montserrat", 0, 28)); // NOI18N
        DOE_labelA1.setForeground(new java.awt.Color(51, 51, 51));
        DOE_labelA1.setText("Date of Enrolment");
        panelC.add(DOE_labelA1, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 230, -1, 40));

        DOE_date_comboboxA1.setFont(new java.awt.Font("Roboto", 0, 18)); // NOI18N
        DOE_date_comboboxA1.setForeground(new java.awt.Color(44, 62, 80));
        DOE_date_comboboxA1.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "DD", "01", "02", "03", "04", "05", "06", "07", "08", "09", "10", "11", "12", "13", "14", "15", "16", "17", "18", "19", "20", "21", "22", "23", "24", "25", "26", "27", "28", "29", "30", "31" }));
        DOE_date_comboboxA1.setBorder(null);
        panelC.add(DOE_date_comboboxA1, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 270, 100, 40));

        DOE_month_comboboxA1.setFont(new java.awt.Font("Roboto", 0, 18)); // NOI18N
        DOE_month_comboboxA1.setForeground(new java.awt.Color(44, 62, 80));
        DOE_month_comboboxA1.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "MM", "01", "02", "03", "04", "05", "06", "07", "08", "09", "10", "11", "12" }));
        DOE_month_comboboxA1.setBorder(null);
        panelC.add(DOE_month_comboboxA1, new org.netbeans.lib.awtextra.AbsoluteConstraints(190, 270, 100, 40));

        DOE_year_comboboxA1.setFont(new java.awt.Font("Roboto", 0, 18)); // NOI18N
        DOE_year_comboboxA1.setForeground(new java.awt.Color(44, 62, 80));
        DOE_year_comboboxA1.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "YYYY", "1950", "1951", "1952", "1953", "1954", "1955", "1956", "1957", "1958", "1959", "1960", "1961", "1962", "1963", "1964", "1965", "1966", "1967", "1968", "1969", "1970", "1971", "1972", "1973", "1974", "1975", "1976", "1977", "1978", "1979", "1980", "1981", "1982", "1983", "1984", "1985", "1986", "1987", "1988", "1989", "1990", "1991", "1992", "1993", "1994", "1995", "1996", "1997", "1998", "1999", "2000", "2001", "2002", "2003", "2004", "2005", "2006", "2007", "2008", "2009", "2010", "2011", "2012", "2013", "2014", "2015", "2016", "2017", "2018", "2019", "2020", "2021", "2022", "2023", "2024", "2025", "2026", "2027", "2028", "2029", "2030", "2031", "2032", "2033", "2034", "2035", "2036", "2037", "2038", "2039", "2040", "2041", "2042", "2043", "2044", "2045", "2046", "2047", "2048", "2049", "2050", "2051", "2052", "2053", "2054", "2055", "2056", "2057", "2058", "2059", "2060", "2061", "2062", "2063", "2064", "2065", "2066", "2067", "2068", "2069", "2070", "2071", "2072", "2073", "2074", "2075", "2076", "2077", "2078", "2079", "2080", "2081", "2082", "2083", "2084", "2085", "2086", "2087", "2088", "2089", "2090", "2091", "2092", "2093", "2094", "2095", "2096", "2097", "2098", "2099", "2100", " " }));
        DOE_year_comboboxA1.setBorder(null);
        panelC.add(DOE_year_comboboxA1, new org.netbeans.lib.awtextra.AbsoluteConstraints(300, 270, 100, 40));

        TTT_labelA5.setFont(new java.awt.Font("Montserrat", 0, 28)); // NOI18N
        TTT_labelA5.setForeground(new java.awt.Color(51, 51, 51));
        TTT_labelA5.setText("TTT");
        panelC.add(TTT_labelA5, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 340, -1, 40));

        TTT_textfieldA5.setFont(new java.awt.Font("Roboto", 0, 24)); // NOI18N
        TTT_textfieldA5.setForeground(new java.awt.Color(54, 33, 89));
        TTT_textfieldA5.setBorder(null);
        TTT_textfieldA5.setMargin(new java.awt.Insets(2, 4, 2, 2));
        panelC.add(TTT_textfieldA5, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 380, 320, 40));

        TTT_underlineA5.setForeground(new java.awt.Color(54, 33, 89));
        panelC.add(TTT_underlineA5, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 420, 320, 20));

        MR_labelA1.setFont(new java.awt.Font("Montserrat", 0, 28)); // NOI18N
        MR_labelA1.setForeground(new java.awt.Color(51, 51, 51));
        MR_labelA1.setText("MR");
        panelC.add(MR_labelA1, new org.netbeans.lib.awtextra.AbsoluteConstraints(480, 340, -1, 40));

        MR_textfieldA1.setFont(new java.awt.Font("Roboto", 0, 24)); // NOI18N
        MR_textfieldA1.setForeground(new java.awt.Color(54, 33, 89));
        MR_textfieldA1.setBorder(null);
        MR_textfieldA1.setMargin(new java.awt.Insets(2, 4, 2, 2));
        panelC.add(MR_textfieldA1, new org.netbeans.lib.awtextra.AbsoluteConstraints(480, 380, 320, 40));

        MR_underlineA1.setForeground(new java.awt.Color(54, 33, 89));
        panelC.add(MR_underlineA1, new org.netbeans.lib.awtextra.AbsoluteConstraints(480, 420, 320, 20));

        DOC_labelA1.setBackground(new java.awt.Color(255, 255, 255));
        DOC_labelA1.setFont(new java.awt.Font("Montserrat", 0, 28)); // NOI18N
        DOC_labelA1.setForeground(new java.awt.Color(51, 51, 51));
        DOC_labelA1.setText("Date of Completion");
        panelC.add(DOC_labelA1, new org.netbeans.lib.awtextra.AbsoluteConstraints(480, 230, -1, 40));

        DOC_date_comboboxA1.setFont(new java.awt.Font("Roboto", 0, 18)); // NOI18N
        DOC_date_comboboxA1.setForeground(new java.awt.Color(44, 62, 80));
        DOC_date_comboboxA1.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "DD", "01", "02", "03", "04", "05", "06", "07", "08", "09", "10", "11", "12", "13", "14", "15", "16", "17", "18", "19", "20", "21", "22", "23", "24", "25", "26", "27", "28", "29", "30", "31" }));
        DOC_date_comboboxA1.setBorder(null);
        panelC.add(DOC_date_comboboxA1, new org.netbeans.lib.awtextra.AbsoluteConstraints(480, 270, 100, 40));

        DOC_month_comboboxA1.setFont(new java.awt.Font("Roboto", 0, 18)); // NOI18N
        DOC_month_comboboxA1.setForeground(new java.awt.Color(44, 62, 80));
        DOC_month_comboboxA1.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "MM", "01", "02", "03", "04", "05", "06", "07", "08", "09", "10", "11", "12" }));
        DOC_month_comboboxA1.setBorder(null);
        panelC.add(DOC_month_comboboxA1, new org.netbeans.lib.awtextra.AbsoluteConstraints(590, 270, 100, 40));

        DOC_year_comboboxA1.setFont(new java.awt.Font("Roboto", 0, 18)); // NOI18N
        DOC_year_comboboxA1.setForeground(new java.awt.Color(44, 62, 80));
        DOC_year_comboboxA1.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "YYYY", "1950", "1951", "1952", "1953", "1954", "1955", "1956", "1957", "1958", "1959", "1960", "1961", "1962", "1963", "1964", "1965", "1966", "1967", "1968", "1969", "1970", "1971", "1972", "1973", "1974", "1975", "1976", "1977", "1978", "1979", "1980", "1981", "1982", "1983", "1984", "1985", "1986", "1987", "1988", "1989", "1990", "1991", "1992", "1993", "1994", "1995", "1996", "1997", "1998", "1999", "2000", "2001", "2002", "2003", "2004", "2005", "2006", "2007", "2008", "2009", "2010", "2011", "2012", "2013", "2014", "2015", "2016", "2017", "2018", "2019", "2020", "2021", "2022", "2023", "2024", "2025", "2026", "2027", "2028", "2029", "2030", "2031", "2032", "2033", "2034", "2035", "2036", "2037", "2038", "2039", "2040", "2041", "2042", "2043", "2044", "2045", "2046", "2047", "2048", "2049", "2050", "2051", "2052", "2053", "2054", "2055", "2056", "2057", "2058", "2059", "2060", "2061", "2062", "2063", "2064", "2065", "2066", "2067", "2068", "2069", "2070", "2071", "2072", "2073", "2074", "2075", "2076", "2077", "2078", "2079", "2080", "2081", "2082", "2083", "2084", "2085", "2086", "2087", "2088", "2089", "2090", "2091", "2092", "2093", "2094", "2095", "2096", "2097", "2098", "2099", "2100", " " }));
        DOC_year_comboboxA1.setBorder(null);
        panelC.add(DOC_year_comboboxA1, new org.netbeans.lib.awtextra.AbsoluteConstraints(700, 270, 100, 40));

        TTT_labelA6.setFont(new java.awt.Font("Montserrat", 0, 28)); // NOI18N
        TTT_labelA6.setForeground(new java.awt.Color(51, 51, 51));
        TTT_labelA6.setText("I-Card Number");
        panelC.add(TTT_labelA6, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 450, -1, 40));

        TTT_textfieldA6.setFont(new java.awt.Font("Roboto", 0, 24)); // NOI18N
        TTT_textfieldA6.setForeground(new java.awt.Color(54, 33, 89));
        TTT_textfieldA6.setBorder(null);
        TTT_textfieldA6.setMargin(new java.awt.Insets(2, 4, 2, 2));
        panelC.add(TTT_textfieldA6, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 490, 320, 40));

        TTT_underlineA6.setForeground(new java.awt.Color(54, 33, 89));
        panelC.add(TTT_underlineA6, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 530, 320, 20));

        DOI_labelA2.setBackground(new java.awt.Color(255, 255, 255));
        DOI_labelA2.setFont(new java.awt.Font("Montserrat", 0, 28)); // NOI18N
        DOI_labelA2.setForeground(new java.awt.Color(51, 51, 51));
        DOI_labelA2.setText("Date of Issue");
        panelC.add(DOI_labelA2, new org.netbeans.lib.awtextra.AbsoluteConstraints(480, 450, -1, 40));

        DOI_date_comboboxA2.setFont(new java.awt.Font("Roboto", 0, 18)); // NOI18N
        DOI_date_comboboxA2.setForeground(new java.awt.Color(44, 62, 80));
        DOI_date_comboboxA2.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "DD", "01", "02", "03", "04", "05", "06", "07", "08", "09", "10", "11", "12", "13", "14", "15", "16", "17", "18", "19", "20", "21", "22", "23", "24", "25", "26", "27", "28", "29", "30", "31" }));
        DOI_date_comboboxA2.setBorder(null);
        panelC.add(DOI_date_comboboxA2, new org.netbeans.lib.awtextra.AbsoluteConstraints(480, 490, 100, 40));

        DOI_month_comboboxA2.setFont(new java.awt.Font("Roboto", 0, 18)); // NOI18N
        DOI_month_comboboxA2.setForeground(new java.awt.Color(44, 62, 80));
        DOI_month_comboboxA2.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "MM", "01", "02", "03", "04", "05", "06", "07", "08", "09", "10", "11", "12" }));
        DOI_month_comboboxA2.setBorder(null);
        panelC.add(DOI_month_comboboxA2, new org.netbeans.lib.awtextra.AbsoluteConstraints(590, 490, 100, 40));

        DOI_year_comboboxA2.setFont(new java.awt.Font("Roboto", 0, 18)); // NOI18N
        DOI_year_comboboxA2.setForeground(new java.awt.Color(44, 62, 80));
        DOI_year_comboboxA2.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "YYYY", "1950", "1951", "1952", "1953", "1954", "1955", "1956", "1957", "1958", "1959", "1960", "1961", "1962", "1963", "1964", "1965", "1966", "1967", "1968", "1969", "1970", "1971", "1972", "1973", "1974", "1975", "1976", "1977", "1978", "1979", "1980", "1981", "1982", "1983", "1984", "1985", "1986", "1987", "1988", "1989", "1990", "1991", "1992", "1993", "1994", "1995", "1996", "1997", "1998", "1999", "2000", "2001", "2002", "2003", "2004", "2005", "2006", "2007", "2008", "2009", "2010", "2011", "2012", "2013", "2014", "2015", "2016", "2017", "2018", "2019", "2020", "2021", "2022", "2023", "2024", "2025", "2026", "2027", "2028", "2029", "2030", "2031", "2032", "2033", "2034", "2035", "2036", "2037", "2038", "2039", "2040", "2041", "2042", "2043", "2044", "2045", "2046", "2047", "2048", "2049", "2050", "2051", "2052", "2053", "2054", "2055", "2056", "2057", "2058", "2059", "2060", "2061", "2062", "2063", "2064", "2065", "2066", "2067", "2068", "2069", "2070", "2071", "2072", "2073", "2074", "2075", "2076", "2077", "2078", "2079", "2080", "2081", "2082", "2083", "2084", "2085", "2086", "2087", "2088", "2089", "2090", "2091", "2092", "2093", "2094", "2095", "2096", "2097", "2098", "2099", "2100", " " }));
        DOI_year_comboboxA2.setBorder(null);
        panelC.add(DOI_year_comboboxA2, new org.netbeans.lib.awtextra.AbsoluteConstraints(700, 490, 100, 40));

        TTT_labelA7.setFont(new java.awt.Font("Montserrat", 0, 28)); // NOI18N
        TTT_labelA7.setForeground(new java.awt.Color(51, 51, 51));
        TTT_labelA7.setText("Honours & Awards");
        panelC.add(TTT_labelA7, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 560, -1, 40));

        TTT_textfieldA7.setFont(new java.awt.Font("Roboto", 0, 24)); // NOI18N
        TTT_textfieldA7.setForeground(new java.awt.Color(54, 33, 89));
        TTT_textfieldA7.setBorder(null);
        TTT_textfieldA7.setMargin(new java.awt.Insets(2, 4, 2, 2));
        panelC.add(TTT_textfieldA7, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 600, 320, 40));

        TTT_underlineA7.setForeground(new java.awt.Color(54, 33, 89));
        panelC.add(TTT_underlineA7, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 640, 320, 20));

        DOI_labelA3.setBackground(new java.awt.Color(255, 255, 255));
        DOI_labelA3.setFont(new java.awt.Font("Montserrat", 0, 28)); // NOI18N
        DOI_labelA3.setForeground(new java.awt.Color(51, 51, 51));
        DOI_labelA3.setText("Date");
        panelC.add(DOI_labelA3, new org.netbeans.lib.awtextra.AbsoluteConstraints(480, 560, -1, 40));

        DOI_date_comboboxA3.setFont(new java.awt.Font("Roboto", 0, 18)); // NOI18N
        DOI_date_comboboxA3.setForeground(new java.awt.Color(44, 62, 80));
        DOI_date_comboboxA3.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "DD", "01", "02", "03", "04", "05", "06", "07", "08", "09", "10", "11", "12", "13", "14", "15", "16", "17", "18", "19", "20", "21", "22", "23", "24", "25", "26", "27", "28", "29", "30", "31" }));
        DOI_date_comboboxA3.setBorder(null);
        panelC.add(DOI_date_comboboxA3, new org.netbeans.lib.awtextra.AbsoluteConstraints(480, 600, 100, 40));

        DOI_month_comboboxA3.setFont(new java.awt.Font("Roboto", 0, 18)); // NOI18N
        DOI_month_comboboxA3.setForeground(new java.awt.Color(44, 62, 80));
        DOI_month_comboboxA3.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "MM", "01", "02", "03", "04", "05", "06", "07", "08", "09", "10", "11", "12" }));
        DOI_month_comboboxA3.setBorder(null);
        panelC.add(DOI_month_comboboxA3, new org.netbeans.lib.awtextra.AbsoluteConstraints(590, 600, 100, 40));

        DOI_year_comboboxA3.setFont(new java.awt.Font("Roboto", 0, 18)); // NOI18N
        DOI_year_comboboxA3.setForeground(new java.awt.Color(44, 62, 80));
        DOI_year_comboboxA3.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "YYYY", "1950", "1951", "1952", "1953", "1954", "1955", "1956", "1957", "1958", "1959", "1960", "1961", "1962", "1963", "1964", "1965", "1966", "1967", "1968", "1969", "1970", "1971", "1972", "1973", "1974", "1975", "1976", "1977", "1978", "1979", "1980", "1981", "1982", "1983", "1984", "1985", "1986", "1987", "1988", "1989", "1990", "1991", "1992", "1993", "1994", "1995", "1996", "1997", "1998", "1999", "2000", "2001", "2002", "2003", "2004", "2005", "2006", "2007", "2008", "2009", "2010", "2011", "2012", "2013", "2014", "2015", "2016", "2017", "2018", "2019", "2020", "2021", "2022", "2023", "2024", "2025", "2026", "2027", "2028", "2029", "2030", "2031", "2032", "2033", "2034", "2035", "2036", "2037", "2038", "2039", "2040", "2041", "2042", "2043", "2044", "2045", "2046", "2047", "2048", "2049", "2050", "2051", "2052", "2053", "2054", "2055", "2056", "2057", "2058", "2059", "2060", "2061", "2062", "2063", "2064", "2065", "2066", "2067", "2068", "2069", "2070", "2071", "2072", "2073", "2074", "2075", "2076", "2077", "2078", "2079", "2080", "2081", "2082", "2083", "2084", "2085", "2086", "2087", "2088", "2089", "2090", "2091", "2092", "2093", "2094", "2095", "2096", "2097", "2098", "2099", "2100", " " }));
        DOI_year_comboboxA3.setBorder(null);
        panelC.add(DOI_year_comboboxA3, new org.netbeans.lib.awtextra.AbsoluteConstraints(700, 600, 100, 40));

        current_date1.setBackground(new java.awt.Color(255, 255, 255));
        current_date1.setFont(new java.awt.Font("Segoe UI", 0, 13)); // NOI18N
        current_date1.setForeground(new java.awt.Color(60, 63, 65));
        current_date1.setText("Get Current Date");
        current_date1.setOpaque(true);
        current_date1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                current_date1MouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                current_date1MouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                current_date1MouseExited(evt);
            }
        });
        panelC.add(current_date1, new org.netbeans.lib.awtextra.AbsoluteConstraints(480, 650, -1, -1));

        TTT_labelA8.setFont(new java.awt.Font("Montserrat", 0, 28)); // NOI18N
        TTT_labelA8.setForeground(new java.awt.Color(51, 51, 51));
        TTT_labelA8.setText("Details of Children");
        panelC.add(TTT_labelA8, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 690, -1, 40));

        TTT_textfieldA8.setFont(new java.awt.Font("Roboto", 0, 24)); // NOI18N
        TTT_textfieldA8.setForeground(new java.awt.Color(54, 33, 89));
        TTT_textfieldA8.setBorder(null);
        TTT_textfieldA8.setMargin(new java.awt.Insets(2, 4, 2, 2));
        panelC.add(TTT_textfieldA8, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 730, 410, 40));

        TTT_underlineA8.setForeground(new java.awt.Color(54, 33, 89));
        panelC.add(TTT_underlineA8, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 770, 410, 20));

        TTT_labelA9.setFont(new java.awt.Font("Montserrat", 0, 28)); // NOI18N
        TTT_labelA9.setForeground(new java.awt.Color(51, 51, 51));
        TTT_labelA9.setText("Blood Group");
        panelC.add(TTT_labelA9, new org.netbeans.lib.awtextra.AbsoluteConstraints(620, 690, -1, 40));

        TTT_textfieldA9.setFont(new java.awt.Font("Roboto", 0, 24)); // NOI18N
        TTT_textfieldA9.setForeground(new java.awt.Color(54, 33, 89));
        TTT_textfieldA9.setBorder(null);
        TTT_textfieldA9.setMargin(new java.awt.Insets(2, 4, 2, 2));
        panelC.add(TTT_textfieldA9, new org.netbeans.lib.awtextra.AbsoluteConstraints(620, 730, 180, 40));

        TTT_underlineA9.setForeground(new java.awt.Color(54, 33, 89));
        panelC.add(TTT_underlineA9, new org.netbeans.lib.awtextra.AbsoluteConstraints(620, 770, 180, 20));

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

        getContentPane().add(bg, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 1280, 720));

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
                "S. No.", "Army No.", "Date of Enrollment", "Date of Completion", "Date of Issue", "Date", "TTT", "MR", "ID", "Honour and Awards", "Children Details", "Blood Group"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, false, false, false, false, false, false
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

        getContentPane().add(fullscreen_panelB, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 1280, 720));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void move_panelMouseDragged(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_move_panelMouseDragged
        this.setLocation(this.getX() +evt.getX() -mouseX, this.getY() + evt.getY() -mouseY);
    }//GEN-LAST:event_move_panelMouseDragged

    private void move_panelMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_move_panelMousePressed
        mouseX =evt.getX();
        mouseY =evt.getY();
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
        if(back_button){
            new Modules(0).setVisible(true);
            this.setVisible(false);
          
            back_button=false;
        }
    }//GEN-LAST:event_go_back_labelMouseClicked

    private void menuAMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_menuAMouseClicked
        menuA.setBackground(new java.awt.Color(85,65,118));
        menuB.setBackground(new java.awt.Color(64,43,100));

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
        menuB.setBackground(new java.awt.Color(85,65,118));
        menuA.setBackground(new java.awt.Color(64,43,100));
        
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
                        NOK_relationB.setText(rs.getString("kin_relation"));
                        NOK_nameB.setText(rs.getString("kin_name"));
                        NOK_mobileB.setText(rs.getString("kin_contact"));

                        String c = rs.getString("Address");
                        String[] ad = c.split(",");
                        villC.setText(ad[0]);
                        PO_B.setText(ad[3]);
                        tehsil.setText(ad[2]);
                        distB.setText(ad[1]);
                        PIN_B.setText(ad[5]);
                        stateB.setText(ad[4]);

                        company_label.setText("Company");
                        unit_label.setText("Unit");
                        trade_label.setText("Trade");
                    }
                    scaleImage(photo_label, sno);
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
                        //unit.setText(rs.getString("Unit"));
                        //trade.setText(rs.getString("Trade"));
                        
                        if(count2==0){
                            DOB.setText(rs.getString("DOB"));
                            NOK_nameB.setText(rs.getString("kin_name"));
                            NOK_mobileB.setText(rs.getString("kin_contact"));
                        }
                        else{
                            DOB.setText(rs.getString("birth_date"));
                            NOK_nameB.setText(rs.getString("NOK"));
                            NOK_mobileB.setText(rs.getString("kin_contact_no"));
                        }
                        NOK_relationB.setText(rs.getString("kin_relation"));
                        
                        

                        String c = rs.getString("Address");
                        String[] ad = c.split(",");
                        villC.setText(ad[0]);
                        PO_B.setText(ad[3]);
                        tehsil.setText(ad[2]);
                        distB.setText(ad[1]);
                        PIN_B.setText(ad[5]);
                        stateB.setText(ad[4]);

                        trade.setText("> XXXXXXX");
                        unit.setText("> XXXXXXX");
                    }
                    scaleImage(photo_label, sno);
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

    private void okayBMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_okayBMouseClicked
        String sno = service_numberB.getText();
        try {
            Statement st = con.createStatement();
            ResultSet rs = null;
            String q = "select count(*) as count from long_roll where Service_no='" + sno + "'";
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
        current_date.setText("");
        PrintRecord();
        current_date.setText("Get Current Date");
    }//GEN-LAST:event_print_buttonAMouseClicked

    private void print_buttonAMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_print_buttonAMouseEntered
        print_buttonA.setBackground(new java.awt.Color(237, 224, 255));
    }//GEN-LAST:event_print_buttonAMouseEntered

    private void print_buttonAMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_print_buttonAMouseExited
        print_buttonA.setBackground(new java.awt.Color(240,240,240));
    }//GEN-LAST:event_print_buttonAMouseExited

    private void insert_buttonAMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_insert_buttonAMouseEntered
        insert_buttonA.setBackground(new java.awt.Color(237, 224, 255));
    }//GEN-LAST:event_insert_buttonAMouseEntered

    private void insert_buttonAMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_insert_buttonAMouseExited
        insert_buttonA.setBackground(new java.awt.Color(240,240,240));
    }//GEN-LAST:event_insert_buttonAMouseExited

    private void edit_buttonBMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_edit_buttonBMouseEntered
        edit_buttonB.setBackground(new java.awt.Color(237, 224, 255));
    }//GEN-LAST:event_edit_buttonBMouseEntered

    private void edit_buttonBMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_edit_buttonBMouseExited
        edit_buttonB.setBackground(new java.awt.Color(240,240,240));
    }//GEN-LAST:event_edit_buttonBMouseExited

    private void refresh_buttonBMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_refresh_buttonBMouseClicked
        view_table(null);
    }//GEN-LAST:event_refresh_buttonBMouseClicked

    private void refresh_buttonBMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_refresh_buttonBMouseEntered
        refresh_buttonB.setBackground(new java.awt.Color(237, 224, 255));
    }//GEN-LAST:event_refresh_buttonBMouseEntered

    private void refresh_buttonBMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_refresh_buttonBMouseExited
        refresh_buttonB.setBackground(new java.awt.Color(240,240,240));
    }//GEN-LAST:event_refresh_buttonBMouseExited

    private void print_buttonBMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_print_buttonBMouseClicked
        try{
            path_file paths=new path_file();
            PrintWriter pw=new PrintWriter(new File((paths.csv + "\\long_roll.csv")));
            StringBuilder sb=new StringBuilder();
            String query="Select * from long_roll";
            
            sb.append("Service_no      \t");
            sb.append(",");
            sb.append("Date of Enrollemnt            \t");
            sb.append(",");
            sb.append("Date of Complement            \t");
            sb.append(",");
            sb.append("Date of Issue             \t");
            sb.append(",");
            sb.append("Date          \t");
            sb.append(",");
            sb.append("TTT         \t");
            sb.append(",");
            sb.append("MR      \t");
            sb.append(",");
            sb.append("ID Card        \t");
            sb.append(",");
            sb.append("Honour and Award     \t");
            sb.append(",");
            sb.append("Children Details        \t");
            sb.append(",");
            sb.append("Blood Group   \t");
            sb.append("\r\n");
            ResultSet rs = null;
            Statement st = con.createStatement();
            rs=st.executeQuery(query);  
            
            while(rs.next()){
                sb.append(rs.getString("Service_no"));
                sb.append(",");
                sb.append(rs.getString("DOE"));
                sb.append(",");
                sb.append(rs.getString("DOC"));
                sb.append(",");
                sb.append(rs.getString("DOI"));
                sb.append(",");
                sb.append(rs.getString("Date_"));
                sb.append(",");
                sb.append(rs.getString("TTT"));
                sb.append(",");
                sb.append(rs.getString("MR"));
                sb.append(",");
                sb.append(rs.getString("i_card"));
                sb.append(",");
                if(rs.getString("honour_award").contains(","))
                    sb.append("\""+rs.getString("honour_award")+"\"");
                else
                    sb.append(rs.getString("Courses_done"));
                sb.append(",");
                sb.append(rs.getString("Child_details"));
                sb.append(",");
                sb.append(rs.getString("blood_group"));
                sb.append("\r\n");
            }
            pw.write(sb.toString());
            pw.close();
            JOptionPane.showMessageDialog(null,"Successfully Converted to CSV");
            Desktop.getDesktop().open(new File(paths.csv + "/long_roll.csv"));
        }
        catch(Exception e){
            System.out.print("\n"+e);
            String er=e.getMessage();
            if(er.contains("The process cannot access the file because it is being used by another process")){
                JOptionPane.showMessageDialog(null,"Please close the CSV file first!");
            }
        }
    }//GEN-LAST:event_print_buttonBMouseClicked

    private void print_buttonBMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_print_buttonBMouseEntered
        print_buttonB.setBackground(new java.awt.Color(237, 224, 255));
    }//GEN-LAST:event_print_buttonBMouseEntered

    private void print_buttonBMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_print_buttonBMouseExited
        print_buttonB.setBackground(new java.awt.Color(240,240,240));
    }//GEN-LAST:event_print_buttonBMouseExited

    private void update_buttonCMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_update_buttonCMouseEntered
        update_buttonC.setBackground(new java.awt.Color(237, 224, 255));
    }//GEN-LAST:event_update_buttonCMouseEntered

    private void update_buttonCMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_update_buttonCMouseExited
        update_buttonC.setBackground(new java.awt.Color(240,240,240));
    }//GEN-LAST:event_update_buttonCMouseExited

    private void fullscreen_buttonBMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_fullscreen_buttonBMouseClicked
        fullscreen_panelB.setVisible(true);
        bg.setVisible(false);
    }//GEN-LAST:event_fullscreen_buttonBMouseClicked

    private void fullscreen_buttonBMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_fullscreen_buttonBMouseEntered
        fullscreen_buttonB.setBackground(new java.awt.Color(237, 224, 255));
    }//GEN-LAST:event_fullscreen_buttonBMouseEntered

    private void fullscreen_buttonBMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_fullscreen_buttonBMouseExited
        fullscreen_buttonB.setBackground(new java.awt.Color(240,240,240));
    }//GEN-LAST:event_fullscreen_buttonBMouseExited

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

    private void formWindowOpened(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowOpened
        //for a centered frame
        Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
        this.setLocation(dim.width/2-this.getSize().width/2, dim.height/2-this.getSize().height/2);
        //for a centered frame
        
        menuA.setBackground(new java.awt.Color(85,65,118));
        menuB.setBackground(new java.awt.Color(64,43,100));
        
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

    private void current_dateMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_current_dateMouseClicked
        String dd = new SimpleDateFormat("dd-MM-yyyy").format(new Date());
        String[] d = dd.split("-");
        DOI_date_comboboxA1.setSelectedItem(d[0]);
        DOI_month_comboboxA1.setSelectedItem(d[1]);
        DOI_year_comboboxA1.setSelectedItem(d[2]);
    }//GEN-LAST:event_current_dateMouseClicked

    private void current_dateMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_current_dateMouseEntered
        current_date.setForeground(new java.awt.Color(54,33,89));
        current_date.setBackground(new java.awt.Color(237, 224, 255));

    }//GEN-LAST:event_current_dateMouseEntered

    private void current_dateMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_current_dateMouseExited
        current_date.setForeground(new java.awt.Color(60,63,65));
        current_date.setBackground(new java.awt.Color(255, 255, 255));
    }//GEN-LAST:event_current_dateMouseExited

    private void Photo_jointMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_Photo_jointMouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_Photo_jointMouseClicked

    private void Photo_jointMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_Photo_jointMouseEntered
        Photo_joint.setBackground(new java.awt.Color(237, 224, 255));// TODO add your handling code here:
    }//GEN-LAST:event_Photo_jointMouseEntered

    private void Photo_jointMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_Photo_jointMouseExited
        Photo_joint.setBackground(new java.awt.Color(240,240,240));// TODO add your handling code here:
    }//GEN-LAST:event_Photo_jointMouseExited

    private void Photo_individualMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_Photo_individualMouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_Photo_individualMouseClicked

    private void Photo_individualMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_Photo_individualMouseEntered
        // TODO add your handling code here:
    }//GEN-LAST:event_Photo_individualMouseEntered

    private void Photo_individualMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_Photo_individualMouseExited
        // TODO add your handling code here:
    }//GEN-LAST:event_Photo_individualMouseExited

    private void current_date1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_current_date1MouseClicked
        // TODO add your handling code here:
        String dd = new SimpleDateFormat("dd-MM-yyyy").format(new Date());
        String[] d = dd.split("-");
        DOI_date_comboboxA3.setSelectedItem(d[0]);
        DOI_month_comboboxA3.setSelectedItem(d[1]);
        DOI_year_comboboxA3.setSelectedItem(d[2]);
    }//GEN-LAST:event_current_date1MouseClicked

    private void current_date1MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_current_date1MouseEntered
        // TODO add your handling code here:
    }//GEN-LAST:event_current_date1MouseEntered

    private void current_date1MouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_current_date1MouseExited
        // TODO add your handling code here:
    }//GEN-LAST:event_current_date1MouseExited

    private void edit_buttonBMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_edit_buttonBMouseClicked
        try {
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
                    String lg = "select * from long_roll where Service_no='"+sno+"'";
                    rs = st.executeQuery(lg);
                    while (rs.next()) {
                        TTT_textfieldA5.setText(rs.getString("TTT"));
                        MR_textfieldA1.setText(rs.getString("MR"));
                        TTT_textfieldA6.setText(rs.getString("i_card"));
                        TTT_textfieldA7.setText(rs.getString("honour_award"));
                        TTT_textfieldA8.setText(rs.getString("child_details"));
                        TTT_textfieldA9.setText(rs.getString("blood_group"));
                        String sp = rs.getString("DOE");
                        String[] d = sp.split("-");
                        DOE_date_comboboxA1.setSelectedItem(d[0]);
                        DOE_month_comboboxA1.setSelectedItem(d[1]);
                        DOE_year_comboboxA1.setSelectedItem(d[2]);

                        sp = rs.getString("DOC");
                        d = sp.split("-");
                        DOC_date_comboboxA1.setSelectedItem(d[0]);
                        DOC_month_comboboxA1.setSelectedItem(d[1]);
                        DOC_year_comboboxA1.setSelectedItem(d[2]);

                        sp = rs.getString("DOI");
                        d = sp.split("-");
                        DOI_date_comboboxA2.setSelectedItem(d[0]);
                        DOI_month_comboboxA2.setSelectedItem(d[1]);
                        DOI_year_comboboxA2.setSelectedItem(d[2]);

                        sp = rs.getString("Date_");
                        d = sp.split("-");
                        DOI_date_comboboxA3.setSelectedItem(d[0]);
                        DOI_month_comboboxA3.setSelectedItem(d[1]);
                        DOI_year_comboboxA3.setSelectedItem(d[2]);

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

    private void insert_buttonAMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_insert_buttonAMouseClicked
        // TODO add your handling code here:
        String sno = service_number_textfield.getText();
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
                String query = "insert into long_roll " + "values(?,?,?,?,?,?,?,?,?,?,?)";
                //date of enrollment
                String doe_year = (String) DOE_year_comboboxA.getSelectedItem();
                String doe_month = (String) DOE_month_comboboxA.getSelectedItem();
                String doe_date = (String) DOE_date_comboboxA.getSelectedItem();
                String date_of_enrollment = doe_date + "-" + doe_month + "-" + doe_year;
                int y = Integer.parseInt(doe_year);
                int m = Integer.parseInt(doe_month);
                int d = Integer.parseInt(doe_date);

                //date of completion 
                String doc_year = (String) DOC_year_comboboxA.getSelectedItem();
                String doc_month = (String) DOC_month_comboboxA.getSelectedItem();
                String doc_date = (String) DOC_date_comboboxA.getSelectedItem();
                int dy = Integer.parseInt(doc_year);
                int dm = Integer.parseInt(doc_month);
                int dd = Integer.parseInt(doc_date);
                String date_of_completion = doc_date + "-" + doc_month + "-" + doc_year;

                // date of issue DOI_date_comboboxA
                String doi_year = (String) DOI_year_comboboxA.getSelectedItem();
                String doi_month = (String) DOI_month_comboboxA.getSelectedItem();
                String doi_date = (String) DOI_date_comboboxA.getSelectedItem();
                int dyi = Integer.parseInt(doi_year);
                int dmi = Integer.parseInt(doi_month);
                int ddi = Integer.parseInt(doi_date);
                String date_of_issue = doi_date + "-" + doi_month + "-" + doi_year;

                //date DOI_date_comboboxA1
                String year = (String) DOI_year_comboboxA1.getSelectedItem();
                String month = (String) DOI_month_comboboxA1.getSelectedItem();
                String date = (String) DOI_date_comboboxA1.getSelectedItem();
                int dyy = Integer.parseInt(year);
                int dmm = Integer.parseInt(month);
                int ddd = Integer.parseInt(date);
                String date_ = date + "-" + month + "-" + year;

                String ttt = TTT_textfieldA.getText();
                String mr = MR_textfieldA.getText();
                String id = TTT_textfieldA1.getText();
                String h_a = TTT_textfieldA2.getText();
                String child = TTT_textfieldA3.getText();
                String blood = TTT_textfieldA4.getText();
                String err = "";
                boolean check_all_tf = (ttt != null && !ttt.trim().isEmpty()) && (mr != null && !mr.trim().isEmpty()) && (id != null && !id.trim().isEmpty()) && (h_a != null && !h_a.trim().isEmpty()) && (child != null && !child.trim().isEmpty()) && (blood != null && !blood.trim().isEmpty());
                boolean check_all_dd1 = !((y % 4 == 0 && m == 2 && (d > 29)) || ((m == 2 || m == 4 || m == 6 || m == 9 || m == 11) && d == 31) || (m == 2 && d > 28 && y % 4 != 0)) && !((dy % 4 == 0 && dm == 2 && (dd > 29)) || ((dm == 2 || dm == 4 || dm == 6 || dm == 9 || dm == 11) && dd == 31) || (dm == 2 && dd > 28 && dy % 4 != 0));
                boolean check_all_dd2 = !((dyi % 4 == 0 && dmi == 2 && (ddi > 29)) || ((dmi == 2 || dmi == 4 || dmi == 6 || dmi == 9 || dmi == 11) && ddi == 31) || (dmi == 2 && ddi > 28 && dyi % 4 != 0)) && !((dyy % 4 == 0 && dmm == 2 && (ddd > 29)) || ((dmm == 2 || dmm == 4 || dmm == 6 || dmm == 9 || dmm == 11) && ddd == 31) || (dmm == 2 && ddd > 28 && dyy % 4 != 0));
                boolean check_all_dd3 = (doe_year.matches("[0-9]+") && doe_month.matches("[0-9]+") && doe_date.matches("[0-9]+")) && (doc_year.matches("[0-9]+") && doc_month.matches("[0-9]+") && doc_date.matches("[0-9]+")) && (doi_year.matches("[0-9]+") && doi_month.matches("[0-9]+") && doi_date.matches("[0-9]+")) && (year.matches("[0-9]+") && month.matches("[0-9]+") && date.matches("[0-9]+"));
                if (check_all_tf && check_all_dd1 && check_all_dd2 && check_all_dd3) {
                    System.out.print("\n Inserton if of long roll");
                    stmt = con.prepareStatement(query);
                    stmt.setString(1, sno);
                    stmt.setString(2, date_of_enrollment);
                    stmt.setString(3, date_of_completion);
                    stmt.setString(4, date_of_issue);
                    stmt.setString(5, date_);
                    stmt.setString(6, ttt);
                    stmt.setString(7, mr);
                    stmt.setString(8, id);
                    stmt.setString(9, h_a);
                    stmt.setString(10, child);
                    stmt.setString(11, blood);
                    stmt.execute();
                    JOptionPane.showMessageDialog(null, "Data inserted successfully");
                    TTT_textfieldA.setText("");
                    MR_textfieldA.setText("");
                    TTT_textfieldA1.setText("");
                    TTT_textfieldA2.setText("");
                    TTT_textfieldA3.setText("");
                    TTT_textfieldA4.setText("");
                    service_number_textfield.setText("");
                    //date of enrollment
                    DOE_year_comboboxA.setSelectedIndex(0);
                    DOE_month_comboboxA.setSelectedIndex(0);
                    DOE_date_comboboxA.setSelectedIndex(0);

                    //date of completion 
                    DOC_year_comboboxA.setSelectedIndex(0);
                    DOC_month_comboboxA.setSelectedIndex(0);
                    DOC_date_comboboxA.setSelectedIndex(0);

                    // date of issue DOI_date_comboboxA
                    DOI_year_comboboxA.setSelectedIndex(0);
                    DOI_month_comboboxA.setSelectedIndex(0);
                    DOI_date_comboboxA.setSelectedIndex(0);

                    //date DOI_date_comboboxA1
                    DOI_year_comboboxA1.setSelectedIndex(0);
                    DOI_month_comboboxA1.setSelectedIndex(0);
                    DOI_date_comboboxA1.setSelectedIndex(0);

                    name.setText("> XXXXXXX");
                    rank.setText("> XXXXXXX");
                    company.setText("> XXXXXXX");
                    unit.setText("> XXXXXXX");
                    trade.setText("> XXXXXXX");
                    DOB.setText("> XXXXXXX");
                    NOK_relationB.setText("> XXXXXXX");
                    NOK_nameB.setText("> XXXXXXX");
                    NOK_mobileB.setText("> XXXXXXX");
                    villC.setText("> XXXXXXX");
                    PO_B.setText("> XXXXXXX");
                    tehsil.setText("> XXXXXXX");
                    distB.setText("> XXXXXXX");
                    PIN_B.setText("> XXXXXXX");
                    stateB.setText("> XXXXXXX");

                } else {
                    if (ttt == null || ttt.trim().isEmpty()) {
                        err = err + "\nTTT Field Empty";
                    }
                    if (mr == null || mr.trim().isEmpty()) {
                        err = err + "\nMR Field Empty";
                    }
                    if (id == null || id.trim().isEmpty()) {
                        err = err + "\nID Field Empty";
                    }
                    if (h_a == null || h_a.trim().isEmpty()) {
                        err = err + "\nHonour & Awards Field Empty";
                    }
                    if (child == null || child.trim().isEmpty()) {
                        err = err + "\nChildren Details Field Empty";
                    }
                    if (blood == null || blood.trim().isEmpty()) {
                        err = err + "\nBlood Field Empty";
                    }
                    if (((y % 4 == 0 && m == 2 && (d > 29)) || ((m == 2 || m == 4 || m == 6 || m == 9 || m == 11) && d == 31) || (m == 2 && d > 28 && y % 4 != 0))) {
                        err = err + "\nInvalid Date of enrollment";
                    }
                    if (((dy % 4 == 0 && dm == 2 && (dd > 29)) || ((dm == 2 || dm == 4 || dm == 6 || dm == 9 || dm == 11) && dd == 31) || (dm == 2 && dd > 28 && dy % 4 != 0))) {
                        err = err + "\nInvalid Date of completion";
                    }
                    if (((dyi % 4 == 0 && dmi == 2 && (ddi > 29)) || ((dmi == 2 || dmi == 4 || dmi == 6 || dmi == 9 || dmi == 11) && ddi == 31) || (dmi == 2 && ddi > 28 && dyi % 4 != 0))) {
                        err = err + "\nInvalid Date of Issue";
                    }
                    if (((dyy % 4 == 0 && dmm == 2 && (ddd > 29)) || ((dmm == 2 || dmm == 4 || dmm == 6 || dmm == 9 || dmm == 11) && ddd == 31) || (dmm == 2 && ddd > 28 && dyy % 4 != 0))) {
                        err = err + "\nInvalid Date";
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
                String query = "update long_roll set DOE=?,DOC=?,DOI=?,Date_=?,TTT=?,MR=?,i_card=?,honour_award=?,child_details=?,blood_group=? where Service_no='"+sno+"'";
                //date of enrollment
                String doe_year = (String) DOE_year_comboboxA1.getSelectedItem();
                String doe_month = (String) DOE_month_comboboxA1.getSelectedItem();
                String doe_date = (String) DOE_date_comboboxA1.getSelectedItem();
                String date_of_enrollment = doe_date + "-" + doe_month + "-" + doe_year;
                int y = Integer.parseInt(doe_year);
                int m = Integer.parseInt(doe_month);
                int d = Integer.parseInt(doe_date);

                //date of completion 
                String doc_year = (String) DOC_year_comboboxA1.getSelectedItem();
                String doc_month = (String) DOC_month_comboboxA1.getSelectedItem();
                String doc_date = (String) DOC_date_comboboxA1.getSelectedItem();
                int dy = Integer.parseInt(doc_year);
                int dm = Integer.parseInt(doc_month);
                int dd = Integer.parseInt(doc_date);
                String date_of_completion = doc_date + "-" + doc_month + "-" + doc_year;

                // date of issue DOI_date_comboboxA
                String doi_year = (String) DOI_year_comboboxA2.getSelectedItem();
                String doi_month = (String) DOI_month_comboboxA2.getSelectedItem();
                String doi_date = (String) DOI_date_comboboxA2.getSelectedItem();
                int dyi = Integer.parseInt(doi_year);
                int dmi = Integer.parseInt(doi_month);
                int ddi = Integer.parseInt(doi_date);
                String date_of_issue = doi_date + "-" + doi_month + "-" + doi_year;

                //date DOI_date_comboboxA1
                String year = (String) DOI_year_comboboxA3.getSelectedItem();
                String month = (String) DOI_month_comboboxA3.getSelectedItem();
                String date = (String) DOI_date_comboboxA3.getSelectedItem();
                int dyy = Integer.parseInt(year);
                int dmm = Integer.parseInt(month);
                int ddd = Integer.parseInt(date);
                String date_ = date + "-" + month + "-" + year;

                String ttt = TTT_textfieldA5.getText();
                String mr = MR_textfieldA1.getText();
                String id = TTT_textfieldA6.getText();
                String h_a = TTT_textfieldA7.getText();
                String child = TTT_textfieldA8.getText();
                String blood = TTT_textfieldA9.getText();
                String err = "";
                boolean check_all_tf = (ttt != null && !ttt.trim().isEmpty()) && (mr != null && !mr.trim().isEmpty()) && (id != null && !id.trim().isEmpty()) && (h_a != null && !h_a.trim().isEmpty()) && (child != null && !child.trim().isEmpty()) && (blood != null && !blood.trim().isEmpty());
                boolean check_all_dd1 = !((y % 4 == 0 && m == 2 && (d > 29)) || ((m == 2 || m == 4 || m == 6 || m == 9 || m == 11) && d == 31) || (m == 2 && d > 28 && y % 4 != 0)) && !((dy % 4 == 0 && dm == 2 && (dd > 29)) || ((dm == 2 || dm == 4 || dm == 6 || dm == 9 || dm == 11) && dd == 31) || (dm == 2 && dd > 28 && dy % 4 != 0));
                boolean check_all_dd2 = !((dyi % 4 == 0 && dmi == 2 && (ddi > 29)) || ((dmi == 2 || dmi == 4 || dmi == 6 || dmi == 9 || dmi == 11) && ddi == 31) || (dmi == 2 && ddi > 28 && dyi % 4 != 0)) && !((dyy % 4 == 0 && dmm == 2 && (ddd > 29)) || ((dmm == 2 || dmm == 4 || dmm == 6 || dmm == 9 || dmm == 11) && ddd == 31) || (dmm == 2 && ddd > 28 && dyy % 4 != 0));
                boolean check_all_dd3 = (doe_year.matches("[0-9]+") && doe_month.matches("[0-9]+") && doe_date.matches("[0-9]+")) && (doc_year.matches("[0-9]+") && doc_month.matches("[0-9]+") && doc_date.matches("[0-9]+")) && (doi_year.matches("[0-9]+") && doi_month.matches("[0-9]+") && doi_date.matches("[0-9]+")) && (year.matches("[0-9]+") && month.matches("[0-9]+") && date.matches("[0-9]+"));
                if (check_all_tf && check_all_dd1 && check_all_dd2 && check_all_dd3) {
                    System.out.print("\n Inserton if of long roll");
                    stmt = con.prepareStatement(query);
                    //stmt.setString(1, sno);
                    stmt.setString(1, date_of_enrollment);
                    stmt.setString(2, date_of_completion);
                    stmt.setString(3, date_of_issue);
                    stmt.setString(4, date_);
                    stmt.setString(5, ttt);
                    stmt.setString(6, mr);
                    stmt.setString(7, id);
                    stmt.setString(8, h_a);
                    stmt.setString(9, child);
                    stmt.setString(10, blood);
                    stmt.executeUpdate();
                    JOptionPane.showMessageDialog(null, "Data updated successfully");
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
                    TTT_textfieldA5.setText("");
                    MR_textfieldA1.setText("");
                    TTT_textfieldA6.setText("");
                    TTT_textfieldA7.setText("");
                    TTT_textfieldA8.setText("");
                    TTT_textfieldA9.setText("");
                    army_numberC.setText("> XXXXXXX");
                    //date of enrollment
                    DOE_year_comboboxA1.setSelectedIndex(0);
                    DOE_month_comboboxA1.setSelectedIndex(0);
                    DOE_date_comboboxA1.setSelectedIndex(0);

                    //date of completion 
                    DOC_year_comboboxA1.setSelectedIndex(0);
                    DOC_month_comboboxA1.setSelectedIndex(0);
                    DOC_date_comboboxA1.setSelectedIndex(0);

                    // date of issue DOI_date_comboboxA
                    DOI_year_comboboxA2.setSelectedIndex(0);
                    DOI_month_comboboxA2.setSelectedIndex(0);
                    DOI_date_comboboxA2.setSelectedIndex(0);

                    //date DOI_date_comboboxA1
                    DOI_year_comboboxA3.setSelectedIndex(0);
                    DOI_month_comboboxA3.setSelectedIndex(0);
                    DOI_date_comboboxA3.setSelectedIndex(0);

                    nameC.setText("> XXXXXXX");
                    rankC.setText("> XXXXXXX");
                    
                    

                } else {
                    if (ttt == null || ttt.trim().isEmpty()) {
                        err = err + "\nTTT Field Empty";
                    }
                    if (mr == null || mr.trim().isEmpty()) {
                        err = err + "\nMR Field Empty";
                    }
                    if (id == null || id.trim().isEmpty()) {
                        err = err + "\nID Field Empty";
                    }
                    if (h_a == null || h_a.trim().isEmpty()) {
                        err = err + "\nHonour & Awards Field Empty";
                    }
                    if (child == null || child.trim().isEmpty()) {
                        err = err + "\nChildren Details Field Empty";
                    }
                    if (blood == null || blood.trim().isEmpty()) {
                        err = err + "\nBlood Field Empty";
                    }
                    if (((y % 4 == 0 && m == 2 && (d > 29)) || ((m == 2 || m == 4 || m == 6 || m == 9 || m == 11) && d == 31) || (m == 2 && d > 28 && y % 4 != 0))) {
                        err = err + "\nInvalid Date of enrollment";
                    }
                    if (((dy % 4 == 0 && dm == 2 && (dd > 29)) || ((dm == 2 || dm == 4 || dm == 6 || dm == 9 || dm == 11) && dd == 31) || (dm == 2 && dd > 28 && dy % 4 != 0))) {
                        err = err + "\nInvalid Date of completion";
                    }
                    if (((dyi % 4 == 0 && dmi == 2 && (ddi > 29)) || ((dmi == 2 || dmi == 4 || dmi == 6 || dmi == 9 || dmi == 11) && ddi == 31) || (dmi == 2 && ddi > 28 && dyi % 4 != 0))) {
                        err = err + "\nInvalid Date of Issue";
                    }
                    if (((dyy % 4 == 0 && dmm == 2 && (ddd > 29)) || ((dmm == 2 || dmm == 4 || dmm == 6 || dmm == 9 || dmm == 11) && ddd == 31) || (dmm == 2 && ddd > 28 && dyy % 4 != 0))) {
                        err = err + "\nInvalid Date";
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

    private void select_file_buttonAMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_select_file_buttonAMouseClicked
        JFileChooser chooser = new JFileChooser();

        //applying extension filter
        chooser.setFileFilter(new FileNameExtensionFilter("Image Files", "png", "jpg", "jpeg"));

        //opening file chooser window
        chooser.showOpenDialog(null);

        File f = chooser.getSelectedFile();

        //filepath
        filepath = f.getAbsolutePath();
        select_file_labelA.setText(f.getName());
        
        scaleImagefamily(joint_photo_label, filepath);
        chooser.setSelectedFile(null);
    }//GEN-LAST:event_select_file_buttonAMouseClicked

    private void select_file_buttonAMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_select_file_buttonAMouseEntered
        select_file_buttonA.setBackground(new java.awt.Color(237, 224, 255));// TODO add your handling code here:
    }//GEN-LAST:event_select_file_buttonAMouseEntered

    private void select_file_buttonAMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_select_file_buttonAMouseExited
        select_file_buttonA.setBackground(new java.awt.Color(240, 240, 240));// TODO add your handling code here:
    }//GEN-LAST:event_select_file_buttonAMouseExited

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
            java.util.logging.Logger.getLogger(Long_Roll.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Long_Roll.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Long_Roll.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Long_Roll.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Long_Roll().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel DOB;
    private javax.swing.JLabel DOB_label;
    private javax.swing.JComboBox<String> DOC_date_comboboxA;
    private javax.swing.JComboBox<String> DOC_date_comboboxA1;
    private javax.swing.JLabel DOC_labelA;
    private javax.swing.JLabel DOC_labelA1;
    private javax.swing.JComboBox<String> DOC_month_comboboxA;
    private javax.swing.JComboBox<String> DOC_month_comboboxA1;
    private javax.swing.JComboBox<String> DOC_year_comboboxA;
    private javax.swing.JComboBox<String> DOC_year_comboboxA1;
    private javax.swing.JComboBox<String> DOE_date_comboboxA;
    private javax.swing.JComboBox<String> DOE_date_comboboxA1;
    private javax.swing.JLabel DOE_labelA;
    private javax.swing.JLabel DOE_labelA1;
    private javax.swing.JComboBox<String> DOE_month_comboboxA;
    private javax.swing.JComboBox<String> DOE_month_comboboxA1;
    private javax.swing.JComboBox<String> DOE_year_comboboxA;
    private javax.swing.JComboBox<String> DOE_year_comboboxA1;
    private javax.swing.JComboBox<String> DOI_date_comboboxA;
    private javax.swing.JComboBox<String> DOI_date_comboboxA1;
    private javax.swing.JComboBox<String> DOI_date_comboboxA2;
    private javax.swing.JComboBox<String> DOI_date_comboboxA3;
    private javax.swing.JLabel DOI_labelA;
    private javax.swing.JLabel DOI_labelA1;
    private javax.swing.JLabel DOI_labelA2;
    private javax.swing.JLabel DOI_labelA3;
    private javax.swing.JComboBox<String> DOI_month_comboboxA;
    private javax.swing.JComboBox<String> DOI_month_comboboxA1;
    private javax.swing.JComboBox<String> DOI_month_comboboxA2;
    private javax.swing.JComboBox<String> DOI_month_comboboxA3;
    private javax.swing.JComboBox<String> DOI_year_comboboxA;
    private javax.swing.JComboBox<String> DOI_year_comboboxA1;
    private javax.swing.JComboBox<String> DOI_year_comboboxA2;
    private javax.swing.JComboBox<String> DOI_year_comboboxA3;
    private javax.swing.JLabel MR_labelA;
    private javax.swing.JLabel MR_labelA1;
    private javax.swing.JTextField MR_textfieldA;
    private javax.swing.JTextField MR_textfieldA1;
    private javax.swing.JSeparator MR_underlineA;
    private javax.swing.JSeparator MR_underlineA1;
    private javax.swing.JLabel NOK_mobileB;
    private javax.swing.JLabel NOK_mobile_labelB;
    private javax.swing.JLabel NOK_nameB;
    private javax.swing.JLabel NOK_name_labelB;
    private javax.swing.JLabel NOK_relationB;
    private javax.swing.JLabel NOK_relation_labelB;
    private javax.swing.JLabel PIN_B;
    private javax.swing.JLabel PIN_labelB;
    private javax.swing.JLabel PO_B;
    private javax.swing.JLabel PO_labelB;
    private javax.swing.JPanel Photo_individual;
    private javax.swing.JPanel Photo_joint;
    private javax.swing.JScrollPane ScrollPaneA;
    private javax.swing.JScrollPane ScrollPaneB;
    private javax.swing.JScrollPane ScrollPaneC;
    private javax.swing.JSeparator Separator;
    private javax.swing.JLabel TTT_labelA;
    private javax.swing.JLabel TTT_labelA1;
    private javax.swing.JLabel TTT_labelA2;
    private javax.swing.JLabel TTT_labelA3;
    private javax.swing.JLabel TTT_labelA4;
    private javax.swing.JLabel TTT_labelA5;
    private javax.swing.JLabel TTT_labelA6;
    private javax.swing.JLabel TTT_labelA7;
    private javax.swing.JLabel TTT_labelA8;
    private javax.swing.JLabel TTT_labelA9;
    private javax.swing.JTextField TTT_textfieldA;
    private javax.swing.JTextField TTT_textfieldA1;
    private javax.swing.JTextField TTT_textfieldA2;
    private javax.swing.JTextField TTT_textfieldA3;
    private javax.swing.JTextField TTT_textfieldA4;
    private javax.swing.JTextField TTT_textfieldA5;
    private javax.swing.JTextField TTT_textfieldA6;
    private javax.swing.JTextField TTT_textfieldA7;
    private javax.swing.JTextField TTT_textfieldA8;
    private javax.swing.JTextField TTT_textfieldA9;
    private javax.swing.JSeparator TTT_underlineA;
    private javax.swing.JSeparator TTT_underlineA1;
    private javax.swing.JSeparator TTT_underlineA2;
    private javax.swing.JSeparator TTT_underlineA3;
    private javax.swing.JSeparator TTT_underlineA4;
    private javax.swing.JSeparator TTT_underlineA5;
    private javax.swing.JSeparator TTT_underlineA6;
    private javax.swing.JSeparator TTT_underlineA7;
    private javax.swing.JSeparator TTT_underlineA8;
    private javax.swing.JSeparator TTT_underlineA9;
    private javax.swing.JTable TableG_out;
    private javax.swing.JLabel address_labelB;
    private javax.swing.JLabel army_numberC;
    private javax.swing.JPanel bg;
    private javax.swing.JLabel changing_for_label;
    private javax.swing.JLabel company;
    private javax.swing.JLabel company_label;
    private javax.swing.JLabel current_date;
    private javax.swing.JLabel current_date1;
    private javax.swing.JLabel distB;
    private javax.swing.JLabel dist_labelB;
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
    private javax.swing.JLabel insert_label;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JLabel joint_photo_label;
    private javax.swing.JPanel menuA;
    private javax.swing.JLabel menuA_icon;
    private javax.swing.JLabel menuA_label;
    private javax.swing.JPanel menuB;
    private javax.swing.JLabel menuB_icon;
    private javax.swing.JLabel menuB_label;
    private javax.swing.JSeparator midB1;
    private javax.swing.JSeparator midB2;
    private javax.swing.JSeparator midB3;
    private javax.swing.JSeparator midC0;
    private javax.swing.JSeparator midC4;
    private javax.swing.JLabel minimize;
    private javax.swing.JPanel move_panel;
    private javax.swing.JLabel name;
    private javax.swing.JLabel nameC;
    private javax.swing.JLabel name_label;
    private javax.swing.JLabel name_labelC;
    private javax.swing.JLabel okay;
    private javax.swing.JLabel okayB;
    private javax.swing.JPanel panelA;
    private javax.swing.JPanel panelB;
    private javax.swing.JPanel panelC;
    private javax.swing.JSeparator panelC_bottom_line;
    private javax.swing.JSeparator panelC_bottom_line1;
    private javax.swing.JLabel photo_label;
    private javax.swing.JPanel print_buttonA;
    private javax.swing.JPanel print_buttonB;
    private javax.swing.JLabel print_label;
    private javax.swing.JLabel print_labelB;
    private javax.swing.JLabel rank;
    private javax.swing.JLabel rankC;
    private javax.swing.JLabel rank_label;
    private javax.swing.JLabel rank_labelC;
    private javax.swing.JPanel refresh_buttonB;
    private javax.swing.JLabel refresh_labelB;
    private javax.swing.JPanel select_file_buttonA;
    private javax.swing.JLabel select_file_labelA;
    private javax.swing.JTextField service_numberB;
    private javax.swing.JLabel service_number_label;
    private javax.swing.JLabel service_number_labelB;
    private javax.swing.JLabel service_number_labelC;
    private javax.swing.JTextField service_number_textfield;
    private javax.swing.JSeparator service_number_underline;
    private javax.swing.JPanel side_pane;
    private javax.swing.JLabel stateB;
    private javax.swing.JLabel state_labelB;
    private javax.swing.JLabel teh_labelB;
    private javax.swing.JLabel tehsil;
    private javax.swing.JSeparator textfield_underline;
    private javax.swing.JLabel title_icon;
    private javax.swing.JLabel title_label;
    private javax.swing.JLabel trade;
    private javax.swing.JLabel trade_label;
    private javax.swing.JLabel unit;
    private javax.swing.JLabel unit_label;
    private javax.swing.JPanel update_buttonC;
    private javax.swing.JLabel update_labelC;
    private javax.swing.JLabel upload_photo_labelA;
    private javax.swing.JLabel villC;
    private javax.swing.JLabel vill_labelB;
    // End of variables declaration//GEN-END:variables
}
