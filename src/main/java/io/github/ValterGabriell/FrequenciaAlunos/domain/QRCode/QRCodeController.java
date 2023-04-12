package io.github.ValterGabriell.FrequenciaAlunos.domain.QRCode;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.awt.image.BufferedImage;

@RestController
@RequestMapping("qrcode")
public class QRCodeController {

    private final QRCodeService qrCodeService;

    public QRCodeController(QRCodeService qrCodeService) {
        this.qrCodeService = qrCodeService;
    }
    @GetMapping(value = "generate", produces = MediaType.IMAGE_PNG_VALUE, params = {"studentId"})
    public ResponseEntity<BufferedImage> generateQRCode(@RequestParam String studentId) throws Exception {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.IMAGE_PNG);

        BufferedImage imageData = qrCodeService.generateQRCode(studentId);
        return new ResponseEntity<>(imageData, headers, HttpStatus.OK);
    }
}
