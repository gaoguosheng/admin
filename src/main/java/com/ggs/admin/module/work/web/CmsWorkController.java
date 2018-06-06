package com.ggs.admin.module.work.web;

import com.ggs.admin.module.sys.model.PageModel;
import com.ggs.admin.module.sys.model.UserModel;
import com.ggs.admin.module.sys.web.BaseController;
import com.ggs.admin.module.work.model.CmsWorkModel;
import com.ggs.admin.module.work.service.WorkService;
import com.sun.org.apache.xpath.internal.operations.Mod;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttribute;

import java.util.HashMap;
import java.util.List;

/**
 * 周报管理
 * */
@Controller
@RequestMapping("/admin/cmswork/")
public class CmsWorkController  extends BaseController {

    private final String path=webRoot+"cmswork";

    @Autowired
    private WorkService service;

    @RequestMapping("index.do")
    public String index(HashMap<String, Object> map) {
        return path+"/index";
    }

    @RequestMapping("search.do")
    public String search(HashMap<String, Object> map) {
        return path+"/search";
    }

    @RequestMapping("query.do")
    @ResponseBody
    public PageModel query(CmsWorkModel cmsWorkModel,@SessionAttribute UserModel  admin){
        cmsWorkModel.setTouser(admin.getUsercode());
        cmsWorkModel.setOrgid(admin.getOrgid());
        PageModel pageModel = service.queryCmsWork(cmsWorkModel);
        return  pageModel;
    }

    @RequestMapping("queryAll.do")
    @ResponseBody
    public PageModel queryAll(CmsWorkModel cmsWorkModel,@SessionAttribute UserModel  admin){
        cmsWorkModel.setOrgid(admin.getOrgid());
        PageModel pageModel = service.queryCmsWork(cmsWorkModel);
        return  pageModel;
    }

    @RequestMapping("add.do")
    @ResponseBody
    public void add(CmsWorkModel cmsWorkModel,@SessionAttribute UserModel  admin){
        cmsWorkModel.setCreateuser(admin.getUsercode());
        cmsWorkModel.setName(admin.getUsername());
        cmsWorkModel.setTaskname("新建任务");
        service.addCmsWork(cmsWorkModel);
    }

    @RequestMapping("update.do")
    @ResponseBody
    public void update(CmsWorkModel cmsWorkModel,@SessionAttribute UserModel admin){
        cmsWorkModel.setUpdateuser(admin.getUsercode());
        service.updateCmsWork(cmsWorkModel);
    }

    @RequestMapping("del.do")
    @ResponseBody
    public void del(CmsWorkModel cmsWorkModel){
        service.delCmsWork(cmsWorkModel);
    }

    @RequestMapping("touser.html")
    public String touserHtml(CmsWorkModel cmsWorkModel, ModelMap map){
        map.put("cmswork",cmsWorkModel);
        return path+"/touser";
    }

    @RequestMapping("finishStatus.do")
    @ResponseBody
    public List workFinishStatus(CmsWorkModel cmsWorkModel,@SessionAttribute UserModel  admin){
        cmsWorkModel.setOrgid(admin.getOrgid());
        List list = service.getWorkFinishStatus(cmsWorkModel);
        return  list;
    }

    @RequestMapping("freeStatus.do")
    @ResponseBody
    public PageModel workFreeStatus(CmsWorkModel cmsWorkModel,@SessionAttribute UserModel  admin){
        cmsWorkModel.setOrgid(admin.getOrgid());
        PageModel pageModel = service.getWorkFreeStatus(cmsWorkModel);
        return  pageModel;
    }

    @RequestMapping("weekTotal.do")
    @ResponseBody
    public List weekTotal(CmsWorkModel cmsWorkModel,@SessionAttribute UserModel  admin){
        cmsWorkModel.setOrgid(admin.getOrgid());
        List list = service.getWeekWorkFinishStatus(cmsWorkModel);
        return list;
    }

    @RequestMapping("report.do")
    public String report(HashMap<String, Object> map) {
        return path+"/report";
    }

    @RequestMapping("orgTotal.html")
    public String orgTotalHtml(HashMap<String, Object> map) {
        return path+"/orgTotal";
    }

    @RequestMapping("orgTotal.do")
    @ResponseBody
    public List orgTotal(CmsWorkModel cmsWorkModel,@SessionAttribute UserModel  admin){
        cmsWorkModel.setOrgid(admin.getOrgid());
        List list = service.getWorkOrgTotal(cmsWorkModel);
        return list;
    }

    @RequestMapping("userTotal.do")
    @ResponseBody
    public List userTotal(CmsWorkModel cmsWorkModel,@SessionAttribute UserModel  admin){
        cmsWorkModel.setOrgid(admin.getOrgid());
        List list = service.getWorkUserTotal(cmsWorkModel);
        return list;
    }

    @RequestMapping("userTotal.html")
    public String userTotalHtml(ModelMap map,CmsWorkModel cmsWorkModel) {
        map.put("cmswork",cmsWorkModel);
        return path+"/userTotal";
    }

    @RequestMapping("trend.html")
    public String trend(ModelMap map,CmsWorkModel cmsWorkModel) {
        map.put("cmswork",cmsWorkModel);
        return path+"/trend";
    }

    @RequestMapping("trend.do")
    @ResponseBody
    public List trend(CmsWorkModel cmsWorkModel,@SessionAttribute UserModel  admin){
        List list = service.getWeekWorkFinishStatus(cmsWorkModel);
        return list;
    }


    @RequestMapping("userRanking.html")
    public String userRankingHtml(ModelMap map,CmsWorkModel cmsWorkModel) {
        map.addAttribute("model",cmsWorkModel);
        return path+"/userRanking";
    }

    @RequestMapping("userRanking.do")
    @ResponseBody
    public PageModel userRanking(CmsWorkModel cmsWorkModel,@SessionAttribute UserModel  admin){
        PageModel pageModel = service.userRanking(cmsWorkModel);
        return  pageModel;
    }

}
