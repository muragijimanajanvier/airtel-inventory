package rw.airtel.digitaltransformation.assetinventory.AssetInventoryApplication.repository;

import rw.airtel.digitaltransformation.assetinventory.AssetInventoryApplication.model.AuditLog;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface AuditLogRepository extends JpaRepository<AuditLog, Long> {
    List<AuditLog> findAllByOrderByTimestampDesc();
    List<AuditLog> findByAssignmentId(Long assignmentId);   // add this
}