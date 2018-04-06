package hxk.action;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import hxk.model.Student;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.app.bean.bo.WherePrams;
import com.dao.StuDao;

/**
 * @author Administrator
 * @description 关于Restful的定义 GET /user/10086 查询10086用户资料。 POST /user 用户注册。 PUT
 *              /user/10086 修改10086用户资料。 DELETE /user/10086 删除10086用户 用同一个
 *              URL，不同的动词 做不同的操作
 */
@Controller
@RequestMapping("/student/")
public class StudentAction {
	@RequestMapping(value = "{id}", method = RequestMethod.GET)
	public @ResponseBody
	Student get(@PathVariable String id, HttpServletRequest request) {
		WherePrams wherePrams=new WherePrams("id", "=", id);
		return new StuDao().get(wherePrams);
	}

	@RequestMapping(method = RequestMethod.POST)
	public @ResponseBody
	boolean add(Student student, HttpServletRequest request) {
		try {
			if(student.getId() != 0)
			{
				if (new StuDao().updateLocal(student) > 0) {
					return true;
				} else {
					return false;
				}
				
			}else
			{
				if (new StuDao().addLocal(student) > 0) {
					return true;
				} else {
					return false;
				}
			}
			
		} catch (Exception e) {
			return false;
		}
	}

	@RequestMapping(value = "{id}", method = RequestMethod.DELETE)
	public @ResponseBody
	boolean delete(@PathVariable Integer id,
			HttpServletRequest request) {
		if(new  StuDao().del(id)>0)
		{
			return true;
		}
		return false;
	}

	@RequestMapping(value = "/findUser")
	public String findUser(String name) {
		System.out.println(name);
		return name;
	}

	@RequestMapping(value = "/list")
	@ResponseBody
	public List<Student> list(HttpServletRequest request) {
		WherePrams wherePrams = new WherePrams("1", "=", "1");
		if(request.getParameter("name")!=null)
		{
			wherePrams.and("name", " like ", "%"+request.getParameter("name")+"%");
		}
		return new StuDao().list(wherePrams);
	}

	@RequestMapping(value = "/login")
	public String login(String userName, String password) {
		WherePrams wherePrams = new WherePrams("name", "=", userName);
		wherePrams.and("password", "=", password);
		System.out.println(userName + password);
		Student stu = new StuDao().get(wherePrams);
		if (stu != null) {
			return "admin/main";
		} else {
			return "login";
		}

	}

}
