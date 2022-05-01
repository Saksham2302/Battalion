import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;
import javax.swing.JOptionPane;

public class Database {

    //192.168.27.43:3306,bat,mysql
    String username = "root";
    String password = "chisel";
    Connection con;
    Statement stmt;

    path_file pathF = new path_file();
    
    Connection create_connection(boolean flag) {
        pathF.create_directories();
        try {
            if (flag == true) {
                Class.forName("com.mysql.cj.jdbc.Driver");
                con = DriverManager.getConnection("jdbc:mysql://localhost:3306/battalion", username, password);

            } else {
                con.close();
            }
        } catch (Exception e) {

        }
        return con;
    }

    void create_tables() {
        try {

            con = create_connection(true);
            stmt = con.createStatement();
            
            
            //Binay's tables
            stmt.executeUpdate("CREATE TABLE if not exists attach_out(Service_no varchar(200) NOT NULL,name varchar(200) DEFAULT NULL,DOB varchar(200) DEFAULT NULL,Rank_ varchar(200) NOT NULL,Company varchar(200) NOT NULL,Contact_no varchar(200) DEFAULT NULL,indl_id varchar(200) DEFAULT NULL,indl_aadhar varchar(200) DEFAULT NULL,indl_pan varchar(200) DEFAULT NULL,indl_bank_acc varchar(200) DEFAULT NULL,indl_bank_name varchar(200) DEFAULT NULL,indl_ifsc varchar(200) DEFAULT NULL,indl_branch_name varchar(200) DEFAULT NULL,indl_email varchar(200) DEFAULT NULL,Courses_done varchar(200) NOT NULL,Address varchar(200) DEFAULT NULL,Date_of_Joining varchar(200) DEFAULT NULL,Medical_Category varchar(200) NOT NULL,kin_name varchar(200) DEFAULT NULL,kin_contact varchar(200) DEFAULT NULL,kin_relation varchar(200) DEFAULT NULL,kin_aadhar varchar(200) DEFAULT NULL,kin_pan varchar(200) DEFAULT NULL,kin_bank_acc varchar(200) DEFAULT NULL,kin_bank_name varchar(200) DEFAULT NULL,kin_ifsc varchar(200) DEFAULT NULL,kin_branch_name varchar(200) DEFAULT NULL,kin_email varchar(200) DEFAULT NULL,Date_of_Attachment varchar(200) DEFAULT NULL,Location varchar(200) NOT NULL,Purpose varchar(200) NOT NULL,serv_doa varchar(200) NOT NULL,PRIMARY KEY (serv_doa));");
            stmt.executeUpdate("CREATE TABLE if not exists attach_in(Service_no varchar(200) NOT NULL,name varchar(200) DEFAULT NULL,DOB varchar(200) DEFAULT NULL,Rank_ varchar(200) NOT NULL,Company varchar(200) NOT NULL,Contact_no varchar(200) DEFAULT NULL,indl_id varchar(200) DEFAULT NULL,indl_aadhar varchar(200) DEFAULT NULL,indl_pan varchar(200) DEFAULT NULL,indl_bank_acc varchar(200) DEFAULT NULL,indl_bank_name varchar(200) DEFAULT NULL,indl_ifsc varchar(200) DEFAULT NULL,indl_branch_name varchar(200) DEFAULT NULL,indl_email varchar(200) DEFAULT NULL,Courses_done varchar(200) NOT NULL,Address varchar(200) DEFAULT NULL,Date_of_Joining varchar(200) NOT NULL,Medical_Category varchar(200) NOT NULL,kin_name varchar(200) DEFAULT NULL,kin_contact varchar(200) DEFAULT NULL,kin_relation varchar(200) DEFAULT NULL,kin_aadhar varchar(200) DEFAULT NULL,kin_pan varchar(200) DEFAULT NULL,kin_bank_acc varchar(200) DEFAULT NULL,kin_bank_name varchar(200) DEFAULT NULL,kin_ifsc varchar(200) DEFAULT NULL,kin_branch_name varchar(200) DEFAULT NULL,kin_email varchar(200) DEFAULT NULL,Armed_Services varchar(200) NOT NULL,Trade varchar(200) NOT NULL,Unit varchar(200) NOT NULL,Location varchar(200) NOT NULL,serv_doj varchar(200) NOT NULL,PRIMARY KEY (serv_doj));");
            stmt.executeUpdate("CREATE TABLE if not exists post_in(Service_no varchar(200) NOT NULL,Name varchar(200) DEFAULT NULL,DOB varchar(200) DEFAULT NULL,Rank_ varchar(200) NOT NULL,Company varchar(200) NOT NULL,Contact_no varchar(200) DEFAULT NULL,indl_id varchar(200) DEFAULT NULL,indl_aadhar varchar(200) DEFAULT NULL,indl_pan varchar(200) DEFAULT NULL,indl_bank_acc varchar(200) DEFAULT NULL,indl_bank_name varchar(200) DEFAULT NULL,indl_ifsc varchar(200) DEFAULT NULL,indl_branch_name varchar(200) DEFAULT NULL,indl_email varchar(200) DEFAULT NULL,Courses_done varchar(200) NOT NULL,Address varchar(200) DEFAULT NULL,Date_of_Joining varchar(200) NOT NULL,Medical_Category varchar(200) DEFAULT NULL,kin_name varchar(200) DEFAULT NULL,kin_contact varchar(200) DEFAULT NULL,kin_relation varchar(200) DEFAULT NULL,kin_aadhar varchar(200) DEFAULT NULL,kin_pan varchar(200) DEFAULT NULL,kin_bank_acc varchar(200) DEFAULT NULL,kin_bank_name varchar(200) DEFAULT NULL,kin_ifsc varchar(200) DEFAULT NULL,kin_branch_name varchar(200) DEFAULT NULL,kin_email varchar(200) DEFAULT NULL,serv_doj varchar(200) NOT NULL,PRIMARY KEY (serv_doj));");
            stmt.executeUpdate("CREATE TABLE if not exists post_out (Service_no varchar(200) NOT NULL,name varchar(200) DEFAULT NULL,DOB varchar(200) DEFAULT NULL,Rank_ varchar(200) NOT NULL,Company varchar(200) NOT NULL,Contact_no varchar(200) DEFAULT NULL,indl_id varchar(200) DEFAULT NULL,indl_aadhar varchar(200) DEFAULT NULL,indl_pan varchar(200) DEFAULT NULL,indl_bank_acc varchar(200) DEFAULT NULL,indl_bank_name varchar(200) DEFAULT NULL,indl_ifsc varchar(200) DEFAULT NULL,indl_branch_name varchar(200) DEFAULT NULL,indl_email varchar(200) DEFAULT NULL,Courses_done varchar(200) NOT NULL,Address varchar(200) DEFAULT NULL,Date_of_Joining varchar(200) DEFAULT NULL,Medical_Category varchar(200) NOT NULL,kin_name varchar(200) DEFAULT NULL,kin_contact varchar(200) DEFAULT NULL, kin_relation varchar(200) DEFAULT NULL,kin_aadhar varchar(200) DEFAULT NULL,kin_pan varchar(200) DEFAULT NULL,kin_bank_acc varchar(200) DEFAULT NULL,kin_bank_name varchar(200) DEFAULT NULL,kin_ifsc varchar(200) DEFAULT NULL,kin_branch_name varchar(200) DEFAULT NULL,kin_email varchar(200) DEFAULT NULL,Date_of_Leaving varchar(200) DEFAULT NULL,serv_dol varchar(200) NOT NULL,PRIMARY KEY (serv_dol));");
            stmt.executeUpdate("CREATE TABLE if not exists long_roll (Service_no varchar(200) NOT NULL,DOE varchar(200) DEFAULT NULL,DOC varchar(200) DEFAULT NULL,DOI varchar(200) DEFAULT NULL,Date_ varchar(200) DEFAULT NULL,TTT varchar(200) DEFAULT NULL,MR varchar(200) DEFAULT NULL,i_card varchar(200) DEFAULT NULL,honour_award varchar(200) DEFAULT NULL,child_details varchar(200) DEFAULT NULL,blood_group varchar(200) DEFAULT NULL,PRIMARY KEY (Service_no));");
            stmt.executeUpdate("CREATE TABLE if not exists mov_perm (Service_no varchar(200) NOT NULL,DOD varchar(200) DEFAULT NULL,D_Rationed varchar(200) DEFAULT NULL,to_unit varchar(200) DEFAULT NULL,fn_an varchar(200) DEFAULT NULL,offr varchar(200) DEFAULT NULL,sors varchar(200) DEFAULT NULL,sos varchar(200) DEFAULT NULL,auth varchar(200) DEFAULT NULL,purpose_duty varchar(200) DEFAULT NULL,report_to varchar(200) DEFAULT NULL,type_lve varchar(200) DEFAULT NULL,gtd_lve varchar(200) DEFAULT NULL,cl varchar(200) DEFAULT NULL,al varchar(200) DEFAULT NULL,PRIMARY KEY (Service_no));");
            stmt.executeUpdate("CREATE TABLE if not exists urc_pay (VR varchar(200) NOT NULL,Date varchar(200) DEFAULT NULL,Received_from varchar(200) DEFAULT NULL,acc_of varchar(200) DEFAULT NULL,cash varchar(200) DEFAULT NULL,bank varchar(200) DEFAULT NULL,gen_store varchar(200) DEFAULT NULL,liq_store varchar(200) DEFAULT NULL,prof_gen_store varchar(200) DEFAULT NULL,prof_liq_store varchar(200) DEFAULT NULL,capital varchar(200) DEFAULT NULL,property varchar(200) DEFAULT NULL,sy_dd varchar(200) DEFAULT NULL,sy_cr varchar(200) DEFAULT NULL);");
            stmt.executeUpdate("CREATE TABLE if not exists urc_rec (VR varchar(200) NOT NULL,Date varchar(200) DEFAULT NULL,Received_from varchar(200) DEFAULT NULL,acc_of varchar(200) DEFAULT NULL,cash varchar(200) DEFAULT NULL,bank varchar(200) DEFAULT NULL,gen_store varchar(200) DEFAULT NULL,liq_store varchar(200) DEFAULT NULL,prof_gen_store varchar(200) DEFAULT NULL,prof_liq_store varchar(200) DEFAULT NULL,capital varchar(200) DEFAULT NULL,property varchar(200) DEFAULT NULL,sy_dd varchar(200) DEFAULT NULL,sy_cr varchar(200) DEFAULT NULL);");
            stmt.executeUpdate("CREATE TABLE if not exists urcc_pay (VR varchar(200) NOT NULL,Date varchar(200) DEFAULT NULL,Received_from varchar(200) DEFAULT NULL,acc_of varchar(200) DEFAULT NULL,cash varchar(200) DEFAULT NULL,bank varchar(200) DEFAULT NULL,gen_store varchar(200) DEFAULT NULL,liq_store varchar(200) DEFAULT NULL,prof_gen_store varchar(200) DEFAULT NULL,prof_liq_store varchar(200) DEFAULT NULL,capital varchar(200) DEFAULT NULL,property varchar(200) DEFAULT NULL,sy_dd varchar(200) DEFAULT NULL,sy_cr varchar(200) DEFAULT NULL);");
            stmt.executeUpdate("CREATE TABLE if not exists urcc_rec (VR varchar(200) NOT NULL,Date varchar(200) DEFAULT NULL,Received_from varchar(200) DEFAULT NULL,acc_of varchar(200) DEFAULT NULL,cash varchar(200) DEFAULT NULL,bank varchar(200) DEFAULT NULL,gen_store varchar(200) DEFAULT NULL,liq_store varchar(200) DEFAULT NULL,prof_gen_store varchar(200) DEFAULT NULL,prof_liq_store varchar(200) DEFAULT NULL,capital varchar(200) DEFAULT NULL,property varchar(200) DEFAULT NULL,sy_dd varchar(200) DEFAULT NULL,sy_cr varchar(200) DEFAULT NULL);");
            stmt.executeUpdate("CREATE TABLE if not exists bat_tenure (Location varchar(200) NOT NULL,from_ varchar(200) DEFAULT NULL,to_ varchar(200) DEFAULT NULL,PRIMARY KEY (Location));");
            stmt.executeUpdate("CREATE TABLE if not exists bpet(service_no varchar(200) NOT NULL,Name varchar(200) DEFAULT NULL,Age varchar(200) DEFAULT NULL,Test_date varchar(200) DEFAULT NULL,5km varchar(200) DEFAULT NULL,vrope varchar(200) DEFAULT NULL,hrope varchar(200) DEFAULT NULL,60m_sprint varchar(200) DEFAULT NULL,9m_ditch varchar(200) DEFAULT NULL,Result1 varchar(200) DEFAULT NULL,2400m_run varchar(200) DEFAULT NULL,Chinup varchar(200) DEFAULT NULL,5m_shuttle varchar(200) DEFAULT NULL,100m_sprint varchar(200) DEFAULT NULL,sit_up varchar(200) DEFAULT NULL,Result2 varchar(200) DEFAULT NULL);");
            stmt.executeUpdate("CREATE TABLE if not exists `seniority` (\n"
                    + "  `Service_no` varchar(20) NOT NULL,\n"
                    + "  `DOE` varchar(20) DEFAULT NULL,\n"
                    + "  `DOC` varchar(20) DEFAULT NULL,\n"
                    + "  `TTT` varchar(20) DEFAULT NULL,\n"
                    + "  `MR` varchar(20) DEFAULT NULL,\n"
                    + "  `ACE` varchar(20) DEFAULT NULL,\n"
                    + "  `Screening_nb_sub` varchar(20) DEFAULT NULL,\n"
                    + "  `Screening_sub` varchar(20) DEFAULT NULL,\n"
                    + "  `due_date_nb_sub` varchar(20) DEFAULT NULL,\n"
                    + "  `due_date_sub` varchar(20) DEFAULT NULL,\n"
                    + "  `LNK_dop_date` varchar(20) DEFAULT NULL,\n"
                    + "  `NK_dop_date` varchar(20) DEFAULT NULL,\n"
                    + "  `NK_dos_date` varchar(20) DEFAULT NULL,\n"
                    + "  `LHAV_dop_date` varchar(20) DEFAULT NULL,\n"
                    + "  `HAV_dop_date` varchar(20) DEFAULT NULL,\n"
                    + "  `HAV_dos_date` varchar(20) DEFAULT NULL,\n"
                    + "  `NB_SUB_dop_date` varchar(20) DEFAULT NULL,\n"
                    + "  `NB_SUB_dos_date` varchar(20) DEFAULT NULL,\n"
                    + "  `SUB_dop_date` varchar(20) DEFAULT NULL,\n"
                    + "  `SUB_dos_date` varchar(20) DEFAULT NULL,\n"
                    + "  `SUB_MAJ_dop_date` varchar(20) DEFAULT NULL,\n"
                    + "  `SUB_MAJ_dos_date` varchar(20) DEFAULT NULL,\n"
                    + "  `MACP_1` varchar(20) DEFAULT NULL,\n"
                    + "  `Screening_sep_lnk` varchar(20) DEFAULT NULL,\n"
                    + "  `due_date_sep_lnk` varchar(20) DEFAULT NULL,\n"
                    + "  `MACP_2` varchar(20) DEFAULT NULL,\n"
                    + "  `Screening_bd_nk` varchar(20) DEFAULT NULL,\n"
                    + "  `due_date_bd_nk` varchar(20) DEFAULT NULL,\n"
                    + "  `MACP_3` varchar(20) DEFAULT NULL,\n"
                    + "  `Screening_bd_hav` varchar(20) DEFAULT NULL,\n"
                    + "  `due_date_bd_hav` varchar(20) DEFAULT NULL,\n"
                    + "  PRIMARY KEY (`Service_no`)\n"
                    + ");");
            //Jhalkesh's tables
            stmt.executeUpdate("CREATE TABLE if not exists `trg_rec` (\n"
                    + "  `name` varchar(200) NOT NULL,\n"
                    + "  `loc` varchar(200) NOT NULL,\n"
                    + "  `from_` varchar(200) NOT NULL,\n"
                    + "  `to_` varchar(200) NOT NULL,\n"
                    + "  `offr` varchar(200) NOT NULL,\n"
                    + "  `jco` varchar(200) NOT NULL,\n"
                    + "  `or_` varchar(200) NOT NULL,\n"
                    + "  `remarks` varchar(100) NOT NULL\n"
                    + ") ;");
            stmt.executeUpdate("CREATE TABLE if not exists `temp` (\n"
                    + "  `army_no` varchar(200) NOT NULL,\n"
                    + "  `unit_to` varchar(200) NOT NULL,\n"
                    + "  `dep_date` varchar(200) NOT NULL,\n"
                    + "  `fnan` varchar(200) NOT NULL,\n"
                    + "  `offr` varchar(200) NOT NULL,\n"
                    + "  `sors` varchar(200) NOT NULL,\n"
                    + "  `rationed` varchar(200) NOT NULL,\n"
                    + "  `auth` varchar(200) NOT NULL,\n"
                    + "  `distri` varchar(200) NOT NULL,\n"
                    + "  `purpose` varchar(200) NOT NULL,\n"
                    + "  `report` varchar(200) NOT NULL,\n"
                    + "  `case_file` varchar(200) NOT NULL\n"
                    + ") ;");
            stmt.executeUpdate("CREATE TABLE if not exists `regt_receipt` (\n"
                    + "  `date` varchar(200) NOT NULL,\n"
                    + "  `vr` varchar(200) NOT NULL,\n"
                    + "  `from_` varchar(200) DEFAULT NULL,\n"
                    + "  `on_` varchar(200) DEFAULT NULL,\n"
                    + "  `cash` varchar(200) DEFAULT NULL,\n"
                    + "  `bank` varchar(200) DEFAULT NULL,\n"
                    + "  `regt` varchar(200) DEFAULT NULL,\n"
                    + "  `Gurudwara` varchar(200) DEFAULT NULL,\n"
                    + "  `sports` varchar(200) DEFAULT NULL,\n"
                    + "  `band` varchar(200) DEFAULT NULL,\n"
                    + "  `dr` varchar(200) DEFAULT NULL,\n"
                    + "  `cr` varchar(200) DEFAULT NULL,\n"
                    + "  `bk` varchar(200) DEFAULT NULL,\n"
                    + "  `wel` varchar(200) DEFAULT NULL,\n"
                    + "  `plat` varchar(200) DEFAULT NULL,\n"
                    + "  `fdr` varchar(200) DEFAULT NULL,\n"
                    + "  `prop` varchar(200) DEFAULT NULL\n"
                    + ") ;");
            stmt.executeUpdate("CREATE TABLE if not exists `regt_payment` (\n"
                    + "  `date` varchar(200) NOT NULL,\n"
                    + "  `vr` varchar(200) NOT NULL,\n"
                    + "  `from_` varchar(200) DEFAULT NULL,\n"
                    + "  `on_` varchar(200) DEFAULT NULL,\n"
                    + "  `cash` varchar(200) DEFAULT NULL,\n"
                    + "  `bank` varchar(200) DEFAULT NULL,\n"
                    + "  `regt` varchar(200) DEFAULT NULL,\n"
                    + "  `Gurudwara` varchar(200) DEFAULT NULL,\n"
                    + "  `sports` varchar(200) DEFAULT NULL,\n"
                    + "  `band` varchar(200) DEFAULT NULL,\n"
                    + "  `dr` varchar(200) DEFAULT NULL,\n"
                    + "  `cr` varchar(200) DEFAULT NULL,\n"
                    + "  `bk` varchar(200) DEFAULT NULL,\n"
                    + "  `wel` varchar(200) DEFAULT NULL,\n"
                    + "  `plat` varchar(200) DEFAULT NULL,\n"
                    + "  `fdr` varchar(200) DEFAULT NULL,\n"
                    + "  `prop` varchar(200) DEFAULT NULL\n"
                    + ") ;");
            stmt.executeUpdate("CREATE TABLE if not exists `punishment` (\n"
                    + "  `Army_no` varchar(200) NOT NULL,\n"
                    + "  `name` varchar(200) NOT NULL,\n"
                    + "  `aa` varchar(200) NOT NULL,\n"
                    + "  `punish` varchar(200) NOT NULL,\n"
                    + "  `awarded_by` varchar(200) NOT NULL,\n"
                    + "  `Pun_Date` varchar(200) NOT NULL\n"
                    + ") ;");
            stmt.executeUpdate("CREATE TABLE if not exists `out_dak` (\n"
                    + "  `letter_no` varchar(200) NOT NULL,\n"
                    + "  `dol` varchar(200) NOT NULL,\n"
                    + "  `dod` varchar(200) NOT NULL,\n"
                    + "  `to_` varchar(200) NOT NULL,\n"
                    + "  `subject` varchar(200) NOT NULL,\n"
                    + "  `classification` varchar(200) NOT NULL,\n"
                    + "  `mode` varchar(200) NOT NULL,\n"
                    + "  PRIMARY KEY (`letter_no`)\n"
                    + ") ;");
            stmt.executeUpdate("CREATE TABLE if not exists `inc_dak` (\n"
                    + "  `letter_no` varchar(200) NOT NULL,\n"
                    + "  `dol` varchar(200) NOT NULL,\n"
                    + "  `dor` varchar(200) NOT NULL,\n"
                    + "  `from_` varchar(200) NOT NULL,\n"
                    + "  `to_` varchar(200) NOT NULL,\n"
                    + "  `subject` varchar(200) NOT NULL,\n"
                    + "  `classification` varchar(200) NOT NULL,\n"
                    + "  `file_no` varchar(200) NOT NULL,\n"
                    + "  `remarks` varchar(100) NOT NULL,\n"
                    + "  `reply` varchar(200) NOT NULL,\n"
                    + "  PRIMARY KEY (`letter_no`)\n"
                    + ") ;");
            stmt.executeUpdate("CREATE TABLE if not exists `ex_co` (\n"
                    + "  `Army_no` varchar(200) NOT NULL,\n"
                    + "  `Name` varchar(200) NOT NULL,\n"
                    + "  `Rank_` varchar(200) NOT NULL,\n"
                    + "  `Company` varchar(200) NOT NULL,\n"
                    + "  `Courses_done` varchar(200) NOT NULL,\n"
                    + "  `Address` varchar(200) NOT NULL,\n"
                    + "  `Indi_Contact_No` varchar(200) NOT NULL,\n"
                    + "  `Birth_date` varchar(200) DEFAULT NULL,\n"
                    + "  `Joining_date` varchar(200) NOT NULL,\n"
                    + "  `Medical_Category` varchar(200) NOT NULL,\n"
                    + "  `Indi_ICard_Number` varchar(200) NOT NULL,\n"
                    + "  `Indi_PAN` varchar(200) NOT NULL,\n"
                    + "  `Indi_Aadhar` varchar(200) NOT NULL,\n"
                    + "  `Indi_Email` varchar(200) NOT NULL,\n"
                    + "  `Indi_Acc_No` varchar(200) NOT NULL,\n"
                    + "  `Indi_Bank_Name` varchar(200) NOT NULL,\n"
                    + "  `Indi_Bank_Branch` varchar(200) NOT NULL,\n"
                    + "  `Indi_IFSC` varchar(200) NOT NULL,\n"
                    + "  `NOK` varchar(200) NOT NULL,\n"
                    + "  `Kin_Contact_No` varchar(200) NOT NULL,\n"
                    + "  `Kin_Relation` varchar(200) NOT NULL,\n"
                    + "  `Kin_PAN` varchar(200) NOT NULL,\n"
                    + "  `Kin_Aadhar` varchar(200) NOT NULL,\n"
                    + "  `Kin_Email` varchar(200) NOT NULL,\n"
                    + "  `Kin_Acc_No` varchar(200) NOT NULL,\n"
                    + "  `Kin_Bank_Name` varchar(200) NOT NULL,\n"
                    + "  `Kin_Bank_Branch` varchar(200) NOT NULL,\n"
                    + "  `Kin_IFSC` varchar(200) NOT NULL,\n"
                    + "  `adjt_mob` varchar(200) NOT NULL,\n"
                    + "  `date_of_start` varchar(200) NOT NULL,\n"
                    + "  `date_of_end` varchar(200) DEFAULT NULL,\n"
                    + "  `photo` varchar(200) DEFAULT NULL,\n"
                    + "  `new_` varchar(200) DEFAULT NULL,\n"
                    + "  PRIMARY KEY (`Army_no`)\n"
                    + ");");
            stmt.executeUpdate("CREATE TABLE if not exists `current_co` (\n"
                    + "  `Army_no` varchar(200) NOT NULL,\n"
                    + "  `Name` varchar(200) NOT NULL,\n"
                    + "  `Rank_` varchar(200) NOT NULL,\n"
                    + "  `Company` varchar(200) NOT NULL,\n"
                    + "  `Courses_done` varchar(200) NOT NULL,\n"
                    + "  `Address` varchar(200) NOT NULL,\n"
                    + "  `Indi_Contact_No` varchar(200) NOT NULL,\n"
                    + "  `Birth_date` varchar(200) DEFAULT NULL,\n"
                    + "  `Joining_date` varchar(200) NOT NULL,\n"
                    + "  `Medical_Category` varchar(200) NOT NULL,\n"
                    + "  `Indi_ICard_Number` varchar(200) NOT NULL,\n"
                    + "  `Indi_PAN` varchar(200) NOT NULL,\n"
                    + "  `Indi_Aadhar` varchar(200) NOT NULL,\n"
                    + "  `Indi_Email` varchar(200) NOT NULL,\n"
                    + "  `Indi_Acc_No` varchar(200) NOT NULL,\n"
                    + "  `Indi_Bank_Name` varchar(200) NOT NULL,\n"
                    + "  `Indi_Bank_Branch` varchar(200) NOT NULL,\n"
                    + "  `Indi_IFSC` varchar(200) NOT NULL,\n"
                    + "  `NOK` varchar(200) NOT NULL,\n"
                    + "  `Kin_Contact_No` varchar(200) NOT NULL,\n"
                    + "  `Kin_Relation` varchar(200) NOT NULL,\n"
                    + "  `Kin_PAN` varchar(200) NOT NULL,\n"
                    + "  `Kin_Aadhar` varchar(200) NOT NULL,\n"
                    + "  `Kin_Email` varchar(200) NOT NULL,\n"
                    + "  `Kin_Acc_No` varchar(200) NOT NULL,\n"
                    + "  `Kin_Bank_Name` varchar(200) NOT NULL,\n"
                    + "  `Kin_Bank_Branch` varchar(200) NOT NULL,\n"
                    + "  `Kin_IFSC` varchar(200) NOT NULL,\n"
                    + "  `adjt_mob` varchar(200) NOT NULL,\n"
                    + "  `date_of_start` varchar(200) NOT NULL,\n"
                    + "  `date_of_end` varchar(200) DEFAULT NULL,\n"
                    + "  `photo` varchar(200) DEFAULT NULL,\n"
                    + "  PRIMARY KEY (`Army_no`)\n"
                    + ");");
            stmt.executeUpdate("CREATE TABLE if not exists `csd_receipt` (\n"
                    + "  `date` varchar(200) NOT NULL,\n"
                    + "  `vr` varchar(200) NOT NULL,\n"
                    + "  `from_` varchar(200) DEFAULT NULL,\n"
                    + "  `on_` varchar(200) DEFAULT NULL,\n"
                    + "  `cash` varchar(200) DEFAULT NULL,\n"
                    + "  `bank` varchar(200) DEFAULT NULL,\n"
                    + "  `infra` varchar(200) DEFAULT NULL,\n"
                    + "  `wel` varchar(200) DEFAULT NULL,\n"
                    + "  `csd` varchar(200) DEFAULT NULL,\n"
                    + "  `over_` varchar(200) DEFAULT NULL,\n"
                    + "  `dr` varchar(200) DEFAULT NULL,\n"
                    + "  `cr` varchar(200) DEFAULT NULL,\n"
                    + "  `sports` varchar(200) DEFAULT NULL,\n"
                    + "  `pro` varchar(200) DEFAULT NULL\n"
                    + ") ;");
            stmt.executeUpdate("CREATE TABLE if not exists `csd_payment` (\n"
                    + "  `date` varchar(200) NOT NULL,\n"
                    + "  `vr` varchar(200) NOT NULL,\n"
                    + "  `from_` varchar(200) DEFAULT NULL,\n"
                    + "  `on_` varchar(200) DEFAULT NULL,\n"
                    + "  `cash` varchar(200) DEFAULT NULL,\n"
                    + "  `bank` varchar(200) DEFAULT NULL,\n"
                    + "  `infra` varchar(200) DEFAULT NULL,\n"
                    + "  `wel` varchar(200) DEFAULT NULL,\n"
                    + "  `csd` varchar(200) DEFAULT NULL,\n"
                    + "  `over_` varchar(200) DEFAULT NULL,\n"
                    + "  `dr` varchar(200) DEFAULT NULL,\n"
                    + "  `cr` varchar(200) DEFAULT NULL,\n"
                    + "  `sports` varchar(200) DEFAULT NULL,\n"
                    + "  `pro` varchar(200) DEFAULT NULL\n"
                    + ");");
            stmt.executeUpdate("CREATE TABLE if not exists `cadre` (\n"
                    + "  `Army_no` varchar(200) DEFAULT NULL,\n"
                    + "  `Date_from` varchar(200) NOT NULL,\n"
                    + "  `Date_to` varchar(200) NOT NULL,\n"
                    + "  `cadre_NK` varchar(200) NOT NULL,\n"
                    + "  `HAV` varchar(200) NOT NULL,\n"
                    + "  `NB_SUB` varchar(200) NOT NULL,\n"
                    + "  `BPET` varchar(200) NOT NULL,\n"
                    + "  `firing` varchar(200) NOT NULL,\n"
                    + "  `drill` varchar(200) NOT NULL,\n"
                    + "  `ipit` varchar(200) NOT NULL,\n"
                    + "  `bfg` varchar(200) NOT NULL,\n"
                    + "  `total` varchar(200) NOT NULL,\n"
                    + "  `result` varchar(200) NOT NULL,\n"
                    + "  `remarks` varchar(200) NOT NULL\n"
                    + ");");
            stmt.executeUpdate("CREATE TABLE if not exists `veh` (\n" + "  `nomenclature` varchar(200) NOT NULL,\n" + "  `held` varchar(200) NOT NULL,\n" + "  `defi` varchar(200) NOT NULL,\n" + "  `on_road` varchar(200) NOT NULL,\n" + "  `off_road` varchar(200) NOT NULL,\n" + "  `auth` varchar(200) NOT NULL,\n" + "  `sur` varchar(200) NOT NULL,\n" + "  `remarks` varchar(100) NOT NULL,\n" + "  PRIMARY KEY (`nomenclature`)\n" + ") ;");

            //Shruti's tables
            stmt.executeUpdate("Create Table if not exists Achievements_post_ind(Award Varchar(200) Primary key, count VARCHAR(200) default 0);");
            stmt.executeUpdate("Create Table if not exists Achievements_pre_ind(Award Varchar(200) Primary key, count VARCHAR(200) default 0);");
            stmt.executeUpdate("Create table if not exists Auth_Strength(bat_strength INT NOT NULL DEFAULT 0, Company_strength INT NOT NULL DEFAULT 0)");
            stmt.executeUpdate("Create Table if not exists Competition_Record(comp_type Varchar(200) NOT NULL, trg_year VARCHAR(200) NOT NULL, from_date date NOT NULL, to_date date NOT NULL, offr VARCHAR(200) NOT NULL, JCO VARCHAR(200) NOT NULL, OR_ VARCHAR(200) NOT NULL, pos1 VARCHAR(200), pos2 VARCHAR(200), pos3 VARCHAR(200), Primary Key(comp_type, from_date, to_date));");
            stmt.executeUpdate("Create Table if not exists Course_Record_Army(Service_no Varchar(200) NOT NULL, Name VARCHAR(200) NOT NULL, Course VARCHAR(200) NOT NULL, Institute VARCHAR(200) NOT NULL, From_Date date, To_date date, grading VARCHAR(200) NOT NULL);");
            stmt.executeUpdate("Create Table if not exists Course_Record_Local(Service_no Varchar(200) NOT NULL, Name VARCHAR(200) NOT NULL, Course VARCHAR(200) NOT NULL, Institute VARCHAR(200) NOT NULL, From_Date date, To_date date, grading VARCHAR(200) NOT NULL);");
            stmt.executeUpdate("Create Table if not exists firing(Service_no Varchar(200) NOT NULL, Name VARCHAR(200), Rank_ VARCHAR(200), Company VARCHAR(200),  Wpn VARCHAR(200), Test_Date date, `400m` VARCHAR(40), `300m` VARCHAR(40), `200m` VARCHAR(40), `100m` VARCHAR(40), result Varchar(40), Primary key(Service_no, Test_Date));");
            stmt.executeUpdate("Create Table if not exists JCO_Mess_receipt(Voucher Varchar(200) NOT NULL PRIMARY KEY, date date NOT NULL, Received_from VARCHAR(200), on_account VARCHAR(200), cash VARCHAR(200), bank VARCHAR(200), mess_fund VARCHAR(200), MMA VARCHAR(200),entertainment VARCHAR(200), Paper VARCHAR(200), silver VARCHAR(200), JCO_security VARCHAR(200), momento VARCHAR(200), Wine VARCHAR(200), regt_cutting VARCHAR(200), M_S VARCHAR(200), sy_DR VARCHAR(200), sy_CR VARCHAR(200), FDR VARCHAR(200), property VARCHAR(200));");
            stmt.executeUpdate("Create Table if not exists Officers_Mess_payment(Voucher Varchar(200) NOT NULL PRIMARY KEY, date date NOT NULL, Received_from VARCHAR(200), on_account VARCHAR(200), cash VARCHAR(200), bank VARCHAR(200), mess_fund VARCHAR(200), MMA VARCHAR(200),entertainment VARCHAR(200), Paper VARCHAR(200), silver VARCHAR(200), momento VARCHAR(200), Wine VARCHAR(200), regt_cutting VARCHAR(200), M_S VARCHAR(200), offr_sports VARCHAR(200), offr_deposit VARCHAR(200),  sy_DR VARCHAR(200), sy_CR VARCHAR(200), FDR VARCHAR(200), property VARCHAR(200));");
            stmt.executeUpdate("Create Table if not exists Officers_Mess_receipt(Voucher Varchar(200) NOT NULL PRIMARY KEY, date date NOT NULL, Received_from VARCHAR(200), on_account VARCHAR(200), cash VARCHAR(200), bank VARCHAR(200), mess_fund VARCHAR(200), MMA VARCHAR(200),entertainment VARCHAR(200), Paper VARCHAR(200), silver VARCHAR(200), momento VARCHAR(200), Wine VARCHAR(200), regt_cutting VARCHAR(200), M_S VARCHAR(200), offr_sports VARCHAR(200), offr_deposit VARCHAR(200),  sy_DR VARCHAR(200), sy_CR VARCHAR(200), FDR VARCHAR(200), property VARCHAR(200));");
            stmt.executeUpdate("Create Table if not exists Our_Heroes(Service_no Varchar(200) NOT NULL, Name VARCHAR(200) NOT NULL, Operation VARCHAR(100) NOT NULL, Incident_detail VARCHAR(400), PRIMARY KEY(service_no, operation));");
            stmt.executeUpdate("Create Table if not exists outpass(Service_no Varchar(200) NOT NULL, Name VARCHAR(200), Time_in VARCHAR(200), Time_out VARCHAR(200) NOT NULL, Date date);");
            stmt.executeUpdate("create table if not exists Pension_out(Service_no varchar(200) PRIMARY KEY, Name varchar(200) NOT NULL, Rank_ varchar(200) not null,Company varchar(200) not null,Courses_done varchar(200) not null,Address varchar(300) not null,Indl_Contact_No varchar(200) not null, Birth_date varchar(200) not null,Joining_date varchar(200) not null, Medical_Category varchar(200) not null, Indl_ICard_Number varchar(200) not null, Indl_PAN varchar(200) not null, Indl_Aadhar varchar(200) not null, Indl_Email varchar(200) not null, Indl_Acc_No varchar(200) not null, Indl_Bank_Name varchar(200) not null, Indl_Bank_Branch varchar(200) not null, Indl_IFSC varchar(200) not null, NOK varchar(200) not null, Kin_Contact_No varchar(200) not null, Kin_Relation varchar(200) not null, Kin_PAN varchar(200) not null, Kin_Aadhar varchar(200) not null, Kin_Email varchar(200) not null, Kin_Acc_No varchar(200) not null, Kin_Bank_Name varchar(200) not null, Kin_Bank_Branch varchar(200) not null, Kin_IFSC varchar(200) not null, Retirement_date varchar(200));");
            stmt.executeUpdate("Create Table if not exists State_AMN(Nomenclature Varchar(200) NOT NULL Primary key, Auth VARCHAR(200) NOT NULL, On_demand VARCHAR(200) NOT NULL, Defi VARCHAR(200) NOT NULL, Cont_no VARCHAR(200) NOT NULL, Cont_dt VARCHAR(200) NOT NULL, remarks VARCHAR(100));");
            stmt.executeUpdate("Create Table if not exists State_WPN(Nomenclature Varchar(200) NOT NULL, Auth VARCHAR(200) NOT NULL, SUR VARCHAR(200) NOT NULL, Defi VARCHAR(200) NOT NULL, Coy_Wise VARCHAR(200) NOT NULL, Held VARCHAR(200) NOT NULL, remarks VARCHAR(100), Primary Key(Nomenclature, Coy_wise, held));");
            stmt.executeUpdate("Create Table if not exists JCO_Mess_payment(Voucher Varchar(200) NOT NULL PRIMARY KEY, date date NOT NULL, Received_from VARCHAR(200), on_account VARCHAR(200), cash VARCHAR(200), bank VARCHAR(200), mess_fund VARCHAR(200), MMA VARCHAR(200),entertainment VARCHAR(200), Paper VARCHAR(200), silver VARCHAR(200), JCO_security VARCHAR(200), momento VARCHAR(200), Wine VARCHAR(200), regt_cutting VARCHAR(200), M_S VARCHAR(200), sy_DR VARCHAR(200), sy_CR VARCHAR(200), FDR VARCHAR(200), property VARCHAR(200));");

            //Abhishek's tables
            stmt.executeUpdate("CREATE TABLE if not exists `notify` (\n"
                    + "  `Date` date NOT NULL,\n"
                    + "  `Time` time NOT NULL,\n"
                    + "  `Message` varchar(100) NOT NULL,\n"
                    + "  `File_name` varchar(200) DEFAULT NULL,\n"
                    + "  `status` boolean NOT NULL,\n"
                    + "  PRIMARY KEY (`Date`,`Time`)\n"
                    + ")");

            //Saksham's tables
            stmt.executeUpdate("create table if not exists new_registration(service_no varchar(200) PRIMARY KEY, Name varchar(200) NOT NULL, Rank_ varchar(200) not null,Company varchar(200) not null,Courses_done varchar(200) not null,Address varchar(300) not null,Indl_Contact_No varchar(200) not null,birth_date varchar(200) not null,Joining_date varchar(200) not null, Medical_Category varchar(200) not null, Indl_ICard_Number varchar(200) not null, Indl_PAN varchar(200) not null, Indl_Aadhar varchar(200) not null, Indl_Email varchar(200) not null, Indl_Acc_No varchar(200) not null, Indl_Bank_Name varchar(200) not null, Indl_Bank_Branch varchar(200) not null, Indl_IFSC varchar(200) not null, NOK varchar(200) not null, Kin_Contact_No varchar(200) not null, Kin_Relation varchar(200) not null, Kin_PAN varchar(200) not null, Kin_Aadhar varchar(200) not null, Kin_Email varchar(200) not null, Kin_Acc_No varchar(200) not null, Kin_Bank_Name varchar(200) not null, Kin_Bank_Branch varchar(200) not null, Kin_IFSC varchar(200) not null);");
            stmt.executeUpdate("CREATE TABLE if not exists`parade_state` (\n"
                    + "  `JCO` int DEFAULT NULL,\n"
                    + "  `Others` int DEFAULT NULL,\n"
                    + "  `Officers` int DEFAULT NULL\n"
                    + ") ");

            stmt.executeUpdate("create table if not exists login( password varchar(200) not null default 'admin');");

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);

        }

    }
}
