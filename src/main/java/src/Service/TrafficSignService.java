package src.Service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import src.Entity.TrafficSign;
import src.Repos.TrafficSignRepos;

import java.io.File;
import java.io.IOException;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class TrafficSignService {

    private final TrafficSignRepos trafficSignRepos;
    private final FileUpload fileUpload;
    private TrafficSign latestSign;


    public void saveTrafficSign(String signName,
                                String signCode,
                                String description,
                                MultipartFile image) throws IOException {

        String imageURL = fileUpload.uploadFile(image);
        TrafficSign trafficSign = new TrafficSign();
        trafficSign.setSignName(signName);
        trafficSign.setSignCode(signCode);
        trafficSign.setDescription(description);
        trafficSign.setImage(imageURL);
        trafficSignRepos.save(trafficSign);
    }

    public void handle(String signId) {
        latestSign = trafficSignRepos.findBySignCode(signId);}

    public TrafficSign getLatest() {
        TrafficSign temp = latestSign;
        latestSign = null;
        return temp;
    }
}