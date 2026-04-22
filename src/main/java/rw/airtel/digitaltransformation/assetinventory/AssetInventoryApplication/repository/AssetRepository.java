package rw.airtel.digitaltransformation.assetinventory.AssetInventoryApplication.repository;

import rw.airtel.digitaltransformation.assetinventory.AssetInventoryApplication.model.Asset;
import rw.airtel.digitaltransformation.assetinventory.AssetInventoryApplication.model.enums.AssetStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;
import java.util.Optional;

public interface AssetRepository extends JpaRepository<Asset, Long> {
    List<Asset> findByStatus(AssetStatus status);
    Optional<Asset> findBySerialNumber(String serialNumber);
}