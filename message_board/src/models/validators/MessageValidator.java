package models.validators;

import java.util.ArrayList;
import java.util.List;

import models.Message;

public class MessageValidator {
    // �o���f�[�V���������s����
    public static List<String> validate(Message m) {
        List<String> errors = new ArrayList<String>();

        String title_error = validateTitle(m.getTitle());
        if(!title_error.equals("")) {
            errors.add(title_error);
        }

        String content_error = validateContent(m.getContent());
        if(!content_error.equals("")) {
            errors.add(content_error);
        }

        return errors;
    }

    // �^�C�g���̕K�{���̓`�F�b�N
    private static String validateTitle(String title) {
        if(title == null || title.equals("")) {
            return "�^�C�g������͂��Ă��������B";
        }

        return "";
    }

    // ���b�Z�[�W�̕K�{���̓`�F�b�N
    private static String validateContent(String content) {
        if(content == null || content.equals("")) {
            return "���b�Z�[�W����͂��Ă��������B";
        }

        return "";
    }
}