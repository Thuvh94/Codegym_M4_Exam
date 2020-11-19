package controller;

import model.Customer;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import service.CustomerService;
import service.CustomerServiceImpl;

import java.util.List;

@Controller
//Annotation controller giúp Spring xác định lớp hiện tại là một controller
public class CustomerController {
    private CustomerService customerService = new CustomerServiceImpl();
    @GetMapping("/")
//    Annotation @GetMapping xác định phương thức Index sẽ đón nhận
//    các request có HTTP method là GET và URI pattern là "/"
    public String index(Model model){
//        Phương thức Index được truyền vào một tham số có kiểu dữ liệu là Model.
//        Model có nhiệm vụ là truyền dữ liệu từ Controller tới View.
        List customerList = customerService.findAll();
        model.addAttribute("customers",customerList);
        return "/index";
//        Phương thức Index sẽ trả về một String, từ String này mà Spring MVC sẽ suy ra
//        View nào sẽ nhận dữ liệu từ Controller (return "index"), vậy View sẽ nhận dữ liệu ở đây là index.html
    }

    @GetMapping("/customer/create")
    public String create (Model model){
        model.addAttribute("customer",new Customer());
        return "/create";
//Chúng ta sẽ truyền sang view create.html một model Customer có tên là customer.
// Mỗi thuộc tính của customer sẽ tương ứng với một input trong form.
    }

    @PostMapping("/customer/save")
    public String save(Customer customer, RedirectAttributes redirect) {
        customer.setId((int)(Math.random() * 10000));
        customerService.save(customer);
        redirect.addFlashAttribute("success", "Saved customer successfully!");
        return "redirect:/";
    }

    @GetMapping ("/customer/{id}/edit")
//    Tham số @PathVariable int id lấy id của customer từ đường dẫn rồi gán vào biến id.
    public String edit(@PathVariable int id, Model model){
        model.addAttribute("customer",customerService.findById(id));
        return "/edit";
    }

    @PostMapping("/customer/update")
    public String update(Customer customer, RedirectAttributes redirect) {
        customerService.update(customer.getId(), customer);
        redirect.addFlashAttribute("success", "Modified customer successfully!");
        return "redirect:/";
    }

    @GetMapping("/customer/{id}/delete")
    public String delete(@PathVariable int id, Model model) {
        model.addAttribute("customer", customerService.findById(id));
        return "/delete";
    }

    @PostMapping("/customer/delete")
    public String delete(Customer customer, RedirectAttributes redirect) {
        customerService.remove(customer.getId());
        redirect.addFlashAttribute("success", "Removed customer successfully!");
        return "redirect:/";
    }

    @GetMapping("/customer/{id}/view")
    public String view(@PathVariable int id, Model model) {
        model.addAttribute("customer", customerService.findById(id));
        return "/view";
    }


}
