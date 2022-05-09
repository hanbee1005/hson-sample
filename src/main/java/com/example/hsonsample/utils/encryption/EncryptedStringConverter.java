package com.example.hsonsample.utils.encryption;

import javax.persistence.AttributeConverter;

public class EncryptedStringConverter implements AttributeConverter<String, byte[]> {
    @Override
    public byte[] convertToDatabaseColumn(String attribute) {
        if (attribute == null)
            return null;

        return EncryptionUtil.encryptionService.encrypt(attribute);
    }

    @Override
    public String convertToEntityAttribute(byte[] dbData) {
        if (dbData == null)
            return null;

        return EncryptionUtil.encryptionService.decrypt(dbData);
    }
}
