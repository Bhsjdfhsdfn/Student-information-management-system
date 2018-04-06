package hxk.action;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/Restful")
public class IndexAction {
	@RequestMapping(value = "/")
	public String index() {
		return "main";
	}
}
