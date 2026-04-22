package rw.airtel.digitaltransformation.assetinventory.AssetInventoryApplication.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class AuthController {

    @GetMapping("/login")
    public String login() {
        return "login";
    }

    // ❌ Remove this method – it conflicts with DashboardController
    // @GetMapping("/dashboard")
    // public String dashboard() {
    //     return "dashboard";
    // }
}