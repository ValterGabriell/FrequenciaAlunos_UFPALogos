package io.github.ValterGabriell.FrequenciaAlunos.domain.QRCode;

import org.springframework.http.*;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("qrcode")
public class QRCodeController {

    private final QRCodeService qrCodeService;

    public QRCodeController(QRCodeService qrCodeService) {
        this.qrCodeService = qrCodeService;
    }

    @GetMapping(value = "generate", params = {"studentId"})
    public ResponseEntity<String> generateQRCode(@RequestParam String studentId) throws Exception {
        String imageData = qrCodeService.generateQRCode(studentId);
        return ResponseEntity.ok().body(imageData);
    }

}
