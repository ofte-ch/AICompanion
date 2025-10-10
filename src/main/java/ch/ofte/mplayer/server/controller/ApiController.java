package ch.ofte.mplayer.server.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import ch.ofte.server.BaseApiController;

@RestController("apiController")
@RestControllerAdvice
@RequestMapping("/api")
public class ApiController extends BaseApiController {
}
