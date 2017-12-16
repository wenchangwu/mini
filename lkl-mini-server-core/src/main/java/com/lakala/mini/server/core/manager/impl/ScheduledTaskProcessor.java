package com.lakala.mini.server.core.manager.impl;

import com.lakala.core.dto.DateRangeDTO;
import com.lakala.mini.dto.DataPushToJobRequest;
import com.lakala.mini.server.core.manager.ICardManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service(value="scheduledTaskProcessor")
public class ScheduledTaskProcessor implements Processor {
    @Autowired
    ICardManager cardManager;
    /**
    @Autowired
    private Worker worker;*/

	public static final long ONE_DAY = 24 * 60 * 60 * 1000;	
    /**
	private final AtomicInteger counter = new AtomicInteger(); 

    @Scheduled(fixedDelay=300)
    public void process() {
    	
        System.out.println("processing next 10 at " + new Date());
        for (int i = 0; i < 10; i++) {
            worker.work(counter.incrementAndGet());
        }

    }*/
    
    /**
     * cron表达式
     * Seconds  Minutes  Hours  Day-of-Month  Month Day-of-Week    
	*/
	@Scheduled(cron = "0 0 0 ? * ?")
	@Override
	public void dataPushToJobScheduled() {

		Date now = new Date();
		Date nextDay = new Date(now.getTime() + ONE_DAY) ;

		DateRangeDTO dateRange = new DateRangeDTO(now, nextDay);
		
		DataPushToJobRequest dataPushToJobRequest = new DataPushToJobRequest();
		dataPushToJobRequest.setDateRange(dateRange);
		
		try {
			cardManager.dataPushToJob(dataPushToJobRequest);
		} catch (Exception e) {
		}

	}
}