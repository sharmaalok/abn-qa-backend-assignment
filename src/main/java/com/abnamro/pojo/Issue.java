package com.abnamro.pojo;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@JsonInclude(Include.NON_EMPTY)
public class Issue {
	private String title;
	private String description;
	private String issue_type; // valid values are  "issue", "incident", "test_case" |   empty value for test = null
	private String assignee_id;
	private Boolean confidential; //valid value for test = true,false  |   empty value for test = null
	private Boolean discussion_locked; //valid value for test = true,false  |   empty value for test = null
	private String created_at;
	private String due_date; 
	private String labels;
	private String add_labels;
	private String remove_labels; 
	private Integer milestone_id; 
	private String state_event; 
	private Integer weight; //  empty value for test = null , should always be supplied an empty value because of the account used for testing is non-admin
	private Integer epic_id; //  empty value for test = null , should always be supplied an empty value because of the account used for testing is non-admin 
	private Integer epic_iid; //  empty value for test = null , should always be supplied an empty value because of the account used for testing is non-admin

	public Issue(String title, String description, String issue_type,String assignee_id, Boolean confidential, Boolean discussion_locked, String created_at, String due_date,  String labels, String add_labels, String remove_labels,  Integer milestone_id,String state_event,  Integer weight,  Integer epic_id,  Integer epic_iid) 
	{
		this.title = title;
		this.description = description;
		this.issue_type = issue_type;
		this.assignee_id = assignee_id;
		this.confidential = confidential;
		this.discussion_locked=discussion_locked;
		this.created_at=created_at;
		this.due_date=due_date; 
		this.labels=labels;
		this.add_labels=add_labels;
		this.remove_labels=remove_labels;
		this.milestone_id=milestone_id; 
		this.state_event=state_event; 
		this.weight=weight; 
		this.epic_id=epic_id; 
		this.epic_iid=epic_iid;
	}
}
