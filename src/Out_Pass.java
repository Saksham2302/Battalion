
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
import java.sql.ResultSet;
import java.sql.Statement;
import java.sql.PreparedStatement;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import java.time.format.DateTimeFormatter;
import java.time.LocalDateTime;
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
public class Out_Pass extends javax.swing.JFrame {

    private boolean go_back = true;
    /**
     * Creates new form Out_Pass
     */
    DefaultTableModel model_table, model_fullscreen;
    private int mouseX, mouseY;
    Database db = new Database();
    Connection con = db.create_connection(true);

    public Out_Pass() {
        initComponents();

        ScrollPaneC.getVerticalScrollBar().setUnitIncrement(16);
        ScrollPaneB.getVerticalScrollBar().setUnitIncrement(16);

        print_buttonA.setVisible(true);
        insert_buttonA.setVisible(true);
        refresh_buttonB.setVisible(false);
        refresh_buttonC.setVisible(false);
        print_buttonC.setVisible(false);
    }

    private String retrieve_Army_No() {
        String outpass_name = "";
        try {
            Statement stmt = con.createStatement();
            if (service_number_textfield.getText().length() != 0) {
                String Query = "Select Name, Rank_, Unit, Company, Trade from attach_in where Service_no = '" + service_number_textfield.getText() + "';";
                ResultSet rs = stmt.executeQuery(Query);
                String name_, rank_, unit_, trade_, company_;
                if (rs.next()) {
                    name_ = rs.getString("Name");
                    rank_ = rs.getString("Rank_");
                    unit_ = rs.getString("Unit");
                    company_ = rs.getString("Company");
                    trade_ = rs.getString("Trade");
                    name.setText("> " + name_);
                    rank.setText("> " + rank_);
                    unit.setText("> " + unit_);
                    trade.setText("> " + trade_);
                    company.setText("> " + company_);
                    outpass_name = name_;
                } else {
                    Query = "Select Name, Rank_, Company from post_in where Service_no = '" + service_number_textfield.getText() + "';";
                    rs = stmt.executeQuery(Query);
                    if (rs.next()) {
                        name_ = rs.getString("Name");
                        rank_ = rs.getString("Rank_");
                        company_ = rs.getString("Company");
                        name.setText("> " + name_);
                        rank.setText("> " + rank_);
                        unit.setText("> XXXXXXX");
                        trade.setText("> XXXXXX");
                        company.setText("> " + company_);
                        outpass_name = name_;
                    } else {
                        Query = "Select Name, Rank_, Company from new_registration where Service_no = '" + service_number_textfield.getText() + "';";
                        rs = stmt.executeQuery(Query);
                        if (rs.next()) {
                            name_ = rs.getString("Name");
                            rank_ = rs.getString("Rank_");
                            company_ = rs.getString("Company");
                            name.setText("> " + name_);
                            rank.setText("> " + rank_);
                            unit.setText("> XXXXXXX");
                            trade.setText("> XXXXXXX");
                            company.setText("> " + company_);
                            outpass_name = name_;
                        }
                    }
                }

                if (outpass_name.equals("")) {
                    JOptionPane.showMessageDialog(null, "Army Number does not exist!", "Error", JOptionPane.ERROR_MESSAGE);
                    name.setText("> XXXXXXX");
                    rank.setText("> XXXXXXX");
                    unit.setText("> XXXXXXX");
                    trade.setText("> XXXXXXX");
                    company.setText("> XXXXXXX");
                }
            } else {
                JOptionPane.showMessageDialog(null, "Army Number cannot be empty!", "Error", JOptionPane.ERROR_MESSAGE);

            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);

        }
        return outpass_name;
    }

    private boolean retrieve_time_date() {
        boolean flag = false;
        try {
            Statement stmt = con.createStatement();
            String Query = "Select * from outpass where Service_no='" + service_number_textfield.getText() + "' and Time_in is null";
            ResultSet rs = stmt.executeQuery(Query);

            while (rs.next()) {
                flag = true;
                String time_out = rs.getString("Time_out");
                String outpass_date = rs.getString("Date");
                String date_ = new String(outpass_date);
                String[] timeout = time_out.split(":");
                String date_day = outpass_date.substring(6, 8);
                String date_month = outpass_date.substring(4, 6);
                String date_year = outpass_date.substring(0, 4);
                hour_out_combobox.setSelectedItem(timeout[0]);
                minute_out_combobox.setSelectedItem(timeout[1]);
                date_combobox.setSelectedItem(date_day);
                month_combobox.setSelectedItem(date_month);
                year_combobox.setSelectedItem(date_year);
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
        }
        return flag;
    }

    private String validate_date() {
        String day_ = (String) date_combobox.getSelectedItem();
        String month_ = (String) month_combobox.getSelectedItem();
        String year_ = (String) year_combobox.getSelectedItem();
        String Join_date = "";
        if ("DD".equals(day_) || "MO".equals(month_) || "YYYY".equals(year_)) {
            JOptionPane.showMessageDialog(null, "Enter a Valid Date", "Error", JOptionPane.ERROR_MESSAGE);
        } else {
            int day = Integer.parseInt(day_);
            int month = Integer.parseInt(month_);
            int year = Integer.parseInt(year_);
            boolean flag = false;
            if ((month == 1 || month == 3 || month == 5 || month == 7 || month == 8 || month == 10 || month == 12) && day <= 31) {
                flag = true;
            } else if ((month == 4 || month == 6 || month == 9 || month == 11) && day <= 30) {
                flag = true;
            } else if (month == 2 && ((year % 4 == 0 && day <= 29) || (year % 4 != 0 && day <= 28))) {
                flag = true;
            }
            if (flag == true) {
                Join_date = year_ + "" + month_ + "" + day_;
            } else {
                JOptionPane.showMessageDialog(null, "Enter a Valid Date", "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
        return Join_date;
    }

    private boolean validate_time(String time_in, String time_out) {
        String[] tin = time_in.trim().split(":");
        String[] tout = time_out.trim().split(":");
        int tin_hour = Integer.valueOf(tin[0].trim());
        int tin_min = Integer.valueOf(tin[1].trim());
        int tout_hour = Integer.valueOf(tout[0].trim());
        int tout_min = Integer.valueOf(tout[1].trim());

        if ((tin_hour > tout_hour) || ((tin_hour == tout_hour) && (tin_min >= tout_min))) {
            return true;
        } else {
            JOptionPane.showMessageDialog(null, "Enter a Valid Time in", "Error", JOptionPane.ERROR_MESSAGE);
            return false;

        }
    }

    private void currently_out() {
        try {
            model_table = (DefaultTableModel) Table.getModel();
            Statement stmt = con.createStatement();
            String Query = "Select Service_no, Name, Time_in, Time_out, Date from outpass where time_in is null order by Date, Time_out;";
            ResultSet rs = stmt.executeQuery(Query);
            String service_no, name, time_in, time_out, date;
            model_table.setRowCount(0);
            int sno = 0;
            while (rs.next()) {
                sno++;
                service_no = rs.getString("Service_no");
                name = rs.getString("Name");
                time_in = rs.getString("Time_in");
                time_out = rs.getString("Time_out");
                date = rs.getString("Date");

                model_table.addRow(new Object[]{
                    sno, service_no, name, date, time_out, time_in
                });
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
        }

    }

    private void current_time_date(String time_date) {
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");
        LocalDateTime now = LocalDateTime.now();
        String[] day = dtf.format(now).split(" ");
        if (time_date == "time_in") {
            String time[] = day[1].split(":");
            hour_in_combobox.setSelectedItem(time[0]);
            minute_in_combobox.setSelectedItem(time[1]);
        } else if (time_date == "time_out") {
            String time[] = day[1].split(":");
            hour_out_combobox.setSelectedItem(time[0]);
            minute_out_combobox.setSelectedItem(time[1]);
        } else if (time_date == "date") {
            String date[] = day[0].split("-");
            date_combobox.setSelectedItem(date[0]);
            month_combobox.setSelectedItem(date[1]);
            year_combobox.setSelectedItem(date[2]);
        }

    }

    private void viewByArmyNo() {
        try {
            model_table = (DefaultTableModel) TableC.getModel();
            Statement stmt = con.createStatement();
            boolean flag = false;

            if (service_numberC.getText().length() != 0) {
                String Query = "Select * from outpass where Service_no='" + service_numberC.getText() + "';";
                ResultSet rs = stmt.executeQuery(Query);
                String service_no, name_, time_in, time_out, date;
                int sno = 0;
                model_table.setRowCount(0);
                while (rs.next()) {
                    flag = true;
                    sno++;
                    service_no = rs.getString("Service_no");
                    name_ = rs.getString("name");
                    time_in = rs.getString("Time_in");
                    time_out = rs.getString("Time_out");
                    date = rs.getString("Date");

                    model_table.addRow(new Object[]{
                        sno, service_no, name_, date, time_out, time_in
                    });
                }
            }
            if (flag == false) {
                JOptionPane.showMessageDialog(null, "Army Number does not exist!", "Error", JOptionPane.ERROR_MESSAGE);

            }

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
        }
    }

    private void viewall() {
        try {
            model_table = (DefaultTableModel) TableC.getModel();

            Statement stmt = con.createStatement();
            String Query = "Select * from outpass;";
            ResultSet rs = stmt.executeQuery(Query);
            String service_no, name_, time_in, time_out, date;
            int sno = 0;
            model_table.setRowCount(0);
            while (rs.next()) {
                sno++;
                service_no = rs.getString("Service_no");
                name_ = rs.getString("name");
                time_in = rs.getString("Time_in");
                time_out = rs.getString("Time_out");
                date = rs.getString("Date");

                model_table.addRow(new Object[]{
                    sno, service_no, name_, date, time_out, time_in
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
        title_label = new javax.swing.JLabel();
        title_icon = new javax.swing.JLabel();
        menuA = new javax.swing.JPanel();
        menuA_icon = new javax.swing.JLabel();
        menuA_label = new javax.swing.JLabel();
        menuB = new javax.swing.JPanel();
        menuB_icon = new javax.swing.JLabel();
        menuB_label = new javax.swing.JLabel();
        menuC = new javax.swing.JPanel();
        menuC_icon = new javax.swing.JLabel();
        menuC_label = new javax.swing.JLabel();
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
        date_label = new javax.swing.JLabel();
        time_in_label = new javax.swing.JLabel();
        time_out_label = new javax.swing.JLabel();
        hour_in_combobox = new javax.swing.JComboBox<>();
        minute_in_combobox = new javax.swing.JComboBox<>();
        hour_out_combobox = new javax.swing.JComboBox<>();
        minute_out_combobox = new javax.swing.JComboBox<>();
        date_combobox = new javax.swing.JComboBox<>();
        month_combobox = new javax.swing.JComboBox<>();
        year_combobox = new javax.swing.JComboBox<>();
        current_date = new javax.swing.JLabel();
        current_time_in = new javax.swing.JLabel();
        current_time_out = new javax.swing.JLabel();
        panelB = new javax.swing.JPanel();
        ScrollPaneB = new javax.swing.JScrollPane();
        Table = new javax.swing.JTable();
        panelC = new javax.swing.JPanel();
        service_number_labelC = new javax.swing.JLabel();
        service_numberC = new javax.swing.JTextField();
        textfield_underline = new javax.swing.JSeparator();
        okayC = new javax.swing.JLabel();
        ScrollPaneC = new javax.swing.JScrollPane();
        TableC = new javax.swing.JTable();
        print_buttonA = new javax.swing.JPanel();
        print_label = new javax.swing.JLabel();
        insert_buttonA = new javax.swing.JPanel();
        insert_label = new javax.swing.JLabel();
        print_buttonC = new javax.swing.JPanel();
        print_labelD = new javax.swing.JLabel();
        refresh_buttonC = new javax.swing.JPanel();
        refresh_labelD = new javax.swing.JLabel();
        refresh_buttonB = new javax.swing.JPanel();
        refresh_label = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setBounds(new java.awt.Rectangle(0, 0, 1280, 720));
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
        title_label.setText("Out Pass");
        side_pane.add(title_label, new org.netbeans.lib.awtextra.AbsoluteConstraints(112, 135, 140, 40));

        title_icon.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        title_icon.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/PNGS/out_pass white.png"))); // NOI18N
        side_pane.add(title_icon, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 118, 60, 70));

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
        menuA_label.setText("Create Pass");

        javax.swing.GroupLayout menuALayout = new javax.swing.GroupLayout(menuA);
        menuA.setLayout(menuALayout);
        menuALayout.setHorizontalGroup(
            menuALayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(menuALayout.createSequentialGroup()
                .addGap(27, 27, 27)
                .addComponent(menuA_icon)
                .addGap(33, 33, 33)
                .addComponent(menuA_label)
                .addContainerGap(156, Short.MAX_VALUE))
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
        menuB_label.setText("Currently Out");

        javax.swing.GroupLayout menuBLayout = new javax.swing.GroupLayout(menuB);
        menuB.setLayout(menuBLayout);
        menuBLayout.setHorizontalGroup(
            menuBLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(menuBLayout.createSequentialGroup()
                .addGap(27, 27, 27)
                .addComponent(menuB_icon)
                .addGap(33, 33, 33)
                .addComponent(menuB_label)
                .addContainerGap(144, Short.MAX_VALUE))
        );
        menuBLayout.setVerticalGroup(
            menuBLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(menuBLayout.createSequentialGroup()
                .addGap(1, 1, 1)
                .addGroup(menuBLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(menuB_icon, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(menuB_label, javax.swing.GroupLayout.DEFAULT_SIZE, 60, Short.MAX_VALUE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        side_pane.add(menuB, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 300, 330, 60));

        menuC.setBackground(new java.awt.Color(85, 65, 118));
        menuC.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                menuCMouseClicked(evt);
            }
        });

        menuC_icon.setForeground(new java.awt.Color(255, 255, 255));
        menuC_icon.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        menuC_icon.setText("C");

        menuC_label.setFont(new java.awt.Font("Roboto", 1, 18)); // NOI18N
        menuC_label.setForeground(new java.awt.Color(255, 255, 255));
        menuC_label.setText("View by Army Number");

        javax.swing.GroupLayout menuCLayout = new javax.swing.GroupLayout(menuC);
        menuC.setLayout(menuCLayout);
        menuCLayout.setHorizontalGroup(
            menuCLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(menuCLayout.createSequentialGroup()
                .addGap(27, 27, 27)
                .addComponent(menuC_icon)
                .addGap(33, 33, 33)
                .addComponent(menuC_label)
                .addContainerGap(69, Short.MAX_VALUE))
        );
        menuCLayout.setVerticalGroup(
            menuCLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(menuCLayout.createSequentialGroup()
                .addGap(1, 1, 1)
                .addGroup(menuCLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(menuC_icon, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(menuC_label, javax.swing.GroupLayout.DEFAULT_SIZE, 60, Short.MAX_VALUE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        side_pane.add(menuC, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 360, 330, 60));

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
        panelA.add(trade_label, new org.netbeans.lib.awtextra.AbsoluteConstraints(350, 260, -1, 40));

        trade.setFont(new java.awt.Font("Roboto Bk", 0, 24)); // NOI18N
        trade.setForeground(new java.awt.Color(54, 33, 89));
        trade.setText("> XXXXXXX");
        panelA.add(trade, new org.netbeans.lib.awtextra.AbsoluteConstraints(350, 300, -1, 30));

        unit_label.setBackground(new java.awt.Color(255, 255, 255));
        unit_label.setFont(new java.awt.Font("Montserrat", 0, 28)); // NOI18N
        unit_label.setForeground(new java.awt.Color(51, 51, 51));
        unit_label.setText("Unit");
        panelA.add(unit_label, new org.netbeans.lib.awtextra.AbsoluteConstraints(620, 170, -1, 40));

        unit.setFont(new java.awt.Font("Roboto Bk", 0, 24)); // NOI18N
        unit.setForeground(new java.awt.Color(54, 33, 89));
        unit.setText("> XXXXXXX");
        panelA.add(unit, new org.netbeans.lib.awtextra.AbsoluteConstraints(620, 210, -1, 30));

        date_label.setBackground(new java.awt.Color(255, 255, 255));
        date_label.setFont(new java.awt.Font("Montserrat", 0, 28)); // NOI18N
        date_label.setForeground(new java.awt.Color(51, 51, 51));
        date_label.setText("Date");
        panelA.add(date_label, new org.netbeans.lib.awtextra.AbsoluteConstraints(620, 360, -1, 40));

        time_in_label.setBackground(new java.awt.Color(255, 255, 255));
        time_in_label.setFont(new java.awt.Font("Montserrat", 0, 28)); // NOI18N
        time_in_label.setForeground(new java.awt.Color(51, 51, 51));
        time_in_label.setText("Time In");
        panelA.add(time_in_label, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 360, -1, 40));

        time_out_label.setBackground(new java.awt.Color(255, 255, 255));
        time_out_label.setFont(new java.awt.Font("Montserrat", 0, 28)); // NOI18N
        time_out_label.setForeground(new java.awt.Color(51, 51, 51));
        time_out_label.setText("Time Out");
        panelA.add(time_out_label, new org.netbeans.lib.awtextra.AbsoluteConstraints(350, 360, -1, 40));

        hour_in_combobox.setFont(new java.awt.Font("Roboto", 0, 18)); // NOI18N
        hour_in_combobox.setForeground(new java.awt.Color(44, 62, 80));
        hour_in_combobox.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "HR", "00", "01", "02", "03", "04", "05", "06", "07", "08", "09", "10", "11", "12", "13", "14", "15", "16", "17", "18", "19", "20", "21", "22", "23" }));
        hour_in_combobox.setBorder(null);
        panelA.add(hour_in_combobox, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 400, 70, 40));

        minute_in_combobox.setFont(new java.awt.Font("Roboto", 0, 18)); // NOI18N
        minute_in_combobox.setForeground(new java.awt.Color(44, 62, 80));
        minute_in_combobox.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "MM", "00", "01", "02", "03", "04", "05", "06", "07", "08", "09", "10", "11", "12", "13", "14", "15", "16", "17", "18", "19", "20", "21", "22", "23", "24", "25", "26", "27", "28", "29", "30", "31", "32", "33", "34", "35", "36", "37", "38", "39", "40", "41", "42", "43", "44", "45", "46", "47", "48", "49", "50", "51", "52", "53", "54", "55", "56", "57", "58", "59" }));
        minute_in_combobox.setBorder(null);
        panelA.add(minute_in_combobox, new org.netbeans.lib.awtextra.AbsoluteConstraints(160, 400, 70, 40));

        hour_out_combobox.setFont(new java.awt.Font("Roboto", 0, 18)); // NOI18N
        hour_out_combobox.setForeground(new java.awt.Color(44, 62, 80));
        hour_out_combobox.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "HR", "00", "01", "02", "03", "04", "05", "06", "07", "08", "09", "10", "11", "12", "13", "14", "15", "16", "17", "18", "19", "20", "21", "22", "23" }));
        hour_out_combobox.setBorder(null);
        hour_out_combobox.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                hour_out_comboboxActionPerformed(evt);
            }
        });
        panelA.add(hour_out_combobox, new org.netbeans.lib.awtextra.AbsoluteConstraints(350, 400, 70, 40));

        minute_out_combobox.setFont(new java.awt.Font("Roboto", 0, 18)); // NOI18N
        minute_out_combobox.setForeground(new java.awt.Color(44, 62, 80));
        minute_out_combobox.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "MM", "00", "01", "02", "03", "04", "05", "06", "07", "08", "09", "10", "11", "12", "13", "14", "15", "16", "17", "18", "19", "20", "21", "22", "23", "24", "25", "26", "27", "28", "29", "30", "31", "32", "33", "34", "35", "36", "37", "38", "39", "40", "41", "42", "43", "44", "45", "46", "47", "48", "49", "50", "51", "52", "53", "54", "55", "56", "57", "58", "59" }));
        minute_out_combobox.setBorder(null);
        panelA.add(minute_out_combobox, new org.netbeans.lib.awtextra.AbsoluteConstraints(430, 400, 70, 40));

        date_combobox.setFont(new java.awt.Font("Roboto", 0, 18)); // NOI18N
        date_combobox.setForeground(new java.awt.Color(44, 62, 80));
        date_combobox.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "DD", "01", "02", "03", "04", "05", "06", "07", "08", "09", "10", "11", "12", "13", "14", "15", "16", "17", "18", "19", "20", "21", "22", "23", "24", "25", "26", "27", "28", "29", "30", "31" }));
        date_combobox.setBorder(null);
        panelA.add(date_combobox, new org.netbeans.lib.awtextra.AbsoluteConstraints(620, 400, 70, 40));

        month_combobox.setFont(new java.awt.Font("Roboto", 0, 18)); // NOI18N
        month_combobox.setForeground(new java.awt.Color(44, 62, 80));
        month_combobox.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "MO", "01", "02", "03", "04", "05", "06", "07", "08", "09", "10", "11", "12" }));
        month_combobox.setBorder(null);
        panelA.add(month_combobox, new org.netbeans.lib.awtextra.AbsoluteConstraints(700, 400, 70, 40));

        year_combobox.setFont(new java.awt.Font("Roboto", 0, 18)); // NOI18N
        year_combobox.setForeground(new java.awt.Color(44, 62, 80));
        year_combobox.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "YYYY", "1950", "1951", "1952", "1953", "1954", "1955", "1956", "1957", "1958", "1959", "1960", "1961", "1962", "1963", "1964", "1965", "1966", "1967", "1968", "1969", "1970", "1971", "1972", "1973", "1974", "1975", "1976", "1977", "1978", "1979", "1980", "1981", "1982", "1983", "1984", "1985", "1986", "1987", "1988", "1989", "1990", "1991", "1992", "1993", "1994", "1995", "1996", "1997", "1998", "1999", "2000", "2001", "2002", "2003", "2004", "2005", "2006", "2007", "2008", "2009", "2010", "2011", "2012", "2013", "2014", "2015", "2016", "2017", "2018", "2019", "2020", "2021", "2022", "2023", "2024", "2025", "2026", "2027", "2028", "2029", "2030", "2031", "2032", "2033", "2034", "2035", "2036", "2037", "2038", "2039", "2040", "2041", "2042", "2043", "2044", "2045", "2046", "2047", "2048", "2049", "2050", "2051", "2052", "2053", "2054", "2055", "2056", "2057", "2058", "2059", "2060", "2061", "2062", "2063", "2064", "2065", "2066", "2067", "2068", "2069", "2070", "2071", "2072", "2073", "2074", "2075", "2076", "2077", "2078", "2079", "2080", "2081", "2082", "2083", "2084", "2085", "2086", "2087", "2088", "2089", "2090", "2091", "2092", "2093", "2094", "2095", "2096", "2097", "2098", "2099", "2100", " " }));
        year_combobox.setBorder(null);
        panelA.add(year_combobox, new org.netbeans.lib.awtextra.AbsoluteConstraints(780, 400, 70, 40));

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
        panelA.add(current_date, new org.netbeans.lib.awtextra.AbsoluteConstraints(620, 450, -1, -1));

        current_time_in.setBackground(new java.awt.Color(255, 255, 255));
        current_time_in.setFont(new java.awt.Font("Segoe UI", 0, 13)); // NOI18N
        current_time_in.setForeground(new java.awt.Color(60, 63, 65));
        current_time_in.setText("Get Current Time IN");
        current_time_in.setOpaque(true);
        current_time_in.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                current_time_inMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                current_time_inMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                current_time_inMouseExited(evt);
            }
        });
        panelA.add(current_time_in, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 450, -1, -1));

        current_time_out.setBackground(new java.awt.Color(255, 255, 255));
        current_time_out.setFont(new java.awt.Font("Segoe UI", 0, 13)); // NOI18N
        current_time_out.setForeground(new java.awt.Color(60, 63, 65));
        current_time_out.setText("Get Current Time OUT");
        current_time_out.setOpaque(true);
        current_time_out.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                current_time_outMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                current_time_outMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                current_time_outMouseExited(evt);
            }
        });
        panelA.add(current_time_out, new org.netbeans.lib.awtextra.AbsoluteConstraints(350, 450, -1, -1));

        ScrollPaneA.setViewportView(panelA);

        bg.add(ScrollPaneA, new org.netbeans.lib.awtextra.AbsoluteConstraints(340, 192, 910, 480));

        panelB.setBackground(new java.awt.Color(255, 255, 255));
        panelB.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        ScrollPaneB.setBackground(new java.awt.Color(255, 255, 255));
        ScrollPaneB.setHorizontalScrollBarPolicy(javax.swing.ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);

        Table.setFont(new java.awt.Font("Roboto", 0, 18)); // NOI18N
        Table.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "S.No", "Service Num", "Name", "Date", "Time OUT", "Time IN"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                true, false, false, false, true, true
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        Table.setFocusable(false);
        Table.setGridColor(new java.awt.Color(255, 255, 255));
        Table.setMaximumSize(new java.awt.Dimension(300, 60));
        Table.setMinimumSize(new java.awt.Dimension(300, 60));
        Table.setRowHeight(30);
        Table.setSelectionBackground(new java.awt.Color(54, 33, 89));
        Table.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TableKeyPressed(evt);
            }
        });
        ScrollPaneB.setViewportView(Table);
        if (Table.getColumnModel().getColumnCount() > 0) {
            Table.getColumnModel().getColumn(0).setMinWidth(50);
            Table.getColumnModel().getColumn(0).setPreferredWidth(50);
            Table.getColumnModel().getColumn(0).setMaxWidth(50);
        }

        panelB.add(ScrollPaneB, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 50, 780, 420));

        bg.add(panelB, new org.netbeans.lib.awtextra.AbsoluteConstraints(340, 192, 890, 480));

        panelC.setBackground(new java.awt.Color(255, 255, 255));
        panelC.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        service_number_labelC.setBackground(new java.awt.Color(255, 255, 255));
        service_number_labelC.setFont(new java.awt.Font("Montserrat", 0, 28)); // NOI18N
        service_number_labelC.setForeground(new java.awt.Color(51, 51, 51));
        service_number_labelC.setText("Army Number");
        panelC.add(service_number_labelC, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 50, -1, 40));

        service_numberC.setFont(new java.awt.Font("Roboto", 0, 24)); // NOI18N
        service_numberC.setForeground(new java.awt.Color(54, 33, 89));
        service_numberC.setBorder(null);
        service_numberC.setMargin(new java.awt.Insets(2, 4, 2, 2));
        service_numberC.setSelectionColor(new java.awt.Color(204, 204, 255));
        panelC.add(service_numberC, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 90, 400, 40));

        textfield_underline.setForeground(new java.awt.Color(54, 33, 89));
        panelC.add(textfield_underline, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 130, 400, 10));

        okayC.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/PNGS/check-mark.png"))); // NOI18N
        okayC.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                okayCMouseClicked(evt);
            }
        });
        panelC.add(okayC, new org.netbeans.lib.awtextra.AbsoluteConstraints(490, 90, -1, 40));

        ScrollPaneC.setBackground(new java.awt.Color(255, 255, 255));
        ScrollPaneC.setVerticalScrollBarPolicy(javax.swing.ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);

        TableC.setFont(new java.awt.Font("Roboto", 0, 18)); // NOI18N
        TableC.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "S. No.", "Army Number", "Name", "Date", "Time OUT", "Time IN"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, true, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        TableC.setAutoResizeMode(javax.swing.JTable.AUTO_RESIZE_ALL_COLUMNS);
        TableC.setFocusable(false);
        TableC.setGridColor(new java.awt.Color(255, 255, 255));
        TableC.setMaximumSize(null);
        TableC.setRowHeight(30);
        TableC.setSelectionBackground(new java.awt.Color(54, 33, 89));
        TableC.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        ScrollPaneC.setViewportView(TableC);
        if (TableC.getColumnModel().getColumnCount() > 0) {
            TableC.getColumnModel().getColumn(0).setMinWidth(40);
            TableC.getColumnModel().getColumn(0).setPreferredWidth(40);
            TableC.getColumnModel().getColumn(0).setMaxWidth(40);
        }

        panelC.add(ScrollPaneC, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 170, 780, 300));

        bg.add(panelC, new org.netbeans.lib.awtextra.AbsoluteConstraints(340, 192, 910, 480));

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

        print_label.setFont(new java.awt.Font("Roboto", 0, 14)); // NOI18N
        print_label.setForeground(new java.awt.Color(54, 33, 89));
        print_label.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        print_label.setText("PRINT");
        print_buttonA.add(print_label, new org.netbeans.lib.awtextra.AbsoluteConstraints(52, 13, 71, 24));

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

        print_buttonC.setForeground(new java.awt.Color(240, 240, 240));
        print_buttonC.setMaximumSize(new java.awt.Dimension(200, 200));
        print_buttonC.setMinimumSize(new java.awt.Dimension(200, 200));
        print_buttonC.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                print_buttonCMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                print_buttonCMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                print_buttonCMouseExited(evt);
            }
        });
        print_buttonC.setLayout(null);

        print_labelD.setBackground(new java.awt.Color(54, 33, 89));
        print_labelD.setFont(new java.awt.Font("Roboto", 0, 14)); // NOI18N
        print_labelD.setForeground(new java.awt.Color(54, 33, 89));
        print_labelD.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        print_labelD.setText("PRINT");
        print_buttonC.add(print_labelD);
        print_labelD.setBounds(50, 0, 80, 50);

        bg.add(print_buttonC, new org.netbeans.lib.awtextra.AbsoluteConstraints(870, 120, 180, 50));

        refresh_buttonC.setForeground(new java.awt.Color(240, 240, 240));
        refresh_buttonC.setMaximumSize(new java.awt.Dimension(200, 200));
        refresh_buttonC.setMinimumSize(new java.awt.Dimension(200, 200));
        refresh_buttonC.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                refresh_buttonCMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                refresh_buttonCMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                refresh_buttonCMouseExited(evt);
            }
        });
        refresh_buttonC.setLayout(null);

        refresh_labelD.setBackground(new java.awt.Color(54, 33, 89));
        refresh_labelD.setFont(new java.awt.Font("Roboto", 0, 14)); // NOI18N
        refresh_labelD.setForeground(new java.awt.Color(54, 33, 89));
        refresh_labelD.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        refresh_labelD.setText("REFRESH");
        refresh_buttonC.add(refresh_labelD);
        refresh_labelD.setBounds(50, 0, 80, 50);

        bg.add(refresh_buttonC, new org.netbeans.lib.awtextra.AbsoluteConstraints(1070, 120, 180, 50));

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

        refresh_label.setBackground(new java.awt.Color(54, 33, 89));
        refresh_label.setFont(new java.awt.Font("Roboto", 0, 14)); // NOI18N
        refresh_label.setForeground(new java.awt.Color(54, 33, 89));
        refresh_label.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        refresh_label.setText("REFRESH");
        refresh_buttonB.add(refresh_label);
        refresh_label.setBounds(50, 0, 80, 50);

        bg.add(refresh_buttonB, new org.netbeans.lib.awtextra.AbsoluteConstraints(1070, 120, 180, 50));

        getContentPane().add(bg, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 1280, 720));

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
        menuB.setBackground(new java.awt.Color(64, 43, 100));
        menuA.setBackground(new java.awt.Color(85, 65, 118));
        menuC.setBackground(new java.awt.Color(64, 43, 100));

        print_buttonA.setVisible(true);
        insert_buttonA.setVisible(true);
        refresh_buttonB.setVisible(false);
        refresh_buttonC.setVisible(false);
        print_buttonC.setVisible(false);

        ScrollPaneA.setVisible(true);
        panelB.setVisible(false);
        panelC.setVisible(false);
    }//GEN-LAST:event_menuAMouseClicked

    private void okayMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_okayMouseClicked
        name.setText("> XXXXXXX");
        rank.setText("> XXXXXXX");
        unit.setText("> XXXXXXX");
        trade.setText("> XXXXXXX");
        company.setText("> XXXXXXX");

        hour_out_combobox.setSelectedItem("HR");
        minute_out_combobox.setSelectedItem("MM");
        hour_in_combobox.setSelectedItem("HR");
        minute_in_combobox.setSelectedItem("MM");
        date_combobox.setSelectedItem("DD");
        month_combobox.setSelectedItem("MO");
        year_combobox.setSelectedItem("YYYY");
        if (!retrieve_Army_No().equals("")) {
            retrieve_time_date();
        }
    }//GEN-LAST:event_okayMouseClicked

    private void print_buttonAMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_print_buttonAMouseClicked
        current_time_in.setVisible(false);
        current_time_out.setVisible(false);
        current_date.setVisible(false);
        title_label.grabFocus();
        PrintRecord();
        current_time_in.setVisible(true);
        current_time_out.setVisible(true);
        current_date.setVisible(true);
    }//GEN-LAST:event_print_buttonAMouseClicked

    private void print_buttonAMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_print_buttonAMouseEntered
        print_buttonA.setBackground(new java.awt.Color(237, 224, 255));
    }//GEN-LAST:event_print_buttonAMouseEntered

    private void print_buttonAMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_print_buttonAMouseExited
        print_buttonA.setBackground(new java.awt.Color(240, 240, 240));
    }//GEN-LAST:event_print_buttonAMouseExited

    private void formWindowOpened(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowOpened
        //for a centered frame
        Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
        this.setLocation(dim.width / 2 - this.getSize().width / 2, dim.height / 2 - this.getSize().height / 2);
        //for a centered frame

        menuB.setBackground(new java.awt.Color(64, 43, 100));
        menuA.setBackground(new java.awt.Color(85, 65, 118));
        menuC.setBackground(new java.awt.Color(64, 43, 100));

        print_buttonA.setVisible(true);
        insert_buttonA.setVisible(true);

        refresh_buttonB.setVisible(false);
        refresh_buttonC.setVisible(false);
        print_buttonC.setVisible(false);

        ScrollPaneA.setVisible(true);
        panelB.setVisible(false);
        panelC.setVisible(false);

    }//GEN-LAST:event_formWindowOpened

    private void hour_out_comboboxActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_hour_out_comboboxActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_hour_out_comboboxActionPerformed

    private void menuBMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_menuBMouseClicked
        menuB.setBackground(new java.awt.Color(85, 65, 118));
        menuA.setBackground(new java.awt.Color(64, 43, 100));
        menuC.setBackground(new java.awt.Color(64, 43, 100));

        print_buttonA.setVisible(false);
        insert_buttonA.setVisible(false);
        refresh_buttonB.setVisible(true);
        refresh_buttonC.setVisible(false);
        print_buttonC.setVisible(false);

        ScrollPaneA.setVisible(false);
        panelB.setVisible(true);
        panelC.setVisible(false);
        currently_out();

    }//GEN-LAST:event_menuBMouseClicked

    private void refresh_buttonBMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_refresh_buttonBMouseClicked

        int row;
        String time_in;
        PreparedStatement pstmt;
        try {
            for (row = 0; row < Table.getRowCount(); row++) {
                if (Table.getValueAt(row, 5) != null) {
                    time_in = (String) Table.getValueAt(row, 5);
                    if (validate_time(time_in, (String) Table.getValueAt(row, 4))) {
                        pstmt = con.prepareStatement("update outpass set Time_in=? where service_no=? and time_in is null");
                        pstmt.setString(1, time_in);
                        pstmt.setString(2, (String) Table.getValueAt(row, 1));
                        pstmt.executeUpdate();
                        JOptionPane.showMessageDialog(null, "Updation Successful!");
                        currently_out();
                    }
                }
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);

        }
    }//GEN-LAST:event_refresh_buttonBMouseClicked

    private void insert_buttonAMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_insert_buttonAMouseClicked
        // TODO add your handling code here:
        String outpass_name = retrieve_Army_No();
        if (!outpass_name.equals("")) {
            String valid_date = validate_date();
            String hour_out = (String) hour_out_combobox.getSelectedItem();
            String minute_out = (String) minute_out_combobox.getSelectedItem();
            String hour_in = (String) hour_in_combobox.getSelectedItem();
            String minute_in = (String) minute_in_combobox.getSelectedItem();
            PreparedStatement pstmt;
            String Query, time_in;
            try {
                if ("HR".equals(hour_out) || "MM".equals(minute_out)) {
                    JOptionPane.showMessageDialog(null, "Enter Valid Out Time", "Error", JOptionPane.ERROR_MESSAGE);
                } else {
                    String time_out = hour_out + ":" + minute_out;
                    if (retrieve_time_date()) {
                        time_in = hour_in + ":" + minute_in;
                        if (validate_time(time_in, time_out)) {
                            pstmt = con.prepareStatement("update outpass set Time_out=?, Time_in=?, date=? where service_no=?");
                            pstmt.setString(1, time_out);
                            pstmt.setString(2, time_in);
                            pstmt.setString(3, valid_date);
                            pstmt.setString(4, service_number_textfield.getText());
                            pstmt.executeUpdate();
                            JOptionPane.showMessageDialog(null, "Updation Successful!", "Successful", JOptionPane.WARNING_MESSAGE);
                        }
                    } else {
                        Query = "";
                        if ("HR".equals(hour_in) || "MM".equals(minute_in)) {
                            Query = "insert into outpass(Service_No, name, Time_out, Date) values(\"" + service_number_textfield.getText() + "\", \"" + outpass_name + "\", \"" + time_out + "\", \"" + valid_date + "\");";
                        } else {
                            time_in = hour_in + ":" + minute_in;
                            if (validate_time(time_in, time_out)) {
                                Query = "insert into outpass(Service_No, name, Time_out, Time_in, Date) values(\"" + service_number_textfield.getText() + "\", \"" + outpass_name + "\", \"" + time_out + "\", \"" + time_in + "\", \"" + valid_date + "\");";
                            }
                        }
                        Statement stmt = con.createStatement();
                        stmt.executeUpdate(Query);
                        JOptionPane.showMessageDialog(null, "Entry Successful!", "Successful", JOptionPane.WARNING_MESSAGE);
                    }
                    name.setText("> XXXXXXX");
                    rank.setText("> XXXXXXX");
                    unit.setText("> XXXXXXX");
                    trade.setText("> XXXXXXX");
                    company.setText("> XXXXXXX");
                    service_number_textfield.setText("");
                    hour_out_combobox.setSelectedItem("HR");
                    minute_out_combobox.setSelectedItem("MM");
                    hour_in_combobox.setSelectedItem("HR");
                    minute_in_combobox.setSelectedItem("MM");
                    date_combobox.setSelectedItem("DD");
                    month_combobox.setSelectedItem("MO");
                    year_combobox.setSelectedItem("YYYY");
                }
            } catch (Exception e) {
                JOptionPane.showMessageDialog(null, e);

            }
        }
    }//GEN-LAST:event_insert_buttonAMouseClicked

    private void insert_buttonAMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_insert_buttonAMouseEntered
        insert_buttonA.setBackground(new java.awt.Color(237, 224, 255));
    }//GEN-LAST:event_insert_buttonAMouseEntered

    private void insert_buttonAMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_insert_buttonAMouseExited
        insert_buttonA.setBackground(new java.awt.Color(240, 240, 240));
    }//GEN-LAST:event_insert_buttonAMouseExited

    private void menuCMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_menuCMouseClicked
        menuC.setBackground(new java.awt.Color(85, 65, 118));
        menuA.setBackground(new java.awt.Color(64, 43, 100));
        menuB.setBackground(new java.awt.Color(64, 43, 100));

        print_buttonA.setVisible(false);
        insert_buttonA.setVisible(false);

        refresh_buttonB.setVisible(false);
        refresh_buttonC.setVisible(true);
        print_buttonC.setVisible(true);

        ScrollPaneA.setVisible(false);
        panelB.setVisible(false);
        panelC.setVisible(true);
        viewall();
    }//GEN-LAST:event_menuCMouseClicked

    private void okayCMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_okayCMouseClicked
        if (service_numberC.getText().trim().equals("")) {
            viewall();
        } else {
            viewByArmyNo();
        }

    }//GEN-LAST:event_okayCMouseClicked

    private void print_buttonCMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_print_buttonCMouseClicked
        try {

            Statement statement = con.createStatement();
            ResultSet resultSet = statement.executeQuery("select * from outpass");
            XSSFWorkbook workbook = new XSSFWorkbook();
            XSSFSheet spreadsheet = null;
            spreadsheet = workbook.createSheet("Outpass");

            XSSFRow row = spreadsheet.createRow(0);
            XSSFCell cell;
            cell = row.createCell(0);
            cell.setCellValue("Service No");
            cell = row.createCell(1);
            cell.setCellValue("Name");
            cell = row.createCell(2);
            cell.setCellValue("Time IN");
            cell = row.createCell(3);
            cell.setCellValue("Time OUT");
            cell = row.createCell(4);
            cell.setCellValue("Date");

            int i = 1;

            while (resultSet.next()) {
                row = spreadsheet.createRow(i);
                cell = row.createCell(0);
                cell.setCellValue(resultSet.getString("Service_no"));
                cell = row.createCell(1);
                cell.setCellValue(resultSet.getString("name"));
                cell = row.createCell(2);
                cell.setCellValue(resultSet.getString("time_in"));
                cell = row.createCell(3);
                cell.setCellValue(resultSet.getString("time_out"));
                cell = row.createCell(4);
                cell.setCellValue(resultSet.getString("date"));

                i++;
            }
            path_file paths = new path_file();
            FileOutputStream out = new FileOutputStream(new File(paths.csv + "\\Outpass.xlsx"));
            workbook.write(out);
            JOptionPane.showMessageDialog(null, "outpass.xlsx written successfully!", "Successful", JOptionPane.WARNING_MESSAGE);
            Desktop.getDesktop().open(new File(paths.csv + "/Outpass.xlsx"));

        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, ex);
        }
    }//GEN-LAST:event_print_buttonCMouseClicked

    private void print_buttonCMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_print_buttonCMouseEntered
        print_buttonC.setBackground(new java.awt.Color(237, 224, 255));
    }//GEN-LAST:event_print_buttonCMouseEntered

    private void print_buttonCMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_print_buttonCMouseExited
        print_buttonC.setBackground(new java.awt.Color(240, 240, 240));
    }//GEN-LAST:event_print_buttonCMouseExited

    private void refresh_buttonCMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_refresh_buttonCMouseClicked

        viewall();
    }//GEN-LAST:event_refresh_buttonCMouseClicked

    private void refresh_buttonCMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_refresh_buttonCMouseEntered
        refresh_buttonC.setBackground(new java.awt.Color(237, 224, 255));
    }//GEN-LAST:event_refresh_buttonCMouseEntered

    private void refresh_buttonCMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_refresh_buttonCMouseExited
        refresh_buttonC.setBackground(new java.awt.Color(240, 240, 240));
    }//GEN-LAST:event_refresh_buttonCMouseExited

    private void refresh_buttonBMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_refresh_buttonBMouseEntered
        refresh_buttonB.setBackground(new java.awt.Color(237, 224, 255));
    }//GEN-LAST:event_refresh_buttonBMouseEntered

    private void refresh_buttonBMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_refresh_buttonBMouseExited
        refresh_buttonB.setBackground(new java.awt.Color(240, 240, 240));
    }//GEN-LAST:event_refresh_buttonBMouseExited

    private void current_time_inMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_current_time_inMouseEntered
        current_time_in.setForeground(new java.awt.Color(60, 63, 65));
        current_time_in.setBackground(new java.awt.Color(237, 224, 255));
    }//GEN-LAST:event_current_time_inMouseEntered

    private void current_time_inMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_current_time_inMouseExited
        current_time_in.setForeground(new java.awt.Color(60, 63, 65));
        current_time_in.setBackground(new java.awt.Color(255, 255, 255));
    }//GEN-LAST:event_current_time_inMouseExited

    private void current_time_outMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_current_time_outMouseEntered
        current_time_out.setForeground(new java.awt.Color(54, 33, 89));
        current_time_out.setBackground(new java.awt.Color(237, 224, 255));
    }//GEN-LAST:event_current_time_outMouseEntered

    private void current_time_outMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_current_time_outMouseExited
        current_time_out.setForeground(new java.awt.Color(60, 63, 65));
        current_time_out.setBackground(new java.awt.Color(255, 255, 255));
    }//GEN-LAST:event_current_time_outMouseExited

    private void current_dateMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_current_dateMouseEntered
        current_date.setForeground(new java.awt.Color(54, 33, 89));
        current_date.setBackground(new java.awt.Color(237, 224, 255));

    }//GEN-LAST:event_current_dateMouseEntered

    private void current_dateMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_current_dateMouseExited
        current_date.setForeground(new java.awt.Color(60, 63, 65));
        current_date.setBackground(new java.awt.Color(255, 255, 255));
    }//GEN-LAST:event_current_dateMouseExited

    private void move_panelMouseDragged(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_move_panelMouseDragged
        this.setLocation(this.getX() + evt.getX() - mouseX, this.getY() + evt.getY() - mouseY);
    }//GEN-LAST:event_move_panelMouseDragged

    private void move_panelMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_move_panelMousePressed
        mouseX = evt.getX();
        mouseY = evt.getY();
    }//GEN-LAST:event_move_panelMousePressed

    private void current_time_inMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_current_time_inMouseClicked
        // TODO add your handling code here:
        current_time_date("time_in");
    }//GEN-LAST:event_current_time_inMouseClicked

    private void current_time_outMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_current_time_outMouseClicked
        // TODO add your handling code here:
        current_time_date("time_out");

    }//GEN-LAST:event_current_time_outMouseClicked

    private void current_dateMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_current_dateMouseClicked
        // TODO add your handling code here:
        current_time_date("date");

    }//GEN-LAST:event_current_dateMouseClicked

    private void service_number_textfieldKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_service_number_textfieldKeyPressed
        if (evt.getKeyCode() == java.awt.event.KeyEvent.VK_ENTER) {
            okayMouseClicked(null);
        }
    }//GEN-LAST:event_service_number_textfieldKeyPressed

    private void TableKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TableKeyPressed
        if (evt.getKeyCode() == java.awt.event.KeyEvent.VK_ENTER) {
            refresh_buttonBMouseClicked(null);
        }
    }//GEN-LAST:event_TableKeyPressed

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
            java.util.logging.Logger.getLogger(Out_Pass.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Out_Pass.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Out_Pass.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Out_Pass.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Out_Pass().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JScrollPane ScrollPaneA;
    private javax.swing.JScrollPane ScrollPaneB;
    private javax.swing.JScrollPane ScrollPaneC;
    private javax.swing.JSeparator Separator;
    private javax.swing.JTable Table;
    private javax.swing.JTable TableC;
    private javax.swing.JPanel bg;
    private javax.swing.JLabel company;
    private javax.swing.JLabel company_label;
    private javax.swing.JLabel current_date;
    private javax.swing.JLabel current_time_in;
    private javax.swing.JLabel current_time_out;
    private javax.swing.JComboBox<String> date_combobox;
    private javax.swing.JLabel date_label;
    private javax.swing.JLabel exit_icon;
    private javax.swing.JLabel go_back_label;
    private javax.swing.JComboBox<String> hour_in_combobox;
    private javax.swing.JComboBox<String> hour_out_combobox;
    private javax.swing.JPanel insert_buttonA;
    private javax.swing.JLabel insert_label;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JPanel menuA;
    private javax.swing.JLabel menuA_icon;
    private javax.swing.JLabel menuA_label;
    private javax.swing.JPanel menuB;
    private javax.swing.JLabel menuB_icon;
    private javax.swing.JLabel menuB_label;
    private javax.swing.JPanel menuC;
    private javax.swing.JLabel menuC_icon;
    private javax.swing.JLabel menuC_label;
    private javax.swing.JLabel minimize;
    private javax.swing.JComboBox<String> minute_in_combobox;
    private javax.swing.JComboBox<String> minute_out_combobox;
    private javax.swing.JComboBox<String> month_combobox;
    private javax.swing.JPanel move_panel;
    private javax.swing.JLabel name;
    private javax.swing.JLabel name_label;
    private javax.swing.JLabel okay;
    private javax.swing.JLabel okayC;
    private javax.swing.JPanel panelA;
    private javax.swing.JPanel panelB;
    private javax.swing.JPanel panelC;
    private javax.swing.JPanel print_buttonA;
    private javax.swing.JPanel print_buttonC;
    private javax.swing.JLabel print_label;
    private javax.swing.JLabel print_labelD;
    private javax.swing.JLabel rank;
    private javax.swing.JLabel rank_label;
    private javax.swing.JPanel refresh_buttonB;
    private javax.swing.JPanel refresh_buttonC;
    private javax.swing.JLabel refresh_label;
    private javax.swing.JLabel refresh_labelD;
    private javax.swing.JTextField service_numberC;
    private javax.swing.JLabel service_number_label;
    private javax.swing.JLabel service_number_labelC;
    private javax.swing.JTextField service_number_textfield;
    private javax.swing.JSeparator service_number_underline;
    private javax.swing.JPanel side_pane;
    private javax.swing.JSeparator textfield_underline;
    private javax.swing.JLabel time_in_label;
    private javax.swing.JLabel time_out_label;
    private javax.swing.JLabel title_icon;
    private javax.swing.JLabel title_label;
    private javax.swing.JLabel trade;
    private javax.swing.JLabel trade_label;
    private javax.swing.JLabel unit;
    private javax.swing.JLabel unit_label;
    private javax.swing.JComboBox<String> year_combobox;
    // End of variables declaration//GEN-END:variables
}
