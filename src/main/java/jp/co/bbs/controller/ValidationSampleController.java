package jp.co.bbs.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import jp.co.bbs.dto.employee.EmployeeDto;
import jp.co.bbs.form.employee.EmployeeListForm;
@Controller
public class ValidationSampleController {
	private List<EmployeeDto> employeeList = new ArrayList<EmployeeDto>();

    @RequestMapping(value = "/employee/list", method = RequestMethod.GET)
    public String list(Model model) {
        model.addAttribute("title", "社員一覧");
        model.addAttribute("message", "登録社員一覧情報を表示します");
        EmployeeListForm form = new EmployeeListForm();
        model.addAttribute("employeeListForm", form);
        model.addAttribute("employeeList", employeeList);
        return "list";
    }

    @RequestMapping(value = "/employee/list", method = RequestMethod.POST)
    public String list(@ModelAttribute EmployeeListForm form, Model model, BindingResult result) {
        EmployeeDto dto = new EmployeeDto();
        BeanUtils.copyProperties(form, dto);
        employeeList.add(dto);
        model.addAttribute("title", "社員一覧");
        model.addAttribute("message", form.getName() + "を登録しました。");
        model.addAttribute("employeeListForm", new EmployeeListForm());
        model.addAttribute("employeeList", employeeList);
        return "list";
    }
}
