package src.Controller;

import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import src.Service.TrafficSignService;

import java.io.IOException;

@Controller
@CrossOrigin("*")
@RequiredArgsConstructor
public class WebController {
    private final TrafficSignService trafficSignService;


    @GetMapping({"/", "/index"})
    public String showHomePage() {
        return "index";
    }

    @GetMapping("/login")
    public String showLoginPage() {
        return "login";
    }

    @GetMapping("/register")
    public String showRegisterPage() {
        return "register";
    }

    @GetMapping("/contact")
    public String showContactPage() {
        return "contact";
    }

    @GetMapping("/vsr")
    public String showVsrPage() {
        return "vsr";
    }

    @PostMapping("/register")
    public String handleRegister() {

        System.out.println("VSR System: Đã nhận yêu cầu đăng ký tài khoản mới!");
        return "redirect:/login";
    }

    @PostMapping("/login")
    public String handleLogin() {
        System.out.println("VSR System: Đang thực hiện xác thực người dùng...");
        return "redirect:/";
    }

    @PostMapping("/contact")
    public String handleContact() {
        System.out.println("VSR System: Đã nhận thông tin liên hệ từ khách hàng!");
        return "redirect:/contact";
    }


    @PostMapping("/save")
    public String saveTrafficSign(
            @RequestParam String signName,
            @RequestParam String signCode,
            @RequestParam String description,
            @RequestParam("image") MultipartFile image
    ) throws IOException {

        trafficSignService.saveTrafficSign(signName, signCode, description, image);

        return "redirect:/addData";
    }

    @GetMapping("/addData")
    public String showForm() {
        return "addData";
    }

    @GetMapping("/testec2")
    public String testEc2(){
        return "TestEc2";
    }
}