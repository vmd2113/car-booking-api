package com.duongw.carbookingapi.controller;

import com.duongw.carbookingapi.DTO.DriverDTO;
import com.duongw.carbookingapi.service.DriverService;
import com.duongw.carbookingapi.service.TripService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * DriverController là một RESTful Controller xử lý các yêu cầu liên quan đến thông tin của tài xế.
 * Nó chịu trách nhiệm xử lý các HTTP requests từ phía Client và gọi các phương thức tương ứng trong DriverService.
 */
@RestController
public class DriverController {

    private DriverService driverService;
    private TripService tripService;

    /**
     * Constructor của DriverController.
     *
     * @param driverService Đối tượng DriverService để thực hiện các thao tác liên quan đến tài xế.
     * @param tripService   Đối tượng TripService để thực hiện các thao tác liên quan đến chuyến đi.
     */
    @Autowired
    public DriverController(DriverService driverService, TripService tripService) {
        this.driverService = driverService;
        this.tripService = tripService;
    }

    /**
     * Lấy danh sách tất cả tài xế có sẵn.
     *
     * @return Danh sách DriverDTO chứa thông tin chi tiết về mỗi tài xế.
     */
    @GetMapping("/drivers")
    public List<DriverDTO> findAll() {
        return driverService.findAll();
    }

    /**
     * Thêm một tài xế mới vào hệ thống.
     *
     * @param driverDTO Thông tin chi tiết của tài xế được gửi từ Client.
     * @return ResponseEntity chứa thông tin chi tiết của tài xế và mã trạng thái HTTP.
     */
    @PostMapping("/drivers")
    public ResponseEntity<DriverDTO> addDriver(@Valid @RequestBody DriverDTO driverDTO) {
        return new ResponseEntity<>(driverService.addDriver(driverDTO), HttpStatus.CREATED);
    }

    /**
     * Lấy thông tin chi tiết của một tài xế dựa trên ID.
     *
     * @param id ID của tài xế cần truy vấn.
     * @return ResponseEntity chứa thông tin chi tiết của tài xế và mã trạng thái HTTP.
     */
    @GetMapping("/drivers/{id}")
    public ResponseEntity<DriverDTO> getDriver(@PathVariable(name = "id") Integer id) {
        return new ResponseEntity<>(driverService.getDriver(id), HttpStatus.OK);
    }

    /**
     * Cập nhật thông tin của một tài xế dựa trên ID.
     *
     * @param id        ID của tài xế cần cập nhật.
     * @param driverDTO Thông tin mới của tài xế được gửi từ Client.
     * @return ResponseEntity chứa thông tin chi tiết của tài xế sau khi đã cập nhật và mã trạng thái HTTP.
     */
    @PutMapping("/drivers/{id}")
    public ResponseEntity<DriverDTO> updateDriver(@PathVariable(name = "id") Integer id, @Valid @RequestBody DriverDTO driverDTO) {
        return new ResponseEntity<>(driverService.updateDriver(id, driverDTO), HttpStatus.OK);
    }

    /**
     * Xóa một tài xế dựa trên ID.
     *
     * @param id ID của tài xế cần xóa.
     * @return ResponseEntity chứa thông báo và mã trạng thái HTTP.
     */
    @DeleteMapping("/drivers/{id}")
    public ResponseEntity<String> deleteDriver(@PathVariable(name = "id") Integer id) {
        driverService.deleteDriver(id);
        return new ResponseEntity<>("Driver was deleted successfully!", HttpStatus.OK);
    }
}

