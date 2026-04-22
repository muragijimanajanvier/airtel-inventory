package rw.airtel.digitaltransformation.assetinventory.AssetInventoryApplication.controller;

import rw.airtel.digitaltransformation.assetinventory.AssetInventoryApplication.service.ReportService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class DashboardController {

    @Autowired
    private ReportService reportService;

    @GetMapping("/dashboard")
    public String dashboard(Model model) {
        model.addAttribute("totalAssets", reportService.getTotalAssets());
        model.addAttribute("totalEmployees", reportService.getTotalEmployees());
        model.addAttribute("activeAssignments", reportService.getActiveAssignments());
        model.addAttribute("availableAssets", reportService.getAvailableAssets());
        return "dashboard";
    }
}