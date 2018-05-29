package com.edu.nju.wel.controller;

import com.edu.nju.wel.model.ChartParaVO;
import com.edu.nju.wel.model.ListVO;
import com.edu.nju.wel.service.CompanyDetailService;
import com.edu.nju.wel.service.MakerDetailService;
import com.edu.nju.wel.util.ItemOfPageNum;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2017/5/31.
 */
@Controller
@RequestMapping("/company")
public class CompanyDetailController {
    @Resource(name="CompanyDetailService")
    CompanyDetailService companyDetailService;

    @RequestMapping(value = "/detail",method = RequestMethod.GET)
    public ModelAndView companyDetail(String companyName,String genre,String page){
        ModelAndView company = new ModelAndView("companyDetail");

        List<ListVO> movieList = companyDetailService.getMoviesByCompanyName(companyName,genre==null?"All":genre);
        int index = page == null ? 1 : Integer.valueOf(page);
        List<ListVO> res = ItemOfPageNum.getPageItem(index,ItemOfPageNum.COMPANY_MOVIE,movieList);
        //统计页码数
        company.addObject("pageNum", movieList.size() % ItemOfPageNum.COMPANY_MOVIE == 0 ? movieList.size() / ItemOfPageNum.COMPANY_MOVIE : movieList.size() / ItemOfPageNum.COMPANY_MOVIE + 1);
        //当前页码数
        company.addObject("presentPage", index);
        company.addObject("movies", res);

        company.addObject("company",companyDetailService.getCompanyDetail(companyName));

        //记录该公司有的流派
        ArrayList<String> genres = new ArrayList<>();

        //判断雷达图是否能够画出来
        boolean canDraw = companyDetailService.getTopSix(companyName)!=null;
        company.addObject("canDraw",canDraw);
        if(canDraw) {
            ArrayList<ChartParaVO> topsix = new ArrayList<>();
            DecimalFormat df = new DecimalFormat("#.0");
            for (Map.Entry<String, Double> entry : companyDetailService.getTopSix(companyName).entrySet()) {
                genres.add(entry.getKey());
                topsix.add(new ChartParaVO(entry.getKey(), df.format(entry.getValue())));
            }
            company.addObject("topsix", topsix);
        }
        //补上All
        genres.add("All");

        company.addObject("genres",genres);
        company.addObject("gross",companyDetailService.getGrossPerYear(companyName));

        return company;
    }

    @RequestMapping("/all")
    public ModelAndView allCompany(){
        ModelAndView allCompany = new ModelAndView("company");
        allCompany.addObject("allCompany",companyDetailService.getAllCompany());
        return allCompany;
    }
}
