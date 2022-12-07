package com.junyharang.endecrypttest.common.util.endecryption;

import org.hibernate.PropertyValueException;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import static org.springframework.test.util.AssertionErrors.assertEquals;

@SpringBootTest
class DataEnDecryptionTest {

    @Test
    @DisplayName("주요 데이터 암/복호화 확인")
    void majorDataEnDecryptionCheck () throws PropertyValueException {
        String encryptionKeyValue = "기깔나는 사람들 잇딩 프로젝트";
        String plainText = "기깔나는 사람들 화이팅!!";

        String encryptionKey = DataEnDecryption.base64Encoder(encryptionKeyValue);
        System.out.println("Base64 Encoding된 encryptionKeyValue : " + encryptionKey);

        String encryptionBoardContent = DataEnDecryption.dataEnDecrypt(encryptionKey, plainText, 1);
        System.out.println("암호화된 게시글 내용 : " + encryptionBoardContent);

        String decryptionBoardContent = DataEnDecryption.dataEnDecrypt(encryptionKey, encryptionBoardContent, 2);
        System.out.println("복호화된 게시글 내용 : " + decryptionBoardContent);
        assertEquals ("복호화가 정상적으로 진행되지 않았습니다.", plainText, decryptionBoardContent);
    }
}