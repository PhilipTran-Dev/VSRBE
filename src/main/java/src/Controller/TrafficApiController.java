package src.Controller;

import lombok.RequiredArgsConstructor;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.multipart.MultipartFile;
import src.Entity.TrafficSign;
import src.Service.TrafficSignService;

import java.io.IOException;
import java.util.Map;

@RestController
@CrossOrigin("*")
@RequestMapping("/api")
@RequiredArgsConstructor
public class TrafficApiController {
    private final TrafficSignService trafficSignService;
    @PostMapping("/traffic-data")
    public void receive(@RequestBody Map<String, Object> data) {
        String signId = (String) data.get("signId");
        trafficSignService.handle(signId);
    }

    @GetMapping("/current-sign")
    public ResponseEntity<?> current() {
        TrafficSign sign = trafficSignService.getLatest();

        if (sign == null) {
            return ResponseEntity.ok(Map.of());
        }

        return ResponseEntity.ok(sign);
    }
    @PostMapping("/detect")
    public ResponseEntity<String> proxyDetect(
            @RequestParam("frame") MultipartFile frame) throws IOException {

        // Forward frame sang Python Flask
        RestTemplate restTemplate = new RestTemplate();

        MultiValueMap<String, Object> body = new LinkedMultiValueMap<>();
        body.add("frame", new ByteArrayResource(frame.getBytes()) {
            @Override
            public String getFilename() { return "frame.jpg"; }
        });

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.MULTIPART_FORM_DATA);

        HttpEntity<MultiValueMap<String, Object>> requestEntity = new HttpEntity<>(body, headers);

        ResponseEntity<String> response = restTemplate.postForEntity(
                "http://localhost:5000/detect",
                requestEntity,
                String.class
        );

        return ResponseEntity.ok()
                .header("Content-Type", "application/json")
                .body(response.getBody());
    }
    @GetMapping("/sounds/{filename}")
    public ResponseEntity<byte[]> proxySound(
            @PathVariable String filename) throws IOException {

        RestTemplate restTemplate = new RestTemplate();
        byte[] sound = restTemplate.getForObject(
                "http://localhost:5000/sounds/" + filename,
                byte[].class
        );

        return ResponseEntity.ok()
                .header("Content-Type", "audio/mpeg")
                .body(sound);
    }
}