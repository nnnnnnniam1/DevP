package com.devP.Controller;

import com.devP.Service.WbsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.SessionAttributes;

import java.security.PrivateKey;

@Controller
@SessionAttributes("wbs")
@RequestMapping("wbs")
public class WBSController {

    @Autowired
    private WbsService wbsService;

    @RequestMapping(value = "/view.do", method = RequestMethod.GET)
    public String getWbsView(){
        if(wbsService.getWbsView()==200) return "wbs";
        else if(wbsService.getWbsView()==405) return "redirect:/";
        else return null;
    }
}
