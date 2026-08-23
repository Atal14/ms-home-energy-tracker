package ms_home_energy_tracker.device_service.service;

import org.springframework.transaction.annotation.Transactional;
import ms_home_energy_tracker.device_service.dto.DeviceDto;
import ms_home_energy_tracker.device_service.entity.Device;
import ms_home_energy_tracker.device_service.exception.DeviceNotFoundException;
import ms_home_energy_tracker.device_service.repository.DeviceRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DeviceService {

    private final DeviceRepository deviceRepository;

    public DeviceService(DeviceRepository deviceRepository) {
        this.deviceRepository = deviceRepository;
    }

    @Transactional(readOnly = true)
    public DeviceDto getDeviceById(Long deviceId) {
        Device device = findDeviceByIdOrThrowError(deviceId);
        return getDeviceDto(device);
    }

    @Transactional(readOnly = true)
    public List<DeviceDto> getDeviceByUserId(Long userId) {
        return deviceRepository.findAllByUserId(userId)
                .stream()
                .map(this::getDeviceDto)
                .toList();
    }

    @Transactional
    public DeviceDto createDevice (DeviceDto deviceDto) {
        Device device = new Device();
        BeanUtils.copyProperties(deviceDto, device);
        Device savedDevice =  deviceRepository.save(device);
        return getDeviceDto(savedDevice);
    }

    @Transactional
    public DeviceDto updateDevice (Long deviceId, DeviceDto deviceDto) {
        Device toBeUpdatedDevice = findDeviceByIdOrThrowError(deviceId);
        BeanUtils.copyProperties(deviceDto, toBeUpdatedDevice);
        deviceRepository.save(toBeUpdatedDevice);
        return getDeviceDto(toBeUpdatedDevice);
    }

    @Transactional
    public String deleteDevice (Long deviceId) {
        findDeviceByIdOrThrowError(deviceId);
        deviceRepository.deleteById(deviceId);
        return "Device with id: " + deviceId + " has been deleted.";
    }

    private Device findDeviceByIdOrThrowError(Long deviceId) {
        return deviceRepository.findById(deviceId)
                .orElseThrow(() -> new DeviceNotFoundException("Device with id: " + deviceId + " not found."));
    }

    private DeviceDto getDeviceDto(Device device) {
        final DeviceDto deviceDto = new DeviceDto();
        BeanUtils.copyProperties(device, deviceDto);
        return deviceDto;
    }
}
