package com.aaa.service.claim.measure;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import org.springframework.util.StopWatch;
import lombok.extern.slf4j.Slf4j;


@Slf4j
public class MetricsLog {
	private static final String NOT_RUNNING = "none";
	private StopWatch stopWatch = new StopWatch();
	private String methodName;
	
	
	public MetricsLog(String methodName){
		this.methodName = methodName;
	}
	
	public void start(){
		stopWatch.start();
	}
	
	public void stop(){
		if (stopWatch.isRunning()) {
			stopWatch.stop();
			logTime(String.valueOf(stopWatch.getTotalTimeMillis()));
			return;
		}
		logTime(NOT_RUNNING);
	}
	
	private void logTime(String time) {
		DateTimeFormatter format = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
		log.info(String.format("%s Method=%s Time=%s", LocalDateTime.now().format(format), methodName, time));
	}
}
