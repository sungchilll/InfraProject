package app.api.controller;

import org.springframework.web.bind.annotation.RestController;

import app.repository.EmpRepository;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@RestController
public class EmpAPIController {

	private final EmpRepository empRepository;
	
	
}