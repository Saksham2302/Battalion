
import java.awt.Color;
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
public class Firing extends javax.swing.JFrame {

    private String service_no_panelB = "", test_date_ = "";
    private boolean go_back = true;
    /**
     * Creates new form Firing
     */
    DefaultTableModel model_table, fullscreen_table;

    private int mouseX, mouseY;

    public Firing() {
        initComponents();
        
        ScrollPaneC.getVerticalScrollBar().setUnitIncrement(16);
        ScrollPaneA.getVerticalScrollBar().setUnitIncrement(16);
        ScrollPaneB.getVerticalScrollBar().setUnitIncrement(16);
        fullscreen_ScrollPaneB.getVerticalScrollBar().setUnitIncrement(16);

        print_buttonA.setVisible(true);
        insert_buttonA.setVisible(true);
        print_buttonB.setVisible(false);
        edit_buttonB.setVisible(false);
        refresh_buttonB.setVisible(false);
        fullscreen_buttonB.setVisible(false);
        update_buttonC.setVisible(false);

        model_table = (DefaultTableModel) TableG_firing.getModel();
        fullscreen_table = (DefaultTableModel) fullscreen_tableB.getModel();
    }

    Database db = new Database();
    Connection con = db.create_connection(true);

    private String[] retrieve_Army_No() {
        String firing_name = "";
        String firing_rank = "";
        String firing_company = "";

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
                    firing_name = name_;
                    firing_rank = rank_;
                    firing_company = company_;
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
                        firing_name = name_;
                        firing_rank = rank_;
                        firing_company = company_;
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
                            firing_name = name_;
                            firing_rank = rank_;
                            firing_company = company_;
                        }
                    }
                }

                if (firing_name.equals("")) {
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
        String value[] = {firing_name, firing_rank, firing_company};
        return value;
    }

    private String validate_date(String panel_) {
        String day_, month_, year_;
        if (panel_.equals("A")) {
            day_ = (String) test_date_combobox.getSelectedItem();
            month_ = (String) test_month_combobox.getSelectedItem();
            year_ = (String) test_year_combobox.getSelectedItem();
        } else {
            day_ = (String) test_date_comboboxC.getSelectedItem();
            month_ = (String) test_month_comboboxC.getSelectedItem();
            year_ = (String) test_year_comboboxC.getSelectedItem();
        }

        String Join_date = "";
        if ("DD".equals(day_) || "MM".equals(month_) || "YYYY".equals(year_)) {
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

    private boolean viewByArmyNo() {
        boolean flag = false;
        try {

            Statement stmt = con.createStatement();

            service_no_panelB = service_numberB.getText().trim();
            if (service_numberB.getText().length() != 0) {
                String Query = "Select * from firing where service_no='" + service_numberB.getText().trim() + "';";
                ResultSet rs = stmt.executeQuery(Query);
                String name, wpn, four, three, two, one, result, test_date, rank, company;
                int sno = 0;

                model_table.setRowCount(0);
                fullscreen_table.setRowCount(0);
                while (rs.next()) {
                    flag = true;
                    sno++;
                    name = rs.getString("Name");
                    wpn = rs.getString("wpn");
                    rank = rs.getString("rank_");
                    company = rs.getString("company");
                    four = rs.getString("400m");
                    three = rs.getString("300m");
                    two = rs.getString("200m");
                    one = rs.getString("100m");
                    result = rs.getString("result");
                    test_date = rs.getString("test_date");

                    four = four.replace("/;", "/NA;");
                    four = four.replace("; /", "; NA/");
                    if (four.startsWith("/")) {
                        four = "NA" + four;
                    }

                    three = three.replace("/;", "/NA;");
                    three = three.replace("; /", "; NA/");
                    if (three.startsWith("/")) {
                        three = "NA" + three;
                    }

                    two = two.replace("/;", "/NA;");
                    two = two.replace("; /", "; NA/");
                    if (two.startsWith("/")) {
                        two = "NA" + two;
                    }

                    one = one.replace("/;", "/NA;");
                    one = one.replace("; /", "; NA/");
                    if (one.startsWith("/")) {
                        one = "NA" + one;
                    }

                    result = result.replace("; ;", "; NA;");
                    if (result.startsWith(";")) {
                        result = "NA" + result;
                    }

                    model_table.addRow(new Object[]{
                        sno, name, wpn, test_date, four, three, two, one, result
                    });
                    fullscreen_table.addRow(new Object[]{
                        sno, name, rank, company, wpn, test_date, four, three, two, one, result
                    });
                }
            }
            if (flag == false) {
                JOptionPane.showMessageDialog(null, "Army Number does not exist!", "Error", JOptionPane.ERROR_MESSAGE);
            }
        } catch (Exception e) {
                        JOptionPane.showMessageDialog(null, e);

        }
        return flag;
    }

    private void viewall() {
        try {

            Statement stmt = con.createStatement();
            String Query = "Select * from firing;";
            ResultSet rs = stmt.executeQuery(Query);
            String service_no, name, wpn, four, three, two, one, result, test_date, rank, company;
            int sno = 0;

            model_table.setRowCount(0);
            fullscreen_table.setRowCount(0);
            while (rs.next()) {

                sno++;
                service_no = rs.getString("service_no");
                name = rs.getString("Name");
                wpn = rs.getString("wpn");
                rank = rs.getString("rank_");
                company = rs.getString("company");
                four = rs.getString("400m");
                three = rs.getString("300m");
                two = rs.getString("200m");
                one = rs.getString("100m");
                result = rs.getString("result");
                test_date = rs.getString("test_date");

                four = four.replace("/;", "/NA;");
                four = four.replace("; /", "; NA/");
                if (four.startsWith("/")) {
                    four = "NA" + four;
                }

                three = three.replace("/;", "/NA;");
                three = three.replace("; /", "; NA/");
                if (three.startsWith("/")) {
                    three = "NA" + three;
                }

                two = two.replace("/;", "/NA;");
                two = two.replace("; /", "; NA/");
                if (two.startsWith("/")) {
                    two = "NA" + two;
                }

                one = one.replace("/;", "/NA;");
                one = one.replace("; /", "; NA/");
                if (one.startsWith("/")) {
                    one = "NA" + one;
                }

                result = result.replace("; ;", "; NA;");
                if (result.startsWith(";")) {
                    result = "NA" + result;
                }

                model_table.addRow(new Object[]{
                    sno, service_no, name, wpn, test_date, four, three, two, one, result
                });
                fullscreen_table.addRow(new Object[]{
                    sno, service_no, name, rank, company, wpn, test_date, four, three, two, one, result
                });
            }

        } catch (Exception e) {
                      JOptionPane.showMessageDialog(null, e);

        }
    }

    private void editingData(int row) {
        army_numberC.setText((String) fullscreen_tableB.getValueAt(row, 1));
        nameC.setText((String) fullscreen_tableB.getValueAt(row, 2));
        rankC.setText((String) fullscreen_tableB.getValueAt(row, 3));
        wpn_textfieldC.setText((String) fullscreen_tableB.getValueAt(row, 5));
        test_date_ = (String) fullscreen_tableB.getValueAt(row, 6);

        test_date_comboboxC.setSelectedItem(test_date_.substring(8, 10));
        test_month_comboboxC.setSelectedItem(test_date_.substring(5, 7));
        test_year_comboboxC.setSelectedItem(test_date_.substring(0, 4));

        String four = (String) fullscreen_tableB.getValueAt(row, 7);
        String three = (String) fullscreen_tableB.getValueAt(row, 8);
        String two = (String) fullscreen_tableB.getValueAt(row, 9);
        String one = (String) fullscreen_tableB.getValueAt(row, 10);
        String result = (String) fullscreen_tableB.getValueAt(row, 11);

        String rounds[][] = new String[5][];
        rounds[0] = four.split(";");
        rounds[1] = three.split(";");
        rounds[2] = two.split(";");
        rounds[3] = one.split(";");
        rounds[4] = result.split(";");
        if (four.equals("")) {
            score_textfield12.setText("");
            grade_textfield12.setText("");
            score_textfield16.setText("");
            grade_textfield16.setText("");
            score_textfield20.setText("");
            grade_textfield20.setText("");
        } else {

            if (rounds[0][0].equals("/")) {
                score_textfield12.setText("");
                grade_textfield12.setText("");

            } else if (rounds[0][0].endsWith("/")) {
                if (rounds[0][0].split("/")[0].trim().equals("NA")) {
                    score_textfield12.setText("");
                } else {
                    score_textfield12.setText(rounds[0][0].split("/")[0].trim());
                }
                grade_textfield12.setText("");
            } else {
                if (rounds[0][0].split("/")[0].trim().equals("NA")) {
                    score_textfield12.setText("");
                } else {
                    score_textfield12.setText(rounds[0][0].split("/")[0].trim());
                }
                if (rounds[0][0].split("/")[1].trim().equals("NA")) {
                    grade_textfield12.setText("");
                } else {
                    grade_textfield12.setText(rounds[0][0].split("/")[1].trim());
                }
            }

            if (rounds[0][1].equals("/")) {
                score_textfield16.setText("");
                grade_textfield16.setText("");

            } else if (rounds[0][1].endsWith("/")) {
                if (rounds[0][1].split("/")[0].trim().equals("NA")) {
                    score_textfield16.setText("");
                } else {
                    score_textfield16.setText(rounds[0][1].split("/")[0].trim());
                }
                grade_textfield16.setText("");
            } else {
                if (rounds[0][1].split("/")[0].trim().equals("NA")) {
                    score_textfield16.setText("");
                } else {
                    score_textfield16.setText(rounds[0][1].split("/")[0].trim());
                }
                if (rounds[0][1].split("/")[1].trim().equals("NA")) {
                    grade_textfield16.setText("");
                } else {
                    grade_textfield16.setText(rounds[0][1].split("/")[1].trim());
                }
            }
            if (rounds[0][2].equals("/")) {
                score_textfield20.setText("");
                grade_textfield20.setText("");

            } else if (rounds[0][2].endsWith("/")) {
                if (rounds[0][2].split("/")[0].trim().equals("NA")) {
                    score_textfield20.setText("");
                } else {
                    score_textfield20.setText(rounds[0][2].split("/")[0].trim());
                }
                grade_textfield20.setText("");
            } else {
                if (rounds[0][2].split("/")[0].trim().equals("NA")) {
                    score_textfield20.setText("");
                } else {
                    score_textfield20.setText(rounds[0][2].split("/")[0].trim());
                }
                if (rounds[0][2].split("/")[1].trim().equals("NA")) {
                    grade_textfield20.setText("");
                } else {
                    grade_textfield20.setText(rounds[0][2].split("/")[1].trim());
                }

            }

        }

        if (three.equals("")) {
            score_textfield13.setText("");
            grade_textfield13.setText("");
            score_textfield17.setText("");
            grade_textfield17.setText("");
            score_textfield21.setText("");
            grade_textfield21.setText("");
        } else {

            if (rounds[1][0].equals("/")) {
                score_textfield13.setText("");
                grade_textfield13.setText("");

            } else if (rounds[1][0].endsWith("/")) {
                if (rounds[1][0].split("/")[0].trim().equals("NA")) {
                    score_textfield13.setText("");
                } else {
                    score_textfield13.setText(rounds[1][0].split("/")[0].trim());
                }
                grade_textfield13.setText("");
            } else {
                if (rounds[1][0].split("/")[0].trim().equals("NA")) {
                    score_textfield13.setText("");
                } else {
                    score_textfield13.setText(rounds[1][0].split("/")[0].trim());
                }
                if (rounds[1][0].split("/")[1].trim().equals("NA")) {
                    grade_textfield13.setText("");
                } else {
                    grade_textfield13.setText(rounds[1][0].split("/")[1].trim());
                }
            }
            if (rounds[1][1].equals("/")) {
                score_textfield17.setText("");
                grade_textfield17.setText("");

            } else if (rounds[1][1].endsWith("/")) {
                if (rounds[1][1].split("/")[0].trim().equals("NA")) {
                    score_textfield17.setText("");
                } else {
                    score_textfield17.setText(rounds[1][1].split("/")[0].trim());
                }
                grade_textfield17.setText("");
            } else {
                if (rounds[1][1].split("/")[0].trim().equals("NA")) {
                    score_textfield17.setText("");
                } else {
                    score_textfield17.setText(rounds[1][1].split("/")[0].trim());
                }
                if (rounds[1][1].split("/")[1].trim().equals("NA")) {
                    grade_textfield17.setText("");
                } else {
                    grade_textfield17.setText(rounds[1][1].split("/")[1].trim());
                }
            }

            if (rounds[1][2].equals("/")) {
                score_textfield21.setText("");
                grade_textfield21.setText("");

            } else if (rounds[1][2].endsWith("/")) {
                if (rounds[1][2].split("/")[0].trim().equals("NA")) {
                    score_textfield21.setText("");
                } else {
                    score_textfield21.setText(rounds[1][2].split("/")[0].trim());
                }
                grade_textfield21.setText("");
            } else {
                if (rounds[1][2].split("/")[0].trim().equals("NA")) {
                    score_textfield21.setText("");
                } else {
                    score_textfield21.setText(rounds[1][2].split("/")[0].trim());
                }

                if (rounds[1][2].split("/")[1].trim().equals("NA")) {
                    grade_textfield21.setText("");
                } else {
                    grade_textfield21.setText(rounds[1][2].split("/")[1].trim());
                }

            }

        }

        if (two.equals("")) {
            score_textfield14.setText("");
            grade_textfield14.setText("");
            score_textfield18.setText("");
            grade_textfield18.setText("");
            score_textfield22.setText("");
            grade_textfield22.setText("");
        } else {
            if (rounds[2][0].equals("/")) {
                score_textfield14.setText("");
                grade_textfield14.setText("");

            } else if (rounds[2][0].endsWith("/")) {
                if (rounds[2][0].split("/")[0].trim().equals("NA")) {
                    score_textfield14.setText("");
                } else {
                    score_textfield14.setText(rounds[2][0].split("/")[0].trim());
                }
                grade_textfield14.setText("");
            } else {
                if (rounds[2][0].split("/")[0].trim().equals("NA")) {
                    score_textfield14.setText("");
                } else {
                    score_textfield14.setText(rounds[2][0].split("/")[0].trim());
                }
                if (rounds[2][0].split("/")[1].trim().equals("NA")) {
                    grade_textfield14.setText("");
                } else {
                    grade_textfield14.setText(rounds[2][0].split("/")[1].trim());
                }

            }
            if (rounds[2][1].equals("/")) {
                score_textfield18.setText("");
                grade_textfield18.setText("");

            } else if (rounds[2][1].endsWith("/")) {
                if (rounds[2][1].split("/")[0].trim().equals("NA")) {
                    score_textfield18.setText("");
                } else {
                    score_textfield18.setText(rounds[2][1].split("/")[0].trim());
                }
                grade_textfield18.setText("");
            } else {
                if (rounds[2][1].split("/")[0].trim().equals("NA")) {
                    score_textfield18.setText("");
                } else {
                    score_textfield18.setText(rounds[2][1].split("/")[0].trim());
                }
                if (rounds[2][1].split("/")[1].trim().equals("NA")) {
                    grade_textfield18.setText("");
                } else {
                    grade_textfield18.setText(rounds[2][1].split("/")[1].trim());
                }

            }

            if (rounds[2][2].equals("/")) {
                score_textfield22.setText("");
                grade_textfield22.setText("");

            } else if (rounds[2][2].endsWith("/")) {
                if (rounds[2][2].split("/")[0].trim().equals("NA")) {
                    score_textfield22.setText("");
                } else {
                    score_textfield22.setText(rounds[2][2].split("/")[0].trim());
                }
                grade_textfield22.setText("");
            } else {
                if (rounds[2][2].split("/")[0].trim().equals("NA")) {
                    score_textfield22.setText("");
                } else {
                    score_textfield22.setText(rounds[2][2].split("/")[0].trim());
                }

                if (rounds[2][2].split("/")[1].trim().equals("NA")) {
                    grade_textfield22.setText("");
                } else {
                    grade_textfield22.setText(rounds[2][2].split("/")[1].trim());
                }

            }

        }

        if (one.equals("")) {
            score_textfield15.setText("");
            grade_textfield15.setText("");
            score_textfield19.setText("");
            grade_textfield19.setText("");
            score_textfield23.setText("");
            grade_textfield23.setText("");
        } else {
            if (rounds[3][0].equals("/")) {
                score_textfield15.setText("");
                grade_textfield15.setText("");

            } else if (rounds[3][0].endsWith("/")) {
                if (rounds[3][0].split("/")[0].trim().equals("NA")) {
                    score_textfield15.setText("");
                } else {
                    score_textfield15.setText(rounds[3][0].split("/")[0].trim());
                }
                grade_textfield15.setText("");
            } else {
                if (rounds[3][0].split("/")[0].trim().equals("NA")) {
                    score_textfield15.setText("");
                } else {
                    score_textfield15.setText(rounds[3][0].split("/")[0].trim());
                }
                if (rounds[3][0].split("/")[1].trim().equals("NA")) {
                    grade_textfield15.setText("");
                } else {
                    grade_textfield15.setText(rounds[3][0].split("/")[1].trim());
                }
            }

            if (rounds[3][1].equals("/")) {
                score_textfield19.setText("");
                grade_textfield19.setText("");

            } else if (rounds[3][1].endsWith("/")) {
                if (rounds[3][1].split("/")[0].trim().equals("NA")) {
                    score_textfield19.setText("");
                } else {
                    score_textfield19.setText(rounds[3][1].split("/")[0].trim());
                }
                grade_textfield19.setText("");
            } else {
                if (rounds[3][1].split("/")[0].trim().equals("NA")) {
                    score_textfield19.setText("");
                } else {
                    score_textfield19.setText(rounds[3][1].split("/")[0].trim());
                }
                if (rounds[3][1].split("/")[1].trim().equals("NA")) {
                    grade_textfield19.setText("");
                } else {
                    grade_textfield19.setText(rounds[3][1].split("/")[1].trim());
                }

            }

            if (rounds[3][2].equals("/")) {
                score_textfield23.setText("");
                grade_textfield23.setText("");

            } else if (rounds[3][2].endsWith("/")) {
                if (rounds[3][2].split("/")[0].trim().equals("NA")) {
                    score_textfield23.setText("");
                } else {
                    score_textfield23.setText(rounds[3][2].split("/")[0].trim());
                }
                grade_textfield23.setText("");
            } else {
                if (rounds[3][2].split("/")[0].trim().equals("NA")) {
                    score_textfield23.setText("");
                } else {
                    score_textfield23.setText(rounds[3][2].split("/")[0].trim());
                }
                if (rounds[3][2].split("/")[1].trim().equals("NA")) {
                    grade_textfield23.setText("");
                } else {
                    grade_textfield23.setText(rounds[3][2].split("/")[1].trim());
                }

            }

        }
        if (result.equals("")) {
            result1_textfieldC.setText("");
            result2_textfieldC.setText("");
            result3_textfieldC.setText("");
        } else {
            if (rounds[4][0].trim().equals("NA")) {
                result1_textfieldC.setText("");
            } else {
                result1_textfieldC.setText(rounds[4][0].trim());
            }

            if (rounds[4][1].trim().equals("NA")) {
                result2_textfieldC.setText("");
            } else {
                result2_textfieldC.setText(rounds[4][1].trim());
            }

            if (rounds[4][2].trim().equals("NA")) {
                result3_textfieldC.setText("");
            } else {
                result3_textfieldC.setText(rounds[4][2].trim());
            }

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
            JOptionPane.showMessageDialog(null, e);
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
        trade = new javax.swing.JLabel();
        unit_label = new javax.swing.JLabel();
        unit = new javax.swing.JLabel();
        run300_label = new javax.swing.JLabel();
        run200_label = new javax.swing.JLabel();
        run400_label = new javax.swing.JLabel();
        midA1 = new javax.swing.JSeparator();
        date_label = new javax.swing.JLabel();
        wpn_label = new javax.swing.JLabel();
        test_date_combobox = new javax.swing.JComboBox<>();
        test_month_combobox = new javax.swing.JComboBox<>();
        test_year_combobox = new javax.swing.JComboBox<>();
        wpn_textfield = new javax.swing.JTextField();
        wpn_underline = new javax.swing.JSeparator();
        result1_label = new javax.swing.JLabel();
        run100_label = new javax.swing.JLabel();
        result_textfield = new javax.swing.JTextField();
        underline6 = new javax.swing.JSeparator();
        score_textfield = new javax.swing.JTextField();
        score_underline0 = new javax.swing.JSeparator();
        grade_textfield = new javax.swing.JTextField();
        grade_underline0 = new javax.swing.JSeparator();
        score_textfield1 = new javax.swing.JTextField();
        score_underline1 = new javax.swing.JSeparator();
        grade_textfield1 = new javax.swing.JTextField();
        grade_underline1 = new javax.swing.JSeparator();
        score_textfield2 = new javax.swing.JTextField();
        score_underline2 = new javax.swing.JSeparator();
        grade_textfield2 = new javax.swing.JTextField();
        grade_underline2 = new javax.swing.JSeparator();
        score_textfield3 = new javax.swing.JTextField();
        score_underline3 = new javax.swing.JSeparator();
        grade_textfield3 = new javax.swing.JTextField();
        grade_underline3 = new javax.swing.JSeparator();
        result_textfield1 = new javax.swing.JTextField();
        underline7 = new javax.swing.JSeparator();
        score_textfield4 = new javax.swing.JTextField();
        score_underline4 = new javax.swing.JSeparator();
        grade_textfield4 = new javax.swing.JTextField();
        grade_underline4 = new javax.swing.JSeparator();
        score_textfield5 = new javax.swing.JTextField();
        score_underline5 = new javax.swing.JSeparator();
        grade_textfield5 = new javax.swing.JTextField();
        grade_underline5 = new javax.swing.JSeparator();
        score_textfield6 = new javax.swing.JTextField();
        score_underline6 = new javax.swing.JSeparator();
        grade_textfield6 = new javax.swing.JTextField();
        grade_underline6 = new javax.swing.JSeparator();
        score_textfield7 = new javax.swing.JTextField();
        score_underline7 = new javax.swing.JSeparator();
        grade_textfield7 = new javax.swing.JTextField();
        grade_underline7 = new javax.swing.JSeparator();
        result_textfield2 = new javax.swing.JTextField();
        underline8 = new javax.swing.JSeparator();
        score_textfield8 = new javax.swing.JTextField();
        score_underline8 = new javax.swing.JSeparator();
        grade_textfield8 = new javax.swing.JTextField();
        grade_underline8 = new javax.swing.JSeparator();
        score_textfield9 = new javax.swing.JTextField();
        score_underline9 = new javax.swing.JSeparator();
        grade_textfield9 = new javax.swing.JTextField();
        grade_underline9 = new javax.swing.JSeparator();
        score_textfield10 = new javax.swing.JTextField();
        score_underline10 = new javax.swing.JSeparator();
        grade_textfield10 = new javax.swing.JTextField();
        grade_underline10 = new javax.swing.JSeparator();
        score_textfield11 = new javax.swing.JTextField();
        score_underline11 = new javax.swing.JSeparator();
        grade_textfield11 = new javax.swing.JTextField();
        grade_underline11 = new javax.swing.JSeparator();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        panelB = new javax.swing.JPanel();
        ScrollPaneB = new javax.swing.JScrollPane();
        TableG_firing = new javax.swing.JTable();
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
        run300_labelC = new javax.swing.JLabel();
        run200_labelC = new javax.swing.JLabel();
        run400_labelC = new javax.swing.JLabel();
        midC1 = new javax.swing.JSeparator();
        date_labelC = new javax.swing.JLabel();
        wpn_labelC = new javax.swing.JLabel();
        test_date_comboboxC = new javax.swing.JComboBox<>();
        test_month_comboboxC = new javax.swing.JComboBox<>();
        test_year_comboboxC = new javax.swing.JComboBox<>();
        wpn_textfieldC = new javax.swing.JTextField();
        wpn_underline1 = new javax.swing.JSeparator();
        result1_labelC = new javax.swing.JLabel();
        run100_labelC = new javax.swing.JLabel();
        result1_textfieldC = new javax.swing.JTextField();
        underline9 = new javax.swing.JSeparator();
        score_textfield12 = new javax.swing.JTextField();
        score_underline12 = new javax.swing.JSeparator();
        grade_textfield12 = new javax.swing.JTextField();
        grade_underline12 = new javax.swing.JSeparator();
        score_textfield13 = new javax.swing.JTextField();
        score_underline13 = new javax.swing.JSeparator();
        grade_textfield13 = new javax.swing.JTextField();
        grade_underline13 = new javax.swing.JSeparator();
        score_textfield14 = new javax.swing.JTextField();
        score_underline14 = new javax.swing.JSeparator();
        grade_textfield14 = new javax.swing.JTextField();
        grade_underline14 = new javax.swing.JSeparator();
        score_textfield15 = new javax.swing.JTextField();
        score_underline15 = new javax.swing.JSeparator();
        grade_textfield15 = new javax.swing.JTextField();
        grade_underline15 = new javax.swing.JSeparator();
        result2_textfieldC = new javax.swing.JTextField();
        underline10 = new javax.swing.JSeparator();
        score_textfield16 = new javax.swing.JTextField();
        score_underline16 = new javax.swing.JSeparator();
        grade_textfield16 = new javax.swing.JTextField();
        grade_underline16 = new javax.swing.JSeparator();
        score_textfield17 = new javax.swing.JTextField();
        score_underline17 = new javax.swing.JSeparator();
        grade_textfield17 = new javax.swing.JTextField();
        grade_underline17 = new javax.swing.JSeparator();
        score_textfield18 = new javax.swing.JTextField();
        score_underline18 = new javax.swing.JSeparator();
        grade_textfield18 = new javax.swing.JTextField();
        grade_underline18 = new javax.swing.JSeparator();
        score_textfield19 = new javax.swing.JTextField();
        score_underline19 = new javax.swing.JSeparator();
        grade_textfield19 = new javax.swing.JTextField();
        grade_underline19 = new javax.swing.JSeparator();
        result3_textfieldC = new javax.swing.JTextField();
        underline11 = new javax.swing.JSeparator();
        score_textfield20 = new javax.swing.JTextField();
        score_underline20 = new javax.swing.JSeparator();
        grade_textfield20 = new javax.swing.JTextField();
        grade_underline20 = new javax.swing.JSeparator();
        score_textfield21 = new javax.swing.JTextField();
        score_underline21 = new javax.swing.JSeparator();
        grade_textfield21 = new javax.swing.JTextField();
        grade_underline21 = new javax.swing.JSeparator();
        score_textfield22 = new javax.swing.JTextField();
        score_underline22 = new javax.swing.JSeparator();
        grade_textfield22 = new javax.swing.JTextField();
        grade_underline22 = new javax.swing.JSeparator();
        score_textfield23 = new javax.swing.JTextField();
        score_underline23 = new javax.swing.JSeparator();
        grade_textfield23 = new javax.swing.JTextField();
        grade_underline23 = new javax.swing.JSeparator();
        jLabel9 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();
        jLabel14 = new javax.swing.JLabel();
        jLabel15 = new javax.swing.JLabel();
        jLabel16 = new javax.swing.JLabel();
        insert_buttonA = new javax.swing.JPanel();
        insert_label = new javax.swing.JLabel();
        print_buttonA = new javax.swing.JPanel();
        print_label = new javax.swing.JLabel();
        refresh_buttonB = new javax.swing.JPanel();
        refresh_labelB = new javax.swing.JLabel();
        print_buttonB = new javax.swing.JPanel();
        print_labelB = new javax.swing.JLabel();
        fullscreen_buttonB = new javax.swing.JPanel();
        fullscreen_labelB = new javax.swing.JLabel();
        update_buttonC = new javax.swing.JPanel();
        update_labelC = new javax.swing.JLabel();
        edit_buttonB = new javax.swing.JPanel();
        edit_labelB = new javax.swing.JLabel();
        fullscreen_panelB = new javax.swing.JPanel();
        fullscreen_ScrollPaneB = new javax.swing.JScrollPane();
        fullscreen_tableB = new javax.swing.JTable();
        exit_fullscreen_buttonB = new javax.swing.JPanel();
        exit_fullscreen_labelB = new javax.swing.JLabel();

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
        menuA_label.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                menuA_labelMouseClicked(evt);
            }
        });

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

        title_label.setFont(new java.awt.Font("Montserrat", 0, 28)); // NOI18N
        title_label.setForeground(new java.awt.Color(255, 255, 255));
        title_label.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        title_label.setText("Firing");
        side_pane.add(title_label, new org.netbeans.lib.awtextra.AbsoluteConstraints(120, 130, 120, 40));

        title_icon.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        title_icon.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/PNGS/firing_white.png"))); // NOI18N
        side_pane.add(title_icon, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 120, 70, 60));

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
                .addContainerGap(65, Short.MAX_VALUE))
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
        panelA.add(panelA_bottom_line, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 930, 910, -1));

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
        panelA.add(company_label, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 260, -1, 50));

        company.setFont(new java.awt.Font("Roboto Bk", 0, 24)); // NOI18N
        company.setForeground(new java.awt.Color(54, 33, 89));
        company.setText("> XXXXXXX");
        panelA.add(company, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 300, -1, 30));

        trade_label.setBackground(new java.awt.Color(255, 255, 255));
        trade_label.setFont(new java.awt.Font("Montserrat", 0, 28)); // NOI18N
        trade_label.setForeground(new java.awt.Color(51, 51, 51));
        trade_label.setText("Trade");
        panelA.add(trade_label, new org.netbeans.lib.awtextra.AbsoluteConstraints(620, 170, -1, 50));

        trade.setFont(new java.awt.Font("Roboto Bk", 0, 24)); // NOI18N
        trade.setForeground(new java.awt.Color(54, 33, 89));
        trade.setText("> XXXXXXX");
        panelA.add(trade, new org.netbeans.lib.awtextra.AbsoluteConstraints(620, 210, -1, 30));

        unit_label.setBackground(new java.awt.Color(255, 255, 255));
        unit_label.setFont(new java.awt.Font("Montserrat", 0, 28)); // NOI18N
        unit_label.setForeground(new java.awt.Color(51, 51, 51));
        unit_label.setText("Unit");
        panelA.add(unit_label, new org.netbeans.lib.awtextra.AbsoluteConstraints(350, 260, -1, 50));

        unit.setFont(new java.awt.Font("Roboto Bk", 0, 24)); // NOI18N
        unit.setForeground(new java.awt.Color(54, 33, 89));
        unit.setText("> XXXXXXX");
        panelA.add(unit, new org.netbeans.lib.awtextra.AbsoluteConstraints(350, 300, -1, 30));

        run300_label.setBackground(new java.awt.Color(255, 255, 255));
        run300_label.setFont(new java.awt.Font("Montserrat", 0, 24)); // NOI18N
        run300_label.setForeground(new java.awt.Color(66, 50, 77));
        run300_label.setText("300 m");
        panelA.add(run300_label, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 640, -1, 40));

        run200_label.setBackground(new java.awt.Color(255, 255, 255));
        run200_label.setFont(new java.awt.Font("Montserrat", 0, 24)); // NOI18N
        run200_label.setForeground(new java.awt.Color(66, 50, 77));
        run200_label.setText("200 m");
        panelA.add(run200_label, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 700, -1, 40));

        run400_label.setBackground(new java.awt.Color(255, 255, 255));
        run400_label.setFont(new java.awt.Font("Montserrat", 0, 24)); // NOI18N
        run400_label.setForeground(new java.awt.Color(66, 50, 77));
        run400_label.setText("400 m");
        panelA.add(run400_label, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 550, -1, 40));

        midA1.setForeground(new java.awt.Color(204, 204, 255));
        panelA.add(midA1, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 500, 570, 10));

        date_label.setBackground(new java.awt.Color(255, 255, 255));
        date_label.setFont(new java.awt.Font("Montserrat", 0, 28)); // NOI18N
        date_label.setForeground(new java.awt.Color(51, 51, 51));
        date_label.setText("Date of Test");
        panelA.add(date_label, new org.netbeans.lib.awtextra.AbsoluteConstraints(350, 350, -1, 40));

        wpn_label.setBackground(new java.awt.Color(255, 255, 255));
        wpn_label.setFont(new java.awt.Font("Montserrat", 0, 28)); // NOI18N
        wpn_label.setForeground(new java.awt.Color(51, 51, 51));
        wpn_label.setText("Wpn");
        panelA.add(wpn_label, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 350, -1, 40));

        test_date_combobox.setFont(new java.awt.Font("Roboto", 0, 18)); // NOI18N
        test_date_combobox.setForeground(new java.awt.Color(44, 62, 80));
        test_date_combobox.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "DD", "01", "02", "03", "04", "05", "06", "07", "08", "09", "10", "11", "12", "13", "14", "15", "16", "17", "18", "19", "20", "21", "22", "23", "24", "25", "26", "27", "28", "29", "30", "31" }));
        test_date_combobox.setBorder(null);
        panelA.add(test_date_combobox, new org.netbeans.lib.awtextra.AbsoluteConstraints(350, 390, 70, 40));

        test_month_combobox.setFont(new java.awt.Font("Roboto", 0, 18)); // NOI18N
        test_month_combobox.setForeground(new java.awt.Color(44, 62, 80));
        test_month_combobox.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "MM", "01", "02", "03", "04", "05", "06", "07", "08", "09", "10", "11", "12" }));
        test_month_combobox.setBorder(null);
        panelA.add(test_month_combobox, new org.netbeans.lib.awtextra.AbsoluteConstraints(430, 390, 70, 40));

        test_year_combobox.setFont(new java.awt.Font("Roboto", 0, 18)); // NOI18N
        test_year_combobox.setForeground(new java.awt.Color(44, 62, 80));
        test_year_combobox.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "YYYY", "1950", "1951", "1952", "1953", "1954", "1955", "1956", "1957", "1958", "1959", "1960", "1961", "1962", "1963", "1964", "1965", "1966", "1967", "1968", "1969", "1970", "1971", "1972", "1973", "1974", "1975", "1976", "1977", "1978", "1979", "1980", "1981", "1982", "1983", "1984", "1985", "1986", "1987", "1988", "1989", "1990", "1991", "1992", "1993", "1994", "1995", "1996", "1997", "1998", "1999", "2000", "2001", "2002", "2003", "2004", "2005", "2006", "2007", "2008", "2009", "2010", "2011", "2012", "2013", "2014", "2015", "2016", "2017", "2018", "2019", "2020", "2021", "2022", "2023", "2024", "2025", "2026", "2027", "2028", "2029", "2030", "2031", "2032", "2033", "2034", "2035", "2036", "2037", "2038", "2039", "2040", "2041", "2042", "2043", "2044", "2045", "2046", "2047", "2048", "2049", "2050", "2051", "2052", "2053", "2054", "2055", "2056", "2057", "2058", "2059", "2060", "2061", "2062", "2063", "2064", "2065", "2066", "2067", "2068", "2069", "2070", "2071", "2072", "2073", "2074", "2075", "2076", "2077", "2078", "2079", "2080", "2081", "2082", "2083", "2084", "2085", "2086", "2087", "2088", "2089", "2090", "2091", "2092", "2093", "2094", "2095", "2096", "2097", "2098", "2099", "2100", " " }));
        test_year_combobox.setBorder(null);
        panelA.add(test_year_combobox, new org.netbeans.lib.awtextra.AbsoluteConstraints(510, 390, 70, 40));

        wpn_textfield.setFont(new java.awt.Font("Roboto", 0, 24)); // NOI18N
        wpn_textfield.setForeground(new java.awt.Color(54, 33, 89));
        wpn_textfield.setBorder(null);
        wpn_textfield.setMargin(new java.awt.Insets(2, 4, 2, 2));
        wpn_textfield.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                wpn_textfieldActionPerformed(evt);
            }
        });
        panelA.add(wpn_textfield, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 390, 210, 40));

        wpn_underline.setForeground(new java.awt.Color(54, 33, 89));
        panelA.add(wpn_underline, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 430, 210, 20));

        result1_label.setBackground(new java.awt.Color(255, 255, 255));
        result1_label.setFont(new java.awt.Font("Montserrat", 0, 24)); // NOI18N
        result1_label.setForeground(new java.awt.Color(66, 50, 77));
        result1_label.setText("Result");
        panelA.add(result1_label, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 820, -1, 40));

        run100_label.setBackground(new java.awt.Color(255, 255, 255));
        run100_label.setFont(new java.awt.Font("Montserrat", 0, 24)); // NOI18N
        run100_label.setForeground(new java.awt.Color(66, 50, 77));
        run100_label.setText("100 m");
        panelA.add(run100_label, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 760, -1, 40));

        result_textfield.setFont(new java.awt.Font("Roboto", 0, 24)); // NOI18N
        result_textfield.setForeground(new java.awt.Color(51, 51, 51));
        result_textfield.setBorder(null);
        result_textfield.setMargin(new java.awt.Insets(2, 4, 2, 2));
        result_textfield.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                result_textfieldActionPerformed(evt);
            }
        });
        panelA.add(result_textfield, new org.netbeans.lib.awtextra.AbsoluteConstraints(210, 820, 140, 40));

        underline6.setForeground(new java.awt.Color(54, 33, 89));
        panelA.add(underline6, new org.netbeans.lib.awtextra.AbsoluteConstraints(210, 860, 140, 20));

        score_textfield.setFont(new java.awt.Font("Roboto", 0, 24)); // NOI18N
        score_textfield.setForeground(new java.awt.Color(51, 51, 51));
        score_textfield.setBorder(null);
        score_textfield.setMargin(new java.awt.Insets(2, 4, 2, 2));
        score_textfield.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                score_textfieldFocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                score_textfieldFocusLost(evt);
            }
        });
        score_textfield.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                score_textfieldMouseClicked(evt);
            }
        });
        score_textfield.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                score_textfieldActionPerformed(evt);
            }
        });
        panelA.add(score_textfield, new org.netbeans.lib.awtextra.AbsoluteConstraints(210, 550, 80, 40));

        score_underline0.setForeground(new java.awt.Color(54, 33, 89));
        panelA.add(score_underline0, new org.netbeans.lib.awtextra.AbsoluteConstraints(210, 590, 80, 10));

        grade_textfield.setFont(new java.awt.Font("Roboto", 0, 24)); // NOI18N
        grade_textfield.setForeground(new java.awt.Color(51, 51, 51));
        grade_textfield.setBorder(null);
        grade_textfield.setMargin(new java.awt.Insets(2, 4, 2, 2));
        grade_textfield.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                grade_textfieldFocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                grade_textfieldFocusLost(evt);
            }
        });
        grade_textfield.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                grade_textfieldMouseClicked(evt);
            }
        });
        grade_textfield.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                grade_textfieldActionPerformed(evt);
            }
        });
        panelA.add(grade_textfield, new org.netbeans.lib.awtextra.AbsoluteConstraints(300, 550, 50, 40));

        grade_underline0.setForeground(new java.awt.Color(54, 33, 89));
        panelA.add(grade_underline0, new org.netbeans.lib.awtextra.AbsoluteConstraints(300, 590, 50, 10));

        score_textfield1.setFont(new java.awt.Font("Roboto", 0, 24)); // NOI18N
        score_textfield1.setForeground(new java.awt.Color(51, 51, 51));
        score_textfield1.setBorder(null);
        score_textfield1.setMargin(new java.awt.Insets(2, 4, 2, 2));
        score_textfield1.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                score_textfield1FocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                score_textfield1FocusLost(evt);
            }
        });
        score_textfield1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                score_textfield1MouseClicked(evt);
            }
        });
        score_textfield1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                score_textfield1ActionPerformed(evt);
            }
        });
        panelA.add(score_textfield1, new org.netbeans.lib.awtextra.AbsoluteConstraints(210, 640, 80, 40));

        score_underline1.setForeground(new java.awt.Color(54, 33, 89));
        panelA.add(score_underline1, new org.netbeans.lib.awtextra.AbsoluteConstraints(210, 680, 80, 10));

        grade_textfield1.setFont(new java.awt.Font("Roboto", 0, 24)); // NOI18N
        grade_textfield1.setForeground(new java.awt.Color(51, 51, 51));
        grade_textfield1.setBorder(null);
        grade_textfield1.setMargin(new java.awt.Insets(2, 4, 2, 2));
        grade_textfield1.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                grade_textfield1FocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                grade_textfield1FocusLost(evt);
            }
        });
        grade_textfield1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                grade_textfield1MouseClicked(evt);
            }
        });
        grade_textfield1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                grade_textfield1ActionPerformed(evt);
            }
        });
        panelA.add(grade_textfield1, new org.netbeans.lib.awtextra.AbsoluteConstraints(300, 640, 50, 40));

        grade_underline1.setForeground(new java.awt.Color(54, 33, 89));
        panelA.add(grade_underline1, new org.netbeans.lib.awtextra.AbsoluteConstraints(300, 680, 50, 10));

        score_textfield2.setFont(new java.awt.Font("Roboto", 0, 24)); // NOI18N
        score_textfield2.setForeground(new java.awt.Color(51, 51, 51));
        score_textfield2.setBorder(null);
        score_textfield2.setMargin(new java.awt.Insets(2, 4, 2, 2));
        score_textfield2.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                score_textfield2FocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                score_textfield2FocusLost(evt);
            }
        });
        score_textfield2.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                score_textfield2MouseClicked(evt);
            }
        });
        score_textfield2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                score_textfield2ActionPerformed(evt);
            }
        });
        panelA.add(score_textfield2, new org.netbeans.lib.awtextra.AbsoluteConstraints(210, 700, 80, 40));

        score_underline2.setForeground(new java.awt.Color(54, 33, 89));
        panelA.add(score_underline2, new org.netbeans.lib.awtextra.AbsoluteConstraints(210, 740, 80, 10));

        grade_textfield2.setFont(new java.awt.Font("Roboto", 0, 24)); // NOI18N
        grade_textfield2.setForeground(new java.awt.Color(51, 51, 51));
        grade_textfield2.setBorder(null);
        grade_textfield2.setMargin(new java.awt.Insets(2, 4, 2, 2));
        grade_textfield2.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                grade_textfield2FocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                grade_textfield2FocusLost(evt);
            }
        });
        grade_textfield2.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                grade_textfield2MouseClicked(evt);
            }
        });
        grade_textfield2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                grade_textfield2ActionPerformed(evt);
            }
        });
        panelA.add(grade_textfield2, new org.netbeans.lib.awtextra.AbsoluteConstraints(300, 700, 50, 40));

        grade_underline2.setForeground(new java.awt.Color(54, 33, 89));
        panelA.add(grade_underline2, new org.netbeans.lib.awtextra.AbsoluteConstraints(300, 740, 50, 10));

        score_textfield3.setFont(new java.awt.Font("Roboto", 0, 24)); // NOI18N
        score_textfield3.setForeground(new java.awt.Color(51, 51, 51));
        score_textfield3.setBorder(null);
        score_textfield3.setMargin(new java.awt.Insets(2, 4, 2, 2));
        score_textfield3.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                score_textfield3FocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                score_textfield3FocusLost(evt);
            }
        });
        score_textfield3.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                score_textfield3MouseClicked(evt);
            }
        });
        score_textfield3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                score_textfield3ActionPerformed(evt);
            }
        });
        panelA.add(score_textfield3, new org.netbeans.lib.awtextra.AbsoluteConstraints(210, 760, 80, 40));

        score_underline3.setForeground(new java.awt.Color(54, 33, 89));
        panelA.add(score_underline3, new org.netbeans.lib.awtextra.AbsoluteConstraints(210, 800, 80, 10));

        grade_textfield3.setFont(new java.awt.Font("Roboto", 0, 24)); // NOI18N
        grade_textfield3.setForeground(new java.awt.Color(51, 51, 51));
        grade_textfield3.setBorder(null);
        grade_textfield3.setMargin(new java.awt.Insets(2, 4, 2, 2));
        grade_textfield3.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                grade_textfield3FocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                grade_textfield3FocusLost(evt);
            }
        });
        grade_textfield3.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                grade_textfield3MouseClicked(evt);
            }
        });
        grade_textfield3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                grade_textfield3ActionPerformed(evt);
            }
        });
        panelA.add(grade_textfield3, new org.netbeans.lib.awtextra.AbsoluteConstraints(300, 760, 50, 40));

        grade_underline3.setForeground(new java.awt.Color(54, 33, 89));
        panelA.add(grade_underline3, new org.netbeans.lib.awtextra.AbsoluteConstraints(300, 800, 50, 10));

        result_textfield1.setFont(new java.awt.Font("Roboto", 0, 24)); // NOI18N
        result_textfield1.setForeground(new java.awt.Color(51, 51, 51));
        result_textfield1.setBorder(null);
        result_textfield1.setMargin(new java.awt.Insets(2, 4, 2, 2));
        result_textfield1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                result_textfield1ActionPerformed(evt);
            }
        });
        panelA.add(result_textfield1, new org.netbeans.lib.awtextra.AbsoluteConstraints(420, 820, 140, 40));

        underline7.setForeground(new java.awt.Color(54, 33, 89));
        panelA.add(underline7, new org.netbeans.lib.awtextra.AbsoluteConstraints(420, 860, 140, 20));

        score_textfield4.setFont(new java.awt.Font("Roboto", 0, 24)); // NOI18N
        score_textfield4.setForeground(new java.awt.Color(51, 51, 51));
        score_textfield4.setBorder(null);
        score_textfield4.setMargin(new java.awt.Insets(2, 4, 2, 2));
        score_textfield4.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                score_textfield4FocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                score_textfield4FocusLost(evt);
            }
        });
        score_textfield4.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                score_textfield4MouseClicked(evt);
            }
        });
        score_textfield4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                score_textfield4ActionPerformed(evt);
            }
        });
        panelA.add(score_textfield4, new org.netbeans.lib.awtextra.AbsoluteConstraints(420, 550, 80, 40));

        score_underline4.setForeground(new java.awt.Color(54, 33, 89));
        panelA.add(score_underline4, new org.netbeans.lib.awtextra.AbsoluteConstraints(420, 590, 80, 10));

        grade_textfield4.setFont(new java.awt.Font("Roboto", 0, 24)); // NOI18N
        grade_textfield4.setForeground(new java.awt.Color(51, 51, 51));
        grade_textfield4.setBorder(null);
        grade_textfield4.setMargin(new java.awt.Insets(2, 4, 2, 2));
        grade_textfield4.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                grade_textfield4FocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                grade_textfield4FocusLost(evt);
            }
        });
        grade_textfield4.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                grade_textfield4MouseClicked(evt);
            }
        });
        grade_textfield4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                grade_textfield4ActionPerformed(evt);
            }
        });
        panelA.add(grade_textfield4, new org.netbeans.lib.awtextra.AbsoluteConstraints(510, 550, 50, 40));

        grade_underline4.setForeground(new java.awt.Color(54, 33, 89));
        panelA.add(grade_underline4, new org.netbeans.lib.awtextra.AbsoluteConstraints(510, 590, 50, 10));

        score_textfield5.setFont(new java.awt.Font("Roboto", 0, 24)); // NOI18N
        score_textfield5.setForeground(new java.awt.Color(51, 51, 51));
        score_textfield5.setBorder(null);
        score_textfield5.setMargin(new java.awt.Insets(2, 4, 2, 2));
        score_textfield5.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                score_textfield5FocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                score_textfield5FocusLost(evt);
            }
        });
        score_textfield5.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                score_textfield5MouseClicked(evt);
            }
        });
        score_textfield5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                score_textfield5ActionPerformed(evt);
            }
        });
        panelA.add(score_textfield5, new org.netbeans.lib.awtextra.AbsoluteConstraints(420, 640, 80, 40));

        score_underline5.setForeground(new java.awt.Color(54, 33, 89));
        panelA.add(score_underline5, new org.netbeans.lib.awtextra.AbsoluteConstraints(420, 680, 80, 10));

        grade_textfield5.setFont(new java.awt.Font("Roboto", 0, 24)); // NOI18N
        grade_textfield5.setForeground(new java.awt.Color(51, 51, 51));
        grade_textfield5.setBorder(null);
        grade_textfield5.setMargin(new java.awt.Insets(2, 4, 2, 2));
        grade_textfield5.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                grade_textfield5FocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                grade_textfield5FocusLost(evt);
            }
        });
        grade_textfield5.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                grade_textfield5MouseClicked(evt);
            }
        });
        grade_textfield5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                grade_textfield5ActionPerformed(evt);
            }
        });
        panelA.add(grade_textfield5, new org.netbeans.lib.awtextra.AbsoluteConstraints(510, 640, 50, 40));

        grade_underline5.setForeground(new java.awt.Color(54, 33, 89));
        panelA.add(grade_underline5, new org.netbeans.lib.awtextra.AbsoluteConstraints(510, 680, 50, 10));

        score_textfield6.setFont(new java.awt.Font("Roboto", 0, 24)); // NOI18N
        score_textfield6.setForeground(new java.awt.Color(51, 51, 51));
        score_textfield6.setBorder(null);
        score_textfield6.setMargin(new java.awt.Insets(2, 4, 2, 2));
        score_textfield6.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                score_textfield6FocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                score_textfield6FocusLost(evt);
            }
        });
        score_textfield6.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                score_textfield6MouseClicked(evt);
            }
        });
        score_textfield6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                score_textfield6ActionPerformed(evt);
            }
        });
        panelA.add(score_textfield6, new org.netbeans.lib.awtextra.AbsoluteConstraints(420, 700, 80, 40));

        score_underline6.setForeground(new java.awt.Color(54, 33, 89));
        panelA.add(score_underline6, new org.netbeans.lib.awtextra.AbsoluteConstraints(420, 740, 80, 10));

        grade_textfield6.setFont(new java.awt.Font("Roboto", 0, 24)); // NOI18N
        grade_textfield6.setForeground(new java.awt.Color(51, 51, 51));
        grade_textfield6.setBorder(null);
        grade_textfield6.setMargin(new java.awt.Insets(2, 4, 2, 2));
        grade_textfield6.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                grade_textfield6FocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                grade_textfield6FocusLost(evt);
            }
        });
        grade_textfield6.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                grade_textfield6MouseClicked(evt);
            }
        });
        grade_textfield6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                grade_textfield6ActionPerformed(evt);
            }
        });
        panelA.add(grade_textfield6, new org.netbeans.lib.awtextra.AbsoluteConstraints(510, 700, 50, 40));

        grade_underline6.setForeground(new java.awt.Color(54, 33, 89));
        panelA.add(grade_underline6, new org.netbeans.lib.awtextra.AbsoluteConstraints(510, 740, 50, 10));

        score_textfield7.setFont(new java.awt.Font("Roboto", 0, 24)); // NOI18N
        score_textfield7.setForeground(new java.awt.Color(51, 51, 51));
        score_textfield7.setBorder(null);
        score_textfield7.setMargin(new java.awt.Insets(2, 4, 2, 2));
        score_textfield7.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                score_textfield7FocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                score_textfield7FocusLost(evt);
            }
        });
        score_textfield7.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                score_textfield7MouseClicked(evt);
            }
        });
        score_textfield7.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                score_textfield7ActionPerformed(evt);
            }
        });
        panelA.add(score_textfield7, new org.netbeans.lib.awtextra.AbsoluteConstraints(420, 760, 80, 40));

        score_underline7.setForeground(new java.awt.Color(54, 33, 89));
        panelA.add(score_underline7, new org.netbeans.lib.awtextra.AbsoluteConstraints(420, 800, 80, 10));

        grade_textfield7.setFont(new java.awt.Font("Roboto", 0, 24)); // NOI18N
        grade_textfield7.setForeground(new java.awt.Color(51, 51, 51));
        grade_textfield7.setBorder(null);
        grade_textfield7.setMargin(new java.awt.Insets(2, 4, 2, 2));
        grade_textfield7.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                grade_textfield7FocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                grade_textfield7FocusLost(evt);
            }
        });
        grade_textfield7.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                grade_textfield7MouseClicked(evt);
            }
        });
        grade_textfield7.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                grade_textfield7ActionPerformed(evt);
            }
        });
        panelA.add(grade_textfield7, new org.netbeans.lib.awtextra.AbsoluteConstraints(510, 760, 50, 40));

        grade_underline7.setForeground(new java.awt.Color(54, 33, 89));
        panelA.add(grade_underline7, new org.netbeans.lib.awtextra.AbsoluteConstraints(510, 800, 50, 10));

        result_textfield2.setFont(new java.awt.Font("Roboto", 0, 24)); // NOI18N
        result_textfield2.setForeground(new java.awt.Color(51, 51, 51));
        result_textfield2.setBorder(null);
        result_textfield2.setMargin(new java.awt.Insets(2, 4, 2, 2));
        result_textfield2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                result_textfield2ActionPerformed(evt);
            }
        });
        panelA.add(result_textfield2, new org.netbeans.lib.awtextra.AbsoluteConstraints(630, 820, 140, 40));

        underline8.setForeground(new java.awt.Color(54, 33, 89));
        panelA.add(underline8, new org.netbeans.lib.awtextra.AbsoluteConstraints(630, 860, 140, 20));

        score_textfield8.setFont(new java.awt.Font("Roboto", 0, 24)); // NOI18N
        score_textfield8.setForeground(new java.awt.Color(51, 51, 51));
        score_textfield8.setBorder(null);
        score_textfield8.setMargin(new java.awt.Insets(2, 4, 2, 2));
        score_textfield8.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                score_textfield8FocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                score_textfield8FocusLost(evt);
            }
        });
        score_textfield8.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                score_textfield8MouseClicked(evt);
            }
        });
        score_textfield8.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                score_textfield8ActionPerformed(evt);
            }
        });
        panelA.add(score_textfield8, new org.netbeans.lib.awtextra.AbsoluteConstraints(630, 550, 80, 40));

        score_underline8.setForeground(new java.awt.Color(54, 33, 89));
        panelA.add(score_underline8, new org.netbeans.lib.awtextra.AbsoluteConstraints(630, 590, 80, 10));

        grade_textfield8.setFont(new java.awt.Font("Roboto", 0, 24)); // NOI18N
        grade_textfield8.setForeground(new java.awt.Color(51, 51, 51));
        grade_textfield8.setBorder(null);
        grade_textfield8.setMargin(new java.awt.Insets(2, 4, 2, 2));
        grade_textfield8.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                grade_textfield8FocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                grade_textfield8FocusLost(evt);
            }
        });
        grade_textfield8.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                grade_textfield8MouseClicked(evt);
            }
        });
        grade_textfield8.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                grade_textfield8ActionPerformed(evt);
            }
        });
        panelA.add(grade_textfield8, new org.netbeans.lib.awtextra.AbsoluteConstraints(720, 550, 50, 40));

        grade_underline8.setForeground(new java.awt.Color(54, 33, 89));
        panelA.add(grade_underline8, new org.netbeans.lib.awtextra.AbsoluteConstraints(720, 590, 50, 10));

        score_textfield9.setFont(new java.awt.Font("Roboto", 0, 24)); // NOI18N
        score_textfield9.setForeground(new java.awt.Color(51, 51, 51));
        score_textfield9.setBorder(null);
        score_textfield9.setMargin(new java.awt.Insets(2, 4, 2, 2));
        score_textfield9.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                score_textfield9FocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                score_textfield9FocusLost(evt);
            }
        });
        score_textfield9.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                score_textfield9MouseClicked(evt);
            }
        });
        score_textfield9.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                score_textfield9ActionPerformed(evt);
            }
        });
        panelA.add(score_textfield9, new org.netbeans.lib.awtextra.AbsoluteConstraints(630, 640, 80, 40));

        score_underline9.setForeground(new java.awt.Color(54, 33, 89));
        panelA.add(score_underline9, new org.netbeans.lib.awtextra.AbsoluteConstraints(630, 680, 80, 10));

        grade_textfield9.setFont(new java.awt.Font("Roboto", 0, 24)); // NOI18N
        grade_textfield9.setForeground(new java.awt.Color(51, 51, 51));
        grade_textfield9.setBorder(null);
        grade_textfield9.setMargin(new java.awt.Insets(2, 4, 2, 2));
        grade_textfield9.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                grade_textfield9FocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                grade_textfield9FocusLost(evt);
            }
        });
        grade_textfield9.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                grade_textfield9MouseClicked(evt);
            }
        });
        grade_textfield9.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                grade_textfield9ActionPerformed(evt);
            }
        });
        panelA.add(grade_textfield9, new org.netbeans.lib.awtextra.AbsoluteConstraints(720, 640, 50, 40));

        grade_underline9.setForeground(new java.awt.Color(54, 33, 89));
        panelA.add(grade_underline9, new org.netbeans.lib.awtextra.AbsoluteConstraints(720, 680, 50, 10));

        score_textfield10.setFont(new java.awt.Font("Roboto", 0, 24)); // NOI18N
        score_textfield10.setForeground(new java.awt.Color(51, 51, 51));
        score_textfield10.setBorder(null);
        score_textfield10.setMargin(new java.awt.Insets(2, 4, 2, 2));
        score_textfield10.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                score_textfield10FocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                score_textfield10FocusLost(evt);
            }
        });
        score_textfield10.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                score_textfield10MouseClicked(evt);
            }
        });
        score_textfield10.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                score_textfield10ActionPerformed(evt);
            }
        });
        panelA.add(score_textfield10, new org.netbeans.lib.awtextra.AbsoluteConstraints(630, 700, 80, 40));

        score_underline10.setForeground(new java.awt.Color(54, 33, 89));
        panelA.add(score_underline10, new org.netbeans.lib.awtextra.AbsoluteConstraints(630, 740, 80, 10));

        grade_textfield10.setFont(new java.awt.Font("Roboto", 0, 24)); // NOI18N
        grade_textfield10.setForeground(new java.awt.Color(51, 51, 51));
        grade_textfield10.setBorder(null);
        grade_textfield10.setMargin(new java.awt.Insets(2, 4, 2, 2));
        grade_textfield10.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                grade_textfield10FocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                grade_textfield10FocusLost(evt);
            }
        });
        grade_textfield10.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                grade_textfield10MouseClicked(evt);
            }
        });
        grade_textfield10.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                grade_textfield10ActionPerformed(evt);
            }
        });
        panelA.add(grade_textfield10, new org.netbeans.lib.awtextra.AbsoluteConstraints(720, 700, 50, 40));

        grade_underline10.setForeground(new java.awt.Color(54, 33, 89));
        panelA.add(grade_underline10, new org.netbeans.lib.awtextra.AbsoluteConstraints(720, 740, 50, 10));

        score_textfield11.setFont(new java.awt.Font("Roboto", 0, 24)); // NOI18N
        score_textfield11.setForeground(new java.awt.Color(51, 51, 51));
        score_textfield11.setBorder(null);
        score_textfield11.setMargin(new java.awt.Insets(2, 4, 2, 2));
        score_textfield11.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                score_textfield11FocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                score_textfield11FocusLost(evt);
            }
        });
        score_textfield11.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                score_textfield11MouseClicked(evt);
            }
        });
        score_textfield11.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                score_textfield11ActionPerformed(evt);
            }
        });
        panelA.add(score_textfield11, new org.netbeans.lib.awtextra.AbsoluteConstraints(630, 760, 80, 40));

        score_underline11.setForeground(new java.awt.Color(54, 33, 89));
        panelA.add(score_underline11, new org.netbeans.lib.awtextra.AbsoluteConstraints(630, 800, 80, 10));

        grade_textfield11.setFont(new java.awt.Font("Roboto", 0, 24)); // NOI18N
        grade_textfield11.setForeground(new java.awt.Color(51, 51, 51));
        grade_textfield11.setBorder(null);
        grade_textfield11.setMargin(new java.awt.Insets(2, 4, 2, 2));
        grade_textfield11.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                grade_textfield11FocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                grade_textfield11FocusLost(evt);
            }
        });
        grade_textfield11.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                grade_textfield11MouseClicked(evt);
            }
        });
        grade_textfield11.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                grade_textfield11ActionPerformed(evt);
            }
        });
        panelA.add(grade_textfield11, new org.netbeans.lib.awtextra.AbsoluteConstraints(720, 760, 50, 40));

        grade_underline11.setForeground(new java.awt.Color(54, 33, 89));
        panelA.add(grade_underline11, new org.netbeans.lib.awtextra.AbsoluteConstraints(720, 800, 50, 10));

        jLabel1.setBackground(new java.awt.Color(102, 102, 102));
        jLabel1.setText("(grade)");
        panelA.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(300, 600, -1, -1));

        jLabel2.setBackground(new java.awt.Color(102, 102, 102));
        jLabel2.setText("(hit/score)");
        panelA.add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(210, 600, -1, -1));

        jLabel3.setBackground(new java.awt.Color(102, 102, 102));
        jLabel3.setText("(grade)");
        panelA.add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(300, 600, -1, -1));

        jLabel4.setBackground(new java.awt.Color(102, 102, 102));
        jLabel4.setText("(hit/score)");
        panelA.add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(210, 600, -1, -1));

        jLabel5.setBackground(new java.awt.Color(102, 102, 102));
        jLabel5.setText("(grade)");
        panelA.add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(510, 600, -1, -1));

        jLabel6.setBackground(new java.awt.Color(102, 102, 102));
        jLabel6.setText("(hit/score)");
        panelA.add(jLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(420, 600, -1, -1));

        jLabel7.setBackground(new java.awt.Color(102, 102, 102));
        jLabel7.setText("(grade)");
        panelA.add(jLabel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(720, 600, -1, -1));

        jLabel8.setBackground(new java.awt.Color(102, 102, 102));
        jLabel8.setText("(hit/score)");
        panelA.add(jLabel8, new org.netbeans.lib.awtextra.AbsoluteConstraints(630, 600, -1, -1));

        ScrollPaneA.setViewportView(panelA);

        bg.add(ScrollPaneA, new org.netbeans.lib.awtextra.AbsoluteConstraints(340, 192, 910, 500));

        panelB.setBackground(new java.awt.Color(255, 255, 255));
        panelB.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        ScrollPaneB.setBackground(new java.awt.Color(255, 255, 255));
        ScrollPaneB.setVerticalScrollBarPolicy(javax.swing.ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);

        TableG_firing.setFont(new java.awt.Font("Roboto", 0, 18)); // NOI18N
        TableG_firing.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "S. No.", "Army No", "Name", "Wpn", "Date of Test", "400m", "300m", "200m", "100m", "Result"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        TableG_firing.setAutoResizeMode(javax.swing.JTable.AUTO_RESIZE_ALL_COLUMNS);
        TableG_firing.setFocusable(false);
        TableG_firing.setGridColor(new java.awt.Color(255, 255, 255));
        TableG_firing.setMaximumSize(null);
        TableG_firing.setRowHeight(30);
        TableG_firing.setSelectionBackground(new java.awt.Color(54, 33, 89));
        TableG_firing.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        ScrollPaneB.setViewportView(TableG_firing);
        if (TableG_firing.getColumnModel().getColumnCount() > 0) {
            TableG_firing.getColumnModel().getColumn(0).setMinWidth(40);
            TableG_firing.getColumnModel().getColumn(0).setPreferredWidth(40);
            TableG_firing.getColumnModel().getColumn(0).setMaxWidth(40);
            TableG_firing.getColumnModel().getColumn(4).setResizable(false);
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
        panelC.add(midC0, new org.netbeans.lib.awtextra.AbsoluteConstraints(270, 200, 390, 10));

        panelC_bottom_line.setForeground(new java.awt.Color(54, 33, 89));
        panelC.add(panelC_bottom_line, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 830, 910, -1));

        run300_labelC.setBackground(new java.awt.Color(255, 255, 255));
        run300_labelC.setFont(new java.awt.Font("Montserrat", 0, 24)); // NOI18N
        run300_labelC.setForeground(new java.awt.Color(66, 50, 77));
        run300_labelC.setText("300 m");
        panelC.add(run300_labelC, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 540, -1, 40));

        run200_labelC.setBackground(new java.awt.Color(255, 255, 255));
        run200_labelC.setFont(new java.awt.Font("Montserrat", 0, 24)); // NOI18N
        run200_labelC.setForeground(new java.awt.Color(66, 50, 77));
        run200_labelC.setText("200 m");
        panelC.add(run200_labelC, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 600, -1, 40));

        run400_labelC.setBackground(new java.awt.Color(255, 255, 255));
        run400_labelC.setFont(new java.awt.Font("Montserrat", 0, 24)); // NOI18N
        run400_labelC.setForeground(new java.awt.Color(66, 50, 77));
        run400_labelC.setText("400 m");
        panelC.add(run400_labelC, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 450, -1, 40));

        midC1.setForeground(new java.awt.Color(204, 204, 255));
        panelC.add(midC1, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 400, 570, 10));

        date_labelC.setBackground(new java.awt.Color(255, 255, 255));
        date_labelC.setFont(new java.awt.Font("Montserrat", 0, 28)); // NOI18N
        date_labelC.setForeground(new java.awt.Color(51, 51, 51));
        date_labelC.setText("Date of Test");
        panelC.add(date_labelC, new org.netbeans.lib.awtextra.AbsoluteConstraints(350, 250, -1, 40));

        wpn_labelC.setBackground(new java.awt.Color(255, 255, 255));
        wpn_labelC.setFont(new java.awt.Font("Montserrat", 0, 28)); // NOI18N
        wpn_labelC.setForeground(new java.awt.Color(51, 51, 51));
        wpn_labelC.setText("Wpn");
        panelC.add(wpn_labelC, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 250, -1, 40));

        test_date_comboboxC.setFont(new java.awt.Font("Roboto", 0, 18)); // NOI18N
        test_date_comboboxC.setForeground(new java.awt.Color(44, 62, 80));
        test_date_comboboxC.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "DD", "01", "02", "03", "04", "05", "06", "07", "08", "09", "10", "11", "12", "13", "14", "15", "16", "17", "18", "19", "20", "21", "22", "23", "24", "25", "26", "27", "28", "29", "30", "31" }));
        test_date_comboboxC.setBorder(null);
        panelC.add(test_date_comboboxC, new org.netbeans.lib.awtextra.AbsoluteConstraints(350, 290, 70, 40));

        test_month_comboboxC.setFont(new java.awt.Font("Roboto", 0, 18)); // NOI18N
        test_month_comboboxC.setForeground(new java.awt.Color(44, 62, 80));
        test_month_comboboxC.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "MM", "01", "02", "03", "04", "05", "06", "07", "08", "09", "10", "11", "12" }));
        test_month_comboboxC.setBorder(null);
        panelC.add(test_month_comboboxC, new org.netbeans.lib.awtextra.AbsoluteConstraints(430, 290, 70, 40));

        test_year_comboboxC.setFont(new java.awt.Font("Roboto", 0, 18)); // NOI18N
        test_year_comboboxC.setForeground(new java.awt.Color(44, 62, 80));
        test_year_comboboxC.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "YYYY", "1950", "1951", "1952", "1953", "1954", "1955", "1956", "1957", "1958", "1959", "1960", "1961", "1962", "1963", "1964", "1965", "1966", "1967", "1968", "1969", "1970", "1971", "1972", "1973", "1974", "1975", "1976", "1977", "1978", "1979", "1980", "1981", "1982", "1983", "1984", "1985", "1986", "1987", "1988", "1989", "1990", "1991", "1992", "1993", "1994", "1995", "1996", "1997", "1998", "1999", "2000", "2001", "2002", "2003", "2004", "2005", "2006", "2007", "2008", "2009", "2010", "2011", "2012", "2013", "2014", "2015", "2016", "2017", "2018", "2019", "2020", "2021", "2022", "2023", "2024", "2025", "2026", "2027", "2028", "2029", "2030", "2031", "2032", "2033", "2034", "2035", "2036", "2037", "2038", "2039", "2040", "2041", "2042", "2043", "2044", "2045", "2046", "2047", "2048", "2049", "2050", "2051", "2052", "2053", "2054", "2055", "2056", "2057", "2058", "2059", "2060", "2061", "2062", "2063", "2064", "2065", "2066", "2067", "2068", "2069", "2070", "2071", "2072", "2073", "2074", "2075", "2076", "2077", "2078", "2079", "2080", "2081", "2082", "2083", "2084", "2085", "2086", "2087", "2088", "2089", "2090", "2091", "2092", "2093", "2094", "2095", "2096", "2097", "2098", "2099", "2100", " " }));
        test_year_comboboxC.setBorder(null);
        panelC.add(test_year_comboboxC, new org.netbeans.lib.awtextra.AbsoluteConstraints(510, 290, 70, 40));

        wpn_textfieldC.setFont(new java.awt.Font("Roboto", 0, 24)); // NOI18N
        wpn_textfieldC.setForeground(new java.awt.Color(54, 33, 89));
        wpn_textfieldC.setBorder(null);
        wpn_textfieldC.setMargin(new java.awt.Insets(2, 4, 2, 2));
        wpn_textfieldC.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                wpn_textfieldCActionPerformed(evt);
            }
        });
        panelC.add(wpn_textfieldC, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 290, 210, 40));

        wpn_underline1.setForeground(new java.awt.Color(54, 33, 89));
        panelC.add(wpn_underline1, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 330, 210, 20));

        result1_labelC.setBackground(new java.awt.Color(255, 255, 255));
        result1_labelC.setFont(new java.awt.Font("Montserrat", 0, 24)); // NOI18N
        result1_labelC.setForeground(new java.awt.Color(66, 50, 77));
        result1_labelC.setText("Result");
        panelC.add(result1_labelC, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 720, -1, 40));

        run100_labelC.setBackground(new java.awt.Color(255, 255, 255));
        run100_labelC.setFont(new java.awt.Font("Montserrat", 0, 24)); // NOI18N
        run100_labelC.setForeground(new java.awt.Color(66, 50, 77));
        run100_labelC.setText("100 m");
        panelC.add(run100_labelC, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 660, -1, 40));

        result1_textfieldC.setFont(new java.awt.Font("Roboto", 0, 24)); // NOI18N
        result1_textfieldC.setForeground(new java.awt.Color(54, 33, 89));
        result1_textfieldC.setBorder(null);
        result1_textfieldC.setMargin(new java.awt.Insets(2, 4, 2, 2));
        result1_textfieldC.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                result1_textfieldCActionPerformed(evt);
            }
        });
        panelC.add(result1_textfieldC, new org.netbeans.lib.awtextra.AbsoluteConstraints(210, 720, 140, 40));

        underline9.setForeground(new java.awt.Color(54, 33, 89));
        panelC.add(underline9, new org.netbeans.lib.awtextra.AbsoluteConstraints(210, 760, 140, 20));

        score_textfield12.setFont(new java.awt.Font("Roboto", 0, 24)); // NOI18N
        score_textfield12.setForeground(new java.awt.Color(102, 102, 102));
        score_textfield12.setBorder(null);
        score_textfield12.setMargin(new java.awt.Insets(2, 4, 2, 2));
        score_textfield12.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                score_textfield12FocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                score_textfield12FocusLost(evt);
            }
        });
        score_textfield12.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                score_textfield12MouseClicked(evt);
            }
        });
        score_textfield12.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                score_textfield12ActionPerformed(evt);
            }
        });
        panelC.add(score_textfield12, new org.netbeans.lib.awtextra.AbsoluteConstraints(210, 450, 80, 40));

        score_underline12.setForeground(new java.awt.Color(54, 33, 89));
        panelC.add(score_underline12, new org.netbeans.lib.awtextra.AbsoluteConstraints(210, 490, 80, 10));

        grade_textfield12.setFont(new java.awt.Font("Roboto", 0, 24)); // NOI18N
        grade_textfield12.setForeground(new java.awt.Color(102, 102, 102));
        grade_textfield12.setBorder(null);
        grade_textfield12.setMargin(new java.awt.Insets(2, 4, 2, 2));
        grade_textfield12.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                grade_textfield12FocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                grade_textfield12FocusLost(evt);
            }
        });
        grade_textfield12.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                grade_textfield12MouseClicked(evt);
            }
        });
        grade_textfield12.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                grade_textfield12ActionPerformed(evt);
            }
        });
        panelC.add(grade_textfield12, new org.netbeans.lib.awtextra.AbsoluteConstraints(300, 450, 50, 40));

        grade_underline12.setForeground(new java.awt.Color(54, 33, 89));
        panelC.add(grade_underline12, new org.netbeans.lib.awtextra.AbsoluteConstraints(300, 490, 50, 10));

        score_textfield13.setFont(new java.awt.Font("Roboto", 0, 24)); // NOI18N
        score_textfield13.setForeground(new java.awt.Color(102, 102, 102));
        score_textfield13.setBorder(null);
        score_textfield13.setMargin(new java.awt.Insets(2, 4, 2, 2));
        score_textfield13.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                score_textfield13FocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                score_textfield13FocusLost(evt);
            }
        });
        score_textfield13.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                score_textfield13MouseClicked(evt);
            }
        });
        score_textfield13.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                score_textfield13ActionPerformed(evt);
            }
        });
        panelC.add(score_textfield13, new org.netbeans.lib.awtextra.AbsoluteConstraints(210, 540, 80, 40));

        score_underline13.setForeground(new java.awt.Color(54, 33, 89));
        panelC.add(score_underline13, new org.netbeans.lib.awtextra.AbsoluteConstraints(210, 580, 80, 10));

        grade_textfield13.setFont(new java.awt.Font("Roboto", 0, 24)); // NOI18N
        grade_textfield13.setForeground(new java.awt.Color(102, 102, 102));
        grade_textfield13.setBorder(null);
        grade_textfield13.setMargin(new java.awt.Insets(2, 4, 2, 2));
        grade_textfield13.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                grade_textfield13FocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                grade_textfield13FocusLost(evt);
            }
        });
        grade_textfield13.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                grade_textfield13MouseClicked(evt);
            }
        });
        grade_textfield13.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                grade_textfield13ActionPerformed(evt);
            }
        });
        panelC.add(grade_textfield13, new org.netbeans.lib.awtextra.AbsoluteConstraints(300, 540, 50, 40));

        grade_underline13.setForeground(new java.awt.Color(54, 33, 89));
        panelC.add(grade_underline13, new org.netbeans.lib.awtextra.AbsoluteConstraints(300, 580, 50, 10));

        score_textfield14.setFont(new java.awt.Font("Roboto", 0, 24)); // NOI18N
        score_textfield14.setForeground(new java.awt.Color(102, 102, 102));
        score_textfield14.setBorder(null);
        score_textfield14.setMargin(new java.awt.Insets(2, 4, 2, 2));
        score_textfield14.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                score_textfield14FocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                score_textfield14FocusLost(evt);
            }
        });
        score_textfield14.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                score_textfield14MouseClicked(evt);
            }
        });
        score_textfield14.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                score_textfield14ActionPerformed(evt);
            }
        });
        panelC.add(score_textfield14, new org.netbeans.lib.awtextra.AbsoluteConstraints(210, 600, 80, 40));

        score_underline14.setForeground(new java.awt.Color(54, 33, 89));
        panelC.add(score_underline14, new org.netbeans.lib.awtextra.AbsoluteConstraints(210, 640, 80, 10));

        grade_textfield14.setFont(new java.awt.Font("Roboto", 0, 24)); // NOI18N
        grade_textfield14.setForeground(new java.awt.Color(102, 102, 102));
        grade_textfield14.setBorder(null);
        grade_textfield14.setMargin(new java.awt.Insets(2, 4, 2, 2));
        grade_textfield14.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                grade_textfield14FocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                grade_textfield14FocusLost(evt);
            }
        });
        grade_textfield14.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                grade_textfield14MouseClicked(evt);
            }
        });
        grade_textfield14.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                grade_textfield14ActionPerformed(evt);
            }
        });
        panelC.add(grade_textfield14, new org.netbeans.lib.awtextra.AbsoluteConstraints(300, 600, 50, 40));

        grade_underline14.setForeground(new java.awt.Color(54, 33, 89));
        panelC.add(grade_underline14, new org.netbeans.lib.awtextra.AbsoluteConstraints(300, 640, 50, 10));

        score_textfield15.setFont(new java.awt.Font("Roboto", 0, 24)); // NOI18N
        score_textfield15.setForeground(new java.awt.Color(102, 102, 102));
        score_textfield15.setBorder(null);
        score_textfield15.setMargin(new java.awt.Insets(2, 4, 2, 2));
        score_textfield15.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                score_textfield15FocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                score_textfield15FocusLost(evt);
            }
        });
        score_textfield15.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                score_textfield15MouseClicked(evt);
            }
        });
        score_textfield15.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                score_textfield15ActionPerformed(evt);
            }
        });
        panelC.add(score_textfield15, new org.netbeans.lib.awtextra.AbsoluteConstraints(210, 660, 80, 40));

        score_underline15.setForeground(new java.awt.Color(54, 33, 89));
        panelC.add(score_underline15, new org.netbeans.lib.awtextra.AbsoluteConstraints(210, 700, 80, 10));

        grade_textfield15.setFont(new java.awt.Font("Roboto", 0, 24)); // NOI18N
        grade_textfield15.setForeground(new java.awt.Color(102, 102, 102));
        grade_textfield15.setBorder(null);
        grade_textfield15.setMargin(new java.awt.Insets(2, 4, 2, 2));
        grade_textfield15.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                grade_textfield15FocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                grade_textfield15FocusLost(evt);
            }
        });
        grade_textfield15.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                grade_textfield15MouseClicked(evt);
            }
        });
        grade_textfield15.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                grade_textfield15ActionPerformed(evt);
            }
        });
        panelC.add(grade_textfield15, new org.netbeans.lib.awtextra.AbsoluteConstraints(300, 660, 50, 40));

        grade_underline15.setForeground(new java.awt.Color(54, 33, 89));
        panelC.add(grade_underline15, new org.netbeans.lib.awtextra.AbsoluteConstraints(300, 700, 50, 10));

        result2_textfieldC.setFont(new java.awt.Font("Roboto", 0, 24)); // NOI18N
        result2_textfieldC.setForeground(new java.awt.Color(54, 33, 89));
        result2_textfieldC.setBorder(null);
        result2_textfieldC.setMargin(new java.awt.Insets(2, 4, 2, 2));
        result2_textfieldC.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                result2_textfieldCActionPerformed(evt);
            }
        });
        panelC.add(result2_textfieldC, new org.netbeans.lib.awtextra.AbsoluteConstraints(420, 720, 140, 40));

        underline10.setForeground(new java.awt.Color(54, 33, 89));
        panelC.add(underline10, new org.netbeans.lib.awtextra.AbsoluteConstraints(420, 760, 140, 20));

        score_textfield16.setFont(new java.awt.Font("Roboto", 0, 24)); // NOI18N
        score_textfield16.setForeground(new java.awt.Color(102, 102, 102));
        score_textfield16.setBorder(null);
        score_textfield16.setMargin(new java.awt.Insets(2, 4, 2, 2));
        score_textfield16.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                score_textfield16FocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                score_textfield16FocusLost(evt);
            }
        });
        score_textfield16.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                score_textfield16MouseClicked(evt);
            }
        });
        score_textfield16.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                score_textfield16ActionPerformed(evt);
            }
        });
        panelC.add(score_textfield16, new org.netbeans.lib.awtextra.AbsoluteConstraints(420, 450, 80, 40));

        score_underline16.setForeground(new java.awt.Color(54, 33, 89));
        panelC.add(score_underline16, new org.netbeans.lib.awtextra.AbsoluteConstraints(420, 490, 80, 10));

        grade_textfield16.setFont(new java.awt.Font("Roboto", 0, 24)); // NOI18N
        grade_textfield16.setForeground(new java.awt.Color(102, 102, 102));
        grade_textfield16.setBorder(null);
        grade_textfield16.setMargin(new java.awt.Insets(2, 4, 2, 2));
        grade_textfield16.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                grade_textfield16FocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                grade_textfield16FocusLost(evt);
            }
        });
        grade_textfield16.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                grade_textfield16MouseClicked(evt);
            }
        });
        grade_textfield16.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                grade_textfield16ActionPerformed(evt);
            }
        });
        panelC.add(grade_textfield16, new org.netbeans.lib.awtextra.AbsoluteConstraints(510, 450, 50, 40));

        grade_underline16.setForeground(new java.awt.Color(54, 33, 89));
        panelC.add(grade_underline16, new org.netbeans.lib.awtextra.AbsoluteConstraints(510, 490, 50, 10));

        score_textfield17.setFont(new java.awt.Font("Roboto", 0, 24)); // NOI18N
        score_textfield17.setForeground(new java.awt.Color(102, 102, 102));
        score_textfield17.setBorder(null);
        score_textfield17.setMargin(new java.awt.Insets(2, 4, 2, 2));
        score_textfield17.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                score_textfield17FocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                score_textfield17FocusLost(evt);
            }
        });
        score_textfield17.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                score_textfield17MouseClicked(evt);
            }
        });
        score_textfield17.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                score_textfield17ActionPerformed(evt);
            }
        });
        panelC.add(score_textfield17, new org.netbeans.lib.awtextra.AbsoluteConstraints(420, 540, 80, 40));

        score_underline17.setForeground(new java.awt.Color(54, 33, 89));
        panelC.add(score_underline17, new org.netbeans.lib.awtextra.AbsoluteConstraints(420, 580, 80, 10));

        grade_textfield17.setFont(new java.awt.Font("Roboto", 0, 24)); // NOI18N
        grade_textfield17.setForeground(new java.awt.Color(102, 102, 102));
        grade_textfield17.setBorder(null);
        grade_textfield17.setMargin(new java.awt.Insets(2, 4, 2, 2));
        grade_textfield17.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                grade_textfield17FocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                grade_textfield17FocusLost(evt);
            }
        });
        grade_textfield17.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                grade_textfield17MouseClicked(evt);
            }
        });
        grade_textfield17.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                grade_textfield17ActionPerformed(evt);
            }
        });
        panelC.add(grade_textfield17, new org.netbeans.lib.awtextra.AbsoluteConstraints(510, 540, 50, 40));

        grade_underline17.setForeground(new java.awt.Color(54, 33, 89));
        panelC.add(grade_underline17, new org.netbeans.lib.awtextra.AbsoluteConstraints(510, 580, 50, 10));

        score_textfield18.setFont(new java.awt.Font("Roboto", 0, 24)); // NOI18N
        score_textfield18.setForeground(new java.awt.Color(102, 102, 102));
        score_textfield18.setBorder(null);
        score_textfield18.setMargin(new java.awt.Insets(2, 4, 2, 2));
        score_textfield18.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                score_textfield18FocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                score_textfield18FocusLost(evt);
            }
        });
        score_textfield18.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                score_textfield18MouseClicked(evt);
            }
        });
        score_textfield18.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                score_textfield18ActionPerformed(evt);
            }
        });
        panelC.add(score_textfield18, new org.netbeans.lib.awtextra.AbsoluteConstraints(420, 600, 80, 40));

        score_underline18.setForeground(new java.awt.Color(54, 33, 89));
        panelC.add(score_underline18, new org.netbeans.lib.awtextra.AbsoluteConstraints(420, 640, 80, 10));

        grade_textfield18.setFont(new java.awt.Font("Roboto", 0, 24)); // NOI18N
        grade_textfield18.setForeground(new java.awt.Color(102, 102, 102));
        grade_textfield18.setBorder(null);
        grade_textfield18.setMargin(new java.awt.Insets(2, 4, 2, 2));
        grade_textfield18.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                grade_textfield18FocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                grade_textfield18FocusLost(evt);
            }
        });
        grade_textfield18.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                grade_textfield18MouseClicked(evt);
            }
        });
        grade_textfield18.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                grade_textfield18ActionPerformed(evt);
            }
        });
        panelC.add(grade_textfield18, new org.netbeans.lib.awtextra.AbsoluteConstraints(510, 600, 50, 40));

        grade_underline18.setForeground(new java.awt.Color(54, 33, 89));
        panelC.add(grade_underline18, new org.netbeans.lib.awtextra.AbsoluteConstraints(510, 640, 50, 10));

        score_textfield19.setFont(new java.awt.Font("Roboto", 0, 24)); // NOI18N
        score_textfield19.setForeground(new java.awt.Color(102, 102, 102));
        score_textfield19.setBorder(null);
        score_textfield19.setMargin(new java.awt.Insets(2, 4, 2, 2));
        score_textfield19.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                score_textfield19FocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                score_textfield19FocusLost(evt);
            }
        });
        score_textfield19.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                score_textfield19MouseClicked(evt);
            }
        });
        score_textfield19.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                score_textfield19ActionPerformed(evt);
            }
        });
        panelC.add(score_textfield19, new org.netbeans.lib.awtextra.AbsoluteConstraints(420, 660, 80, 40));

        score_underline19.setForeground(new java.awt.Color(54, 33, 89));
        panelC.add(score_underline19, new org.netbeans.lib.awtextra.AbsoluteConstraints(420, 700, 80, 10));

        grade_textfield19.setFont(new java.awt.Font("Roboto", 0, 24)); // NOI18N
        grade_textfield19.setForeground(new java.awt.Color(102, 102, 102));
        grade_textfield19.setBorder(null);
        grade_textfield19.setMargin(new java.awt.Insets(2, 4, 2, 2));
        grade_textfield19.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                grade_textfield19FocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                grade_textfield19FocusLost(evt);
            }
        });
        grade_textfield19.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                grade_textfield19MouseClicked(evt);
            }
        });
        grade_textfield19.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                grade_textfield19ActionPerformed(evt);
            }
        });
        panelC.add(grade_textfield19, new org.netbeans.lib.awtextra.AbsoluteConstraints(510, 660, 50, 40));

        grade_underline19.setForeground(new java.awt.Color(54, 33, 89));
        panelC.add(grade_underline19, new org.netbeans.lib.awtextra.AbsoluteConstraints(510, 700, 50, 10));

        result3_textfieldC.setFont(new java.awt.Font("Roboto", 0, 24)); // NOI18N
        result3_textfieldC.setForeground(new java.awt.Color(54, 33, 89));
        result3_textfieldC.setBorder(null);
        result3_textfieldC.setMargin(new java.awt.Insets(2, 4, 2, 2));
        result3_textfieldC.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                result3_textfieldCActionPerformed(evt);
            }
        });
        panelC.add(result3_textfieldC, new org.netbeans.lib.awtextra.AbsoluteConstraints(630, 720, 140, 40));

        underline11.setForeground(new java.awt.Color(54, 33, 89));
        panelC.add(underline11, new org.netbeans.lib.awtextra.AbsoluteConstraints(630, 760, 140, 20));

        score_textfield20.setFont(new java.awt.Font("Roboto", 0, 24)); // NOI18N
        score_textfield20.setForeground(new java.awt.Color(102, 102, 102));
        score_textfield20.setBorder(null);
        score_textfield20.setMargin(new java.awt.Insets(2, 4, 2, 2));
        score_textfield20.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                score_textfield20FocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                score_textfield20FocusLost(evt);
            }
        });
        score_textfield20.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                score_textfield20MouseClicked(evt);
            }
        });
        score_textfield20.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                score_textfield20ActionPerformed(evt);
            }
        });
        panelC.add(score_textfield20, new org.netbeans.lib.awtextra.AbsoluteConstraints(630, 450, 80, 40));

        score_underline20.setForeground(new java.awt.Color(54, 33, 89));
        panelC.add(score_underline20, new org.netbeans.lib.awtextra.AbsoluteConstraints(630, 490, 80, 10));

        grade_textfield20.setFont(new java.awt.Font("Roboto", 0, 24)); // NOI18N
        grade_textfield20.setForeground(new java.awt.Color(102, 102, 102));
        grade_textfield20.setBorder(null);
        grade_textfield20.setMargin(new java.awt.Insets(2, 4, 2, 2));
        grade_textfield20.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                grade_textfield20FocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                grade_textfield20FocusLost(evt);
            }
        });
        grade_textfield20.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                grade_textfield20MouseClicked(evt);
            }
        });
        grade_textfield20.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                grade_textfield20ActionPerformed(evt);
            }
        });
        panelC.add(grade_textfield20, new org.netbeans.lib.awtextra.AbsoluteConstraints(720, 450, 50, 40));

        grade_underline20.setForeground(new java.awt.Color(54, 33, 89));
        panelC.add(grade_underline20, new org.netbeans.lib.awtextra.AbsoluteConstraints(720, 490, 50, 10));

        score_textfield21.setFont(new java.awt.Font("Roboto", 0, 24)); // NOI18N
        score_textfield21.setForeground(new java.awt.Color(102, 102, 102));
        score_textfield21.setBorder(null);
        score_textfield21.setMargin(new java.awt.Insets(2, 4, 2, 2));
        score_textfield21.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                score_textfield21FocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                score_textfield21FocusLost(evt);
            }
        });
        score_textfield21.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                score_textfield21MouseClicked(evt);
            }
        });
        score_textfield21.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                score_textfield21ActionPerformed(evt);
            }
        });
        panelC.add(score_textfield21, new org.netbeans.lib.awtextra.AbsoluteConstraints(630, 540, 80, 40));

        score_underline21.setForeground(new java.awt.Color(54, 33, 89));
        panelC.add(score_underline21, new org.netbeans.lib.awtextra.AbsoluteConstraints(630, 580, 80, 10));

        grade_textfield21.setFont(new java.awt.Font("Roboto", 0, 24)); // NOI18N
        grade_textfield21.setForeground(new java.awt.Color(102, 102, 102));
        grade_textfield21.setBorder(null);
        grade_textfield21.setMargin(new java.awt.Insets(2, 4, 2, 2));
        grade_textfield21.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                grade_textfield21FocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                grade_textfield21FocusLost(evt);
            }
        });
        grade_textfield21.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                grade_textfield21MouseClicked(evt);
            }
        });
        grade_textfield21.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                grade_textfield21ActionPerformed(evt);
            }
        });
        panelC.add(grade_textfield21, new org.netbeans.lib.awtextra.AbsoluteConstraints(720, 540, 50, 40));

        grade_underline21.setForeground(new java.awt.Color(54, 33, 89));
        panelC.add(grade_underline21, new org.netbeans.lib.awtextra.AbsoluteConstraints(720, 580, 50, 10));

        score_textfield22.setFont(new java.awt.Font("Roboto", 0, 24)); // NOI18N
        score_textfield22.setForeground(new java.awt.Color(102, 102, 102));
        score_textfield22.setBorder(null);
        score_textfield22.setMargin(new java.awt.Insets(2, 4, 2, 2));
        score_textfield22.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                score_textfield22FocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                score_textfield22FocusLost(evt);
            }
        });
        score_textfield22.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                score_textfield22MouseClicked(evt);
            }
        });
        score_textfield22.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                score_textfield22ActionPerformed(evt);
            }
        });
        panelC.add(score_textfield22, new org.netbeans.lib.awtextra.AbsoluteConstraints(630, 600, 80, 40));

        score_underline22.setForeground(new java.awt.Color(54, 33, 89));
        panelC.add(score_underline22, new org.netbeans.lib.awtextra.AbsoluteConstraints(630, 640, 80, 10));

        grade_textfield22.setFont(new java.awt.Font("Roboto", 0, 24)); // NOI18N
        grade_textfield22.setForeground(new java.awt.Color(102, 102, 102));
        grade_textfield22.setBorder(null);
        grade_textfield22.setMargin(new java.awt.Insets(2, 4, 2, 2));
        grade_textfield22.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                grade_textfield22FocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                grade_textfield22FocusLost(evt);
            }
        });
        grade_textfield22.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                grade_textfield22MouseClicked(evt);
            }
        });
        grade_textfield22.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                grade_textfield22ActionPerformed(evt);
            }
        });
        panelC.add(grade_textfield22, new org.netbeans.lib.awtextra.AbsoluteConstraints(720, 600, 50, 40));

        grade_underline22.setForeground(new java.awt.Color(54, 33, 89));
        panelC.add(grade_underline22, new org.netbeans.lib.awtextra.AbsoluteConstraints(720, 640, 50, 10));

        score_textfield23.setFont(new java.awt.Font("Roboto", 0, 24)); // NOI18N
        score_textfield23.setForeground(new java.awt.Color(102, 102, 102));
        score_textfield23.setBorder(null);
        score_textfield23.setMargin(new java.awt.Insets(2, 4, 2, 2));
        score_textfield23.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                score_textfield23FocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                score_textfield23FocusLost(evt);
            }
        });
        score_textfield23.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                score_textfield23MouseClicked(evt);
            }
        });
        score_textfield23.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                score_textfield23ActionPerformed(evt);
            }
        });
        panelC.add(score_textfield23, new org.netbeans.lib.awtextra.AbsoluteConstraints(630, 660, 80, 40));

        score_underline23.setForeground(new java.awt.Color(54, 33, 89));
        panelC.add(score_underline23, new org.netbeans.lib.awtextra.AbsoluteConstraints(630, 700, 80, 10));

        grade_textfield23.setFont(new java.awt.Font("Roboto", 0, 24)); // NOI18N
        grade_textfield23.setForeground(new java.awt.Color(102, 102, 102));
        grade_textfield23.setBorder(null);
        grade_textfield23.setMargin(new java.awt.Insets(2, 4, 2, 2));
        grade_textfield23.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                grade_textfield23FocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                grade_textfield23FocusLost(evt);
            }
        });
        grade_textfield23.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                grade_textfield23MouseClicked(evt);
            }
        });
        grade_textfield23.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                grade_textfield23ActionPerformed(evt);
            }
        });
        panelC.add(grade_textfield23, new org.netbeans.lib.awtextra.AbsoluteConstraints(720, 660, 50, 40));

        grade_underline23.setForeground(new java.awt.Color(54, 33, 89));
        panelC.add(grade_underline23, new org.netbeans.lib.awtextra.AbsoluteConstraints(720, 700, 50, 10));

        jLabel9.setBackground(new java.awt.Color(102, 102, 102));
        jLabel9.setText("(grade)");
        panelC.add(jLabel9, new org.netbeans.lib.awtextra.AbsoluteConstraints(300, 500, -1, -1));

        jLabel10.setBackground(new java.awt.Color(102, 102, 102));
        jLabel10.setText("(hit/score)");
        panelC.add(jLabel10, new org.netbeans.lib.awtextra.AbsoluteConstraints(210, 500, -1, -1));

        jLabel11.setBackground(new java.awt.Color(102, 102, 102));
        jLabel11.setText("(grade)");
        panelC.add(jLabel11, new org.netbeans.lib.awtextra.AbsoluteConstraints(300, 500, -1, -1));

        jLabel12.setBackground(new java.awt.Color(102, 102, 102));
        jLabel12.setText("(hit/score)");
        panelC.add(jLabel12, new org.netbeans.lib.awtextra.AbsoluteConstraints(210, 500, -1, -1));

        jLabel13.setBackground(new java.awt.Color(102, 102, 102));
        jLabel13.setText("(grade)");
        panelC.add(jLabel13, new org.netbeans.lib.awtextra.AbsoluteConstraints(510, 500, -1, -1));

        jLabel14.setBackground(new java.awt.Color(102, 102, 102));
        jLabel14.setText("(hit/score)");
        panelC.add(jLabel14, new org.netbeans.lib.awtextra.AbsoluteConstraints(420, 500, -1, -1));

        jLabel15.setBackground(new java.awt.Color(102, 102, 102));
        jLabel15.setText("(grade)");
        panelC.add(jLabel15, new org.netbeans.lib.awtextra.AbsoluteConstraints(720, 500, -1, -1));

        jLabel16.setBackground(new java.awt.Color(102, 102, 102));
        jLabel16.setText("(hit/score)");
        panelC.add(jLabel16, new org.netbeans.lib.awtextra.AbsoluteConstraints(630, 500, -1, -1));

        ScrollPaneC.setViewportView(panelC);

        bg.add(ScrollPaneC, new org.netbeans.lib.awtextra.AbsoluteConstraints(340, 192, 910, 480));

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

        insert_label.setBackground(new java.awt.Color(54, 33, 89));
        insert_label.setFont(new java.awt.Font("Roboto", 0, 14)); // NOI18N
        insert_label.setForeground(new java.awt.Color(54, 33, 89));
        insert_label.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        insert_label.setText("INSERT");
        insert_buttonA.add(insert_label, new org.netbeans.lib.awtextra.AbsoluteConstraints(52, 13, 71, 24));

        bg.add(insert_buttonA, new org.netbeans.lib.awtextra.AbsoluteConstraints(870, 120, 180, 50));

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
        print_buttonA.add(print_label, new org.netbeans.lib.awtextra.AbsoluteConstraints(52, 13, 71, 24));

        bg.add(print_buttonA, new org.netbeans.lib.awtextra.AbsoluteConstraints(1070, 120, 180, 50));

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
        update_buttonC.setLayout(null);

        update_labelC.setBackground(new java.awt.Color(54, 33, 89));
        update_labelC.setFont(new java.awt.Font("Roboto", 0, 14)); // NOI18N
        update_labelC.setForeground(new java.awt.Color(54, 33, 89));
        update_labelC.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        update_labelC.setText("UPDATE");
        update_buttonC.add(update_labelC);
        update_labelC.setBounds(30, 0, 120, 50);

        bg.add(update_buttonC, new org.netbeans.lib.awtextra.AbsoluteConstraints(1070, 120, 180, 50));

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
        edit_buttonB.setLayout(null);

        edit_labelB.setBackground(new java.awt.Color(54, 33, 89));
        edit_labelB.setFont(new java.awt.Font("Roboto", 0, 14)); // NOI18N
        edit_labelB.setForeground(new java.awt.Color(54, 33, 89));
        edit_labelB.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        edit_labelB.setText("EDIT");
        edit_buttonB.add(edit_labelB);
        edit_labelB.setBounds(50, 0, 80, 50);

        bg.add(edit_buttonB, new org.netbeans.lib.awtextra.AbsoluteConstraints(470, 120, 180, 50));

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
                "S. No.", "Army No", "Name", "Rank", "Company", "Wpn", "Date of test", "400m", "300m", "200m", "100m", "Result"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, false, false, false, false, false, false
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
            fullscreen_tableB.getColumnModel().getColumn(0).setMinWidth(40);
            fullscreen_tableB.getColumnModel().getColumn(0).setPreferredWidth(40);
            fullscreen_tableB.getColumnModel().getColumn(0).setMaxWidth(40);
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
            new Modules(0).setVisible(true);
            this.setVisible(false);

        }
        go_back = false;

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
        update_buttonC.setVisible(false);

        ScrollPaneA.setVisible(true);
        panelB.setVisible(false);
        ScrollPaneC.setVisible(false);
        fullscreen_panelB.setVisible(false);

        service_number_textfield.setText("");
        name.setText("> XXXXXXX");
        rank.setText("> XXXXXXX");
        unit.setText("> XXXXXXX");
        trade.setText("> XXXXXXX");
        company.setText("> XXXXXXX");
        wpn_textfield.setText("");
        test_date_combobox.setSelectedItem("DD");
        test_month_combobox.setSelectedItem("MM");
        test_year_combobox.setSelectedItem("YYYY");
        score_textfield.setText("");
        score_textfield1.setText("");
        score_textfield2.setText("");
        score_textfield3.setText("");
        score_textfield4.setText("");
        score_textfield5.setText("");
        score_textfield6.setText("");
        score_textfield7.setText("");
        score_textfield8.setText("");
        score_textfield9.setText("");
        score_textfield10.setText("");
        score_textfield11.setText("");
        grade_textfield.setText("");
        grade_textfield1.setText("");
        grade_textfield2.setText("");
        grade_textfield3.setText("");
        grade_textfield4.setText("");
        grade_textfield5.setText("");
        grade_textfield6.setText("");
        grade_textfield7.setText("");
        grade_textfield8.setText("");
        grade_textfield9.setText("");
        grade_textfield10.setText("");
        grade_textfield11.setText("");
        result_textfield.setText("");
        result_textfield1.setText("");
        result_textfield2.setText("");
    }//GEN-LAST:event_menuAMouseClicked

    private void menuBMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_menuBMouseClicked
        menuB.setBackground(new java.awt.Color(85, 65, 118));
        menuA.setBackground(new java.awt.Color(64, 43, 100));

        print_buttonB.setVisible(true);
        edit_buttonB.setVisible(true);
        refresh_buttonB.setVisible(true);
        fullscreen_buttonB.setVisible(true);
        print_buttonA.setVisible(false);
        insert_buttonA.setVisible(false);
        update_buttonC.setVisible(false);

        ScrollPaneA.setVisible(false);
        panelB.setVisible(true);
        ScrollPaneC.setVisible(false);
        fullscreen_panelB.setVisible(false);
        service_numberB.setText("");
        viewall();
    }//GEN-LAST:event_menuBMouseClicked

    private void okayMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_okayMouseClicked
        retrieve_Army_No();
    }//GEN-LAST:event_okayMouseClicked

    private void wpn_textfieldActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_wpn_textfieldActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_wpn_textfieldActionPerformed

    private void result_textfieldActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_result_textfieldActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_result_textfieldActionPerformed

    private void okayBMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_okayBMouseClicked
        viewByArmyNo();
    }//GEN-LAST:event_okayBMouseClicked

    private void insert_buttonAMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_insert_buttonAMouseClicked
        // TODO add your handling code here:
        try {

            String value[] = retrieve_Army_No();
            boolean flag = true;
            if (!value[0].equals("")) {
                String valid_date = validate_date("A");
                if (!valid_date.equals("")) {
                    String fourM = score_textfield.getText().trim() + "/" + grade_textfield.getText().trim() + "; " + score_textfield4.getText().trim() + "/" + grade_textfield4.getText().trim() + "; " + score_textfield8.getText().trim() + "/" + grade_textfield8.getText().trim() + ";";
                    String threeM = score_textfield1.getText().trim() + "/" + grade_textfield1.getText().trim() + "; " + score_textfield5.getText().trim() + "/" + grade_textfield5.getText().trim() + "; " + score_textfield9.getText().trim() + "/" + grade_textfield9.getText().trim() + ";";
                    String twoM = score_textfield2.getText().trim() + "/" + grade_textfield2.getText().trim() + "; " + score_textfield6.getText().trim() + "/" + grade_textfield6.getText().trim() + "; " + score_textfield10.getText().trim() + "/" + grade_textfield10.getText().trim() + ";";
                    String oneM = score_textfield3.getText().trim() + "/" + grade_textfield3.getText().trim() + "; " + score_textfield7.getText().trim() + "/" + grade_textfield7.getText().trim() + "; " + score_textfield11.getText().trim() + "/" + grade_textfield11.getText().trim() + ";";
                    String result = result_textfield.getText().trim() + "; " + result_textfield1.getText().trim() + "; " + result_textfield2.getText().trim() + ";";
                    if (fourM.equals("/; /; /;")) {
                        fourM = "";
                    }
                    if (threeM.equals("/; /; /;")) {
                        threeM = "";
                    }
                    if (twoM.equals("/; /; /;")) {
                        twoM = "";
                    }
                    if (oneM.equals("/; /; /;")) {
                        oneM = "";
                    }
                    if (result.equals("; ; ;")) {
                        result = "";
                    }
                    if (wpn_textfield.getText().trim().equals("")) {
                        JOptionPane.showMessageDialog(null, "Wpn field cannot be empty!", "Error", JOptionPane.ERROR_MESSAGE);
                    } else {
                        PreparedStatement pstmt = con.prepareStatement("insert into firing values(?,?,?,?,?,?,?,?,?,?,?)");

                        try {
                            pstmt = con.prepareStatement("insert into firing values(?,?,?,?,?,?,?,?,?,?,?)");
                            pstmt.setString(1, service_number_textfield.getText().trim());
                            pstmt.setString(2, value[0]);
                            pstmt.setString(3, value[1]);
                            pstmt.setString(4, value[2]);
                            pstmt.setString(5, wpn_textfield.getText().trim());
                            pstmt.setString(6, valid_date);
                            pstmt.setString(7, fourM);
                            pstmt.setString(8, threeM);
                            pstmt.setString(9, twoM);
                            pstmt.setString(10, oneM);
                            pstmt.setString(11, result);
                            pstmt.executeUpdate();

                        } catch (java.sql.SQLIntegrityConstraintViolationException sqle) {
                            JOptionPane.showMessageDialog(null, "Record for the given test date already exists!", "Error", JOptionPane.ERROR_MESSAGE);
                            flag = false;

                        }
                        if (flag) {
                            JOptionPane.showMessageDialog(null, "Entry successful!", "Sucessful", JOptionPane.WARNING_MESSAGE);

                            service_number_textfield.setText("");
                            name.setText("> XXXXXXX");
                            rank.setText("> XXXXXXX");
                            unit.setText("> XXXXXXX");
                            trade.setText("> XXXXXXX");
                            company.setText("> XXXXXXX");
                            wpn_textfield.setText("");
                            test_date_combobox.setSelectedItem("DD");
                            test_month_combobox.setSelectedItem("MM");
                            test_year_combobox.setSelectedItem("YYYY");
                            score_textfield.setText("");
                            score_textfield1.setText("");
                            score_textfield2.setText("");
                            score_textfield3.setText("");
                            score_textfield4.setText("");
                            score_textfield5.setText("");
                            score_textfield6.setText("");
                            score_textfield7.setText("");
                            score_textfield8.setText("");
                            score_textfield9.setText("");
                            score_textfield10.setText("");
                            score_textfield11.setText("");
                            grade_textfield.setText("");
                            grade_textfield1.setText("");
                            grade_textfield2.setText("");
                            grade_textfield3.setText("");
                            grade_textfield4.setText("");
                            grade_textfield5.setText("");
                            grade_textfield6.setText("");
                            grade_textfield7.setText("");
                            grade_textfield8.setText("");
                            grade_textfield9.setText("");
                            grade_textfield10.setText("");
                            grade_textfield11.setText("");
                            result_textfield.setText("");
                            result_textfield1.setText("");
                            result_textfield2.setText("");
                        }

                    }
                }
            }

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
        }
    }//GEN-LAST:event_insert_buttonAMouseClicked

    private void insert_buttonAMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_insert_buttonAMouseEntered
        insert_buttonA.setBackground(new java.awt.Color(237, 224, 255));
    }//GEN-LAST:event_insert_buttonAMouseEntered

    private void insert_buttonAMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_insert_buttonAMouseExited
        insert_buttonA.setBackground(new java.awt.Color(240, 240, 240));
    }//GEN-LAST:event_insert_buttonAMouseExited

    private void print_buttonAMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_print_buttonAMouseClicked
        title_label.grabFocus();
        panelA_bottom_line.setVisible(false);
        PrintRecord();
        panelA_bottom_line.setVisible(true);

    }//GEN-LAST:event_print_buttonAMouseClicked

    private void print_buttonAMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_print_buttonAMouseEntered
        print_buttonA.setBackground(new java.awt.Color(237, 224, 255));
    }//GEN-LAST:event_print_buttonAMouseEntered

    private void print_buttonAMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_print_buttonAMouseExited
        print_buttonA.setBackground(new java.awt.Color(240, 240, 240));
    }//GEN-LAST:event_print_buttonAMouseExited

    private void refresh_buttonBMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_refresh_buttonBMouseClicked
        // TODO add your handling code here:
        if (!service_numberB.getText().trim().equals("")) {
            viewByArmyNo();
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
            ResultSet resultSet = statement.executeQuery("select * from firing");
            XSSFWorkbook workbook = new XSSFWorkbook();
            XSSFSheet spreadsheet = null;
            spreadsheet = workbook.createSheet("Firing");

            XSSFRow row = spreadsheet.createRow(0);
            XSSFCell cell;
            cell = row.createCell(0);
            cell.setCellValue("Army No");
            cell = row.createCell(1);
            cell.setCellValue("Name");
            cell = row.createCell(2);
            cell.setCellValue("Rank");
            cell = row.createCell(3);
            cell.setCellValue("Company");
            cell = row.createCell(4);
            cell.setCellValue("WPN");
            cell = row.createCell(5);
            cell.setCellValue("Test date");
            cell = row.createCell(6);
            cell.setCellValue("400 m");
            cell = row.createCell(7);
            cell.setCellValue("300 m");
            cell = row.createCell(8);
            cell.setCellValue("200 m");
            cell = row.createCell(9);
            cell.setCellValue("100 m");
            cell = row.createCell(10);
            cell.setCellValue("Result");

            int i = 1;

            while (resultSet.next()) {
                row = spreadsheet.createRow(i);
                cell = row.createCell(0);
                cell.setCellValue(resultSet.getString("Service_No"));
                cell = row.createCell(1);
                cell.setCellValue(resultSet.getString("Name"));
                cell = row.createCell(2);
                cell.setCellValue(resultSet.getString("rank_"));
                cell = row.createCell(3);
                cell.setCellValue(resultSet.getString("company"));
                cell = row.createCell(4);
                cell.setCellValue(resultSet.getString("wpn"));
                cell = row.createCell(5);
                cell.setCellValue(resultSet.getString("test_date"));
                cell = row.createCell(6);
                cell.setCellValue(resultSet.getString("400m"));
                cell = row.createCell(7);
                cell.setCellValue(resultSet.getString("300m"));
                cell = row.createCell(8);
                cell.setCellValue(resultSet.getString("200m"));
                cell = row.createCell(9);
                cell.setCellValue(resultSet.getString("100m"));
                cell = row.createCell(10);
                cell.setCellValue(resultSet.getString("result"));

                i++;
            }
            path_file paths = new path_file();
            FileOutputStream out = new FileOutputStream(new File(paths.csv + "\\Firing.xlsx"));
            workbook.write(out);
            System.out.println("Firing.xlsx written successfully");
            Desktop.getDesktop().open(new File(paths.csv+"/Firing.xlsx"));
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
        if (!service_numberB.getText().trim().equals("")) {
            if (viewByArmyNo()) {
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

    private void update_buttonCMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_update_buttonCMouseClicked
        // TODO add your handling code here:
        try {

            String valid_date = validate_date("C");
            if (!valid_date.equals("")) {
                String fourM = score_textfield12.getText().trim() + "/" + grade_textfield12.getText().trim() + "; " + score_textfield16.getText().trim() + "/" + grade_textfield16.getText().trim() + "; " + score_textfield20.getText().trim() + "/" + grade_textfield20.getText().trim() + ";";
                String threeM = score_textfield13.getText().trim() + "/" + grade_textfield13.getText().trim() + "; " + score_textfield17.getText().trim() + "/" + grade_textfield17.getText().trim() + "; " + score_textfield21.getText().trim() + "/" + grade_textfield21.getText().trim() + ";";
                String twoM = score_textfield14.getText().trim() + "/" + grade_textfield14.getText().trim() + "; " + score_textfield18.getText().trim() + "/" + grade_textfield18.getText().trim() + "; " + score_textfield22.getText().trim() + "/" + grade_textfield22.getText().trim() + ";";
                String oneM = score_textfield15.getText().trim() + "/" + grade_textfield15.getText().trim() + "; " + score_textfield19.getText().trim() + "/" + grade_textfield19.getText().trim() + "; " + score_textfield23.getText().trim() + "/" + grade_textfield23.getText().trim() + ";";
                String result = result1_textfieldC.getText().trim() + "; " + result2_textfieldC.getText().trim() + "; " + result3_textfieldC.getText().trim() + ";";
                if (fourM.equals("/; /; /;")) {
                    fourM = "";
                }
                if (threeM.equals("/; /; /;")) {
                    threeM = "";
                }
                if (twoM.equals("/; /; /;")) {
                    twoM = "";
                }
                if (oneM.equals("/; /; /;")) {
                    oneM = "";
                }
                if (result.equals("; ; ;")) {
                    result = "";
                }

                if (wpn_textfieldC.getText().trim().equals("")) {
                    JOptionPane.showMessageDialog(null, "Wpn field cannot be empty!", "Error", JOptionPane.ERROR_MESSAGE);
                } else {
                    try {
                        String query = "update firing set wpn='" + wpn_textfieldC.getText().trim() + "', Test_date=" + valid_date + ", 400m='" + fourM + "', 300m='" + threeM + "', 200m='" + twoM + "', 100m='" + oneM + "', result='" + result + "' where Service_no='" + army_numberC.getText().trim() + "' and test_date=" + test_date_.replace("-", "");

                        Statement stmt = con.createStatement();
                        stmt.executeUpdate(query);
                        JOptionPane.showMessageDialog(null, "Updation successful!", "Sucessful", JOptionPane.WARNING_MESSAGE);
                        army_numberC.setText("> XXXXXXX");
                        nameC.setText("> XXXXXXX");
                        rankC.setText("> XXXXXXX");
                        rankC.setText("> XXXXXXX");
                        wpn_textfieldC.setText("");

                        test_date_comboboxC.setSelectedItem("DD");
                        test_month_comboboxC.setSelectedItem("MM");
                        test_year_comboboxC.setSelectedItem("YYYY");

                        score_textfield12.setText("");
                        grade_textfield12.setText("");
                        score_textfield16.setText("");
                        grade_textfield16.setText("");
                        score_textfield20.setText("");
                        grade_textfield20.setText("");

                        score_textfield13.setText("");
                        grade_textfield13.setText("");
                        score_textfield17.setText("");
                        grade_textfield17.setText("");
                        score_textfield21.setText("");
                        grade_textfield21.setText("");

                        score_textfield14.setText("");
                        grade_textfield14.setText("");
                        score_textfield18.setText("");
                        grade_textfield18.setText("");
                        score_textfield22.setText("");
                        grade_textfield22.setText("");

                        score_textfield15.setText("");
                        grade_textfield15.setText("");
                        score_textfield19.setText("");
                        grade_textfield19.setText("");
                        score_textfield23.setText("");
                        grade_textfield23.setText("");

                        result1_textfieldC.setText("");
                        result2_textfieldC.setText("");
                        result3_textfieldC.setText("");

                    } catch (java.sql.SQLIntegrityConstraintViolationException sqle) {
                        JOptionPane.showMessageDialog(null, "Record for the given test date already exists!", "Error", JOptionPane.ERROR_MESSAGE);

                    }
                }
            }
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

    private void edit_buttonBMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_edit_buttonBMouseClicked

        int row = TableG_firing.getSelectedRow();
        if (row == -1) {
            JOptionPane.showMessageDialog(null, "Please select a row to update", "Error", JOptionPane.ERROR_MESSAGE);
        } else {
            ScrollPaneA.setVisible(false);
            panelB.setVisible(false);
            ScrollPaneC.setVisible(true);
            fullscreen_panelB.setVisible(false);

            print_buttonA.setVisible(false);
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

    private void exit_fullscreen_buttonBMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_exit_fullscreen_buttonBMouseClicked
        fullscreen_panelB.setVisible(false);
        bg.setVisible(true);
    }//GEN-LAST:event_exit_fullscreen_buttonBMouseClicked

    private void score_textfieldFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_score_textfieldFocusGained
        // TODO add your handling code here:
        score_textfield.setForeground(Color.decode("#000000"));
        if (score_textfield.getText().trim().equals("ICardNumber")) {
            score_textfield.setText("");
        }
    }//GEN-LAST:event_score_textfieldFocusGained

    private void score_textfieldFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_score_textfieldFocusLost
        // TODO add your handling code here:
        if (score_textfield.getText().trim().equals("")) {
            score_textfield.setText("ICardNumber");
            score_textfield.setForeground(Color.decode("#666666"));
        }
    }//GEN-LAST:event_score_textfieldFocusLost

    private void score_textfieldMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_score_textfieldMouseClicked
        // indl_Icard_numberA.setText("");
    }//GEN-LAST:event_score_textfieldMouseClicked

    private void grade_textfieldFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_grade_textfieldFocusGained
        // TODO add your handling code here:
        grade_textfield.setForeground(Color.decode("#000000"));
        if (grade_textfield.getText().trim().equals("PAN")) {
            grade_textfield.setText("");
        }

    }//GEN-LAST:event_grade_textfieldFocusGained

    private void grade_textfieldFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_grade_textfieldFocusLost
        // TODO add your handling code here:
        if (grade_textfield.getText().trim().equals("")) {
            grade_textfield.setText("PAN");
            grade_textfield.setForeground(Color.decode("#666666"));
        }
    }//GEN-LAST:event_grade_textfieldFocusLost

    private void grade_textfieldMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_grade_textfieldMouseClicked
        //  indl_PAN_A.setText("");
    }//GEN-LAST:event_grade_textfieldMouseClicked

    private void grade_textfieldActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_grade_textfieldActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_grade_textfieldActionPerformed

    private void score_textfieldActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_score_textfieldActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_score_textfieldActionPerformed

    private void score_textfield1FocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_score_textfield1FocusGained
        // TODO add your handling code here:
    }//GEN-LAST:event_score_textfield1FocusGained

    private void score_textfield1FocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_score_textfield1FocusLost
        // TODO add your handling code here:
    }//GEN-LAST:event_score_textfield1FocusLost

    private void score_textfield1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_score_textfield1MouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_score_textfield1MouseClicked

    private void score_textfield1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_score_textfield1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_score_textfield1ActionPerformed

    private void grade_textfield1FocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_grade_textfield1FocusGained
        // TODO add your handling code here:
    }//GEN-LAST:event_grade_textfield1FocusGained

    private void grade_textfield1FocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_grade_textfield1FocusLost
        // TODO add your handling code here:
    }//GEN-LAST:event_grade_textfield1FocusLost

    private void grade_textfield1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_grade_textfield1MouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_grade_textfield1MouseClicked

    private void grade_textfield1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_grade_textfield1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_grade_textfield1ActionPerformed

    private void score_textfield2FocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_score_textfield2FocusGained
        // TODO add your handling code here:
    }//GEN-LAST:event_score_textfield2FocusGained

    private void score_textfield2FocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_score_textfield2FocusLost
        // TODO add your handling code here:
    }//GEN-LAST:event_score_textfield2FocusLost

    private void score_textfield2MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_score_textfield2MouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_score_textfield2MouseClicked

    private void score_textfield2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_score_textfield2ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_score_textfield2ActionPerformed

    private void grade_textfield2FocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_grade_textfield2FocusGained
        // TODO add your handling code here:
    }//GEN-LAST:event_grade_textfield2FocusGained

    private void grade_textfield2FocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_grade_textfield2FocusLost
        // TODO add your handling code here:
    }//GEN-LAST:event_grade_textfield2FocusLost

    private void grade_textfield2MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_grade_textfield2MouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_grade_textfield2MouseClicked

    private void grade_textfield2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_grade_textfield2ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_grade_textfield2ActionPerformed

    private void score_textfield3FocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_score_textfield3FocusGained
        // TODO add your handling code here:
    }//GEN-LAST:event_score_textfield3FocusGained

    private void score_textfield3FocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_score_textfield3FocusLost
        // TODO add your handling code here:
    }//GEN-LAST:event_score_textfield3FocusLost

    private void score_textfield3MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_score_textfield3MouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_score_textfield3MouseClicked

    private void score_textfield3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_score_textfield3ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_score_textfield3ActionPerformed

    private void grade_textfield3FocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_grade_textfield3FocusGained
        // TODO add your handling code here:
    }//GEN-LAST:event_grade_textfield3FocusGained

    private void grade_textfield3FocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_grade_textfield3FocusLost
        // TODO add your handling code here:
    }//GEN-LAST:event_grade_textfield3FocusLost

    private void grade_textfield3MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_grade_textfield3MouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_grade_textfield3MouseClicked

    private void grade_textfield3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_grade_textfield3ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_grade_textfield3ActionPerformed

    private void result_textfield1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_result_textfield1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_result_textfield1ActionPerformed

    private void score_textfield4FocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_score_textfield4FocusGained
        // TODO add your handling code here:
    }//GEN-LAST:event_score_textfield4FocusGained

    private void score_textfield4FocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_score_textfield4FocusLost
        // TODO add your handling code here:
    }//GEN-LAST:event_score_textfield4FocusLost

    private void score_textfield4MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_score_textfield4MouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_score_textfield4MouseClicked

    private void score_textfield4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_score_textfield4ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_score_textfield4ActionPerformed

    private void grade_textfield4FocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_grade_textfield4FocusGained
        // TODO add your handling code here:
    }//GEN-LAST:event_grade_textfield4FocusGained

    private void grade_textfield4FocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_grade_textfield4FocusLost
        // TODO add your handling code here:
    }//GEN-LAST:event_grade_textfield4FocusLost

    private void grade_textfield4MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_grade_textfield4MouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_grade_textfield4MouseClicked

    private void grade_textfield4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_grade_textfield4ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_grade_textfield4ActionPerformed

    private void score_textfield5FocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_score_textfield5FocusGained
        // TODO add your handling code here:
    }//GEN-LAST:event_score_textfield5FocusGained

    private void score_textfield5FocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_score_textfield5FocusLost
        // TODO add your handling code here:
    }//GEN-LAST:event_score_textfield5FocusLost

    private void score_textfield5MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_score_textfield5MouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_score_textfield5MouseClicked

    private void score_textfield5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_score_textfield5ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_score_textfield5ActionPerformed

    private void grade_textfield5FocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_grade_textfield5FocusGained
        // TODO add your handling code here:
    }//GEN-LAST:event_grade_textfield5FocusGained

    private void grade_textfield5FocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_grade_textfield5FocusLost
        // TODO add your handling code here:
    }//GEN-LAST:event_grade_textfield5FocusLost

    private void grade_textfield5MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_grade_textfield5MouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_grade_textfield5MouseClicked

    private void grade_textfield5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_grade_textfield5ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_grade_textfield5ActionPerformed

    private void score_textfield6FocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_score_textfield6FocusGained
        // TODO add your handling code here:
    }//GEN-LAST:event_score_textfield6FocusGained

    private void score_textfield6FocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_score_textfield6FocusLost
        // TODO add your handling code here:
    }//GEN-LAST:event_score_textfield6FocusLost

    private void score_textfield6MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_score_textfield6MouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_score_textfield6MouseClicked

    private void score_textfield6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_score_textfield6ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_score_textfield6ActionPerformed

    private void grade_textfield6FocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_grade_textfield6FocusGained
        // TODO add your handling code here:
    }//GEN-LAST:event_grade_textfield6FocusGained

    private void grade_textfield6FocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_grade_textfield6FocusLost
        // TODO add your handling code here:
    }//GEN-LAST:event_grade_textfield6FocusLost

    private void grade_textfield6MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_grade_textfield6MouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_grade_textfield6MouseClicked

    private void grade_textfield6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_grade_textfield6ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_grade_textfield6ActionPerformed

    private void score_textfield7FocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_score_textfield7FocusGained
        // TODO add your handling code here:
    }//GEN-LAST:event_score_textfield7FocusGained

    private void score_textfield7FocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_score_textfield7FocusLost
        // TODO add your handling code here:
    }//GEN-LAST:event_score_textfield7FocusLost

    private void score_textfield7MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_score_textfield7MouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_score_textfield7MouseClicked

    private void score_textfield7ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_score_textfield7ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_score_textfield7ActionPerformed

    private void grade_textfield7FocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_grade_textfield7FocusGained
        // TODO add your handling code here:
    }//GEN-LAST:event_grade_textfield7FocusGained

    private void grade_textfield7FocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_grade_textfield7FocusLost
        // TODO add your handling code here:
    }//GEN-LAST:event_grade_textfield7FocusLost

    private void grade_textfield7MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_grade_textfield7MouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_grade_textfield7MouseClicked

    private void grade_textfield7ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_grade_textfield7ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_grade_textfield7ActionPerformed

    private void result_textfield2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_result_textfield2ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_result_textfield2ActionPerformed

    private void score_textfield8FocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_score_textfield8FocusGained
        // TODO add your handling code here:
    }//GEN-LAST:event_score_textfield8FocusGained

    private void score_textfield8FocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_score_textfield8FocusLost
        // TODO add your handling code here:
    }//GEN-LAST:event_score_textfield8FocusLost

    private void score_textfield8MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_score_textfield8MouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_score_textfield8MouseClicked

    private void score_textfield8ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_score_textfield8ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_score_textfield8ActionPerformed

    private void grade_textfield8FocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_grade_textfield8FocusGained
        // TODO add your handling code here:
    }//GEN-LAST:event_grade_textfield8FocusGained

    private void grade_textfield8FocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_grade_textfield8FocusLost
        // TODO add your handling code here:
    }//GEN-LAST:event_grade_textfield8FocusLost

    private void grade_textfield8MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_grade_textfield8MouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_grade_textfield8MouseClicked

    private void grade_textfield8ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_grade_textfield8ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_grade_textfield8ActionPerformed

    private void score_textfield9FocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_score_textfield9FocusGained
        // TODO add your handling code here:
    }//GEN-LAST:event_score_textfield9FocusGained

    private void score_textfield9FocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_score_textfield9FocusLost
        // TODO add your handling code here:
    }//GEN-LAST:event_score_textfield9FocusLost

    private void score_textfield9MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_score_textfield9MouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_score_textfield9MouseClicked

    private void score_textfield9ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_score_textfield9ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_score_textfield9ActionPerformed

    private void grade_textfield9FocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_grade_textfield9FocusGained
        // TODO add your handling code here:
    }//GEN-LAST:event_grade_textfield9FocusGained

    private void grade_textfield9FocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_grade_textfield9FocusLost
        // TODO add your handling code here:
    }//GEN-LAST:event_grade_textfield9FocusLost

    private void grade_textfield9MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_grade_textfield9MouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_grade_textfield9MouseClicked

    private void grade_textfield9ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_grade_textfield9ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_grade_textfield9ActionPerformed

    private void score_textfield10FocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_score_textfield10FocusGained
        // TODO add your handling code here:
    }//GEN-LAST:event_score_textfield10FocusGained

    private void score_textfield10FocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_score_textfield10FocusLost
        // TODO add your handling code here:
    }//GEN-LAST:event_score_textfield10FocusLost

    private void score_textfield10MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_score_textfield10MouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_score_textfield10MouseClicked

    private void score_textfield10ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_score_textfield10ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_score_textfield10ActionPerformed

    private void grade_textfield10FocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_grade_textfield10FocusGained
        // TODO add your handling code here:
    }//GEN-LAST:event_grade_textfield10FocusGained

    private void grade_textfield10FocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_grade_textfield10FocusLost
        // TODO add your handling code here:
    }//GEN-LAST:event_grade_textfield10FocusLost

    private void grade_textfield10MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_grade_textfield10MouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_grade_textfield10MouseClicked

    private void grade_textfield10ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_grade_textfield10ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_grade_textfield10ActionPerformed

    private void score_textfield11FocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_score_textfield11FocusGained
        // TODO add your handling code here:
    }//GEN-LAST:event_score_textfield11FocusGained

    private void score_textfield11FocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_score_textfield11FocusLost
        // TODO add your handling code here:
    }//GEN-LAST:event_score_textfield11FocusLost

    private void score_textfield11MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_score_textfield11MouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_score_textfield11MouseClicked

    private void score_textfield11ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_score_textfield11ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_score_textfield11ActionPerformed

    private void grade_textfield11FocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_grade_textfield11FocusGained
        // TODO add your handling code here:
    }//GEN-LAST:event_grade_textfield11FocusGained

    private void grade_textfield11FocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_grade_textfield11FocusLost
        // TODO add your handling code here:
    }//GEN-LAST:event_grade_textfield11FocusLost

    private void grade_textfield11MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_grade_textfield11MouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_grade_textfield11MouseClicked

    private void grade_textfield11ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_grade_textfield11ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_grade_textfield11ActionPerformed

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

    private void exit_fullscreen_buttonBMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_exit_fullscreen_buttonBMouseEntered
        exit_fullscreen_buttonB.setBackground(new java.awt.Color(237, 224, 255));
    }//GEN-LAST:event_exit_fullscreen_buttonBMouseEntered

    private void exit_fullscreen_buttonBMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_exit_fullscreen_buttonBMouseExited
        exit_fullscreen_buttonB.setBackground(new java.awt.Color(240, 240, 240));
    }//GEN-LAST:event_exit_fullscreen_buttonBMouseExited

    private void wpn_textfieldCActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_wpn_textfieldCActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_wpn_textfieldCActionPerformed

    private void result1_textfieldCActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_result1_textfieldCActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_result1_textfieldCActionPerformed

    private void score_textfield12FocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_score_textfield12FocusGained
        // TODO add your handling code here:
    }//GEN-LAST:event_score_textfield12FocusGained

    private void score_textfield12FocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_score_textfield12FocusLost
        // TODO add your handling code here:
    }//GEN-LAST:event_score_textfield12FocusLost

    private void score_textfield12MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_score_textfield12MouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_score_textfield12MouseClicked

    private void score_textfield12ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_score_textfield12ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_score_textfield12ActionPerformed

    private void grade_textfield12FocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_grade_textfield12FocusGained
        // TODO add your handling code here:
    }//GEN-LAST:event_grade_textfield12FocusGained

    private void grade_textfield12FocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_grade_textfield12FocusLost
        // TODO add your handling code here:
    }//GEN-LAST:event_grade_textfield12FocusLost

    private void grade_textfield12MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_grade_textfield12MouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_grade_textfield12MouseClicked

    private void grade_textfield12ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_grade_textfield12ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_grade_textfield12ActionPerformed

    private void score_textfield13FocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_score_textfield13FocusGained
        // TODO add your handling code here:
    }//GEN-LAST:event_score_textfield13FocusGained

    private void score_textfield13FocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_score_textfield13FocusLost
        // TODO add your handling code here:
    }//GEN-LAST:event_score_textfield13FocusLost

    private void score_textfield13MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_score_textfield13MouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_score_textfield13MouseClicked

    private void score_textfield13ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_score_textfield13ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_score_textfield13ActionPerformed

    private void grade_textfield13FocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_grade_textfield13FocusGained
        // TODO add your handling code here:
    }//GEN-LAST:event_grade_textfield13FocusGained

    private void grade_textfield13FocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_grade_textfield13FocusLost
        // TODO add your handling code here:
    }//GEN-LAST:event_grade_textfield13FocusLost

    private void grade_textfield13MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_grade_textfield13MouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_grade_textfield13MouseClicked

    private void grade_textfield13ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_grade_textfield13ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_grade_textfield13ActionPerformed

    private void score_textfield14FocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_score_textfield14FocusGained
        // TODO add your handling code here:
    }//GEN-LAST:event_score_textfield14FocusGained

    private void score_textfield14FocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_score_textfield14FocusLost
        // TODO add your handling code here:
    }//GEN-LAST:event_score_textfield14FocusLost

    private void score_textfield14MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_score_textfield14MouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_score_textfield14MouseClicked

    private void score_textfield14ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_score_textfield14ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_score_textfield14ActionPerformed

    private void grade_textfield14FocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_grade_textfield14FocusGained
        // TODO add your handling code here:
    }//GEN-LAST:event_grade_textfield14FocusGained

    private void grade_textfield14FocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_grade_textfield14FocusLost
        // TODO add your handling code here:
    }//GEN-LAST:event_grade_textfield14FocusLost

    private void grade_textfield14MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_grade_textfield14MouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_grade_textfield14MouseClicked

    private void grade_textfield14ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_grade_textfield14ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_grade_textfield14ActionPerformed

    private void score_textfield15FocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_score_textfield15FocusGained
        // TODO add your handling code here:
    }//GEN-LAST:event_score_textfield15FocusGained

    private void score_textfield15FocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_score_textfield15FocusLost
        // TODO add your handling code here:
    }//GEN-LAST:event_score_textfield15FocusLost

    private void score_textfield15MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_score_textfield15MouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_score_textfield15MouseClicked

    private void score_textfield15ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_score_textfield15ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_score_textfield15ActionPerformed

    private void grade_textfield15FocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_grade_textfield15FocusGained
        // TODO add your handling code here:
    }//GEN-LAST:event_grade_textfield15FocusGained

    private void grade_textfield15FocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_grade_textfield15FocusLost
        // TODO add your handling code here:
    }//GEN-LAST:event_grade_textfield15FocusLost

    private void grade_textfield15MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_grade_textfield15MouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_grade_textfield15MouseClicked

    private void grade_textfield15ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_grade_textfield15ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_grade_textfield15ActionPerformed

    private void result2_textfieldCActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_result2_textfieldCActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_result2_textfieldCActionPerformed

    private void score_textfield16FocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_score_textfield16FocusGained
        // TODO add your handling code here:
    }//GEN-LAST:event_score_textfield16FocusGained

    private void score_textfield16FocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_score_textfield16FocusLost
        // TODO add your handling code here:
    }//GEN-LAST:event_score_textfield16FocusLost

    private void score_textfield16MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_score_textfield16MouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_score_textfield16MouseClicked

    private void score_textfield16ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_score_textfield16ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_score_textfield16ActionPerformed

    private void grade_textfield16FocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_grade_textfield16FocusGained
        // TODO add your handling code here:
    }//GEN-LAST:event_grade_textfield16FocusGained

    private void grade_textfield16FocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_grade_textfield16FocusLost
        // TODO add your handling code here:
    }//GEN-LAST:event_grade_textfield16FocusLost

    private void grade_textfield16MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_grade_textfield16MouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_grade_textfield16MouseClicked

    private void grade_textfield16ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_grade_textfield16ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_grade_textfield16ActionPerformed

    private void score_textfield17FocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_score_textfield17FocusGained
        // TODO add your handling code here:
    }//GEN-LAST:event_score_textfield17FocusGained

    private void score_textfield17FocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_score_textfield17FocusLost
        // TODO add your handling code here:
    }//GEN-LAST:event_score_textfield17FocusLost

    private void score_textfield17MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_score_textfield17MouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_score_textfield17MouseClicked

    private void score_textfield17ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_score_textfield17ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_score_textfield17ActionPerformed

    private void grade_textfield17FocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_grade_textfield17FocusGained
        // TODO add your handling code here:
    }//GEN-LAST:event_grade_textfield17FocusGained

    private void grade_textfield17FocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_grade_textfield17FocusLost
        // TODO add your handling code here:
    }//GEN-LAST:event_grade_textfield17FocusLost

    private void grade_textfield17MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_grade_textfield17MouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_grade_textfield17MouseClicked

    private void grade_textfield17ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_grade_textfield17ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_grade_textfield17ActionPerformed

    private void score_textfield18FocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_score_textfield18FocusGained
        // TODO add your handling code here:
    }//GEN-LAST:event_score_textfield18FocusGained

    private void score_textfield18FocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_score_textfield18FocusLost
        // TODO add your handling code here:
    }//GEN-LAST:event_score_textfield18FocusLost

    private void score_textfield18MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_score_textfield18MouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_score_textfield18MouseClicked

    private void score_textfield18ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_score_textfield18ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_score_textfield18ActionPerformed

    private void grade_textfield18FocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_grade_textfield18FocusGained
        // TODO add your handling code here:
    }//GEN-LAST:event_grade_textfield18FocusGained

    private void grade_textfield18FocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_grade_textfield18FocusLost
        // TODO add your handling code here:
    }//GEN-LAST:event_grade_textfield18FocusLost

    private void grade_textfield18MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_grade_textfield18MouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_grade_textfield18MouseClicked

    private void grade_textfield18ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_grade_textfield18ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_grade_textfield18ActionPerformed

    private void score_textfield19FocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_score_textfield19FocusGained
        // TODO add your handling code here:
    }//GEN-LAST:event_score_textfield19FocusGained

    private void score_textfield19FocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_score_textfield19FocusLost
        // TODO add your handling code here:
    }//GEN-LAST:event_score_textfield19FocusLost

    private void score_textfield19MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_score_textfield19MouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_score_textfield19MouseClicked

    private void score_textfield19ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_score_textfield19ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_score_textfield19ActionPerformed

    private void grade_textfield19FocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_grade_textfield19FocusGained
        // TODO add your handling code here:
    }//GEN-LAST:event_grade_textfield19FocusGained

    private void grade_textfield19FocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_grade_textfield19FocusLost
        // TODO add your handling code here:
    }//GEN-LAST:event_grade_textfield19FocusLost

    private void grade_textfield19MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_grade_textfield19MouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_grade_textfield19MouseClicked

    private void grade_textfield19ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_grade_textfield19ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_grade_textfield19ActionPerformed

    private void result3_textfieldCActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_result3_textfieldCActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_result3_textfieldCActionPerformed

    private void score_textfield20FocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_score_textfield20FocusGained
        // TODO add your handling code here:
    }//GEN-LAST:event_score_textfield20FocusGained

    private void score_textfield20FocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_score_textfield20FocusLost
        // TODO add your handling code here:
    }//GEN-LAST:event_score_textfield20FocusLost

    private void score_textfield20MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_score_textfield20MouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_score_textfield20MouseClicked

    private void score_textfield20ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_score_textfield20ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_score_textfield20ActionPerformed

    private void grade_textfield20FocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_grade_textfield20FocusGained
        // TODO add your handling code here:
    }//GEN-LAST:event_grade_textfield20FocusGained

    private void grade_textfield20FocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_grade_textfield20FocusLost
        // TODO add your handling code here:
    }//GEN-LAST:event_grade_textfield20FocusLost

    private void grade_textfield20MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_grade_textfield20MouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_grade_textfield20MouseClicked

    private void grade_textfield20ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_grade_textfield20ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_grade_textfield20ActionPerformed

    private void score_textfield21FocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_score_textfield21FocusGained
        // TODO add your handling code here:
    }//GEN-LAST:event_score_textfield21FocusGained

    private void score_textfield21FocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_score_textfield21FocusLost
        // TODO add your handling code here:
    }//GEN-LAST:event_score_textfield21FocusLost

    private void score_textfield21MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_score_textfield21MouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_score_textfield21MouseClicked

    private void score_textfield21ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_score_textfield21ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_score_textfield21ActionPerformed

    private void grade_textfield21FocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_grade_textfield21FocusGained
        // TODO add your handling code here:
    }//GEN-LAST:event_grade_textfield21FocusGained

    private void grade_textfield21FocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_grade_textfield21FocusLost
        // TODO add your handling code here:
    }//GEN-LAST:event_grade_textfield21FocusLost

    private void grade_textfield21MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_grade_textfield21MouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_grade_textfield21MouseClicked

    private void grade_textfield21ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_grade_textfield21ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_grade_textfield21ActionPerformed

    private void score_textfield22FocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_score_textfield22FocusGained
        // TODO add your handling code here:
    }//GEN-LAST:event_score_textfield22FocusGained

    private void score_textfield22FocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_score_textfield22FocusLost
        // TODO add your handling code here:
    }//GEN-LAST:event_score_textfield22FocusLost

    private void score_textfield22MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_score_textfield22MouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_score_textfield22MouseClicked

    private void score_textfield22ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_score_textfield22ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_score_textfield22ActionPerformed

    private void grade_textfield22FocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_grade_textfield22FocusGained
        // TODO add your handling code here:
    }//GEN-LAST:event_grade_textfield22FocusGained

    private void grade_textfield22FocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_grade_textfield22FocusLost
        // TODO add your handling code here:
    }//GEN-LAST:event_grade_textfield22FocusLost

    private void grade_textfield22MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_grade_textfield22MouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_grade_textfield22MouseClicked

    private void grade_textfield22ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_grade_textfield22ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_grade_textfield22ActionPerformed

    private void score_textfield23FocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_score_textfield23FocusGained
        // TODO add your handling code here:
    }//GEN-LAST:event_score_textfield23FocusGained

    private void score_textfield23FocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_score_textfield23FocusLost
        // TODO add your handling code here:
    }//GEN-LAST:event_score_textfield23FocusLost

    private void score_textfield23MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_score_textfield23MouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_score_textfield23MouseClicked

    private void score_textfield23ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_score_textfield23ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_score_textfield23ActionPerformed

    private void grade_textfield23FocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_grade_textfield23FocusGained
        // TODO add your handling code here:
    }//GEN-LAST:event_grade_textfield23FocusGained

    private void grade_textfield23FocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_grade_textfield23FocusLost
        // TODO add your handling code here:
    }//GEN-LAST:event_grade_textfield23FocusLost

    private void grade_textfield23MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_grade_textfield23MouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_grade_textfield23MouseClicked

    private void grade_textfield23ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_grade_textfield23ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_grade_textfield23ActionPerformed

    private void menuA_labelMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_menuA_labelMouseClicked
        // TODO add your handling code here:
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

        service_number_textfield.setText("");
        name.setText("> XXXXXXX");
        rank.setText("> XXXXXXX");
        unit.setText("> XXXXXXX");
        trade.setText("> XXXXXXX");
        company.setText("> XXXXXXX");
        wpn_textfield.setText("");
        test_date_combobox.setSelectedItem("DD");
        test_month_combobox.setSelectedItem("MM");
        test_year_combobox.setSelectedItem("YYYY");
        score_textfield.setText("");
        score_textfield1.setText("");
        score_textfield2.setText("");
        score_textfield3.setText("");
        score_textfield4.setText("");
        score_textfield5.setText("");
        score_textfield6.setText("");
        score_textfield7.setText("");
        score_textfield8.setText("");
        score_textfield9.setText("");
        score_textfield10.setText("");
        score_textfield11.setText("");
        grade_textfield.setText("");
        grade_textfield1.setText("");
        grade_textfield2.setText("");
        grade_textfield3.setText("");
        grade_textfield4.setText("");
        grade_textfield5.setText("");
        grade_textfield6.setText("");
        grade_textfield7.setText("");
        grade_textfield8.setText("");
        grade_textfield9.setText("");
        grade_textfield10.setText("");
        grade_textfield11.setText("");
        result_textfield.setText("");
        result_textfield1.setText("");
        result_textfield2.setText("");
    }//GEN-LAST:event_menuA_labelMouseClicked

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
            java.util.logging.Logger.getLogger(Firing.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Firing.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Firing.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Firing.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Firing().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JScrollPane ScrollPaneA;
    private javax.swing.JScrollPane ScrollPaneB;
    private javax.swing.JScrollPane ScrollPaneC;
    private javax.swing.JSeparator Separator;
    private javax.swing.JTable TableG_firing;
    private javax.swing.JLabel army_numberC;
    private javax.swing.JPanel bg;
    private javax.swing.JLabel changing_for_label;
    private javax.swing.JLabel company;
    private javax.swing.JLabel company_label;
    private javax.swing.JLabel date_label;
    private javax.swing.JLabel date_labelC;
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
    private javax.swing.JTextField grade_textfield;
    private javax.swing.JTextField grade_textfield1;
    private javax.swing.JTextField grade_textfield10;
    private javax.swing.JTextField grade_textfield11;
    private javax.swing.JTextField grade_textfield12;
    private javax.swing.JTextField grade_textfield13;
    private javax.swing.JTextField grade_textfield14;
    private javax.swing.JTextField grade_textfield15;
    private javax.swing.JTextField grade_textfield16;
    private javax.swing.JTextField grade_textfield17;
    private javax.swing.JTextField grade_textfield18;
    private javax.swing.JTextField grade_textfield19;
    private javax.swing.JTextField grade_textfield2;
    private javax.swing.JTextField grade_textfield20;
    private javax.swing.JTextField grade_textfield21;
    private javax.swing.JTextField grade_textfield22;
    private javax.swing.JTextField grade_textfield23;
    private javax.swing.JTextField grade_textfield3;
    private javax.swing.JTextField grade_textfield4;
    private javax.swing.JTextField grade_textfield5;
    private javax.swing.JTextField grade_textfield6;
    private javax.swing.JTextField grade_textfield7;
    private javax.swing.JTextField grade_textfield8;
    private javax.swing.JTextField grade_textfield9;
    private javax.swing.JSeparator grade_underline0;
    private javax.swing.JSeparator grade_underline1;
    private javax.swing.JSeparator grade_underline10;
    private javax.swing.JSeparator grade_underline11;
    private javax.swing.JSeparator grade_underline12;
    private javax.swing.JSeparator grade_underline13;
    private javax.swing.JSeparator grade_underline14;
    private javax.swing.JSeparator grade_underline15;
    private javax.swing.JSeparator grade_underline16;
    private javax.swing.JSeparator grade_underline17;
    private javax.swing.JSeparator grade_underline18;
    private javax.swing.JSeparator grade_underline19;
    private javax.swing.JSeparator grade_underline2;
    private javax.swing.JSeparator grade_underline20;
    private javax.swing.JSeparator grade_underline21;
    private javax.swing.JSeparator grade_underline22;
    private javax.swing.JSeparator grade_underline23;
    private javax.swing.JSeparator grade_underline3;
    private javax.swing.JSeparator grade_underline4;
    private javax.swing.JSeparator grade_underline5;
    private javax.swing.JSeparator grade_underline6;
    private javax.swing.JSeparator grade_underline7;
    private javax.swing.JSeparator grade_underline8;
    private javax.swing.JSeparator grade_underline9;
    private javax.swing.JPanel insert_buttonA;
    private javax.swing.JLabel insert_label;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JPanel menuA;
    private javax.swing.JLabel menuA_icon;
    private javax.swing.JLabel menuA_label;
    private javax.swing.JPanel menuB;
    private javax.swing.JLabel menuB_icon;
    private javax.swing.JLabel menuB_label;
    private javax.swing.JSeparator midA1;
    private javax.swing.JSeparator midC0;
    private javax.swing.JSeparator midC1;
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
    private javax.swing.JSeparator panelA_bottom_line;
    private javax.swing.JPanel panelB;
    private javax.swing.JPanel panelC;
    private javax.swing.JSeparator panelC_bottom_line;
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
    private javax.swing.JLabel result1_label;
    private javax.swing.JLabel result1_labelC;
    private javax.swing.JTextField result1_textfieldC;
    private javax.swing.JTextField result2_textfieldC;
    private javax.swing.JTextField result3_textfieldC;
    private javax.swing.JTextField result_textfield;
    private javax.swing.JTextField result_textfield1;
    private javax.swing.JTextField result_textfield2;
    private javax.swing.JLabel run100_label;
    private javax.swing.JLabel run100_labelC;
    private javax.swing.JLabel run200_label;
    private javax.swing.JLabel run200_labelC;
    private javax.swing.JLabel run300_label;
    private javax.swing.JLabel run300_labelC;
    private javax.swing.JLabel run400_label;
    private javax.swing.JLabel run400_labelC;
    private javax.swing.JTextField score_textfield;
    private javax.swing.JTextField score_textfield1;
    private javax.swing.JTextField score_textfield10;
    private javax.swing.JTextField score_textfield11;
    private javax.swing.JTextField score_textfield12;
    private javax.swing.JTextField score_textfield13;
    private javax.swing.JTextField score_textfield14;
    private javax.swing.JTextField score_textfield15;
    private javax.swing.JTextField score_textfield16;
    private javax.swing.JTextField score_textfield17;
    private javax.swing.JTextField score_textfield18;
    private javax.swing.JTextField score_textfield19;
    private javax.swing.JTextField score_textfield2;
    private javax.swing.JTextField score_textfield20;
    private javax.swing.JTextField score_textfield21;
    private javax.swing.JTextField score_textfield22;
    private javax.swing.JTextField score_textfield23;
    private javax.swing.JTextField score_textfield3;
    private javax.swing.JTextField score_textfield4;
    private javax.swing.JTextField score_textfield5;
    private javax.swing.JTextField score_textfield6;
    private javax.swing.JTextField score_textfield7;
    private javax.swing.JTextField score_textfield8;
    private javax.swing.JTextField score_textfield9;
    private javax.swing.JSeparator score_underline0;
    private javax.swing.JSeparator score_underline1;
    private javax.swing.JSeparator score_underline10;
    private javax.swing.JSeparator score_underline11;
    private javax.swing.JSeparator score_underline12;
    private javax.swing.JSeparator score_underline13;
    private javax.swing.JSeparator score_underline14;
    private javax.swing.JSeparator score_underline15;
    private javax.swing.JSeparator score_underline16;
    private javax.swing.JSeparator score_underline17;
    private javax.swing.JSeparator score_underline18;
    private javax.swing.JSeparator score_underline19;
    private javax.swing.JSeparator score_underline2;
    private javax.swing.JSeparator score_underline20;
    private javax.swing.JSeparator score_underline21;
    private javax.swing.JSeparator score_underline22;
    private javax.swing.JSeparator score_underline23;
    private javax.swing.JSeparator score_underline3;
    private javax.swing.JSeparator score_underline4;
    private javax.swing.JSeparator score_underline5;
    private javax.swing.JSeparator score_underline6;
    private javax.swing.JSeparator score_underline7;
    private javax.swing.JSeparator score_underline8;
    private javax.swing.JSeparator score_underline9;
    private javax.swing.JTextField service_numberB;
    private javax.swing.JLabel service_number_label;
    private javax.swing.JLabel service_number_labelB;
    private javax.swing.JLabel service_number_labelC;
    private javax.swing.JTextField service_number_textfield;
    private javax.swing.JSeparator service_number_underline;
    private javax.swing.JPanel side_pane;
    private javax.swing.JComboBox<String> test_date_combobox;
    private javax.swing.JComboBox<String> test_date_comboboxC;
    private javax.swing.JComboBox<String> test_month_combobox;
    private javax.swing.JComboBox<String> test_month_comboboxC;
    private javax.swing.JComboBox<String> test_year_combobox;
    private javax.swing.JComboBox<String> test_year_comboboxC;
    private javax.swing.JSeparator textfield_underline;
    private javax.swing.JLabel title_icon;
    private javax.swing.JLabel title_label;
    private javax.swing.JLabel trade;
    private javax.swing.JLabel trade_label;
    private javax.swing.JSeparator underline10;
    private javax.swing.JSeparator underline11;
    private javax.swing.JSeparator underline6;
    private javax.swing.JSeparator underline7;
    private javax.swing.JSeparator underline8;
    private javax.swing.JSeparator underline9;
    private javax.swing.JLabel unit;
    private javax.swing.JLabel unit_label;
    private javax.swing.JPanel update_buttonC;
    private javax.swing.JLabel update_labelC;
    private javax.swing.JLabel wpn_label;
    private javax.swing.JLabel wpn_labelC;
    private javax.swing.JTextField wpn_textfield;
    private javax.swing.JTextField wpn_textfieldC;
    private javax.swing.JSeparator wpn_underline;
    private javax.swing.JSeparator wpn_underline1;
    // End of variables declaration//GEN-END:variables
}
