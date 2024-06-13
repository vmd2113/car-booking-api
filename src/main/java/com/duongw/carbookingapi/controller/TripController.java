package com.duongw.carbookingapi.controller;

import com.duongw.carbookingapi.DTO.TripDTO;
import com.duongw.carbookingapi.service.TripService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * TripController là một RESTful Controller xử lý các yêu cầu liên quan đến thông tin chuyến đi.
 * Nó chịu trách nhiệm xử lý các HTTP requests từ phía Client và gọi các phương thức tương ứng trong TripService.
 */
@RestController
@RequestMapping("/api/v1")
public class TripController {

    private final TripService tripService;

    /**
     * Constructor của TripController.
     *
     * @param tripService Đối tượng TripService để thực hiện các thao tác liên quan đến chuyến đi.
     */
    @Autowired
    public TripController(TripService tripService) {
        this.tripService = tripService;
    }

    /**
     * Lấy danh sách tất cả các chuyến đi.
     *
     * @return Danh sách TripDTO chứa thông tin chi tiết về mỗi chuyến đi.
     */
    @GetMapping(value = "/trips")
    public List<TripDTO> findAll() {
        return tripService.findAll();
    }

    /**
     * Lấy danh sách tất cả các chuyến đi của một tài xế dựa trên ID của tài xế.
     *
     * @param id ID của tài xế cần truy vấn.
     * @return Danh sách TripDTO chứa thông tin chi tiết về mỗi chuyến đi của tài xế.
     */
    @GetMapping(value = "/drivers/{id}/trips")
    public List<TripDTO> findAllTripByDriverId(@PathVariable(name = "id") Integer id) {
        return tripService.findAllTripsByDriverId(id);
    }

    /**
     * Thêm một chuyến đi mới vào hệ thống.
     *
     * @param tripDTO Thông tin chi tiết của chuyến đi được gửi từ Client.
     * @return ResponseEntity chứa thông tin chi tiết của chuyến đi và mã trạng thái HTTP.
     */
    @PostMapping(value = "/trips")
    public ResponseEntity<TripDTO> addTrip(@Valid @RequestBody TripDTO tripDTO) {
        ResponseEntity<TripDTO> response = new ResponseEntity<>(tripService.addTrip(tripDTO), HttpStatus.CREATED);
        return response;
    }

    /**
     * Lấy thông tin chi tiết của một chuyến đi dựa trên ID.
     *
     * @param id ID của chuyến đi cần truy vấn.
     * @return ResponseEntity chứa thông tin chi tiết của chuyến đi và mã trạng thái HTTP.
     */
    @GetMapping(value = "/trips/{id}")
    public ResponseEntity<TripDTO> getTrip(@PathVariable(name = "id") Integer id) {
        ResponseEntity<TripDTO> response = new ResponseEntity<TripDTO>(tripService.getTrip(id), HttpStatus.OK);
        return response;
    }

    /**
     * Cập nhật thông tin của một chuyến đi dựa trên ID.
     *
     * @param id      ID của chuyến đi cần cập nhật.
     * @param tripDTO Thông tin mới của chuyến đi được gửi từ Client.
     * @return ResponseEntity chứa thông tin chi tiết của chuyến đi sau khi đã cập nhật và mã trạng thái HTTP.
     */
    @PutMapping(value = "/trips/{id}")
    public ResponseEntity<TripDTO> updateTrip(@PathVariable(name = "id") Integer id, @Valid @RequestBody TripDTO tripDTO) {
        ResponseEntity<TripDTO> response = new ResponseEntity<TripDTO>(tripService.updateTrip(id, tripDTO), HttpStatus.OK);
        return response;
    }

    /**
     * Xóa một chuyến đi dựa trên ID.
     *
     * @param id ID của chuyến đi cần xóa.
     * @return ResponseEntity chứa thông báo và mã trạng thái HTTP.
     */
    @DeleteMapping("/trips/{id}")
    public ResponseEntity<String> deleteTrip(@PathVariable(name = "id") Integer id) {
        tripService.deleteTrip(id);
        ResponseEntity response = new ResponseEntity<String>("Trip was deleted successfully", HttpStatus.OK);
        return response;
    }
}
