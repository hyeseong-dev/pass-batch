package com.fc.pass.pass_batch;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;

import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;

import org.springframework.batch.repeat.RepeatStatus;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@EnableBatchProcessing
@SpringBootApplication
public class PassBatchApplication {

	private final JobBuilderFactory jobBuilderFactory;
	private final StepBuilderFactory stepBuilderFactory;

    public PassBatchApplication(JobBuilderFactory jobBuilderFactory, StepBuilderFactory stepBuilderFactory) {
        this.jobBuilderFactory = jobBuilderFactory;
        this.stepBuilderFactory = stepBuilderFactory;
    }

	@Bean
	public Step passStep(){
		return this.stepBuilderFactory.get("passStep")
				.tasklet((contribution, chunkContext) -> {
					System.out.println("PassSetup 실행");
					return RepeatStatus.FINISHED;
				}).build();
	}
	@Bean
	public Job passJob(){
        return this.jobBuilderFactory.get("passJob")
				.start(passStep())
				.build();
    }

    public static void main(String[] args) {
		SpringApplication.run(PassBatchApplication.class, args);
	}

}
