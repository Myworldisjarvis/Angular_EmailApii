package com.email.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.email.model.EmailRequest;
import com.email.model.EmailResponce;
import com.email.services.EmailServices;

@RestController
@CrossOrigin
public class EmailController {

	@Autowired
	private EmailServices emailServices;

	@RequestMapping("/wel")
	public String welcome() {

		return "welcome Email";
	}

	// api to send email
	@PostMapping("/sendmail")
	public ResponseEntity<?> sendEmail(@RequestBody EmailRequest request) {

		boolean sendStatus = this.emailServices.sendEmail(request.getMsg(), request.getSubject(), request.getTo());
		if (sendStatus) {
			return ResponseEntity.ok(new EmailResponce("Email is sent successfully..."));
		} else {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new EmailResponce("Email not sent..."));
		}

	}

}
