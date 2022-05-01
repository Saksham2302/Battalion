
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Toolkit;
import java.awt.print.Book;
import java.awt.print.PageFormat;
import java.awt.print.Printable;
import java.awt.print.PrinterException;
import java.awt.print.PrinterJob;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import javax.swing.JOptionPane;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author chise
 */
public class Bank_Details extends javax.swing.JFrame {

    private boolean go_back;
    /**
     * Creates new form Bank_Details
     */
    private int mouseX, mouseY;
    Database db=new Database();
    Connection con = db.create_connection(true);
    public Bank_Details() {
        initComponents();
        
        ScrollPaneA.getVerticalScrollBar().setUnitIncrement(16);

    }
    

    private String retrieve_Army_No() {
        String outpass_name = "";
        try {
            Statement stmt = con.createStatement();
            String army_no = service_number_textfield.getText().trim();
            if (army_no.length() != 0) {
                System.out.println("processing SQL");
                String Query = "Select * from attach_in where Service_no = '" + service_number_textfield.getText()+"'";
                ResultSet rs = stmt.executeQuery(Query);
                String name_, rank_, company_, kin_name_, indl_pan_, kin_pan_, indl_aadhar_, kin_aadhar_, indl_bank_, kin_bank_, indl_branch_, kin_branch_, indl_acc_, kin_acc_, indl_ifsc_, kin_ifsc_;
                if (rs.next()) {
                    name_ = rs.getString("Name");
                    rank_ = rs.getString("Rank_");
                    company_ = rs.getString("company");
                    indl_pan_ = rs.getString("indl_pan");
                    indl_aadhar_ = rs.getString("indl_aadhar");
                    indl_bank_ = rs.getString("indl_bank_name");
                    indl_branch_ = rs.getString("indl_branch_name");
                    indl_acc_ = rs.getString("indl_bank_acc");
                    indl_ifsc_ = rs.getString("indl_ifsc");
                    kin_name_ = rs.getString("kin_name");
                    kin_pan_ = rs.getString("kin_pan");
                    kin_aadhar_ = rs.getString("kin_aadhar");
                    kin_bank_ = rs.getString("kin_bank_name");
                    kin_branch_ = rs.getString("kin_branch_name");
                    kin_acc_ = rs.getString("kin_bank_acc");
                    kin_ifsc_ = rs.getString("kin_ifsc");
                    name.setText("> " + name_);
                    rank.setText("> " + rank_);
                    company.setText("> " + company_);
                    indl_PAN.setText("> " + indl_pan_);
                    indl_aadhar.setText("> " + indl_aadhar_);
                    indl_bank.setText("> " + indl_bank_);
                    indl_branch.setText("> " + indl_branch_);
                    indl_account.setText("> " + indl_acc_);
                    indl_IFSC.setText("> " + indl_ifsc_);
                    kin_NOK.setText("> " + kin_name_);
                    kin_PAN.setText("> " + kin_pan_);
                    kin_aadhar.setText("> " + kin_aadhar_);
                    kin_bank.setText("> " + kin_bank_);
                    kin_branch.setText("> " + kin_branch_);
                    kin_account.setText("> " + kin_acc_);
                    kin_IFSC.setText("> " + kin_ifsc_);

                    outpass_name = name_;
                } else {
                    Query = "Select * from post_in where Service_no = '" + service_number_textfield.getText()+"';";
                    rs = stmt.executeQuery(Query);
                    if (rs.next()) {
                        name_ = rs.getString("Name");
                        rank_ = rs.getString("Rank_");
                        company_ = rs.getString("company");
                        indl_pan_ = rs.getString("indl_pan");
                        indl_aadhar_ = rs.getString("indl_aadhar");
                        indl_bank_ = rs.getString("indl_bank_name");
                        indl_branch_ = rs.getString("indl_branch_name");
                        indl_acc_ = rs.getString("indl_bank_acc");
                        indl_ifsc_ = rs.getString("indl_ifsc");
                        kin_name_ = rs.getString("kin_name");
                        kin_pan_ = rs.getString("kin_pan");
                        kin_aadhar_ = rs.getString("kin_aadhar");
                        kin_bank_ = rs.getString("kin_bank_name");
                        kin_branch_ = rs.getString("kin_branch_name");
                        kin_acc_ = rs.getString("kin_bank_acc");
                        kin_ifsc_ = rs.getString("kin_ifsc");
                        name.setText("> " + name_);
                        rank.setText("> " + rank_);
                        company.setText("> " + company_);
                        indl_PAN.setText("> " + indl_pan_);
                        indl_aadhar.setText("> " + indl_aadhar_);
                        indl_bank.setText("> " + indl_bank_);
                        indl_branch.setText("> " + indl_branch_);
                        indl_account.setText("> " + indl_acc_);
                        indl_IFSC.setText("> " + indl_ifsc_);
                        kin_NOK.setText("> " + kin_name_);
                        kin_PAN.setText("> " + kin_pan_);
                        kin_aadhar.setText("> " + kin_aadhar_);
                        kin_bank.setText("> " + kin_bank_);
                        kin_branch.setText("> " + kin_branch_);
                        kin_account.setText("> " + kin_acc_);
                        kin_IFSC.setText("> " + kin_ifsc_);

                        outpass_name = name_;
                    } else {
                        Query = "Select * from new_registration where Service_no = '" + service_number_textfield.getText()+"';";
                        rs = stmt.executeQuery(Query);
                        if (rs.next()) {
                            name_ = rs.getString("Name");
                            rank_ = rs.getString("Rank_");
                            company_ = rs.getString("company");
                            indl_pan_ = rs.getString("indl_pan");
                            indl_aadhar_ = rs.getString("indl_aadhar");
                            indl_bank_ = rs.getString("indl_bank_name");
                            indl_branch_ = rs.getString("indl_bank_branch");
                            indl_acc_ = rs.getString("indl_acc_no");
                            indl_ifsc_ = rs.getString("indl_ifsc");
                            kin_name_ = rs.getString("nok");
                            kin_pan_ = rs.getString("kin_pan");
                            kin_aadhar_ = rs.getString("kin_aadhar");
                            kin_bank_ = rs.getString("kin_bank_name");
                            kin_branch_ = rs.getString("kin_bank_branch");
                            kin_acc_ = rs.getString("kin_acc_no");
                            kin_ifsc_ = rs.getString("kin_ifsc");
                            name.setText("> " + name_);
                            rank.setText("> " + rank_);
                            company.setText("> " + company_);
                            indl_PAN.setText("> " + indl_pan_);
                            indl_aadhar.setText("> " + indl_aadhar_);
                            indl_bank.setText("> " + indl_bank_);
                            indl_branch.setText("> " + indl_branch_);
                            indl_account.setText("> " + indl_acc_);
                            indl_IFSC.setText("> " + indl_ifsc_);
                            kin_NOK.setText("> " + kin_name_);
                            kin_PAN.setText("> " + kin_pan_);
                            kin_aadhar.setText("> " + kin_aadhar_);
                            kin_bank.setText("> " + kin_bank_);
                            kin_branch.setText("> " + kin_branch_);
                            kin_account.setText("> " + kin_acc_);
                            kin_IFSC.setText("> " + kin_ifsc_);

                            outpass_name = name_;
                        }
                    }
                }

                if (outpass_name.equals("")) {
                    JOptionPane.showMessageDialog(null, "Army Number does not exist!", "Error", JOptionPane.ERROR_MESSAGE);
                    name.setText("> XXXXXXX");
                    rank.setText("> XXXXXXX");
                    company.setText("> XXXXXXX");
                    indl_PAN.setText("> XXXXXXX");
                    indl_aadhar.setText("> XXXXXXX");
                    indl_bank.setText("> XXXXXXX");
                    indl_branch.setText("> XXXXXXX");
                    indl_account.setText("> XXXXXXX");
                    indl_IFSC.setText("> XXXXXXX");
                    kin_NOK.setText("> XXXXXXX");
                    kin_PAN.setText("> XXXXXXX");
                    kin_aadhar.setText("> XXXXXXX");
                    kin_bank.setText("> XXXXXXX");
                    kin_branch.setText("> XXXXXXX");
                    kin_account.setText("> XXXXXXX");
                    kin_IFSC.setText("> XXXXXXX");
                }
            } else {
                JOptionPane.showMessageDialog(null, "Army Number cannot be empty!", "Error", JOptionPane.ERROR_MESSAGE);

            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
        }
        return outpass_name;
    }

    class Page1 implements Printable {

        public int print(Graphics graphics, PageFormat pageFormat, int pageIndex) throws PrinterException {

            // Check If No Printable Content
            if (pageIndex >= 2) {
                return Printable.NO_SUCH_PAGE;
            }
            Graphics2D graphics2D = (Graphics2D) graphics;

            graphics2D.setClip(0, 0, 690, 700); //shapes the printing rectangle in respect to translate
            graphics2D.translate(10, 0); //marks the top of the page

            graphics2D.scale(0.68, 0.7);
            panelA.paint(graphics2D);

            // return if page exists
            return Printable.PAGE_EXISTS;
        }
    }

    private void PrintRecord() {
        PrinterJob printerjob = PrinterJob.getPrinterJob();
        PageFormat pageformat = printerjob.defaultPage();
        Book bk = new Book();

        bk.append(new Page1(), pageformat);

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
        indl_ID_details_label = new javax.swing.JLabel();
        midA3 = new javax.swing.JSeparator();
        NOK_detailsA = new javax.swing.JLabel();
        midA4 = new javax.swing.JSeparator();
        indl_PAN = new javax.swing.JLabel();
        indl_bank_label = new javax.swing.JLabel();
        indl_bank = new javax.swing.JLabel();
        indl_account_label = new javax.swing.JLabel();
        indl_account = new javax.swing.JLabel();
        indl_aadhar_label = new javax.swing.JLabel();
        indl_aadhar = new javax.swing.JLabel();
        indl_IFSC_label = new javax.swing.JLabel();
        indl_IFSC = new javax.swing.JLabel();
        indl_PAN_label = new javax.swing.JLabel();
        indl_branch_label = new javax.swing.JLabel();
        indl_branch = new javax.swing.JLabel();
        kin_NOK = new javax.swing.JLabel();
        kin_aadhar_label = new javax.swing.JLabel();
        kin_aadhar = new javax.swing.JLabel();
        kin_account_label = new javax.swing.JLabel();
        kin_account = new javax.swing.JLabel();
        kin_PAN_label = new javax.swing.JLabel();
        kin_PAN = new javax.swing.JLabel();
        kin_IFSC_label = new javax.swing.JLabel();
        kin_IFSC = new javax.swing.JLabel();
        kin_NOK_label = new javax.swing.JLabel();
        kin_branch_label = new javax.swing.JLabel();
        kin_branch = new javax.swing.JLabel();
        kin_bank_label = new javax.swing.JLabel();
        kin_bank = new javax.swing.JLabel();
        print_buttonA = new javax.swing.JPanel();
        print_label = new javax.swing.JLabel();

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

        menuA_icon.setForeground(new java.awt.Color(255, 255, 255));
        menuA_icon.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        menuA_icon.setText("A");

        menuA_label.setFont(new java.awt.Font("Roboto", 1, 18)); // NOI18N
        menuA_label.setForeground(new java.awt.Color(255, 255, 255));
        menuA_label.setText("View");

        javax.swing.GroupLayout menuALayout = new javax.swing.GroupLayout(menuA);
        menuA.setLayout(menuALayout);
        menuALayout.setHorizontalGroup(
            menuALayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(menuALayout.createSequentialGroup()
                .addGap(27, 27, 27)
                .addComponent(menuA_icon)
                .addGap(33, 33, 33)
                .addComponent(menuA_label)
                .addContainerGap(220, Short.MAX_VALUE))
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
        title_label.setText("Bank Details");
        side_pane.add(title_label, new org.netbeans.lib.awtextra.AbsoluteConstraints(90, 135, 200, 40));

        title_icon.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        title_icon.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/PNGS/bank white.png"))); // NOI18N
        side_pane.add(title_icon, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 110, 60, 70));

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
        panelA.add(panelA_bottom_line, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 960, 910, -1));

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
        panelA.add(name_label, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 170, -1, 50));

        name.setFont(new java.awt.Font("Roboto Bk", 0, 24)); // NOI18N
        name.setForeground(new java.awt.Color(54, 33, 89));
        name.setText("> XXXXXXX");
        panelA.add(name, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 210, -1, 30));

        rank_label.setBackground(new java.awt.Color(255, 255, 255));
        rank_label.setFont(new java.awt.Font("Montserrat", 0, 28)); // NOI18N
        rank_label.setForeground(new java.awt.Color(51, 51, 51));
        rank_label.setText("Rank");
        panelA.add(rank_label, new org.netbeans.lib.awtextra.AbsoluteConstraints(350, 170, -1, 50));

        rank.setFont(new java.awt.Font("Roboto Bk", 0, 24)); // NOI18N
        rank.setForeground(new java.awt.Color(54, 33, 89));
        rank.setText("> XXXXXXX");
        panelA.add(rank, new org.netbeans.lib.awtextra.AbsoluteConstraints(350, 210, -1, 30));

        company_label.setBackground(new java.awt.Color(255, 255, 255));
        company_label.setFont(new java.awt.Font("Montserrat", 0, 28)); // NOI18N
        company_label.setForeground(new java.awt.Color(51, 51, 51));
        company_label.setText("Company");
        panelA.add(company_label, new org.netbeans.lib.awtextra.AbsoluteConstraints(618, 170, 140, 50));

        company.setFont(new java.awt.Font("Roboto Bk", 0, 24)); // NOI18N
        company.setForeground(new java.awt.Color(54, 33, 89));
        company.setText("> XXXXXXX");
        panelA.add(company, new org.netbeans.lib.awtextra.AbsoluteConstraints(623, 210, 160, 30));

        indl_ID_details_label.setFont(new java.awt.Font("Montserrat", 0, 28)); // NOI18N
        indl_ID_details_label.setForeground(new java.awt.Color(51, 51, 51));
        indl_ID_details_label.setText("Indl Details");
        panelA.add(indl_ID_details_label, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 330, -1, 40));

        midA3.setForeground(new java.awt.Color(204, 204, 255));
        panelA.add(midA3, new org.netbeans.lib.awtextra.AbsoluteConstraints(240, 290, 390, 10));

        NOK_detailsA.setFont(new java.awt.Font("Montserrat", 0, 28)); // NOI18N
        NOK_detailsA.setForeground(new java.awt.Color(51, 51, 51));
        NOK_detailsA.setText("Details of Kin");
        panelA.add(NOK_detailsA, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 620, -1, 40));

        midA4.setForeground(new java.awt.Color(204, 204, 255));
        panelA.add(midA4, new org.netbeans.lib.awtextra.AbsoluteConstraints(240, 580, 390, 10));

        indl_PAN.setFont(new java.awt.Font("Roboto Bk", 0, 24)); // NOI18N
        indl_PAN.setForeground(new java.awt.Color(54, 33, 89));
        indl_PAN.setText("> XXXXXXX");
        panelA.add(indl_PAN, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 420, -1, 30));

        indl_bank_label.setBackground(new java.awt.Color(255, 255, 255));
        indl_bank_label.setFont(new java.awt.Font("Montserrat", 0, 24)); // NOI18N
        indl_bank_label.setForeground(new java.awt.Color(66, 50, 77));
        indl_bank_label.setText("Bank Name");
        panelA.add(indl_bank_label, new org.netbeans.lib.awtextra.AbsoluteConstraints(620, 380, -1, 50));

        indl_bank.setFont(new java.awt.Font("Roboto Bk", 0, 24)); // NOI18N
        indl_bank.setForeground(new java.awt.Color(54, 33, 89));
        indl_bank.setText("> XXXXXXX");
        panelA.add(indl_bank, new org.netbeans.lib.awtextra.AbsoluteConstraints(620, 420, -1, 30));

        indl_account_label.setBackground(new java.awt.Color(255, 255, 255));
        indl_account_label.setFont(new java.awt.Font("Montserrat", 0, 24)); // NOI18N
        indl_account_label.setForeground(new java.awt.Color(66, 50, 77));
        indl_account_label.setText("Account Num");
        panelA.add(indl_account_label, new org.netbeans.lib.awtextra.AbsoluteConstraints(350, 460, -1, 50));

        indl_account.setFont(new java.awt.Font("Roboto Bk", 0, 24)); // NOI18N
        indl_account.setForeground(new java.awt.Color(54, 33, 89));
        indl_account.setText("> XXXXXXX");
        panelA.add(indl_account, new org.netbeans.lib.awtextra.AbsoluteConstraints(350, 500, -1, 30));

        indl_aadhar_label.setBackground(new java.awt.Color(255, 255, 255));
        indl_aadhar_label.setFont(new java.awt.Font("Montserrat", 0, 24)); // NOI18N
        indl_aadhar_label.setForeground(new java.awt.Color(66, 50, 77));
        indl_aadhar_label.setText("Aadhar");
        panelA.add(indl_aadhar_label, new org.netbeans.lib.awtextra.AbsoluteConstraints(350, 380, -1, 50));

        indl_aadhar.setFont(new java.awt.Font("Roboto Bk", 0, 24)); // NOI18N
        indl_aadhar.setForeground(new java.awt.Color(54, 33, 89));
        indl_aadhar.setText("> XXXXXXX");
        panelA.add(indl_aadhar, new org.netbeans.lib.awtextra.AbsoluteConstraints(350, 420, -1, 30));

        indl_IFSC_label.setBackground(new java.awt.Color(255, 255, 255));
        indl_IFSC_label.setFont(new java.awt.Font("Montserrat", 0, 24)); // NOI18N
        indl_IFSC_label.setForeground(new java.awt.Color(66, 50, 77));
        indl_IFSC_label.setText("IFSC");
        panelA.add(indl_IFSC_label, new org.netbeans.lib.awtextra.AbsoluteConstraints(620, 460, -1, 50));

        indl_IFSC.setFont(new java.awt.Font("Roboto Bk", 0, 24)); // NOI18N
        indl_IFSC.setForeground(new java.awt.Color(54, 33, 89));
        indl_IFSC.setText("> XXXXXXX");
        panelA.add(indl_IFSC, new org.netbeans.lib.awtextra.AbsoluteConstraints(620, 500, -1, 30));

        indl_PAN_label.setBackground(new java.awt.Color(255, 255, 255));
        indl_PAN_label.setFont(new java.awt.Font("Montserrat", 0, 24)); // NOI18N
        indl_PAN_label.setForeground(new java.awt.Color(66, 50, 77));
        indl_PAN_label.setText("PAN");
        panelA.add(indl_PAN_label, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 380, -1, 50));

        indl_branch_label.setBackground(new java.awt.Color(255, 255, 255));
        indl_branch_label.setFont(new java.awt.Font("Montserrat", 0, 24)); // NOI18N
        indl_branch_label.setForeground(new java.awt.Color(66, 50, 77));
        indl_branch_label.setText("Branch");
        panelA.add(indl_branch_label, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 460, -1, 50));

        indl_branch.setFont(new java.awt.Font("Roboto Bk", 0, 24)); // NOI18N
        indl_branch.setForeground(new java.awt.Color(54, 33, 89));
        indl_branch.setText("> XXXXXXX");
        panelA.add(indl_branch, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 500, -1, 30));

        kin_NOK.setFont(new java.awt.Font("Roboto Bk", 0, 24)); // NOI18N
        kin_NOK.setForeground(new java.awt.Color(54, 33, 89));
        kin_NOK.setText("> XXXXXXX");
        panelA.add(kin_NOK, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 710, -1, 30));

        kin_aadhar_label.setBackground(new java.awt.Color(255, 255, 255));
        kin_aadhar_label.setFont(new java.awt.Font("Montserrat", 0, 24)); // NOI18N
        kin_aadhar_label.setForeground(new java.awt.Color(66, 50, 77));
        kin_aadhar_label.setText("Aadhar");
        panelA.add(kin_aadhar_label, new org.netbeans.lib.awtextra.AbsoluteConstraints(620, 670, -1, 50));

        kin_aadhar.setFont(new java.awt.Font("Roboto Bk", 0, 24)); // NOI18N
        kin_aadhar.setForeground(new java.awt.Color(54, 33, 89));
        kin_aadhar.setText("> XXXXXXX");
        panelA.add(kin_aadhar, new org.netbeans.lib.awtextra.AbsoluteConstraints(620, 710, -1, 30));

        kin_account_label.setBackground(new java.awt.Color(255, 255, 255));
        kin_account_label.setFont(new java.awt.Font("Montserrat", 0, 24)); // NOI18N
        kin_account_label.setForeground(new java.awt.Color(66, 50, 77));
        kin_account_label.setText("Account Num");
        panelA.add(kin_account_label, new org.netbeans.lib.awtextra.AbsoluteConstraints(620, 750, -1, 50));

        kin_account.setFont(new java.awt.Font("Roboto Bk", 0, 24)); // NOI18N
        kin_account.setForeground(new java.awt.Color(54, 33, 89));
        kin_account.setText("> XXXXXXX");
        panelA.add(kin_account, new org.netbeans.lib.awtextra.AbsoluteConstraints(620, 790, -1, 30));

        kin_PAN_label.setBackground(new java.awt.Color(255, 255, 255));
        kin_PAN_label.setFont(new java.awt.Font("Montserrat", 0, 24)); // NOI18N
        kin_PAN_label.setForeground(new java.awt.Color(66, 50, 77));
        kin_PAN_label.setText("PAN");
        panelA.add(kin_PAN_label, new org.netbeans.lib.awtextra.AbsoluteConstraints(350, 670, -1, 50));

        kin_PAN.setFont(new java.awt.Font("Roboto Bk", 0, 24)); // NOI18N
        kin_PAN.setForeground(new java.awt.Color(54, 33, 89));
        kin_PAN.setText("> XXXXXXX");
        panelA.add(kin_PAN, new org.netbeans.lib.awtextra.AbsoluteConstraints(350, 710, -1, 30));

        kin_IFSC_label.setBackground(new java.awt.Color(255, 255, 255));
        kin_IFSC_label.setFont(new java.awt.Font("Montserrat", 0, 24)); // NOI18N
        kin_IFSC_label.setForeground(new java.awt.Color(66, 50, 77));
        kin_IFSC_label.setText("IFSC");
        panelA.add(kin_IFSC_label, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 830, -1, 50));

        kin_IFSC.setFont(new java.awt.Font("Roboto Bk", 0, 24)); // NOI18N
        kin_IFSC.setForeground(new java.awt.Color(54, 33, 89));
        kin_IFSC.setText("> XXXXXXX");
        panelA.add(kin_IFSC, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 870, -1, 30));

        kin_NOK_label.setBackground(new java.awt.Color(255, 255, 255));
        kin_NOK_label.setFont(new java.awt.Font("Montserrat", 0, 24)); // NOI18N
        kin_NOK_label.setForeground(new java.awt.Color(66, 50, 77));
        kin_NOK_label.setText("NOK");
        panelA.add(kin_NOK_label, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 670, -1, 50));

        kin_branch_label.setBackground(new java.awt.Color(255, 255, 255));
        kin_branch_label.setFont(new java.awt.Font("Montserrat", 0, 24)); // NOI18N
        kin_branch_label.setForeground(new java.awt.Color(66, 50, 77));
        kin_branch_label.setText("Branch");
        panelA.add(kin_branch_label, new org.netbeans.lib.awtextra.AbsoluteConstraints(350, 750, -1, 50));

        kin_branch.setFont(new java.awt.Font("Roboto Bk", 0, 24)); // NOI18N
        kin_branch.setForeground(new java.awt.Color(54, 33, 89));
        kin_branch.setText("> XXXXXXX");
        panelA.add(kin_branch, new org.netbeans.lib.awtextra.AbsoluteConstraints(350, 790, -1, 30));

        kin_bank_label.setBackground(new java.awt.Color(255, 255, 255));
        kin_bank_label.setFont(new java.awt.Font("Montserrat", 0, 24)); // NOI18N
        kin_bank_label.setForeground(new java.awt.Color(66, 50, 77));
        kin_bank_label.setText("Bank Name");
        panelA.add(kin_bank_label, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 750, -1, 50));

        kin_bank.setFont(new java.awt.Font("Roboto Bk", 0, 24)); // NOI18N
        kin_bank.setForeground(new java.awt.Color(54, 33, 89));
        kin_bank.setText("> XXXXXXX");
        panelA.add(kin_bank, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 790, -1, 30));

        ScrollPaneA.setViewportView(panelA);

        bg.add(ScrollPaneA, new org.netbeans.lib.awtextra.AbsoluteConstraints(340, 192, 910, 470));

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

        print_label.setBackground(new java.awt.Color(64, 43, 100));
        print_label.setFont(new java.awt.Font("Roboto", 0, 14)); // NOI18N
        print_label.setForeground(new java.awt.Color(64, 43, 100));
        print_label.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        print_label.setText("PRINT");
        print_buttonA.add(print_label, new org.netbeans.lib.awtextra.AbsoluteConstraints(52, 13, 71, 24));

        bg.add(print_buttonA, new org.netbeans.lib.awtextra.AbsoluteConstraints(1070, 120, 180, 50));

        getContentPane().add(bg, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, -1, 730));

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
        if (go_back) {
            new Modules(0).setVisible(true);
            this.setVisible(false);
            
        }
        go_back = false;

    }//GEN-LAST:event_go_back_labelMouseClicked

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

    private void okayMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_okayMouseClicked
        retrieve_Army_No();
    }//GEN-LAST:event_okayMouseClicked

    private void formWindowOpened(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowOpened
        //for a centered frame
        Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
        this.setLocation(dim.width / 2 - this.getSize().width / 2, dim.height / 2 - this.getSize().height / 2);
        //for a centered frame

        go_back=true;
    }//GEN-LAST:event_formWindowOpened

    private void service_number_textfieldKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_service_number_textfieldKeyPressed
        if (evt.getKeyCode() == java.awt.event.KeyEvent.VK_ENTER) {
            okayMouseClicked(null);
        }
    }//GEN-LAST:event_service_number_textfieldKeyPressed

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
            java.util.logging.Logger.getLogger(Bank_Details.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Bank_Details.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Bank_Details.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Bank_Details.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Bank_Details().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel NOK_detailsA;
    private javax.swing.JScrollPane ScrollPaneA;
    private javax.swing.JSeparator Separator;
    private javax.swing.JPanel bg;
    private javax.swing.JLabel company;
    private javax.swing.JLabel company_label;
    private javax.swing.JLabel exit_icon;
    private javax.swing.JLabel go_back_label;
    private javax.swing.JLabel indl_ID_details_label;
    private javax.swing.JLabel indl_IFSC;
    private javax.swing.JLabel indl_IFSC_label;
    private javax.swing.JLabel indl_PAN;
    private javax.swing.JLabel indl_PAN_label;
    private javax.swing.JLabel indl_aadhar;
    private javax.swing.JLabel indl_aadhar_label;
    private javax.swing.JLabel indl_account;
    private javax.swing.JLabel indl_account_label;
    private javax.swing.JLabel indl_bank;
    private javax.swing.JLabel indl_bank_label;
    private javax.swing.JLabel indl_branch;
    private javax.swing.JLabel indl_branch_label;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JLabel kin_IFSC;
    private javax.swing.JLabel kin_IFSC_label;
    private javax.swing.JLabel kin_NOK;
    private javax.swing.JLabel kin_NOK_label;
    private javax.swing.JLabel kin_PAN;
    private javax.swing.JLabel kin_PAN_label;
    private javax.swing.JLabel kin_aadhar;
    private javax.swing.JLabel kin_aadhar_label;
    private javax.swing.JLabel kin_account;
    private javax.swing.JLabel kin_account_label;
    private javax.swing.JLabel kin_bank;
    private javax.swing.JLabel kin_bank_label;
    private javax.swing.JLabel kin_branch;
    private javax.swing.JLabel kin_branch_label;
    private javax.swing.JPanel menuA;
    private javax.swing.JLabel menuA_icon;
    private javax.swing.JLabel menuA_label;
    private javax.swing.JSeparator midA3;
    private javax.swing.JSeparator midA4;
    private javax.swing.JLabel minimize;
    private javax.swing.JPanel move_panel;
    private javax.swing.JLabel name;
    private javax.swing.JLabel name_label;
    private javax.swing.JLabel okay;
    private javax.swing.JPanel panelA;
    private javax.swing.JSeparator panelA_bottom_line;
    private javax.swing.JPanel print_buttonA;
    private javax.swing.JLabel print_label;
    private javax.swing.JLabel rank;
    private javax.swing.JLabel rank_label;
    private javax.swing.JLabel service_number_label;
    private javax.swing.JTextField service_number_textfield;
    private javax.swing.JSeparator service_number_underline;
    private javax.swing.JPanel side_pane;
    private javax.swing.JLabel title_icon;
    private javax.swing.JLabel title_label;
    // End of variables declaration//GEN-END:variables
}
