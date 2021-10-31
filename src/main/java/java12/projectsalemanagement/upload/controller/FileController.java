package java12.projectsalemanagement.upload.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.Part;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java12.projectsalemanagement.upload.FileStorageService;
import java12.projectsalemanagement.upload.dto.UploadFileResponse;

@RestController
public class FileController {

	private static final Logger logger = LoggerFactory.getLogger(FileController.class);

	@Autowired
	private FileStorageService fileStorageService;

	@PostMapping("/uploadFile")
	public ResponseEntity<UploadFileResponse> uploadFile(@RequestParam("file") MultipartFile file) {
		String fileName = fileStorageService.storeFile(file);

		String fileDownloadUri = ServletUriComponentsBuilder.fromCurrentContextPath().path("/downloadFile/")
				.path(fileName).toUriString();

		return new ResponseEntity<UploadFileResponse>(
				new UploadFileResponse(fileName, fileDownloadUri, file.getContentType(), file.getSize()),
				HttpStatus.OK);
	}

	@PostMapping("/uploadMultipleFiles")
	public ResponseEntity<List<UploadFileResponse>> uploadMultipleFiles(@RequestParam("file") MultipartFile[] files) {
		return new ResponseEntity<>(
				Arrays.asList(files).stream().map(file -> uploadFileMethod(file)).collect(Collectors.toList()),
				HttpStatus.OK);
//		 

	}

	@GetMapping("/downloadFile/{fileName:.+}")
	public ResponseEntity<Resource> downloadFile(@PathVariable String fileName, HttpServletRequest request) {
		// Load file as Resource
		Resource resource = fileStorageService.loadFileAsResource(fileName); 

		// Try to determine file's content type
		String contentType = null;
		try {
			contentType = request.getServletContext().getMimeType(resource.getFile().getAbsolutePath());
		} catch (IOException ex) {
			logger.info("Could not determine file type.");
		}

		// Fallback to the default content type if type could not be determined
		if (contentType == null) {
			contentType = "application/octet-stream";
		}

		return ResponseEntity.ok().contentType(MediaType.parseMediaType(contentType))
				.header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + resource.getFilename() + "\"")
				.body(resource);
	}
	
	@GetMapping("/deleteFile/{fileName:.+}")
	public ResponseEntity<Map<String,Object>> deleteFile(@PathVariable String fileName, HttpServletRequest request) {
		// Load file as Resource
		boolean isSuccess = fileStorageService.deleteFile(fileName);
		Map<String, Object> map;
		HttpStatus statusCode;
		if(isSuccess) {
			statusCode = HttpStatus.OK;
			map = new HashMap<>();
			map.put("statusCode", 200);
			map.put("message", "Delete file success");
		} else {
			statusCode = HttpStatus.BAD_REQUEST;
			map = new HashMap<>();
			map.put("statusCode", 400);
			map.put("message", "Delete file failure");
		}
		return new ResponseEntity<>(map, statusCode);
	}

	private UploadFileResponse uploadFileMethod(@RequestParam("file") MultipartFile file) {
		String fileName = fileStorageService.storeFile(file);

		String fileDownloadUri = ServletUriComponentsBuilder.fromCurrentContextPath().path("/downloadFile/")
				.path(fileName).toUriString();

		return new UploadFileResponse(fileName, fileDownloadUri, file.getContentType(), file.getSize());
	}

	@PostMapping("/uploadFileDisk")
	public ResponseEntity<List<UploadFileResponse>> uploadFileDisk(HttpServletRequest request) {
		List<UploadFileResponse> list = new ArrayList<UploadFileResponse>();
		Collection<Part> parts;
		try {
			parts = request.getParts();
			for (Iterator<Part> iterator = parts.iterator(); iterator.hasNext();) {
				Part part = (Part) iterator.next();
				String fileName = fileStorageService.storeFile(part);

				String fileDownloadUri = ServletUriComponentsBuilder.fromCurrentContextPath().path("/downloadFile/")
						.path(fileName).toUriString();
				
				list.add(new UploadFileResponse(fileName, fileDownloadUri, part.getContentType(), part.getSize()));
			}
		} catch (IOException | ServletException e) {
			return new ResponseEntity<>(null,HttpStatus.BAD_REQUEST);
		} 

		return new ResponseEntity<>(list, HttpStatus.OK);

	}
}