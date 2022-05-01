
import java.awt.Desktop;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Toolkit;
import java.awt.print.PageFormat;
import java.awt.print.Printable;
import java.awt.print.PrinterException;
import java.awt.print.PrinterJob;
import java.io.File;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.text.SimpleDateFormat;
 
import java.time.LocalDate;
import java.time.Period;
import java.util.Date;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.table.DefaultTableModel;


/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author chise
 */

public class BPET_PPT extends javax.swing.JFrame {

    /**
     * Creates new form BPET_PPT
     */
    DefaultTableModel small, big;
    private int mouseX, mouseY;
    Database db=new Database();
    Connection con = db.create_connection(true);
    static int arrange=0,rroo=0,tick=0;
    boolean back_button=true;
    
    public BPET_PPT() {
        
        //for a centered frame
        Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
        this.setLocation(dim.width/2-this.getSize().width/2, dim.height/2-this.getSize().height/2);
        //for a centered frame
        
        initComponents();
        ScrollPaneA.getVerticalScrollBar().setUnitIncrement(16);
        ScrollPaneB.getVerticalScrollBar().setUnitIncrement(16);
        ScrollPaneC.getVerticalScrollBar().setUnitIncrement(16);
        fullscreen_ScrollPaneB.getVerticalScrollBar().setUnitIncrement(16);
       
        print_buttonA.setVisible(true);
        insert_buttonA.setVisible(true);
        print_buttonB.setVisible(false);
        edit_buttonB.setVisible(false);
        refresh_buttonB.setVisible(false);
        fullscreen_buttonB.setVisible(false);
        
        big=(DefaultTableModel) fullscreen_tableB.getModel();
        small=(DefaultTableModel) TableG_out.getModel();
    }
    private void printRecord(JPanel panel){
        // Create PrinterJob Here
        PrinterJob printerJob = PrinterJob.getPrinterJob();
        // Set Printer Job Name
        printerJob.setJobName("Print Record");
        // Set Printable
        printerJob.setPrintable(new Printable() {
            @Override
            public int print(Graphics graphics, PageFormat pageFormat, int pageIndex) throws PrinterException {
                // Check If No Printable Content
                if(pageIndex > 0){
                    return Printable.NO_SUCH_PAGE;
                }
                
                // Make 2D Graphics to map content
                Graphics2D graphics2D = (Graphics2D)graphics;
                graphics2D.translate(pageFormat.getImageableX()+10, pageFormat.getImageableY()+10);
                graphics2D.scale(0.68,0.7);
                
                // Now paint panel as graphics2D
                panel.paint(graphics2D);
                
                // return if page exists
                return Printable.PAGE_EXISTS;
            }
        });
        // Store printerDialog as boolean
        boolean returningResult = printerJob.printDialog();
        // check if dilog is showing
        if(returningResult){
            // Use try catch exeption for failure
            try{
                // Now call print method inside printerJob to print
                printerJob.print();
            }catch (PrinterException printerException){
                JOptionPane.showMessageDialog(this, "Print Error: " + printerException.getMessage());
            }
        }}
    private void view_table(String a){
     try{
         Statement st = con.createStatement();
         String query ="select *from bpet";
         ResultSet rs = st.executeQuery(query);
         small.setRowCount(0);
         big.setRowCount(0);
         String service_no,age,test_date;
         int count =0;
         String five_run,vrope,hrope,sixtym,ninem,result1,two_km,chinup,fivem_shuttle,situp,result2,hundred_m,name_;
         while(rs.next()){
             count=count+1;
             service_no =rs.getString("Service_no");
             age =rs.getString("Age");
             name_ = rs.getString("Name");
             test_date =rs.getString("Test_date");
             five_run =rs.getString("5km");
             vrope =rs.getString("vrope");
             hrope=rs.getString("hrope");
             sixtym =rs.getString("60m_sprint");
             ninem =rs.getString("9m_ditch");
             result1=rs.getString("Result1");
             two_km =rs.getString("2400m_run");
             chinup =rs.getString("Chinup");
             fivem_shuttle =rs.getString("5m_shuttle");
             hundred_m =rs.getString("100m_sprint");
             situp =rs.getString("sit_up");
             result2 =rs.getString("Result2");
             small.addRow(new Object[]{
                   count,
                   service_no,
                   name_,
                   result1,
                   result2
                });
            big.addRow(new Object[]{
                count,
                service_no,
                name_,
                age,
                test_date,
                five_run,
                vrope,
                hrope,
                sixtym,
                ninem,
                result1,
                two_km,
                chinup,
                fivem_shuttle,
                hundred_m,
                situp,
                result2
            });
            
         }
         if(a!=null){
             count=0;
             String sno=service_numberB.getText();
             arrange=100;
             String diff_query = "select *from bpet where service_no='"+sno+"'";
             rs=st.executeQuery(diff_query);
             small.setRowCount(0);
             big.setRowCount(0);
             while(rs.next()){
                 count=count+1;
                 service_no =rs.getString("Service_no");
             age =rs.getString("Age");
             name_ = rs.getString("Name");
             test_date =rs.getString("Test_date");
             five_run =rs.getString("5km");
             vrope =rs.getString("vrope");
             hrope=rs.getString("hrope");
             sixtym =rs.getString("60m_sprint");
             ninem =rs.getString("9m_ditch");
             result1=rs.getString("Result1");
             two_km =rs.getString("2400m_run");
             chinup =rs.getString("Chinup");
             fivem_shuttle =rs.getString("5m_shuttle");
             hundred_m =rs.getString("100m_sprint");
             situp =rs.getString("sit_up");
             result2 =rs.getString("Result2");
             small.addRow(new Object[]{
                    count,
                   service_no,
                   name_,
                   result1,
                   result2
                });
            big.addRow(new Object[]{
                count,
                service_no,
                name_,
                age,
                test_date,
                five_run,
                vrope,
                hrope,
                sixtym,
                ninem,
                result1,
                two_km,
                chinup,
                fivem_shuttle,
                hundred_m,
                situp,
                result2
            });
             }
             
         }
                 
     }
     catch(Exception e){
         System.out.print("\n"+e);
     }
    }
    
    
    private void upd(){  //change has happened
        try{
            Statement st = con.createStatement();
            String sno=army_numberC.getText();
            String age =age_textfieldC.getText();
            boolean check_age = age.matches("[0-9]+");
                String year = (String)test_year_comboboxC.getSelectedItem();
                String month = (String)test_month_comboboxC.getSelectedItem();
                String date = (String)test_date_comboboxC.getSelectedItem();
                String date_of_test = date+"-"+month+"-"+year;
                int y = Integer.parseInt(year);
                int m = Integer.parseInt(month);
                int d = Integer.parseInt(date);
                String five_run = five_run_textfieldC.getText();
                String vrope = Vrope_textfieldC.getText();
                String hrope = Hrope_textfieldC.getText();
                String sixtym = sixty_sprint_textfieldC.getText();
                String ninem = nine_ditch_textfieldC.getText();
                String result1 = result1_textfieldC.getText();
                String two_km = two_run_textfieldC.getText();
                String chinup = chin_up_textfieldC.getText();
                String fivem_shuttle = five_shuttle_textfieldC.getText();
                String hundred_m = hundred_sprint_textfieldC.getText();
                String situp = sit_up_textfieldC.getText();
                String result2 = result2_textfieldC.getText();
                String err ="";  
                String date_of="";
                boolean cal = !((y%4==0 && m==2 &&  (d>29)  )   || (  (m==2 || m==4 || m==6 || m==9 || m==11)  && d==31  )    ||  (m==2 && d>28 && y%4!=0));
                boolean all_check1= (five_run!=null && !five_run.trim().isEmpty())&& (vrope!=null && !vrope.trim().isEmpty())&&(hrope!=null && !hrope.trim().isEmpty())&&(sixtym!=null && !sixtym.trim().isEmpty())&&(ninem!=null && !ninem.trim().isEmpty())&&(result1!=null && !result1.trim().isEmpty());
                boolean all_check2 = (chinup!=null && !chinup.trim().isEmpty())&&(fivem_shuttle!=null && !fivem_shuttle.trim().isEmpty())&&(hundred_m!=null && !hundred_m.trim().isEmpty())&&(situp!=null && !situp.trim().isEmpty())&&(result2!=null && !result2.trim().isEmpty());
                if(cal&&all_check1&&all_check2){
//                    
                    String upd_q="update bpet set Age='"+age+"',Test_date='"+date_of_test+"',5km='"+five_run+"',vrope='"+vrope+"',hrope='"+hrope+"',60m_sprint='"+sixtym+"',9m_ditch='"+ninem+"',Result1='"+result1+"',2400m_run='"+two_km+"',Chinup='"+chinup+"',5m_shuttle='"+fivem_shuttle+"',100m_sprint='"+hundred_m+"',sit_up='"+situp+"',Result2='"+result2+"' where Test_date='"+big.getValueAt(rroo, 4).toString()+"' and Service_no='"+big.getValueAt(rroo, 1).toString()+"'";
                    st.executeUpdate(upd_q);
                    
                    JOptionPane.showMessageDialog(null,"Updation Successful");
                    
                    menuB.setBackground(new java.awt.Color(85,65,118));
                    menuA.setBackground(new java.awt.Color(64,43,100));
                    view_table(null);
                    arrange=0;
                    print_buttonB.setVisible(true);
                    edit_buttonB.setVisible(true);
                    refresh_buttonB.setVisible(true);
                    fullscreen_buttonB.setVisible(true);
                    print_buttonA.setVisible(false);
                    insert_buttonA.setVisible(false);

                    ScrollPaneA.setVisible(false);
                    panelB.setVisible(true);
                    ScrollPaneC.setVisible(false);
                    fullscreen_panelB.setVisible(false);
                    
                    String x=big.getValueAt(rroo, 3).toString();
                    String yyyy=big.getValueAt(rroo, 1).toString();
                    System.out.print("\n"+x+"  "+yyyy);
                    System.out.print("\nlalalalal");
                }
                else{
                    System.out.print("\nInsode else of first if");
                    if(!check_age){
                        err=err+"\nAge is in incorrect format";
                    }
                    if(!cal){
                        err=err+"\nIncorrect Test Date";
                    }
                    if(!all_check1 || !all_check2){
                        err=err+"\nMake sure no textfield is left empty";
                    }
                    
                    
                    if(!err.trim().isEmpty()){
                         JOptionPane.showMessageDialog(null, err);
                    }
                    
                }
        }
        catch(Exception e){
            System.out.print("\n"+e);
            String exe = e.getMessage();
                
                if(exe.contains("For input")){
                    JOptionPane.showMessageDialog(null,"\nPlease Re-Check Date Formats");
                }
                else
                    JOptionPane.showMessageDialog(null,"Data not updated!");
        }
    }
    private void bpet_insert(){ //change
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
        else{
            try{
                String age =age_1.getText();
                boolean check_age = age.matches("[0-9]+");
                //date of test
                String year = (String)test_year_combobox.getSelectedItem();
                String month = (String)test_month_combobox.getSelectedItem();
                String date = (String)test_date_combobox.getSelectedItem();
                String date_of_test = date+"-"+month+"-"+year;
                int y = Integer.parseInt(year);
                int m = Integer.parseInt(month);
                int d = Integer.parseInt(date);
                String name_="";
                if(!name.getText().contains(">")){
                name_ = name.getText();
                }
                String five_run = five_run_textfield.getText();
                String vrope = Vrope_textfield.getText();
                String hrope = Hrope_textfield.getText();
                String sixtym = sixty_sprint_textfield.getText();
                String ninem = nine_ditch_textfield.getText();
                String result1 = result1_textfield.getText();
                String two_km = two_run_textfield.getText();
                String chinup = chin_up_textfield.getText();
                String fivem_shuttle = five_shuttle_textfield.getText();
                String hundred_m = hundred_sprint_textfield.getText();
                String situp = sit_up_textfield.getText();
                String result2 = result2_textfield.getText();
                String err ="";
                boolean attach= true;
                
                boolean n = (!name_.trim().isEmpty());
                boolean cal = !((y%4==0 && m==2 &&  (d>29)  )   || (  (m==2 || m==4 || m==6 || m==9 || m==11)  && d==31  )    ||  (m==2 && d>28 && y%4!=0));
                boolean all_check1= (five_run!=null && !five_run.trim().isEmpty())&& (vrope!=null && !vrope.trim().isEmpty())&&(hrope!=null && !hrope.trim().isEmpty())&&(sixtym!=null && !sixtym.trim().isEmpty())&&(ninem!=null && !ninem.trim().isEmpty())&&(result1!=null && !result1.trim().isEmpty());
                boolean all_check2 = (chinup!=null && !chinup.trim().isEmpty())&&(fivem_shuttle!=null && !fivem_shuttle.trim().isEmpty())&&(hundred_m!=null && !hundred_m.trim().isEmpty())&&(situp!=null && !situp.trim().isEmpty())&&(result2!=null && !result2.trim().isEmpty());
                if(n&&(count1!=0 || count2!=0 ||count3!=0)&&check_age && all_check1 &&all_check2 &&cal){
                    //String queryy="insert into bpet "+"values(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
                    System.out.print("\nInside if!!");
                    String tempsno = sno.toUpperCase();
                    String query = "insert into bpet "+"values('"+sno+"',"+"'"+name_+"',"+"'"+age+"',"+"'"+date_of_test+"',"+"'"+five_run+"',"+"'"+vrope+"',"+"'"+hrope+"',"+"'"+sixtym+"',"+"'"+ninem+"',"+"'"+result1+"',"+"'"+two_km+"',"+"'"+chinup+"',"+"'"+fivem_shuttle+"',"+"'"+hundred_m+"',"+"'"+situp+"',"+"'"+result2+"')";
                    Statement stmt=con.createStatement();
                    stmt.executeUpdate(query);
                    JOptionPane.showMessageDialog(null,"Insertion seccessfull");
                    
                    name.setText("> XXXXXXX");
                    age_1.setText("> XXXXXXX");
                    trade.setText("> XXXXXXX");
                    unit.setText("> XXXXXXX");
                    rank.setText("> XXXXXXX");
                    
                    five_run_textfield.setText("");
                    Vrope_textfield.setText("");
                    Hrope_textfield.setText("");
                    sixty_sprint_textfield.setText("");
                    nine_ditch_textfield.setText("");
                    result1_textfield.setText("");
                    two_run_textfield.setText("");
                    chin_up_textfield.setText("");
                    five_shuttle_textfield.setText("");
                    hundred_sprint_textfield.setText("");
                    sit_up_textfield.setText("");
                    result2_textfield.setText("");
                    //age_textfield.setText("");
                    service_number_textfield.setText("");
                    
                    test_year_combobox.setSelectedIndex(0);
                    test_month_combobox.setSelectedIndex(0);
                    test_date_combobox.setSelectedIndex(0);
                    
                }
                else{
                    System.out.print("\nInsode else of first if");
                    if(!check_age){
                        err=err+"\nAge is in incorrect format";
                    }
                    if(!cal){
                        err=err+"\nIncorrect Test Date";
                    }
                    if(!all_check1 || !all_check2){
                        err=err+"\nMake sure no textfield is left empty";
                    }
                    
                    if(!n){
                        err=err+"\nPlease click the tick mark for a name to appear if Army number is Valid";
                    }
                    if(!err.trim().isEmpty()){
                         JOptionPane.showMessageDialog(null, err);
                    }
                    
                }
                
            }
            catch(Exception e){
                System.out.print(e);
                String exe = e.getMessage();
                if(exe.contains("Duplicate entry")){
                    JOptionPane.showMessageDialog(null,"Data not inserted!\nEntered ARMY NUMBER exists!");
                }
                else if(exe.contains("For input")){
                    JOptionPane.showMessageDialog(null,"\nPlease Re-Check Date Formats");
                }
                else
                    JOptionPane.showMessageDialog(null,"Data not inserted!");
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
        age_1 = new javax.swing.JLabel();
        trade_label = new javax.swing.JLabel();
        trade = new javax.swing.JLabel();
        unit_label = new javax.swing.JLabel();
        unit = new javax.swing.JLabel();
        Vrope_label = new javax.swing.JLabel();
        Hrope_label = new javax.swing.JLabel();
        five_run_label = new javax.swing.JLabel();
        midA1 = new javax.swing.JSeparator();
        from_date_label = new javax.swing.JLabel();
        age_label = new javax.swing.JLabel();
        test_date_combobox = new javax.swing.JComboBox<>();
        test_month_combobox = new javax.swing.JComboBox<>();
        test_year_combobox = new javax.swing.JComboBox<>();
        five_run_textfield = new javax.swing.JTextField();
        underline1 = new javax.swing.JSeparator();
        Vrope_textfield = new javax.swing.JTextField();
        underline2 = new javax.swing.JSeparator();
        Hrope_textfield = new javax.swing.JTextField();
        underline3 = new javax.swing.JSeparator();
        nine_ditch_label = new javax.swing.JLabel();
        result1_label = new javax.swing.JLabel();
        sixty_sprint_label = new javax.swing.JLabel();
        sixty_sprint_textfield = new javax.swing.JTextField();
        underline4 = new javax.swing.JSeparator();
        nine_ditch_textfield = new javax.swing.JTextField();
        underline5 = new javax.swing.JSeparator();
        result1_textfield = new javax.swing.JTextField();
        underline6 = new javax.swing.JSeparator();
        chin_up_label = new javax.swing.JLabel();
        five_shuttle_label = new javax.swing.JLabel();
        two_run_label = new javax.swing.JLabel();
        midA2 = new javax.swing.JSeparator();
        two_run_textfield = new javax.swing.JTextField();
        underline7 = new javax.swing.JSeparator();
        chin_up_textfield = new javax.swing.JTextField();
        underline8 = new javax.swing.JSeparator();
        five_shuttle_textfield = new javax.swing.JTextField();
        underline9 = new javax.swing.JSeparator();
        sit_up_label = new javax.swing.JLabel();
        result2_label = new javax.swing.JLabel();
        hundred_sprint_label = new javax.swing.JLabel();
        hundred_sprint_textfield = new javax.swing.JTextField();
        underline10 = new javax.swing.JSeparator();
        sit_up_textfield = new javax.swing.JTextField();
        underline11 = new javax.swing.JSeparator();
        result2_textfield = new javax.swing.JTextField();
        underline12 = new javax.swing.JSeparator();
        current_dateA = new javax.swing.JLabel();
        company = new javax.swing.JLabel();
        panelB = new javax.swing.JPanel();
        ScrollPaneB = new javax.swing.JScrollPane();
        TableG_out = new javax.swing.JTable();
        service_number_labelB = new javax.swing.JLabel();
        service_numberB = new javax.swing.JTextField();
        textfield_underline = new javax.swing.JSeparator();
        okayB = new javax.swing.JLabel();
        ScrollPaneC = new javax.swing.JScrollPane();
        panelC = new javax.swing.JPanel();
        panelC_bottom_line = new javax.swing.JSeparator();
        Vrope_labelC = new javax.swing.JLabel();
        Hrope_labelC = new javax.swing.JLabel();
        five_run_labelC = new javax.swing.JLabel();
        midC1 = new javax.swing.JSeparator();
        from_date_labelC = new javax.swing.JLabel();
        age_labelC = new javax.swing.JLabel();
        test_date_comboboxC = new javax.swing.JComboBox<>();
        test_month_comboboxC = new javax.swing.JComboBox<>();
        test_year_comboboxC = new javax.swing.JComboBox<>();
        five_run_textfieldC = new javax.swing.JTextField();
        underline13 = new javax.swing.JSeparator();
        Vrope_textfieldC = new javax.swing.JTextField();
        underline14 = new javax.swing.JSeparator();
        Hrope_textfieldC = new javax.swing.JTextField();
        underline15 = new javax.swing.JSeparator();
        age_textfieldC = new javax.swing.JTextField();
        age_underlineC = new javax.swing.JSeparator();
        nine_ditch_labelC = new javax.swing.JLabel();
        result1_labelC = new javax.swing.JLabel();
        sixty_sprint_labelC = new javax.swing.JLabel();
        sixty_sprint_textfieldC = new javax.swing.JTextField();
        underline16 = new javax.swing.JSeparator();
        nine_ditch_textfieldC = new javax.swing.JTextField();
        underline17 = new javax.swing.JSeparator();
        result1_textfieldC = new javax.swing.JTextField();
        underline18 = new javax.swing.JSeparator();
        chin_up_labelC = new javax.swing.JLabel();
        five_shuttle_labelC = new javax.swing.JLabel();
        two_run_labelC = new javax.swing.JLabel();
        midC2 = new javax.swing.JSeparator();
        two_run_textfieldC = new javax.swing.JTextField();
        underline19 = new javax.swing.JSeparator();
        chin_up_textfieldC = new javax.swing.JTextField();
        underline20 = new javax.swing.JSeparator();
        five_shuttle_textfieldC = new javax.swing.JTextField();
        underline21 = new javax.swing.JSeparator();
        sit_up_labelC = new javax.swing.JLabel();
        result2_labelC = new javax.swing.JLabel();
        hundred_sprint_labelC = new javax.swing.JLabel();
        hundred_sprint_textfieldC = new javax.swing.JTextField();
        underline22 = new javax.swing.JSeparator();
        sit_up_textfieldC = new javax.swing.JTextField();
        underline23 = new javax.swing.JSeparator();
        result2_textfieldC = new javax.swing.JTextField();
        underline24 = new javax.swing.JSeparator();
        service_number_labelC = new javax.swing.JLabel();
        name_labelC = new javax.swing.JLabel();
        army_numberC = new javax.swing.JLabel();
        rank_labelC = new javax.swing.JLabel();
        rankC = new javax.swing.JLabel();
        nameC = new javax.swing.JLabel();
        midC4 = new javax.swing.JSeparator();
        changing_for_label = new javax.swing.JLabel();
        midC0 = new javax.swing.JSeparator();
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
        setBounds(new java.awt.Rectangle(0, 0, 1280, 720));
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
        title_label.setText("BPET/PPT");
        side_pane.add(title_label, new org.netbeans.lib.awtextra.AbsoluteConstraints(90, 135, 190, 40));

        title_icon.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        title_icon.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/PNGS/bpet white.png"))); // NOI18N
        side_pane.add(title_icon, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 110, 60, 70));

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
        panelA.add(panelA_bottom_line, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 1080, 910, -1));

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

        age_1.setFont(new java.awt.Font("Roboto Bk", 0, 24)); // NOI18N
        age_1.setForeground(new java.awt.Color(54, 33, 89));
        age_1.setText("> XXXXXXX");
        panelA.add(age_1, new org.netbeans.lib.awtextra.AbsoluteConstraints(350, 300, -1, 30));

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
        panelA.add(unit_label, new org.netbeans.lib.awtextra.AbsoluteConstraints(620, 260, -1, 50));

        unit.setFont(new java.awt.Font("Roboto Bk", 0, 24)); // NOI18N
        unit.setForeground(new java.awt.Color(54, 33, 89));
        unit.setText("> XXXXXXX");
        panelA.add(unit, new org.netbeans.lib.awtextra.AbsoluteConstraints(620, 300, -1, 30));

        Vrope_label.setBackground(new java.awt.Color(255, 255, 255));
        Vrope_label.setFont(new java.awt.Font("Montserrat", 0, 24)); // NOI18N
        Vrope_label.setForeground(new java.awt.Color(66, 50, 77));
        Vrope_label.setText("V Rope");
        panelA.add(Vrope_label, new org.netbeans.lib.awtextra.AbsoluteConstraints(350, 550, -1, 40));

        Hrope_label.setBackground(new java.awt.Color(255, 255, 255));
        Hrope_label.setFont(new java.awt.Font("Montserrat", 0, 24)); // NOI18N
        Hrope_label.setForeground(new java.awt.Color(66, 50, 77));
        Hrope_label.setText("H Rope");
        panelA.add(Hrope_label, new org.netbeans.lib.awtextra.AbsoluteConstraints(620, 550, -1, 40));

        five_run_label.setBackground(new java.awt.Color(255, 255, 255));
        five_run_label.setFont(new java.awt.Font("Montserrat", 0, 24)); // NOI18N
        five_run_label.setForeground(new java.awt.Color(66, 50, 77));
        five_run_label.setText("5 km Run");
        panelA.add(five_run_label, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 550, -1, 40));

        midA1.setForeground(new java.awt.Color(204, 204, 255));
        panelA.add(midA1, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 510, 570, 10));

        from_date_label.setBackground(new java.awt.Color(255, 255, 255));
        from_date_label.setFont(new java.awt.Font("Montserrat", 0, 28)); // NOI18N
        from_date_label.setForeground(new java.awt.Color(51, 51, 51));
        from_date_label.setText("Date of Test");
        panelA.add(from_date_label, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 360, -1, 40));

        age_label.setBackground(new java.awt.Color(255, 255, 255));
        age_label.setFont(new java.awt.Font("Montserrat", 0, 28)); // NOI18N
        age_label.setForeground(new java.awt.Color(51, 51, 51));
        age_label.setText("Age");
        panelA.add(age_label, new org.netbeans.lib.awtextra.AbsoluteConstraints(350, 260, -1, 50));

        test_date_combobox.setFont(new java.awt.Font("Roboto", 0, 18)); // NOI18N
        test_date_combobox.setForeground(new java.awt.Color(44, 62, 80));
        test_date_combobox.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "DD", "01", "02", "03", "04", "05", "06", "07", "08", "09", "10", "11", "12", "13", "14", "15", "16", "17", "18", "19", "20", "21", "22", "23", "24", "25", "26", "27", "28", "29", "30", "31" }));
        test_date_combobox.setBorder(null);
        panelA.add(test_date_combobox, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 400, 100, 40));

        test_month_combobox.setFont(new java.awt.Font("Roboto", 0, 18)); // NOI18N
        test_month_combobox.setForeground(new java.awt.Color(44, 62, 80));
        test_month_combobox.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "MM", "01", "02", "03", "04", "05", "06", "07", "08", "09", "10", "11", "12" }));
        test_month_combobox.setBorder(null);
        panelA.add(test_month_combobox, new org.netbeans.lib.awtextra.AbsoluteConstraints(190, 400, 100, 40));

        test_year_combobox.setFont(new java.awt.Font("Roboto", 0, 18)); // NOI18N
        test_year_combobox.setForeground(new java.awt.Color(44, 62, 80));
        test_year_combobox.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "YYYY", "1950", "1951", "1952", "1953", "1954", "1955", "1956", "1957", "1958", "1959", "1960", "1961", "1962", "1963", "1964", "1965", "1966", "1967", "1968", "1969", "1970", "1971", "1972", "1973", "1974", "1975", "1976", "1977", "1978", "1979", "1980", "1981", "1982", "1983", "1984", "1985", "1986", "1987", "1988", "1989", "1990", "1991", "1992", "1993", "1994", "1995", "1996", "1997", "1998", "1999", "2000", "2001", "2002", "2003", "2004", "2005", "2006", "2007", "2008", "2009", "2010", "2011", "2012", "2013", "2014", "2015", "2016", "2017", "2018", "2019", "2020", "2021", "2022", "2023", "2024", "2025", "2026", "2027", "2028", "2029", "2030", "2031", "2032", "2033", "2034", "2035", "2036", "2037", "2038", "2039", "2040", "2041", "2042", "2043", "2044", "2045", "2046", "2047", "2048", "2049", "2050", "2051", "2052", "2053", "2054", "2055", "2056", "2057", "2058", "2059", "2060", "2061", "2062", "2063", "2064", "2065", "2066", "2067", "2068", "2069", "2070", "2071", "2072", "2073", "2074", "2075", "2076", "2077", "2078", "2079", "2080", "2081", "2082", "2083", "2084", "2085", "2086", "2087", "2088", "2089", "2090", "2091", "2092", "2093", "2094", "2095", "2096", "2097", "2098", "2099", "2100", " " }));
        test_year_combobox.setBorder(null);
        panelA.add(test_year_combobox, new org.netbeans.lib.awtextra.AbsoluteConstraints(300, 400, 100, 40));

        five_run_textfield.setFont(new java.awt.Font("Roboto", 0, 24)); // NOI18N
        five_run_textfield.setForeground(new java.awt.Color(54, 33, 89));
        five_run_textfield.setBorder(null);
        five_run_textfield.setMargin(new java.awt.Insets(2, 4, 2, 2));
        five_run_textfield.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                five_run_textfieldActionPerformed(evt);
            }
        });
        panelA.add(five_run_textfield, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 590, 210, 40));

        underline1.setForeground(new java.awt.Color(54, 33, 89));
        panelA.add(underline1, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 630, 210, 20));

        Vrope_textfield.setFont(new java.awt.Font("Roboto", 0, 24)); // NOI18N
        Vrope_textfield.setForeground(new java.awt.Color(54, 33, 89));
        Vrope_textfield.setBorder(null);
        Vrope_textfield.setMargin(new java.awt.Insets(2, 4, 2, 2));
        Vrope_textfield.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Vrope_textfieldActionPerformed(evt);
            }
        });
        panelA.add(Vrope_textfield, new org.netbeans.lib.awtextra.AbsoluteConstraints(350, 590, 210, 40));

        underline2.setForeground(new java.awt.Color(54, 33, 89));
        panelA.add(underline2, new org.netbeans.lib.awtextra.AbsoluteConstraints(350, 630, 210, 20));

        Hrope_textfield.setFont(new java.awt.Font("Roboto", 0, 24)); // NOI18N
        Hrope_textfield.setForeground(new java.awt.Color(54, 33, 89));
        Hrope_textfield.setBorder(null);
        Hrope_textfield.setMargin(new java.awt.Insets(2, 4, 2, 2));
        Hrope_textfield.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Hrope_textfieldActionPerformed(evt);
            }
        });
        panelA.add(Hrope_textfield, new org.netbeans.lib.awtextra.AbsoluteConstraints(620, 590, 210, 40));

        underline3.setForeground(new java.awt.Color(54, 33, 89));
        panelA.add(underline3, new org.netbeans.lib.awtextra.AbsoluteConstraints(620, 630, 210, 20));

        nine_ditch_label.setBackground(new java.awt.Color(255, 255, 255));
        nine_ditch_label.setFont(new java.awt.Font("Montserrat", 0, 24)); // NOI18N
        nine_ditch_label.setForeground(new java.awt.Color(66, 50, 77));
        nine_ditch_label.setText("9 m ditch");
        panelA.add(nine_ditch_label, new org.netbeans.lib.awtextra.AbsoluteConstraints(350, 650, -1, 40));

        result1_label.setBackground(new java.awt.Color(255, 255, 255));
        result1_label.setFont(new java.awt.Font("Montserrat", 0, 24)); // NOI18N
        result1_label.setForeground(new java.awt.Color(66, 50, 77));
        result1_label.setText("Result");
        panelA.add(result1_label, new org.netbeans.lib.awtextra.AbsoluteConstraints(620, 650, -1, 40));

        sixty_sprint_label.setBackground(new java.awt.Color(255, 255, 255));
        sixty_sprint_label.setFont(new java.awt.Font("Montserrat", 0, 24)); // NOI18N
        sixty_sprint_label.setForeground(new java.awt.Color(66, 50, 77));
        sixty_sprint_label.setText("60 m Sprint");
        panelA.add(sixty_sprint_label, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 650, -1, 40));

        sixty_sprint_textfield.setFont(new java.awt.Font("Roboto", 0, 24)); // NOI18N
        sixty_sprint_textfield.setForeground(new java.awt.Color(54, 33, 89));
        sixty_sprint_textfield.setBorder(null);
        sixty_sprint_textfield.setMargin(new java.awt.Insets(2, 4, 2, 2));
        sixty_sprint_textfield.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                sixty_sprint_textfieldActionPerformed(evt);
            }
        });
        panelA.add(sixty_sprint_textfield, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 690, 210, 40));

        underline4.setForeground(new java.awt.Color(54, 33, 89));
        panelA.add(underline4, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 730, 210, 20));

        nine_ditch_textfield.setFont(new java.awt.Font("Roboto", 0, 24)); // NOI18N
        nine_ditch_textfield.setForeground(new java.awt.Color(54, 33, 89));
        nine_ditch_textfield.setBorder(null);
        nine_ditch_textfield.setMargin(new java.awt.Insets(2, 4, 2, 2));
        nine_ditch_textfield.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                nine_ditch_textfieldActionPerformed(evt);
            }
        });
        panelA.add(nine_ditch_textfield, new org.netbeans.lib.awtextra.AbsoluteConstraints(350, 690, 210, 40));

        underline5.setForeground(new java.awt.Color(54, 33, 89));
        panelA.add(underline5, new org.netbeans.lib.awtextra.AbsoluteConstraints(350, 730, 210, 20));

        result1_textfield.setFont(new java.awt.Font("Roboto", 0, 24)); // NOI18N
        result1_textfield.setForeground(new java.awt.Color(54, 33, 89));
        result1_textfield.setBorder(null);
        result1_textfield.setMargin(new java.awt.Insets(2, 4, 2, 2));
        result1_textfield.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                result1_textfieldActionPerformed(evt);
            }
        });
        panelA.add(result1_textfield, new org.netbeans.lib.awtextra.AbsoluteConstraints(620, 690, 210, 40));

        underline6.setForeground(new java.awt.Color(54, 33, 89));
        panelA.add(underline6, new org.netbeans.lib.awtextra.AbsoluteConstraints(620, 730, 210, 20));

        chin_up_label.setBackground(new java.awt.Color(255, 255, 255));
        chin_up_label.setFont(new java.awt.Font("Montserrat", 0, 24)); // NOI18N
        chin_up_label.setForeground(new java.awt.Color(66, 50, 77));
        chin_up_label.setText("Chin Up");
        panelA.add(chin_up_label, new org.netbeans.lib.awtextra.AbsoluteConstraints(350, 830, -1, 40));

        five_shuttle_label.setBackground(new java.awt.Color(255, 255, 255));
        five_shuttle_label.setFont(new java.awt.Font("Montserrat", 0, 24)); // NOI18N
        five_shuttle_label.setForeground(new java.awt.Color(66, 50, 77));
        five_shuttle_label.setText("5 m Shuttle");
        panelA.add(five_shuttle_label, new org.netbeans.lib.awtextra.AbsoluteConstraints(620, 830, -1, 40));

        two_run_label.setBackground(new java.awt.Color(255, 255, 255));
        two_run_label.setFont(new java.awt.Font("Montserrat", 0, 24)); // NOI18N
        two_run_label.setForeground(new java.awt.Color(66, 50, 77));
        two_run_label.setText("2.4 km Run");
        panelA.add(two_run_label, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 830, -1, 40));

        midA2.setForeground(new java.awt.Color(204, 204, 255));
        panelA.add(midA2, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 790, 570, 10));

        two_run_textfield.setFont(new java.awt.Font("Roboto", 0, 24)); // NOI18N
        two_run_textfield.setForeground(new java.awt.Color(54, 33, 89));
        two_run_textfield.setBorder(null);
        two_run_textfield.setMargin(new java.awt.Insets(2, 4, 2, 2));
        two_run_textfield.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                two_run_textfieldActionPerformed(evt);
            }
        });
        panelA.add(two_run_textfield, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 870, 210, 40));

        underline7.setForeground(new java.awt.Color(54, 33, 89));
        panelA.add(underline7, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 910, 210, 20));

        chin_up_textfield.setFont(new java.awt.Font("Roboto", 0, 24)); // NOI18N
        chin_up_textfield.setForeground(new java.awt.Color(54, 33, 89));
        chin_up_textfield.setBorder(null);
        chin_up_textfield.setMargin(new java.awt.Insets(2, 4, 2, 2));
        chin_up_textfield.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                chin_up_textfieldActionPerformed(evt);
            }
        });
        panelA.add(chin_up_textfield, new org.netbeans.lib.awtextra.AbsoluteConstraints(350, 870, 210, 40));

        underline8.setForeground(new java.awt.Color(54, 33, 89));
        panelA.add(underline8, new org.netbeans.lib.awtextra.AbsoluteConstraints(350, 910, 210, 20));

        five_shuttle_textfield.setFont(new java.awt.Font("Roboto", 0, 24)); // NOI18N
        five_shuttle_textfield.setForeground(new java.awt.Color(54, 33, 89));
        five_shuttle_textfield.setBorder(null);
        five_shuttle_textfield.setMargin(new java.awt.Insets(2, 4, 2, 2));
        five_shuttle_textfield.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                five_shuttle_textfieldActionPerformed(evt);
            }
        });
        panelA.add(five_shuttle_textfield, new org.netbeans.lib.awtextra.AbsoluteConstraints(620, 870, 210, 40));

        underline9.setForeground(new java.awt.Color(54, 33, 89));
        panelA.add(underline9, new org.netbeans.lib.awtextra.AbsoluteConstraints(620, 910, 210, 20));

        sit_up_label.setBackground(new java.awt.Color(255, 255, 255));
        sit_up_label.setFont(new java.awt.Font("Montserrat", 0, 24)); // NOI18N
        sit_up_label.setForeground(new java.awt.Color(66, 50, 77));
        sit_up_label.setText("Sit Up");
        panelA.add(sit_up_label, new org.netbeans.lib.awtextra.AbsoluteConstraints(350, 930, -1, 40));

        result2_label.setBackground(new java.awt.Color(255, 255, 255));
        result2_label.setFont(new java.awt.Font("Montserrat", 0, 24)); // NOI18N
        result2_label.setForeground(new java.awt.Color(66, 50, 77));
        result2_label.setText("Result");
        panelA.add(result2_label, new org.netbeans.lib.awtextra.AbsoluteConstraints(620, 930, -1, 40));

        hundred_sprint_label.setBackground(new java.awt.Color(255, 255, 255));
        hundred_sprint_label.setFont(new java.awt.Font("Montserrat", 0, 24)); // NOI18N
        hundred_sprint_label.setForeground(new java.awt.Color(66, 50, 77));
        hundred_sprint_label.setText("100 m Sprint");
        panelA.add(hundred_sprint_label, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 930, -1, 40));

        hundred_sprint_textfield.setFont(new java.awt.Font("Roboto", 0, 24)); // NOI18N
        hundred_sprint_textfield.setForeground(new java.awt.Color(54, 33, 89));
        hundred_sprint_textfield.setBorder(null);
        hundred_sprint_textfield.setMargin(new java.awt.Insets(2, 4, 2, 2));
        hundred_sprint_textfield.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                hundred_sprint_textfieldActionPerformed(evt);
            }
        });
        panelA.add(hundred_sprint_textfield, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 970, 210, 40));

        underline10.setForeground(new java.awt.Color(54, 33, 89));
        panelA.add(underline10, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 1010, 210, 20));

        sit_up_textfield.setFont(new java.awt.Font("Roboto", 0, 24)); // NOI18N
        sit_up_textfield.setForeground(new java.awt.Color(54, 33, 89));
        sit_up_textfield.setBorder(null);
        sit_up_textfield.setMargin(new java.awt.Insets(2, 4, 2, 2));
        sit_up_textfield.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                sit_up_textfieldActionPerformed(evt);
            }
        });
        panelA.add(sit_up_textfield, new org.netbeans.lib.awtextra.AbsoluteConstraints(350, 970, 210, 40));

        underline11.setForeground(new java.awt.Color(54, 33, 89));
        panelA.add(underline11, new org.netbeans.lib.awtextra.AbsoluteConstraints(350, 1010, 210, 20));

        result2_textfield.setFont(new java.awt.Font("Roboto", 0, 24)); // NOI18N
        result2_textfield.setForeground(new java.awt.Color(54, 33, 89));
        result2_textfield.setBorder(null);
        result2_textfield.setMargin(new java.awt.Insets(2, 4, 2, 2));
        result2_textfield.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                result2_textfieldActionPerformed(evt);
            }
        });
        panelA.add(result2_textfield, new org.netbeans.lib.awtextra.AbsoluteConstraints(620, 970, 210, 40));

        underline12.setForeground(new java.awt.Color(54, 33, 89));
        panelA.add(underline12, new org.netbeans.lib.awtextra.AbsoluteConstraints(620, 1010, 210, 20));

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
        panelA.add(current_dateA, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 450, -1, -1));

        company.setFont(new java.awt.Font("Roboto Bk", 0, 24)); // NOI18N
        company.setForeground(new java.awt.Color(54, 33, 89));
        company.setText("> XXXXXXX");
        panelA.add(company, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 300, -1, 30));

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
                "S. No.", "Service number", "Name", "Result 1", "Result 2"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, true, true
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

        panelC_bottom_line.setForeground(new java.awt.Color(54, 33, 89));
        panelC.add(panelC_bottom_line, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 960, 910, -1));

        Vrope_labelC.setBackground(new java.awt.Color(255, 255, 255));
        Vrope_labelC.setFont(new java.awt.Font("Montserrat", 0, 24)); // NOI18N
        Vrope_labelC.setForeground(new java.awt.Color(66, 50, 77));
        Vrope_labelC.setText("V Rope");
        panelC.add(Vrope_labelC, new org.netbeans.lib.awtextra.AbsoluteConstraints(350, 430, -1, 40));

        Hrope_labelC.setBackground(new java.awt.Color(255, 255, 255));
        Hrope_labelC.setFont(new java.awt.Font("Montserrat", 0, 24)); // NOI18N
        Hrope_labelC.setForeground(new java.awt.Color(66, 50, 77));
        Hrope_labelC.setText("H Rope");
        panelC.add(Hrope_labelC, new org.netbeans.lib.awtextra.AbsoluteConstraints(620, 430, -1, 40));

        five_run_labelC.setBackground(new java.awt.Color(255, 255, 255));
        five_run_labelC.setFont(new java.awt.Font("Montserrat", 0, 24)); // NOI18N
        five_run_labelC.setForeground(new java.awt.Color(66, 50, 77));
        five_run_labelC.setText("5 km Run");
        panelC.add(five_run_labelC, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 430, -1, 40));

        midC1.setForeground(new java.awt.Color(204, 204, 255));
        panelC.add(midC1, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 400, 570, 10));

        from_date_labelC.setBackground(new java.awt.Color(255, 255, 255));
        from_date_labelC.setFont(new java.awt.Font("Montserrat", 0, 28)); // NOI18N
        from_date_labelC.setForeground(new java.awt.Color(51, 51, 51));
        from_date_labelC.setText("Date of Test");
        panelC.add(from_date_labelC, new org.netbeans.lib.awtextra.AbsoluteConstraints(350, 250, -1, 40));

        age_labelC.setBackground(new java.awt.Color(255, 255, 255));
        age_labelC.setFont(new java.awt.Font("Montserrat", 0, 28)); // NOI18N
        age_labelC.setForeground(new java.awt.Color(51, 51, 51));
        age_labelC.setText("Age");
        panelC.add(age_labelC, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 250, -1, 40));

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

        five_run_textfieldC.setFont(new java.awt.Font("Roboto", 0, 24)); // NOI18N
        five_run_textfieldC.setForeground(new java.awt.Color(54, 33, 89));
        five_run_textfieldC.setBorder(null);
        five_run_textfieldC.setMargin(new java.awt.Insets(2, 4, 2, 2));
        five_run_textfieldC.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                five_run_textfieldCActionPerformed(evt);
            }
        });
        panelC.add(five_run_textfieldC, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 470, 210, 40));

        underline13.setForeground(new java.awt.Color(54, 33, 89));
        panelC.add(underline13, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 510, 210, 20));

        Vrope_textfieldC.setFont(new java.awt.Font("Roboto", 0, 24)); // NOI18N
        Vrope_textfieldC.setForeground(new java.awt.Color(54, 33, 89));
        Vrope_textfieldC.setBorder(null);
        Vrope_textfieldC.setMargin(new java.awt.Insets(2, 4, 2, 2));
        Vrope_textfieldC.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Vrope_textfieldCActionPerformed(evt);
            }
        });
        panelC.add(Vrope_textfieldC, new org.netbeans.lib.awtextra.AbsoluteConstraints(350, 470, 210, 40));

        underline14.setForeground(new java.awt.Color(54, 33, 89));
        panelC.add(underline14, new org.netbeans.lib.awtextra.AbsoluteConstraints(350, 510, 210, 20));

        Hrope_textfieldC.setFont(new java.awt.Font("Roboto", 0, 24)); // NOI18N
        Hrope_textfieldC.setForeground(new java.awt.Color(54, 33, 89));
        Hrope_textfieldC.setBorder(null);
        Hrope_textfieldC.setMargin(new java.awt.Insets(2, 4, 2, 2));
        Hrope_textfieldC.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Hrope_textfieldCActionPerformed(evt);
            }
        });
        panelC.add(Hrope_textfieldC, new org.netbeans.lib.awtextra.AbsoluteConstraints(620, 470, 210, 40));

        underline15.setForeground(new java.awt.Color(54, 33, 89));
        panelC.add(underline15, new org.netbeans.lib.awtextra.AbsoluteConstraints(620, 510, 210, 20));

        age_textfieldC.setFont(new java.awt.Font("Roboto", 0, 24)); // NOI18N
        age_textfieldC.setForeground(new java.awt.Color(54, 33, 89));
        age_textfieldC.setBorder(null);
        age_textfieldC.setMargin(new java.awt.Insets(2, 4, 2, 2));
        age_textfieldC.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                age_textfieldCActionPerformed(evt);
            }
        });
        panelC.add(age_textfieldC, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 290, 210, 40));

        age_underlineC.setForeground(new java.awt.Color(54, 33, 89));
        panelC.add(age_underlineC, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 330, 210, 20));

        nine_ditch_labelC.setBackground(new java.awt.Color(255, 255, 255));
        nine_ditch_labelC.setFont(new java.awt.Font("Montserrat", 0, 24)); // NOI18N
        nine_ditch_labelC.setForeground(new java.awt.Color(66, 50, 77));
        nine_ditch_labelC.setText("9 m ditch");
        panelC.add(nine_ditch_labelC, new org.netbeans.lib.awtextra.AbsoluteConstraints(350, 530, -1, 40));

        result1_labelC.setBackground(new java.awt.Color(255, 255, 255));
        result1_labelC.setFont(new java.awt.Font("Montserrat", 0, 24)); // NOI18N
        result1_labelC.setForeground(new java.awt.Color(66, 50, 77));
        result1_labelC.setText("Result");
        panelC.add(result1_labelC, new org.netbeans.lib.awtextra.AbsoluteConstraints(620, 530, -1, 40));

        sixty_sprint_labelC.setBackground(new java.awt.Color(255, 255, 255));
        sixty_sprint_labelC.setFont(new java.awt.Font("Montserrat", 0, 24)); // NOI18N
        sixty_sprint_labelC.setForeground(new java.awt.Color(66, 50, 77));
        sixty_sprint_labelC.setText("60 m Sprint");
        panelC.add(sixty_sprint_labelC, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 530, -1, 40));

        sixty_sprint_textfieldC.setFont(new java.awt.Font("Roboto", 0, 24)); // NOI18N
        sixty_sprint_textfieldC.setForeground(new java.awt.Color(54, 33, 89));
        sixty_sprint_textfieldC.setBorder(null);
        sixty_sprint_textfieldC.setMargin(new java.awt.Insets(2, 4, 2, 2));
        sixty_sprint_textfieldC.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                sixty_sprint_textfieldCActionPerformed(evt);
            }
        });
        panelC.add(sixty_sprint_textfieldC, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 570, 210, 40));

        underline16.setForeground(new java.awt.Color(54, 33, 89));
        panelC.add(underline16, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 610, 210, 20));

        nine_ditch_textfieldC.setFont(new java.awt.Font("Roboto", 0, 24)); // NOI18N
        nine_ditch_textfieldC.setForeground(new java.awt.Color(54, 33, 89));
        nine_ditch_textfieldC.setBorder(null);
        nine_ditch_textfieldC.setMargin(new java.awt.Insets(2, 4, 2, 2));
        nine_ditch_textfieldC.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                nine_ditch_textfieldCActionPerformed(evt);
            }
        });
        panelC.add(nine_ditch_textfieldC, new org.netbeans.lib.awtextra.AbsoluteConstraints(350, 570, 210, 40));

        underline17.setForeground(new java.awt.Color(54, 33, 89));
        panelC.add(underline17, new org.netbeans.lib.awtextra.AbsoluteConstraints(350, 610, 210, 20));

        result1_textfieldC.setFont(new java.awt.Font("Roboto", 0, 24)); // NOI18N
        result1_textfieldC.setForeground(new java.awt.Color(54, 33, 89));
        result1_textfieldC.setBorder(null);
        result1_textfieldC.setMargin(new java.awt.Insets(2, 4, 2, 2));
        result1_textfieldC.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                result1_textfieldCActionPerformed(evt);
            }
        });
        panelC.add(result1_textfieldC, new org.netbeans.lib.awtextra.AbsoluteConstraints(620, 570, 210, 40));

        underline18.setForeground(new java.awt.Color(54, 33, 89));
        panelC.add(underline18, new org.netbeans.lib.awtextra.AbsoluteConstraints(620, 610, 210, 20));

        chin_up_labelC.setBackground(new java.awt.Color(255, 255, 255));
        chin_up_labelC.setFont(new java.awt.Font("Montserrat", 0, 24)); // NOI18N
        chin_up_labelC.setForeground(new java.awt.Color(66, 50, 77));
        chin_up_labelC.setText("Chin Up");
        panelC.add(chin_up_labelC, new org.netbeans.lib.awtextra.AbsoluteConstraints(350, 710, -1, 40));

        five_shuttle_labelC.setBackground(new java.awt.Color(255, 255, 255));
        five_shuttle_labelC.setFont(new java.awt.Font("Montserrat", 0, 24)); // NOI18N
        five_shuttle_labelC.setForeground(new java.awt.Color(66, 50, 77));
        five_shuttle_labelC.setText("5 m Shuttle");
        panelC.add(five_shuttle_labelC, new org.netbeans.lib.awtextra.AbsoluteConstraints(620, 710, -1, 40));

        two_run_labelC.setBackground(new java.awt.Color(255, 255, 255));
        two_run_labelC.setFont(new java.awt.Font("Montserrat", 0, 24)); // NOI18N
        two_run_labelC.setForeground(new java.awt.Color(66, 50, 77));
        two_run_labelC.setText("2.4 km Run");
        panelC.add(two_run_labelC, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 710, -1, 40));

        midC2.setForeground(new java.awt.Color(204, 204, 255));
        panelC.add(midC2, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 680, 570, 10));

        two_run_textfieldC.setFont(new java.awt.Font("Roboto", 0, 24)); // NOI18N
        two_run_textfieldC.setForeground(new java.awt.Color(54, 33, 89));
        two_run_textfieldC.setBorder(null);
        two_run_textfieldC.setMargin(new java.awt.Insets(2, 4, 2, 2));
        two_run_textfieldC.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                two_run_textfieldCActionPerformed(evt);
            }
        });
        panelC.add(two_run_textfieldC, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 750, 210, 40));

        underline19.setForeground(new java.awt.Color(54, 33, 89));
        panelC.add(underline19, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 790, 210, 20));

        chin_up_textfieldC.setFont(new java.awt.Font("Roboto", 0, 24)); // NOI18N
        chin_up_textfieldC.setForeground(new java.awt.Color(54, 33, 89));
        chin_up_textfieldC.setBorder(null);
        chin_up_textfieldC.setMargin(new java.awt.Insets(2, 4, 2, 2));
        chin_up_textfieldC.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                chin_up_textfieldCActionPerformed(evt);
            }
        });
        panelC.add(chin_up_textfieldC, new org.netbeans.lib.awtextra.AbsoluteConstraints(350, 750, 210, 40));

        underline20.setForeground(new java.awt.Color(54, 33, 89));
        panelC.add(underline20, new org.netbeans.lib.awtextra.AbsoluteConstraints(350, 790, 210, 20));

        five_shuttle_textfieldC.setFont(new java.awt.Font("Roboto", 0, 24)); // NOI18N
        five_shuttle_textfieldC.setForeground(new java.awt.Color(54, 33, 89));
        five_shuttle_textfieldC.setBorder(null);
        five_shuttle_textfieldC.setMargin(new java.awt.Insets(2, 4, 2, 2));
        five_shuttle_textfieldC.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                five_shuttle_textfieldCActionPerformed(evt);
            }
        });
        panelC.add(five_shuttle_textfieldC, new org.netbeans.lib.awtextra.AbsoluteConstraints(620, 750, 210, 40));

        underline21.setForeground(new java.awt.Color(54, 33, 89));
        panelC.add(underline21, new org.netbeans.lib.awtextra.AbsoluteConstraints(620, 790, 210, 20));

        sit_up_labelC.setBackground(new java.awt.Color(255, 255, 255));
        sit_up_labelC.setFont(new java.awt.Font("Montserrat", 0, 24)); // NOI18N
        sit_up_labelC.setForeground(new java.awt.Color(66, 50, 77));
        sit_up_labelC.setText("Sit Up");
        panelC.add(sit_up_labelC, new org.netbeans.lib.awtextra.AbsoluteConstraints(350, 810, -1, 40));

        result2_labelC.setBackground(new java.awt.Color(255, 255, 255));
        result2_labelC.setFont(new java.awt.Font("Montserrat", 0, 24)); // NOI18N
        result2_labelC.setForeground(new java.awt.Color(66, 50, 77));
        result2_labelC.setText("Result");
        panelC.add(result2_labelC, new org.netbeans.lib.awtextra.AbsoluteConstraints(620, 810, -1, 40));

        hundred_sprint_labelC.setBackground(new java.awt.Color(255, 255, 255));
        hundred_sprint_labelC.setFont(new java.awt.Font("Montserrat", 0, 24)); // NOI18N
        hundred_sprint_labelC.setForeground(new java.awt.Color(66, 50, 77));
        hundred_sprint_labelC.setText("100 m Sprint");
        panelC.add(hundred_sprint_labelC, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 810, -1, 40));

        hundred_sprint_textfieldC.setFont(new java.awt.Font("Roboto", 0, 24)); // NOI18N
        hundred_sprint_textfieldC.setForeground(new java.awt.Color(54, 33, 89));
        hundred_sprint_textfieldC.setBorder(null);
        hundred_sprint_textfieldC.setMargin(new java.awt.Insets(2, 4, 2, 2));
        hundred_sprint_textfieldC.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                hundred_sprint_textfieldCActionPerformed(evt);
            }
        });
        panelC.add(hundred_sprint_textfieldC, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 850, 210, 40));

        underline22.setForeground(new java.awt.Color(54, 33, 89));
        panelC.add(underline22, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 890, 210, 20));

        sit_up_textfieldC.setFont(new java.awt.Font("Roboto", 0, 24)); // NOI18N
        sit_up_textfieldC.setForeground(new java.awt.Color(54, 33, 89));
        sit_up_textfieldC.setBorder(null);
        sit_up_textfieldC.setMargin(new java.awt.Insets(2, 4, 2, 2));
        sit_up_textfieldC.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                sit_up_textfieldCActionPerformed(evt);
            }
        });
        panelC.add(sit_up_textfieldC, new org.netbeans.lib.awtextra.AbsoluteConstraints(350, 850, 210, 40));

        underline23.setForeground(new java.awt.Color(54, 33, 89));
        panelC.add(underline23, new org.netbeans.lib.awtextra.AbsoluteConstraints(350, 890, 210, 20));

        result2_textfieldC.setFont(new java.awt.Font("Roboto", 0, 24)); // NOI18N
        result2_textfieldC.setForeground(new java.awt.Color(54, 33, 89));
        result2_textfieldC.setBorder(null);
        result2_textfieldC.setMargin(new java.awt.Insets(2, 4, 2, 2));
        result2_textfieldC.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                result2_textfieldCActionPerformed(evt);
            }
        });
        panelC.add(result2_textfieldC, new org.netbeans.lib.awtextra.AbsoluteConstraints(620, 850, 210, 40));

        underline24.setForeground(new java.awt.Color(54, 33, 89));
        panelC.add(underline24, new org.netbeans.lib.awtextra.AbsoluteConstraints(620, 890, 210, 20));

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

        insert_label.setBackground(new java.awt.Color(64, 43, 100));
        insert_label.setFont(new java.awt.Font("Roboto", 0, 14)); // NOI18N
        insert_label.setForeground(new java.awt.Color(64, 43, 100));
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

        print_label.setBackground(new java.awt.Color(64, 43, 100));
        print_label.setFont(new java.awt.Font("Roboto", 0, 14)); // NOI18N
        print_label.setForeground(new java.awt.Color(64, 43, 100));
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

        getContentPane().add(bg, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, -1, 730));

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
                "S. No.", "Service Num", "Name", "Age", "Test Date", "5 km", "V rope", "H rope", "60 m sprint", "9m ditch", "Result 1", "2.4 km Run", "Chin up", "5 m Shuttle", "100 m sprint", "Sit Up", "Result 2"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false
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

    private void okayMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_okayMouseClicked
        String sno = service_number_textfield.getText();
        if(sno==null || sno.trim().isEmpty()){
            JOptionPane.showMessageDialog(null,"No Army Number found");
            
        }
        else{
            try{
                Statement st = con.createStatement();
                String query="Select count(*) as count from attach_in where service_no = '"+sno+"'";
                String query2="Select count(*) as count from new_registration where service_no = '"+sno+"'";
                String query3="Select count(*) as count from post_in where service_no = '"+sno+"'";
                String q1 = "select Name,Rank_,DOB,Trade,Company,Unit from attach_in where Service_no ='"+sno+"'";
                String q2 = "select Name,Rank_,birth_date,Company from new_registration where Service_no ='"+sno+"'";
                String q3 = "select Name,DOB,Rank_,Company from post_in where Service_no ='"+sno+"'";
                ResultSet rs = st.executeQuery(query);
                int count = 0;
                int count2 = 0;
                int count3 = 0;
                if(rs.next()){
                    count=rs.getInt("count");
                }
                rs = st.executeQuery(query2);
                if(rs.next()){
                    count2=rs.getInt("count");
                }
                rs = st.executeQuery(query3);
                if(rs.next()){
                    count3=rs.getInt("count");
                }
                String namee="",rankk="",tradee="",companyy="",unitt="",ageee="";
                if(count!=0){
                    
                    rs= st.executeQuery(q1);
                    
                    System.out.println("Inside if");
                    while(rs.next()){
                        System.out.println("Inside while");
                        namee =rs.getString("Name");
                        rankk = rs.getString("Rank_");
                        tradee = rs.getString("Trade");
                        companyy = rs.getString("Company");
                        unitt = rs.getString("Unit");
                        ageee=rs.getString("DOB");
                    }
                    System.out.print("\nSplitting");
                    String[] a=ageee.split("-");
                    int[] latest_age = new int[3];
                    for(int i=0; i<a.length;i++){
                        latest_age[i]=Integer.parseInt(a[i]);
                    }
                    System.out.println("\nbday");
                    System.out.println("\n"+latest_age[0]+" "+latest_age[1]+" "+latest_age[2]);
                    LocalDate bday= LocalDate.of(latest_age[2],latest_age[1],latest_age[0]);
                    LocalDate now = LocalDate.now();
                    System.out.print("\nPeriod");
                    int y=Period.between(bday,now).getYears();
                    String years = String.valueOf(y);
                    System.out.print("\n"+years);
                    name.setText(namee);
                    rank.setText(rankk);
                    company.setText(companyy);
                    unit.setText(unitt);
                    trade.setText(tradee);
                    age_1.setText(years);
                   // System.out.println(namee+" "+unitt+" "+tradee+" "+rankk+" "+companyy);
                    
                }
                else if(count2!=0 ||count3!=0){
                    if(count2!=0){
                        rs= st.executeQuery(q2);
                        
                    }    
                        
                    if(count3!=0){
                        rs= st.executeQuery(q3);
                        
                    }    
                    System.out.println("Inside if");
                    while(rs.next()){
                        System.out.println("Inside while");
                        namee =rs.getString("Name");
                        rankk = rs.getString("Rank_");
                        companyy=rs.getString("Company");
                        if(count2!=0){
                            ageee=rs.getString("birth_date");
                        }
                        if(count3!=0){
                            ageee=rs.getString("DOB");
                        }
                        
                    }
                    String[] a=ageee.split("-");
                    int[] latest_age = new int[3];
                    for(int i=0; i<a.length;i++){
                        latest_age[i]=Integer.parseInt(a[i]);
                    }
                    LocalDate bday= LocalDate.of(latest_age[2],latest_age[1],latest_age[1]);
                    LocalDate now = LocalDate.now();
                    int y=Period.between(bday,now).getYears();
                    String years = String.valueOf(y);
                    System.out.print("\nyears");
                    name.setText(namee);
                    rank.setText(rankk);
                    company.setText(companyy);
                    age_1.setText(years);
                }    
                else{
                    JOptionPane.showMessageDialog(null,"No Records of "+sno+" found!");
                    name.setText("> XXXXXXX");
                    rank.setText("> XXXXXXX");
                    age_1.setText("> XXXXXXX");
                    company.setText("> XXXXXXX");
                    unit.setText("> XXXXXXX");
                    trade.setText("> XXXXXXX");
                }
            }
            catch(Exception e){
                System.out.print(e);
                JOptionPane.showMessageDialog(null,"No records of "+sno+" found!");
            }
        }
    }//GEN-LAST:event_okayMouseClicked

    private void five_run_textfieldActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_five_run_textfieldActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_five_run_textfieldActionPerformed

    private void Vrope_textfieldActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Vrope_textfieldActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_Vrope_textfieldActionPerformed

    private void Hrope_textfieldActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Hrope_textfieldActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_Hrope_textfieldActionPerformed

    private void sixty_sprint_textfieldActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_sixty_sprint_textfieldActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_sixty_sprint_textfieldActionPerformed

    private void nine_ditch_textfieldActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_nine_ditch_textfieldActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_nine_ditch_textfieldActionPerformed

    private void result1_textfieldActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_result1_textfieldActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_result1_textfieldActionPerformed

    private void two_run_textfieldActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_two_run_textfieldActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_two_run_textfieldActionPerformed

    private void chin_up_textfieldActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_chin_up_textfieldActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_chin_up_textfieldActionPerformed

    private void five_shuttle_textfieldActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_five_shuttle_textfieldActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_five_shuttle_textfieldActionPerformed

    private void hundred_sprint_textfieldActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_hundred_sprint_textfieldActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_hundred_sprint_textfieldActionPerformed

    private void sit_up_textfieldActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_sit_up_textfieldActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_sit_up_textfieldActionPerformed

    private void result2_textfieldActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_result2_textfieldActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_result2_textfieldActionPerformed

    private void menuBMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_menuBMouseClicked
        menuB.setBackground(new java.awt.Color(85,65,118));
        menuA.setBackground(new java.awt.Color(64,43,100));
        view_table(null);
        arrange=0;
        print_buttonB.setVisible(true);
        edit_buttonB.setVisible(true);
        refresh_buttonB.setVisible(true);
        fullscreen_buttonB.setVisible(true);
        print_buttonA.setVisible(false);
        insert_buttonA.setVisible(false);

        ScrollPaneA.setVisible(false);
        panelB.setVisible(true);
        ScrollPaneC.setVisible(false);
        fullscreen_panelB.setVisible(false);
    }//GEN-LAST:event_menuBMouseClicked

    private void okayBMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_okayBMouseClicked
        String sno = service_numberB.getText();
        try {
            Statement st = con.createStatement();
            ResultSet rs = null;
            String q = "select count(*) as count from bpet where Service_no='" + sno + "'";
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

    private void five_run_textfieldCActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_five_run_textfieldCActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_five_run_textfieldCActionPerformed

    private void Vrope_textfieldCActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Vrope_textfieldCActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_Vrope_textfieldCActionPerformed

    private void Hrope_textfieldCActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Hrope_textfieldCActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_Hrope_textfieldCActionPerformed

    private void age_textfieldCActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_age_textfieldCActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_age_textfieldCActionPerformed

    private void sixty_sprint_textfieldCActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_sixty_sprint_textfieldCActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_sixty_sprint_textfieldCActionPerformed

    private void nine_ditch_textfieldCActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_nine_ditch_textfieldCActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_nine_ditch_textfieldCActionPerformed

    private void result1_textfieldCActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_result1_textfieldCActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_result1_textfieldCActionPerformed

    private void two_run_textfieldCActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_two_run_textfieldCActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_two_run_textfieldCActionPerformed

    private void chin_up_textfieldCActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_chin_up_textfieldCActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_chin_up_textfieldCActionPerformed

    private void five_shuttle_textfieldCActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_five_shuttle_textfieldCActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_five_shuttle_textfieldCActionPerformed

    private void hundred_sprint_textfieldCActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_hundred_sprint_textfieldCActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_hundred_sprint_textfieldCActionPerformed

    private void sit_up_textfieldCActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_sit_up_textfieldCActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_sit_up_textfieldCActionPerformed

    private void result2_textfieldCActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_result2_textfieldCActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_result2_textfieldCActionPerformed

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

    private void edit_buttonBMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_edit_buttonBMouseClicked
        try{ //change has happended
            String sno = service_numberB.getText();
            Statement st = con.createStatement();
            int row_ = TableG_out.getSelectedRow();
            if((sno==null || sno.trim().isEmpty()) &&row_==-1  ){
                JOptionPane.showMessageDialog(null,"Please enter Army Number");
            }
            if(row_==-1 &&sno!=null &&!sno.trim().isEmpty())
                JOptionPane.showMessageDialog(null,"Please select a row");

            else if(row_!=-1){
                tick=0;
                sno=big.getValueAt(row_, 1).toString();
                String query = "select count(*) as count from bpet where service_no='"+sno+"'";
                String query2 = "select count(*) as count from attach_in where service_no='"+sno+"'";
                String query3 = "select count(*) as count from new_registration where service_no='"+sno+"'";
                String query4 = "select count(*) as count from post_in where service_no='"+sno+"'";
                ResultSet rs = st.executeQuery(query);
                int count =0,count2=0,count3=0,count4=0;
                if(rs.next()){
                    count = rs.getInt("count");
                }
                
                if(count==0){
                    JOptionPane.showMessageDialog(null,"Army Number does not exist!");
                }
                else{
                    System.out.print("\nYooo");
                    ScrollPaneA.setVisible(false);
                    panelB.setVisible(false);
                    ScrollPaneC.setVisible(true);
                    fullscreen_panelB.setVisible(false);
                    String name ="",rank="";
                    String att="select Name,rank_ from attach_in where service_no='"+sno+"'";
                    String new_="select Name,rank_ from new_registration where service_no='"+sno+"'";
                    String post_="select Name,rank_ from post_in where service_no='"+sno+"'";
                    rs=st.executeQuery(query2);
                    if(rs.next()){
                        count2=rs.getInt("count");
                    }
                    if(count2!=0){
                        System.out.print("\nattach if");
                        rs=st.executeQuery(att);
                       while(rs.next()){
                           // army_numberC.setText(sno);
                            name = rs.getString("Name");
                            rank = rs.getString("Rank_");
                       }
                    }
                    rs=st.executeQuery(query3);
                    if(rs.next()){
                        count3=rs.getInt("count");
                    }
                    
                    rs=st.executeQuery(query4);
                    if(rs.next()){
                        count4=rs.getInt("count");
                    }
                    if(count3!=0 ||count4!=0){
                       System.out.print("\n new if");
                       if(count3!=0)
                        rs=st.executeQuery(new_);
                       else
                           rs=st.executeQuery(post_);
                       while(rs.next()){
                            
                            name = rs.getString("Name");
                            rank = rs.getString("Rank_");
                       }
                    }
                    //row_ = TableG_out.getSelectedRow();
                    rroo = row_;
                    System.out.print(rroo);
                    
                    if(row_!=-1){
                        System.out.print("\nTable ke paas");
                        age_textfieldC.setText(big.getValueAt(row_, 3).toString());
                        String ddd =(big.getValueAt(row_, 4).toString());
                        String[] dd = ddd.split("-");
                        test_date_comboboxC.setSelectedItem(dd[0]);
                        test_month_comboboxC.setSelectedItem(dd[1]);
                        test_year_comboboxC.setSelectedItem(dd[2]);
                        five_run_textfieldC.setText(big.getValueAt(row_, 5).toString());
                        Vrope_textfieldC.setText(big.getValueAt(row_, 6).toString());
                        Hrope_textfieldC.setText(big.getValueAt(row_, 7).toString());
                        sixty_sprint_textfieldC.setText(big.getValueAt(row_, 8).toString());
                        nine_ditch_textfieldC.setText(big.getValueAt(row_, 9).toString());
                        result1_textfieldC.setText(big.getValueAt(row_, 10).toString());
                        two_run_textfieldC.setText(big.getValueAt(row_, 11).toString());
                        chin_up_textfieldC.setText(big.getValueAt(row_, 12).toString());
                        five_shuttle_textfieldC.setText(big.getValueAt(row_, 12).toString());
                        hundred_sprint_textfieldC.setText(big.getValueAt(row_, 14).toString());
                        sit_up_textfieldC.setText(big.getValueAt(row_, 15).toString());
                        result2_textfieldC.setText(big.getValueAt(row_, 16).toString());
                        nameC.setText(name);
                        rankC.setText(rank);
                        army_numberC.setText(sno);
                        print_buttonA.setVisible(false);
                        insert_buttonA.setVisible(false);
                        print_buttonB.setVisible(false);
                        edit_buttonB.setVisible(false);
                        refresh_buttonB.setVisible(false);
                        fullscreen_buttonB.setVisible(false);
                        update_buttonC.setVisible(true);
                        row_=-1;
                    }
                    else{
                        
                        
                            menuB.setBackground(new java.awt.Color(85,65,118));
                            menuA.setBackground(new java.awt.Color(64,43,100));
                            view_table(null);
                            arrange=0;
                            print_buttonB.setVisible(true);
                            edit_buttonB.setVisible(true);
                            refresh_buttonB.setVisible(true);
                            fullscreen_buttonB.setVisible(true);
                            print_buttonA.setVisible(false);
                            insert_buttonA.setVisible(false);

                            ScrollPaneA.setVisible(false);
                            panelB.setVisible(true);
                            ScrollPaneC.setVisible(false);
                            fullscreen_panelB.setVisible(false);
                            JOptionPane.showMessageDialog(null,"Please select a row");
                        
                    }
                }
                
            }    
        }
        catch(Exception e){
            System.out.print("\n"+e);
            
                JOptionPane.showMessageDialog(null,"Operation Unsuccessful!");
                menuB.setBackground(new java.awt.Color(85,65,118));
                menuA.setBackground(new java.awt.Color(64,43,100));
                view_table(null);
                arrange=0;
                print_buttonB.setVisible(true);
                edit_buttonB.setVisible(true);
                refresh_buttonB.setVisible(true);
                fullscreen_buttonB.setVisible(true);
                print_buttonA.setVisible(false);
                insert_buttonA.setVisible(false);

                ScrollPaneA.setVisible(false);
                panelB.setVisible(true);
                ScrollPaneC.setVisible(false);
                fullscreen_panelB.setVisible(false);
        }
    }//GEN-LAST:event_edit_buttonBMouseClicked

    private void edit_buttonBMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_edit_buttonBMouseEntered
        edit_buttonB.setBackground(new java.awt.Color(237, 224, 255));
    }//GEN-LAST:event_edit_buttonBMouseEntered

    private void edit_buttonBMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_edit_buttonBMouseExited
        edit_buttonB.setBackground(new java.awt.Color(240,240,240));
    }//GEN-LAST:event_edit_buttonBMouseExited

    private void print_buttonBMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_print_buttonBMouseClicked
        try{ 
            path_file paths=new path_file();
            PrintWriter pw=new PrintWriter(new File((paths.csv + "\\bpet.csv")));
            StringBuilder sb=new StringBuilder();
            String query="Select * from bpet";
            
            sb.append("Service_no      \t");
            sb.append(",");
            sb.append("Name            \t");
            sb.append(",");
            sb.append("Age            \t");
            sb.append(",");
            sb.append("Test Date             \t");
            sb.append(",");
            sb.append("5km          \t");
            sb.append(",");
            sb.append("Vrope         \t");
            sb.append(",");
            sb.append("Hrope      \t");
            sb.append(",");
            sb.append("60m sprint        \t");
            sb.append(",");
            sb.append("9m Ditch     \t");
            sb.append(",");
            sb.append("Result 1        \t");
            sb.append(",");
            sb.append("2.4km Run   \t");
            sb.append(",");
            sb.append("Chinup  \t");
            sb.append(",");
            sb.append("5m shuttle       \t");
            sb.append(",");
            sb.append("100m sprint\t");
            sb.append(",");
            sb.append("Sit Up      \t");
            sb.append(",");
            sb.append("Result 2    \t");
            
            
            sb.append("\r\n");
            ResultSet rs = null;
            Statement st = con.createStatement();
            rs=st.executeQuery(query);  
            
            while(rs.next()){
                sb.append(rs.getString("Service_no"));
                sb.append(",");
                sb.append(rs.getString("name"));
                sb.append(",");
                sb.append(rs.getString("Age"));
                sb.append(",");
                sb.append(rs.getString("Test_date"));
                sb.append(",");
                sb.append(rs.getString("5km"));
                sb.append(",");
                sb.append(rs.getString("vrope"));
                sb.append(",");
                sb.append(rs.getString("hrope"));
                sb.append(",");
                sb.append(rs.getString("60m_sprint")+"\t");
                sb.append(",");
                sb.append(rs.getString("9m_ditch")+"\t");
                sb.append(",");
                sb.append(rs.getString("Result1")+"\t");
                sb.append(",");
                sb.append(rs.getString("2400m_run"));
                sb.append(",");
                sb.append(rs.getString("Chinup"));
                sb.append(",");
                sb.append(rs.getString("5m_shuttle"));
                sb.append(",");
                sb.append(rs.getString("100m_sprint"));
                sb.append(",");
                sb.append("\""+rs.getString("sit_up")+"\"");
                sb.append(",");
                sb.append(rs.getString("Result2"));
                
               
                
                sb.append("\r\n");
            }
            pw.write(sb.toString());
            pw.close();
            JOptionPane.showMessageDialog(null,"Successfully Converted to CSV");
            Desktop.getDesktop().open(new File(paths.csv + "/bpet.csv"));

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

    private void insert_buttonAMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_insert_buttonAMouseClicked
        // TODO add your handling code here:
        bpet_insert();
    }//GEN-LAST:event_insert_buttonAMouseClicked

    private void insert_buttonAMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_insert_buttonAMouseEntered
        insert_buttonA.setBackground(new java.awt.Color(237, 224, 255));
    }//GEN-LAST:event_insert_buttonAMouseEntered

    private void insert_buttonAMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_insert_buttonAMouseExited
        insert_buttonA.setBackground(new java.awt.Color(240,240,240));
    }//GEN-LAST:event_insert_buttonAMouseExited

    private void print_buttonAMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_print_buttonAMouseEntered
        print_buttonA.setBackground(new java.awt.Color(237, 224, 255));
    }//GEN-LAST:event_print_buttonAMouseEntered

    private void print_buttonAMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_print_buttonAMouseClicked
        printRecord(panelA);
    }//GEN-LAST:event_print_buttonAMouseClicked

    private void print_buttonAMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_print_buttonAMouseExited
        print_buttonA.setBackground(new java.awt.Color(240,240,240));
    }//GEN-LAST:event_print_buttonAMouseExited

    private void refresh_buttonBMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_refresh_buttonBMouseClicked
        // TODO add your handling code here:
        view_table(null);
        arrange=0;
    }//GEN-LAST:event_refresh_buttonBMouseClicked

    private void refresh_buttonBMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_refresh_buttonBMouseEntered
        refresh_buttonB.setBackground(new java.awt.Color(237, 224, 255));
    }//GEN-LAST:event_refresh_buttonBMouseEntered

    private void refresh_buttonBMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_refresh_buttonBMouseExited
        refresh_buttonB.setBackground(new java.awt.Color(240,240,240));
    }//GEN-LAST:event_refresh_buttonBMouseExited

    private void fullscreen_buttonBMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_fullscreen_buttonBMouseEntered
        fullscreen_buttonB.setBackground(new java.awt.Color(237, 224, 255));
    }//GEN-LAST:event_fullscreen_buttonBMouseEntered

    private void fullscreen_buttonBMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_fullscreen_buttonBMouseExited
        fullscreen_buttonB.setBackground(new java.awt.Color(240,240,240));
    }//GEN-LAST:event_fullscreen_buttonBMouseExited

    private void fullscreen_buttonBMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_fullscreen_buttonBMouseClicked
        fullscreen_panelB.setVisible(true);
        if(arrange!=0)
            view_table("o");
        else
            view_table(null);
        bg.setVisible(false);
    }//GEN-LAST:event_fullscreen_buttonBMouseClicked

    private void update_buttonCMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_update_buttonCMouseEntered
        update_buttonC.setBackground(new java.awt.Color(237, 224, 255));
    }//GEN-LAST:event_update_buttonCMouseEntered

    private void update_buttonCMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_update_buttonCMouseExited
        update_buttonC.setBackground(new java.awt.Color(240,240,240));
    }//GEN-LAST:event_update_buttonCMouseExited

    private void update_buttonCMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_update_buttonCMouseClicked
        // TODO add your handling code here:
        upd();
        
    }//GEN-LAST:event_update_buttonCMouseClicked

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

    private void current_dateAMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_current_dateAMouseClicked
        String dd = new SimpleDateFormat("dd-MM-yyyy").format(new Date());
        String[] d = dd.split("-");
        test_date_combobox.setSelectedItem(d[0]);
        test_month_combobox.setSelectedItem(d[1]);
        test_year_combobox.setSelectedItem(d[2]);
    }//GEN-LAST:event_current_dateAMouseClicked

    private void current_dateAMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_current_dateAMouseEntered
        current_dateA.setForeground(new java.awt.Color(54, 33, 89));
        current_dateA.setBackground(new java.awt.Color(237, 224, 255));
    }//GEN-LAST:event_current_dateAMouseEntered

    private void current_dateAMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_current_dateAMouseExited
        current_dateA.setForeground(new java.awt.Color(60, 63, 65));
        current_dateA.setBackground(new java.awt.Color(255, 255, 255));
    }//GEN-LAST:event_current_dateAMouseExited

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
            java.util.logging.Logger.getLogger(BPET_PPT.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(BPET_PPT.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(BPET_PPT.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(BPET_PPT.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new BPET_PPT().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel Hrope_label;
    private javax.swing.JLabel Hrope_labelC;
    private javax.swing.JTextField Hrope_textfield;
    private javax.swing.JTextField Hrope_textfieldC;
    private javax.swing.JScrollPane ScrollPaneA;
    private javax.swing.JScrollPane ScrollPaneB;
    private javax.swing.JScrollPane ScrollPaneC;
    private javax.swing.JSeparator Separator;
    private javax.swing.JTable TableG_out;
    private javax.swing.JLabel Vrope_label;
    private javax.swing.JLabel Vrope_labelC;
    private javax.swing.JTextField Vrope_textfield;
    private javax.swing.JTextField Vrope_textfieldC;
    private javax.swing.JLabel age_1;
    private javax.swing.JLabel age_label;
    private javax.swing.JLabel age_labelC;
    private javax.swing.JTextField age_textfieldC;
    private javax.swing.JSeparator age_underlineC;
    private javax.swing.JLabel army_numberC;
    private javax.swing.JPanel bg;
    private javax.swing.JLabel changing_for_label;
    private javax.swing.JLabel chin_up_label;
    private javax.swing.JLabel chin_up_labelC;
    private javax.swing.JTextField chin_up_textfield;
    private javax.swing.JTextField chin_up_textfieldC;
    private javax.swing.JLabel company;
    private javax.swing.JLabel company_label;
    private javax.swing.JLabel current_dateA;
    private javax.swing.JPanel edit_buttonB;
    private javax.swing.JLabel edit_labelB;
    private javax.swing.JPanel exit_fullscreen_buttonB;
    private javax.swing.JLabel exit_fullscreen_labelB;
    private javax.swing.JLabel exit_icon;
    private javax.swing.JLabel five_run_label;
    private javax.swing.JLabel five_run_labelC;
    private javax.swing.JTextField five_run_textfield;
    private javax.swing.JTextField five_run_textfieldC;
    private javax.swing.JLabel five_shuttle_label;
    private javax.swing.JLabel five_shuttle_labelC;
    private javax.swing.JTextField five_shuttle_textfield;
    private javax.swing.JTextField five_shuttle_textfieldC;
    private javax.swing.JLabel from_date_label;
    private javax.swing.JLabel from_date_labelC;
    private javax.swing.JScrollPane fullscreen_ScrollPaneB;
    private javax.swing.JPanel fullscreen_buttonB;
    private javax.swing.JLabel fullscreen_labelB;
    private javax.swing.JPanel fullscreen_panelB;
    private javax.swing.JTable fullscreen_tableB;
    private javax.swing.JLabel go_back_label;
    private javax.swing.JLabel hundred_sprint_label;
    private javax.swing.JLabel hundred_sprint_labelC;
    private javax.swing.JTextField hundred_sprint_textfield;
    private javax.swing.JTextField hundred_sprint_textfieldC;
    private javax.swing.JPanel insert_buttonA;
    private javax.swing.JLabel insert_label;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JPanel menuA;
    private javax.swing.JLabel menuA_icon;
    private javax.swing.JLabel menuA_label;
    private javax.swing.JPanel menuB;
    private javax.swing.JLabel menuB_icon;
    private javax.swing.JLabel menuB_label;
    private javax.swing.JSeparator midA1;
    private javax.swing.JSeparator midA2;
    private javax.swing.JSeparator midC0;
    private javax.swing.JSeparator midC1;
    private javax.swing.JSeparator midC2;
    private javax.swing.JSeparator midC4;
    private javax.swing.JLabel minimize;
    private javax.swing.JPanel move_panel;
    private javax.swing.JLabel name;
    private javax.swing.JLabel nameC;
    private javax.swing.JLabel name_label;
    private javax.swing.JLabel name_labelC;
    private javax.swing.JLabel nine_ditch_label;
    private javax.swing.JLabel nine_ditch_labelC;
    private javax.swing.JTextField nine_ditch_textfield;
    private javax.swing.JTextField nine_ditch_textfieldC;
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
    private javax.swing.JTextField result1_textfield;
    private javax.swing.JTextField result1_textfieldC;
    private javax.swing.JLabel result2_label;
    private javax.swing.JLabel result2_labelC;
    private javax.swing.JTextField result2_textfield;
    private javax.swing.JTextField result2_textfieldC;
    private javax.swing.JTextField service_numberB;
    private javax.swing.JLabel service_number_label;
    private javax.swing.JLabel service_number_labelB;
    private javax.swing.JLabel service_number_labelC;
    private javax.swing.JTextField service_number_textfield;
    private javax.swing.JSeparator service_number_underline;
    private javax.swing.JPanel side_pane;
    private javax.swing.JLabel sit_up_label;
    private javax.swing.JLabel sit_up_labelC;
    private javax.swing.JTextField sit_up_textfield;
    private javax.swing.JTextField sit_up_textfieldC;
    private javax.swing.JLabel sixty_sprint_label;
    private javax.swing.JLabel sixty_sprint_labelC;
    private javax.swing.JTextField sixty_sprint_textfield;
    private javax.swing.JTextField sixty_sprint_textfieldC;
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
    private javax.swing.JLabel two_run_label;
    private javax.swing.JLabel two_run_labelC;
    private javax.swing.JTextField two_run_textfield;
    private javax.swing.JTextField two_run_textfieldC;
    private javax.swing.JSeparator underline1;
    private javax.swing.JSeparator underline10;
    private javax.swing.JSeparator underline11;
    private javax.swing.JSeparator underline12;
    private javax.swing.JSeparator underline13;
    private javax.swing.JSeparator underline14;
    private javax.swing.JSeparator underline15;
    private javax.swing.JSeparator underline16;
    private javax.swing.JSeparator underline17;
    private javax.swing.JSeparator underline18;
    private javax.swing.JSeparator underline19;
    private javax.swing.JSeparator underline2;
    private javax.swing.JSeparator underline20;
    private javax.swing.JSeparator underline21;
    private javax.swing.JSeparator underline22;
    private javax.swing.JSeparator underline23;
    private javax.swing.JSeparator underline24;
    private javax.swing.JSeparator underline3;
    private javax.swing.JSeparator underline4;
    private javax.swing.JSeparator underline5;
    private javax.swing.JSeparator underline6;
    private javax.swing.JSeparator underline7;
    private javax.swing.JSeparator underline8;
    private javax.swing.JSeparator underline9;
    private javax.swing.JLabel unit;
    private javax.swing.JLabel unit_label;
    private javax.swing.JPanel update_buttonC;
    private javax.swing.JLabel update_labelC;
    // End of variables declaration//GEN-END:variables
}
