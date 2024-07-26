/**
 * 
 */
package com.gniot.crs.rest;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 
 */
@RestController
@RequestMapping("/api")
public class DemoController {

	// write the get method for test 
		@GetMapping("/getService")
		public String helloGetService() {
			return "This is my first rest service";
		}
		// write the get method for test 
		@PostMapping("/postService")
		public String helloPostService() {
			return "This is my first Post service";
		}
		// write the get method for test 
		@PutMapping("/putService")
		public String helloPutService() {
			return "This is my first Put service";
		}
		// write the get method for test 
		@DeleteMapping("/deleteService")
		public String helloDeleteService() {
			return "This is my first Delete service";
		}

}