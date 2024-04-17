package Controllers;

import Models.Club;
import Repositories.ClubRepository;
import Utils.CloudinarySDK;
import org.apache.catalina.core.ApplicationContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@RestController
@RequestMapping("/")
public class HomeController {

    @Autowired
    private ClubRepository clubRepository;


    @PostMapping
    public void home(@RequestParam int id , @RequestParam("logo") MultipartFile logo , @RequestParam("banner") MultipartFile banner) throws IOException {
        Club c = clubRepository.findById((long) id).get();

        String logoimg = CloudinarySDK.imageUpload(logo);
        String bannerimg = CloudinarySDK.imageUpload(banner);

        c.setLogo(logoimg);
        c.setBanner(bannerimg);

        clubRepository.save(c);


    }

}
