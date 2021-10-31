package java12.projectsalemanagement.upload;

import javax.servlet.http.Part;

import org.springframework.core.io.Resource;
import org.springframework.web.multipart.MultipartFile;

public interface FileStorageService {
	 public String storeFile(MultipartFile file);
	 public String storeFile(Part part);
	 public Resource loadFileAsResource(String fileName);
	 public boolean deleteFile(String fileName);
}
