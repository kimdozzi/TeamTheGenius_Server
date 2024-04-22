package com.genius.gitget.global.file.service;

import com.genius.gitget.global.file.domain.FileType;
import com.genius.gitget.global.file.domain.Files;
import com.genius.gitget.global.file.dto.FileDTO;
import com.genius.gitget.global.file.dto.UpdateDTO;
import com.genius.gitget.global.util.exception.BusinessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

@Service
@Transactional(readOnly = true)
public interface FileManager {

    /**
     * Files 내에 저장된 값들을 통해 UrlResource 등으로 다운받은 후, base64로 인코딩한 결과 반환
     *
     * @param files 얻기 원하는 파일의 정보를 담고 있는 Files 객체
     * @return base64로 encode한 결과 값(문자열)
     */
    String getEncodedImage(Files files);

    /**
     * 전달한 파일 저장 후, Files 객체 형성에 필요한 정보를 담은 객체 반환
     *
     * @param multipartFile 저장하고자 전달한 파일
     * @param fileType      저장하고자하는 파일의 종류 (Topic, Instance, Profile 중 1)
     * @return Files 객체 생성에 필요한 정보(UploadDTO) 반환
     */
    FileDTO upload(MultipartFile multipartFile, FileType fileType);

    /**
     * 기존에 저장소에 저장되어 있던 파일을 특정 타입에 복사 후, Files 객체 생성에 필요한 정보들을 반환
     *
     * @param files    복사하고자하는 파일의 정보를 담고 있는 Files 객체
     * @param fileType 복사해서 적용하고 싶은 대상의 파일 타입(TOPIC/INSTANCE/PROFILE 중 택 1)
     * @return Files 객체 생성에 필요한 정보(UploadDTO) 반환
     */
    FileDTO copy(Files files, FileType fileType);

    /**
     * Files에 해당하는 이미지를 찾아서 삭제 및 새로운 이미지 저장 후, Files 내용 갱신에 필요한 정보들을 반환
     *
     * @param files         대체 하고자하는 대상 객체
     * @param multipartFile 저장하고자하는 파일
     * @return Files 내용 갱신에 필요한 정보(UpdateDTO) 반환
     */
    UpdateDTO update(Files files, MultipartFile multipartFile);

    /**
     * Files 객체 내의 정보를 활용하여 저장소(Local/S3)에서 해당 파일 삭제.
     *
     * @param files 삭제하고자하는 Files 객체
     * @throws BusinessException 삭제에 실패했을 때 발생
     */
    void deleteInStorage(Files files);
}