package rw.airtel.digitaltransformation.assetinventory.AssetInventoryApplication.controller;

import rw.airtel.digitaltransformation.assetinventory.AssetInventoryApplication.model.enums.Condition;
import rw.airtel.digitaltransformation.assetinventory.AssetInventoryApplication.service.AssetService;
import rw.airtel.digitaltransformation.assetinventory.AssetInventoryApplication.service.AssignmentService;
import rw.airtel.digitaltransformation.assetinventory.AssetInventoryApplication.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import java.time.LocalDate;

@Controller
@RequestMapping("/assignments")
public class AssignmentController {
    @Autowired
    private AssignmentService assignmentService;
    @Autowired
    private AssetService assetService;
    @Autowired
    private EmployeeService employeeService;

    @GetMapping
    public String listAssignments(Model model) {
        model.addAttribute("assignments", assignmentService.getAllAssignments());
        model.addAttribute("assets", assetService.getAvailableAssets());
        model.addAttribute("employees", employeeService.getAllEmployees());
        return "assignments";
    }

    @PostMapping("/assign")
    public String assignDevice(@RequestParam Long assetId, @RequestParam Long employeeId,
                               @RequestParam(required = false) String notes,
                               RedirectAttributes redirectAttributes) {
        try {
            assignmentService.assignDevice(assetId, employeeId, LocalDate.now(), notes);
            redirectAttributes.addFlashAttribute("message", "Device assigned successfully");
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("error", e.getMessage());
        }
        return "redirect:/assignments";
    }

    @GetMapping("/return/{id}")
    public String showReturnForm(@PathVariable Long id, Model model) {
        model.addAttribute("assignmentId", id);
        model.addAttribute("conditions", Condition.values());
        return "return-form";
    }

    @PostMapping("/return")
    public String returnDevice(@RequestParam Long assignmentId, @RequestParam Condition condition,
                               RedirectAttributes redirectAttributes) {
        try {
            assignmentService.returnDevice(assignmentId, condition, LocalDate.now());
            redirectAttributes.addFlashAttribute("message", "Device returned successfully");
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("error", e.getMessage());
        }
        return "redirect:/assignments";
    }

    @GetMapping("/delete/{id}")
    public String deleteAssignment(@PathVariable Long id, RedirectAttributes redirectAttributes) {
        assignmentService.deleteAssignment(id);
        redirectAttributes.addFlashAttribute("message", "Assignment deleted");
        return "redirect:/assignments";
    }
}