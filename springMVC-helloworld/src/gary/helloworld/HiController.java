package gary.helloworld;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.ui.Model;

@Controller
@RequestMapping("/hi")
public class HiController {

    @RequestMapping("/say")
    public String say(Model model) {
        model.addAttribute("name","wormday"); // 指定Model的值
        model.addAttribute("url","http://www.cnblogs.com/wormday/p/8435617.html"); // 指定Model的值
        return "say";
    }
}