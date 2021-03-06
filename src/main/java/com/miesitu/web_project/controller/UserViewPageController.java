package com.miesitu.web_project.controller;


import java.util.List;

import com.miesitu.web_project.entity.Anouncement;
import com.miesitu.web_project.services.AnouncementService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
public class UserViewPageController {

    @Autowired
    AnouncementService anouncementRepository;

    @GetMapping("/distr/anouncement")
    public String distAnounce(Model model){
        return userViewAnouncement(model);
    }
    @GetMapping("/distr/anouncement/{pageNo}")
    public String distAnouncePage(@PathVariable(value="pageNo") int pageNo, Model model){
        return findPagenated(pageNo, model);
    }

    // @GetMapping("/ViewAnouncement")
    public String userViewAnouncement(Model model){
        return findPagenated(1, model);
    }
    public String userViewAnouncement(int pageNo, Model model){
        return findPagenated(pageNo, model);
    }

    // @GetMapping("/ViewAnouncement/{pageNo}")
    public String  findPagenated(int pageNo, Model model ) {
        int pageSize = 5;
        Page<Anouncement> page = anouncementRepository.findAnouncementPaginated(pageNo,pageSize);
        List<Anouncement> anouncementlist = page.getContent();
        model.addAttribute("currentPage", pageNo);
        model.addAttribute("totalPages", page.getTotalPages());
        model.addAttribute("totalItems", page.getTotalElements());
        model.addAttribute("anouncementlist", anouncementlist);
        return "userViewAnouncement";
    }
    
}
