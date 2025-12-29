package com.rasthrabhasha.application;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ExamApplicationController {

	@Autowired
	ExamApplicationService eas;

	@PostMapping("/fill-form")
	public ExamApplication fillForm(@RequestBody ExamApplication exapp) {

		return eas.fillForm(exapp);

	}

	@GetMapping("/get-form")
	public ExamApplication getForm(@RequestParam long applicationId, @RequestParam long examNo) {
		return eas.getFormByApplicationIdAndExamNo(applicationId, examNo);
	}

}
