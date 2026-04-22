package rw.airtel.digitaltransformation.assetinventory.AssetInventoryApplication.repository;

import rw.airtel.digitaltransformation.assetinventory.AssetInventoryApplication.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByUsername(String username);
}