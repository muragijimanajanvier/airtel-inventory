package rw.airtel.digitaltransformation.assetinventory.AssetInventoryApplication.service;

import rw.airtel.digitaltransformation.assetinventory.AssetInventoryApplication.exception.ResourceNotFoundException;
import rw.airtel.digitaltransformation.assetinventory.AssetInventoryApplication.model.*;
import rw.airtel.digitaltransformation.assetinventory.AssetInventoryApplication.model.enums.AssetStatus;
import rw.airtel.digitaltransformation.assetinventory.AssetInventoryApplication.model.enums.AssignmentStatus;
import rw.airtel.digitaltransformation.assetinventory.AssetInventoryApplication.model.enums.Condition;
import rw.airtel.digitaltransformation.assetinventory.AssetInventoryApplication.repository.AssignmentRepository;
import rw.airtel.digitaltransformation.assetinventory.AssetInventoryApplication.repository.AuditLogRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Service
public class AssignmentService {
    @Autowired
    private AssignmentRepository assignmentRepository;
    @Autowired
    private AuditLogRepository auditLogRepository;
    @Autowired
    private AssetService assetService;
    @Autowired
    private EmployeeService employeeService;

    public List<Assignment> getAllAssignments() {
        return assignmentRepository.findAll();
    }

    public Assignment getAssignmentById(Long id) {
        return assignmentRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Assignment not found"));
    }

    @Transactional
    public Assignment assignDevice(Long assetId, Long employeeId, LocalDate issueDate, String notes) {
        Asset asset = assetService.getAssetById(assetId);
        if (asset.getStatus() != AssetStatus.AVAILABLE) {
            throw new IllegalStateException("Asset is not available for assignment");
        }
        Employee employee = employeeService.getEmployeeById(employeeId);

        Assignment assignment = new Assignment();
        assignment.setAsset(asset);
        assignment.setEmployee(employee);
        assignment.setIssueDate(issueDate != null ? issueDate : LocalDate.now());
        assignment.setStatus(AssignmentStatus.ACTIVE);
        assignment.setNotes(notes);

        asset.setStatus(AssetStatus.ASSIGNED);
        assetService.saveAsset(asset);

        Assignment saved = assignmentRepository.save(assignment);

        AuditLog log = new AuditLog();
        log.setAssignment(saved);
        log.setAction("ASSIGN");
        log.setDetails(String.format("Device '%s' assigned to %s on %s", asset.getDeviceName(), employee.getName(), saved.getIssueDate()));
        log.setTimestamp(LocalDateTime.now());
        auditLogRepository.save(log);

        return saved;
    }

    @Transactional
    public void returnDevice(Long assignmentId, Condition newCondition, LocalDate returnDate) {
        Assignment assignment = getAssignmentById(assignmentId);
        if (assignment.getStatus() == AssignmentStatus.RETURNED) {
            throw new IllegalStateException("Device already returned");
        }

        Asset asset = assignment.getAsset();
        if (newCondition != null) {
            asset.setAssetCondition(newCondition);
        }
        asset.setStatus(AssetStatus.AVAILABLE);
        assetService.saveAsset(asset);

        assignment.setReturnDate(returnDate != null ? returnDate : LocalDate.now());
        assignment.setStatus(AssignmentStatus.RETURNED);
        assignmentRepository.save(assignment);

        AuditLog log = new AuditLog();
        log.setAssignment(assignment);
        log.setAction("RETURN");
        log.setDetails(String.format("Device '%s' returned with condition: %s", asset.getDeviceName(), asset.getAssetCondition()));
        log.setTimestamp(LocalDateTime.now());
        auditLogRepository.save(log);
    }

    @Transactional
    public void deleteAssignment(Long id) {
        Assignment assignment = getAssignmentById(id);
        
        // If assignment is active, free the asset first
        if (assignment.getStatus() == AssignmentStatus.ACTIVE) {
            Asset asset = assignment.getAsset();
            asset.setStatus(AssetStatus.AVAILABLE);
            assetService.saveAsset(asset);
        }
        
        // ✅ Delete all audit logs associated with this assignment (to avoid foreign key violation)
        List<AuditLog> logs = auditLogRepository.findByAssignmentId(id);
        if (logs != null && !logs.isEmpty()) {
            auditLogRepository.deleteAll(logs);
        }
        
        // Now safe to delete the assignment
        assignmentRepository.delete(assignment);
    }
}