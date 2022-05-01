
import java.awt.Desktop;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Toolkit;
import java.awt.print.Book;
import java.awt.print.PageFormat;
import java.awt.print.Printable;
import java.awt.print.PrinterException;
import java.awt.print.PrinterJob;
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

/* To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author chise
 */
public class JCO_Mess extends javax.swing.JFrame {

    private String VR_panel = "";
    private boolean go_back;
    /**
     * Creates new form JCOs_Mess
     */
    DefaultTableModel model_table, fullscreen_table;

    Database db = new Database();
    Connection con = db.create_connection(true);

    private int mouseX, mouseY;

    public JCO_Mess() {
        initComponents();
        
        ScrollPaneA.getVerticalScrollBar().setUnitIncrement(16);
        ScrollPaneB.getVerticalScrollBar().setUnitIncrement(16);
        fullscreen_ScrollPaneB.getVerticalScrollBar().setUnitIncrement(16);

        menuA.setBackground(new java.awt.Color(85, 65, 118));
        menuB.setBackground(new java.awt.Color(64, 43, 100));

        insert_buttonA.setVisible(true);
        print_buttonA.setVisible(true);
        print_buttonB.setVisible(false);
        refresh_buttonB.setVisible(false);
        fullscreen_buttonB.setVisible(false);
        reset_buttonA.setVisible(true);

        ScrollPaneA.setVisible(true);
        panelB.setVisible(false);
        fullscreen_panelB.setVisible(false);

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

    private void current_date() {
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd-MM-yyyy");
        LocalDateTime now = LocalDateTime.now();
        String[] day = dtf.format(now).split("-");

        date_comboboxA.setSelectedItem(day[0]);
        month_comboboxA.setSelectedItem(day[1]);
        year_comboboxA.setSelectedItem(day[2]);

    }

    private boolean viewByVR() {
        boolean flag = false;
        try {
            Statement stmt = con.createStatement();
            VR_panel = VR_textfieldB.getText().trim();

            if (VR_panel.length() != 0) {
                String Query1 = "Select * from JCO_mess_receipt where voucher='" + VR_panel + "' order by date, Voucher;";
                String Query2 = "Select * from JCO_mess_payment where voucher='" + VR_panel + "' order by date, voucher;";
                ResultSet rs = stmt.executeQuery(Query1);
                String voucher, date, type, Received_from, on_account, cash, bank, mess_fund, MMA, entertainment, Paper, silver, JCO_security, Momento, Wine, regt_cutting, M_S, sy_DR, sy_CR, fdr, property;
                int sno = 0;
                model_table.setRowCount(0);
                fullscreen_table.setRowCount(0);
                while (rs.next()) {
                    flag = true;
                    sno++;
                    voucher = rs.getString("voucher");
                    date = rs.getString("date");
                    Received_from = rs.getString("Received_from");
                    on_account = rs.getString("on_account");
                    cash = rs.getString("cash");
                    bank = rs.getString("bank");
                    mess_fund = rs.getString("mess_fund");
                    MMA = rs.getString("MMA");
                    entertainment = rs.getString("entertainment");
                    Paper = rs.getString("paper");
                    silver = rs.getString("silver");
                    JCO_security = rs.getString("JCO_security");
                    Momento = rs.getString("Momento");
                    Wine = rs.getString("wine");
                    regt_cutting = rs.getString("regt_cutting");
                    M_S = rs.getString("M_S");
                    sy_DR = rs.getString("sy_DR");
                    sy_CR = rs.getString("sy_CR");
                    fdr = rs.getString("FDR");
                    property = rs.getString("property");

                    model_table.addRow(new Object[]{
                        sno, VR_panel, date, "Receipt"
                    });
                    fullscreen_table.addRow(new Object[]{
                        sno, VR_panel, date, "Receipt", Received_from, on_account, cash, bank, mess_fund, MMA, entertainment, Paper, silver, JCO_security, Momento, Wine, regt_cutting, M_S, sy_DR, sy_CR, fdr, property
                    });
                }
                rs = stmt.executeQuery(Query2);
                while (rs.next()) {
                    flag = true;
                    sno++;
                    voucher = rs.getString("voucher");
                    date = rs.getString("date");
                    Received_from = rs.getString("Received_from");
                    on_account = rs.getString("on_account");
                    cash = rs.getString("cash");
                    bank = rs.getString("bank");
                    mess_fund = rs.getString("mess_fund");
                    MMA = rs.getString("MMA");
                    entertainment = rs.getString("entertainment");
                    Paper = rs.getString("paper");
                    silver = rs.getString("silver");
                    JCO_security = rs.getString("JCO_security");
                    Momento = rs.getString("Momento");
                    Wine = rs.getString("wine");
                    regt_cutting = rs.getString("regt_cutting");
                    M_S = rs.getString("M_S");
                    sy_DR = rs.getString("sy_DR");
                    sy_CR = rs.getString("sy_CR");
                    fdr = rs.getString("FDR");
                    property = rs.getString("property");

                    model_table.addRow(new Object[]{
                        sno, VR_panel, date, "Payment"
                    });
                    fullscreen_table.addRow(new Object[]{
                        sno, VR_panel, date, "Payment", Received_from, on_account, cash, bank, mess_fund, MMA, entertainment, Paper, silver, JCO_security, Momento, Wine, regt_cutting, M_S, sy_DR, sy_CR, fdr, property
                    });
                }
            }

            if (flag == false) {
                JOptionPane.showMessageDialog(null, "Voucher does not exists!", "Error", JOptionPane.ERROR_MESSAGE);
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Cannot fetch values!", "Error", JOptionPane.ERROR_MESSAGE);

        }
        return flag;
    }

    private void viewall() {
        try {
            Statement stmt = con.createStatement();
            VR_panel = VR_textfieldB.getText().trim();

            String Query1 = "Select * from JCO_mess_receipt order by date, Voucher;";
            String Query2 = "Select * from JCO_mess_payment order by date, voucher;";
            ResultSet rs = stmt.executeQuery(Query1);
            String voucher, date, type, Received_from, on_account, cash, bank, mess_fund, MMA, entertainment, Paper, silver, JCO_security, Momento, Wine, regt_cutting, M_S, sy_DR, sy_CR, fdr, property;
            int sno = 0;
            model_table.setRowCount(0);
            fullscreen_table.setRowCount(0);
            while (rs.next()) {
                sno++;
                voucher = rs.getString("voucher");
                date = rs.getString("date");
                Received_from = rs.getString("Received_from");
                on_account = rs.getString("on_account");
                cash = rs.getString("cash");
                bank = rs.getString("bank");
                mess_fund = rs.getString("mess_fund");
                MMA = rs.getString("MMA");
                entertainment = rs.getString("entertainment");
                Paper = rs.getString("paper");
                silver = rs.getString("silver");
                JCO_security = rs.getString("JCO_security");
                Momento = rs.getString("Momento");
                Wine = rs.getString("wine");
                regt_cutting = rs.getString("regt_cutting");
                M_S = rs.getString("M_S");
                sy_DR = rs.getString("sy_DR");
                sy_CR = rs.getString("sy_CR");
                fdr = rs.getString("FDR");
                property = rs.getString("property");

                model_table.addRow(new Object[]{
                    sno, voucher, date, "Receipt"
                });
                fullscreen_table.addRow(new Object[]{
                    sno, voucher, date, "Receipt", Received_from, on_account, cash, bank, mess_fund, MMA, entertainment, Paper, silver, JCO_security, Momento, Wine, regt_cutting, M_S, sy_DR, sy_CR, fdr, property
                });
            }
            rs = stmt.executeQuery(Query2);
            while (rs.next()) {
                sno++;
                voucher = rs.getString("voucher");
                date = rs.getString("date");
                Received_from = rs.getString("Received_from");
                on_account = rs.getString("on_account");
                cash = rs.getString("cash");
                bank = rs.getString("bank");
                mess_fund = rs.getString("mess_fund");
                MMA = rs.getString("MMA");
                entertainment = rs.getString("entertainment");
                Paper = rs.getString("paper");
                silver = rs.getString("silver");
                JCO_security = rs.getString("JCO_security");
                Momento = rs.getString("Momento");
                Wine = rs.getString("wine");
                regt_cutting = rs.getString("regt_cutting");
                M_S = rs.getString("M_S");
                sy_DR = rs.getString("sy_DR");
                sy_CR = rs.getString("sy_CR");
                fdr = rs.getString("FDR");
                property = rs.getString("property");

                model_table.addRow(new Object[]{
                    sno, voucher, date, "Payment"
                });
                fullscreen_table.addRow(new Object[]{
                    sno, voucher, date, "Payment", Received_from, on_account, cash, bank, mess_fund, MMA, entertainment, Paper, silver, JCO_security, Momento, Wine, regt_cutting, M_S, sy_DR, sy_CR, fdr, property
                });
            }

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);

        }

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

    class Page2 implements Printable {

        public int print(Graphics graphics, PageFormat pageFormat, int pageIndex) throws PrinterException {
            Graphics2D graphics2D = (Graphics2D) graphics;

            graphics2D.setClip(0, 25, 690, 700); //shapes the printing rectangle in respect to translate
            graphics2D.translate(10, -675); //marks the top of the page

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

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        buttonGroup1 = new javax.swing.ButtonGroup();
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
        ScrollPaneA = new javax.swing.JScrollPane();
        panelA = new javax.swing.JPanel();
        date_labelA = new javax.swing.JLabel();
        bank_labelA = new javax.swing.JLabel();
        cash_labelA = new javax.swing.JLabel();
        VR_labelA = new javax.swing.JLabel();
        VR_textfieldA = new javax.swing.JTextField();
        cash_textfieldA = new javax.swing.JTextField();
        bank_textfieldA = new javax.swing.JTextField();
        mess_fund_labelA = new javax.swing.JLabel();
        mess_fund_textfieldA = new javax.swing.JTextField();
        panelA_bottom_line = new javax.swing.JSeparator();
        date_comboboxA = new javax.swing.JComboBox<>();
        month_comboboxA = new javax.swing.JComboBox<>();
        year_comboboxA = new javax.swing.JComboBox<>();
        current_dateA = new javax.swing.JLabel();
        on_labelA = new javax.swing.JLabel();
        on_textfieldA = new javax.swing.JTextField();
        from_labelA = new javax.swing.JLabel();
        from_textfieldA = new javax.swing.JTextField();
        MMA_labelA = new javax.swing.JLabel();
        MMA_textfieldA = new javax.swing.JTextField();
        initials_labelA = new javax.swing.JLabel();
        momento_textfieldA = new javax.swing.JTextField();
        paper_labelA = new javax.swing.JLabel();
        paper_textfieldA = new javax.swing.JTextField();
        entertainment_labelA = new javax.swing.JLabel();
        entertainment_textfieldA = new javax.swing.JTextField();
        welfare_labelA = new javax.swing.JLabel();
        wine_textfieldA = new javax.swing.JTextField();
        damage_labelA = new javax.swing.JLabel();
        textArea_scrollpane = new javax.swing.JScrollPane();
        signature = new javax.swing.JTextArea();
        security_labelA = new javax.swing.JLabel();
        silver_labelA = new javax.swing.JLabel();
        silver_textfieldA = new javax.swing.JTextField();
        security_textfieldA = new javax.swing.JTextField();
        sy_CR_labelA = new javax.swing.JLabel();
        sy_DD_labelA = new javax.swing.JLabel();
        sy_DR_textfieldA = new javax.swing.JTextField();
        sy_CR_textfieldA = new javax.swing.JTextField();
        property_textfieldA = new javax.swing.JTextField();
        property_labelA = new javax.swing.JLabel();
        underlineA2 = new javax.swing.JSeparator();
        underlineA5 = new javax.swing.JSeparator();
        underlineA6 = new javax.swing.JSeparator();
        underlineA7 = new javax.swing.JSeparator();
        underlineA8 = new javax.swing.JSeparator();
        underlineA9 = new javax.swing.JSeparator();
        underlineA10 = new javax.swing.JSeparator();
        underlineA11 = new javax.swing.JSeparator();
        underlineA12 = new javax.swing.JSeparator();
        underlineA13 = new javax.swing.JSeparator();
        underlineA14 = new javax.swing.JSeparator();
        underlineA15 = new javax.swing.JSeparator();
        underlineA16 = new javax.swing.JSeparator();
        underlineA17 = new javax.swing.JSeparator();
        underlineA18 = new javax.swing.JSeparator();
        underlineA19 = new javax.swing.JSeparator();
        regt_textfieldA = new javax.swing.JTextField();
        MS_labelA = new javax.swing.JLabel();
        MS_textfieldA = new javax.swing.JTextField();
        regt_labelA = new javax.swing.JLabel();
        underlineA20 = new javax.swing.JSeparator();
        underlineA21 = new javax.swing.JSeparator();
        receipt_radio = new javax.swing.JRadioButton();
        payment_radio = new javax.swing.JRadioButton();
        FDR_labelA = new javax.swing.JLabel();
        FDR_textfieldA = new javax.swing.JTextField();
        underlineA22 = new javax.swing.JSeparator();
        panelB = new javax.swing.JPanel();
        ScrollPaneB = new javax.swing.JScrollPane();
        TableB = new javax.swing.JTable();
        VR_labelB = new javax.swing.JLabel();
        VR_textfieldB = new javax.swing.JTextField();
        comp_type_underlineB = new javax.swing.JSeparator();
        okayB = new javax.swing.JLabel();
        insert_buttonA = new javax.swing.JPanel();
        insert_labelA = new javax.swing.JLabel();
        refresh_buttonB = new javax.swing.JPanel();
        refresh_labelB = new javax.swing.JLabel();
        print_buttonB = new javax.swing.JPanel();
        print_labelB = new javax.swing.JLabel();
        fullscreen_buttonB = new javax.swing.JPanel();
        fullscreen_labelB = new javax.swing.JLabel();
        print_buttonA = new javax.swing.JPanel();
        print_labelA = new javax.swing.JLabel();
        reset_buttonA = new javax.swing.JLabel();
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
        move_panel.setMaximumSize(new java.awt.Dimension(1280, 30));
        move_panel.setMinimumSize(new java.awt.Dimension(1280, 30));
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
        menuA_label.setText("Receipt/ Payment Slip");

        javax.swing.GroupLayout menuALayout = new javax.swing.GroupLayout(menuA);
        menuA.setLayout(menuALayout);
        menuALayout.setHorizontalGroup(
            menuALayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(menuALayout.createSequentialGroup()
                .addGap(27, 27, 27)
                .addComponent(menuA_icon)
                .addGap(33, 33, 33)
                .addComponent(menuA_label)
                .addContainerGap(71, Short.MAX_VALUE))
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
                .addContainerGap(221, Short.MAX_VALUE))
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
        title_icon.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/PNGS/mess_white.png"))); // NOI18N
        side_pane.add(title_icon, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 115, 60, 60));

        title_label.setFont(new java.awt.Font("Montserrat", 0, 28)); // NOI18N
        title_label.setForeground(new java.awt.Color(255, 255, 255));
        title_label.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        title_label.setText("JCO Mess");
        side_pane.add(title_label, new org.netbeans.lib.awtextra.AbsoluteConstraints(100, 130, 180, 40));

        bg.add(side_pane, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 320, 740));

        ScrollPaneA.setBorder(null);
        ScrollPaneA.setHorizontalScrollBarPolicy(javax.swing.ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
        ScrollPaneA.setVerticalScrollBarPolicy(javax.swing.ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);

        panelA.setBackground(new java.awt.Color(255, 255, 255));
        panelA.setFocusable(false);
        panelA.setMaximumSize(new java.awt.Dimension(950, 1310));
        panelA.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        date_labelA.setBackground(new java.awt.Color(255, 255, 255));
        date_labelA.setFont(new java.awt.Font("Montserrat", 0, 28)); // NOI18N
        date_labelA.setForeground(new java.awt.Color(51, 51, 51));
        date_labelA.setText("Date");
        panelA.add(date_labelA, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 110, -1, 40));

        bank_labelA.setBackground(new java.awt.Color(255, 255, 255));
        bank_labelA.setFont(new java.awt.Font("Montserrat", 0, 28)); // NOI18N
        bank_labelA.setForeground(new java.awt.Color(51, 51, 51));
        bank_labelA.setText("Bank");
        panelA.add(bank_labelA, new org.netbeans.lib.awtextra.AbsoluteConstraints(490, 340, -1, 40));

        cash_labelA.setBackground(new java.awt.Color(255, 255, 255));
        cash_labelA.setFont(new java.awt.Font("Montserrat", 0, 28)); // NOI18N
        cash_labelA.setForeground(new java.awt.Color(51, 51, 51));
        cash_labelA.setText("Cash");
        panelA.add(cash_labelA, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 340, -1, 40));

        VR_labelA.setBackground(new java.awt.Color(255, 255, 255));
        VR_labelA.setFont(new java.awt.Font("Montserrat", 0, 28)); // NOI18N
        VR_labelA.setForeground(new java.awt.Color(51, 51, 51));
        VR_labelA.setText("VR");
        panelA.add(VR_labelA, new org.netbeans.lib.awtextra.AbsoluteConstraints(490, 110, -1, 40));

        VR_textfieldA.setFont(new java.awt.Font("Roboto", 0, 24)); // NOI18N
        VR_textfieldA.setForeground(new java.awt.Color(54, 33, 89));
        VR_textfieldA.setBorder(null);
        VR_textfieldA.setMargin(new java.awt.Insets(2, 4, 2, 2));
        VR_textfieldA.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                VR_textfieldAActionPerformed(evt);
            }
        });
        panelA.add(VR_textfieldA, new org.netbeans.lib.awtextra.AbsoluteConstraints(490, 150, 320, 40));

        cash_textfieldA.setFont(new java.awt.Font("Roboto", 0, 24)); // NOI18N
        cash_textfieldA.setForeground(new java.awt.Color(54, 33, 89));
        cash_textfieldA.setBorder(null);
        cash_textfieldA.setMargin(new java.awt.Insets(2, 4, 2, 2));
        cash_textfieldA.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cash_textfieldAActionPerformed(evt);
            }
        });
        panelA.add(cash_textfieldA, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 380, 320, 40));

        bank_textfieldA.setFont(new java.awt.Font("Roboto", 0, 24)); // NOI18N
        bank_textfieldA.setForeground(new java.awt.Color(54, 33, 89));
        bank_textfieldA.setBorder(null);
        bank_textfieldA.setMargin(new java.awt.Insets(2, 4, 2, 2));
        bank_textfieldA.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bank_textfieldAActionPerformed(evt);
            }
        });
        panelA.add(bank_textfieldA, new org.netbeans.lib.awtextra.AbsoluteConstraints(490, 380, 320, 40));

        mess_fund_labelA.setBackground(new java.awt.Color(255, 255, 255));
        mess_fund_labelA.setFont(new java.awt.Font("Montserrat", 0, 28)); // NOI18N
        mess_fund_labelA.setForeground(new java.awt.Color(66, 50, 77));
        mess_fund_labelA.setText("Mess Fund");
        panelA.add(mess_fund_labelA, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 450, -1, 40));

        mess_fund_textfieldA.setFont(new java.awt.Font("Roboto", 0, 24)); // NOI18N
        mess_fund_textfieldA.setForeground(new java.awt.Color(54, 33, 89));
        mess_fund_textfieldA.setBorder(null);
        mess_fund_textfieldA.setMargin(new java.awt.Insets(2, 4, 2, 2));
        mess_fund_textfieldA.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                mess_fund_textfieldAActionPerformed(evt);
            }
        });
        panelA.add(mess_fund_textfieldA, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 490, 320, 40));

        panelA_bottom_line.setForeground(new java.awt.Color(54, 33, 89));
        panelA.add(panelA_bottom_line, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 1400, 910, -1));

        date_comboboxA.setFont(new java.awt.Font("Roboto", 0, 18)); // NOI18N
        date_comboboxA.setForeground(new java.awt.Color(44, 62, 80));
        date_comboboxA.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "DD", "01", "02", "03", "04", "05", "06", "07", "08", "09", "10", "11", "12", "13", "14", "15", "16", "17", "18", "19", "20", "21", "22", "23", "24", "25", "26", "27", "28", "29", "30", "31" }));
        date_comboboxA.setBorder(null);
        date_comboboxA.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                date_comboboxAActionPerformed(evt);
            }
        });
        panelA.add(date_comboboxA, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 150, 100, 40));

        month_comboboxA.setFont(new java.awt.Font("Roboto", 0, 18)); // NOI18N
        month_comboboxA.setForeground(new java.awt.Color(44, 62, 80));
        month_comboboxA.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "MM", "01", "02", "03", "04", "05", "06", "07", "08", "09", "10", "11", "12" }));
        month_comboboxA.setBorder(null);
        panelA.add(month_comboboxA, new org.netbeans.lib.awtextra.AbsoluteConstraints(190, 150, 100, 40));

        year_comboboxA.setFont(new java.awt.Font("Roboto", 0, 18)); // NOI18N
        year_comboboxA.setForeground(new java.awt.Color(44, 62, 80));
        year_comboboxA.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "YYYY", "1950", "1951", "1952", "1953", "1954", "1955", "1956", "1957", "1958", "1959", "1960", "1961", "1962", "1963", "1964", "1965", "1966", "1967", "1968", "1969", "1970", "1971", "1972", "1973", "1974", "1975", "1976", "1977", "1978", "1979", "1980", "1981", "1982", "1983", "1984", "1985", "1986", "1987", "1988", "1989", "1990", "1991", "1992", "1993", "1994", "1995", "1996", "1997", "1998", "1999", "2000", "2001", "2002", "2003", "2004", "2005", "2006", "2007", "2008", "2009", "2010", "2011", "2012", "2013", "2014", "2015", "2016", "2017", "2018", "2019", "2020", "2021", "2022", "2023", "2024", "2025", "2026", "2027", "2028", "2029", "2030", "2031", "2032", "2033", "2034", "2035", "2036", "2037", "2038", "2039", "2040", "2041", "2042", "2043", "2044", "2045", "2046", "2047", "2048", "2049", "2050", "2051", "2052", "2053", "2054", "2055", "2056", "2057", "2058", "2059", "2060", "2061", "2062", "2063", "2064", "2065", "2066", "2067", "2068", "2069", "2070", "2071", "2072", "2073", "2074", "2075", "2076", "2077", "2078", "2079", "2080", "2081", "2082", "2083", "2084", "2085", "2086", "2087", "2088", "2089", "2090", "2091", "2092", "2093", "2094", "2095", "2096", "2097", "2098", "2099", "2100", " " }));
        year_comboboxA.setBorder(null);
        panelA.add(year_comboboxA, new org.netbeans.lib.awtextra.AbsoluteConstraints(300, 150, 100, 40));

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
        panelA.add(current_dateA, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 200, -1, -1));

        on_labelA.setBackground(new java.awt.Color(255, 255, 255));
        on_labelA.setFont(new java.awt.Font("Montserrat", 0, 28)); // NOI18N
        on_labelA.setForeground(new java.awt.Color(51, 51, 51));
        on_labelA.setText("On Account of");
        panelA.add(on_labelA, new org.netbeans.lib.awtextra.AbsoluteConstraints(490, 230, -1, 40));

        on_textfieldA.setFont(new java.awt.Font("Roboto", 0, 24)); // NOI18N
        on_textfieldA.setForeground(new java.awt.Color(54, 33, 89));
        on_textfieldA.setBorder(null);
        on_textfieldA.setMargin(new java.awt.Insets(2, 4, 2, 2));
        on_textfieldA.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                on_textfieldAActionPerformed(evt);
            }
        });
        panelA.add(on_textfieldA, new org.netbeans.lib.awtextra.AbsoluteConstraints(490, 270, 320, 40));

        from_labelA.setBackground(new java.awt.Color(255, 255, 255));
        from_labelA.setFont(new java.awt.Font("Montserrat", 0, 28)); // NOI18N
        from_labelA.setForeground(new java.awt.Color(51, 51, 51));
        from_labelA.setText("Received from");
        panelA.add(from_labelA, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 230, -1, 40));

        from_textfieldA.setFont(new java.awt.Font("Roboto", 0, 24)); // NOI18N
        from_textfieldA.setForeground(new java.awt.Color(54, 33, 89));
        from_textfieldA.setBorder(null);
        from_textfieldA.setMargin(new java.awt.Insets(2, 4, 2, 2));
        from_textfieldA.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                from_textfieldAActionPerformed(evt);
            }
        });
        panelA.add(from_textfieldA, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 270, 320, 40));

        MMA_labelA.setBackground(new java.awt.Color(255, 255, 255));
        MMA_labelA.setFont(new java.awt.Font("Montserrat", 0, 28)); // NOI18N
        MMA_labelA.setForeground(new java.awt.Color(66, 50, 77));
        MMA_labelA.setText("MMA");
        panelA.add(MMA_labelA, new org.netbeans.lib.awtextra.AbsoluteConstraints(490, 450, -1, 40));

        MMA_textfieldA.setFont(new java.awt.Font("Roboto", 0, 24)); // NOI18N
        MMA_textfieldA.setForeground(new java.awt.Color(54, 33, 89));
        MMA_textfieldA.setBorder(null);
        MMA_textfieldA.setMargin(new java.awt.Insets(2, 4, 2, 2));
        MMA_textfieldA.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                MMA_textfieldAActionPerformed(evt);
            }
        });
        panelA.add(MMA_textfieldA, new org.netbeans.lib.awtextra.AbsoluteConstraints(490, 490, 320, 40));

        initials_labelA.setBackground(new java.awt.Color(255, 255, 255));
        initials_labelA.setFont(new java.awt.Font("Montserrat", 0, 14)); // NOI18N
        initials_labelA.setForeground(new java.awt.Color(66, 50, 77));
        initials_labelA.setText("Initials");
        panelA.add(initials_labelA, new org.netbeans.lib.awtextra.AbsoluteConstraints(90, 1280, -1, 40));

        momento_textfieldA.setFont(new java.awt.Font("Roboto", 0, 24)); // NOI18N
        momento_textfieldA.setForeground(new java.awt.Color(54, 33, 89));
        momento_textfieldA.setBorder(null);
        momento_textfieldA.setMargin(new java.awt.Insets(2, 4, 2, 2));
        momento_textfieldA.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                momento_textfieldAActionPerformed(evt);
            }
        });
        panelA.add(momento_textfieldA, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 820, 320, 40));

        paper_labelA.setBackground(new java.awt.Color(255, 255, 255));
        paper_labelA.setFont(new java.awt.Font("Montserrat", 0, 28)); // NOI18N
        paper_labelA.setForeground(new java.awt.Color(51, 51, 51));
        paper_labelA.setText("Paper");
        panelA.add(paper_labelA, new org.netbeans.lib.awtextra.AbsoluteConstraints(490, 560, -1, 40));

        paper_textfieldA.setFont(new java.awt.Font("Roboto", 0, 24)); // NOI18N
        paper_textfieldA.setForeground(new java.awt.Color(54, 33, 89));
        paper_textfieldA.setBorder(null);
        paper_textfieldA.setMargin(new java.awt.Insets(2, 4, 2, 2));
        paper_textfieldA.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                paper_textfieldAActionPerformed(evt);
            }
        });
        panelA.add(paper_textfieldA, new org.netbeans.lib.awtextra.AbsoluteConstraints(490, 600, 320, 40));

        entertainment_labelA.setBackground(new java.awt.Color(255, 255, 255));
        entertainment_labelA.setFont(new java.awt.Font("Montserrat", 0, 28)); // NOI18N
        entertainment_labelA.setForeground(new java.awt.Color(51, 51, 51));
        entertainment_labelA.setText("Entertainment");
        panelA.add(entertainment_labelA, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 560, -1, 40));

        entertainment_textfieldA.setFont(new java.awt.Font("Roboto", 0, 24)); // NOI18N
        entertainment_textfieldA.setForeground(new java.awt.Color(54, 33, 89));
        entertainment_textfieldA.setBorder(null);
        entertainment_textfieldA.setMargin(new java.awt.Insets(2, 4, 2, 2));
        entertainment_textfieldA.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                entertainment_textfieldAActionPerformed(evt);
            }
        });
        panelA.add(entertainment_textfieldA, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 600, 320, 40));

        welfare_labelA.setBackground(new java.awt.Color(255, 255, 255));
        welfare_labelA.setFont(new java.awt.Font("Montserrat", 0, 28)); // NOI18N
        welfare_labelA.setForeground(new java.awt.Color(66, 50, 77));
        welfare_labelA.setText("Wine");
        panelA.add(welfare_labelA, new org.netbeans.lib.awtextra.AbsoluteConstraints(490, 780, -1, 40));

        wine_textfieldA.setFont(new java.awt.Font("Roboto", 0, 24)); // NOI18N
        wine_textfieldA.setForeground(new java.awt.Color(54, 33, 89));
        wine_textfieldA.setBorder(null);
        wine_textfieldA.setMargin(new java.awt.Insets(2, 4, 2, 2));
        wine_textfieldA.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                wine_textfieldAActionPerformed(evt);
            }
        });
        panelA.add(wine_textfieldA, new org.netbeans.lib.awtextra.AbsoluteConstraints(490, 820, 320, 40));

        damage_labelA.setBackground(new java.awt.Color(255, 255, 255));
        damage_labelA.setFont(new java.awt.Font("Montserrat", 0, 28)); // NOI18N
        damage_labelA.setForeground(new java.awt.Color(66, 50, 77));
        damage_labelA.setText("Momento");
        panelA.add(damage_labelA, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 780, -1, 40));

        signature.setColumns(20);
        signature.setRows(5);
        signature.setEnabled(false);
        signature.setSelectionColor(new java.awt.Color(54, 33, 89));
        textArea_scrollpane.setViewportView(signature);

        panelA.add(textArea_scrollpane, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 1230, 320, -1));

        security_labelA.setBackground(new java.awt.Color(255, 255, 255));
        security_labelA.setFont(new java.awt.Font("Montserrat", 0, 28)); // NOI18N
        security_labelA.setForeground(new java.awt.Color(51, 51, 51));
        security_labelA.setText("JCOs Security");
        panelA.add(security_labelA, new org.netbeans.lib.awtextra.AbsoluteConstraints(490, 670, -1, 40));

        silver_labelA.setBackground(new java.awt.Color(255, 255, 255));
        silver_labelA.setFont(new java.awt.Font("Montserrat", 0, 28)); // NOI18N
        silver_labelA.setForeground(new java.awt.Color(51, 51, 51));
        silver_labelA.setText("Silver");
        panelA.add(silver_labelA, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 670, -1, 40));

        silver_textfieldA.setFont(new java.awt.Font("Roboto", 0, 24)); // NOI18N
        silver_textfieldA.setForeground(new java.awt.Color(54, 33, 89));
        silver_textfieldA.setBorder(null);
        silver_textfieldA.setMargin(new java.awt.Insets(2, 4, 2, 2));
        silver_textfieldA.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                silver_textfieldAActionPerformed(evt);
            }
        });
        panelA.add(silver_textfieldA, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 710, 320, 40));

        security_textfieldA.setFont(new java.awt.Font("Roboto", 0, 24)); // NOI18N
        security_textfieldA.setForeground(new java.awt.Color(54, 33, 89));
        security_textfieldA.setBorder(null);
        security_textfieldA.setMargin(new java.awt.Insets(2, 4, 2, 2));
        security_textfieldA.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                security_textfieldAActionPerformed(evt);
            }
        });
        panelA.add(security_textfieldA, new org.netbeans.lib.awtextra.AbsoluteConstraints(490, 710, 320, 40));

        sy_CR_labelA.setBackground(new java.awt.Color(255, 255, 255));
        sy_CR_labelA.setFont(new java.awt.Font("Montserrat", 0, 28)); // NOI18N
        sy_CR_labelA.setForeground(new java.awt.Color(51, 51, 51));
        sy_CR_labelA.setText("Sy CR");
        panelA.add(sy_CR_labelA, new org.netbeans.lib.awtextra.AbsoluteConstraints(490, 1000, -1, 40));

        sy_DD_labelA.setBackground(new java.awt.Color(255, 255, 255));
        sy_DD_labelA.setFont(new java.awt.Font("Montserrat", 0, 28)); // NOI18N
        sy_DD_labelA.setForeground(new java.awt.Color(51, 51, 51));
        sy_DD_labelA.setText("Sy DR");
        panelA.add(sy_DD_labelA, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 1000, -1, 40));

        sy_DR_textfieldA.setFont(new java.awt.Font("Roboto", 0, 24)); // NOI18N
        sy_DR_textfieldA.setForeground(new java.awt.Color(54, 33, 89));
        sy_DR_textfieldA.setBorder(null);
        sy_DR_textfieldA.setMargin(new java.awt.Insets(2, 4, 2, 2));
        sy_DR_textfieldA.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                sy_DR_textfieldAActionPerformed(evt);
            }
        });
        panelA.add(sy_DR_textfieldA, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 1040, 320, 40));

        sy_CR_textfieldA.setFont(new java.awt.Font("Roboto", 0, 24)); // NOI18N
        sy_CR_textfieldA.setForeground(new java.awt.Color(54, 33, 89));
        sy_CR_textfieldA.setBorder(null);
        sy_CR_textfieldA.setMargin(new java.awt.Insets(2, 4, 2, 2));
        sy_CR_textfieldA.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                sy_CR_textfieldAActionPerformed(evt);
            }
        });
        panelA.add(sy_CR_textfieldA, new org.netbeans.lib.awtextra.AbsoluteConstraints(490, 1040, 320, 40));

        property_textfieldA.setFont(new java.awt.Font("Roboto", 0, 24)); // NOI18N
        property_textfieldA.setForeground(new java.awt.Color(54, 33, 89));
        property_textfieldA.setBorder(null);
        property_textfieldA.setMargin(new java.awt.Insets(2, 4, 2, 2));
        property_textfieldA.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                property_textfieldAActionPerformed(evt);
            }
        });
        panelA.add(property_textfieldA, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 1150, 320, 40));

        property_labelA.setBackground(new java.awt.Color(255, 255, 255));
        property_labelA.setFont(new java.awt.Font("Montserrat", 0, 28)); // NOI18N
        property_labelA.setForeground(new java.awt.Color(66, 50, 77));
        property_labelA.setText("Property");
        panelA.add(property_labelA, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 1110, -1, 40));

        underlineA2.setForeground(new java.awt.Color(54, 33, 89));
        panelA.add(underlineA2, new org.netbeans.lib.awtextra.AbsoluteConstraints(490, 190, 320, 20));

        underlineA5.setForeground(new java.awt.Color(54, 33, 89));
        panelA.add(underlineA5, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 420, 320, 20));

        underlineA6.setForeground(new java.awt.Color(54, 33, 89));
        panelA.add(underlineA6, new org.netbeans.lib.awtextra.AbsoluteConstraints(490, 420, 320, 20));

        underlineA7.setForeground(new java.awt.Color(54, 33, 89));
        panelA.add(underlineA7, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 530, 320, 20));

        underlineA8.setForeground(new java.awt.Color(54, 33, 89));
        panelA.add(underlineA8, new org.netbeans.lib.awtextra.AbsoluteConstraints(490, 310, 320, 20));

        underlineA9.setForeground(new java.awt.Color(54, 33, 89));
        panelA.add(underlineA9, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 310, 320, 20));

        underlineA10.setForeground(new java.awt.Color(54, 33, 89));
        panelA.add(underlineA10, new org.netbeans.lib.awtextra.AbsoluteConstraints(490, 530, 320, 20));

        underlineA11.setForeground(new java.awt.Color(54, 33, 89));
        panelA.add(underlineA11, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 750, 320, 20));

        underlineA12.setForeground(new java.awt.Color(54, 33, 89));
        panelA.add(underlineA12, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 860, 320, 20));

        underlineA13.setForeground(new java.awt.Color(54, 33, 89));
        panelA.add(underlineA13, new org.netbeans.lib.awtextra.AbsoluteConstraints(490, 750, 320, 20));

        underlineA14.setForeground(new java.awt.Color(54, 33, 89));
        panelA.add(underlineA14, new org.netbeans.lib.awtextra.AbsoluteConstraints(490, 640, 320, 20));

        underlineA15.setForeground(new java.awt.Color(54, 33, 89));
        panelA.add(underlineA15, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 640, 320, 20));

        underlineA16.setForeground(new java.awt.Color(54, 33, 89));
        panelA.add(underlineA16, new org.netbeans.lib.awtextra.AbsoluteConstraints(490, 860, 320, 20));

        underlineA17.setForeground(new java.awt.Color(54, 33, 89));
        panelA.add(underlineA17, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 1080, 320, 20));

        underlineA18.setForeground(new java.awt.Color(54, 33, 89));
        panelA.add(underlineA18, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 1190, 320, 20));

        underlineA19.setForeground(new java.awt.Color(54, 33, 89));
        panelA.add(underlineA19, new org.netbeans.lib.awtextra.AbsoluteConstraints(490, 1080, 320, 20));

        regt_textfieldA.setFont(new java.awt.Font("Roboto", 0, 24)); // NOI18N
        regt_textfieldA.setForeground(new java.awt.Color(54, 33, 89));
        regt_textfieldA.setBorder(null);
        regt_textfieldA.setMargin(new java.awt.Insets(2, 4, 2, 2));
        regt_textfieldA.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                regt_textfieldAActionPerformed(evt);
            }
        });
        panelA.add(regt_textfieldA, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 930, 320, 40));

        MS_labelA.setBackground(new java.awt.Color(255, 255, 255));
        MS_labelA.setFont(new java.awt.Font("Montserrat", 0, 28)); // NOI18N
        MS_labelA.setForeground(new java.awt.Color(66, 50, 77));
        MS_labelA.setText("M&S");
        panelA.add(MS_labelA, new org.netbeans.lib.awtextra.AbsoluteConstraints(490, 890, -1, 40));

        MS_textfieldA.setFont(new java.awt.Font("Roboto", 0, 24)); // NOI18N
        MS_textfieldA.setForeground(new java.awt.Color(54, 33, 89));
        MS_textfieldA.setBorder(null);
        MS_textfieldA.setMargin(new java.awt.Insets(2, 4, 2, 2));
        MS_textfieldA.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                MS_textfieldAActionPerformed(evt);
            }
        });
        panelA.add(MS_textfieldA, new org.netbeans.lib.awtextra.AbsoluteConstraints(490, 930, 320, 40));

        regt_labelA.setBackground(new java.awt.Color(255, 255, 255));
        regt_labelA.setFont(new java.awt.Font("Montserrat", 0, 28)); // NOI18N
        regt_labelA.setForeground(new java.awt.Color(66, 50, 77));
        regt_labelA.setText("Regt Cutting");
        panelA.add(regt_labelA, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 890, -1, 40));

        underlineA20.setForeground(new java.awt.Color(54, 33, 89));
        panelA.add(underlineA20, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 970, 320, 20));

        underlineA21.setForeground(new java.awt.Color(54, 33, 89));
        panelA.add(underlineA21, new org.netbeans.lib.awtextra.AbsoluteConstraints(490, 970, 320, 20));

        buttonGroup1.add(receipt_radio);
        receipt_radio.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        receipt_radio.setText("Receipt");
        receipt_radio.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                receipt_radioActionPerformed(evt);
            }
        });
        panelA.add(receipt_radio, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 60, -1, -1));

        buttonGroup1.add(payment_radio);
        payment_radio.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        payment_radio.setText("Payment Slip");
        payment_radio.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                payment_radioActionPerformed(evt);
            }
        });
        panelA.add(payment_radio, new org.netbeans.lib.awtextra.AbsoluteConstraints(270, 60, -1, -1));

        FDR_labelA.setBackground(new java.awt.Color(255, 255, 255));
        FDR_labelA.setFont(new java.awt.Font("Montserrat", 0, 28)); // NOI18N
        FDR_labelA.setForeground(new java.awt.Color(66, 50, 77));
        FDR_labelA.setText("FDR");
        panelA.add(FDR_labelA, new org.netbeans.lib.awtextra.AbsoluteConstraints(490, 1110, -1, 40));

        FDR_textfieldA.setFont(new java.awt.Font("Roboto", 0, 24)); // NOI18N
        FDR_textfieldA.setForeground(new java.awt.Color(54, 33, 89));
        FDR_textfieldA.setBorder(null);
        FDR_textfieldA.setMargin(new java.awt.Insets(2, 4, 2, 2));
        FDR_textfieldA.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                FDR_textfieldAActionPerformed(evt);
            }
        });
        panelA.add(FDR_textfieldA, new org.netbeans.lib.awtextra.AbsoluteConstraints(490, 1150, 320, 40));

        underlineA22.setForeground(new java.awt.Color(54, 33, 89));
        panelA.add(underlineA22, new org.netbeans.lib.awtextra.AbsoluteConstraints(490, 1190, 320, 20));

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
                "S. No.", "VR", "Date", "Receipt/Payment"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false
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

        VR_labelB.setBackground(new java.awt.Color(255, 255, 255));
        VR_labelB.setFont(new java.awt.Font("Montserrat", 0, 28)); // NOI18N
        VR_labelB.setForeground(new java.awt.Color(51, 51, 51));
        VR_labelB.setText("VR");
        panelB.add(VR_labelB, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 50, -1, 40));

        VR_textfieldB.setFont(new java.awt.Font("Roboto", 0, 24)); // NOI18N
        VR_textfieldB.setForeground(new java.awt.Color(54, 33, 89));
        VR_textfieldB.setBorder(null);
        VR_textfieldB.setMargin(new java.awt.Insets(2, 4, 2, 2));
        VR_textfieldB.setSelectionColor(new java.awt.Color(204, 204, 255));
        VR_textfieldB.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                VR_textfieldBKeyPressed(evt);
            }
        });
        panelB.add(VR_textfieldB, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 90, 330, 40));

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

        print_buttonA.setForeground(new java.awt.Color(240, 240, 240));
        print_buttonA.setMaximumSize(new java.awt.Dimension(200, 200));
        print_buttonA.setMinimumSize(new java.awt.Dimension(200, 200));
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

        print_labelA.setBackground(new java.awt.Color(54, 33, 89));
        print_labelA.setFont(new java.awt.Font("Roboto Bk", 0, 14)); // NOI18N
        print_labelA.setForeground(new java.awt.Color(54, 33, 89));
        print_labelA.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        print_labelA.setText("PRINT");
        print_buttonA.add(print_labelA, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 0, 80, 50));

        bg.add(print_buttonA, new org.netbeans.lib.awtextra.AbsoluteConstraints(870, 120, 180, 50));

        reset_buttonA.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/PNGS/reset.png"))); // NOI18N
        reset_buttonA.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                reset_buttonAMouseClicked(evt);
            }
        });
        bg.add(reset_buttonA, new org.netbeans.lib.awtextra.AbsoluteConstraints(360, 120, -1, 50));

        getContentPane().add(bg, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 1280, 720));

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
                "S. No.", "VR", "Date", "Received from", "On account", "Cash", "Bank", "Mess Fund", "MMA", "Entertainment", "Paper", "Silver", "JCO Securty", "Momento", "Wine", "Rregt Cutting", "M&S", "Sy DR", "Sy CR", "FDR", "Property"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        fullscreen_tableB.setAutoResizeMode(javax.swing.JTable.AUTO_RESIZE_OFF);
        fullscreen_tableB.setAutoscrolls(false);
        fullscreen_tableB.setFocusable(false);
        fullscreen_tableB.setGridColor(new java.awt.Color(255, 255, 255));
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
            fullscreen_tableB.getColumnModel().getColumn(20).setMinWidth(150);
            fullscreen_tableB.getColumnModel().getColumn(20).setPreferredWidth(150);
            fullscreen_tableB.getColumnModel().getColumn(20).setMaxWidth(150);
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
            new Modules(2).setVisible(true);
            this.setVisible(false);

        }
        go_back = false;
    }//GEN-LAST:event_go_back_labelMouseClicked

    private void menuAMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_menuAMouseClicked
        menuA.setBackground(new java.awt.Color(85, 65, 118));
        menuB.setBackground(new java.awt.Color(64, 43, 100));

        insert_buttonA.setVisible(true);
        print_buttonA.setVisible(true);
        print_buttonB.setVisible(false);
        refresh_buttonB.setVisible(false);
        fullscreen_buttonB.setVisible(false);
        reset_buttonA.setVisible(true);

        ScrollPaneA.setVisible(true);
        panelB.setVisible(false);
        fullscreen_panelB.setVisible(false);

      

    }//GEN-LAST:event_menuAMouseClicked

    private void menuBMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_menuBMouseClicked
        menuB.setBackground(new java.awt.Color(85, 65, 118));
        menuA.setBackground(new java.awt.Color(64, 43, 100));

        print_buttonB.setVisible(true);
        refresh_buttonB.setVisible(true);
        fullscreen_buttonB.setVisible(true);
        print_buttonA.setVisible(false);
        insert_buttonA.setVisible(false);
        reset_buttonA.setVisible(false);

        ScrollPaneA.setVisible(false);
        panelB.setVisible(true);
        fullscreen_panelB.setVisible(false);
        VR_panel = "";
        VR_textfieldB.setText("");
        viewall();

    }//GEN-LAST:event_menuBMouseClicked

    private void VR_textfieldAActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_VR_textfieldAActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_VR_textfieldAActionPerformed

    private void cash_textfieldAActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cash_textfieldAActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_cash_textfieldAActionPerformed

    private void bank_textfieldAActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bank_textfieldAActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_bank_textfieldAActionPerformed

    private void mess_fund_textfieldAActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_mess_fund_textfieldAActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_mess_fund_textfieldAActionPerformed

    private void date_comboboxAActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_date_comboboxAActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_date_comboboxAActionPerformed

    private void current_dateAMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_current_dateAMouseClicked
        current_date();
    }//GEN-LAST:event_current_dateAMouseClicked

    private void current_dateAMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_current_dateAMouseEntered
        current_dateA.setForeground(new java.awt.Color(54, 33, 89));
        current_dateA.setBackground(new java.awt.Color(237, 224, 255));
    }//GEN-LAST:event_current_dateAMouseEntered

    private void current_dateAMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_current_dateAMouseExited
        current_dateA.setForeground(new java.awt.Color(60, 63, 65));
        current_dateA.setBackground(new java.awt.Color(255, 255, 255));
    }//GEN-LAST:event_current_dateAMouseExited

    private void on_textfieldAActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_on_textfieldAActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_on_textfieldAActionPerformed

    private void from_textfieldAActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_from_textfieldAActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_from_textfieldAActionPerformed

    private void MMA_textfieldAActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_MMA_textfieldAActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_MMA_textfieldAActionPerformed

    private void sy_DR_textfieldAActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_sy_DR_textfieldAActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_sy_DR_textfieldAActionPerformed

    private void sy_CR_textfieldAActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_sy_CR_textfieldAActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_sy_CR_textfieldAActionPerformed

    private void momento_textfieldAActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_momento_textfieldAActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_momento_textfieldAActionPerformed

    private void paper_textfieldAActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_paper_textfieldAActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_paper_textfieldAActionPerformed

    private void entertainment_textfieldAActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_entertainment_textfieldAActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_entertainment_textfieldAActionPerformed

    private void wine_textfieldAActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_wine_textfieldAActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_wine_textfieldAActionPerformed

    private void silver_textfieldAActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_silver_textfieldAActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_silver_textfieldAActionPerformed

    private void security_textfieldAActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_security_textfieldAActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_security_textfieldAActionPerformed

    private void property_textfieldAActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_property_textfieldAActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_property_textfieldAActionPerformed

    private void okayBMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_okayBMouseClicked
        viewByVR();
    }//GEN-LAST:event_okayBMouseClicked

    private void insert_buttonAMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_insert_buttonAMouseClicked
        // TODO add your handling code here:
        String vr = VR_textfieldA.getText().trim();
        String date = validate_date((String) date_comboboxA.getSelectedItem(), (String) month_comboboxA.getSelectedItem(), (String) year_comboboxA.getSelectedItem());
        if (!vr.equals("")) {
            if (!date.equals("")) {
                try {
                    PreparedStatement pstmt = con.prepareStatement("Insert into ? values(?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?);");

                    boolean flag = false;
                    if (receipt_radio.isSelected()) {
                        pstmt = con.prepareStatement("Insert into JCO_mess_receipt values(?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?);");

                        flag = true;
                    } else if (payment_radio.isSelected()) {
                        pstmt = con.prepareStatement("Insert into JCO_mess_payment values(?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?);");
                        flag = true;
                    } else {
                        JOptionPane.showMessageDialog(null, "Select a type (receipt/Payment)", "Unsuccessful", JOptionPane.ERROR_MESSAGE);
                    }
                    if (flag) {
                        pstmt.setString(1, vr);
                        pstmt.setString(2, date);
                        pstmt.setString(3, from_textfieldA.getText().trim());
                        pstmt.setString(4, on_textfieldA.getText().trim());
                        pstmt.setString(5, cash_textfieldA.getText().trim());
                        pstmt.setString(6, bank_textfieldA.getText().trim());
                        pstmt.setString(7, mess_fund_textfieldA.getText().trim());
                        pstmt.setString(8, MMA_textfieldA.getText().trim());
                        pstmt.setString(9, entertainment_textfieldA.getText().trim());
                        pstmt.setString(10, paper_textfieldA.getText().trim());
                        pstmt.setString(11, silver_textfieldA.getText().trim());
                        pstmt.setString(12, security_textfieldA.getText().trim());
                        pstmt.setString(13, momento_textfieldA.getText().trim());
                        pstmt.setString(14, wine_textfieldA.getText().trim());
                        pstmt.setString(15, regt_textfieldA.getText().trim());
                        pstmt.setString(16, MS_textfieldA.getText().trim());
                        pstmt.setString(17, sy_DR_textfieldA.getText().trim());
                        pstmt.setString(18, sy_CR_textfieldA.getText().trim());
                        pstmt.setString(19, FDR_textfieldA.getText().trim());
                        pstmt.setString(20, property_textfieldA.getText().trim());
                        pstmt.executeUpdate();
                        JOptionPane.showMessageDialog(null, "Entry Successful", "Successful", JOptionPane.WARNING_MESSAGE);

                    }

                } catch (java.sql.SQLIntegrityConstraintViolationException e) {
                    JOptionPane.showMessageDialog(null, "Voucher already exists", "Unsuccessful", JOptionPane.ERROR_MESSAGE);

                } catch (Exception e) {
                    JOptionPane.showMessageDialog(null, e);

                }
            } else {
                JOptionPane.showMessageDialog(null, "Enter Valid date", "Unsuccessful", JOptionPane.ERROR_MESSAGE);
            }
        } else {
            JOptionPane.showMessageDialog(null, "VR cannot be empty!", "Unsuccessful", JOptionPane.ERROR_MESSAGE);

        }


    }//GEN-LAST:event_insert_buttonAMouseClicked

    private void insert_buttonAMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_insert_buttonAMouseEntered
        insert_buttonA.setBackground(new java.awt.Color(237, 224, 255));
    }//GEN-LAST:event_insert_buttonAMouseEntered

    private void insert_buttonAMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_insert_buttonAMouseExited
        insert_buttonA.setBackground(new java.awt.Color(240, 240, 240));
    }//GEN-LAST:event_insert_buttonAMouseExited

    private void refresh_buttonBMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_refresh_buttonBMouseClicked
        if (VR_textfieldB.getText().trim().equals("")) {
            viewall();
        } else {
            viewByVR();
        }
        VR_panel = "";
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
            ResultSet resultSet = statement.executeQuery("select * from JCO_mess_receipt");
            XSSFWorkbook workbook = new XSSFWorkbook();
            XSSFSheet spreadsheet = null;
            XSSFSheet spreadsheet1 = null;
            spreadsheet = workbook.createSheet("Receipt");
            spreadsheet1 = workbook.createSheet("Payment");

            XSSFRow row = spreadsheet.createRow(0);
            XSSFCell cell;
            cell = row.createCell(0);
            cell.setCellValue("Date");
            cell = row.createCell(1);
            cell.setCellValue("VR");
            cell = row.createCell(2);
            cell.setCellValue("Received From");
            cell = row.createCell(3);
            cell.setCellValue("On Account of");
            cell = row.createCell(4);
            cell.setCellValue("Cash");
            cell = row.createCell(5);
            cell.setCellValue("Bank");
            cell = row.createCell(6);
            cell.setCellValue("Mess Fund");
            cell = row.createCell(7);
            cell.setCellValue("MMA");
            cell = row.createCell(8);
            cell.setCellValue("Entertainment");
            cell = row.createCell(9);
            cell.setCellValue("Paper");
            cell = row.createCell(10);
            cell.setCellValue("Silver");
            cell = row.createCell(11);
            cell.setCellValue("JCO Security");
            cell = row.createCell(12);
            cell.setCellValue("Momento");
            cell = row.createCell(13);
            cell.setCellValue("Wine");
            cell = row.createCell(14);
            cell.setCellValue("Regt Cutting");
            cell = row.createCell(15);
            cell.setCellValue("M&S");
            cell = row.createCell(16);
            cell.setCellValue("Sy DR");
            cell = row.createCell(17);
            cell.setCellValue("Sy CR");
            cell = row.createCell(18);
            cell.setCellValue("FDR");
            cell = row.createCell(19);
            cell.setCellValue("Property");
            int i = 1;

            while (resultSet.next()) {
                row = spreadsheet.createRow(i);
                cell = row.createCell(0);
                cell.setCellValue(resultSet.getString("date"));
                cell = row.createCell(1);
                cell.setCellValue(resultSet.getString("voucher"));
                cell = row.createCell(2);
                cell.setCellValue(resultSet.getString("Received_from"));
                cell = row.createCell(3);
                cell.setCellValue(resultSet.getString("on_account"));
                cell = row.createCell(4);
                cell.setCellValue(resultSet.getString("cash"));
                cell = row.createCell(5);
                cell.setCellValue(resultSet.getString("bank"));
                cell = row.createCell(6);
                cell.setCellValue(resultSet.getString("mess_fund"));
                cell = row.createCell(7);
                cell.setCellValue(resultSet.getString("MMA"));
                cell = row.createCell(8);
                cell.setCellValue(resultSet.getString("entertainment"));
                cell = row.createCell(9);
                cell.setCellValue(resultSet.getString("paper"));
                cell = row.createCell(10);
                cell.setCellValue(resultSet.getString("silver"));
                cell = row.createCell(11);
                cell.setCellValue(resultSet.getString("JCO_security"));
                cell = row.createCell(12);
                cell.setCellValue(resultSet.getString("momento"));
                cell = row.createCell(13);
                cell.setCellValue(resultSet.getString("wine"));
                cell = row.createCell(14);
                cell.setCellValue(resultSet.getString("regt_cutting"));
                cell = row.createCell(15);
                cell.setCellValue(resultSet.getString("M_S"));
                cell = row.createCell(16);
                cell.setCellValue(resultSet.getString("sy_DR"));
                cell = row.createCell(17);
                cell.setCellValue(resultSet.getString("sy_CR"));
                cell = row.createCell(18);
                cell.setCellValue(resultSet.getString("FDR"));
                cell = row.createCell(19);
                cell.setCellValue(resultSet.getString("property"));

                i++;
            }

            resultSet = statement.executeQuery("select * from JCO_mess_payment");
            row = spreadsheet1.createRow(0);
            cell = row.createCell(0);
            cell.setCellValue("Date");
            cell = row.createCell(1);
            cell.setCellValue("VR");
            cell = row.createCell(2);
            cell.setCellValue("Received From");
            cell = row.createCell(3);
            cell.setCellValue("On Account of");
            cell = row.createCell(4);
            cell.setCellValue("Cash");
            cell = row.createCell(5);
            cell.setCellValue("Bank");
            cell = row.createCell(6);
            cell.setCellValue("Mess Fund");
            cell = row.createCell(7);
            cell.setCellValue("MMA");
            cell = row.createCell(8);
            cell.setCellValue("Entertainment");
            cell = row.createCell(9);
            cell.setCellValue("Paper");
            cell = row.createCell(10);
            cell.setCellValue("Silver");
            cell = row.createCell(11);
            cell.setCellValue("JCO Security");
            cell = row.createCell(12);
            cell.setCellValue("Momento");
            cell = row.createCell(13);
            cell.setCellValue("Wine");
            cell = row.createCell(14);
            cell.setCellValue("Regt Cutting");
            cell = row.createCell(15);
            cell.setCellValue("M&S");
            cell = row.createCell(16);
            cell.setCellValue("Sy DR");
            cell = row.createCell(17);
            cell.setCellValue("Sy CR");
            cell = row.createCell(18);
            cell.setCellValue("FDR");
            cell = row.createCell(19);
            cell.setCellValue("Property");
            i = 1;

            while (resultSet.next()) {
                row = spreadsheet1.createRow(i);
                cell = row.createCell(0);
                cell.setCellValue(resultSet.getString("date"));
                cell = row.createCell(1);
                cell.setCellValue(resultSet.getString("voucher"));
                cell = row.createCell(2);
                cell.setCellValue(resultSet.getString("Received_from"));
                cell = row.createCell(3);
                cell.setCellValue(resultSet.getString("on_account"));
                cell = row.createCell(4);
                cell.setCellValue(resultSet.getString("cash"));
                cell = row.createCell(5);
                cell.setCellValue(resultSet.getString("bank"));
                cell = row.createCell(6);
                cell.setCellValue(resultSet.getString("mess_fund"));
                cell = row.createCell(7);
                cell.setCellValue(resultSet.getString("MMA"));
                cell = row.createCell(8);
                cell.setCellValue(resultSet.getString("entertainment"));
                cell = row.createCell(9);
                cell.setCellValue(resultSet.getString("paper"));
                cell = row.createCell(10);
                cell.setCellValue(resultSet.getString("silver"));
                cell = row.createCell(11);
                cell.setCellValue(resultSet.getString("JCO_security"));
                cell = row.createCell(12);
                cell.setCellValue(resultSet.getString("momento"));
                cell = row.createCell(13);
                cell.setCellValue(resultSet.getString("wine"));
                cell = row.createCell(14);
                cell.setCellValue(resultSet.getString("regt_cutting"));
                cell = row.createCell(15);
                cell.setCellValue(resultSet.getString("M_S"));
                cell = row.createCell(16);
                cell.setCellValue(resultSet.getString("sy_DR"));
                cell = row.createCell(17);
                cell.setCellValue(resultSet.getString("sy_CR"));
                cell = row.createCell(18);
                cell.setCellValue(resultSet.getString("FDR"));
                cell = row.createCell(19);
                cell.setCellValue(resultSet.getString("property"));

                i++;
            }
            path_file paths = new path_file();
            FileOutputStream out = new FileOutputStream(new File(paths.csv + "\\JCO_Mess.xlsx"));
            workbook.write(out);
            JOptionPane.showMessageDialog(null, "JCO_Mess written successfully!", "Successful", JOptionPane.WARNING_MESSAGE);
            Desktop.getDesktop().open(new File(paths.csv + "/JCO_Mess.xlsx"));

        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, ex);
        }
    }//GEN-LAST:event_print_buttonBMouseClicked

    private void print_buttonBMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_print_buttonBMouseEntered
        print_buttonB.setBackground(new java.awt.Color(237, 224, 255));
    }//GEN-LAST:event_print_buttonBMouseEntered

    private void print_buttonBMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_print_buttonBMouseExited
        print_buttonB.setBackground(new java.awt.Color(240, 240, 240));
    }//GEN-LAST:event_print_buttonBMouseExited

    private void fullscreen_buttonBMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_fullscreen_buttonBMouseClicked
        if (VR_textfieldB.getText().trim().equals("")) {
            viewall();
            fullscreen_panelB.setVisible(true);
            bg.setVisible(false);
        } else if (viewByVR()) {
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

    private void exit_fullscreen_buttonBMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_exit_fullscreen_buttonBMouseClicked
        bg.setVisible(true);
        fullscreen_panelB.setVisible(false);
        VR_panel = "";
    }//GEN-LAST:event_exit_fullscreen_buttonBMouseClicked

    private void exit_fullscreen_buttonBMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_exit_fullscreen_buttonBMouseEntered
        exit_fullscreen_buttonB.setBackground(new java.awt.Color(237, 224, 255));
    }//GEN-LAST:event_exit_fullscreen_buttonBMouseEntered

    private void exit_fullscreen_buttonBMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_exit_fullscreen_buttonBMouseExited
        exit_fullscreen_buttonB.setBackground(new java.awt.Color(240, 240, 240));
    }//GEN-LAST:event_exit_fullscreen_buttonBMouseExited

    private void regt_textfieldAActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_regt_textfieldAActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_regt_textfieldAActionPerformed

    private void MS_textfieldAActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_MS_textfieldAActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_MS_textfieldAActionPerformed

    private void formWindowOpened(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowOpened
        //for a centered frame
        Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
        this.setLocation(dim.width / 2 - this.getSize().width / 2, dim.height / 2 - this.getSize().height / 2);
        //for a centered frame
        go_back = true;
    }//GEN-LAST:event_formWindowOpened

    private void receipt_radioActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_receipt_radioActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_receipt_radioActionPerformed

    private void payment_radioActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_payment_radioActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_payment_radioActionPerformed

    private void FDR_textfieldAActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_FDR_textfieldAActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_FDR_textfieldAActionPerformed

    private void VR_textfieldBKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_VR_textfieldBKeyPressed
        if (evt.getKeyCode() == java.awt.event.KeyEvent.VK_ENTER) {
            okayBMouseClicked(null);
        }
    }//GEN-LAST:event_VR_textfieldBKeyPressed

    private void reset_buttonAMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_reset_buttonAMouseClicked
          VR_textfieldA.setText("");
        date_comboboxA.setSelectedItem("DD");
        month_comboboxA.setSelectedItem("MM");
        year_comboboxA.setSelectedItem("YYYY");
        from_textfieldA.setText("");
        on_textfieldA.setText("");
        cash_textfieldA.setText("");
        bank_textfieldA.setText("");
        mess_fund_textfieldA.setText("");
        MMA_textfieldA.setText("");
        entertainment_textfieldA.setText("");
        paper_textfieldA.setText("");
        silver_textfieldA.setText("");
        security_textfieldA.setText("");
        momento_textfieldA.setText("");
        wine_textfieldA.setText("");
        regt_textfieldA.setText("");
        MS_textfieldA.setText("");
        sy_DR_textfieldA.setText("");
        sy_CR_textfieldA.setText("");
        FDR_textfieldA.setText("");
        property_textfieldA.setText("");
        buttonGroup1.clearSelection();

    }//GEN-LAST:event_reset_buttonAMouseClicked

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
            java.util.logging.Logger.getLogger(JCO_Mess.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(JCO_Mess.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(JCO_Mess.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(JCO_Mess.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new JCO_Mess().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel FDR_labelA;
    private javax.swing.JTextField FDR_textfieldA;
    private javax.swing.JLabel MMA_labelA;
    private javax.swing.JTextField MMA_textfieldA;
    private javax.swing.JLabel MS_labelA;
    private javax.swing.JTextField MS_textfieldA;
    private javax.swing.JScrollPane ScrollPaneA;
    private javax.swing.JScrollPane ScrollPaneB;
    private javax.swing.JSeparator Separator;
    private javax.swing.JTable TableB;
    private javax.swing.JLabel VR_labelA;
    private javax.swing.JLabel VR_labelB;
    private javax.swing.JTextField VR_textfieldA;
    private javax.swing.JTextField VR_textfieldB;
    private javax.swing.JLabel bank_labelA;
    private javax.swing.JTextField bank_textfieldA;
    private javax.swing.JPanel bg;
    private javax.swing.ButtonGroup buttonGroup1;
    private javax.swing.JLabel cash_labelA;
    private javax.swing.JTextField cash_textfieldA;
    private javax.swing.JSeparator comp_type_underlineB;
    private javax.swing.JLabel current_dateA;
    private javax.swing.JLabel damage_labelA;
    private javax.swing.JComboBox<String> date_comboboxA;
    private javax.swing.JLabel date_labelA;
    private javax.swing.JLabel entertainment_labelA;
    private javax.swing.JTextField entertainment_textfieldA;
    private javax.swing.JPanel exit_fullscreen_buttonB;
    private javax.swing.JLabel exit_fullscreen_labelB;
    private javax.swing.JLabel exit_icon;
    private javax.swing.JLabel from_labelA;
    private javax.swing.JTextField from_textfieldA;
    private javax.swing.JScrollPane fullscreen_ScrollPaneB;
    private javax.swing.JPanel fullscreen_buttonB;
    private javax.swing.JLabel fullscreen_labelB;
    private javax.swing.JPanel fullscreen_panelB;
    private javax.swing.JTable fullscreen_tableB;
    private javax.swing.JLabel go_back_label;
    private javax.swing.JLabel initials_labelA;
    private javax.swing.JPanel insert_buttonA;
    private javax.swing.JLabel insert_labelA;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JPanel menuA;
    private javax.swing.JLabel menuA_icon;
    private javax.swing.JLabel menuA_label;
    private javax.swing.JPanel menuB;
    private javax.swing.JLabel menuB_icon;
    private javax.swing.JLabel menuB_label;
    private javax.swing.JLabel mess_fund_labelA;
    private javax.swing.JTextField mess_fund_textfieldA;
    private javax.swing.JLabel minimize;
    private javax.swing.JTextField momento_textfieldA;
    private javax.swing.JComboBox<String> month_comboboxA;
    private javax.swing.JPanel move_panel;
    private javax.swing.JLabel okayB;
    private javax.swing.JLabel on_labelA;
    private javax.swing.JTextField on_textfieldA;
    private javax.swing.JPanel panelA;
    private javax.swing.JSeparator panelA_bottom_line;
    private javax.swing.JPanel panelB;
    private javax.swing.JLabel paper_labelA;
    private javax.swing.JTextField paper_textfieldA;
    private javax.swing.JRadioButton payment_radio;
    private javax.swing.JPanel print_buttonA;
    private javax.swing.JPanel print_buttonB;
    private javax.swing.JLabel print_labelA;
    private javax.swing.JLabel print_labelB;
    private javax.swing.JLabel property_labelA;
    private javax.swing.JTextField property_textfieldA;
    private javax.swing.JRadioButton receipt_radio;
    private javax.swing.JPanel refresh_buttonB;
    private javax.swing.JLabel refresh_labelB;
    private javax.swing.JLabel regt_labelA;
    private javax.swing.JTextField regt_textfieldA;
    private javax.swing.JLabel reset_buttonA;
    private javax.swing.JLabel security_labelA;
    private javax.swing.JTextField security_textfieldA;
    private javax.swing.JPanel side_pane;
    private javax.swing.JTextArea signature;
    private javax.swing.JLabel silver_labelA;
    private javax.swing.JTextField silver_textfieldA;
    private javax.swing.JLabel sy_CR_labelA;
    private javax.swing.JTextField sy_CR_textfieldA;
    private javax.swing.JLabel sy_DD_labelA;
    private javax.swing.JTextField sy_DR_textfieldA;
    private javax.swing.JScrollPane textArea_scrollpane;
    private javax.swing.JLabel title_icon;
    private javax.swing.JLabel title_label;
    private javax.swing.JSeparator underlineA10;
    private javax.swing.JSeparator underlineA11;
    private javax.swing.JSeparator underlineA12;
    private javax.swing.JSeparator underlineA13;
    private javax.swing.JSeparator underlineA14;
    private javax.swing.JSeparator underlineA15;
    private javax.swing.JSeparator underlineA16;
    private javax.swing.JSeparator underlineA17;
    private javax.swing.JSeparator underlineA18;
    private javax.swing.JSeparator underlineA19;
    private javax.swing.JSeparator underlineA2;
    private javax.swing.JSeparator underlineA20;
    private javax.swing.JSeparator underlineA21;
    private javax.swing.JSeparator underlineA22;
    private javax.swing.JSeparator underlineA5;
    private javax.swing.JSeparator underlineA6;
    private javax.swing.JSeparator underlineA7;
    private javax.swing.JSeparator underlineA8;
    private javax.swing.JSeparator underlineA9;
    private javax.swing.JLabel welfare_labelA;
    private javax.swing.JTextField wine_textfieldA;
    private javax.swing.JComboBox<String> year_comboboxA;
    // End of variables declaration//GEN-END:variables
}
