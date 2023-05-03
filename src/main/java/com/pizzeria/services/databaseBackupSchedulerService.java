package com.pizzeria.services;

import com.pizzeria.entity.classes.Address;
import com.pizzeria.entity.classes.User;
import lombok.extern.slf4j.Slf4j;
import org.apache.tomcat.util.http.fileupload.FileUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

@Slf4j
@EnableScheduling
@Configuration
@ConditionalOnProperty(name = "scheduler.enabled", matchIfMissing = true)
public class databaseBackupSchedulerService {
    @Autowired
    public AddressService as;
    @Autowired
    public UserService us;
    private static final String backupDirectoryPath = "/Users/petr/Documents/4sem/JAVA_Spring/lab18/DB_backups/";
    private static final File folder = new File(backupDirectoryPath);
    private static final File usersFile = new File(backupDirectoryPath + "Users.txt");
    private static final File addressesFile = new File(backupDirectoryPath + "Addresses.txt");
    public boolean cleanBackupDirectory() throws IOException {
        log.warn("Cleaning backup directory...");
        try {
            FileUtils.cleanDirectory(folder);
            log.info("Backup directory clean");
            return true;
        }
        catch(Exception e) {
            log.error("Error while cleaning backup directory");
            return false;
        }
    }

    //@Scheduled(fixedDelay = 30000)
    public void databaseBackup() throws IOException {
        if (cleanBackupDirectory()) {
            log.warn("Starting DB backup...");
            try {
                log.warn("Starting Users table backup...");
                FileWriter usersFileWriter = new FileWriter(usersFile, false);
                List<User> usersList = us.getAllUsers();
                for (User u : usersList) {
                    usersFileWriter.write(u.toString() + System.getProperty("line.separator"));
                }
                usersFileWriter.close();
                log.info("Users table backup complete");
            }
            catch(Exception e) {
                log.error("Error while backup Users table");
            }

            try {
                log.warn("Starting Addresses backup...");
                FileWriter addressesFileWriter = new FileWriter(addressesFile, false);
                List<Address> addressList = as.getAllAddresses();
                for (Address a : addressList) {
                    addressesFileWriter.write(a.toString() + System.getProperty("line.separator"));
                }
                addressesFileWriter.close();
                log.info("Addresses table backup complete");
            }
            catch(Exception e) {
                log.error("Error while backup Addresses table");
            }
        }
    }
}
