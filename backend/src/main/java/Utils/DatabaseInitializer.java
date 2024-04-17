package Utils;

import Dto.RegisterUser;
import Models.Club;
import Models.Field;
import Models.User;
import Repositories.ClubRepository;
import Repositories.FieldRepository;
import Repositories.UserRepository;
import Services.AuthService;
import Services.ClubService;
import Services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

@Component
public class DatabaseInitializer implements CommandLineRunner {


    @Autowired
    private FieldRepository fieldRepository;

    @Autowired
    private AuthService authService;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ClubRepository clubRepository;



    @Override
    public void run(String... args) throws Exception {
        if(userRepository.count() > 0){
            return;
        }
        this.userAndFieldsSetup();
        this.clubSetup();

    }


    public void userAndFieldsSetup(){
        Field field1 = new Field();
        field1.setName("Informatique");
        Field field2 = new Field();
        field2.setName("GSIL");
        Field field3 = new Field();
        field3.setName("Mécatronique");
        Field field4 = new Field();
        field4.setName("Infotronique");

        fieldRepository.save(field1);
        fieldRepository.save(field2);
        fieldRepository.save(field3);
        fieldRepository.save(field4);


        BCryptPasswordEncoder bcyptPasswordEncoder = new BCryptPasswordEncoder();

        User user = new User();
        user.setFirstName("admin");
        user.setLastName("admin");
        user.setEmail("projet@enicar.ucar.tn");
        user.setPassword(bcyptPasswordEncoder.encode("admin"));
        user.setRole("ADMIN");
        user.setVerified(true);
        user.setField(field1);

        userRepository.save(user);

    }

    public void clubSetup(){
        /*
        1 Enic'ulture
        2 IEEE ENICarthage
        3 Club Mécatronique ENICarthage
        4 Mecatronic Engineering Discovery
        5 MTC: Microsoft Tech Club
        6 Google Developer Student Club
        7 Competitive Programming
        8 Génie Industriel ENICarthage
        9 Club Tunivision ENICarthage
        10 Nateg ENICarthage Student Chapter
        11 Espoir
        12 Melkart
        13 Enactus
        14 Joker Info


        //all logos in order
        'http://res.cloudinary.com/dyjbsqfml/image/upload/v1712831388/file_uaox8z.jpg'
'http://res.cloudinary.com/dyjbsqfml/image/upload/v1712831878/file_z1fyfx.png'
'http://res.cloudinary.com/dyjbsqfml/image/upload/v1712832035/file_fpzbhs.jpg'
'http://res.cloudinary.com/dyjbsqfml/image/upload/v1712832145/file_zkxzh6.jpg'
'http://res.cloudinary.com/dyjbsqfml/image/upload/v1712832246/file_oblk2j.jpg'
'http://res.cloudinary.com/dyjbsqfml/image/upload/v1712832341/file_fipmih.png'
'http://res.cloudinary.com/dyjbsqfml/image/upload/v1712832399/file_ush26a.jpg'
'http://res.cloudinary.com/dyjbsqfml/image/upload/v1712832505/file_jfzlal.jpg'
'http://res.cloudinary.com/dyjbsqfml/image/upload/v1712832590/file_so1xio.png'
 'http://res.cloudinary.com/dyjbsqfml/image/upload/v1712832653/file_wiabmf.jpg'
 'http://res.cloudinary.com/dyjbsqfml/image/upload/v1712832705/file_n3nyld.jpg'
 'http://res.cloudinary.com/dyjbsqfml/image/upload/v1712832767/file_xzuauq.jpg'
 'http://res.cloudinary.com/dyjbsqfml/image/upload/v1712832851/file_dsket7.jpg'
 'http://res.cloudinary.com/dyjbsqfml/image/upload/v1712832919/file_enr0iz.png'


 //all banners in order
 'http://res.cloudinary.com/dyjbsqfml/image/upload/v1712831386/file_lncqao.png'
'http://res.cloudinary.com/dyjbsqfml/image/upload/v1712831873/file_mdoqvh.jpg'
'http://res.cloudinary.com/dyjbsqfml/image/upload/v1712832030/file_smygjg.jpg'
'http://res.cloudinary.com/dyjbsqfml/image/upload/v1712832141/file_bywkbq.jpg'
'http://res.cloudinary.com/dyjbsqfml/image/upload/v1712832243/file_bk8z2d.jpg'
'http://res.cloudinary.com/dyjbsqfml/image/upload/v1712832337/file_kysvg5.jpg'
'http://res.cloudinary.com/dyjbsqfml/image/upload/v1712832395/file_klzatm.png'
'http://res.cloudinary.com/dyjbsqfml/image/upload/v1712832504/file_ffhvgf.png'
'http://res.cloudinary.com/dyjbsqfml/image/upload/v1712832586/file_wfkhpp.png'
'http://res.cloudinary.com/dyjbsqfml/image/upload/v1712832651/file_qpaeec.png'
'http://res.cloudinary.com/dyjbsqfml/image/upload/v1712832702/file_rs31hs.jpg'
'http://res.cloudinary.com/dyjbsqfml/image/upload/v1712832765/file_mnln4s.jpg'
'http://res.cloudinary.com/dyjbsqfml/image/upload/v1712832850/file_cqbs7j.jpg'
'http://res.cloudinary.com/dyjbsqfml/image/upload/v1712832916/file_iik3kv.jpg'






         */

        Club eniculture = new Club();
        eniculture.setName("Enic'ulture");
        eniculture.setDescription("Enic'ulture est un club culturel qui a pour but de promouvoir la culture et les arts au sein de l'école.");
        eniculture.setBanner("http://res.cloudinary.com/dyjbsqfml/image/upload/v1712831388/file_uaox8z.jpg");
        eniculture.setLogo("http://res.cloudinary.com/dyjbsqfml/image/upload/v1712831386/file_lncqao.png");
        eniculture.setCreationYear(2018);
        eniculture.setResponsible(userRepository.findById((long) 1).get());

        Club ieee = new Club();
        ieee.setName("IEEE ENICarthage");
        ieee.setDescription("IEEE ENICarthage est un club scientifique qui a pour but de promouvoir la science et la technologie au sein de l'école.");
        ieee.setBanner("http://res.cloudinary.com/dyjbsqfml/image/upload/v1712831878/file_z1fyfx.png");
        ieee.setLogo("http://res.cloudinary.com/dyjbsqfml/image/upload/v1712831873/file_mdoqvh.jpg");
        ieee.setCreationYear(2018);
        ieee.setResponsible(userRepository.findById((long) 1).get());

        Club mecatronique = new Club();
        mecatronique.setName("Club Mécatronique ENICarthage");
        mecatronique.setDescription("Club Mécatronique ENICarthage est un club scientifique qui a pour but de promouvoir la mécatronique au sein de l'école.");
        mecatronique.setCreationYear(2018);
        mecatronique.setBanner("http://res.cloudinary.com/dyjbsqfml/image/upload/v1712832035/file_fpzbhs.jpg");
        mecatronique.setLogo("http://res.cloudinary.com/dyjbsqfml/image/upload/v1712832030/file_smygjg.jpg");
        mecatronique.setResponsible(userRepository.findById((long) 1).get());

        Club med = new Club();
        med.setName("Mecatronic Engineering Discovery");
        med.setDescription("Mecatronic Engineering Discovery est un club scientifique qui a pour but de promouvoir la mécatronique au sein de l'école.");
        med.setCreationYear(2018);
        med.setBanner("http://res.cloudinary.com/dyjbsqfml/image/upload/v1712832145/file_zkxzh6.jpg");
        med.setLogo("http://res.cloudinary.com/dyjbsqfml/image/upload/v1712832141/file_bywkbq.jpg");
        med.setResponsible(userRepository.findById((long) 1).get());

        Club mtc = new Club();
        mtc.setName("MTC: Microsoft Tech Club");
        mtc.setDescription("MTC: Microsoft Tech Club est un club scientifique qui a pour but de promouvoir la technologie Microsoft au sein de l'école.");
        mtc.setCreationYear(2018);
        mtc.setBanner("http://res.cloudinary.com/dyjbsqfml/image/upload/v1712832246/file_oblk2j.jpg");
        mtc.setLogo("http://res.cloudinary.com/dyjbsqfml/image/upload/v1712832243/file_bk8z2d.jpg");
        mtc.setResponsible(userRepository.findById((long) 1).get());

        Club gds = new Club();
        gds.setName("Google Developer Student Club");
        gds.setDescription("Google Developer Student Club est un club scientifique qui a pour but de promouvoir la technologie Google au sein de l'école.");
        gds.setCreationYear(2018);
        gds.setBanner("http://res.cloudinary.com/dyjbsqfml/image/upload/v1712832341/file_fipmih.png");
        gds.setLogo("http://res.cloudinary.com/dyjbsqfml/image/upload/v1712832337/file_kysvg5.jpg");
        gds.setResponsible(userRepository.findById((long) 1).get());

        Club cp = new Club();
        cp.setName("Competitive Programming");
        cp.setDescription("Competitive Programming est un club scientifique qui a pour but de promouvoir la programmation compétitive au sein de l'école.");
        cp.setCreationYear(2018);
        cp.setBanner("http://res.cloudinary.com/dyjbsqfml/image/upload/v1712832399/file_ush26a.jpg");
        cp.setLogo("http://res.cloudinary.com/dyjbsqfml/image/upload/v1712832395/file_klzatm.png");
        cp.setResponsible(userRepository.findById((long) 1).get());

        Club gi = new Club();
        gi.setName("Génie Industriel ENICarthage");
        gi.setDescription("Génie Industriel ENICarthage est un club scientifique qui a pour but de promouvoir le génie industriel au sein de l'école.");
        gi.setCreationYear(2018);
        gi.setBanner("http://res.cloudinary.com/dyjbsqfml/image/upload/v1712832505/file_jfzlal.jpg");
        gi.setLogo("http://res.cloudinary.com/dyjbsqfml/image/upload/v1712832504/file_ffhvgf.png");
        gi.setResponsible(userRepository.findById((long) 1).get());

        Club tunivision = new Club();
        tunivision.setName("Club Tunivision ENICarthage");
        tunivision.setDescription("Club Tunivision ENICarthage est un club culturel qui a pour but de promouvoir la culture et les arts au sein de l'école.");
        tunivision.setCreationYear(2018);
        tunivision.setBanner("http://res.cloudinary.com/dyjbsqfml/image/upload/v1712832590/file_so1xio.png");
        tunivision.setLogo("http://res.cloudinary.com/dyjbsqfml/image/upload/v1712832586/file_wfkhpp.png");
        tunivision.setResponsible(userRepository.findById((long) 1).get());

        Club nateg = new Club();
        nateg.setName("Nateg ENICarthage Student Chapter");
        nateg.setDescription("Nateg ENICarthage Student Chapter est un club culturel qui a pour but de promouvoir la culture et les arts au sein de l'école.");
        nateg.setCreationYear(2018);
        nateg.setBanner("http://res.cloudinary.com/dyjbsqfml/image/upload/v1712832653/file_wiabmf.jpg");
        nateg.setLogo("http://res.cloudinary.com/dyjbsqfml/image/upload/v1712832651/file_qpaeec.png");
        nateg.setResponsible(userRepository.findById((long) 1).get());

        Club espoir = new Club();
        espoir.setName("Espoir");
        espoir.setDescription("Espoir est un club culturel qui a pour but de promouvoir la culture et les arts au sein de l'école.");
        espoir.setCreationYear(2018);
        espoir.setBanner("http://res.cloudinary.com/dyjbsqfml/image/upload/v1712832705/file_n3nyld.jpg");
        espoir.setLogo("http://res.cloudinary.com/dyjbsqfml/image/upload/v1712832702/file_rs31hs.jpg");
        espoir.setResponsible(userRepository.findById((long) 1).get());

        Club melkart = new Club();
        melkart.setName("Melkart");
        melkart.setDescription("Melkart est un club culturel qui a pour but de promouvoir la culture et les arts au sein de l'école.");
        melkart.setCreationYear(2018);
        melkart.setBanner("http://res.cloudinary.com/dyjbsqfml/image/upload/v1712832767/file_xzuauq.jpg");
        melkart.setLogo("http://res.cloudinary.com/dyjbsqfml/image/upload/v1712832765/file_mnln4s.jpg");
        melkart.setResponsible(userRepository.findById((long) 1).get());

        Club enactus = new Club();
        enactus.setName("Enactus");
        enactus.setDescription("Enactus est un club culturel qui a pour but de promouvoir la culture et les arts au sein de l'école.");
        enactus.setCreationYear(2018);
        enactus.setBanner("http://res.cloudinary.com/dyjbsqfml/image/upload/v1712832851/file_dsket7.jpg");
        enactus.setLogo("http://res.cloudinary.com/dyjbsqfml/image/upload/v1712832850/file_cqbs7j.jpg");
        enactus.setResponsible(userRepository.findById((long) 1).get());

        Club joker = new Club();
        joker.setName("Joker Info");
        joker.setDescription("Joker Info est un club culturel qui a pour but de promouvoir la culture et les arts au sein de l'école.");
        joker.setCreationYear(2018);
        joker.setBanner("http://res.cloudinary.com/dyjbsqfml/image/upload/v1712832919/file_enr0iz.png");
        joker.setLogo("http://res.cloudinary.com/dyjbsqfml/image/upload/v1712832916/file_iik3kv.jpg");
        joker.setResponsible(userRepository.findById((long) 1).get());

        clubRepository.save(eniculture);
        clubRepository.save(ieee);
        clubRepository.save(mecatronique);
        clubRepository.save(med);
        clubRepository.save(mtc);
        clubRepository.save(gds);
        clubRepository.save(cp);
        clubRepository.save(gi);
        clubRepository.save(tunivision);
        clubRepository.save(nateg);
        clubRepository.save(espoir);
        clubRepository.save(melkart);
        clubRepository.save(enactus);
        clubRepository.save(joker);


    }


    private void DefaultTags(){
        //tags : Event , Annoucement , Discussion
        //Poll
        //Question
        //Recommendation
        //Tutorial
        //Memes
        //News
        //Inspirational
        //Personal
        //Review
        //Art
        //Music
        //Travel
        //Technology

    }

}
