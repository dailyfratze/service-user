/*
 * Copyright 2018 michael-simons.eu.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package de.dailyfratze.users.domain.model;

import java.util.Optional;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;

/**
 * A unique identifier for a {@link User}.
 *
 * @author Michael J. Simons, 2018-05-31
 */
@Getter
@EqualsAndHashCode
@ToString
public class Login {
	static final String REGEX = "[a-z]+\\w*";
	static final int MAX_LENGTH = 32;

	/**
	 * The value of this login. Must start with a letter, may not be blank and must be lowercase.
	 */
	private final String value;

	Login(final String value) {
		final String hlp = Optional.ofNullable(value).map(String::trim).filter(s -> !s.isEmpty()).orElseThrow(() -> new IllegalArgumentException("Login may not be null or empty!"));
		if(!hlp.matches(REGEX)) {
			throw new IllegalArgumentException(String.format("Invalid login: '%s'!", hlp));
		}
		if(hlp.length() > MAX_LENGTH) {
			throw new IllegalArgumentException(String.format("Login may not exceed %d chars.", MAX_LENGTH));
		}
		this.value = hlp;
	}
}
