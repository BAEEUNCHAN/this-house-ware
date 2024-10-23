package com.contractor.app.common.service;

import java.awt.image.BufferedImage;
import java.awt.image.ColorModel;
import java.awt.image.ComponentColorModel;
import java.awt.image.DataBuffer;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.xml.bind.DatatypeConverter;

/**
 * Base64로 압축된 데이터를 디코딩하여 저장한다.
 */
public class Base64ToImgDecodeUtil {

	public static boolean decoder(String base64, String target, String filename) {
		// 디렉토리가 없다면 생성.
		File directory = new File(target);
		if (!directory.exists()) {
			directory.mkdirs();
		}
		String data = base64.split(",")[1];
		byte[] imageBytes = DatatypeConverter.parseBase64Binary(data);
		try {
			BufferedImage bufImg = ImageIO.read(new ByteArrayInputStream(imageBytes));
			// RGB 색상 모델 생성
			ColorModel colorModel = new ComponentColorModel(bufImg.getColorModel().getColorSpace(),
					bufImg.getColorModel().hasAlpha(), bufImg.getColorModel().isAlphaPremultiplied(),
					bufImg.getColorModel().getTransparency(), DataBuffer.TYPE_BYTE);
			// 새로운 BufferedImage 생성
			BufferedImage image = new BufferedImage(colorModel, bufImg.getRaster(), false, null);
			// ImageIO.setUseCache(false);
			ImageIO.write(image, "png", new File(target + "/" + filename));
		} catch (IOException e) {
			e.printStackTrace();
			return true;
		}
		return true;
	}
}