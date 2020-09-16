package utils;

import io.qameta.allure.Attachment;

public class AllureAttachment {

	@Attachment(value = "{0}", type = "text/plain")
	public static String addTextAttachment(String message) {
		return message;
	}

}
