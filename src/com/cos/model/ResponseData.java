package com.cos.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ResponseData {
	private int statusCode; //1, -1
	private String status; // "ok"
	private String statusMessage;
}
