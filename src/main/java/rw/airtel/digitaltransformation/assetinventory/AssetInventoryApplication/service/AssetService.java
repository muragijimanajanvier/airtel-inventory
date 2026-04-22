package rw.airtel.digitaltransformation.assetinventory.AssetInventoryApplication.service;

import rw.airtel.digitaltransformation.assetinventory.AssetInventoryApplication.exception.ResourceNotFoundException;
import rw.airtel.digitaltransformation.assetinventory.AssetInventoryApplication.model.Asset;
import rw.airtel.digitaltransformation.assetinventory.AssetInventoryApplication.model.enums.AssetStatus;
import rw.airtel.digitaltransformation.assetinventory.AssetInventoryApplication.repository.AssetRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class AssetService {
    @Autowired
    private AssetRepository assetRepository;

    public List<Asset> getAllAssets() {
        return assetRepository.findAll();
    }

    public Asset getAssetById(Long id) {
        return assetRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Asset not found with id: " + id));
    }

    public Asset saveAsset(Asset asset) {
        // Check for duplicate serial number on new asset
        if (asset.getId() == null) {
            Optional<Asset> existing = assetRepository.findBySerialNumber(asset.getSerialNumber());
            if (existing.isPresent()) {
                throw new IllegalArgumentException("Serial number already exists. Please use a unique serial number.");
            }
            asset.setStatus(AssetStatus.AVAILABLE);
        } else if (asset.getStatus() == null) {
            // For updates, if status is null, set a default
            asset.setStatus(AssetStatus.AVAILABLE);
        }
        return assetRepository.save(asset);
    }

    public void deleteAsset(Long id) {
        Asset asset = getAssetById(id);
        assetRepository.delete(asset);
    }

    public List<Asset> getAvailableAssets() {
        return assetRepository.findByStatus(AssetStatus.AVAILABLE);
    }
}