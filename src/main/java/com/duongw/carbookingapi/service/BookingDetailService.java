package com.duongw.carbookingapi.service;

import com.duongw.carbookingapi.DTO.BookingDetailDTO;
import com.duongw.carbookingapi.DTO.BookingDetailResponse;
import com.duongw.carbookingapi.entity.BookingDetail;
import com.duongw.carbookingapi.exception.ResourceNotFoundException;
import com.duongw.carbookingapi.repository.BookingDetailRepository;
import com.duongw.carbookingapi.repository.DriverRepository;
import com.duongw.carbookingapi.repository.TripRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.modelmapper.ModelMapper;

import java.util.List;
import java.util.stream.Collectors;

/**
 * BookingDetailService là một service layer thực hiện logic kinh doanh liên quan đến chi tiết đặt xe.
 * Nó chịu trách nhiệm gọi và xử lý các thao tác từ Controller và giao tiếp với repository để truy vấn và lưu trữ dữ liệu.
 */
@Service
public class BookingDetailService {

    private BookingDetailRepository bookingDetailRepository;
    private ModelMapper modelMapper;
    private TripRepository tripRepository;

    @Autowired
    public BookingDetailService(BookingDetailRepository bookingDetailRepository, ModelMapper modelMapper, TripRepository tripRepository) {
        this.bookingDetailRepository = bookingDetailRepository;
        this.tripRepository = tripRepository;
        this.modelMapper = modelMapper;
    }

    /**
     * Thêm một chi tiết đặt xe mới vào hệ thống.
     *
     * @param detailDTO Thông tin chi tiết đặt xe được gửi từ Controller.
     * @return BookingDetailDTO được trả về sau khi lưu vào cơ sở dữ liệu.
     */
    public BookingDetailDTO addBookingDetail(BookingDetailDTO detailDTO) {
        BookingDetail bookingDetail = modelMapper.map(detailDTO, BookingDetail.class);
        BookingDetail saveBookingDetail = bookingDetailRepository.save(bookingDetail);
        return modelMapper.map(saveBookingDetail, BookingDetailDTO.class);
    }

    /**
     * Tìm kiếm và trả về danh sách chi tiết đặt xe dựa trên trang, kích thước trang, trường sắp xếp và thứ tự sắp xếp.
     *
     * @param pageNo    Số trang cần trả về.
     * @param pageSize  Kích thước của trang.
     * @param sortField Tên trường sắp xếp.
     * @param orderBy   Hướng sắp xếp (ASC hoặc DESC).
     * @return BookingDetailResponse chứa danh sách chi tiết đặt xe và thông tin về phân trang.
     */
    public BookingDetailResponse findAll(int pageNo, int pageSize, String sortField, String orderBy) {
        Sort sort = orderBy.equalsIgnoreCase(Sort.Direction.ASC.name()) ? Sort.by(sortField).ascending() : Sort.by(sortField).descending();
        Pageable pageable = PageRequest.of(pageNo, pageSize, sort);
        Page<BookingDetail> detailPage = bookingDetailRepository.findAll(pageable);
        List<BookingDetail> bookingDetailList = detailPage.getContent();
        List<BookingDetailDTO> content = bookingDetailList.stream().map(detail -> modelMapper.map(detail, BookingDetailDTO.class)).collect(Collectors.toList());
        BookingDetailResponse response = new BookingDetailResponse();
        response.setContent(content);
        response.setPageNo(pageNo);
        response.setPageSize(pageSize);
        response.setTotalElement(detailPage.getTotalElements());
        response.setTotalPage(detailPage.getTotalPages());
        response.setLast(detailPage.isLast());
        response.setSortField(sortField);
        response.setOrderBy(orderBy);
        String reverseOrderBy = orderBy.equalsIgnoreCase(Sort.Direction.ASC.name()) ? "desc" : "asc";
        response.setReversedOrderBy(reverseOrderBy);
        return response;
    }

    /**
     * Cập nhật thông tin chi tiết đặt xe dựa trên ID.
     *
     * @param id        ID của chi tiết đặt xe cần cập nhật.
     * @param detailDTO Thông tin mới được gửi từ Controller.
     * @return BookingDetailDTO sau khi đã cập nhật.
     */
    public BookingDetailDTO updateBookingDetail(Integer id, BookingDetailDTO detailDTO) {
        BookingDetail detail = bookingDetailRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Booking Detail", "id", String.valueOf(id)));
        detail.setFirstName(detailDTO.getFirstName());
        detail.setLastName(detailDTO.getLastName());
        detail.setDropDestination(detailDTO.getDropDestination());
        detail.setPhone(detailDTO.getPhone());
        detail.setPickDestination(detailDTO.getPickDestination());
        detail.setSheet(detailDTO.getSheet());
        detail.setStatus(detailDTO.isStatus());
        detail.setTrip(tripRepository.findById(detailDTO.getTripId()).orElseThrow(() -> new ResourceNotFoundException("Trip", "id", String.valueOf(detailDTO.getTripId()))));
        detail.setDateBooking((detailDTO.getDateBooking()));
        BookingDetail save = bookingDetailRepository.save(detail);
        return modelMapper.map(save, BookingDetailDTO.class);
    }

    /**
     * Lấy thông tin chi tiết đặt xe dựa trên ID.
     *
     * @param id ID của chi tiết đặt xe cần truy vấn.
     * @return BookingDetailDTO chứa thông tin chi tiết đặt xe.
     */
    public BookingDetailDTO getBookingDetail(Integer id) {
        BookingDetail detail = bookingDetailRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Booking Detail", "id", String.valueOf(id)));
        return modelMapper.map(detail, BookingDetailDTO.class);
    }

    /**
     * Xóa một chi tiết đặt xe dựa trên ID.
     *
     * @param id ID của chi tiết đặt xe cần xóa.
     */
    public void deleteBookingDetail(Integer id) {
        BookingDetail detail = bookingDetailRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Booking Detail", "id", String.valueOf(id)));
        bookingDetailRepository.delete(detail);
    }
}
