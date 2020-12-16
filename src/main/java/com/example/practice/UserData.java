package com.example.practice;

import java.io.Serializable;
import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.springframework.stereotype.Service;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name="user_data")
@NoArgsConstructor
@Data
public class UserData{
	

	public UserData(String id, String name, String birthday) {
		this.id = id;
		this.name = name;
		this.birthday = birthday;
	}

	/** ID */
    //主キー項目に「@Id」を付与
    @Id
    @Column(nullable = false)
    private String id;
   
    /** 名前 */
    @Column(nullable = false)
    private String name;

    /** 生年月日_年 */
    @Column(nullable = false)
    private String birthday;
	

    public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getBirthday() {
		return birthday;
	}

	public void setBirthday(String birthday) {
		this.birthday = birthday;
	}

}