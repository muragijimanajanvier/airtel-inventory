package rw.airtel.digitaltransformation.assetinventory.AssetInventoryApplication.service;

import rw.airtel.digitaltransformation.assetinventory.AssetInventoryApplication.model.*;
import rw.airtel.digitaltransformation.assetinventory.AssetInventoryApplication.model.enums.AssetStatus;
import rw.airtel.digitaltransformation.assetinventory.AssetInventoryApplication.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ReportService {

    @Autowired
    private AssetRepository assetRepository;
    @Autowired
    private EmployeeRepository employeeRepository;
    @Autowired
    private AssignmentRepository assignmentRepository;
    @Autowired
    private AuditLogRepository auditLogRepository;

    // ========== DASHBOARD STATISTICS METHODS ==========
    public long getTotalAssets() {
        return assetRepository.count();
    }

    public long getTotalEmployees() {
        return employeeRepository.count();
    }

    public long getActiveAssignments() {
        return assetRepository.findByStatus(AssetStatus.ASSIGNED).size();
    }

    public long getAvailableAssets() {
        return assetRepository.findByStatus(AssetStatus.AVAILABLE).size();
    }

    // ========== FILTERING METHODS (for reports menu) ==========
    public List<Asset> filterAssets(String keyword, String deviceType, String status) {
        List<Asset> assets = assetRepository.findAll();
        if (keyword != null && !keyword.isEmpty()) {
            assets = assets.stream()
                    .filter(a -> (a.getDeviceName() != null && a.getDeviceName().toLowerCase().contains(keyword.toLowerCase())) ||
                                 (a.getSerialNumber() != null && a.getSerialNumber().toLowerCase().contains(keyword.toLowerCase())) ||
                                 (a.getBrand() != null && a.getBrand().toLowerCase().contains(keyword.toLowerCase())))
                    .collect(Collectors.toList());
        }
        if (deviceType != null && !deviceType.isEmpty()) {
            assets = assets.stream()
                    .filter(a -> a.getDeviceType() != null && a.getDeviceType().name().equalsIgnoreCase(deviceType))
                    .collect(Collectors.toList());
        }
        if (status != null && !status.isEmpty()) {
            assets = assets.stream()
                    .filter(a -> a.getStatus() != null && a.getStatus().name().equalsIgnoreCase(status))
                    .collect(Collectors.toList());
        }
        return assets;
    }

    public List<AuditLog> getAuditLogs() {
        return auditLogRepository.findAllByOrderByTimestampDesc();
    }

    // ========== PRINT REPORT METHODS ==========
    public List<Asset> getAllAssetsForReport(String deviceType, String status) {
        List<Asset> assets = assetRepository.findAll();
        if (deviceType != null && !deviceType.isEmpty()) {
            assets = assets.stream()
                    .filter(a -> a.getDeviceType() != null && a.getDeviceType().name().equalsIgnoreCase(deviceType))
                    .collect(Collectors.toList());
        }
        if (status != null && !status.isEmpty()) {
            assets = assets.stream()
                    .filter(a -> a.getStatus() != null && a.getStatus().name().equalsIgnoreCase(status))
                    .collect(Collectors.toList());
        }
        return assets;
    }

    public List<Employee> getAllEmployeesForReport(String department) {
        List<Employee> employees = employeeRepository.findAll();
        if (department != null && !department.isEmpty()) {
            employees = employees.stream()
                    .filter(e -> e.getDepartment() != null && e.getDepartment().equalsIgnoreCase(department))
                    .collect(Collectors.toList());
        }
        return employees;
    }

    public List<Assignment> getAllAssignmentsForReport(String status, String startDate, String endDate) {
        List<Assignment> assignments = assignmentRepository.findAll();
        if (status != null && !status.isEmpty()) {
            assignments = assignments.stream()
                    .filter(a -> a.getStatus() != null && a.getStatus().name().equalsIgnoreCase(status))
                    .collect(Collectors.toList());
        }
        return assignments;
    }

    public List<AuditLog> getAllAuditLogsForReport() {
        return auditLogRepository.findAllByOrderByTimestampDesc();
    }

    public String getCurrentDateTime() {
        return LocalDateTime.now().format(DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss"));
    }
}