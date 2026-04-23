package rw.airtel.digitaltransformation.assetinventory.AssetInventoryApplication.config;

import rw.airtel.digitaltransformation.assetinventory.AssetInventoryApplication.model.User;
import rw.airtel.digitaltransformation.assetinventory.AssetInventoryApplication.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
public class DataInitializer implements CommandLineRunner {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public void run(String... args) throws Exception {
        // Remove the old default Admin user if it exists (to enforce new credentials)
        userRepository.findByUsername("Admin").ifPresent(admin -> {
            userRepository.delete(admin);
            System.out.println("Removed old Admin user.");
        });

        // Create the new SysAdmin user with required registration numbers
        if (userRepository.findByUsername("24RP04809").isEmpty()) {
            User sysAdmin = new User();
            sysAdmin.setUsername("24RP04809");
            sysAdmin.setPassword(passwordEncoder.encode("24RP04727"));
            sysAdmin.setRole("ADMIN");
            sysAdmin.setEnabled(true);
            userRepository.save(sysAdmin);
            System.out.println("SysAdmin created: 24RP04809 / 24RP04727");
        } else {
            System.out.println("SysAdmin already exists.");
        }
    }
}