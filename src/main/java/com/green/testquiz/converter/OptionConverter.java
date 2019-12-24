package com.green.testquiz.converter;

import com.green.testquiz.datalayer.entities.Option;
import com.green.testquiz.presentation.OptionDto;
import org.bson.types.ObjectId;
import org.springframework.stereotype.Component;

@Component
public class OptionConverter {
    public OptionDto toDto(Option option) {
        return OptionDto.builder()
                .optionId(option.getOptionId().toHexString())
                .text(option.getText())
                .build();
    }

    public Option fromDto(OptionDto optionDto) {
        ObjectId optionId = optionDto.getOptionId() != null ? new ObjectId(optionDto.getOptionId()) : new ObjectId();
        return Option.builder()
                .text(optionDto.getText())
                .isChecked(optionDto.isChecked())
                .isCorrect(optionDto.isCorrect())
                .optionId(optionId)
                .build();
    }
}
