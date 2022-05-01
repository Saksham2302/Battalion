import java.awt.Color;
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
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import javax.swing.ImageIcon;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.table.DefaultTableModel;


/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Aditya Pandey
 */
public class Attachment extends javax.swing.JFrame {

    /**
     * Creates new form Attachment
     */
    private int mouseX, mouseY;
    boolean back_button=true;
    
    Database db=new Database();
    Connection con = db.create_connection(true);
    DefaultTableModel model_fullscreenD, model_tableD, model_fullscreenE, model_tableE,attach_i,attach_o,  upd_small_attach_in,upd_big_attach_in;   
    public Attachment() {
        
        initComponents();
        
        ScrollPaneA.getVerticalScrollBar().setUnitIncrement(16);
        ScrollPaneB.getVerticalScrollBar().setUnitIncrement(16);
        ScrollPaneC.getVerticalScrollBar().setUnitIncrement(16);
        ScrollPaneD.getVerticalScrollBar().setUnitIncrement(16);
        ScrollPaneE.getVerticalScrollBar().setUnitIncrement(16);
        ScrollPaneF.getVerticalScrollBar().setUnitIncrement(16);
        ScrollPaneG_IN.getVerticalScrollBar().setUnitIncrement(16);
        ScrollPaneG_OUT.getVerticalScrollBar().setUnitIncrement(16);
        fullscreen_ScrollPaneD.getVerticalScrollBar().setUnitIncrement(16);
        fullscreen_ScrollPaneE.getVerticalScrollBar().setUnitIncrement(16);
        

        //for a centered frame
        Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
        this.setLocation(dim.width/2-this.getSize().width/2, dim.height/2-this.getSize().height/2);
        //for a centered frame
        
        menuA.setBackground(new java.awt.Color(85,65,118));
        menuB.setBackground(new java.awt.Color(64,43,100));
        menuC.setBackground(new java.awt.Color(64,43,100));
        menuD.setBackground(new java.awt.Color(64,43,100));
        menuE.setBackground(new java.awt.Color(64,43,100));
        
        fullscreen_panelD.setVisible(false);
        fullscreen_panelE.setVisible(false);
        ScrollPaneA.setVisible(true);
        ScrollPaneB.setVisible(false);
        ScrollPaneC.setVisible(false);
        panelD.setVisible(false);
        panelE.setVisible(false);
        
        
        print_buttonC.setVisible(false);
        print_buttonD.setVisible(false);
        print_buttonE.setVisible(false);
        refresh_buttonD.setVisible(false);
        refresh_buttonE.setVisible(false);
        fullscreen_buttonD.setVisible(false);
        fullscreen_buttonE.setVisible(false);
        remove_buttonC.setVisible(false);
        
        edit_buttonG.setVisible(false);
        menuF.setBackground(new java.awt.Color(64,43,100));
        ScrollPaneF.setVisible(false);
        panelG.setVisible(false);
        
        model_fullscreenD=(DefaultTableModel) fullscreen_tableD.getModel();
        model_tableD=(DefaultTableModel) TableD.getModel();
        model_fullscreenE=(DefaultTableModel) fullscreen_tableE.getModel();
        model_tableE=(DefaultTableModel) TableE.getModel();
        attach_i=(DefaultTableModel)DateIN_TableC.getModel();
        attach_o=(DefaultTableModel)DateOUT_TableC.getModel();
        
        upd_small_attach_in=(DefaultTableModel)TableG_IN.getModel();
        upd_big_attach_in=(DefaultTableModel)TableG_OUT.getModel();
        
        
        
    }
     
     static int blue=0;
     static String fp="";
  
    
     private void viewall(String attachment){
        
        try{
            Statement stmt=con.createStatement();
            String Query= "Select * from " + attachment;
            ResultSet rs = stmt.executeQuery(Query);
            
            String Service_no, Name, dob, Rank_, Company,Contact_no, indl_id, indl_aadhar,indl_pan, indl_bank_acc, indl_bank_name, indl_ifsc, indl_branch_name, indl_email, Courses_done, address, doj, medical_category, Kin_name, Kin_contact,Kin_relation, Kin_aadhar, Kin_pan, Kin_bank_acc, Kin_bank_name, Kin_ifsc, Kin_branch_name, Kin_email, armed_service ="", location, unit="", trade="",doa, purpose;
            model_tableD.setRowCount(0);
            model_fullscreenD.setRowCount(0);
            model_tableE.setRowCount(0);
            model_fullscreenE.setRowCount(0);
            int counter=0;
            while(rs.next()){
                counter=counter+1;
                Service_no=rs.getString("Service_no");
                Name=rs.getString("Name");
                dob=rs.getString("DOB");
                Rank_=rs.getString("Rank_");
                Company=rs.getString("Company");
                Contact_no=rs.getString("Contact_no");
                indl_id=rs.getString("indl_id");
                indl_aadhar=rs.getString("indl_aadhar");
                indl_pan=rs.getString("indl_pan");
                indl_bank_acc=rs.getString("indl_bank_acc");
                indl_bank_name=rs.getString("indl_bank_name");
                indl_ifsc=rs.getString("indl_ifsc");
                indl_branch_name=rs.getString("indl_branch_name");
                indl_email=rs.getString("indl_email");
                Courses_done=rs.getString("Courses_done");
                address=rs.getString("Address");
                doj=rs.getString("Date_of_Joining");
                medical_category=rs.getString("Medical_Category");
                Kin_name=rs.getString("kin_name");
                Kin_contact=rs.getString("kin_contact");
                Kin_relation=rs.getString("kin_relation");
                Kin_aadhar=rs.getString("kin_aadhar");
                Kin_pan=rs.getString("kin_pan");
                Kin_bank_acc=rs.getString("kin_bank_acc");
                Kin_bank_name=rs.getString("kin_bank_name");
                Kin_ifsc=rs.getString("kin_ifsc");
                Kin_branch_name=rs.getString("kin_branch_name");
                Kin_email=rs.getString("kin_email");
                if(attachment=="attach_in"){
                    armed_service=rs.getString("Armed_Services");
                    unit=rs.getString("Unit");
                    trade=rs.getString("Trade");
                }
                
                location=rs.getString("Location");
                
                                 
                model_tableD.addRow(new Object[]{
                   counter,
                   Service_no,
                   Name,
                   doj,
                   Company,
                   armed_service,
                   trade,
                   unit
                });
                
                if(attachment=="attach_out"){
                    doa=rs.getString("Date_of_Attachment");
                    purpose=rs.getString("Purpose");
                    model_tableE.addRow(new Object[]{
                        counter,
                        Service_no,
                        Name,
                        doj,
                        doa,
                        Company,
                        armed_service,
                        trade,
                        unit
                    });
                    model_fullscreenE.addRow(new Object[]{
                        counter,Service_no, Name, dob, Rank_, Company, Contact_no,address, doj,doa, medical_category,location,purpose
                    });
                }
                else {
                    model_fullscreenD.addRow(new Object[]{
                        counter,Service_no, Name, dob, Rank_, Company, Contact_no,address, doj, medical_category,armed_service, unit, trade,location
                    }); 
                }
            }
        }catch(Exception e){System.out.println(e+"sdfsdv");}
        
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
        menuB = new javax.swing.JPanel();
        menuB_icon1 = new javax.swing.JLabel();
        menuB_label1 = new javax.swing.JLabel();
        menuC = new javax.swing.JPanel();
        menuC_icon = new javax.swing.JLabel();
        menuC_label = new javax.swing.JLabel();
        menuD = new javax.swing.JPanel();
        menuD_icon = new javax.swing.JLabel();
        menuD_label = new javax.swing.JLabel();
        menuE = new javax.swing.JPanel();
        menuE_icon = new javax.swing.JLabel();
        menuE_label = new javax.swing.JLabel();
        title_label = new javax.swing.JLabel();
        title_icon = new javax.swing.JLabel();
        menuF = new javax.swing.JPanel();
        menuF_icon = new javax.swing.JLabel();
        menuF_label = new javax.swing.JLabel();
        ScrollPaneA = new javax.swing.JScrollPane();
        panelA = new javax.swing.JPanel();
        upload_photo_labelA = new javax.swing.JLabel();
        rank_comboboxA = new javax.swing.JComboBox<>();
        name_underlineA = new javax.swing.JSeparator();
        name_textfieldA = new javax.swing.JTextField();
        name_labelA = new javax.swing.JLabel();
        medical_category_labelA = new javax.swing.JLabel();
        birth_labelA = new javax.swing.JLabel();
        birth_year_comboboxA = new javax.swing.JComboBox<>();
        birth_date_comboboxA = new javax.swing.JComboBox<>();
        birth_month_comboboxA = new javax.swing.JComboBox<>();
        courses_done_labelA = new javax.swing.JLabel();
        courses_underlineA = new javax.swing.JSeparator();
        courses_textfieldA = new javax.swing.JTextField();
        indl_ID_details_label = new javax.swing.JLabel();
        army_number_underlineA = new javax.swing.JSeparator();
        army_number_textfieldA = new javax.swing.JTextField();
        army_number_labelA = new javax.swing.JLabel();
        company_underlineA = new javax.swing.JSeparator();
        company_textfieldA = new javax.swing.JTextField();
        company_labelA = new javax.swing.JLabel();
        add_buttonA = new javax.swing.JPanel();
        add_labelA = new javax.swing.JLabel();
        panelA_bottom_line = new javax.swing.JSeparator();
        unit_underlineA = new javax.swing.JSeparator();
        unit_textfieldA = new javax.swing.JTextField();
        unit_labelA = new javax.swing.JLabel();
        trade_underlineA = new javax.swing.JSeparator();
        trade_textfieldA = new javax.swing.JTextField();
        trade_labelA = new javax.swing.JLabel();
        AS_underlineA = new javax.swing.JSeparator();
        AS_textfieldA = new javax.swing.JTextField();
        AS_labelA = new javax.swing.JLabel();
        location_underlineA = new javax.swing.JSeparator();
        location_textfieldA = new javax.swing.JTextField();
        location_labelA = new javax.swing.JLabel();
        indl_mob_labelA = new javax.swing.JLabel();
        label_91A1 = new javax.swing.JLabel();
        indl_mob_underlineA = new javax.swing.JSeparator();
        indl_mob_textfieldA = new javax.swing.JTextField();
        DOJ_labelA = new javax.swing.JLabel();
        DOJ_date_comboboxA = new javax.swing.JComboBox<>();
        DOJ_month_comboboxA = new javax.swing.JComboBox<>();
        DOJ_year_comboboxA = new javax.swing.JComboBox<>();
        medical_category_underlineA = new javax.swing.JSeparator();
        medical_category_textfieldA = new javax.swing.JTextField();
        rank_labelA = new javax.swing.JLabel();
        select_file_buttonA = new javax.swing.JPanel();
        select_file_labelA = new javax.swing.JLabel();
        indl_underline0 = new javax.swing.JSeparator();
        indl_Icard_numberA = new javax.swing.JTextField();
        indl_underline1 = new javax.swing.JSeparator();
        indl_PAN_A = new javax.swing.JTextField();
        indl_underline2 = new javax.swing.JSeparator();
        indl_aadharA = new javax.swing.JTextField();
        indl_underline3 = new javax.swing.JSeparator();
        indl_mailA = new javax.swing.JTextField();
        indl_bank_nameA = new javax.swing.JTextField();
        indl_account_numberA = new javax.swing.JTextField();
        indl_underline4 = new javax.swing.JSeparator();
        indl_underline5 = new javax.swing.JSeparator();
        midA1 = new javax.swing.JSeparator();
        village_underlineA = new javax.swing.JSeparator();
        villageA = new javax.swing.JTextField();
        teh_underlineA = new javax.swing.JSeparator();
        tehA = new javax.swing.JTextField();
        PO_underlineA = new javax.swing.JSeparator();
        PO_A = new javax.swing.JTextField();
        state_underlineA = new javax.swing.JSeparator();
        stateA = new javax.swing.JTextField();
        PIN_A = new javax.swing.JTextField();
        distA = new javax.swing.JTextField();
        dist_underlineA = new javax.swing.JSeparator();
        PIN_underlineA = new javax.swing.JSeparator();
        midA3 = new javax.swing.JSeparator();
        address_labelA = new javax.swing.JLabel();
        indl_mobile_numberA = new javax.swing.JLabel();
        label_91A = new javax.swing.JLabel();
        contact_underlineA2 = new javax.swing.JSeparator();
        contact_textfieldA2 = new javax.swing.JTextField();
        indl_IFSC_A = new javax.swing.JTextField();
        indl_branchA = new javax.swing.JTextField();
        indl_underline6 = new javax.swing.JSeparator();
        indl_underline7 = new javax.swing.JSeparator();
        NOK_detailsA = new javax.swing.JLabel();
        kin_underlineA3 = new javax.swing.JSeparator();
        kin_PAN_A = new javax.swing.JTextField();
        kin_underlineA4 = new javax.swing.JSeparator();
        kin_aadharA = new javax.swing.JTextField();
        kin_underlineA5 = new javax.swing.JSeparator();
        kin_mailA = new javax.swing.JTextField();
        kin_bank_nameA = new javax.swing.JTextField();
        kin_account_numberA = new javax.swing.JTextField();
        kin_underlineA6 = new javax.swing.JSeparator();
        kin_underlineA7 = new javax.swing.JSeparator();
        midA2 = new javax.swing.JSeparator();
        kin_IFSC_A = new javax.swing.JTextField();
        kin_branchA = new javax.swing.JTextField();
        kin_underlineA8 = new javax.swing.JSeparator();
        kin_underlineA9 = new javax.swing.JSeparator();
        kin_underlineA0 = new javax.swing.JSeparator();
        kin_NOK_A = new javax.swing.JTextField();
        kin_underlineA1 = new javax.swing.JSeparator();
        kin_mobile_numberA = new javax.swing.JTextField();
        kin_underlineA2 = new javax.swing.JSeparator();
        kin_relation_A = new javax.swing.JTextField();
        labelA = new javax.swing.JLabel();
        okayA = new javax.swing.JLabel();
        ScrollPaneB = new javax.swing.JScrollPane();
        panelB = new javax.swing.JPanel();
        panelB_bottom_line = new javax.swing.JSeparator();
        purpose_underlineB = new javax.swing.JSeparator();
        purpose_textfieldB = new javax.swing.JTextField();
        purpose_labelB = new javax.swing.JLabel();
        DOA_labelB = new javax.swing.JLabel();
        DOA_date_comboboxB = new javax.swing.JComboBox<>();
        DOA_month_comboboxB = new javax.swing.JComboBox<>();
        DOA_year_comboboxB = new javax.swing.JComboBox<>();
        upload_photo_labelB = new javax.swing.JLabel();
        rank_comboboxB = new javax.swing.JComboBox<>();
        name_underlineB = new javax.swing.JSeparator();
        name_textfieldB = new javax.swing.JTextField();
        name_labelB = new javax.swing.JLabel();
        medical_category_labelB = new javax.swing.JLabel();
        birth_labelB = new javax.swing.JLabel();
        birth_year_comboboxB = new javax.swing.JComboBox<>();
        birth_date_comboboxB = new javax.swing.JComboBox<>();
        birth_month_comboboxB = new javax.swing.JComboBox<>();
        courses_done_labelB = new javax.swing.JLabel();
        courses_underlineB = new javax.swing.JSeparator();
        courses_textfieldB = new javax.swing.JTextField();
        army_number_underlineB = new javax.swing.JSeparator();
        army_number_textfieldB = new javax.swing.JTextField();
        army_number_labelB = new javax.swing.JLabel();
        company_underlineB = new javax.swing.JSeparator();
        company_textfieldB = new javax.swing.JTextField();
        company_labelB = new javax.swing.JLabel();
        location_underlineB = new javax.swing.JSeparator();
        location_textfieldB = new javax.swing.JTextField();
        location_labelB = new javax.swing.JLabel();
        indl_mob_labelB = new javax.swing.JLabel();
        label_91B = new javax.swing.JLabel();
        indl_mob_underlineB = new javax.swing.JSeparator();
        indl_mob_textfieldB = new javax.swing.JTextField();
        DOJ_labelB = new javax.swing.JLabel();
        DOJ_date_comboboxB = new javax.swing.JComboBox<>();
        DOJ_month_comboboxB = new javax.swing.JComboBox<>();
        DOJ_year_comboboxB = new javax.swing.JComboBox<>();
        medical_category_underlineB = new javax.swing.JSeparator();
        medical_category_textfieldB = new javax.swing.JTextField();
        rank_labelB = new javax.swing.JLabel();
        select_file_buttonB = new javax.swing.JPanel();
        select_file_labelB = new javax.swing.JLabel();
        indl_ID_details_labelB = new javax.swing.JLabel();
        indl_underlineB0 = new javax.swing.JSeparator();
        indl_Icard_numberB = new javax.swing.JTextField();
        indl_underlineB = new javax.swing.JSeparator();
        indl_PAN_B = new javax.swing.JTextField();
        indl_underlineB2 = new javax.swing.JSeparator();
        indl_aadharB = new javax.swing.JTextField();
        indl_underlineB3 = new javax.swing.JSeparator();
        indl_mailB = new javax.swing.JTextField();
        indl_bank_nameB = new javax.swing.JTextField();
        indl_account_numberB = new javax.swing.JTextField();
        indl_underlineB4 = new javax.swing.JSeparator();
        indl_underlineB5 = new javax.swing.JSeparator();
        midB1 = new javax.swing.JSeparator();
        midB3 = new javax.swing.JSeparator();
        indl_IFSC_B = new javax.swing.JTextField();
        indl_branchB = new javax.swing.JTextField();
        indl_underlineB6 = new javax.swing.JSeparator();
        indl_underlineB7 = new javax.swing.JSeparator();
        NOK_detailsB = new javax.swing.JLabel();
        kin_underlineB3 = new javax.swing.JSeparator();
        kin_PAN_B = new javax.swing.JTextField();
        kin_underlineB4 = new javax.swing.JSeparator();
        kin_aadharB = new javax.swing.JTextField();
        kin_underlineB5 = new javax.swing.JSeparator();
        kin_mailB = new javax.swing.JTextField();
        kin_bank_nameB = new javax.swing.JTextField();
        kin_account_numberB = new javax.swing.JTextField();
        kin_underlineB6 = new javax.swing.JSeparator();
        kin_underlineB7 = new javax.swing.JSeparator();
        midA6 = new javax.swing.JSeparator();
        kin_IFSC_B = new javax.swing.JTextField();
        kin_branchB = new javax.swing.JTextField();
        kin_underlineB8 = new javax.swing.JSeparator();
        kin_underlineB9 = new javax.swing.JSeparator();
        kin_underlineB0 = new javax.swing.JSeparator();
        kin_NOK_B = new javax.swing.JTextField();
        kin_underlineB1 = new javax.swing.JSeparator();
        kin_mobile_numberB = new javax.swing.JTextField();
        kin_underlineB2 = new javax.swing.JSeparator();
        kin_relation_B = new javax.swing.JTextField();
        address_labelA5 = new javax.swing.JLabel();
        villageB = new javax.swing.JTextField();
        village_underlineB = new javax.swing.JSeparator();
        PO_B = new javax.swing.JTextField();
        PO_underlineB = new javax.swing.JSeparator();
        distB = new javax.swing.JTextField();
        dist_underlineB = new javax.swing.JSeparator();
        PIN_underlineB = new javax.swing.JSeparator();
        PIN_B = new javax.swing.JTextField();
        state_underlineB = new javax.swing.JSeparator();
        stateB = new javax.swing.JTextField();
        teh_underlineB = new javax.swing.JSeparator();
        tehB = new javax.swing.JTextField();
        add_buttonB = new javax.swing.JPanel();
        add_labelA1 = new javax.swing.JLabel();
        okayB = new javax.swing.JLabel();
        labelB = new javax.swing.JLabel();
        ScrollPaneC = new javax.swing.JScrollPane();
        panelC = new javax.swing.JPanel();
        service_number_underlineC = new javax.swing.JSeparator();
        service_number_textfieldC = new javax.swing.JTextField();
        service_number_labelC = new javax.swing.JLabel();
        panelA_bottom_line2 = new javax.swing.JSeparator();
        okayC = new javax.swing.JLabel();
        name_labelC = new javax.swing.JLabel();
        name = new javax.swing.JLabel();
        Photo = new javax.swing.JPanel();
        photo_labelC = new javax.swing.JLabel();
        rank_labelC = new javax.swing.JLabel();
        rank = new javax.swing.JLabel();
        company_labelC = new javax.swing.JLabel();
        company = new javax.swing.JLabel();
        indl_mobileC = new javax.swing.JLabel();
        indl_mobile_labelC = new javax.swing.JLabel();
        NOK_name_labelC = new javax.swing.JLabel();
        medical_category_labelC = new javax.swing.JLabel();
        courses_done_labelC = new javax.swing.JLabel();
        courses_doneC = new javax.swing.JLabel();
        medical_category = new javax.swing.JLabel();
        NOK_nameC = new javax.swing.JLabel();
        trade_labelC = new javax.swing.JLabel();
        tradeC = new javax.swing.JLabel();
        DOB_labelC = new javax.swing.JLabel();
        DOB = new javax.swing.JLabel();
        unitC = new javax.swing.JLabel();
        unit_labelC = new javax.swing.JLabel();
        AS_C = new javax.swing.JLabel();
        AS_labelC = new javax.swing.JLabel();
        locationC = new javax.swing.JLabel();
        location_labelC = new javax.swing.JLabel();
        NOK_relation_labelC = new javax.swing.JLabel();
        NOK_relationC = new javax.swing.JLabel();
        NOK_mobile_labelC = new javax.swing.JLabel();
        NOK_mobileC = new javax.swing.JLabel();
        address_labelC = new javax.swing.JLabel();
        villC = new javax.swing.JLabel();
        PO_labelC = new javax.swing.JLabel();
        PO_C = new javax.swing.JLabel();
        dist_labelC = new javax.swing.JLabel();
        distC = new javax.swing.JLabel();
        state_labelC = new javax.swing.JLabel();
        stateC = new javax.swing.JLabel();
        teh_labelC = new javax.swing.JLabel();
        tehsil = new javax.swing.JLabel();
        PIN_labelC = new javax.swing.JLabel();
        PIN_C = new javax.swing.JLabel();
        vill_labelC = new javax.swing.JLabel();
        midC2 = new javax.swing.JSeparator();
        midC1 = new javax.swing.JSeparator();
        CO_mob_labelC = new javax.swing.JLabel();
        CO_mob = new javax.swing.JLabel();
        ADJT_mob_labelC = new javax.swing.JLabel();
        ADJT_mob = new javax.swing.JLabel();
        DateIN_ScrollPaneC = new javax.swing.JScrollPane();
        DateIN_TableC = new javax.swing.JTable();
        DateOUT_ScrollPaneC = new javax.swing.JScrollPane();
        DateOUT_TableC = new javax.swing.JTable();
        attachment_labelC = new javax.swing.JLabel();
        panelD = new javax.swing.JPanel();
        ScrollPaneD = new javax.swing.JScrollPane();
        TableD = new javax.swing.JTable();
        panelE = new javax.swing.JPanel();
        ScrollPaneE = new javax.swing.JScrollPane();
        TableE = new javax.swing.JTable();
        panelG = new javax.swing.JPanel();
        ScrollPaneG_IN = new javax.swing.JScrollPane();
        TableG_IN = new javax.swing.JTable();
        service_number_label = new javax.swing.JLabel();
        service_numberG = new javax.swing.JTextField();
        textfield_underline = new javax.swing.JSeparator();
        okayG = new javax.swing.JLabel();
        ScrollPaneG_OUT = new javax.swing.JScrollPane();
        TableG_OUT = new javax.swing.JTable();
        ScrollPaneF = new javax.swing.JScrollPane();
        panelF = new javax.swing.JPanel();
        upload_photo_labelF = new javax.swing.JLabel();
        rank_comboboxF = new javax.swing.JComboBox<>();
        name_underlineF = new javax.swing.JSeparator();
        name_textfieldF = new javax.swing.JTextField();
        name_labelF = new javax.swing.JLabel();
        medical_category_labelF = new javax.swing.JLabel();
        birth_labelF = new javax.swing.JLabel();
        birth_year_comboboxF = new javax.swing.JComboBox<>();
        birth_date_comboboxF = new javax.swing.JComboBox<>();
        birth_month_comboboxF = new javax.swing.JComboBox<>();
        courses_done_labelF = new javax.swing.JLabel();
        courses_underlineF = new javax.swing.JSeparator();
        courses_textfieldF = new javax.swing.JTextField();
        indl_ID_details_labelF = new javax.swing.JLabel();
        army_number_labelF = new javax.swing.JLabel();
        company_underlineF = new javax.swing.JSeparator();
        company_textfieldF = new javax.swing.JTextField();
        company_labelF = new javax.swing.JLabel();
        panelF_bottom_line = new javax.swing.JSeparator();
        indl_mob_labelF = new javax.swing.JLabel();
        label_91F1 = new javax.swing.JLabel();
        DOJ_labelF = new javax.swing.JLabel();
        medical_category_underlineF = new javax.swing.JSeparator();
        medical_category_textfieldF = new javax.swing.JTextField();
        rank_labelF = new javax.swing.JLabel();
        select_file_buttonF = new javax.swing.JPanel();
        select_file_labelF = new javax.swing.JLabel();
        indl_underline8 = new javax.swing.JSeparator();
        indl_Icard_numberF = new javax.swing.JTextField();
        indl_underline9 = new javax.swing.JSeparator();
        indl_PAN_F = new javax.swing.JTextField();
        indl_underline10 = new javax.swing.JSeparator();
        indl_aadharF = new javax.swing.JTextField();
        indl_underline11 = new javax.swing.JSeparator();
        indl_mailF = new javax.swing.JTextField();
        indl_bank_nameF = new javax.swing.JTextField();
        indl_account_numberF = new javax.swing.JTextField();
        indl_underline12 = new javax.swing.JSeparator();
        indl_underline13 = new javax.swing.JSeparator();
        midF0 = new javax.swing.JSeparator();
        village_underlineF = new javax.swing.JSeparator();
        villageF = new javax.swing.JTextField();
        teh_underlineF = new javax.swing.JSeparator();
        tehF = new javax.swing.JTextField();
        PO_underlineF = new javax.swing.JSeparator();
        PO_F = new javax.swing.JTextField();
        state_underlineF = new javax.swing.JSeparator();
        stateF = new javax.swing.JTextField();
        PIN_F = new javax.swing.JTextField();
        distF = new javax.swing.JTextField();
        dist_underlineF = new javax.swing.JSeparator();
        PIN_underlineF = new javax.swing.JSeparator();
        midF2 = new javax.swing.JSeparator();
        address_labelF = new javax.swing.JLabel();
        contact_underlineF = new javax.swing.JSeparator();
        contact_textfieldF = new javax.swing.JTextField();
        indl_IFSC_F = new javax.swing.JTextField();
        indl_branchF = new javax.swing.JTextField();
        indl_underline14 = new javax.swing.JSeparator();
        indl_underline15 = new javax.swing.JSeparator();
        NOK_detailsF = new javax.swing.JLabel();
        kin_underlineF4 = new javax.swing.JSeparator();
        kin_PAN_F = new javax.swing.JTextField();
        kin_underlineF5 = new javax.swing.JSeparator();
        kin_aadharF = new javax.swing.JTextField();
        kin_underlineF6 = new javax.swing.JSeparator();
        kin_mailF = new javax.swing.JTextField();
        kin_bank_nameF = new javax.swing.JTextField();
        kin_account_numberF = new javax.swing.JTextField();
        kin_underlineF7 = new javax.swing.JSeparator();
        kin_underlineF8 = new javax.swing.JSeparator();
        midF1 = new javax.swing.JSeparator();
        kin_IFSC_F = new javax.swing.JTextField();
        kin_branchF = new javax.swing.JTextField();
        kin_underlineF9 = new javax.swing.JSeparator();
        kin_underlineF10 = new javax.swing.JSeparator();
        kin_underlineF1 = new javax.swing.JSeparator();
        kin_NOK_F = new javax.swing.JTextField();
        kin_underlineF2 = new javax.swing.JSeparator();
        kin_mobile_numberF = new javax.swing.JTextField();
        kin_underlineF3 = new javax.swing.JSeparator();
        kin_relation_F = new javax.swing.JTextField();
        update_buttonF = new javax.swing.JPanel();
        update_labelF = new javax.swing.JLabel();
        Attachment_labelF = new javax.swing.JLabel();
        DOA_labelF = new javax.swing.JLabel();
        update_labelF1 = new javax.swing.JLabel();
        DOA = new javax.swing.JLabel();
        service_number = new javax.swing.JLabel();
        DOJ = new javax.swing.JLabel();
        midF3 = new javax.swing.JSeparator();
        midF4 = new javax.swing.JSeparator();
        DOJ_labelF1 = new javax.swing.JLabel();
        DOJ_date_comboboxF = new javax.swing.JComboBox<>();
        DOJ_month_comboboxF = new javax.swing.JComboBox<>();
        DOJ_year_comboboxF = new javax.swing.JComboBox<>();
        DOA_labelF1 = new javax.swing.JLabel();
        DOA_date_comboboxF = new javax.swing.JComboBox<>();
        DOA_month_comboboxF = new javax.swing.JComboBox<>();
        DOA_year_comboboxF = new javax.swing.JComboBox<>();
        fullscreen_buttonD = new javax.swing.JPanel();
        fullscreen_labelD = new javax.swing.JLabel();
        fullscreen_buttonE = new javax.swing.JPanel();
        fullscreen_labelE = new javax.swing.JLabel();
        refresh_buttonD = new javax.swing.JPanel();
        refresh_labelD = new javax.swing.JLabel();
        refresh_buttonE = new javax.swing.JPanel();
        refresh_labelE = new javax.swing.JLabel();
        remove_buttonC = new javax.swing.JPanel();
        remove_labelC = new javax.swing.JLabel();
        print_buttonC = new javax.swing.JPanel();
        print_labelC = new javax.swing.JLabel();
        print_buttonD = new javax.swing.JPanel();
        print_labelD = new javax.swing.JLabel();
        print_buttonE = new javax.swing.JPanel();
        print_labelE = new javax.swing.JLabel();
        edit_buttonG = new javax.swing.JPanel();
        edit_labelG = new javax.swing.JLabel();
        fullscreen_panelD = new javax.swing.JPanel();
        exit_fullscreen_buttonD = new javax.swing.JPanel();
        exit_fullscreen_labelD = new javax.swing.JLabel();
        fullscreen_ScrollPaneD = new javax.swing.JScrollPane();
        fullscreen_tableD = new javax.swing.JTable();
        fullscreen_panelE = new javax.swing.JPanel();
        exit_fullscreen_buttonE = new javax.swing.JPanel();
        exit_fullscreen_labelE = new javax.swing.JLabel();
        fullscreen_ScrollPaneE = new javax.swing.JScrollPane();
        fullscreen_tableE = new javax.swing.JTable();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setBackground(new java.awt.Color(255, 255, 255));
        setBounds(new java.awt.Rectangle(0, 0, 1280, 720));
        setLocation(new java.awt.Point(0, 0));
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
        menuA_label.setText("Attachment IN");

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

        menuB_icon1.setForeground(new java.awt.Color(255, 255, 255));
        menuB_icon1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        menuB_icon1.setText("B");

        menuB_label1.setFont(new java.awt.Font("Roboto", 1, 18)); // NOI18N
        menuB_label1.setForeground(new java.awt.Color(255, 255, 255));
        menuB_label1.setText("Attachment OUT");

        javax.swing.GroupLayout menuBLayout = new javax.swing.GroupLayout(menuB);
        menuB.setLayout(menuBLayout);
        menuBLayout.setHorizontalGroup(
            menuBLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(menuBLayout.createSequentialGroup()
                .addGap(27, 27, 27)
                .addComponent(menuB_icon1)
                .addGap(33, 33, 33)
                .addComponent(menuB_label1)
                .addContainerGap(107, Short.MAX_VALUE))
        );
        menuBLayout.setVerticalGroup(
            menuBLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(menuB_label1, javax.swing.GroupLayout.DEFAULT_SIZE, 60, Short.MAX_VALUE)
            .addComponent(menuB_icon1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        side_pane.add(menuB, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 300, 320, 60));

        menuC.setBackground(new java.awt.Color(64, 43, 100));
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
        menuC_label.setText("View/ Remove");

        javax.swing.GroupLayout menuCLayout = new javax.swing.GroupLayout(menuC);
        menuC.setLayout(menuCLayout);
        menuCLayout.setHorizontalGroup(
            menuCLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(menuCLayout.createSequentialGroup()
                .addGap(27, 27, 27)
                .addComponent(menuC_icon)
                .addGap(33, 33, 33)
                .addComponent(menuC_label, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(103, Short.MAX_VALUE))
        );
        menuCLayout.setVerticalGroup(
            menuCLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(menuC_label, javax.swing.GroupLayout.DEFAULT_SIZE, 60, Short.MAX_VALUE)
            .addComponent(menuC_icon, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        side_pane.add(menuC, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 360, 320, 60));

        menuD.setBackground(new java.awt.Color(64, 43, 100));
        menuD.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                menuDMouseClicked(evt);
            }
        });

        menuD_icon.setForeground(new java.awt.Color(255, 255, 255));
        menuD_icon.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        menuD_icon.setText("D");

        menuD_label.setFont(new java.awt.Font("Roboto", 1, 18)); // NOI18N
        menuD_label.setForeground(new java.awt.Color(255, 255, 255));
        menuD_label.setText("All Attachment IN");
        menuD_label.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                menuD_labelMouseClicked(evt);
            }
        });

        javax.swing.GroupLayout menuDLayout = new javax.swing.GroupLayout(menuD);
        menuD.setLayout(menuDLayout);
        menuDLayout.setHorizontalGroup(
            menuDLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(menuDLayout.createSequentialGroup()
                .addGap(27, 27, 27)
                .addComponent(menuD_icon)
                .addGap(33, 33, 33)
                .addComponent(menuD_label, javax.swing.GroupLayout.PREFERRED_SIZE, 172, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(81, Short.MAX_VALUE))
        );
        menuDLayout.setVerticalGroup(
            menuDLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(menuD_label, javax.swing.GroupLayout.DEFAULT_SIZE, 60, Short.MAX_VALUE)
            .addComponent(menuD_icon, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        side_pane.add(menuD, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 420, 320, 60));

        menuE.setBackground(new java.awt.Color(64, 43, 100));
        menuE.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                menuEMouseClicked(evt);
            }
        });

        menuE_icon.setForeground(new java.awt.Color(255, 255, 255));
        menuE_icon.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        menuE_icon.setText("E");

        menuE_label.setFont(new java.awt.Font("Roboto", 1, 18)); // NOI18N
        menuE_label.setForeground(new java.awt.Color(255, 255, 255));
        menuE_label.setText("All Attachment OUT");
        menuE_label.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                menuE_labelMouseClicked(evt);
            }
        });

        javax.swing.GroupLayout menuELayout = new javax.swing.GroupLayout(menuE);
        menuE.setLayout(menuELayout);
        menuELayout.setHorizontalGroup(
            menuELayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(menuELayout.createSequentialGroup()
                .addGap(27, 27, 27)
                .addComponent(menuE_icon)
                .addGap(33, 33, 33)
                .addComponent(menuE_label, javax.swing.GroupLayout.PREFERRED_SIZE, 190, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(64, Short.MAX_VALUE))
        );
        menuELayout.setVerticalGroup(
            menuELayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(menuE_label, javax.swing.GroupLayout.DEFAULT_SIZE, 60, Short.MAX_VALUE)
            .addComponent(menuE_icon, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        side_pane.add(menuE, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 480, 320, 60));

        title_label.setFont(new java.awt.Font("Montserrat", 0, 28)); // NOI18N
        title_label.setForeground(new java.awt.Color(255, 255, 255));
        title_label.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        title_label.setText("Attachment");
        side_pane.add(title_label, new org.netbeans.lib.awtextra.AbsoluteConstraints(90, 130, 190, 40));

        title_icon.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        title_icon.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/PNGS/attach white.png"))); // NOI18N
        side_pane.add(title_icon, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 110, 60, 80));

        menuF.setBackground(new java.awt.Color(64, 43, 100));
        menuF.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                menuFMouseClicked(evt);
            }
        });

        menuF_icon.setForeground(new java.awt.Color(255, 255, 255));
        menuF_icon.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        menuF_icon.setText("F");

        menuF_label.setFont(new java.awt.Font("Roboto", 1, 18)); // NOI18N
        menuF_label.setForeground(new java.awt.Color(255, 255, 255));
        menuF_label.setText("Update");
        menuF_label.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                menuF_labelMouseClicked(evt);
            }
        });

        javax.swing.GroupLayout menuFLayout = new javax.swing.GroupLayout(menuF);
        menuF.setLayout(menuFLayout);
        menuFLayout.setHorizontalGroup(
            menuFLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(menuFLayout.createSequentialGroup()
                .addGap(27, 27, 27)
                .addComponent(menuF_icon)
                .addGap(33, 33, 33)
                .addComponent(menuF_label, javax.swing.GroupLayout.PREFERRED_SIZE, 86, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(168, Short.MAX_VALUE))
        );
        menuFLayout.setVerticalGroup(
            menuFLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(menuF_label, javax.swing.GroupLayout.DEFAULT_SIZE, 60, Short.MAX_VALUE)
            .addComponent(menuF_icon, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        side_pane.add(menuF, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 540, 320, 60));

        bg.add(side_pane, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 320, 760));

        ScrollPaneA.setBorder(null);
        ScrollPaneA.setHorizontalScrollBarPolicy(javax.swing.ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
        ScrollPaneA.setPreferredSize(new java.awt.Dimension(910, 480));

        panelA.setBackground(new java.awt.Color(255, 255, 255));
        panelA.setFocusable(false);
        panelA.setMaximumSize(new java.awt.Dimension(950, 1310));
        panelA.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        upload_photo_labelA.setBackground(new java.awt.Color(255, 255, 255));
        upload_photo_labelA.setFont(new java.awt.Font("Montserrat", 0, 28)); // NOI18N
        upload_photo_labelA.setForeground(new java.awt.Color(51, 51, 51));
        upload_photo_labelA.setText("Upload Photo");
        panelA.add(upload_photo_labelA, new org.netbeans.lib.awtextra.AbsoluteConstraints(490, 490, -1, 40));

        rank_comboboxA.setFont(new java.awt.Font("Roboto", 0, 18)); // NOI18N
        rank_comboboxA.setForeground(new java.awt.Color(44, 62, 80));
        rank_comboboxA.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Sepoy", "Naik", "Havildar", "Naib Subedar", "Subedar", "Subedar Major", "Lieutenant", "Captain", "Major", "Lt Colonel", "Colonel", "Brigadier", "Major General", "Lt General", "General" }));
        rank_comboboxA.setBorder(null);
        panelA.add(rank_comboboxA, new org.netbeans.lib.awtextra.AbsoluteConstraints(490, 200, 320, 40));

        name_underlineA.setForeground(new java.awt.Color(54, 33, 89));
        panelA.add(name_underlineA, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 240, 320, 20));

        name_textfieldA.setFont(new java.awt.Font("Roboto", 0, 24)); // NOI18N
        name_textfieldA.setForeground(new java.awt.Color(54, 33, 89));
        name_textfieldA.setBorder(null);
        name_textfieldA.setMargin(new java.awt.Insets(2, 4, 2, 2));
        panelA.add(name_textfieldA, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 200, 320, 40));

        name_labelA.setFont(new java.awt.Font("Montserrat", 0, 28)); // NOI18N
        name_labelA.setForeground(new java.awt.Color(51, 51, 51));
        name_labelA.setText("Name");
        panelA.add(name_labelA, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 160, -1, 40));

        medical_category_labelA.setBackground(new java.awt.Color(255, 255, 255));
        medical_category_labelA.setFont(new java.awt.Font("Montserrat", 0, 28)); // NOI18N
        medical_category_labelA.setForeground(new java.awt.Color(51, 51, 51));
        medical_category_labelA.setText("Medical Category");
        panelA.add(medical_category_labelA, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 490, -1, 40));

        birth_labelA.setBackground(new java.awt.Color(255, 255, 255));
        birth_labelA.setFont(new java.awt.Font("Montserrat", 0, 28)); // NOI18N
        birth_labelA.setForeground(new java.awt.Color(51, 51, 51));
        birth_labelA.setText("Date of Birth");
        panelA.add(birth_labelA, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 380, -1, 40));

        birth_year_comboboxA.setFont(new java.awt.Font("Roboto", 0, 18)); // NOI18N
        birth_year_comboboxA.setForeground(new java.awt.Color(44, 62, 80));
        birth_year_comboboxA.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "YYYY", "1950", "1951", "1952", "1953", "1954", "1955", "1956", "1957", "1958", "1959", "1960", "1961", "1962", "1963", "1964", "1965", "1966", "1967", "1968", "1969", "1970", "1971", "1972", "1973", "1974", "1975", "1976", "1977", "1978", "1979", "1980", "1981", "1982", "1983", "1984", "1985", "1986", "1987", "1988", "1989", "1990", "1991", "1992", "1993", "1994", "1995", "1996", "1997", "1998", "1999", "2000", "2001", "2002", "2003", "2004", "2005", "2006", "2007", "2008", "2009", "2010", "2011", "2012", "2013", "2014", "2015", "2016", "2017", "2018", "2019", "2020", "2021", "2022", "2023", "2024", "2025", "2026", "2027", "2028", "2029", "2030", "2031", "2032", "2033", "2034", "2035", "2036", "2037", "2038", "2039", "2040", "2041", "2042", "2043", "2044", "2045", "2046", "2047", "2048", "2049", "2050", "2051", "2052", "2053", "2054", "2055", "2056", "2057", "2058", "2059", "2060", "2061", "2062", "2063", "2064", "2065", "2066", "2067", "2068", "2069", "2070", "2071", "2072", "2073", "2074", "2075", "2076", "2077", "2078", "2079", "2080", "2081", "2082", "2083", "2084", "2085", "2086", "2087", "2088", "2089", "2090", "2091", "2092", "2093", "2094", "2095", "2096", "2097", "2098", "2099", "2100", " " }));
        birth_year_comboboxA.setBorder(null);
        panelA.add(birth_year_comboboxA, new org.netbeans.lib.awtextra.AbsoluteConstraints(300, 420, 100, 40));

        birth_date_comboboxA.setFont(new java.awt.Font("Roboto", 0, 18)); // NOI18N
        birth_date_comboboxA.setForeground(new java.awt.Color(44, 62, 80));
        birth_date_comboboxA.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "DD", "01", "02", "03", "04", "05", "06", "07", "08", "09", "10", "11", "12", "13", "14", "15", "16", "17", "18", "19", "20", "21", "22", "23", "24", "25", "26", "27", "28", "29", "30", "31" }));
        birth_date_comboboxA.setBorder(null);
        panelA.add(birth_date_comboboxA, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 420, 100, 40));

        birth_month_comboboxA.setFont(new java.awt.Font("Roboto", 0, 18)); // NOI18N
        birth_month_comboboxA.setForeground(new java.awt.Color(44, 62, 80));
        birth_month_comboboxA.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "MM", "01", "02", "03", "04", "05", "06", "07", "08", "09", "10", "11", "12" }));
        birth_month_comboboxA.setBorder(null);
        panelA.add(birth_month_comboboxA, new org.netbeans.lib.awtextra.AbsoluteConstraints(190, 420, 100, 40));

        courses_done_labelA.setFont(new java.awt.Font("Montserrat", 0, 28)); // NOI18N
        courses_done_labelA.setForeground(new java.awt.Color(51, 51, 51));
        courses_done_labelA.setText("Courses Done");
        panelA.add(courses_done_labelA, new org.netbeans.lib.awtextra.AbsoluteConstraints(490, 820, -1, 40));

        courses_underlineA.setForeground(new java.awt.Color(54, 33, 89));
        panelA.add(courses_underlineA, new org.netbeans.lib.awtextra.AbsoluteConstraints(490, 900, 320, 20));

        courses_textfieldA.setFont(new java.awt.Font("Roboto", 0, 24)); // NOI18N
        courses_textfieldA.setForeground(new java.awt.Color(54, 33, 89));
        courses_textfieldA.setBorder(null);
        courses_textfieldA.setMargin(new java.awt.Insets(2, 4, 2, 2));
        panelA.add(courses_textfieldA, new org.netbeans.lib.awtextra.AbsoluteConstraints(490, 860, 320, 40));

        indl_ID_details_label.setFont(new java.awt.Font("Montserrat", 0, 28)); // NOI18N
        indl_ID_details_label.setForeground(new java.awt.Color(51, 51, 51));
        indl_ID_details_label.setText("Indl ID Details");
        panelA.add(indl_ID_details_label, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 1020, -1, 40));

        army_number_underlineA.setForeground(new java.awt.Color(54, 33, 89));
        panelA.add(army_number_underlineA, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 130, 320, 20));

        army_number_textfieldA.setFont(new java.awt.Font("Roboto", 0, 24)); // NOI18N
        army_number_textfieldA.setForeground(new java.awt.Color(54, 33, 89));
        army_number_textfieldA.setBorder(null);
        army_number_textfieldA.setMargin(new java.awt.Insets(2, 4, 2, 2));
        army_number_textfieldA.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                army_number_textfieldAActionPerformed(evt);
            }
        });
        army_number_textfieldA.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                army_number_textfieldAKeyPressed(evt);
            }
        });
        panelA.add(army_number_textfieldA, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 90, 320, 40));

        army_number_labelA.setFont(new java.awt.Font("Montserrat", 0, 28)); // NOI18N
        army_number_labelA.setForeground(new java.awt.Color(51, 51, 51));
        army_number_labelA.setText("Army Number");
        panelA.add(army_number_labelA, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 50, -1, 40));

        company_underlineA.setForeground(new java.awt.Color(54, 33, 89));
        panelA.add(company_underlineA, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 350, 320, 20));

        company_textfieldA.setFont(new java.awt.Font("Roboto", 0, 24)); // NOI18N
        company_textfieldA.setForeground(new java.awt.Color(54, 33, 89));
        company_textfieldA.setBorder(null);
        company_textfieldA.setMargin(new java.awt.Insets(2, 4, 2, 2));
        panelA.add(company_textfieldA, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 310, 320, 40));

        company_labelA.setFont(new java.awt.Font("Montserrat", 0, 28)); // NOI18N
        company_labelA.setForeground(new java.awt.Color(51, 51, 51));
        company_labelA.setText("Company");
        panelA.add(company_labelA, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 270, -1, 40));

        add_buttonA.setForeground(new java.awt.Color(240, 240, 240));
        add_buttonA.setMaximumSize(new java.awt.Dimension(95, 25));
        add_buttonA.setMinimumSize(new java.awt.Dimension(95, 25));
        add_buttonA.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                add_buttonAMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                add_buttonAMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                add_buttonAMouseExited(evt);
            }
        });
        add_buttonA.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        add_labelA.setFont(new java.awt.Font("Roboto", 0, 14)); // NOI18N
        add_labelA.setForeground(new java.awt.Color(54, 33, 89));
        add_labelA.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        add_labelA.setText("ADD");
        add_buttonA.add(add_labelA, new org.netbeans.lib.awtextra.AbsoluteConstraints(52, 13, 71, 24));

        panelA.add(add_buttonA, new org.netbeans.lib.awtextra.AbsoluteConstraints(630, 2120, 180, 50));

        panelA_bottom_line.setForeground(new java.awt.Color(54, 33, 89));
        panelA.add(panelA_bottom_line, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 2230, 910, -1));

        unit_underlineA.setForeground(new java.awt.Color(54, 33, 89));
        panelA.add(unit_underlineA, new org.netbeans.lib.awtextra.AbsoluteConstraints(490, 680, 320, 20));

        unit_textfieldA.setFont(new java.awt.Font("Roboto", 0, 24)); // NOI18N
        unit_textfieldA.setForeground(new java.awt.Color(54, 33, 89));
        unit_textfieldA.setBorder(null);
        unit_textfieldA.setMargin(new java.awt.Insets(2, 4, 2, 2));
        panelA.add(unit_textfieldA, new org.netbeans.lib.awtextra.AbsoluteConstraints(490, 640, 320, 40));

        unit_labelA.setFont(new java.awt.Font("Montserrat", 0, 28)); // NOI18N
        unit_labelA.setForeground(new java.awt.Color(51, 51, 51));
        unit_labelA.setText("Unit");
        panelA.add(unit_labelA, new org.netbeans.lib.awtextra.AbsoluteConstraints(490, 600, -1, 40));

        trade_underlineA.setForeground(new java.awt.Color(54, 33, 89));
        panelA.add(trade_underlineA, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 900, 320, 20));

        trade_textfieldA.setFont(new java.awt.Font("Roboto", 0, 24)); // NOI18N
        trade_textfieldA.setForeground(new java.awt.Color(54, 33, 89));
        trade_textfieldA.setBorder(null);
        trade_textfieldA.setMargin(new java.awt.Insets(2, 4, 2, 2));
        trade_textfieldA.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                trade_textfieldAActionPerformed(evt);
            }
        });
        panelA.add(trade_textfieldA, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 860, 320, 40));

        trade_labelA.setFont(new java.awt.Font("Montserrat", 0, 28)); // NOI18N
        trade_labelA.setForeground(new java.awt.Color(51, 51, 51));
        trade_labelA.setText("Trade");
        panelA.add(trade_labelA, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 820, -1, 40));

        AS_underlineA.setForeground(new java.awt.Color(54, 33, 89));
        panelA.add(AS_underlineA, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 790, 320, 20));

        AS_textfieldA.setFont(new java.awt.Font("Roboto", 0, 24)); // NOI18N
        AS_textfieldA.setForeground(new java.awt.Color(54, 33, 89));
        AS_textfieldA.setBorder(null);
        AS_textfieldA.setMargin(new java.awt.Insets(2, 4, 2, 2));
        panelA.add(AS_textfieldA, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 750, 320, 40));

        AS_labelA.setFont(new java.awt.Font("Montserrat", 0, 28)); // NOI18N
        AS_labelA.setForeground(new java.awt.Color(51, 51, 51));
        AS_labelA.setText("Armed/Services");
        panelA.add(AS_labelA, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 710, -1, 40));

        location_underlineA.setForeground(new java.awt.Color(54, 33, 89));
        panelA.add(location_underlineA, new org.netbeans.lib.awtextra.AbsoluteConstraints(490, 790, 320, 20));

        location_textfieldA.setFont(new java.awt.Font("Roboto", 0, 24)); // NOI18N
        location_textfieldA.setForeground(new java.awt.Color(54, 33, 89));
        location_textfieldA.setBorder(null);
        location_textfieldA.setMargin(new java.awt.Insets(2, 4, 2, 2));
        panelA.add(location_textfieldA, new org.netbeans.lib.awtextra.AbsoluteConstraints(490, 750, 320, 40));

        location_labelA.setFont(new java.awt.Font("Montserrat", 0, 28)); // NOI18N
        location_labelA.setForeground(new java.awt.Color(51, 51, 51));
        location_labelA.setText("Location");
        panelA.add(location_labelA, new org.netbeans.lib.awtextra.AbsoluteConstraints(490, 710, -1, 40));

        indl_mob_labelA.setFont(new java.awt.Font("Montserrat", 0, 28)); // NOI18N
        indl_mob_labelA.setForeground(new java.awt.Color(51, 51, 51));
        indl_mob_labelA.setText("Indl Mob No.");
        panelA.add(indl_mob_labelA, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 600, -1, 40));

        label_91A1.setFont(new java.awt.Font("Montserrat", 0, 28)); // NOI18N
        label_91A1.setForeground(new java.awt.Color(51, 51, 51));
        label_91A1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        label_91A1.setText("+91");
        panelA.add(label_91A1, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 640, 50, 40));

        indl_mob_underlineA.setForeground(new java.awt.Color(54, 33, 89));
        panelA.add(indl_mob_underlineA, new org.netbeans.lib.awtextra.AbsoluteConstraints(140, 680, 260, 20));

        indl_mob_textfieldA.setFont(new java.awt.Font("Roboto", 0, 24)); // NOI18N
        indl_mob_textfieldA.setForeground(new java.awt.Color(54, 33, 89));
        indl_mob_textfieldA.setBorder(null);
        indl_mob_textfieldA.setMargin(new java.awt.Insets(2, 4, 2, 2));
        panelA.add(indl_mob_textfieldA, new org.netbeans.lib.awtextra.AbsoluteConstraints(140, 640, 260, 40));

        DOJ_labelA.setBackground(new java.awt.Color(255, 255, 255));
        DOJ_labelA.setFont(new java.awt.Font("Montserrat", 0, 28)); // NOI18N
        DOJ_labelA.setForeground(new java.awt.Color(51, 51, 51));
        DOJ_labelA.setText("Date of Joining");
        panelA.add(DOJ_labelA, new org.netbeans.lib.awtextra.AbsoluteConstraints(490, 380, -1, 40));

        DOJ_date_comboboxA.setFont(new java.awt.Font("Roboto", 0, 18)); // NOI18N
        DOJ_date_comboboxA.setForeground(new java.awt.Color(44, 62, 80));
        DOJ_date_comboboxA.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "DD", "01", "02", "03", "04", "05", "06", "07", "08", "09", "10", "11", "12", "13", "14", "15", "16", "17", "18", "19", "20", "21", "22", "23", "24", "25", "26", "27", "28", "29", "30", "31" }));
        DOJ_date_comboboxA.setBorder(null);
        DOJ_date_comboboxA.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                DOJ_date_comboboxAActionPerformed(evt);
            }
        });
        panelA.add(DOJ_date_comboboxA, new org.netbeans.lib.awtextra.AbsoluteConstraints(490, 420, 100, 40));

        DOJ_month_comboboxA.setFont(new java.awt.Font("Roboto", 0, 18)); // NOI18N
        DOJ_month_comboboxA.setForeground(new java.awt.Color(44, 62, 80));
        DOJ_month_comboboxA.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "MM", "01", "02", "03", "04", "05", "06", "07", "08", "09", "10", "11", "12" }));
        DOJ_month_comboboxA.setBorder(null);
        panelA.add(DOJ_month_comboboxA, new org.netbeans.lib.awtextra.AbsoluteConstraints(600, 420, 100, 40));

        DOJ_year_comboboxA.setFont(new java.awt.Font("Roboto", 0, 18)); // NOI18N
        DOJ_year_comboboxA.setForeground(new java.awt.Color(44, 62, 80));
        DOJ_year_comboboxA.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "YYYY", "1950", "1951", "1952", "1953", "1954", "1955", "1956", "1957", "1958", "1959", "1960", "1961", "1962", "1963", "1964", "1965", "1966", "1967", "1968", "1969", "1970", "1971", "1972", "1973", "1974", "1975", "1976", "1977", "1978", "1979", "1980", "1981", "1982", "1983", "1984", "1985", "1986", "1987", "1988", "1989", "1990", "1991", "1992", "1993", "1994", "1995", "1996", "1997", "1998", "1999", "2000", "2001", "2002", "2003", "2004", "2005", "2006", "2007", "2008", "2009", "2010", "2011", "2012", "2013", "2014", "2015", "2016", "2017", "2018", "2019", "2020", "2021", "2022", "2023", "2024", "2025", "2026", "2027", "2028", "2029", "2030", "2031", "2032", "2033", "2034", "2035", "2036", "2037", "2038", "2039", "2040", "2041", "2042", "2043", "2044", "2045", "2046", "2047", "2048", "2049", "2050", "2051", "2052", "2053", "2054", "2055", "2056", "2057", "2058", "2059", "2060", "2061", "2062", "2063", "2064", "2065", "2066", "2067", "2068", "2069", "2070", "2071", "2072", "2073", "2074", "2075", "2076", "2077", "2078", "2079", "2080", "2081", "2082", "2083", "2084", "2085", "2086", "2087", "2088", "2089", "2090", "2091", "2092", "2093", "2094", "2095", "2096", "2097", "2098", "2099", "2100", " " }));
        DOJ_year_comboboxA.setBorder(null);
        panelA.add(DOJ_year_comboboxA, new org.netbeans.lib.awtextra.AbsoluteConstraints(710, 420, 100, 40));

        medical_category_underlineA.setForeground(new java.awt.Color(54, 33, 89));
        panelA.add(medical_category_underlineA, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 570, 320, 20));

        medical_category_textfieldA.setFont(new java.awt.Font("Roboto", 0, 24)); // NOI18N
        medical_category_textfieldA.setForeground(new java.awt.Color(54, 33, 89));
        medical_category_textfieldA.setBorder(null);
        medical_category_textfieldA.setMargin(new java.awt.Insets(2, 4, 2, 2));
        panelA.add(medical_category_textfieldA, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 530, 320, 40));

        rank_labelA.setBackground(new java.awt.Color(255, 255, 255));
        rank_labelA.setFont(new java.awt.Font("Montserrat", 0, 28)); // NOI18N
        rank_labelA.setForeground(new java.awt.Color(51, 51, 51));
        rank_labelA.setText("Rank");
        panelA.add(rank_labelA, new org.netbeans.lib.awtextra.AbsoluteConstraints(490, 160, -1, 40));

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
        select_file_buttonA.add(select_file_labelA, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 0, 270, 40));

        panelA.add(select_file_buttonA, new org.netbeans.lib.awtextra.AbsoluteConstraints(490, 530, 320, 40));

        indl_underline0.setForeground(new java.awt.Color(54, 33, 89));
        panelA.add(indl_underline0, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 1110, 320, 10));

        indl_Icard_numberA.setFont(new java.awt.Font("Roboto", 0, 24)); // NOI18N
        indl_Icard_numberA.setForeground(new java.awt.Color(102, 102, 102));
        indl_Icard_numberA.setText("ICardNumber");
        indl_Icard_numberA.setBorder(null);
        indl_Icard_numberA.setMargin(new java.awt.Insets(2, 4, 2, 2));
        indl_Icard_numberA.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                indl_Icard_numberAFocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                indl_Icard_numberAFocusLost(evt);
            }
        });
        indl_Icard_numberA.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                indl_Icard_numberAMouseClicked(evt);
            }
        });
        panelA.add(indl_Icard_numberA, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 1070, 320, 40));

        indl_underline1.setForeground(new java.awt.Color(54, 33, 89));
        panelA.add(indl_underline1, new org.netbeans.lib.awtextra.AbsoluteConstraints(490, 1110, 320, 10));

        indl_PAN_A.setFont(new java.awt.Font("Roboto", 0, 24)); // NOI18N
        indl_PAN_A.setForeground(new java.awt.Color(102, 102, 102));
        indl_PAN_A.setText("PAN");
        indl_PAN_A.setBorder(null);
        indl_PAN_A.setMargin(new java.awt.Insets(2, 4, 2, 2));
        indl_PAN_A.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                indl_PAN_AFocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                indl_PAN_AFocusLost(evt);
            }
        });
        indl_PAN_A.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                indl_PAN_AMouseClicked(evt);
            }
        });
        indl_PAN_A.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                indl_PAN_AActionPerformed(evt);
            }
        });
        panelA.add(indl_PAN_A, new org.netbeans.lib.awtextra.AbsoluteConstraints(490, 1070, 320, 40));

        indl_underline2.setForeground(new java.awt.Color(54, 33, 89));
        panelA.add(indl_underline2, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 1170, 320, 10));

        indl_aadharA.setFont(new java.awt.Font("Roboto", 0, 24)); // NOI18N
        indl_aadharA.setForeground(new java.awt.Color(102, 102, 102));
        indl_aadharA.setText("Aadhar");
        indl_aadharA.setBorder(null);
        indl_aadharA.setMargin(new java.awt.Insets(2, 4, 2, 2));
        indl_aadharA.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                indl_aadharAFocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                indl_aadharAFocusLost(evt);
            }
        });
        indl_aadharA.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                indl_aadharAMouseClicked(evt);
            }
        });
        indl_aadharA.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                indl_aadharAActionPerformed(evt);
            }
        });
        panelA.add(indl_aadharA, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 1130, 320, 40));

        indl_underline3.setForeground(new java.awt.Color(54, 33, 89));
        panelA.add(indl_underline3, new org.netbeans.lib.awtextra.AbsoluteConstraints(490, 1170, 320, 10));

        indl_mailA.setFont(new java.awt.Font("Roboto", 0, 24)); // NOI18N
        indl_mailA.setForeground(new java.awt.Color(102, 102, 102));
        indl_mailA.setText("E-Mail");
        indl_mailA.setBorder(null);
        indl_mailA.setMargin(new java.awt.Insets(2, 4, 2, 2));
        indl_mailA.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                indl_mailAFocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                indl_mailAFocusLost(evt);
            }
        });
        indl_mailA.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                indl_mailAMouseClicked(evt);
            }
        });
        panelA.add(indl_mailA, new org.netbeans.lib.awtextra.AbsoluteConstraints(490, 1130, 320, 40));

        indl_bank_nameA.setFont(new java.awt.Font("Roboto", 0, 24)); // NOI18N
        indl_bank_nameA.setForeground(new java.awt.Color(102, 102, 102));
        indl_bank_nameA.setText("Bank Name");
        indl_bank_nameA.setBorder(null);
        indl_bank_nameA.setMargin(new java.awt.Insets(2, 4, 2, 2));
        indl_bank_nameA.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                indl_bank_nameAFocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                indl_bank_nameAFocusLost(evt);
            }
        });
        indl_bank_nameA.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                indl_bank_nameAMouseClicked(evt);
            }
        });
        panelA.add(indl_bank_nameA, new org.netbeans.lib.awtextra.AbsoluteConstraints(490, 1190, 320, 40));

        indl_account_numberA.setFont(new java.awt.Font("Roboto", 0, 24)); // NOI18N
        indl_account_numberA.setForeground(new java.awt.Color(102, 102, 102));
        indl_account_numberA.setText("Account Number");
        indl_account_numberA.setBorder(null);
        indl_account_numberA.setMargin(new java.awt.Insets(2, 4, 2, 2));
        indl_account_numberA.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                indl_account_numberAFocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                indl_account_numberAFocusLost(evt);
            }
        });
        indl_account_numberA.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                indl_account_numberAMouseClicked(evt);
            }
        });
        indl_account_numberA.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                indl_account_numberAActionPerformed(evt);
            }
        });
        panelA.add(indl_account_numberA, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 1190, 320, 40));

        indl_underline4.setForeground(new java.awt.Color(54, 33, 89));
        panelA.add(indl_underline4, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 1230, 320, 10));

        indl_underline5.setForeground(new java.awt.Color(54, 33, 89));
        panelA.add(indl_underline5, new org.netbeans.lib.awtextra.AbsoluteConstraints(490, 1230, 320, 10));

        midA1.setForeground(new java.awt.Color(204, 204, 255));
        panelA.add(midA1, new org.netbeans.lib.awtextra.AbsoluteConstraints(250, 970, 390, 10));

        village_underlineA.setForeground(new java.awt.Color(54, 33, 89));
        panelA.add(village_underlineA, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 1950, 320, 10));

        villageA.setFont(new java.awt.Font("Roboto", 0, 24)); // NOI18N
        villageA.setForeground(new java.awt.Color(102, 102, 102));
        villageA.setText("Vill");
        villageA.setBorder(null);
        villageA.setMargin(new java.awt.Insets(2, 4, 2, 2));
        villageA.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                villageAFocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                villageAFocusLost(evt);
            }
        });
        villageA.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                villageAMouseClicked(evt);
            }
        });
        panelA.add(villageA, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 1910, 320, 40));

        teh_underlineA.setForeground(new java.awt.Color(54, 33, 89));
        panelA.add(teh_underlineA, new org.netbeans.lib.awtextra.AbsoluteConstraints(490, 1950, 320, 10));

        tehA.setFont(new java.awt.Font("Roboto", 0, 24)); // NOI18N
        tehA.setForeground(new java.awt.Color(102, 102, 102));
        tehA.setText("Teh");
        tehA.setBorder(null);
        tehA.setMargin(new java.awt.Insets(2, 4, 2, 2));
        tehA.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                tehAFocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                tehAFocusLost(evt);
            }
        });
        tehA.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tehAMouseClicked(evt);
            }
        });
        tehA.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                tehAActionPerformed(evt);
            }
        });
        panelA.add(tehA, new org.netbeans.lib.awtextra.AbsoluteConstraints(490, 1910, 320, 40));

        PO_underlineA.setForeground(new java.awt.Color(54, 33, 89));
        panelA.add(PO_underlineA, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 2010, 320, 10));

        PO_A.setFont(new java.awt.Font("Roboto", 0, 24)); // NOI18N
        PO_A.setForeground(new java.awt.Color(102, 102, 102));
        PO_A.setText("PO");
        PO_A.setBorder(null);
        PO_A.setMargin(new java.awt.Insets(2, 4, 2, 2));
        PO_A.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                PO_AFocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                PO_AFocusLost(evt);
            }
        });
        PO_A.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                PO_AMouseClicked(evt);
            }
        });
        PO_A.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                PO_AActionPerformed(evt);
            }
        });
        panelA.add(PO_A, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 1970, 320, 40));

        state_underlineA.setForeground(new java.awt.Color(54, 33, 89));
        panelA.add(state_underlineA, new org.netbeans.lib.awtextra.AbsoluteConstraints(490, 2010, 320, 10));

        stateA.setFont(new java.awt.Font("Roboto", 0, 24)); // NOI18N
        stateA.setForeground(new java.awt.Color(102, 102, 102));
        stateA.setText("State");
        stateA.setBorder(null);
        stateA.setMargin(new java.awt.Insets(2, 4, 2, 2));
        stateA.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                stateAFocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                stateAFocusLost(evt);
            }
        });
        stateA.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                stateAMouseClicked(evt);
            }
        });
        panelA.add(stateA, new org.netbeans.lib.awtextra.AbsoluteConstraints(490, 1970, 320, 40));

        PIN_A.setFont(new java.awt.Font("Roboto", 0, 24)); // NOI18N
        PIN_A.setForeground(new java.awt.Color(102, 102, 102));
        PIN_A.setText("PIN");
        PIN_A.setBorder(null);
        PIN_A.setMargin(new java.awt.Insets(2, 4, 2, 2));
        PIN_A.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                PIN_AFocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                PIN_AFocusLost(evt);
            }
        });
        PIN_A.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                PIN_AMouseClicked(evt);
            }
        });
        panelA.add(PIN_A, new org.netbeans.lib.awtextra.AbsoluteConstraints(490, 2030, 320, 40));

        distA.setFont(new java.awt.Font("Roboto", 0, 24)); // NOI18N
        distA.setForeground(new java.awt.Color(102, 102, 102));
        distA.setText("Dist");
        distA.setBorder(null);
        distA.setMargin(new java.awt.Insets(2, 4, 2, 2));
        distA.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                distAFocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                distAFocusLost(evt);
            }
        });
        distA.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                distAMouseClicked(evt);
            }
        });
        distA.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                distAActionPerformed(evt);
            }
        });
        panelA.add(distA, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 2030, 320, 40));

        dist_underlineA.setForeground(new java.awt.Color(54, 33, 89));
        panelA.add(dist_underlineA, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 2070, 320, 10));

        PIN_underlineA.setForeground(new java.awt.Color(54, 33, 89));
        panelA.add(PIN_underlineA, new org.netbeans.lib.awtextra.AbsoluteConstraints(490, 2070, 320, 10));

        midA3.setForeground(new java.awt.Color(204, 204, 255));
        panelA.add(midA3, new org.netbeans.lib.awtextra.AbsoluteConstraints(250, 1810, 390, 10));

        address_labelA.setFont(new java.awt.Font("Montserrat", 0, 28)); // NOI18N
        address_labelA.setForeground(new java.awt.Color(51, 51, 51));
        address_labelA.setText("Address");
        panelA.add(address_labelA, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 1860, -1, 40));

        indl_mobile_numberA.setFont(new java.awt.Font("Montserrat", 0, 28)); // NOI18N
        indl_mobile_numberA.setForeground(new java.awt.Color(51, 51, 51));
        indl_mobile_numberA.setText("Indl Mob No.");
        panelA.add(indl_mobile_numberA, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 600, -1, 40));

        label_91A.setFont(new java.awt.Font("Montserrat", 0, 28)); // NOI18N
        label_91A.setForeground(new java.awt.Color(51, 51, 51));
        label_91A.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        label_91A.setText("+91");
        panelA.add(label_91A, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 640, 50, 40));

        contact_underlineA2.setForeground(new java.awt.Color(54, 33, 89));
        panelA.add(contact_underlineA2, new org.netbeans.lib.awtextra.AbsoluteConstraints(140, 680, 260, 20));

        contact_textfieldA2.setFont(new java.awt.Font("Roboto", 0, 24)); // NOI18N
        contact_textfieldA2.setForeground(new java.awt.Color(54, 33, 89));
        contact_textfieldA2.setBorder(null);
        contact_textfieldA2.setMargin(new java.awt.Insets(2, 4, 2, 2));
        panelA.add(contact_textfieldA2, new org.netbeans.lib.awtextra.AbsoluteConstraints(140, 640, 260, 40));

        indl_IFSC_A.setFont(new java.awt.Font("Roboto", 0, 24)); // NOI18N
        indl_IFSC_A.setForeground(new java.awt.Color(102, 102, 102));
        indl_IFSC_A.setText("IFSC");
        indl_IFSC_A.setBorder(null);
        indl_IFSC_A.setMargin(new java.awt.Insets(2, 4, 2, 2));
        indl_IFSC_A.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                indl_IFSC_AFocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                indl_IFSC_AFocusLost(evt);
            }
        });
        indl_IFSC_A.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                indl_IFSC_AMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                indl_IFSC_AMouseEntered(evt);
            }
        });
        panelA.add(indl_IFSC_A, new org.netbeans.lib.awtextra.AbsoluteConstraints(490, 1250, 320, 40));

        indl_branchA.setFont(new java.awt.Font("Roboto", 0, 24)); // NOI18N
        indl_branchA.setForeground(new java.awt.Color(102, 102, 102));
        indl_branchA.setText("Branch");
        indl_branchA.setBorder(null);
        indl_branchA.setMargin(new java.awt.Insets(2, 4, 2, 2));
        indl_branchA.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                indl_branchAFocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                indl_branchAFocusLost(evt);
            }
        });
        indl_branchA.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                indl_branchAMouseClicked(evt);
            }
        });
        indl_branchA.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                indl_branchAActionPerformed(evt);
            }
        });
        panelA.add(indl_branchA, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 1250, 320, 40));

        indl_underline6.setForeground(new java.awt.Color(54, 33, 89));
        panelA.add(indl_underline6, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 1290, 320, 10));

        indl_underline7.setForeground(new java.awt.Color(54, 33, 89));
        panelA.add(indl_underline7, new org.netbeans.lib.awtextra.AbsoluteConstraints(490, 1290, 320, 10));

        NOK_detailsA.setFont(new java.awt.Font("Montserrat", 0, 28)); // NOI18N
        NOK_detailsA.setForeground(new java.awt.Color(51, 51, 51));
        NOK_detailsA.setText("Details of Kin");
        panelA.add(NOK_detailsA, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 1410, -1, 40));

        kin_underlineA3.setForeground(new java.awt.Color(54, 33, 89));
        panelA.add(kin_underlineA3, new org.netbeans.lib.awtextra.AbsoluteConstraints(490, 1560, 320, 10));

        kin_PAN_A.setFont(new java.awt.Font("Roboto", 0, 24)); // NOI18N
        kin_PAN_A.setForeground(new java.awt.Color(102, 102, 102));
        kin_PAN_A.setText("PAN");
        kin_PAN_A.setBorder(null);
        kin_PAN_A.setMargin(new java.awt.Insets(2, 4, 2, 2));
        kin_PAN_A.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                kin_PAN_AFocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                kin_PAN_AFocusLost(evt);
            }
        });
        kin_PAN_A.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                kin_PAN_AMouseClicked(evt);
            }
        });
        kin_PAN_A.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                kin_PAN_AActionPerformed(evt);
            }
        });
        panelA.add(kin_PAN_A, new org.netbeans.lib.awtextra.AbsoluteConstraints(490, 1520, 320, 40));

        kin_underlineA4.setForeground(new java.awt.Color(54, 33, 89));
        panelA.add(kin_underlineA4, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 1620, 320, 10));

        kin_aadharA.setFont(new java.awt.Font("Roboto", 0, 24)); // NOI18N
        kin_aadharA.setForeground(new java.awt.Color(102, 102, 102));
        kin_aadharA.setText("Aadhar");
        kin_aadharA.setBorder(null);
        kin_aadharA.setMargin(new java.awt.Insets(2, 4, 2, 2));
        kin_aadharA.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                kin_aadharAFocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                kin_aadharAFocusLost(evt);
            }
        });
        kin_aadharA.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                kin_aadharAMouseClicked(evt);
            }
        });
        kin_aadharA.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                kin_aadharAActionPerformed(evt);
            }
        });
        panelA.add(kin_aadharA, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 1580, 320, 40));

        kin_underlineA5.setForeground(new java.awt.Color(54, 33, 89));
        panelA.add(kin_underlineA5, new org.netbeans.lib.awtextra.AbsoluteConstraints(490, 1620, 320, 10));

        kin_mailA.setFont(new java.awt.Font("Roboto", 0, 24)); // NOI18N
        kin_mailA.setForeground(new java.awt.Color(102, 102, 102));
        kin_mailA.setText("E-Mail");
        kin_mailA.setBorder(null);
        kin_mailA.setMargin(new java.awt.Insets(2, 4, 2, 2));
        kin_mailA.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                kin_mailAFocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                kin_mailAFocusLost(evt);
            }
        });
        kin_mailA.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                kin_mailAMouseClicked(evt);
            }
        });
        panelA.add(kin_mailA, new org.netbeans.lib.awtextra.AbsoluteConstraints(490, 1580, 320, 40));

        kin_bank_nameA.setFont(new java.awt.Font("Roboto", 0, 24)); // NOI18N
        kin_bank_nameA.setForeground(new java.awt.Color(102, 102, 102));
        kin_bank_nameA.setText("Bank Name");
        kin_bank_nameA.setBorder(null);
        kin_bank_nameA.setMargin(new java.awt.Insets(2, 4, 2, 2));
        kin_bank_nameA.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                kin_bank_nameAFocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                kin_bank_nameAFocusLost(evt);
            }
        });
        kin_bank_nameA.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                kin_bank_nameAMouseClicked(evt);
            }
        });
        panelA.add(kin_bank_nameA, new org.netbeans.lib.awtextra.AbsoluteConstraints(490, 1640, 320, 40));

        kin_account_numberA.setFont(new java.awt.Font("Roboto", 0, 24)); // NOI18N
        kin_account_numberA.setForeground(new java.awt.Color(102, 102, 102));
        kin_account_numberA.setText("Account Number");
        kin_account_numberA.setBorder(null);
        kin_account_numberA.setMargin(new java.awt.Insets(2, 4, 2, 2));
        kin_account_numberA.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                kin_account_numberAFocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                kin_account_numberAFocusLost(evt);
            }
        });
        kin_account_numberA.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                kin_account_numberAMouseClicked(evt);
            }
        });
        kin_account_numberA.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                kin_account_numberAActionPerformed(evt);
            }
        });
        panelA.add(kin_account_numberA, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 1640, 320, 40));

        kin_underlineA6.setForeground(new java.awt.Color(54, 33, 89));
        panelA.add(kin_underlineA6, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 1680, 320, 10));

        kin_underlineA7.setForeground(new java.awt.Color(54, 33, 89));
        panelA.add(kin_underlineA7, new org.netbeans.lib.awtextra.AbsoluteConstraints(490, 1680, 320, 10));

        midA2.setForeground(new java.awt.Color(204, 204, 255));
        panelA.add(midA2, new org.netbeans.lib.awtextra.AbsoluteConstraints(250, 1360, 390, 10));

        kin_IFSC_A.setFont(new java.awt.Font("Roboto", 0, 24)); // NOI18N
        kin_IFSC_A.setForeground(new java.awt.Color(102, 102, 102));
        kin_IFSC_A.setText("IFSC");
        kin_IFSC_A.setBorder(null);
        kin_IFSC_A.setMargin(new java.awt.Insets(2, 4, 2, 2));
        kin_IFSC_A.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                kin_IFSC_AFocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                kin_IFSC_AFocusLost(evt);
            }
        });
        kin_IFSC_A.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                kin_IFSC_AMouseClicked(evt);
            }
        });
        panelA.add(kin_IFSC_A, new org.netbeans.lib.awtextra.AbsoluteConstraints(490, 1700, 320, 40));

        kin_branchA.setFont(new java.awt.Font("Roboto", 0, 24)); // NOI18N
        kin_branchA.setForeground(new java.awt.Color(102, 102, 102));
        kin_branchA.setText("Branch");
        kin_branchA.setBorder(null);
        kin_branchA.setMargin(new java.awt.Insets(2, 4, 2, 2));
        kin_branchA.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                kin_branchAFocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                kin_branchAFocusLost(evt);
            }
        });
        kin_branchA.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                kin_branchAMouseClicked(evt);
            }
        });
        kin_branchA.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                kin_branchAActionPerformed(evt);
            }
        });
        panelA.add(kin_branchA, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 1700, 320, 40));

        kin_underlineA8.setForeground(new java.awt.Color(54, 33, 89));
        panelA.add(kin_underlineA8, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 1740, 320, 10));

        kin_underlineA9.setForeground(new java.awt.Color(54, 33, 89));
        panelA.add(kin_underlineA9, new org.netbeans.lib.awtextra.AbsoluteConstraints(490, 1740, 320, 10));

        kin_underlineA0.setForeground(new java.awt.Color(54, 33, 89));
        panelA.add(kin_underlineA0, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 1500, 320, 10));

        kin_NOK_A.setFont(new java.awt.Font("Roboto", 0, 24)); // NOI18N
        kin_NOK_A.setForeground(new java.awt.Color(102, 102, 102));
        kin_NOK_A.setText("NOK");
        kin_NOK_A.setBorder(null);
        kin_NOK_A.setMargin(new java.awt.Insets(2, 4, 2, 2));
        kin_NOK_A.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                kin_NOK_AFocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                kin_NOK_AFocusLost(evt);
            }
        });
        kin_NOK_A.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                kin_NOK_AMouseEntered(evt);
            }
        });
        kin_NOK_A.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                kin_NOK_AActionPerformed(evt);
            }
        });
        panelA.add(kin_NOK_A, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 1460, 320, 40));

        kin_underlineA1.setForeground(new java.awt.Color(54, 33, 89));
        panelA.add(kin_underlineA1, new org.netbeans.lib.awtextra.AbsoluteConstraints(490, 1500, 320, 10));

        kin_mobile_numberA.setFont(new java.awt.Font("Roboto", 0, 24)); // NOI18N
        kin_mobile_numberA.setForeground(new java.awt.Color(102, 102, 102));
        kin_mobile_numberA.setText("Mob No.");
        kin_mobile_numberA.setBorder(null);
        kin_mobile_numberA.setMargin(new java.awt.Insets(2, 4, 2, 2));
        kin_mobile_numberA.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                kin_mobile_numberAFocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                kin_mobile_numberAFocusLost(evt);
            }
        });
        kin_mobile_numberA.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                kin_mobile_numberAMouseClicked(evt);
            }
        });
        kin_mobile_numberA.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                kin_mobile_numberAActionPerformed(evt);
            }
        });
        panelA.add(kin_mobile_numberA, new org.netbeans.lib.awtextra.AbsoluteConstraints(490, 1460, 320, 40));

        kin_underlineA2.setForeground(new java.awt.Color(54, 33, 89));
        panelA.add(kin_underlineA2, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 1560, 320, 10));

        kin_relation_A.setFont(new java.awt.Font("Roboto", 0, 24)); // NOI18N
        kin_relation_A.setForeground(new java.awt.Color(102, 102, 102));
        kin_relation_A.setText("Relation");
        kin_relation_A.setBorder(null);
        kin_relation_A.setMargin(new java.awt.Insets(2, 4, 2, 2));
        kin_relation_A.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                kin_relation_AFocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                kin_relation_AFocusLost(evt);
            }
        });
        kin_relation_A.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                kin_relation_AMouseClicked(evt);
            }
        });
        kin_relation_A.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                kin_relation_AActionPerformed(evt);
            }
        });
        panelA.add(kin_relation_A, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 1520, 320, 40));

        labelA.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        labelA.setText("Fetch from previous records");
        panelA.add(labelA, new org.netbeans.lib.awtextra.AbsoluteConstraints(460, 90, -1, 40));

        okayA.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/PNGS/check-mark.png"))); // NOI18N
        okayA.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                okayAMouseClicked(evt);
            }
        });
        panelA.add(okayA, new org.netbeans.lib.awtextra.AbsoluteConstraints(410, 90, -1, 40));

        ScrollPaneA.setViewportView(panelA);

        bg.add(ScrollPaneA, new org.netbeans.lib.awtextra.AbsoluteConstraints(340, 192, 910, 480));

        ScrollPaneB.setBorder(null);
        ScrollPaneB.setHorizontalScrollBarPolicy(javax.swing.ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);

        panelB.setBackground(new java.awt.Color(255, 255, 255));
        panelB.setFocusable(false);
        panelB.setMaximumSize(new java.awt.Dimension(950, 1310));
        panelB.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        panelB_bottom_line.setForeground(new java.awt.Color(54, 33, 89));
        panelB.add(panelB_bottom_line, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 2120, 910, -1));

        purpose_underlineB.setForeground(new java.awt.Color(54, 33, 89));
        panelB.add(purpose_underlineB, new org.netbeans.lib.awtextra.AbsoluteConstraints(490, 790, 320, 20));

        purpose_textfieldB.setFont(new java.awt.Font("Roboto", 0, 24)); // NOI18N
        purpose_textfieldB.setForeground(new java.awt.Color(54, 33, 89));
        purpose_textfieldB.setBorder(null);
        purpose_textfieldB.setMargin(new java.awt.Insets(2, 4, 2, 2));
        purpose_textfieldB.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                purpose_textfieldBActionPerformed(evt);
            }
        });
        panelB.add(purpose_textfieldB, new org.netbeans.lib.awtextra.AbsoluteConstraints(490, 750, 320, 40));

        purpose_labelB.setFont(new java.awt.Font("Montserrat", 0, 28)); // NOI18N
        purpose_labelB.setForeground(new java.awt.Color(51, 51, 51));
        purpose_labelB.setText("Purpose");
        panelB.add(purpose_labelB, new org.netbeans.lib.awtextra.AbsoluteConstraints(490, 710, -1, 40));

        DOA_labelB.setBackground(new java.awt.Color(255, 255, 255));
        DOA_labelB.setFont(new java.awt.Font("Montserrat", 0, 28)); // NOI18N
        DOA_labelB.setForeground(new java.awt.Color(51, 51, 51));
        DOA_labelB.setText("Date of Attachment");
        panelB.add(DOA_labelB, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 380, -1, -1));

        DOA_date_comboboxB.setFont(new java.awt.Font("Roboto", 0, 18)); // NOI18N
        DOA_date_comboboxB.setForeground(new java.awt.Color(44, 62, 80));
        DOA_date_comboboxB.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "DD", "01", "02", "03", "04", "05", "06", "07", "08", "09", "10", "11", "12", "13", "14", "15", "16", "17", "18", "19", "20", "21", "22", "23", "24", "25", "26", "27", "28", "29", "30", "31" }));
        DOA_date_comboboxB.setBorder(null);
        panelB.add(DOA_date_comboboxB, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 420, 100, 40));

        DOA_month_comboboxB.setFont(new java.awt.Font("Roboto", 0, 18)); // NOI18N
        DOA_month_comboboxB.setForeground(new java.awt.Color(44, 62, 80));
        DOA_month_comboboxB.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "MM", "01", "02", "03", "04", "05", "06", "07", "08", "09", "10", "11", "12" }));
        DOA_month_comboboxB.setBorder(null);
        panelB.add(DOA_month_comboboxB, new org.netbeans.lib.awtextra.AbsoluteConstraints(190, 420, 100, 40));

        DOA_year_comboboxB.setFont(new java.awt.Font("Roboto", 0, 18)); // NOI18N
        DOA_year_comboboxB.setForeground(new java.awt.Color(44, 62, 80));
        DOA_year_comboboxB.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "YYYY", "1950", "1951", "1952", "1953", "1954", "1955", "1956", "1957", "1958", "1959", "1960", "1961", "1962", "1963", "1964", "1965", "1966", "1967", "1968", "1969", "1970", "1971", "1972", "1973", "1974", "1975", "1976", "1977", "1978", "1979", "1980", "1981", "1982", "1983", "1984", "1985", "1986", "1987", "1988", "1989", "1990", "1991", "1992", "1993", "1994", "1995", "1996", "1997", "1998", "1999", "2000", "2001", "2002", "2003", "2004", "2005", "2006", "2007", "2008", "2009", "2010", "2011", "2012", "2013", "2014", "2015", "2016", "2017", "2018", "2019", "2020", "2021", "2022", "2023", "2024", "2025", "2026", "2027", "2028", "2029", "2030", "2031", "2032", "2033", "2034", "2035", "2036", "2037", "2038", "2039", "2040", "2041", "2042", "2043", "2044", "2045", "2046", "2047", "2048", "2049", "2050", "2051", "2052", "2053", "2054", "2055", "2056", "2057", "2058", "2059", "2060", "2061", "2062", "2063", "2064", "2065", "2066", "2067", "2068", "2069", "2070", "2071", "2072", "2073", "2074", "2075", "2076", "2077", "2078", "2079", "2080", "2081", "2082", "2083", "2084", "2085", "2086", "2087", "2088", "2089", "2090", "2091", "2092", "2093", "2094", "2095", "2096", "2097", "2098", "2099", "2100", " " }));
        DOA_year_comboboxB.setBorder(null);
        panelB.add(DOA_year_comboboxB, new org.netbeans.lib.awtextra.AbsoluteConstraints(300, 420, 100, 40));

        upload_photo_labelB.setBackground(new java.awt.Color(255, 255, 255));
        upload_photo_labelB.setFont(new java.awt.Font("Montserrat", 0, 28)); // NOI18N
        upload_photo_labelB.setForeground(new java.awt.Color(51, 51, 51));
        upload_photo_labelB.setText("Upload Photo");
        panelB.add(upload_photo_labelB, new org.netbeans.lib.awtextra.AbsoluteConstraints(490, 490, -1, -1));

        rank_comboboxB.setFont(new java.awt.Font("Roboto", 0, 18)); // NOI18N
        rank_comboboxB.setForeground(new java.awt.Color(44, 62, 80));
        rank_comboboxB.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Sepoy", "Naik", "Havildar", "Naib Subedar", "Subedar", "Subedar Major", "Lieutenant", "Captain", "Major", "Lt Colonel", "Colonel", "Brigadier", "Major General", "Lt General", "General" }));
        rank_comboboxB.setBorder(null);
        panelB.add(rank_comboboxB, new org.netbeans.lib.awtextra.AbsoluteConstraints(490, 200, 320, 40));

        name_underlineB.setForeground(new java.awt.Color(54, 33, 89));
        panelB.add(name_underlineB, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 240, 320, 20));

        name_textfieldB.setFont(new java.awt.Font("Roboto", 0, 24)); // NOI18N
        name_textfieldB.setForeground(new java.awt.Color(54, 33, 89));
        name_textfieldB.setBorder(null);
        name_textfieldB.setMargin(new java.awt.Insets(2, 4, 2, 2));
        name_textfieldB.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                name_textfieldBActionPerformed(evt);
            }
        });
        panelB.add(name_textfieldB, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 200, 320, 40));

        name_labelB.setFont(new java.awt.Font("Montserrat", 0, 28)); // NOI18N
        name_labelB.setForeground(new java.awt.Color(51, 51, 51));
        name_labelB.setText("Name");
        panelB.add(name_labelB, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 160, -1, 40));

        medical_category_labelB.setBackground(new java.awt.Color(255, 255, 255));
        medical_category_labelB.setFont(new java.awt.Font("Montserrat", 0, 28)); // NOI18N
        medical_category_labelB.setForeground(new java.awt.Color(51, 51, 51));
        medical_category_labelB.setText("Medical Category");
        panelB.add(medical_category_labelB, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 600, -1, 40));

        birth_labelB.setBackground(new java.awt.Color(255, 255, 255));
        birth_labelB.setFont(new java.awt.Font("Montserrat", 0, 28)); // NOI18N
        birth_labelB.setForeground(new java.awt.Color(51, 51, 51));
        birth_labelB.setText("Date of Birth");
        panelB.add(birth_labelB, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 270, -1, -1));

        birth_year_comboboxB.setFont(new java.awt.Font("Roboto", 0, 18)); // NOI18N
        birth_year_comboboxB.setForeground(new java.awt.Color(44, 62, 80));
        birth_year_comboboxB.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "YYYY", "1950", "1951", "1952", "1953", "1954", "1955", "1956", "1957", "1958", "1959", "1960", "1961", "1962", "1963", "1964", "1965", "1966", "1967", "1968", "1969", "1970", "1971", "1972", "1973", "1974", "1975", "1976", "1977", "1978", "1979", "1980", "1981", "1982", "1983", "1984", "1985", "1986", "1987", "1988", "1989", "1990", "1991", "1992", "1993", "1994", "1995", "1996", "1997", "1998", "1999", "2000", "2001", "2002", "2003", "2004", "2005", "2006", "2007", "2008", "2009", "2010", "2011", "2012", "2013", "2014", "2015", "2016", "2017", "2018", "2019", "2020", "2021", "2022", "2023", "2024", "2025", "2026", "2027", "2028", "2029", "2030", "2031", "2032", "2033", "2034", "2035", "2036", "2037", "2038", "2039", "2040", "2041", "2042", "2043", "2044", "2045", "2046", "2047", "2048", "2049", "2050", "2051", "2052", "2053", "2054", "2055", "2056", "2057", "2058", "2059", "2060", "2061", "2062", "2063", "2064", "2065", "2066", "2067", "2068", "2069", "2070", "2071", "2072", "2073", "2074", "2075", "2076", "2077", "2078", "2079", "2080", "2081", "2082", "2083", "2084", "2085", "2086", "2087", "2088", "2089", "2090", "2091", "2092", "2093", "2094", "2095", "2096", "2097", "2098", "2099", "2100", " " }));
        birth_year_comboboxB.setBorder(null);
        panelB.add(birth_year_comboboxB, new org.netbeans.lib.awtextra.AbsoluteConstraints(300, 310, 100, 40));

        birth_date_comboboxB.setFont(new java.awt.Font("Roboto", 0, 18)); // NOI18N
        birth_date_comboboxB.setForeground(new java.awt.Color(44, 62, 80));
        birth_date_comboboxB.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "DD", "01", "02", "03", "04", "05", "06", "07", "08", "09", "10", "11", "12", "13", "14", "15", "16", "17", "18", "19", "20", "21", "22", "23", "24", "25", "26", "27", "28", "29", "30", "31" }));
        birth_date_comboboxB.setBorder(null);
        panelB.add(birth_date_comboboxB, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 310, 100, 40));

        birth_month_comboboxB.setFont(new java.awt.Font("Roboto", 0, 18)); // NOI18N
        birth_month_comboboxB.setForeground(new java.awt.Color(44, 62, 80));
        birth_month_comboboxB.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "MM", "01", "02", "03", "04", "05", "06", "07", "08", "09", "10", "11", "12" }));
        birth_month_comboboxB.setBorder(null);
        panelB.add(birth_month_comboboxB, new org.netbeans.lib.awtextra.AbsoluteConstraints(190, 310, 100, 40));

        courses_done_labelB.setFont(new java.awt.Font("Montserrat", 0, 28)); // NOI18N
        courses_done_labelB.setForeground(new java.awt.Color(51, 51, 51));
        courses_done_labelB.setText("Courses Done");
        panelB.add(courses_done_labelB, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 710, -1, 40));

        courses_underlineB.setForeground(new java.awt.Color(54, 33, 89));
        panelB.add(courses_underlineB, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 790, 320, 20));

        courses_textfieldB.setFont(new java.awt.Font("Roboto", 0, 24)); // NOI18N
        courses_textfieldB.setForeground(new java.awt.Color(54, 33, 89));
        courses_textfieldB.setBorder(null);
        courses_textfieldB.setMargin(new java.awt.Insets(2, 4, 2, 2));
        panelB.add(courses_textfieldB, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 750, 320, 40));

        army_number_underlineB.setForeground(new java.awt.Color(54, 33, 89));
        panelB.add(army_number_underlineB, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 130, 320, 20));

        army_number_textfieldB.setFont(new java.awt.Font("Roboto", 0, 24)); // NOI18N
        army_number_textfieldB.setForeground(new java.awt.Color(54, 33, 89));
        army_number_textfieldB.setBorder(null);
        army_number_textfieldB.setMargin(new java.awt.Insets(2, 4, 2, 2));
        army_number_textfieldB.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                army_number_textfieldBActionPerformed(evt);
            }
        });
        army_number_textfieldB.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                army_number_textfieldBKeyPressed(evt);
            }
        });
        panelB.add(army_number_textfieldB, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 90, 320, 40));

        army_number_labelB.setFont(new java.awt.Font("Montserrat", 0, 28)); // NOI18N
        army_number_labelB.setForeground(new java.awt.Color(51, 51, 51));
        army_number_labelB.setText("Army Number");
        panelB.add(army_number_labelB, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 50, -1, 40));

        company_underlineB.setForeground(new java.awt.Color(54, 33, 89));
        panelB.add(company_underlineB, new org.netbeans.lib.awtextra.AbsoluteConstraints(490, 350, 320, 20));

        company_textfieldB.setFont(new java.awt.Font("Roboto", 0, 24)); // NOI18N
        company_textfieldB.setForeground(new java.awt.Color(54, 33, 89));
        company_textfieldB.setBorder(null);
        company_textfieldB.setMargin(new java.awt.Insets(2, 4, 2, 2));
        panelB.add(company_textfieldB, new org.netbeans.lib.awtextra.AbsoluteConstraints(490, 310, 320, 40));

        company_labelB.setFont(new java.awt.Font("Montserrat", 0, 28)); // NOI18N
        company_labelB.setForeground(new java.awt.Color(51, 51, 51));
        company_labelB.setText("Company");
        panelB.add(company_labelB, new org.netbeans.lib.awtextra.AbsoluteConstraints(490, 270, -1, 40));

        location_underlineB.setForeground(new java.awt.Color(54, 33, 89));
        panelB.add(location_underlineB, new org.netbeans.lib.awtextra.AbsoluteConstraints(490, 680, 320, 20));

        location_textfieldB.setFont(new java.awt.Font("Roboto", 0, 24)); // NOI18N
        location_textfieldB.setForeground(new java.awt.Color(54, 33, 89));
        location_textfieldB.setBorder(null);
        location_textfieldB.setMargin(new java.awt.Insets(2, 4, 2, 2));
        panelB.add(location_textfieldB, new org.netbeans.lib.awtextra.AbsoluteConstraints(490, 640, 320, 40));

        location_labelB.setFont(new java.awt.Font("Montserrat", 0, 28)); // NOI18N
        location_labelB.setForeground(new java.awt.Color(51, 51, 51));
        location_labelB.setText("Location");
        panelB.add(location_labelB, new org.netbeans.lib.awtextra.AbsoluteConstraints(490, 600, -1, 40));

        indl_mob_labelB.setFont(new java.awt.Font("Montserrat", 0, 28)); // NOI18N
        indl_mob_labelB.setForeground(new java.awt.Color(51, 51, 51));
        indl_mob_labelB.setText("Indl Mob No.");
        panelB.add(indl_mob_labelB, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 490, -1, 40));

        label_91B.setFont(new java.awt.Font("Montserrat", 0, 28)); // NOI18N
        label_91B.setForeground(new java.awt.Color(51, 51, 51));
        label_91B.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        label_91B.setText("+91");
        panelB.add(label_91B, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 530, 50, 40));

        indl_mob_underlineB.setForeground(new java.awt.Color(54, 33, 89));
        panelB.add(indl_mob_underlineB, new org.netbeans.lib.awtextra.AbsoluteConstraints(140, 570, 260, 20));

        indl_mob_textfieldB.setFont(new java.awt.Font("Roboto", 0, 24)); // NOI18N
        indl_mob_textfieldB.setForeground(new java.awt.Color(54, 33, 89));
        indl_mob_textfieldB.setBorder(null);
        indl_mob_textfieldB.setMargin(new java.awt.Insets(2, 4, 2, 2));
        panelB.add(indl_mob_textfieldB, new org.netbeans.lib.awtextra.AbsoluteConstraints(140, 530, 260, 40));

        DOJ_labelB.setBackground(new java.awt.Color(255, 255, 255));
        DOJ_labelB.setFont(new java.awt.Font("Montserrat", 0, 28)); // NOI18N
        DOJ_labelB.setForeground(new java.awt.Color(51, 51, 51));
        DOJ_labelB.setText("Date of Joining");
        panelB.add(DOJ_labelB, new org.netbeans.lib.awtextra.AbsoluteConstraints(490, 380, -1, -1));

        DOJ_date_comboboxB.setFont(new java.awt.Font("Roboto", 0, 18)); // NOI18N
        DOJ_date_comboboxB.setForeground(new java.awt.Color(44, 62, 80));
        DOJ_date_comboboxB.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "DD", "01", "02", "03", "04", "05", "06", "07", "08", "09", "10", "11", "12", "13", "14", "15", "16", "17", "18", "19", "20", "21", "22", "23", "24", "25", "26", "27", "28", "29", "30", "31" }));
        DOJ_date_comboboxB.setBorder(null);
        DOJ_date_comboboxB.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                DOJ_date_comboboxBActionPerformed(evt);
            }
        });
        panelB.add(DOJ_date_comboboxB, new org.netbeans.lib.awtextra.AbsoluteConstraints(490, 420, 100, 40));

        DOJ_month_comboboxB.setFont(new java.awt.Font("Roboto", 0, 18)); // NOI18N
        DOJ_month_comboboxB.setForeground(new java.awt.Color(44, 62, 80));
        DOJ_month_comboboxB.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "MM", "01", "02", "03", "04", "05", "06", "07", "08", "09", "10", "11", "12" }));
        DOJ_month_comboboxB.setBorder(null);
        panelB.add(DOJ_month_comboboxB, new org.netbeans.lib.awtextra.AbsoluteConstraints(600, 420, 100, 40));

        DOJ_year_comboboxB.setFont(new java.awt.Font("Roboto", 0, 18)); // NOI18N
        DOJ_year_comboboxB.setForeground(new java.awt.Color(44, 62, 80));
        DOJ_year_comboboxB.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "YYYY", "1950", "1951", "1952", "1953", "1954", "1955", "1956", "1957", "1958", "1959", "1960", "1961", "1962", "1963", "1964", "1965", "1966", "1967", "1968", "1969", "1970", "1971", "1972", "1973", "1974", "1975", "1976", "1977", "1978", "1979", "1980", "1981", "1982", "1983", "1984", "1985", "1986", "1987", "1988", "1989", "1990", "1991", "1992", "1993", "1994", "1995", "1996", "1997", "1998", "1999", "2000", "2001", "2002", "2003", "2004", "2005", "2006", "2007", "2008", "2009", "2010", "2011", "2012", "2013", "2014", "2015", "2016", "2017", "2018", "2019", "2020", "2021", "2022", "2023", "2024", "2025", "2026", "2027", "2028", "2029", "2030", "2031", "2032", "2033", "2034", "2035", "2036", "2037", "2038", "2039", "2040", "2041", "2042", "2043", "2044", "2045", "2046", "2047", "2048", "2049", "2050", "2051", "2052", "2053", "2054", "2055", "2056", "2057", "2058", "2059", "2060", "2061", "2062", "2063", "2064", "2065", "2066", "2067", "2068", "2069", "2070", "2071", "2072", "2073", "2074", "2075", "2076", "2077", "2078", "2079", "2080", "2081", "2082", "2083", "2084", "2085", "2086", "2087", "2088", "2089", "2090", "2091", "2092", "2093", "2094", "2095", "2096", "2097", "2098", "2099", "2100", " " }));
        DOJ_year_comboboxB.setBorder(null);
        panelB.add(DOJ_year_comboboxB, new org.netbeans.lib.awtextra.AbsoluteConstraints(710, 420, 100, 40));

        medical_category_underlineB.setForeground(new java.awt.Color(54, 33, 89));
        panelB.add(medical_category_underlineB, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 680, 320, 20));

        medical_category_textfieldB.setFont(new java.awt.Font("Roboto", 0, 24)); // NOI18N
        medical_category_textfieldB.setForeground(new java.awt.Color(54, 33, 89));
        medical_category_textfieldB.setBorder(null);
        medical_category_textfieldB.setMargin(new java.awt.Insets(2, 4, 2, 2));
        panelB.add(medical_category_textfieldB, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 640, 320, 40));

        rank_labelB.setBackground(new java.awt.Color(255, 255, 255));
        rank_labelB.setFont(new java.awt.Font("Montserrat", 0, 28)); // NOI18N
        rank_labelB.setForeground(new java.awt.Color(51, 51, 51));
        rank_labelB.setText("Rank");
        panelB.add(rank_labelB, new org.netbeans.lib.awtextra.AbsoluteConstraints(490, 160, -1, -1));

        select_file_buttonB.setMaximumSize(new java.awt.Dimension(200, 200));
        select_file_buttonB.setMinimumSize(new java.awt.Dimension(200, 200));
        select_file_buttonB.setPreferredSize(new java.awt.Dimension(200, 200));
        select_file_buttonB.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                select_file_buttonBMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                select_file_buttonBMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                select_file_buttonBMouseExited(evt);
            }
        });
        select_file_buttonB.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        select_file_labelB.setBackground(new java.awt.Color(54, 33, 89));
        select_file_labelB.setFont(new java.awt.Font("Roboto", 0, 18)); // NOI18N
        select_file_labelB.setForeground(new java.awt.Color(54, 33, 89));
        select_file_labelB.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        select_file_labelB.setText("Select File");
        select_file_buttonB.add(select_file_labelB, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 0, 260, 40));

        panelB.add(select_file_buttonB, new org.netbeans.lib.awtextra.AbsoluteConstraints(490, 530, 320, 40));

        indl_ID_details_labelB.setFont(new java.awt.Font("Montserrat", 0, 28)); // NOI18N
        indl_ID_details_labelB.setForeground(new java.awt.Color(51, 51, 51));
        indl_ID_details_labelB.setText("Indl ID Details");
        panelB.add(indl_ID_details_labelB, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 910, -1, 40));

        indl_underlineB0.setForeground(new java.awt.Color(54, 33, 89));
        panelB.add(indl_underlineB0, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 1000, 320, 10));

        indl_Icard_numberB.setFont(new java.awt.Font("Roboto", 0, 24)); // NOI18N
        indl_Icard_numberB.setForeground(new java.awt.Color(102, 102, 102));
        indl_Icard_numberB.setText("ICardNumber");
        indl_Icard_numberB.setBorder(null);
        indl_Icard_numberB.setMargin(new java.awt.Insets(2, 4, 2, 2));
        indl_Icard_numberB.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                indl_Icard_numberBFocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                indl_Icard_numberBFocusLost(evt);
            }
        });
        indl_Icard_numberB.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                indl_Icard_numberBMouseClicked(evt);
            }
        });
        panelB.add(indl_Icard_numberB, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 960, 320, 40));

        indl_underlineB.setForeground(new java.awt.Color(54, 33, 89));
        panelB.add(indl_underlineB, new org.netbeans.lib.awtextra.AbsoluteConstraints(490, 1000, 320, 10));

        indl_PAN_B.setFont(new java.awt.Font("Roboto", 0, 24)); // NOI18N
        indl_PAN_B.setForeground(new java.awt.Color(102, 102, 102));
        indl_PAN_B.setText("PAN");
        indl_PAN_B.setBorder(null);
        indl_PAN_B.setMargin(new java.awt.Insets(2, 4, 2, 2));
        indl_PAN_B.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                indl_PAN_BFocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                indl_PAN_BFocusLost(evt);
            }
        });
        indl_PAN_B.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                indl_PAN_BMouseClicked(evt);
            }
        });
        indl_PAN_B.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                indl_PAN_BActionPerformed(evt);
            }
        });
        panelB.add(indl_PAN_B, new org.netbeans.lib.awtextra.AbsoluteConstraints(490, 960, 320, 40));

        indl_underlineB2.setForeground(new java.awt.Color(54, 33, 89));
        panelB.add(indl_underlineB2, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 1060, 320, 10));

        indl_aadharB.setFont(new java.awt.Font("Roboto", 0, 24)); // NOI18N
        indl_aadharB.setForeground(new java.awt.Color(102, 102, 102));
        indl_aadharB.setText("Aadhar");
        indl_aadharB.setBorder(null);
        indl_aadharB.setMargin(new java.awt.Insets(2, 4, 2, 2));
        indl_aadharB.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                indl_aadharBFocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                indl_aadharBFocusLost(evt);
            }
        });
        indl_aadharB.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                indl_aadharBMouseClicked(evt);
            }
        });
        indl_aadharB.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                indl_aadharBActionPerformed(evt);
            }
        });
        panelB.add(indl_aadharB, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 1020, 320, 40));

        indl_underlineB3.setForeground(new java.awt.Color(54, 33, 89));
        panelB.add(indl_underlineB3, new org.netbeans.lib.awtextra.AbsoluteConstraints(490, 1060, 320, 10));

        indl_mailB.setFont(new java.awt.Font("Roboto", 0, 24)); // NOI18N
        indl_mailB.setForeground(new java.awt.Color(102, 102, 102));
        indl_mailB.setText("E-Mail");
        indl_mailB.setBorder(null);
        indl_mailB.setMargin(new java.awt.Insets(2, 4, 2, 2));
        indl_mailB.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                indl_mailBFocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                indl_mailBFocusLost(evt);
            }
        });
        indl_mailB.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                indl_mailBMouseClicked(evt);
            }
        });
        panelB.add(indl_mailB, new org.netbeans.lib.awtextra.AbsoluteConstraints(490, 1020, 320, 40));

        indl_bank_nameB.setFont(new java.awt.Font("Roboto", 0, 24)); // NOI18N
        indl_bank_nameB.setForeground(new java.awt.Color(102, 102, 102));
        indl_bank_nameB.setText("Bank Name");
        indl_bank_nameB.setBorder(null);
        indl_bank_nameB.setMargin(new java.awt.Insets(2, 4, 2, 2));
        indl_bank_nameB.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                indl_bank_nameBFocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                indl_bank_nameBFocusLost(evt);
            }
        });
        indl_bank_nameB.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                indl_bank_nameBMouseClicked(evt);
            }
        });
        panelB.add(indl_bank_nameB, new org.netbeans.lib.awtextra.AbsoluteConstraints(490, 1080, 320, 40));

        indl_account_numberB.setFont(new java.awt.Font("Roboto", 0, 24)); // NOI18N
        indl_account_numberB.setForeground(new java.awt.Color(102, 102, 102));
        indl_account_numberB.setText("Account Number");
        indl_account_numberB.setBorder(null);
        indl_account_numberB.setMargin(new java.awt.Insets(2, 4, 2, 2));
        indl_account_numberB.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                indl_account_numberBFocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                indl_account_numberBFocusLost(evt);
            }
        });
        indl_account_numberB.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                indl_account_numberBMouseClicked(evt);
            }
        });
        indl_account_numberB.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                indl_account_numberBActionPerformed(evt);
            }
        });
        panelB.add(indl_account_numberB, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 1080, 320, 40));

        indl_underlineB4.setForeground(new java.awt.Color(54, 33, 89));
        panelB.add(indl_underlineB4, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 1120, 320, 10));

        indl_underlineB5.setForeground(new java.awt.Color(54, 33, 89));
        panelB.add(indl_underlineB5, new org.netbeans.lib.awtextra.AbsoluteConstraints(490, 1120, 320, 10));

        midB1.setForeground(new java.awt.Color(204, 204, 255));
        panelB.add(midB1, new org.netbeans.lib.awtextra.AbsoluteConstraints(250, 860, 390, 10));

        midB3.setForeground(new java.awt.Color(204, 204, 255));
        panelB.add(midB3, new org.netbeans.lib.awtextra.AbsoluteConstraints(250, 1700, 390, 10));

        indl_IFSC_B.setFont(new java.awt.Font("Roboto", 0, 24)); // NOI18N
        indl_IFSC_B.setForeground(new java.awt.Color(102, 102, 102));
        indl_IFSC_B.setText("IFSC");
        indl_IFSC_B.setBorder(null);
        indl_IFSC_B.setMargin(new java.awt.Insets(2, 4, 2, 2));
        indl_IFSC_B.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                indl_IFSC_BFocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                indl_IFSC_BFocusLost(evt);
            }
        });
        indl_IFSC_B.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                indl_IFSC_BMouseClicked(evt);
            }
        });
        panelB.add(indl_IFSC_B, new org.netbeans.lib.awtextra.AbsoluteConstraints(490, 1140, 320, 40));

        indl_branchB.setFont(new java.awt.Font("Roboto", 0, 24)); // NOI18N
        indl_branchB.setForeground(new java.awt.Color(102, 102, 102));
        indl_branchB.setText("Branch");
        indl_branchB.setBorder(null);
        indl_branchB.setMargin(new java.awt.Insets(2, 4, 2, 2));
        indl_branchB.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                indl_branchBFocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                indl_branchBFocusLost(evt);
            }
        });
        indl_branchB.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                indl_branchBMouseClicked(evt);
            }
        });
        indl_branchB.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                indl_branchBActionPerformed(evt);
            }
        });
        panelB.add(indl_branchB, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 1140, 320, 40));

        indl_underlineB6.setForeground(new java.awt.Color(54, 33, 89));
        panelB.add(indl_underlineB6, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 1180, 320, 10));

        indl_underlineB7.setForeground(new java.awt.Color(54, 33, 89));
        panelB.add(indl_underlineB7, new org.netbeans.lib.awtextra.AbsoluteConstraints(490, 1180, 320, 10));

        NOK_detailsB.setFont(new java.awt.Font("Montserrat", 0, 28)); // NOI18N
        NOK_detailsB.setForeground(new java.awt.Color(51, 51, 51));
        NOK_detailsB.setText("Details of Kin");
        panelB.add(NOK_detailsB, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 1300, -1, 40));

        kin_underlineB3.setForeground(new java.awt.Color(54, 33, 89));
        panelB.add(kin_underlineB3, new org.netbeans.lib.awtextra.AbsoluteConstraints(490, 1450, 320, 10));

        kin_PAN_B.setFont(new java.awt.Font("Roboto", 0, 24)); // NOI18N
        kin_PAN_B.setForeground(new java.awt.Color(102, 102, 102));
        kin_PAN_B.setText("PAN");
        kin_PAN_B.setBorder(null);
        kin_PAN_B.setMargin(new java.awt.Insets(2, 4, 2, 2));
        kin_PAN_B.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                kin_PAN_BFocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                kin_PAN_BFocusLost(evt);
            }
        });
        kin_PAN_B.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                kin_PAN_BMouseClicked(evt);
            }
        });
        kin_PAN_B.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                kin_PAN_BActionPerformed(evt);
            }
        });
        panelB.add(kin_PAN_B, new org.netbeans.lib.awtextra.AbsoluteConstraints(490, 1410, 320, 40));

        kin_underlineB4.setForeground(new java.awt.Color(54, 33, 89));
        panelB.add(kin_underlineB4, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 1510, 320, 10));

        kin_aadharB.setFont(new java.awt.Font("Roboto", 0, 24)); // NOI18N
        kin_aadharB.setForeground(new java.awt.Color(102, 102, 102));
        kin_aadharB.setText("Aadhar");
        kin_aadharB.setBorder(null);
        kin_aadharB.setMargin(new java.awt.Insets(2, 4, 2, 2));
        kin_aadharB.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                kin_aadharBFocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                kin_aadharBFocusLost(evt);
            }
        });
        kin_aadharB.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                kin_aadharBMouseClicked(evt);
            }
        });
        kin_aadharB.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                kin_aadharBActionPerformed(evt);
            }
        });
        panelB.add(kin_aadharB, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 1470, 320, 40));

        kin_underlineB5.setForeground(new java.awt.Color(54, 33, 89));
        panelB.add(kin_underlineB5, new org.netbeans.lib.awtextra.AbsoluteConstraints(490, 1510, 320, 10));

        kin_mailB.setFont(new java.awt.Font("Roboto", 0, 24)); // NOI18N
        kin_mailB.setForeground(new java.awt.Color(102, 102, 102));
        kin_mailB.setText("E-Mail");
        kin_mailB.setBorder(null);
        kin_mailB.setMargin(new java.awt.Insets(2, 4, 2, 2));
        kin_mailB.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                kin_mailBFocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                kin_mailBFocusLost(evt);
            }
        });
        kin_mailB.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                kin_mailBMouseClicked(evt);
            }
        });
        panelB.add(kin_mailB, new org.netbeans.lib.awtextra.AbsoluteConstraints(490, 1470, 320, 40));

        kin_bank_nameB.setFont(new java.awt.Font("Roboto", 0, 24)); // NOI18N
        kin_bank_nameB.setForeground(new java.awt.Color(102, 102, 102));
        kin_bank_nameB.setText("Bank Name");
        kin_bank_nameB.setBorder(null);
        kin_bank_nameB.setMargin(new java.awt.Insets(2, 4, 2, 2));
        kin_bank_nameB.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                kin_bank_nameBFocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                kin_bank_nameBFocusLost(evt);
            }
        });
        kin_bank_nameB.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                kin_bank_nameBMouseClicked(evt);
            }
        });
        panelB.add(kin_bank_nameB, new org.netbeans.lib.awtextra.AbsoluteConstraints(490, 1530, 320, 40));

        kin_account_numberB.setFont(new java.awt.Font("Roboto", 0, 24)); // NOI18N
        kin_account_numberB.setForeground(new java.awt.Color(102, 102, 102));
        kin_account_numberB.setText("Account Number");
        kin_account_numberB.setBorder(null);
        kin_account_numberB.setMargin(new java.awt.Insets(2, 4, 2, 2));
        kin_account_numberB.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                kin_account_numberBFocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                kin_account_numberBFocusLost(evt);
            }
        });
        kin_account_numberB.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                kin_account_numberBMouseClicked(evt);
            }
        });
        kin_account_numberB.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                kin_account_numberBActionPerformed(evt);
            }
        });
        panelB.add(kin_account_numberB, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 1530, 320, 40));

        kin_underlineB6.setForeground(new java.awt.Color(54, 33, 89));
        panelB.add(kin_underlineB6, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 1570, 320, 10));

        kin_underlineB7.setForeground(new java.awt.Color(54, 33, 89));
        panelB.add(kin_underlineB7, new org.netbeans.lib.awtextra.AbsoluteConstraints(490, 1570, 320, 10));

        midA6.setForeground(new java.awt.Color(204, 204, 255));
        panelB.add(midA6, new org.netbeans.lib.awtextra.AbsoluteConstraints(250, 1250, 390, 10));

        kin_IFSC_B.setFont(new java.awt.Font("Roboto", 0, 24)); // NOI18N
        kin_IFSC_B.setForeground(new java.awt.Color(102, 102, 102));
        kin_IFSC_B.setText("IFSC");
        kin_IFSC_B.setBorder(null);
        kin_IFSC_B.setMargin(new java.awt.Insets(2, 4, 2, 2));
        kin_IFSC_B.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                kin_IFSC_BFocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                kin_IFSC_BFocusLost(evt);
            }
        });
        kin_IFSC_B.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                kin_IFSC_BMouseClicked(evt);
            }
        });
        panelB.add(kin_IFSC_B, new org.netbeans.lib.awtextra.AbsoluteConstraints(490, 1590, 320, 40));

        kin_branchB.setFont(new java.awt.Font("Roboto", 0, 24)); // NOI18N
        kin_branchB.setForeground(new java.awt.Color(102, 102, 102));
        kin_branchB.setText("Branch");
        kin_branchB.setBorder(null);
        kin_branchB.setMargin(new java.awt.Insets(2, 4, 2, 2));
        kin_branchB.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                kin_branchBFocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                kin_branchBFocusLost(evt);
            }
        });
        kin_branchB.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                kin_branchBMouseClicked(evt);
            }
        });
        kin_branchB.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                kin_branchBActionPerformed(evt);
            }
        });
        panelB.add(kin_branchB, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 1590, 320, 40));

        kin_underlineB8.setForeground(new java.awt.Color(54, 33, 89));
        panelB.add(kin_underlineB8, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 1630, 320, 10));

        kin_underlineB9.setForeground(new java.awt.Color(54, 33, 89));
        panelB.add(kin_underlineB9, new org.netbeans.lib.awtextra.AbsoluteConstraints(490, 1630, 320, 10));

        kin_underlineB0.setForeground(new java.awt.Color(54, 33, 89));
        panelB.add(kin_underlineB0, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 1390, 320, 10));

        kin_NOK_B.setFont(new java.awt.Font("Roboto", 0, 24)); // NOI18N
        kin_NOK_B.setForeground(new java.awt.Color(102, 102, 102));
        kin_NOK_B.setText("NOK");
        kin_NOK_B.setBorder(null);
        kin_NOK_B.setMargin(new java.awt.Insets(2, 4, 2, 2));
        kin_NOK_B.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                kin_NOK_BFocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                kin_NOK_BFocusLost(evt);
            }
        });
        kin_NOK_B.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                kin_NOK_BMouseClicked(evt);
            }
        });
        kin_NOK_B.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                kin_NOK_BActionPerformed(evt);
            }
        });
        panelB.add(kin_NOK_B, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 1350, 320, 40));

        kin_underlineB1.setForeground(new java.awt.Color(54, 33, 89));
        panelB.add(kin_underlineB1, new org.netbeans.lib.awtextra.AbsoluteConstraints(490, 1390, 320, 10));

        kin_mobile_numberB.setFont(new java.awt.Font("Roboto", 0, 24)); // NOI18N
        kin_mobile_numberB.setForeground(new java.awt.Color(102, 102, 102));
        kin_mobile_numberB.setText("Mob No.");
        kin_mobile_numberB.setBorder(null);
        kin_mobile_numberB.setMargin(new java.awt.Insets(2, 4, 2, 2));
        kin_mobile_numberB.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                kin_mobile_numberBFocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                kin_mobile_numberBFocusLost(evt);
            }
        });
        kin_mobile_numberB.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                kin_mobile_numberBMouseClicked(evt);
            }
        });
        kin_mobile_numberB.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                kin_mobile_numberBActionPerformed(evt);
            }
        });
        panelB.add(kin_mobile_numberB, new org.netbeans.lib.awtextra.AbsoluteConstraints(490, 1350, 320, 40));

        kin_underlineB2.setForeground(new java.awt.Color(54, 33, 89));
        panelB.add(kin_underlineB2, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 1450, 320, 10));

        kin_relation_B.setFont(new java.awt.Font("Roboto", 0, 24)); // NOI18N
        kin_relation_B.setForeground(new java.awt.Color(102, 102, 102));
        kin_relation_B.setText("Relation");
        kin_relation_B.setBorder(null);
        kin_relation_B.setMargin(new java.awt.Insets(2, 4, 2, 2));
        kin_relation_B.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                kin_relation_BFocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                kin_relation_BFocusLost(evt);
            }
        });
        kin_relation_B.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                kin_relation_BMouseClicked(evt);
            }
        });
        kin_relation_B.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                kin_relation_BActionPerformed(evt);
            }
        });
        panelB.add(kin_relation_B, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 1410, 320, 40));

        address_labelA5.setFont(new java.awt.Font("Montserrat", 0, 28)); // NOI18N
        address_labelA5.setForeground(new java.awt.Color(51, 51, 51));
        address_labelA5.setText("Address:");
        panelB.add(address_labelA5, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 1750, -1, 40));

        villageB.setFont(new java.awt.Font("Roboto", 0, 24)); // NOI18N
        villageB.setForeground(new java.awt.Color(102, 102, 102));
        villageB.setText("Vill");
        villageB.setBorder(null);
        villageB.setMargin(new java.awt.Insets(2, 4, 2, 2));
        villageB.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                villageBFocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                villageBFocusLost(evt);
            }
        });
        villageB.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                villageBMouseClicked(evt);
            }
        });
        panelB.add(villageB, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 1800, 320, 40));

        village_underlineB.setForeground(new java.awt.Color(54, 33, 89));
        panelB.add(village_underlineB, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 1840, 320, 10));

        PO_B.setFont(new java.awt.Font("Roboto", 0, 24)); // NOI18N
        PO_B.setForeground(new java.awt.Color(102, 102, 102));
        PO_B.setText("PO");
        PO_B.setBorder(null);
        PO_B.setMargin(new java.awt.Insets(2, 4, 2, 2));
        PO_B.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                PO_BFocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                PO_BFocusLost(evt);
            }
        });
        PO_B.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                PO_BMouseClicked(evt);
            }
        });
        PO_B.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                PO_BActionPerformed(evt);
            }
        });
        panelB.add(PO_B, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 1860, 320, 40));

        PO_underlineB.setForeground(new java.awt.Color(54, 33, 89));
        panelB.add(PO_underlineB, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 1900, 320, 10));

        distB.setFont(new java.awt.Font("Roboto", 0, 24)); // NOI18N
        distB.setForeground(new java.awt.Color(102, 102, 102));
        distB.setText("Dist");
        distB.setBorder(null);
        distB.setMargin(new java.awt.Insets(2, 4, 2, 2));
        distB.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                distBFocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                distBFocusLost(evt);
            }
        });
        distB.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                distBMouseClicked(evt);
            }
        });
        distB.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                distBActionPerformed(evt);
            }
        });
        panelB.add(distB, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 1920, 320, 40));

        dist_underlineB.setForeground(new java.awt.Color(54, 33, 89));
        panelB.add(dist_underlineB, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 1960, 320, 10));

        PIN_underlineB.setForeground(new java.awt.Color(54, 33, 89));
        panelB.add(PIN_underlineB, new org.netbeans.lib.awtextra.AbsoluteConstraints(490, 1960, 320, 10));

        PIN_B.setFont(new java.awt.Font("Roboto", 0, 24)); // NOI18N
        PIN_B.setForeground(new java.awt.Color(102, 102, 102));
        PIN_B.setText("PIN");
        PIN_B.setBorder(null);
        PIN_B.setMargin(new java.awt.Insets(2, 4, 2, 2));
        PIN_B.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                PIN_BFocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                PIN_BFocusLost(evt);
            }
        });
        PIN_B.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                PIN_BMouseClicked(evt);
            }
        });
        panelB.add(PIN_B, new org.netbeans.lib.awtextra.AbsoluteConstraints(490, 1920, 320, 40));

        state_underlineB.setForeground(new java.awt.Color(54, 33, 89));
        panelB.add(state_underlineB, new org.netbeans.lib.awtextra.AbsoluteConstraints(490, 1900, 320, 10));

        stateB.setFont(new java.awt.Font("Roboto", 0, 24)); // NOI18N
        stateB.setForeground(new java.awt.Color(102, 102, 102));
        stateB.setText("State");
        stateB.setBorder(null);
        stateB.setMargin(new java.awt.Insets(2, 4, 2, 2));
        stateB.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                stateBFocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                stateBFocusLost(evt);
            }
        });
        stateB.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                stateBMouseClicked(evt);
            }
        });
        panelB.add(stateB, new org.netbeans.lib.awtextra.AbsoluteConstraints(490, 1860, 320, 40));

        teh_underlineB.setForeground(new java.awt.Color(54, 33, 89));
        panelB.add(teh_underlineB, new org.netbeans.lib.awtextra.AbsoluteConstraints(490, 1840, 320, 10));

        tehB.setFont(new java.awt.Font("Roboto", 0, 24)); // NOI18N
        tehB.setForeground(new java.awt.Color(102, 102, 102));
        tehB.setText("Teh");
        tehB.setBorder(null);
        tehB.setMargin(new java.awt.Insets(2, 4, 2, 2));
        tehB.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                tehBFocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                tehBFocusLost(evt);
            }
        });
        tehB.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tehBMouseClicked(evt);
            }
        });
        tehB.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                tehBActionPerformed(evt);
            }
        });
        panelB.add(tehB, new org.netbeans.lib.awtextra.AbsoluteConstraints(490, 1800, 320, 40));

        add_buttonB.setForeground(new java.awt.Color(240, 240, 240));
        add_buttonB.setMaximumSize(new java.awt.Dimension(95, 25));
        add_buttonB.setMinimumSize(new java.awt.Dimension(95, 25));
        add_buttonB.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                add_buttonBMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                add_buttonBMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                add_buttonBMouseExited(evt);
            }
        });
        add_buttonB.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        add_labelA1.setFont(new java.awt.Font("Roboto", 0, 14)); // NOI18N
        add_labelA1.setForeground(new java.awt.Color(54, 33, 89));
        add_labelA1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        add_labelA1.setText("ADD");
        add_labelA1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                add_labelA1MouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                add_labelA1MouseEntered(evt);
            }
        });
        add_buttonB.add(add_labelA1, new org.netbeans.lib.awtextra.AbsoluteConstraints(52, 13, 71, 24));

        panelB.add(add_buttonB, new org.netbeans.lib.awtextra.AbsoluteConstraints(630, 2010, 180, 50));

        okayB.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/PNGS/check-mark.png"))); // NOI18N
        okayB.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                okayBMouseClicked(evt);
            }
        });
        panelB.add(okayB, new org.netbeans.lib.awtextra.AbsoluteConstraints(410, 90, -1, 40));

        labelB.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        labelB.setText("Fetch from previous records");
        panelB.add(labelB, new org.netbeans.lib.awtextra.AbsoluteConstraints(460, 90, -1, 40));

        ScrollPaneB.setViewportView(panelB);

        bg.add(ScrollPaneB, new org.netbeans.lib.awtextra.AbsoluteConstraints(340, 192, 910, 480));

        ScrollPaneC.setBorder(null);
        ScrollPaneC.setHorizontalScrollBarPolicy(javax.swing.ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);

        panelC.setBackground(new java.awt.Color(255, 255, 255));
        panelC.setFocusable(false);
        panelC.setMaximumSize(new java.awt.Dimension(950, 1310));
        panelC.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        service_number_underlineC.setForeground(new java.awt.Color(54, 33, 89));
        panelC.add(service_number_underlineC, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 130, 400, 20));

        service_number_textfieldC.setFont(new java.awt.Font("Roboto", 0, 24)); // NOI18N
        service_number_textfieldC.setForeground(new java.awt.Color(54, 33, 89));
        service_number_textfieldC.setBorder(null);
        service_number_textfieldC.setMargin(new java.awt.Insets(2, 4, 2, 2));
        service_number_textfieldC.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                service_number_textfieldCKeyPressed(evt);
            }
        });
        panelC.add(service_number_textfieldC, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 90, 400, 40));

        service_number_labelC.setFont(new java.awt.Font("Montserrat", 0, 28)); // NOI18N
        service_number_labelC.setForeground(new java.awt.Color(51, 51, 51));
        service_number_labelC.setText("Army Number");
        panelC.add(service_number_labelC, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 50, -1, 40));

        panelA_bottom_line2.setForeground(new java.awt.Color(54, 33, 89));
        panelC.add(panelA_bottom_line2, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 1450, 910, -1));

        okayC.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/PNGS/check-mark.png"))); // NOI18N
        okayC.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                okayCMouseClicked(evt);
            }
        });
        panelC.add(okayC, new org.netbeans.lib.awtextra.AbsoluteConstraints(490, 90, -1, 40));

        name_labelC.setBackground(new java.awt.Color(255, 255, 255));
        name_labelC.setFont(new java.awt.Font("Montserrat", 0, 28)); // NOI18N
        name_labelC.setForeground(new java.awt.Color(51, 51, 51));
        name_labelC.setText("Name");
        panelC.add(name_labelC, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 190, -1, 50));

        name.setFont(new java.awt.Font("Roboto Bk", 0, 24)); // NOI18N
        name.setForeground(new java.awt.Color(54, 33, 89));
        name.setText("> XXXXXXX");
        panelC.add(name, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 230, -1, 30));

        Photo.setMaximumSize(new java.awt.Dimension(95, 25));
        Photo.setMinimumSize(new java.awt.Dimension(95, 25));
        Photo.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                PhotoMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                PhotoMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                PhotoMouseExited(evt);
            }
        });
        Photo.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());
        Photo.add(photo_labelC, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 180, 200));

        panelC.add(Photo, new org.netbeans.lib.awtextra.AbsoluteConstraints(620, 80, 180, 200));

        rank_labelC.setBackground(new java.awt.Color(255, 255, 255));
        rank_labelC.setFont(new java.awt.Font("Montserrat", 0, 28)); // NOI18N
        rank_labelC.setForeground(new java.awt.Color(51, 51, 51));
        rank_labelC.setText("Rank");
        panelC.add(rank_labelC, new org.netbeans.lib.awtextra.AbsoluteConstraints(350, 190, -1, 50));

        rank.setFont(new java.awt.Font("Roboto Bk", 0, 24)); // NOI18N
        rank.setForeground(new java.awt.Color(54, 33, 89));
        rank.setText("> XXXXXXX");
        panelC.add(rank, new org.netbeans.lib.awtextra.AbsoluteConstraints(350, 230, -1, 30));

        company_labelC.setBackground(new java.awt.Color(255, 255, 255));
        company_labelC.setFont(new java.awt.Font("Montserrat", 0, 28)); // NOI18N
        company_labelC.setForeground(new java.awt.Color(51, 51, 51));
        company_labelC.setText("Company");
        panelC.add(company_labelC, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 280, -1, 50));

        company.setFont(new java.awt.Font("Roboto Bk", 0, 24)); // NOI18N
        company.setForeground(new java.awt.Color(54, 33, 89));
        company.setText("> XXXXXXX");
        panelC.add(company, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 320, -1, 30));

        indl_mobileC.setFont(new java.awt.Font("Roboto Bk", 0, 24)); // NOI18N
        indl_mobileC.setForeground(new java.awt.Color(54, 33, 89));
        indl_mobileC.setText("> XXXXXXX");
        panelC.add(indl_mobileC, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 500, -1, 30));

        indl_mobile_labelC.setBackground(new java.awt.Color(255, 255, 255));
        indl_mobile_labelC.setFont(new java.awt.Font("Montserrat", 0, 28)); // NOI18N
        indl_mobile_labelC.setForeground(new java.awt.Color(51, 51, 51));
        indl_mobile_labelC.setText("Indl Mob No.");
        panelC.add(indl_mobile_labelC, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 460, -1, 50));

        NOK_name_labelC.setBackground(new java.awt.Color(255, 255, 255));
        NOK_name_labelC.setFont(new java.awt.Font("Montserrat", 0, 28)); // NOI18N
        NOK_name_labelC.setForeground(new java.awt.Color(51, 51, 51));
        NOK_name_labelC.setText("NOK Name");
        panelC.add(NOK_name_labelC, new org.netbeans.lib.awtextra.AbsoluteConstraints(350, 640, -1, 50));

        medical_category_labelC.setBackground(new java.awt.Color(255, 255, 255));
        medical_category_labelC.setFont(new java.awt.Font("Montserrat", 0, 28)); // NOI18N
        medical_category_labelC.setForeground(new java.awt.Color(51, 51, 51));
        medical_category_labelC.setText("Med Cat");
        panelC.add(medical_category_labelC, new org.netbeans.lib.awtextra.AbsoluteConstraints(350, 280, -1, 50));

        courses_done_labelC.setBackground(new java.awt.Color(255, 255, 255));
        courses_done_labelC.setFont(new java.awt.Font("Montserrat", 0, 28)); // NOI18N
        courses_done_labelC.setForeground(new java.awt.Color(51, 51, 51));
        courses_done_labelC.setText("Courses Done");
        panelC.add(courses_done_labelC, new org.netbeans.lib.awtextra.AbsoluteConstraints(350, 370, -1, 50));

        courses_doneC.setFont(new java.awt.Font("Roboto Bk", 0, 24)); // NOI18N
        courses_doneC.setForeground(new java.awt.Color(54, 33, 89));
        courses_doneC.setText("> XXXXXXX");
        panelC.add(courses_doneC, new org.netbeans.lib.awtextra.AbsoluteConstraints(350, 410, -1, 30));

        medical_category.setFont(new java.awt.Font("Roboto Bk", 0, 24)); // NOI18N
        medical_category.setForeground(new java.awt.Color(54, 33, 89));
        medical_category.setText("> XXXXXXX");
        panelC.add(medical_category, new org.netbeans.lib.awtextra.AbsoluteConstraints(350, 320, -1, -1));

        NOK_nameC.setFont(new java.awt.Font("Roboto Bk", 0, 24)); // NOI18N
        NOK_nameC.setForeground(new java.awt.Color(54, 33, 89));
        NOK_nameC.setText("> XXXXXXX");
        panelC.add(NOK_nameC, new org.netbeans.lib.awtextra.AbsoluteConstraints(350, 680, -1, 30));

        trade_labelC.setBackground(new java.awt.Color(255, 255, 255));
        trade_labelC.setFont(new java.awt.Font("Montserrat", 0, 28)); // NOI18N
        trade_labelC.setForeground(new java.awt.Color(51, 51, 51));
        trade_labelC.setText("Trade");
        panelC.add(trade_labelC, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 640, -1, 50));

        tradeC.setFont(new java.awt.Font("Roboto Bk", 0, 24)); // NOI18N
        tradeC.setForeground(new java.awt.Color(54, 33, 89));
        tradeC.setText("> XXXXXXX");
        panelC.add(tradeC, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 680, -1, 30));

        DOB_labelC.setBackground(new java.awt.Color(255, 255, 255));
        DOB_labelC.setFont(new java.awt.Font("Montserrat", 0, 28)); // NOI18N
        DOB_labelC.setForeground(new java.awt.Color(51, 51, 51));
        DOB_labelC.setText("Date of Birth");
        panelC.add(DOB_labelC, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 370, -1, 50));

        DOB.setFont(new java.awt.Font("Roboto Bk", 0, 24)); // NOI18N
        DOB.setForeground(new java.awt.Color(54, 33, 89));
        DOB.setText("> XXXXXXX");
        panelC.add(DOB, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 410, -1, 30));

        unitC.setFont(new java.awt.Font("Roboto Bk", 0, 24)); // NOI18N
        unitC.setForeground(new java.awt.Color(54, 33, 89));
        unitC.setText("> XXXXXXX");
        panelC.add(unitC, new org.netbeans.lib.awtextra.AbsoluteConstraints(350, 500, -1, 30));

        unit_labelC.setBackground(new java.awt.Color(255, 255, 255));
        unit_labelC.setFont(new java.awt.Font("Montserrat", 0, 28)); // NOI18N
        unit_labelC.setForeground(new java.awt.Color(51, 51, 51));
        unit_labelC.setText("Unit");
        panelC.add(unit_labelC, new org.netbeans.lib.awtextra.AbsoluteConstraints(350, 460, -1, 50));

        AS_C.setFont(new java.awt.Font("Roboto Bk", 0, 24)); // NOI18N
        AS_C.setForeground(new java.awt.Color(54, 33, 89));
        AS_C.setText("> XXXXXXX");
        panelC.add(AS_C, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 590, -1, 30));

        AS_labelC.setBackground(new java.awt.Color(255, 255, 255));
        AS_labelC.setFont(new java.awt.Font("Montserrat", 0, 28)); // NOI18N
        AS_labelC.setForeground(new java.awt.Color(51, 51, 51));
        AS_labelC.setText("Armed/Services");
        panelC.add(AS_labelC, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 550, -1, 50));

        locationC.setFont(new java.awt.Font("Roboto Bk", 0, 24)); // NOI18N
        locationC.setForeground(new java.awt.Color(54, 33, 89));
        locationC.setText("> XXXXXXX");
        panelC.add(locationC, new org.netbeans.lib.awtextra.AbsoluteConstraints(350, 590, -1, 30));

        location_labelC.setBackground(new java.awt.Color(255, 255, 255));
        location_labelC.setFont(new java.awt.Font("Montserrat", 0, 28)); // NOI18N
        location_labelC.setForeground(new java.awt.Color(51, 51, 51));
        location_labelC.setText("Location");
        panelC.add(location_labelC, new org.netbeans.lib.awtextra.AbsoluteConstraints(350, 550, -1, 50));

        NOK_relation_labelC.setBackground(new java.awt.Color(255, 255, 255));
        NOK_relation_labelC.setFont(new java.awt.Font("Montserrat", 0, 28)); // NOI18N
        NOK_relation_labelC.setForeground(new java.awt.Color(51, 51, 51));
        NOK_relation_labelC.setText("NOK Relation");
        panelC.add(NOK_relation_labelC, new org.netbeans.lib.awtextra.AbsoluteConstraints(350, 730, -1, 50));

        NOK_relationC.setFont(new java.awt.Font("Roboto Bk", 0, 24)); // NOI18N
        NOK_relationC.setForeground(new java.awt.Color(54, 33, 89));
        NOK_relationC.setText("> XXXXXXX");
        panelC.add(NOK_relationC, new org.netbeans.lib.awtextra.AbsoluteConstraints(350, 770, -1, 30));

        NOK_mobile_labelC.setBackground(new java.awt.Color(255, 255, 255));
        NOK_mobile_labelC.setFont(new java.awt.Font("Montserrat", 0, 28)); // NOI18N
        NOK_mobile_labelC.setForeground(new java.awt.Color(51, 51, 51));
        NOK_mobile_labelC.setText("NOK Mob No.");
        panelC.add(NOK_mobile_labelC, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 730, -1, 50));

        NOK_mobileC.setFont(new java.awt.Font("Roboto Bk", 0, 24)); // NOI18N
        NOK_mobileC.setForeground(new java.awt.Color(54, 33, 89));
        NOK_mobileC.setText("> XXXXXXX");
        panelC.add(NOK_mobileC, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 770, -1, 30));

        address_labelC.setBackground(new java.awt.Color(255, 255, 255));
        address_labelC.setFont(new java.awt.Font("Montserrat", 0, 28)); // NOI18N
        address_labelC.setForeground(new java.awt.Color(51, 51, 51));
        address_labelC.setText("Address");
        panelC.add(address_labelC, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 1030, -1, 50));

        villC.setFont(new java.awt.Font("Roboto Bk", 0, 24)); // NOI18N
        villC.setForeground(new java.awt.Color(54, 33, 89));
        villC.setText("> XXXXXXX");
        panelC.add(villC, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 1130, -1, 30));

        PO_labelC.setBackground(new java.awt.Color(255, 255, 255));
        PO_labelC.setFont(new java.awt.Font("Montserrat", 0, 24)); // NOI18N
        PO_labelC.setForeground(new java.awt.Color(66, 50, 77));
        PO_labelC.setText("PO");
        panelC.add(PO_labelC, new org.netbeans.lib.awtextra.AbsoluteConstraints(350, 1090, -1, 50));

        PO_C.setFont(new java.awt.Font("Roboto Bk", 0, 24)); // NOI18N
        PO_C.setForeground(new java.awt.Color(54, 33, 89));
        PO_C.setText("> XXXXXXX");
        panelC.add(PO_C, new org.netbeans.lib.awtextra.AbsoluteConstraints(350, 1130, -1, 30));

        dist_labelC.setBackground(new java.awt.Color(255, 255, 255));
        dist_labelC.setFont(new java.awt.Font("Montserrat", 0, 24)); // NOI18N
        dist_labelC.setForeground(new java.awt.Color(66, 50, 77));
        dist_labelC.setText("District");
        panelC.add(dist_labelC, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 1170, -1, 50));

        distC.setFont(new java.awt.Font("Roboto Bk", 0, 24)); // NOI18N
        distC.setForeground(new java.awt.Color(54, 33, 89));
        distC.setText("> XXXXXXX");
        panelC.add(distC, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 1210, -1, 30));

        state_labelC.setBackground(new java.awt.Color(255, 255, 255));
        state_labelC.setFont(new java.awt.Font("Montserrat", 0, 24)); // NOI18N
        state_labelC.setForeground(new java.awt.Color(66, 50, 77));
        state_labelC.setText("State");
        panelC.add(state_labelC, new org.netbeans.lib.awtextra.AbsoluteConstraints(350, 1170, -1, 50));

        stateC.setFont(new java.awt.Font("Roboto Bk", 0, 24)); // NOI18N
        stateC.setForeground(new java.awt.Color(54, 33, 89));
        stateC.setText("> XXXXXXX");
        panelC.add(stateC, new org.netbeans.lib.awtextra.AbsoluteConstraints(350, 1210, -1, 30));

        teh_labelC.setBackground(new java.awt.Color(255, 255, 255));
        teh_labelC.setFont(new java.awt.Font("Montserrat", 0, 24)); // NOI18N
        teh_labelC.setForeground(new java.awt.Color(66, 50, 77));
        teh_labelC.setText("Tehsil");
        panelC.add(teh_labelC, new org.netbeans.lib.awtextra.AbsoluteConstraints(600, 1090, -1, 50));

        tehsil.setFont(new java.awt.Font("Roboto Bk", 0, 24)); // NOI18N
        tehsil.setForeground(new java.awt.Color(54, 33, 89));
        tehsil.setText("> XXXXXXX");
        panelC.add(tehsil, new org.netbeans.lib.awtextra.AbsoluteConstraints(600, 1130, -1, 30));

        PIN_labelC.setBackground(new java.awt.Color(255, 255, 255));
        PIN_labelC.setFont(new java.awt.Font("Montserrat", 0, 24)); // NOI18N
        PIN_labelC.setForeground(new java.awt.Color(66, 50, 77));
        PIN_labelC.setText("PIN");
        panelC.add(PIN_labelC, new org.netbeans.lib.awtextra.AbsoluteConstraints(600, 1170, -1, 50));

        PIN_C.setFont(new java.awt.Font("Roboto Bk", 0, 24)); // NOI18N
        PIN_C.setForeground(new java.awt.Color(54, 33, 89));
        PIN_C.setText("> XXXXXXX");
        panelC.add(PIN_C, new org.netbeans.lib.awtextra.AbsoluteConstraints(600, 1210, -1, 30));

        vill_labelC.setBackground(new java.awt.Color(255, 255, 255));
        vill_labelC.setFont(new java.awt.Font("Montserrat", 0, 24)); // NOI18N
        vill_labelC.setForeground(new java.awt.Color(66, 50, 77));
        vill_labelC.setText("Village");
        panelC.add(vill_labelC, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 1090, -1, 50));

        midC2.setForeground(new java.awt.Color(204, 204, 255));
        panelC.add(midC2, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 1290, 580, 10));

        midC1.setForeground(new java.awt.Color(204, 204, 255));
        panelC.add(midC1, new org.netbeans.lib.awtextra.AbsoluteConstraints(210, 1050, 440, 10));

        CO_mob_labelC.setBackground(new java.awt.Color(255, 255, 255));
        CO_mob_labelC.setFont(new java.awt.Font("Montserrat", 0, 28)); // NOI18N
        CO_mob_labelC.setForeground(new java.awt.Color(51, 51, 51));
        CO_mob_labelC.setText("CO Mob No.");
        panelC.add(CO_mob_labelC, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 1320, -1, 50));

        CO_mob.setFont(new java.awt.Font("Roboto Bk", 0, 24)); // NOI18N
        CO_mob.setForeground(new java.awt.Color(54, 33, 89));
        CO_mob.setText("> XXXXXXX");
        panelC.add(CO_mob, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 1360, -1, 30));

        ADJT_mob_labelC.setBackground(new java.awt.Color(255, 255, 255));
        ADJT_mob_labelC.setFont(new java.awt.Font("Montserrat", 0, 28)); // NOI18N
        ADJT_mob_labelC.setForeground(new java.awt.Color(51, 51, 51));
        ADJT_mob_labelC.setText("ADJT Mob No.");
        panelC.add(ADJT_mob_labelC, new org.netbeans.lib.awtextra.AbsoluteConstraints(350, 1320, -1, 50));

        ADJT_mob.setFont(new java.awt.Font("Roboto Bk", 0, 24)); // NOI18N
        ADJT_mob.setForeground(new java.awt.Color(54, 33, 89));
        ADJT_mob.setText("> XXXXXXX");
        panelC.add(ADJT_mob, new org.netbeans.lib.awtextra.AbsoluteConstraints(350, 1360, -1, 30));

        DateIN_ScrollPaneC.setBackground(new java.awt.Color(255, 255, 255));
        DateIN_ScrollPaneC.setVerticalScrollBarPolicy(javax.swing.ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);

        DateIN_TableC.setFont(new java.awt.Font("Roboto", 0, 18)); // NOI18N
        DateIN_TableC.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "S. No.", "Date of Joining"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        DateIN_TableC.setFocusable(false);
        DateIN_TableC.setGridColor(new java.awt.Color(255, 255, 255));
        DateIN_TableC.setMaximumSize(new java.awt.Dimension(300, 60));
        DateIN_TableC.setMinimumSize(new java.awt.Dimension(300, 60));
        DateIN_TableC.setRowHeight(30);
        DateIN_TableC.setSelectionBackground(new java.awt.Color(54, 33, 89));
        DateIN_TableC.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                DateIN_TableCFocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                DateIN_TableCFocusLost(evt);
            }
        });
        DateIN_ScrollPaneC.setViewportView(DateIN_TableC);
        if (DateIN_TableC.getColumnModel().getColumnCount() > 0) {
            DateIN_TableC.getColumnModel().getColumn(0).setMinWidth(40);
            DateIN_TableC.getColumnModel().getColumn(0).setPreferredWidth(40);
            DateIN_TableC.getColumnModel().getColumn(0).setMaxWidth(40);
        }

        panelC.add(DateIN_ScrollPaneC, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 840, 530, 130));

        DateOUT_ScrollPaneC.setBackground(new java.awt.Color(255, 255, 255));
        DateOUT_ScrollPaneC.setVerticalScrollBarPolicy(javax.swing.ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);

        DateOUT_TableC.setFont(new java.awt.Font("Roboto", 0, 18)); // NOI18N
        DateOUT_TableC.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "S. No.", "Date of Attachment", "Date of Joining"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        DateOUT_TableC.setFocusable(false);
        DateOUT_TableC.setGridColor(new java.awt.Color(255, 255, 255));
        DateOUT_TableC.setMaximumSize(new java.awt.Dimension(300, 60));
        DateOUT_TableC.setMinimumSize(new java.awt.Dimension(300, 60));
        DateOUT_TableC.setRowHeight(30);
        DateOUT_TableC.setSelectionBackground(new java.awt.Color(54, 33, 89));
        DateOUT_TableC.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                DateOUT_TableCFocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                DateOUT_TableCFocusLost(evt);
            }
        });
        DateOUT_ScrollPaneC.setViewportView(DateOUT_TableC);
        if (DateOUT_TableC.getColumnModel().getColumnCount() > 0) {
            DateOUT_TableC.getColumnModel().getColumn(0).setMinWidth(40);
            DateOUT_TableC.getColumnModel().getColumn(0).setPreferredWidth(40);
            DateOUT_TableC.getColumnModel().getColumn(0).setMaxWidth(40);
        }

        panelC.add(DateOUT_ScrollPaneC, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 840, 530, 130));

        attachment_labelC.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        attachment_labelC.setText("ATTACHMENT");
        panelC.add(attachment_labelC, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 140, -1, 30));

        ScrollPaneC.setViewportView(panelC);

        bg.add(ScrollPaneC, new org.netbeans.lib.awtextra.AbsoluteConstraints(340, 192, 910, 480));

        panelD.setBackground(new java.awt.Color(255, 255, 255));
        panelD.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        ScrollPaneD.setBackground(new java.awt.Color(255, 255, 255));
        ScrollPaneD.setVerticalScrollBarPolicy(javax.swing.ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);

        TableD.setFont(new java.awt.Font("Roboto", 0, 18)); // NOI18N
        TableD.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "S.no", "Service Num", "Name", "Date of Joining", "Company", "Armed Services", "Trade", "Unit"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        TableD.setFocusable(false);
        TableD.setGridColor(new java.awt.Color(255, 255, 255));
        TableD.setMaximumSize(new java.awt.Dimension(300, 80));
        TableD.setMinimumSize(new java.awt.Dimension(300, 80));
        TableD.setRowHeight(30);
        TableD.setSelectionBackground(new java.awt.Color(54, 33, 89));
        ScrollPaneD.setViewportView(TableD);
        if (TableD.getColumnModel().getColumnCount() > 0) {
            TableD.getColumnModel().getColumn(0).setMinWidth(50);
            TableD.getColumnModel().getColumn(0).setPreferredWidth(50);
            TableD.getColumnModel().getColumn(0).setMaxWidth(50);
        }

        panelD.add(ScrollPaneD, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 50, 780, 420));

        bg.add(panelD, new org.netbeans.lib.awtextra.AbsoluteConstraints(340, 192, 910, 480));

        panelE.setBackground(new java.awt.Color(255, 255, 255));
        panelE.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        ScrollPaneE.setBackground(new java.awt.Color(255, 255, 255));
        ScrollPaneE.setVerticalScrollBarPolicy(javax.swing.ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);

        TableE.setFont(new java.awt.Font("Roboto", 0, 18)); // NOI18N
        TableE.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "S.no", "Service Num", "Name", "Date of Joining", "Date of Attachment", "Company"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        TableE.setAutoResizeMode(javax.swing.JTable.AUTO_RESIZE_ALL_COLUMNS);
        TableE.setFocusable(false);
        TableE.setGridColor(new java.awt.Color(255, 255, 255));
        TableE.setMaximumSize(new java.awt.Dimension(300, 60));
        TableE.setMinimumSize(new java.awt.Dimension(300, 60));
        TableE.setRowHeight(30);
        TableE.setSelectionBackground(new java.awt.Color(54, 33, 89));
        ScrollPaneE.setViewportView(TableE);
        if (TableE.getColumnModel().getColumnCount() > 0) {
            TableE.getColumnModel().getColumn(0).setMinWidth(50);
            TableE.getColumnModel().getColumn(0).setPreferredWidth(50);
            TableE.getColumnModel().getColumn(0).setMaxWidth(50);
        }

        panelE.add(ScrollPaneE, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 50, 780, 420));

        bg.add(panelE, new org.netbeans.lib.awtextra.AbsoluteConstraints(340, 192, 910, 480));

        panelG.setBackground(new java.awt.Color(255, 255, 255));
        panelG.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        ScrollPaneG_IN.setBackground(new java.awt.Color(255, 255, 255));
        ScrollPaneG_IN.setVerticalScrollBarPolicy(javax.swing.ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);

        TableG_IN.setFont(new java.awt.Font("Roboto", 0, 18)); // NOI18N
        TableG_IN.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "S. No.", "Service Number", "Name", "Date of Joining", "Armed Services", "Trade", "Unit"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, true, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        TableG_IN.setAutoResizeMode(javax.swing.JTable.AUTO_RESIZE_ALL_COLUMNS);
        TableG_IN.setFocusable(false);
        TableG_IN.setGridColor(new java.awt.Color(255, 255, 255));
        TableG_IN.setMaximumSize(null);
        TableG_IN.setRowHeight(30);
        TableG_IN.setSelectionBackground(new java.awt.Color(54, 33, 89));
        TableG_IN.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        ScrollPaneG_IN.setViewportView(TableG_IN);
        if (TableG_IN.getColumnModel().getColumnCount() > 0) {
            TableG_IN.getColumnModel().getColumn(0).setMinWidth(40);
            TableG_IN.getColumnModel().getColumn(0).setPreferredWidth(40);
            TableG_IN.getColumnModel().getColumn(0).setMaxWidth(40);
        }

        panelG.add(ScrollPaneG_IN, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 170, 780, 300));

        service_number_label.setBackground(new java.awt.Color(255, 255, 255));
        service_number_label.setFont(new java.awt.Font("Montserrat", 0, 28)); // NOI18N
        service_number_label.setForeground(new java.awt.Color(51, 51, 51));
        service_number_label.setText("Army Number");
        panelG.add(service_number_label, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 50, -1, 40));

        service_numberG.setFont(new java.awt.Font("Roboto", 0, 24)); // NOI18N
        service_numberG.setForeground(new java.awt.Color(54, 33, 89));
        service_numberG.setBorder(null);
        service_numberG.setMargin(new java.awt.Insets(2, 4, 2, 2));
        service_numberG.setSelectionColor(new java.awt.Color(204, 204, 255));
        service_numberG.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                service_numberGKeyPressed(evt);
            }
        });
        panelG.add(service_numberG, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 90, 400, 40));

        textfield_underline.setForeground(new java.awt.Color(54, 33, 89));
        panelG.add(textfield_underline, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 130, 400, 10));

        okayG.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/PNGS/check-mark.png"))); // NOI18N
        okayG.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                okayGMouseClicked(evt);
            }
        });
        panelG.add(okayG, new org.netbeans.lib.awtextra.AbsoluteConstraints(490, 90, -1, 40));

        ScrollPaneG_OUT.setBackground(new java.awt.Color(255, 255, 255));
        ScrollPaneG_OUT.setVerticalScrollBarPolicy(javax.swing.ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);

        TableG_OUT.setFont(new java.awt.Font("Roboto", 0, 18)); // NOI18N
        TableG_OUT.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "S. No.", "Service Number", "Name", "Date of Joining", "Date of Attachment", "Location", "Purpose"
            }
        ));
        TableG_OUT.setAutoResizeMode(javax.swing.JTable.AUTO_RESIZE_ALL_COLUMNS);
        TableG_OUT.setFocusable(false);
        TableG_OUT.setGridColor(new java.awt.Color(255, 255, 255));
        TableG_OUT.setMaximumSize(null);
        TableG_OUT.setRowHeight(30);
        TableG_OUT.setSelectionBackground(new java.awt.Color(54, 33, 89));
        TableG_OUT.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        ScrollPaneG_OUT.setViewportView(TableG_OUT);
        if (TableG_OUT.getColumnModel().getColumnCount() > 0) {
            TableG_OUT.getColumnModel().getColumn(0).setMinWidth(40);
            TableG_OUT.getColumnModel().getColumn(0).setPreferredWidth(40);
            TableG_OUT.getColumnModel().getColumn(0).setMaxWidth(40);
        }

        panelG.add(ScrollPaneG_OUT, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 170, 780, 300));

        bg.add(panelG, new org.netbeans.lib.awtextra.AbsoluteConstraints(340, 192, 910, 480));

        ScrollPaneF.setBorder(null);
        ScrollPaneF.setHorizontalScrollBarPolicy(javax.swing.ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);

        panelF.setBackground(new java.awt.Color(255, 255, 255));
        panelF.setMaximumSize(new java.awt.Dimension(950, 1310));
        panelF.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                panelFFocusGained(evt);
            }
        });
        panelF.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                panelFMouseClicked(evt);
            }
        });
        panelF.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        upload_photo_labelF.setBackground(new java.awt.Color(255, 255, 255));
        upload_photo_labelF.setFont(new java.awt.Font("Montserrat", 0, 28)); // NOI18N
        upload_photo_labelF.setForeground(new java.awt.Color(51, 51, 51));
        upload_photo_labelF.setText("Upload Photo");
        panelF.add(upload_photo_labelF, new org.netbeans.lib.awtextra.AbsoluteConstraints(490, 470, -1, -1));

        rank_comboboxF.setFont(new java.awt.Font("Roboto", 0, 18)); // NOI18N
        rank_comboboxF.setForeground(new java.awt.Color(44, 62, 80));
        rank_comboboxF.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Sepoy", "Naik", "Havildar", "Naib Subedar", "Subedar", "Subedar Major", "Lieutenant", "Captain", "Major", "Lt Colonel", "Colonel", "Brigadier", "Major General", "Lt General", "General" }));
        rank_comboboxF.setBorder(null);
        panelF.add(rank_comboboxF, new org.netbeans.lib.awtextra.AbsoluteConstraints(490, 290, 320, 40));

        name_underlineF.setForeground(new java.awt.Color(54, 33, 89));
        panelF.add(name_underlineF, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 330, 320, 20));

        name_textfieldF.setFont(new java.awt.Font("Roboto", 0, 24)); // NOI18N
        name_textfieldF.setForeground(new java.awt.Color(54, 33, 89));
        name_textfieldF.setBorder(null);
        name_textfieldF.setMargin(new java.awt.Insets(2, 4, 2, 2));
        panelF.add(name_textfieldF, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 290, 320, 40));

        name_labelF.setFont(new java.awt.Font("Montserrat", 0, 28)); // NOI18N
        name_labelF.setForeground(new java.awt.Color(51, 51, 51));
        name_labelF.setText("Name");
        panelF.add(name_labelF, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 250, -1, 40));

        medical_category_labelF.setBackground(new java.awt.Color(255, 255, 255));
        medical_category_labelF.setFont(new java.awt.Font("Montserrat", 0, 28)); // NOI18N
        medical_category_labelF.setForeground(new java.awt.Color(51, 51, 51));
        medical_category_labelF.setText("Medical Category");
        panelF.add(medical_category_labelF, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 470, -1, 40));

        birth_labelF.setBackground(new java.awt.Color(255, 255, 255));
        birth_labelF.setFont(new java.awt.Font("Montserrat", 0, 28)); // NOI18N
        birth_labelF.setForeground(new java.awt.Color(51, 51, 51));
        birth_labelF.setText("Date of Birth");
        panelF.add(birth_labelF, new org.netbeans.lib.awtextra.AbsoluteConstraints(490, 360, -1, -1));

        birth_year_comboboxF.setFont(new java.awt.Font("Roboto", 0, 18)); // NOI18N
        birth_year_comboboxF.setForeground(new java.awt.Color(44, 62, 80));
        birth_year_comboboxF.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "YYYY", "1950", "1951", "1952", "1953", "1954", "1955", "1956", "1957", "1958", "1959", "1960", "1961", "1962", "1963", "1964", "1965", "1966", "1967", "1968", "1969", "1970", "1971", "1972", "1973", "1974", "1975", "1976", "1977", "1978", "1979", "1980", "1981", "1982", "1983", "1984", "1985", "1986", "1987", "1988", "1989", "1990", "1991", "1992", "1993", "1994", "1995", "1996", "1997", "1998", "1999", "2000", "2001", "2002", "2003", "2004", "2005", "2006", "2007", "2008", "2009", "2010", "2011", "2012", "2013", "2014", "2015", "2016", "2017", "2018", "2019", "2020", "2021", "2022", "2023", "2024", "2025", "2026", "2027", "2028", "2029", "2030", "2031", "2032", "2033", "2034", "2035", "2036", "2037", "2038", "2039", "2040", "2041", "2042", "2043", "2044", "2045", "2046", "2047", "2048", "2049", "2050", "2051", "2052", "2053", "2054", "2055", "2056", "2057", "2058", "2059", "2060", "2061", "2062", "2063", "2064", "2065", "2066", "2067", "2068", "2069", "2070", "2071", "2072", "2073", "2074", "2075", "2076", "2077", "2078", "2079", "2080", "2081", "2082", "2083", "2084", "2085", "2086", "2087", "2088", "2089", "2090", "2091", "2092", "2093", "2094", "2095", "2096", "2097", "2098", "2099", "2100", " " }));
        birth_year_comboboxF.setBorder(null);
        panelF.add(birth_year_comboboxF, new org.netbeans.lib.awtextra.AbsoluteConstraints(710, 400, 100, 40));

        birth_date_comboboxF.setFont(new java.awt.Font("Roboto", 0, 18)); // NOI18N
        birth_date_comboboxF.setForeground(new java.awt.Color(44, 62, 80));
        birth_date_comboboxF.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "DD", "01", "02", "03", "04", "05", "06", "07", "08", "09", "10", "11", "12", "13", "14", "15", "16", "17", "18", "19", "20", "21", "22", "23", "24", "25", "26", "27", "28", "29", "30", "31" }));
        birth_date_comboboxF.setBorder(null);
        panelF.add(birth_date_comboboxF, new org.netbeans.lib.awtextra.AbsoluteConstraints(490, 400, 100, 40));

        birth_month_comboboxF.setFont(new java.awt.Font("Roboto", 0, 18)); // NOI18N
        birth_month_comboboxF.setForeground(new java.awt.Color(44, 62, 80));
        birth_month_comboboxF.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "MM", "01", "02", "03", "04", "05", "06", "07", "08", "09", "10", "11", "12" }));
        birth_month_comboboxF.setBorder(null);
        panelF.add(birth_month_comboboxF, new org.netbeans.lib.awtextra.AbsoluteConstraints(600, 400, 100, 40));

        courses_done_labelF.setFont(new java.awt.Font("Montserrat", 0, 28)); // NOI18N
        courses_done_labelF.setForeground(new java.awt.Color(51, 51, 51));
        courses_done_labelF.setText("Courses Done");
        panelF.add(courses_done_labelF, new org.netbeans.lib.awtextra.AbsoluteConstraints(490, 580, -1, 40));

        courses_underlineF.setForeground(new java.awt.Color(54, 33, 89));
        panelF.add(courses_underlineF, new org.netbeans.lib.awtextra.AbsoluteConstraints(490, 660, 320, 20));

        courses_textfieldF.setFont(new java.awt.Font("Roboto", 0, 24)); // NOI18N
        courses_textfieldF.setForeground(new java.awt.Color(54, 33, 89));
        courses_textfieldF.setBorder(null);
        courses_textfieldF.setMargin(new java.awt.Insets(2, 4, 2, 2));
        panelF.add(courses_textfieldF, new org.netbeans.lib.awtextra.AbsoluteConstraints(490, 620, 320, 40));

        indl_ID_details_labelF.setFont(new java.awt.Font("Montserrat", 0, 28)); // NOI18N
        indl_ID_details_labelF.setForeground(new java.awt.Color(51, 51, 51));
        indl_ID_details_labelF.setText("Indl ID Details");
        panelF.add(indl_ID_details_labelF, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 890, -1, 40));

        army_number_labelF.setFont(new java.awt.Font("Montserrat", 0, 28)); // NOI18N
        army_number_labelF.setForeground(new java.awt.Color(51, 51, 51));
        army_number_labelF.setText("Army Number");
        panelF.add(army_number_labelF, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 70, -1, 40));

        company_underlineF.setForeground(new java.awt.Color(54, 33, 89));
        panelF.add(company_underlineF, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 440, 320, 20));

        company_textfieldF.setFont(new java.awt.Font("Roboto", 0, 24)); // NOI18N
        company_textfieldF.setForeground(new java.awt.Color(54, 33, 89));
        company_textfieldF.setBorder(null);
        company_textfieldF.setMargin(new java.awt.Insets(2, 4, 2, 2));
        panelF.add(company_textfieldF, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 400, 320, 40));

        company_labelF.setFont(new java.awt.Font("Montserrat", 0, 28)); // NOI18N
        company_labelF.setForeground(new java.awt.Color(51, 51, 51));
        company_labelF.setText("Company");
        panelF.add(company_labelF, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 360, -1, 40));

        panelF_bottom_line.setForeground(new java.awt.Color(54, 33, 89));
        panelF.add(panelF_bottom_line, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 2100, 910, -1));

        indl_mob_labelF.setFont(new java.awt.Font("Montserrat", 0, 28)); // NOI18N
        indl_mob_labelF.setForeground(new java.awt.Color(51, 51, 51));
        indl_mob_labelF.setText("Indl Mob No.");
        panelF.add(indl_mob_labelF, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 580, -1, 40));

        label_91F1.setFont(new java.awt.Font("Montserrat", 0, 28)); // NOI18N
        label_91F1.setForeground(new java.awt.Color(51, 51, 51));
        label_91F1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        label_91F1.setText("+91");
        panelF.add(label_91F1, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 620, 50, 40));

        DOJ_labelF.setBackground(new java.awt.Color(255, 255, 255));
        DOJ_labelF.setFont(new java.awt.Font("Montserrat", 0, 28)); // NOI18N
        DOJ_labelF.setForeground(new java.awt.Color(51, 51, 51));
        DOJ_labelF.setText("D-O-Joining");
        panelF.add(DOJ_labelF, new org.netbeans.lib.awtextra.AbsoluteConstraints(390, 70, -1, 40));

        medical_category_underlineF.setForeground(new java.awt.Color(54, 33, 89));
        panelF.add(medical_category_underlineF, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 550, 320, 20));

        medical_category_textfieldF.setFont(new java.awt.Font("Roboto", 0, 24)); // NOI18N
        medical_category_textfieldF.setForeground(new java.awt.Color(54, 33, 89));
        medical_category_textfieldF.setBorder(null);
        medical_category_textfieldF.setMargin(new java.awt.Insets(2, 4, 2, 2));
        panelF.add(medical_category_textfieldF, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 510, 320, 40));

        rank_labelF.setBackground(new java.awt.Color(255, 255, 255));
        rank_labelF.setFont(new java.awt.Font("Montserrat", 0, 28)); // NOI18N
        rank_labelF.setForeground(new java.awt.Color(51, 51, 51));
        rank_labelF.setText("Rank");
        panelF.add(rank_labelF, new org.netbeans.lib.awtextra.AbsoluteConstraints(490, 250, -1, 40));

        select_file_buttonF.setMaximumSize(new java.awt.Dimension(200, 200));
        select_file_buttonF.setMinimumSize(new java.awt.Dimension(200, 200));
        select_file_buttonF.setPreferredSize(new java.awt.Dimension(200, 200));
        select_file_buttonF.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                select_file_buttonFMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                select_file_buttonFMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                select_file_buttonFMouseExited(evt);
            }
        });
        select_file_buttonF.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        select_file_labelF.setBackground(new java.awt.Color(54, 33, 89));
        select_file_labelF.setFont(new java.awt.Font("Roboto", 0, 18)); // NOI18N
        select_file_labelF.setForeground(new java.awt.Color(54, 33, 89));
        select_file_labelF.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        select_file_labelF.setText("Select File");
        select_file_buttonF.add(select_file_labelF, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 0, 260, 40));

        panelF.add(select_file_buttonF, new org.netbeans.lib.awtextra.AbsoluteConstraints(490, 510, 320, 40));

        indl_underline8.setForeground(new java.awt.Color(54, 33, 89));
        indl_underline8.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                indl_underline8MouseClicked(evt);
            }
        });
        panelF.add(indl_underline8, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 980, 320, 10));

        indl_Icard_numberF.setFont(new java.awt.Font("Roboto", 0, 24)); // NOI18N
        indl_Icard_numberF.setForeground(new java.awt.Color(102, 102, 102));
        indl_Icard_numberF.setText("ICardNumber");
        indl_Icard_numberF.setBorder(null);
        indl_Icard_numberF.setMargin(new java.awt.Insets(2, 4, 2, 2));
        indl_Icard_numberF.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                indl_Icard_numberFFocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                indl_Icard_numberFFocusLost(evt);
            }
        });
        indl_Icard_numberF.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                indl_Icard_numberFMouseClicked(evt);
            }
        });
        panelF.add(indl_Icard_numberF, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 940, 320, 40));

        indl_underline9.setForeground(new java.awt.Color(54, 33, 89));
        panelF.add(indl_underline9, new org.netbeans.lib.awtextra.AbsoluteConstraints(490, 980, 320, 10));

        indl_PAN_F.setFont(new java.awt.Font("Roboto", 0, 24)); // NOI18N
        indl_PAN_F.setForeground(new java.awt.Color(102, 102, 102));
        indl_PAN_F.setText("PAN");
        indl_PAN_F.setBorder(null);
        indl_PAN_F.setMargin(new java.awt.Insets(2, 4, 2, 2));
        indl_PAN_F.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                indl_PAN_FFocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                indl_PAN_FFocusLost(evt);
            }
        });
        indl_PAN_F.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                indl_PAN_FMouseClicked(evt);
            }
        });
        indl_PAN_F.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                indl_PAN_FActionPerformed(evt);
            }
        });
        panelF.add(indl_PAN_F, new org.netbeans.lib.awtextra.AbsoluteConstraints(490, 940, 320, 40));

        indl_underline10.setForeground(new java.awt.Color(54, 33, 89));
        panelF.add(indl_underline10, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 1040, 320, 10));

        indl_aadharF.setFont(new java.awt.Font("Roboto", 0, 24)); // NOI18N
        indl_aadharF.setForeground(new java.awt.Color(102, 102, 102));
        indl_aadharF.setText("Aadhar");
        indl_aadharF.setBorder(null);
        indl_aadharF.setMargin(new java.awt.Insets(2, 4, 2, 2));
        indl_aadharF.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                indl_aadharFFocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                indl_aadharFFocusLost(evt);
            }
        });
        indl_aadharF.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                indl_aadharFMouseClicked(evt);
            }
        });
        indl_aadharF.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                indl_aadharFActionPerformed(evt);
            }
        });
        panelF.add(indl_aadharF, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 1000, 320, 40));

        indl_underline11.setForeground(new java.awt.Color(54, 33, 89));
        panelF.add(indl_underline11, new org.netbeans.lib.awtextra.AbsoluteConstraints(490, 1040, 320, 10));

        indl_mailF.setFont(new java.awt.Font("Roboto", 0, 24)); // NOI18N
        indl_mailF.setForeground(new java.awt.Color(102, 102, 102));
        indl_mailF.setText("E-Mail");
        indl_mailF.setBorder(null);
        indl_mailF.setMargin(new java.awt.Insets(2, 4, 2, 2));
        indl_mailF.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                indl_mailFFocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                indl_mailFFocusLost(evt);
            }
        });
        indl_mailF.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                indl_mailFMouseClicked(evt);
            }
        });
        panelF.add(indl_mailF, new org.netbeans.lib.awtextra.AbsoluteConstraints(490, 1000, 320, 40));

        indl_bank_nameF.setFont(new java.awt.Font("Roboto", 0, 24)); // NOI18N
        indl_bank_nameF.setForeground(new java.awt.Color(102, 102, 102));
        indl_bank_nameF.setText("Bank Name");
        indl_bank_nameF.setBorder(null);
        indl_bank_nameF.setMargin(new java.awt.Insets(2, 4, 2, 2));
        indl_bank_nameF.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                indl_bank_nameFFocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                indl_bank_nameFFocusLost(evt);
            }
        });
        indl_bank_nameF.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                indl_bank_nameFMouseClicked(evt);
            }
        });
        panelF.add(indl_bank_nameF, new org.netbeans.lib.awtextra.AbsoluteConstraints(490, 1060, 320, 40));

        indl_account_numberF.setFont(new java.awt.Font("Roboto", 0, 24)); // NOI18N
        indl_account_numberF.setForeground(new java.awt.Color(102, 102, 102));
        indl_account_numberF.setText("Account Number");
        indl_account_numberF.setBorder(null);
        indl_account_numberF.setMargin(new java.awt.Insets(2, 4, 2, 2));
        indl_account_numberF.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                indl_account_numberFFocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                indl_account_numberFFocusLost(evt);
            }
        });
        indl_account_numberF.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                indl_account_numberFMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                indl_account_numberFMouseEntered(evt);
            }
        });
        indl_account_numberF.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                indl_account_numberFActionPerformed(evt);
            }
        });
        panelF.add(indl_account_numberF, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 1060, 320, 40));

        indl_underline12.setForeground(new java.awt.Color(54, 33, 89));
        panelF.add(indl_underline12, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 1100, 320, 10));

        indl_underline13.setForeground(new java.awt.Color(54, 33, 89));
        panelF.add(indl_underline13, new org.netbeans.lib.awtextra.AbsoluteConstraints(490, 1100, 320, 10));

        midF0.setForeground(new java.awt.Color(204, 204, 255));
        panelF.add(midF0, new org.netbeans.lib.awtextra.AbsoluteConstraints(270, 200, 390, 10));

        village_underlineF.setForeground(new java.awt.Color(54, 33, 89));
        panelF.add(village_underlineF, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 1820, 320, 10));

        villageF.setFont(new java.awt.Font("Roboto", 0, 24)); // NOI18N
        villageF.setForeground(new java.awt.Color(102, 102, 102));
        villageF.setText("Vill");
        villageF.setBorder(null);
        villageF.setMargin(new java.awt.Insets(2, 4, 2, 2));
        villageF.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                villageFFocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                villageFFocusLost(evt);
            }
        });
        villageF.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                villageFMouseClicked(evt);
            }
        });
        panelF.add(villageF, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 1780, 320, 40));

        teh_underlineF.setForeground(new java.awt.Color(54, 33, 89));
        panelF.add(teh_underlineF, new org.netbeans.lib.awtextra.AbsoluteConstraints(490, 1820, 320, 10));

        tehF.setFont(new java.awt.Font("Roboto", 0, 24)); // NOI18N
        tehF.setForeground(new java.awt.Color(102, 102, 102));
        tehF.setText("Teh");
        tehF.setBorder(null);
        tehF.setMargin(new java.awt.Insets(2, 4, 2, 2));
        tehF.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                tehFFocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                tehFFocusLost(evt);
            }
        });
        tehF.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tehFMouseClicked(evt);
            }
        });
        tehF.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                tehFActionPerformed(evt);
            }
        });
        panelF.add(tehF, new org.netbeans.lib.awtextra.AbsoluteConstraints(490, 1780, 320, 40));

        PO_underlineF.setForeground(new java.awt.Color(54, 33, 89));
        panelF.add(PO_underlineF, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 1880, 320, 10));

        PO_F.setFont(new java.awt.Font("Roboto", 0, 24)); // NOI18N
        PO_F.setForeground(new java.awt.Color(102, 102, 102));
        PO_F.setText("PO");
        PO_F.setBorder(null);
        PO_F.setMargin(new java.awt.Insets(2, 4, 2, 2));
        PO_F.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                PO_FFocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                PO_FFocusLost(evt);
            }
        });
        PO_F.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                PO_FMouseClicked(evt);
            }
        });
        PO_F.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                PO_FActionPerformed(evt);
            }
        });
        panelF.add(PO_F, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 1840, 320, 40));

        state_underlineF.setForeground(new java.awt.Color(54, 33, 89));
        panelF.add(state_underlineF, new org.netbeans.lib.awtextra.AbsoluteConstraints(490, 1880, 320, 10));

        stateF.setFont(new java.awt.Font("Roboto", 0, 24)); // NOI18N
        stateF.setForeground(new java.awt.Color(102, 102, 102));
        stateF.setText("State");
        stateF.setBorder(null);
        stateF.setMargin(new java.awt.Insets(2, 4, 2, 2));
        stateF.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                stateFFocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                stateFFocusLost(evt);
            }
        });
        stateF.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                stateFMouseClicked(evt);
            }
        });
        panelF.add(stateF, new org.netbeans.lib.awtextra.AbsoluteConstraints(490, 1840, 320, 40));

        PIN_F.setFont(new java.awt.Font("Roboto", 0, 24)); // NOI18N
        PIN_F.setForeground(new java.awt.Color(102, 102, 102));
        PIN_F.setText("PIN");
        PIN_F.setBorder(null);
        PIN_F.setMargin(new java.awt.Insets(2, 4, 2, 2));
        PIN_F.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                PIN_FFocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                PIN_FFocusLost(evt);
            }
        });
        PIN_F.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                PIN_FMouseClicked(evt);
            }
        });
        panelF.add(PIN_F, new org.netbeans.lib.awtextra.AbsoluteConstraints(490, 1900, 320, 40));

        distF.setFont(new java.awt.Font("Roboto", 0, 24)); // NOI18N
        distF.setForeground(new java.awt.Color(102, 102, 102));
        distF.setText("Dist");
        distF.setBorder(null);
        distF.setMargin(new java.awt.Insets(2, 4, 2, 2));
        distF.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                distFFocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                distFFocusLost(evt);
            }
        });
        distF.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                distFMouseClicked(evt);
            }
        });
        distF.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                distFActionPerformed(evt);
            }
        });
        panelF.add(distF, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 1900, 320, 40));

        dist_underlineF.setForeground(new java.awt.Color(54, 33, 89));
        panelF.add(dist_underlineF, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 1940, 320, 10));

        PIN_underlineF.setForeground(new java.awt.Color(54, 33, 89));
        panelF.add(PIN_underlineF, new org.netbeans.lib.awtextra.AbsoluteConstraints(490, 1940, 320, 10));

        midF2.setForeground(new java.awt.Color(204, 204, 255));
        panelF.add(midF2, new org.netbeans.lib.awtextra.AbsoluteConstraints(260, 1680, 390, 10));

        address_labelF.setFont(new java.awt.Font("Montserrat", 0, 28)); // NOI18N
        address_labelF.setForeground(new java.awt.Color(51, 51, 51));
        address_labelF.setText("Address");
        panelF.add(address_labelF, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 1730, -1, 40));

        contact_underlineF.setForeground(new java.awt.Color(54, 33, 89));
        panelF.add(contact_underlineF, new org.netbeans.lib.awtextra.AbsoluteConstraints(140, 660, 260, 20));

        contact_textfieldF.setFont(new java.awt.Font("Roboto", 0, 24)); // NOI18N
        contact_textfieldF.setForeground(new java.awt.Color(54, 33, 89));
        contact_textfieldF.setBorder(null);
        contact_textfieldF.setMargin(new java.awt.Insets(2, 4, 2, 2));
        panelF.add(contact_textfieldF, new org.netbeans.lib.awtextra.AbsoluteConstraints(140, 620, 260, 40));

        indl_IFSC_F.setFont(new java.awt.Font("Roboto", 0, 24)); // NOI18N
        indl_IFSC_F.setForeground(new java.awt.Color(102, 102, 102));
        indl_IFSC_F.setText("IFSC");
        indl_IFSC_F.setBorder(null);
        indl_IFSC_F.setMargin(new java.awt.Insets(2, 4, 2, 2));
        indl_IFSC_F.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                indl_IFSC_FFocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                indl_IFSC_FFocusLost(evt);
            }
        });
        indl_IFSC_F.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                indl_IFSC_FMouseClicked(evt);
            }
        });
        panelF.add(indl_IFSC_F, new org.netbeans.lib.awtextra.AbsoluteConstraints(490, 1120, 320, 40));

        indl_branchF.setFont(new java.awt.Font("Roboto", 0, 24)); // NOI18N
        indl_branchF.setForeground(new java.awt.Color(102, 102, 102));
        indl_branchF.setText("Branch");
        indl_branchF.setBorder(null);
        indl_branchF.setMargin(new java.awt.Insets(2, 4, 2, 2));
        indl_branchF.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                indl_branchFFocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                indl_branchFFocusLost(evt);
            }
        });
        indl_branchF.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                indl_branchFMouseClicked(evt);
            }
        });
        indl_branchF.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                indl_branchFActionPerformed(evt);
            }
        });
        panelF.add(indl_branchF, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 1120, 320, 40));

        indl_underline14.setForeground(new java.awt.Color(54, 33, 89));
        panelF.add(indl_underline14, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 1160, 320, 10));

        indl_underline15.setForeground(new java.awt.Color(54, 33, 89));
        panelF.add(indl_underline15, new org.netbeans.lib.awtextra.AbsoluteConstraints(490, 1160, 320, 10));

        NOK_detailsF.setFont(new java.awt.Font("Montserrat", 0, 28)); // NOI18N
        NOK_detailsF.setForeground(new java.awt.Color(51, 51, 51));
        NOK_detailsF.setText("Details of Kin");
        panelF.add(NOK_detailsF, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 1280, -1, 40));

        kin_underlineF4.setForeground(new java.awt.Color(54, 33, 89));
        panelF.add(kin_underlineF4, new org.netbeans.lib.awtextra.AbsoluteConstraints(490, 1430, 320, 10));

        kin_PAN_F.setFont(new java.awt.Font("Roboto", 0, 24)); // NOI18N
        kin_PAN_F.setForeground(new java.awt.Color(102, 102, 102));
        kin_PAN_F.setText("PAN");
        kin_PAN_F.setBorder(null);
        kin_PAN_F.setMargin(new java.awt.Insets(2, 4, 2, 2));
        kin_PAN_F.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                kin_PAN_FFocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                kin_PAN_FFocusLost(evt);
            }
        });
        kin_PAN_F.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                kin_PAN_FMouseClicked(evt);
            }
        });
        kin_PAN_F.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                kin_PAN_FActionPerformed(evt);
            }
        });
        panelF.add(kin_PAN_F, new org.netbeans.lib.awtextra.AbsoluteConstraints(490, 1390, 320, 40));

        kin_underlineF5.setForeground(new java.awt.Color(54, 33, 89));
        panelF.add(kin_underlineF5, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 1490, 320, 10));

        kin_aadharF.setFont(new java.awt.Font("Roboto", 0, 24)); // NOI18N
        kin_aadharF.setForeground(new java.awt.Color(102, 102, 102));
        kin_aadharF.setText("Aadhar");
        kin_aadharF.setBorder(null);
        kin_aadharF.setMargin(new java.awt.Insets(2, 4, 2, 2));
        kin_aadharF.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                kin_aadharFFocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                kin_aadharFFocusLost(evt);
            }
        });
        kin_aadharF.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                kin_aadharFMouseClicked(evt);
            }
        });
        kin_aadharF.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                kin_aadharFActionPerformed(evt);
            }
        });
        panelF.add(kin_aadharF, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 1450, 320, 40));

        kin_underlineF6.setForeground(new java.awt.Color(54, 33, 89));
        panelF.add(kin_underlineF6, new org.netbeans.lib.awtextra.AbsoluteConstraints(490, 1490, 320, 10));

        kin_mailF.setFont(new java.awt.Font("Roboto", 0, 24)); // NOI18N
        kin_mailF.setForeground(new java.awt.Color(102, 102, 102));
        kin_mailF.setText("E-Mail");
        kin_mailF.setBorder(null);
        kin_mailF.setMargin(new java.awt.Insets(2, 4, 2, 2));
        kin_mailF.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                kin_mailFFocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                kin_mailFFocusLost(evt);
            }
        });
        kin_mailF.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                kin_mailFMouseClicked(evt);
            }
        });
        panelF.add(kin_mailF, new org.netbeans.lib.awtextra.AbsoluteConstraints(490, 1450, 320, 40));

        kin_bank_nameF.setFont(new java.awt.Font("Roboto", 0, 24)); // NOI18N
        kin_bank_nameF.setForeground(new java.awt.Color(102, 102, 102));
        kin_bank_nameF.setText("Bank Name");
        kin_bank_nameF.setBorder(null);
        kin_bank_nameF.setMargin(new java.awt.Insets(2, 4, 2, 2));
        kin_bank_nameF.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                kin_bank_nameFFocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                kin_bank_nameFFocusLost(evt);
            }
        });
        kin_bank_nameF.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                kin_bank_nameFMouseClicked(evt);
            }
        });
        panelF.add(kin_bank_nameF, new org.netbeans.lib.awtextra.AbsoluteConstraints(490, 1510, 320, 40));

        kin_account_numberF.setFont(new java.awt.Font("Roboto", 0, 24)); // NOI18N
        kin_account_numberF.setForeground(new java.awt.Color(102, 102, 102));
        kin_account_numberF.setText("Account Number");
        kin_account_numberF.setBorder(null);
        kin_account_numberF.setMargin(new java.awt.Insets(2, 4, 2, 2));
        kin_account_numberF.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                kin_account_numberFFocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                kin_account_numberFFocusLost(evt);
            }
        });
        kin_account_numberF.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                kin_account_numberFMouseClicked(evt);
            }
        });
        kin_account_numberF.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                kin_account_numberFActionPerformed(evt);
            }
        });
        panelF.add(kin_account_numberF, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 1510, 320, 40));

        kin_underlineF7.setForeground(new java.awt.Color(54, 33, 89));
        panelF.add(kin_underlineF7, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 1550, 320, 10));

        kin_underlineF8.setForeground(new java.awt.Color(54, 33, 89));
        panelF.add(kin_underlineF8, new org.netbeans.lib.awtextra.AbsoluteConstraints(490, 1550, 320, 10));

        midF1.setForeground(new java.awt.Color(204, 204, 255));
        panelF.add(midF1, new org.netbeans.lib.awtextra.AbsoluteConstraints(260, 1230, 390, 10));

        kin_IFSC_F.setFont(new java.awt.Font("Roboto", 0, 24)); // NOI18N
        kin_IFSC_F.setForeground(new java.awt.Color(102, 102, 102));
        kin_IFSC_F.setText("IFSC");
        kin_IFSC_F.setBorder(null);
        kin_IFSC_F.setMargin(new java.awt.Insets(2, 4, 2, 2));
        kin_IFSC_F.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                kin_IFSC_FFocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                kin_IFSC_FFocusLost(evt);
            }
        });
        kin_IFSC_F.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                kin_IFSC_FMouseClicked(evt);
            }
        });
        panelF.add(kin_IFSC_F, new org.netbeans.lib.awtextra.AbsoluteConstraints(490, 1570, 320, 40));

        kin_branchF.setFont(new java.awt.Font("Roboto", 0, 24)); // NOI18N
        kin_branchF.setForeground(new java.awt.Color(102, 102, 102));
        kin_branchF.setText("Branch");
        kin_branchF.setBorder(null);
        kin_branchF.setMargin(new java.awt.Insets(2, 4, 2, 2));
        kin_branchF.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                kin_branchFFocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                kin_branchFFocusLost(evt);
            }
        });
        kin_branchF.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                kin_branchFMouseClicked(evt);
            }
        });
        kin_branchF.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                kin_branchFActionPerformed(evt);
            }
        });
        panelF.add(kin_branchF, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 1570, 320, 40));

        kin_underlineF9.setForeground(new java.awt.Color(54, 33, 89));
        panelF.add(kin_underlineF9, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 1610, 320, 10));

        kin_underlineF10.setForeground(new java.awt.Color(54, 33, 89));
        panelF.add(kin_underlineF10, new org.netbeans.lib.awtextra.AbsoluteConstraints(490, 1610, 320, 10));

        kin_underlineF1.setForeground(new java.awt.Color(54, 33, 89));
        panelF.add(kin_underlineF1, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 1370, 320, 10));

        kin_NOK_F.setFont(new java.awt.Font("Roboto", 0, 24)); // NOI18N
        kin_NOK_F.setForeground(new java.awt.Color(102, 102, 102));
        kin_NOK_F.setText("NOK");
        kin_NOK_F.setBorder(null);
        kin_NOK_F.setMargin(new java.awt.Insets(2, 4, 2, 2));
        kin_NOK_F.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                kin_NOK_FFocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                kin_NOK_FFocusLost(evt);
            }
        });
        kin_NOK_F.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                kin_NOK_FMouseClicked(evt);
            }
        });
        kin_NOK_F.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                kin_NOK_FActionPerformed(evt);
            }
        });
        panelF.add(kin_NOK_F, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 1330, 320, 40));

        kin_underlineF2.setForeground(new java.awt.Color(54, 33, 89));
        panelF.add(kin_underlineF2, new org.netbeans.lib.awtextra.AbsoluteConstraints(490, 1370, 320, 10));

        kin_mobile_numberF.setFont(new java.awt.Font("Roboto", 0, 24)); // NOI18N
        kin_mobile_numberF.setForeground(new java.awt.Color(102, 102, 102));
        kin_mobile_numberF.setText("Mob No.");
        kin_mobile_numberF.setBorder(null);
        kin_mobile_numberF.setMargin(new java.awt.Insets(2, 4, 2, 2));
        kin_mobile_numberF.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                kin_mobile_numberFFocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                kin_mobile_numberFFocusLost(evt);
            }
        });
        kin_mobile_numberF.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                kin_mobile_numberFMouseClicked(evt);
            }
        });
        kin_mobile_numberF.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                kin_mobile_numberFActionPerformed(evt);
            }
        });
        panelF.add(kin_mobile_numberF, new org.netbeans.lib.awtextra.AbsoluteConstraints(490, 1330, 320, 40));

        kin_underlineF3.setForeground(new java.awt.Color(54, 33, 89));
        panelF.add(kin_underlineF3, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 1430, 320, 10));

        kin_relation_F.setFont(new java.awt.Font("Roboto", 0, 24)); // NOI18N
        kin_relation_F.setForeground(new java.awt.Color(102, 102, 102));
        kin_relation_F.setText("Relation");
        kin_relation_F.setBorder(null);
        kin_relation_F.setMargin(new java.awt.Insets(2, 4, 2, 2));
        kin_relation_F.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                kin_relation_FFocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                kin_relation_FFocusLost(evt);
            }
        });
        kin_relation_F.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                kin_relation_FMouseClicked(evt);
            }
        });
        kin_relation_F.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                kin_relation_FActionPerformed(evt);
            }
        });
        panelF.add(kin_relation_F, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 1390, 320, 40));

        update_buttonF.setMaximumSize(new java.awt.Dimension(95, 25));
        update_buttonF.setMinimumSize(new java.awt.Dimension(95, 25));
        update_buttonF.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                update_buttonFMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                update_buttonFMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                update_buttonFMouseExited(evt);
            }
        });

        update_labelF.setFont(new java.awt.Font("Roboto", 0, 14)); // NOI18N
        update_labelF.setForeground(new java.awt.Color(54, 33, 89));
        update_labelF.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        update_labelF.setText("UPDATE");

        javax.swing.GroupLayout update_buttonFLayout = new javax.swing.GroupLayout(update_buttonF);
        update_buttonF.setLayout(update_buttonFLayout);
        update_buttonFLayout.setHorizontalGroup(
            update_buttonFLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(update_buttonFLayout.createSequentialGroup()
                .addGap(36, 36, 36)
                .addComponent(update_labelF, javax.swing.GroupLayout.PREFERRED_SIZE, 110, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(34, Short.MAX_VALUE))
        );
        update_buttonFLayout.setVerticalGroup(
            update_buttonFLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(update_labelF, javax.swing.GroupLayout.DEFAULT_SIZE, 50, Short.MAX_VALUE)
        );

        panelF.add(update_buttonF, new org.netbeans.lib.awtextra.AbsoluteConstraints(630, 1990, 180, 50));

        Attachment_labelF.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        Attachment_labelF.setText("ATTACHMENT");
        panelF.add(Attachment_labelF, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 140, -1, 20));

        DOA_labelF.setBackground(new java.awt.Color(255, 255, 255));
        DOA_labelF.setFont(new java.awt.Font("Montserrat", 0, 28)); // NOI18N
        DOA_labelF.setForeground(new java.awt.Color(51, 51, 51));
        DOA_labelF.setText("D-O-Attachment");
        panelF.add(DOA_labelF, new org.netbeans.lib.awtextra.AbsoluteConstraints(610, 70, -1, 40));

        update_labelF1.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        update_labelF1.setText("Changing For");
        panelF.add(update_labelF1, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 50, -1, 20));

        DOA.setFont(new java.awt.Font("Roboto Bk", 0, 24)); // NOI18N
        DOA.setForeground(new java.awt.Color(54, 33, 89));
        DOA.setText("> XXXXXXX");
        panelF.add(DOA, new org.netbeans.lib.awtextra.AbsoluteConstraints(610, 100, -1, 40));

        service_number.setFont(new java.awt.Font("Roboto Bk", 0, 24)); // NOI18N
        service_number.setForeground(new java.awt.Color(54, 33, 89));
        service_number.setText("> XXXXXXX");
        panelF.add(service_number, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 100, -1, 40));

        DOJ.setFont(new java.awt.Font("Roboto Bk", 0, 24)); // NOI18N
        DOJ.setForeground(new java.awt.Color(54, 33, 89));
        DOJ.setText("> XXXXXXX");
        panelF.add(DOJ, new org.netbeans.lib.awtextra.AbsoluteConstraints(390, 100, -1, 40));

        midF3.setForeground(new java.awt.Color(204, 204, 255));
        panelF.add(midF3, new org.netbeans.lib.awtextra.AbsoluteConstraints(260, 840, 390, 10));

        midF4.setForeground(new java.awt.Color(204, 204, 255));
        midF4.setOrientation(javax.swing.SwingConstants.VERTICAL);
        panelF.add(midF4, new org.netbeans.lib.awtextra.AbsoluteConstraints(335, 85, 20, 40));

        DOJ_labelF1.setBackground(new java.awt.Color(255, 255, 255));
        DOJ_labelF1.setFont(new java.awt.Font("Montserrat", 0, 28)); // NOI18N
        DOJ_labelF1.setForeground(new java.awt.Color(51, 51, 51));
        DOJ_labelF1.setText("Date of Joining");
        panelF.add(DOJ_labelF1, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 690, -1, 40));

        DOJ_date_comboboxF.setFont(new java.awt.Font("Roboto", 0, 18)); // NOI18N
        DOJ_date_comboboxF.setForeground(new java.awt.Color(44, 62, 80));
        DOJ_date_comboboxF.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "DD", "01", "02", "03", "04", "05", "06", "07", "08", "09", "10", "11", "12", "13", "14", "15", "16", "17", "18", "19", "20", "21", "22", "23", "24", "25", "26", "27", "28", "29", "30", "31" }));
        DOJ_date_comboboxF.setBorder(null);
        DOJ_date_comboboxF.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                DOJ_date_comboboxFActionPerformed(evt);
            }
        });
        panelF.add(DOJ_date_comboboxF, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 730, 100, 40));

        DOJ_month_comboboxF.setFont(new java.awt.Font("Roboto", 0, 18)); // NOI18N
        DOJ_month_comboboxF.setForeground(new java.awt.Color(44, 62, 80));
        DOJ_month_comboboxF.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "MM", "01", "02", "03", "04", "05", "06", "07", "08", "09", "10", "11", "12" }));
        DOJ_month_comboboxF.setBorder(null);
        panelF.add(DOJ_month_comboboxF, new org.netbeans.lib.awtextra.AbsoluteConstraints(190, 730, 100, 40));

        DOJ_year_comboboxF.setFont(new java.awt.Font("Roboto", 0, 18)); // NOI18N
        DOJ_year_comboboxF.setForeground(new java.awt.Color(44, 62, 80));
        DOJ_year_comboboxF.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "YYYY", "1950", "1951", "1952", "1953", "1954", "1955", "1956", "1957", "1958", "1959", "1960", "1961", "1962", "1963", "1964", "1965", "1966", "1967", "1968", "1969", "1970", "1971", "1972", "1973", "1974", "1975", "1976", "1977", "1978", "1979", "1980", "1981", "1982", "1983", "1984", "1985", "1986", "1987", "1988", "1989", "1990", "1991", "1992", "1993", "1994", "1995", "1996", "1997", "1998", "1999", "2000", "2001", "2002", "2003", "2004", "2005", "2006", "2007", "2008", "2009", "2010", "2011", "2012", "2013", "2014", "2015", "2016", "2017", "2018", "2019", "2020", "2021", "2022", "2023", "2024", "2025", "2026", "2027", "2028", "2029", "2030", "2031", "2032", "2033", "2034", "2035", "2036", "2037", "2038", "2039", "2040", "2041", "2042", "2043", "2044", "2045", "2046", "2047", "2048", "2049", "2050", "2051", "2052", "2053", "2054", "2055", "2056", "2057", "2058", "2059", "2060", "2061", "2062", "2063", "2064", "2065", "2066", "2067", "2068", "2069", "2070", "2071", "2072", "2073", "2074", "2075", "2076", "2077", "2078", "2079", "2080", "2081", "2082", "2083", "2084", "2085", "2086", "2087", "2088", "2089", "2090", "2091", "2092", "2093", "2094", "2095", "2096", "2097", "2098", "2099", "2100", " " }));
        DOJ_year_comboboxF.setBorder(null);
        panelF.add(DOJ_year_comboboxF, new org.netbeans.lib.awtextra.AbsoluteConstraints(300, 730, 100, 40));

        DOA_labelF1.setBackground(new java.awt.Color(255, 255, 255));
        DOA_labelF1.setFont(new java.awt.Font("Montserrat", 0, 28)); // NOI18N
        DOA_labelF1.setForeground(new java.awt.Color(51, 51, 51));
        DOA_labelF1.setText("Date of Attachment");
        panelF.add(DOA_labelF1, new org.netbeans.lib.awtextra.AbsoluteConstraints(490, 690, -1, 40));

        DOA_date_comboboxF.setFont(new java.awt.Font("Roboto", 0, 18)); // NOI18N
        DOA_date_comboboxF.setForeground(new java.awt.Color(44, 62, 80));
        DOA_date_comboboxF.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "DD", "01", "02", "03", "04", "05", "06", "07", "08", "09", "10", "11", "12", "13", "14", "15", "16", "17", "18", "19", "20", "21", "22", "23", "24", "25", "26", "27", "28", "29", "30", "31" }));
        DOA_date_comboboxF.setBorder(null);
        DOA_date_comboboxF.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                DOA_date_comboboxFActionPerformed(evt);
            }
        });
        panelF.add(DOA_date_comboboxF, new org.netbeans.lib.awtextra.AbsoluteConstraints(490, 730, 100, 40));

        DOA_month_comboboxF.setFont(new java.awt.Font("Roboto", 0, 18)); // NOI18N
        DOA_month_comboboxF.setForeground(new java.awt.Color(44, 62, 80));
        DOA_month_comboboxF.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "MM", "01", "02", "03", "04", "05", "06", "07", "08", "09", "10", "11", "12" }));
        DOA_month_comboboxF.setBorder(null);
        panelF.add(DOA_month_comboboxF, new org.netbeans.lib.awtextra.AbsoluteConstraints(600, 730, 100, 40));

        DOA_year_comboboxF.setFont(new java.awt.Font("Roboto", 0, 18)); // NOI18N
        DOA_year_comboboxF.setForeground(new java.awt.Color(44, 62, 80));
        DOA_year_comboboxF.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "YYYY", "1950", "1951", "1952", "1953", "1954", "1955", "1956", "1957", "1958", "1959", "1960", "1961", "1962", "1963", "1964", "1965", "1966", "1967", "1968", "1969", "1970", "1971", "1972", "1973", "1974", "1975", "1976", "1977", "1978", "1979", "1980", "1981", "1982", "1983", "1984", "1985", "1986", "1987", "1988", "1989", "1990", "1991", "1992", "1993", "1994", "1995", "1996", "1997", "1998", "1999", "2000", "2001", "2002", "2003", "2004", "2005", "2006", "2007", "2008", "2009", "2010", "2011", "2012", "2013", "2014", "2015", "2016", "2017", "2018", "2019", "2020", "2021", "2022", "2023", "2024", "2025", "2026", "2027", "2028", "2029", "2030", "2031", "2032", "2033", "2034", "2035", "2036", "2037", "2038", "2039", "2040", "2041", "2042", "2043", "2044", "2045", "2046", "2047", "2048", "2049", "2050", "2051", "2052", "2053", "2054", "2055", "2056", "2057", "2058", "2059", "2060", "2061", "2062", "2063", "2064", "2065", "2066", "2067", "2068", "2069", "2070", "2071", "2072", "2073", "2074", "2075", "2076", "2077", "2078", "2079", "2080", "2081", "2082", "2083", "2084", "2085", "2086", "2087", "2088", "2089", "2090", "2091", "2092", "2093", "2094", "2095", "2096", "2097", "2098", "2099", "2100", " " }));
        DOA_year_comboboxF.setBorder(null);
        panelF.add(DOA_year_comboboxF, new org.netbeans.lib.awtextra.AbsoluteConstraints(710, 730, 100, 40));

        ScrollPaneF.setViewportView(panelF);

        bg.add(ScrollPaneF, new org.netbeans.lib.awtextra.AbsoluteConstraints(340, 193, 910, 475));

        fullscreen_buttonD.setForeground(new java.awt.Color(240, 240, 240));
        fullscreen_buttonD.setMaximumSize(new java.awt.Dimension(200, 200));
        fullscreen_buttonD.setMinimumSize(new java.awt.Dimension(200, 200));
        fullscreen_buttonD.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                fullscreen_buttonDMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                fullscreen_buttonDMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                fullscreen_buttonDMouseExited(evt);
            }
        });
        fullscreen_buttonD.setLayout(null);

        fullscreen_labelD.setBackground(new java.awt.Color(54, 33, 89));
        fullscreen_labelD.setFont(new java.awt.Font("Roboto Bk", 0, 14)); // NOI18N
        fullscreen_labelD.setForeground(new java.awt.Color(54, 33, 89));
        fullscreen_labelD.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        fullscreen_labelD.setText("FULLSCREEN");
        fullscreen_labelD.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                fullscreen_labelDMouseClicked(evt);
            }
        });
        fullscreen_buttonD.add(fullscreen_labelD);
        fullscreen_labelD.setBounds(30, 0, 120, 50);

        bg.add(fullscreen_buttonD, new org.netbeans.lib.awtextra.AbsoluteConstraints(1070, 120, 180, 50));

        fullscreen_buttonE.setForeground(new java.awt.Color(240, 240, 240));
        fullscreen_buttonE.setMaximumSize(new java.awt.Dimension(200, 200));
        fullscreen_buttonE.setMinimumSize(new java.awt.Dimension(200, 200));
        fullscreen_buttonE.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                fullscreen_buttonEMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                fullscreen_buttonEMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                fullscreen_buttonEMouseExited(evt);
            }
        });
        fullscreen_buttonE.setLayout(null);

        fullscreen_labelE.setBackground(new java.awt.Color(54, 33, 89));
        fullscreen_labelE.setFont(new java.awt.Font("Roboto Bk", 0, 14)); // NOI18N
        fullscreen_labelE.setForeground(new java.awt.Color(54, 33, 89));
        fullscreen_labelE.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        fullscreen_labelE.setText("FULLSCREEN");
        fullscreen_buttonE.add(fullscreen_labelE);
        fullscreen_labelE.setBounds(30, 0, 120, 50);

        bg.add(fullscreen_buttonE, new org.netbeans.lib.awtextra.AbsoluteConstraints(1070, 120, 180, 50));

        refresh_buttonD.setForeground(new java.awt.Color(240, 240, 240));
        refresh_buttonD.setMaximumSize(new java.awt.Dimension(200, 200));
        refresh_buttonD.setMinimumSize(new java.awt.Dimension(200, 200));
        refresh_buttonD.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                refresh_buttonDMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                refresh_buttonDMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                refresh_buttonDMouseExited(evt);
            }
        });
        refresh_buttonD.setLayout(null);

        refresh_labelD.setBackground(new java.awt.Color(54, 33, 89));
        refresh_labelD.setFont(new java.awt.Font("Roboto Bk", 0, 14)); // NOI18N
        refresh_labelD.setForeground(new java.awt.Color(54, 33, 89));
        refresh_labelD.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        refresh_labelD.setText("REFRESH");
        refresh_buttonD.add(refresh_labelD);
        refresh_labelD.setBounds(50, 0, 80, 50);

        bg.add(refresh_buttonD, new org.netbeans.lib.awtextra.AbsoluteConstraints(870, 120, 180, 50));

        refresh_buttonE.setForeground(new java.awt.Color(240, 240, 240));
        refresh_buttonE.setMaximumSize(new java.awt.Dimension(200, 200));
        refresh_buttonE.setMinimumSize(new java.awt.Dimension(200, 200));
        refresh_buttonE.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                refresh_buttonEMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                refresh_buttonEMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                refresh_buttonEMouseExited(evt);
            }
        });
        refresh_buttonE.setLayout(null);

        refresh_labelE.setBackground(new java.awt.Color(54, 33, 89));
        refresh_labelE.setFont(new java.awt.Font("Roboto Bk", 0, 14)); // NOI18N
        refresh_labelE.setForeground(new java.awt.Color(54, 33, 89));
        refresh_labelE.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        refresh_labelE.setText("REFRESH");
        refresh_buttonE.add(refresh_labelE);
        refresh_labelE.setBounds(50, 0, 80, 50);

        bg.add(refresh_buttonE, new org.netbeans.lib.awtextra.AbsoluteConstraints(870, 120, 180, 50));

        remove_buttonC.setForeground(new java.awt.Color(240, 240, 240));
        remove_buttonC.setMaximumSize(new java.awt.Dimension(95, 25));
        remove_buttonC.setMinimumSize(new java.awt.Dimension(95, 25));
        remove_buttonC.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                remove_buttonCMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                remove_buttonCMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                remove_buttonCMouseExited(evt);
            }
        });
        remove_buttonC.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        remove_labelC.setFont(new java.awt.Font("Roboto Bk", 0, 14)); // NOI18N
        remove_labelC.setForeground(new java.awt.Color(54, 33, 89));
        remove_labelC.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        remove_labelC.setText("REMOVE");
        remove_buttonC.add(remove_labelC, new org.netbeans.lib.awtextra.AbsoluteConstraints(52, 13, 71, 24));

        bg.add(remove_buttonC, new org.netbeans.lib.awtextra.AbsoluteConstraints(870, 120, 180, 50));

        print_buttonC.setForeground(new java.awt.Color(240, 240, 240));
        print_buttonC.setMaximumSize(new java.awt.Dimension(95, 25));
        print_buttonC.setMinimumSize(new java.awt.Dimension(95, 25));
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
        print_buttonC.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        print_labelC.setFont(new java.awt.Font("Roboto Bk", 0, 14)); // NOI18N
        print_labelC.setForeground(new java.awt.Color(54, 33, 89));
        print_labelC.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        print_labelC.setText("PRINT");
        print_buttonC.add(print_labelC, new org.netbeans.lib.awtextra.AbsoluteConstraints(52, 13, 71, 24));

        bg.add(print_buttonC, new org.netbeans.lib.awtextra.AbsoluteConstraints(1070, 120, 180, 50));

        print_buttonD.setForeground(new java.awt.Color(240, 240, 240));
        print_buttonD.setMaximumSize(new java.awt.Dimension(200, 200));
        print_buttonD.setMinimumSize(new java.awt.Dimension(200, 200));
        print_buttonD.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                print_buttonDMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                print_buttonDMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                print_buttonDMouseExited(evt);
            }
        });
        print_buttonD.setLayout(null);

        print_labelD.setBackground(new java.awt.Color(54, 33, 89));
        print_labelD.setFont(new java.awt.Font("Roboto Bk", 0, 14)); // NOI18N
        print_labelD.setForeground(new java.awt.Color(54, 33, 89));
        print_labelD.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        print_labelD.setText("PRINT");
        print_buttonD.add(print_labelD);
        print_labelD.setBounds(50, 0, 80, 50);

        bg.add(print_buttonD, new org.netbeans.lib.awtextra.AbsoluteConstraints(670, 120, 180, 50));

        print_buttonE.setForeground(new java.awt.Color(240, 240, 240));
        print_buttonE.setMaximumSize(new java.awt.Dimension(200, 200));
        print_buttonE.setMinimumSize(new java.awt.Dimension(200, 200));
        print_buttonE.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                print_buttonEMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                print_buttonEMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                print_buttonEMouseExited(evt);
            }
        });
        print_buttonE.setLayout(null);

        print_labelE.setBackground(new java.awt.Color(54, 33, 89));
        print_labelE.setFont(new java.awt.Font("Roboto Bk", 0, 14)); // NOI18N
        print_labelE.setForeground(new java.awt.Color(54, 33, 89));
        print_labelE.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        print_labelE.setText("PRINT");
        print_buttonE.add(print_labelE);
        print_labelE.setBounds(50, 0, 80, 50);

        bg.add(print_buttonE, new org.netbeans.lib.awtextra.AbsoluteConstraints(670, 120, 180, 50));

        edit_buttonG.setForeground(new java.awt.Color(240, 240, 240));
        edit_buttonG.setMaximumSize(new java.awt.Dimension(200, 200));
        edit_buttonG.setMinimumSize(new java.awt.Dimension(200, 200));
        edit_buttonG.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                edit_buttonGMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                edit_buttonGMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                edit_buttonGMouseExited(evt);
            }
        });
        edit_buttonG.setLayout(null);

        edit_labelG.setBackground(new java.awt.Color(54, 33, 89));
        edit_labelG.setFont(new java.awt.Font("Roboto Bk", 0, 14)); // NOI18N
        edit_labelG.setForeground(new java.awt.Color(54, 33, 89));
        edit_labelG.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        edit_labelG.setText("EDIT");
        edit_buttonG.add(edit_labelG);
        edit_labelG.setBounds(50, 0, 80, 50);

        bg.add(edit_buttonG, new org.netbeans.lib.awtextra.AbsoluteConstraints(1070, 120, 180, 50));

        getContentPane().add(bg, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 1280, 720));

        fullscreen_panelD.setBackground(new java.awt.Color(255, 255, 255));
        fullscreen_panelD.setMaximumSize(new java.awt.Dimension(40000, 720));
        fullscreen_panelD.setMinimumSize(new java.awt.Dimension(40000, 720));
        fullscreen_panelD.setPreferredSize(new java.awt.Dimension(1280, 720));
        fullscreen_panelD.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        exit_fullscreen_buttonD.setForeground(new java.awt.Color(240, 240, 240));
        exit_fullscreen_buttonD.setMaximumSize(new java.awt.Dimension(200, 200));
        exit_fullscreen_buttonD.setMinimumSize(new java.awt.Dimension(200, 200));
        exit_fullscreen_buttonD.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                exit_fullscreen_buttonDMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                exit_fullscreen_buttonDMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                exit_fullscreen_buttonDMouseExited(evt);
            }
        });
        exit_fullscreen_buttonD.setLayout(null);

        exit_fullscreen_labelD.setBackground(new java.awt.Color(54, 33, 89));
        exit_fullscreen_labelD.setFont(new java.awt.Font("Roboto Bk", 0, 14)); // NOI18N
        exit_fullscreen_labelD.setForeground(new java.awt.Color(54, 33, 89));
        exit_fullscreen_labelD.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        exit_fullscreen_labelD.setText("GO BACK");
        exit_fullscreen_buttonD.add(exit_fullscreen_labelD);
        exit_fullscreen_labelD.setBounds(30, 0, 120, 50);

        fullscreen_panelD.add(exit_fullscreen_buttonD, new org.netbeans.lib.awtextra.AbsoluteConstraints(1060, 640, 180, 50));

        fullscreen_ScrollPaneD.setBackground(new java.awt.Color(255, 255, 255));
        fullscreen_ScrollPaneD.setToolTipText("");
        fullscreen_ScrollPaneD.setVerticalScrollBarPolicy(javax.swing.ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);

        fullscreen_tableD.setFont(new java.awt.Font("Roboto", 0, 18)); // NOI18N
        fullscreen_tableD.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "S.no", "Service Num", "Name", "DOB", "Rank", "Company", "Contact No", "Address", "Date of Joining", "Medical Category", "Armed Services", "Unit", "Trade", "Location"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, false, false, false, false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        fullscreen_tableD.setAutoResizeMode(javax.swing.JTable.AUTO_RESIZE_OFF);
        fullscreen_tableD.setFocusable(false);
        fullscreen_tableD.setGridColor(new java.awt.Color(255, 255, 255));
        fullscreen_tableD.setMaximumSize(new java.awt.Dimension(40000, 40000));
        fullscreen_tableD.setMinimumSize(new java.awt.Dimension(1000, 1000));
        fullscreen_tableD.setRowHeight(30);
        fullscreen_tableD.setSelectionBackground(new java.awt.Color(54, 33, 89));
        fullscreen_ScrollPaneD.setViewportView(fullscreen_tableD);
        if (fullscreen_tableD.getColumnModel().getColumnCount() > 0) {
            fullscreen_tableD.getColumnModel().getColumn(0).setMinWidth(50);
            fullscreen_tableD.getColumnModel().getColumn(0).setPreferredWidth(50);
            fullscreen_tableD.getColumnModel().getColumn(0).setMaxWidth(50);
            fullscreen_tableD.getColumnModel().getColumn(1).setMinWidth(150);
            fullscreen_tableD.getColumnModel().getColumn(1).setPreferredWidth(150);
            fullscreen_tableD.getColumnModel().getColumn(1).setMaxWidth(150);
            fullscreen_tableD.getColumnModel().getColumn(2).setMinWidth(150);
            fullscreen_tableD.getColumnModel().getColumn(2).setPreferredWidth(150);
            fullscreen_tableD.getColumnModel().getColumn(2).setMaxWidth(150);
            fullscreen_tableD.getColumnModel().getColumn(3).setMinWidth(150);
            fullscreen_tableD.getColumnModel().getColumn(3).setPreferredWidth(150);
            fullscreen_tableD.getColumnModel().getColumn(3).setMaxWidth(150);
            fullscreen_tableD.getColumnModel().getColumn(4).setMinWidth(150);
            fullscreen_tableD.getColumnModel().getColumn(4).setPreferredWidth(150);
            fullscreen_tableD.getColumnModel().getColumn(4).setMaxWidth(150);
            fullscreen_tableD.getColumnModel().getColumn(5).setMinWidth(150);
            fullscreen_tableD.getColumnModel().getColumn(5).setPreferredWidth(150);
            fullscreen_tableD.getColumnModel().getColumn(5).setMaxWidth(150);
            fullscreen_tableD.getColumnModel().getColumn(6).setMinWidth(150);
            fullscreen_tableD.getColumnModel().getColumn(6).setPreferredWidth(150);
            fullscreen_tableD.getColumnModel().getColumn(6).setMaxWidth(150);
            fullscreen_tableD.getColumnModel().getColumn(7).setMinWidth(150);
            fullscreen_tableD.getColumnModel().getColumn(7).setPreferredWidth(150);
            fullscreen_tableD.getColumnModel().getColumn(7).setMaxWidth(150);
            fullscreen_tableD.getColumnModel().getColumn(8).setMinWidth(150);
            fullscreen_tableD.getColumnModel().getColumn(8).setPreferredWidth(150);
            fullscreen_tableD.getColumnModel().getColumn(8).setMaxWidth(150);
            fullscreen_tableD.getColumnModel().getColumn(9).setMinWidth(150);
            fullscreen_tableD.getColumnModel().getColumn(9).setPreferredWidth(150);
            fullscreen_tableD.getColumnModel().getColumn(9).setMaxWidth(150);
            fullscreen_tableD.getColumnModel().getColumn(10).setMinWidth(150);
            fullscreen_tableD.getColumnModel().getColumn(10).setPreferredWidth(150);
            fullscreen_tableD.getColumnModel().getColumn(10).setMaxWidth(150);
            fullscreen_tableD.getColumnModel().getColumn(11).setMinWidth(150);
            fullscreen_tableD.getColumnModel().getColumn(11).setPreferredWidth(150);
            fullscreen_tableD.getColumnModel().getColumn(11).setMaxWidth(150);
            fullscreen_tableD.getColumnModel().getColumn(12).setMinWidth(150);
            fullscreen_tableD.getColumnModel().getColumn(12).setPreferredWidth(150);
            fullscreen_tableD.getColumnModel().getColumn(12).setMaxWidth(150);
            fullscreen_tableD.getColumnModel().getColumn(13).setMinWidth(150);
            fullscreen_tableD.getColumnModel().getColumn(13).setPreferredWidth(150);
            fullscreen_tableD.getColumnModel().getColumn(13).setMaxWidth(150);
        }

        fullscreen_panelD.add(fullscreen_ScrollPaneD, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 40, 1200, 580));

        getContentPane().add(fullscreen_panelD, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 1280, 720));

        fullscreen_panelE.setBackground(new java.awt.Color(255, 255, 255));
        fullscreen_panelE.setMaximumSize(new java.awt.Dimension(1280, 720));
        fullscreen_panelE.setMinimumSize(new java.awt.Dimension(1280, 720));
        fullscreen_panelE.setPreferredSize(new java.awt.Dimension(1280, 720));
        fullscreen_panelE.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        exit_fullscreen_buttonE.setForeground(new java.awt.Color(240, 240, 240));
        exit_fullscreen_buttonE.setMaximumSize(new java.awt.Dimension(200, 200));
        exit_fullscreen_buttonE.setMinimumSize(new java.awt.Dimension(200, 200));
        exit_fullscreen_buttonE.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                exit_fullscreen_buttonEMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                exit_fullscreen_buttonEMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                exit_fullscreen_buttonEMouseExited(evt);
            }
        });
        exit_fullscreen_buttonE.setLayout(null);

        exit_fullscreen_labelE.setBackground(new java.awt.Color(54, 33, 89));
        exit_fullscreen_labelE.setFont(new java.awt.Font("Roboto Bk", 0, 14)); // NOI18N
        exit_fullscreen_labelE.setForeground(new java.awt.Color(54, 33, 89));
        exit_fullscreen_labelE.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        exit_fullscreen_labelE.setText("GO BACK");
        exit_fullscreen_buttonE.add(exit_fullscreen_labelE);
        exit_fullscreen_labelE.setBounds(30, 0, 120, 50);

        fullscreen_panelE.add(exit_fullscreen_buttonE, new org.netbeans.lib.awtextra.AbsoluteConstraints(1060, 640, 180, 50));

        fullscreen_ScrollPaneE.setBackground(new java.awt.Color(255, 255, 255));
        fullscreen_ScrollPaneE.setHorizontalScrollBarPolicy(javax.swing.ScrollPaneConstants.HORIZONTAL_SCROLLBAR_ALWAYS);
        fullscreen_ScrollPaneE.setToolTipText("");
        fullscreen_ScrollPaneE.setVerticalScrollBarPolicy(javax.swing.ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);

        fullscreen_tableE.setFont(new java.awt.Font("Roboto", 0, 18)); // NOI18N
        fullscreen_tableE.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "S.no", "Service Num", "Name", "DOB", "Rank", "Company", "Contact No", "Address", "Date of Joining", "Date of Attachment", "Medical Category", "Location", "Purpose"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, false, false, false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        fullscreen_tableE.setAutoResizeMode(javax.swing.JTable.AUTO_RESIZE_OFF);
        fullscreen_tableE.setFocusable(false);
        fullscreen_tableE.setGridColor(new java.awt.Color(255, 255, 255));
        fullscreen_tableE.setMaximumSize(new java.awt.Dimension(40000, 40000));
        fullscreen_tableE.setMinimumSize(new java.awt.Dimension(1000, 1000));
        fullscreen_tableE.setRowHeight(30);
        fullscreen_tableE.setSelectionBackground(new java.awt.Color(54, 33, 89));
        fullscreen_ScrollPaneE.setViewportView(fullscreen_tableE);
        if (fullscreen_tableE.getColumnModel().getColumnCount() > 0) {
            fullscreen_tableE.getColumnModel().getColumn(0).setMinWidth(50);
            fullscreen_tableE.getColumnModel().getColumn(0).setPreferredWidth(50);
            fullscreen_tableE.getColumnModel().getColumn(0).setMaxWidth(50);
            fullscreen_tableE.getColumnModel().getColumn(1).setMinWidth(150);
            fullscreen_tableE.getColumnModel().getColumn(1).setPreferredWidth(150);
            fullscreen_tableE.getColumnModel().getColumn(1).setMaxWidth(150);
            fullscreen_tableE.getColumnModel().getColumn(2).setMinWidth(150);
            fullscreen_tableE.getColumnModel().getColumn(2).setPreferredWidth(150);
            fullscreen_tableE.getColumnModel().getColumn(2).setMaxWidth(150);
            fullscreen_tableE.getColumnModel().getColumn(3).setMinWidth(150);
            fullscreen_tableE.getColumnModel().getColumn(3).setPreferredWidth(150);
            fullscreen_tableE.getColumnModel().getColumn(3).setMaxWidth(150);
            fullscreen_tableE.getColumnModel().getColumn(4).setMinWidth(150);
            fullscreen_tableE.getColumnModel().getColumn(4).setPreferredWidth(150);
            fullscreen_tableE.getColumnModel().getColumn(4).setMaxWidth(150);
            fullscreen_tableE.getColumnModel().getColumn(5).setMinWidth(150);
            fullscreen_tableE.getColumnModel().getColumn(5).setPreferredWidth(150);
            fullscreen_tableE.getColumnModel().getColumn(5).setMaxWidth(150);
            fullscreen_tableE.getColumnModel().getColumn(6).setMinWidth(150);
            fullscreen_tableE.getColumnModel().getColumn(6).setPreferredWidth(150);
            fullscreen_tableE.getColumnModel().getColumn(6).setMaxWidth(150);
            fullscreen_tableE.getColumnModel().getColumn(7).setMinWidth(150);
            fullscreen_tableE.getColumnModel().getColumn(7).setPreferredWidth(150);
            fullscreen_tableE.getColumnModel().getColumn(7).setMaxWidth(150);
            fullscreen_tableE.getColumnModel().getColumn(8).setMinWidth(150);
            fullscreen_tableE.getColumnModel().getColumn(8).setPreferredWidth(150);
            fullscreen_tableE.getColumnModel().getColumn(8).setMaxWidth(150);
            fullscreen_tableE.getColumnModel().getColumn(9).setMinWidth(150);
            fullscreen_tableE.getColumnModel().getColumn(9).setPreferredWidth(150);
            fullscreen_tableE.getColumnModel().getColumn(9).setMaxWidth(150);
            fullscreen_tableE.getColumnModel().getColumn(10).setMinWidth(150);
            fullscreen_tableE.getColumnModel().getColumn(10).setPreferredWidth(150);
            fullscreen_tableE.getColumnModel().getColumn(10).setMaxWidth(150);
            fullscreen_tableE.getColumnModel().getColumn(11).setMinWidth(150);
            fullscreen_tableE.getColumnModel().getColumn(11).setPreferredWidth(150);
            fullscreen_tableE.getColumnModel().getColumn(11).setMaxWidth(150);
            fullscreen_tableE.getColumnModel().getColumn(12).setMinWidth(150);
            fullscreen_tableE.getColumnModel().getColumn(12).setPreferredWidth(150);
            fullscreen_tableE.getColumnModel().getColumn(12).setMaxWidth(150);
        }

        fullscreen_panelE.add(fullscreen_ScrollPaneE, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 40, 1200, 580));

        getContentPane().add(fullscreen_panelE, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 1280, 720));

        pack();
    }// </editor-fold>//GEN-END:initComponents
    private void move(String str,String sno)
    {
        try {
            path_file paths=new path_file();
            Path temp = Files.copy(Paths.get(str),Paths.get(paths.images +"\\"+sno+".JPG"), StandardCopyOption.REPLACE_EXISTING);
            if(temp !=null)
                System.out.println("File renamed and moved successfully");
            else
                System.out.println("Failed to move the file");
        }
        catch (Exception e) {
            JOptionPane.showMessageDialog(null, "An unexpected error occurred!");
        }
    }
    // >>>called in insert/add buttons
    //----------------------------------FOR PICKING & MOVING IMAGES -end

    
        //--------------------------------------------------------------------------
    //--------------------------------------------------------------------------Printing using book class -start
     
    class Page1 implements Printable  
    {    
            public int print(Graphics graphics, PageFormat pageFormat, int pageIndex) throws PrinterException {
                
                // Check If No Printable Content
                if (pageIndex >= 2) return Printable.NO_SUCH_PAGE;
                Graphics2D graphics2D = (Graphics2D)graphics;

                graphics2D.setClip(0, 0, 690, 700); //shapes the printing rectangle in respect to translate
                graphics2D.translate(10, 0); //marks the top of the page
                
                graphics2D.scale(0.68,0.7);
                panelC.paint(graphics2D);
                
                // return if page exists
                return Printable.PAGE_EXISTS;
            }
    }    
    class Page2 implements Printable  
    {    
            public int print(Graphics graphics, PageFormat pageFormat, int pageIndex) throws PrinterException {
                Graphics2D graphics2D = (Graphics2D)graphics;

                graphics2D.setClip(0, 25, 690, 700); //shapes the printing rectangle in respect to translate
                graphics2D.translate(10, -675); //marks the top of the page
                
                graphics2D.scale(0.68,0.7);
                panelC.paint(graphics2D);
                
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

    //--------------------------------------------------------------------------Printing using book class -end
    //--------------------------------------------------------------------------
    
    public void scaleImage(){
        String sno=service_number_textfieldC.getText();
        if(sno==null ||sno.trim().isEmpty()){
            JOptionPane.showMessageDialog(null,"Enter Army Number");
        }
        else{
            path_file paths=new path_file();
        
            ImageIcon icon=new ImageIcon(paths.images +"\\"+sno+".jPG");
            Image img=icon.getImage();
            Image imgScale=img.getScaledInstance(photo_labelC.getWidth(), photo_labelC.getHeight(), Image.SCALE_SMOOTH);
            ImageIcon scaledIcon =new ImageIcon(imgScale);
            photo_labelC.setIcon(scaledIcon);
        }
    }
    // >>>called in init()
    //----------------------------------FOR RESIZING IMAGE TO FIT PHOTO LABEL -end
    
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
            new Parade_Module().setVisible(true);
            this.setVisible(false);
            try{
            con.close();
            }catch(Exception e){
                System.out.println(e);
            }
            back_button=false;
        }
    }//GEN-LAST:event_go_back_labelMouseClicked

    private void menuAMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_menuAMouseClicked
        menuA.setBackground(new java.awt.Color(85,65,118));
        menuB.setBackground(new java.awt.Color(64,43,100));
        menuC.setBackground(new java.awt.Color(64,43,100));
        menuD.setBackground(new java.awt.Color(64,43,100));
        menuE.setBackground(new java.awt.Color(64,43,100));
        
        print_buttonC.setVisible(false);
        print_buttonD.setVisible(false);
        print_buttonE.setVisible(false);
        refresh_buttonD.setVisible(false);
        refresh_buttonE.setVisible(false);
        fullscreen_buttonD.setVisible(false);
        fullscreen_buttonE.setVisible(false);
        remove_buttonC.setVisible(false);

        fullscreen_panelD.setVisible(false);
        fullscreen_panelE.setVisible(false);
        ScrollPaneA.setVisible(true);
        ScrollPaneB.setVisible(false);
        ScrollPaneC.setVisible(false);
        panelD.setVisible(false);
        panelE.setVisible(false);
        
        edit_buttonG.setVisible(false);
        menuF.setBackground(new java.awt.Color(64,43,100));
        ScrollPaneF.setVisible(false);
        panelG.setVisible(false);
    }//GEN-LAST:event_menuAMouseClicked

    private void menuBMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_menuBMouseClicked
        menuB.setBackground(new java.awt.Color(85,65,118));
        menuA.setBackground(new java.awt.Color(64,43,100));
        menuC.setBackground(new java.awt.Color(64,43,100));
        menuD.setBackground(new java.awt.Color(64,43,100));
        menuE.setBackground(new java.awt.Color(64,43,100));
        
        print_buttonC.setVisible(false);
        print_buttonD.setVisible(false);
        print_buttonE.setVisible(false);
        refresh_buttonD.setVisible(false);
        refresh_buttonE.setVisible(false);
        fullscreen_buttonD.setVisible(false);
        fullscreen_buttonE.setVisible(false);
        remove_buttonC.setVisible(false);

        fullscreen_panelD.setVisible(false);
        fullscreen_panelE.setVisible(false);
        ScrollPaneA.setVisible(false);
        ScrollPaneB.setVisible(true);
        ScrollPaneC.setVisible(false);
        panelD.setVisible(false);
        panelE.setVisible(false);
        
        edit_buttonG.setVisible(false);
        menuF.setBackground(new java.awt.Color(64,43,100));
        ScrollPaneF.setVisible(false);
        panelG.setVisible(false);
    }//GEN-LAST:event_menuBMouseClicked

    private void menuCMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_menuCMouseClicked
        if(attachment_labelC.getText().equalsIgnoreCase("Attachment")){
            attachment_labelC.setText("");
        }
        menuC.setBackground(new java.awt.Color(85,65,118));
        menuB.setBackground(new java.awt.Color(64,43,100));
        menuA.setBackground(new java.awt.Color(64,43,100));
        menuD.setBackground(new java.awt.Color(64,43,100));
        menuE.setBackground(new java.awt.Color(64,43,100));
        
        print_buttonC.setVisible(true);
        print_buttonD.setVisible(false);
        print_buttonE.setVisible(false);
        refresh_buttonD.setVisible(false);
        refresh_buttonE.setVisible(false);
        fullscreen_buttonD.setVisible(false);
        fullscreen_buttonE.setVisible(false);
        remove_buttonC.setVisible(true);

        fullscreen_panelD.setVisible(false);
        fullscreen_panelE.setVisible(false);
        ScrollPaneA.setVisible(false);
        ScrollPaneB.setVisible(false);
        ScrollPaneC.setVisible(true);
        panelD.setVisible(false);
        panelE.setVisible(false);
        
        edit_buttonG.setVisible(false);
        menuF.setBackground(new java.awt.Color(64,43,100));
        ScrollPaneF.setVisible(false);
        panelG.setVisible(false);
    }//GEN-LAST:event_menuCMouseClicked

    private void PhotoMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_PhotoMouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_PhotoMouseClicked

    private void PhotoMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_PhotoMouseEntered
        Photo.setBackground(new java.awt.Color(237, 224, 255));// TODO add your handling code here:
    }//GEN-LAST:event_PhotoMouseEntered

    private void PhotoMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_PhotoMouseExited
        Photo.setBackground(new java.awt.Color(240,240,240));// TODO add your handling code here:
    }//GEN-LAST:event_PhotoMouseExited

    private void formWindowOpened(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowOpened
       
    }//GEN-LAST:event_formWindowOpened

    private void menuDMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_menuDMouseClicked
        menuD.setBackground(new java.awt.Color(85,65,118));
        menuB.setBackground(new java.awt.Color(64,43,100));
        menuA.setBackground(new java.awt.Color(64,43,100));
        menuC.setBackground(new java.awt.Color(64,43,100));
        menuE.setBackground(new java.awt.Color(64,43,100));
        
        print_buttonC.setVisible(false);
        print_buttonD.setVisible(true);
        print_buttonE.setVisible(false);
        refresh_buttonD.setVisible(true);
        refresh_buttonE.setVisible(false);
        fullscreen_buttonD.setVisible(true);
        fullscreen_buttonE.setVisible(false);
        remove_buttonC.setVisible(false);
        
        fullscreen_panelD.setVisible(false);
        fullscreen_panelE.setVisible(false);
        ScrollPaneA.setVisible(false);
        ScrollPaneB.setVisible(false);
        ScrollPaneC.setVisible(false);
        panelD.setVisible(true);
        panelE.setVisible(false);
        viewall("attach_in");
        
        edit_buttonG.setVisible(false);
        menuF.setBackground(new java.awt.Color(64,43,100));
        ScrollPaneF.setVisible(false);
        panelG.setVisible(false);
    }//GEN-LAST:event_menuDMouseClicked

    private void menuEMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_menuEMouseClicked
        fullscreen_panelD.setVisible(false);
        fullscreen_panelE.setVisible(false);
        ScrollPaneA.setVisible(false);
        ScrollPaneB.setVisible(false);
        ScrollPaneC.setVisible(false);
        panelD.setVisible(false);
        panelE.setVisible(true);
        
        print_buttonC.setVisible(false);
        print_buttonD.setVisible(false);
        print_buttonE.setVisible(true);
        refresh_buttonD.setVisible(false);
        refresh_buttonE.setVisible(true);
        fullscreen_buttonD.setVisible(false);
        fullscreen_buttonE.setVisible(true);
        remove_buttonC.setVisible(false);

        menuE.setBackground(new java.awt.Color(85,65,118));
        menuA.setBackground(new java.awt.Color(64,43,100));
        menuB.setBackground(new java.awt.Color(64,43,100));
        menuC.setBackground(new java.awt.Color(64,43,100));
        menuD.setBackground(new java.awt.Color(64,43,100));
        viewall("attach_out");
        
        edit_buttonG.setVisible(false);
        menuF.setBackground(new java.awt.Color(64,43,100));
        ScrollPaneF.setVisible(false);
        panelG.setVisible(false);
    }//GEN-LAST:event_menuEMouseClicked

    private void exit_fullscreen_buttonDMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_exit_fullscreen_buttonDMouseClicked
        fullscreen_panelD.setVisible(false);
        fullscreen_panelE.setVisible(false);
        bg.setVisible(true);
    }//GEN-LAST:event_exit_fullscreen_buttonDMouseClicked

    private void exit_fullscreen_buttonDMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_exit_fullscreen_buttonDMouseEntered
        // TODO add your handling code here:
    }//GEN-LAST:event_exit_fullscreen_buttonDMouseEntered

    private void exit_fullscreen_buttonDMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_exit_fullscreen_buttonDMouseExited
        // TODO add your handling code here:
    }//GEN-LAST:event_exit_fullscreen_buttonDMouseExited

    private void exit_fullscreen_buttonEMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_exit_fullscreen_buttonEMouseClicked
        fullscreen_panelE.setVisible(false);
        fullscreen_panelD.setVisible(false);
        bg.setVisible(true);
    }//GEN-LAST:event_exit_fullscreen_buttonEMouseClicked

    private void exit_fullscreen_buttonEMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_exit_fullscreen_buttonEMouseEntered
        // TODO add your handling code here:
    }//GEN-LAST:event_exit_fullscreen_buttonEMouseEntered

    private void exit_fullscreen_buttonEMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_exit_fullscreen_buttonEMouseExited
        // TODO add your handling code here:
    }//GEN-LAST:event_exit_fullscreen_buttonEMouseExited

    private void kin_relation_AActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_kin_relation_AActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_kin_relation_AActionPerformed

    private void kin_mobile_numberAActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_kin_mobile_numberAActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_kin_mobile_numberAActionPerformed

    private void kin_NOK_AActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_kin_NOK_AActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_kin_NOK_AActionPerformed

    private void kin_branchAActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_kin_branchAActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_kin_branchAActionPerformed

    private void kin_account_numberAActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_kin_account_numberAActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_kin_account_numberAActionPerformed

    private void kin_aadharAActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_kin_aadharAActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_kin_aadharAActionPerformed

    private void kin_PAN_AActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_kin_PAN_AActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_kin_PAN_AActionPerformed

    private void indl_branchAActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_indl_branchAActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_indl_branchAActionPerformed

    private void distAActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_distAActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_distAActionPerformed

    private void PO_AActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_PO_AActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_PO_AActionPerformed

    private void tehAActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_tehAActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_tehAActionPerformed

    private void indl_account_numberAActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_indl_account_numberAActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_indl_account_numberAActionPerformed

    private void indl_aadharAActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_indl_aadharAActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_indl_aadharAActionPerformed

    private void indl_PAN_AActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_indl_PAN_AActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_indl_PAN_AActionPerformed

    private void select_file_buttonAMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_select_file_buttonAMouseExited
        select_file_buttonA.setBackground(new java.awt.Color(240,240,240));// TODO add your handling code here:
    }//GEN-LAST:event_select_file_buttonAMouseExited

    private void select_file_buttonAMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_select_file_buttonAMouseEntered
        select_file_buttonA.setBackground(new java.awt.Color(237, 224, 255));// TODO add your handling code here:
    }//GEN-LAST:event_select_file_buttonAMouseEntered

    private void select_file_buttonAMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_select_file_buttonAMouseClicked
        
        String sno=army_number_textfieldA.getText();
        if(sno==null ||sno.trim().isEmpty()){
            JOptionPane.showMessageDialog(null,"Please enter Army Number");
        }
        else{
            JFileChooser chooser=new JFileChooser();

            //applying extension filter
            chooser.setFileFilter(new FileNameExtensionFilter("Image Files", "png", "jpg", "jpeg"));

            //opening file chooser window
            chooser.showOpenDialog(null);

            File f= chooser.getSelectedFile();

            //filepath
            String filepath =f.getAbsolutePath();

            //setting the label to filename
            select_file_labelA.setText(f.getName());
            //calling function
            fp=filepath;
            chooser.setSelectedFile(null);
        }
    }//GEN-LAST:event_select_file_buttonAMouseClicked

    private void DOJ_date_comboboxAActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_DOJ_date_comboboxAActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_DOJ_date_comboboxAActionPerformed

    private void trade_textfieldAActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_trade_textfieldAActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_trade_textfieldAActionPerformed

    private void add_buttonAMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_add_buttonAMouseExited
        add_buttonA.setBackground(new java.awt.Color(240,240,240));// TODO add your handling code here:
    }//GEN-LAST:event_add_buttonAMouseExited

    private void add_buttonAMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_add_buttonAMouseEntered
        add_buttonA.setBackground(new java.awt.Color(237, 224, 255));// TODO add your handling code here:
    }//GEN-LAST:event_add_buttonAMouseEntered
    private void attach_in_add(){
         PreparedStatement  st   = null;
        
        try{  
            
       
        String name = name_textfieldA.getText();
        String service = army_number_textfieldA.getText();
        String company = company_textfieldA.getText();
        String courses = courses_textfieldA.getText();
        String unit = unit_textfieldA.getText();
        String armed = AS_textfieldA.getText();
        String location = location_textfieldA.getText();
        String trade = trade_textfieldA.getText();
        
        String contact = indl_mob_textfieldA.getText();
        String rank1 = (String)rank_comboboxA.getSelectedItem();
        String medical = medical_category_textfieldA.getText(); 
        
        //address
        String village = villageA.getText(); 
        String po = PO_A.getText();
        String district = distA.getText();
        String teh = tehA.getText();
        String state = stateA.getText();
        String pin = PIN_A.getText();
        String address = village+","+district+","+teh+","+po+","+state+","+pin;
        
        //indl records 
        String indl_icard = indl_Icard_numberA.getText();
        String indl_aadhar = indl_aadharA.getText();
        String indl_account_no = indl_account_numberA.getText();
        String indl_branch = indl_branchA.getText();
        String indl_pan = indl_PAN_A.getText();
        String indl_mail = indl_mailA.getText();
        String indl_bank = indl_bank_nameA.getText();
        String indl_ifsc = indl_IFSC_A.getText();
        
        //kin
        String kin_name = kin_NOK_A.getText();
        String kin_mob = kin_mobile_numberA.getText();
        String kin_contact = "+91 "+kin_mob;
        String kin_relation = kin_relation_A.getText();
        String kin_pan = kin_PAN_A.getText();
        String kin_aadhar = kin_aadharA.getText();
        String kin_email = kin_mailA.getText(); 
        String kin_acc = kin_account_numberA.getText();
        String kin_bank = kin_bank_nameA.getText();
        String kin_branch = kin_branchA.getText();
        String kin_ifsc = kin_IFSC_A.getText();
        
        
        
        //date of joining
        String year = (String)DOJ_year_comboboxA.getSelectedItem();
        String month = (String)DOJ_month_comboboxA.getSelectedItem();
        String date = (String)DOJ_date_comboboxA.getSelectedItem();
        String date_of_joining = date+"-"+month+"-"+year;
        int y = Integer.parseInt(year);
        int m = Integer.parseInt(month);
        int d = Integer.parseInt(date);
        
        //dob
        String dob_year = (String)birth_year_comboboxA.getSelectedItem();
        String dob_month = (String)birth_month_comboboxA.getSelectedItem();
        String dob_date = (String)birth_date_comboboxA.getSelectedItem();
        int dy = Integer.parseInt(dob_year);
        int dm = Integer.parseInt(dob_month);
        int dd = Integer.parseInt(dob_date);
        String date_of_birth = dob_date+"-"+dob_month+"-"+dob_year;
        boolean x = (kin_email.contains("@") && indl_mail.contains("@") &&(pin.length()==6) && (pin.matches("[0-9]+")));
        String err =" ";
        String doj =service.toLowerCase()+","+date_of_joining;
       // System.out.println(date_of_joining + " "+medical);
//        String p = "04-05-2016";
        // System.out.print("\noutside if");
        if(!(select_file_labelA.getText().equalsIgnoreCase("Select File"))&&x&&(unit!=null && !unit.trim().isEmpty()) &&(trade!=null && !trade.trim().isEmpty()) &&(location!=null && !location.trim().isEmpty()) &&(armed!=null && !armed.trim().isEmpty()) &&!((dy%4==0 && dm==2 &&  (dd>29)  )   || (  (dm==2 || dm==4 || dm==6 || dm==9 || dm==11)  && dd==31  )    ||  (dm==2 && dd>28 && dy%4!=0))&&!((y%4==0 && m==2 &&  (d>29)  )   || (  (m==2 || m==4 || m==6 || m==9 || m==11)  && d==31  )    ||  (m==2 && d>28 && y%4!=0)) &&(name!=null && !name.trim().isEmpty()) && (contact.length()==10 && contact.matches("[0-9]+") && kin_mob.length()==10 && kin_mob.matches("[0-9]+") )&&(service!=null && !service.trim().isEmpty()) && (company!=null && !company.trim().isEmpty()) && (courses!=null && !courses.trim().isEmpty()) && (rank1!=null && !rank1.trim().isEmpty()) && (medical!=null && !medical.trim().isEmpty()) && (date_of_joining!=null && !date_of_joining.trim().isEmpty() && year.matches("[0-9]+") &&month.matches("[0-9]+")&&date.matches("[0-9]+")&& dob_year.matches("[0-9]+") && dob_month.matches("[0-9]+")&& dob_date.matches("[0-9]+"))){
          //  System.out.print("inside first if");
            if((kin_email.contains("@"))&& (indl_mail.contains("@"))&&(village!=null && !village.trim().isEmpty()) &&(indl_account_no!=null && !indl_account_no.trim().isEmpty()) && (po!=null && !po.trim().isEmpty()) && (district!=null && !district.trim().isEmpty()) &&(teh!=null && !teh.trim().isEmpty()) && (state!=null && !state.trim().isEmpty()) &&(indl_icard!=null && !indl_icard.trim().isEmpty()) &&(pin.length()==6 && pin.matches("[0-9]+") && indl_aadhar.length()==12 && indl_aadhar.matches("[0-9]+") && indl_pan.length()==10 && kin_aadhar.length()==12 && kin_aadhar.matches("[0-9]+") && kin_pan.length()==10) &&(indl_branch!=null && !indl_branch.trim().isEmpty()) &&(indl_mail!=null && !indl_mail.trim().isEmpty()) &&(indl_bank!=null && !indl_bank.trim().isEmpty()) &&(indl_ifsc!=null && !indl_ifsc.trim().isEmpty()) &&(kin_acc!=null && !kin_acc.trim().isEmpty()) &&(kin_branch!=null && !kin_branch.trim().isEmpty())&&(kin_bank!=null && !kin_bank.trim().isEmpty()) &&(kin_ifsc!=null && !kin_ifsc.trim().isEmpty())&&(kin_email!=null && !kin_email.trim().isEmpty())&&(kin_relation!=null && !kin_relation.trim().isEmpty()) &&(kin_name!=null && !kin_name.trim().isEmpty()) ){
                
                System.out.print("\ninside second if\n");
                String q = "insert into attach_in "+"values(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";

                st = (PreparedStatement) con.prepareStatement(q);
                st.setString(1,service.toUpperCase());
                st.setString(2,name);
                st.setString(3,date_of_birth);
                st.setString(4,rank1);
                st.setString(5,company);
                st.setString(6,"+91 "+contact);
                st.setString(7,indl_icard);
                st.setString(8,indl_aadhar);
                st.setString(9,indl_pan); 
                st.setString(10,indl_account_no);
                st.setString(11,indl_bank);
                st.setString(12,indl_ifsc);
                st.setString(13,indl_branch);
                st.setString(14,indl_mail);
                st.setString(15,courses);
                st.setString(16,address);
                st.setString(17,date_of_joining);
                st.setString(18,medical);
                st.setString(19,kin_name);
                st.setString(20,kin_contact);
                st.setString(21,kin_relation);
                st.setString(22,kin_aadhar);
                st.setString(23,kin_pan);
                st.setString(24,kin_acc);
                st.setString(25,kin_bank);
                st.setString(26,kin_ifsc);
                st.setString(27,kin_branch);
                st.setString(28,kin_email);
                st.setString(29,armed);
                st.setString(30,trade);
                st.setString(31,unit);
                st.setString(32,location);
                st.setString(33,doj);

                //ResultSet rs=st.executeQuery(); 
                st.execute();
               
                JOptionPane.showMessageDialog(null,"Data inserted successfully!");
                
                move(fp,service);
                
                name_textfieldA.setText("");
                army_number_textfieldA.setText("");
                company_textfieldA.setText("");
                courses_textfieldA.setText("");
                unit_textfieldA.setText("");
                AS_textfieldA.setText("");
                location_textfieldA.setText("");
                trade_textfieldA.setText("");

                indl_mob_textfieldA.setText("");
                rank_comboboxA.setSelectedIndex(0);
                medical_category_textfieldA.setText(""); 

                select_file_labelA.setText("Select File");
                
                //address
                villageA.setText("Vill");
                villageA.setForeground(Color.decode("#666666"));
                PO_A.setText("PO");
                PO_A.setForeground(Color.decode("#666666"));
                distA.setText("Dist");
                distA.setForeground(Color.decode("#666666"));
                tehA.setText("Teh");
                tehA.setForeground(Color.decode("#666666"));
                stateA.setText("State");
                stateA.setForeground(Color.decode("#666666"));
                PIN_A.setText("PIN");
                PIN_A.setForeground(Color.decode("#666666"));
                
                

                //indl records
                indl_Icard_numberA.setText("ICardNumber");
                indl_PAN_A.setText("PAN");
                indl_aadharA.setText("Aadhar");
                indl_mailA.setText("E-Mail");
                indl_account_numberA.setText("Account Number");
                indl_bank_nameA.setText("Bank Name");
                indl_branchA.setText("Branch");
                indl_IFSC_A.setText("IFSC");
                
                indl_Icard_numberA.setForeground(Color.decode("#666666"));
                indl_aadharA.setForeground(Color.decode("#666666"));
                indl_account_numberA.setForeground(Color.decode("#666666"));
                indl_branchA.setForeground(Color.decode("#666666"));
                indl_PAN_A.setForeground(Color.decode("#666666"));
                indl_mailA.setForeground(Color.decode("#666666"));
                indl_bank_nameA.setForeground(Color.decode("#666666"));
                indl_IFSC_A.setForeground(Color.decode("#666666"));

                //kin
                kin_NOK_A.setText("NOK");
                kin_mobile_numberA.setText("Mob No.");
                kin_relation_A.setText("Relation");
                kin_PAN_A.setText("PAN");
                kin_aadharA.setText("Aadhar");
                kin_mailA.setText("E-Mail");
                kin_account_numberA.setText("Account Number");
                kin_bank_nameA.setText("Bank Name");
                kin_branchA.setText("Branch");
                kin_IFSC_A.setText("IFSC");
                
                kin_NOK_A.setForeground(Color.decode("#666666"));
                kin_mobile_numberA.setForeground(Color.decode("#666666"));
               
                kin_relation_A.setForeground(Color.decode("#666666"));
                kin_PAN_A.setForeground(Color.decode("#666666"));
                kin_aadharA.setForeground(Color.decode("#666666"));
                kin_mailA.setForeground(Color.decode("#666666"));
                kin_account_numberA.setForeground(Color.decode("#666666"));
                kin_bank_nameA.setForeground(Color.decode("#666666"));
                kin_branchA.setForeground(Color.decode("#666666"));
                kin_IFSC_A.setForeground(Color.decode("#666666"));



                //date of joining
                DOJ_year_comboboxA.setSelectedIndex(0);
                DOJ_month_comboboxA.setSelectedIndex(0);
                DOJ_date_comboboxA.setSelectedIndex(0);
               
                //dob
                birth_year_comboboxA.setSelectedIndex(0);
                birth_month_comboboxA.setSelectedIndex(0);
                birth_date_comboboxA.setSelectedIndex(0);
                System.out.print("\nEND");
                
                
                
                
                
                
            }
            else{
               // System.out.print("ooopppsssss");
//                boolean x = (village!=null && !village.trim().isEmpty());
//                System.out.println("hii"+(village!=null && !village.trim().isEmpty()));
                
                String err1="";
               
                
                if((village==null || village.trim().isEmpty())){
                    err1= err1+"\nVillage Field Empty";
                }
                if((indl_account_no==null || indl_account_no.trim().isEmpty())){
                    err1= err1+"\nIndividual Account Number Field Empty";
                }
                if((po==null || po.trim().isEmpty())){
                    err1= err1+"\nPost Office Field Empty";
                }
                if((district==null || district.trim().isEmpty())){
                    err1= err1+"\nDistrict Field Empty";
                }
                if((teh==null || teh.trim().isEmpty())){
                    err1= err1+"\nTeh Field Empty";
                }
                if((state==null || state.trim().isEmpty())){
                    err1= err1+"\nState Field Empty";
                }
                if((indl_icard==null || indl_icard.trim().isEmpty())){
                    err1= err1+"\nIndividual id Field Empty";
                }
                if((indl_branch==null || indl_branch.trim().isEmpty())){
                    err1= err1+"\nIndividual Branch Field Empty";
                }
                if((indl_bank==null || indl_bank.trim().isEmpty())){
                    err1= err1+"\nIndividual Bank Field Empty";
                }
                if((indl_ifsc==null || indl_ifsc.trim().isEmpty())){
                    err1= err1+"\nIndividual IFSC Field Empty";
                }
                if((indl_mail==null || indl_mail.trim().isEmpty()) || !indl_mail.contains("@")){
                    err1= err1+"\nIncorrect Individual Mail Field";
                }
                if((kin_acc==null || kin_acc.trim().isEmpty())){
                    err1= err1+"\nKin Account No Field Empty";
                }
                if((kin_branch==null || kin_branch.trim().isEmpty())){
                    err1= err1+"\nKin Branch Field Empty";
                }
                if((kin_bank==null || kin_bank.trim().isEmpty())){
                    err1= err1+"\nKin Bank Field Empty";
                }
                if((kin_ifsc==null || kin_ifsc.trim().isEmpty())){
                    err1= err1+"\nKin IFSC Field Empty";
                }
                if((kin_relation==null || kin_relation.trim().isEmpty())){
                    err1= err1+"\nKin Relation Field Empty";
                }
                if((kin_name==null || kin_name.trim().isEmpty())){
                    err1= err1+"\nKin Name Field Empty";
                }
                if((kin_email==null || kin_email.trim().isEmpty())  || !kin_email.contains("@")){
                    err1= err1+"\nIncorrect Kin Email Field";
                }
                if((indl_aadhar.length()!=12 || !indl_aadhar.matches("[0-9]+"))){
                    err1=err1+"\nIncorrect Individual Aadhar";
                }
                if((indl_pan.length()!=10)){
                    err1=err1+"\nIncorrect Individual Pan";
                }
                if((kin_aadhar.length()!=12 || !kin_aadhar.matches("[0-9]+"))){
                    err1=err1+"\nIncorrect Kin Aadhar";
                }
                if((kin_pan.length()!=10)){
                    err1=err1+"\nIncorrect Kin Pan";
                }
                if((pin.length()!=6 || !pin.matches("[0-9]+"))){
                    err1=err1+"\nIncorrect Pin";
                }
                
                
                
                
                if(!err1.trim().isEmpty()){
                    JOptionPane.showMessageDialog(null, err1);
                }
                
            } //\n
             
        } 
        else{
           
          //  System.out.print("else");
                if((select_file_labelA.getText().equalsIgnoreCase("Select File")))
                    err=err+"\nPlease Upload PIC";
                if(contact.length()!=10 || !contact.matches("[0-9]+"))
                    err= err+"\nIncorrect Number";
                if(name==null || name.trim().isEmpty())
                    err= err+"\nName Field Empty";
                if(kin_mob.length()!=10 || !kin_mob.matches("[0-9]+"))
                    err= err+"\nIncorrect Kin Mobile";
                if(service==null || service.trim().isEmpty())
                    err= err+"\nArmy Number Empty";
                if(company==null || company.trim().isEmpty())
                    err= err+"\nCompany Field Empty";
                if(courses==null || courses.trim().isEmpty())
                    err= err+"\nCourses Field Empty";
                if(rank1==null || rank1.trim().isEmpty())
                    err= err+"\nRank Field Empty";
                if(medical==null || medical.trim().isEmpty())
                    err= err+"\nMedical Field Empty";
                if(!year.matches("[0-9]+") || !month.matches("[0-9]+")|| !date.matches("[0-9]+"))
                    err= err+"\nIncorrect Date of Joining";
                if(!dob_year.matches("[0-9]+") || !dob_month.matches("[0-9]+")|| !dob_date.matches("[0-9]+"))
                    err= err+"\nIncorrect Date of Birth";
                
                if((village==null || village.trim().isEmpty())){
                    err= err+"\nVillage Field Empty";
                }
                if(((y%4==0 && m==2 &&  (d>29)  )   || (  (m==2 || m==4 || m==6 || m==9 || m==11)  && d==31  )    ||  (m==2 && d>28 && y%4!=0)))
                    err = err+"\nDate of Joining not Correct";
                
                if(((dy%4==0 && dm==2 &&  (dd>29)  )   || (  (dm==2 || dm==4 || dm==6 || dm==9 || dm==11)  && dd==31  )    ||  (dm==2 && dd>28 && dy%4!=0)))
                    err = err+"\nDOB not Correct";
                if((indl_account_no==null || indl_account_no.trim().isEmpty())){
                    err= err+"\nIndividual Account Number Field Empty";
                }
                if((po==null || po.trim().isEmpty())){
                    err= err+"\nPost Office Field Empty";
                }
                if((district==null || district.trim().isEmpty())){
                    err= err+"\nDistrict Field Empty";
                }
                if((teh==null || teh.trim().isEmpty())){
                    err= err+"\nTeh Field Empty";
                }
                if((state==null || state.trim().isEmpty())){
                    err= err+"\nState Field Empty";
                }
                if((indl_icard==null || indl_icard.trim().isEmpty())){
                    err= err+"\nIndividual id Field Empty";
                }
                if((indl_branch==null || indl_branch.trim().isEmpty())){
                    err= err+"\nIndividual Branch Field Empty";
                }
                if((indl_bank==null || indl_bank.trim().isEmpty())){
                    err= err+"\nIndividual Bank Field Empty";
                }
                if((indl_ifsc==null || indl_ifsc.trim().isEmpty())){
                    err= err+"\nIndividual IFSC Field Empty";
                }
                if((indl_mail==null || indl_mail.trim().isEmpty()) || !indl_mail.contains("@")){
                    err= err+"\nIncorrect Individual Mail Field";
                }
                if((kin_acc==null || kin_acc.trim().isEmpty())){
                    err= err+"\nKin Account No Field Empty";
                }
                if((kin_branch==null || kin_branch.trim().isEmpty())){
                    err= err+"\nKin Branch Field Empty";
                }
                if((kin_bank==null || kin_bank.trim().isEmpty())){
                    err= err+"\nKin Bank Field Empty";
                }
                if((kin_ifsc==null || kin_ifsc.trim().isEmpty())){
                    err= err+"\nKin IFSC Field Empty";
                }
                if((kin_relation==null || kin_relation.trim().isEmpty())){
                    err= err+"\nKin Relation Field Empty";
                }
                if((kin_name==null || kin_name.trim().isEmpty())){
                    err= err+"\nKin Name Field Empty";
                }
                if((kin_email==null || kin_email.trim().isEmpty())  || !kin_email.contains("@")){
                    err= err+"\nIncorrect Kin Email Field";
                }
                if((indl_aadhar.length()!=12 || !indl_aadhar.matches("[0-9]+"))){
                    err=err+"\nIncorrect Individual Aadhar";
                }
                if((indl_pan.length()!=10)){
                    err=err+"\nIncorrect Individual Pan";
                }
                if((kin_aadhar.length()!=12 || !kin_aadhar.matches("[0-9]+"))){
                    err=err+"\nIncorrect Kin Aadhar";
                }
                if((kin_pan.length()!=10)){
                    err=err+"\nIncorrect Kin Pan";
                }
                if((pin.length()!=6 || !pin.matches("[0-9]+"))){
                    err=err+"\nIncorrect Pin";
                }
                if((trade==null || trade.trim().isEmpty())){
                    err= err+"\nTrade Field Empty";
                }
                if((unit==null || unit.trim().isEmpty())){
                    err= err+"\nUnit Field Empty";
                }
                if((location==null || location.trim().isEmpty())){
                    err= err+"\nLocation Field Empty";
                }
                if((armed==null || armed.trim().isEmpty())){
                    err= err+"\nArmed/Services Field Empty";
                }
                
            if(!err.trim().isEmpty()){
                JOptionPane.showMessageDialog(null, err);
            }
        }    
        
        }catch(Exception e){ 
            System.out.println(e);
            String exe = e.getMessage();
            if(exe.contains("Duplicate entry")){
                JOptionPane.showMessageDialog(null,"Data not inserted!\nEntered ARMY NUMBER and Date_of_Joining exists!");
            }
            else if(exe.contains("For input")){
                JOptionPane.showMessageDialog(null,"\nPlease Re-Check Date Formats");
            }
            else
                JOptionPane.showMessageDialog(null,"Data not inserted!");
        }
    }
    private void add_buttonAMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_add_buttonAMouseClicked
        // TODO add your handling code here:
        attach_in_add();
    }//GEN-LAST:event_add_buttonAMouseClicked

    private void army_number_textfieldAActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_army_number_textfieldAActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_army_number_textfieldAActionPerformed

    private void army_number_textfieldBActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_army_number_textfieldBActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_army_number_textfieldBActionPerformed

    private void DOJ_date_comboboxBActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_DOJ_date_comboboxBActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_DOJ_date_comboboxBActionPerformed

    private void indl_PAN_BActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_indl_PAN_BActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_indl_PAN_BActionPerformed

    private void indl_aadharBActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_indl_aadharBActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_indl_aadharBActionPerformed

    private void indl_account_numberBActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_indl_account_numberBActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_indl_account_numberBActionPerformed

    private void indl_branchBActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_indl_branchBActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_indl_branchBActionPerformed

    private void kin_PAN_BActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_kin_PAN_BActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_kin_PAN_BActionPerformed

    private void kin_aadharBActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_kin_aadharBActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_kin_aadharBActionPerformed

    private void kin_account_numberBActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_kin_account_numberBActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_kin_account_numberBActionPerformed

    private void kin_branchBActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_kin_branchBActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_kin_branchBActionPerformed

    private void kin_NOK_BActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_kin_NOK_BActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_kin_NOK_BActionPerformed

    private void kin_mobile_numberBActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_kin_mobile_numberBActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_kin_mobile_numberBActionPerformed

    private void kin_relation_BActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_kin_relation_BActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_kin_relation_BActionPerformed

    private void PO_BActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_PO_BActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_PO_BActionPerformed

    private void distBActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_distBActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_distBActionPerformed

    private void tehBActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_tehBActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_tehBActionPerformed

    private void add_labelA1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_add_labelA1MouseClicked
        // TODO add your handling code here:
        attach_out_add();
    }//GEN-LAST:event_add_labelA1MouseClicked

    private void add_labelA1MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_add_labelA1MouseEntered
        // TODO add your handling code here:
    }//GEN-LAST:event_add_labelA1MouseEntered
    private void attach_out_add(){
         PreparedStatement  st   = null;
        
        try{  
      
        String name = name_textfieldB.getText();
        String service = army_number_textfieldB.getText();
        String company = company_textfieldB.getText();
        String courses = courses_textfieldB.getText();
        
        String location = location_textfieldB.getText();
        String purpose = purpose_textfieldB.getText();
        
        
        String contact = indl_mob_textfieldB.getText();
        String rank1 = (String)rank_comboboxB.getSelectedItem();
        String medical = medical_category_textfieldB.getText(); 
        
        //address
        String village = villageB.getText(); 
        String po = PO_B.getText();
        String district = distB.getText();
        String teh = tehB.getText();
        String state = stateB.getText();
        String pin = PIN_B.getText();
        String address = village+","+district+","+teh+","+po+","+state+","+pin;
        
        //indl records
        String indl_icard = indl_Icard_numberB.getText();
        String indl_aadhar = indl_aadharB.getText();
        String indl_account_no = indl_account_numberB.getText();
        String indl_branch = indl_branchB.getText();
        String indl_pan = indl_PAN_B.getText();
        String indl_mail = indl_mailB.getText();
        String indl_bank = indl_bank_nameB.getText();
        String indl_ifsc = indl_IFSC_B.getText();
        
        //kin
        String kin_name = kin_NOK_B.getText();
        String kin_mob = kin_mobile_numberB.getText();
        String kin_contact = "+91 "+kin_mob;
        String kin_relation = kin_relation_B.getText();
        String kin_pan = kin_PAN_B.getText();
        String kin_aadhar = kin_aadharB.getText();
        String kin_email = kin_mailB.getText(); 
        String kin_acc = kin_account_numberB.getText();
        String kin_bank = kin_bank_nameB.getText();
        String kin_branch = kin_branchB.getText();
        String kin_ifsc = kin_IFSC_B.getText();
        
        
        
        //date of joining
        String year = (String)DOJ_year_comboboxB.getSelectedItem();
        String month = (String)DOJ_month_comboboxB.getSelectedItem();
        String date = (String)DOJ_date_comboboxB.getSelectedItem();
        String date_of_joining = null;
        int y=0,m=0,d=0;
        if(!year.contains("YYYY") && !month.contains("MM") && !date.contains("DD")){
            System.out.print("\ndate_check");
            y = Integer.parseInt(year);
            m = Integer.parseInt(month);
            d = Integer.parseInt(date);
            date_of_joining = date+"-"+month+"-"+year;
        }
        if(year.contains("YYYY") && month.contains("MM") && date.contains("DD")){
            System.out.print("\ndate_check2");
            date_of_joining = null;
            y=100;
        }
        
        //dob
        String dob_year = (String)birth_year_comboboxB.getSelectedItem();
        String dob_month = (String)birth_month_comboboxB.getSelectedItem();
        String dob_date = (String)birth_date_comboboxB.getSelectedItem();
        int dy = Integer.parseInt(dob_year);
        int dm = Integer.parseInt(dob_month);
        int dd = Integer.parseInt(dob_date);
        String date_of_birth = dob_date+"-"+dob_month+"-"+dob_year;
        
        
        //date of attachement
        String dl_y = (String)DOA_year_comboboxB.getSelectedItem();
        String dl_m = (String)DOA_month_comboboxB.getSelectedItem();
        String dl_d = (String)DOA_date_comboboxB.getSelectedItem();
        String date_of_attachment = dl_d+"-"+dl_m+"-"+dl_y;
        int dly = Integer.parseInt(dl_y);
        int dlm = Integer.parseInt(dl_m);
        int dld = Integer.parseInt(dl_d);
        String err =" ";
        String doa=service.toLowerCase()+","+date_of_attachment;
        boolean x = (kin_email.contains("@") && indl_mail.contains("@") &&(pin.length()==6) && (pin.matches("[0-9]+")));
                        System.out.print("\noutside if");

       // System.out.println(date_of_joining + " "+medical);
//        String p = "04-05-2016";
        // System.out.print("\noutside if");
        if(!(select_file_labelB.getText().equalsIgnoreCase("Select File"))&&x&&(purpose!=null && !purpose.trim().isEmpty()) && (location!=null && !location.trim().isEmpty()) && !((dly%4==0 && dlm==2 &&  (dld>29)  )   || (  (dlm==2 || dlm==4 || dlm==6 || dlm==9 || dlm==11)  && dld==31  )    ||  (dlm==2 && dld>28 && dly%4!=0))&&!((dy%4==0 && dm==2 &&  (dd>29)  )   || (  (dm==2 || dm==4 || dm==6 || dm==9 || dm==11)  && dd==31  )    ||  (dm==2 && dd>28 && dy%4!=0))&&!((y%4==0 && m==2 &&  (d>29)  )   || (  (m==2 || m==4 || m==6 || m==9 || m==11)  && d==31  )    ||  (m==2 && d>28 && y%4!=0)) &&(name!=null && !name.trim().isEmpty()) && (contact.length()==10 && contact.matches("[0-9]+") && kin_mob.length()==10 && kin_mob.matches("[0-9]+") )&&(service!=null && !service.trim().isEmpty()) && (company!=null && !company.trim().isEmpty()) && (courses!=null && !courses.trim().isEmpty()) && (rank1!=null && !rank1.trim().isEmpty()) && (medical!=null && !medical.trim().isEmpty()) && ( dob_year.matches("[0-9]+") && dob_month.matches("[0-9]+")&& dob_date.matches("[0-9]+"))){
            System.out.print("inside first if");
            if((kin_email.contains("@"))&& (indl_mail.contains("@"))&&(village!=null && !village.trim().isEmpty()) &&(indl_account_no!=null && !indl_account_no.trim().isEmpty()) && (po!=null && !po.trim().isEmpty()) && (district!=null && !district.trim().isEmpty()) &&(teh!=null && !teh.trim().isEmpty()) && (state!=null && !state.trim().isEmpty()) &&(indl_icard!=null && !indl_icard.trim().isEmpty()) &&(pin.length()==6 && pin.matches("[0-9]+") && indl_aadhar.length()==12 && indl_aadhar.matches("[0-9]+") && indl_pan.length()==10 && kin_aadhar.length()==12 && kin_aadhar.matches("[0-9]+") && kin_pan.length()==10) &&(indl_branch!=null && !indl_branch.trim().isEmpty()) &&(indl_mail!=null && !indl_mail.trim().isEmpty()) &&(indl_bank!=null && !indl_bank.trim().isEmpty()) &&(indl_ifsc!=null && !indl_ifsc.trim().isEmpty()) &&(kin_acc!=null && !kin_acc.trim().isEmpty()) &&(kin_branch!=null && !kin_branch.trim().isEmpty())&&(kin_bank!=null && !kin_bank.trim().isEmpty()) &&(kin_ifsc!=null && !kin_ifsc.trim().isEmpty())&&(kin_email!=null && !kin_email.trim().isEmpty())&&(kin_relation!=null && !kin_relation.trim().isEmpty()) &&(kin_name!=null && !kin_name.trim().isEmpty()) ){
                System.out.print("\ninside second if ");
                if(y==0 &&d==0&&m==0){
                    System.out.print("\nInside third if");
                    JOptionPane.showMessageDialog(null,"Inocrrect Date of Joining format!!!");
                }
                else{
                    //System.out.print("\ninside second if");
                    String q = "insert into attach_out "+"values(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";

                    st = con.prepareStatement(q);
                    st.setString(1,service.toUpperCase());
                    st.setString(2,name);
                    st.setString(3,date_of_birth);
                    st.setString(4,rank1);
                    st.setString(5,company);
                    st.setString(6,"+91 "+contact);
                    st.setString(7,indl_icard);
                    st.setString(8,indl_aadhar);
                    st.setString(9,indl_pan); 
                    st.setString(10,indl_account_no);
                    st.setString(11,indl_bank);
                    st.setString(12,indl_ifsc);
                    st.setString(13,indl_branch);
                    st.setString(14,indl_mail);
                    st.setString(15,courses);
                    st.setString(16,address);
                    st.setString(17,date_of_joining);
                    st.setString(18,medical);
                    st.setString(19,kin_name);
                    st.setString(20,kin_contact);
                    st.setString(21,kin_relation);
                    st.setString(22,kin_aadhar);
                    st.setString(23,kin_pan);
                    st.setString(24,kin_acc);
                    st.setString(25,kin_bank);
                    st.setString(26,kin_ifsc);
                    st.setString(27,kin_branch);
                    st.setString(28,kin_email);
                    st.setString(29,date_of_attachment);
                    st.setString(30,location);
                    st.setString(31,purpose);
                    st.setString(32,doa);

                    //ResultSet rs=st.executeQuery(); 
                    st.execute();
                    JOptionPane.showMessageDialog(null,"Data inserted successfully!");
                    move(fp,service);
                    name_textfieldB.setText("");
                    army_number_textfieldB.setText("");
                    company_textfieldB.setText("");
                    courses_textfieldB.setText("");

                    location_textfieldB.setText("");
                    purpose_textfieldB.setText("");

                    indl_mob_textfieldB.setText("");
                    rank_comboboxB.setSelectedIndex(0);
                    medical_category_textfieldB.setText(""); 

                    select_file_labelB.setText("Select File");

                    //address
                    villageB.setText("Vill");
                    villageB.setForeground(Color.decode("#666666"));
                    PO_B.setText("PO");
                    PO_B.setForeground(Color.decode("#666666"));
                    distB.setText("Dist");
                    distB.setForeground(Color.decode("#666666"));
                    tehB.setText("Teh");
                    tehB.setForeground(Color.decode("#666666"));
                    stateB.setText("State");
                    stateB.setForeground(Color.decode("#666666"));
                    PIN_B.setText("PIN");
                    PIN_B.setForeground(Color.decode("#666666"));



                    //indl records
                    indl_Icard_numberB.setText("ICardNumber");
                    indl_PAN_B.setText("PAN");
                    indl_aadharB.setText("Aadhar");
                    indl_mailB.setText("E-Mail");
                    indl_account_numberB.setText("Account Number");
                    indl_bank_nameB.setText("Bank Name");
                    indl_branchB.setText("Branch");
                    indl_IFSC_B.setText("IFSC");

                    indl_Icard_numberB.setForeground(Color.decode("#666666"));
                    indl_aadharB.setForeground(Color.decode("#666666"));
                    indl_account_numberB.setForeground(Color.decode("#666666"));
                    indl_branchB.setForeground(Color.decode("#666666"));
                    indl_PAN_B.setForeground(Color.decode("#666666"));
                    indl_mailB.setForeground(Color.decode("#666666"));
                    indl_bank_nameB.setForeground(Color.decode("#666666"));
                    indl_IFSC_B.setForeground(Color.decode("#666666"));

                    //kin
                    kin_NOK_B.setText("NOK");
                    kin_mobile_numberB.setText("Mob No.");
                    kin_relation_B.setText("Relation");
                    kin_PAN_B.setText("PAN");
                    kin_aadharB.setText("Aadhar");
                    kin_mailB.setText("E-Mail");
                    kin_account_numberB.setText("Account Number");
                    kin_bank_nameB.setText("Bank Name");
                    kin_branchB.setText("Branch");
                    kin_IFSC_B.setText("IFSC");

                    kin_NOK_B.setForeground(Color.decode("#666666"));
                    kin_mobile_numberB.setForeground(Color.decode("#666666"));

                    kin_relation_B.setForeground(Color.decode("#666666"));
                    kin_PAN_B.setForeground(Color.decode("#666666"));
                    kin_aadharB.setForeground(Color.decode("#666666"));
                    kin_mailB.setForeground(Color.decode("#666666"));
                    kin_account_numberB.setForeground(Color.decode("#666666"));
                    kin_bank_nameB.setForeground(Color.decode("#666666"));
                    kin_branchB.setForeground(Color.decode("#666666"));
                    kin_IFSC_B.setForeground(Color.decode("#666666"));



                    //date of joining
                    DOJ_year_comboboxB.setSelectedIndex(0);
                    DOJ_month_comboboxB.setSelectedIndex(0);
                    DOJ_date_comboboxB.setSelectedIndex(0);

                    //dob
                    birth_year_comboboxB.setSelectedIndex(0);
                    birth_month_comboboxB.setSelectedIndex(0);
                    birth_date_comboboxB.setSelectedIndex(0);

                    //dol
                    DOA_year_comboboxB.setSelectedIndex(0);
                    DOA_month_comboboxB.setSelectedIndex(0);
                    DOA_date_comboboxB.setSelectedIndex(0);
                }
               
            }
            else{
                System.out.print("ooopppsssss");
//                boolean x = (village!=null && !village.trim().isEmpty());
//                System.out.println("hii"+(village!=null && !village.trim().isEmpty()));
                
                String err1="";
               
                
                if((village==null || village.trim().isEmpty())){
                    err1= err1+"\nVillage Field Empty";
                }
                if((indl_account_no==null || indl_account_no.trim().isEmpty())){
                    err1= err1+"\nIndividual Account Number Field Empty";
                }
                if((po==null || po.trim().isEmpty())){
                    err1= err1+"\nPost Office Field Empty";
                }
                if((district==null || district.trim().isEmpty())){
                    err1= err1+"\nDistrict Field Empty";
                }
                if((teh==null || teh.trim().isEmpty())){
                    err1= err1+"\nTeh Field Empty";
                }
                if((state==null || state.trim().isEmpty())){
                    err1= err1+"\nState Field Empty";
                }
                if((indl_icard==null || indl_icard.trim().isEmpty())){
                    err1= err1+"\nIndividual id Field Empty";
                }
                if((indl_branch==null || indl_branch.trim().isEmpty())){
                    err1= err1+"\nIndividual Branch Field Empty";
                }
                if((indl_bank==null || indl_bank.trim().isEmpty())){
                    err1= err1+"\nIndividual Bank Field Empty";
                }
                if((indl_ifsc==null || indl_ifsc.trim().isEmpty())){
                    err1= err1+"\nIndividual IFSC Field Empty";
                }
                if((indl_mail==null || indl_mail.trim().isEmpty()) || !indl_mail.contains("@")){
                    err1= err1+"\nIncorrect Individual Mail Field";
                }
                if((kin_acc==null || kin_acc.trim().isEmpty())){
                    err1= err1+"\nKin Account No Field Empty";
                }
                if((kin_branch==null || kin_branch.trim().isEmpty())){
                    err1= err1+"\nKin Branch Field Empty";
                }
                if((kin_bank==null || kin_bank.trim().isEmpty())){
                    err1= err1+"\nKin Bank Field Empty";
                }
                if((kin_ifsc==null || kin_ifsc.trim().isEmpty())){
                    err1= err1+"\nKin IFSC Field Empty";
                }
                if((kin_relation==null || kin_relation.trim().isEmpty())){
                    err1= err1+"\nKin Relation Field Empty";
                }
                if((kin_name==null || kin_name.trim().isEmpty())){
                    err1= err1+"\nKin Name Field Empty";
                }
                if((kin_email==null || kin_email.trim().isEmpty())  || !kin_email.contains("@")){
                    err1= err1+"\nIncorrect Kin Email Field";
                }
                if((indl_aadhar.length()!=12 || !indl_aadhar.matches("[0-9]+"))){
                    err1=err1+"\nIncorrect Individual Aadhar";
                }
                if((indl_pan.length()!=10)){
                    err1=err1+"\nIncorrect Individual Pan";
                }
                if((kin_aadhar.length()!=12 || !kin_aadhar.matches("[0-9]+"))){
                    err1=err1+"\nIncorrect Kin Aadhar";
                }
                if((kin_pan.length()!=10)){
                    err1=err1+"\nIncorrect Kin Pan";
                }
                if((pin.length()!=6 || !pin.matches("[0-9]+"))){
                    err1=err1+"\nIncorrect Pin";
                }
                
                
                
                
                if(!err1.trim().isEmpty()){
                    JOptionPane.showMessageDialog(null, err1);
                }
                
            } //\n
             
        } 
        else{
           
            System.out.print("else");
                if((select_file_labelB.getText().equalsIgnoreCase("Select File")))
                    err=err+"\nPlease Upload PIC";
                if(contact.length()!=10 || !contact.matches("[0-9]+"))
                    err= err+"\nIncorrect Number";
                if(name==null || name.trim().isEmpty())
                    err= err+"\nName Field Empty";
                if(kin_mob.length()!=10 || !kin_mob.matches("[0-9]+"))
                    err= err+"\nIncorrect Kin Mobile";
                if(service==null || service.trim().isEmpty())
                    err= err+"\nArmy Number Field Empty";
                if(company==null || company.trim().isEmpty())
                    err= err+"\nCompany Field Empty";
                if(courses==null || courses.trim().isEmpty())
                    err= err+"\nCourses Field Empty";
                if(rank1==null || rank1.trim().isEmpty())
                    err= err+"\nRank Field Empty";
                if(medical==null || medical.trim().isEmpty())
                    err= err+"\nMedical Field Empty";
                if(!year.matches("[0-9]+") || !month.matches("[0-9]+")|| !date.matches("[0-9]+"))
                    err= err+"\nIncorrect Date of Joining";
                if(!dob_year.matches("[0-9]+") || !dob_month.matches("[0-9]+")|| !dob_date.matches("[0-9]+"))
                    err= err+"\nIncorrect Date of Birth";
                
                if((village==null || village.trim().isEmpty())){
                    err= err+"\nVillage Field Empty";
                }
                if(((y%4==0 && m==2 &&  (d>29)  )   || (  (m==2 || m==4 || m==6 || m==9 || m==11)  && d==31  )    ||  (m==2 && d>28 && y%4!=0)))
                    err = err+"\nDate of Joining not Correct";
                
                if(((dy%4==0 && dm==2 &&  (dd>29)  )   || (  (dm==2 || dm==4 || dm==6 || dm==9 || dm==11)  && dd==31  )    ||  (dm==2 && dd>28 && dy%4!=0)))
                    err = err+"\nDOB not Correct";
                if((indl_account_no==null || indl_account_no.trim().isEmpty())){
                    err= err+"\nIndividual Account Number Field Empty";
                }
                if(((dly%4==0 && dlm==2 &&  (dld>29)  )   || (  (dlm==2 || dlm==4 || dlm==6 || dlm==9 || dlm==11)  && dld==31  )    ||  (dlm==2 && dld>28 && dly%4!=0)))
                    err = err+"\nDate of Leaving not Correct";
                if((po==null || po.trim().isEmpty())){
                    err= err+"\nPost Office Field Empty";
                }
                if((district==null || district.trim().isEmpty())){
                    err= err+"\nDistrict Field Empty";
                }
                if((teh==null || teh.trim().isEmpty())){
                    err= err+"\nTeh Field Empty";
                }
                if((state==null || state.trim().isEmpty())){
                    err= err+"\nState Field Empty";
                }
                if((indl_icard==null || indl_icard.trim().isEmpty())){
                    err= err+"\nIndividual id Field Empty";
                }
                if((indl_branch==null || indl_branch.trim().isEmpty())){
                    err= err+"\nIndividual Branch Field Empty";
                }
                if((indl_bank==null || indl_bank.trim().isEmpty())){
                    err= err+"\nIndividual Bank Field Empty";
                }
                if((indl_ifsc==null || indl_ifsc.trim().isEmpty())){
                    err= err+"\nIndividual IFSC Field Empty";
                }
                if((indl_mail==null || indl_mail.trim().isEmpty()) || !indl_mail.contains("@")){
                    err= err+"\nIncorrect Individual Mail Field";
                }
                if((kin_acc==null || kin_acc.trim().isEmpty())){
                    err= err+"\nKin Account No Field Empty";
                }
                if((kin_branch==null || kin_branch.trim().isEmpty())){
                    err= err+"\nKin Branch Field Empty";
                }
                if((kin_bank==null || kin_bank.trim().isEmpty())){
                    err= err+"\nKin Bank Field Empty";
                }
                if((kin_ifsc==null || kin_ifsc.trim().isEmpty())){
                    err= err+"\nKin IFSC Field Empty";
                }
                if((kin_relation==null || kin_relation.trim().isEmpty())){
                    err= err+"\nKin Relation Field Empty";
                }
                if((kin_name==null || kin_name.trim().isEmpty())){
                    err= err+"\nKin Name Field Empty";
                }
                if((kin_email==null || kin_email.trim().isEmpty())  || !kin_email.contains("@")){
                    err= err+"\nIncorrect Kin Email Field";
                }
                if((indl_aadhar.length()!=12 || !indl_aadhar.matches("[0-9]+"))){
                    err=err+"\nIncorrect Individual Aadhar";
                }
                if((indl_pan.length()!=10)){
                    err=err+"\nIncorrect Individual Pan";
                }
                if((kin_aadhar.length()!=12 || !kin_aadhar.matches("[0-9]+"))){
                    err=err+"\nIncorrect Kin Aadhar";
                }
                if((kin_pan.length()!=10)){
                    err=err+"\nIncorrect Kin Pan";
                }
                if((pin.length()!=6 || !pin.matches("[0-9]+"))){
                    err=err+"\nIncorrect Pin";
                }
                if((location==null || location.trim().isEmpty())){
                    err= err+"\nLocation Field Empty";
                }
                if((purpose==null || purpose.trim().isEmpty())){
                    err= err+"\nArmed/Services Field Empty";
                }
            if(!err.trim().isEmpty()){
                JOptionPane.showMessageDialog(null, err);
            }
        }    
        
        }catch(Exception e){ 
            System.out.println(e);
            String exe = e.getMessage();
            if(exe.contains("Duplicate entry")){
                JOptionPane.showMessageDialog(null,"Data not inserted!\nEntered ARMY NUMBER and Date of Attachement exists!");
            }
            else if(exe.contains("For input")){
                JOptionPane.showMessageDialog(null,"\nPlease Re-Check Date Formats");
            }
            else
                JOptionPane.showMessageDialog(null,"Data not inserted!");
        }
    }
    
    private void add_buttonBMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_add_buttonBMouseClicked
        // TODO add your handling code here:
        attach_out_add();
    }//GEN-LAST:event_add_buttonBMouseClicked

    private void add_buttonBMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_add_buttonBMouseEntered
        add_buttonB.setBackground(new java.awt.Color(237, 224, 255));
    }//GEN-LAST:event_add_buttonBMouseEntered

    private void add_buttonBMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_add_buttonBMouseExited
        add_buttonB.setBackground(new java.awt.Color(240,240,240));
    }//GEN-LAST:event_add_buttonBMouseExited

    private void purpose_textfieldBActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_purpose_textfieldBActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_purpose_textfieldBActionPerformed

    private void select_file_buttonBMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_select_file_buttonBMouseExited
        // TODO add your handling code here:
    }//GEN-LAST:event_select_file_buttonBMouseExited

    private void select_file_buttonBMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_select_file_buttonBMouseEntered
        // TODO add your handling code here:
    }//GEN-LAST:event_select_file_buttonBMouseEntered

    private void select_file_buttonBMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_select_file_buttonBMouseClicked
        
        String sno=army_number_textfieldB.getText();
        JFileChooser chooser=new JFileChooser();
        
        //applying extension filter
        chooser.setFileFilter(new FileNameExtensionFilter("Image Files", "png", "jpg", "jpeg"));
        
        //opening file chooser window
        chooser.showOpenDialog(null);
        
        File f= chooser.getSelectedFile();
        
        //filepath
        String filepath =f.getAbsolutePath();
        
        //setting the label to filename
        select_file_labelB.setText(f.getName());
        fp=filepath;
        
        chooser.setSelectedFile(null);
    }//GEN-LAST:event_select_file_buttonBMouseClicked
    private void remove_id_attach(){
        String sno = service_number_textfieldC.getText().toUpperCase();
        if(sno==null || sno.trim().isEmpty()){
            JOptionPane.showMessageDialog(null,"No Input Found!");
        }
        else{
            try{  
                path_file paths= new path_file();
                Statement st = con.createStatement();
                String query = "select count(*) as count from attach_in where service_no ='"+sno+"'";
                String delete = "delete from attach_in where service_no='"+sno+"'";
                String query2 = "select count(*) as count from attach_out where service_no ='"+sno+"'";
                String delete2 = "delete from attach_out where service_no='"+sno+"'";
                ResultSet rs = st.executeQuery(query);
                int count = 0;
                int flag = 0;
                if(rs.next())
                    count = rs.getInt("count");
                if(count!=0){
                    flag=100;
                    st.executeUpdate(delete);
                    try {
                    Files.delete(Paths.get(paths.images + "\\" + sno+".JPG"));
                    } catch (IOException iOException) {
                        JOptionPane.showMessageDialog(this, "File Error encountered");
                    }
                    JOptionPane.showMessageDialog(null,"Records of "+sno+" deleted successfully!");
                    DateIN_ScrollPaneC.setVisible(false);
                    DateOUT_ScrollPaneC.setVisible(false);
                    name.setText(">XXXXXXX");
                    rank.setText(">XXXXXXX");
                    company.setText(">XXXXXXX");
                    DOB.setText(">XXXXXXX");
                    
                    
                    indl_mobileC.setText(">XXXXXXX");
                    courses_doneC.setText(">XXXXXXX");
                    NOK_nameC.setText(">XXXXXXX");
                    NOK_relationC.setText(">XXXXXXX");
                    medical_category.setText(">XXXXXXX");
                    NOK_mobileC.setText(">XXXXXXX");
                    
                    tradeC.setText(">XXXXXXX");
                    unitC.setText(">XXXXXXX");
                    locationC.setText(">XXXXXXX");
                    villC.setText(">XXXXXXX");
                    PO_C.setText(">XXXXXXX");
                    tehsil.setText(">XXXXXXX");
                    distC.setText(">XXXXXXX");
                    stateC.setText(">XXXXXXX");
                    PIN_C.setText(">XXXXXXX");
                }
                rs = st.executeQuery(query2);
                if(rs.next())
                    count = rs.getInt("count");
                if(count!=0){
                    flag=100;
                    st.executeUpdate(delete2);
                    try {
                    Files.delete(Paths.get(paths.images + "\\" + sno+".JPG"));
                    } catch (IOException iOException) {
                        JOptionPane.showMessageDialog(this, "File Error encountered");
                    }
                    JOptionPane.showMessageDialog(null,"Records of "+sno+" deleted successfully!");
                    DateIN_ScrollPaneC.setVisible(false);
                    DateOUT_ScrollPaneC.setVisible(false);
                    name.setText(">XXXXXXX");
                    rank.setText(">XXXXXXX");
                    company.setText(">XXXXXXX");
                    DOB.setText(">XXXXXXX");
                    
                    
                    indl_mobileC.setText(">XXXXXXX");
                    courses_doneC.setText(">XXXXXXX");
                    NOK_nameC.setText(">XXXXXXX");
                    NOK_relationC.setText(">XXXXXXX");
                    medical_category.setText(">XXXXXXX");
                    NOK_mobileC.setText(">XXXXXXX");
                    
                    tradeC.setText(">XXXXXXX");
                    unitC.setText(">XXXXXXX");
                    locationC.setText(">XXXXXXX");
                    villC.setText(">XXXXXXX");
                    PO_C.setText(">XXXXXXX");
                    tehsil.setText(">XXXXXXX");
                    distC.setText(">XXXXXXX");
                    stateC.setText(">XXXXXXX");
                    PIN_C.setText(">XXXXXXX");
                }
                if(flag!=100){
                    JOptionPane.showMessageDialog(null,"No records of "+sno+" found!");
                }
              
            }
            catch(Exception e){ 
                System.out.println(e);
                JOptionPane.showMessageDialog(null,"No records of "+sno+" found!");
            }
        }
    }

    private void remove_buttonCMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_remove_buttonCMouseClicked
        // TODO add your handling code here:
        remove_id_attach();
        

    }//GEN-LAST:event_remove_buttonCMouseClicked

    private void remove_buttonCMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_remove_buttonCMouseEntered
        remove_buttonC.setBackground(new java.awt.Color(237, 224, 255));
    }//GEN-LAST:event_remove_buttonCMouseEntered

    private void remove_buttonCMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_remove_buttonCMouseExited
        remove_buttonC.setBackground(new java.awt.Color(240,240,240));
    }//GEN-LAST:event_remove_buttonCMouseExited

    private void print_buttonCMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_print_buttonCMouseClicked
        title_label.grabFocus();
        PrintRecord();
    }//GEN-LAST:event_print_buttonCMouseClicked

    private void print_buttonCMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_print_buttonCMouseEntered
        print_buttonC.setBackground(new java.awt.Color(237, 224, 255));
    }//GEN-LAST:event_print_buttonCMouseEntered

    private void print_buttonCMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_print_buttonCMouseExited
        print_buttonC.setBackground(new java.awt.Color(240,240,240));
    }//GEN-LAST:event_print_buttonCMouseExited

    private void name_textfieldBActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_name_textfieldBActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_name_textfieldBActionPerformed

    private void print_buttonDMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_print_buttonDMouseClicked
        PrinterJob job =PrinterJob.getPrinterJob();
        job.setJobName("Print Panel");
        System.out.print("Dii");
        try{
            path_file paths=new path_file();
            PrintWriter pw=new PrintWriter(new File((paths.csv + "\\attach_in.csv")));
            StringBuilder sb=new StringBuilder();
            String query="Select * from attach_in";
            
            sb.append("Service_no      \t");
            sb.append(",");
            sb.append("Name            \t");
            sb.append(",");
            sb.append("Date of Birth             \t");
            sb.append(",");
            sb.append("Rank          \t");
            sb.append(",");
            sb.append("Company         \t");
            sb.append(",");
            sb.append("Contact_no      \t");
            sb.append(",");
            sb.append("Individual id         \t");
            sb.append(",");
            sb.append("Individual aadhar     \t");
            sb.append(",");
            sb.append("Individual pan        \t");
            sb.append(",");
            sb.append("Individual Bank account   \t");
            sb.append(",");
            sb.append("Individual Bank Name  \t");
            sb.append(",");
            sb.append("Individual ifsc       \t");
            sb.append(",");
            sb.append("Individual Branch Name\t");
            sb.append(",");
            sb.append("Individual email      \t");
            sb.append(",");
            sb.append("Courses_done    \t");
            sb.append(",");
            sb.append("Address         \t");
            sb.append(",");
            sb.append("Date_of_Joining \t");
            sb.append(",");
            sb.append("Medical_Category\t");
            sb.append(",");
            sb.append("kin name        \t");
            sb.append(",");
            sb.append("kin contact     \t");
            sb.append(",");
            sb.append("kin relation    \t");
            sb.append(",");
            sb.append("kin aadhar      \t");
            sb.append(",");
            sb.append("kin pan         \t");
            sb.append(",");
            sb.append("kin Bank account    \t");
            sb.append(",");
            sb.append("kin Bank Name   \t");
            sb.append(",");
            sb.append("kin ifsc        \t");
            sb.append(",");
            sb.append("kin Branch Name \t");
            sb.append(",");
            sb.append("kin email       \t");
            sb.append(",");
            sb.append("Armed_Services  \t");
            sb.append(",");
            sb.append("Trade           \t");
            sb.append(",");
            sb.append("Unit            \t");
            sb.append(",");
            sb.append("Location        \t");
            sb.append("\r");
            ResultSet rs = null;
            Statement st = con.createStatement();
            rs=st.executeQuery(query);  
            
            while(rs.next()){
                sb.append(rs.getString("Service_no"));
                sb.append(",");
                sb.append(rs.getString("name"));
                sb.append(",");
                sb.append(rs.getString("DOB"));
                sb.append(",");
                sb.append(rs.getString("Rank_"));
                sb.append(",");
                sb.append(rs.getString("Company"));
                sb.append(",");
                sb.append(rs.getString("Contact_no"));
                sb.append(",");
                sb.append(rs.getString("indl_id"));
                sb.append(",");
                sb.append(rs.getString("indl_aadhar")+"\t");
                sb.append(",");
                sb.append(rs.getString("indl_pan")+"\t");
                sb.append(",");
                sb.append(rs.getString("indl_bank_acc")+"\t");
                sb.append(",");
                sb.append(rs.getString("indl_bank_name"));
                sb.append(",");
                sb.append(rs.getString("indl_ifsc"));
                sb.append(",");
                sb.append(rs.getString("indl_branch_name"));
                sb.append(",");
                sb.append(rs.getString("indl_email"));
                sb.append(",");
                if(rs.getString("Courses_done").contains(","))
                    sb.append("\""+rs.getString("Courses_done")+"\"");
                else
                    sb.append(rs.getString("Courses_done"));
                sb.append(",");
                sb.append("\""+rs.getString("Address")+"\"");
                sb.append(",");
                sb.append(rs.getString("Date_of_Joining"));
                sb.append(",");
                sb.append(rs.getString("Medical_Category"));
                sb.append(",");
                sb.append(rs.getString("kin_name"));
                sb.append(",");
                sb.append(rs.getString("kin_contact"));
                sb.append(",");
                sb.append(rs.getString("kin_relation"));
                sb.append(",");
                sb.append(rs.getString("kin_aadhar")+"\t");
                sb.append(",");
                sb.append(rs.getString("kin_pan")+"\t");
                sb.append(",");
                sb.append(rs.getString("kin_bank_acc")+"\t");
                sb.append(",");
                sb.append(rs.getString("kin_bank_name"));
                sb.append(",");
                sb.append(rs.getString("kin_ifsc"));
                sb.append(",");
                sb.append(rs.getString("kin_branch_name"));
                sb.append(",");
                sb.append(rs.getString("kin_email"));
                sb.append(",");
                sb.append(rs.getString("Armed_Services"));
                sb.append(",");
                sb.append(rs.getString("Trade"));
                sb.append(",");
                sb.append(rs.getString("Unit"));
                sb.append(",");
                sb.append(rs.getString("Location"));
                sb.append("\r\n");
            }
            pw.write(sb.toString());
            pw.close();
            JOptionPane.showMessageDialog(null,"Successfully Converted to CSV");
            Desktop.getDesktop().open(new File(paths.csv + "/attach_in.csv"));

        }
        catch(Exception e){
            System.out.print("\n"+e);
            String er=e.getMessage();
            if(er.contains("The process cannot access the file because it is being used by another process")){
                JOptionPane.showMessageDialog(null,"Please close the CSV file first!");
            }
            else{
                JOptionPane.showMessageDialog(null, e);
            }
        }
    }//GEN-LAST:event_print_buttonDMouseClicked

    private void print_buttonEMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_print_buttonEMouseClicked
        try{
            path_file paths=new path_file();
            PrintWriter pw=new PrintWriter(new File((paths.csv + "\\attach_out.csv")));
            StringBuilder sb=new StringBuilder();
            String query="Select * from attach_out";
            
            sb.append("Service_no      \t");
            sb.append(",");
            sb.append("Name            \t");
            sb.append(",");
            sb.append("Date of Birth             \t");
            sb.append(",");
            sb.append("Rank          \t");
            sb.append(",");
            sb.append("Company         \t");
            sb.append(",");
            sb.append("Contact_no      \t");
            sb.append(",");
            sb.append("Individual id         \t");
            sb.append(",");
            sb.append("Individual aadhar     \t");
            sb.append(",");
            sb.append("Individual pan        \t");
            sb.append(",");
            sb.append("Individual Bank account   \t");
            sb.append(",");
            sb.append("Individual Bank Name  \t");
            sb.append(",");
            sb.append("Individual ifsc       \t");
            sb.append(",");
            sb.append("Individual Branch Name\t");
            sb.append(",");
            sb.append("Individual email      \t");
            sb.append(",");
            sb.append("Courses_done    \t");
            sb.append(",");
            sb.append("Address         \t");
            sb.append(",");
            sb.append("Date_of_Joining \t");
            sb.append(",");
            sb.append("Medical_Category\t");
            sb.append(",");
            sb.append("kin name        \t");
            sb.append(",");
            sb.append("kin contact     \t");
            sb.append(",");
            sb.append("kin relation    \t");
            sb.append(",");
            sb.append("kin aadhar      \t");
            sb.append(",");
            sb.append("kin pan         \t");
            sb.append(",");
            sb.append("kin Bank account    \t");
            sb.append(",");
            sb.append("kin Bank Name   \t");
            sb.append(",");
            sb.append("kin ifsc        \t");
            sb.append(",");
            sb.append("kin Branch Name \t");
            sb.append(",");
            sb.append("kin email       \t");
            sb.append(",");
            sb.append("Date of Attachment  \t");
            sb.append(",");
            sb.append("Location           \t");
            sb.append(",");
            sb.append("Purpose            \t");
            sb.append("\r\n");
            ResultSet rs = null;
            Statement st = con.createStatement();
            rs=st.executeQuery(query);  
            
            while(rs.next()){
                sb.append(rs.getString("Service_no"));
                sb.append(",");
                sb.append(rs.getString("name"));
                sb.append(",");
                sb.append(rs.getString("DOB"));
                sb.append(",");
                sb.append(rs.getString("Rank_"));
                sb.append(",");
                sb.append(rs.getString("Company"));
                sb.append(",");
                sb.append(rs.getString("Contact_no"));
                sb.append(",");
                sb.append(rs.getString("indl_id"));
                sb.append(",");
                sb.append(rs.getString("indl_aadhar")+"\t");
                sb.append(",");
                sb.append(rs.getString("indl_pan")+"\t");
                sb.append(",");
                sb.append(rs.getString("indl_bank_acc")+"\t");
                sb.append(",");
                sb.append(rs.getString("indl_bank_name"));
                sb.append(",");
                sb.append(rs.getString("indl_ifsc"));
                sb.append(",");
                sb.append(rs.getString("indl_branch_name"));
                sb.append(",");
                sb.append(rs.getString("indl_email"));
                sb.append(",");
                if(rs.getString("Courses_done").contains(","))
                    sb.append("\""+rs.getString("Courses_done")+"\"");
                else
                    sb.append(rs.getString("Courses_done"));
                sb.append(",");
                sb.append("\""+rs.getString("Address")+"\"");
                sb.append(",");
                sb.append(rs.getString("Date_of_Joining"));
                sb.append(",");
                sb.append(rs.getString("Medical_Category"));
                sb.append(",");
                sb.append(rs.getString("kin_name"));
                sb.append(",");
                sb.append(rs.getString("kin_contact"));
                sb.append(",");
                sb.append(rs.getString("kin_relation"));
                sb.append(",");
                sb.append(rs.getString("kin_aadhar")+"\t");
                sb.append(",");
                sb.append(rs.getString("kin_pan")+"\t");
                sb.append(",");
                sb.append(rs.getString("kin_bank_acc")+"\t");
                sb.append(",");
                sb.append(rs.getString("kin_bank_name"));
                sb.append(",");
                sb.append(rs.getString("kin_ifsc"));
                sb.append(",");
                sb.append(rs.getString("kin_branch_name"));
                sb.append(",");
                sb.append(rs.getString("kin_email"));
                sb.append(",");
                sb.append(rs.getString("Date_of_Attachment"));
                sb.append(",");
                sb.append(rs.getString("Location"));
                sb.append(",");
                sb.append(rs.getString("Purpose"));
                
                sb.append("\r\n");
            }
            pw.write(sb.toString());
            pw.close();
            JOptionPane.showMessageDialog(null,"Successfully Converted to CSV");
            Desktop.getDesktop().open(new File(paths.csv + "/attach_out.csv"));

            
        }
        catch(Exception e){
            System.out.print("\n"+e);
            String er=e.getMessage();
            if(er.contains("The process cannot access the file because it is being used by another process")){
                JOptionPane.showMessageDialog(null,"Please close the CSV file first!");
            }
        }
    }//GEN-LAST:event_print_buttonEMouseClicked

    private void fullscreen_buttonEMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_fullscreen_buttonEMouseClicked
        fullscreen_panelE.setVisible(true);
        bg.setVisible(false);
        viewall("attach_out");
    }//GEN-LAST:event_fullscreen_buttonEMouseClicked

    private void fullscreen_buttonDMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_fullscreen_buttonDMouseClicked
        fullscreen_panelD.setVisible(true);
        bg.setVisible(false);
        viewall("attach_in");
    }//GEN-LAST:event_fullscreen_buttonDMouseClicked

    private void indl_Icard_numberBMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_indl_Icard_numberBMouseClicked
        //indl_Icard_numberB.setText("");
    }//GEN-LAST:event_indl_Icard_numberBMouseClicked

    private void indl_PAN_BMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_indl_PAN_BMouseClicked
       //indl_PAN_B.setText("");
    }//GEN-LAST:event_indl_PAN_BMouseClicked

    private void indl_aadharBMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_indl_aadharBMouseClicked
       // indl_aadharB.setText("");
    }//GEN-LAST:event_indl_aadharBMouseClicked

    private void indl_mailBMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_indl_mailBMouseClicked
       // indl_mailB.setText("");
    }//GEN-LAST:event_indl_mailBMouseClicked

    private void indl_account_numberBMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_indl_account_numberBMouseClicked
       // indl_account_numberB.setText("");
    }//GEN-LAST:event_indl_account_numberBMouseClicked

    private void indl_bank_nameBMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_indl_bank_nameBMouseClicked
       // indl_bank_nameB.setText("");
    }//GEN-LAST:event_indl_bank_nameBMouseClicked

    private void indl_branchBMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_indl_branchBMouseClicked
       // indl_branchB.setText("");
    }//GEN-LAST:event_indl_branchBMouseClicked

    private void indl_IFSC_BMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_indl_IFSC_BMouseClicked
       // indl_IFSC_B.setText("");
    }//GEN-LAST:event_indl_IFSC_BMouseClicked

    private void kin_NOK_BMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_kin_NOK_BMouseClicked
      //  kin_NOK_B.setText("");
    }//GEN-LAST:event_kin_NOK_BMouseClicked

    private void kin_mobile_numberBMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_kin_mobile_numberBMouseClicked
      //  kin_mobile_numberB.setText("");
    }//GEN-LAST:event_kin_mobile_numberBMouseClicked

    private void kin_relation_BMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_kin_relation_BMouseClicked
       // kin_relation_B.setText("");
    }//GEN-LAST:event_kin_relation_BMouseClicked

    private void kin_PAN_BMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_kin_PAN_BMouseClicked
       // kin_PAN_B.setText("");
    }//GEN-LAST:event_kin_PAN_BMouseClicked

    private void kin_aadharBMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_kin_aadharBMouseClicked
      ////  kin_aadharB.setText("");
    }//GEN-LAST:event_kin_aadharBMouseClicked

    private void kin_mailBMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_kin_mailBMouseClicked
       // kin_mailB.setText("");
    }//GEN-LAST:event_kin_mailBMouseClicked

    private void kin_account_numberBMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_kin_account_numberBMouseClicked
      //  kin_account_numberB.setText("");
    }//GEN-LAST:event_kin_account_numberBMouseClicked

    private void kin_bank_nameBMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_kin_bank_nameBMouseClicked
      //  kin_bank_nameB.setText("");
    }//GEN-LAST:event_kin_bank_nameBMouseClicked

    private void kin_branchBMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_kin_branchBMouseClicked
       // kin_branchB.setText("");
    }//GEN-LAST:event_kin_branchBMouseClicked

    private void kin_IFSC_BMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_kin_IFSC_BMouseClicked
        //kin_IFSC_B.setText("");
    }//GEN-LAST:event_kin_IFSC_BMouseClicked

    private void villageBMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_villageBMouseClicked
       // villageB.setText("");
    }//GEN-LAST:event_villageBMouseClicked

    private void tehBMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tehBMouseClicked
       // tehB.setText("");
    }//GEN-LAST:event_tehBMouseClicked

    private void PO_BMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_PO_BMouseClicked
       // PO_B.setText("");
    }//GEN-LAST:event_PO_BMouseClicked

    private void stateBMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_stateBMouseClicked
       // stateB.setText("");
    }//GEN-LAST:event_stateBMouseClicked

    private void distBMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_distBMouseClicked
      //  distB.setText("");
    }//GEN-LAST:event_distBMouseClicked

    private void PIN_BMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_PIN_BMouseClicked
       // PIN_B.setText("");
    }//GEN-LAST:event_PIN_BMouseClicked

    private void indl_Icard_numberAMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_indl_Icard_numberAMouseClicked
       // indl_Icard_numberA.setText("");
    }//GEN-LAST:event_indl_Icard_numberAMouseClicked

    private void indl_PAN_AMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_indl_PAN_AMouseClicked
      //  indl_PAN_A.setText("");
    }//GEN-LAST:event_indl_PAN_AMouseClicked

    private void indl_aadharAMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_indl_aadharAMouseClicked
       // indl_aadharA.setText("");
    }//GEN-LAST:event_indl_aadharAMouseClicked

    private void indl_mailAMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_indl_mailAMouseClicked
       // indl_mailA.setText("");
    }//GEN-LAST:event_indl_mailAMouseClicked

    private void indl_account_numberAMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_indl_account_numberAMouseClicked
       // indl_account_numberA.setText("");
    }//GEN-LAST:event_indl_account_numberAMouseClicked

    private void indl_bank_nameAMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_indl_bank_nameAMouseClicked
       // indl_bank_nameA.setText("");
    }//GEN-LAST:event_indl_bank_nameAMouseClicked

    private void indl_branchAMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_indl_branchAMouseClicked
      //  indl_branchA.setText("");
    }//GEN-LAST:event_indl_branchAMouseClicked

    private void indl_IFSC_AMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_indl_IFSC_AMouseEntered
        
    }//GEN-LAST:event_indl_IFSC_AMouseEntered

    private void indl_IFSC_AMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_indl_IFSC_AMouseClicked
      //  indl_IFSC_A.setText("");
    }//GEN-LAST:event_indl_IFSC_AMouseClicked

    private void kin_NOK_AMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_kin_NOK_AMouseEntered
       // kin_NOK_A.setText("");
    }//GEN-LAST:event_kin_NOK_AMouseEntered

    private void kin_mobile_numberAMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_kin_mobile_numberAMouseClicked
      //  kin_mobile_numberA.setText("");
    }//GEN-LAST:event_kin_mobile_numberAMouseClicked

    private void kin_relation_AMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_kin_relation_AMouseClicked
      //  kin_relation_A.setText("");
    }//GEN-LAST:event_kin_relation_AMouseClicked

    private void kin_PAN_AMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_kin_PAN_AMouseClicked
      //  kin_PAN_A.setText("");
    }//GEN-LAST:event_kin_PAN_AMouseClicked

    private void kin_aadharAMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_kin_aadharAMouseClicked
       // kin_aadharA.setText("");
    }//GEN-LAST:event_kin_aadharAMouseClicked

    private void kin_mailAMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_kin_mailAMouseClicked
       // kin_mailA.setText("");
    }//GEN-LAST:event_kin_mailAMouseClicked

    private void kin_account_numberAMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_kin_account_numberAMouseClicked
      //  kin_account_numberA.setText("");
    }//GEN-LAST:event_kin_account_numberAMouseClicked

    private void kin_bank_nameAMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_kin_bank_nameAMouseClicked
       // kin_bank_nameA.setText("");
    }//GEN-LAST:event_kin_bank_nameAMouseClicked

    private void kin_branchAMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_kin_branchAMouseClicked
     //   kin_branchA.setText("");
    }//GEN-LAST:event_kin_branchAMouseClicked

    private void kin_IFSC_AMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_kin_IFSC_AMouseClicked
       // kin_IFSC_A.setText("");
    }//GEN-LAST:event_kin_IFSC_AMouseClicked

    private void villageAMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_villageAMouseClicked
       // villageA.setText("");
    }//GEN-LAST:event_villageAMouseClicked

    private void tehAMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tehAMouseClicked
       // tehA.setText("");
    }//GEN-LAST:event_tehAMouseClicked

    private void PO_AMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_PO_AMouseClicked
      //  PO_A.setText("");
    }//GEN-LAST:event_PO_AMouseClicked

    private void stateAMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_stateAMouseClicked
      //  stateA.setText("");
    }//GEN-LAST:event_stateAMouseClicked

    private void distAMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_distAMouseClicked
       // distA.setText("");
    }//GEN-LAST:event_distAMouseClicked

    private void PIN_AMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_PIN_AMouseClicked
       // PIN_A.setText("");
    }//GEN-LAST:event_PIN_AMouseClicked

    private void indl_Icard_numberAFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_indl_Icard_numberAFocusGained
        // TODO add your handling code here:
        indl_Icard_numberA.setForeground(Color.decode("#000000"));
        if(indl_Icard_numberA.getText().trim().equals("ICardNumber")){
            indl_Icard_numberA.setText("");
        }
    }//GEN-LAST:event_indl_Icard_numberAFocusGained

    private void indl_Icard_numberAFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_indl_Icard_numberAFocusLost
        // TODO add your handling code here:
        if(indl_Icard_numberA.getText().trim().equals("")){
            indl_Icard_numberA.setText("ICardNumber");
            indl_Icard_numberA.setForeground(Color.decode("#666666"));
        }
    }//GEN-LAST:event_indl_Icard_numberAFocusLost

    private void indl_PAN_AFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_indl_PAN_AFocusLost
        // TODO add your handling code here:
        if(indl_PAN_A.getText().trim().equals("")){
            indl_PAN_A.setText("PAN");
            indl_PAN_A.setForeground(Color.decode("#666666"));
        }
    }//GEN-LAST:event_indl_PAN_AFocusLost

    private void indl_PAN_AFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_indl_PAN_AFocusGained
        // TODO add your handling code here:
        indl_PAN_A.setForeground(Color.decode("#000000"));
        if(indl_PAN_A.getText().trim().equals("PAN")){
            indl_PAN_A.setText("");
        }
                                          
      
        
    }//GEN-LAST:event_indl_PAN_AFocusGained

    private void indl_aadharAFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_indl_aadharAFocusGained
        // TODO add your handling code here:
        indl_aadharA.setForeground(Color.decode("#000000"));
        if(indl_aadharA.getText().trim().equals("Aadhar")){
            indl_aadharA.setText("");
        }
                                           

   
        
    }//GEN-LAST:event_indl_aadharAFocusGained

    private void indl_aadharAFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_indl_aadharAFocusLost
        // TODO add your handling code here:
        if(indl_aadharA.getText().trim().equals("")){
            indl_aadharA.setText("Aadhar");
            indl_aadharA.setForeground(Color.decode("#666666"));
        }
    }//GEN-LAST:event_indl_aadharAFocusLost

    private void indl_mailAFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_indl_mailAFocusGained
        // TODO add your handling code here:
        indl_mailA.setForeground(Color.decode("#000000"));
        if(indl_mailA.getText().trim().equals("E-Mail")){
            indl_mailA.setText("");
        }
                                    
    }//GEN-LAST:event_indl_mailAFocusGained

    private void indl_mailAFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_indl_mailAFocusLost
        // TODO add your handling code here:
        if(indl_mailA.getText().trim().equals("")){
            indl_mailA.setText("E-Mail");
            indl_mailA.setForeground(Color.decode("#666666"));
        }
    }//GEN-LAST:event_indl_mailAFocusLost

    private void indl_account_numberAFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_indl_account_numberAFocusGained
        // TODO add your handling code here:
        indl_account_numberA.setForeground(Color.decode("#000000"));
        if(indl_account_numberA.getText().trim().equals("Account Number")){
            indl_account_numberA.setText("");
        }
                                                  

   
        
    }//GEN-LAST:event_indl_account_numberAFocusGained

    private void indl_account_numberAFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_indl_account_numberAFocusLost
        // TODO add your handling code here:
        if(indl_account_numberA.getText().trim().equals("")){
            indl_account_numberA.setText("Account Number");
            indl_account_numberA.setForeground(Color.decode("#666666"));
        }
    }//GEN-LAST:event_indl_account_numberAFocusLost

    private void indl_bank_nameAFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_indl_bank_nameAFocusGained
        // TODO add your handling code here:
        indl_bank_nameA.setForeground(Color.decode("#000000"));
        if(indl_bank_nameA.getText().trim().equals("Bank Name")){
            indl_bank_nameA.setText("");
        }
    }//GEN-LAST:event_indl_bank_nameAFocusGained

    private void indl_bank_nameAFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_indl_bank_nameAFocusLost
        // TODO add your handling code here:
        if(indl_bank_nameA.getText().trim().equals("")){
            indl_bank_nameA.setText("Bank Name");
            indl_bank_nameA.setForeground(Color.decode("#666666"));
        }
    }//GEN-LAST:event_indl_bank_nameAFocusLost

    private void indl_branchAFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_indl_branchAFocusGained
        // TODO add your handling code here:
        indl_branchA.setForeground(Color.decode("#000000"));
        if(indl_branchA.getText().trim().equals("Branch")){
            indl_branchA.setText("");
        }
    }//GEN-LAST:event_indl_branchAFocusGained

    private void indl_branchAFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_indl_branchAFocusLost
        // TODO add your handling code here:
        if(indl_branchA.getText().trim().equals("")){
            indl_branchA.setText("Branch");
            indl_branchA.setForeground(Color.decode("#666666"));
        }
    }//GEN-LAST:event_indl_branchAFocusLost

    private void indl_IFSC_AFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_indl_IFSC_AFocusGained
        // TODO add your handling code here:
        indl_IFSC_A.setForeground(Color.decode("#000000"));
        if(indl_IFSC_A.getText().trim().equals("IFSC")){
            indl_IFSC_A.setText("");
        }
    }//GEN-LAST:event_indl_IFSC_AFocusGained

    private void indl_IFSC_AFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_indl_IFSC_AFocusLost
        // TODO add your handling code here:
        if(indl_IFSC_A.getText().trim().equals("")){
            indl_IFSC_A.setText("IFSC");
            indl_IFSC_A.setForeground(Color.decode("#666666"));
        }
    }//GEN-LAST:event_indl_IFSC_AFocusLost

    private void kin_NOK_AFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_kin_NOK_AFocusGained
        // TODO add your handling code here:
        kin_NOK_A.setForeground(Color.decode("#000000"));
        if(kin_NOK_A.getText().trim().equals("NOK")){
            kin_NOK_A.setText("");
        }
    }//GEN-LAST:event_kin_NOK_AFocusGained

    private void kin_NOK_AFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_kin_NOK_AFocusLost
        // TODO add your handling code here:
        if(kin_NOK_A.getText().trim().equals("")){
            kin_NOK_A.setText("NOK");
            kin_NOK_A.setForeground(Color.decode("#666666"));
        }
    }//GEN-LAST:event_kin_NOK_AFocusLost

    private void kin_mobile_numberAFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_kin_mobile_numberAFocusGained
        // TODO add your handling code here:
        kin_mobile_numberA.setForeground(Color.decode("#000000"));
        if(kin_mobile_numberA.getText().trim().equals("Mob No.")){
            kin_mobile_numberA.setText("");
        }
    }//GEN-LAST:event_kin_mobile_numberAFocusGained

    private void kin_mobile_numberAFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_kin_mobile_numberAFocusLost
        // TODO add your handling code here:
        if(kin_mobile_numberA.getText().trim().equals("")){
            kin_mobile_numberA.setText("Mob No.");
            kin_mobile_numberA.setForeground(Color.decode("#666666"));
        }
    }//GEN-LAST:event_kin_mobile_numberAFocusLost

    private void kin_relation_AFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_kin_relation_AFocusGained
        // TODO add your handling code here:
        kin_relation_A.setForeground(Color.decode("#000000"));
        if(kin_relation_A.getText().trim().equals("Relation")){
            kin_relation_A.setText("");
        }
    }//GEN-LAST:event_kin_relation_AFocusGained

    private void kin_relation_AFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_kin_relation_AFocusLost
        // TODO add your handling code here:
        if(kin_relation_A.getText().trim().equals("")){
            kin_relation_A.setText("Relation");
            kin_relation_A.setForeground(Color.decode("#666666"));
        }
    }//GEN-LAST:event_kin_relation_AFocusLost

    private void kin_PAN_AFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_kin_PAN_AFocusGained
        // TODO add your handling code here:
        kin_PAN_A.setForeground(Color.decode("#000000"));
        if(kin_PAN_A.getText().trim().equals("PAN")){
            kin_PAN_A.setText("");
        }

    }//GEN-LAST:event_kin_PAN_AFocusGained

    private void kin_PAN_AFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_kin_PAN_AFocusLost
        // TODO add your handling code here:
        if(kin_PAN_A.getText().trim().equals("")){
            kin_PAN_A.setText("PAN");
            kin_PAN_A.setForeground(Color.decode("#666666"));
        }

    }//GEN-LAST:event_kin_PAN_AFocusLost

    private void kin_aadharAFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_kin_aadharAFocusGained
        // TODO add your handling code here:
        kin_aadharA.setForeground(Color.decode("#000000"));
        if(kin_aadharA.getText().trim().equals("Aadhar")){
            kin_aadharA.setText("");
        }

    }//GEN-LAST:event_kin_aadharAFocusGained

    private void kin_aadharAFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_kin_aadharAFocusLost
        // TODO add your handling code here:
        if(kin_aadharA.getText().trim().equals("")){
            kin_aadharA.setText("Aadhar");
            kin_aadharA.setForeground(Color.decode("#666666"));
        }

    }//GEN-LAST:event_kin_aadharAFocusLost

    private void kin_mailAFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_kin_mailAFocusGained
        // TODO add your handling code here:
        kin_mailA.setForeground(Color.decode("#000000"));
        if(kin_mailA.getText().trim().equals("E-Mail")){
            kin_mailA.setText("");
        }

    }//GEN-LAST:event_kin_mailAFocusGained

    private void kin_mailAFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_kin_mailAFocusLost
        // TODO add your handling code here:
        if(kin_mailA.getText().trim().equals("")){
            kin_mailA.setText("E-Mail");
            kin_mailA.setForeground(Color.decode("#666666"));
        }

    }//GEN-LAST:event_kin_mailAFocusLost

    private void kin_account_numberAFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_kin_account_numberAFocusGained
        // TODO add your handling code here:
         kin_account_numberA.setForeground(Color.decode("#000000"));
        if(kin_account_numberA.getText().trim().equals("Account Number")){
            kin_account_numberA.setText("");
        }

    }//GEN-LAST:event_kin_account_numberAFocusGained

    private void kin_account_numberAFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_kin_account_numberAFocusLost
        // TODO add your handling code here:
        if(kin_account_numberA.getText().trim().equals("")){
            kin_account_numberA.setText("Account Number");
            kin_account_numberA.setForeground(Color.decode("#666666"));
        }

    }//GEN-LAST:event_kin_account_numberAFocusLost

    private void kin_bank_nameAFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_kin_bank_nameAFocusGained
        // TODO add your handling code here:
         kin_bank_nameA.setForeground(Color.decode("#000000"));
        if(kin_bank_nameA.getText().trim().equals("Bank Name")){
            kin_bank_nameA.setText("");
        }

    }//GEN-LAST:event_kin_bank_nameAFocusGained

    private void kin_bank_nameAFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_kin_bank_nameAFocusLost
        // TODO add your handling code here:
        if(kin_bank_nameA.getText().trim().equals("")){
            kin_bank_nameA.setText("Bank Name");
            kin_bank_nameA.setForeground(Color.decode("#666666"));
        }

    }//GEN-LAST:event_kin_bank_nameAFocusLost

    private void kin_branchAFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_kin_branchAFocusGained
        // TODO add your handling code here:
         kin_branchA.setForeground(Color.decode("#000000"));
        if(kin_branchA.getText().trim().equals("Branch")){
            kin_branchA.setText("");
        }

    }//GEN-LAST:event_kin_branchAFocusGained

    private void kin_branchAFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_kin_branchAFocusLost
        // TODO add your handling code here:
         if(kin_branchA.getText().trim().equals("")){
            kin_branchA.setText("Branch");
            kin_branchA.setForeground(Color.decode("#666666"));
        }

    }//GEN-LAST:event_kin_branchAFocusLost

    private void kin_IFSC_AFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_kin_IFSC_AFocusGained
        // TODO add your handling code here:
         kin_IFSC_A.setForeground(Color.decode("#000000"));
        if(kin_IFSC_A.getText().trim().equals("IFSC")){
            kin_IFSC_A.setText("");
        }

    }//GEN-LAST:event_kin_IFSC_AFocusGained

    private void kin_IFSC_AFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_kin_IFSC_AFocusLost
        // TODO add your handling code here:
         if(kin_IFSC_A.getText().trim().equals("")){
            kin_IFSC_A.setText("IFSC");
            kin_IFSC_A.setForeground(Color.decode("#666666"));
        }

    }//GEN-LAST:event_kin_IFSC_AFocusLost

    private void villageAFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_villageAFocusGained
        // TODO add your handling code here:
        villageA.setForeground(Color.decode("#000000"));
        if(villageA.getText().trim().equals("Vill")){
            villageA.setText("");
        }

    }//GEN-LAST:event_villageAFocusGained

    private void villageAFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_villageAFocusLost
        // TODO add your handling code here:
        if(villageA.getText().trim().equals("")){
            villageA.setText("Vill");
            villageA.setForeground(Color.decode("#666666"));
        }


    }//GEN-LAST:event_villageAFocusLost

    private void tehAFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_tehAFocusGained
        // TODO add your handling code here:
        tehA.setForeground(Color.decode("#000000"));
        if(tehA.getText().trim().equals("Teh")){
            tehA.setText("");
        }
    }//GEN-LAST:event_tehAFocusGained

    private void tehAFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_tehAFocusLost
        // TODO add your handling code here:
         if(tehA.getText().trim().equals("")){
            tehA.setText("Teh");
            tehA.setForeground(Color.decode("#666666"));
        }

    }//GEN-LAST:event_tehAFocusLost

    private void PO_AFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_PO_AFocusGained
        // TODO add your handling code here:
        PO_A.setForeground(Color.decode("#000000"));
        if(PO_A.getText().trim().equals("PO")){
            PO_A.setText("");
        }

    }//GEN-LAST:event_PO_AFocusGained

    private void PO_AFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_PO_AFocusLost
        // TODO add your handling code here:
        if(PO_A.getText().trim().equals("")){
            PO_A.setText("PO");
            PO_A.setForeground(Color.decode("#666666"));
        }

    }//GEN-LAST:event_PO_AFocusLost

    private void stateAFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_stateAFocusGained
        // TODO add your handling code here:
        stateA.setForeground(Color.decode("#000000"));
        if(stateA.getText().trim().equals("State")){
            stateA.setText("");
        }

    }//GEN-LAST:event_stateAFocusGained

    private void stateAFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_stateAFocusLost
        // TODO add your handling code here:
        if(stateA.getText().trim().equals("")){
            stateA.setText("State");
            stateA.setForeground(Color.decode("#666666"));
        }

    }//GEN-LAST:event_stateAFocusLost

    private void distAFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_distAFocusGained
        // TODO add your handling code here:
        distA.setForeground(Color.decode("#000000"));
        if(distA.getText().trim().equals("Dist")){
            distA.setText("");
        }

    }//GEN-LAST:event_distAFocusGained

    private void distAFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_distAFocusLost
        // TODO add your handling code here:
        if(distA.getText().trim().equals("")){
            distA.setText("Dist");
            distA.setForeground(Color.decode("#666666"));
        }

    }//GEN-LAST:event_distAFocusLost

    private void PIN_AFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_PIN_AFocusGained
        // TODO add your handling code here:
         PIN_A.setForeground(Color.decode("#000000"));
        if(PIN_A.getText().trim().equals("PIN")){
            PIN_A.setText("");
        }

    }//GEN-LAST:event_PIN_AFocusGained

    private void PIN_AFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_PIN_AFocusLost
        // TODO add your handling code here:
        if(PIN_A.getText().trim().equals("")){
            PIN_A.setText("PIN");
            PIN_A.setForeground(Color.decode("#666666"));
        }
    }//GEN-LAST:event_PIN_AFocusLost

    private void indl_Icard_numberBFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_indl_Icard_numberBFocusGained
        // TODO add your handling code here:
        indl_Icard_numberB.setForeground(Color.decode("#000000"));
        if(indl_Icard_numberB.getText().trim().equals("ICardNumber")){
            indl_Icard_numberB.setText("");
        }

    }//GEN-LAST:event_indl_Icard_numberBFocusGained

    private void indl_Icard_numberBFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_indl_Icard_numberBFocusLost
        // TODO add your handling code here:
        if(indl_Icard_numberB.getText().trim().equals("")){
            indl_Icard_numberB.setText("ICardNumber");
            indl_Icard_numberB.setForeground(Color.decode("#666666"));
        }

    }//GEN-LAST:event_indl_Icard_numberBFocusLost

    private void okayCMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_okayCMouseClicked
        // TODO add your handling code here:
        String sno = service_number_textfieldC.getText().toUpperCase();
        if(sno==null || sno.trim().isEmpty()){
            JOptionPane.showMessageDialog(null,"Please Enter Army Number");
        }
        else{
            try{  
            scaleImage();
            Statement st = con.createStatement();
            String query="Select count(*) as count from attach_in where service_no = '"+sno+"'";
            
            String q1_attachin = "select Name,Rank_,Company,Medical_Category,DOB,Date_of_Joining,Contact_no,Trade,Unit,Location,Armed_Services,Courses_done,kin_name,kin_relation,kin_contact,Address from attach_in where Service_no='"+sno+"'";
            String q2_attachout = "select Name,Rank_,Company,Medical_Category,DOB,Date_of_Joining,Contact_no,Location,Courses_done,kin_name,kin_relation,kin_contact,Date_of_Attachment,Address from attach_out where Service_no='"+sno+"'";
            int count=0;
            ResultSet rs=st.executeQuery(query);
            if (rs.next()){
                count=rs.getInt("count");
            }
            String Service_no,Trade_,Unit_,Location_,Armed, Name,doa, dob, Rank_, Company,Contact_no, indl_id, indl_aadhar,indl_pan, indl_bank_acc, indl_bank_name, indl_ifsc,indl_branch_name, indl_email, Courses_done, address, doj, Medical_category, Kin_name, Kin_contact,Kin_relation, Kin_aadhar, Kin_pan, Kin_bank_acc, Kin_bank_name, Kin_ifsc, Kin_branch_name, Kin_email;
            String rec="";
            int flag=0;
            if(count!=0){
                DateIN_ScrollPaneC.setVisible(true);
                DateOUT_ScrollPaneC.setVisible(false);
                rs=st.executeQuery(q1_attachin);
    //            if(rs.next()){
                    //System.out.println("inside postin_if");
                    flag=1;
                    while(rs.next()){
                      //  System.out.println("inside postin");
                        System.out.print("\nInside attach In");
                        Name=rs.getString("Name");
                        dob=rs.getString("DOB");
                        
                        
                        
                        
                        Rank_=rs.getString("Rank_");
                        Company=rs.getString("Company");
                        Contact_no=rs.getString("Contact_no");
                        Courses_done=rs.getString("Courses_done");
                        address=rs.getString("Address");
                        doj=rs.getString("Date_of_Joining");
                        Medical_category=rs.getString("Medical_Category");
                        Kin_name=rs.getString("kin_name");
                        Kin_contact=rs.getString("kin_contact");
                        Kin_relation=rs.getString("kin_relation");
                        System.out.print("\nInside attach In near trade");
                        Armed =rs.getString("Armed_Services");
                        Trade_ =rs.getString("Trade");
                        Unit_ =rs.getString("Unit");
                        Location_ =rs.getString("Location");
                       
                        String[] ad = address.split(",");
                        System.out.print(Name);
                        name.setText(Name);
                        rank.setText(Rank_);
                        company.setText(Company);
                        DOB.setText(dob);
                        tradeC.setText(Trade_);
                        unitC.setText(Unit_);
                        locationC.setText(Location_);
                        AS_C.setText(Armed);
                        
                        indl_mobileC.setText(Contact_no);
                        courses_doneC.setText(Courses_done);
                        NOK_nameC.setText(Kin_name);
                        NOK_relationC.setText(Kin_relation);
                        medical_category.setText(Medical_category);
                        NOK_mobileC.setText(Kin_contact);
                        villC.setText(ad[0]);
                        PO_C.setText(ad[3]);
                        tehsil.setText(ad[2]);
                        distC.setText(ad[1]);
                        stateC.setText(ad[4]);
                        PIN_C.setText(ad[5]);
                        attachment_labelC.setText("Attach In");

                    }   
                    int counter=0;
                    String q="select Date_of_Joining from attach_in where Service_no='"+sno+"'";
                    rs=st.executeQuery(q);
                    attach_i.setRowCount(0);
                    String ddoojj="";
                    while(rs.next()){
                        counter=counter+1;
                        ddoojj=rs.getString("Date_of_Joining");
                        attach_i.addRow(new Object[]{counter,ddoojj});
                    }
                
            }
            query="Select count(*) as count from attach_out where service_no = '"+sno+"'";
            rs=st.executeQuery(query);
            if (rs.next()){
                count=rs.getInt("count");
            }
            if(count!=0){
                DateIN_ScrollPaneC.setVisible(false);
                DateOUT_ScrollPaneC.setVisible(true);
                rs=st.executeQuery(q2_attachout);
                    flag=1;
                    //System.out.println("inside postout_if");
                    while(rs.next()){
                        System.out.println("inside postout");
                        Name = rs.getString("Name");
                        //Trade_ =rs.getString("Trade");
                        //Unit_ =rs.getString("Unit");
                        Location_ =rs.getString("Location");
                        //Armed =rs.getString("Armed_Services");
                        dob=rs.getString("DOB");
                        Rank_=rs.getString("Rank_");
                        Company=rs.getString("Company");
                        doa = rs.getString("Date_of_Attachment");
                        Contact_no=rs.getString("Contact_no");
                        Courses_done=rs.getString("Courses_done");
                        address=rs.getString("Address");
                        doj=rs.getString("Date_of_Joining");
                        Medical_category=rs.getString("Medical_Category");
                        Kin_name=rs.getString("kin_name");
                        Kin_contact=rs.getString("kin_contact");
                        Kin_relation=rs.getString("kin_relation");
                        String[] ad = address.split(",");
                        name.setText(Name);
                        rank.setText(Rank_);
                        company.setText(Company);
                        DOB.setText(dob);
                        
                        tradeC.setText("> XXXXXXX");
                        unitC.setText("> XXXXXXX");
                        locationC.setText(Location_);
                        AS_C.setText("> XXXXXXX");
                        
                        indl_mobileC.setText(Contact_no);
                        courses_doneC.setText(Courses_done);
                        NOK_nameC.setText(Kin_name);
                        NOK_relationC.setText(Kin_relation);
                        medical_category.setText(Medical_category);
                        NOK_mobileC.setText(Kin_contact);
                        
                        villC.setText(ad[0]);
                        PO_C.setText(ad[3]);
                        tehsil.setText(ad[2]);
                        distC.setText(ad[1]);
                        stateC.setText(ad[4]);
                        PIN_C.setText(ad[5]);
                        attachment_labelC.setText("Attach Out");


                    }
                    int counter=0;
                    String q="select Date_of_Attachment,Date_of_Joining from attach_out where Service_no='"+sno+"'";
                    rs=st.executeQuery(q);
                    attach_o.setRowCount(0);
                    String ddoojj="",ddooll="";
                    while(rs.next()){
                        counter=counter+1;
                        ddoojj=rs.getString("Date_of_Joining");
                        ddooll=rs.getString("Date_of_Attachment");
                        attach_o.addRow(new Object[]{counter,ddooll,ddoojj});
                    }
            }
            else if(flag!= 1){
                 JOptionPane.showMessageDialog(null,"Data not Found!");
                    name.setText(">XXXXXXX");
                    rank.setText(">XXXXXXX");
                    company.setText(">XXXXXXX");
                    DOB.setText(">XXXXXXX");
                    
                    
                    indl_mobileC.setText(">XXXXXXX");
                    courses_doneC.setText(">XXXXXXX");
                    NOK_nameC.setText(">XXXXXXX");
                    NOK_relationC.setText(">XXXXXXX");
                    medical_category.setText(">XXXXXXX");
                    NOK_mobileC.setText(">XXXXXXX");
                    
                    tradeC.setText(">XXXXXXX");
                    unitC.setText(">XXXXXXX");
                    locationC.setText(">XXXXXXX");
                    villC.setText(">XXXXXXX");
                    PO_C.setText(">XXXXXXX");
                    tehsil.setText(">XXXXXXX");
                    distC.setText(">XXXXXXX");
                    stateC.setText(">XXXXXXX");
                    PIN_C.setText(">XXXXXXX");

            
          }
            }catch(Exception e){ 
                System.out.println(e);
                JOptionPane.showMessageDialog(null,"Data not Found");
            }
        }
        
    }//GEN-LAST:event_okayCMouseClicked

    private void indl_PAN_BFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_indl_PAN_BFocusGained
        // TODO add your handling code here:
        indl_PAN_B.setForeground(Color.decode("#000000"));
        if(indl_PAN_B.getText().trim().equals("PAN")){
            indl_PAN_B.setText("");
        }

    }//GEN-LAST:event_indl_PAN_BFocusGained

    private void indl_PAN_BFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_indl_PAN_BFocusLost
        // TODO add your handling code here:
        if(indl_PAN_B.getText().trim().equals("")){
            indl_PAN_B.setText("PAN");
            indl_PAN_B.setForeground(Color.decode("#666666"));
        }

    }//GEN-LAST:event_indl_PAN_BFocusLost

    private void indl_aadharBFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_indl_aadharBFocusGained
        // TODO add your handling code here:
        indl_aadharB.setForeground(Color.decode("#000000"));
        if(indl_aadharB.getText().trim().equals("Aadhar")){
            indl_aadharB.setText("");
        }

    }//GEN-LAST:event_indl_aadharBFocusGained

    private void indl_aadharBFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_indl_aadharBFocusLost
        // TODO add your handling code here:
        if(indl_aadharB.getText().trim().equals("")){
            indl_aadharB.setText("Aadhar");
            indl_aadharB.setForeground(Color.decode("#666666"));
        }

    }//GEN-LAST:event_indl_aadharBFocusLost

    private void indl_mailBFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_indl_mailBFocusGained
        // TODO add your handling code here:
        indl_mailB.setForeground(Color.decode("#000000"));
        if(indl_mailB.getText().trim().equals("E-Mail")){
            indl_mailB.setText("");
        }

    }//GEN-LAST:event_indl_mailBFocusGained

    private void indl_mailBFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_indl_mailBFocusLost
        // TODO add your handling code here:
         if(indl_mailB.getText().trim().equals("")){
            indl_mailB.setText("E-Mail");
            indl_mailB.setForeground(Color.decode("#666666"));
        }

    }//GEN-LAST:event_indl_mailBFocusLost

    private void indl_account_numberBFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_indl_account_numberBFocusGained
        // TODO add your handling code here:
         indl_account_numberB.setForeground(Color.decode("#000000"));
        if(indl_account_numberB.getText().trim().equals("Account Number")){
            indl_account_numberB.setText("");
        }
    }//GEN-LAST:event_indl_account_numberBFocusGained

    private void indl_account_numberBFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_indl_account_numberBFocusLost
        // TODO add your handling code here:
        if(indl_account_numberB.getText().trim().equals("")){
            indl_account_numberB.setText("Account Number");
            indl_account_numberB.setForeground(Color.decode("#666666"));
        }

    }//GEN-LAST:event_indl_account_numberBFocusLost

    private void indl_bank_nameBFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_indl_bank_nameBFocusGained
        // TODO add your handling code here:
         indl_bank_nameB.setForeground(Color.decode("#000000"));
        if(indl_bank_nameB.getText().trim().equals("Bank Name")){
            indl_bank_nameB.setText("");
        }

    }//GEN-LAST:event_indl_bank_nameBFocusGained

    private void indl_bank_nameBFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_indl_bank_nameBFocusLost
        // TODO add your handling code here:
        if(indl_bank_nameB.getText().trim().equals("")){
            indl_bank_nameB.setText("Bank Name");
            indl_bank_nameB.setForeground(Color.decode("#666666"));
        }


    }//GEN-LAST:event_indl_bank_nameBFocusLost

    private void indl_branchBFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_indl_branchBFocusGained
        // TODO add your handling code here:
        indl_branchB.setForeground(Color.decode("#000000"));
        if(indl_branchB.getText().trim().equals("Branch")){
            indl_branchB.setText("");
        }

    }//GEN-LAST:event_indl_branchBFocusGained

    private void indl_branchBFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_indl_branchBFocusLost
        // TODO add your handling code here:
        if(indl_branchB.getText().trim().equals("")){
            indl_branchB.setText("Branch");
            indl_branchB.setForeground(Color.decode("#666666"));
        }

    }//GEN-LAST:event_indl_branchBFocusLost

    private void indl_IFSC_BFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_indl_IFSC_BFocusGained
        // TODO add your handling code here:
        indl_IFSC_B.setForeground(Color.decode("#000000"));
        if(indl_IFSC_B.getText().trim().equals("IFSC")){
            indl_IFSC_B.setText("");
        }

    }//GEN-LAST:event_indl_IFSC_BFocusGained

    private void indl_IFSC_BFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_indl_IFSC_BFocusLost
        // TODO add your handling code here:
         if(indl_IFSC_B.getText().trim().equals("")){
            indl_IFSC_B.setText("IFSC");
            indl_IFSC_B.setForeground(Color.decode("#666666"));
        }

    }//GEN-LAST:event_indl_IFSC_BFocusLost

    private void kin_NOK_BFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_kin_NOK_BFocusGained
        // TODO add your handling code here:
        kin_NOK_B.setForeground(Color.decode("#000000"));
        if(kin_NOK_B.getText().trim().equals("NOK")){
            kin_NOK_B.setText("");
        }

    }//GEN-LAST:event_kin_NOK_BFocusGained

    private void kin_NOK_BFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_kin_NOK_BFocusLost
        // TODO add your handling code here:
        if(kin_NOK_B.getText().trim().equals("")){
            kin_NOK_B.setText("NOK");
            kin_NOK_B.setForeground(Color.decode("#666666"));
        }
    }//GEN-LAST:event_kin_NOK_BFocusLost

    private void kin_mobile_numberBFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_kin_mobile_numberBFocusGained
        // TODO add your handling code here:
         kin_mobile_numberB.setForeground(Color.decode("#000000"));
        if(kin_mobile_numberB.getText().trim().equals("Mob No.")){
            kin_mobile_numberB.setText("");
        }

    }//GEN-LAST:event_kin_mobile_numberBFocusGained

    private void kin_mobile_numberBFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_kin_mobile_numberBFocusLost
        // TODO add your handling code here:
         if(kin_mobile_numberB.getText().trim().equals("")){
            kin_mobile_numberB.setText("Mob No.");
            kin_mobile_numberB.setForeground(Color.decode("#666666"));
        }
    }//GEN-LAST:event_kin_mobile_numberBFocusLost

    private void kin_relation_BFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_kin_relation_BFocusGained
        // TODO add your handling code here:
        kin_relation_B.setForeground(Color.decode("#000000"));
        if(kin_relation_B.getText().trim().equals("Relation")){
            kin_relation_B.setText("");
        }

    }//GEN-LAST:event_kin_relation_BFocusGained

    private void kin_relation_BFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_kin_relation_BFocusLost
        // TODO add your handling code here:
         if(kin_relation_B.getText().trim().equals("")){
            kin_relation_B.setText("Relation");
            kin_relation_B.setForeground(Color.decode("#666666"));
        }

    }//GEN-LAST:event_kin_relation_BFocusLost

    private void kin_PAN_BFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_kin_PAN_BFocusGained
        // TODO add your handling code here:
        kin_PAN_B.setForeground(Color.decode("#000000"));
        if(kin_PAN_B.getText().trim().equals("PAN")){
            kin_PAN_B.setText("");
        }

    }//GEN-LAST:event_kin_PAN_BFocusGained

    private void kin_PAN_BFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_kin_PAN_BFocusLost
        // TODO add your handling code here:
        if(kin_PAN_B.getText().trim().equals("")){
            kin_PAN_B.setText("PAN");
            kin_PAN_B.setForeground(Color.decode("#666666"));
        }

    }//GEN-LAST:event_kin_PAN_BFocusLost

    private void kin_aadharBFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_kin_aadharBFocusGained
        // TODO add your handling code here:
         kin_aadharB.setForeground(Color.decode("#000000"));
        if(kin_aadharB.getText().trim().equals("Aadhar")){
            kin_aadharB.setText("");
        }

    }//GEN-LAST:event_kin_aadharBFocusGained

    private void kin_aadharBFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_kin_aadharBFocusLost
        // TODO add your handling code here:
         if(kin_aadharB.getText().trim().equals("")){
            kin_aadharB.setText("Aadhar");
            kin_aadharB.setForeground(Color.decode("#666666"));
        }

    }//GEN-LAST:event_kin_aadharBFocusLost

    private void kin_mailBFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_kin_mailBFocusGained
        // TODO add your handling code here:
         kin_mailB.setForeground(Color.decode("#000000"));
        if(kin_mailB.getText().trim().equals("E-Mail")){
            kin_mailB.setText("");
        }

    }//GEN-LAST:event_kin_mailBFocusGained

    private void kin_mailBFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_kin_mailBFocusLost
        // TODO add your handling code here:
        if(kin_mailB.getText().trim().equals("")){
            kin_mailB.setText("E-Mail");
            kin_mailB.setForeground(Color.decode("#666666"));
        }

    }//GEN-LAST:event_kin_mailBFocusLost

    private void kin_account_numberBFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_kin_account_numberBFocusGained
        // TODO add your handling code here:
         kin_account_numberB.setForeground(Color.decode("#000000"));
        if(kin_account_numberB.getText().trim().equals("Account Number")){
            kin_account_numberB.setText("");
        }

    }//GEN-LAST:event_kin_account_numberBFocusGained

    private void kin_account_numberBFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_kin_account_numberBFocusLost
        // TODO add your handling code here:
        
         if(kin_account_numberB.getText().trim().equals("")){
            kin_account_numberB.setText("Account Number");
            kin_account_numberB.setForeground(Color.decode("#666666"));
        }
    }//GEN-LAST:event_kin_account_numberBFocusLost

    private void kin_bank_nameBFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_kin_bank_nameBFocusGained
        // TODO add your handling code here:
         kin_bank_nameB.setForeground(Color.decode("#000000"));
        if(kin_bank_nameB.getText().trim().equals("Bank Name")){
            kin_bank_nameB.setText("");
        }
        
        

    }//GEN-LAST:event_kin_bank_nameBFocusGained

    private void kin_bank_nameBFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_kin_bank_nameBFocusLost
        // TODO add your handling code here:
        if(kin_bank_nameB.getText().trim().equals("")){
            kin_bank_nameB.setText("Bank Name");
            kin_bank_nameB.setForeground(Color.decode("#666666"));
        }
    }//GEN-LAST:event_kin_bank_nameBFocusLost

    private void kin_branchBFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_kin_branchBFocusGained
        // TODO add your handling code here:
         kin_branchB.setForeground(Color.decode("#000000"));
        if(kin_branchB.getText().trim().equals("Branch")){
            kin_branchB.setText("");
        }

    }//GEN-LAST:event_kin_branchBFocusGained

    private void kin_branchBFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_kin_branchBFocusLost
        // TODO add your handling code here:
        if(kin_branchB.getText().trim().equals("")){
            kin_branchB.setText("Branch");
            kin_branchB.setForeground(Color.decode("#666666"));
        }
    }//GEN-LAST:event_kin_branchBFocusLost

    private void kin_IFSC_BFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_kin_IFSC_BFocusGained
        // TODO add your handling code here:
        kin_IFSC_B.setForeground(Color.decode("#000000"));
        if(kin_IFSC_B.getText().trim().equals("IFSC")){
            kin_IFSC_B.setText("");
        }

    }//GEN-LAST:event_kin_IFSC_BFocusGained

    private void kin_IFSC_BFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_kin_IFSC_BFocusLost
        // TODO add your handling code here:
        if(kin_IFSC_B.getText().trim().equals("")){
            kin_IFSC_B.setText("IFSC");
            kin_IFSC_B.setForeground(Color.decode("#666666"));
        }

    }//GEN-LAST:event_kin_IFSC_BFocusLost

    private void villageBFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_villageBFocusGained
        // TODO add your handling code here:
        villageB.setForeground(Color.decode("#000000"));
        if(villageB.getText().trim().equals("Vill")){
            villageB.setText("");
        }

    }//GEN-LAST:event_villageBFocusGained

    private void villageBFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_villageBFocusLost
        // TODO add your handling code here:
        if(villageB.getText().trim().equals("")){
            villageB.setText("Vill");
            villageB.setForeground(Color.decode("#666666"));
        }
    }//GEN-LAST:event_villageBFocusLost

    private void tehBFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_tehBFocusGained
        // TODO add your handling code here:
        tehB.setForeground(Color.decode("#000000"));
        if(tehB.getText().trim().equals("Teh")){
            tehB.setText("");
        }

    }//GEN-LAST:event_tehBFocusGained

    private void tehBFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_tehBFocusLost
        // TODO add your handling code here:
        if(tehB.getText().trim().equals("")){
            tehB.setText("Teh");
            tehB.setForeground(Color.decode("#666666"));
        }

    }//GEN-LAST:event_tehBFocusLost

    private void PO_BFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_PO_BFocusGained
        // TODO add your handling code here:
        PO_B.setForeground(Color.decode("#000000"));
        if(PO_B.getText().trim().equals("PO")){
            PO_B.setText("");
        }

    }//GEN-LAST:event_PO_BFocusGained

    private void PO_BFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_PO_BFocusLost
        // TODO add your handling code here:
         if(PO_B.getText().trim().equals("")){
            PO_B.setText("PO");
            PO_B.setForeground(Color.decode("#666666"));
        }

    }//GEN-LAST:event_PO_BFocusLost

    private void stateBFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_stateBFocusGained
        // TODO add your handling code here:
         stateB.setForeground(Color.decode("#000000"));
        if(stateB.getText().trim().equals("State")){
            stateB.setText("");
        }

    }//GEN-LAST:event_stateBFocusGained

    private void stateBFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_stateBFocusLost
        // TODO add your handling code here:
        if(stateB.getText().trim().equals("")){
            stateB.setText("State");
            stateB.setForeground(Color.decode("#666666"));
        }

    }//GEN-LAST:event_stateBFocusLost

    private void distBFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_distBFocusGained
        // TODO add your handling code here:
        distB.setForeground(Color.decode("#000000"));
        if(distB.getText().trim().equals("Dist")){
            distB.setText("");
        }

    }//GEN-LAST:event_distBFocusGained

    private void distBFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_distBFocusLost
        // TODO add your handling code here:
         if(distB.getText().trim().equals("")){
            distB.setText("Dist");
            distB.setForeground(Color.decode("#666666"));
        }

    }//GEN-LAST:event_distBFocusLost

    private void PIN_BFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_PIN_BFocusGained
        // TODO add your handling code here:
         PIN_B.setForeground(Color.decode("#000000"));
        if(PIN_B.getText().trim().equals("PIN")){
            PIN_B.setText("");
        }

    }//GEN-LAST:event_PIN_BFocusGained

    private void PIN_BFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_PIN_BFocusLost
        // TODO add your handling code here:
        if(PIN_B.getText().trim().equals("")){
            PIN_B.setText("PIN");
            PIN_B.setForeground(Color.decode("#666666"));
        }

    }//GEN-LAST:event_PIN_BFocusLost

    private void menuD_labelMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_menuD_labelMouseClicked
        // TODO add your handling code here:
        menuD.setBackground(new java.awt.Color(85,65,118));
        menuB.setBackground(new java.awt.Color(64,43,100));
        menuA.setBackground(new java.awt.Color(64,43,100));
        menuC.setBackground(new java.awt.Color(64,43,100));
        menuE.setBackground(new java.awt.Color(64,43,100));
        
        print_buttonC.setVisible(false);
        print_buttonD.setVisible(true);
        print_buttonE.setVisible(false);
        refresh_buttonD.setVisible(true);
        refresh_buttonE.setVisible(false);
        fullscreen_buttonD.setVisible(true);
        fullscreen_buttonE.setVisible(false);
        remove_buttonC.setVisible(false);

        fullscreen_panelD.setVisible(false);
        fullscreen_panelE.setVisible(false);
        ScrollPaneA.setVisible(false);
        ScrollPaneB.setVisible(false);
        ScrollPaneC.setVisible(false);
        panelD.setVisible(true);
        panelE.setVisible(false);
        viewall("attach_in");
        
        edit_buttonG.setVisible(false);
        menuF.setBackground(new java.awt.Color(64,43,100));
        ScrollPaneF.setVisible(false);
        panelG.setVisible(false);
    }//GEN-LAST:event_menuD_labelMouseClicked

    private void menuE_labelMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_menuE_labelMouseClicked
        // TODO add your handling code here:
        fullscreen_panelD.setVisible(false);
        fullscreen_panelE.setVisible(false);
        ScrollPaneA.setVisible(false);
        ScrollPaneB.setVisible(false);
        ScrollPaneC.setVisible(false);
        panelD.setVisible(false);
        panelE.setVisible(true);
        
        print_buttonC.setVisible(false);
        print_buttonD.setVisible(false);
        print_buttonE.setVisible(true);
        refresh_buttonD.setVisible(false);
        refresh_buttonE.setVisible(true);
        fullscreen_buttonD.setVisible(false);
        fullscreen_buttonE.setVisible(true);
        remove_buttonC.setVisible(false);

        menuE.setBackground(new java.awt.Color(85,65,118));
        menuA.setBackground(new java.awt.Color(64,43,100));
        menuB.setBackground(new java.awt.Color(64,43,100));
        menuC.setBackground(new java.awt.Color(64,43,100));
        menuD.setBackground(new java.awt.Color(64,43,100));
        viewall("attach_out");
        
        edit_buttonG.setVisible(false);
        menuF.setBackground(new java.awt.Color(64,43,100));
        ScrollPaneF.setVisible(false);
        panelG.setVisible(false);
    }//GEN-LAST:event_menuE_labelMouseClicked

    private void fullscreen_labelDMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_fullscreen_labelDMouseClicked
        // TODO add your handling code here:
        fullscreen_panelD.setVisible(true);
        bg.setVisible(false);
        viewall("attach_in");
    }//GEN-LAST:event_fullscreen_labelDMouseClicked

    private void refresh_buttonDMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_refresh_buttonDMouseClicked
        // TODO add your handling code here:
        viewall("attach_in");
    }//GEN-LAST:event_refresh_buttonDMouseClicked

    private void refresh_buttonEMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_refresh_buttonEMouseClicked
        // TODO add your handling code here:
        viewall("attach_out");
    }//GEN-LAST:event_refresh_buttonEMouseClicked

    private void edit_buttonGMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_edit_buttonGMouseClicked
//        ScrollPaneF.setVisible(true);
//        panelG.setVisible(false);
//        edit_buttonG.setVisible(false);
        String sno=service_numberG.getText().toLowerCase();
        if(sno==null || sno.trim().isEmpty()){
            JOptionPane.showMessageDialog(null,"Please Enter Army Number");
        }
        else{
            try{
                Statement st = con.createStatement();
                
                ResultSet rs = null;
                //int row_in = TableG_out.getSelectedRow();
                
               // System.out.print("\nPost out-"+row_out);
               // System.out.print("\nPost in-"+row_in);
                if(blue==1){
                    int row_in = TableG_IN.getSelectedRow();
                    if(row_in!=-1){   
                        Attachment_labelF.setText("Attachment In");
                        ScrollPaneF.setVisible(true);
                        panelG.setVisible(false);
                        edit_buttonG.setVisible(false);
                    }
                        
                    
                    
                //int row_out = TableG_in.getSelectedRow();
                    System.out.print("\nInside attach in");
                    DOA_labelF.setText("");
                    DOA.setText("");
                    System.out.print("\n check_1");
                    String doj=upd_small_attach_in.getValueAt(row_in,3).toString();
                    String unique=sno+","+doj;
                    System.out.print("\n"+unique+" check_2");
                    String query="select*from attach_in where serv_doj='"+unique+"'";
                    rs=st.executeQuery(query);
                    while(rs.next()){
                        service_number.setText(rs.getString("Service_no"));
                        DOJ.setText(rs.getString("Date_of_Joining"));
                        company_textfieldF.setText(rs.getString("Company"));
                        name_textfieldF.setText(rs.getString("Name"));
                        courses_textfieldF.setText(rs.getString("Courses_done"));
                        String con =rs.getString("Contact_no");
                        String[] c = con.split(" ");
                        contact_textfieldF.setText(c[1]);
                        
                        rank_comboboxF.setSelectedItem(rs.getString("Rank_"));
                        medical_category_textfieldF.setText(rs.getString("Medical_category"));
                         //address
                        String add = rs.getString("Address");
                        String[] ad = add.split(",");
                        villageF.setText(ad[0]);
                        PO_F.setText(ad[3]);
                        distF.setText(ad[1]);
                        tehF.setText(ad[2]);
                        stateF.setText(ad[4]);
                        PIN_F.setText(ad[5]);
                        System.out.print("\nPIN-"+ad[5]);
                        //indl records
                        indl_Icard_numberF.setText(rs.getString("indl_id"));
                        indl_PAN_F.setText(rs.getString("indl_pan"));
                        indl_aadharF.setText(rs.getString("indl_aadhar"));
                        indl_mailF.setText(rs.getString("indl_email"));
                        indl_account_numberF.setText(rs.getString("indl_bank_acc"));
                        indl_bank_nameF.setText(rs.getString("indl_bank_name"));
                        indl_branchF.setText(rs.getString("indl_branch_name"));
                        indl_IFSC_F.setText(rs.getString("indl_ifsc"));
                        kin_NOK_F.setText(rs.getString("kin_name"));
                        con =rs.getString("kin_contact");
                        c = con.split(" ");
                        kin_mobile_numberF.setText(c[1]);
                        kin_relation_F.setText(rs.getString("kin_relation"));
                        kin_PAN_F.setText(rs.getString("kin_pan"));
                        kin_aadharF.setText(rs.getString("kin_aadhar"));
                        kin_mailF.setText(rs.getString("kin_email"));
                        kin_account_numberF.setText(rs.getString("kin_bank_acc"));
                        kin_bank_nameF.setText(rs.getString("kin_bank_name"));
                        kin_branchF.setText(rs.getString("kin_branch_name"));
                        kin_IFSC_F.setText(rs.getString("kin_ifsc"));
                        
                        
                        kin_NOK_F.setForeground(Color.decode("#000000"));
                        kin_mobile_numberF.setForeground(Color.decode("#000000"));

                        kin_relation_F.setForeground(Color.decode("#000000"));
                        kin_PAN_F.setForeground(Color.decode("#000000"));
                        kin_aadharF.setForeground(Color.decode("#000000"));
                        kin_mailF.setForeground(Color.decode("#000000"));
                        kin_account_numberF.setForeground(Color.decode("#000000"));
                        kin_bank_nameF.setForeground(Color.decode("#000000"));
                        kin_branchF.setForeground(Color.decode("#000000"));
                        kin_IFSC_F.setForeground(Color.decode("#000000"));indl_Icard_numberF.setForeground(Color.decode("#000000"));
                        indl_aadharF.setForeground(Color.decode("#000000"));
                        indl_account_numberF.setForeground(Color.decode("#000000"));
                        indl_branchF.setForeground(Color.decode("#000000"));
                        indl_PAN_F.setForeground(Color.decode("#000000"));
                        indl_mailF.setForeground(Color.decode("#000000"));
                        indl_bank_nameF.setForeground(Color.decode("#000000"));
                        indl_IFSC_F.setForeground(Color.decode("#000000"));
                       
                        villageF.setForeground(Color.decode("#000000"));
                       
                        PO_F.setForeground(Color.decode("#000000"));
                       
                        distF.setForeground(Color.decode("#000000"));
                       
                        tehF.setForeground(Color.decode("#000000"));
                       
                        stateF.setForeground(Color.decode("#000000"));
                       
                        PIN_F.setForeground(Color.decode("#000000"));
                        
                        
                        
                        //dob
                        con=rs.getString("DOB");
                        c=con.split("-");
                        birth_year_comboboxF.setSelectedItem(c[2]);
                        birth_month_comboboxF.setSelectedItem(c[1]);
                        birth_date_comboboxF.setSelectedItem(c[0]);
                        System.out.print("\n"+c[0]+" "+c[1]+" "+c[2]);
                        
                        //Date of joining
                        con=rs.getString("Date_of_joining");
                        c=con.split("-");
                        DOJ_year_comboboxF.setSelectedItem(c[2]);
                        DOJ_month_comboboxF.setSelectedItem(c[1]);
                        DOJ_date_comboboxF.setSelectedItem(c[0]);
                        
                        DOA_year_comboboxF.setVisible(false);
                        DOA_month_comboboxF.setVisible(false);
                        DOA_date_comboboxF.setVisible(false);
                        DOA_labelF1.setVisible(false);
                    }
                    
                    
                    
                }
                
                
                else if(blue==2){
                    int row_out = TableG_OUT.getSelectedRow();
                    if(row_out!=-1){
                        Attachment_labelF.setText("Attachment Out");
                        ScrollPaneF.setVisible(true);
                        panelG.setVisible(false);
                        edit_buttonG.setVisible(false);
                    }
                    DOA_year_comboboxF.setVisible(true);
                    DOA_month_comboboxF.setVisible(true);
                    DOA_date_comboboxF.setVisible(true);
                    DOA_labelF1.setVisible(true);
                    DOA_labelF.setText("D-O-Attachment");
                    //rank1.setText("");
                    System.out.print("\n Enter attach_out");
                    String doa=upd_big_attach_in.getValueAt(row_out,4).toString();
                    String unique=sno+","+doa;
                    System.out.print("\n"+unique+" check_3");
                    String query="select*from attach_out where serv_doa='"+unique+"'";
                    rs=st.executeQuery(query);
                    while(rs.next()){
                        DOA.setText(rs.getString("Date_of_Attachment"));
                        service_number.setText(rs.getString("Service_no"));
                        DOJ.setText(rs.getString("Date_of_Joining"));
                        company_textfieldF.setText(rs.getString("Company"));
                        name_textfieldF.setText(rs.getString("Name"));
                        courses_textfieldF.setText(rs.getString("Courses_done"));
                        String con =rs.getString("Contact_no");
                        String[] c = con.split(" ");
                        contact_textfieldF.setText(c[1]);
                        
                        rank_comboboxF.setSelectedItem(rs.getString("Rank_"));
                        medical_category_textfieldF.setText(rs.getString("Medical_category"));
                         //address
                        String add = rs.getString("Address");
                        String[] ad = add.split(",");
                        villageF.setText(ad[0]);
                        PO_F.setText(ad[3]);
                        distF.setText(ad[1]);
                        tehF.setText(ad[2]);
                        stateF.setText(ad[4]);
                        PIN_F.setText(ad[5]);
                        System.out.print("\nPIN-"+ad[5]);
                        //indl records
                        indl_Icard_numberF.setText(rs.getString("indl_id"));
                        indl_PAN_F.setText(rs.getString("indl_pan"));
                        indl_aadharF.setText(rs.getString("indl_aadhar"));
                        indl_mailF.setText(rs.getString("indl_email"));
                        indl_account_numberF.setText(rs.getString("indl_bank_acc"));
                        indl_bank_nameF.setText(rs.getString("indl_bank_name"));
                        indl_branchF.setText(rs.getString("indl_branch_name"));
                        indl_IFSC_F.setText(rs.getString("indl_ifsc"));
                        kin_NOK_F.setText(rs.getString("kin_name"));
                        con =rs.getString("kin_contact");
                        c = con.split(" ");
                        kin_mobile_numberF.setText(c[1]);
                        kin_relation_F.setText(rs.getString("kin_relation"));
                        kin_PAN_F.setText(rs.getString("kin_pan"));
                        kin_aadharF.setText(rs.getString("kin_aadhar"));
                        kin_mailF.setText(rs.getString("kin_email"));
                        kin_account_numberF.setText(rs.getString("kin_bank_acc"));
                        kin_bank_nameF.setText(rs.getString("kin_bank_name"));
                        kin_branchF.setText(rs.getString("kin_branch_name"));
                        kin_IFSC_F.setText(rs.getString("kin_ifsc"));
                        
                        
                        kin_NOK_F.setForeground(Color.decode("#000000"));
                        kin_mobile_numberF.setForeground(Color.decode("#000000"));

                        kin_relation_F.setForeground(Color.decode("#000000"));
                        kin_PAN_F.setForeground(Color.decode("#000000"));
                        kin_aadharF.setForeground(Color.decode("#000000"));
                        kin_mailF.setForeground(Color.decode("#000000"));
                        kin_account_numberF.setForeground(Color.decode("#000000"));
                        kin_bank_nameF.setForeground(Color.decode("#000000"));
                        kin_branchF.setForeground(Color.decode("#000000"));
                        kin_IFSC_F.setForeground(Color.decode("#000000"));indl_Icard_numberF.setForeground(Color.decode("#000000"));
                        indl_aadharF.setForeground(Color.decode("#000000"));
                        indl_account_numberF.setForeground(Color.decode("#000000"));
                        indl_branchF.setForeground(Color.decode("#000000"));
                        indl_PAN_F.setForeground(Color.decode("#000000"));
                        indl_mailF.setForeground(Color.decode("#000000"));
                        indl_bank_nameF.setForeground(Color.decode("#000000"));
                        indl_IFSC_F.setForeground(Color.decode("#000000"));
                       
                        villageF.setForeground(Color.decode("#000000"));
                       
                        PO_F.setForeground(Color.decode("#000000"));
                       
                        distF.setForeground(Color.decode("#000000"));
                       
                        tehF.setForeground(Color.decode("#000000"));
                       
                        stateF.setForeground(Color.decode("#000000"));
                       
                        PIN_F.setForeground(Color.decode("#000000"));
                        
                        
                        
                        //dob
                        con=rs.getString("DOB");
                        c=con.split("-");
                        birth_year_comboboxF.setSelectedItem(c[2]);
                        birth_month_comboboxF.setSelectedItem(c[1]);
                        birth_date_comboboxF.setSelectedItem(c[0]);
                        System.out.print("\n"+c[0]+" "+c[1]+" "+c[2]);
                        System.out.print("\nNear date join");
                        
                        //Date of joining
                        con=rs.getString("Date_of_joining");
                        if(con!=null && !con.equalsIgnoreCase("null")){
                        c=con.split("-");
                        DOJ_year_comboboxF.setSelectedItem(c[2]);
                        DOJ_month_comboboxF.setSelectedItem(c[1]);
                        DOJ_date_comboboxF.setSelectedItem(c[0]);
                        }
                        else{
                           DOJ_year_comboboxF.setSelectedIndex(0);
                           DOJ_month_comboboxF.setSelectedIndex(0);
                           DOJ_date_comboboxF.setSelectedIndex(0); 
                        }
                        System.out.print("\nNear date attach");
                        
                        
                        //Date of attachment
                        con=rs.getString("Date_of_Attachment");
                        c=con.split("-");
                        DOA_year_comboboxF.setSelectedItem(c[2]);
                        DOA_month_comboboxF.setSelectedItem(c[1]);
                        DOA_date_comboboxF.setSelectedItem(c[0]);
                        
                    }
                }
                else if(blue==0){
                    //update_row=1;
                    JOptionPane.showMessageDialog(null,"Please select a row to be edited");
                    fullscreen_panelD.setVisible(false);
                    fullscreen_panelE.setVisible(false);
                    ScrollPaneA.setVisible(false);
                    ScrollPaneB.setVisible(false);
                    ScrollPaneC.setVisible(false);
                    panelD.setVisible(false);
                    panelE.setVisible(false);
                    ScrollPaneF.setVisible(false);
                    panelG.setVisible(true);

                    print_buttonC.setVisible(false);
                    print_buttonD.setVisible(false);
                    print_buttonE.setVisible(false);
                    refresh_buttonD.setVisible(false);
                    refresh_buttonE.setVisible(false);
                    fullscreen_buttonD.setVisible(false);
                    fullscreen_buttonE.setVisible(false);
                    remove_buttonC.setVisible(false);
                    edit_buttonG.setVisible(true);

                    menuF.setBackground(new java.awt.Color(85,65,118));
                    menuA.setBackground(new java.awt.Color(64,43,100));
                    menuB.setBackground(new java.awt.Color(64,43,100));
                    menuC.setBackground(new java.awt.Color(64,43,100));
                    menuD.setBackground(new java.awt.Color(64,43,100));
                    menuE.setBackground(new java.awt.Color(64,43,100));
                }
                
               
                
            }
            catch(Exception e){
                System.out.print("\n"+e);
                String er=e.getMessage();  //latest change
                if(er.contains("Index -1 out of bounds")){
                    ;
                    JOptionPane.showMessageDialog(null,"Please Select a Row");
                }
                else{
                    
                    JOptionPane.showMessageDialog(null,"Operation Unsuccessful!");
                }    
                    fullscreen_panelD.setVisible(false);
                    fullscreen_panelE.setVisible(false);
                    ScrollPaneA.setVisible(false);
                    ScrollPaneB.setVisible(false);
                    ScrollPaneC.setVisible(false);
                    panelD.setVisible(false);
                    panelE.setVisible(false);
                    ScrollPaneF.setVisible(false);
                    panelG.setVisible(true);

                    print_buttonC.setVisible(false);
                    print_buttonD.setVisible(false);
                    print_buttonE.setVisible(false);
                    refresh_buttonD.setVisible(false);
                    refresh_buttonE.setVisible(false);
                    fullscreen_buttonD.setVisible(false);
                    fullscreen_buttonE.setVisible(false);
                    remove_buttonC.setVisible(false);
                    edit_buttonG.setVisible(true);

                    menuF.setBackground(new java.awt.Color(85,65,118));
                    menuA.setBackground(new java.awt.Color(64,43,100));
                    menuB.setBackground(new java.awt.Color(64,43,100));
                    menuC.setBackground(new java.awt.Color(64,43,100));
                    menuD.setBackground(new java.awt.Color(64,43,100));
                    menuE.setBackground(new java.awt.Color(64,43,100));
                
            }
        }
    }//GEN-LAST:event_edit_buttonGMouseClicked

    private void edit_buttonGMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_edit_buttonGMouseEntered
        edit_buttonG.setBackground(new java.awt.Color(237, 224, 255));
    }//GEN-LAST:event_edit_buttonGMouseEntered

    private void edit_buttonGMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_edit_buttonGMouseExited
        edit_buttonG.setBackground(new java.awt.Color(240, 240, 240));
    }//GEN-LAST:event_edit_buttonGMouseExited

    private void okayGMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_okayGMouseClicked
        String sno=service_numberG.getText();
        if(sno==null || sno.trim().isEmpty()){
            JOptionPane.showMessageDialog(null,"Please Enter Army Number");
        }
        else{
            try{
                
                Statement st = con.createStatement();
                String query="select count(*) as count from attach_in where Service_no='"+sno+"'";
                ResultSet rs = st.executeQuery(query);
                int count =0;
                String n ="";
                upd_small_attach_in.setRowCount(0);
                upd_big_attach_in.setRowCount(0);
                int flag=0;
                if(rs.next()){
                    count=rs.getInt("count");
                }
                if(count!=0){
                    ScrollPaneG_IN.setVisible(true);
                    ScrollPaneG_OUT.setVisible(false);
                    flag=100;
                    blue=1;
                    int counter=0;
                    String q ="select * from attach_in where Service_no='"+sno+"'";
                    rs=st.executeQuery(q);
                    while(rs.next()){
                        counter=counter+1;
                        upd_small_attach_in.addRow(new Object[]{counter,rs.getString("Service_no"),rs.getString("Name"),rs.getString("Date_of_Joining"),rs.getString("Armed_Services"),rs.getString("Trade"),rs.getString("Unit")});
                        
                    }
                }
                query="select count(*) as count from attach_out where Service_no='"+sno+"'";
                rs=st.executeQuery(query);
                if(rs.next()){
                    count=rs.getInt("count");
                }
                if(count!=0){
                    ScrollPaneG_IN.setVisible(false);
                    ScrollPaneG_OUT.setVisible(true);
                    flag=100;
                    blue=2;
                    int counter=0;
                    String q ="select * from attach_out where Service_no='"+sno+"'";
                    rs=st.executeQuery(q);
                    while(rs.next()){
                        counter=counter+1;
                        upd_big_attach_in.addRow(new Object[]{counter,rs.getString("Service_no"),rs.getString("Name"),rs.getString("Date_of_Joining"),rs.getString("Date_of_Attachment"),rs.getString("Location"),rs.getString("purpose")});
                        
                    }
                }
                if(flag==0){
                    JOptionPane.showMessageDialog(null,"No record of "+sno+" found");
                }
                
                
            }
            catch(Exception e){
                System.out.print("\n"+e);
                JOptionPane.showMessageDialog(null,"Operation Unsuccessful!");
            }
        }
    }//GEN-LAST:event_okayGMouseClicked

    private void menuFMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_menuFMouseClicked
        fullscreen_panelD.setVisible(false);
        fp="";
        fullscreen_panelE.setVisible(false);
        ScrollPaneA.setVisible(false);
        ScrollPaneB.setVisible(false);
        ScrollPaneC.setVisible(false);
        panelD.setVisible(false);
        panelE.setVisible(false);
        ScrollPaneF.setVisible(false);
        panelG.setVisible(true);

        print_buttonC.setVisible(false);
        print_buttonD.setVisible(false);
        print_buttonE.setVisible(false);
        refresh_buttonD.setVisible(false);
        refresh_buttonE.setVisible(false);
        fullscreen_buttonD.setVisible(false);
        fullscreen_buttonE.setVisible(false);
        remove_buttonC.setVisible(false);
        edit_buttonG.setVisible(true);

        menuF.setBackground(new java.awt.Color(85,65,118));
        menuA.setBackground(new java.awt.Color(64,43,100));
        menuB.setBackground(new java.awt.Color(64,43,100));
        menuC.setBackground(new java.awt.Color(64,43,100));
        menuD.setBackground(new java.awt.Color(64,43,100));
        menuE.setBackground(new java.awt.Color(64,43,100));
        
    }//GEN-LAST:event_menuFMouseClicked

    private void select_file_buttonFMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_select_file_buttonFMouseClicked
        // TODO add your handling code here:
        JFileChooser chooser=new JFileChooser();
        
        //applying extension filter
        chooser.setFileFilter(new FileNameExtensionFilter("Image Files", "png", "jpg", "jpeg"));
        
        //opening file chooser window
        chooser.showOpenDialog(null);
        
        File f= chooser.getSelectedFile();
        
        //filepath
        String filepath =f.getAbsolutePath();
        
        //setting the label to filename
        select_file_labelF.setText(f.getName());
        fp=filepath;
        
        chooser.setSelectedFile(null);
    }//GEN-LAST:event_select_file_buttonFMouseClicked

    private void select_file_buttonFMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_select_file_buttonFMouseEntered
        select_file_buttonF.setBackground(new java.awt.Color(237, 224, 255));
    }//GEN-LAST:event_select_file_buttonFMouseEntered

    private void select_file_buttonFMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_select_file_buttonFMouseExited
        select_file_buttonF.setBackground(new java.awt.Color(240, 240, 240));
    }//GEN-LAST:event_select_file_buttonFMouseExited

    private void indl_underline8MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_indl_underline8MouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_indl_underline8MouseClicked

    private void indl_Icard_numberFFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_indl_Icard_numberFFocusGained
        // TODO add your handling code here:
    }//GEN-LAST:event_indl_Icard_numberFFocusGained

    private void indl_Icard_numberFFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_indl_Icard_numberFFocusLost
        // TODO add your handling code here:
    }//GEN-LAST:event_indl_Icard_numberFFocusLost

    private void indl_Icard_numberFMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_indl_Icard_numberFMouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_indl_Icard_numberFMouseClicked

    private void indl_PAN_FFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_indl_PAN_FFocusGained
        // TODO add your handling code here:
    }//GEN-LAST:event_indl_PAN_FFocusGained

    private void indl_PAN_FFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_indl_PAN_FFocusLost
        // TODO add your handling code here:
    }//GEN-LAST:event_indl_PAN_FFocusLost

    private void indl_PAN_FMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_indl_PAN_FMouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_indl_PAN_FMouseClicked

    private void indl_PAN_FActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_indl_PAN_FActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_indl_PAN_FActionPerformed

    private void indl_aadharFFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_indl_aadharFFocusGained
        // TODO add your handling code here:
    }//GEN-LAST:event_indl_aadharFFocusGained

    private void indl_aadharFFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_indl_aadharFFocusLost
        // TODO add your handling code here:
    }//GEN-LAST:event_indl_aadharFFocusLost

    private void indl_aadharFMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_indl_aadharFMouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_indl_aadharFMouseClicked

    private void indl_aadharFActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_indl_aadharFActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_indl_aadharFActionPerformed

    private void indl_mailFFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_indl_mailFFocusGained
        // TODO add your handling code here:
    }//GEN-LAST:event_indl_mailFFocusGained

    private void indl_mailFFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_indl_mailFFocusLost
        // TODO add your handling code here:
    }//GEN-LAST:event_indl_mailFFocusLost

    private void indl_mailFMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_indl_mailFMouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_indl_mailFMouseClicked

    private void indl_bank_nameFFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_indl_bank_nameFFocusGained
        // TODO add your handling code here:
    }//GEN-LAST:event_indl_bank_nameFFocusGained

    private void indl_bank_nameFFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_indl_bank_nameFFocusLost
        // TODO add your handling code here:
    }//GEN-LAST:event_indl_bank_nameFFocusLost

    private void indl_bank_nameFMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_indl_bank_nameFMouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_indl_bank_nameFMouseClicked

    private void indl_account_numberFFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_indl_account_numberFFocusGained
        // TODO add your handling code here:
    }//GEN-LAST:event_indl_account_numberFFocusGained

    private void indl_account_numberFFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_indl_account_numberFFocusLost
        // TODO add your handling code here:
    }//GEN-LAST:event_indl_account_numberFFocusLost

    private void indl_account_numberFMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_indl_account_numberFMouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_indl_account_numberFMouseClicked

    private void indl_account_numberFMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_indl_account_numberFMouseEntered
        // TODO add your handling code here:
    }//GEN-LAST:event_indl_account_numberFMouseEntered

    private void indl_account_numberFActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_indl_account_numberFActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_indl_account_numberFActionPerformed

    private void villageFFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_villageFFocusGained
        // TODO add your handling code here:
    }//GEN-LAST:event_villageFFocusGained

    private void villageFFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_villageFFocusLost
        // TODO add your handling code here:
    }//GEN-LAST:event_villageFFocusLost

    private void villageFMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_villageFMouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_villageFMouseClicked

    private void tehFFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_tehFFocusGained
        // TODO add your handling code here:
    }//GEN-LAST:event_tehFFocusGained

    private void tehFFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_tehFFocusLost
        // TODO add your handling code here:
    }//GEN-LAST:event_tehFFocusLost

    private void tehFMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tehFMouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_tehFMouseClicked

    private void tehFActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_tehFActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_tehFActionPerformed

    private void PO_FFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_PO_FFocusGained
        // TODO add your handling code here:
    }//GEN-LAST:event_PO_FFocusGained

    private void PO_FFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_PO_FFocusLost
        // TODO add your handling code here:
    }//GEN-LAST:event_PO_FFocusLost

    private void PO_FMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_PO_FMouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_PO_FMouseClicked

    private void PO_FActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_PO_FActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_PO_FActionPerformed

    private void stateFFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_stateFFocusGained
        // TODO add your handling code here:
    }//GEN-LAST:event_stateFFocusGained

    private void stateFFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_stateFFocusLost
        // TODO add your handling code here:
    }//GEN-LAST:event_stateFFocusLost

    private void stateFMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_stateFMouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_stateFMouseClicked

    private void PIN_FFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_PIN_FFocusGained
        // TODO add your handling code here:
    }//GEN-LAST:event_PIN_FFocusGained

    private void PIN_FFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_PIN_FFocusLost
        // TODO add your handling code here:
    }//GEN-LAST:event_PIN_FFocusLost

    private void PIN_FMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_PIN_FMouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_PIN_FMouseClicked

    private void distFFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_distFFocusGained
        // TODO add your handling code here:
    }//GEN-LAST:event_distFFocusGained

    private void distFFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_distFFocusLost
        // TODO add your handling code here:
    }//GEN-LAST:event_distFFocusLost

    private void distFMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_distFMouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_distFMouseClicked

    private void distFActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_distFActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_distFActionPerformed

    private void indl_IFSC_FFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_indl_IFSC_FFocusGained
        // TODO add your handling code here:
    }//GEN-LAST:event_indl_IFSC_FFocusGained

    private void indl_IFSC_FFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_indl_IFSC_FFocusLost
        // TODO add your handling code here:
    }//GEN-LAST:event_indl_IFSC_FFocusLost

    private void indl_IFSC_FMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_indl_IFSC_FMouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_indl_IFSC_FMouseClicked

    private void indl_branchFFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_indl_branchFFocusGained
        // TODO add your handling code here:
    }//GEN-LAST:event_indl_branchFFocusGained

    private void indl_branchFFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_indl_branchFFocusLost
        // TODO add your handling code here:
    }//GEN-LAST:event_indl_branchFFocusLost

    private void indl_branchFMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_indl_branchFMouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_indl_branchFMouseClicked

    private void indl_branchFActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_indl_branchFActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_indl_branchFActionPerformed

    private void kin_PAN_FFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_kin_PAN_FFocusGained
        // TODO add your handling code here:
    }//GEN-LAST:event_kin_PAN_FFocusGained

    private void kin_PAN_FFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_kin_PAN_FFocusLost
        // TODO add your handling code here:
    }//GEN-LAST:event_kin_PAN_FFocusLost

    private void kin_PAN_FMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_kin_PAN_FMouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_kin_PAN_FMouseClicked

    private void kin_PAN_FActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_kin_PAN_FActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_kin_PAN_FActionPerformed

    private void kin_aadharFFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_kin_aadharFFocusGained
        // TODO add your handling code here:
    }//GEN-LAST:event_kin_aadharFFocusGained

    private void kin_aadharFFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_kin_aadharFFocusLost
        // TODO add your handling code here:
    }//GEN-LAST:event_kin_aadharFFocusLost

    private void kin_aadharFMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_kin_aadharFMouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_kin_aadharFMouseClicked

    private void kin_aadharFActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_kin_aadharFActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_kin_aadharFActionPerformed

    private void kin_mailFFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_kin_mailFFocusGained
        // TODO add your handling code here:
    }//GEN-LAST:event_kin_mailFFocusGained

    private void kin_mailFFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_kin_mailFFocusLost
        // TODO add your handling code here:
    }//GEN-LAST:event_kin_mailFFocusLost

    private void kin_mailFMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_kin_mailFMouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_kin_mailFMouseClicked

    private void kin_bank_nameFFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_kin_bank_nameFFocusGained
        // TODO add your handling code here:
    }//GEN-LAST:event_kin_bank_nameFFocusGained

    private void kin_bank_nameFFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_kin_bank_nameFFocusLost
        // TODO add your handling code here:
    }//GEN-LAST:event_kin_bank_nameFFocusLost

    private void kin_bank_nameFMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_kin_bank_nameFMouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_kin_bank_nameFMouseClicked

    private void kin_account_numberFFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_kin_account_numberFFocusGained
        // TODO add your handling code here:
    }//GEN-LAST:event_kin_account_numberFFocusGained

    private void kin_account_numberFFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_kin_account_numberFFocusLost
        // TODO add your handling code here:
    }//GEN-LAST:event_kin_account_numberFFocusLost

    private void kin_account_numberFMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_kin_account_numberFMouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_kin_account_numberFMouseClicked

    private void kin_account_numberFActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_kin_account_numberFActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_kin_account_numberFActionPerformed

    private void kin_IFSC_FFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_kin_IFSC_FFocusGained
        // TODO add your handling code here:
    }//GEN-LAST:event_kin_IFSC_FFocusGained

    private void kin_IFSC_FFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_kin_IFSC_FFocusLost
        // TODO add your handling code here:
    }//GEN-LAST:event_kin_IFSC_FFocusLost

    private void kin_IFSC_FMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_kin_IFSC_FMouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_kin_IFSC_FMouseClicked

    private void kin_branchFFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_kin_branchFFocusGained
        // TODO add your handling code here:
    }//GEN-LAST:event_kin_branchFFocusGained

    private void kin_branchFFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_kin_branchFFocusLost
        // TODO add your handling code here:
    }//GEN-LAST:event_kin_branchFFocusLost

    private void kin_branchFMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_kin_branchFMouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_kin_branchFMouseClicked

    private void kin_branchFActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_kin_branchFActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_kin_branchFActionPerformed

    private void kin_NOK_FFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_kin_NOK_FFocusGained
        // TODO add your handling code here:
    }//GEN-LAST:event_kin_NOK_FFocusGained

    private void kin_NOK_FFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_kin_NOK_FFocusLost
        // TODO add your handling code here:
    }//GEN-LAST:event_kin_NOK_FFocusLost

    private void kin_NOK_FMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_kin_NOK_FMouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_kin_NOK_FMouseClicked

    private void kin_NOK_FActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_kin_NOK_FActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_kin_NOK_FActionPerformed

    private void kin_mobile_numberFFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_kin_mobile_numberFFocusGained
        // TODO add your handling code here:
    }//GEN-LAST:event_kin_mobile_numberFFocusGained

    private void kin_mobile_numberFFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_kin_mobile_numberFFocusLost
        // TODO add your handling code here:
    }//GEN-LAST:event_kin_mobile_numberFFocusLost

    private void kin_mobile_numberFMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_kin_mobile_numberFMouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_kin_mobile_numberFMouseClicked

    private void kin_mobile_numberFActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_kin_mobile_numberFActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_kin_mobile_numberFActionPerformed

    private void kin_relation_FFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_kin_relation_FFocusGained
        // TODO add your handling code here:
    }//GEN-LAST:event_kin_relation_FFocusGained

    private void kin_relation_FFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_kin_relation_FFocusLost
        // TODO add your handling code here:
    }//GEN-LAST:event_kin_relation_FFocusLost

    private void kin_relation_FMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_kin_relation_FMouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_kin_relation_FMouseClicked

    private void kin_relation_FActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_kin_relation_FActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_kin_relation_FActionPerformed
    private void ref(){
        String sno=service_numberG.getText();
        if(sno==null || sno.trim().isEmpty()){
            JOptionPane.showMessageDialog(null,"Please Enter Army Number");
        }
        else{
            try{
                
                Statement st = con.createStatement();
                String query="select count(*) as count from attach_in where Service_no='"+sno+"'";
                ResultSet rs = st.executeQuery(query);
                int count =0;
                String n ="";
                upd_small_attach_in.setRowCount(0);
                upd_big_attach_in.setRowCount(0);
                int flag=0;
                if(rs.next()){
                    count=rs.getInt("count");
                }
                if(count!=0){
                    ScrollPaneG_IN.setVisible(true);
                    ScrollPaneG_OUT.setVisible(false);
                    flag=100;
                    blue=1;
                    int counter=0;
                    String q ="select * from attach_in where Service_no='"+sno+"'";
                    rs=st.executeQuery(q);
                    while(rs.next()){
                        counter=counter+1;
                        upd_small_attach_in.addRow(new Object[]{counter,rs.getString("Service_no"),rs.getString("Name"),rs.getString("Date_of_Joining"),rs.getString("Armed_Services"),rs.getString("Trade"),rs.getString("Unit")});
                        
                    }
                }
                query="select count(*) as count from attach_out where Service_no='"+sno+"'";
                rs=st.executeQuery(query);
                if(rs.next()){
                    count=rs.getInt("count");
                }
                if(count!=0){
                    ScrollPaneG_IN.setVisible(false);
                    ScrollPaneG_OUT.setVisible(true);
                    flag=100;
                    blue=2;
                    int counter=0;
                    String q ="select * from attach_out where Service_no='"+sno+"'";
                    rs=st.executeQuery(q);
                    while(rs.next()){
                        counter=counter+1;
                        upd_big_attach_in.addRow(new Object[]{counter,rs.getString("Service_no"),rs.getString("Name"),rs.getString("Date_of_Joining"),rs.getString("Date_of_Attachment"),rs.getString("Location"),rs.getString("purpose")});
                        
                    }
                }
                if(flag==0){
                    JOptionPane.showMessageDialog(null,"No record of "+sno+" found");
                }
                
                
            }
            catch(Exception e){
                System.out.print("\n"+e);
                JOptionPane.showMessageDialog(null,"Operation Unsuccessful!");
            }
        }
    
    }
    private void update_buttonFMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_update_buttonFMouseClicked
        // TODO add your handling code here:
        String sno=service_number.getText();
        
        try{
            Statement st = con.createStatement();
            ResultSet rs = null;
            String name = name_textfieldF.getText();
        
            String company = company_textfieldF.getText();
            String courses = courses_textfieldF.getText();

            String contact = contact_textfieldF.getText();
            String rank11 = (String)rank_comboboxF.getSelectedItem();
            String medical = medical_category_textfieldF.getText(); 

            //address
            String village = villageF.getText(); 
            String po = PO_F.getText();
            String district = distF.getText();
            String teh = tehF.getText();
            String state = stateF.getText();
            String pin = PIN_F.getText();
            String address = village+","+district+","+teh+","+po+","+state+","+pin;

            //indl records
            String indl_icard = indl_Icard_numberF.getText();
            String indl_aadhar = indl_aadharF.getText();
            String indl_account_no = indl_account_numberF.getText();
            String indl_branch = indl_branchF.getText();
            String indl_pan = indl_PAN_F.getText();
            String indl_mail = indl_mailF.getText();
            String indl_bank = indl_bank_nameF.getText();
            String indl_ifsc = indl_IFSC_F.getText();

            //kin
            String kin_name = kin_NOK_F.getText();
            String kin_mob = kin_mobile_numberF.getText();
            String kin_contact = "+91 "+kin_mob;
            String kin_relation = kin_relation_F.getText();
            String kin_pan = kin_PAN_F.getText();
            String kin_aadhar = kin_aadharF.getText();
            String kin_email = kin_mailF.getText(); 
            String kin_acc = kin_account_numberF.getText();
            String kin_bank = kin_bank_nameF.getText();
            String kin_branch = kin_branchF.getText();
            String kin_ifsc = kin_IFSC_F.getText();
            
            String dob_year = (String)birth_year_comboboxF.getSelectedItem();
            String dob_month = (String)birth_month_comboboxF.getSelectedItem();
            String dob_date = (String)birth_date_comboboxF.getSelectedItem();
            int dy = Integer.parseInt(dob_year);
            int dm = Integer.parseInt(dob_month);
            int dd = Integer.parseInt(dob_date);
            String date_of_birth = dob_date+"-"+dob_month+"-"+dob_year;
            
            
            boolean test_case1 = (contact.length()==10 && contact.matches("[0-9]+") && kin_mob.length()==10 && kin_mob.matches("[0-9]+") );
            //boolean dunna=(service!=null && !service.trim().isEmpty());
            String err="";
            boolean test_case2 =(company!=null && !company.trim().isEmpty())&&(courses!=null && !courses.trim().isEmpty())&&(rank11!=null && !rank11.trim().isEmpty())&&(medical!=null && !medical.trim().isEmpty());
            
            
            
            boolean date_test=dob_year.matches("[0-9]+") && dob_month.matches("[0-9]+")&& dob_date.matches("[0-9]+");
            boolean date_test2=!((dy%4==0 && dm==2 &&  (dd>29)  )   || (  (dm==2 || dm==4 || dm==6 || dm==9 || dm==11)  && dd==31  )    ||  (dm==2 && dd>28 && dy%4!=0));
            boolean email_check = (kin_email.contains("@") && indl_mail.contains("@") &&(pin.length()==6) && (pin.matches("[0-9]+")));
            if(email_check&&date_test2&&test_case1&&test_case2&&date_test&&(kin_email.contains("@"))&& (indl_mail.contains("@"))&&(village!=null && !village.trim().isEmpty()) &&(indl_account_no!=null && !indl_account_no.trim().isEmpty()) && (po!=null && !po.trim().isEmpty()) && (district!=null && !district.trim().isEmpty()) &&(teh!=null && !teh.trim().isEmpty()) && (state!=null && !state.trim().isEmpty()) &&(indl_icard!=null && !indl_icard.trim().isEmpty()) &&(pin.length()==6 && pin.matches("[0-9]+") && indl_aadhar.length()==12 && indl_aadhar.matches("[0-9]+") && indl_pan.length()==10 && kin_aadhar.length()==12 && kin_aadhar.matches("[0-9]+") && kin_pan.length()==10) &&(indl_branch!=null && !indl_branch.trim().isEmpty()) &&(indl_mail!=null && !indl_mail.trim().isEmpty()) &&(indl_bank!=null && !indl_bank.trim().isEmpty()) &&(indl_ifsc!=null && !indl_ifsc.trim().isEmpty()) &&(kin_acc!=null && !kin_acc.trim().isEmpty()) &&(kin_branch!=null && !kin_branch.trim().isEmpty())&&(kin_bank!=null && !kin_bank.trim().isEmpty()) &&(kin_ifsc!=null && !kin_ifsc.trim().isEmpty())&&(kin_email!=null && !kin_email.trim().isEmpty())&&(kin_relation!=null && !kin_relation.trim().isEmpty()) &&(kin_name!=null && !kin_name.trim().isEmpty())){
                if(blue==1){
                    
                    //DOJ_date_comboboxF
                    String doj_year = (String)DOJ_year_comboboxF.getSelectedItem();
                    String doj_month = (String)DOJ_month_comboboxF.getSelectedItem();
                    String doj_date = (String)DOJ_date_comboboxF.getSelectedItem();
                    int djy = Integer.parseInt(doj_year);
                    int djm = Integer.parseInt(doj_month);
                    int djd = Integer.parseInt(doj_date);
                    String date_of_j = doj_date+"-"+doj_month+"-"+doj_year;
                    boolean updated_doj=!((djy%4==0 && djm==2 &&  (djd>29)  )   || (  (djm==2 || djm==4 || djm==6 || djm==9 || djm==11)  && djd==31  )    ||  (djm==2 && djd>28 && djy%4!=0));
            
                    
                    String date_of_joining =DOJ.getText();
                    String cond=sno.toLowerCase()+","+date_of_joining;
                    
                    String new_serv_doj=sno.toLowerCase()+","+date_of_j;
                    System.out.print("\n"+cond);
                    if(updated_doj){
                        String query="update attach_in set Name='"+name+"',serv_doj='"+new_serv_doj+"',Date_of_Joining='"+date_of_j+"',Rank_='"+rank11+"',Company='"+company+"',Courses_done='"+courses+"',Contact_no='+91 "+contact+"',Medical_category='"+medical+"',Address='"+address+"',indl_id='"+indl_icard+"',indl_aadhar='"+indl_aadhar+"',indl_pan='"+indl_pan+"',indl_bank_acc='"+indl_account_no+"',indl_branch_name='"+indl_branch+"',indl_email='"+indl_mail+"',indl_bank_name='"+indl_bank+"',indl_ifsc='"+indl_ifsc+"',kin_name='"+kin_name+"',kin_contact='"+kin_contact+"',kin_relation='"+kin_relation+"',kin_aadhar='"+kin_aadhar+"',kin_pan='"+kin_pan+"',kin_bank_acc='"+kin_acc+"',kin_branch_name='"+kin_branch+"',kin_email='"+kin_email+"',kin_bank_name='"+kin_bank+"',kin_ifsc='"+kin_ifsc+"',DOB='"+date_of_birth+"' where serv_doj='"+cond+"'";          
                        //System.out.print("\n"+name+"\n"+rank11+"\n"+company+"\n"+courses+"\n"+contact+"\n"+medical+"\n"+address+"\n"+indl_icard+"\n"+indl_aadhar+"\n"+indl_pan+"\n"+indl_account_no+"\n"+indl_branch+"\n"+indl_mail+"\n"+indl_bank+"\n"+indl_ifsc+"\n"+kin_name+"\n"+kin_contact+"\n"+kin_relation+"\n"+kin_aadhar+"\n"+kin_bank+"\n"+kin_pan+"\n"+kin_acc+"\n"+kin_branch+"\n"+kin_email+"\n"+kin_ifsc+"\n"+date_of_birth);
                        st.executeUpdate(query);
                        JOptionPane.showMessageDialog(null,"Updation Successful");
                        if(!select_file_labelF.getText().equalsIgnoreCase("Select File"))
                            move(fp,sno);
                        fp="";
                        ref();
                        select_file_labelF.setText("Select File");
                        fullscreen_panelD.setVisible(false);
                        fullscreen_panelE.setVisible(false);
                        ScrollPaneA.setVisible(false);
                        ScrollPaneB.setVisible(false);
                        ScrollPaneC.setVisible(false);
                        panelD.setVisible(false);
                        panelE.setVisible(false);
                        ScrollPaneF.setVisible(false);
                        panelG.setVisible(true);

                        print_buttonC.setVisible(false);
                        print_buttonD.setVisible(false);
                        print_buttonE.setVisible(false);
                        refresh_buttonD.setVisible(false);
                        refresh_buttonE.setVisible(false);
                        fullscreen_buttonD.setVisible(false);
                        fullscreen_buttonE.setVisible(false);
                        remove_buttonC.setVisible(false);
                        edit_buttonG.setVisible(true);

                        menuF.setBackground(new java.awt.Color(85,65,118));
                        menuA.setBackground(new java.awt.Color(64,43,100));
                        menuB.setBackground(new java.awt.Color(64,43,100));
                        menuC.setBackground(new java.awt.Color(64,43,100));
                        menuD.setBackground(new java.awt.Color(64,43,100));
                        menuE.setBackground(new java.awt.Color(64,43,100));
                    }    
                    else{
                        JOptionPane.showMessageDialog(null, "Date of Joining Incorrect");
                    }
                }
                if(blue==2){
                    
                    //date of joining
                    String doj_year = (String)DOJ_year_comboboxF.getSelectedItem();
                    String doj_month = (String)DOJ_month_comboboxF.getSelectedItem();
                    String doj_date = (String)DOJ_date_comboboxF.getSelectedItem();
                    String date_of_j = null;
                    int y=0,m=0,d=0;
                    if(!doj_year.contains("YYYY") && !doj_month.contains("MM") && !doj_date.contains("DD")){
                        System.out.print("\ndoj check for values");
                        y = Integer.parseInt(doj_year);
                        m = Integer.parseInt(doj_month);
                        d = Integer.parseInt(doj_date);
                        date_of_j = doj_date+"-"+doj_month+"-"+doj_year;
                    }
                    if(doj_year.contains("YYYY") && doj_month.contains("MM") && doj_date.contains("DD")){
                        System.out.print("\ndoj check for char");
                        date_of_j = null;
                        y=100;
                    }
                    
                    //DOA
                    String doa_year = (String)DOA_year_comboboxF.getSelectedItem();
                    String doa_month = (String)DOA_month_comboboxF.getSelectedItem();
                    String doa_date = (String)DOA_date_comboboxF.getSelectedItem();
                    int day = Integer.parseInt(doa_year);
                    int dam = Integer.parseInt(doa_month);
                    int dad = Integer.parseInt(doa_date);
                    String date_of_a = doa_date+"-"+doa_month+"-"+doa_year;
                    
                    boolean updated_doj_doa=!((y%4==0 && m==2 &&  (d>29)  )   || (  (m==2 || m==4 || m==6 || m==9 || m==11)  && d==31  )    ||  (m==2 && d>28 && y%4!=0))&&!((day%4==0 && dam==2 &&  (dad>29)  )   || (  (dam==2 || dam==4 || dam==6 || dam==9 || dam==11)  && dad==31  )    ||  (dam==2 && dad>28 && day%4!=0));
            ;
            
                    if(y==0 &&d==0&&m==0){
                        System.out.print("\nInside third if");
                        JOptionPane.showMessageDialog(null,"Inocrrect Date of Joining format!!!");
                    }
                    if(updated_doj_doa &&!(y==0 &&d==0&&m==0)){
                        String date_of_attachment =DOA.getText();
                        String cond=sno.toLowerCase()+","+date_of_attachment;
                        System.out.print("\n"+cond);
                        
                        String serv_doa=sno.toLowerCase()+","+date_of_a;

                        String query="update attach_out set Name='"+name+"',serv_doa='"+serv_doa+"',Date_of_attachment='"+date_of_a+"',Date_of_joining='"+date_of_j+"',Rank_='"+rank11+"',Company='"+company+"',Courses_done='"+courses+"',Contact_no='"+"+91 "+contact+"',Medical_category='"+medical+"',Address='"+address+"',indl_id='"+indl_icard+"',indl_aadhar='"+indl_aadhar+"',indl_pan='"+indl_pan+"',indl_bank_acc='"+indl_account_no+"',indl_branch_name='"+indl_branch+"',indl_email='"+indl_mail+"',indl_bank_name='"+indl_bank+"',indl_ifsc='"+indl_ifsc+"',kin_name='"+kin_name+"',kin_contact='"+kin_contact+"',kin_relation='"+kin_relation+"',kin_aadhar='"+kin_aadhar+"',kin_pan='"+kin_pan+"',kin_bank_acc='"+kin_acc+"',kin_branch_name='"+kin_branch+"',kin_email='"+kin_email+"',kin_bank_name='"+kin_bank+"',kin_ifsc='"+kin_ifsc+"',DOB='"+date_of_birth+"' where serv_doa='"+cond+"'";          
                       // System.out.print("\n"+name+"\n"+rank11+"\n"+company+"\n"+courses+"\n"+contact+"\n"+medical+"\n"+address+"\n"+indl_icard+"\n"+indl_aadhar+"\n"+indl_pan+"\n"+indl_account_no+"\n"+indl_branch+"\n"+indl_mail+"\n"+indl_bank+"\n"+indl_ifsc+"\n"+kin_name+"\n"+kin_contact+"\n"+kin_relation+"\n"+kin_aadhar+"\n"+kin_bank+"\n"+kin_pan+"\n"+kin_acc+"\n"+kin_branch+"\n"+kin_email+"\n"+kin_ifsc+"\n"+date_of_birth);
                        st.executeUpdate(query);

                        JOptionPane.showMessageDialog(null,"Updation Successful");
                        if(!select_file_labelF.getText().equalsIgnoreCase("Select File"))
                            move(fp,sno);
                        
                        fp="";
                        ref();
                        select_file_labelF.setText("Select File");
                        fullscreen_panelD.setVisible(false);
                        fullscreen_panelE.setVisible(false);
                        ScrollPaneA.setVisible(false);
                        ScrollPaneB.setVisible(false);
                        ScrollPaneC.setVisible(false);
                        panelD.setVisible(false);
                        panelE.setVisible(false);
                        ScrollPaneF.setVisible(false);
                        panelG.setVisible(true);

                        print_buttonC.setVisible(false);
                        print_buttonD.setVisible(false);
                        print_buttonE.setVisible(false);
                        refresh_buttonD.setVisible(false);
                        refresh_buttonE.setVisible(false);
                        fullscreen_buttonD.setVisible(false);
                        fullscreen_buttonE.setVisible(false);
                        remove_buttonC.setVisible(false);
                        edit_buttonG.setVisible(true);

                        menuF.setBackground(new java.awt.Color(85,65,118));
                        menuA.setBackground(new java.awt.Color(64,43,100));
                        menuB.setBackground(new java.awt.Color(64,43,100));
                        menuC.setBackground(new java.awt.Color(64,43,100));
                        menuD.setBackground(new java.awt.Color(64,43,100));
                        menuE.setBackground(new java.awt.Color(64,43,100));
                        
                    }  
                    else{
                        if (((y % 4 == 0 && m == 2 && (d > 29)) || ((m == 2 || m == 4 || m == 6 || m == 9 || m == 11) && d == 31) || (m == 2 && d > 28 && y % 4 != 0))) {
                            JOptionPane.showMessageDialog(null, "Date of Joining Incorrect");
                        }
                        if (((day % 4 == 0 && dam == 2 && (dad > 29)) || ((dam == 2 || dam == 4 || dam == 6 || dam == 9 || dam == 11) && dad == 31) || (dam == 2 && dad > 28 && day % 4 != 0))) {
                            JOptionPane.showMessageDialog(null, "Date of Joining Attachment");
                        }
                        
                        
                    }
                }
            }
            else{
                if(contact.length()!=10 || !contact.matches("[0-9]+"))
                    err= err+"\nIncorrect Number";
                if(name==null || name.trim().isEmpty())
                    err= err+"\nName Field Empty";
                if(kin_mob.length()!=10 || !kin_mob.matches("[0-9]+"))
                    err= err+"\nIncorrect Kin Mobile";
                
                if(company==null || company.trim().isEmpty())
                    err= err+"\nCompany Field Empty";
                if(courses==null || courses.trim().isEmpty())
                    err= err+"\nCourses Field Empty";
                if(rank11==null || rank11.trim().isEmpty())
                    err= err+"\nRank Field Empty";
                if(medical==null || medical.trim().isEmpty())
                    err= err+"\nMedical Field Empty";
                
                if(!dob_year.matches("[0-9]+") || !dob_month.matches("[0-9]+")|| !dob_date.matches("[0-9]+"))
                    err= err+"\nIncorrect Date of Birth";
                
                if((village==null || village.trim().isEmpty())){
                    err= err+"\nVillage Field Empty";
                }
                
                if(((dy%4==0 && dm==2 &&  (dd>29)  )   || (  (dm==2 || dm==4 || dm==6 || dm==9 || dm==11)  && dd==31  )    ||  (dm==2 && dd>28 && dy%4!=0)))
                    err = err+"\nDOB not Correct";
                if((indl_account_no==null || indl_account_no.trim().isEmpty())){
                    err= err+"\nIndividual Account Number Field Empty";
                }
                
                if((po==null || po.trim().isEmpty())){
                    err= err+"\nPost Office Field Empty";
                }
                if((district==null || district.trim().isEmpty())){
                    err= err+"\nDistrict Field Empty";
                }
                if((teh==null || teh.trim().isEmpty())){
                    err= err+"\nTeh Field Empty";
                }
                if((state==null || state.trim().isEmpty())){
                    err= err+"\nState Field Empty";
                }
                if((indl_icard==null || indl_icard.trim().isEmpty())){
                    err= err+"\nIndividual id Field Empty";
                }
                if((indl_branch==null || indl_branch.trim().isEmpty())){
                    err= err+"\nIndividual Branch Field Empty";
                }
                if((indl_bank==null || indl_bank.trim().isEmpty())){
                    err= err+"\nIndividual Bank Field Empty";
                }
                if((indl_ifsc==null || indl_ifsc.trim().isEmpty())){
                    err= err+"\nIndividual IFSC Field Empty";
                }
                if((indl_mail==null || indl_mail.trim().isEmpty()) || !indl_mail.contains("@")){
                    err= err+"\nIndividual Mail Field Empty";
                }
                if((kin_acc==null || kin_acc.trim().isEmpty())){
                    err= err+"\nKin Account No Field Empty";
                }
                if((kin_branch==null || kin_branch.trim().isEmpty())){
                    err= err+"\nKin Branch Field Empty";
                }
                if((kin_bank==null || kin_bank.trim().isEmpty())){
                    err= err+"\nKin Bank Field Empty";
                }
                if((kin_ifsc==null || kin_ifsc.trim().isEmpty())){
                    err= err+"\nKin IFSC Field Empty";
                }
                if((kin_relation==null || kin_relation.trim().isEmpty())){
                    err= err+"\nKin Relation Field Empty";
                }
                if((kin_name==null || kin_name.trim().isEmpty())){
                    err= err+"\nKin Name Field Empty";
                }
                if((kin_email==null || kin_email.trim().isEmpty())  || !kin_email.contains("@")){
                    err= err+"\nKin Email Field Empty";
                }
                if((indl_aadhar.length()!=12 || !indl_aadhar.matches("[0-9]+"))){
                    err=err+"\nIncorrect Individual Aadhar";
                }
                if((indl_pan.length()!=10)){
                    err=err+"\nIncorrect Individual Pan";
                }
                if((kin_aadhar.length()!=12 || !kin_aadhar.matches("[0-9]+"))){
                    err=err+"\nIncorrect Kin Aadhar";
                }
                if((kin_pan.length()!=10)){
                    err=err+"\nIncorrect Kin Pan";
                }
                if((pin.length()!=6 || !pin.matches("[0-9]+"))){
                    err=err+"\nIncorrect Pin";
                }
                if(!err.trim().isEmpty()){
                    JOptionPane.showMessageDialog(null, err);
                }
            }

        
        
        }
        catch(Exception e){
            System.out.print("\n"+e);
            String exe = e.getMessage();
            if(exe.contains("Duplicate entry")){
                JOptionPane.showMessageDialog(null,"Data not inserted!\nEntered ARMY NUMBER and Date of joining exists!");
            }
            else if(exe.contains("For input")){
                JOptionPane.showMessageDialog(null,"\nPlease Re-Check Date Formats");
            }
            else
                JOptionPane.showMessageDialog(null,"Data not inserted!");
        }
    }//GEN-LAST:event_update_buttonFMouseClicked

    private void update_buttonFMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_update_buttonFMouseEntered
        // TODO add your handling code here:
    }//GEN-LAST:event_update_buttonFMouseEntered

    private void update_buttonFMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_update_buttonFMouseExited
        // TODO add your handling code here:
    }//GEN-LAST:event_update_buttonFMouseExited

    private void panelFFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_panelFFocusGained
        // TODO add your handling code here:
    }//GEN-LAST:event_panelFFocusGained

    private void panelFMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_panelFMouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_panelFMouseClicked

    private void DateIN_TableCFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_DateIN_TableCFocusGained

    }//GEN-LAST:event_DateIN_TableCFocusGained

    private void DateIN_TableCFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_DateIN_TableCFocusLost

    }//GEN-LAST:event_DateIN_TableCFocusLost

    private void DateOUT_TableCFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_DateOUT_TableCFocusGained
        // TODO add your handling code here:
    }//GEN-LAST:event_DateOUT_TableCFocusGained

    private void DateOUT_TableCFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_DateOUT_TableCFocusLost
        // TODO add your handling code here:
    }//GEN-LAST:event_DateOUT_TableCFocusLost

    private void okayAMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_okayAMouseClicked
        // TODO add your handling code here:
        String sno = army_number_textfieldA.getText();
        if(sno==null || sno.trim().isEmpty()){
            JOptionPane.showMessageDialog(null,"Empty ARMY NUMBER Field");
        }
        else{
            try{
                Statement st = con.createStatement();
                ResultSet rs = null;
                String query="select* from attach_in where Service_no='"+sno+"'";
                String q = "select count(*) as count from attach_in where Service_no='"+sno+"'";
                rs=st.executeQuery(q);
                int count=0;
                if(rs.next()){
                    count=rs.getInt("count");
                    System.out.print("\n"+count);
                }
                rs=st.executeQuery(query);
                //String name1,dob1,rank1,mob1,comp1,indlid,indlaadhar,indlpan,indlacc,indlbank,indlifsc,indlbranch,indlemail,coursesdone,address,kname,kmob,krel,kaadhar,kpan,kacc,kbank,kifsc,kbranch,kemail;
                if(count!=0){
                    while(rs.next()){
                        name_textfieldA.setText(rs.getString("Name"));
                        trade_textfieldA.setText(rs.getString("Trade"));
                        unit_textfieldA.setText(rs.getString("Unit"));
                        location_textfieldA.setText(rs.getString("Location"));
                        AS_textfieldA.setText(rs.getString("Armed_Services"));
                        company_textfieldA.setText(rs.getString("Company"));
                        courses_textfieldA.setText(rs.getString("Courses_done"));
                        String con =rs.getString("Contact_no");
                        String[] c = con.split(" ");
                        indl_mob_textfieldA.setText(c[1]);
                        
                        rank_comboboxA.setSelectedItem(rs.getString("Rank_"));
                        medical_category_textfieldA.setText(rs.getString("Medical_category"));
                         //address
                        String add = rs.getString("Address");
                        String[] ad = add.split(",");
                        villageA.setText(ad[0]);
                        PO_A.setText(ad[3]);
                        distA.setText(ad[1]);
                        tehA.setText(ad[2]);
                        stateA.setText(ad[4]);
                        PIN_A.setText(ad[5]);
                        //indl records
                        indl_Icard_numberA.setText(rs.getString("indl_id"));
                        indl_PAN_A.setText(rs.getString("indl_pan"));
                        indl_aadharA.setText(rs.getString("indl_aadhar"));
                        indl_mailA.setText(rs.getString("indl_email"));
                        indl_account_numberA.setText(rs.getString("indl_bank_acc"));
                        indl_bank_nameA.setText(rs.getString("indl_bank_name"));
                        indl_branchA.setText(rs.getString("indl_branch_name"));
                        indl_IFSC_A.setText(rs.getString("indl_ifsc"));
                        kin_NOK_A.setText(rs.getString("kin_name"));
                        con =rs.getString("kin_contact");
                        c = con.split(" ");
                        kin_mobile_numberA.setText(c[1]);
                        kin_relation_A.setText(rs.getString("kin_relation"));
                        kin_PAN_A.setText(rs.getString("kin_pan"));
                        kin_aadharA.setText(rs.getString("kin_aadhar"));
                        kin_mailA.setText(rs.getString("kin_email"));
                        kin_account_numberA.setText(rs.getString("kin_bank_acc"));
                        kin_bank_nameA.setText(rs.getString("kin_bank_name"));
                        kin_branchA.setText(rs.getString("kin_branch_name"));
                        kin_IFSC_A.setText(rs.getString("kin_ifsc"));
                        
                        select_file_labelA.setText(army_number_textfieldA.getText().trim()+".jpg");
                        
                        
                        kin_NOK_A.setForeground(Color.decode("#000000"));
                        kin_mobile_numberA.setForeground(Color.decode("#000000"));

                        kin_relation_A.setForeground(Color.decode("#000000"));
                        kin_PAN_A.setForeground(Color.decode("#000000"));
                        kin_aadharA.setForeground(Color.decode("#000000"));
                        kin_mailA.setForeground(Color.decode("#000000"));
                        kin_account_numberA.setForeground(Color.decode("#000000"));
                        kin_bank_nameA.setForeground(Color.decode("#000000"));
                        kin_branchA.setForeground(Color.decode("#000000"));
                        kin_IFSC_A.setForeground(Color.decode("#000000"));indl_Icard_numberA.setForeground(Color.decode("#000000"));
                        indl_aadharA.setForeground(Color.decode("#000000"));
                        indl_account_numberA.setForeground(Color.decode("#000000"));
                        indl_branchA.setForeground(Color.decode("#000000"));
                        indl_PAN_A.setForeground(Color.decode("#000000"));
                        indl_mailA.setForeground(Color.decode("#000000"));
                        indl_bank_nameA.setForeground(Color.decode("#000000"));
                        indl_IFSC_A.setForeground(Color.decode("#000000"));
                        //villageA.setText("Vill");
                        villageA.setForeground(Color.decode("#000000"));
                        //PO_A.setText("PO");
                        PO_A.setForeground(Color.decode("#000000"));
                        //distA.setText("Dist");
                        distA.setForeground(Color.decode("#000000"));
                        //tehA.setText("Teh");
                        tehA.setForeground(Color.decode("#000000"));
                        //stateA.setText("State");
                        stateA.setForeground(Color.decode("#000000"));
                        //PIN_A.setText("PIN");
                        PIN_A.setForeground(Color.decode("#000000"));
                        
                        
                         //date of joining
                        con=rs.getString("Date_of_Joining");
                        c=con.split("-");
                        DOJ_year_comboboxA.setSelectedItem(c[2]);
                        DOJ_month_comboboxA.setSelectedItem(c[1]);
                        DOJ_date_comboboxA.setSelectedItem(c[0]);
                        System.out.print("\n"+c[0]+" "+c[1]+" "+c[2]);
                        //dob
                        con=rs.getString("DOB");
                        c=con.split("-");
                        birth_year_comboboxA.setSelectedItem(c[2]);
                        birth_month_comboboxA.setSelectedItem(c[1]);
                        birth_date_comboboxA.setSelectedItem(c[0]);
                        System.out.print("\n"+c[0]+" "+c[1]+" "+c[2]);
                    }
                }
                else{
                    JOptionPane.showMessageDialog(null,"No records of "+sno+" Found");
                }
            }
            catch(Exception e){
                System.out.print("\n"+e);
                JOptionPane.showMessageDialog(null,"Operation Not Successful!");
            }
        }
    }//GEN-LAST:event_okayAMouseClicked

    private void menuF_labelMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_menuF_labelMouseClicked
        fullscreen_panelD.setVisible(false);
        fullscreen_panelE.setVisible(false);
        ScrollPaneA.setVisible(false);
        ScrollPaneB.setVisible(false);
        ScrollPaneC.setVisible(false);
        panelD.setVisible(false);
        panelE.setVisible(false);
        ScrollPaneF.setVisible(true);
        panelG.setVisible(true);

        print_buttonC.setVisible(false);
        print_buttonD.setVisible(false);
        print_buttonE.setVisible(false);
        refresh_buttonD.setVisible(false);
        refresh_buttonE.setVisible(false);
        fullscreen_buttonD.setVisible(false);
        fullscreen_buttonE.setVisible(false);
        remove_buttonC.setVisible(false);
        edit_buttonG.setVisible(true);

        menuF.setBackground(new java.awt.Color(85,65,118));
        menuA.setBackground(new java.awt.Color(64,43,100));
        menuB.setBackground(new java.awt.Color(64,43,100));
        menuC.setBackground(new java.awt.Color(64,43,100));
        menuD.setBackground(new java.awt.Color(64,43,100));
        menuE.setBackground(new java.awt.Color(64,43,100));
    }//GEN-LAST:event_menuF_labelMouseClicked

    private void fullscreen_buttonDMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_fullscreen_buttonDMouseEntered
        fullscreen_buttonD.setBackground(new java.awt.Color(237, 224, 255));
    }//GEN-LAST:event_fullscreen_buttonDMouseEntered

    private void fullscreen_buttonDMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_fullscreen_buttonDMouseExited
        fullscreen_buttonD.setBackground(new java.awt.Color(240,240,240));
    }//GEN-LAST:event_fullscreen_buttonDMouseExited

    private void fullscreen_buttonEMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_fullscreen_buttonEMouseEntered
        fullscreen_buttonE.setBackground(new java.awt.Color(237, 224, 255));
    }//GEN-LAST:event_fullscreen_buttonEMouseEntered

    private void fullscreen_buttonEMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_fullscreen_buttonEMouseExited
        fullscreen_buttonE.setBackground(new java.awt.Color(240,240,240));
    }//GEN-LAST:event_fullscreen_buttonEMouseExited

    private void refresh_buttonDMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_refresh_buttonDMouseEntered
        refresh_buttonD.setBackground(new java.awt.Color(237, 224, 255));
    }//GEN-LAST:event_refresh_buttonDMouseEntered

    private void refresh_buttonDMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_refresh_buttonDMouseExited
        refresh_buttonD.setBackground(new java.awt.Color(240,240,240));
    }//GEN-LAST:event_refresh_buttonDMouseExited

    private void refresh_buttonEMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_refresh_buttonEMouseEntered
        refresh_buttonE.setBackground(new java.awt.Color(237, 224, 255));
    }//GEN-LAST:event_refresh_buttonEMouseEntered

    private void refresh_buttonEMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_refresh_buttonEMouseExited
        refresh_buttonE.setBackground(new java.awt.Color(240,240,240));
    }//GEN-LAST:event_refresh_buttonEMouseExited

    private void print_buttonDMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_print_buttonDMouseEntered
        print_buttonD.setBackground(new java.awt.Color(237, 224, 255));
    }//GEN-LAST:event_print_buttonDMouseEntered

    private void print_buttonDMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_print_buttonDMouseExited
        print_buttonD.setBackground(new java.awt.Color(240,240,240));
    }//GEN-LAST:event_print_buttonDMouseExited

    private void print_buttonEMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_print_buttonEMouseEntered
        print_buttonE.setBackground(new java.awt.Color(237, 224, 255));
    }//GEN-LAST:event_print_buttonEMouseEntered

    private void print_buttonEMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_print_buttonEMouseExited
        print_buttonE.setBackground(new java.awt.Color(240,240,240));
    }//GEN-LAST:event_print_buttonEMouseExited

    private void okayBMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_okayBMouseClicked
        // TODO add your handling code here:
        String sno = army_number_textfieldB.getText();
        if(sno==null || sno.trim().isEmpty()){
            JOptionPane.showMessageDialog(null,"Empty ARMY NUMBER Field");
        }
        else{
            try{
                Statement st = con.createStatement();
                ResultSet rs = null;
                String query="select* from new_registration where Service_no='"+sno+"'";
                String q = "select count(*) as count from new_registration where Service_no='"+sno+"'";
                rs=st.executeQuery(q);
                int count=0;
                if(rs.next()){
                    count=rs.getInt("count");
                }
                
                //String name1,dob1,rank1,mob1,comp1,indlid,indlaadhar,indlpan,indlacc,indlbank,indlifsc,indlbranch,indlemail,coursesdone,address,kname,kmob,krel,kaadhar,kpan,kacc,kbank,kifsc,kbranch,kemail;
                if(count!=0){
                    rs=st.executeQuery(query);
                    //boolean y = rs.next();
                    //System.out.print("\n"+y);
                    while(rs.next()){
                        name_textfieldB.setText(rs.getString("Name"));
                        //purpose_textfieldB.setText(rs.getString("Purpose"));
                        //location_textfieldB.setText(rs.getString("Location"));
                        company_textfieldB.setText(rs.getString("Company"));
                        courses_textfieldB.setText(rs.getString("Courses_done"));
                        String con =rs.getString("indl_contact_no");
                        System.out.println("1");
                        String[] c = con.split(" ");
                        indl_mob_textfieldB.setText(c[1]);
                        
                        rank_comboboxB.setSelectedItem(rs.getString("Rank_"));
                        medical_category_textfieldB.setText(rs.getString("Medical_category"));
                         //address
                        System.out.println("2"); 
                        String add = rs.getString("Address");
                        String[] ad = add.split(",");
                        villageB.setText(ad[0]);
                        PO_B.setText(ad[3]);
                        distB.setText(ad[1]);
                        tehB.setText(ad[2]);
                        stateB.setText(ad[4]);
                        PIN_B.setText(ad[5]);
                        //indl records
                        indl_Icard_numberB.setText(rs.getString("Indl_ICard_Number"));
                        indl_PAN_B.setText(rs.getString("indl_pan"));
                        indl_aadharB.setText(rs.getString("indl_aadhar"));
                        indl_mailB.setText(rs.getString("indl_email"));
                        indl_account_numberB.setText(rs.getString("Indl_Acc_No"));
                        indl_bank_nameB.setText(rs.getString("indl_bank_name"));
                        indl_branchB.setText(rs.getString("indl_bank_branch"));
                        indl_IFSC_B.setText(rs.getString("indl_ifsc"));
                        kin_NOK_B.setText(rs.getString("NOK"));
                        System.out.println("3");
                        con =rs.getString("kin_contact_no");
                        c = con.split(" ");
                        System.out.println("4");
                        kin_mobile_numberB.setText(c[1]);
                        kin_relation_B.setText(rs.getString("kin_relation"));
                        kin_PAN_B.setText(rs.getString("kin_pan"));
                        kin_aadharB.setText(rs.getString("kin_aadhar"));
                        kin_mailB.setText(rs.getString("kin_email"));
                        kin_account_numberB.setText(rs.getString("kin_acc_no"));
                        kin_bank_nameB.setText(rs.getString("kin_bank_name"));
                        kin_branchB.setText(rs.getString("kin_bank_branch"));
                        kin_IFSC_B.setText(rs.getString("kin_ifsc"));
                        select_file_labelB.setText(army_number_textfieldB.getText().trim()+".jpg");
                        
                        
                        kin_NOK_B.setForeground(Color.decode("#000000"));
                        kin_mobile_numberB.setForeground(Color.decode("#000000"));

                        kin_relation_B.setForeground(Color.decode("#000000"));
                        kin_PAN_B.setForeground(Color.decode("#000000"));
                        kin_aadharB.setForeground(Color.decode("#000000"));
                        kin_mailB.setForeground(Color.decode("#000000"));
                        kin_account_numberB.setForeground(Color.decode("#000000"));
                        kin_bank_nameB.setForeground(Color.decode("#000000"));
                        kin_branchB.setForeground(Color.decode("#000000"));
                        kin_IFSC_B.setForeground(Color.decode("#000000"));
                        indl_Icard_numberB.setForeground(Color.decode("#000000"));
                        indl_aadharB.setForeground(Color.decode("#000000"));
                        indl_account_numberB.setForeground(Color.decode("#000000"));
                        indl_branchB.setForeground(Color.decode("#000000"));
                        indl_PAN_B.setForeground(Color.decode("#000000"));
                        indl_mailB.setForeground(Color.decode("#000000"));
                        indl_bank_nameB.setForeground(Color.decode("#000000"));
                        indl_IFSC_B.setForeground(Color.decode("#000000"));
                        //villageB.setText("Vill");
                        villageB.setForeground(Color.decode("#000000"));
                        //PO_B.setText("PO");
                        PO_B.setForeground(Color.decode("#000000"));
                        //distB.setText("Dist");
                        distB.setForeground(Color.decode("#000000"));
                        //tehB.setText("Teh");
                        tehB.setForeground(Color.decode("#000000"));
                        //tateB.setText("State");
                        stateB.setForeground(Color.decode("#000000"));
                        //PIN_B.setText("PIN");
                        PIN_B.setForeground(Color.decode("#000000"));
                        
                        
                         //date of joining
//                        con=rs.getString("Date_of_Attachment");
//                        c=con.split("-");
//                        DOA_year_comboboxB.setSelectedItem(c[2]);
//                        DOA_month_comboboxB.setSelectedItem(c[1]);
//                        DOA_date_comboboxB.setSelectedItem(c[0]);
//                        System.out.print("\n"+c[0]+" "+c[1]+" "+c[2]);
                        //dob
                        con=rs.getString("birth_date");
                        c=con.split("-");
                        birth_year_comboboxB.setSelectedItem(c[2]);
                        birth_month_comboboxB.setSelectedItem(c[1]);
                        birth_date_comboboxB.setSelectedItem(c[0]);
                        System.out.print("\n"+c[0]+" "+c[1]+" "+c[2]);
                        con=rs.getString("Joining_date");
                        if(con==null || con.trim().isEmpty()){
                           DOJ_year_comboboxB.setSelectedIndex(0);
                           DOJ_month_comboboxB.setSelectedIndex(0);
                           DOJ_date_comboboxB.setSelectedIndex(0);

                        }
                        else{
                           c=con.split("-");
                           DOJ_year_comboboxB.setSelectedItem(c[2]);
                           DOJ_month_comboboxB.setSelectedItem(c[1]);
                           DOJ_date_comboboxB.setSelectedItem(c[0]);

                        }
                    }
                }
                else{
                    JOptionPane.showMessageDialog(null,"No records of "+sno+" Found");
                }
            }
            catch(Exception e){
                System.out.print("\n"+e);
                JOptionPane.showMessageDialog(null,"Operation Unsuccessful!");
            }
        }
    }//GEN-LAST:event_okayBMouseClicked

    private void move_panelMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_move_panelMousePressed
        mouseX =evt.getX();
        mouseY =evt.getY();
    }//GEN-LAST:event_move_panelMousePressed

    private void move_panelMouseDragged(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_move_panelMouseDragged
        this.setLocation(this.getX() +evt.getX() -mouseX, this.getY() + evt.getY() -mouseY);
    }//GEN-LAST:event_move_panelMouseDragged

    private void DOA_date_comboboxFActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_DOA_date_comboboxFActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_DOA_date_comboboxFActionPerformed

    private void DOJ_date_comboboxFActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_DOJ_date_comboboxFActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_DOJ_date_comboboxFActionPerformed

    private void army_number_textfieldAKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_army_number_textfieldAKeyPressed
        if (evt.getKeyCode() == java.awt.event.KeyEvent.VK_ENTER) {
            okayAMouseClicked(null);
        }
    }//GEN-LAST:event_army_number_textfieldAKeyPressed

    private void army_number_textfieldBKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_army_number_textfieldBKeyPressed
        if (evt.getKeyCode() == java.awt.event.KeyEvent.VK_ENTER) {
            okayBMouseClicked(null);
        }
    }//GEN-LAST:event_army_number_textfieldBKeyPressed

    private void service_number_textfieldCKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_service_number_textfieldCKeyPressed
        if (evt.getKeyCode() == java.awt.event.KeyEvent.VK_ENTER) {
            okayCMouseClicked(null);
        }
    }//GEN-LAST:event_service_number_textfieldCKeyPressed

    private void service_numberGKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_service_numberGKeyPressed
        if (evt.getKeyCode() == java.awt.event.KeyEvent.VK_ENTER) {
            okayGMouseClicked(null);
        }
    }//GEN-LAST:event_service_numberGKeyPressed
    
    
    
    
    
    
    
       
    
    
    
    
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
            java.util.logging.Logger.getLogger(Attachment.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Attachment.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Attachment.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Attachment.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Attachment().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel ADJT_mob;
    private javax.swing.JLabel ADJT_mob_labelC;
    private javax.swing.JLabel AS_C;
    private javax.swing.JLabel AS_labelA;
    private javax.swing.JLabel AS_labelC;
    private javax.swing.JTextField AS_textfieldA;
    private javax.swing.JSeparator AS_underlineA;
    private javax.swing.JLabel Attachment_labelF;
    private javax.swing.JLabel CO_mob;
    private javax.swing.JLabel CO_mob_labelC;
    private javax.swing.JLabel DOA;
    private javax.swing.JComboBox<String> DOA_date_comboboxB;
    private javax.swing.JComboBox<String> DOA_date_comboboxF;
    private javax.swing.JLabel DOA_labelB;
    private javax.swing.JLabel DOA_labelF;
    private javax.swing.JLabel DOA_labelF1;
    private javax.swing.JComboBox<String> DOA_month_comboboxB;
    private javax.swing.JComboBox<String> DOA_month_comboboxF;
    private javax.swing.JComboBox<String> DOA_year_comboboxB;
    private javax.swing.JComboBox<String> DOA_year_comboboxF;
    private javax.swing.JLabel DOB;
    private javax.swing.JLabel DOB_labelC;
    private javax.swing.JLabel DOJ;
    private javax.swing.JComboBox<String> DOJ_date_comboboxA;
    private javax.swing.JComboBox<String> DOJ_date_comboboxB;
    private javax.swing.JComboBox<String> DOJ_date_comboboxF;
    private javax.swing.JLabel DOJ_labelA;
    private javax.swing.JLabel DOJ_labelB;
    private javax.swing.JLabel DOJ_labelF;
    private javax.swing.JLabel DOJ_labelF1;
    private javax.swing.JComboBox<String> DOJ_month_comboboxA;
    private javax.swing.JComboBox<String> DOJ_month_comboboxB;
    private javax.swing.JComboBox<String> DOJ_month_comboboxF;
    private javax.swing.JComboBox<String> DOJ_year_comboboxA;
    private javax.swing.JComboBox<String> DOJ_year_comboboxB;
    private javax.swing.JComboBox<String> DOJ_year_comboboxF;
    private javax.swing.JScrollPane DateIN_ScrollPaneC;
    private javax.swing.JTable DateIN_TableC;
    private javax.swing.JScrollPane DateOUT_ScrollPaneC;
    private javax.swing.JTable DateOUT_TableC;
    private javax.swing.JLabel NOK_detailsA;
    private javax.swing.JLabel NOK_detailsB;
    private javax.swing.JLabel NOK_detailsF;
    private javax.swing.JLabel NOK_mobileC;
    private javax.swing.JLabel NOK_mobile_labelC;
    private javax.swing.JLabel NOK_nameC;
    private javax.swing.JLabel NOK_name_labelC;
    private javax.swing.JLabel NOK_relationC;
    private javax.swing.JLabel NOK_relation_labelC;
    private javax.swing.JTextField PIN_A;
    private javax.swing.JTextField PIN_B;
    private javax.swing.JLabel PIN_C;
    private javax.swing.JTextField PIN_F;
    private javax.swing.JLabel PIN_labelC;
    private javax.swing.JSeparator PIN_underlineA;
    private javax.swing.JSeparator PIN_underlineB;
    private javax.swing.JSeparator PIN_underlineF;
    private javax.swing.JTextField PO_A;
    private javax.swing.JTextField PO_B;
    private javax.swing.JLabel PO_C;
    private javax.swing.JTextField PO_F;
    private javax.swing.JLabel PO_labelC;
    private javax.swing.JSeparator PO_underlineA;
    private javax.swing.JSeparator PO_underlineB;
    private javax.swing.JSeparator PO_underlineF;
    private javax.swing.JPanel Photo;
    private javax.swing.JScrollPane ScrollPaneA;
    private javax.swing.JScrollPane ScrollPaneB;
    private javax.swing.JScrollPane ScrollPaneC;
    private javax.swing.JScrollPane ScrollPaneD;
    private javax.swing.JScrollPane ScrollPaneE;
    private javax.swing.JScrollPane ScrollPaneF;
    private javax.swing.JScrollPane ScrollPaneG_IN;
    private javax.swing.JScrollPane ScrollPaneG_OUT;
    private javax.swing.JSeparator Separator;
    private javax.swing.JTable TableD;
    private javax.swing.JTable TableE;
    private javax.swing.JTable TableG_IN;
    private javax.swing.JTable TableG_OUT;
    private javax.swing.JPanel add_buttonA;
    private javax.swing.JPanel add_buttonB;
    private javax.swing.JLabel add_labelA;
    private javax.swing.JLabel add_labelA1;
    private javax.swing.JLabel address_labelA;
    private javax.swing.JLabel address_labelA5;
    private javax.swing.JLabel address_labelC;
    private javax.swing.JLabel address_labelF;
    private javax.swing.JLabel army_number_labelA;
    private javax.swing.JLabel army_number_labelB;
    private javax.swing.JLabel army_number_labelF;
    private javax.swing.JTextField army_number_textfieldA;
    private javax.swing.JTextField army_number_textfieldB;
    private javax.swing.JSeparator army_number_underlineA;
    private javax.swing.JSeparator army_number_underlineB;
    private javax.swing.JLabel attachment_labelC;
    private javax.swing.JPanel bg;
    private javax.swing.JComboBox<String> birth_date_comboboxA;
    private javax.swing.JComboBox<String> birth_date_comboboxB;
    private javax.swing.JComboBox<String> birth_date_comboboxF;
    private javax.swing.JLabel birth_labelA;
    private javax.swing.JLabel birth_labelB;
    private javax.swing.JLabel birth_labelF;
    private javax.swing.JComboBox<String> birth_month_comboboxA;
    private javax.swing.JComboBox<String> birth_month_comboboxB;
    private javax.swing.JComboBox<String> birth_month_comboboxF;
    private javax.swing.JComboBox<String> birth_year_comboboxA;
    private javax.swing.JComboBox<String> birth_year_comboboxB;
    private javax.swing.JComboBox<String> birth_year_comboboxF;
    private javax.swing.JLabel company;
    private javax.swing.JLabel company_labelA;
    private javax.swing.JLabel company_labelB;
    private javax.swing.JLabel company_labelC;
    private javax.swing.JLabel company_labelF;
    private javax.swing.JTextField company_textfieldA;
    private javax.swing.JTextField company_textfieldB;
    private javax.swing.JTextField company_textfieldF;
    private javax.swing.JSeparator company_underlineA;
    private javax.swing.JSeparator company_underlineB;
    private javax.swing.JSeparator company_underlineF;
    private javax.swing.JTextField contact_textfieldA2;
    private javax.swing.JTextField contact_textfieldF;
    private javax.swing.JSeparator contact_underlineA2;
    private javax.swing.JSeparator contact_underlineF;
    private javax.swing.JLabel courses_doneC;
    private javax.swing.JLabel courses_done_labelA;
    private javax.swing.JLabel courses_done_labelB;
    private javax.swing.JLabel courses_done_labelC;
    private javax.swing.JLabel courses_done_labelF;
    private javax.swing.JTextField courses_textfieldA;
    private javax.swing.JTextField courses_textfieldB;
    private javax.swing.JTextField courses_textfieldF;
    private javax.swing.JSeparator courses_underlineA;
    private javax.swing.JSeparator courses_underlineB;
    private javax.swing.JSeparator courses_underlineF;
    private javax.swing.JTextField distA;
    private javax.swing.JTextField distB;
    private javax.swing.JLabel distC;
    private javax.swing.JTextField distF;
    private javax.swing.JLabel dist_labelC;
    private javax.swing.JSeparator dist_underlineA;
    private javax.swing.JSeparator dist_underlineB;
    private javax.swing.JSeparator dist_underlineF;
    private javax.swing.JPanel edit_buttonG;
    private javax.swing.JLabel edit_labelG;
    private javax.swing.JPanel exit_fullscreen_buttonD;
    private javax.swing.JPanel exit_fullscreen_buttonE;
    private javax.swing.JLabel exit_fullscreen_labelD;
    private javax.swing.JLabel exit_fullscreen_labelE;
    private javax.swing.JLabel exit_icon;
    private javax.swing.JScrollPane fullscreen_ScrollPaneD;
    private javax.swing.JScrollPane fullscreen_ScrollPaneE;
    private javax.swing.JPanel fullscreen_buttonD;
    private javax.swing.JPanel fullscreen_buttonE;
    private javax.swing.JLabel fullscreen_labelD;
    private javax.swing.JLabel fullscreen_labelE;
    private javax.swing.JPanel fullscreen_panelD;
    private javax.swing.JPanel fullscreen_panelE;
    private javax.swing.JTable fullscreen_tableD;
    private javax.swing.JTable fullscreen_tableE;
    private javax.swing.JLabel go_back_label;
    private javax.swing.JLabel indl_ID_details_label;
    private javax.swing.JLabel indl_ID_details_labelB;
    private javax.swing.JLabel indl_ID_details_labelF;
    private javax.swing.JTextField indl_IFSC_A;
    private javax.swing.JTextField indl_IFSC_B;
    private javax.swing.JTextField indl_IFSC_F;
    private javax.swing.JTextField indl_Icard_numberA;
    private javax.swing.JTextField indl_Icard_numberB;
    private javax.swing.JTextField indl_Icard_numberF;
    private javax.swing.JTextField indl_PAN_A;
    private javax.swing.JTextField indl_PAN_B;
    private javax.swing.JTextField indl_PAN_F;
    private javax.swing.JTextField indl_aadharA;
    private javax.swing.JTextField indl_aadharB;
    private javax.swing.JTextField indl_aadharF;
    private javax.swing.JTextField indl_account_numberA;
    private javax.swing.JTextField indl_account_numberB;
    private javax.swing.JTextField indl_account_numberF;
    private javax.swing.JTextField indl_bank_nameA;
    private javax.swing.JTextField indl_bank_nameB;
    private javax.swing.JTextField indl_bank_nameF;
    private javax.swing.JTextField indl_branchA;
    private javax.swing.JTextField indl_branchB;
    private javax.swing.JTextField indl_branchF;
    private javax.swing.JTextField indl_mailA;
    private javax.swing.JTextField indl_mailB;
    private javax.swing.JTextField indl_mailF;
    private javax.swing.JLabel indl_mob_labelA;
    private javax.swing.JLabel indl_mob_labelB;
    private javax.swing.JLabel indl_mob_labelF;
    private javax.swing.JTextField indl_mob_textfieldA;
    private javax.swing.JTextField indl_mob_textfieldB;
    private javax.swing.JSeparator indl_mob_underlineA;
    private javax.swing.JSeparator indl_mob_underlineB;
    private javax.swing.JLabel indl_mobileC;
    private javax.swing.JLabel indl_mobile_labelC;
    private javax.swing.JLabel indl_mobile_numberA;
    private javax.swing.JSeparator indl_underline0;
    private javax.swing.JSeparator indl_underline1;
    private javax.swing.JSeparator indl_underline10;
    private javax.swing.JSeparator indl_underline11;
    private javax.swing.JSeparator indl_underline12;
    private javax.swing.JSeparator indl_underline13;
    private javax.swing.JSeparator indl_underline14;
    private javax.swing.JSeparator indl_underline15;
    private javax.swing.JSeparator indl_underline2;
    private javax.swing.JSeparator indl_underline3;
    private javax.swing.JSeparator indl_underline4;
    private javax.swing.JSeparator indl_underline5;
    private javax.swing.JSeparator indl_underline6;
    private javax.swing.JSeparator indl_underline7;
    private javax.swing.JSeparator indl_underline8;
    private javax.swing.JSeparator indl_underline9;
    private javax.swing.JSeparator indl_underlineB;
    private javax.swing.JSeparator indl_underlineB0;
    private javax.swing.JSeparator indl_underlineB2;
    private javax.swing.JSeparator indl_underlineB3;
    private javax.swing.JSeparator indl_underlineB4;
    private javax.swing.JSeparator indl_underlineB5;
    private javax.swing.JSeparator indl_underlineB6;
    private javax.swing.JSeparator indl_underlineB7;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JTextField kin_IFSC_A;
    private javax.swing.JTextField kin_IFSC_B;
    private javax.swing.JTextField kin_IFSC_F;
    private javax.swing.JTextField kin_NOK_A;
    private javax.swing.JTextField kin_NOK_B;
    private javax.swing.JTextField kin_NOK_F;
    private javax.swing.JTextField kin_PAN_A;
    private javax.swing.JTextField kin_PAN_B;
    private javax.swing.JTextField kin_PAN_F;
    private javax.swing.JTextField kin_aadharA;
    private javax.swing.JTextField kin_aadharB;
    private javax.swing.JTextField kin_aadharF;
    private javax.swing.JTextField kin_account_numberA;
    private javax.swing.JTextField kin_account_numberB;
    private javax.swing.JTextField kin_account_numberF;
    private javax.swing.JTextField kin_bank_nameA;
    private javax.swing.JTextField kin_bank_nameB;
    private javax.swing.JTextField kin_bank_nameF;
    private javax.swing.JTextField kin_branchA;
    private javax.swing.JTextField kin_branchB;
    private javax.swing.JTextField kin_branchF;
    private javax.swing.JTextField kin_mailA;
    private javax.swing.JTextField kin_mailB;
    private javax.swing.JTextField kin_mailF;
    private javax.swing.JTextField kin_mobile_numberA;
    private javax.swing.JTextField kin_mobile_numberB;
    private javax.swing.JTextField kin_mobile_numberF;
    private javax.swing.JTextField kin_relation_A;
    private javax.swing.JTextField kin_relation_B;
    private javax.swing.JTextField kin_relation_F;
    private javax.swing.JSeparator kin_underlineA0;
    private javax.swing.JSeparator kin_underlineA1;
    private javax.swing.JSeparator kin_underlineA2;
    private javax.swing.JSeparator kin_underlineA3;
    private javax.swing.JSeparator kin_underlineA4;
    private javax.swing.JSeparator kin_underlineA5;
    private javax.swing.JSeparator kin_underlineA6;
    private javax.swing.JSeparator kin_underlineA7;
    private javax.swing.JSeparator kin_underlineA8;
    private javax.swing.JSeparator kin_underlineA9;
    private javax.swing.JSeparator kin_underlineB0;
    private javax.swing.JSeparator kin_underlineB1;
    private javax.swing.JSeparator kin_underlineB2;
    private javax.swing.JSeparator kin_underlineB3;
    private javax.swing.JSeparator kin_underlineB4;
    private javax.swing.JSeparator kin_underlineB5;
    private javax.swing.JSeparator kin_underlineB6;
    private javax.swing.JSeparator kin_underlineB7;
    private javax.swing.JSeparator kin_underlineB8;
    private javax.swing.JSeparator kin_underlineB9;
    private javax.swing.JSeparator kin_underlineF1;
    private javax.swing.JSeparator kin_underlineF10;
    private javax.swing.JSeparator kin_underlineF2;
    private javax.swing.JSeparator kin_underlineF3;
    private javax.swing.JSeparator kin_underlineF4;
    private javax.swing.JSeparator kin_underlineF5;
    private javax.swing.JSeparator kin_underlineF6;
    private javax.swing.JSeparator kin_underlineF7;
    private javax.swing.JSeparator kin_underlineF8;
    private javax.swing.JSeparator kin_underlineF9;
    private javax.swing.JLabel labelA;
    private javax.swing.JLabel labelB;
    private javax.swing.JLabel label_91A;
    private javax.swing.JLabel label_91A1;
    private javax.swing.JLabel label_91B;
    private javax.swing.JLabel label_91F1;
    private javax.swing.JLabel locationC;
    private javax.swing.JLabel location_labelA;
    private javax.swing.JLabel location_labelB;
    private javax.swing.JLabel location_labelC;
    private javax.swing.JTextField location_textfieldA;
    private javax.swing.JTextField location_textfieldB;
    private javax.swing.JSeparator location_underlineA;
    private javax.swing.JSeparator location_underlineB;
    private javax.swing.JLabel medical_category;
    private javax.swing.JLabel medical_category_labelA;
    private javax.swing.JLabel medical_category_labelB;
    private javax.swing.JLabel medical_category_labelC;
    private javax.swing.JLabel medical_category_labelF;
    private javax.swing.JTextField medical_category_textfieldA;
    private javax.swing.JTextField medical_category_textfieldB;
    private javax.swing.JTextField medical_category_textfieldF;
    private javax.swing.JSeparator medical_category_underlineA;
    private javax.swing.JSeparator medical_category_underlineB;
    private javax.swing.JSeparator medical_category_underlineF;
    private javax.swing.JPanel menuA;
    private javax.swing.JLabel menuA_icon;
    private javax.swing.JLabel menuA_label;
    private javax.swing.JPanel menuB;
    private javax.swing.JLabel menuB_icon1;
    private javax.swing.JLabel menuB_label1;
    private javax.swing.JPanel menuC;
    private javax.swing.JLabel menuC_icon;
    private javax.swing.JLabel menuC_label;
    private javax.swing.JPanel menuD;
    private javax.swing.JLabel menuD_icon;
    private javax.swing.JLabel menuD_label;
    private javax.swing.JPanel menuE;
    private javax.swing.JLabel menuE_icon;
    private javax.swing.JLabel menuE_label;
    private javax.swing.JPanel menuF;
    private javax.swing.JLabel menuF_icon;
    private javax.swing.JLabel menuF_label;
    private javax.swing.JSeparator midA1;
    private javax.swing.JSeparator midA2;
    private javax.swing.JSeparator midA3;
    private javax.swing.JSeparator midA6;
    private javax.swing.JSeparator midB1;
    private javax.swing.JSeparator midB3;
    private javax.swing.JSeparator midC1;
    private javax.swing.JSeparator midC2;
    private javax.swing.JSeparator midF0;
    private javax.swing.JSeparator midF1;
    private javax.swing.JSeparator midF2;
    private javax.swing.JSeparator midF3;
    private javax.swing.JSeparator midF4;
    private javax.swing.JLabel minimize;
    private javax.swing.JPanel move_panel;
    private javax.swing.JLabel name;
    private javax.swing.JLabel name_labelA;
    private javax.swing.JLabel name_labelB;
    private javax.swing.JLabel name_labelC;
    private javax.swing.JLabel name_labelF;
    private javax.swing.JTextField name_textfieldA;
    private javax.swing.JTextField name_textfieldB;
    private javax.swing.JTextField name_textfieldF;
    private javax.swing.JSeparator name_underlineA;
    private javax.swing.JSeparator name_underlineB;
    private javax.swing.JSeparator name_underlineF;
    private javax.swing.JLabel okayA;
    private javax.swing.JLabel okayB;
    private javax.swing.JLabel okayC;
    private javax.swing.JLabel okayG;
    private javax.swing.JPanel panelA;
    private javax.swing.JSeparator panelA_bottom_line;
    private javax.swing.JSeparator panelA_bottom_line2;
    private javax.swing.JPanel panelB;
    private javax.swing.JSeparator panelB_bottom_line;
    private javax.swing.JPanel panelC;
    private javax.swing.JPanel panelD;
    private javax.swing.JPanel panelE;
    private javax.swing.JPanel panelF;
    private javax.swing.JSeparator panelF_bottom_line;
    private javax.swing.JPanel panelG;
    private javax.swing.JLabel photo_labelC;
    private javax.swing.JPanel print_buttonC;
    private javax.swing.JPanel print_buttonD;
    private javax.swing.JPanel print_buttonE;
    private javax.swing.JLabel print_labelC;
    private javax.swing.JLabel print_labelD;
    private javax.swing.JLabel print_labelE;
    private javax.swing.JLabel purpose_labelB;
    private javax.swing.JTextField purpose_textfieldB;
    private javax.swing.JSeparator purpose_underlineB;
    private javax.swing.JLabel rank;
    private javax.swing.JComboBox<String> rank_comboboxA;
    private javax.swing.JComboBox<String> rank_comboboxB;
    private javax.swing.JComboBox<String> rank_comboboxF;
    private javax.swing.JLabel rank_labelA;
    private javax.swing.JLabel rank_labelB;
    private javax.swing.JLabel rank_labelC;
    private javax.swing.JLabel rank_labelF;
    private javax.swing.JPanel refresh_buttonD;
    private javax.swing.JPanel refresh_buttonE;
    private javax.swing.JLabel refresh_labelD;
    private javax.swing.JLabel refresh_labelE;
    private javax.swing.JPanel remove_buttonC;
    private javax.swing.JLabel remove_labelC;
    private javax.swing.JPanel select_file_buttonA;
    private javax.swing.JPanel select_file_buttonB;
    private javax.swing.JPanel select_file_buttonF;
    private javax.swing.JLabel select_file_labelA;
    private javax.swing.JLabel select_file_labelB;
    private javax.swing.JLabel select_file_labelF;
    private javax.swing.JLabel service_number;
    private javax.swing.JTextField service_numberG;
    private javax.swing.JLabel service_number_label;
    private javax.swing.JLabel service_number_labelC;
    private javax.swing.JTextField service_number_textfieldC;
    private javax.swing.JSeparator service_number_underlineC;
    private javax.swing.JPanel side_pane;
    private javax.swing.JTextField stateA;
    private javax.swing.JTextField stateB;
    private javax.swing.JLabel stateC;
    private javax.swing.JTextField stateF;
    private javax.swing.JLabel state_labelC;
    private javax.swing.JSeparator state_underlineA;
    private javax.swing.JSeparator state_underlineB;
    private javax.swing.JSeparator state_underlineF;
    private javax.swing.JTextField tehA;
    private javax.swing.JTextField tehB;
    private javax.swing.JTextField tehF;
    private javax.swing.JLabel teh_labelC;
    private javax.swing.JSeparator teh_underlineA;
    private javax.swing.JSeparator teh_underlineB;
    private javax.swing.JSeparator teh_underlineF;
    private javax.swing.JLabel tehsil;
    private javax.swing.JSeparator textfield_underline;
    private javax.swing.JLabel title_icon;
    private javax.swing.JLabel title_label;
    private javax.swing.JLabel tradeC;
    private javax.swing.JLabel trade_labelA;
    private javax.swing.JLabel trade_labelC;
    private javax.swing.JTextField trade_textfieldA;
    private javax.swing.JSeparator trade_underlineA;
    private javax.swing.JLabel unitC;
    private javax.swing.JLabel unit_labelA;
    private javax.swing.JLabel unit_labelC;
    private javax.swing.JTextField unit_textfieldA;
    private javax.swing.JSeparator unit_underlineA;
    private javax.swing.JPanel update_buttonF;
    private javax.swing.JLabel update_labelF;
    private javax.swing.JLabel update_labelF1;
    private javax.swing.JLabel upload_photo_labelA;
    private javax.swing.JLabel upload_photo_labelB;
    private javax.swing.JLabel upload_photo_labelF;
    private javax.swing.JLabel villC;
    private javax.swing.JLabel vill_labelC;
    private javax.swing.JTextField villageA;
    private javax.swing.JTextField villageB;
    private javax.swing.JTextField villageF;
    private javax.swing.JSeparator village_underlineA;
    private javax.swing.JSeparator village_underlineB;
    private javax.swing.JSeparator village_underlineF;
    // End of variables declaration//GEN-END:variables
}