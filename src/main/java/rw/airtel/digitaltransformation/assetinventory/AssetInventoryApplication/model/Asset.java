package rw.airtel.digitaltransformation.assetinventory.AssetInventoryApplication.model;

import rw.airtel.digitaltransformation.assetinventory.AssetInventoryApplication.model.enums.AssetStatus;
import rw.airtel.digitaltransformation.assetinventory.AssetInventoryApplication.model.enums.Condition;
import rw.airtel.digitaltransformation.assetinventory.AssetInventoryApplication.model.enums.DeviceType;
import org.springframework.format.annotation.DateTimeFormat;
import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.PastOrPresent;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "asset")
public class Asset {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "Device name is required")
    @Column(nullable = false)
    private String deviceName;

    @NotBlank(message = "Serial number is required")
    @Column(unique = true, nullable = false)
    private String serialNumber;

    @NotNull(message = "Device type is required")
    @Enumerated(EnumType.STRING)
    private DeviceType deviceType;

    private String brand;
    private String model;

    @PastOrPresent(message = "Purchase date cannot be in the future")
    @DateTimeFormat(pattern = "yyyy-MM-dd")   // ✅ ADD THIS LINE
    private LocalDate purchaseDate;

    @NotNull(message = "Condition is required")
    @Enumerated(EnumType.STRING)
    @Column(name = "asset_condition")
    private Condition assetCondition;

    @Enumerated(EnumType.STRING)
    private AssetStatus status;

    @OneToMany(mappedBy = "asset", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Assignment> assignments = new ArrayList<>();

    public Asset() {}

    // Getters and Setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public String getDeviceName() { return deviceName; }
    public void setDeviceName(String deviceName) { this.deviceName = deviceName; }
    public String getSerialNumber() { return serialNumber; }
    public void setSerialNumber(String serialNumber) { this.serialNumber = serialNumber; }
    public DeviceType getDeviceType() { return deviceType; }
    public void setDeviceType(DeviceType deviceType) { this.deviceType = deviceType; }
    public String getBrand() { return brand; }
    public void setBrand(String brand) { this.brand = brand; }
    public String getModel() { return model; }
    public void setModel(String model) { this.model = model; }
    public LocalDate getPurchaseDate() { return purchaseDate; }
    public void setPurchaseDate(LocalDate purchaseDate) { this.purchaseDate = purchaseDate; }
    public Condition getAssetCondition() { return assetCondition; }
    public void setAssetCondition(Condition assetCondition) { this.assetCondition = assetCondition; }
    public AssetStatus getStatus() { return status; }
    public void setStatus(AssetStatus status) { this.status = status; }
    public List<Assignment> getAssignments() { return assignments; }
    public void setAssignments(List<Assignment> assignments) { this.assignments = assignments; }
}