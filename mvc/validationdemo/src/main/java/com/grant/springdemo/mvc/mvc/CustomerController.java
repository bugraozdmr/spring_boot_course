package com.grant.springdemo.mvc.mvc;

import jakarta.validation.Valid;
import org.springframework.beans.propertyeditors.StringTrimmerEditor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class CustomerController {

    // Spring MVC'de String türündeki verileri otomatik olarak başındaki ve sonundaki boşluklardan
    // arındırmak için StringTrimmerEditor'ı kullanarak bir WebDataBinder'a özel bir düzenleyici kaydeder.
    @InitBinder
    public void initBinder(WebDataBinder dataBinder) {
        StringTrimmerEditor stringTrimmerEditor = new StringTrimmerEditor(true);

        dataBinder.registerCustomEditor(String.class, stringTrimmerEditor);
    }

    @GetMapping("/")
    public String showForm(Model model) {
        model.addAttribute("customer",new Customer());

        return "customer-form";
    }

    @PostMapping("/processForm")
    public String processForm(
            @Valid @ModelAttribute Customer customer,
            BindingResult bindingResult)
    {
        System.out.println("Last Name: |" + customer.getLastName() + "|");

        // burdan gelene gore kendine custom error message yazabilirsin messages.properties altında
        // typeMismatch.customer.freePasses,typeMismatch.freePasses,typeMismatch.java.lang.Integer,typeMismatch gibi gibi
        System.out.println("Binding results: |" + bindingResult.toString() + "|");

        if (bindingResult.hasErrors())
            return "customer-form";

        return "customer-confirmation";
    }
}
