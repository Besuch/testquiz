package com.green.testquiz.converter;

import com.green.testquiz.datalayer.entities.Option;
import com.green.testquiz.presentation.OptionDto;
import org.springframework.stereotype.Component;

@Component
public class OptionConverter {
    public OptionDto toDto(Option option) {
        return OptionDto.builder()
                .optionId(option.getOptionId().toHexString())
                .text(option.getText())
                .build();
    }
}
