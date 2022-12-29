package com.twy.tripwithyou_spring.controller;

import com.twy.tripwithyou_spring.dto.MatchingDto;
import com.twy.tripwithyou_spring.dto.MatchingImgDto;
import com.twy.tripwithyou_spring.dto.PagingDto;
import com.twy.tripwithyou_spring.dto.UserDto;
import com.twy.tripwithyou_spring.service.MatchingService;
import jakarta.servlet.http.HttpServletRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/matching")
public class MatchingController {
    private MatchingService matchingService;
    private Logger log=LoggerFactory.getLogger(this.getClass().getSimpleName());
    @Value("${img.matching.path}")
    private String imgPath;
    public MatchingController(MatchingService matchingService) {
        this.matchingService = matchingService;
    }

    @GetMapping("/matchingMain")
    public String main(Model model,
                       PagingDto paging,
                       HttpServletRequest req) {
        if(paging.getOrderField()==null)paging.setOrderField("matching_no");
        paging.setQueryString(req.getParameterMap());
        List<MatchingDto> matchingList = matchingService.list(paging);
        model.addAttribute("matchingList",matchingList);
        model.addAttribute("paging",paging);
        log.info(paging.toString());
        return "/matching/matchingMain";
    }

    @GetMapping("/matchingRegister.do")
    public String register(@SessionAttribute UserDto loginInfo){
        return "/matching/matchingRegister";
    }

    @GetMapping("/matchingDetail.do")
    public ModelAndView detail(
            @RequestParam(name = "matchingNo") int matchingNo,
            ModelAndView model) throws Exception {
            MatchingDto matching=matchingService.detail(matchingNo);
            model.addObject("matching",matching);
            model.setViewName("/matching/matchingDetail");
        return model;
    }

    @PostMapping("/matchingRegister.do")
    public String register(
            MatchingDto matching,
            @RequestParam(required = false,name = "imgFile") MultipartFile[] imgFiles
    ) {
        int register = 0;
            try {
                List<MatchingImgDto> matchingImgList=new ArrayList<MatchingImgDto>();
                for (MultipartFile imgFile:imgFiles){
                    if(imgFile!=null && !imgFile.isEmpty()){
                        String[] contentsTypes=imgFile.getContentType().split("/");
                        if (contentsTypes[0].equals("image")){
                            try {
                                String fileName="matching_"+System.currentTimeMillis()+"_"+(int)(Math.random()*10000)+"."+contentsTypes[1];
                                Path path= Paths.get(imgPath+"/"+fileName);
                                imgFile.transferTo(path);
                                MatchingImgDto matchingImg=new MatchingImgDto();
                                matchingImg.setImgPath(fileName);
                                matchingImgList.add(matchingImg);
                            } catch (Exception e) {
                                log.error(e.getMessage());
                            }
                        }
                    }
                }
                matching.setMatchingImgList(matchingImgList);
                register=matchingService.register(matching);
            } catch (Exception e){
                log.error(e.getMessage());
            }
        if (register > 0) {
            return "redirect:/matching/matchingMain";
        } else {
            return "redirect:/matching/matchingMain";
        }
    }
}