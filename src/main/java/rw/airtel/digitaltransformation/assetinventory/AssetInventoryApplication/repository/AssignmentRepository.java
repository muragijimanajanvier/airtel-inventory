package rw.airtel.digitaltransformation.assetinventory.AssetInventoryApplication.repository;

import rw.airtel.digitaltransformation.assetinventory.AssetInventoryApplication.model.Assignment;
import rw.airtel.digitaltransformation.assetinventory.AssetInventoryApplication.model.enums.AssignmentStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;
import java.util.Optional;

public interface AssignmentRepository extends JpaRepository<Assignment, Long> {
    List<Assignment> findByStatus(AssignmentStatus status);
    Optional<Assignment> findByAssetIdAndStatus(Long assetId, AssignmentStatus status);
}