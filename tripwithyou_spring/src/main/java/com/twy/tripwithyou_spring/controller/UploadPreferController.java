package com.twy.tripwithyou_spring.controller;

import com.twy.tripwithyou_spring.dto.AjaxStateHandler;
import com.twy.tripwithyou_spring.dto.UploadPreferDto;
import com.twy.tripwithyou_spring.dto.UploadPreferViewDto;
import com.twy.tripwithyou_spring.dto.UserDto;
import com.twy.tripwithyou_spring.service.UploadPreferService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/upload/prefer")
public class UploadPreferController {
    private UploadPreferService uploadPreferService;

    public UploadPreferController(UploadPreferService uploadPreferService) {
        this.uploadPreferService = uploadPreferService;
    }

    @GetMapping("view.do")
    public String view(
            int uploadNo,
            Model model,
            @SessionAttribute UserDto loginInfo
    ) {
        String loginUserId = (loginInfo != null) ? loginInfo.getUserId() : null;
        UploadPreferViewDto uploadPreferView = uploadPreferService.detailUploadPreferView(uploadNo, loginUserId);
        model.addAttribute("prefer", uploadPreferView);
        return "/upload/uploadPrefer";
    }

    @RequestMapping(path = "/handler.do", method = {RequestMethod.PUT})
    public @ResponseBody AjaxStateHandler handler(int uploadNo,
                                                  boolean preferBtn,
                                                  @SessionAttribute UserDto loginInfo) {
        AjaxStateHandler ajaxStateHandler = new AjaxStateHandler();
        UploadPreferDto userLoginPrefer = uploadPreferService.detail(uploadNo, loginInfo.getUserId());
        int handler = 0;
        if (userLoginPrefer == null) {
            userLoginPrefer = new UploadPreferDto();
            userLoginPrefer.setUploadNo(uploadNo);
            userLoginPrefer.setUserId(loginInfo.getUserId());
            userLoginPrefer.setPrefer(preferBtn);
            handler = uploadPreferService.register(userLoginPrefer);
        } else {
            if (userLoginPrefer.isPrefer() == preferBtn) {
                handler = uploadPreferService.remove(userLoginPrefer.getUploadPreferNo());
            } else {
                userLoginPrefer.setPrefer(preferBtn);
                handler = uploadPreferService.modify(userLoginPrefer);
            }
        }
        ajaxStateHandler.setState(handler);
        return ajaxStateHandler;
    }
}
