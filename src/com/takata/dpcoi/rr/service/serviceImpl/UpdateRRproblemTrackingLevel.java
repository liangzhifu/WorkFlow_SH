package com.takata.dpcoi.rr.service.serviceImpl;

import com.takata.dpcoi.rr.domain.RRProblem;
import com.takata.dpcoi.rr.service.RRProblemService;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author lzf
 **/

@Component("updateRRproblemTrackingLevel")
public class UpdateRRproblemTrackingLevel {

    @Resource(name = "rRProblemService")
    private RRProblemService rRProblemService;

    @Scheduled(cron = "0 0 0/1 * * ?")
    public void job() {
        try {
            List<RRProblem> rrProblemList = this.rRProblemService.queryJobRRProblemTrackingLevelList();
            for (RRProblem rrProblem : rrProblemList){
                this.rRProblemService.updateTrackingLevel(rrProblem);
                this.rRProblemService.updateRRProblem(rrProblem);
            }
        } catch (Exception e) {

        }
    }
}
