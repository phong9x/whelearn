package org.trams.web.common.utils;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics2D;

import java.awt.RenderingHints;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Date;
import java.util.EnumMap;
import java.util.Map;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import javax.imageio.ImageIO;
import javax.servlet.ServletContext;

import org.apache.commons.codec.binary.Base64;
import org.apache.commons.io.FilenameUtils;
import org.springframework.web.multipart.MultipartFile;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.EncodeHintType;
import com.google.zxing.WriterException;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.QRCodeWriter;
import com.google.zxing.qrcode.decoder.ErrorCorrectionLevel;


public class FileUtils {


	public static String saveFileOrigin(MultipartFile file,String  folder, ServletContext servletContext) {
		String imgPatch = "";
		try {
			String fileName = null;
			InputStream inputStream = null;
			OutputStream outputStream = null;

			inputStream = file.getInputStream();
			String separator = java.nio.file.FileSystems.getDefault().getSeparator();
			String patch = separator+"data"+separator+"opt"+separator+"was"+separator+"whelearn"+separator+"webapps"+separator+"images"+separator;
			if(folder !=null && !folder.equals("")){
				patch=patch+folder+separator;
			}
			String fileNewName = System.nanoTime() + "." + FilenameUtils.getExtension(file.getOriginalFilename());
			fileName = patch  + fileNewName;
			outputStream = new FileOutputStream(fileName);
			int readBytes = 0;
			byte[] buffer = new byte[10000];
			while ((readBytes = inputStream.read(buffer, 0, 10000)) != -1) {
				outputStream.write(buffer, 0, readBytes);
			}
			outputStream.close();
			inputStream.close();
			if(folder !=null && !folder.equals("")){
				imgPatch = "images/"+folder+"/" + fileNewName;
			}else{
				imgPatch = "images/" + fileNewName;
			}
		} catch (Exception e) {
			System.out.println(e);
		}
		
		return ConstantUtils.getConfig("domain")+ imgPatch;
	}

	public static String uploadImageEditor(MultipartFile file,String folder, ServletContext servletContext) {
		String imgPatch = "";
		try {
			String fileName = null;
			InputStream inputStream = null;
			OutputStream outputStream = null;

			inputStream = file.getInputStream();
			String separator = java.nio.file.FileSystems.getDefault().getSeparator();
			String patch = separator+"data"+separator+"opt"+separator+"was"+separator+"whelearn"+separator+"webapps"+separator+"images"+separator;
			if(folder !=null && !folder.equals("")){
				patch=patch+folder+separator;
			}
			String fileNewName = System.nanoTime() + "." + FilenameUtils.getExtension(file.getOriginalFilename());
			fileName = patch  + fileNewName;
			outputStream = new FileOutputStream(fileName);
			int readBytes = 0;
			byte[] buffer = new byte[10000];
			while ((readBytes = inputStream.read(buffer, 0, 10000)) != -1) {
				outputStream.write(buffer, 0, readBytes);
			}
			outputStream.close();
			inputStream.close();
			if(folder !=null && !folder.equals("")){
				imgPatch = "images/"+folder+"/" + fileNewName;
			}else{
				imgPatch = "images/" + fileNewName;
			}
		} catch (Exception e) {
			System.out.println(e);
		}
		return ConstantUtils.getConfig("domain")+ imgPatch;
	}

	public static String saveFileResize(MultipartFile file,String folder,int wdith, int height, ServletContext servletContext) {
		String imgPatch = "";
		try {
			String fileName = null;
			InputStream inputStream = null;
			OutputStream outputStream = null;

			inputStream = file.getInputStream();
			String separator = java.nio.file.FileSystems.getDefault().getSeparator();
			String patch = separator+"data"+separator+"opt"+separator+"was"+separator+"whelearn"+separator+"webapps"+separator+"images"+separator;
			if(folder !=null && !folder.equals("")){
				patch=patch+folder+separator;
			}
			String fileNewName = System.nanoTime() + "." + FilenameUtils.getExtension(file.getOriginalFilename());
			fileName = patch  + fileNewName;
			outputStream = new FileOutputStream(fileName);
			int readBytes = 0;
			byte[] buffer = new byte[10000];
			while ((readBytes = inputStream.read(buffer, 0, 10000)) != -1) {
				outputStream.write(buffer, 0, readBytes);
			}
			outputStream.close();
			inputStream.close();
			
			if(folder !=null && !folder.equals("")){
				imgPatch = "images/"+folder+"/" + fileNewName;
			}else{
				imgPatch = "images/" + fileNewName;
			}
		} catch (Exception e) {
			System.out.println(e);

		}

		return imgPatch;
	}
	
	public static String saveFileResize(File originalFime, int width, int height) {
		String patch = "";
		try {

			BufferedImage originalImage = ImageIO.read(originalFime);
			int type = originalImage.getType() == 0 ? BufferedImage.TYPE_INT_ARGB : originalImage.getType();
			BufferedImage scaleImage = scaleImage(originalImage, width, height, Color.white);
			BufferedImage resizeImageJpg = remakeImage(scaleImage, width, height, type);
			ImageIO.write(resizeImageJpg, "jpg", originalFime);

		} catch (Exception e) {

		}
		return patch;
	}
	
	private static BufferedImage scaleImage(BufferedImage img, int maxWidth, int maxHeight, Color background) {

		int imgWidth = img.getWidth();
		int imgHeight = img.getHeight();

		Dimension largestDimension = new Dimension(maxWidth, maxHeight);

		float aspectRatio = (float) imgWidth / imgHeight;

		if (imgWidth > maxWidth || imgHeight > maxHeight) {
			if ((float) largestDimension.width / largestDimension.height > aspectRatio) {
				largestDimension.width = (int) Math.ceil(largestDimension.height * aspectRatio);
			} else {
				largestDimension.height = (int) Math.ceil(largestDimension.width / aspectRatio);
			}

			imgWidth = largestDimension.width;
			imgHeight = largestDimension.height;
		}

		// if (imgWidth*maxHeight < imgHeight*maxWidth) {
		// maxWidth = imgWidth*maxHeight/imgHeight;
		// } else {
		// maxHeight = imgHeight*maxWidth/imgWidth;
		// }

		BufferedImage newImage = new BufferedImage(imgWidth, imgHeight, BufferedImage.TYPE_3BYTE_BGR);
		Graphics2D g = newImage.createGraphics();
		try {
			g.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BICUBIC);
			g.setBackground(background);
			g.clearRect(0, 0, imgWidth, imgHeight);
			g.drawImage(img, 0, 0, imgWidth, imgHeight, null);
		} finally {
			g.dispose();
		}
		return newImage;
	}
	
	private static BufferedImage remakeImage(BufferedImage originalImage, int thumbWidth, int thumbHeight, int type) {
		int width = originalImage.getWidth();
		int height = originalImage.getHeight();
		BufferedImage resizedImage = new BufferedImage(thumbWidth, thumbHeight, type);
		Graphics2D g = resizedImage.createGraphics();
		g.setColor(Color.WHITE);
		g.fillRect(0, 0, thumbWidth, thumbHeight);
		g.setColor(Color.BLACK);
		g.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BICUBIC);
		g.drawImage(originalImage, (thumbWidth - width) / 2, (thumbHeight - height) / 2, width, height, null);

		g.dispose();

		return resizedImage;
	}
}
