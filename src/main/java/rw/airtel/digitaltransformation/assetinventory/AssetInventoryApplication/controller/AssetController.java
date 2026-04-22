package rw.airtel.digitaltransformation.assetinventory.AssetInventoryApplication.controller;

import rw.airtel.digitaltransformation.assetinventory.AssetInventoryApplication.model.Asset;
import rw.airtel.digitaltransformation.assetinventory.AssetInventoryApplication.model.enums.Condition;
import rw.airtel.digitaltransformation.assetinventory.AssetInventoryApplication.model.enums.DeviceType;
import rw.airtel.digitaltransformation.assetinventory.AssetInventoryApplication.service.AssetService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;

@Controller
@RequestMapping("/assets")
public class AssetController {

    @Autowired
    private AssetService assetService;

    @GetMapping
    public String listAssets(Model model) {
        model.addAttribute("assets", assetService.getAllAssets());
        return "assets";
    }

    @GetMapping("/add")
    public String showAddForm(Model model) {
        model.addAttribute("asset", new Asset());
        model.addAttribute("deviceTypes", DeviceType.values());
        model.addAttribute("conditions", Condition.values());
        return "add-asset";
    }

    @PostMapping("/save")
    public String saveAsset(@Valid @ModelAttribute("asset") Asset asset,
                            BindingResult result,
                            Model model,
                            RedirectAttributes redirectAttributes) {
        if (result.hasErrors()) {
            model.addAttribute("deviceTypes", DeviceType.values());
            model.addAttribute("conditions", Condition.values());
            return "add-asset";
        }
        try {
            assetService.saveAsset(asset);
            redirectAttributes.addFlashAttribute("message", "Asset saved successfully");
            return "redirect:/assets";
        } catch (IllegalArgumentException e) {
            // Catch duplicate serial number exception from service
            redirectAttributes.addFlashAttribute("error", e.getMessage());
            return "redirect:/assets/add";
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("error", "An unexpected error occurred: " + e.getMessage());
            return "redirect:/assets/add";
        }
    }

    @GetMapping("/edit/{id}")
    public String showEditForm(@PathVariable Long id, Model model) {
        Asset asset = assetService.getAssetById(id);
        model.addAttribute("asset", asset);
        model.addAttribute("deviceTypes", DeviceType.values());
        model.addAttribute("conditions", Condition.values());
        return "edit-asset";
    }

    @PostMapping("/update/{id}")
    public String updateAsset(@PathVariable Long id,
                              @Valid @ModelAttribute("asset") Asset asset,
                              BindingResult result,
                              Model model,
                              RedirectAttributes redirectAttributes) {
        if (result.hasErrors()) {
            model.addAttribute("deviceTypes", DeviceType.values());
            model.addAttribute("conditions", Condition.values());
            return "edit-asset";
        }
        try {
            asset.setId(id);
            assetService.saveAsset(asset);
            redirectAttributes.addFlashAttribute("message", "Asset updated successfully");
            return "redirect:/assets";
        } catch (IllegalArgumentException e) {
            redirectAttributes.addFlashAttribute("error", e.getMessage());
            return "redirect:/assets/edit/" + id;
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("error", "An unexpected error occurred");
            return "redirect:/assets/edit/" + id;
        }
    }

    @GetMapping("/delete/{id}")
    public String deleteAsset(@PathVariable Long id, RedirectAttributes redirectAttributes) {
        try {
            assetService.deleteAsset(id);
            redirectAttributes.addFlashAttribute("message", "Asset deleted successfully");
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("error", "Cannot delete asset: " + e.getMessage());
        }
        return "redirect:/assets";
    }
}