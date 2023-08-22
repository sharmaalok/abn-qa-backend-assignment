package com.abnamro.pojo;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@Data
@NoArgsConstructor
@JsonInclude(Include.NON_NULL)
public class Issue {
	private String title;
	private String description;
	private String issue_type;

	public Issue(String title, String description, String issue_type) {
		this.title = title;
		this.description = description;
		this.issue_type = issue_type;
	}
}
