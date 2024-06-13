package com.duongw.carbookingapi.controller;

import com.duongw.carbookingapi.DTO.BookingDetailDTO;
import com.duongw.carbookingapi.DTO.BookingDetailResponse;
import com.duongw.carbookingapi.entity.AppConstant;
import com.duongw.carbookingapi.service.BookingDetailService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 * BookingDetailController là một RESTful Controller xử lý các yêu cầu liên quan đến chi tiết đặt xe.
 * Nó chịu trách nhiệm xử lý các HTTP requests từ phía Client và gọi các phương thức tương ứng trong BookingDetailService.
 */
@RestController
public class BookingDetailController {

    private BookingDetailService detailService;

    @Autowired
    public BookingDetailController(BookingDetailService detailService) {
        this.detailService = detailService;
    }

    /**
     * Lấy danh sách tất cả chi tiết đặt xe dựa trên các tham số như trang, kích thước trang, trường sắp xếp, và thứ tự sắp xếp.
     *
     * @param pageNo    Số trang cần trả về.
     * @param pageSize  Kích thước của trang.
     * @param sortField Tên trường sắp xếp.
     * @param orderBy   Hướng sắp xếp (ASC hoặc DESC).
     * @return BookingDetailResponse chứa danh sách chi tiết đặt xe và thông tin về phân trang.
     */
    @GetMapping("/booking-details")
    public BookingDetailResponse findAll(
            @RequestParam(value = "pageNo", defaultValue = AppConstant.BOOKING_DETAIL_DEFAULT_PAGE_NUMBER, required = false) int pageNo,
            @RequestParam(value = "pageSize", defaultValue = AppConstant.BOOKING_DETAIL_DEFAULT_PAGE_SIZE, required = false) int pageSize,
            @RequestParam(value = "sortField", defaultValue = AppConstant.BOOKING_DETAIL_DEFAULT_SORT_FIELD, required = false) String sortField,
            @RequestParam(value = "orderBy", defaultValue = AppConstant.BOOKING_DETAIL_DEFAULT_ORDER_BY, required = false) String orderBy
    ) {
        return detailService.findAll(pageNo, pageSize, sortField, orderBy);
    }

    /**
     * Lấy thông tin chi tiết đặt xe dựa trên ID.
     *
     * @param id ID của chi tiết đặt xe cần truy vấn.
     * @return ResponseEntity chứa thông tin chi tiết đặt xe và mã trạng thái HTTP.
     */
    @GetMapping("/booking-details/{id}")
    public ResponseEntity<BookingDetailDTO> getBookingDetail(@PathVariable(name = "id") Integer id) {
        return new ResponseEntity<>(detailService.getBookingDetail(id), HttpStatus.OK);
    }

    /**
     * Thêm một chi tiết đặt xe mới vào hệ thống.
     *
     * @param detailDTO Thông tin chi tiết đặt xe được gửi từ Client.
     * @return ResponseEntity chứa thông tin chi tiết đặt xe và mã trạng thái HTTP.
     */
    @PostMapping("/booking-details")
    public ResponseEntity<BookingDetailDTO> addBookingDetail(@Valid @RequestBody BookingDetailDTO detailDTO) {
        return new ResponseEntity<>(detailService.addBookingDetail(detailDTO), HttpStatus.CREATED);
    }

    /**
     * Cập nhật thông tin chi tiết đặt xe dựa trên ID.
     *
     * @param id        ID của chi tiết đặt xe cần cập nhật.
     * @param detailDTO Thông tin mới được gửi từ Client.
     * @return ResponseEntity chứa thông tin chi tiết đặt xe sau khi đã cập nhật và mã trạng thái HTTP.
     */
    @PutMapping("/booking-details/{id}")
    public ResponseEntity<BookingDetailDTO> updateBookingDetail(@PathVariable(name = "id") Integer id, @Valid @RequestBody BookingDetailDTO detailDTO) {
        return new ResponseEntity<>(detailService.updateBookingDetail(id, detailDTO), HttpStatus.OK);
    }

    /**
     * Xóa một chi tiết đặt xe dựa trên ID.
     *
     * @param id ID của chi tiết đặt xe cần xóa.
     * @return ResponseEntity chứa thông báo và mã trạng thái HTTP.
     */
    @DeleteMapping("/booking-details/{id}")
    public ResponseEntity<String> deleteBookingDetail(@PathVariable(name = "id") Integer id) {
        detailService.deleteBookingDetail(id);
        return new ResponseEntity<>("Booking Detail was deleted successfully!", HttpStatus.OK);
    }
}
