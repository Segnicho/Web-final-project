package com.miesitu.web_project.controller;

import com.miesitu.web_project.Repository.CodeRepository;

import com.miesitu.web_project.entity.Code;
import com.miesitu.web_project.form.SignUpForm;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;


// import com.miesitu.web_project.services.VarifyCodeService;
import com.miesitu.web_project.services.ValidateSaveService;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.servlet.support.RequestContextUtils;

import org.springframework.beans.factory.annotation.Autowired;

@Controller
@RequestMapping("/signup")
public class SignUpController{

    // private VarifyCodeService varifyCode;
    @Autowired
    private ValidateSaveService valsave;

    @Autowired
    private CodeRepository codeRepo;

    @GetMapping
    public String signup(HttpServletRequest request, Model model, SignUpForm form, RedirectAttributes reatr) {
        try {
            Map<String, ?> inputFlash = RequestContextUtils.getInputFlashMap(request);
            if(inputFlash.get("form") != null){
                model.addAttribute("form", inputFlash.get("form"));//er
            }else{
                model.addAttribute("form", form);
            }
        } catch (NullPointerException e) {
            model.addAttribute("form", form);
        }
        return "signup";
    
    }

    @PostMapping //@ModelAttribute("User") User user
    public String processRegistration(@Valid SignUpForm form, BindingResult bindingResult, Model model, RedirectAttributes errmsg ) {
        errmsg.addFlashAttribute("form", form);
        if (bindingResult.hasErrors()) {   
            errmsg.addFlashAttribute("er", bindingResult.getFieldError().getDefaultMessage());
            return "redirect:signup?error";
            
        }else {
            // System.out.println(form);
            if(!valsave.passwordMacher(form.getPassword(), form.getConfirm_Password())){
                errmsg.addFlashAttribute("er", "Password Does Not Match");
                return "redirect:/signup?error";
            }
            if(valsave.userEmailExists(form.getEmail())){
                errmsg.addFlashAttribute("er", "Email Already exists");
                return "redirect:/signup?error";
            }
            if(valsave.userPhoneExists(form.getPhone())){
                errmsg.addFlashAttribute("er", "Phone number Already Exists");
                return "redirect:/signup?error";
            }
            if(valsave.doSave(form)){
                errmsg.addFlashAttribute("message", "Signup Successfull. Please Log In");
                return "redirect:login?signupsuccess";
            }else{
                long codeNum = form.getCode();
                Code code = codeRepo.findByCode(codeNum);
                if ( code != null && !code.isStatus()){
                    errmsg.addFlashAttribute("er", "Error, The code is already used by another user");
                    return "redirect:/signup?error";
                }else if(code != null){
                    errmsg.addFlashAttribute("er", "Error, code not found, please contact the admin to get one");
                    return "redirect:/signup?error";
                }
            }
            errmsg.addFlashAttribute("er", "Error, Please try again");
            return "redirect:/signup?error";
        }
    }

}
