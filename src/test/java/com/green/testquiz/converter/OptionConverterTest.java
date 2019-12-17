package com.green.testquiz.converter;

import com.green.testquiz.datalayer.entities.Option;
import com.green.testquiz.presentation.OptionDto;
import org.bson.types.ObjectId;
import org.junit.Assert;
import org.junit.jupiter.api.Test;

public class OptionConverterTest {

    private OptionConverter optionConverter;

    @Test
    public void toDto() {
        optionConverter = new OptionConverter();
        ObjectId objectId = new ObjectId();
        Option option = new Option(objectId, "Option", true, false);

        OptionDto expected = OptionDto.builder()
                .optionId(objectId.toHexString())
                .text("Option")
                .isChecked(false)
                .build();

        OptionDto actual = optionConverter.toDto(option);

        Assert.assertEquals(expected, actual);
    }
}