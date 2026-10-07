package com.autoservice.backend.service;

import com.autoservice.backend.dto.PartDTO;
import com.autoservice.backend.model.Part;
import com.autoservice.backend.model.PartImage;
import com.autoservice.backend.repository.PartRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import net.coobird.thumbnailator.Thumbnails;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

@Service
@RequiredArgsConstructor
public class PartImageService {
    private final PartRepository partRepository;
    private final Path rootPath = Paths.get("uploads/parts");

    @Transactional
    public PartDTO uploadPartImages(Long partId, List<MultipartFile> files) throws IOException {
        Part part = partRepository.findById(partId)
                .orElseThrow(() -> new RuntimeException("The part does not exist!"));

        if(!Files.exists(rootPath)) {
            Files.createDirectories(rootPath);
        }

        for(int i=0; i<files.size(); i++) {
            MultipartFile file = files.get(i);

            String fileName = "part_" + partId + "_" + System.currentTimeMillis() + "_" + i + ".webp";
            Path destinationPath = rootPath.resolve(fileName);

            Thumbnails.of(file.getInputStream())
                    .size(800, 800)
                    .outputFormat("webp")
                    .outputQuality(0.8)
                    .toFile(destinationPath.toFile());

            String imageUrl = "/uploads/parts/" + fileName;

            if(i==0 && part.getThumbnailUrl() == null) {
                String thumbFileName = "thumb_" + fileName;
                Path thumbPath = rootPath.resolve(thumbFileName);

                Thumbnails.of(file.getInputStream())
                        .size(200, 200)
                        .outputFormat("webp")
                        .outputQuality(0.75)
                        .toFile(thumbPath.toFile());

                part.setThumbnailUrl("/uploads/parts/" + thumbFileName);
            }

            PartImage partImage = new PartImage();
            partImage.setImageUrl(imageUrl);
            partImage.setPart(part);

            part.getImageList().add(partImage);
        }

        Part saved = partRepository.save(part);
        return PartDTO.mapToDTO(saved);
    }
}
