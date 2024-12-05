package cn.believesun.springmvc.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping("/product")
@Controller
public class ProductController {
    @RequestMapping("/detail")
    public String productDetail(){
        return "product_detail";
    }
}
