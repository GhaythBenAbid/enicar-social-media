package Utils;

import com.cloudinary.*;
import com.cloudinary.utils.ObjectUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Map;

public class CloudinarySDK {


    @Value("${cloudinary.cloud_name}")
    private static String cloudName;

    @Value("${cloudinary.api_key}")
    private static String apiKey;

    @Value("${cloudinary.api_secret}")
    private static String apiSecret;




    public static String imageUpload(MultipartFile file) throws IOException {
        Cloudinary cloudinary = new Cloudinary("cloudinary://322175328725812:41RYE2R5_2KZSQ4tJhgwIZsXzYA@dyjbsqfml");
        Map<String, Object> params = ObjectUtils.asMap(
                "use_filename", true,
                "unique_filename", true,
                "overwrite", true
        );

        // Upload file to Cloudinary
        Map<?, ?> uploadResult = cloudinary.uploader().upload(file.getBytes(), params);

        // Extract URL of the uploaded image
        String imageUrl = (String) uploadResult.get("url");

        return imageUrl;

    }
}
