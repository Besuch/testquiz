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

    @Test
    public void fromDto() {
        optionConverter = new OptionConverter();
        ObjectId objectId = new ObjectId();

        OptionDto optionDto = OptionDto.builder()
                .optionId(objectId.toHexString())
                .text("Option")
                .isCorrect(true)
                .isChecked(false)
                .build();

        Option expected = Option.builder().optionId(objectId).text("Option").isChecked(false).isCorrect(true).build();

        Option actual = optionConverter.fromDto(optionDto);

        Assert.assertEquals(expected, actual);
    }
}