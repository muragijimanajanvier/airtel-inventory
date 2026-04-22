package rw.airtel.digitaltransformation.assetinventory.AssetInventoryApplication.controller;

import rw.airtel.digitaltransformation.assetinventory.AssetInventoryApplication.model.enums.AssetStatus;
import rw.airtel.digitaltransformation.assetinventory.AssetInventoryApplication.model.enums.DeviceType;
import rw.airtel.digitaltransformation.assetinventory.AssetInventoryApplication.service.ReportService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/reports")
public class ReportController {

    @Autowired
    private ReportService reportService;

    // Main report menu page (existing)
    @GetMapping
    public String reports(Model model,
                          @RequestParam(required = false) String keyword,
                          @RequestParam(required = false) String deviceType,
                          @RequestParam(required = false) String status) {
        model.addAttribute("assets", reportService.filterAssets(keyword, deviceType, status));
        model.addAttribute("deviceTypes", DeviceType.values());
        model.addAttribute("statuses", AssetStatus.values());
        model.addAttribute("auditLogs", reportService.getAuditLogs());
        return "reports";
    }

    // Printable Assets Report
    @GetMapping("/assets")
    public String printAssetsReport(Model model,
                                    @RequestParam(required = false) String deviceType,
                                    @RequestParam(required = false) String status) {
        model.addAttribute("assets", reportService.getAllAssetsForReport(deviceType, status));
        model.addAttribute("totalRecords", reportService.getAllAssetsForReport(deviceType, status).size());
        model.addAttribute("currentDateTime", reportService.getCurrentDateTime());
        model.addAttribute("deviceTypeFilter", deviceType);
        model.addAttribute("statusFilter", status);
        return "print-assets";
    }

    // Printable Employees Report
    @GetMapping("/employees")
    public String printEmployeesReport(Model model,
                                       @RequestParam(required = false) String department) {
        model.addAttribute("employees", reportService.getAllEmployeesForReport(department));
        model.addAttribute("totalRecords", reportService.getAllEmployeesForReport(department).size());
        model.addAttribute("currentDateTime", reportService.getCurrentDateTime());
        model.addAttribute("departmentFilter", department);
        return "print-employees";
    }

    // Printable Assignments Report
    @GetMapping("/assignments")
    public String printAssignmentsReport(Model model,
                                         @RequestParam(required = false) String status,
                                         @RequestParam(required = false) String startDate,
                                         @RequestParam(required = false) String endDate) {
        model.addAttribute("assignments", reportService.getAllAssignmentsForReport(status, startDate, endDate));
        model.addAttribute("totalRecords", reportService.getAllAssignmentsForReport(status, startDate, endDate).size());
        model.addAttribute("currentDateTime", reportService.getCurrentDateTime());
        model.addAttribute("statusFilter", status);
        return "print-assignments";
    }

    // Printable Audit Log Report
    @GetMapping("/audit")
    public String printAuditReport(Model model) {
        model.addAttribute("auditLogs", reportService.getAllAuditLogsForReport());
        model.addAttribute("totalRecords", reportService.getAllAuditLogsForReport().size());
        model.addAttribute("currentDateTime", reportService.getCurrentDateTime());
        return "print-audit";
    }
}