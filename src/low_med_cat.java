
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
import java.sql.Statement;
import java.sql.ResultSet;
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
public class low_med_cat extends javax.swing.JFrame {

    private boolean go_back;
    /**
     * Creates new form low_med_cat
     */
    private int mouseX, mouseY;
    Database db = new Database();
    private Connection con = db.create_connection(true);

    public low_med_cat() {
        initComponents();
        
        
        ScrollPaneA.getVerticalScrollBar().setUnitIncrement(16);
        
        
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

    private boolean validate_page() {

        boolean flag = true;
        String med_cat = med_category_textfieldA.getText().trim();
        String diag = CIV_EDN_textfieldA.getText().trim();
        String bc_pc = ACE_textfieldA.getText().trim();
        String DOI_day = (String) DOI_date_comboboxA.getSelectedItem();
        String DOI_month = (String) DOI_month_comboboxA.getSelectedItem();
        String DOI_year = (String) DOI_year_comboboxA.getSelectedItem();
        String DOL_day = (String) DOL_date_comboboxA.getSelectedItem();
        String DOL_month = (String) DOL_month_comboboxA.getSelectedItem();
        String DOL_year = (String) DOL_year_comboboxA.getSelectedItem();
        String Due_day = (String) due_date_comboboxA.getSelectedItem();
        String Due_month = (String) due_month_comboboxA.getSelectedItem();
        String Due_year = (String) due_year_comboboxA.getSelectedItem();
        String emp_restn = emp_restn_textfield.getText().trim();
        String query = "";
        if (!med_cat.equals("")) {
            if (diag.equals("")) {
                query = query + "First Diag cannot be empty!\n";
                flag = false;
            }
            if (bc_pc.equals("")) {
                query = query + "First BC/PC cannot be empty!\n";
                flag = false;
            }
            if (emp_restn.equals("")) {
                query = query + "First Emp Restn cannot be empty!\n";
                flag = false;
            }
            if (validate_date(DOI_day, DOI_month, DOI_year) == false) {
                query = query + "Enter valid Date of Initial Med Bd for first MED CAT!\n";
                flag = false;
            }
            if (validate_date(DOL_day, DOL_month, DOL_year) == false) {
                query = query + "Enter valid Date of Last Med Bd for first MED CAT!\n";
                flag = false;
            }
            if (validate_date(Due_day, Due_month, Due_year) == false) {
                query = query + "Enter valid Next Due Date for first MED CAT!\n";
                flag = false;
            }
            if (flag == false) {
                JOptionPane.showMessageDialog(null, query, "Error", JOptionPane.ERROR_MESSAGE);
            }
        } else {
            JOptionPane.showMessageDialog(null, "First Medical category cannot be empty!", "Error", JOptionPane.ERROR_MESSAGE);
            flag = false;
        }

        if (flag) {

            med_cat = med_category_textfieldA1.getText().trim();

            if (!med_cat.equals("")) {
                diag = CIV_EDN_textfieldA1.getText().trim();
                bc_pc = ACE_textfieldA1.getText().trim();
                DOI_day = (String) DOI_date_comboboxA1.getSelectedItem();
                DOI_month = (String) DOI_month_comboboxA1.getSelectedItem();
                DOI_year = (String) DOI_year_comboboxA1.getSelectedItem();
                DOL_day = (String) DOL_date_comboboxA1.getSelectedItem();
                DOL_month = (String) DOL_month_comboboxA1.getSelectedItem();
                DOL_year = (String) DOL_year_comboboxA1.getSelectedItem();
                Due_day = (String) due_date_comboboxA1.getSelectedItem();
                Due_month = (String) due_month_comboboxA1.getSelectedItem();
                Due_year = (String) due_year_comboboxA1.getSelectedItem();
                emp_restn = emp_restn_textfield1.getText().trim();
                if (diag.equals("")) {
                    query = query + "Second Diag cannot be empty!\n";
                    flag = false;
                }
                if (bc_pc.equals("")) {
                    query = query + "Second BC/PC cannot be empty!\n";
                    flag = false;
                }
                if (emp_restn.equals("")) {
                    query = query + "Second Emp Restn cannot be empty!\n";
                    flag = false;
                }
                if (validate_date(DOI_day, DOI_month, DOI_year) == false) {
                    query = query + "Enter valid Date of Initial Med Bd for Second MED CAT!\n";
                    flag = false;
                }
                if (validate_date(DOL_day, DOL_month, DOL_year) == false) {
                    query = query + "Enter valid Date of Last Med Bd for Second MED CAT!\n";
                    flag = false;
                }
                if (validate_date(Due_day, Due_month, Due_year) == false) {
                    query = query + "Enter valid Next Due Date for Second MED CAT!\n";
                    flag = false;
                }
                if (flag == false) {
                    JOptionPane.showMessageDialog(null, query, "Error", JOptionPane.ERROR_MESSAGE);
                }

                if (flag) {

                    med_cat = med_category_textfieldA2.getText().trim();
                    if (!med_cat.equals("")) {
                        diag = CIV_EDN_textfieldA2.getText().trim();
                        bc_pc = ACE_textfieldA2.getText().trim();
                        DOI_day = (String) DOI_date_comboboxA2.getSelectedItem();
                        DOI_month = (String) DOI_month_comboboxA2.getSelectedItem();
                        DOI_year = (String) DOI_year_comboboxA2.getSelectedItem();
                        DOL_day = (String) DOL_date_comboboxA2.getSelectedItem();
                        DOL_month = (String) DOL_month_comboboxA2.getSelectedItem();
                        DOL_year = (String) DOL_year_comboboxA2.getSelectedItem();
                        Due_day = (String) due_date_comboboxA2.getSelectedItem();
                        Due_month = (String) due_month_comboboxA2.getSelectedItem();
                        Due_year = (String) due_year_comboboxA2.getSelectedItem();
                        emp_restn = emp_restn_textfield2.getText().trim();
                        if (diag.equals("")) {
                            query = query + "Thrid Diag cannot be empty!\n";
                            flag = false;
                        }
                        if (bc_pc.equals("")) {
                            query = query + "Third BC/PC cannot be empty!\n";
                            flag = false;
                        }
                        if (emp_restn.equals("")) {
                            query = query + "Third Emp Restn cannot be empty!\n";
                            flag = false;
                        }
                        if (validate_date(DOI_day, DOI_month, DOI_year) == false) {
                            query = query + "Enter valid Date of Initial Med Bd for Third MED CAT!\n";
                            flag = false;
                        }
                        if (validate_date(DOL_day, DOL_month, DOL_year) == false) {
                            query = query + "Enter valid Date of Last Med Bd for Third MED CAT!\n";
                            flag = false;
                        }
                        if (validate_date(Due_day, Due_month, Due_year) == false) {
                            query = query + "Enter valid Next Due Date for Third MED CAT!\n";
                            flag = false;
                        }
                        if (flag == false) {
                            JOptionPane.showMessageDialog(null, query, "Error", JOptionPane.ERROR_MESSAGE);
                        }
                    }
                }
            }
        }

        return flag;

    }

    private boolean validate_date(String day_, String month_, String year_) {

        boolean flag = true;

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

        }
        return flag;
    }

    private void enableFields(boolean value, boolean value1) {

        med_category_labelA1.setEnabled(value);
        DIAG_labelA1.setEnabled(value);
        underline9.setEnabled(value);
        BCPC_labelA1.setEnabled(value);
        underline5.setEnabled(value);
        underline4.setEnabled(value);
        DOL_labelA1.setEnabled(value);
        DOL_date_comboboxA1.setEnabled(value);
        DOL_month_comboboxA1.setEnabled(value);
        DOL_year_comboboxA1.setEnabled(value);
        DOI_labelA1.setEnabled(value);
        DOI_date_comboboxA1.setEnabled(value);
        DOI_month_comboboxA1.setEnabled(value);
        DOI_year_comboboxA1.setEnabled(value);
        due_date_labelA1.setEnabled(value);
        due_date_comboboxA1.setEnabled(value);
        due_month_comboboxA1.setEnabled(value);
        due_year_comboboxA1.setEnabled(value);
        emp_restn_labelA1.setEnabled(value);
        underline8.setEnabled(value);
        midA3.setEnabled(value);
        med_category_textfieldA1.setEnabled(value);
        CIV_EDN_textfieldA1.setEnabled(value);
        ACE_textfieldA1.setEnabled(value);
        emp_restn_textfield1.setEnabled(value);

        med_category_labelA2.setEnabled(value1);
        DIAG_labelA2.setEnabled(value1);
        underline13.setEnabled(value1);
        BCPC_labelA2.setEnabled(value1);
        underline10.setEnabled(value1);
        underline11.setEnabled(value1);
        DOL_labelA2.setEnabled(value1);
        DOL_date_comboboxA2.setEnabled(value1);
        DOL_month_comboboxA2.setEnabled(value1);
        DOL_year_comboboxA2.setEnabled(value1);
        DOI_labelA2.setEnabled(value1);
        DOI_date_comboboxA2.setEnabled(value1);
        DOI_month_comboboxA2.setEnabled(value1);
        DOI_year_comboboxA2.setEnabled(value1);
        due_date_labelA2.setEnabled(value1);
        due_date_comboboxA2.setEnabled(value1);
        due_month_comboboxA2.setEnabled(value1);
        due_year_comboboxA2.setEnabled(value1);
        emp_restn_labelA2.setEnabled(value1);
        underline12.setEnabled(value1);
        panelA_bottom_line.setEnabled(value1);
        med_category_textfieldA2.setEnabled(value1);
        CIV_EDN_textfieldA2.setEnabled(value1);
        ACE_textfieldA2.setEnabled(value1);
        emp_restn_textfield2.setEnabled(value1);

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

    private void visibleFields(boolean value, boolean value1) {
        midA2.setVisible(value);
        med_category_labelA1.setVisible(value);
        DIAG_labelA1.setVisible(value);
        underline9.setVisible(value);
        BCPC_labelA1.setVisible(value);
        underline5.setVisible(value);
        underline4.setVisible(value);
        DOL_labelA1.setVisible(value);
        DOL_date_comboboxA1.setVisible(value);
        DOL_month_comboboxA1.setVisible(value);
        DOL_year_comboboxA1.setVisible(value);
        DOI_labelA1.setVisible(value);
        DOI_date_comboboxA1.setVisible(value);
        DOI_month_comboboxA1.setVisible(value);
        DOI_year_comboboxA1.setVisible(value);
        due_date_labelA1.setVisible(value);
        due_date_comboboxA1.setVisible(value);
        due_month_comboboxA1.setVisible(value);
        due_year_comboboxA1.setVisible(value);
        emp_restn_labelA1.setVisible(value);
        underline8.setVisible(value);
        med_category_textfieldA1.setVisible(value);
        CIV_EDN_textfieldA1.setVisible(value);
        ACE_textfieldA1.setVisible(value);
        emp_restn_textfield1.setVisible(value);

        midA3.setVisible(value1);
        med_category_labelA2.setVisible(value1);
        DIAG_labelA2.setVisible(value1);
        underline13.setVisible(value1);
        BCPC_labelA2.setVisible(value1);
        underline10.setVisible(value1);
        underline11.setVisible(value1);
        DOL_labelA2.setVisible(value1);
        DOL_date_comboboxA2.setVisible(value1);
        DOL_month_comboboxA2.setVisible(value1);
        DOL_year_comboboxA2.setVisible(value1);
        DOI_labelA2.setVisible(value1);
        DOI_date_comboboxA2.setVisible(value1);
        DOI_month_comboboxA2.setVisible(value1);
        DOI_year_comboboxA2.setVisible(value1);
        due_date_labelA2.setVisible(value1);
        due_date_comboboxA2.setVisible(value1);
        due_month_comboboxA2.setVisible(value1);
        due_year_comboboxA2.setVisible(value1);
        emp_restn_labelA2.setVisible(value1);
        underline12.setVisible(value1);
        panelA_bottom_line.setVisible(value1);
        med_category_textfieldA2.setVisible(value1);
        CIV_EDN_textfieldA2.setVisible(value1);
        ACE_textfieldA2.setVisible(value1);
        emp_restn_textfield2.setVisible(value1);

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
        exit_icon1 = new javax.swing.JLabel();
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
        panelA_bottom_line = new javax.swing.JSeparator();
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
        DIAG_labelA = new javax.swing.JLabel();
        BCPC_labelA = new javax.swing.JLabel();
        CIV_EDN_textfieldA = new javax.swing.JTextField();
        underline2 = new javax.swing.JSeparator();
        ACE_textfieldA = new javax.swing.JTextField();
        underline3 = new javax.swing.JSeparator();
        midA1 = new javax.swing.JSeparator();
        emp_restn_labelA = new javax.swing.JLabel();
        DOL_labelA = new javax.swing.JLabel();
        emp_restn_textfield = new javax.swing.JTextField();
        med_category_labelA = new javax.swing.JLabel();
        med_category_textfieldA = new javax.swing.JTextField();
        underline6 = new javax.swing.JSeparator();
        underline7 = new javax.swing.JSeparator();
        due_year_comboboxA = new javax.swing.JComboBox<>();
        due_date_comboboxA = new javax.swing.JComboBox<>();
        due_month_comboboxA = new javax.swing.JComboBox<>();
        DOI_labelA = new javax.swing.JLabel();
        DOI_date_comboboxA = new javax.swing.JComboBox<>();
        DOI_month_comboboxA = new javax.swing.JComboBox<>();
        DOI_year_comboboxA = new javax.swing.JComboBox<>();
        due_date_labelA = new javax.swing.JLabel();
        DOL_year_comboboxA = new javax.swing.JComboBox<>();
        DOL_date_comboboxA = new javax.swing.JComboBox<>();
        DOL_month_comboboxA = new javax.swing.JComboBox<>();
        DIAG_labelA1 = new javax.swing.JLabel();
        BCPC_labelA1 = new javax.swing.JLabel();
        CIV_EDN_textfieldA1 = new javax.swing.JTextField();
        underline4 = new javax.swing.JSeparator();
        ACE_textfieldA1 = new javax.swing.JTextField();
        underline5 = new javax.swing.JSeparator();
        midA2 = new javax.swing.JSeparator();
        emp_restn_labelA1 = new javax.swing.JLabel();
        DOL_labelA1 = new javax.swing.JLabel();
        emp_restn_textfield1 = new javax.swing.JTextField();
        med_category_labelA1 = new javax.swing.JLabel();
        med_category_textfieldA1 = new javax.swing.JTextField();
        underline8 = new javax.swing.JSeparator();
        underline9 = new javax.swing.JSeparator();
        due_year_comboboxA1 = new javax.swing.JComboBox<>();
        due_date_comboboxA1 = new javax.swing.JComboBox<>();
        due_month_comboboxA1 = new javax.swing.JComboBox<>();
        DOI_labelA1 = new javax.swing.JLabel();
        DOI_date_comboboxA1 = new javax.swing.JComboBox<>();
        DOI_month_comboboxA1 = new javax.swing.JComboBox<>();
        DOI_year_comboboxA1 = new javax.swing.JComboBox<>();
        due_date_labelA1 = new javax.swing.JLabel();
        DOL_year_comboboxA1 = new javax.swing.JComboBox<>();
        DOL_date_comboboxA1 = new javax.swing.JComboBox<>();
        DOL_month_comboboxA1 = new javax.swing.JComboBox<>();
        DIAG_labelA2 = new javax.swing.JLabel();
        BCPC_labelA2 = new javax.swing.JLabel();
        CIV_EDN_textfieldA2 = new javax.swing.JTextField();
        underline10 = new javax.swing.JSeparator();
        ACE_textfieldA2 = new javax.swing.JTextField();
        underline11 = new javax.swing.JSeparator();
        midA3 = new javax.swing.JSeparator();
        emp_restn_labelA2 = new javax.swing.JLabel();
        DOL_labelA2 = new javax.swing.JLabel();
        emp_restn_textfield2 = new javax.swing.JTextField();
        med_category_labelA2 = new javax.swing.JLabel();
        med_category_textfieldA2 = new javax.swing.JTextField();
        underline12 = new javax.swing.JSeparator();
        underline13 = new javax.swing.JSeparator();
        due_year_comboboxA2 = new javax.swing.JComboBox<>();
        due_date_comboboxA2 = new javax.swing.JComboBox<>();
        due_month_comboboxA2 = new javax.swing.JComboBox<>();
        DOI_labelA2 = new javax.swing.JLabel();
        DOI_date_comboboxA2 = new javax.swing.JComboBox<>();
        DOI_month_comboboxA2 = new javax.swing.JComboBox<>();
        DOI_year_comboboxA2 = new javax.swing.JComboBox<>();
        due_date_labelA2 = new javax.swing.JLabel();
        DOL_year_comboboxA2 = new javax.swing.JComboBox<>();
        DOL_date_comboboxA2 = new javax.swing.JComboBox<>();
        DOL_month_comboboxA2 = new javax.swing.JComboBox<>();
        print_button = new javax.swing.JPanel();
        print_label = new javax.swing.JLabel();

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
        bg.setPreferredSize(new java.awt.Dimension(93, 93));
        bg.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        exit_icon1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/PNGS/remove_purple.png"))); // NOI18N
        exit_icon1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                exit_icon1MouseClicked(evt);
            }
        });
        bg.add(exit_icon1, new org.netbeans.lib.awtextra.AbsoluteConstraints(1215, 30, -1, -1));

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
        title_label.setText("Low Med Cat");
        side_pane.add(title_label, new org.netbeans.lib.awtextra.AbsoluteConstraints(90, 135, 200, 40));

        title_icon.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        title_icon.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/PNGS/low_med_cat_white.png"))); // NOI18N
        side_pane.add(title_icon, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 120, 70, 70));

        bg.add(side_pane, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 320, 740));

        ScrollPaneA.setBorder(null);
        ScrollPaneA.setHorizontalScrollBarPolicy(javax.swing.ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);

        panelA.setBackground(new java.awt.Color(255, 255, 255));
        panelA.setFocusable(false);
        panelA.setMaximumSize(new java.awt.Dimension(950, 1310));
        panelA.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        panelA_bottom_line.setForeground(new java.awt.Color(54, 33, 89));
        panelA.add(panelA_bottom_line, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 1532, 910, -1));

        service_number_underline.setForeground(new java.awt.Color(54, 33, 89));
        panelA.add(service_number_underline, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 130, 400, 20));

        service_number_textfield.setFont(new java.awt.Font("Roboto", 0, 24)); // NOI18N
        service_number_textfield.setForeground(new java.awt.Color(54, 33, 89));
        service_number_textfield.setBorder(null);
        service_number_textfield.setMargin(new java.awt.Insets(2, 4, 2, 2));
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
        panelA.add(trade_label, new org.netbeans.lib.awtextra.AbsoluteConstraints(620, 170, -1, 40));

        trade.setFont(new java.awt.Font("Roboto Bk", 0, 24)); // NOI18N
        trade.setForeground(new java.awt.Color(54, 33, 89));
        trade.setText("> XXXXXXX");
        panelA.add(trade, new org.netbeans.lib.awtextra.AbsoluteConstraints(620, 210, -1, 30));

        unit_label.setBackground(new java.awt.Color(255, 255, 255));
        unit_label.setFont(new java.awt.Font("Montserrat", 0, 28)); // NOI18N
        unit_label.setForeground(new java.awt.Color(51, 51, 51));
        unit_label.setText("Unit");
        panelA.add(unit_label, new org.netbeans.lib.awtextra.AbsoluteConstraints(350, 260, -1, 40));

        unit.setFont(new java.awt.Font("Roboto Bk", 0, 24)); // NOI18N
        unit.setForeground(new java.awt.Color(54, 33, 89));
        unit.setText("> XXXXXXX");
        panelA.add(unit, new org.netbeans.lib.awtextra.AbsoluteConstraints(350, 300, -1, 30));

        DIAG_labelA.setBackground(new java.awt.Color(255, 255, 255));
        DIAG_labelA.setFont(new java.awt.Font("Montserrat", 0, 24)); // NOI18N
        DIAG_labelA.setForeground(new java.awt.Color(66, 50, 77));
        DIAG_labelA.setText("DIAG");
        panelA.add(DIAG_labelA, new org.netbeans.lib.awtextra.AbsoluteConstraints(350, 420, -1, 40));

        BCPC_labelA.setBackground(new java.awt.Color(255, 255, 255));
        BCPC_labelA.setFont(new java.awt.Font("Montserrat", 0, 24)); // NOI18N
        BCPC_labelA.setForeground(new java.awt.Color(66, 50, 77));
        BCPC_labelA.setText("BC/PC");
        panelA.add(BCPC_labelA, new org.netbeans.lib.awtextra.AbsoluteConstraints(620, 420, -1, 40));

        CIV_EDN_textfieldA.setFont(new java.awt.Font("Roboto", 0, 24)); // NOI18N
        CIV_EDN_textfieldA.setForeground(new java.awt.Color(54, 33, 89));
        CIV_EDN_textfieldA.setBorder(null);
        CIV_EDN_textfieldA.setMargin(new java.awt.Insets(2, 4, 2, 2));
        CIV_EDN_textfieldA.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                CIV_EDN_textfieldAActionPerformed(evt);
            }
        });
        panelA.add(CIV_EDN_textfieldA, new org.netbeans.lib.awtextra.AbsoluteConstraints(350, 460, 210, 40));

        underline2.setForeground(new java.awt.Color(54, 33, 89));
        panelA.add(underline2, new org.netbeans.lib.awtextra.AbsoluteConstraints(350, 500, 210, 20));

        ACE_textfieldA.setFont(new java.awt.Font("Roboto", 0, 24)); // NOI18N
        ACE_textfieldA.setForeground(new java.awt.Color(54, 33, 89));
        ACE_textfieldA.setBorder(null);
        ACE_textfieldA.setMargin(new java.awt.Insets(2, 4, 2, 2));
        ACE_textfieldA.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ACE_textfieldAActionPerformed(evt);
            }
        });
        panelA.add(ACE_textfieldA, new org.netbeans.lib.awtextra.AbsoluteConstraints(620, 460, 210, 40));

        underline3.setForeground(new java.awt.Color(54, 33, 89));
        panelA.add(underline3, new org.netbeans.lib.awtextra.AbsoluteConstraints(620, 500, 210, 20));

        midA1.setForeground(new java.awt.Color(204, 204, 255));
        panelA.add(midA1, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 380, 440, 10));

        emp_restn_labelA.setBackground(new java.awt.Color(255, 255, 255));
        emp_restn_labelA.setFont(new java.awt.Font("Montserrat", 0, 24)); // NOI18N
        emp_restn_labelA.setForeground(new java.awt.Color(66, 50, 77));
        emp_restn_labelA.setText("Emp Restn");
        panelA.add(emp_restn_labelA, new org.netbeans.lib.awtextra.AbsoluteConstraints(500, 620, 260, 40));

        DOL_labelA.setBackground(new java.awt.Color(255, 255, 255));
        DOL_labelA.setFont(new java.awt.Font("Montserrat", 0, 24)); // NOI18N
        DOL_labelA.setForeground(new java.awt.Color(66, 50, 77));
        DOL_labelA.setText("Date of Last Med Bd");
        panelA.add(DOL_labelA, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 520, -1, 40));

        emp_restn_textfield.setFont(new java.awt.Font("Roboto", 0, 24)); // NOI18N
        emp_restn_textfield.setForeground(new java.awt.Color(54, 33, 89));
        emp_restn_textfield.setBorder(null);
        emp_restn_textfield.setMargin(new java.awt.Insets(2, 4, 2, 2));
        emp_restn_textfield.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                emp_restn_textfieldActionPerformed(evt);
            }
        });
        emp_restn_textfield.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                emp_restn_textfieldKeyTyped(evt);
            }
        });
        panelA.add(emp_restn_textfield, new org.netbeans.lib.awtextra.AbsoluteConstraints(500, 660, 330, 40));

        med_category_labelA.setBackground(new java.awt.Color(255, 255, 255));
        med_category_labelA.setFont(new java.awt.Font("Montserrat", 0, 24)); // NOI18N
        med_category_labelA.setForeground(new java.awt.Color(66, 50, 77));
        med_category_labelA.setText("Med Category");
        panelA.add(med_category_labelA, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 420, -1, 40));

        med_category_textfieldA.setFont(new java.awt.Font("Roboto", 0, 24)); // NOI18N
        med_category_textfieldA.setForeground(new java.awt.Color(54, 33, 89));
        med_category_textfieldA.setBorder(null);
        med_category_textfieldA.setMargin(new java.awt.Insets(2, 4, 2, 2));
        med_category_textfieldA.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                med_category_textfieldAActionPerformed(evt);
            }
        });
        panelA.add(med_category_textfieldA, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 460, 210, 40));

        underline6.setForeground(new java.awt.Color(54, 33, 89));
        panelA.add(underline6, new org.netbeans.lib.awtextra.AbsoluteConstraints(500, 700, 330, 20));

        underline7.setForeground(new java.awt.Color(54, 33, 89));
        panelA.add(underline7, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 500, 210, 20));

        due_year_comboboxA.setFont(new java.awt.Font("Roboto", 0, 18)); // NOI18N
        due_year_comboboxA.setForeground(new java.awt.Color(44, 62, 80));
        due_year_comboboxA.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "YYYY", "1950", "1951", "1952", "1953", "1954", "1955", "1956", "1957", "1958", "1959", "1960", "1961", "1962", "1963", "1964", "1965", "1966", "1967", "1968", "1969", "1970", "1971", "1972", "1973", "1974", "1975", "1976", "1977", "1978", "1979", "1980", "1981", "1982", "1983", "1984", "1985", "1986", "1987", "1988", "1989", "1990", "1991", "1992", "1993", "1994", "1995", "1996", "1997", "1998", "1999", "2000", "2001", "2002", "2003", "2004", "2005", "2006", "2007", "2008", "2009", "2010", "2011", "2012", "2013", "2014", "2015", "2016", "2017", "2018", "2019", "2020", "2021", "2022", "2023", "2024", "2025", "2026", "2027", "2028", "2029", "2030", "2031", "2032", "2033", "2034", "2035", "2036", "2037", "2038", "2039", "2040", "2041", "2042", "2043", "2044", "2045", "2046", "2047", "2048", "2049", "2050", "2051", "2052", "2053", "2054", "2055", "2056", "2057", "2058", "2059", "2060", "2061", "2062", "2063", "2064", "2065", "2066", "2067", "2068", "2069", "2070", "2071", "2072", "2073", "2074", "2075", "2076", "2077", "2078", "2079", "2080", "2081", "2082", "2083", "2084", "2085", "2086", "2087", "2088", "2089", "2090", "2091", "2092", "2093", "2094", "2095", "2096", "2097", "2098", "2099", "2100", " " }));
        due_year_comboboxA.setBorder(null);
        panelA.add(due_year_comboboxA, new org.netbeans.lib.awtextra.AbsoluteConstraints(310, 660, 100, 40));

        due_date_comboboxA.setFont(new java.awt.Font("Roboto", 0, 18)); // NOI18N
        due_date_comboboxA.setForeground(new java.awt.Color(44, 62, 80));
        due_date_comboboxA.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "DD", "01", "02", "03", "04", "05", "06", "07", "08", "09", "10", "11", "12", "13", "14", "15", "16", "17", "18", "19", "20", "21", "22", "23", "24", "25", "26", "27", "28", "29", "30", "31" }));
        due_date_comboboxA.setBorder(null);
        panelA.add(due_date_comboboxA, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 660, 100, 40));

        due_month_comboboxA.setFont(new java.awt.Font("Roboto", 0, 18)); // NOI18N
        due_month_comboboxA.setForeground(new java.awt.Color(44, 62, 80));
        due_month_comboboxA.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "MM", "01", "02", "03", "04", "05", "06", "07", "08", "09", "10", "11", "12" }));
        due_month_comboboxA.setBorder(null);
        panelA.add(due_month_comboboxA, new org.netbeans.lib.awtextra.AbsoluteConstraints(195, 660, 100, 40));

        DOI_labelA.setBackground(new java.awt.Color(255, 255, 255));
        DOI_labelA.setFont(new java.awt.Font("Montserrat", 0, 24)); // NOI18N
        DOI_labelA.setForeground(new java.awt.Color(51, 51, 51));
        DOI_labelA.setText("Date of Initial Med Bd");
        panelA.add(DOI_labelA, new org.netbeans.lib.awtextra.AbsoluteConstraints(500, 520, -1, 40));

        DOI_date_comboboxA.setFont(new java.awt.Font("Roboto", 0, 18)); // NOI18N
        DOI_date_comboboxA.setForeground(new java.awt.Color(44, 62, 80));
        DOI_date_comboboxA.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "DD", "01", "02", "03", "04", "05", "06", "07", "08", "09", "10", "11", "12", "13", "14", "15", "16", "17", "18", "19", "20", "21", "22", "23", "24", "25", "26", "27", "28", "29", "30", "31" }));
        DOI_date_comboboxA.setBorder(null);
        DOI_date_comboboxA.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                DOI_date_comboboxAActionPerformed(evt);
            }
        });
        panelA.add(DOI_date_comboboxA, new org.netbeans.lib.awtextra.AbsoluteConstraints(500, 560, 100, 40));

        DOI_month_comboboxA.setFont(new java.awt.Font("Roboto", 0, 18)); // NOI18N
        DOI_month_comboboxA.setForeground(new java.awt.Color(44, 62, 80));
        DOI_month_comboboxA.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "MM", "01", "02", "03", "04", "05", "06", "07", "08", "09", "10", "11", "12" }));
        DOI_month_comboboxA.setBorder(null);
        panelA.add(DOI_month_comboboxA, new org.netbeans.lib.awtextra.AbsoluteConstraints(615, 560, 100, 40));

        DOI_year_comboboxA.setFont(new java.awt.Font("Roboto", 0, 18)); // NOI18N
        DOI_year_comboboxA.setForeground(new java.awt.Color(44, 62, 80));
        DOI_year_comboboxA.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "YYYY", "1950", "1951", "1952", "1953", "1954", "1955", "1956", "1957", "1958", "1959", "1960", "1961", "1962", "1963", "1964", "1965", "1966", "1967", "1968", "1969", "1970", "1971", "1972", "1973", "1974", "1975", "1976", "1977", "1978", "1979", "1980", "1981", "1982", "1983", "1984", "1985", "1986", "1987", "1988", "1989", "1990", "1991", "1992", "1993", "1994", "1995", "1996", "1997", "1998", "1999", "2000", "2001", "2002", "2003", "2004", "2005", "2006", "2007", "2008", "2009", "2010", "2011", "2012", "2013", "2014", "2015", "2016", "2017", "2018", "2019", "2020", "2021", "2022", "2023", "2024", "2025", "2026", "2027", "2028", "2029", "2030", "2031", "2032", "2033", "2034", "2035", "2036", "2037", "2038", "2039", "2040", "2041", "2042", "2043", "2044", "2045", "2046", "2047", "2048", "2049", "2050", "2051", "2052", "2053", "2054", "2055", "2056", "2057", "2058", "2059", "2060", "2061", "2062", "2063", "2064", "2065", "2066", "2067", "2068", "2069", "2070", "2071", "2072", "2073", "2074", "2075", "2076", "2077", "2078", "2079", "2080", "2081", "2082", "2083", "2084", "2085", "2086", "2087", "2088", "2089", "2090", "2091", "2092", "2093", "2094", "2095", "2096", "2097", "2098", "2099", "2100", " " }));
        DOI_year_comboboxA.setBorder(null);
        panelA.add(DOI_year_comboboxA, new org.netbeans.lib.awtextra.AbsoluteConstraints(730, 560, 100, 40));

        due_date_labelA.setBackground(new java.awt.Color(255, 255, 255));
        due_date_labelA.setFont(new java.awt.Font("Montserrat", 0, 24)); // NOI18N
        due_date_labelA.setForeground(new java.awt.Color(66, 50, 77));
        due_date_labelA.setText("Next Due Date");
        panelA.add(due_date_labelA, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 620, -1, 40));

        DOL_year_comboboxA.setFont(new java.awt.Font("Roboto", 0, 18)); // NOI18N
        DOL_year_comboboxA.setForeground(new java.awt.Color(44, 62, 80));
        DOL_year_comboboxA.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "YYYY", "1950", "1951", "1952", "1953", "1954", "1955", "1956", "1957", "1958", "1959", "1960", "1961", "1962", "1963", "1964", "1965", "1966", "1967", "1968", "1969", "1970", "1971", "1972", "1973", "1974", "1975", "1976", "1977", "1978", "1979", "1980", "1981", "1982", "1983", "1984", "1985", "1986", "1987", "1988", "1989", "1990", "1991", "1992", "1993", "1994", "1995", "1996", "1997", "1998", "1999", "2000", "2001", "2002", "2003", "2004", "2005", "2006", "2007", "2008", "2009", "2010", "2011", "2012", "2013", "2014", "2015", "2016", "2017", "2018", "2019", "2020", "2021", "2022", "2023", "2024", "2025", "2026", "2027", "2028", "2029", "2030", "2031", "2032", "2033", "2034", "2035", "2036", "2037", "2038", "2039", "2040", "2041", "2042", "2043", "2044", "2045", "2046", "2047", "2048", "2049", "2050", "2051", "2052", "2053", "2054", "2055", "2056", "2057", "2058", "2059", "2060", "2061", "2062", "2063", "2064", "2065", "2066", "2067", "2068", "2069", "2070", "2071", "2072", "2073", "2074", "2075", "2076", "2077", "2078", "2079", "2080", "2081", "2082", "2083", "2084", "2085", "2086", "2087", "2088", "2089", "2090", "2091", "2092", "2093", "2094", "2095", "2096", "2097", "2098", "2099", "2100", " " }));
        DOL_year_comboboxA.setBorder(null);
        panelA.add(DOL_year_comboboxA, new org.netbeans.lib.awtextra.AbsoluteConstraints(310, 560, 100, 40));

        DOL_date_comboboxA.setFont(new java.awt.Font("Roboto", 0, 18)); // NOI18N
        DOL_date_comboboxA.setForeground(new java.awt.Color(44, 62, 80));
        DOL_date_comboboxA.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "DD", "01", "02", "03", "04", "05", "06", "07", "08", "09", "10", "11", "12", "13", "14", "15", "16", "17", "18", "19", "20", "21", "22", "23", "24", "25", "26", "27", "28", "29", "30", "31" }));
        DOL_date_comboboxA.setBorder(null);
        panelA.add(DOL_date_comboboxA, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 560, 100, 40));

        DOL_month_comboboxA.setFont(new java.awt.Font("Roboto", 0, 18)); // NOI18N
        DOL_month_comboboxA.setForeground(new java.awt.Color(44, 62, 80));
        DOL_month_comboboxA.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "MM", "01", "02", "03", "04", "05", "06", "07", "08", "09", "10", "11", "12" }));
        DOL_month_comboboxA.setBorder(null);
        panelA.add(DOL_month_comboboxA, new org.netbeans.lib.awtextra.AbsoluteConstraints(195, 560, 100, 40));

        DIAG_labelA1.setBackground(new java.awt.Color(255, 255, 255));
        DIAG_labelA1.setFont(new java.awt.Font("Montserrat", 0, 24)); // NOI18N
        DIAG_labelA1.setForeground(new java.awt.Color(66, 50, 77));
        DIAG_labelA1.setText("DIAG");
        panelA.add(DIAG_labelA1, new org.netbeans.lib.awtextra.AbsoluteConstraints(350, 800, -1, 40));

        BCPC_labelA1.setBackground(new java.awt.Color(255, 255, 255));
        BCPC_labelA1.setFont(new java.awt.Font("Montserrat", 0, 24)); // NOI18N
        BCPC_labelA1.setForeground(new java.awt.Color(66, 50, 77));
        BCPC_labelA1.setText("BC/PC");
        panelA.add(BCPC_labelA1, new org.netbeans.lib.awtextra.AbsoluteConstraints(620, 800, -1, 40));

        CIV_EDN_textfieldA1.setFont(new java.awt.Font("Roboto", 0, 24)); // NOI18N
        CIV_EDN_textfieldA1.setForeground(new java.awt.Color(54, 33, 89));
        CIV_EDN_textfieldA1.setBorder(null);
        CIV_EDN_textfieldA1.setMargin(new java.awt.Insets(2, 4, 2, 2));
        CIV_EDN_textfieldA1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                CIV_EDN_textfieldA1ActionPerformed(evt);
            }
        });
        panelA.add(CIV_EDN_textfieldA1, new org.netbeans.lib.awtextra.AbsoluteConstraints(350, 840, 210, 40));

        underline4.setForeground(new java.awt.Color(54, 33, 89));
        panelA.add(underline4, new org.netbeans.lib.awtextra.AbsoluteConstraints(350, 880, 210, 20));

        ACE_textfieldA1.setFont(new java.awt.Font("Roboto", 0, 24)); // NOI18N
        ACE_textfieldA1.setForeground(new java.awt.Color(54, 33, 89));
        ACE_textfieldA1.setBorder(null);
        ACE_textfieldA1.setMargin(new java.awt.Insets(2, 4, 2, 2));
        ACE_textfieldA1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ACE_textfieldA1ActionPerformed(evt);
            }
        });
        panelA.add(ACE_textfieldA1, new org.netbeans.lib.awtextra.AbsoluteConstraints(620, 840, 210, 40));

        underline5.setForeground(new java.awt.Color(54, 33, 89));
        panelA.add(underline5, new org.netbeans.lib.awtextra.AbsoluteConstraints(620, 880, 210, 20));

        midA2.setForeground(new java.awt.Color(204, 204, 255));
        panelA.add(midA2, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 760, 440, 10));

        emp_restn_labelA1.setBackground(new java.awt.Color(255, 255, 255));
        emp_restn_labelA1.setFont(new java.awt.Font("Montserrat", 0, 24)); // NOI18N
        emp_restn_labelA1.setForeground(new java.awt.Color(66, 50, 77));
        emp_restn_labelA1.setText("Emp Restn");
        panelA.add(emp_restn_labelA1, new org.netbeans.lib.awtextra.AbsoluteConstraints(500, 1000, 260, 40));

        DOL_labelA1.setBackground(new java.awt.Color(255, 255, 255));
        DOL_labelA1.setFont(new java.awt.Font("Montserrat", 0, 24)); // NOI18N
        DOL_labelA1.setForeground(new java.awt.Color(66, 50, 77));
        DOL_labelA1.setText("Date of Last Med Bd");
        panelA.add(DOL_labelA1, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 900, -1, 40));

        emp_restn_textfield1.setFont(new java.awt.Font("Roboto", 0, 24)); // NOI18N
        emp_restn_textfield1.setForeground(new java.awt.Color(54, 33, 89));
        emp_restn_textfield1.setBorder(null);
        emp_restn_textfield1.setMargin(new java.awt.Insets(2, 4, 2, 2));
        emp_restn_textfield1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                emp_restn_textfield1ActionPerformed(evt);
            }
        });
        emp_restn_textfield1.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                emp_restn_textfield1KeyTyped(evt);
            }
        });
        panelA.add(emp_restn_textfield1, new org.netbeans.lib.awtextra.AbsoluteConstraints(500, 1040, 330, 40));

        med_category_labelA1.setBackground(new java.awt.Color(255, 255, 255));
        med_category_labelA1.setFont(new java.awt.Font("Montserrat", 0, 24)); // NOI18N
        med_category_labelA1.setForeground(new java.awt.Color(66, 50, 77));
        med_category_labelA1.setText("Med Category");
        panelA.add(med_category_labelA1, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 800, -1, 40));

        med_category_textfieldA1.setFont(new java.awt.Font("Roboto", 0, 24)); // NOI18N
        med_category_textfieldA1.setForeground(new java.awt.Color(54, 33, 89));
        med_category_textfieldA1.setBorder(null);
        med_category_textfieldA1.setMargin(new java.awt.Insets(2, 4, 2, 2));
        med_category_textfieldA1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                med_category_textfieldA1ActionPerformed(evt);
            }
        });
        panelA.add(med_category_textfieldA1, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 840, 210, 40));

        underline8.setForeground(new java.awt.Color(54, 33, 89));
        panelA.add(underline8, new org.netbeans.lib.awtextra.AbsoluteConstraints(500, 1080, 330, 20));

        underline9.setForeground(new java.awt.Color(54, 33, 89));
        panelA.add(underline9, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 880, 210, 20));

        due_year_comboboxA1.setFont(new java.awt.Font("Roboto", 0, 18)); // NOI18N
        due_year_comboboxA1.setForeground(new java.awt.Color(44, 62, 80));
        due_year_comboboxA1.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "YYYY", "1950", "1951", "1952", "1953", "1954", "1955", "1956", "1957", "1958", "1959", "1960", "1961", "1962", "1963", "1964", "1965", "1966", "1967", "1968", "1969", "1970", "1971", "1972", "1973", "1974", "1975", "1976", "1977", "1978", "1979", "1980", "1981", "1982", "1983", "1984", "1985", "1986", "1987", "1988", "1989", "1990", "1991", "1992", "1993", "1994", "1995", "1996", "1997", "1998", "1999", "2000", "2001", "2002", "2003", "2004", "2005", "2006", "2007", "2008", "2009", "2010", "2011", "2012", "2013", "2014", "2015", "2016", "2017", "2018", "2019", "2020", "2021", "2022", "2023", "2024", "2025", "2026", "2027", "2028", "2029", "2030", "2031", "2032", "2033", "2034", "2035", "2036", "2037", "2038", "2039", "2040", "2041", "2042", "2043", "2044", "2045", "2046", "2047", "2048", "2049", "2050", "2051", "2052", "2053", "2054", "2055", "2056", "2057", "2058", "2059", "2060", "2061", "2062", "2063", "2064", "2065", "2066", "2067", "2068", "2069", "2070", "2071", "2072", "2073", "2074", "2075", "2076", "2077", "2078", "2079", "2080", "2081", "2082", "2083", "2084", "2085", "2086", "2087", "2088", "2089", "2090", "2091", "2092", "2093", "2094", "2095", "2096", "2097", "2098", "2099", "2100", " " }));
        due_year_comboboxA1.setBorder(null);
        panelA.add(due_year_comboboxA1, new org.netbeans.lib.awtextra.AbsoluteConstraints(310, 1040, 100, 40));

        due_date_comboboxA1.setFont(new java.awt.Font("Roboto", 0, 18)); // NOI18N
        due_date_comboboxA1.setForeground(new java.awt.Color(44, 62, 80));
        due_date_comboboxA1.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "DD", "01", "02", "03", "04", "05", "06", "07", "08", "09", "10", "11", "12", "13", "14", "15", "16", "17", "18", "19", "20", "21", "22", "23", "24", "25", "26", "27", "28", "29", "30", "31" }));
        due_date_comboboxA1.setBorder(null);
        panelA.add(due_date_comboboxA1, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 1040, 100, 40));

        due_month_comboboxA1.setFont(new java.awt.Font("Roboto", 0, 18)); // NOI18N
        due_month_comboboxA1.setForeground(new java.awt.Color(44, 62, 80));
        due_month_comboboxA1.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "MM", "01", "02", "03", "04", "05", "06", "07", "08", "09", "10", "11", "12" }));
        due_month_comboboxA1.setBorder(null);
        panelA.add(due_month_comboboxA1, new org.netbeans.lib.awtextra.AbsoluteConstraints(195, 1040, 100, 40));

        DOI_labelA1.setBackground(new java.awt.Color(255, 255, 255));
        DOI_labelA1.setFont(new java.awt.Font("Montserrat", 0, 24)); // NOI18N
        DOI_labelA1.setForeground(new java.awt.Color(51, 51, 51));
        DOI_labelA1.setText("Date of Initial Med Bd");
        panelA.add(DOI_labelA1, new org.netbeans.lib.awtextra.AbsoluteConstraints(500, 900, -1, 40));

        DOI_date_comboboxA1.setFont(new java.awt.Font("Roboto", 0, 18)); // NOI18N
        DOI_date_comboboxA1.setForeground(new java.awt.Color(44, 62, 80));
        DOI_date_comboboxA1.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "DD", "01", "02", "03", "04", "05", "06", "07", "08", "09", "10", "11", "12", "13", "14", "15", "16", "17", "18", "19", "20", "21", "22", "23", "24", "25", "26", "27", "28", "29", "30", "31" }));
        DOI_date_comboboxA1.setBorder(null);
        DOI_date_comboboxA1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                DOI_date_comboboxA1ActionPerformed(evt);
            }
        });
        panelA.add(DOI_date_comboboxA1, new org.netbeans.lib.awtextra.AbsoluteConstraints(500, 940, 100, 40));

        DOI_month_comboboxA1.setFont(new java.awt.Font("Roboto", 0, 18)); // NOI18N
        DOI_month_comboboxA1.setForeground(new java.awt.Color(44, 62, 80));
        DOI_month_comboboxA1.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "MM", "01", "02", "03", "04", "05", "06", "07", "08", "09", "10", "11", "12" }));
        DOI_month_comboboxA1.setBorder(null);
        panelA.add(DOI_month_comboboxA1, new org.netbeans.lib.awtextra.AbsoluteConstraints(615, 940, 100, 40));

        DOI_year_comboboxA1.setFont(new java.awt.Font("Roboto", 0, 18)); // NOI18N
        DOI_year_comboboxA1.setForeground(new java.awt.Color(44, 62, 80));
        DOI_year_comboboxA1.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "YYYY", "1950", "1951", "1952", "1953", "1954", "1955", "1956", "1957", "1958", "1959", "1960", "1961", "1962", "1963", "1964", "1965", "1966", "1967", "1968", "1969", "1970", "1971", "1972", "1973", "1974", "1975", "1976", "1977", "1978", "1979", "1980", "1981", "1982", "1983", "1984", "1985", "1986", "1987", "1988", "1989", "1990", "1991", "1992", "1993", "1994", "1995", "1996", "1997", "1998", "1999", "2000", "2001", "2002", "2003", "2004", "2005", "2006", "2007", "2008", "2009", "2010", "2011", "2012", "2013", "2014", "2015", "2016", "2017", "2018", "2019", "2020", "2021", "2022", "2023", "2024", "2025", "2026", "2027", "2028", "2029", "2030", "2031", "2032", "2033", "2034", "2035", "2036", "2037", "2038", "2039", "2040", "2041", "2042", "2043", "2044", "2045", "2046", "2047", "2048", "2049", "2050", "2051", "2052", "2053", "2054", "2055", "2056", "2057", "2058", "2059", "2060", "2061", "2062", "2063", "2064", "2065", "2066", "2067", "2068", "2069", "2070", "2071", "2072", "2073", "2074", "2075", "2076", "2077", "2078", "2079", "2080", "2081", "2082", "2083", "2084", "2085", "2086", "2087", "2088", "2089", "2090", "2091", "2092", "2093", "2094", "2095", "2096", "2097", "2098", "2099", "2100", " " }));
        DOI_year_comboboxA1.setBorder(null);
        panelA.add(DOI_year_comboboxA1, new org.netbeans.lib.awtextra.AbsoluteConstraints(730, 940, 100, 40));

        due_date_labelA1.setBackground(new java.awt.Color(255, 255, 255));
        due_date_labelA1.setFont(new java.awt.Font("Montserrat", 0, 24)); // NOI18N
        due_date_labelA1.setForeground(new java.awt.Color(66, 50, 77));
        due_date_labelA1.setText("Next Due Date");
        panelA.add(due_date_labelA1, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 1000, -1, 40));

        DOL_year_comboboxA1.setFont(new java.awt.Font("Roboto", 0, 18)); // NOI18N
        DOL_year_comboboxA1.setForeground(new java.awt.Color(44, 62, 80));
        DOL_year_comboboxA1.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "YYYY", "1950", "1951", "1952", "1953", "1954", "1955", "1956", "1957", "1958", "1959", "1960", "1961", "1962", "1963", "1964", "1965", "1966", "1967", "1968", "1969", "1970", "1971", "1972", "1973", "1974", "1975", "1976", "1977", "1978", "1979", "1980", "1981", "1982", "1983", "1984", "1985", "1986", "1987", "1988", "1989", "1990", "1991", "1992", "1993", "1994", "1995", "1996", "1997", "1998", "1999", "2000", "2001", "2002", "2003", "2004", "2005", "2006", "2007", "2008", "2009", "2010", "2011", "2012", "2013", "2014", "2015", "2016", "2017", "2018", "2019", "2020", "2021", "2022", "2023", "2024", "2025", "2026", "2027", "2028", "2029", "2030", "2031", "2032", "2033", "2034", "2035", "2036", "2037", "2038", "2039", "2040", "2041", "2042", "2043", "2044", "2045", "2046", "2047", "2048", "2049", "2050", "2051", "2052", "2053", "2054", "2055", "2056", "2057", "2058", "2059", "2060", "2061", "2062", "2063", "2064", "2065", "2066", "2067", "2068", "2069", "2070", "2071", "2072", "2073", "2074", "2075", "2076", "2077", "2078", "2079", "2080", "2081", "2082", "2083", "2084", "2085", "2086", "2087", "2088", "2089", "2090", "2091", "2092", "2093", "2094", "2095", "2096", "2097", "2098", "2099", "2100", " " }));
        DOL_year_comboboxA1.setBorder(null);
        panelA.add(DOL_year_comboboxA1, new org.netbeans.lib.awtextra.AbsoluteConstraints(310, 940, 100, 40));

        DOL_date_comboboxA1.setFont(new java.awt.Font("Roboto", 0, 18)); // NOI18N
        DOL_date_comboboxA1.setForeground(new java.awt.Color(44, 62, 80));
        DOL_date_comboboxA1.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "DD", "01", "02", "03", "04", "05", "06", "07", "08", "09", "10", "11", "12", "13", "14", "15", "16", "17", "18", "19", "20", "21", "22", "23", "24", "25", "26", "27", "28", "29", "30", "31" }));
        DOL_date_comboboxA1.setBorder(null);
        panelA.add(DOL_date_comboboxA1, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 940, 100, 40));

        DOL_month_comboboxA1.setFont(new java.awt.Font("Roboto", 0, 18)); // NOI18N
        DOL_month_comboboxA1.setForeground(new java.awt.Color(44, 62, 80));
        DOL_month_comboboxA1.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "MM", "01", "02", "03", "04", "05", "06", "07", "08", "09", "10", "11", "12" }));
        DOL_month_comboboxA1.setBorder(null);
        panelA.add(DOL_month_comboboxA1, new org.netbeans.lib.awtextra.AbsoluteConstraints(195, 940, 100, 40));

        DIAG_labelA2.setBackground(new java.awt.Color(255, 255, 255));
        DIAG_labelA2.setFont(new java.awt.Font("Montserrat", 0, 24)); // NOI18N
        DIAG_labelA2.setForeground(new java.awt.Color(66, 50, 77));
        DIAG_labelA2.setText("DIAG");
        panelA.add(DIAG_labelA2, new org.netbeans.lib.awtextra.AbsoluteConstraints(350, 1180, -1, 40));

        BCPC_labelA2.setBackground(new java.awt.Color(255, 255, 255));
        BCPC_labelA2.setFont(new java.awt.Font("Montserrat", 0, 24)); // NOI18N
        BCPC_labelA2.setForeground(new java.awt.Color(66, 50, 77));
        BCPC_labelA2.setText("BC/PC");
        panelA.add(BCPC_labelA2, new org.netbeans.lib.awtextra.AbsoluteConstraints(620, 1180, -1, 40));

        CIV_EDN_textfieldA2.setFont(new java.awt.Font("Roboto", 0, 24)); // NOI18N
        CIV_EDN_textfieldA2.setForeground(new java.awt.Color(54, 33, 89));
        CIV_EDN_textfieldA2.setBorder(null);
        CIV_EDN_textfieldA2.setMargin(new java.awt.Insets(2, 4, 2, 2));
        CIV_EDN_textfieldA2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                CIV_EDN_textfieldA2ActionPerformed(evt);
            }
        });
        panelA.add(CIV_EDN_textfieldA2, new org.netbeans.lib.awtextra.AbsoluteConstraints(350, 1220, 210, 40));

        underline10.setForeground(new java.awt.Color(54, 33, 89));
        panelA.add(underline10, new org.netbeans.lib.awtextra.AbsoluteConstraints(350, 1260, 210, 20));

        ACE_textfieldA2.setFont(new java.awt.Font("Roboto", 0, 24)); // NOI18N
        ACE_textfieldA2.setForeground(new java.awt.Color(54, 33, 89));
        ACE_textfieldA2.setBorder(null);
        ACE_textfieldA2.setMargin(new java.awt.Insets(2, 4, 2, 2));
        ACE_textfieldA2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ACE_textfieldA2ActionPerformed(evt);
            }
        });
        panelA.add(ACE_textfieldA2, new org.netbeans.lib.awtextra.AbsoluteConstraints(620, 1220, 210, 40));

        underline11.setForeground(new java.awt.Color(54, 33, 89));
        panelA.add(underline11, new org.netbeans.lib.awtextra.AbsoluteConstraints(620, 1260, 210, 20));

        midA3.setForeground(new java.awt.Color(204, 204, 255));
        panelA.add(midA3, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 1140, 440, 10));

        emp_restn_labelA2.setBackground(new java.awt.Color(255, 255, 255));
        emp_restn_labelA2.setFont(new java.awt.Font("Montserrat", 0, 24)); // NOI18N
        emp_restn_labelA2.setForeground(new java.awt.Color(66, 50, 77));
        emp_restn_labelA2.setText("Emp Restn");
        panelA.add(emp_restn_labelA2, new org.netbeans.lib.awtextra.AbsoluteConstraints(500, 1380, 260, 40));

        DOL_labelA2.setBackground(new java.awt.Color(255, 255, 255));
        DOL_labelA2.setFont(new java.awt.Font("Montserrat", 0, 24)); // NOI18N
        DOL_labelA2.setForeground(new java.awt.Color(66, 50, 77));
        DOL_labelA2.setText("Date of Last Med Bd");
        panelA.add(DOL_labelA2, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 1280, -1, 40));

        emp_restn_textfield2.setFont(new java.awt.Font("Roboto", 0, 24)); // NOI18N
        emp_restn_textfield2.setForeground(new java.awt.Color(54, 33, 89));
        emp_restn_textfield2.setBorder(null);
        emp_restn_textfield2.setMargin(new java.awt.Insets(2, 4, 2, 2));
        emp_restn_textfield2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                emp_restn_textfield2ActionPerformed(evt);
            }
        });
        panelA.add(emp_restn_textfield2, new org.netbeans.lib.awtextra.AbsoluteConstraints(500, 1420, 330, 40));

        med_category_labelA2.setBackground(new java.awt.Color(255, 255, 255));
        med_category_labelA2.setFont(new java.awt.Font("Montserrat", 0, 24)); // NOI18N
        med_category_labelA2.setForeground(new java.awt.Color(66, 50, 77));
        med_category_labelA2.setText("Med Category");
        panelA.add(med_category_labelA2, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 1180, -1, 40));

        med_category_textfieldA2.setFont(new java.awt.Font("Roboto", 0, 24)); // NOI18N
        med_category_textfieldA2.setForeground(new java.awt.Color(54, 33, 89));
        med_category_textfieldA2.setBorder(null);
        med_category_textfieldA2.setMargin(new java.awt.Insets(2, 4, 2, 2));
        med_category_textfieldA2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                med_category_textfieldA2ActionPerformed(evt);
            }
        });
        panelA.add(med_category_textfieldA2, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 1220, 210, 40));

        underline12.setForeground(new java.awt.Color(54, 33, 89));
        panelA.add(underline12, new org.netbeans.lib.awtextra.AbsoluteConstraints(500, 1460, 330, 20));

        underline13.setForeground(new java.awt.Color(54, 33, 89));
        panelA.add(underline13, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 1260, 210, 20));

        due_year_comboboxA2.setFont(new java.awt.Font("Roboto", 0, 18)); // NOI18N
        due_year_comboboxA2.setForeground(new java.awt.Color(44, 62, 80));
        due_year_comboboxA2.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "YYYY", "1950", "1951", "1952", "1953", "1954", "1955", "1956", "1957", "1958", "1959", "1960", "1961", "1962", "1963", "1964", "1965", "1966", "1967", "1968", "1969", "1970", "1971", "1972", "1973", "1974", "1975", "1976", "1977", "1978", "1979", "1980", "1981", "1982", "1983", "1984", "1985", "1986", "1987", "1988", "1989", "1990", "1991", "1992", "1993", "1994", "1995", "1996", "1997", "1998", "1999", "2000", "2001", "2002", "2003", "2004", "2005", "2006", "2007", "2008", "2009", "2010", "2011", "2012", "2013", "2014", "2015", "2016", "2017", "2018", "2019", "2020", "2021", "2022", "2023", "2024", "2025", "2026", "2027", "2028", "2029", "2030", "2031", "2032", "2033", "2034", "2035", "2036", "2037", "2038", "2039", "2040", "2041", "2042", "2043", "2044", "2045", "2046", "2047", "2048", "2049", "2050", "2051", "2052", "2053", "2054", "2055", "2056", "2057", "2058", "2059", "2060", "2061", "2062", "2063", "2064", "2065", "2066", "2067", "2068", "2069", "2070", "2071", "2072", "2073", "2074", "2075", "2076", "2077", "2078", "2079", "2080", "2081", "2082", "2083", "2084", "2085", "2086", "2087", "2088", "2089", "2090", "2091", "2092", "2093", "2094", "2095", "2096", "2097", "2098", "2099", "2100", " " }));
        due_year_comboboxA2.setBorder(null);
        panelA.add(due_year_comboboxA2, new org.netbeans.lib.awtextra.AbsoluteConstraints(310, 1420, 100, 40));

        due_date_comboboxA2.setFont(new java.awt.Font("Roboto", 0, 18)); // NOI18N
        due_date_comboboxA2.setForeground(new java.awt.Color(44, 62, 80));
        due_date_comboboxA2.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "DD", "01", "02", "03", "04", "05", "06", "07", "08", "09", "10", "11", "12", "13", "14", "15", "16", "17", "18", "19", "20", "21", "22", "23", "24", "25", "26", "27", "28", "29", "30", "31" }));
        due_date_comboboxA2.setBorder(null);
        panelA.add(due_date_comboboxA2, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 1420, 100, 40));

        due_month_comboboxA2.setFont(new java.awt.Font("Roboto", 0, 18)); // NOI18N
        due_month_comboboxA2.setForeground(new java.awt.Color(44, 62, 80));
        due_month_comboboxA2.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "MM", "01", "02", "03", "04", "05", "06", "07", "08", "09", "10", "11", "12" }));
        due_month_comboboxA2.setBorder(null);
        panelA.add(due_month_comboboxA2, new org.netbeans.lib.awtextra.AbsoluteConstraints(195, 1420, 100, 40));

        DOI_labelA2.setBackground(new java.awt.Color(255, 255, 255));
        DOI_labelA2.setFont(new java.awt.Font("Montserrat", 0, 24)); // NOI18N
        DOI_labelA2.setForeground(new java.awt.Color(51, 51, 51));
        DOI_labelA2.setText("Date of Initial Med Bd");
        panelA.add(DOI_labelA2, new org.netbeans.lib.awtextra.AbsoluteConstraints(500, 1280, -1, 40));

        DOI_date_comboboxA2.setFont(new java.awt.Font("Roboto", 0, 18)); // NOI18N
        DOI_date_comboboxA2.setForeground(new java.awt.Color(44, 62, 80));
        DOI_date_comboboxA2.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "DD", "01", "02", "03", "04", "05", "06", "07", "08", "09", "10", "11", "12", "13", "14", "15", "16", "17", "18", "19", "20", "21", "22", "23", "24", "25", "26", "27", "28", "29", "30", "31" }));
        DOI_date_comboboxA2.setBorder(null);
        DOI_date_comboboxA2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                DOI_date_comboboxA2ActionPerformed(evt);
            }
        });
        panelA.add(DOI_date_comboboxA2, new org.netbeans.lib.awtextra.AbsoluteConstraints(500, 1320, 100, 40));

        DOI_month_comboboxA2.setFont(new java.awt.Font("Roboto", 0, 18)); // NOI18N
        DOI_month_comboboxA2.setForeground(new java.awt.Color(44, 62, 80));
        DOI_month_comboboxA2.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "MM", "01", "02", "03", "04", "05", "06", "07", "08", "09", "10", "11", "12" }));
        DOI_month_comboboxA2.setBorder(null);
        panelA.add(DOI_month_comboboxA2, new org.netbeans.lib.awtextra.AbsoluteConstraints(615, 1320, 100, 40));

        DOI_year_comboboxA2.setFont(new java.awt.Font("Roboto", 0, 18)); // NOI18N
        DOI_year_comboboxA2.setForeground(new java.awt.Color(44, 62, 80));
        DOI_year_comboboxA2.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "YYYY", "1950", "1951", "1952", "1953", "1954", "1955", "1956", "1957", "1958", "1959", "1960", "1961", "1962", "1963", "1964", "1965", "1966", "1967", "1968", "1969", "1970", "1971", "1972", "1973", "1974", "1975", "1976", "1977", "1978", "1979", "1980", "1981", "1982", "1983", "1984", "1985", "1986", "1987", "1988", "1989", "1990", "1991", "1992", "1993", "1994", "1995", "1996", "1997", "1998", "1999", "2000", "2001", "2002", "2003", "2004", "2005", "2006", "2007", "2008", "2009", "2010", "2011", "2012", "2013", "2014", "2015", "2016", "2017", "2018", "2019", "2020", "2021", "2022", "2023", "2024", "2025", "2026", "2027", "2028", "2029", "2030", "2031", "2032", "2033", "2034", "2035", "2036", "2037", "2038", "2039", "2040", "2041", "2042", "2043", "2044", "2045", "2046", "2047", "2048", "2049", "2050", "2051", "2052", "2053", "2054", "2055", "2056", "2057", "2058", "2059", "2060", "2061", "2062", "2063", "2064", "2065", "2066", "2067", "2068", "2069", "2070", "2071", "2072", "2073", "2074", "2075", "2076", "2077", "2078", "2079", "2080", "2081", "2082", "2083", "2084", "2085", "2086", "2087", "2088", "2089", "2090", "2091", "2092", "2093", "2094", "2095", "2096", "2097", "2098", "2099", "2100", " " }));
        DOI_year_comboboxA2.setBorder(null);
        panelA.add(DOI_year_comboboxA2, new org.netbeans.lib.awtextra.AbsoluteConstraints(730, 1320, 100, 40));

        due_date_labelA2.setBackground(new java.awt.Color(255, 255, 255));
        due_date_labelA2.setFont(new java.awt.Font("Montserrat", 0, 24)); // NOI18N
        due_date_labelA2.setForeground(new java.awt.Color(66, 50, 77));
        due_date_labelA2.setText("Next Due Date");
        panelA.add(due_date_labelA2, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 1380, -1, 40));

        DOL_year_comboboxA2.setFont(new java.awt.Font("Roboto", 0, 18)); // NOI18N
        DOL_year_comboboxA2.setForeground(new java.awt.Color(44, 62, 80));
        DOL_year_comboboxA2.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "YYYY", "1950", "1951", "1952", "1953", "1954", "1955", "1956", "1957", "1958", "1959", "1960", "1961", "1962", "1963", "1964", "1965", "1966", "1967", "1968", "1969", "1970", "1971", "1972", "1973", "1974", "1975", "1976", "1977", "1978", "1979", "1980", "1981", "1982", "1983", "1984", "1985", "1986", "1987", "1988", "1989", "1990", "1991", "1992", "1993", "1994", "1995", "1996", "1997", "1998", "1999", "2000", "2001", "2002", "2003", "2004", "2005", "2006", "2007", "2008", "2009", "2010", "2011", "2012", "2013", "2014", "2015", "2016", "2017", "2018", "2019", "2020", "2021", "2022", "2023", "2024", "2025", "2026", "2027", "2028", "2029", "2030", "2031", "2032", "2033", "2034", "2035", "2036", "2037", "2038", "2039", "2040", "2041", "2042", "2043", "2044", "2045", "2046", "2047", "2048", "2049", "2050", "2051", "2052", "2053", "2054", "2055", "2056", "2057", "2058", "2059", "2060", "2061", "2062", "2063", "2064", "2065", "2066", "2067", "2068", "2069", "2070", "2071", "2072", "2073", "2074", "2075", "2076", "2077", "2078", "2079", "2080", "2081", "2082", "2083", "2084", "2085", "2086", "2087", "2088", "2089", "2090", "2091", "2092", "2093", "2094", "2095", "2096", "2097", "2098", "2099", "2100", " " }));
        DOL_year_comboboxA2.setBorder(null);
        panelA.add(DOL_year_comboboxA2, new org.netbeans.lib.awtextra.AbsoluteConstraints(310, 1320, 100, 40));

        DOL_date_comboboxA2.setFont(new java.awt.Font("Roboto", 0, 18)); // NOI18N
        DOL_date_comboboxA2.setForeground(new java.awt.Color(44, 62, 80));
        DOL_date_comboboxA2.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "DD", "01", "02", "03", "04", "05", "06", "07", "08", "09", "10", "11", "12", "13", "14", "15", "16", "17", "18", "19", "20", "21", "22", "23", "24", "25", "26", "27", "28", "29", "30", "31" }));
        DOL_date_comboboxA2.setBorder(null);
        panelA.add(DOL_date_comboboxA2, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 1320, 100, 40));

        DOL_month_comboboxA2.setFont(new java.awt.Font("Roboto", 0, 18)); // NOI18N
        DOL_month_comboboxA2.setForeground(new java.awt.Color(44, 62, 80));
        DOL_month_comboboxA2.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "MM", "01", "02", "03", "04", "05", "06", "07", "08", "09", "10", "11", "12" }));
        DOL_month_comboboxA2.setBorder(null);
        panelA.add(DOL_month_comboboxA2, new org.netbeans.lib.awtextra.AbsoluteConstraints(195, 1320, 100, 40));

        ScrollPaneA.setViewportView(panelA);

        bg.add(ScrollPaneA, new org.netbeans.lib.awtextra.AbsoluteConstraints(340, 192, 910, 480));

        print_button.setForeground(new java.awt.Color(240, 240, 240));
        print_button.setMaximumSize(new java.awt.Dimension(95, 25));
        print_button.setMinimumSize(new java.awt.Dimension(95, 25));
        print_button.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                print_buttonMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                print_buttonMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                print_buttonMouseExited(evt);
            }
        });
        print_button.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        print_label.setBackground(new java.awt.Color(64, 43, 100));
        print_label.setFont(new java.awt.Font("Roboto", 0, 14)); // NOI18N
        print_label.setForeground(new java.awt.Color(64, 43, 100));
        print_label.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        print_label.setText("PRINT");
        print_label.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                print_labelMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                print_labelMouseEntered(evt);
            }
        });
        print_button.add(print_label, new org.netbeans.lib.awtextra.AbsoluteConstraints(52, 13, 71, 24));

        bg.add(print_button, new org.netbeans.lib.awtextra.AbsoluteConstraints(1070, 120, 180, 50));

        getContentPane().add(bg, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 1300, 740));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void formWindowOpened(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowOpened
        //for a centered frame
        Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
        this.setLocation(dim.width / 2 - this.getSize().width / 2, dim.height / 2 - this.getSize().height / 2);
        //for a centered frame

        go_back = true;
        enableFields(false, false);
    }//GEN-LAST:event_formWindowOpened

    private void move_panelMouseDragged(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_move_panelMouseDragged
        this.setLocation(this.getX() + evt.getX() - mouseX, this.getY() + evt.getY() - mouseY);
    }//GEN-LAST:event_move_panelMouseDragged

    private void move_panelMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_move_panelMousePressed
        mouseX = evt.getX();
        mouseY = evt.getY();
    }//GEN-LAST:event_move_panelMousePressed

    private void exit_icon1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_exit_icon1MouseClicked
        int confirmed = JOptionPane.showConfirmDialog(null, "Are you sure you want to exit the program?", "Exit Program", JOptionPane.YES_NO_OPTION);

        if (confirmed == JOptionPane.YES_OPTION) {
            dispose();
            db.create_connection(false);
        }
    }//GEN-LAST:event_exit_icon1MouseClicked

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

    }//GEN-LAST:event_menuAMouseClicked

    private void okayMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_okayMouseClicked
        retrieve_Army_No();
    }//GEN-LAST:event_okayMouseClicked

    private void CIV_EDN_textfieldAActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_CIV_EDN_textfieldAActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_CIV_EDN_textfieldAActionPerformed

    private void ACE_textfieldAActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ACE_textfieldAActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_ACE_textfieldAActionPerformed

    private void emp_restn_textfieldActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_emp_restn_textfieldActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_emp_restn_textfieldActionPerformed

    private void med_category_textfieldAActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_med_category_textfieldAActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_med_category_textfieldAActionPerformed

    private void print_labelMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_print_labelMouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_print_labelMouseClicked

    private void print_labelMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_print_labelMouseEntered
        // TODO add your handling code here:
    }//GEN-LAST:event_print_labelMouseEntered

    private void print_buttonMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_print_buttonMouseClicked
        title_label.grabFocus();
        if (!retrieve_Army_No().equals("")) {
            title_label.grabFocus();
            
            if (validate_page()) {
                if (med_category_textfieldA1.getText().trim().equals("")) {
                    visibleFields(false, false);
                }
                if (med_category_textfieldA2.getText().trim().equals("")) {
                    visibleFields(true, false);
                }
                PrintRecord();
                if (med_category_textfieldA1.getText().trim().equals("")) {
                    visibleFields(true, true);
                }
                if (med_category_textfieldA2.getText().trim().equals("")) {
                    visibleFields(true, true);
                }
            }
        }

    }//GEN-LAST:event_print_buttonMouseClicked

    private void print_buttonMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_print_buttonMouseEntered
        print_button.setBackground(new java.awt.Color(237, 224, 255));
    }//GEN-LAST:event_print_buttonMouseEntered

    private void print_buttonMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_print_buttonMouseExited
        print_button.setBackground(new java.awt.Color(240, 240, 240));
    }//GEN-LAST:event_print_buttonMouseExited

    private void DOI_date_comboboxAActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_DOI_date_comboboxAActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_DOI_date_comboboxAActionPerformed

    private void CIV_EDN_textfieldA1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_CIV_EDN_textfieldA1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_CIV_EDN_textfieldA1ActionPerformed

    private void ACE_textfieldA1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ACE_textfieldA1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_ACE_textfieldA1ActionPerformed

    private void emp_restn_textfield1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_emp_restn_textfield1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_emp_restn_textfield1ActionPerformed

    private void med_category_textfieldA1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_med_category_textfieldA1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_med_category_textfieldA1ActionPerformed

    private void DOI_date_comboboxA1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_DOI_date_comboboxA1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_DOI_date_comboboxA1ActionPerformed

    private void CIV_EDN_textfieldA2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_CIV_EDN_textfieldA2ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_CIV_EDN_textfieldA2ActionPerformed

    private void ACE_textfieldA2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ACE_textfieldA2ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_ACE_textfieldA2ActionPerformed

    private void emp_restn_textfield2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_emp_restn_textfield2ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_emp_restn_textfield2ActionPerformed

    private void med_category_textfieldA2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_med_category_textfieldA2ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_med_category_textfieldA2ActionPerformed

    private void DOI_date_comboboxA2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_DOI_date_comboboxA2ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_DOI_date_comboboxA2ActionPerformed

    private void emp_restn_textfieldKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_emp_restn_textfieldKeyTyped
        // TODO add your handling code here:
        enableFields(true, false);
    }//GEN-LAST:event_emp_restn_textfieldKeyTyped

    private void emp_restn_textfield1KeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_emp_restn_textfield1KeyTyped
        // TODO add your handling code here:
        enableFields(true, true);
    }//GEN-LAST:event_emp_restn_textfield1KeyTyped

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
            java.util.logging.Logger.getLogger(low_med_cat.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(low_med_cat.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(low_med_cat.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(low_med_cat.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new low_med_cat().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTextField ACE_textfieldA;
    private javax.swing.JTextField ACE_textfieldA1;
    private javax.swing.JTextField ACE_textfieldA2;
    private javax.swing.JLabel BCPC_labelA;
    private javax.swing.JLabel BCPC_labelA1;
    private javax.swing.JLabel BCPC_labelA2;
    private javax.swing.JTextField CIV_EDN_textfieldA;
    private javax.swing.JTextField CIV_EDN_textfieldA1;
    private javax.swing.JTextField CIV_EDN_textfieldA2;
    private javax.swing.JLabel DIAG_labelA;
    private javax.swing.JLabel DIAG_labelA1;
    private javax.swing.JLabel DIAG_labelA2;
    private javax.swing.JComboBox<String> DOI_date_comboboxA;
    private javax.swing.JComboBox<String> DOI_date_comboboxA1;
    private javax.swing.JComboBox<String> DOI_date_comboboxA2;
    private javax.swing.JLabel DOI_labelA;
    private javax.swing.JLabel DOI_labelA1;
    private javax.swing.JLabel DOI_labelA2;
    private javax.swing.JComboBox<String> DOI_month_comboboxA;
    private javax.swing.JComboBox<String> DOI_month_comboboxA1;
    private javax.swing.JComboBox<String> DOI_month_comboboxA2;
    private javax.swing.JComboBox<String> DOI_year_comboboxA;
    private javax.swing.JComboBox<String> DOI_year_comboboxA1;
    private javax.swing.JComboBox<String> DOI_year_comboboxA2;
    private javax.swing.JComboBox<String> DOL_date_comboboxA;
    private javax.swing.JComboBox<String> DOL_date_comboboxA1;
    private javax.swing.JComboBox<String> DOL_date_comboboxA2;
    private javax.swing.JLabel DOL_labelA;
    private javax.swing.JLabel DOL_labelA1;
    private javax.swing.JLabel DOL_labelA2;
    private javax.swing.JComboBox<String> DOL_month_comboboxA;
    private javax.swing.JComboBox<String> DOL_month_comboboxA1;
    private javax.swing.JComboBox<String> DOL_month_comboboxA2;
    private javax.swing.JComboBox<String> DOL_year_comboboxA;
    private javax.swing.JComboBox<String> DOL_year_comboboxA1;
    private javax.swing.JComboBox<String> DOL_year_comboboxA2;
    private javax.swing.JScrollPane ScrollPaneA;
    private javax.swing.JSeparator Separator;
    private javax.swing.JPanel bg;
    private javax.swing.JLabel company;
    private javax.swing.JLabel company_label;
    private javax.swing.JComboBox<String> due_date_comboboxA;
    private javax.swing.JComboBox<String> due_date_comboboxA1;
    private javax.swing.JComboBox<String> due_date_comboboxA2;
    private javax.swing.JLabel due_date_labelA;
    private javax.swing.JLabel due_date_labelA1;
    private javax.swing.JLabel due_date_labelA2;
    private javax.swing.JComboBox<String> due_month_comboboxA;
    private javax.swing.JComboBox<String> due_month_comboboxA1;
    private javax.swing.JComboBox<String> due_month_comboboxA2;
    private javax.swing.JComboBox<String> due_year_comboboxA;
    private javax.swing.JComboBox<String> due_year_comboboxA1;
    private javax.swing.JComboBox<String> due_year_comboboxA2;
    private javax.swing.JLabel emp_restn_labelA;
    private javax.swing.JLabel emp_restn_labelA1;
    private javax.swing.JLabel emp_restn_labelA2;
    private javax.swing.JTextField emp_restn_textfield;
    private javax.swing.JTextField emp_restn_textfield1;
    private javax.swing.JTextField emp_restn_textfield2;
    private javax.swing.JLabel exit_icon1;
    private javax.swing.JLabel go_back_label;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JLabel med_category_labelA;
    private javax.swing.JLabel med_category_labelA1;
    private javax.swing.JLabel med_category_labelA2;
    private javax.swing.JTextField med_category_textfieldA;
    private javax.swing.JTextField med_category_textfieldA1;
    private javax.swing.JTextField med_category_textfieldA2;
    private javax.swing.JPanel menuA;
    private javax.swing.JLabel menuA_icon;
    private javax.swing.JLabel menuA_label;
    private javax.swing.JSeparator midA1;
    private javax.swing.JSeparator midA2;
    private javax.swing.JSeparator midA3;
    private javax.swing.JLabel minimize;
    private javax.swing.JPanel move_panel;
    private javax.swing.JLabel name;
    private javax.swing.JLabel name_label;
    private javax.swing.JLabel okay;
    private javax.swing.JPanel panelA;
    private javax.swing.JSeparator panelA_bottom_line;
    private javax.swing.JPanel print_button;
    private javax.swing.JLabel print_label;
    private javax.swing.JLabel rank;
    private javax.swing.JLabel rank_label;
    private javax.swing.JLabel service_number_label;
    private javax.swing.JTextField service_number_textfield;
    private javax.swing.JSeparator service_number_underline;
    private javax.swing.JPanel side_pane;
    private javax.swing.JLabel title_icon;
    private javax.swing.JLabel title_label;
    private javax.swing.JLabel trade;
    private javax.swing.JLabel trade_label;
    private javax.swing.JSeparator underline10;
    private javax.swing.JSeparator underline11;
    private javax.swing.JSeparator underline12;
    private javax.swing.JSeparator underline13;
    private javax.swing.JSeparator underline2;
    private javax.swing.JSeparator underline3;
    private javax.swing.JSeparator underline4;
    private javax.swing.JSeparator underline5;
    private javax.swing.JSeparator underline6;
    private javax.swing.JSeparator underline7;
    private javax.swing.JSeparator underline8;
    private javax.swing.JSeparator underline9;
    private javax.swing.JLabel unit;
    private javax.swing.JLabel unit_label;
    // End of variables declaration//GEN-END:variables
}
