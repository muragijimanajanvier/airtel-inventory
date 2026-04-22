package rw.airtel.digitaltransformation.assetinventory.AssetInventoryApplication.exception;

public class ResourceNotFoundException extends RuntimeException {
    public ResourceNotFoundException(String message) {
        super(message);
    }
}