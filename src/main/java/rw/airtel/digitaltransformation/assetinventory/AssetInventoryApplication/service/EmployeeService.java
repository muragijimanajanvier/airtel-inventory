package rw.airtel.digitaltransformation.assetinventory.AssetInventoryApplication.service;

import rw.airtel.digitaltransformation.assetinventory.AssetInventoryApplication.exception.ResourceNotFoundException;
import rw.airtel.digitaltransformation.assetinventory.AssetInventoryApplication.model.Employee;
import rw.airtel.digitaltransformation.assetinventory.AssetInventoryApplication.repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class EmployeeService {
    @Autowired
    private EmployeeRepository employeeRepository;

    public List<Employee> getAllEmployees() {
        return employeeRepository.findAll();
    }

    public Employee getEmployeeById(Long id) {
        return employeeRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Employee not found with id: " + id));
    }

    public Employee saveEmployee(Employee employee) {
        return employeeRepository.save(employee);
    }

    public void deleteEmployee(Long id) {
        Employee employee = getEmployeeById(id);
        employeeRepository.delete(employee);
    }
}