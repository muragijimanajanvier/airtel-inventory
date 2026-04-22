package rw.airtel.digitaltransformation.assetinventory.AssetInventoryApplication.repository;

import rw.airtel.digitaltransformation.assetinventory.AssetInventoryApplication.model.Employee;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EmployeeRepository extends JpaRepository<Employee, Long> {
}