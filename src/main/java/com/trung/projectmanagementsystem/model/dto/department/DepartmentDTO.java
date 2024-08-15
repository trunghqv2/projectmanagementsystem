package com.trung.projectmanagementsystem.model.dto.department;

import java.util.Date;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class DepartmentDTO {
	private int id;
	private String name;

	private Integer memberSize;

	private String managerFullname;
	private String managerUsername;

	private String creatorFullname;
	private String creatorUsername;

	private Date createdDateTime;
}
