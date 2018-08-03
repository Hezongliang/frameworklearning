package com.ztesoft.frameworklearning.schedule.job;

import org.quartz.DisallowConcurrentExecution;
import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.quartz.PersistJobDataAfterExecution;
import org.springframework.stereotype.Component;

import com.ztesoft.frameworklearning.base.BaseJob;

@PersistJobDataAfterExecution
@DisallowConcurrentExecution
@Component("unlockUserJob")
public class UnlockUserJob extends BaseJob implements Job {

	// 此处不使用事务，在unlockUser中针对每个用户使用单独的事务
	public void execute(JobExecutionContext jobCtx) throws JobExecutionException {
		logger.info("execute quartz job: unlockUserJob");
	}
}
