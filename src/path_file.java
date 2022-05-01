
import java.io.File;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author chise
 */
public class path_file {

    String shared_folder = "C:\\Users\\chise\\Documents\\Battalion";
    String csv = "C:\\Users\\chise\\Documents\\Battalion\\csv";
    String images = "C:\\Users\\chise\\Documents\\Battalion\\images";
    String notification_files = "C:\\Users\\chise\\Documents\\Battalion\\notification_files";
    String coy = "C:\\Users\\chise\\Documents\\Battalion\\coy_data";
    String gallery = "C:\\Users\\chise\\Documents\\Battalion\\gallery";

    String path_arr[] = {"C:\\Users\\chise\\Documents\\Battalion",
        "C:\\Users\\chise\\Documents\\Battalion\\csv",
        "C:\\Users\\chise\\Documents\\Battalion\\images",
        "C:\\Users\\chise\\Documents\\Battalion\\notification_files",
        "C:\\Users\\chise\\Documents\\Battalion\\coy_data",
        "C:\\Users\\chise\\Documents\\Battalion\\gallery"
    };

    void create_directories() {

        for (int i = 0; i < path_arr.length; i++) {
            File directory = new File(path_arr[i]);
            if (!directory.exists()) {
                directory.mkdir();
            }
        }
    }

//    String shared_folder="//192.168.27.43/Users/Admin/Documents/Battalion";
//    String csv="//192.168.27.43/Users/Admin/Documents/Battalion/csv";
//    String images="//192.168.27.43/Users/Admin/Documents/Battalion/images";
//    String notification_files="//192.168.27.43/Users/Admin/Documents/Battalion/notification_files";
//    String coy="//192.168.27.43/Users/Admin/Documents/Battalion/coy_data";
//    String gallery="//192.168.27.43/Users/Admin/Documents/Battalion/gallery";
}
