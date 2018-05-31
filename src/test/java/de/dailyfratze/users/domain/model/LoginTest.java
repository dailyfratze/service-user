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

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatExceptionOfType;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

/**
 * @author Michael J. Simons, 2018-05-31
 */
@Tag("model")
@DisplayName("Login")
class LoginTest {
	@Test
	@DisplayName("Login should reject invalid values.")
	public void shouldRejectInvalidValues() {
		assertThatExceptionOfType(IllegalArgumentException.class).isThrownBy(() -> new Login("42abc"))
				.withMessage("Invalid login: '42abc'!");
	}

	@Test
	@DisplayName("Login should reject empty and null values.")
	public void shouldRejectEmptyAndNullValues() {
		var expectedMessage = "Login may not be null or empty!";
		assertThatExceptionOfType(IllegalArgumentException.class).isThrownBy(() -> new Login(""))
				.withMessage(expectedMessage);
		assertThatExceptionOfType(IllegalArgumentException.class).isThrownBy(() -> new Login(null))
				.withMessage(expectedMessage);
	}

	@Test
	@DisplayName("Login should reject long values.")
	public void shouldRejectLongLogins() {
		var expectedMessage = "Login may not be null or empty!";
		assertThatExceptionOfType(IllegalArgumentException.class).isThrownBy(() -> new Login("abcdefghijklmnopqrstuvw1234567890"))
				.withMessage("Login may not exceed 32 chars.");
	}

	@Test
	@DisplayName("Login should trim input values.")
	public void shouldTrimInputValues() {
		var login = new Login(" michael ");
		assertThat(login.getValue()).isEqualTo("michael");
	}
}