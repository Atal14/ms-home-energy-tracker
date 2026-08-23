package ms_home_energy_tracker.device_service.controller;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import ms_home_energy_tracker.device_service.dto.DeviceDto;
import ms_home_energy_tracker.device_service.service.DeviceService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@Validated
@RequestMapping("api/v1/devices")
public class DeviceController {
    private final DeviceService deviceService;

    public  DeviceController(DeviceService deviceService) {
        this.deviceService = deviceService;
    }

    @GetMapping("/{deviceId}")
    public ResponseEntity<DeviceDto> getDevice(@NotNull @PathVariable("deviceId") Long deviceId) {
        final DeviceDto deviceDto = deviceService.getDeviceById(deviceId);
        return ResponseEntity.ok(deviceDto);
    }

    @GetMapping("user/{userId}")
    public ResponseEntity<List<DeviceDto>> getDevicesByUserId(@NotNull @PathVariable("userId") Long userId){
        final List<DeviceDto> userDevices = deviceService.getDeviceByUserId(userId);
        return ResponseEntity.ok(userDevices);
    }

    @PostMapping
    public ResponseEntity<DeviceDto> createDevice(@Valid @RequestBody DeviceDto deviceDto) {
        final DeviceDto createdDeviceDto = deviceService.createDevice(deviceDto);
        return new ResponseEntity<>(createdDeviceDto,HttpStatus.CREATED);
    }

    @PutMapping("/{deviceId}")
    public ResponseEntity<DeviceDto> updateDevice(@NotNull @PathVariable Long deviceId, @Valid @RequestBody DeviceDto deviceDto) {
        final DeviceDto updatedDeviceDto = deviceService.updateDevice(deviceId, deviceDto);
        return ResponseEntity.ok(updatedDeviceDto);
    }

    @DeleteMapping("/{deviceId}")
    public ResponseEntity<String> deleteDevice(@NotNull @PathVariable Long deviceId) {
        final String deleteMsg = deviceService.deleteDevice(deviceId);
        return ResponseEntity.ok(deleteMsg);
    }
}
