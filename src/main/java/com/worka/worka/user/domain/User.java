package com.worka.worka.user.domain;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.NoArgsConstructor;

/**
 * todo 추가할 수 있는 필드
 * 생년월일: 성인인증에 필요할 수 있음.
 * 휴대폰번호, 이메일: 본인인증할 때 필요함.
 */

@Getter
@Entity
@NoArgsConstructor
public class User {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@JoinColumn(name = "user_id")
	private Long id;
	@NotNull
	private String name;
	@NotNull
	private Gender gender;

	public static User addUser(String name, Gender gender) {
		return new User(name, gender);
	}

	private User(String name, Gender gender) {
		this.name = name;
		this.gender = gender;
	}

	/**
	 * test용 생성자. 서비스에서는 사용 금지.
 	 */
	public User(Long id, String name, Gender gender) {
		this.id = id;
		this.name = name;
		this.gender = gender;
	}

	public void updateName(String updateName) {
		this.name = updateName;
	}

	public void updateGender(Gender gender) {
		this.gender = gender;
	}
}