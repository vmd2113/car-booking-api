package com.duongw.carbookingapi.service;

import com.duongw.carbookingapi.DTO.TripDTO;
import com.duongw.carbookingapi.entity.Trip;
import com.duongw.carbookingapi.exception.ResourceNotFoundException;
import com.duongw.carbookingapi.repository.DriverRepository;
import com.duongw.carbookingapi.repository.TripRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

/**
 * TripService là một service layer thực hiện logic kinh doanh liên quan đến chuyến đi.
 * Nó chịu trách nhiệm gọi và xử lý các thao tác từ Controller và giao tiếp với repository để truy vấn và lưu trữ dữ liệu.
 */
@Service
public class TripService {

    private TripRepository tripRepository;
    private DriverRepository driverRepository;
    private ModelMapper modelMapper;

    @Autowired
    public TripService(TripRepository tripRepository, DriverRepository driverRepository, ModelMapper modelMapper) {
        this.driverRepository = driverRepository;
        this.tripRepository = tripRepository;
        this.modelMapper = modelMapper;
    }

    /**
     * Lấy danh sách tất cả chuyến đi có sẵn.
     *
     * @return Danh sách TripDTO chứa thông tin chi tiết về mỗi chuyến đi.
     */
    public List<TripDTO> findAll() {
        List<Trip> trips = tripRepository.findAll();
        return trips.stream().map(trip -> modelMapper.map(trip, TripDTO.class)).collect(Collectors.toList());
    }

    /**
     * Thêm một chuyến đi mới vào hệ thống.
     *
     * @param tripDTO Thông tin chi tiết của chuyến đi được gửi từ Controller.
     * @return TripDTO được trả về sau khi lưu vào cơ sở dữ liệu.
     */
    public TripDTO addTrip(TripDTO tripDTO) {
        Trip trip = modelMapper.map(tripDTO, Trip.class);
        return modelMapper.map(tripRepository.save(trip), TripDTO.class);
    }

    /**
     * Lấy danh sách tất cả chuyến đi của một tài xế dựa trên ID tài xế.
     *
     * @param driverId ID của tài xế.
     * @return Danh sách TripDTO chứa thông tin chi tiết về mỗi chuyến đi của tài xế.
     */
    public List<TripDTO> findAllTripsByDriverId(Integer driverId) {
        List<Trip> trips = tripRepository.findAllByDriverId(driverId);
        return trips.stream().map(trip -> modelMapper.map(trip, TripDTO.class)).collect(Collectors.toList());
    }

    /**
     * Lấy thông tin chi tiết của một chuyến đi dựa trên ID.
     *
     * @param id ID của chuyến đi cần truy vấn.
     * @return TripDTO chứa thông tin chi tiết về chuyến đi.
     */
    public TripDTO getTrip(Integer id) {
        Trip trip = tripRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Trip", "id", String.valueOf(id)));
        return modelMapper.map(trip, TripDTO.class);
    }

    /**
     * Cập nhật thông tin của một chuyến đi dựa trên ID.
     *
     * @param id      ID của chuyến đi cần cập nhật.
     * @param tripDTO Thông tin mới được gửi từ Controller.
     * @return TripDTO sau khi đã cập nhật.
     */
    public TripDTO updateTrip(Integer id, TripDTO tripDTO) {
        Trip trip = tripRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Trip", "id", String.valueOf(id)));
        trip.setDriver(driverRepository.findById(tripDTO.getDriverId()).orElseThrow(() -> new ResourceNotFoundException("Driver", "id", String.valueOf(tripDTO.getDriverId()))));
        trip.setStartTime(tripDTO.getStartTime());
        trip.setEndTime(tripDTO.getEndTime());
        Trip saveTrip = tripRepository.save(trip);
        return modelMapper.map(saveTrip, TripDTO.class);
    }

    /**
     * Xóa một chuyến đi dựa trên ID.
     *
     * @param id ID của chuyến đi cần xóa.
     */
    public void deleteTrip(Integer id) {
        Trip trip = tripRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Trip", "id", String.valueOf(id)));
        tripRepository.delete(trip);
    }
}
