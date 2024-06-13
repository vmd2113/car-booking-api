package com.duongw.carbookingapi.service;

import com.duongw.carbookingapi.DTO.DriverDTO;
import com.duongw.carbookingapi.entity.Driver;
import com.duongw.carbookingapi.exception.ResourceNotFoundException;
import com.duongw.carbookingapi.repository.DriverRepository;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

/**
 * DriverService là một service layer thực hiện logic kinh doanh liên quan đến thông tin của tài xế.
 * Nó chịu trách nhiệm gọi và xử lý các thao tác từ Controller và giao tiếp với repository để truy vấn và lưu trữ dữ liệu.
 */
@Service
public class DriverService {
    private DriverRepository driverRepository;
    private ModelMapper modelMapper;

    /**
     * Constructor của DriverService.
     *
     * @param driverRepository Đối tượng repository cho các thao tác liên quan đến Driver.
     * @param modelMapper      Đối tượng ModelMapper để chuyển đổi giữa các đối tượng DTO và Entity.
     */
    public DriverService(DriverRepository driverRepository, ModelMapper modelMapper) {
        this.driverRepository = driverRepository;
        this.modelMapper = modelMapper;
    }

    /**
     * Lấy danh sách tất cả tài xế có sẵn.
     *
     * @return Danh sách DriverDTO chứa thông tin chi tiết về mỗi tài xế.
     */
    public List<DriverDTO> findAll() {
        List<Driver> drivers = driverRepository.findAll();
        return drivers.stream().map(driver -> modelMapper.map(driver, DriverDTO.class)).collect(Collectors.toList());
    }

    /**
     * Thêm một tài xế mới vào hệ thống.
     *
     * @param driverDTO Thông tin chi tiết của tài xế được gửi từ Controller.
     * @return DriverDTO được trả về sau khi lưu vào cơ sở dữ liệu.
     */
    public DriverDTO addDriver(DriverDTO driverDTO) {
        Driver driver = modelMapper.map(driverDTO, Driver.class);
        return modelMapper.map(driverRepository.save(driver), DriverDTO.class);
    }

    /**
     * Lấy thông tin chi tiết của một tài xế dựa trên ID.
     *
     * @param id ID của tài xế cần truy vấn.
     * @return DriverDTO chứa thông tin chi tiết về tài xế.
     */
    public DriverDTO getDriver(Integer id) {
        Driver driver = driverRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Driver", "ID", String.valueOf(id)));
        return modelMapper.map(driver, DriverDTO.class);
    }

    /**
     * Cập nhật thông tin của một tài xế dựa trên ID.
     *
     * @param id        ID của tài xế cần cập nhật.
     * @param driverDTO Thông tin mới được gửi từ Controller.
     * @return DriverDTO sau khi đã cập nhật.
     */
    public DriverDTO updateDriver(Integer id, DriverDTO driverDTO) {
        Driver driver = driverRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Driver", "id", String.valueOf(id)));
        driver.setAddress(driverDTO.getAddress());
        driver.setEmail(driverDTO.getEmail());
        driver.setFirstName(driverDTO.getFirstName());
        driver.setLastName(driverDTO.getLastName());
        driver.setPhone(driverDTO.getPhone());

        Driver savedDriver = driverRepository.save(driver);
        return modelMapper.map(savedDriver, DriverDTO.class);
    }

    /**
     * Xóa một tài xế dựa trên ID.
     *
     * @param id ID của tài xế cần xóa.
     */
    public void deleteDriver(Integer id) {
        Driver driver = driverRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Driver", "id", String.valueOf(id)));
        driverRepository.delete(driver);
    }
}
