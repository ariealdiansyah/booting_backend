package com.lawencon.booting.utility;

import java.io.UnsupportedEncodingException;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Component;

@Component
public class Mail {

	private String subject;
	private String body;
	private String address;

	@Autowired
	private JavaMailSender javaMailSender;

	public Mail init(String address, String subject, String body) {
		this.body = body;
		this.subject = subject;
		this.address = address;

		return this;
	}

	public void sendMail() {
		// without attachment file
		SimpleMailMessage email = new SimpleMailMessage();

		// with attachment file
		MimeMessage msg = javaMailSender.createMimeMessage();
		try {
			MimeMessageHelper helper = new MimeMessageHelper(msg, true);
			helper.setTo(address);

			helper.setSubject(subject);

			// default = text/plain
			// helper.setText("Check attachment for image!");

			// true = text/html
			// helper.setText("<h1> Ini Text berupa HTML </h1>");
			
			String textHTML = "<div class=\"col-sm-12\"\r\n" + 
					"    style=\"margin: 0 !important; padding: 0 !important; -webkit-text-size-adjust: 100%; -ms-text-size-adjust: 100%; height: 100%; margin: 0; padding: 0; width: 100%; font-weight: 400; color: rgb(79, 79, 101); -webkit-font-smoothing: antialiased; -ms-text-size-adjust: 100%; -moz-osx-font-smoothing: grayscale; text-rendering: optimizeLegibility;\">\r\n" + 
					"    <!-- HIDDEN PREHEADER TEXT -->\r\n" + 
					"    <div class=\"preheader\"\r\n" + 
					"        style=\"display: none; font-size: 1px; color: rgb(255, 255, 255); line-height: 1px; max-height: 0px; max-width: 0px; opacity: 0; overflow: hidden;\">\r\n" + 
					"    </div>\r\n" + 
					"    <table border=\"0\" cellpadding=\"0\" cellspacing=\"0\" width=\"100%\" class=\" mainTable  \"\r\n" + 
					"        style=\"-webkit-text-size-adjust: 100%; -ms-text-size-adjust: 100%; border-collapse: collapse; background-color: rgb(240, 240, 240);\">\r\n" + 
					"        <!-- HEADER -->\r\n" + 
					"        <tr>\r\n" + 
					"            <td align=\"center\" class=\"header\"\r\n" + 
					"                style=\"-webkit-text-size-adjust: 100%; -ms-text-size-adjust: 100%; border-collapse: collapse; display: table-cell;\">\r\n" + 
					"                <table border=\"0\" cellpadding=\"0\" cellspacing=\"0\" width=\"100%\" class=\"Content\"\r\n" + 
					"                    style=\"-webkit-text-size-adjust: 100%; -ms-text-size-adjust: 100%; border-collapse: collapse; width: 580px; margin: 0 auto;\">\r\n" + 
					"                    <tr class=\"spacer\">\r\n" + 
					"                        <td height=\"12px\" colspan=\"2\"\r\n" + 
					"                            style=\"font-size: 12px; line-height:12px; -webkit-text-size-adjust: 100%; -ms-text-size-adjust: 100%; border-collapse: collapse; display: table-cell;\">\r\n" + 
					"                            &nbsp;</td>\r\n" + 
					"                    </tr>\r\n" + 
					"                    <tr class=\"spacer\">\r\n" + 
					"                        <td height=\"12px\" colspan=\"2\"\r\n" + 
					"                            style=\"font-size: 12px; line-height:12px; -webkit-text-size-adjust: 100%; -ms-text-size-adjust: 100%; border-collapse: collapse; display: table-cell;\">\r\n" + 
					"                            &nbsp;</td>\r\n" + 
					"                    </tr>\r\n" + 
					"                    <tr>\r\n" + 
					"                        <td align=\"left\" valign=\"middle\"\r\n" + 
					"                            style=\"-webkit-text-size-adjust: 100%; -ms-text-size-adjust: 100%; border-collapse: collapse; display: table-cell; padding-left: 10px;\">\r\n" + 
					"                            <a routerLink=\"/user-pages/login\"\r\n" + 
					"                                style=\"-webkit-text-size-adjust: 100%; -ms-text-size-adjust: 100%;\">\r\n" + 
					"                                <img src=\"https://cdn.discordapp.com/attachments/743009938221170688/743413450784636998/unknown.png\"\r\n" + 
					"                                    width=\"110\" height=\"45\" alt=\"Tock\" border=\"0\" class=\"header-tockLogoImage\"\r\n" + 
					"                                    style=\"-ms-interpolation-mode: bicubic; border: 0; height: auto; line-height: 100%; outline: none; text-decoration: none; display: block; display: block; color: rgb(240, 240, 240);\" />\r\n" + 
					"                            </a> </td>\r\n" + 
					"                    </tr>\r\n" + 
					"                    <tr class=\"spacer\">\r\n" + 
					"                        <td height=\"12px\" colspan=\"2\"\r\n" + 
					"                            style=\"font-size: 12px; line-height:12px; -webkit-text-size-adjust: 100%; -ms-text-size-adjust: 100%; border-collapse: collapse; display: table-cell;\">\r\n" + 
					"                            &nbsp;</td>\r\n" + 
					"                    </tr>\r\n" + 
					"                    <tr class=\"spacer\">\r\n" + 
					"                        <td height=\"12px\" colspan=\"2\"\r\n" + 
					"                            style=\"font-size: 12px; line-height:12px; -webkit-text-size-adjust: 100%; -ms-text-size-adjust: 100%; border-collapse: collapse; display: table-cell;\">\r\n" + 
					"                            &nbsp;</td>\r\n" + 
					"                    </tr>\r\n" + 
					"                </table>\r\n" + 
					"            </td>\r\n" + 
					"        </tr>\r\n" + 
					"        <!-- CONTENT -->\r\n" + 
					"        <tr>\r\n" + 
					"            <td align=\"center\"\r\n" + 
					"                style=\"-webkit-text-size-adjust: 100%; -ms-text-size-adjust: 100%; border-collapse: collapse; display: table-cell;\">\r\n" + 
					"                <table border=\"0\" cellpadding=\"0\" cellspacing=\"0\" width=\"100%\" class=\"Content bg-white\"\r\n" + 
					"                    style=\"-webkit-text-size-adjust: 100%; -ms-text-size-adjust: 100%; border-collapse: collapse; background-color: white; width: 580px; margin: 0 auto;\">\r\n" + 
					"                    <tr>\r\n" + 
					"                        <td class=\"Content-container Content-container--main text textColorNormal\"\r\n" + 
					"                            style=\"-webkit-text-size-adjust: 100%; -ms-text-size-adjust: 100%; border-collapse: collapse; display: table-cell; font-weight: 400; font-size: 16px; line-height: 21px; color: rgb(79, 79, 101); padding-left: 60px; padding-right: 60px; padding-top: 54px; padding-bottom: 60px;\">\r\n" + 
					"                            <table width=\"100%\" border=\"0\" cellspacing=\"0\" cellpadding=\"0\"\r\n" + 
					"                                style=\"-webkit-text-size-adjust: 100%; -ms-text-size-adjust: 100%; border-collapse: collapse;\">\r\n" + 
					"                                <tr>\r\n" + 
					"                                    <td valign=\"top\" align=\"left\"\r\n" + 
					"                                        style=\"-webkit-text-size-adjust: 100%; -ms-text-size-adjust: 100%; border-collapse: collapse; display: table-cell;\">\r\n" + 
					"                                        <table width=\"100%\" border=\"0\" cellspacing=\"0\" cellpadding=\"0\"\r\n" + 
					"                                            style=\"-webkit-text-size-adjust: 100%; -ms-text-size-adjust: 100%; border-collapse: collapse;\">\r\n" + 
					"                                            <tr>\r\n" + 
					"                                                <td align=\"left\"\r\n" + 
					"                                                    style=\"-webkit-text-size-adjust: 100%; -ms-text-size-adjust: 100%; border-collapse: collapse; display: table-cell;\">\r\n" + 
					"                                                    <span class=\"h1 textColorDark\"\r\n" + 
					"                                                        style=\"font-weight: 700; vertical-align: middle; font-size: 36px; line-height: 42px; color: rgb(35, 35, 62);\">Your\r\n" + 
					"                                                        Account Was Arrived</span>\r\n" + 
					"                                                </td>\r\n" + 
					"                                            </tr>\r\n" + 
					"                                            <tr class=\"spacer\">\r\n" + 
					"                                                <td height=\"18px\" colspan=\"2\"\r\n" + 
					"                                                    style=\"font-size: 18px; line-height:18px; -webkit-text-size-adjust: 100%; -ms-text-size-adjust: 100%; border-collapse: collapse; display: table-cell;\">\r\n" + 
					"                                                    &nbsp;</td>\r\n" + 
					"                                            </tr>\r\n" + 
					"                                            <tr>\r\n" + 
					"                                                <td align=\"left\" colspan=\"2\" valign=\"top\" width=\"100%\" height=\"1\"\r\n" + 
					"                                                    class=\"hr\"\r\n" + 
					"                                                    style=\"-webkit-text-size-adjust: 100%; -ms-text-size-adjust: 100%; border-collapse: collapse; display: table-cell; background-color: rgb(211, 211, 216); border-collapse: collapse; line-height: 1px;\">\r\n" + 
					"                                                </td>\r\n" + 
					"                                            </tr>\r\n" + 
					"                                            <tr class=\"spacer\">\r\n" + 
					"                                                <td height=\"18px\" colspan=\"2\"\r\n" + 
					"                                                    style=\"font-size: 18px; line-height:18px; -webkit-text-size-adjust: 100%; -ms-text-size-adjust: 100%; border-collapse: collapse; display: table-cell;\">\r\n" + 
					"                                                    &nbsp;</td>\r\n" + 
					"                                            </tr>\r\n" + 
					"                                        </table>\r\n" + 
					"                                    </td>\r\n" + 
					"                                </tr>\r\n" + 
					"                                <tr>\r\n" + 
					"                                    <td valign=\"top\" align=\"left\"\r\n" + 
					"                                        style=\"-webkit-text-size-adjust: 100%; -ms-text-size-adjust: 100%; border-collapse: collapse; display: table-cell;\">\r\n" + 
					"                                        <table border=\"0\" cellpadding=\"0\" cellspacing=\"0\" width=\"100%\"\r\n" + 
					"                                            style=\"-webkit-text-size-adjust: 100%; -ms-text-size-adjust: 100%; border-collapse: collapse;\">\r\n" + 
					"                                            <tr>\r\n" + 
					"                                                <td align=\"left\" class=\"text textColorNormal\"\r\n" + 
					"                                                    style=\"-webkit-text-size-adjust: 100%; -ms-text-size-adjust: 100%; border-collapse: collapse; display: table-cell; font-weight: 400; font-size: 16px; line-height: 21px; color: rgb(79, 79, 101);\">\r\n" + 
					"                                                    Haloo (...)\r\n" + 
					"                                                    <br>\r\n" + 
					"                                                    Akun anda telah dapat digunakan:\r\n" + 
					"                                                    <br><br>\r\n" + 
					"                                                    <p>\r\n" + 
					"                                                        <b>\r\n" + 
					"                                                            Username: asdsadsa\r\n" + 
					"                                                            <br>\r\n" + 
					"                                                            Password: asdasdas\r\n" + 
					"                                                        </b>\r\n" + 
					"                                                    </p>\r\n" + 
					"                                                    Silahkan login untuk mengubah password anda.\r\n" + 
					"                                                </td>\r\n" + 
					"                                            </tr>\r\n" + 
					"                                            <tr class=\"spacer\">\r\n" + 
					"                                                <td height=\"12px\" colspan=\"2\"\r\n" + 
					"                                                    style=\"font-size: 12px; line-height:12px; -webkit-text-size-adjust: 100%; -ms-text-size-adjust: 100%; border-collapse: collapse; display: table-cell;\">\r\n" + 
					"                                                    &nbsp;</td>\r\n" + 
					"                                            </tr>\r\n" + 
					"                                            <tr class=\"spacer\">\r\n" + 
					"                                                <td height=\"12px\" colspan=\"2\"\r\n" + 
					"                                                    style=\"font-size: 12px; line-height:12px; -webkit-text-size-adjust: 100%; -ms-text-size-adjust: 100%; border-collapse: collapse; display: table-cell;\">\r\n" + 
					"                                                    &nbsp;</td>\r\n" + 
					"                                            </tr>\r\n" + 
					"                                            <tr>\r\n" + 
					"                                                <td class=\"align-items-center\"\r\n" + 
					"                                                    style=\"-webkit-text-size-adjust: 100%; -ms-text-size-adjust: 100%; border-collapse: collapse; display: table-cell;\">\r\n" + 
					"                                                    <table>\r\n" + 
					"                                                        <div class=\"template-demo\">\r\n" + 
					"                                                            <button routerLink=\"/user-pages/login\" type=\"button\"\r\n" + 
					"                                                                class=\"btn btn-gradient-primary\">Login</button>\r\n" + 
					"                                                        </div>\r\n" + 
					"                                                    </table>\r\n" + 
					"                                                </td>\r\n" + 
					"                                            </tr>\r\n" + 
					"                                        </table>\r\n" + 
					"                                    </td>\r\n" + 
					"                                </tr>\r\n" + 
					"                            </table>\r\n" + 
					"                        </td>\r\n" + 
					"                    </tr>\r\n" + 
					"                </table>\r\n" + 
					"            </td>\r\n" + 
					"        </tr>\r\n" + 
					"        <!-- FOOTER -->\r\n" + 
					"        <tr>\r\n" + 
					"            <td align=\"center\" class=\"Content\"\r\n" + 
					"                style=\"-webkit-text-size-adjust: 100%; -ms-text-size-adjust: 100%; border-collapse: collapse; display: table-cell; width: 580px; margin: 0 auto;\">\r\n" + 
					"                <table width=\"100%\" border=\"0\" cellspacing=\"0\" cellpadding=\"0\" align=\"center\" class=\"Content-container\"\r\n" + 
					"                    style=\"-webkit-text-size-adjust: 100%; -ms-text-size-adjust: 100%; border-collapse: collapse; padding-left: 60px; padding-right: 60px;\">\r\n" + 
					"                    <tr class=\"spacer\">\r\n" + 
					"                        <td height=\"12px\" colspan=\"2\"\r\n" + 
					"                            style=\"font-size: 12px; line-height:12px; -webkit-text-size-adjust: 100%; -ms-text-size-adjust: 100%; border-collapse: collapse; display: table-cell;\">\r\n" + 
					"                            &nbsp;</td>\r\n" + 
					"                    </tr>\r\n" + 
					"                    <tr class=\"spacer\">\r\n" + 
					"                        <td height=\"12px\" colspan=\"2\"\r\n" + 
					"                            style=\"font-size: 12px; line-height:12px; -webkit-text-size-adjust: 100%; -ms-text-size-adjust: 100%; border-collapse: collapse; display: table-cell;\">\r\n" + 
					"                            &nbsp;</td>\r\n" + 
					"                    </tr>\r\n" + 
					"                    <tr class=\"spacer\">\r\n" + 
					"                        <td height=\"12px\" colspan=\"2\"\r\n" + 
					"                            style=\"font-size: 12px; line-height:12px; -webkit-text-size-adjust: 100%; -ms-text-size-adjust: 100%; border-collapse: collapse; display: table-cell;\">\r\n" + 
					"                            &nbsp;</td>\r\n" + 
					"                    </tr>\r\n" + 
					"                    <tr>\r\n" + 
					"                        <td align=\"center\" valign=\"top\"\r\n" + 
					"                            style=\"-webkit-text-size-adjust: 100%; -ms-text-size-adjust: 100%; border-collapse: collapse; display: table-cell;\">\r\n" + 
					"                            <a routerLink=\"/user-pages/login\"\r\n" + 
					"                                style=\"-webkit-text-size-adjust: 100%; -ms-text-size-adjust: 100%;\"> <img\r\n" + 
					"                                    src=\"https://cdn.discordapp.com/attachments/743009938221170688/745555202949644308/android-icon-72x72.png\"\r\n" + 
					"                                    width=\"30\" height=\"30\" alt=\"Tock\" border=\"0\"\r\n" + 
					"                                    style=\"-ms-interpolation-mode: bicubic; border: 0; height: auto; line-height: 100%; outline: none; text-decoration: none; display: block;\" />\r\n" + 
					"                            </a> </td>\r\n" + 
					"                    </tr>\r\n" + 
					"                    <tr class=\"spacer\">\r\n" + 
					"                        <td height=\"18px\" colspan=\"2\"\r\n" + 
					"                            style=\"font-size: 18px; line-height:18px; -webkit-text-size-adjust: 100%; -ms-text-size-adjust: 100%; border-collapse: collapse; display: table-cell;\">\r\n" + 
					"                            &nbsp;</td>\r\n" + 
					"                    </tr>\r\n" + 
					"                    <tr>\r\n" + 
					"                        <td align=\"center\"\r\n" + 
					"                            style=\"-webkit-text-size-adjust: 100%; -ms-text-size-adjust: 100%; border-collapse: collapse; display: table-cell;\">\r\n" + 
					"                            <div class=\"text-xsmall textColorNormal\"\r\n" + 
					"                                style=\"font-size: 11px; text-transform: uppercase; line-height: 22px; letter-spacing: 1px; color: rgb(79, 79, 101);\">\r\n" + 
					"                                © 2019 Booting, Batch6.\r\n" + 
					"                            </div>\r\n" + 
					"                            <div class=\"text-xsmall textColorNormal\"\r\n" + 
					"                                style=\"font-size: 11px; text-transform: uppercase; line-height: 22px; letter-spacing: 1px; color: rgb(79, 79, 101);\">\r\n" + 
					"                                Linov Support\r\n" + 
					"                            </div>\r\n" + 
					"                        </td>\r\n" + 
					"                    </tr>\r\n" + 
					"                    <tr class=\"spacer\">\r\n" + 
					"                        <td height=\"12px\" colspan=\"2\"\r\n" + 
					"                            style=\"font-size: 12px; line-height:12px; -webkit-text-size-adjust: 100%; -ms-text-size-adjust: 100%; border-collapse: collapse; display: table-cell;\">\r\n" + 
					"                            &nbsp;</td>\r\n" + 
					"                    </tr>\r\n" + 
					"                    <tr class=\"spacer\">\r\n" + 
					"                        <td height=\"12px\" colspan=\"2\"\r\n" + 
					"                            style=\"font-size: 12px; line-height:12px; -webkit-text-size-adjust: 100%; -ms-text-size-adjust: 100%; border-collapse: collapse; display: table-cell;\">\r\n" + 
					"                            &nbsp;</td>\r\n" + 
					"                    </tr>\r\n" + 
					"                    <tr class=\"spacer\">\r\n" + 
					"                        <td height=\"12px\" colspan=\"2\"\r\n" + 
					"                            style=\"font-size: 12px; line-height:12px; -webkit-text-size-adjust: 100%; -ms-text-size-adjust: 100%; border-collapse: collapse; display: table-cell;\">\r\n" + 
					"                            &nbsp;</td>\r\n" + 
					"                    </tr>\r\n" + 
					"                </table>\r\n" + 
					"            </td>\r\n" + 
					"        </tr>\r\n" + 
					"    </table>\r\n" + 
					"</div>";
			
			helper.setText(textHTML, true);
			try {
				helper.setFrom(address, "Mochammad Arie Aldiansyah");
			} catch (UnsupportedEncodingException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		} catch (MessagingException e) {
			e.printStackTrace();
		}

		email.setTo(address);
		email.setFrom(address);
		email.setSubject(body);
		email.setText(body);

		//javaMailSender.send(email);
		javaMailSender.send(msg);
	}

}
