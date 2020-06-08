package com.bystrov.rent.controller;

import org.springframework.data.domain.Page;
import org.springframework.ui.Model;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

class ControllerUtils {

    static String saveFile(MultipartFile file, String uploadPath) throws IOException {
        if(file != null && !file.isEmpty()) {
            File uploadDir = new File(uploadPath);

            if (!uploadDir.exists()) {
                uploadDir.mkdir();
            }
            String uuidFile = UUID.randomUUID().toString();
            String resultFileName = uuidFile + "." + file.getOriginalFilename();

            file.transferTo(new File(uploadPath + "/" + resultFileName));
            return resultFileName;
        }
        return null;
    }

    static void getPaginationPage(Model model, Page<?> listDTOPage) {
        model.addAttribute("listDTOPage" ,listDTOPage);
        if(listDTOPage != null) {
            int totalPages = listDTOPage.getTotalPages();
            if (totalPages > 0) {
                List<Integer> pageNumbers = IntStream.rangeClosed(1, totalPages)
                        .boxed()
                        .collect(Collectors.toList());
                model.addAttribute("pageNumbers", pageNumbers);
            }
        }
    }
}
