package com.ggs.admin.module.work.service;

import com.ggs.admin.module.sys.model.PageModel;
import com.ggs.admin.module.work.dao.CmsWorkMapper;
import com.ggs.admin.module.work.dao.ProblemMapper;
import com.ggs.admin.module.work.dao.WorkGoalMapper;
import com.ggs.admin.module.work.model.CmsWorkModel;
import com.ggs.admin.module.work.model.ProblemModel;
import com.ggs.admin.module.work.model.WorkGoalModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;

@Component
public class WorkService{
    @Autowired
    private CmsWorkMapper cmsWorkMapper;//周报dao

    @Autowired
    private ProblemMapper problemMapper;//问题dao

    @Autowired
    private WorkGoalMapper workGoalMapper;//工作目标dao

    /**
     * 查询周报
     * */
    public PageModel queryCmsWork(CmsWorkModel cmsWorkModel){
        String code="0";
        String msg="";
        List list = null;
        Integer counter=0;
        try {
            list = cmsWorkMapper.query(cmsWorkModel);
            counter= cmsWorkMapper.queryCount(cmsWorkModel);
        }catch (Exception e){
            e.printStackTrace();
            code="1";
            msg="查询用户失败";
        }
        PageModel pageModel =new PageModel(code,msg,counter,list);
        return pageModel;
    }

    /**
     * 添加周报
     * */
    public void addCmsWork(CmsWorkModel cmsWorkModel){
        cmsWorkMapper.add(cmsWorkModel);
    }

    /**
     * 更新周报
     * */
    public void updateCmsWork(CmsWorkModel cmsWorkModel){
        cmsWorkMapper.update(cmsWorkModel);
        if(cmsWorkModel.getRemark()!=null){
            cmsWorkModel.setCreateuser(cmsWorkModel.getUpdateuser());
            cmsWorkMapper.log(cmsWorkModel);
        }
    }


    /**
     * 删除周报
     * */
    public void delCmsWork(CmsWorkModel cmsWorkModel){
        cmsWorkMapper.del(cmsWorkModel);
    }



    /**
     * 获取工作完成情况
     * */
    public List<Map<String,Object>>getWorkFinishStatus(CmsWorkModel cmsWorkModel){
        return cmsWorkMapper.getWorkFinishStatus(cmsWorkModel);
    }

    /**
     * 获取工作空闲状态
     * */
    public PageModel getWorkFreeStatus(CmsWorkModel cmsWorkModel) {
        String code="0";
        String msg="";
        List list = null;
        Integer counter=0;
        try {
            list = cmsWorkMapper.getWorkFreeStatus(cmsWorkModel);
            counter= list.size();
        }catch (Exception e){
            e.printStackTrace();
            code="1";
            msg="查询失败";
        }
        PageModel pageModel =new PageModel(code,msg,counter,list);
        return pageModel;

    }

    /**
     * 获取工作完成情况
     * */
    public List<Map<String,Object>>getWeekWorkFinishStatus(CmsWorkModel cmsWorkModel){
        return cmsWorkMapper.getWeekWorkFinishStatus(cmsWorkModel);
    }


    /**
     * 问题管理
     * */
    public PageModel queryProblem(ProblemModel problemModel){
        String code="0";
        String msg="";
        List list = null;
        Integer counter=0;
        try {
            list = problemMapper.query(problemModel);
            counter= problemMapper.queryCount(problemModel);
        }catch (Exception e){
            e.printStackTrace();
            code="1";
            msg="查询用户失败";
        }
        PageModel pageModel =new PageModel(code,msg,counter,list);
        return pageModel;
    }
    /**
     * 添加问题
     * */
    public void addProblem(ProblemModel problemModel){
        problemMapper.add(problemModel);
    }

    /**
     * 更新问题
     * */
    public void updateProblem(ProblemModel problemModel){
        problemMapper.update(problemModel);
    }


    /**
     * 删除问题
     * */
    public void delProblem(ProblemModel problemModel){
        problemMapper.del(problemModel);
    }

    /**
     * 查找一个问题通过id
     * */
    public ProblemModel getProblemById(ProblemModel problemModel){
        return problemMapper.queryById(problemModel);
    }


    /**
     * 获取问题完成情况
     * */
    public List<Map<String,Object>>getWeekProblemFinishStatus(ProblemModel problemModel){
        return problemMapper.getWeekProblemFinishStatus(problemModel);
    }

    /**
     * 问题原因分析
     * */
    public List<Map<String,Object>>causeTypeTotal(ProblemModel problemModel){
        return problemMapper.causeTypeTotal(problemModel);
    }

    /**
     * 获取工作完成情况
     * */
    public List<Map<String,Object>>getWorkOrgTotal(CmsWorkModel cmsWorkModel){
        return cmsWorkMapper.getOrgTotal(cmsWorkModel);
    }

    /**
     * 获取工作完成情况
     * */
    public List<Map<String,Object>>getWorkUserTotal(CmsWorkModel cmsWorkModel){
        return cmsWorkMapper.getUserTotal(cmsWorkModel);
    }


    /**
     * 获取工作完成情况
     * */
    public List<Map<String,Object>>getProblemOrgTotal(ProblemModel problemModel){
        return problemMapper.getOrgTotal(problemModel);
    }

    /**
     * 获取工作完成情况
     * */
    public List<Map<String,Object>>getProblemUserTotal(ProblemModel problemModel){
        return problemMapper.getUserTotal(problemModel);
    }

    /**
     * 获得用户排名
     * */
    public PageModel userRanking(CmsWorkModel cmsWorkModel) {
        String code="0";
        String msg="";
        List list = null;
        Integer counter=0;
        try {
            list = cmsWorkMapper.userRanking(cmsWorkModel);
            counter= list.size();
        }catch (Exception e){
            e.printStackTrace();
            code="1";
            msg="查询失败";
        }
        PageModel pageModel =new PageModel(code,msg,counter,list);
        return pageModel;

    }


    /**
     * 查询目标
     * */
    public PageModel queryWorkGoal(WorkGoalModel model){
        String code="0";
        String msg="";
        List list = null;
        Integer counter=0;
        try {
            list = workGoalMapper.query(model);
            counter= workGoalMapper.queryCount(model);
        }catch (Exception e){
            e.printStackTrace();
            code="1";
            msg="查询用户失败";
        }
        PageModel pageModel =new PageModel(code,msg,counter,list);
        return pageModel;
    }

    /**
     * 添加目标
     * */
    public void addWorkGoal(WorkGoalModel model){
        workGoalMapper.add(model);
    }

    /**
     * 更新目标
     * */
    public void updateWorkGoal(WorkGoalModel model){
        workGoalMapper.update(model);
        if(model.getRemark()!=null){
            model.setCreateuser(model.getUpdateuser());
            workGoalMapper.log(model);
        }
    }


    /**
     * 删除目标
     * */
    public void delWorkGoal(WorkGoalModel model){
        workGoalMapper.del(model);
    }


    /**
     * 查询当月目标
     * */
    public List<Map<String,Object>> queryMonthGoal(WorkGoalModel model){
        return workGoalMapper.queryMonthGoal(model);
    }

    /**
     * 查询日志
     * */
    public List<Map<String,Object>> queryGoalLog(WorkGoalModel model){
        return workGoalMapper.queryLog(model);
    }

    /**
     * 查询日志
     * */
    public List<Map<String,Object>> queryWorkLog(CmsWorkModel model){
        return cmsWorkMapper.queryLog(model);
    }


    /**
     * 统计待办事项
     * */
    public List<Map<String,Object>> countUnfinishWorks(WorkGoalModel model){
        return workGoalMapper.countUnfinishWorks(model);
    }
}
